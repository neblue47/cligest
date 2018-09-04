package ao.co.cligest.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ao.co.cligest.cripto.Seguranca;
import ao.co.cligest.dao.AcessosDAO;
import ao.co.cligest.dao.InstituicaoDAO;
import ao.co.cligest.dao.UserDAO;
import ao.co.cligest.entidades.Diverso;
import ao.co.cligest.entidades.Instituicao;
import ao.co.cligest.entidades.Usuario;
import ao.co.cligest.util.MetodosBuscas;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String key  = "140b41b22a29beb4061bda66b6747e14"; 
	/**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		HttpSession sessaos = request.getSession(false);
		if(acao!=null && acao.equals("out") && sessaos!=null) 
		{
			sessaos.invalidate();
		}
		response.sendRedirect("login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usu = new Usuario();
		UserDAO uDao = new UserDAO();
		String user = request.getParameter("usuario");
		String pass = request.getParameter("senha");
		HttpSession sessao = request.getSession();
		
		String nomeInst = "Nao Cadastrado";
		try
		{
			
			usu.setNome(user);
			usu.setSenha(pass);
			if(uDao.existe_user(usu))
			{
				Usuario us = uDao.autenticado(usu);
				List<Diverso>AcessoMudulos = new AcessosDAO().AcessoModulosLogin(us.getFk_grupo());
				List<Diverso>AcessoTelasPai   = new AcessosDAO().AcessoTelasPrivPai(us.getFk_grupo());
				List<Diverso>AcessoTelas   = new AcessosDAO().AcessoTelasPriv(us.getFk_grupo());
				//List<Diverso>AcessoConfigTelas   = new AcessosDAO().AcessoTelasConfigLogin(us.getFK_entidade());
				
				sessao.setAttribute("nomeUsa", us.getNome());
				sessao.setAttribute("usuario", us.getId());
				sessao.setAttribute("grupo", us.getFk_grupo());
//				sessao.setAttribute("nivelUs", us.getNivel());
				
				sessao.setAttribute("AcessoMudulos", AcessoMudulos);
				sessao.setAttribute("AcessoTelasPai", AcessoTelasPai);
				sessao.setAttribute("AcessoTelas", AcessoTelas);
//				sessao.setAttribute("AcessoPrivTelas", AcessoPrivTelas);
//				sessao.setAttribute("AcessoConfigTelas", AcessoConfigTelas);
			 
				response.sendRedirect("navegacao?mods=ng");
 
			}
			else
			{
				request.setAttribute("mensLogin", "Nome do Utilizador ou Senha Invalida! ");
				RequestDispatcher saida = request.getRequestDispatcher("login.jsp");
				saida.forward(request, response);
			}
	    }
		catch(Exception er)
		{
			er.printStackTrace();
			response.sendRedirect("login.jsp");
		}
	}

}
