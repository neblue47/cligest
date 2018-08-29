package ao.co.cligest.navegacao;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ao.co.cligest.beans.BancoUEmergenciaBean;
import ao.co.cligest.dao.AgendaDAO;
import ao.co.cligest.dao.ConfirmarDAO;
import ao.co.cligest.dao.ExamesDAO;
import ao.co.cligest.dao.Formatando;
import ao.co.cligest.dao.HistVendaDAO;
import ao.co.cligest.dao.LaboratorioAtendidosDAO;
import ao.co.cligest.dao.LaboratorioDAO;
import ao.co.cligest.dao.PacienteAgendadoDAO;
import ao.co.cligest.dao.PacienteDAO;
import ao.co.cligest.dao.ProdutosDAO;
import ao.co.cligest.dao.RelatorioBancoUEDAO;
import ao.co.cligest.dao.RelatorioConsultaDAO;
import ao.co.cligest.dao.RelatorioPagamentosDAO;
import ao.co.cligest.dao.ServicoDAO;
import ao.co.cligest.dao.TratamentoDAO;
import ao.co.cligest.entidades.Diverso;
import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Facturacao;
import ao.co.cligest.entidades.Farmacos;
import ao.co.cligest.entidades.Funcionario;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.PacienteRelatorio;
import ao.co.cligest.entidades.Produtos;
import ao.co.cligest.entidades.Servico;
import ao.co.cligest.entidades.Tratamento;
import ao.co.cligest.entidades.Triagem;
import ao.co.cligest.relatoriobean.PacienteExame;
import ao.co.cligest.relatoriodaos.PacienteExameDAO;
import ao.co.cligest.test.ParenteDAO;
import ao.co.cligest.util.ListaCarrinho;
import ao.co.cligest.util.MetodosBuscas;

/**
 * Servlet implementation class FarmController
 */
@WebServlet("/RelatorioController")
public class RelatoriosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RelatoriosController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher saida = null;
		String mod = request.getParameter("mod");
		HttpSession ss = request.getSession(false); 
		if(ss!=null){
		Integer grupo = (Integer)ss.getAttribute("grupo");
		  if(mod!=null && mod.equals("rel"))
		  {
			String tela = request.getParameter("pesquisar");
			if(tela!=null && tela.equals("agendamTs")){
				List <Diverso> telas = new MetodosBuscas().buscaTAGENDTs(grupo);
				request.setAttribute("telas", telas);
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=agendamTs");
				saida.forward(request, response);
		    }
			
			if(tela!=null && (tela.equals("agendam")||tela.equals("Pesquisar"))){
				
				String nomePaciente = request.getParameter("nomePac");
			    String idade 		= request.getParameter("idade");
			    String servico 		= request.getParameter("servico");
			    String sexo 		= request.getParameter("sexo");
			    String medico 		= request.getParameter("medico");
			    String idades 		= request.getParameter("idades");
			    String dataInicio 	= request.getParameter("dataI");
			    String dataFinal 	= request.getParameter("dataF");
			    String axu 			= request.getParameter("pesquisar");
			    
				PacienteAgendadoDAO paDAO = new PacienteAgendadoDAO();
				List <Paciente> lista = null;

				if(dataInicio!=null && !dataInicio.equals("") && dataFinal!=null && !dataFinal.equals("")){
				Date dtInicio = new Formatando().dataSql(dataInicio);
				Date dtFinal = new Formatando().dataSql(dataFinal);
				
				if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals(""))
			    {
			    		lista = paDAO.buscarPorNome(dtInicio, dtFinal, nomePaciente);
			    }
				else if(dataInicio!=null && !dataInicio.equals("") && dataFinal!=null && !dataFinal.equals("") && idade!=null && !idade.equals("") && idades!=null && !idades.equals(""))
			    {
			    		lista = paDAO.buscarPorIdades(dtInicio, dtFinal, idade, idades);
			    }
			    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade!=null && !idade.equals(""))
			    {
			    		lista = paDAO.buscarPorIdade(dtInicio, dtFinal, idade); 
			    }
			    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && servico!=null && !servico.equals(""))
			    {
			  			lista = paDAO.buscarPorServico(dtInicio, dtFinal, servico);
			    }
			    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && sexo!=null && !sexo.equals(""))
			    {
			    		lista = paDAO.buscarPorGenero(dtInicio,dtFinal,sexo);
			    }
			    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && medico!=null && !medico.equals(""))
			    {
			    		lista = paDAO.buscarPorMedico(dtInicio,dtFinal,medico);
			    }
				else if( dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals(""))
			    {
			    		lista = paDAO.buscarPorData(dtInicio,dtFinal);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals("") && idade!=null && !idade.equals("") && idades!=null && !idades.equals(""))
			    {
			    		lista = paDAO.buscarPorNomeAndIdade(dtInicio,dtFinal,nomePaciente, idade, idades);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals("") && sexo!=null && !sexo.equals(""))
			    {
			    		lista = paDAO.buscarPorNomeAndGenero(dtInicio,dtFinal,nomePaciente,sexo);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals("") && servico!=null && !servico.equals(""))
			    {
			    		lista = paDAO.buscarPorNomeAndServico(dtInicio,dtFinal,nomePaciente,servico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals("") && medico!=null && !medico.equals(""))
			    {
			    		lista = paDAO.buscarPorNomeAndMedico(dtInicio,dtFinal,nomePaciente,medico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade!=null && !idade.equals("") && idades!=null && !idades.equals("") && sexo!=null && !sexo.equals(""))
			    {
			    		lista = paDAO.buscarPorIdadesAndGenero(dtInicio,dtFinal,idade,idades,sexo);
			    }
				else if(dtInicio!=null&&!dtInicio.equals("") && dtFinal!=null&&!dtFinal.equals("") && idade!=null&&!idade.equals("") && idades!=null&&!idades.equals("") && servico!=null&&!servico.equals(""))
			    {
			    		lista = paDAO.buscarPorIdadesAndGeneroAndServico(dtInicio,dtFinal,idade,idades,sexo,servico);
			    }
				else if(dtInicio!=null&&!dtInicio.equals("") && dtFinal!=null&&!dtFinal.equals("") && idade!=null&&!idade.equals("") && idades!=null&&!idades.equals("") && sexo!=null&&!sexo.equals("") && medico!=null && !medico.equals(""))
			    {
			    		lista = paDAO.buscarPorIdadesAndGeneroAndMedico(dtInicio,dtFinal,idade,idades,sexo,medico);
			    }
				else if(dtInicio!=null&&!dtInicio.equals("") && dtFinal!=null&&!dtFinal.equals("") && sexo!=null&&!sexo.equals("") && servico!=null&&!servico.equals(""))
			    {
			    		lista = paDAO.buscarPorGeneroAndServico(dtInicio,dtFinal,sexo,servico);
			    }
				else if(dtInicio!=null&&!dtInicio.equals("") && dtFinal!=null&&!dtFinal.equals("") && sexo!=null&&!sexo.equals("") && medico!=null && !medico.equals(""))
			    {
			    		lista = paDAO.buscarPorGeneroAndMedico(dtInicio,dtFinal,sexo,medico);
			    }
				else if(dtInicio!=null&&!dtInicio.equals("") && dtFinal!=null&&!dtFinal.equals("") && servico!=null && !servico.equals("") && medico!=null && !medico.equals(""))
			    {
			    		lista = paDAO.buscarPorServicoAndMedico(dtInicio,dtFinal,servico,medico);
			    }
				}
				else{
					lista = paDAO.buscarPaciente();
				}
				
				request.setAttribute("lista", lista);
				request.setAttribute("axu", axu);
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=agendam");
				saida.forward(request, response);
			}
			
			if(tela!=null && tela.equals("consult")){
				RelatorioConsultaDAO rcDao = new RelatorioConsultaDAO();
				List<Paciente> consultados = null;
				String cod_p = request.getParameter("cod_p");
				 
				String acao = request.getParameter("acao");
				String valorp = request.getParameter("valorp");
				if(acao!=null && acao.equals("ver")){
					request.setAttribute("modal", acao);
					int cod = Integer.parseInt(cod_p);
					Paciente pac = rcDao.getPaciente(cod);
					request.setAttribute("pac", pac);
				}
				if(valorp!=null){
					 consultados = rcDao.getPacientesConsultados(valorp);
				}
				else{
				 consultados = rcDao.getPacientesConsultados();
				}
				request.setAttribute("consultados", consultados);
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=consult");
				saida.forward(request, response);
			}
			
			
