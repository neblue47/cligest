package ao.co.cligest.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import ao.co.cligest.dao.Formatando;
import ao.co.cligest.dao.PacienteDAO;
import ao.co.cligest.entidades.Paciente;

/**
 * Servlet implementation class PacienteController
 */
@WebServlet("/PacienteController")
public class PacienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PacienteDAO _pacienteDAO = new PacienteDAO();
	private String saveFile = "C:/cligestFile/";
	String arquivo = "";
	Formatando ft = new Formatando();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PacienteController() {
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
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter out = response.getWriter(); 
		HttpSession ss = request.getSession();
		Paciente p = new Paciente();
		
		try {
			boolean ismultipart = ServletFileUpload.isMultipartContent(request);
			if(ismultipart){
				gerarPasta(saveFile);
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List items = null;
				try
				{
					items = upload.parseRequest(request);
				}catch(Exception e){out.print("Errror 1" +e);}
				Iterator itr = items.iterator();
				List<FileItem>  itm = (List<FileItem> ) items;
				try
				{
					// Pegar os Dados no formulario que estejam no Input[text]
					int a = 0;
					String primeironome = itm.get(0).getString("UTF-8");
					String ultimonome   = itm.get(1).getString("UTF-8");
					String dtNascido	= ft.dataToPadrao(itm.get(2).getString("UTF-8"));
					int genero 			= Integer.parseInt(itm.get(3).getString("UTF-8"));
					int estadocvl 		= Integer.parseInt(itm.get(4).getString("UTF-8"));
					int gruposg 		= Integer.parseInt(itm.get(5).getString("UTF-8"));
					int tpdocum 		= Integer.parseInt(itm.get(6).getString("UTF-8"));
					String numdocum 	= itm.get(7).getString("UTF-8");
					long contacto 		= Long.parseLong(itm.get(8).getString("UTF-8"));
					String email 		= itm.get(9).getString("UTF-8");
					String morada 		= itm.get(10).getString("UTF-8");
					arquivo 			= ft.novoGUID();
					p.setNome(primeironome);
					p.setNomem("");
					p.setApelido(ultimonome);
					p.setDataNasc(ft.data(dtNascido));
					p.setGenero(genero);
					p.setEst_civil(estadocvl);
					p.setNumero_doc(numdocum);
					p.setTelefone(contacto);
					p.setTipo_doc(tpdocum);
					p.setEmail(email);
					p.setEndereco(morada);
					p.setGruposg(gruposg);
					p.setNomeArq(arquivo);
				}
				catch(NumberFormatException er){out.print("Errror 2" +er);} 
				
				while(itr.hasNext()){
					FileItem item = (FileItem)itr.next();
					if(!item.isFormField()){ 
						String itemname = item.getName();
						String filename = FilenameUtils.getName(itemname);
						File f = checkExist(filename);
						item.write(f);
						break;
					}
				}
				_pacienteDAO.novoPaciente(p);
				ss.setAttribute("msgOk", "msgOK");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ss.setAttribute("msgNOK", "msgNOK");
		}
		response.sendRedirect("navegacaoag?mods=ag&pag=pac");
	}
	
	private File checkExist(String fileName) {
		File f = new File(saveFile+"/"+fileName);	
		if(f.exists()){
			StringBuffer sb = new StringBuffer(fileName);
			sb.replace(0, sb.indexOf("."), arquivo);
			f = new File(saveFile+"/"+sb.toString());
		}
		return f;
	}
	
	private  void gerarPasta(String pasta){
		try {
			File dir = new File(pasta);
			if(!dir.isDirectory())
				dir.mkdir();
		}
		catch (Exception e)  
		{
			e.printStackTrace();
		}
	
	}

}
