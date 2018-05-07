package ao.co.cligest.navegacao;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ao.co.cligest.dao.AgendaConsultaDAO;
import ao.co.cligest.dao.AlocadorDAO;
import ao.co.cligest.dao.ConfNomeFacturaDAO;
import ao.co.cligest.dao.DosagemDAO;
import ao.co.cligest.dao.EmbalagemDAO;
import ao.co.cligest.dao.FormaFarmaceuticaDAO;
import ao.co.cligest.dao.Formatando;
import ao.co.cligest.dao.FornecedorDAO;
import ao.co.cligest.dao.GrupoDAO;
import ao.co.cligest.dao.GrupoFarmaciaDAO;
import ao.co.cligest.dao.LaboratorioDAO;
import ao.co.cligest.dao.ProcedimentoStockDAO;
import ao.co.cligest.dao.ProdutosDAO;
import ao.co.cligest.dao.RequisicaoInternaDAO;
import ao.co.cligest.entidades.Alocador;
import ao.co.cligest.entidades.ConfNomeFactura;
import ao.co.cligest.entidades.Diverso;
import ao.co.cligest.entidades.Dosagem;
import ao.co.cligest.entidades.Embalagem;
import ao.co.cligest.entidades.FormaFarmaceutica;
import ao.co.cligest.entidades.Fornecedor;
import ao.co.cligest.entidades.GrupoFarmacia;
import ao.co.cligest.entidades.Laboratorio;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Produtos;
import ao.co.cligest.entidades.RequisicaoInterna;
import ao.co.cligest.interfaces.IProcedimentoStock;
import ao.co.cligest.util.CarEntidade;
import ao.co.cligest.util.ListaCarrinho;
import ao.co.cligest.util.MetodosBuscas;

/**
 * Servlet implementation class FarmController
 */