//			if(tela!=null && tela.equals("histTriag"))
//			{
//				
//				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=histTriag");
//				saida.forward(request, response);
//			}
			
			if(tela!=null && (tela.equals("triages") ||tela.equals("PesquisarT")) || tela!=null && tela.equals("histTriag")){
				
				PacienteAgendadoDAO paDAO = new PacienteAgendadoDAO();
				List <Paciente> lista = null;
				
				String nomePaciente = request.getParameter("nomePac");
				String dataInicio   = request.getParameter("dataI");
				String dataFinal 	= request.getParameter("dataF");
				String idade1 		= request.getParameter("idade1");
				String idade2 		= request.getParameter("idade2");
				String genero 		= request.getParameter("sexo");
				String servico 		= request.getParameter("servico");
				String medico 		= request.getParameter("medico");
				
				if(dataInicio!=null && !dataInicio.equals("") && dataFinal!=null && !dataFinal.equals(""))
				{
					Date dtInicio = new Formatando().dataSql(dataInicio);
					Date dtFinal = new Formatando().dataSql(dataFinal);
					
				if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals(""))
			    {
			    		lista = paDAO.buscarPorDataAndNomeTriagem(dtInicio, dtFinal, nomePaciente);
			    		System.out.println("Datas e Paciente:" +nomePaciente);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade1!=null && !idade1.equals(""))
			    {
			    		lista = paDAO.buscarPorDataAndIdadeTriagem(dtInicio, dtFinal, idade1); 
			    		System.out.println("Datas e Idade:" +idade1);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals(""))
			    {
			    		lista = paDAO.buscarPorDataAndIdadeTriagens(dtInicio, dtFinal, idade1, idade2); 
			    		System.out.println("Datas e Duas Idades:" +idade2);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && genero!=null && !genero.equals(""))
			    {
			    		lista = paDAO.buscarPorDataAndGeneroTriagem(dtInicio, dtFinal, genero); 
			    		System.out.println("Datas e Genero:" +genero);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals(""))
			    {
			    		lista = paDAO.buscarPorDataTriagens(dtInicio, dtFinal); 
			    		System.out.println("Datas Ini e Fim:");
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && servico!=null && !servico.equals(""))
			    {
			    		lista = paDAO.buscarPorDataAndServicoTriagem(dtInicio, dtFinal, servico); 
			    		System.out.println("Datas e Especialidade:" +servico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && medico!=null && !medico.equals(""))
			    {
			    		lista = paDAO.buscarPorDataAndMedicoTriagem(dtInicio, dtFinal, medico); 
			    		System.out.println("Datas e Médico:" +medico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals("") && idade1!=null && !idade1.equals("")&& idade2!=null && !idade2.equals(""))
			    {
		    		lista = paDAO.buscarPorDataAndNomeAndIdadeTriagem(dtInicio, dtFinal, nomePaciente, idade1, idade2);
		    		System.out.println("Datas e Paciente e Idades:" +nomePaciente+idade1+idade2);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals("") && servico!=null && !servico.equals(""))
			    {
		    		lista = paDAO.buscarPorDataAndNomeAndServicoTriagem(dtInicio, dtFinal, nomePaciente, servico);
		    		System.out.println("Datas e Paciente e Especialidade:" +nomePaciente+servico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals("") && medico!=null && !medico.equals(""))
			    {
		    		lista = paDAO.buscarPorDataAndNomeAndMedicoTriagem(dtInicio, dtFinal, nomePaciente, medico);
		    		System.out.println("Datas e Paciente e Medico:" +nomePaciente+medico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null&&!dtFinal.equals("") && idade1!=null&&!idade1.equals("")&& idade2!=null&&!idade2.equals("") && genero!= null&&!genero.equals(""))
			    {
		    		lista = paDAO.buscarPorDataAndIdadeAndGeneroTriagem(dtInicio, dtFinal, idade1, idade2, genero);
		    		System.out.println("Datas e Idade e Genero:" +idade1+idade2+genero);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null&&!dtFinal.equals("") && idade1!=null&&!idade1.equals("")&& idade2!=null&&!idade2.equals("") && servico!=null && !servico.equals(""))
			    {
		    		lista = paDAO.buscarPorDataAndIdadeAndServicoTriagem(dtInicio, dtFinal, idade1, idade2, servico);
		    		System.out.println("Datas e Idade e Especialidade:" +idade1+idade2+servico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null&&!dtFinal.equals("") && idade1!=null&&!idade1.equals("")&& idade2!=null&&!idade2.equals("") && medico!=null && !medico.equals(""))
			    {
		    		lista = paDAO.buscarPorDataAndIdadeAndMedicoTriagem(dtInicio, dtFinal, idade1, idade2, medico);
		    		System.out.println("Datas e Idade e Médico:" +idade1+idade2+medico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null&&!dtFinal.equals("") && genero!=null&&!genero.equals("") && servico!=null && !servico.equals(""))
			    {
		    		lista = paDAO.buscarPorDataAndGeneroAndServicoTriagem(dtInicio, dtFinal, genero, servico);
		    		System.out.println("Datas e Genero, Especialidade:" +genero+servico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null&&!dtFinal.equals("") && genero!=null&&!genero.equals("") && medico!=null && !medico.equals(""))
			    {
		    		lista = paDAO.buscarPorDataAndGeneroAndServicoTriagem(dtInicio, dtFinal, genero, medico);
		    		System.out.println("Datas e Genero, Medico:" +genero+medico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null&&!dtFinal.equals("") && servico!=null && !servico.equals("") && medico!=null && !medico.equals(""))
			    {
		    		lista = paDAO.buscarPorDataAndServicoAndMedicoTriagem(dtInicio, dtFinal, servico, medico);
		    		System.out.println("Datas e Servico e Médico:" +servico+medico);
			    }
				}else{
						lista = paDAO.buscarPacienteTriagem();
						System.out.println("Datas: hoje");
				} 
				
				request.setAttribute("lista", lista);
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=triages");
				saida.forward(request, response);
			}

			if(tela!=null && (tela.equals("internament")||tela.equals("PesquisarI"))){
	
			PacienteAgendadoDAO paDAO = new PacienteAgendadoDAO();
			List <Diverso> metBusc =  new MetodosBuscas().buscarSector();
			List <Paciente> lista = null;
			
			String nomePaciente = request.getParameter("nomePac");
			String dataInicio   = request.getParameter("dataI");
			String dataFinal 	= request.getParameter("dataF");
			String idade1 		= request.getParameter("idade1");
			String idade2 		= request.getParameter("idade2");
			String genero 		= request.getParameter("sexo");
			String sector 		= request.getParameter("sector");
			String sala 		= request.getParameter("sala");
			String medico 		= request.getParameter("medico");
			
			if(dataInicio!=null && !dataInicio.equals("") && dataFinal!=null && !dataFinal.equals("")){
				Date dtInicio = new Formatando().dataSql(dataInicio);
				Date dtFinal = new Formatando().dataSql(dataFinal);
				
				if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals(""))
			    {
			    		lista = paDAO.buscarPorDataAndNomePacInt(dtInicio, dtFinal, nomePaciente);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals(""))
			    {
			    		lista = paDAO.buscarPorDataAndIdadesPacInt(dtInicio, dtFinal, idade1, idade2); 
			    }
			    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade1!=null && !idade1.equals(""))
			    {
			    		lista = paDAO.buscarPorDataAndIdadePacInt(dtInicio, dtFinal, idade1); 
			    }
			    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && genero!=null && !genero.equals(""))
			    {
			    		lista = paDAO.buscarPorDataAndGeneroPacInt(dtInicio, dtFinal, genero); 
			    }
			    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && medico!=null && !medico.equals(""))
			    {
			    		lista = paDAO.buscarPorDataAndMedicoPacInt(dtInicio, dtFinal, medico); 
			    }
			    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && sector!=null && !sector.equals("") && sala!=null && !sala.equals(""))
			    {
			    		lista = paDAO.buscarPorDataAndSectorAndSalaPacInt(dtInicio, dtFinal, sector, sala); 
			    }
			    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals(""))
			    {
			    		lista = paDAO.buscarPorDataAndNomeAndIdadePacInt(dtInicio, dtFinal, nomePaciente, idade1, idade2); 
			    }
			    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals("") && genero!=null && !genero.equals(""))
			    {
			    		lista = paDAO.buscarPorDataAndNomeAndGeneroPacInt(dtInicio, dtFinal, nomePaciente, genero); 
			    }
			    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals("") && medico!=null && !medico.equals(""))
			    {
			    		lista = paDAO.buscarPorDataAndNomeAndMedicoPacInt(dtInicio, dtFinal, nomePaciente, medico); 
			    }
			    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals("") && sector!=null && !sector.equals("") && sala!=null && !sala.equals(""))
			    {
			    		lista = paDAO.buscarPorDataAndNomeAndSectorAndSalaPacInt(dtInicio, dtFinal, nomePaciente, sector, sala); 
			    }
			    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && genero!=null && !genero.equals(""))
			    {
			    		lista = paDAO.buscarPorDataAndIdadeAndGeneroPacInt(dtInicio, dtFinal, idade1, idade2, genero); 
			    }
			    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && medico!=null && !medico.equals(""))
			    {
			    		lista = paDAO.buscarPorDataAndIdadeAndMedicoPacInt(dtInicio, dtFinal, idade1, idade2, medico); 
			    }
			    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && sector!=null && !sector.equals("") && sala!=null && !sala.equals(""))
			    {
			    		lista = paDAO.buscarPorDataAndIdadeAndSectorAndSalaPacInt(dtInicio, dtFinal, idade1, idade2, sector, sala); 
			    }
			    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && genero!=null && !genero.equals("") && medico!=null && !medico.equals(""))
			    {
			    		lista = paDAO.buscarPorDataAndGeneroAndMedicoPacInt(dtInicio, dtFinal, genero, medico); 
			    }
			    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && genero!=null && !genero.equals("") && sector!=null && !sector.equals("") && sala!=null && !sala.equals(""))
			    {
			    		lista = paDAO.buscarPorDataAndGeneroAndSectorAndSalaPacInt(dtInicio, dtFinal, genero, sector, sala); 
			    }
			    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && genero!=null && !medico.equals("") && medico!=null && !sector.equals("") && sala!=null && !sala.equals(""))
			    {
			    		lista = paDAO.buscarPorDataAndMedicoAndSectorAndSalaPacInt(dtInicio, dtFinal, medico, sector, sala); 
			    }
			    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals(""))
			    {
			    		lista = paDAO.buscarPorDataPacInt(dtInicio, dtFinal); 
			    }
			}else{
						lista = paDAO.buscarPacienteInternado();
			}
			
			request.setAttribute("metBusc", metBusc);
			request.setAttribute("lista", lista);
			saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=internament");
			saida.forward(request, response);
			}
				
			if(tela!=null && tela.equals("pagament")){
	        	List <Diverso> telas = new MetodosBuscas().buscaTPAGAM(grupo);
				request.setAttribute("telas", telas);
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=pagament");
			    saida.forward(request, response);
			}
			       
			if(tela!=null && ((tela.equals("pagmCons") || tela!=null &&(tela.equals("pesquisar")) || tela.equals("pagCons"))))
			{
			
			RelatorioPagamentosDAO rpDao = new RelatorioPagamentosDAO();
			List<Facturacao> pacieFaturados = rpDao.getPacienteFaturados();
			String xpesq = request.getParameter("valorp");
			
			if(xpesq!=null){
				pacieFaturados = new RelatorioPagamentosDAO().getPacienteFaturados(xpesq);
			}
			request.setAttribute("pacieFaturados", pacieFaturados);
			saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=pagmCons");
			saida.forward(request, response);
			}
			
			if(tela!=null && tela.equals("urgeEemerg")){
				
				List<Triagem> triadosBU = new RelatorioBancoUEDAO().getTriadosBancoUrgencia();
				request.setAttribute("triadosHBU", triadosBU);
				
				saida = request.getRequestDispatcher("index.jsp?mod=rel");
				saida.forward(request, response);
			}
			
			if(tela!=null && tela.equals("relUrTria")){
				
				List<Triagem> triadosBU = null;
				String data_ini = request.getParameter("data_ini");
				String data_fim = request.getParameter("data_fim");
				String op = request.getParameter("nome_idade");
				String opg = request.getParameter("genero");
				String openc = request.getParameter("encam");
				try{
					if(data_ini !=null && data_fim !=null){
						
						if(op!=null && !op.equals("") && op.equals("1") && opg == null && openc == null){
							Date data_in = new Formatando().dataSql(data_ini);
							Date data_fi = new Formatando().dataSql(data_fim);
							String nome = request.getParameter("nomep");
							triadosBU = new RelatorioBancoUEDAO().getTriadosBancoUrgencia(data_in,data_fi,nome);
							System.out.println("NOME");
						}
						else if(op!=null && !op.equals("") && op.equals("2") && opg == null){
							Date data_in = new Formatando().dataSql(data_ini);
							Date data_fi = new Formatando().dataSql(data_fim);
							int idade1 = Integer.parseInt(request.getParameter("idade1"));
							int idade2 = Integer.parseInt(request.getParameter("idade2"));
							triadosBU = new RelatorioBancoUEDAO().getTriadosBancoUrgencia(data_in,data_fi,idade1,idade2);
							System.out.println("IDADE");
						}
						else if(opg!=null && !opg.equals("") && openc==null &&  op==null && op == null ){
							Date data_in = new Formatando().dataSql(data_ini);
							Date data_fi = new Formatando().dataSql(data_fim);
							int sexo = Integer.parseInt(request.getParameter("sexo"));
							triadosBU = new RelatorioBancoUEDAO().getTriadosBancoUrgencia(data_in,data_fi,sexo);
							System.out.println("GENERO");
						}
						else if(openc!=null && !openc.equals("") && op==null && opg==null ){
							Date data_in = new Formatando().dataSql(data_ini);
							Date data_fi = new Formatando().dataSql(data_fim);
							String encam_op = request.getParameter("encam_op");
							triadosBU = new RelatorioBancoUEDAO().getTriadosBancoUrgenciaEncam(data_in,data_fi,encam_op);
							System.out.println("ENCAM");
						}
						else if(op!=null && !op.equals("") && openc!=null && !openc.equals("")){
							Date data_in = new Formatando().dataSql(data_ini);
							Date data_fi = new Formatando().dataSql(data_fim);
							int ops = Integer.parseInt(request.getParameter("encam_op"));
							String nome = request.getParameter("nomep");
							triadosBU = new RelatorioBancoUEDAO().getTriadosBancoUrgenciaNomEnc(data_in,data_fi,nome,ops);
							System.out.println("NOME-ENCAM");
						}
						else if(op!=null && !op.equals("") && opg!=null ){
							Date data_in = new Formatando().dataSql(data_ini);
							Date data_fi = new Formatando().dataSql(data_fim);
							int sexo = Integer.parseInt(request.getParameter("sexo"));
							String nome = request.getParameter("nomep");
							triadosBU = new RelatorioBancoUEDAO().getTriadosBancoUrgencia(data_in,data_fi,nome,sexo);
							System.out.println("NOME-GENERO");
						}
						
						else{
							Date data_in = new Formatando().dataSql(data_ini);
							Date data_fi = new Formatando().dataSql(data_fim);
							triadosBU = new RelatorioBancoUEDAO().getTriadosBancoUrgencia(data_in,data_fi);
							System.out.println("DATAS");
						}
						
					}
					else{
						triadosBU = new RelatorioBancoUEDAO().getTriadosBancoUrgencia();
					}
					saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=urgeEemerg");
				}
				catch(Exception er){
					System.out.println(er.getMessage());
//					response.sendRedirect("navegcao?mod=ajd");
					saida = request.getRequestDispatcher("navegacao?mod=ajd");
				}
				
				
				request.setAttribute("triadosHBU", triadosBU);				
				
				saida.forward(request, response);
			}
			
			if(tela!=null && tela.equals("relUrgecia")){
				RelatorioBancoUEDAO rdDAO =  new RelatorioBancoUEDAO();
				String cod_p = request.getParameter("cod_p");
				String bt_acao = request.getParameter("bt_acao");
				String cod_u = request.getParameter("cod_u");
				String nome = request.getParameter("valorp");
				try{
					if(bt_acao!=null && bt_acao.equals("1")){
						int cod = Integer.parseInt(cod_p);
						List<Triagem> datasConsultas = rdDAO.getDatasExameClinico(cod);
						request.setAttribute("datasConsultas", datasConsultas);
						if(cod_u != null && !cod_u.equals(""))
						{
							cod = Integer.parseInt(cod_u);
							List<Triagem> exames = rdDAO.getExamesClinicos(cod);
							request.setAttribute("exames", exames);
						}
						saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=relUrgeExams");
					}
					
					else if(bt_acao!=null && bt_acao.equals("2")){
						int cod = Integer.parseInt(cod_p);
						List<Triagem> datasConsultas = rdDAO. getDatasRecomendacao(cod);
						request.setAttribute("datasConsultas", datasConsultas);
						if(cod_u != null && !cod_u.equals(""))
						{
							cod = Integer.parseInt(cod_u);
							Triagem recom = rdDAO.getRecomendacao(cod);
							request.setAttribute("recom", recom);
						}
						saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=relUrgeRecom");
					}
					else if(bt_acao!=null && bt_acao.equals("3")){
						int cod = Integer.parseInt(cod_p);
						List<Triagem> datasConsultas = rdDAO. getDatasReceituario(cod);
						request.setAttribute("datasConsultas", datasConsultas);
						if(cod_u != null && !cod_u.equals(""))
						{
							cod = Integer.parseInt(cod_u);
							 List<Farmacos> farmacos = rdDAO.getReceituarios(cod);
							request.setAttribute("farmacos", farmacos);
						}
						saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=relUrgeRecei");
					}
					else if(bt_acao!=null && bt_acao.equals("4")){
						int cod = Integer.parseInt(cod_p);
						List<Triagem> datasConsultas = rdDAO. getDatasJustificativos(cod);
						request.setAttribute("datasConsultas", datasConsultas);
						if(cod_u != null && !cod_u.equals(""))
						{
							request.setAttribute("mensg", "Prima o botao de Impressao para gerar pdf.");
						}
						saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=relUrgeJusti");
					}
					else if(bt_acao!=null && bt_acao.equals("5")){
						int cod = Integer.parseInt(cod_p);
						List<Triagem> datasConsultas = rdDAO. getDatasFichaAtendtimentos(cod);
						request.setAttribute("datasConsultas", datasConsultas);
						if(cod_u != null && !cod_u.equals(""))
						{
							request.setAttribute("mensg", "Prima o botï¿½o de Impressao para gerar pdf.");
						}
						saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=relUrgeFicha");
					}
					else{
						
						saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=relUrgecia");
					}
					List<Triagem> atendidosHJ = rdDAO.getAtendidosNaUrgenciaHJ();
					if(nome != null){
						atendidosHJ = rdDAO.getAtendidosNaUrgenciaHJ(nome);
						request.setAttribute("atendidosHJ", atendidosHJ);
					}

					request.setAttribute("atendidosHJ", atendidosHJ);
				}catch(Exception er){
					System.out.println(er.getMessage());
				}
				
				    saida.forward(request, response);
			}		
			if(tela!=null && tela.equals("relEmergencia")){
				RelatorioBancoUEDAO rdDAO =  new RelatorioBancoUEDAO();
				List<Paciente> atendidosEmer = null;
				String data_ini = request.getParameter("data_ini");
				String data_fim = request.getParameter("data_fim");
				String op = request.getParameter("nome_idade");
				String opg = request.getParameter("genero");
				String openc = request.getParameter("encam");
				 try{
					 if(data_ini!=null && data_fim!=null){
						Date data1 = new Formatando().dataSql(data_ini);
						Date data2 = new Formatando().dataSql(data_fim);
							if(op!=null && op.equals("1") && opg == null && openc == null){
								String nome = request.getParameter("nomep");
								atendidosEmer = rdDAO.getPacientesNaEmergencia(data1,data2,nome);
								System.out.println("NOME");
							}
							else if(op!=null && op.equals("2") && opg == null && openc == null){
								int idade1 = Integer.parseInt(request.getParameter("idade1"));
								int idade2 = Integer.parseInt(request.getParameter("idade2"));
								atendidosEmer = rdDAO.getPacientesNaEmergencia(data1,data2,idade1,idade2); 
								System.out.println("IDADE");
							}
							else if(op==null  && opg != null && openc == null){
								int sexo = Integer.parseInt(request.getParameter("sexo"));
								atendidosEmer = rdDAO.getPacientesNaEmergencia(data1,data2,sexo); 
								System.out.println("GENERO");
							}
							else if(op==null  && opg == null && openc != null){
								int encam_op = Integer.parseInt(request.getParameter("encam_op"));
								atendidosEmer = rdDAO.getPacientesNaEmergenciaEnc(data1,data2,encam_op); 
								System.out.println("ENCAMINHADO");
							}
							else if(op!=null  && op.equals("1") && opg != null && openc == null){
								String nome = request.getParameter("nomep");
								int sexo = Integer.parseInt(request.getParameter("sexo"));
								atendidosEmer = rdDAO.getPacientesNaEmergenciaNomGen(data1,data2,nome,sexo); 
								System.out.println("NOME-GENERO");
							}
							else if(op!=null  && op.equals("2") && opg != null && openc == null){
								int sexo = Integer.parseInt(request.getParameter("sexo"));
								int idade1 = Integer.parseInt(request.getParameter("idade1"));
								int idade2 = Integer.parseInt(request.getParameter("idade2"));
								atendidosEmer = rdDAO.getPacientesNaEmergenciaIdaGen(data1,data2,idade1,idade2,sexo); 
								System.out.println("IDADES-GENERO");
							}
							else if(op!=null  && op.equals("1") && opg == null && openc != null){
								String nome = request.getParameter("nomep");
								int encam_op = Integer.parseInt(request.getParameter("encam_op"));
								atendidosEmer = rdDAO.getPacientesNaEmergenciaNomEncam(data1,data2,nome,encam_op); 
								System.out.println("NOME-ENCAMINHADO");
							}
							else if(op==null   && opg != null && openc != null){
								int sexo = Integer.parseInt(request.getParameter("sexo"));
								int encam_op = Integer.parseInt(request.getParameter("encam_op"));
								atendidosEmer = rdDAO.getPacientesNaEmergenciaGenEncam(data1,data2,sexo,encam_op); 
								System.out.println("GENERO-ENCAMINHADO");
							}
							else
							{
								atendidosEmer = rdDAO.getPacientesNaEmergencia(data1,data2);
								System.out.println("DATAS");
							}
						}
						else
						{
							atendidosEmer = rdDAO.getPacientesNaEmergencia();
						}
				 }
				catch(Exception er){
					System.out.println(er.getMessage());
				}
				request.setAttribute("atendidosEmer", atendidosEmer);
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=relEmergencia");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("relHistourgencia")){
				RelatorioBancoUEDAO rdDAO =  new RelatorioBancoUEDAO();
				 
				List<Paciente> atendidosU = null;
				String data_ini = request.getParameter("data_ini");
				String data_fim = request.getParameter("data_fim");
				String op = request.getParameter("nome_idade");
				String opg = request.getParameter("genero");
				String openc = request.getParameter("encam");
				 try{
					 if(data_ini!=null && data_fim!=null){
						Date data1 = new Formatando().dataSql(data_ini);
						Date data2 = new Formatando().dataSql(data_fim);
							if(op!=null && op.equals("1") && opg == null && openc == null){
								String nome = request.getParameter("nomep");
								atendidosU = rdDAO.getPacientesNaUrgencia(data1,data2,nome);
								System.out.println("NOME");
							}
							else if(op!=null && op.equals("2") && opg == null && openc == null){
								int idade1 = Integer.parseInt(request.getParameter("idade1"));
								int idade2 = Integer.parseInt(request.getParameter("idade2"));
								atendidosU = rdDAO.getPacientesNaUrgencia(data1,data2,idade1,idade2); 
								System.out.println("IDADE");
							}
							else if(op==null  && opg != null && openc == null){
								int sexo = Integer.parseInt(request.getParameter("sexo"));
								atendidosU = rdDAO.getPacientesNaUrgencia(data1,data2,sexo); 
								System.out.println("GENERO");
							}
							else if(op==null  && opg == null && openc != null){
								int encam_op = Integer.parseInt(request.getParameter("encam_op"));
								atendidosU = rdDAO.getPacientesNaEmergenciaEnc(data1,data2,encam_op); 
								System.out.println("ENCAMINHADO");
							}
							else if(op!=null  && op.equals("1") && opg != null && openc == null){
								String nome = request.getParameter("nomep");
								int sexo = Integer.parseInt(request.getParameter("sexo"));
								atendidosU = rdDAO.getPacientesNaUrgenciaNomGen(data1,data2,nome,sexo); 
								System.out.println("NOME-GENERO");
							}
							else if(op!=null  && op.equals("2") && opg != null && openc == null){
								int sexo = Integer.parseInt(request.getParameter("sexo"));
								int idade1 = Integer.parseInt(request.getParameter("idade1"));
								int idade2 = Integer.parseInt(request.getParameter("idade2"));
								atendidosU = rdDAO.getPacientesNaUrgenciaIdaGen(data1,data2,idade1,idade2,sexo); 
								System.out.println("IDADES-GENERO");
							}
							else if(op!=null  && op.equals("1") && opg == null && openc != null){
								String nome = request.getParameter("nomep");
								int encam_op = Integer.parseInt(request.getParameter("encam_op"));
								atendidosU = rdDAO.getPacientesNaUrgenciaNomEncam(data1,data2,nome,encam_op); 
								System.out.println("NOME-ENCAMINHADO");
							}
							else if(op==null   && opg != null && openc != null){
								int sexo = Integer.parseInt(request.getParameter("sexo"));
								int encam_op = Integer.parseInt(request.getParameter("encam_op"));
								atendidosU = rdDAO.getPacientesNaUrgenciaGenEncam(data1,data2,sexo,encam_op); 
								System.out.println("GENERO-ENCAMINHADO");
							}
							else
							{
								atendidosU = rdDAO.getPacientesNaUrgencia(data1,data2);
								System.out.println("DATAS");
							}
						}
						else
						{
							atendidosU = rdDAO.getPacientesNaUrgencia();
						}
				 }
				catch(Exception er){
					System.out.println(er.getMessage());
				}
				  
				request.setAttribute("atendidosU", atendidosU);
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=relHistourgencia");
				saida.forward(request, response);
			}
			
			if(tela!=null && tela.equals("histConsAgend")){
				String nomePaciente = request.getParameter("nomePac");
			    String idade 		= request.getParameter("idade");
			    String servico 		= request.getParameter("servico");
			    String sexo 		= request.getParameter("sexo");
			    String medico 		= request.getParameter("medico");
			    String idades 		= request.getParameter("idades");
			    String dataInicio 	= request.getParameter("dataI");
			    String dataFinal 	= request.getParameter("dataF");
			    String axu 			= request.getParameter("pesquisar");
			    
				PacienteAgendadoDAO paDAO = new PacienteAgendadoDAO();
				List <Paciente> lista = null;

				if(dataInicio!=null && !dataInicio.equals("") && dataFinal!=null && !dataFinal.equals("")){
				Date dtInicio = new Formatando().dataSql(dataInicio);
				Date dtFinal = new Formatando().dataSql(dataFinal);
				
				if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals(""))
			    {
			    		lista = paDAO.buscarPorNome(dtInicio, dtFinal, nomePaciente);
			    }
				else if(dataInicio!=null && !dataInicio.equals("") && dataFinal!=null && !dataFinal.equals("") && idade!=null && !idade.equals("") && idades!=null && !idades.equals(""))
			    {
			    		lista = paDAO.buscarPorIdades(dtInicio, dtFinal, idade, idades);
			    }
			    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade!=null && !idade.equals(""))
			    {
			    		lista = paDAO.buscarPorIdade(dtInicio, dtFinal, idade); 
			    }
			    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && servico!=null && !servico.equals(""))
			    {
			  			lista = paDAO.buscarPorServico(dtInicio, dtFinal, servico);
			    }
			    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && sexo!=null && !sexo.equals(""))
			    {
			    		lista = paDAO.buscarPorGenero(dtInicio,dtFinal,sexo);
			    }
			    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && medico!=null && !medico.equals(""))
			    {
			    		lista = paDAO.buscarPorMedico(dtInicio,dtFinal,medico);
			    }
				else if( dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals(""))
			    {
			    		lista = paDAO.buscarPorData(dtInicio,dtFinal);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals("") && idade!=null && !idade.equals("") && idades!=null && !idades.equals(""))
			    {
			    		lista = paDAO.buscarPorNomeAndIdade(dtInicio,dtFinal,nomePaciente, idade, idades);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals("") && sexo!=null && !sexo.equals(""))
			    {
			    		lista = paDAO.buscarPorNomeAndGenero(dtInicio,dtFinal,nomePaciente,sexo);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals("") && servico!=null && !servico.equals(""))
			    {
			    		lista = paDAO.buscarPorNomeAndServico(dtInicio,dtFinal,nomePaciente,servico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals("") && medico!=null && !medico.equals(""))
			    {
			    		lista = paDAO.buscarPorNomeAndMedico(dtInicio,dtFinal,nomePaciente,medico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade!=null && !idade.equals("") && idades!=null && !idades.equals("") && sexo!=null && !sexo.equals(""))
			    {
			    		lista = paDAO.buscarPorIdadesAndGenero(dtInicio,dtFinal,idade,idades,sexo);
			    }
				else if(dtInicio!=null&&!dtInicio.equals("") && dtFinal!=null&&!dtFinal.equals("") && idade!=null&&!idade.equals("") && idades!=null&&!idades.equals("") && servico!=null&&!servico.equals(""))
			    {
			    		lista = paDAO.buscarPorIdadesAndGeneroAndServico(dtInicio,dtFinal,idade,idades,sexo,servico);
			    }
				else if(dtInicio!=null&&!dtInicio.equals("") && dtFinal!=null&&!dtFinal.equals("") && idade!=null&&!idade.equals("") && idades!=null&&!idades.equals("") && sexo!=null&&!sexo.equals("") && medico!=null && !medico.equals(""))
			    {
			    		lista = paDAO.buscarPorIdadesAndGeneroAndMedico(dtInicio,dtFinal,idade,idades,sexo,medico);
			    }
				else if(dtInicio!=null&&!dtInicio.equals("") && dtFinal!=null&&!dtFinal.equals("") && sexo!=null&&!sexo.equals("") && servico!=null&&!servico.equals(""))
			    {
			    		lista = paDAO.buscarPorGeneroAndServico(dtInicio,dtFinal,sexo,servico);
			    }
				else if(dtInicio!=null&&!dtInicio.equals("") && dtFinal!=null&&!dtFinal.equals("") && sexo!=null&&!sexo.equals("") && medico!=null && !medico.equals(""))
			    {
			    		lista = paDAO.buscarPorGeneroAndMedico(dtInicio,dtFinal,sexo,medico);
			    }
				else if(dtInicio!=null&&!dtInicio.equals("") && dtFinal!=null&&!dtFinal.equals("") && servico!=null && !servico.equals("") && medico!=null && !medico.equals(""))
			    {
			    		lista = paDAO.buscarPorServicoAndMedico(dtInicio,dtFinal,servico,medico);
			    }
				}
				else{
					lista = paDAO.buscarPaciente();
				}
				
				request.setAttribute("lista", lista);
				request.setAttribute("axu", axu);
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=histConsAgend");
				saida.forward(request, response);
			}
			
			if(tela!=null && (tela.equals("histConfirm") || tela.equals("PesquisarH"))){
				
				String nome = request.getParameter("nomeDoPaciente");
				String idade1 = request.getParameter("idade1");
				String idade2 = request.getParameter("idade2");
				String genero = request.getParameter("sexo");
				String servico = request.getParameter("servico");
				String medico = request.getParameter("medico");
				String dataInicio = request.getParameter("dataI");
				String dataFinal = request.getParameter("dataF");
				String axu = request.getParameter("pesquisar");
				
				PacienteAgendadoDAO paDAO = new PacienteAgendadoDAO();
				ConfirmarDAO cfDAO = new ConfirmarDAO();
				List <Paciente> preenchertabela = cfDAO.buscarmarcacaotabela();
				List <Paciente> lista = null;
				
				if(dataInicio!=null && !dataInicio.equals("") && dataFinal!=null && !dataFinal.equals("")){
					Date dtInicio = new Formatando().dataSql(dataInicio);
					Date dtFinal = new Formatando().dataSql(dataFinal);
					
				if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nome!=null && !nome.equals(""))
				{
				    	lista = paDAO.buscarPorNomeConf(dtInicio, dtFinal, nome);
				}
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") &&idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals(""))
			    {
			    		lista = paDAO.buscarPorIdadeConfs(dtInicio,dtFinal,idade1,idade2);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade1!=null && !idade1.equals(""))
				{	
				    	lista = paDAO.buscarPorIdadeConf(dtInicio,dtFinal,idade1);
				}
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && genero!=null && !genero.equals(""))
			    {		
			    		lista = paDAO.buscarPorGeneroConf(dtInicio, dtFinal,genero);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && servico!=null && !servico.equals(""))
			    {
			    		lista = paDAO.buscarPorServicoConf(dtInicio,dtFinal,servico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && medico!=null && !medico.equals(""))
			    {
			    		lista = paDAO.buscarPorMedicoConf(dtInicio,dtFinal,medico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals(""))
			    {
			    		lista = paDAO.buscarPorDataConf(dtInicio,dtFinal);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nome!=null&&!nome.equals("") && idade1!=null&&idade1.equals("") && idade2!=null&&idade2.equals(""))
			    {
			    		lista = paDAO.buscarPorNomeAndIdadeConf(dtInicio,dtFinal, nome, idade1, idade2);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("")&& nome!=null&&!nome.equals("") && genero!=null&&!genero.equals(""))
			    {
			    		lista = paDAO.buscarPorNomeAndGeneroConf(dtInicio,dtFinal, nome, genero);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("")&& nome!=null&&!nome.equals("") && servico!=null&&!servico.equals(""))
			    {
			    		lista = paDAO.buscarPorNomeAndServicoConf(dtInicio,dtFinal, nome, servico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("")&& nome!=null&&!nome.equals("") && medico!=null&&!medico.equals(""))
			    {
			    		lista = paDAO.buscarPorNomeAndMedicoConf(dtInicio,dtFinal, nome, medico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("")&& idade1!=null&&!idade1.equals("")&& idade2!=null&&!idade2.equals("") && genero!=null&&!genero.equals(""))
			    {
			    		lista = paDAO.buscarPorIdadeAndGeneroConf(dtInicio,dtFinal,idade1,idade2,genero);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("")&& idade1!=null&&!idade1.equals("")&& idade2!=null&&!idade2.equals("") && servico!=null&&!servico.equals(""))
			    {
			    		lista = paDAO.buscarPorIdadeAndServicoConf(dtInicio,dtFinal,idade1,idade2,servico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("")&& idade1!=null&&!idade1.equals("")&& idade2!=null&&!idade2.equals("") && medico!=null&&!medico.equals(""))
			    {
			    		lista = paDAO.buscarPorIdadeAndMedicoConf(dtInicio,dtFinal,idade1,idade2,medico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("")&& genero!=null&&!genero.equals("") && servico!=null&&!servico.equals(""))
			    {
			    		lista = paDAO.buscarPorGeneroAndServicoConf(dtInicio,dtFinal,genero,servico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("")&& genero!=null&&!genero.equals("") && medico!=null&&!medico.equals(""))
			    {
			    		lista = paDAO.buscarPorGeneroAndMedicoConf(dtInicio,dtFinal,genero,medico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("")&& servico!=null&&!servico.equals("") && medico!=null&&!medico.equals(""))
			    {
			    		lista = paDAO.buscarPorServicoAndMedicoConf(dtInicio,dtFinal,servico,medico);
			    }
				
				}else{
						lista = paDAO.buscarPacienteConfirmado();
				}
				
				request.setAttribute("lista", lista);
				request.setAttribute("listas", preenchertabela);
				request.setAttribute("axu", axu);
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=histConfirm");
				saida.forward(request, response);
			}
			
			if(tela!=null && (tela.equals("agendamEx")||tela.equals("agendExams")))
			{
				PacienteExameDAO eDAO = new PacienteExameDAO();				 
				ListaCarrinho listaTabela = null;
				
				String acao = request.getParameter("acao");
				if(acao!=null && acao.equals("ver")){
 			    	String cod = request.getParameter("cod");
 			    	int codInt = Integer.parseInt(cod);
 			    	List <Servico> VeFactura = new ServicoDAO().getServicosExamesAgendados(codInt);
 			    	request.setAttribute("VeFactura", VeFactura);
 			    }
 			    else{
 			    	if(request.getAttribute("VeFactura")!=null){
 			    		request.removeAttribute("VeFactura");
 			    	}
 			    }
				
				if (ss.getAttribute("listaTabela")!=null) {
					listaTabela = (ListaCarrinho) ss.getAttribute("listaTabela");
				}
				else{
					List<Paciente> dados = eDAO.buscarAgendadosDiario();
					
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
					
				}
				request.setAttribute("listaTabela", listaTabela);
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=agendamEx");
				saida.forward(request, response);
			}
			
			if(tela!=null && tela.equals("agendExasHs"))
			{
				PacienteExameDAO eDAO = new PacienteExameDAO();				 
				ListaCarrinho listaTabela = null;
				String acao = request.getParameter("acao");
				if(acao!=null && acao.equals("ver")){
 			    	String cod = request.getParameter("cod");
 			    	int codInt = Integer.parseInt(cod);
 			    	List <Servico> VeFactura = new ServicoDAO().getServicosExamesRequisitados(codInt);
 			    	request.setAttribute("VeFactura", VeFactura);
 			    }
 			    else{
 			    	if(request.getAttribute("VeFactura")!=null){
 			    		request.removeAttribute("VeFactura");
 			    	}
 			    }
				
				if (ss.getAttribute("listaTabelaEX")!=null) {
					listaTabela = (ListaCarrinho) ss.getAttribute("listaTabelaEX");
				}
				else{
					List<Paciente> dados = eDAO.buscarAgendadosConfirmadosDiario();
					
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
					
				}

				request.setAttribute("listaTabela", listaTabela);
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=agendExasHs");
				saida.forward(request, response);
			}
			// Balão Agendamento Teste de Qualidade
			if(tela!=null && (tela.equals("agendamTq")||tela.equals("agendTestq"))){
				PacienteExameDAO eDAO = new PacienteExameDAO();				 
				ListaCarrinho listaTabela = null;
				
				if (ss.getAttribute("listaTabelaEX")!=null) {
					listaTabela = (ListaCarrinho) ss.getAttribute("listaTabelaEX");
				}
				else{
					List<Paciente> dados = eDAO.buscarAgendadosDiario();
					
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
					
				}
				request.setAttribute("listaTabela", listaTabela);
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=agendamTq");
				saida.forward(request, response);
			}
			
			
			if(tela!=null && (tela.equals("histCadPac") ||tela.equals("PesquisarP"))){
			
			PacienteDAO pcDAO = new PacienteDAO();
			List<Paciente> lista_pais = pcDAO.buscaPais();
			
			String nome 		= request.getParameter("nome");
			String idade1 		= request.getParameter("idade1");
			String idade2 		= request.getParameter("idade2");
			String genero 		= request.getParameter("sexo");
			String dataInicio 	= request.getParameter("dataI");
			String dataFinal 	= request.getParameter("dataF");
			String fk_pais 		= request.getParameter("pais");
			String fk_provincia	= request.getParameter("provincia");
			String fk_municipio	= request.getParameter("municipio");
			String fk_distrito 	= request.getParameter("distrito");
			String axu 			= request.getParameter("pesquisar");
			
			PacienteAgendadoDAO paDAO = new PacienteAgendadoDAO();
			List <PacienteRelatorio> lista = null;
			
			if(dataInicio!=null && !dataInicio.equals("") && dataFinal!=null && !dataFinal.equals("")){
				Date dtInicio = new Formatando().dataSql(dataInicio);
				Date dtFinal = new Formatando().dataSql(dataFinal);
				
				String pais = new PacienteAgendadoDAO().buscarPais(fk_pais);
				String provincia = new PacienteAgendadoDAO().buscarProvincia(fk_provincia); 
				String municipio = new PacienteAgendadoDAO().buscarMunicipio(fk_municipio);
				String distrito = new PacienteAgendadoDAO().buscarDistrito(fk_distrito);
											
			if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nome!=null && !nome.equals(""))
		    {
		    		lista = paDAO.buscarPorNomeHistPaciente(dtInicio,dtFinal,nome);
		    }
			else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals(""))
		    {
		    		lista = paDAO.buscarPorIdadeHistPaciente(dtInicio,dtFinal,idade1,idade2);
		    }
			else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade1!=null && !idade1.equals(""))
		    {
		    		lista = paDAO.buscarPorIdadeHistPaciente(dtInicio,dtFinal,idade1);
		    }
			else if(dataInicio!=null && !dataInicio.equals("") && dataFinal!=null && !dataFinal.equals("") && genero!=null && !genero.equals(""))
		    {
		    		lista = paDAO.buscarPorGeneroHistPaciente(dtInicio,dtFinal,genero);
		    }
			else if(dataInicio!=null && !dataInicio.equals("") && dataFinal!=null && !dataFinal.equals("") && pais!=null && !pais.equals("") && provincia!=null && !provincia.equals("") && municipio!=null && !municipio.equals("") && distrito!=null && !distrito.equals(""))
		    {
		//    		lista = paDAO.buscarPorNacionalidadeHistPaciente(dtInicio,dtFinal, pais, provincia,municipio,distrito);
		    }
			else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nome!=null && !nome.equals("") && idade1!=null && !idade1.equals(""))
		    {
		//    		lista = paDAO.buscarPorNomeHiPacienteAndIdade(dtInicio,dtFinal,nome,idade1);
		    }
			else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nome!=null && !nome.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals(""))
			{
		//			lista = paDAO.buscarPorNomeHiPacienteAndIdade(dtInicio,dtFinal,nome,idade1,idade2);
			}
			else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nome!=null && !nome.equals("") && genero!=null && !genero.equals(""))
		    {
		//    		lista = paDAO.buscarPorNomeHiPacienteAndGenero(dtInicio,dtFinal,nome,genero);
		    }
			else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nome!=null && !nome.equals("") && pais!=null && !pais.equals("") && provincia!=null && !provincia.equals("") && municipio!=null && !municipio.equals("") && distrito!=null && !distrito.equals(""))
		    {
		//    		lista = paDAO.buscarPorNomeHiPacienteAndNacional(dtInicio,dtFinal,nome,pais,provincia,municipio,distrito);
		    }
			else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && genero!=null && !genero.equals(""))
		    {
		//    		lista = paDAO.buscarPorIdadeHiPacienteAndGenero(dtInicio,dtFinal,idade1,idade2,genero);
		    }
			else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && pais!=null && !pais.equals("") && provincia!=null && !provincia.equals("") && municipio!=null && !municipio.equals("") && distrito!=null && !distrito.equals(""))
		    {
		//    		lista = paDAO.buscarPorIdadeHiPacienteAndNacional(dtInicio,dtFinal,idade1,idade2,pais,provincia,municipio,distrito);
		    }
			else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && pais!=null && !pais.equals("") && provincia!=null && !provincia.equals("") && municipio!=null && !municipio.equals("") && distrito!=null && !distrito.equals(""))
		    {
		//    		lista = paDAO.buscarPorGeneroHiPacienteAndNacional(dtInicio,dtFinal,genero,pais,provincia,municipio,distrito);
		    }
			else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals(""))
		    {
		    		lista = paDAO.buscarPorDataHistPaciente(dtInicio,dtFinal);
		    		
		    		 
		    }
			}else{
					lista = paDAO.buscarPacienteCadastro();
			}
			
			request.setAttribute("pais", lista_pais);
			request.setAttribute("axu", axu);
			ss.setAttribute("lista", lista);
			
			saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=histCadPac");
			saida.forward(request, response);
		}
			
			if(tela!=null && tela.equals("relAtendMed")){
				RelatorioConsultaDAO rcDao = new RelatorioConsultaDAO();
				List<Paciente> consultados = new ArrayList<Paciente>();
				String cod_p = request.getParameter("cod_p");
				String bt_acao = request.getParameter("bt_acao");
				String cod_c = request.getParameter("cod_c");
				String acao = request.getParameter("acao");
				String valorp = request.getParameter("valorp");
				
				if(acao!=null && acao.equals("ver")){
					request.setAttribute("modal", acao);
					int cod = Integer.parseInt(cod_p);
					Paciente pac = rcDao.getPaciente(cod);
					request.setAttribute("pac", pac);
				}
				
				if(valorp!=null){
					 consultados = rcDao.getPacientesConsultados(valorp);
				}
				else{
				 consultados = rcDao.getPacientesConsultados();
				}
				request.setAttribute("consultados", consultados);
				
				if(bt_acao !=null && bt_acao.equals("1")){
					int cod = Integer.parseInt(cod_p);
					List<Paciente> datasConsultas = rcDao.getPacientesConsultadosExames(cod);
					request.setAttribute("datasConsultas", datasConsultas);
					if(cod_c != null && !cod_c.equals(""))
					{
						cod = Integer.parseInt(cod_c);
						List<Triagem> examesClinicos = rcDao.getExamesClinicos(cod);
						request.setAttribute("examesClinicos", examesClinicos);
					}
					saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=relExamesCl");
				}
				
				else if(bt_acao !=null && bt_acao.equals("2")){
					int cod = Integer.parseInt(cod_p);
					List<Paciente> datasConsultas = rcDao.getPacientesConsultadosTratamRecom(cod);
					request.setAttribute("datasConsultas", datasConsultas);
					if(cod_c != null && !cod_c.equals(""))
					{
						cod = Integer.parseInt(cod_c);
						Paciente tratam = rcDao.getPacienteTratamRecom(cod);
						request.setAttribute("tratam", tratam);
					}
					saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=relTratam");
				}
				else if(bt_acao !=null && bt_acao.equals("3")){
					int cod = Integer.parseInt(cod_p);
					List<Paciente> datasConsultas = rcDao.getPacientesConsultadosTratamRecom(cod);
					request.setAttribute("datasConsultas", datasConsultas);
					if(cod_c != null && !cod_c.equals(""))
					{
						cod = Integer.parseInt(cod_c);
						Paciente tratam = rcDao.getPacienteTratamRecom(cod);
						request.setAttribute("tratam", tratam);
					}
					saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=relRecom");
				}
				else if(bt_acao !=null && bt_acao.equals("4")){
					int cod = Integer.parseInt(cod_p);
					List<Paciente> datasConsultas = rcDao.getPacientesConsultadosReceituario(cod);
					request.setAttribute("datasConsultas", datasConsultas);
					if(cod_c != null && !cod_c.equals(""))
					{
						cod = Integer.parseInt(cod_c);
						List<Triagem> farmacos = rcDao.getPacientesReceituario(cod);
						request.setAttribute("farmacos", farmacos);
					}
					saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=relReceit");
				}
				else if(bt_acao !=null && bt_acao.equals("5")){
					int cod = Integer.parseInt(cod_p);
					List<Paciente> datasConsultas = rcDao.getPacientesNaConsulta(cod);
					request.setAttribute("datasConsultas", datasConsultas);
					if(cod_c != null && !cod_c.equals(""))
					{
						request.setAttribute("mensg", "Prima o botï¿½o de Impressao para gerar pdf.");
					}
					saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=relFicha");
				}
				else if(bt_acao !=null && bt_acao.equals("6")){
					int cod = Integer.parseInt(cod_p);
					List<Paciente> datasConsultas = rcDao.getJusticativosNaConsulta(cod);
					request.setAttribute("datasConsultas", datasConsultas);
					if(cod_c != null && !cod_c.equals(""))
					{
						request.setAttribute("mensg", "Prima o botï¿½o de Impressao para gerar pdf.");
					}
					saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=relJusti");
				}
				else{
					saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=relAtendMed");
				}
				
				
				saida.forward(request, response);
			}
		
			if(tela!=null && tela.equals("histPagCons"))
			{
				RelatorioPagamentosDAO rpDao = new RelatorioPagamentosDAO();
				Formatando fmt = new Formatando();
				List <Servico> servicos = new ServicoDAO().BuscarServico();
				List<Facturacao> servFaturados = null;
				
				String data_ini = request.getParameter("data_ini");
				String data_fim = request.getParameter("data_fim");
				String opcao = request.getParameter("escolha");
				String s_pagos = request.getParameter("s_pagos");
				String n_pagos = request.getParameter("n_pagos");
				String servico = request.getParameter("servico_hp");
				String servIsento = request.getParameter("servico_hp1");
				String serviPlano = request.getParameter("servico_hp2");
				
				
				if(data_ini != null && data_fim !=null ){
					Date inicio = fmt.dataSql(data_ini);
					Date fim = fmt.dataSql(data_fim);
					
				if(opcao!=null && opcao.equals("3") &&  !serviPlano.equals("0")){
						int m = Integer.parseInt(opcao);
						int op = 0;
						int ser = Integer.parseInt(serviPlano);
						servFaturados = rpDao.FaturadosPagamentosIsentosPlanosOpcaoServico(inicio, fim, m,op,ser);
						System.out.println("P-PAGOS-SERVICO");
						}
				else if(opcao!=null && opcao.equals("1") && s_pagos != null && !servico.equals("0")){
					int m = Integer.parseInt(opcao);
					int op = Integer.parseInt(s_pagos);
					int ser = Integer.parseInt(servico);
					servFaturados = rpDao.FaturadosPagamentosIsentosPlanosOpcaoServico(inicio, fim, m,op,ser);
					System.out.println("PAGOS-OPCAO-SERVICO");
					}
				else if(opcao!=null && opcao.equals("2") && n_pagos != null && !servIsento.equals("0")){
					int m = Integer.parseInt(opcao);
					int op = Integer.parseInt(n_pagos);
					int ser = Integer.parseInt(servIsento);
					servFaturados = rpDao.FaturadosPagamentosIsentosPlanosOpcaoServico(inicio, fim, m,op,ser);
					System.out.println("N-PAGOS-OPCAO-SERVICO");
					}
				else if(opcao!=null && opcao.equals("1") && s_pagos != null && servico.equals("0")){
					int m = Integer.parseInt(opcao);
					int op = Integer.parseInt(s_pagos);
					servFaturados = rpDao.FaturadosPagamentosIsentosOpcao(inicio, fim, m,op);
					System.out.println("PAGOS-OPCAO");
					}
				else if(opcao!=null && opcao.equals("2") && n_pagos != null && servIsento.equals("0")){
					int m = Integer.parseInt(opcao);
					int op = Integer.parseInt(n_pagos);
					servFaturados = rpDao.FaturadosPagamentosIsentosOpcao(inicio, fim, m,op);
					System.out.println("N-PAGOS-OPCAO");
					}
				else if(opcao!=null && opcao.equals("2") && n_pagos == null && !servIsento.equals("0")){
					int m = Integer.parseInt(opcao);
					servFaturados = rpDao.FaturadosComPagamentoIsentoPlanoServico(inicio, fim, m,servIsento);
					System.out.println("N-PAGOS-SERVICO");
					}
				else if(opcao!=null && opcao.equals("1") && s_pagos == null && !servico.equals("0")){
					int m = Integer.parseInt(opcao);
					servFaturados = rpDao.FaturadosComPagamentoIsentoPlanoServico(inicio, fim, m,servico);
					System.out.println("PAGOS-SERVICO");
				}
				else if(opcao!=null && opcao.equals("1") && s_pagos == null && servico.equals("0")){
					int m = Integer.parseInt(opcao);
					servFaturados = rpDao.FaturadosPagamentosIsentosPlanos(inicio, fim, m);
					System.out.println("PAGOS");
				}
				else if(opcao!=null && opcao.equals("2") && n_pagos == null && servIsento.equals("0")){
						int m = Integer.parseInt(opcao);
						servFaturados = rpDao.FaturadosPagamentosIsentosPlanos(inicio, fim, m);
						System.out.println("N-PAGOS");
				}
				else if(opcao!=null && opcao.equals("3") &&  serviPlano.equals("0")){
						int m = Integer.parseInt(opcao);
						servFaturados = rpDao.FaturadosPagamentosIsentosPlanos(inicio, fim, m);
						System.out.println("P-PAGOS");
				}else{
						servFaturados = rpDao.getFaturadosNasDatas(inicio, fim);
						System.out.println("DATAS");
					}						
				}
				else{
					servFaturados = rpDao.getServicosFaturados();
				}
				request.setAttribute("servicos", servicos);
				request.setAttribute("servFaturados", servFaturados);
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=histPagCons");
				saida.forward(request, response);
			}
