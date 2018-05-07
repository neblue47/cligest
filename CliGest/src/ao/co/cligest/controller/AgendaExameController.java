package ao.co.cligest.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ao.co.cligest.dao.AgendaConsultaDAO;
import ao.co.cligest.dao.AgendaExameDAO;
import ao.co.cligest.dao.Formatando;
import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.interfaces.IAgendaConsulta;
import ao.co.cligest.interfaces.IAgendaExame;

/**
 * Servlet implementation class AgendaExameController
 */
@WebServlet("/AgendaExameController")
public class AgendaExameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IAgendaExame _agendaExameDAO = new AgendaExameDAO();
	Formatando ft = new Formatando();    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgendaExameController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ss = request.getSession();
		String acao = request.getParameter("acao");
		try {
		if(acao!=null && acao.equals("alterar"))
		{
			String codcs = request.getParameter("codcs");
			int temp = Integer.parseInt(codcs);
			
				//Paciente pac = _agendaExameDAO.listaRequisicaoExamePorId(temp)
				//_agendaConsultaDAO.confirmarConsulta(pac);
				ss.setAttribute("msgOK", "msgOK");
		}
		if(acao!=null && acao.equals("cancel"))
		{
			String codcs = request.getParameter("codcs");
			int temp = Integer.parseInt(codcs);
			
				//Paciente pac = _agendaExameDAO.listaConsultaPorId(temp);
				//_agendaConsultaDAO.confirmarConsulta(pac);
				ss.setAttribute("msgOK", "msgOK");
		}
		} catch (Exception e) {
			e.printStackTrace();
			ss.setAttribute("msgNOK", "msgNOK");
		}
		response.sendRedirect("navegacaoag?mods=ag&pag=listagexa");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ss = request.getSession();
		Exames p = new Exames();
		String pac = request.getParameter("pac");
		String grExames = request.getParameter("grExames");
		String dtConsulta = ft.dataToPadrao(request.getParameter("dtConsulta"));
		String numero = _agendaExameDAO.retornaNumExame();
		String servico [] = request.getParameterValues("id_exame");
		String hrConsulta = request.getParameter("hrConsulta");
		String usuario = request.getParameter("usuario");
		String nota = request.getParameter("notas");

		try {
				p.setFK_paciente(Integer.parseInt(pac));
				p.setFK_grupo_analise_clinica(Integer.parseInt(grExames));
				p.setRecomendacao(nota);
				p.setObservacao_recomendacao(nota);
				p.setData_darecolha(ft.data(dtConsulta));
				p.setHora_agendamento(hrConsulta);
				p.setFK_funcionario(Integer.parseInt(usuario));
				p.setNumero_do_exame(numero); 
				if(servico!=null)
				{
					int ultimoId = _agendaExameDAO.inserirnumerodoexame(p);
					for(String s : servico)
					{
						p.setFK_servico_exame(Integer.parseInt(s));
						p.setFK_numero_do_servico_de_exame(ultimoId);
						_agendaExameDAO.requisitarExame(p);
					}
				}
				
				
				
				ss.setAttribute("msgOk", "msgOK");		
			} 
		catch (Exception e) {
			e.printStackTrace();
			ss.setAttribute("msgNOK", "msgNOK");
		}
		response.sendRedirect("navegacaoag?mods=ag&pag=listagexa");
	}

}
