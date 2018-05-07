package ao.co.cligest.relatoriocontroller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ao.co.cligest.dao.Formatando;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.relatoriobean.PacienteExame;
import ao.co.cligest.relatoriodaos.PacienteExameDAO;
import ao.co.cligest.util.ListaCarrinho;

/**
 * Servlet implementation class PacienteExameController
 */
@WebServlet("/PacienteExameController")
public class PacienteExameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PacienteExameController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ss = request.getSession(false);
		PacienteExameDAO eDAO = new PacienteExameDAO();
		Formatando fmt = new Formatando();
		String txtData1 = request.getParameter("txtData1");
		String txtData2 = request.getParameter("txtData2");
		String txtNome = request.getParameter("txtNome");
		String opGenero = request.getParameter("opGenero");
		String txtIdade1 = request.getParameter("txtIdade1");
		String txtIdade2 = request.getParameter("txtIdade2");
		String servico  = request.getParameter("servico");
		
		  
		 
		ListaCarrinho listaTabela = new ListaCarrinho();
		List<Paciente> dados = new ArrayList<Paciente>();
		
		try {
			if(txtData1!=null && !txtData1.equals("") && txtData2!=null && !txtData2.equals("") ){
				Date data1 = fmt.dataSql(txtData1);
				Date data2 = fmt.dataSql(txtData2);
				dados = eDAO.buscarAgendadosConfirmadosEmDatas(data1, data2);
				
				if(txtNome!=null && !txtNome.trim().equals("")){
					dados = eDAO.buscarAgendadosConfirmadosEmDatasNome(data1, data2,txtNome);
				}
				else if(txtIdade1!=null && !txtIdade2.trim().equals("") && txtIdade2!=null && txtIdade2.trim().equals("")){
					int idade1 = Integer.parseInt(txtIdade1);
					int idade2 = Integer.parseInt(txtIdade2);
										
					if(opGenero!=null && !opGenero.trim().equals("")){
						int genero = Integer.parseInt(opGenero);
						dados = eDAO.buscarAgendadosConfirmadosEmDatasIdadeGenero(data1, data2, idade1, idade2, genero);
					}
					else if(servico!=null && !servico.trim().equals("")){
						int FK_exame = eDAO.buscarIdServicoExame(servico);
						dados = eDAO.buscarAgendadosConfirmadosEmDatasIdadeExame(data1, data2, idade1, idade2, FK_exame);
					}
					else{
						dados = eDAO.buscarAgendadosConfirmadosEmDatasIdade(data1, data2, idade1, idade2);
					}
				}
				
				else if(opGenero!=null && !opGenero.trim().equals("")){
					int genero = Integer.parseInt(opGenero);
					
					if(servico!=null && !servico.trim().equals("")){
						int FK_exame = eDAO.buscarIdServicoExame(servico);
						dados = eDAO.buscarAgendadosConfirmadosEmDatasGeneroExame(data1, data2, genero, FK_exame);
					}
					else{
						dados = eDAO.buscarAgendadosConfirmadosEmDatasGenero(data1, data2, genero);
					}
				}
				else if(servico!=null && !servico.trim().equals("")){
					int FK_exame = eDAO.buscarIdServicoExame(servico);
					dados = eDAO.buscarAgendadosEmDatasExame(data1, data2, FK_exame);
				}
				
			}
			
					
			listaTabela = new ListaCarrinho();
			for (Paciente p : dados) {
				PacienteExame PEBean = new PacienteExame();
				PEBean.setNome_paciente(p.getNome_paciente());
				PEBean.setFK_num_servico(p.getId());
				PEBean.setNumProcesso(p.getNumero_processo());
				PEBean.setIdade(p.getIdade());
				PEBean.setGenero(p.getNomegenero());
				PEBean.setQdt_servico(p.getQuantidade());
				PEBean.setData_exame(p.getData_do_agendamento());
				listaTabela.adExamesRelat(PEBean);
			}
			
			if(txtData1!=null && !txtData1.equals(""))
				ss.setAttribute("txtData1", txtData1);
			
			if(txtData2!=null && !txtData2.equals(""))
				ss.setAttribute("txtData2", txtData2);
			
			if(txtNome!=null && !txtNome.equals(""))
				ss.setAttribute("txtNome", txtNome);
			
			if(txtIdade1!=null && !txtIdade1.equals(""))
				ss.setAttribute("txtIdade1", txtIdade1);
			
			if(txtIdade2!=null && !txtIdade2.equals(""))
				ss.setAttribute("txtIdade2", txtIdade2);
			
			if(opGenero!=null && !opGenero.equals(""))
				ss.setAttribute("opGenero", opGenero);
			
			if(servico!=null && !servico.equals(""))
				ss.setAttribute("servico", servico);
			
			ss.setAttribute("listaTabelaEX", listaTabela);
			response.sendRedirect("navegacaorel?mod=rel&pesquisar=agendExasHs");
		}
		catch (NullPointerException e) {
			e.printStackTrace();
			response.sendRedirect("navegacao?mod=ajd");
		}
		 catch (NumberFormatException e) {
				e.printStackTrace();
				response.sendRedirect("navegacao?mod=ajd");
			}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession ss = request.getSession(false);
		PacienteExameDAO eDAO = new PacienteExameDAO();
		Formatando fmt = new Formatando();
		String txtData1 = request.getParameter("txtData1");
		String txtData2 = request.getParameter("txtData2");
		String txtNome = request.getParameter("txtNome");
		String opGenero = request.getParameter("opGenero");
		String txtIdade1 = request.getParameter("txtIdade1");
		String txtIdade2 = request.getParameter("txtIdade2");
		String servico  = request.getParameter("servico");
		
		  
		 
		ListaCarrinho listaTabela = new ListaCarrinho();
		List<Paciente> dados = new ArrayList<Paciente>();
		
		try {
			if(txtData1!=null && !txtData1.equals("") && txtData2!=null && !txtData2.equals("") ){
				Date data1 = fmt.dataSql(txtData1);
				Date data2 = fmt.dataSql(txtData2);
				dados = eDAO.buscarAgendadosEmDatas(data1, data2);
				
				if(txtNome!=null && !txtNome.trim().equals("")){
					dados = eDAO.buscarAgendadosEmDatasNome(data1, data2, txtNome);
				}
				else if(txtIdade1!=null && !txtIdade2.trim().equals("") && txtIdade2!=null && txtIdade2.trim().equals("")){
					int idade1 = Integer.parseInt(txtIdade1);
					int idade2 = Integer.parseInt(txtIdade2);
										
					if(opGenero!=null && !opGenero.trim().equals("")){
						int genero = Integer.parseInt(opGenero);
						dados = eDAO.buscarAgendadosEmDatasIdadesGenero(data1, data2, idade1, idade2, genero);
					}
					else if(servico!=null && !servico.trim().equals("")){
						int FK_exame = eDAO.buscarIdServicoExame(servico);
						dados = eDAO.buscarAgendadosEmDatasIdadesExame(data1, data2, idade1, idade2, FK_exame);
					}
					else{
						dados = eDAO.buscarAgendadosEmDatasIdades(data1, data2, idade1, idade2);
					}
				}
				
				else if(opGenero!=null && !opGenero.trim().equals("")){
					int genero = Integer.parseInt(opGenero);
					
					if(servico!=null && !servico.trim().equals("")){
						int FK_exame = eDAO.buscarIdServicoExame(servico);
						dados = eDAO.buscarAgendadosEmDatasGeneroExame(data1, data2, genero, FK_exame);
					}
					else{
						dados = eDAO.buscarAgendadosEmDatasGenero(data1, data2, genero);
					}
				}
				else if(servico!=null && !servico.trim().equals("")){
					int FK_exame = eDAO.buscarIdServicoExame(servico);
					dados = eDAO.buscarAgendadosEmDatasExame(data1, data2, FK_exame);
				}
				
			}
			
					
			listaTabela = new ListaCarrinho();
			for (Paciente p : dados) {
				PacienteExame PEBean = new PacienteExame();
				PEBean.setNome_paciente(p.getNome_paciente());
				PEBean.setFK_num_servico(p.getId());
				PEBean.setNumProcesso(p.getNumero_processo());
				PEBean.setIdade(p.getIdade());
				PEBean.setGenero(p.getNomegenero());
				PEBean.setQdt_servico(p.getQuantidade());
				PEBean.setData_exame(p.getData_do_agendamento());
				listaTabela.adExamesRelat(PEBean);
			}
			
			if(txtData1!=null && !txtData1.equals(""))
				ss.setAttribute("txtData1", txtData1);
			
			if(txtData2!=null && !txtData2.equals(""))
				ss.setAttribute("txtData2", txtData2);
			
			if(txtNome!=null && !txtNome.equals(""))
				ss.setAttribute("txtNome", txtNome);
			
			if(txtIdade1!=null && !txtIdade1.equals(""))
				ss.setAttribute("txtIdade1", txtIdade1);
			
			if(txtIdade2!=null && !txtIdade2.equals(""))
				ss.setAttribute("txtIdade2", txtIdade2);
			
			if(opGenero!=null && !opGenero.equals(""))
				ss.setAttribute("opGenero", opGenero);
			
			if(servico!=null && !servico.equals(""))
				ss.setAttribute("servico", servico);
			
			ss.setAttribute("listaTabelaEX", listaTabela);
			response.sendRedirect("navegacaorel?mod=rel&pesquisar=agendExams");
		}
		catch (NullPointerException e) {
			e.printStackTrace();
			response.sendRedirect("navegacao?mod=ajd");
		}
		 catch (NumberFormatException e) {
				e.printStackTrace();
				response.sendRedirect("navegacao?mod=ajd");
			}
	}

}
