package ao.co.cligest.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ao.co.cligest.dao.AgendaConsultaDAO;
import ao.co.cligest.dao.ConsultaDAO;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Triagem;
import ao.co.cligest.interfaces.IAgendaConsulta;

/**
 * Servlet implementation class QueixaHistorController
 */
@WebServlet("/QueixaAtencedenteController")
public class QueixaAntecedenteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IAgendaConsulta _agendaConsulta = new AgendaConsultaDAO();  
	private ConsultaDAO _consulta = new ConsultaDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueixaAntecedenteController() {
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
		String queixas = request.getParameter("queixas");
		String historial = request.getParameter("historial");
		
		try {
			Triagem tn = new Triagem();
			int cods = _agendaConsulta.getServicoConfirmadaPorId(Integer.parseInt(codc));
			
			tn.setFK_paciente(Integer.parseInt(codp));
			tn.setFk_funcionario(Integer.parseInt(codm));
			tn.setFK_doutor(Integer.parseInt(codm));
			tn.setDescricao(queixas);
			tn.setQueixa(queixas);
			tn.setHistorial(historial);
			tn.setId_servico(cods);
			tn.setId_cons_conf(Integer.parseInt(codc));
			int cons = _consulta.novaConsultas(tn);
			tn.setId_consulta(cons);
			_consulta.novaQueixaHistorial(tn);
			
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
		String queixas = request.getParameter("ant_pessoal");
		String historial = request.getParameter("ant_familiar");
		
		try {
			Triagem tn = new Triagem();
			int cods = _agendaConsulta.getServicoConfirmadaPorId(Integer.parseInt(codc));
			
			tn.setFK_paciente(Integer.parseInt(codp));
			tn.setFk_funcionario(Integer.parseInt(codm));
			tn.setFK_doutor(Integer.parseInt(codm));
			tn.setDescricao(queixas);
			tn.setQueixa(queixas);
			tn.setHistorial(historial);
			tn.setId_servico(cods);
			tn.setId_cons_conf(Integer.parseInt(codc));
			int cons = _consulta.novaConsultas(tn);
			tn.setId_consulta(cons);
			_consulta.novaAntecedentes(tn);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		response.sendRedirect("navegacaopd?mods=pd&pag=newcons&codp="+codp+"&codc="+codc);
	}

}
