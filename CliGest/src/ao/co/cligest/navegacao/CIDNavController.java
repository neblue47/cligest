package ao.co.cligest.navegacao;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ao.co.cligest.dao.AgendaConsultaDAO;
import ao.co.cligest.dao.CidDAO;
import ao.co.cligest.dao.ConfirmarDAO;
import ao.co.cligest.dao.FuncionarioDAO;
import ao.co.cligest.dao.GrupoDAO;
import ao.co.cligest.dao.ServicoDAO;
import ao.co.cligest.interfaces.IAgendaConsulta;
import ao.co.cligest.util.MetodosBuscas;

/**
 * 
 * @author NELSON DIWIDI - ND SOLUCOES
 * @manutencao NELSON DIWIDI
 *
 */
@WebServlet("/cidnavegacao")
public class CIDNavController extends HttpServlet{

	private static final long serialVersionUID = 1L; 
	private CidDAO _cidDAO = new CidDAO();
    private MetodosBuscas _metodos = new MetodosBuscas(); 
	
    public CIDNavController() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher saida = null;
		String mod = request.getParameter("mods");
		HttpSession ss = request.getSession(false);
		String tela = request.getParameter("pag");
		
		if(ss!=null){
			request.setAttribute("cidn", "active open");
			
			// Navegacao de CID
			if (tela != null && (tela.equals("cids"))) {
				request.setAttribute("lsCidDoencas", _cidDAO.listarCid()); 
				saida = request.getRequestDispatcher("index.jsp?mods=cdn&pag=cids");
				saida.forward(request, response);
			}
			if (tela != null && (tela.equals("cidscap"))) {
				request.setAttribute("lsCapitulosCid", _cidDAO.getCapitulo()); 
				saida = request.getRequestDispatcher("index.jsp?mods=cdn&pag=cidscap");
				saida.forward(request, response);
			}
			if (tela != null && (tela.equals("cidscat"))) {
				request.setAttribute("lsCategoriaGruposCid", _cidDAO.listarTodoCategoriaDGrupo());  
				saida = request.getRequestDispatcher("index.jsp?mods=cdn&pag=cidscat");
				saida.forward(request, response);
			}
			if (tela != null && (tela.equals("cidsgrp"))) {
				request.setAttribute("lsGruposCid", _cidDAO.listarTodoGrupoCid());  
				saida = request.getRequestDispatcher("index.jsp?mods=cdn&pag=cidsgrp");
				saida.forward(request, response);
			}
		}
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
