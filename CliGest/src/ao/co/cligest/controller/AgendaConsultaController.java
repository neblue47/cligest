package ao.co.cligest.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ao.co.cligest.dao.AgendaConsultaDAO;
import ao.co.cligest.dao.ConsultaDAO;
import ao.co.cligest.dao.Formatando;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Triagem;
import ao.co.cligest.interfaces.IAgendaConsulta; 

/**
 * Servlet implementation class AgendaConsultaController
 */
@WebServlet("/AgendaConsultaController")
public class AgendaConsultaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IAgendaConsulta _agendaConsultaDAO = new AgendaConsultaDAO();
	private ConsultaDAO _consulta = new ConsultaDAO();
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
		String encaminha = "navegacaoag?mods=ag&pag=listagen";
		try {
		if(acao!=null && acao.equals("confirmar"))
		{
			String codcs = request.getParameter("codcs");
			int temp = Integer.parseInt(codcs);
			int usuario = (int) ss.getAttribute("usuario");
				Paciente pac = _agendaConsultaDAO.listaConsultaPorId(temp);
				pac.setFK_funcionario(usuario);
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
		if(acao!=null && acao.equals("finalizar")){
			String codp = request.getParameter("pacInt");
			String codm = request.getParameter("funInt");
			String codc = request.getParameter("conInt");
			
			Triagem tn = new Triagem();
			int ultimoId = _consulta.xequeConsulta(Integer.parseInt(codp), Integer.parseInt(codc));
			tn.setFK_paciente(Integer.parseInt(codp));
			tn.setFk_funcionario(Integer.parseInt(codm));
			tn.setId_consulta(ultimoId);
			tn.setFk_consulta_confirmada(Integer.parseInt(codc));
			
			_consulta.finalizarConsulta(tn);
			encaminha = "navegacaopd?mods=pd&pag=conspac"; 
		}
		} catch (Exception e) {
			e.printStackTrace();
			ss.setAttribute("msgNOK", "msgNOK");
		}
		response.sendRedirect(encaminha);
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
