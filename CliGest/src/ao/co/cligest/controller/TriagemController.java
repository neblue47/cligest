package ao.co.cligest.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ao.co.cligest.dao.TriagemDAO;
import ao.co.cligest.entidades.Triagem;

/**
 * Servlet implementation class TriagemController
 */
@WebServlet("/TriagemController")
public class TriagemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TriagemDAO _triagemDAO = new TriagemDAO(); 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TriagemController() {
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
		String respiracao = request.getParameter("respiracao");
		String temperatua = request.getParameter("temperatura");
		String tensao_d = request.getParameter("diastolica");
		String tensao_s = request.getParameter("sistolica");
		String peso = request.getParameter("peso");
		String altura = request.getParameter("altura");
		String pulso = request.getParameter("pulso");
		String estado = request.getParameter("estado");
		String nota = request.getParameter("nota");
		String imc = request.getParameter("imc");
		String pacInt = request.getParameter("pacInt");
		String funInt = request.getParameter("funInt");
		String serInt = request.getParameter("serInt");
		String conInt = request.getParameter("conInt");
		
		try {
			 Triagem tr = new Triagem();	
			 tr.setTemperatura(temperatua);
			 tr.setRespiracao(respiracao);
			 tr.setTensao_diastolica(tensao_d);
			 tr.setTensao_sistolica(tensao_s);
			 tr.setPulso(pulso);
			 tr.setPeso(Double.parseDouble(peso));
			 tr.setAltura(Double.parseDouble(altura));
			 tr.setImc(Double.parseDouble(imc));
			 tr.setFK_estado_do_paciente(Integer.parseInt(estado));
			 tr.setDiagnostico_preliminar(nota);
			 tr.setFk_funcionario(Integer.parseInt(funInt));
			 tr.setFK_paciente(Integer.parseInt(pacInt));
			 tr.setId_servico(Integer.parseInt(serInt));
			 tr.setId_cons_conf(Integer.parseInt(conInt));
			 _triagemDAO.salvar(tr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("navegacaopd?mods=pd&pag=triapac");
	}

}
