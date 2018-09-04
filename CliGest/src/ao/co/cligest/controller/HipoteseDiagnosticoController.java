package ao.co.cligest.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ao.co.cligest.dao.AgendaConsultaDAO;
import ao.co.cligest.dao.ConsultaDAO;
import ao.co.cligest.entidades.Triagem;
import ao.co.cligest.interfaces.IAgendaConsulta;

/**
 * Servlet implementation class HipoteseDiagnosticoController
 */
@WebServlet("/HipoteseDiagnosticoController")
public class HipoteseDiagnosticoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IAgendaConsulta _agendaConsulta = new AgendaConsultaDAO();  
	private ConsultaDAO _consulta = new ConsultaDAO();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HipoteseDiagnosticoController() {
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
		String descricao = request.getParameter("queixas");
		String doencasHipotica []= request.getParameterValues("hipotese");
		try {
			if(doencasHipotica!= null && doencasHipotica.length>0){
				Triagem tn = new Triagem();
				int cods = _agendaConsulta.getServicoConfirmadaPorId(Integer.parseInt(codc));
				tn.setFK_paciente(Integer.parseInt(codp));
				tn.setFk_funcionario(Integer.parseInt(codm));
				tn.setFK_doutor(Integer.parseInt(codm));
				tn.setDescricao(descricao);
				tn.setId_servico(cods);
				tn.setId_cons_conf(Integer.parseInt(codc));
				int cons = _consulta.novaConsultas(tn);
				tn.setId_consulta(cons);
				for(String s : doencasHipotica){
					 tn.setFk_cid(s);
					_consulta.novaQueixaHistorial(tn);
				}
				
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
