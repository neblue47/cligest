package ao.co.cligest.navegacao;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ao.co.cligest.dao.AgendaConsultaDAO;
import ao.co.cligest.dao.ConfirmarDAO;
import ao.co.cligest.dao.FuncionarioDAO;
import ao.co.cligest.dao.ServicoDAO;
import ao.co.cligest.entidades.Funcionario;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.interfaces.IAgendaConsulta;
import ao.co.cligest.util.MetodosBuscas;

/**
 * 
 * @author NELSON DIWIDI - ANGOTECH
 * @manutencao NELSON DIWIDI
 *
 */
@WebServlet("/navegacao")
public class AdministracaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ConfirmarDAO _marconsulta = new ConfirmarDAO();
    private IAgendaConsulta _agendaConsulta = new AgendaConsultaDAO();
    private FuncionarioDAO _funcionarioDAO = new FuncionarioDAO();
	private ServicoDAO _servicoDAO = new ServicoDAO();
    private MetodosBuscas _metodos = new MetodosBuscas();
   
    public AdministracaoController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher saida = null;
		String mod = request.getParameter("mods");
		HttpSession ss = request.getSession(false);
		String tela = request.getParameter("pag");
		
		if(ss!=null){
			// Inicio - Doutor
			if (tela != null && (tela.equals("doc"))) {
				List<Funcionario> lsDoutores = _funcionarioDAO.listaDoutoreTodos();
				request.setAttribute("lsDoutores", lsDoutores);
				saida = request.getRequestDispatcher("index.jsp?mods=ad&pag=doc");
				saida.forward(request, response);
			}
			if (tela != null && (tela.equals("novodoc"))) {
				request.setAttribute("lsEspm", _metodos.buscaEspecialidade());
				request.setAttribute("lsFuncao", _metodos.buscaFuncao());
				saida = request.getRequestDispatcher("index.jsp?mods=ad&pag=novodoc");
				saida.forward(request, response);
			}
			if (tela != null && (tela.equals("agenddoc"))) {
				saida = request.getRequestDispatcher("index.jsp?mods=ad&pag=agenddoc");
				saida.forward(request, response);
			}
			if (tela != null && (tela.equals("novogenddoc"))) {
				saida = request.getRequestDispatcher("index.jsp?mods=ad&pag=novogenddoc");
				saida.forward(request, response);
			}
			if (tela != null && (tela.equals("editagenddoc"))) {
				saida = request.getRequestDispatcher("index.jsp?mods=ad&pag=editagenddoc");
				saida.forward(request, response);
			}
			if (tela != null && (tela.equals("editdoc"))) {
				saida = request.getRequestDispatcher("index.jsp?mods=ad&pag=editdoc");
				saida.forward(request, response);
			}
			if (tela != null && (tela.equals("profdoc"))) {
				saida = request.getRequestDispatcher("index.jsp?mods=ad&pag=profdoc");
				saida.forward(request, response);
			}
			
			// Cadastro de Serviços - Consulta
			if (tela != null && (tela.equals("lscons"))) {
				request.setAttribute("lsServicos", _servicoDAO.BuscarServico());
				saida = request.getRequestDispatcher("index.jsp?mods=ad&pag=lscons");
				saida.forward(request, response);
			}
			if (tela != null && (tela.equals("novocons"))) {
				saida = request.getRequestDispatcher("index.jsp?mods=ad&pag=novocons");
				saida.forward(request, response);
			}
			if (tela != null && (tela.equals("editcons"))) {
				saida = request.getRequestDispatcher("index.jsp?mods=ad&pag=editcons");
				saida.forward(request, response);
			}
			// Cadastro de Serviços - Exame
			if (tela != null && (tela.equals("lsexam"))) {
				saida = request.getRequestDispatcher("index.jsp?mods=ad&pag=lsexam");
				saida.forward(request, response);
			}
			if (tela != null && (tela.equals("novoexam"))) {
				saida = request.getRequestDispatcher("index.jsp?mods=ad&pag=novoexam");
				saida.forward(request, response);
			}
			if (tela != null && (tela.equals("editexam"))) {
				saida = request.getRequestDispatcher("index.jsp?mods=ad&pag=editexam");
				saida.forward(request, response);
			}
			
			// Cadastro de Serviços - Especialidade
			if (tela != null && (tela.equals("lsesp"))) {
				saida = request.getRequestDispatcher("index.jsp?mods=ad&pag=lsesp");
				saida.forward(request, response);
			}
			if (tela != null && (tela.equals("novoesp"))) {
				saida = request.getRequestDispatcher("index.jsp?mods=ad&pag=novoesp");
				saida.forward(request, response);
			}
			if (tela != null && (tela.equals("editesp"))) {
				saida = request.getRequestDispatcher("index.jsp?mods=ad&pag=editesp");
				saida.forward(request, response);
			}
			if(mod!=null && mod.equals("ng"))
			{
				List<Paciente> agendados =  _agendaConsulta.listaConsultaAgendada();
				request.setAttribute("listaPac", agendados);
				saida = request.getRequestDispatcher("index.jsp?mods=ng");
				saida.forward(request, response);
			}
			
			if(mod!=null && mod.equals("ajd"))
			{
				saida = request.getRequestDispatcher("index.jsp?mod=ajd");
				saida.forward(request, response);
			}
		} // if de Sessao 
		else
		{
			String url = request.getRequestURL().toString();
	        String queryString = request.getQueryString();
			request.getSession().setAttribute("ultimaURL", url);
			request.getSession().setAttribute("queryString", queryString);
			response.sendRedirect("login.jsp");
		}
	}
	

}
