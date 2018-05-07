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
import ao.co.cligest.dao.ExamesDAO;
import ao.co.cligest.entidades.CobrarSeguros;
import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.interfaces.IAgendaConsulta;

/**
 * Servlet implementation class AjaxExameController
 */
@WebServlet("/AjaxExameController")
public class AjaxExameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExamesDAO _exameDAO = new ExamesDAO();  
	 private IAgendaConsulta _agendaConsulta = new AgendaConsultaDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxExameController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ss = request.getSession();
		PrintWriter out =  response.getWriter();
		StringBuilder sb = new StringBuilder();
		String grExames = request.getParameter("grExames");
		
		if(grExames!=null){
			 int codigo = Integer.parseInt(grExames);
			 List<Exames> detalhesFac = _exameDAO.buscarServicosExamesPorGrupo(codigo);
			 for(Exames ex : detalhesFac){
				 sb.append(ex.getFK_exame()+"|"+ex.getDescricao_servico_analise()+";");
			 }
		}
		
		response.setContentType("text/plain");  
	  	response.setCharacterEncoding("UTF-8"); 
		out.write(sb.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out =  response.getWriter();
		StringBuilder sb = new StringBuilder();
			 List<Paciente> detalhesFac = _agendaConsulta.listaAgendasPacientes();
			 for(Paciente p : detalhesFac){
				 /** {
				 	"title": "Long Event", 
				 	"start": "2018-04-07",
				 	"end":"2018-04-10" 
				 	},
				 */
				 sb.append("{'id':'"+p.getFK_consulta_marcada()+"',"+"'title':'"+p.getNome_paciente()+"',"+"'start':'"+p.getData_em_string()+"'},");
				// sb.append(p.getFK_consulta_marcada()+"|"+p.getNome_paciente()+"|"+p.getData_em_string()+"|"+p.getHora_daconfirmacao()+"|"+p.getColor()+";");
			 }
		
		response.setContentType("text/plain");  
	  	response.setCharacterEncoding("UTF-8"); 
		out.write(sb.toString());
	}

}
