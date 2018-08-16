package ao.co.cligest.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ao.co.cligest.dao.AgendaConsultaDAO;
import ao.co.cligest.dao.Formatando;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.interfaces.IAgendaConsulta; 

/**
 * Servlet implementation class AgendaConsultaController
 */
@WebServlet("/AgendaConsultaController")
public class AgendaConsultaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IAgendaConsulta _agendaConsultaDAO = new AgendaConsultaDAO();
	Formatando ft = new Formatando();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgendaConsultaController() {
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
		if(acao!=null && acao.equals("confirmar"))
		{
			String codcs = request.getParameter("codcs");
			int temp = Integer.parseInt(codcs);
			
				Paciente pac = _agendaConsultaDAO.listaConsultaPorId(temp);
				_agendaConsultaDAO.confirmarConsulta(pac);
				ss.setAttribute("msgOK", "msgOK");
		}
		if(acao!=null && acao.equals("cancel"))
		{
			String codcs = request.getParameter("codcs");
			int temp = Integer.parseInt(codcs);
			
				Paciente pac = _agendaConsultaDAO.listaConsultaPorId(temp);
				_agendaConsultaDAO.confirmarConsulta(pac);
				ss.setAttribute("msgOK", "msgOK");
		}
		} catch (Exception e) {
			e.printStackTrace();
			ss.setAttribute("msgNOK", "msgNOK");
		}
		response.sendRedirect("navegacaoag?mods=ag&pag=listagen");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ss = request.getSession();
		Paciente p = new Paciente();
		String pac = request.getParameter("pac");
		String doutor = request.getParameter("doutor");
		String dtConsulta = ft.dataToPadrao(request.getParameter("dtConsulta"));
		String number = request.getParameter("number");
		String servico = request.getParameter("servico");
		String hrConsulta = request.getParameter("hrConsulta");
		String usuario = request.getParameter("usuario");
		String encaminho = "";
		try {
				p.setFK_paciente(Integer.parseInt(pac));
				p.setFK_doutor(Integer.parseInt(doutor));
				p.setFK_servico(Integer.parseInt(servico));
				p.setData_agendamento(ft.dataSql(dtConsulta));
				if(!number.isEmpty())
				{
					p.setTelefone(Long.parseLong(number));
					p.setTelefonep(Long.parseLong(number));
				}
				p.setHora_daconfirmacao(hrConsulta);
				p.setFK_funcionario(Integer.parseInt(usuario));
				_agendaConsultaDAO.adicionaConsulta(p);
				ss.setAttribute("msgOk", "msgOK");	
				encaminho = "navegacaoag?mods=ag&pag=listagen";
			} 
		catch (Exception e) {
			e.printStackTrace();
			ss.setAttribute("msgNOK", "msgNOK");
			encaminho = "navegacaoag?mods=ag&pag=novoagen";
		}
		response.sendRedirect(encaminho);
	}

}
