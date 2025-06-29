package ao.co.cligest.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.omg.CORBA.INTERNAL;

import ao.co.cligest.dao.GrupoDAO;
import ao.co.cligest.entidades.Grupos;

/**
 * Servlet implementation class GrupoPrivilegiosController
 */
@WebServlet("/GrupoPrivilegiosController")
public class GrupoPrivilegiosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GrupoDAO _grupoDAO = new GrupoDAO();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GrupoPrivilegiosController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Grupos g ;
		String grupo = request.getParameter("grupos");
		String modulo = request.getParameter("modulos");
		String usuario = request.getParameter("usuario");
		String telas [] = request.getParameterValues("id_tela");
		HttpSession ss = request.getSession();
		try {
			if(telas!=null && telas.length>0){
				int grupoId =Integer.parseInt(grupo);
				int moduloId = Integer.parseInt(modulo);
				int userId = Integer.parseInt(usuario);
				for(String s: telas){
					g = new Grupos();
					int telaId = Integer.parseInt(s);
					g.setFk_grupo(grupoId);
					g.setFk_modulos(moduloId);
					g.setFk_telas(telaId);
					g.setFk_funcionario(userId);
					_grupoDAO.addPrivilegiosGrupo(g);
				}
				ss.setAttribute("msgOk", "msgOK");
			}
		}catch (Exception e) {
			e.printStackTrace();
			ss.setAttribute("msgNOK", "msgNOK");
		}
		
		response.sendRedirect("navegacao?mods=ad&pag=usersgrp");
	}

}