//-----------------------------------------------------------------------------------------------------------------			
			// Pagamentos Teste de Qualidade
			
			if(tela!=null && ((tela.equals("pagmTestq") ||tela.equals("Pesquisar") || tela.equals("pagaTestesq"))) )
			{
				
				 String acao = request.getParameter("acao");
				 String xpesq = request.getParameter("valorp");
				
				 List <Paciente> vendas = new HistVendaDAO().buscarhistVendas();
	 			    if(acao!=null && acao.equals("ver")){
	 			    	String cod = request.getParameter("cod");
	 			    	List <Exames> VeFactura = new HistVendaDAO().buscarVisualizarHistVendas(cod);
	 			    	request.setAttribute("VeFactura", VeFactura);
	 			    }
	 			    else{
	 			    	if(request.getAttribute("VeFactura")!=null){
	 			    		request.removeAttribute("VeFactura");
	 			    	}
	 			    }
	 			    if(xpesq != null){
	 			    	vendas = new HistVendaDAO().buscarhistVendaAndPesq(xpesq);
	 			    }
	 			   
				   request.setAttribute("exame", vendas);
				
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=pagaTestesq");
				saida.forward(request, response);
			}

			if(tela!=null && (tela.equals("histPagaTestesq")))
			{
				
				Formatando fmt = new Formatando();
				List <Exames> lista = new ExamesDAO().buscagrupoexame();
				List <Exames> vendasRelatorios = null;
				
				String data_ini = request.getParameter("data_ini");
				String data_fim = request.getParameter("data_fim");
				String opcao = request.getParameter("escolha");
				String s_pagos = request.getParameter("s_pagos");
				String n_pagos = request.getParameter("n_pagos");
				String exame = request.getParameter("exame_hp");
				String examIsento = request.getParameter("exame_hp1");
				String examePlano = request.getParameter("exame_hp2");

				if(data_ini != null && data_fim != null)
				{
					Date inicio = fmt.dataSql(data_ini);
					Date fim = fmt.dataSql(data_fim);
				
				if(opcao!=null && opcao.equals("1") && s_pagos != null && !exame.equals("0")){
						int m = Integer.parseInt(opcao);
						int op = Integer.parseInt(s_pagos);
						int ex = Integer.parseInt(exame);
						vendasRelatorios = new RelatorioPagamentosDAO().buscarPagamentosIsentosPlanosOpcaoEx(inicio, fim, m,op,ex);
						System.out.println("PAGOS-OPCAO-SERVICO");
				}
				else if(opcao!=null && !equals("3") && !examePlano.equals("0")){
					int m = Integer.parseInt(opcao);
					int op = 0;
					int ex = Integer.parseInt(examePlano);
					vendasRelatorios = new RelatorioPagamentosDAO().buscarPagamentosIsentosPlanosOpcaoExame(inicio, fim, m, op, ex);
					System.out.println("P-PAGOS-SERVICO");
				}
				else if(opcao!=null && opcao.equals("2") && n_pagos != null && !examIsento.equals("0")){
					int m = Integer.parseInt(opcao);
					int op = Integer.parseInt(n_pagos);
					int ex = Integer.parseInt(examIsento);
					vendasRelatorios = new RelatorioPagamentosDAO().buscarPagamentosIsentosPlanosOpcaoExmeIsento(inicio, fim, m, op, ex);
					System.out.println("N-PAGOS-OPCAO-SERVICO");
				}
				else if(opcao!=null && opcao.equals("1") && s_pagos != null && exame.equals("0")){
					int m = Integer.parseInt(opcao);
					int op = Integer.parseInt(s_pagos);
					vendasRelatorios = new RelatorioPagamentosDAO().buscarPagamentosIsentosOpcaox(inicio, fim, m,op);
					System.out.println("PAGOS-OPCAO");
				}
				else if(opcao!=null && opcao.equals("1") && s_pagos == null && !exame.equals("0")){
					int m = Integer.parseInt(opcao);
					vendasRelatorios = new RelatorioPagamentosDAO().buscarComPagamentoIsentoPlanoExame(inicio, fim, m,exame);
					System.out.println("PAGOS-SERVICO");
				}
				else if(opcao!=null && opcao.equals("1") && s_pagos == null && exame.equals("0")){
					int m = Integer.parseInt(opcao);
					vendasRelatorios = new RelatorioPagamentosDAO().buscarPagamentosIsentosPlanosx(inicio, fim, m);
					System.out.println("PAGOS");
				}
				else if(opcao!=null && opcao.equals("2") && n_pagos != null && examIsento.equals("0")){
					int m = Integer.parseInt(opcao);
					int op = Integer.parseInt(n_pagos);
					vendasRelatorios = new RelatorioPagamentosDAO().buscarPagamentosIsentosOpcaob(inicio, fim, m,op);
					System.out.println("N-PAGOS-OPCAO");
				}
				else if(opcao!=null && opcao.equals("2") && n_pagos == null && !exame.equals("0")){
					int m = Integer.parseInt(opcao);
					vendasRelatorios = new RelatorioPagamentosDAO().buscarComPagamentoIsentoPlanoExamea(inicio, fim, m, exame);
					System.out.println("N-PAGOS-SERVICO");
				}
				else if(opcao!=null && opcao.equals("2") && n_pagos == null && examIsento.equals("0")){
					int m = Integer.parseInt(opcao);
					vendasRelatorios = new RelatorioPagamentosDAO().buscarPagamentosIsentosPlanoc(inicio, fim, m);
					System.out.println("N-PAGOS");
				}
				else if(opcao!=null && opcao.equals("3") && examePlano.equals("0")){
					int ex = Integer.parseInt(opcao);
					vendasRelatorios = new RelatorioPagamentosDAO().buscarPagamentosIsentosPlanosDeExame(inicio, fim, ex);
					System.out.println("P-PAGOS");
				}
				else if(inicio != null && fim != null){
					vendasRelatorios = new RelatorioPagamentosDAO().buscarhistVendasRelatorio(inicio, fim);
					System.out.println("DATAS");
				}
				}
				
				else{
					vendasRelatorios = new RelatorioPagamentosDAO().buscarhistVendasRelatorio();
				}
				request.setAttribute("lista", lista);
				request.setAttribute("vendasRelatorios", vendasRelatorios);
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=histPagaTestesq");
				saida.forward(request, response);	
			}
			
			
