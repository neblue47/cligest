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

import ao.co.cligest.dao.CidDAO;
import ao.co.cligest.dao.ExamesDAO;
import ao.co.cligest.dao.ProdutosDAO;
import ao.co.cligest.entidades.Cid;
import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Produtos;

/**
 * Servlet implementation class AjaxCarrinhoController
 */
@WebServlet("/AjaxCarrinhoController")
public class AjaxCarrinhoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ExamesDAO _exameDAO = new ExamesDAO();   
	private ProdutosDAO _produtoDAO = new ProdutosDAO();   
	private CidDAO _doencas = new CidDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxCarrinhoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession ss = request.getSession();
		PrintWriter out =  response.getWriter();
		StringBuilder sb = new StringBuilder();
		String produto = request.getParameter("produto");
		String hipotese = request.getParameter("hipotese");
		String addhipotese = request.getParameter("addhipotese");
		String diagnostico = request.getParameter("diagnostico");
		String addproduto = request.getParameter("addproduto");
		
		if(produto!=null){
			for(Produtos p: _produtoDAO.getProdutoIniciais(produto))
			{
				sb.append(p.getNome_comercial()+"|");
			}
		}
		if(hipotese!=null){
			for(Cid c: _doencas.getCIDConsultas(hipotese))
			{
				sb.append(c.getDescricao()+"|");
			}
		}
		if(diagnostico!=null){
			for(Cid c: _doencas.getCIDConsultas(diagnostico))
			{
				sb.append(c.getDescricao()+"|");
			}
		}
		if(addproduto!=null){
				sb.append(addproduto+"\n");
		}
		
		if(addhipotese!=null){
			Cid c = _doencas.getCidPNome(addhipotese);
			sb.append(c.getDescricao()+";"+c.getCodigocid()+"\n");
		}
		
		response.setContentType("text/plain");  
	  	response.setCharacterEncoding("UTF-8"); 
		out.write(sb.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
