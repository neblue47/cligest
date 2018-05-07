package ao.co.cligest.ajaxcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ao.co.cligest.dao.AgendaConsultaDAO;
import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.interfaces.IAgendaConsulta;

/**
 * Servlet implementation class AjaxAgendaController
 */
@WebServlet("/AjaxAgendaController")
public class AjaxAgendaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IAgendaConsulta _agendaConsulta = new AgendaConsultaDAO();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxAgendaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =  response.getWriter();
		StringBuilder sb = new StringBuilder();
			 List<Paciente> detalhesFac = _agendaConsulta.listaAgendasPacientes();
			 for(Paciente p : detalhesFac){
				 sb.append(p.getFK_consulta_marcada()+"|"+p.getNome_paciente()+"|"+p.getData_em_string()+"|"+p.getHora_daconfirmacao()+"|"+p.getColor()+";");
			 }
		
		response.setContentType("text/plain");  
	  	response.setCharacterEncoding("UTF-8"); 
		out.write(sb.toString());
	}

}