//-----------------------------------------------------------------------------------------------------------------//
			
			if(tela!=null && ((tela.equals("pagmExms") ||tela.equals("Pesquisar") || tela.equals("pagaExames"))) )
			{
				
				 String acao = request.getParameter("acao");
				 String xpesq = request.getParameter("valorp");
				
				 List <Paciente> vendas = new HistVendaDAO().buscarhistVendas();
	 			    if(acao!=null && acao.equals("ver")){
	 			    	String cod = request.getParameter("cod");
	 			    	List <Exames> VeFactura = new HistVendaDAO().buscarVisualizarHistVendas(cod);
	 			    	request.setAttribute("VeFactura", VeFactura);
	 			    }
	 			    else{
	 			    	if(request.getAttribute("VeFactura")!=null){
	 			    		request.removeAttribute("VeFactura");
	 			    	}
	 			    }
	 			    if(xpesq != null){
	 			    	vendas = new HistVendaDAO().buscarhistVendaAndPesq(xpesq);
	 			    }
	 			   
				   request.setAttribute("exame", vendas);
				
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=pagaExames");
				saida.forward(request, response);
			}
			
			if(tela!=null && (tela.equals("histPagaExames")))
			{
				
				Formatando fmt = new Formatando();
				List <Exames> lista = new ExamesDAO().buscagrupoexame();
				List <Exames> vendasRelatorios = null;
				
				String data_ini = request.getParameter("data_ini");
				String data_fim = request.getParameter("data_fim");
				String opcao = request.getParameter("escolha");
				String s_pagos = request.getParameter("s_pagos");
				String n_pagos = request.getParameter("n_pagos");
				String exame = request.getParameter("exame_hp");
				String examIsento = request.getParameter("exame_hp1");
				String examePlano = request.getParameter("exame_hp2");

				if(data_ini != null && data_fim != null)
				{
					Date inicio = fmt.dataSql(data_ini);
					Date fim = fmt.dataSql(data_fim);
				
				if(opcao!=null && opcao.equals("1") && s_pagos != null && !exame.equals("0")){
						int m = Integer.parseInt(opcao);
						int op = Integer.parseInt(s_pagos);
						int ex = Integer.parseInt(exame);
						vendasRelatorios = new RelatorioPagamentosDAO().buscarPagamentosIsentosPlanosOpcaoEx(inicio, fim, m,op,ex);
						System.out.println("PAGOS-OPCAO-SERVICO");
				}
				else if(opcao!=null && opcao.equals("3") && !examePlano.equals("0"))
				{
					int m = Integer.parseInt(opcao);
					System.out.println("opcao: "+opcao);
					System.out.println("m: "+m);
					int op = 0;
					int ex = Integer.parseInt(examePlano);
					vendasRelatorios = new RelatorioPagamentosDAO().buscarPagamentosIsentosPlanosOpcaoExame(inicio, fim, m, op, ex);
					System.out.println("P-PAGOS-SERVICO");
				}
				else if(opcao!=null && opcao.equals("2") && n_pagos != null && !examIsento.equals("0")){
					int m = Integer.parseInt(opcao);
					int op = Integer.parseInt(n_pagos);
					int ex = Integer.parseInt(examIsento);
					vendasRelatorios = new RelatorioPagamentosDAO().buscarPagamentosIsentosPlanosOpcaoExmeIsento(inicio, fim, m, op, ex);
					System.out.println("N-PAGOS-OPCAO-SERVICO");
				}
				else if(opcao!=null && opcao.equals("1") && s_pagos != null && exame.equals("0")){
					int m = Integer.parseInt(opcao);
					int op = Integer.parseInt(s_pagos);
					vendasRelatorios = new RelatorioPagamentosDAO().buscarPagamentosIsentosOpcaox(inicio, fim, m,op);
					System.out.println("PAGOS-OPCAO");
				}
				else if(opcao!=null && opcao.equals("1") && s_pagos == null && !exame.equals("0")){
					int m = Integer.parseInt(opcao);
					vendasRelatorios = new RelatorioPagamentosDAO().buscarComPagamentoIsentoPlanoExame(inicio, fim, m,exame);
					System.out.println("PAGOS-SERVICO");
				}
				else if(opcao!=null && opcao.equals("1") && s_pagos == null && exame.equals("0")){
					int m = Integer.parseInt(opcao);
					vendasRelatorios = new RelatorioPagamentosDAO().buscarPagamentosIsentosPlanosx(inicio, fim, m);
					System.out.println("PAGOS");
				}
				else if(opcao!=null && opcao.equals("2") && n_pagos != null && examIsento.equals("0")){
					int m = Integer.parseInt(opcao);
					int op = Integer.parseInt(n_pagos);
					vendasRelatorios = new RelatorioPagamentosDAO().buscarPagamentosIsentosOpcaob(inicio, fim, m,op);
					System.out.println("N-PAGOS-OPCAO");
				}
				else if(opcao!=null && opcao.equals("2") && n_pagos != null && !exame.equals("0"))
				{
					int m = Integer.parseInt(opcao);
					vendasRelatorios = new RelatorioPagamentosDAO().buscarComPagamentoIsentoPlanoExamea(inicio, fim, m, exame);
					System.out.println("N-PAGOS-SERVICO");
				}
				else if(opcao!=null && opcao.equals("2") && n_pagos == null && examIsento.equals("0")){
					int m = Integer.parseInt(opcao);
					vendasRelatorios = new RelatorioPagamentosDAO().buscarPagamentosIsentosPlanoc(inicio, fim, m);
					System.out.println("N-PAGOS");
				}
				else if(opcao!=null && opcao.equals("3") && examePlano.equals("0")){
					int ex = Integer.parseInt(opcao);
					vendasRelatorios = new RelatorioPagamentosDAO().buscarPagamentosIsentosPlanosDeExame(inicio, fim, ex);
					System.out.println("P-PAGOS");
				}
				else if(inicio != null && fim != null){
					vendasRelatorios = new RelatorioPagamentosDAO().buscarhistVendasRelatorio(inicio, fim);
					System.out.println("DATAS");
				}
				}
				
				else{
					vendasRelatorios = new RelatorioPagamentosDAO().buscarhistVendasRelatorio();
				}
				request.setAttribute("lista", lista);
				request.setAttribute("vendasRelatorios", vendasRelatorios);
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=histPagaExames");
				saida.forward(request, response);	
			}
			
			if(tela!=null && ((tela.equals("pagmSerGr")||tela.equals("Pesquisar") || tela.equals("pagserGerais"))) ){
				List<Tratamento> getVendas = new  TratamentoDAO().getVendasTratamentosR();
				String acao = request.getParameter("acao");
				String pesq = request.getParameter("valorp");
				
				if(acao!=null && acao.equals("VeRecibo")){
					String codf = request.getParameter("codf");
					List<Tratamento> getVendaItens = new  TratamentoDAO().getVendasTratamentoItens(codf);;
					request.setAttribute("VeRecibo", acao);
					request.setAttribute("getVendaItens", getVendaItens);
				}
				if(pesq!=null){
					getVendas = new TratamentoDAO().getVendasTratamento(pesq);
				}
				
				request.setAttribute("getVendas", getVendas);
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=pagserGerais");
				saida.forward(request, response);
			}
			
			if(tela!=null && tela.equals("histPagserv")){
				
				Formatando fmt = new Formatando();
				List <Servico> servicos = new ServicoDAO().BuscarServicos();
				List <Tratamento> getHistVenda = null;
				
				String data_ini = request.getParameter("data_ini");
				String data_fim = request.getParameter("data_fim");
				String opcao = request.getParameter("escolha");
				String s_pagos = request.getParameter("s_pagos");
				String n_pagos = request.getParameter("n_pagos");
				String s_gerais = request.getParameter("servico_hp");
				String s_geraisIsento = request.getParameter("servico_hp1");
				String s_geraisPlanto = request.getParameter("servico_hp2");

				if(data_ini != null && data_fim != null){
					Date inicio = fmt.dataSql(data_ini);
					Date fim = fmt.dataSql(data_fim);
				
				 if(opcao!=null && opcao.equals("1") && s_pagos != null && !s_gerais.equals("0")){
						int m = Integer.parseInt(opcao);
						int op = Integer.parseInt(s_pagos);
						int sg = Integer.parseInt(s_gerais);
						getHistVenda = new RelatorioPagamentosDAO().buscarPagamentosIsentosPlanosOpcaoSg(inicio, fim, m,op,sg);
						System.out.println("PAGOS-OPCAO-SERVICO");
				}
				else if(opcao!=null && opcao.equals("2") && n_pagos != null && !s_geraisIsento.equals("0")){
						int m = Integer.parseInt(opcao);
						int op = Integer.parseInt(n_pagos);
						int sg = Integer.parseInt(s_geraisIsento);
						getHistVenda = new RelatorioPagamentosDAO().buscarPagamentosIsentosPlanosOpcaoServicogIsento(inicio, fim, m, op, sg);
						System.out.println("N-PAGOS-OPCAO-SERVICO");	
				}
				else if(opcao!=null && !equals("3") && !s_geraisPlanto.equals("0")){
					int m = Integer.parseInt(opcao);
					int op = 0;
					int sg = Integer.parseInt(s_geraisPlanto);
					getHistVenda = new RelatorioPagamentosDAO().buscarPagamentosIsentosPlanosOpcaoServico(inicio, fim, m, op, sg);
					System.out.println("P-PAGOS-SERVICO");
				}
				else if(opcao!=null && opcao.equals("2") && n_pagos != null && s_geraisIsento.equals("0")){
					int m = Integer.parseInt(opcao);
					int op = Integer.parseInt(n_pagos);
					getHistVenda = new RelatorioPagamentosDAO().buscarPagamentosIsentosOpcaoSG(inicio, fim, m,op);
					System.out.println("N-PAGOS-OPCAO");
				} 
				else if(opcao!=null && opcao.equals("2") && n_pagos == null && !s_gerais.equals("0")){
					int m = Integer.parseInt(opcao);
					getHistVenda = new RelatorioPagamentosDAO().buscarComPagamentoIsentoPlanoServicog(inicio, fim, m, s_gerais);
					System.out.println("N-PAGOS-SERVICO");
				}
				else if(opcao!=null && opcao.equals("1") && s_pagos != null && s_gerais.equals("0")){
					int m = Integer.parseInt(opcao);
					int op = Integer.parseInt(s_pagos);
					getHistVenda = new RelatorioPagamentosDAO().buscarPagamentosIsentosOpcaoSGx(inicio, fim, m,op);
					System.out.println("PAGOS-OPCAO");
				}
				else if(opcao!=null && opcao.equals("1") && s_pagos == null && !s_gerais.equals("0")){
					int m = Integer.parseInt(opcao);
					getHistVenda = new RelatorioPagamentosDAO().buscarComPagamentoIsentoPlanoServicoGerais(inicio, fim, m,s_gerais);
					System.out.println("PAGOS-SERVICO");
				}
				 else if(opcao!=null && opcao.equals("1") && s_pagos == null && s_gerais.equals("0")){
					int m = Integer.parseInt(opcao);
					getHistVenda = new RelatorioPagamentosDAO().buscarPagamentosIsentosPlanoGSx(inicio, fim, m);
					System.out.println("PAGOS");
				}
				else if(opcao!=null && opcao.equals("2") && n_pagos == null && s_geraisIsento.equals("0")){
					int m = Integer.parseInt(opcao);
					System.out.println("opcao: "+m);
					System.out.println("----- N-PAGOS ------");
					getHistVenda = new RelatorioPagamentosDAO().buscarPagamentosIsentosPlanoSG(inicio, fim, m);
					System.out.println("N-PAGOS");
				}
				else if(opcao!=null && opcao.equals("3") && s_geraisPlanto.equals("0")){
					int sg = Integer.parseInt(opcao);
					System.out.println("----- P-PAGOS ----");
					getHistVenda = new RelatorioPagamentosDAO().buscarPagamentosIsentosPlanosDeServicog(inicio, fim, sg);
					System.out.println("P-PAGOS");
				}
			
				else if(inicio != null && fim != null){
					getHistVenda = new RelatorioPagamentosDAO().buscarVendasTratamentoPorData(inicio, fim);
					System.out.println("DATAS");
				}
				}
				else{
					getHistVenda = new RelatorioPagamentosDAO().getVendasTratamentoItens();
				}
				
				request.setAttribute("servicos", servicos);
				request.setAttribute("getHistVenda", getHistVenda);
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=histPagserv");
				saida.forward(request, response);
			}
			if(tela!=null && (tela.equals("pagmIsnt")|| tela.equals("Pesquisar") || tela.equals("pagIsencao")) ){
				
				Formatando fmt = new Formatando();
				List<Produtos> isencao = null;
				
				String data_ini = request.getParameter("data_ini");
				String data_fim = request.getParameter("data_fim");
				String opcao = request.getParameter("escolha");
				String consulta = request.getParameter("consulta");
				String exame = request.getParameter("exame");
				String s_gerais = request.getParameter("s_gerais");
				String produto = request.getParameter("produto");
				String receita = request.getParameter("receita");
				
				if(data_ini != null && data_fim != null){
					Date inicio = fmt.dataSql(data_ini);
					Date fim = fmt.dataSql(data_fim);
					
				if(opcao!=null && !opcao.equals("")  && consulta!=null && !consulta.equals("") && exame!=null && !exame.equals("") && s_gerais!=null && !s_gerais.equals("") && produto!=null && !produto.equals("") && receita!=null && !receita.equals("")){
						int opc = Integer.parseInt(opcao);
						int cons = Integer.parseInt(consulta);
						int exam = Integer.parseInt(exame);
						int ser = Integer.parseInt(s_gerais);
						int prod = Integer.parseInt(produto);
						int rec = Integer.parseInt(receita);
						isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoConsultaExameServicoGFarmacoReceita(inicio, fim, opc, cons, exam, ser, prod, rec);
						System.out.println("DATA ISENCAO CONSULTA, EXAME, SERVICO GERAIS, FARMACO E RECEITA");
						
				}
				if(opcao!=null && !opcao.equals("")  && consulta!=null && !consulta.equals("") && exame!=null && !exame.equals("") && s_gerais!=null && !s_gerais.equals("") && produto!=null && !produto.equals("")){
					int opc = Integer.parseInt(opcao);
					int cons = Integer.parseInt(consulta);
					int exam = Integer.parseInt(exame);
					int ser = Integer.parseInt(s_gerais);
					int prod = Integer.parseInt(produto);
					isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoConsultaExameServicoGFarmaco(inicio, fim, opc, cons, exam, ser, prod);
					System.out.println("DATA ISENCAO CONSULTA, EXAME, SERVICO GERAIS, FARMACO");
					
				}
				if(opcao!=null && !opcao.equals("")  && exame!=null && !exame.equals("") && s_gerais!=null && !s_gerais.equals("") && produto!=null && !produto.equals("") && receita!=null && !receita.equals("")){
					int opc = Integer.parseInt(opcao);
					int exam = Integer.parseInt(exame);
					int ser = Integer.parseInt(s_gerais);
					int prod = Integer.parseInt(produto);
					int rec = Integer.parseInt(receita);
					isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoExameServicoGFarmacoReceita(inicio, fim, opc, exam, ser, prod, rec);
					System.out.println("DATA ISENCAO EXAME, SERVICO GERAIS, FARMACO E RECEITA");
					
				}
				else if(opcao!=null && !opcao.equals("")  && s_gerais!=null && !s_gerais.equals("") && produto!=null && !produto.equals("") && receita!=null && !receita.equals("")){
						int opc = Integer.parseInt(opcao);
						int ser = Integer.parseInt(s_gerais);
						int prod = Integer.parseInt(produto);
						int rec = Integer.parseInt(receita);
						isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoServicoGFarmacoReceita(inicio, fim, opc, ser, prod, rec);
						System.out.println("DATA ISENCAO SERVICO GERAIS, EXAME E FARMACO");
						
				}
				else if(opcao!=null && !opcao.equals("")  && exame!=null && !exame.equals("") && s_gerais!=null && !s_gerais.equals("") && produto!=null && !produto.equals("")){
						int opc = Integer.parseInt(opcao);
						int exam = Integer.parseInt(exame);
						int ser = Integer.parseInt(s_gerais);
						int prod = Integer.parseInt(produto);
						isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoExameServicoGFormaco(inicio, fim, opc, exam, ser, prod);
						System.out.println("DATA ISENCAO EXAME, SERVICOS GERAIS E FARMACO");
						
				}
				else if(opcao!=null && !opcao.equals("")  && consulta!=null && !consulta.equals("") && exame!=null && !exame.equals("") && receita!=null && !receita.equals("")){
						int opc = Integer.parseInt(opcao);
						int cons = Integer.parseInt(consulta);
						int exam = Integer.parseInt(exame);
						int rec = Integer.parseInt(receita);
						isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoConsultaExameReceita(inicio, fim, opc, cons, exam, rec);
						System.out.println("DATA ISENCAO CONSULTA, EXAME E RECEITA");
						
				}
				else if(opcao!=null && !opcao.equals("")  && consulta!=null && !consulta.equals("") && exame!=null && !exame.equals("") && produto!=null && !produto.equals("")){
						int opc = Integer.parseInt(opcao);
						int cons = Integer.parseInt(consulta);
						int exam = Integer.parseInt(exame);
						int prodt = Integer.parseInt(produto);
						isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoConsultaExameFarmaco(inicio, fim, opc, cons, exam, prodt);
						System.out.println("DATA ISENCAO CONSULTA, EXAME E FARMACO");
						
				}
				else if(opcao!=null && !opcao.equals("")  && consulta!=null && !consulta.equals("") && exame!=null && !exame.equals("") && s_gerais!=null && !s_gerais.equals("")){
						int opc = Integer.parseInt(opcao);
						int cons = Integer.parseInt(consulta);
						int exam = Integer.parseInt(exame);
						int serv = Integer.parseInt(s_gerais);
						isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoConsultaExameServicoG(inicio, fim, opc, cons, exam, serv);
						System.out.println("DATA ISENCAO CONSULTA, EXAME E SERVICO");
						
				}
				else if(opcao!=null && !opcao.equals("")  && produto!=null && !produto.equals("") && receita!=null && !receita.equals("") ){
						int opc = Integer.parseInt(opcao);
						int prodt = Integer.parseInt(produto);
						int rec = Integer.parseInt(receita);
						isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoFarmacoReceita(inicio, fim, opc, prodt, rec);
						System.out.println("DATA ISENCAO FARMACO E RECEITA");
						
				}
				else if(opcao!=null && !opcao.equals("")  && s_gerais!=null && !s_gerais.equals("") && receita!=null && !receita.equals("") ){
						int opc = Integer.parseInt(opcao);
						int ser = Integer.parseInt(s_gerais);
						int rec = Integer.parseInt(receita);
						isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoServicoGReceita(inicio, fim, opc, ser, rec);
						System.out.println("DATA ISENCAO SERVICOs GERAIS E FARMACO");
						
				}
				else if(opcao!=null && !opcao.equals("")  && s_gerais!=null && !s_gerais.equals("") && produto!=null && !produto.equals("") ){
						int opc = Integer.parseInt(opcao);
						int ser = Integer.parseInt(s_gerais);
						int prodt = Integer.parseInt(produto);
						isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoServicoGFarmaco(inicio, fim, opc, ser, prodt);
						System.out.println("DATA ISENCAO SERVICOs GERAIS E FARMACO");
						
				}
				else if(opcao!=null && !opcao.equals("")  && exame!=null && !exame.equals("") && s_gerais!=null && !s_gerais.equals("")){
						int opc = Integer.parseInt(opcao);
						int ser = Integer.parseInt(s_gerais);
						int exam = Integer.parseInt(exame);
						isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoExameServico(inicio, fim, opc, exam, ser);
						System.out.println("DATA ISENCAO EXAME E SERVICOs GERAIS");
						
				}
				else if(opcao!=null && !opcao.equals("") && exame!=null && !exame.equals("")&& receita!=null && !receita.equals("") ){
						int opc = Integer.parseInt(opcao);
						int rec = Integer.parseInt(receita);
						int exam = Integer.parseInt(exame);
						isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoExameReceita(inicio, fim, opc, exam, rec);
						System.out.println("DATA ISENCAO EXAME E RECEITA");
						
				}
				else if(opcao!=null && !opcao.equals("") && exame!=null && !exame.equals("")&& produto!=null && !produto.equals("") ){
					int opc = Integer.parseInt(opcao);
					int prodt = Integer.parseInt(produto);
					int exam = Integer.parseInt(exame);
					isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoExameFarmaco(inicio, fim, opc, exam, prodt);
					System.out.println("DATA ISENCAO EXAME E PRODUTO ");
					
				}
				else if(opcao!=null && !opcao.equals("") && consulta!=null && !consulta.equals("") && s_gerais!=null && !s_gerais.equals("")){
					int opc = Integer.parseInt(opcao);
					int cons = Integer.parseInt(consulta);
					int serv = Integer.parseInt(s_gerais);
					isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoConsultaServico(inicio, fim, opc, cons, serv);
					System.out.println("DATA ISENCAO CONSULTA E SERVICOS SERVICO ");
					
				}
				else if(opcao!=null && !opcao.equals("") && consulta!=null && !consulta.equals("") && produto!=null && !produto.equals("")){
					int opc = Integer.parseInt(opcao);
					int cons = Integer.parseInt(consulta);
					int prodt = Integer.parseInt(produto);
					isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoConsultaServico(inicio, fim, opc, cons, prodt);
					System.out.println("DATA ISENCAO CONSULTA E FARMACO ");
					
				}
				else if(opcao!=null && !opcao.equals("") && consulta!=null && !consulta.equals("") && receita!=null && !receita.equals("")){
					int opc = Integer.parseInt(opcao);
					int cons = Integer.parseInt(consulta);
					int rec = Integer.parseInt(receita);
					isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoConsultaServico(inicio, fim, opc, cons, rec);
					System.out.println("DATA ISENCAO CONSULTA E RECEITA ");
					
				}
				else if(opcao!=null && !opcao.equals("") && consulta!=null && !consulta.equals("")){
					int opc = Integer.parseInt(opcao);
					int cons = Integer.parseInt(consulta);
					isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoConsulta(inicio, fim, opc, cons);
					System.out.println("DATA ISENCAO CONSULTA ");
					
				}
				
				else if(opcao!=null && !opcao.equals("") && exame!=null && !exame.equals("")){
					int opc = Integer.parseInt(opcao);
					int exam = Integer.parseInt(exame);
					isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoConsulta(inicio, fim, opc, exam);
					System.out.println("DATA ISENCAO EXAME ");
					
				}
				else if(opcao!=null && !opcao.equals("") && s_gerais!=null && !s_gerais.equals("")){
					int opc = Integer.parseInt(opcao);
					int servico = Integer.parseInt(s_gerais);
					isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoServicoGerais(inicio, fim, opc, servico);
					System.out.println("DATA ISENCAO SERVICOS GERAIS ");
					
				}
				else if(opcao!=null && !opcao.equals("") && produto!=null && !produto.equals("")){
					int opc = Integer.parseInt(opcao);
					int prodt = Integer.parseInt(produto);
					isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoFarmaco(inicio, fim, opc, prodt);
					System.out.println("DATA ISENCAO FARMACO ");
					
				}
				else if(opcao!=null && !opcao.equals("") && receita!=null && !receita.equals("")){
					int opc = Integer.parseInt(opcao);
					int rec = Integer.parseInt(receita);
					isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoReceita(inicio, fim, opc, rec);
					System.out.println("DATA ISENCAO RECEITA ");
					
				}
				else if(opcao!=null && !opcao.equals("")){
					int opc = Integer.parseInt(opcao);
					isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoAll(inicio, fim, opc);
					System.out.println("All");
				}
				else if(inicio!=null && !inicio.equals("") && fim!=null && !fim.equals("")){
						isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencaoPorData(inicio, fim);
						System.out.println("DATA");
				}
				}
				else{
					isencao = new RelatorioPagamentosDAO().buscarPagamentoIsencao();
				}
				
				request.setAttribute("isencao", isencao);
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=pagIsencao");
				saida.forward(request, response);
			}
			if(tela!=null && (tela.equals("pagmPrd") || tela.equals("pagProd")) )
			{
				
				String acao = request.getParameter("acao");
				String cod = request.getParameter("cod");
				if(acao!=null && cod != null && cod.trim().length() > 0 )
				{
					int codf = Integer.parseInt(cod);
					List<Produtos> lsFatModal = new ProdutosDAO().buscarHistoricoVendaUmProduto(codf);
					request.setAttribute("lsFatModal", lsFatModal);
				}
				
				List<Facturacao> lsFatProdutos = new ProdutosDAO().buscarHistoricoVendaProdutosRelatorio();
				String pesquisar = request.getParameter("valorp");
				
				if(pesquisar!=null){
					lsFatProdutos = new ProdutosDAO().buscarHistoricoVendaProdutosRelatorio(pesquisar);
				}
				request.setAttribute("lsFatProdutos", lsFatProdutos);
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=pagProd");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("histPagProd"))
			{
				
				Formatando fmt = new Formatando();
				List<Produtos> histProdutos = new RelatorioPagamentosDAO().buscarHistoricoVendaUmProdutoVisualiza();
				List<Produtos> histProduto = null;
				
				String data_ini = request.getParameter("data_ini");
				String data_fim = request.getParameter("data_fim");
				String opcao = request.getParameter("escolha");
				String s_pagos = request.getParameter("s_pagos");
				String n_pagos = request.getParameter("n_pagos");
				String produto = request.getParameter("produto_hp");
				String prodIsento = request.getParameter("produto_hp1");
				String prodPlano = request.getParameter("produto_hp2");
				
				if(data_ini != null && data_fim != null){
					Date inicio = fmt.dataSql(data_ini);
					Date fim = fmt.dataSql(data_fim);
					
				if(opcao!=null && opcao.equals("1") && s_pagos == null && produto.equals("0"))
				{
					int m = Integer.parseInt(opcao);
					histProduto = new RelatorioPagamentosDAO().buscarProdutosPagoPlano(inicio, fim, m);
					System.out.println("PAGOS");
				}
				else if(opcao!=null && opcao.equals("1") && s_pagos != null && produto.equals("0")){
					int m = Integer.parseInt(opcao);
					int op = Integer.parseInt(s_pagos);
					histProduto = new RelatorioPagamentosDAO().buscarProdutoPagarPorOpcao(inicio, fim, m,op);
					System.out.println("PAGOS-OPCAO");
				}
				else if(opcao!=null && opcao.equals("1") && s_pagos != null && !produto.equals("0")){
					int m = Integer.parseInt(opcao);
					int op = Integer.parseInt(s_pagos);
					int pd = Integer.parseInt(produto);
					histProduto = new RelatorioPagamentosDAO().buscarProdutosIsentosPlanosOpcao(inicio, fim, m,op,pd);
					System.out.println("PAGOS-OPCAO-SERVICO");
				}
				else if(opcao!=null && opcao.equals("1") && s_pagos == null && !produto.equals("0")){
					int m = Integer.parseInt(opcao);
					histProduto = new RelatorioPagamentosDAO().buscarComProdutoIsentoPlano(inicio, fim, m,produto);
					System.out.println("PAGOS-SERVICO");
				}
				else if(opcao!=null && opcao.equals("2") && n_pagos == null && prodIsento.equals("0"))
				{
					int m = Integer.parseInt(opcao);
					histProduto = new RelatorioPagamentosDAO().buscarPagamentosProdutoIsentosPlano(inicio, fim, m);
					System.out.println("N-PAGOS");
				}
				else if(opcao!=null && opcao.equals("2") && n_pagos != null && prodIsento.equals("0")){
					int m = Integer.parseInt(opcao);
					int op = Integer.parseInt(n_pagos);
					
					histProduto = new RelatorioPagamentosDAO().buscarPagamentosProdutoIsentosOpcao(inicio, fim, m,op);
					System.out.println(" N-PAGOS-OPCAO");
				}
				else if(opcao!=null && opcao.equals("2") && n_pagos == null && !prodIsento.equals("0")){
					int m = Integer.parseInt(opcao);
					
					histProduto = new RelatorioPagamentosDAO().buscarComPagamentoProdutoIsentoPlano(inicio, fim, m, prodIsento);
					System.out.println("N-PAGOS-SERVICO");
				}
				else if(opcao!=null && opcao.equals("2") && n_pagos != null && !prodIsento.equals("0")){
					int m = Integer.parseInt(opcao);
					int op = Integer.parseInt(n_pagos);
					int pd = Integer.parseInt(prodIsento);
					
					histProduto = new RelatorioPagamentosDAO().buscarPagamentosIsentosPlanosOpcaoProduto(inicio, fim, m, op, pd);
					System.out.println("N-PAGOS-OPCAO-SERVICO");	
				}
				else if(opcao!=null && opcao.equals("3") && prodPlano.equals("0")){
					int pd = Integer.parseInt(opcao);
					histProduto = new RelatorioPagamentosDAO().buscarPagamentosIsentosPlanosProduto(inicio, fim, pd);
					System.out.println("P-PAGOS");
				}
				else if(opcao!=null && !equals("3") && !prodPlano.equals("0")){
					int m = Integer.parseInt(opcao);
					int op = 0;
					int pd = Integer.parseInt(prodPlano);
					histProduto = new RelatorioPagamentosDAO().buscarPagamentosProdutosIsentosPlanosOpcao(inicio, fim, m, op, pd);
					System.out.println("P-PAGOS-SERVICO");
				}
			
				else if(inicio != null && fim != null)
				{
					histProduto = new RelatorioPagamentosDAO().buscarProdutoPorData(inicio, fim);
					System.out.println("DATA");
				}
				}
				else
				{
					histProduto = new RelatorioPagamentosDAO().buscarHistoricoVendaUmProduto();
				}
				
				request.setAttribute("histProduto", histProduto);
				request.setAttribute("histProdutos", histProdutos);
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=histPagProd");
				saida.forward(request, response);
			}
			if(tela!=null && (tela.equals("fechoCaixa") || tela.equals("pagmFch"))){
				
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=fechoCaixa");
				saida.forward(request, response);
			}
			
			if(tela!=null && tela.equals("histPacInt")){
				
				PacienteAgendadoDAO paDAO = new PacienteAgendadoDAO();
				List <Diverso> metBusc =  new MetodosBuscas().buscarSector();
				List <Paciente> lista = null;
				
				String nomePaciente = request.getParameter("nomePac");
				String dataInicio   = request.getParameter("dataI");
				String dataFinal 	= request.getParameter("dataF");
				String idade1 		= request.getParameter("idade1");
				String idade2 		= request.getParameter("idade2");
				String genero 		= request.getParameter("sexo");
				String sector 		= request.getParameter("sector");
				String sala 		= request.getParameter("sala");
				String medico 		= request.getParameter("medico");
				
				if(dataInicio!=null && !dataInicio.equals("") && dataFinal!=null && !dataFinal.equals("")){
					Date dtInicio = new Formatando().dataSql(dataInicio);
					Date dtFinal = new Formatando().dataSql(dataFinal);
					
					if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals(""))
				    {
				    		lista = paDAO.buscarPorDataAndNomePacInt(dtInicio, dtFinal, nomePaciente);
				    }
				    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade1!=null && !idade1.equals(""))
				    {
				    		lista = paDAO.buscarPorDataAndIdadePacInt(dtInicio, dtFinal, idade1); 
				    }
				    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals(""))
				    {
				    		lista = paDAO.buscarPorDataAndIdadesPacInt(dtInicio, dtFinal, idade1, idade2); 
				    }
				    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && genero!=null && !genero.equals(""))
				    {
				    		lista = paDAO.buscarPorDataAndGeneroPacInt(dtInicio, dtFinal, genero); 
				    }
				    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && medico!=null && !medico.equals(""))
				    {
				    		lista = paDAO.buscarPorDataAndMedicoPacInt(dtInicio, dtFinal, medico); 
				    }
				    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && sector!=null && !sector.equals("") && sala!=null && !sala.equals(""))
				    {
				    		lista = paDAO.buscarPorDataAndSectorAndSalaPacInt(dtInicio, dtFinal, sector, sala); 
				    }
				    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals(""))
				    {
				    		lista = paDAO.buscarPorDataAndNomeAndIdadePacInt(dtInicio, dtFinal, nomePaciente, idade1, idade2); 
				    }
				    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals("") && genero!=null && !genero.equals(""))
				    {
				    		lista = paDAO.buscarPorDataAndNomeAndGeneroPacInt(dtInicio, dtFinal, nomePaciente, genero); 
				    }
				    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals("") && medico!=null && !medico.equals(""))
				    {
				    		lista = paDAO.buscarPorDataAndNomeAndMedicoPacInt(dtInicio, dtFinal, nomePaciente, medico); 
				    }
				    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nomePaciente!=null && !nomePaciente.equals("") && sector!=null && !sector.equals("") && sala!=null && !sala.equals(""))
				    {
				    		lista = paDAO.buscarPorDataAndNomeAndSectorAndSalaPacInt(dtInicio, dtFinal, nomePaciente, sector, sala); 
				    }
				    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && genero!=null && !genero.equals(""))
				    {
				    		lista = paDAO.buscarPorDataAndIdadeAndGeneroPacInt(dtInicio, dtFinal, idade1, idade2, genero); 
				    }
				    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && medico!=null && !medico.equals(""))
				    {
				    		lista = paDAO.buscarPorDataAndIdadeAndMedicoPacInt(dtInicio, dtFinal, idade1, idade2, medico); 
				    }
				    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && sector!=null && !sector.equals("") && sala!=null && !sala.equals(""))
				    {
				    		lista = paDAO.buscarPorDataAndIdadeAndSectorAndSalaPacInt(dtInicio, dtFinal, idade1, idade2, sector, sala); 
				    }
				    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && genero!=null && !genero.equals("") && medico!=null && !medico.equals(""))
				    {
				    		lista = paDAO.buscarPorDataAndGeneroAndMedicoPacInt(dtInicio, dtFinal, genero, medico); 
				    }
				    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && genero!=null && !genero.equals("") && sector!=null && !sector.equals("") && sala!=null && !sala.equals(""))
				    {
				    		lista = paDAO.buscarPorDataAndGeneroAndSectorAndSalaPacInt(dtInicio, dtFinal, genero, sector, sala); 
				    }
				    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && genero!=null && !medico.equals("") && medico!=null && !sector.equals("") && sala!=null && !sala.equals(""))
				    {
				    		lista = paDAO.buscarPorDataAndMedicoAndSectorAndSalaPacInt(dtInicio, dtFinal, medico, sector, sala); 
				    }
				    else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals(""))
				    {
				    		lista = paDAO.buscarPorDataPacInt(dtInicio, dtFinal); 
				    }
				}
				else
				{
							lista = paDAO.buscarPacienteInternado();
				}
				request.setAttribute("metBusc", metBusc);
				request.setAttribute("lista", lista);
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=histPacInt");
				saida.forward(request, response);
			}
			
			if(tela!=null && (tela.equals("histAltMed")||tela.equals("PesquisarAM"))){
				
				
				String dataInicio = request.getParameter("dataI");
				String dataFinal = request.getParameter("dataF");
				String axu 		= request.getParameter("pesquisar");
				
				PacienteAgendadoDAO paDAO = new PacienteAgendadoDAO();
				List <Paciente> lista = null;
				List <Diverso> ltsala = new MetodosBuscas().buscarSala();
				List <Diverso> ltsector= new MetodosBuscas().buscarSector();
				List <Funcionario> ltfun = new AgendaDAO().buscaFun();
				
				if(dataInicio!=null && !dataInicio.equals("") && dataFinal!=null && !dataFinal.equals("")){
					Date dtInicio = new Formatando().dataSql(dataInicio);
					Date dtFinal = new Formatando().dataSql(dataFinal);
					
					
					String nome 	= request.getParameter("nomePac");
					String idade1 	= request.getParameter("idade");
					String idade2	= request.getParameter("idades");
					String genero 	= request.getParameter("sexo");
					String medico 	= request.getParameter("medico");
					String sector	= request.getParameter("sector");
					String sala		= request.getParameter("sala");
					String motivo 	= request.getParameter("motivo");
					System.out.println("Motivo da alta: "+motivo);
				
				if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && motivo!=null && !motivo.equals(""))
				{
				    	lista = paDAO.buscarPorMotivoHistAltaMedico(dtInicio, dtFinal, motivo);
				}
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nome!=null && !nome.equals(""))
			    {
			    		lista = paDAO.buscarPorNomeHistAltaMedico(dtInicio,dtFinal,nome);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals(""))
			    {
			    		lista = paDAO.buscarPorIdadesHistAltaMedico(dtInicio, dtFinal, idade1, idade2);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") )
			    {
			    		lista = paDAO.buscarPorIdadeHistAltaMedico(dtInicio, dtFinal, idade1);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && genero!=null && !genero.equals(""))
			    {
			    		lista = paDAO.buscarPorGeneroHistAltaMedico(dtInicio, dtFinal, genero);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && medico!=null && !medico.equals(""))
			    {
			    		lista = paDAO.buscarPorMedicoHistAltaMedico(dtInicio, dtFinal, medico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && sector!=null && !sector.equals("")&& sala!=null && !sala.equals(""))
			    {
			    		lista = paDAO.buscarPorSectorAndSalaHistAltaMedico(dtInicio, dtFinal, sector, sala);
			    }
				
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nome!=null && !nome.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals(""))
			    {
			    		lista = paDAO.buscarPorNomeAndIdadeHistAltaMedico(dtInicio, dtFinal, nome, idade1, idade2);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") )
			    {
			    		lista = paDAO.buscarPorDataHistAltaMedico(dtInicio,dtFinal);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nome!=null && !nome.equals("") && genero!=null && !genero.equals(""))
			    {
			    		lista = paDAO.buscarPorNomeAndGeneroHistAltaMedico(dtInicio, dtFinal, nome, genero);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nome!=null && !nome.equals("") && medico!=null && !genero.equals(""))
			    {
			    		lista = paDAO.buscarPorNomeAndMedicoHistAltaMedico(dtInicio, dtFinal, nome, medico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nome!=null && !nome.equals("") && sector!=null && !sector.equals("") && sala!=null && !sala.equals(""))
			    {
			    		lista = paDAO.buscarPorNomeAndSectorAndSalaHistAltaMedico(dtInicio, dtFinal, nome, sector, sala);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && nome!=null && !nome.equals("") && motivo!=null && !motivo.equals(""))
			    {
			    		lista = paDAO.buscarPorNomeAndMotivoHistAltaMedico(dtInicio, dtFinal, nome, motivo);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && genero!=null && !genero.equals(""))
			    {
			    		lista = paDAO.buscarPorIdadeAndGeneroHistAltaMedico(dtInicio, dtFinal, idade1, idade2, genero);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && medico!=null && !medico.equals(""))
			    {
			    		lista = paDAO.buscarPorIdadeAndMedicoHistAltaMedico(dtInicio, dtFinal, idade1, idade2, medico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && sector!=null && !sector.equals("") && sala!=null && !sala.equals(""))
			    {
			    		lista = paDAO.buscarPorIdadeAndSectorAndSalaHistAltaMedico(dtInicio, dtFinal, idade1, idade2, sector, sala);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && motivo!=null && !motivo.equals(""))
			    {
			    		lista = paDAO.buscarPorIdadeAndMotivoHistAltaMedico(dtInicio, dtFinal, idade1, idade2, motivo);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && genero!=null && !genero.equals("") && medico!=null && !medico.equals(""))
			    {
			    		lista = paDAO.buscarPorGeneroAndMedicoHistAltaMedico(dtInicio, dtFinal, genero, medico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && genero!=null && !genero.equals("") && sector!=null && !sector.equals("") && sala!=null && !sala.equals(""))
			    {
			    		lista = paDAO.buscarPorGeneroAndSectorAndSalaHistAltaMedico(dtInicio, dtFinal, genero, sector, sala);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && genero!=null && !genero.equals("") && medico!=null && !medico.equals(""))
			    {
			    		lista = paDAO.buscarPorGeneroAndMotivoHistAltaMedico(dtInicio, dtFinal, genero, medico);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && medico!=null && !medico.equals("") && sector!=null && !sector.equals("") && sala!=null && !sala.equals(""))
			    {
			    		lista = paDAO.buscarPorMedicoAndSectorAndSalaHistAltaMedico(dtInicio, dtFinal, medico, sector, sala);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && medico!=null && !medico.equals("") && motivo!=null && !motivo.equals(""))
			    {
			    		lista = paDAO.buscarPorMedicoAndMotivoHistAltaMedico(dtInicio, dtFinal, medico, motivo);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && sector!=null && !sector.equals("") && sala!=null && !sala.equals("") && motivo!=null && !motivo.equals(""))
			    {
			    		lista = paDAO.buscarPorSectorAndSalaAndMotivoHistAltaMedico(dtInicio, dtFinal, sector, sala, motivo);
			    }
				if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals(""))
			    {
			    		lista = paDAO.buscarPorDataHistAltaMedico(dtInicio,dtFinal);
			    }
				
				}else{
						lista = paDAO.buscarHistoricoAltaMedica();
				}
				
				request.setAttribute("lista", lista);
				request.setAttribute("ltsala", ltsala);
				request.setAttribute("ltsector", ltsector);
				request.setAttribute("ltfun", ltfun);
				request.setAttribute("axu", axu);
				saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=histAltMed");
				saida.forward(request, response);
			}	
			
			
		
		if(tela!=null && (tela.equals("labRel")||(tela.equals("Pesquisar"))||tela.equals("labpaAt"))){
			
			Formatando fmt = new Formatando();
			List<Exames> pacienteConsulta = null;
			List<Exames> grupoExames = new ExamesDAO().getGrupoExames();
			List<Exames> exames = new ExamesDAO().buscagrupoexame();
			
			String data_ini = request.getParameter("data_ini");
			String data_fim = request.getParameter("data_fim");
			String gExame = request.getParameter("grExames");
			String idade1 = request.getParameter("idade");
			String idade2 = request.getParameter("idades");
			String genero = request.getParameter("sexo");
			String exame = request.getParameter("exame");
			
			if(data_ini != null && data_fim != null){
				Date inicio = fmt.dataSql(data_ini);
				Date fim = fmt.dataSql(data_fim);
			
				if(idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && gExame!=null && !gExame.equals("") && genero!=null && !genero.equals("") && exame!=null && !exame.equals("")){
					int idadeX = Integer.parseInt(idade1);
					int idadeY = Integer.parseInt(idade2);
					int grupos = Integer.parseInt(gExame);
					int xgenero = Integer.parseInt(genero);
					int exameX = Integer.parseInt(exame);
					pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasAtendidosQuarto(inicio, fim, grupos, idadeX, idadeY, xgenero, exameX);
					System.out.println("DATA GRUPO IDADE GENERO EXAME");
				}
				else if(exame!=null && !exame.equals("") && gExame!=null && !gExame.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") ){
					int exameX = Integer.parseInt(exame);
					int grupos = Integer.parseInt(gExame);
					int idadeX = Integer.parseInt(idade1);
					int idadeY = Integer.parseInt(idade2);
					pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasAtendidosExameTres(inicio, fim, exameX, grupos, idadeX, idadeY);
					System.out.println("DATA EXAME GRUPO IDADE");
					
				}
				else if(idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && genero!=null && !genero.equals("")&& exame!=null && !exame.equals("")){
					int idadeX = Integer.parseInt(idade1);
					int idadeY = Integer.parseInt(idade2);
					int xgenero = Integer.parseInt(genero);
					int exameX = Integer.parseInt(exame);
					pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasAtendidosIdadeDois(inicio, fim, idadeX, idadeY, xgenero, exameX);
					System.out.println("DATA IDADE GENERO SERVICO");
				}
			else if(exame!=null && !exame.equals("") && gExame!=null && !gExame.equals("") && genero!=null && !genero.equals("")){
					int exameX = Integer.parseInt(exame);
					int grupos = Integer.parseInt(gExame);
					int xgenero = Integer.parseInt(genero);
					pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasAtendidosExameDois(inicio, fim, exameX, grupos, xgenero);
					System.out.println("DATA EXAME GRUPO GENERO");
			}
			else if(gExame!=null && !gExame.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && genero!=null && !genero.equals("")){
				int grupos = Integer.parseInt(gExame);
				int idadeX = Integer.parseInt(idade1);
				int idadeY = Integer.parseInt(idade2);
				int xgenero = Integer.parseInt(genero);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasAtendidosTres(inicio, fim, grupos, idadeX, idadeY, xgenero);
				System.out.println("DATA GRUPO IDADE GENERO");
			}
			else if(exame!=null && !exame.equals("")  && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("")){
				int exameX = Integer.parseInt(exame);
				int idadeX = Integer.parseInt(idade1);
				int idadeY = Integer.parseInt(idade2);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasAtendidosExameQuarto(inicio, fim, exameX, idadeX, idadeY);
				System.out.println("DATA EXAME IDADE");
			}
			
			else if(exame!=null && !exame.equals("") && gExame!=null && !gExame.equals("")){
				int exameX = Integer.parseInt(exame);
				int grupos = Integer.parseInt(gExame);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasAtendidosExameUm(inicio, fim, exameX, grupos);
				System.out.println("DATA EXAME GRUPO");
			}
			
			else if(genero!=null && !genero.equals("") && exame!=null && !exame.equals("")){
				int xgenero = Integer.parseInt(genero);
				int exameX = Integer.parseInt(exame);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasAtendidosGeneroUm(inicio, fim, xgenero, exameX);
				System.out.println("DATA GENERO EXAME");
			}
			
			else if(gExame!=null && !gExame.equals("")&& idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("")){
				int grupos = Integer.parseInt(gExame);
					int idadeX = Integer.parseInt(idade1);
					int idadeY = Integer.parseInt(idade2);
					pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasAtendidosDois(inicio, fim, grupos, idadeX, idadeY);
					System.out.println("DATA GRUPO IDADE");
			}
			
			
			
			else if(idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && genero!=null && !genero.equals("")){
				int idadeX = Integer.parseInt(idade1);
				int idadeY = Integer.parseInt(idade2);
				int xgenero = Integer.parseInt(genero);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasAtendidosIdadeUm(inicio, fim, idadeX, idadeY, xgenero);
				System.out.println("DATA IDADE GENERO");
			}
			
			else if(idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("")){
				int idadeX = Integer.parseInt(idade1);
				int idadeY = Integer.parseInt(idade2);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasAtendidosIdade(inicio, fim, idadeX, idadeY);
				System.out.println("DATA IDADE");
			}
			else if(genero!=null && !genero.equals("")){
				int xgenero = Integer.parseInt(genero);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasAtendidosGenero(inicio, fim, xgenero);
				System.out.println("DATA GENERO ");
			}
			else if(exame!=null && !exame.equals("")){
				int exameX = Integer.parseInt(exame);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasAtendidosExame(inicio, fim, exameX);
				System.out.println("DATA EXAME");
			}
			else if(gExame!=null && !gExame.equals("")){
				int grupos = Integer.parseInt(gExame);	
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasAtendidosUm(inicio, fim, grupos);
				System.out.println("DATA GRUPO");
			}
			else if(inicio != null && fim != null)
			{
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasAtendidosPorData(inicio, fim);	
				System.out.println("DATA");
			}
			}else{
				pacienteConsulta =	new LaboratorioAtendidosDAO().buscarPacienteConsultasAtendidos();
			}
	
			request.setAttribute("exames", exames);
			request.setAttribute("grupoExames", grupoExames);
			request.setAttribute("consulta", pacienteConsulta);
			saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=labpaAt");
			saida.forward(request, response);
		
		}
		if(tela!=null && (tela.equals("labpaExAt")  ||(tela.equals("Pesquisar")))){
			 
			Formatando fmt = new Formatando();
			List<Exames> pacienteConsulta = null;
			List<Exames> grupoExames = new ExamesDAO().getGrupoExames();
			List<Exames> exames = new ExamesDAO().buscagrupoexame();
			
			String data_ini = request.getParameter("data_ini");
			String data_fim = request.getParameter("data_fim");
			String gExame = request.getParameter("grExames");
			String idade1 = request.getParameter("idade");
			String idade2 = request.getParameter("idades");
			String genero = request.getParameter("sexo");
			String exame = request.getParameter("exame");
			
			if(data_ini != null && data_fim != null){
				Date inicio = fmt.dataSql(data_ini);
				Date fim = fmt.dataSql(data_fim);
			
			if(idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && gExame!=null && !gExame.equals("") && genero!=null && !genero.equals("") && exame!=null && !exame.equals("")){
					int idadeX = Integer.parseInt(idade1);
					int idadeY = Integer.parseInt(idade2);
					int grupos = Integer.parseInt(gExame);
					int xgenero = Integer.parseInt(genero);
					int exameX = Integer.parseInt(exame);
					pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasExternaAtendidosQuarto(inicio, fim, grupos, idadeX, idadeY, xgenero, exameX);
					System.out.println("DATA GRUPO IDADE GENERO EXAME");
			}
			else if(gExame!=null && !gExame.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && genero!=null && !genero.equals("")){
				int grupos = Integer.parseInt(gExame);
				int idadeX = Integer.parseInt(idade1);
				int idadeY = Integer.parseInt(idade2);
				int xgenero = Integer.parseInt(genero);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasExternaAtendidosTres(inicio, fim, grupos, idadeX, idadeY, xgenero);
				System.out.println("DATA GRUPO IDADE GENERO");
			}
			else if(exame!=null && !exame.equals("") && gExame!=null && !gExame.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") ){
				int exameX = Integer.parseInt(exame);
				int grupos = Integer.parseInt(gExame);
				int idadeX = Integer.parseInt(idade1);
				int idadeY = Integer.parseInt(idade2);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasExternaAtendidosExameTres(inicio, fim, exameX, grupos, idadeX, idadeY);
				System.out.println("DATA EXAME GRUPO IDADE");
				
			}
			else if(idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && genero!=null && !genero.equals("")&& exame!=null && !exame.equals("")){
				int idadeX = Integer.parseInt(idade1);
				int idadeY = Integer.parseInt(idade2);
				int xgenero = Integer.parseInt(genero);
				int exameX = Integer.parseInt(exame);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasExternaAtendidosIdadeDois(inicio, fim, idadeX, idadeY, xgenero, exameX);
				System.out.println("DATA IDADE GENERO SERVICO");
			}
			else if(exame!=null && !exame.equals("") && gExame!=null && !gExame.equals("") && genero!=null && !genero.equals("")){
				int exameX = Integer.parseInt(exame);
				int grupos = Integer.parseInt(gExame);
				int xgenero = Integer.parseInt(genero);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasExternaAtendidosExameDois(inicio, fim, exameX, grupos, xgenero);
				System.out.println("DATA EXAME GRUPO GENERO");
			}
			else if(exame!=null && !exame.equals("")  && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("")){
				int exameX = Integer.parseInt(exame);
				int idadeX = Integer.parseInt(idade1);
				int idadeY = Integer.parseInt(idade2);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasExternaAtendidosExameQuarto(inicio, fim, exameX, idadeX, idadeY);
				System.out.println("DATA EXAME IDADE");
			}
			else if(exame!=null && !exame.equals("") && gExame!=null && !gExame.equals("")){
				int exameX = Integer.parseInt(exame);
				int grupos = Integer.parseInt(gExame);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasExternaAtendidosExameUm(inicio, fim, exameX, grupos);
				System.out.println("DATA EXAME GRUPO");
			}
			else if(genero!=null && !genero.equals("") && exame!=null && !exame.equals("")){
				int xgenero = Integer.parseInt(genero);
				int exameX = Integer.parseInt(exame);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasExternaAtendidosGeneroUm(inicio, fim, xgenero, exameX);
				System.out.println("DATA GENERO EXAME");
			}
			else if(gExame!=null && !gExame.equals("")&& idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("")){
				int grupos = Integer.parseInt(gExame);
					int idadeX = Integer.parseInt(idade1);
					int idadeY = Integer.parseInt(idade2);
					pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasExternaAtendidosDois(inicio, fim, grupos, idadeX, idadeY);
					System.out.println("DATA GRUPO IDADE");
			}
			else if(idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && genero!=null && !genero.equals("")){
				int idadeX = Integer.parseInt(idade1);
				int idadeY = Integer.parseInt(idade2);
				int xgenero = Integer.parseInt(genero);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasExternaAtendidosIdadeUm(inicio, fim, idadeX, idadeY, xgenero);
				System.out.println("DATA IDADE GENERO");
			}
			else if(idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("")){
				int idadeX = Integer.parseInt(idade1);
				int idadeY = Integer.parseInt(idade2);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasExternaAtendidosIdade(inicio, fim, idadeX, idadeY);
				System.out.println("DATA IDADE");
			}
			else if(genero!=null && !genero.equals("")){
				int xgenero = Integer.parseInt(genero);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasExternaAtendidosGenero(inicio, fim, xgenero);
				System.out.println("DATA GENERO ");
			}
			else if(exame!=null && !exame.equals("")){
				int exameX = Integer.parseInt(exame);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasExternaAtendidosExame(inicio, fim, exameX);
				System.out.println("DATA EXAME");
			}
			else if(gExame!=null && !gExame.equals("")){
				int grupos = Integer.parseInt(gExame);	
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasExternaAtendidosUm(inicio, fim, grupos);
				System.out.println("DATA GRUPO");
			}
			else if(inicio != null && fim != null)
			{
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasExternaAtendidosPorData(inicio, fim);	
				System.out.println("DATA");
			}
			}else{
				pacienteConsulta =	new LaboratorioAtendidosDAO().buscarPacienteConsultasExternaAtendidos();
			}
	
			request.setAttribute("exames", exames);
			request.setAttribute("grupoExames", grupoExames);
			request.setAttribute("consulta", pacienteConsulta);
			saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=labpaExAt");
			saida.forward(request, response);
		
		}
		if(tela!=null && (tela.equals("labpaInAt")||(tela.equals("Pesquisar")))){
			
			Formatando fmt = new Formatando();
			List<Exames> pacienteConsulta = null;
			List<Exames> grupoExames = new ExamesDAO().getGrupoExames();
			List<Exames> exames = new ExamesDAO().buscagrupoexame();
			
			String data_ini = request.getParameter("data_ini");
			String data_fim = request.getParameter("data_fim");
			String gExame = request.getParameter("grExames");
			String idade1 = request.getParameter("idade");
			String idade2 = request.getParameter("idades");
			String genero = request.getParameter("sexo");
			String exame = request.getParameter("exame");
			
			if(data_ini != null && data_fim != null){
				Date inicio = fmt.dataSql(data_ini);
				Date fim = fmt.dataSql(data_fim);
			
			if(idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && gExame!=null && !gExame.equals("") && genero!=null && !genero.equals("") && exame!=null && !exame.equals("")){
					int idadeX = Integer.parseInt(idade1);
					int idadeY = Integer.parseInt(idade2);
					int grupos = Integer.parseInt(gExame);
					int xgenero = Integer.parseInt(genero);
					int exameX = Integer.parseInt(exame);
					pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasInternadosAtendidosQuarto(inicio, fim, grupos, idadeX, idadeY, xgenero, exameX);
					System.out.println("DATA GRUPO IDADE GENERO EXAME");
			}
			else if(gExame!=null && !gExame.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && genero!=null && !genero.equals("")){
				int grupos = Integer.parseInt(gExame);
				int idadeX = Integer.parseInt(idade1);
				int idadeY = Integer.parseInt(idade2);
				int xgenero = Integer.parseInt(genero);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasInternadosAtendidosTres(inicio, fim, grupos, idadeX, idadeY, xgenero);
				System.out.println("DATA GRUPO IDADE GENERO");
			}
			else if(exame!=null && !exame.equals("") && gExame!=null && !gExame.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") ){
				int exameX = Integer.parseInt(exame);
				int grupos = Integer.parseInt(gExame);
				int idadeX = Integer.parseInt(idade1);
				int idadeY = Integer.parseInt(idade2);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasInternadosAtendidosExameTres(inicio, fim, exameX, grupos, idadeX, idadeY);
				System.out.println("DATA EXAME GRUPO IDADE");
			}
			else if(exame!=null && !exame.equals("") && gExame!=null && !gExame.equals("") && genero!=null && !genero.equals("")){
				int exameX = Integer.parseInt(exame);
				int grupos = Integer.parseInt(gExame);
				int xgenero = Integer.parseInt(genero);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasInternadosAtendidosExameDois(inicio, fim, exameX, grupos, xgenero);
				System.out.println("DATA EXAME GRUPO GENERO");
			}
			else if(idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && genero!=null && !genero.equals("")&& exame!=null && !exame.equals("")){
				int idadeX = Integer.parseInt(idade1);
				int idadeY = Integer.parseInt(idade2);
				int xgenero = Integer.parseInt(genero);
				int exameX = Integer.parseInt(exame);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasInternadosAtendidosIdadeDois(inicio, fim, idadeX, idadeY, xgenero, exameX);
				System.out.println("DATA IDADE GENERO SERVICO");
			}
			else if(exame!=null && !exame.equals("")  && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("")){
				int exameX = Integer.parseInt(exame);
				int idadeX = Integer.parseInt(idade1);
				int idadeY = Integer.parseInt(idade2);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasInternadosAtendidosExameQuarto(inicio, fim, exameX, idadeX, idadeY);
				System.out.println("DATA EXAME IDADE");
			}
			else if(exame!=null && !exame.equals("") && gExame!=null && !gExame.equals("")){
				int exameX = Integer.parseInt(exame);
				int grupos = Integer.parseInt(gExame);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasAtendidosExameUm(inicio, fim, exameX, grupos);
				System.out.println("DATA EXAME GRUPO");
			}
			else if(genero!=null && !genero.equals("") && exame!=null && !exame.equals("")){
				int xgenero = Integer.parseInt(genero);
				int exameX = Integer.parseInt(exame);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasInternadosAtendidosGeneroUm(inicio, fim, xgenero, exameX);
				System.out.println("DATA GENERO EXAME");
			}
			else if(gExame!=null && !gExame.equals("")&& idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("")){
				int grupos = Integer.parseInt(gExame);
				int idadeX = Integer.parseInt(idade1);
				int idadeY = Integer.parseInt(idade2);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasInternadosAtendidosDois(inicio, fim, grupos, idadeX, idadeY);
				System.out.println("DATA GRUPO IDADE");
			}
			else if(idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && genero!=null && !genero.equals("")){
				int idadeX = Integer.parseInt(idade1);
				int idadeY = Integer.parseInt(idade2);
				int xgenero = Integer.parseInt(genero);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasInternadosAtendidosIdadeUm(inicio, fim, idadeX, idadeY, xgenero);
				System.out.println("DATA IDADE GENERO");
			}
			else if(idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("")){
				int idadeX = Integer.parseInt(idade1);
				int idadeY = Integer.parseInt(idade2);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasInternadosAtendidosIdade(inicio, fim, idadeX, idadeY);
				System.out.println("DATA IDADE");
			}
			else if(genero!=null && !genero.equals("")){
				int xgenero = Integer.parseInt(genero);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasInternadosAtendidosGenero(inicio, fim, xgenero);
				System.out.println("DATA GENERO ");
			}
			else if(exame!=null && !exame.equals("")){
				int exameX = Integer.parseInt(exame);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasInternadosAtendidosExame(inicio, fim, exameX);
				System.out.println("DATA EXAME");
			}
			else if(gExame!=null && !gExame.equals("")){
				int grupos = Integer.parseInt(gExame);	
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasInternadosAtendidosUm(inicio, fim, grupos);
				System.out.println("DATA GRUPO");
			}
			else if(inicio != null && fim != null)
			{
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasInternadosAtendidosPorData(inicio, fim);	
				System.out.println("DATA");
			}
			}else{
				pacienteConsulta =	new LaboratorioAtendidosDAO().buscarPacienteConsultasInternadosAtendidos();
			}
	
			request.setAttribute("exames", exames);
			request.setAttribute("grupoExames", grupoExames);
			request.setAttribute("consulta", pacienteConsulta);
			saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=labpaInAt");
			saida.forward(request, response);
		
		}
		if(tela!=null && (tela.equals("labpaBUE")||(tela.equals("Pesquisar")))){
			 
			Formatando fmt = new Formatando();
			List<Exames> pacienteConsulta = null;
			List<Exames> grupoExames = new ExamesDAO().getGrupoExames();
			List<Exames> exames = new ExamesDAO().buscagrupoexame();
			
			String data_ini = request.getParameter("data_ini");
			String data_fim = request.getParameter("data_fim");
			String gExame = request.getParameter("grExames");
			String idade1 = request.getParameter("idade");
			String idade2 = request.getParameter("idades");
			String genero = request.getParameter("sexo");
			String exame = request.getParameter("exame");
			
			if(data_ini != null && data_fim != null){
				Date inicio = fmt.dataSql(data_ini);
				Date fim = fmt.dataSql(data_fim);
			
			 if(idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && gExame!=null && !gExame.equals("") && genero!=null && !genero.equals("") && exame!=null && !exame.equals("")){
					int idadeX = Integer.parseInt(idade1);
					int idadeY = Integer.parseInt(idade2);
					int grupos = Integer.parseInt(gExame);
					int xgenero = Integer.parseInt(genero);
					int exameX = Integer.parseInt(exame);
					pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasBUEAtendidosQuarto(inicio, fim, grupos, idadeX, idadeY, xgenero, exameX);
					System.out.println("DATA GRUPO IDADE GENERO EXAME");
			}
			else if(gExame!=null && !gExame.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && genero!=null && !genero.equals("")){
					int grupos = Integer.parseInt(gExame);
					int idadeX = Integer.parseInt(idade1);
					int idadeY = Integer.parseInt(idade2);
					int xgenero = Integer.parseInt(genero);
					pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasBUEAtendidosTres(inicio, fim, grupos, idadeX, idadeY, xgenero);
					System.out.println("DATA GRUPO IDADE GENERO");
			}
			 else if(exame!=null && !exame.equals("") && gExame!=null && !gExame.equals("") && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") ){
					int exameX = Integer.parseInt(exame);
					int grupos = Integer.parseInt(gExame);
					int idadeX = Integer.parseInt(idade1);
					int idadeY = Integer.parseInt(idade2);
					pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasBUEAtendidosExameTres(inicio, fim, exameX, grupos, idadeX, idadeY);
					System.out.println("DATA EXAME GRUPO IDADE");
			}
			else if(idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && genero!=null && !genero.equals("")&& exame!=null && !exame.equals("")){
					int idadeX = Integer.parseInt(idade1);
					int idadeY = Integer.parseInt(idade2);
					int xgenero = Integer.parseInt(genero);
					int exameX = Integer.parseInt(exame);
					pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasBUEAtendidosIdadeDois(inicio, fim, idadeX, idadeY, xgenero, exameX);
					System.out.println("DATA IDADE GENERO SERVICO");
			}
			else if(exame!=null && !exame.equals("") && gExame!=null && !gExame.equals("") && genero!=null && !genero.equals("")){
					int exameX = Integer.parseInt(exame);
					int grupos = Integer.parseInt(gExame);
					int xgenero = Integer.parseInt(genero);
					pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasBUEAtendidosExameDois(inicio, fim, exameX, grupos, xgenero);
					System.out.println("DATA EXAME GRUPO GENERO");
			}
			else if(exame!=null && !exame.equals("")  && idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("")){
				int exameX = Integer.parseInt(exame);
				int idadeX = Integer.parseInt(idade1);
				int idadeY = Integer.parseInt(idade2);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasBUEAtendidosExameQuarto(inicio, fim, exameX, idadeX, idadeY);
				System.out.println("DATA EXAME IDADE");
			}
			else if(exame!=null && !exame.equals("") && gExame!=null && !gExame.equals("")){
				int exameX = Integer.parseInt(exame);
				int grupos = Integer.parseInt(gExame);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasBUEAtendidosExameUm(inicio, fim, exameX, grupos);
				System.out.println("DATA EXAME GRUPO");
			}
			else if(genero!=null && !genero.equals("") && exame!=null && !exame.equals("")){
				int xgenero = Integer.parseInt(genero);
				int exameX = Integer.parseInt(exame);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasBUEAtendidosGeneroUm(inicio, fim, xgenero, exameX);
				System.out.println("DATA GENERO EXAME");
			}
			else if(gExame!=null && !gExame.equals("")&& idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("")){
				int grupos = Integer.parseInt(gExame);
				int idadeX = Integer.parseInt(idade1);
				int idadeY = Integer.parseInt(idade2);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasBUEAtendidosDois(inicio, fim, grupos, idadeX, idadeY);
				System.out.println("DATA GRUPO IDADE");
			}
			else if(idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("") && genero!=null && !genero.equals("")){
				int idadeX = Integer.parseInt(idade1);
				int idadeY = Integer.parseInt(idade2);
				int xgenero = Integer.parseInt(genero);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasBUEAtendidosIdadeUm(inicio, fim, idadeX, idadeY, xgenero);
				System.out.println("DATA IDADE GENERO");
			}
			else if(idade1!=null && !idade1.equals("") && idade2!=null && !idade2.equals("")){
				int idadeX = Integer.parseInt(idade1);
				int idadeY = Integer.parseInt(idade2);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasBUEAtendidosIdade(inicio, fim, idadeX, idadeY);
				System.out.println("DATA IDADE");
			}
			else if(genero!=null && !genero.equals("")){
				int xgenero = Integer.parseInt(genero);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasBUEAtendidosGenero(inicio, fim, xgenero);
				System.out.println("DATA GENERO ");
			}
			else if(exame!=null && !exame.equals("")){
				int exameX = Integer.parseInt(exame);
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasBUEAtendidosExame(inicio, fim, exameX);
				System.out.println("DATA EXAME");
			}
			else if(gExame!=null && !gExame.equals("")){
				int grupos = Integer.parseInt(gExame);	
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasBUEAtendidosUm(inicio, fim, grupos);
				System.out.println("DATA GRUPO");
			}
			else if(inicio != null && fim != null)
			{
				pacienteConsulta = new LaboratorioAtendidosDAO().buscarPacienteConsultasBUEAtendidosPorData(inicio, fim);	
				System.out.println("DATA");
			}
			}else{
				pacienteConsulta =	new LaboratorioAtendidosDAO().buscarPacienteConsultasBUEAtendidos();
			}
	
			request.setAttribute("exames", exames);
			request.setAttribute("grupoExames", grupoExames);
			request.setAttribute("consulta", pacienteConsulta);
			saida = request.getRequestDispatcher("index.jsp?mod=rel&pesquisar=labpaBUE");
			saida.forward(request, response);
		
		}
			if(tela == null ) {
				List <Diverso> telas = new MetodosBuscas().buscaTREL(grupo);
				request.setAttribute("telas", telas);
				saida = request.getRequestDispatcher("index.jsp?mod=rel");
				saida.forward(request, response);
			}
		}
	}// if de Sessao 
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
