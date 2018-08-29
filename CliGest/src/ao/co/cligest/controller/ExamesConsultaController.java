package ao.co.cligest.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ao.co.cligest.dao.AgendaConsultaDAO;
import ao.co.cligest.dao.ConsultaDAO;
import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Triagem;
import ao.co.cligest.interfaces.IAgendaConsulta;
import ao.co.cligest.dao.ExamesDAO;

/**
 * Servlet implementation class ExamesConsultaController
 */
@WebServlet("/ExamesConsultaController")
public class ExamesConsultaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IAgendaConsulta _agendaConsulta = new AgendaConsultaDAO();  
	private ConsultaDAO _consulta = new ConsultaDAO(); 
	private ExamesDAO _exames = new ExamesDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExamesConsultaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codp = request.getParameter("pacInt");
		String codm = request.getParameter("funInt");
		String codc = request.getParameter("conInt");
		String [] exams = request.getParameterValues("exames");
		String obs = request.getParameter("observacao");
		 
		try {
			
			if (exams!=null && exams.length>0) {
				Triagem tn = new Triagem();
				int cods = _agendaConsulta.getServicoConfirmadaPorId(Integer.parseInt(codc));
				
				tn.setFK_paciente(Integer.parseInt(codp));
				tn.setFk_funcionario(Integer.parseInt(codm));
				tn.setFK_doutor(Integer.parseInt(codm));
				tn.setId_servico(cods);
				tn.setId_cons_conf(Integer.parseInt(codc));
				int cons = _consulta.novaConsultas(tn);
				
				// Criando numero da requisicao
				 int numero = new ExamesDAO().getNumExmRequisitado(tn.getFK_paciente());
				 String numfactura = "EXACONS" + numero;
				 	
				 Exames ex = new Exames();
				 ex.setNumero_do_exame(numfactura);
				 ex.setFK_paciente(Integer.parseInt(codp));
				 ex.setFk_profissional(tn.getFk_funcionario());
					
				 int FK_numero_do_servico_de_exame = _exames.getNumExmRequisitado(tn.getFK_paciente());
				 
				 if(FK_numero_do_servico_de_exame == 0 )
					 FK_numero_do_servico_de_exame = _exames.inserirnumerodoexame(ex);
				 
				tn.setId_consulta(cons);
				for(String exm : exams){
					tn.setId_analise(Integer.parseInt(exm));
					tn.setFK_numero_requisicao(FK_numero_do_servico_de_exame);
					_consulta.novoExamesclinicos(tn);
				}
				_consulta.ExamescliObs(tn);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("navegacaopd?mods=pd&pag=newcons&codp="+codp+"&codc="+codc);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codp = request.getParameter("pacInt");
		String codm = request.getParameter("funInt");
		String codc = request.getParameter("conInt");
		String objectivo = request.getParameter("objectivo");
		String cabeca = request.getParameter("cabeca");
		String pescoco = request.getParameter("pescoco");
		String torax = request.getParameter("torax");
		String abdomen = request.getParameter("abdomen");
		String urina = request.getParameter("urinario");
		String membrossup = request.getParameter("membrossup");
		String membrosinf = request.getParameter("membrosinf");
		String sistemanev = request.getParameter("sistemanev");
		
		try {
			Triagem tn = new Triagem();
			int cods = _agendaConsulta.getServicoConfirmadaPorId(Integer.parseInt(codc));
			
			tn.setFK_paciente(Integer.parseInt(codp));
			tn.setFk_funcionario(Integer.parseInt(codm));
			tn.setFK_doutor(Integer.parseInt(codm));
			tn.setId_servico(cods);
			tn.setId_cons_conf(Integer.parseInt(codc));
			tn.setExa_cabeca(cabeca);
			tn.setExa_pescoco(pescoco);
			tn.setExa_torax(torax);
			tn.setExa_abdomen(abdomen);
			tn.setExa_urinario(urina);
			tn.setExa_membSup(membrossup);
			tn.setExa_membInf(membrosinf);
			tn.setSistema_nervoso(sistemanev);
			tn.setObjectivo_geral(objectivo);
			int cons = _consulta.novaConsultas(tn);
			tn.setId_consulta(cons);
			_consulta.novosExamesFisicos(tn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		response.sendRedirect("navegacaopd?mods=pd&pag=newcons&codp="+codp+"&codc="+codc);
	}

}
