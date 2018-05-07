package ao.co.cligest.navegacao;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ao.co.cligest.dao.ConfNomeFacturaDAO;
import ao.co.cligest.dao.ExamesDAO;
import ao.co.cligest.dao.Formatando;
import ao.co.cligest.dao.GerirLaboratorioDAO;
import ao.co.cligest.dao.GerirStockImagioDAO;
import ao.co.cligest.dao.HistVendaDAO;
import ao.co.cligest.dao.LaboratorioDAO;
import ao.co.cligest.dao.LaboratorioOutrosExamesDAO;
import ao.co.cligest.dao.ProcedimentoStockDAO;
import ao.co.cligest.dao.ProdutosDAO;
import ao.co.cligest.dao.RequisicaoInternaDAO;
import ao.co.cligest.entidades.ConfNomeFactura;
import ao.co.cligest.entidades.Diverso;
import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.LaboratorioOutrosExames;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Produtos;
import ao.co.cligest.entidades.RequisicaoInterna;
import ao.co.cligest.interfaces.IProcedimentoStock;
import ao.co.cligest.util.MetodosBuscas;

/**
 * Servlet implementation class LaboratorioController
 */
@WebServlet("/navegacaolab")
public class LaboratorioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IProcedimentoStock iprogerirstock = new ProcedimentoStockDAO();
    private ProcedimentoStockDAO progerirstock = new ProcedimentoStockDAO();   
    /**
     * @see HttpServlet#HttpServlet()
     * @autor NELSON DIWIDI - ANGOTECH
     * @manutenecao NELSON DIWIDI , ARIDJA CUSSUNGA - ANGOTECH
     */
    public LaboratorioController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher saida = null;
		String mod = request.getParameter("mod");
		HttpSession ss = request.getSession(false);
		if(ss!=null){
		Integer grupo = (Integer)ss.getAttribute("grupo"); 
		if(mod!=null && mod.equals("lab"))
		{
			String tela = request.getParameter("pesquisar");
			if(tela==null || tela.equals("") ) {
//				Integer grupo = (Integer)request.getSession().getAttribute("grupo");
				List <Diverso> telas = new MetodosBuscas().buscaTELASLABPrincipal(grupo);   //buscaTELASLAB metodo que irei usar depois
				request.setAttribute("telas", telas);
				saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=tela");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("labort")){
				
				List <Diverso> telas = new MetodosBuscas().buscaTELASLAB(grupo);
				request.setAttribute("telas", telas);
				
				saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=labort");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("otrexame")){
				
				List <Diverso> telas = new MetodosBuscas().buscaTELASLABOTREXAME(grupo);
				request.setAttribute("telas", telas);
				
				saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=otrexame");
				saida.forward(request, response);
			}
			
			if(tela!=null && (tela.equals("cadOutrExam")|| tela.equals("o_egerirexa"))){
				
				String IDOExamesEdi = request.getParameter("oeedit");
				String acao = request.getParameter("acao");
				
				List<LaboratorioOutrosExames> lsOExames = new LaboratorioOutrosExamesDAO().buscarServicosExamesOutrosExame();
				request.setAttribute("lsOExames", lsOExames);
				
				List<Exames> grExames = new ExamesDAO().buscagrupoexames();
//				List<Exames> grsExames = new ExamesDAO().buscarsubgrupo();
				request.setAttribute("grExames", grExames);
				//
				List<Exames>amostraExames = new ExamesDAO().buscaAmostraExames();
				request.setAttribute("amostraExames", amostraExames);
				request.setAttribute("acao", acao);
				
				if(IDOExamesEdi!=null){
					int IDOExamesEdiConv = Integer.parseInt(IDOExamesEdi);//VALOR CONVERTIDO
					List<LaboratorioOutrosExames> lstOExamesEd = new LaboratorioOutrosExamesDAO().buscarServicosExamesOutrosExameEditar(IDOExamesEdiConv);
					List<Exames> grsExames = new ExamesDAO().buscarsubgrupoEdit(lstOExamesEd.get(0).getId_sub_grupo());
					request.setAttribute("grsExames", grsExames);
					request.setAttribute("lstOExamesEd", lstOExamesEd);
				}
				
				saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=cadOutrExam");
				saida.forward(request, response);
			}
			
			if(tela!=null && tela.equals("parametro5")){
				
				saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=parametro5");
				saida.forward(request, response);
			}
			
			if(tela!=null && tela.equals("parametro6")){
				
				saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=parametro6");
				saida.forward(request, response);
			}
			
			if(tela!=null && (tela.equals("lista") || tela.equals("consLab")|| tela.equals("consBUE")|| tela.equals("consExt")|| tela.equals("consInt"))){
				List<Exames> lstAnterior = new ExamesDAO().buscarPacienteExameNaoAtendidoOntem();
				List <Exames> lsExames = null;
				 
				if(lstAnterior.isEmpty()){
					for(Exames es: lstAnterior){
						new ExamesDAO().addEsperaDosExames(es);
					}
				}
				if(tela.equals("consLab") || (ss.getAttribute("origemLab")!=null && ss.getAttribute("origemLab").equals("Consultas")))
					{
					lsExames = new ExamesDAO().buscarPacienteExameAtendido(1);
					ss.setAttribute("origemLab", "Consultas");
					}
				
				if(tela.equals("consBUE") || (ss.getAttribute("origemLab")!=null && ss.getAttribute("origemLab").equals("BUE")))
					{
					lsExames = new ExamesDAO().buscarPacienteExameAtendidoBUE();
					ss.setAttribute("origemLab", "BUE");
					}
				
				if((tela.equals("consExt")||ss.getAttribute("origemLab")!=null && ss.getAttribute("origemLab").equals("Externos")))
					{
					lsExames = new ExamesDAO().buscarPacienteExameAtendido(2);
					ss.setAttribute("origemLab", "Externos");
					}
				
				if(tela.equals("consInt") || (ss.getAttribute("origemLab")!=null && ss.getAttribute("origemLab").equals("Internados")))
				  {
					lsExames = new ExamesDAO().buscarPacienteExameAtendidoInternado();
					ss.setAttribute("origemLab", "Internados");
				  }
				
				
				request.setAttribute("lsExames", lsExames);
				
				saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=lista");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("atExam") ){
				
				String acao = request.getParameter("acao");
 			    if(acao!=null && acao.equals("ver")){
 			    	String cod = request.getParameter("codFact");
 			    	String codP= request.getParameter("codProc");
 			    	String codE= request.getParameter("codEx");
 			    	
 			    	List <Exames> VeFactura = null;
 			    	if((ss.getAttribute("origemLab")!=null && ss.getAttribute("origemLab").equals("Consultas")))
					{
 			    		VeFactura = new ExamesDAO().buscarExamePorFazer(cod,codE);
 			    		
					}
				
					if( (ss.getAttribute("origemLab")!=null && ss.getAttribute("origemLab").equals("BUE")))
					{
						VeFactura = new ExamesDAO().buscarExamePorFazerBUE(cod,codE);
					 
					}
				
					if( (ss.getAttribute("origemLab")!=null && ss.getAttribute("origemLab").equals("Externos")))
					{
						VeFactura = new ExamesDAO().buscarExamePorFazer(cod,codE);
					}
					if( (ss.getAttribute("origemLab")!=null && ss.getAttribute("origemLab").equals("Internados")))
					{
						VeFactura = new ExamesDAO().buscarPacienteExameAtendidoInternado(cod,codE);
				    }
 			    	
 			    	Paciente codPaciente = new ExamesDAO().buscarPerfilDoPaciente(codP);
 			    	Exames tlAmostra = new ExamesDAO().editarAmostra(cod,codE);
 			    	
					
					request.setAttribute("VeFactura", VeFactura);
 			    	List <Exames> ltTpAmostra = new ExamesDAO().buscarTipoDeAmostra();
 			    	request.setAttribute("codPaciente", codPaciente);	
 			    	request.setAttribute("tlAmostra", tlAmostra);
 			    	request.setAttribute("ltTpAmostra", ltTpAmostra);
 			    }else{
 			    	if(request.getAttribute("VeFactura")!=null){
 			    		request.removeAttribute("VeFactura");
 			    	}
 			    }
				
				String cod = request.getParameter("codProc");
				String codf= request.getParameter("codFact");
				
				if(cod!=null && codf!=null){
					
					Paciente lsPaciente = new ExamesDAO().buscarPerfilDoPaciente(cod);
					String   foto = new ExamesDAO().foto(cod);
					if(foto.equals(""))
				   		   foto ="foto.jpg";
				
					request.setAttribute("foto", foto);
					request.setAttribute("lsPaciente", lsPaciente);	
					
					Exames lstAmostra = new ExamesDAO().buscarAmostra(codf); 
					request.setAttribute("lstAmostra", lstAmostra);
					
					
					
					List <Exames> lstExames = null;
					
					if((ss.getAttribute("origemLab")!=null && ss.getAttribute("origemLab").equals("Consultas")))
					{
						lstExames = new ExamesDAO().buscarExamePorFazer(codf);
						boolean habSalvar = new ExamesDAO().getXequeTotal(codf);
						request.setAttribute("habSalvar", habSalvar);
						request.setAttribute("provenienca", 1);
					}
				
					if( (ss.getAttribute("origemLab")!=null && ss.getAttribute("origemLab").equals("BUE")))
					{
						lstExames = new ExamesDAO().buscarExamePorFazerBUE(codf);
						boolean habSalvar = new ExamesDAO().getXequeTotalBUE(codf);
						request.setAttribute("habSalvar", habSalvar);
						request.setAttribute("provenienca", 3);
					}
				
					if( (ss.getAttribute("origemLab")!=null && ss.getAttribute("origemLab").equals("Externos")))
					{
						lstExames = new ExamesDAO().buscarExamePorFazer(codf);
						boolean habSalvar = new ExamesDAO().getXequeTotal(codf);
						request.setAttribute("habSalvar", habSalvar);
						request.setAttribute("provenienca", 2);
					}
				
					if( (ss.getAttribute("origemLab")!=null && ss.getAttribute("origemLab").equals("Internados")))
					{
						lstExames = new ExamesDAO().buscarPacienteExameAtendendoInternado(codf);
						boolean habSalvar = new ExamesDAO().getXequeTotalInt(codf);
						request.setAttribute("habSalvar", habSalvar);
						request.setAttribute("provenienca", 4);
				    }
					
					request.setAttribute("lstExames", lstExames);
					
				}
				saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=atExam&codProc="+cod+"&codFact="+codf);
				saida.forward(request, response);
			}
			if(tela!=null && (tela.equals("atdExam")||tela.equals("Pesquisar")) ){
				List <Diverso> telas = new MetodosBuscas().buscaTELASLAB(grupo);
				request.setAttribute("telas", telas);
				
				String dInicio = request.getParameter("dataI");
				String dFinal  = request.getParameter("dataF");
				String acao = request.getParameter("acao");
				String axu 	 = request.getParameter("pesquisar");
				String orig = request.getParameter("origem");
				
				if(acao!=null && acao.equals("ver")){
					String cod = request.getParameter("cod");
					List <Exames> xprocessar = new ExamesDAO().buscarOsAtendidosExaminadosModal(cod);
					request.setAttribute("xprocessar", xprocessar);
				}
				else{
					if(request.getAttribute("xprocessar")!=null){
						request.removeAttribute("xprocessar");
					}
				}
				
				LaboratorioDAO lbDAO = new LaboratorioDAO();
				List<Exames> atendido = null;
				
				int provencia = 0;
				if(orig != null )
					provencia = Integer.parseInt(orig);
				
				if(dInicio!=null && !dInicio.equals("") && dFinal!=null && !dFinal.equals("")){
					Date dtInicio = new Formatando().dataSql(dInicio);
					Date dtFinal = new Formatando().dataSql(dFinal);
					String gExame  = request.getParameter("grExam");
					String idadeI  = request.getParameter("idade1");
					String idadeF  = request.getParameter("idade2");
					String sexo    = request.getParameter("sexo");
					String exames  = request.getParameter("exame");
				
				 if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && gExame!=null && !gExame.equals(""))
			    {
					atendido = lbDAO.buscarDataAndGrupoExameAtendidos(dtInicio, dtFinal, gExame,provencia);	
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idadeI!=null && !idadeI.equals("")&& idadeF!=null && !idadeF.equals(""))
			    {
					atendido = lbDAO.buscarDataAndIdadeAtendidos(dtInicio, dtFinal, idadeI, idadeF,provencia);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && sexo!=null && !sexo.equals(""))
			    {
					atendido = lbDAO.buscarDataAndGeneroAtendidos(dtInicio, dtFinal, sexo,provencia);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && gExame!=null && !gExame.equals("") && idadeI!=null && !idadeI.equals("")&& idadeF!=null && !idadeF.equals(""))
			    {
					atendido = lbDAO.buscarDataAndGrupoAndIdadeAtendidos(dtInicio, dtFinal, gExame, idadeI, idadeF,provencia);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && exames!=null && !exames.equals(""))
			    {
					atendido = lbDAO.buscarDataAndExameAtendidos(dtInicio, dtFinal, exames,provencia);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idadeI!=null && !idadeI.equals(""))
			    {
					atendido = lbDAO.buscarDataAndIdade1Atendidos(dtInicio, dtFinal, idadeI,provencia);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && gExame!=null && !gExame.equals("") && sexo!=null && !sexo.equals(""))
			    {
					atendido = lbDAO.buscarDataAndGrupoAndGeneroAtendidos(dtInicio, dtFinal, gExame, sexo,provencia);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && gExame!=null && !gExame.equals("") && exames!=null && !exames.equals(""))
			    {
					atendido = lbDAO.buscarDataAndGrupoAndExameAtendidos(dtInicio, dtFinal, gExame, exames,provencia);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idadeI!=null && !idadeI.equals("") && idadeF!=null && !idadeF.equals("") && sexo!=null && !sexo.equals(""))
			    {
					atendido = lbDAO.buscarDataAndIdadeAndGeneroAtendidos(dtInicio, dtFinal, idadeI, idadeF, sexo,provencia);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idadeI!=null && !idadeI.equals("") && idadeF!=null && !idadeF.equals("") && exames!=null && !exames.equals(""))
			    {
					atendido = lbDAO.buscarDataAndIdadeAndGeneroAtendidos(dtInicio, dtFinal, idadeI, idadeF, exames,provencia);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && idadeI!=null && !idadeI.equals("") && sexo!=null && !sexo.equals(""))
			    {
					atendido = lbDAO.buscarDataAndIdade1AndGeneroAtendidos(dtInicio, dtFinal, idadeI, sexo,provencia);
			    }
				else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals("") && sexo!=null && !sexo.equals("") && exames!=null && !exames.equals(""))
			    {
					atendido = lbDAO.buscarDataAndGeneroAndExameAtendidos(dtInicio, dtFinal, sexo, exames,provencia);
			    }else if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals(""))
					{
						atendido = lbDAO.buscarDataAtendidos(dtInicio, dtFinal,provencia);
					}
					}else{
					
						atendido = lbDAO.buscarPacienteAtendidos();
					}
				
					List<Exames> lsGrExame = new ExamesDAO().buscagrupoexames();
					List<Exames> exame = new ExamesDAO().buscarServicosExames();
				
					request.setAttribute("lsGrExame", lsGrExame);
					request.setAttribute("exame", exame);
					request.setAttribute("axu", axu);
					request.setAttribute("atendido", atendido);
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=atdExam");
					saida.forward(request, response);
				}
				//VALORES DA TELA DE SERVICOS DE EXAMES
				if(tela!=null && tela.equals("cadExames")||tela!=null && tela.equals("gerirExames")){
				
					String cod = request.getParameter("cod");
					String acao = request.getParameter("acao");
					List<Exames> lsExames = new ExamesDAO().buscarServicosExamesClinico();
					
					List<Exames>amostraExames = new ExamesDAO().buscaAmostraExames();
					List<Exames> grExames = new ExamesDAO().buscagrupoexames();
					
					if(cod!=null && !cod.equals("")){
						int cod_e = Integer.parseInt(cod);
						Exames UmExame = new ExamesDAO().buscarServicosExamesClinico(cod_e);
						List<Exames> grsExames = new ExamesDAO().buscarsubgrupoEdit(UmExame.getId_sub_grupo());
						request.setAttribute("UmExame", UmExame);
						request.setAttribute("grsExames", grsExames);
					}
					request.setAttribute("acao", acao);
					request.setAttribute("lsExames", lsExames);
					
					request.setAttribute("grExames", grExames);
					request.setAttribute("amostraExames", amostraExames);
				
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=cadExames");
					saida.forward(request, response);
				}
				if(tela!=null && tela.equals("parametro1")){
					
					List<Exames> lsExames = new ExamesDAO().buscarServicosExamesClinico();
					List<Exames> lsParametro = new ExamesDAO().buscarParametroExames();
					List<Exames> lsGrupo = new ExamesDAO().buscarGrupoLabExames();
					String codigo = request.getParameter("cod");
					String codigo1= request.getParameter("cod1");
					String acao = request.getParameter("acao");
					
					if(acao!=null && acao.equals("ver")){
						int codInt = Integer.parseInt(codigo);
						List<Exames> lsParamEdit = new ExamesDAO().buscarParametroExames(codInt);
						request.setAttribute("lsParamEdit", lsParamEdit);
					}
					if(acao!=null && acao.equals("edit")){
						int codInt = Integer.parseInt(codigo1);
						List<Exames> lsGrupoEdit = new ExamesDAO().buscarGrupoLabExames(codInt);
						request.setAttribute("lsGrupoEdit", lsGrupoEdit);
					}
					request.setAttribute("lsGrupo", lsGrupo);
					request.setAttribute("lsExames", lsExames);
					request.setAttribute("lsParametro", lsParametro);
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=parametro1");
					saida.forward(request, response);
				}
				if(tela!=null && tela.equals("parametro2")){
					
					List<Exames> lsUnidade = new ExamesDAO().buscarUnidadeExames();
					List<Exames> lsFaixa = new ExamesDAO().buscarFaixaEtariaExames();
					List<Exames> lsExames = new ExamesDAO().buscarServicosExamesClinico();
//					List<Exames> lsReferencia = new ExamesDAO().buscarReferenciaExamesClinico();
					List<Exames> lsTesteRapido = new ExamesDAO().buscarTesteRapidoExamesClinico();
					List<Exames> lsReferencia= new ExamesDAO().buscarReferenciaExamesClinicoOrder();
					
					List<Exames> lsResultado = new ExamesDAO().buscarResultadoDoExames();
					
					String codigo = request.getParameter("cod");
					String cTeste = request.getParameter("codigo");
					String cPara  = request.getParameter("codParametro");
					String cod_parametro = request.getParameter("cod_parametro");
					String acao = request.getParameter("acao");
					String acaov= request.getParameter("acaov");
					String visual= request.getParameter("visual");
					
					if(acaov != null && cTeste!=null){
						int codigox = Integer.parseInt(cTeste);
						int parametro = Integer.parseInt(cPara);
						List<Exames> lsTesteRapEdit = new ExamesDAO().buscarTesteRapidoExamesClinico(codigox);
						List<Exames> lsParamEdits = new ExamesDAO().buscarParametroExames(parametro);
						System.out.println(lsParamEdits.get(0).getParametro_lab());
						
						request.setAttribute("lsParamEdits", lsParamEdits);
						request.setAttribute("lsTesteRapEdit", lsTesteRapEdit);
					}
					if(acao!=null && acao.equals("ver")){
						int codInt = Integer.parseInt(codigo);
						int codParametro = Integer.parseInt(cod_parametro);
						List<Exames> lsReferEdit = new ExamesDAO().buscarReferenciaExamesClinico(codInt);
						List<Exames> lsParamEdit = new ExamesDAO().buscarParametroExames(codParametro);
						
						request.setAttribute("lsParamEdit", lsParamEdit);
						request.setAttribute("lsReferEdit", lsReferEdit);
					}
					
					if(acao!=null && acao.equals("ver1"))
					{
						String cod = request.getParameter("cod");
						int codInt = Integer.parseInt(cod);
						List <Exames> VeFactura = new ExamesDAO().buscarReferenciaExamesClinicoModel(codInt);

						request.setAttribute("VeFactura", VeFactura);
					}
					else
					{
						if(request.getAttribute("VeFactura")!=null){
 			    		request.removeAttribute("VeFactura");
 			    	}
					}
					
					request.setAttribute("acao", visual);
					request.setAttribute("lsFaixa", lsFaixa);
					request.setAttribute("lsExames", lsExames);
					request.setAttribute("lsUnidade", lsUnidade);
					request.setAttribute("lsReferencia", lsReferencia);
					request.setAttribute("lsResultado", lsResultado);
					request.setAttribute("lsTesteRapido", lsTesteRapido);
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=parametro2");
					saida.forward(request, response);
				}
				if(tela!=null && tela.equals("faixa")){
					
					List<Exames> lsFaixa = new ExamesDAO().buscarFaixaEtariaExames();
					String codigo = request.getParameter("cod");
					String acao = request.getParameter("acao");
					
					if(acao!=null && acao.equals("ver")){
						int codInt = Integer.parseInt(codigo);
						List<Exames> lsFaixas = new ExamesDAO().buscarFaixaEtariaExames(codInt);
						request.setAttribute("lsFaixas", lsFaixas);
					}
					request.setAttribute("lsFaixa", lsFaixa);
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=faixa");
					saida.forward(request, response);
				}
				if(tela!=null && tela.equals("exa_unidade")){
					
					List<Exames> lsUnidade = new ExamesDAO().buscarUnidadeExames();
					
					String codigo = request.getParameter("cod");
					String acao = request.getParameter("acao");
					if(acao!=null && acao.equals("ver")){
						int codInt = Integer.parseInt(codigo);
						List<Exames> lsUniEdit = new ExamesDAO().buscarUnidadeExames(codInt);
						request.setAttribute("lsUniEdit", lsUniEdit);
					}
					
					request.setAttribute("lsUnidade", lsUnidade);
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=exa_unidade");
					saida.forward(request, response);
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				if(tela!=null && (tela.equals("grupoExames") || tela!=null && tela.equals("confg"))){
					List<Exames> grExames = new ExamesDAO().buscagrupoexames();
				
					String cod = request.getParameter("cod");
					if(cod!=null && !cod.equals("")){
						int cod_g = Integer.parseInt(cod);
						Exames grExam = new ExamesDAO().buscaUmGrupoExame(cod_g);
						request.setAttribute("grExame", grExam);
					}
					request.setAttribute("grExames", grExames);
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=grupoExames");
					saida.forward(request, response);
				}
				
				if(tela!=null && tela.equals("subgrupo")){
					
					List<Exames> grpExames = new ExamesDAO().buscagrupoexames();
					List<Exames> grExames = new ExamesDAO().buscarsubgrupo();
					
					String cod = request.getParameter("cod");
					if(cod!=null && !cod.equals("")){
						int cod_g = Integer.parseInt(cod);
						Exames grExam = new ExamesDAO().buscaUmSubGrupo(cod_g);
						System.out.println(grExam.getFK_grupo_analise_clinica());
						request.setAttribute("grExame", grExam);
					}
					
					request.setAttribute("grExames", grExames);
					request.setAttribute("grpExames", grpExames);
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=subgrupo");
					saida.forward(request, response);
				}
				
				if(tela!=null && tela.equals("unidade")){
					
					List<Exames> unidade = new ExamesDAO().buscarUnidade();
					request.setAttribute("unidade", unidade);
					
					String cod = request.getParameter("cod");
					if(cod!=null && !cod.equals("")){
						int cod_g = Integer.parseInt(cod);
						Exames grUnidade = new ExamesDAO().buscaUmUnidade(cod_g);
						request.setAttribute("grUnidade", grUnidade);
					}
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=unidade");
					saida.forward(request, response);
				}
				
				if(tela!=null && (tela.equals("espExam")||tela.equals("Pesquisarp")) ){

					String dInicio = request.getParameter("dataI");
					String dFinal  = request.getParameter("dataF");
					String axu 	 = request.getParameter("pesquisarp");
				
					List <Exames> lstAtendido = null;
				
					if(dInicio!=null && !dInicio.equals("") && dFinal!=null && !dFinal.equals("")){
						Date dtInicio = new Formatando().dataSql(dInicio);
						Date dtFinal = new Formatando().dataSql(dFinal);
					
						if(dtInicio!=null && !dtInicio.equals("") && dtFinal!=null && !dtFinal.equals(""))
						{
							lstAtendido = new ExamesDAO().buscarAtendidos(dtInicio, dtFinal);
						}
					}else{
						lstAtendido = new ExamesDAO().buscarAtendidos();
					}
				
					String acao = request.getParameter("acao");
				
					if(acao!=null && acao.equals("ver")){
						String cod = request.getParameter("cod");
						List <Exames> xcancelados = new ExamesDAO().buscarExamesModal(cod);
						request.setAttribute("xcancelados", xcancelados);
					}else{
						if(request.getAttribute("xcancelados")!=null){
							request.removeAttribute("xcancelados");
						}
					}
				
					request.setAttribute("lstAtendido", lstAtendido);
					request.setAttribute("axu", axu);
				
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=espExam");
					saida.forward(request, response);
				}
				if(tela!=null && tela.equals("imagio")){
				
					List <Diverso> telas = new MetodosBuscas().buscaTELASLABImagio(grupo);   //buscaTELASLAB metodo que irei usar depois
					request.setAttribute("telas", telas);
				
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=imagio");
					saida.forward(request, response);
				}
				
				if(tela!=null && (tela.equals("gerirExamesx") || tela.equals("gerirExamess"))){
					
					String cod = request.getParameter("cod");
					String acao = request.getParameter("acao");
					List<Exames> lsExames = new ExamesDAO().buscarServicosExamesClinicoImag();
					
					List<Exames>amostraExames = new ExamesDAO().buscaAmostraExames();
					List<Exames> grExames = new ExamesDAO().buscagrupoexames();
//					List<Exames> grsExames = new ExamesDAO().buscarsubgrupo();
					if(cod!=null && !cod.equals("")){
						int cod_e = Integer.parseInt(cod);
						Exames UmExame = new ExamesDAO().buscarServicosExamesClinico(cod_e);
						List<Exames> grsExames = new ExamesDAO().buscarsubgrupoEdit(UmExame.getId_sub_grupo());
						request.setAttribute("UmExame", UmExame);
						request.setAttribute("grsExames", grsExames);
					}
					request.setAttribute("acao", acao);
					request.setAttribute("lsExames", lsExames);
					request.setAttribute("grExames", grExames);
					request.setAttribute("amostraExames", amostraExames);
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=gerirExamesx");
					saida.forward(request, response);
				}
				
				if(tela!=null && tela.equals("parametro3")){
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=parametro3");
					saida.forward(request, response);
				}
				
				if(tela!=null && tela.equals("parametro4")){
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=parametro4");
					saida.forward(request, response);
				}
				
				if(tela!=null && (tela.equals("examListaIma") || tela.equals("exterCons"))){
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=examListaIma");
					saida.forward(request, response);
				}
				if(tela!=null && tela.equals("atendidosImax")){
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=atendidosImax");
					saida.forward(request, response);
				}
				if(tela!=null && tela.equals("examEsperax")){
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=examEsperax");
					saida.forward(request, response);
				}
				if(tela!=null && (tela.equals("examEsprx") || tela.equals("bueInt"))){
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=examEsprx");
					saida.forward(request, response);
				}
				if(tela!=null && tela.equals("atddos")){
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=atddos");
					saida.forward(request, response);
				}
				if(tela!=null && tela.equals("espera")){
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=espera");
					saida.forward(request, response);
				}
				
				if(tela!=null && (tela.equals("exterConss") || tela.equals("o_eexconsul"))){
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=exterConss");
					saida.forward(request, response);
				}
				if(tela!=null && tela.equals("ttendidos")){
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=ttendidos");
					saida.forward(request, response);
				}
				if(tela!=null && tela.equals("lEspera")){
	
				saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=lEspera");
				saida.forward(request, response);
			}
				
				if(tela!=null && (tela.equals("lPaciente") || tela.equals("o_ebueinter"))){
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=lPaciente");
					saida.forward(request, response);
				}
				if(tela!=null && tela.equals("iAtendidos")){
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=iAtendidos");
					saida.forward(request, response);
				}
				if(tela!=null && tela.equals("lEspera")){
	
				saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=lEspera");
				saida.forward(request, response);
			}
				
				
				if(tela!=null && (tela.equals("stockLab") || tela.equals("gerirStockLab"))){
					
					
			
					/*if (acao != null && acao.equals("perfil")) 
					{
						String valor = request.getParameter("cod");
						Produtos perfil = new ProdutosDAO().getProdutoPerfil(valor);
						int num = Integer.parseInt(valor);
						String foto = new ProdutosDAO().foto(num);

						request.setAttribute("perfil", perfil);
						request.setAttribute("foto", foto);
					}*/
			
					List<RequisicaoInterna> listaStock = iprogerirstock.buscarProdutosStockProcedimento(4);
					request.setAttribute("listaStock", listaStock);
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=stockLab");
					saida.forward(request, response);
				}
				
				if(tela!=null && tela.equals("requisitarLab")){
					
					List<Produtos> listaPrd = new ArrayList<Produtos>();
					String prodNome = request.getParameter("prodNome");
					if(prodNome!=null)
						listaPrd = progerirstock.buscarProdutosPorNome(prodNome);
					else 
						listaPrd = progerirstock.buscarProdutos ();

					request.setAttribute("listaPrd", listaPrd);
					request.setAttribute("numRequi", progerirstock.numRequisicao ("LAB"));
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=requisitarLab");
					saida.forward(request, response);
				}
				
				if(tela!=null && tela.equals("reqEfectLab")){
					
					List<RequisicaoInterna> listaPrdReq = iprogerirstock.buscarRequisicaoEfectuada(4);
					request.setAttribute("listaPrdReq", listaPrdReq);
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=reqEfectLab");
					saida.forward(request, response);
				}
				
				if(tela!=null && tela.equals("receberLab")){
					
					String acao = request.getParameter("acao");
					if(acao!=null && acao.equals("recebendo")){
						
						String codp = request.getParameter("codp");
						int codreq = Integer.parseInt(request.getParameter("cod"));

						List<RequisicaoInterna> listaPrdRecItens = iprogerirstock.buscarRequisicaoReceberItens(4, codreq);
						request.setAttribute("listaPrdRecItens", listaPrdRecItens);
						
						if(codp!=null && !codp.equals("")){
							int prd = Integer.parseInt(codp);
							RequisicaoInterna Item = iprogerirstock.buscarRequisicaoReceberItem(4, codreq, prd);
							request.setAttribute("Item", Item);
						}
					}
					List<RequisicaoInterna> listaPrdRec = iprogerirstock.buscarRequisicaoReceber(4);
					request.setAttribute("listaPrdRec", listaPrdRec);					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=receberLab");
					saida.forward(request, response);
				}
				
				if(tela!=null && tela.equals("reqRecebLab")){
					
					List<RequisicaoInterna> listaPrdRec = new ArrayList<RequisicaoInterna>();
					String acao = request.getParameter("acao");
					if(acao!=null && acao.equals("recebendo")){
						
						String codp = request.getParameter("codp");
						int codreq = Integer.parseInt(request.getParameter("cod"));
						listaPrdRec = iprogerirstock.buscarRequisicaoRecebida(4, codreq);
						List<RequisicaoInterna> listaPrdRecItens = iprogerirstock.buscarRequisicaoRecebidoItens(4, codreq);
						request.setAttribute("listaPrdRecItens", listaPrdRecItens);
						
						if(codp!=null && !codp.equals("")){
							int prd = Integer.parseInt(codp);
							RequisicaoInterna Item = iprogerirstock.buscarRequisicaoRecebidoItens(4, codreq, prd);
							request.setAttribute("modal", Item);
							request.setAttribute("local", progerirstock.buscarLocalFar());
						}
					}
					else
						listaPrdRec = iprogerirstock.buscarRequisicaoRecebida(4);
					request.setAttribute("listaPrdRec", listaPrdRec);
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=reqRecebLab");
					saida.forward(request, response);
				}
				
				if(tela!=null && tela.equals("expirarLab")){
					
					List<RequisicaoInterna> listaStock = iprogerirstock.buscarProdutosStockExpirandoProcedimento(4);
					request.setAttribute("listaStock", listaStock);
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=expirarLab");
					saida.forward(request, response);
				}
				
				if(tela!=null && tela.equals("devLab")){
					String acao = request.getParameter("acao");
					if(acao!=null && acao.equals("cancel"))
						ss.removeAttribute("reembolso");
					List<RequisicaoInterna> listaStock = iprogerirstock.buscarProdutosStockProcedimento(4);
					request.setAttribute("listaStock", listaStock);
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=devLab");
					saida.forward(request, response);
				}
				
				if(tela!=null && tela.equals("devEfectuadaLab")){
					
					List<RequisicaoInterna> listaStock = iprogerirstock.buscarProdutosDevolvidosEmStock(4);
					request.setAttribute("listaStock", listaStock);
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=devEfectuadaLab");
					saida.forward(request, response);
				}
				
				if(tela!=null && tela.equals("ajusteLab")){
					
					List<RequisicaoInterna> listaStock = iprogerirstock.buscarProdutosStockProcedimento(4);
					request.setAttribute("listaStock", listaStock);
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=ajusteLab");
					saida.forward(request, response);
				}
				
				if(tela!=null && tela.equals("histAjustLab")){
					
					List<RequisicaoInterna> historico = new GerirLaboratorioDAO().buscarAjusteProduto();
					request.setAttribute("historico", historico);
					

					String acao = request.getParameter("acao");
					if(acao!=null && acao.equals("ver"))
					{
						String cod = request.getParameter("codigo");
						List <Produtos> VeFactura = new GerirLaboratorioDAO().buscarHistAjustModal(cod);	
						request.setAttribute("VeFactura", VeFactura);
					}
					else
					{
						if(request.getAttribute("VeFactura")!=null){
				    		request.removeAttribute("VeFactura");
				    	}
				    }
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=histAjustLab");
					saida.forward(request, response);
				}
				
				if(tela!=null && tela.equals("reqLabMSGs")){
					
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=reqLabMSGs");
					saida.forward(request, response);
				}
				
				
//				Gerir stock no imagioligia 
				if(tela!=null && (tela.equals("stockImagio") || tela.equals("gerirStockImagio"))){
					
					List<RequisicaoInterna> listaStock = iprogerirstock.buscarProdutosStockProcedimento(5);
					request.setAttribute("listaStock", listaStock);					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=stockImagio");
					saida.forward(request, response);
				}
				
				if(tela!=null && tela.equals("requisitarImagio")){
					
					List<Produtos> listaPrd = new ArrayList<Produtos>();
					String prodNome = request.getParameter("prodNome");
					if(prodNome!=null)
						listaPrd = progerirstock.buscarProdutosPorNome(prodNome);
					else 
						listaPrd = progerirstock.buscarProdutos ();

					request.setAttribute("listaPrd", listaPrd);
					request.setAttribute("numRequi", progerirstock.numRequisicao ("IMA"));
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=requisitarImagio");
					saida.forward(request, response);
				}
				
				if(tela!=null && tela.equals("reqEfectuaImagio")){
					
					List<RequisicaoInterna> listaPrdReq = iprogerirstock.buscarRequisicaoEfectuada(5);
					request.setAttribute("listaPrdReq", listaPrdReq);
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=reqEfectuaImagio");
					saida.forward(request, response);
				}
				
				if(tela!=null && tela.equals("receberImagio")){
					
					String acao = request.getParameter("acao");
					if(acao!=null && acao.equals("recebendo")){
						
						String codp = request.getParameter("codp");
						int codreq = Integer.parseInt(request.getParameter("cod"));

						List<RequisicaoInterna> listaPrdRecItens = iprogerirstock.buscarRequisicaoReceberItens(5, codreq);
						request.setAttribute("listaPrdRecItens", listaPrdRecItens);
						
						if(codp!=null && !codp.equals("")){
							int prd = Integer.parseInt(codp);
							RequisicaoInterna Item = iprogerirstock.buscarRequisicaoReceberItem(5, codreq, prd);
							request.setAttribute("Item", Item);
						}
					}
					List<RequisicaoInterna> listaPrdRec = iprogerirstock.buscarRequisicaoReceber(5);
					request.setAttribute("listaPrdRec", listaPrdRec);
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=receberImagio");
					saida.forward(request, response);
				}
				
				if(tela!=null && tela.equals("recebidaImagio")){
					List<RequisicaoInterna> listaPrdRec = new ArrayList<RequisicaoInterna>();
					String acao = request.getParameter("acao");
					if(acao!=null && acao.equals("recebendo")){
						
						String codp = request.getParameter("codp");
						int codreq = Integer.parseInt(request.getParameter("cod"));
						listaPrdRec = iprogerirstock.buscarRequisicaoRecebida(5, codreq);
						List<RequisicaoInterna> listaPrdRecItens = iprogerirstock.buscarRequisicaoRecebidoItens(5, codreq);
						request.setAttribute("listaPrdRecItens", listaPrdRecItens);
						
						if(codp!=null && !codp.equals("")){
							int prd = Integer.parseInt(codp);
							RequisicaoInterna Item = iprogerirstock.buscarRequisicaoRecebidoItens(5, codreq, prd);
							request.setAttribute("modal", Item);
							request.setAttribute("local", progerirstock.buscarLocalFar());
						}
					}
					else
						listaPrdRec = iprogerirstock.buscarRequisicaoRecebida(5);
					request.setAttribute("listaPrdRec", listaPrdRec);
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=recebidaImagio");
					saida.forward(request, response);
				}
				
				if(tela!=null && tela.equals("expirarImagio")){
					
					List<RequisicaoInterna> listaStock = iprogerirstock.buscarProdutosStockExpirandoProcedimento(5);
					request.setAttribute("listaStock", listaStock);
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=expirarImagio");
					saida.forward(request, response);
				}
				
				if(tela!=null && tela.equals("devImagio")){
					
					String acao = request.getParameter("acao");
					if(acao!=null && acao.equals("cancel"))
						ss.removeAttribute("reembolso");
					List<RequisicaoInterna> listaStock = iprogerirstock.buscarProdutosStockProcedimento(5);
					request.setAttribute("listaStock", listaStock);
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=devImagio");
					saida.forward(request, response);
				}
				
				if(tela!=null && tela.equals("devEfectImagio")){
					
					List<RequisicaoInterna> listaStock = iprogerirstock.buscarProdutosDevolvidosEmStock(5);
					request.setAttribute("listaStock", listaStock);
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=devEfectImagio");
					saida.forward(request, response);
				}
				
				if(tela!=null && tela.equals("ajsuteImagio")){
					
					List<RequisicaoInterna> listaStock = iprogerirstock.buscarProdutosStockProcedimento(5);
					request.setAttribute("listaStock", listaStock);
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=ajsuteImagio");
					saida.forward(request, response);
				}
				
				if(tela!=null && tela.equals("histAjustImagio")){
					
					List<RequisicaoInterna> historico = new GerirStockImagioDAO().buscarAjusteProduto();
					request.setAttribute("historico", historico);
					

					String acao = request.getParameter("acao");
					if(acao!=null && acao.equals("ver"))
					{
						String cod = request.getParameter("codigo");
						List <Produtos> VeFactura = new GerirStockImagioDAO().buscarHistAjustModal(cod);	
						request.setAttribute("VeFactura", VeFactura);
					}
					else
					{
						if(request.getAttribute("VeFactura")!=null){
				    		request.removeAttribute("VeFactura");
				    	}
				    }
					
					saida = request.getRequestDispatcher("index.jsp?mod=lab&pesquisar=histAjustImagio");
					saida.forward(request, response);
				}
			
			}
		
		}else{
			String url = request.getRequestURL().toString();
	        String queryString = request.getQueryString();
			request.getSession().setAttribute("ultimaURL", url);
			request.getSession().setAttribute("queryString", queryString);	 
			response.sendRedirect("login.jsp");
		}
	}

}
