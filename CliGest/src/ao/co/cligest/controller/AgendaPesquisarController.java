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
import ao.co.cligest.interfaces.IAgendaConsulta;

@WebServlet("/AgendaPesquisarController")
public class AgendaPesquisarController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	IAgendaConsulta _agendaConsultaDAO = new AgendaConsultaDAO();
	private ConsultaDAO _consulta = new ConsultaDAO();
	Formatando ft = new Formatando();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgendaPesquisarController() {
        super();
        // TODO Auto-generated constructor stub
    }
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ss = request.getSession();		 
		/*
		 * String pac = request.getParameter("nomPaciente"); String doutor =
		 * request.getParameter("nomDoutor"); String dtConsulta =
		 * ft.dataToPadrao(request.getParameter("dtConsulta"));
		 */
		String encaminho = "navegacaoag?mods=ag&pag=listagen";
		try {
			encaminho = "navegacaoag?mods=ag&pag=listagen";
		} 
	catch (Exception e) {
		e.printStackTrace();
	}
	response.sendRedirect(encaminho);
	}
    
    
}