@WebServlet("/navegacaofar")
public class FarmaciaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IProcedimentoStock iprogerirstock = new ProcedimentoStockDAO();
	 private ProcedimentoStockDAO progerirstock = new ProcedimentoStockDAO();
	 Formatando ft = new Formatando();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FarmaciaController() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher saida = null;
		String mod = request.getParameter("mod");
		HttpSession ss = request.getSession(false);
		if(ss!=null)
		{
			Integer grupo = (Integer)ss.getAttribute("grupo");
			if(mod!=null && mod.equals("far"))
			{
				String tela = request.getParameter("pesquisar");
				
				if(tela!=null && tela.equals("pdt"))
				{
					List <Diverso> telas = new MetodosBuscas().buscaTFARInternaDentro(grupo);
					request.setAttribute("telas", telas);
					saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=pdt");
					saida.forward(request, response);
				}
			
				if(tela!=null && tela.equals("pos"))
				{
					saida = request.getRequestDispatcher("navegacaoft?mod=fat&pesquisar=fatProd");
					saida.forward(request, response);
				}
			
				if(tela!=null && tela.equals("alc"))
				{
					String acao = request.getParameter("acao");
					String cod = request.getParameter("cod");
					AlocadorDAO alcdDAO = new AlocadorDAO();
					if(cod==null){
						cod=""+0;
						acao = "novo";
					}
					List <Alocador> local =  alcdDAO.getLocalDeArmazena();
					Alocador localD = alcdDAO.edit_alocador(cod);
				
					request.setAttribute("localD", localD);
					request.setAttribute("localAr", local);
					request.setAttribute("acao", acao);
					request.setAttribute("cod", cod);
					saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=alc");
					saida.forward(request, response);
				}
			
					if(tela!=null && tela.equals("li") || tela!=null && tela.equals("encomenda"))
					{
						ProdutosDAO pdDAO = new ProdutosDAO();
						String acao = request.getParameter("acao");
						String valorp = request.getParameter("valorp");
						List <Produtos> estoque = pdDAO.buscarProdutosEmEstoqueArmazem();
						List <Produtos> tqee = pdDAO.buscarTotalTipoProdutosEmEstoque();
				
						if (acao != null && acao.equals("perfil")) 
						{
							String valor = request.getParameter("cod");
							Produtos perfil = new ProdutosDAO().getProdutoPerfil(valor);
							int num = Integer.parseInt(valor);
							String foto = new ProdutosDAO().foto(num);

							request.setAttribute("perfil", perfil);
							request.setAttribute("foto", foto);
						}
						if(valorp!=null){
							estoque = pdDAO.buscarProdutosEmEstoqueArmazem(valorp);
						}
				
						request.setAttribute("estoque", estoque);	
						request.setAttribute("tqee", tqee);
						saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=li");
						saida.forward(request, response);
					}
					if(tela!=null && tela.equals("cp") || tela!=null && tela.equals("farmc"))
					{
				
						ProdutosDAO pdDAO = new ProdutosDAO();
						EmbalagemDAO egDAO = new EmbalagemDAO();
						String acao = request.getParameter("acao");
						String cod = request.getParameter("cod");
				        String pesquisar = request.getParameter("valorp");
						if(cod==null)
						{
							cod=""+0;
							acao = "novo";
						}
				
						List <Produtos> lista = pdDAO.buscarProdutos();
						List <Produtos> farmac= pdDAO.buscarFarmaceutico();
						List <Embalagem> embalagem = egDAO.buscarEmbalagem();
						List <Produtos> dosagem = pdDAO.buscarApresentacao();
						List<GrupoFarmacia> gfarmacia = new GrupoFarmaciaDAO().buscarGrupoFarmacia();
						List <Produtos> taxa = pdDAO.buscarTaxa();
						int id = Integer.parseInt(cod);
						Produtos modProd = pdDAO.edit_produto(id);
						
						if(pesquisar!=null){
							lista = pdDAO.buscarProdutos_pesq(pesquisar);
						}
						
						request.setAttribute("lista", lista);
						request.setAttribute("farmac", farmac);
						request.setAttribute("embalagem", embalagem);
						request.setAttribute("dosagem", dosagem);
						request.setAttribute("taxa", taxa);
						request.setAttribute("cod", cod);
						request.setAttribute("acao", acao);
						request.setAttribute("modi", modProd);
						request.setAttribute("gfarmacia", gfarmacia);
				
						saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=cp");
						saida.forward(request, response);
					}
			if(tela!=null && tela.equals("enc"))
			{
				List<Produtos> farmacos = new ProdutosDAO().buscarProdutos();
				List <Fornecedor> fornecedores = new FornecedorDAO().fornecedorVisualizar2();
				List<Diverso> laboratorios = new MetodosBuscas().buscaLabs();
				ConfNomeFactura nomeEnc = new ConfNomeFacturaDAO().configNomEncomenda();
				String numEnco = nomeEnc.getConfig_nome()+nomeEnc.getNumEnco();
				request.setAttribute("numEnco", numEnco);
				request.setAttribute("produtos", farmacos);
				request.setAttribute("laboratorios", laboratorios);
				request.setAttribute("fornecedores", fornecedores);
				String acao = request.getParameter("acao");
				if(acao!=null&& acao.equals("cancel"))
				{
				HttpSession s = request.getSession(false);
				if(s.getAttribute("lsEncomenda")!=null){
					s.removeAttribute("lsEncomenda");
				}
				}
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=enc");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("encms")){
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=encms");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("recEnc"))
			{
				String acao = request.getParameter("acao");
				List<Produtos> listaEncomenda = new ProdutosDAO().buscarEncomendas();
				request.setAttribute("listaEncomenda", listaEncomenda);
				if(acao!=null)
				{
					request.setAttribute("acao", "confirmar");
				}
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=recEnc");
				saida.forward(request, response);
			}

			if(tela!=null && tela.equals("encRec"))
			{
				ProdutosDAO pdDAO = new ProdutosDAO();
				List <Produtos> encomds = pdDAO.buscarListaDeEncomendado();
				request.setAttribute("encomds", encomds);
				String encoId = request.getParameter("encoId");
				if(encoId!=null){
					int fk_enco = Integer.parseInt(encoId);
					List <Produtos> produtos = new ProdutosDAO().buscarProdutosRecebidosLista(fk_enco);
					request.setAttribute("produtos", produtos);
				}
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=encRec");
				saida.forward(request, response);
			}
			
			if(tela!=null && tela.equals("alcProd")){	
				ProdutosDAO pdDAO = new ProdutosDAO();
				List <Produtos> encoms = pdDAO.buscarListaDeEncomendado();
				request.setAttribute("encoms", encoms);
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=alcProd");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("ajInv")){
				List<Produtos> produtos = new ProdutosDAO().buscarProdutosEstoques();
				request.setAttribute("produtos", produtos);
				String acao = request.getParameter("acao");
				if(acao!=null&& !acao.equals("cancel"))
					{
					ConfNomeFactura nomeEnc = new ConfNomeFacturaDAO().configNumDiferenca();
					String numEnco = nomeEnc.getConfig_nome()+nomeEnc.getNumEnco(); 
					request.setAttribute("acao", acao);
					request.setAttribute("numDf", numEnco);
					}
				if(acao!=null&& acao.equals("cancel"))
					{
					HttpSession s = request.getSession(false);
					if(s.getAttribute("lsAjustes")!=null){
						s.removeAttribute("lsAjustes");
					}
					}
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=ajInv");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("histAjust")){
				
				List <RequisicaoInterna> historico = new RequisicaoInternaDAO().buscarHistoricoDeAjusteArmazem();
				request.setAttribute("historico", historico);
				
				String acao = request.getParameter("accao");
				if(acao!=null && acao.equals("h_a_m"))
				{
					String cod = request.getParameter("codigo");
					List <RequisicaoInterna> VeModal = new RequisicaoInternaDAO().buscarHistAjustModals(cod);	
					request.setAttribute("VeModal", VeModal);
				}
				else
				{
					if(request.getAttribute("VeModal")!=null){
			    		request.removeAttribute("VeModal");
			    	}
			    }
				
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=histAjust");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("emb") || tela!=null && tela.equals("embalar"))
			{
				EmbalagemDAO egDAO = new EmbalagemDAO();
				String acao = request.getParameter("acao");
				String cod = request.getParameter("cod");
				
				String acaof = request.getParameter("acaof");
				String codf = request.getParameter("codf");
				
				String acaod = request.getParameter("acaod");
				String codd = request.getParameter("codd");
				
				String acaox= request.getParameter("acaox");
				String codx= request.getParameter("codx");
				
				String acaog= request.getParameter("acaog");
				String codg= request.getParameter("codg");
				
				if(cod==null){
				    cod=""+0;
				    acao = "novo";
				   }
				
				if(codf==null){
				    codf=""+0;
				    acaof = "novo";
				   }
				
				if(codd==null){
				    codd=""+0;
				    acaod = "novo";
				   }
				
				if(codx==null){
				    codx=""+0;
				    acaox = "novo";
				   }
				
				if(codg==null){
				    codg=""+0;
				    acaog = "novo";
				   }
				
				List <Embalagem> embalagem = egDAO.buscarEmbalagem();
				List<FormaFarmaceutica> forma = new FormaFarmaceuticaDAO().buscarFormaFarmaceutica();
				List<Dosagem> dosagem = new DosagemDAO().buscarDosagem();
				List<Laboratorio> laboratorio = new LaboratorioDAO().buscarLaboratorio();
				List<GrupoFarmacia> gfarmacia = new GrupoFarmaciaDAO().buscarGrupoFarmacia();
				
				Embalagem modEmbalagem = egDAO.edit_embalagem(cod);
				FormaFarmaceutica modForma = new FormaFarmaceuticaDAO().edit_formaFarmaceutica(codf);
				Dosagem modDosagem = new DosagemDAO().edit_dosagem(codd);
				Laboratorio modLaboratorio = new LaboratorioDAO().edit_laboratorio(codx);
				GrupoFarmacia modGrupo = new GrupoFarmaciaDAO().edit_grupoFarmacia(codg);
				
				
				
				request.setAttribute("embalagem", embalagem);
				request.setAttribute("dosagem", dosagem);
				request.setAttribute("laboratorios", laboratorio);
				request.setAttribute("gfarmacia", gfarmacia);
				request.setAttribute("forma", forma);
				request.setAttribute("acao", acao);
				request.setAttribute("cod", cod);
				request.setAttribute("acaof", acaof);
				request.setAttribute("codf", codf);
				request.setAttribute("acaox", acaox);
				request.setAttribute("codx", codx);
				request.setAttribute("acaog", acaog);
				request.setAttribute("codg", codg);
				request.setAttribute("modGrupo", modGrupo);
				
				request.setAttribute("acaod", acaod);
				request.setAttribute("codd", codd);
				request.setAttribute("modForma", modForma);
				request.setAttribute("modDado", modEmbalagem);
				request.setAttribute("modDosagem", modDosagem);
				request.setAttribute("modLaboratorio", modLaboratorio);
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=emb");
				saida.forward(request, response);
			}
			
			
			if(tela!=null && tela.equals("local")){
			
				String acao = request.getParameter("acao");
				String cod = request.getParameter("cod");
				AlocadorDAO alcdDAO = new AlocadorDAO();
				if(cod==null){
				    cod=""+0;
				    acao = "novo";
				   }
				List <Alocador> local =  alcdDAO.getLocalDeArmazena();
				Alocador localD = alcdDAO.edit_alocador(cod);
				
				request.setAttribute("localD", localD);
				request.setAttribute("localAr", local);
				request.setAttribute("acao", acao);
				request.setAttribute("cod", cod);
				
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=local");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("bloco")){
				String acao = request.getParameter("acao");
				String cod = request.getParameter("cod");
				AlocadorDAO alcdDAO = new AlocadorDAO();
				if(cod==null){
				    cod=""+0;
				    acao = "novo";
				   }
				List <Alocador> local =  alcdDAO.getLocalDeArmazena();
				List <Alocador> blocoL = alcdDAO.getBlocoAla();
				Alocador localB = alcdDAO.edit_alaBloco(cod);
				
				request.setAttribute("localAr", local);
				request.setAttribute("blocoL", blocoL);
				request.setAttribute("localB", localB);
				request.setAttribute("cod", cod);
				request.setAttribute("acao", acao);
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=bloco");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("montra")){
				AlocadorDAO alcdDAO = new AlocadorDAO();
				String acao = request.getParameter("acao");
				String cod = request.getParameter("cod");
				if(cod==null){
				    cod=""+0;
				    acao = "novo";
				   }
				List <Alocador> local =  alcdDAO.getLocalDeArmazena();
				List <Alocador> montraL = alcdDAO.getMontra();
				Alocador montraM = alcdDAO.edit_montra(cod);
				
				request.setAttribute("localAr", local);
				request.setAttribute("montraL", montraL);
				request.setAttribute("montraM", montraM);
				request.setAttribute("cod", cod);
				request.setAttribute("acao", acao);
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=montra");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("prate")){
				AlocadorDAO alcdDAO = new AlocadorDAO();
				String acao = request.getParameter("acao");
				String cod = request.getParameter("cod");
				if(cod==null){
				    cod=""+0;
				    acao = "novo";
				   }
				List <Alocador> local =  alcdDAO.getLocalDeArmazena();
				List <Alocador> pratL = alcdDAO.getPratileira();
				Alocador pratM = alcdDAO.edit_Pratileira(cod);
				
				request.setAttribute("localAr", local);
				request.setAttribute("pratL", pratL);
				request.setAttribute("pratM", pratM);
				
				request.setAttribute("cod", cod);
				request.setAttribute("acao", acao);
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=prate");
				saida.forward(request, response);
			}
			
			if(tela!=null && tela.equals("requisitar")){
				
				Date data = new Date();
				SimpleDateFormat formatar = new SimpleDateFormat("y");
				String dataString = formatar.format(data);
				
				int numero = new RequisicaoInternaDAO().numero();
				String numEnco = "REF"+dataString+"/FAR/00" + numero;
				
				List<Produtos> farmacos = new ProdutosDAO().buscarProdutos();
				String valorp = request.getParameter("valorp");
				if(valorp!=null){
					farmacos = new ProdutosDAO().buscarProdutos(valorp);
				}
				request.setAttribute("produtos", farmacos);
				request.setAttribute("numEnco", numEnco);
				
				String acao = request.getParameter("acao");
				if(acao!=null&& acao.equals("cancel"))
				{
				HttpSession s = request.getSession(false);
				if(s.getAttribute("lsEncomendas")!=null){
					s.removeAttribute("lsEncomendas");
				}
				}
				
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=requisitar");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("receber")){
				
								
				String acao = request.getParameter("acao");
				if(acao!=null && acao.equals("recebendo")){
					
					String codp = request.getParameter("codp");
					int codreq = Integer.parseInt(request.getParameter("cod"));

					List<RequisicaoInterna> listaPrdRecItens = iprogerirstock.buscarRequisicaoReceberItens(1, codreq);
					request.setAttribute("listaPrdRecItens", listaPrdRecItens);
					
					if(codp!=null && !codp.equals("")){
						int prd = Integer.parseInt(codp);
						RequisicaoInterna Item = iprogerirstock.buscarRequisicaoReceberItem(1, codreq, prd);
						request.setAttribute("Item", Item);
						
						request.setAttribute("local", progerirstock.buscarLocalFar());
					}
				}
				List<RequisicaoInterna> listaPrdRec = iprogerirstock.buscarRequisicaoReceber(1);
				request.setAttribute("listaPrdRec", listaPrdRec);
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=receber");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("declinada")){
			
				List<RequisicaoInterna> listaProduto = new RequisicaoInternaDAO().buscarRequisicaoDeclininada();
				
				String acao = request.getParameter("acao");
				String valorp = request.getParameter("valorp");
				if(valorp!=null){
					listaProduto = new RequisicaoInternaDAO().buscarRequisicaoDeclininada(valorp);
				}
				
				if(acao!=null && acao.equals("ver"))
				{
					String cod = request.getParameter("cod");
					List <RequisicaoInterna> VeFactura = new RequisicaoInternaDAO().buscarProdutoRequisitadoIntModal(cod);
					request.setAttribute("VeFactura", VeFactura);
				}
				else
				{
					if(request.getAttribute("VeFactura")!=null){
			    		request.removeAttribute("VeFactura");
			    	}
			    }
				
				String codigo = request.getParameter("codigo");
				if(codigo!=null){
					int cod = Integer.parseInt(codigo); 
					List<Produtos> lista = new RequisicaoInternaDAO().buscarProdutosExpirados(cod);
					request.setAttribute("lista", lista);
				}
				
				request.setAttribute("listaProduto", listaProduto);
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=declinada");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("reqFeitas")){
				
				List<RequisicaoInterna> devolucaoFeita = new RequisicaoInternaDAO().buscarRequisicaoFeitas();
				request.setAttribute("devolucaoFeita", devolucaoFeita);
				
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=reqFeitas");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("reqRecebida")){
				
				List<RequisicaoInterna> listaPrdRec = new ArrayList<RequisicaoInterna>();
				String acao = request.getParameter("acao");
				if(acao!=null && acao.equals("recebendo")){
					
					String codp = request.getParameter("codp");
					int codreq = Integer.parseInt(request.getParameter("cod"));
					listaPrdRec = iprogerirstock.buscarRequisicaoRecebida(1, codreq);
					List<RequisicaoInterna> listaPrdRecItens = iprogerirstock.buscarRequisicaoRecebidoItens(1, codreq);
					request.setAttribute("listaPrdRecItens", listaPrdRecItens);
					
					if(codp!=null && !codp.equals("")){
						int prd = Integer.parseInt(codp);
						RequisicaoInterna Item = iprogerirstock.buscarRequisicaoRecebidoItens(1, codreq, prd);
						request.setAttribute("modal", Item);
						
						request.setAttribute("local", progerirstock.buscarLocalFar());
					}
				}
				else
					listaPrdRec = iprogerirstock.buscarRequisicaoRecebida(1);
				request.setAttribute("listaPrdRec", listaPrdRec);
				
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=reqRecebida");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("requisicao") ||tela!=null && tela.equals("inventario")){
				
				ProdutosDAO pdDAO = new ProdutosDAO();
				String acao = request.getParameter("acao");
				List <Produtos> estoque = pdDAO.buscarProdutosEmEstoque();
				List <Produtos> tqee = pdDAO.buscarTotalTipoProdutosEmEstoque();
		
				if (acao != null && acao.equals("perfil")) 
				{
					String valor = request.getParameter("cod");
					Produtos perfil = new ProdutosDAO().getProdutoPerfil(valor);
					int num = Integer.parseInt(valor);
					String foto = new ProdutosDAO().foto(num);

					request.setAttribute("perfil", perfil);
					request.setAttribute("foto", foto);
				}
		
				request.setAttribute("estoque", estoque);	
				request.setAttribute("tqee", tqee);
				
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=inventario");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("preste")){
				
				List<RequisicaoInterna> lista = new RequisicaoInternaDAO().buscarProdutoEmStockNaFarmacia();
				String valorp = request.getParameter("valorp");
				String valorn = request.getParameter("numDias");
				
				if(valorp!=null){
					lista = new RequisicaoInternaDAO().buscarProdutoEmStockNaFarmacia(valorp);
				}
				if(valorn!=null){
					lista = new RequisicaoInternaDAO().buscarProdutoEmStockNaFarmacias(valorn);
				}
				
				request.setAttribute("lista", lista);
				
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=preste");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("devRequisicao")){
				
				List <Produtos> devolucao = new ProdutosDAO().buscarProdutosEmEstoque();
				String codigo = request.getParameter("codigo");
				
				if(codigo!=null){
					int codigoInt = Integer.parseInt(codigo);
					List<RequisicaoInterna> visDevolucao = new RequisicaoInternaDAO().buscarDevolucaoProdutoVisual(codigoInt);
					request.setAttribute("visDevolucao", visDevolucao);
				}
				request.setAttribute("devolucao", devolucao);
				
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=devRequisicao");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("reqDevolvida")){
				
				List<RequisicaoInterna> histDev = new RequisicaoInternaDAO().buscarRequisicaoDevolucao();
				request.setAttribute("histDev", histDev);
				
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=reqDevolvida");
				saida.forward(request, response);
			}
			
			List<RequisicaoInterna> listaProduto = new RequisicaoInternaDAO().buscarRequisicaoDeclininada();
			request.setAttribute("listaProduto", listaProduto);
			
			String codigo = request.getParameter("codigo");
			if(codigo!=null){
				int cod = Integer.parseInt(codigo); 
				List<Produtos> lista = new RequisicaoInternaDAO().buscarProdutosExpirados(cod);
				request.setAttribute("lista", lista);
			}
			
			if(tela!=null && tela.equals("lancarp")){
				
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=lancarp");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("ajustInv")){
				
				ProdutosDAO pdDAO = new ProdutosDAO();
				List<Produtos> produtos = pdDAO.buscarProdutosEstoque();
				request.setAttribute("produtos", produtos);
				
				String acao = request.getParameter("acao");
				if(acao!=null&& !acao.equals("cancel"))
					{
					ConfNomeFactura nomeEnc = new ConfNomeFacturaDAO().configNumDiferenca();
					String numEnco = nomeEnc.getConfig_nome()+nomeEnc.getNumEnco(); 
					request.setAttribute("acao", acao);
					request.setAttribute("numDf", numEnco);
					}
				if(acao!=null&& acao.equals("cancel"))
					{
					HttpSession s = request.getSession(false);
					if(s.getAttribute("lsAjustess")!=null){
						s.removeAttribute("lsAjustess");
					}
					}
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=ajustInv");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("histAjus")){
				
				List<RequisicaoInterna> historico = new RequisicaoInternaDAO().buscarAjusteProduto();
				request.setAttribute("historico", historico);
				

				String acao = request.getParameter("acao");
				if(acao!=null && acao.equals("ver"))
				{
					String cod = request.getParameter("codigo");
					List <Produtos> VeFactura = new RequisicaoInternaDAO().buscarHistAjustModal(cod);	
					request.setAttribute("VeFactura", VeFactura);
				}
				else
				{
					if(request.getAttribute("VeFactura")!=null){
			    		request.removeAttribute("VeFactura");
			    	}
			    }
				
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=histAjus");
				saida.forward(request, response);
			}
			
//			Centro de distribuição
			if(tela!=null && tela.equals("centro")){
				
				List <Diverso> telas = new MetodosBuscas().buscaTPFARCentroDistr(grupo);
				request.setAttribute("telas", telas);
				
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=centro");
				saida.forward(request, response);
			}
			
			if(tela!=null && tela.equals("encoInterna") || tela!=null && tela.equals("reqFarm")){
				
				String codigox = request.getParameter("codigo");
				String sessao = request.getParameter("sessao");
				
				if(codigox!=null && sessao == null){
					int cod = Integer.parseInt(codigox);
					List<Produtos> lista = new RequisicaoInternaDAO().buscarProdutoRequisitado(cod);
					ListaCarrinho lsEncomenda = new ListaCarrinho();
					
					for(Produtos p: lista){
						CarEntidade c = new CarEntidade();
						c.setId_produto(p.getId_produto());
						c.setFarmaco(p.getNome_comercial());
						c.setNumero(p.getNumero_ordem());
						c.setPreco_c(""+p.getPrecoUnitario());
						c.setQuantidade(p.getQuantidade());
						c.setNum_lote(p.getNumero_lote_sere());
						c.setQuantdd(""+p.getQtd_por_receber());
						c.setTotal_pago(p.getTotal());
						c.setData(ft.dataToPadrao(p.getData_de_caducidade()));
						lsEncomenda.adFarmacos(c);
					}
					ss.setAttribute("lsEncomendax", lsEncomenda);
					ss.setAttribute("lsEncomendax2", new ListaCarrinho());
				}
				
				String acao = request.getParameter("acao");
				if(acao!=null&& acao.equals("cancel"))
				{
				HttpSession s = request.getSession(false);
				if(s.getAttribute("lsEncomendax")!=null){
					s.removeAttribute("lsEncomendax");
				}
				}
				
				List<Produtos> listaEncomenda = new RequisicaoInternaDAO().buscarRequisicaoInternas();
				request.setAttribute("listaEncomenda", listaEncomenda);
				
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=encoInterna");
				saida.forward(request, response);
			}
			
			if(tela!=null && tela.equals("reqDistrib")){
				
				String valorp = request.getParameter("valorp");
				String acao = request.getParameter("acao");
				List<RequisicaoInterna> distribuida = new RequisicaoInternaDAO().buscarRequisitadoDistribuida();
				
				if(valorp!=null){
					distribuida = new RequisicaoInternaDAO().buscarRequisitadoDistribuida(valorp);
				}
				
				if(acao!=null && acao.equals("ver"))
				{
					String cod = request.getParameter("cod");
					List <Produtos> VeFactura = new RequisicaoInternaDAO().buscarProdutoRequisitadoModal(cod);
					request.setAttribute("VeFactura", VeFactura);
				}
				else
				{
					if(request.getAttribute("VeFactura")!=null){
			    		request.removeAttribute("VeFactura");
			    	}
			    }
				
				if(acao!=null && acao.equals("ver1"))
				{
					String cod = request.getParameter("cod");
					List <Produtos> VeFactura2 = new RequisicaoInternaDAO().buscarProdutosEncomendadosModel(cod);
					request.setAttribute("VeFactura2", VeFactura2);
				}
				else
				{
					if(request.getAttribute("VeFactura2")!=null){
			    		request.removeAttribute("VeFactura2");
			    	}
			    }
				
				request.setAttribute("distribuida", distribuida);
				
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=reqDistrib");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("reqExpirar")){
				
				List<RequisicaoInterna> prodCaducado = new RequisicaoInternaDAO().buscarProdutosExpirados();
				
				String valorp = request.getParameter("valorp");
				if(valorp!=null){
					System.out.println("Valor produto: "+valorp);
					prodCaducado = new RequisicaoInternaDAO().buscarProdutosExpirados(valorp);
				}
				request.setAttribute("caducados", prodCaducado);
				
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=reqExpirar");
				saida.forward(request, response);
			}
			
			if(tela!=null && tela.equals("confReq")){
				
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=confReq");
				saida.forward(request, response);
			}
			
			if(tela!=null && tela.equals("requiMSG")){
				
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=requiMSG");
				saida.forward(request, response);
			}
			if(tela!=null && tela.equals("reqMSGs")){
				
				saida = request.getRequestDispatcher("index.jsp?mod=far&pesquisar=reqMSGs");
				saida.forward(request, response);
			}
			
			if(tela==null ) 
			{
				List <Diverso> telas = new MetodosBuscas().buscaTFAR(grupo);
				request.setAttribute("telas", telas);
				saida = request.getRequestDispatcher("index.jsp?mod=far");
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
