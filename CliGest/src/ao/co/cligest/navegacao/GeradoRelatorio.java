package ao.co.cligest.navegacao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import ao.co.cligest.dao.Conexao;
import ao.co.cligest.dao.Formatando;
import ao.co.cligest.dao.InstituicaoDAO;
import ao.co.cligest.dao.LaboratorioDAO;
import ao.co.cligest.dao.PacienteDAO;
import ao.co.cligest.dao.RelatorioPagamentosDAO;
import ao.co.cligest.entidades.Facturacao;
import ao.co.cligest.entidades.Instituicao;
import ao.co.cligest.relatoriodaos.Util;
import ao.co.cligest.util.GeradorDeRelatorios;
import ao.co.cligest.util.ListaCarrinho;
import ao.co.cligest.util.RelatorioUtil;


/**  
 * Servlet implementation class RelatorioController
 */  
@WebServlet("/GeradoRelatorio")
public class GeradoRelatorio extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	Connection con = Conexao.getConexao();
	RelatorioUtil rutil = new RelatorioUtil();
//	String logoImage = "logoGesthosp.png";
    public GeradoRelatorio() {
        super();
        // TODO Auto-generated constructor stub
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
	
		 Instituicao dadosEmp = new InstituicaoDAO().getInstalacaoRel();
		 String nomeRelat = req.getParameter("nomeRelat");
		 String cod_param = req.getParameter("cod_param");
		 String cod_seguradora = req.getParameter("cod_seguradora");
		 String nomefunc = req.getParameter("codf");
		 String requerente = req.getParameter("requerente");
		 
		 String cod_troco = req.getParameter("troco");
		 String cod_total_recebido = req.getParameter("total_recebido");
		 
		 // DADOS DA ESTATISTICA - INDICADORES ... DD MEXENDO
		 String mes_indicador = req.getParameter("mes");
		 String dia_indicador = req.getParameter("dia");
		 String outroDia_indicador = req.getParameter("outroDia");
		 String escolha_indicador = req.getParameter("escolha");
		 
		 
		 HttpSession ss = req.getSession(false);
		 String usuario = (String)ss.getAttribute("nome_us");
		 ServletOutputStream saida = resp.getOutputStream();	
		 
		 // IMAGEM DO CENTRO
		 //String logoImage = getServletContext().getRealPath("/WEB-INF/relatorios/logoGesthosp.jpg");
		 
		 // IMAGEM DA DADIVA
		 String logoImage = getServletContext().getRealPath("/WEB-INF/relatorios/caridade.jpg");
		 
		 // Imagem do Produto
		 String imgProd = getServletContext().getRealPath("/icons/caridade.jpg");
		 
//		 String PerfilImage = getServletContext().getRealPath("/ficheiro/usuarios.jpg");
		 
//		 params.put("logo", gto.getImage());
//		 String caminho = new RelatoriosJasper().getClass().getResource(PerfilImage).toString().substring(6);
//		 System.out.println("Caminho: "+gto.getImage());
		
		 GeradorDeRelatorios gpdf = new GeradorDeRelatorios();
		 Map param = new HashMap();
		 
		 // Pacientes Cadastrados
		 if(nomeRelat!=null && nomeRelat.equals("PacientesCadastrados"))
		 {
			 try
			 {
				 
				 if(nomeRelat!=null && nomeRelat.equals("PacientesCadastrados"))
				 {
					/* List <Parente> lista = (List <Parente> ) ss.getAttribute("listaAgendado");
					 if(!lista.isEmpty())
					 {
						 for (Parente paciente : lista) 
						 {
							System.out.println("P: "+paciente.getNome_parente());
						}
					 }
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/exemploLista.jrxml");
					 gpdf.geraPdfComLista(jrxml, null, saida, lista);
					 */
					 
					 param.put("logoImage", logoImage);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
					 gpdf.geraPdf(jrxml, param, saida);
				 }
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 
		 if(nomeRelat!=null && nomeRelat.equals("HistoricoPacientesUrgencia"))
		 {
			 try
			 {
				 
				 if(nomeRelat!=null && nomeRelat.equals("HistoricoPacientesUrgencia"))
				 {
					 param.put("logoImage", logoImage);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
					 gpdf.geraPdf(jrxml, param, saida);
				 }
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 
		 // Perfil do Paciente
		 if(nomeRelat!=null && nomeRelat.equals("PerfilPaciente"))
		 {
			 try
			 {
 
				 if(nomeRelat!=null && nomeRelat.equals("PerfilPaciente") && cod_param!=null && !cod_param.equals("")){
					 String foto = new PacienteDAO().getFotoPefil(cod_param);
					 ImageIcon gto = new ImageIcon(getServletContext().getRealPath("/ficheiro/"+foto));
					 
					 param.put("processo", cod_param);
					 param.put("logoImage", logoImage);
					 param.put("fotoPerfil", gto.getImage());
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
					  gpdf.geraPdf(jrxml, param, saida);
				 }
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 
		 
		 if(nomeRelat!=null && nomeRelat.equals("PerfilPaciente2"))
		 {
			 try
			 {
 
				 if(nomeRelat!=null && nomeRelat.equals("PerfilPaciente2") && cod_param!=null && !cod_param.equals("")){
					 String foto = new PacienteDAO().getFotoPefil(cod_param);
					 ImageIcon gto = new ImageIcon(getServletContext().getRealPath("/ficheiro/"+foto));
					 
					 param.put("processo", cod_param);
					 param.put("logoImage", logoImage);
					 param.put("fotoPerfil", gto.getImage());
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
					  gpdf.geraPdf(jrxml, param, saida);
				 }
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 
		 //rel consultas agendadas
		 if(nomeRelat!=null && nomeRelat.equals("ConsultasAgendadaA5"))
		 {
			 try{
				 if(nomeRelat!=null && nomeRelat.equals("ConsultasAgendadaA5") && cod_param!=null && !cod_param.equals("")){
					 param.put("idConsMarcada", Integer.parseInt(cod_param));
					 param.put("logoImage", logoImage);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
					  gpdf.geraPdf(jrxml, param, saida);
				 }
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 
		// Hemoterapia
		 
		 if(nomeRelat!=null && nomeRelat.equals("ColherSangue"))
		 {
			 try{
				 if(nomeRelat!=null && nomeRelat.equals("ColherSangue") && cod_param!=null && !cod_param.equals("")){
					 param.put("id_sangue_colhido", Integer.parseInt(cod_param));
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/ColherSangue.jrxml");
					  gpdf.geraPdf(jrxml, param, saida);
				 }
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 
		 if(nomeRelat!=null && nomeRelat.equals("listaSangueColhido"))
		 {
			 try{
				 if(nomeRelat!=null && nomeRelat.equals("listaSangueColhido") && cod_param!=null && !cod_param.equals("")){
					 param.put("ver", cod_param);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/listaDeSangueColhido.jrxml");
					  gpdf.geraPdf(jrxml, param, saida);
				 }
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 
		 
		 if(nomeRelat!=null && nomeRelat.equals("indicadorTotal"))
		 {
		
			
			 System.out.println("Testando o ano: " + cod_param);
			 System.out.println("Testando o mes: " + mes_indicador);
			 System.out.println("Testando o dia: " + dia_indicador);
			 System.out.println("Testando o outro dia: " + outroDia_indicador);
			 System.out.println("Testando a escolha: " + escolha_indicador);
			 
			 
			 if(cod_param!=null && !cod_param.equals("") && mes_indicador!=null && !mes_indicador.equals("") && dia_indicador!=null && !dia_indicador.equals("") && outroDia_indicador!=null && !outroDia_indicador.equals("")){
					
				 System.out.println("Existe ano, mes, dia e outro dia");
				 try{
					 if(nomeRelat!=null && nomeRelat.equals("indicadorTotal") && cod_param!=null && !cod_param.equals("")){
						 param.put("ano_indicador", Integer.parseInt(cod_param));
						 param.put("mes_indicador", mes_indicador);
						 param.put("dia_indicador",dia_indicador);
						 param.put("outroDia_indicador", outroDia_indicador);
						 param.put("logoImage", logoImage);
						 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/IndicadorYYYYMMDDDD.jrxml");
						  gpdf.geraPdf(jrxml, param, saida);
					 }
				 }
				 catch(Exception e){
					 e.printStackTrace();
				 }
				 
				}else if(cod_param != null && !cod_param.equals("") && mes_indicador!= null && !mes_indicador.equals("") && dia_indicador!= null && !dia_indicador.equals("")){
					
					System.out.println("Existe ano, mes e dia");
		
				}  else if(cod_param!=null && !cod_param.equals("") && mes_indicador!=null && !mes_indicador.equals("") && outroDia_indicador!= null && !outroDia_indicador.equals("")){
					
					System.out.println("Existe ano, mes e outro dia");
		            
			   } else if(cod_param!=null && !cod_param.equals("") && mes_indicador!=null && !mes_indicador.equals("")){
					
					System.out.println("Existe ano e mes");
		           
				} else if (cod_param!=null && !cod_param.equals("") && escolha_indicador!=null && !escolha_indicador.equals("")){
					
					System.out.println("Existe ano e escolha");
					if(escolha_indicador.equals("1")){
						System.out.println("escolha " + escolha_indicador);
					}
					if(escolha_indicador.equals("2")){
						System.out.println("escolha " + escolha_indicador);
					}
					if(escolha_indicador.equals("3")){
						System.out.println("escolha " + escolha_indicador);
					}
					if(escolha_indicador.equals("4")){
						System.out.println("escolha " + escolha_indicador);
					}
					if(escolha_indicador.equals("5")){
						System.out.println("escolha " + escolha_indicador);
					}
					if(escolha_indicador.equals("6")){
						System.out.println("escolha " + escolha_indicador);
					}
				
				} else if(cod_param!=null && !cod_param.equals("")){
		            
					System.out.println("Existe ano");
					
					
				}else{
				   System.out.println("Existe geral");
			   }
		      }
		 
		// HistoricoConsultasAgendadas
		 if(nomeRelat!=null && nomeRelat.equals("HistoricoConsultasAgendadas"))
		 {
			 try
			 {
				 
				 if(nomeRelat!=null && nomeRelat.equals("HistoricoConsultasAgendadas"))
				 {
					 param.put("logoImage", logoImage);
					 //param.put("usuario", usuario);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
					 gpdf.geraPdf(jrxml, param, saida);
				 }
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
			// rel confirmaï¿½ï¿½o de consulta
			 if(nomeRelat!=null && nomeRelat.equals("ConsultasConfirmadaA5")) 
			 {
				 try{
					 if(nomeRelat!=null && nomeRelat.equals("ConsultasConfirmadaA5") && cod_param!=null && !cod_param.equals("")){
						 param.put("idConsConfirmada", Integer.parseInt(cod_param));
						 param.put("logoImage", logoImage);
						 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
						  gpdf.geraPdf(jrxml, param, saida);
					 }
				 }
				 catch(Exception e){
					 e.printStackTrace();
				 }
			 }
			 		 
			 // HistoricoConsultasConfirmadas
			 if(nomeRelat!=null && nomeRelat.equals("HistoricoConsultasConfirmadas"))
			 {
				 try
				 {
					 
					 if(nomeRelat!=null && nomeRelat.equals("HistoricoConsultasConfirmadas"))
					 {
						 param.put("logoImage", logoImage);
						 //param.put("usuario", usuario);
						 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
						 gpdf.geraPdf(jrxml, param, saida);
					 }
				 }
				 catch(Exception e){
					 e.printStackTrace();
				 }
			 }
			 
			 // rel triagem ambulatorio
			 
			 if(nomeRelat!=null && nomeRelat.equals("TriagemA5"))
			 {
				 try{
					 if(nomeRelat!=null && nomeRelat.equals("TriagemA5") && cod_param!=null && !cod_param.equals("")){
						 param.put("cod_triagem", Integer.parseInt(cod_param));
						 param.put("logoImage", logoImage);
						 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
						  gpdf.geraPdf(jrxml, param, saida);
					 }
				 }
				 catch(Exception e){
					 e.printStackTrace();
				 }
			 }
			 
			 // Historico Triagens Ambulatorio
			 if(nomeRelat!=null && nomeRelat.equals("HistoricoTriagensConsulta"))
			 {
				 try
				 {
					 
					 if(nomeRelat!=null && nomeRelat.equals("HistoricoTriagensConsulta"))
					 {
						 param.put("logoImage", logoImage);
						 //param.put("usuario", usuario);
						 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
						 gpdf.geraPdf(jrxml, param, saida);
					 }
				 }
				 catch(Exception e){
					 e.printStackTrace();
				 }
			 }
			 
			 // rel triagem banco de ue
			 
			 if(nomeRelat!=null && nomeRelat.equals("TriagemBUE"))
			 {
				 try{
					 if(nomeRelat!=null && nomeRelat.equals("TriagemBUE") && cod_param!=null && !cod_param.equals("")){
						 param.put("cod_triagem", Integer.parseInt(cod_param));
						 param.put("logoImage", logoImage);
						 param.put("usuario", usuario);
						 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
						  gpdf.geraPdf(jrxml, param, saida);
					 }
				 }
				 catch(Exception e){
					 e.printStackTrace();
				 }
			 }
			 
			// Historico Triagens no Banco de ue
			 if(nomeRelat!=null && nomeRelat.equals("HistoricoTriagensBUE"))
			 {
				 try
				 {
					 
					 if(nomeRelat!=null && nomeRelat.equals("HistoricoTriagensBUE"))
					 {
						 param.put("logoImage", logoImage);
						 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
						 gpdf.geraPdf(jrxml, param, saida);
					 }
				 }
				 catch(Exception e){
					 e.printStackTrace();
				 }
			 }
			 
		 // Modulo Relatorio - Agendamento - Exames agendados
		 if(nomeRelat!=null && nomeRelat.equals("agendarexame"))
		 {
			 try{
				 if(nomeRelat!=null && nomeRelat.equals("agendarexame") && cod_param!=null && !cod_param.equals("")){
					 param.put("id_exame_servico", Integer.parseInt(cod_param));
					 param.put("logoImage", logoImage);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
					  gpdf.geraPdf(jrxml, param, saida);
				 }
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 
		// Agenda - Pesquisar Exame - Agendados
				 if(nomeRelat!=null && nomeRelat.equals("agendarexameA5"))
				 {
					 try{
						 if(nomeRelat!=null && nomeRelat.equals("agendarexameA5") && cod_param!=null && !cod_param.equals("")){
							 param.put("id_exame_servico", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
		 
		 // Modulo Relatorio - Agendamento - Exames Confirmados
		 if(nomeRelat!=null && nomeRelat.equals("ReqExame"))
		 {
			 try{
				 if(nomeRelat!=null && nomeRelat.equals("ReqExame") && cod_param!=null && !cod_param.equals("")){
					 param.put("id_requisicao", Integer.parseInt(cod_param));
					 param.put("logoImage", logoImage);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
					  gpdf.geraPdf(jrxml, param, saida);
				 }
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 
		// Agenda - Pesquisar Exame - Confirma
				 if(nomeRelat!=null && nomeRelat.equals("ReqExameA5"))
				 {
					 try{
						 if(nomeRelat!=null && nomeRelat.equals("ReqExameA5") && cod_param!=null && !cod_param.equals("")){
							 param.put("id_requisicao", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
		 
		 // HistoricoExamesAgendados
		 if(nomeRelat!=null && nomeRelat.equals("HistoricoExamesAgendados"))
		 {
			 try
			 {
				 
				 if(nomeRelat!=null && nomeRelat.equals("HistoricoExamesAgendados"))
				 {
					 param.put("logoImage", logoImage);
					 //param.put("", usuario);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
					 gpdf.geraPdf(jrxml, param, saida);
				 }
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 
		 // HistoricoExamesConfirmados
		 if(nomeRelat!=null && nomeRelat.equals("HistoricoExamesConfirmados"))
		 {
			 try
			 {
				 
				 if(nomeRelat!=null && nomeRelat.equals("HistoricoExamesConfirmados"))
				 {
					 param.put("logoImage", logoImage);
					 //param.put("usuario", usuario);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
					 gpdf.geraPdf(jrxml, param, saida);
				 }
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 
		 //Agenda Teste de Qualidade
		 
		 if(nomeRelat!=null && nomeRelat.equals("AgendaTesteQualidade"))
		 {
			 try{
				 if(nomeRelat!=null && nomeRelat.equals("AgendaTesteQualidade") && cod_param!=null && !cod_param.equals(""))
				 {
					 param.put("id_requisicao", Integer.parseInt(cod_param));
					 param.put("logoImage", logoImage);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
					  gpdf.geraPdf(jrxml, param, saida);
				 }
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 
//		 Image logo = new ImageIcon(getServletContext().getRealPath("/WEB-INF/relatorios/logoGesthosp.png")).getImage(); 
//		 param.put("RelLogo", logo);
		 
		 // Recibo de Pagamento de Consulta Factura Geral
		 if(nomeRelat!=null && nomeRelat.equals("pagConsultaGeralA5"))
		 {
			 try
			 {
				 req.getSession().removeAttribute("troco");
				 req.getSession().removeAttribute("total_recebido");
				 req.getSession().removeAttribute("pacientepg");
				 req.getSession().removeAttribute("confirm");
				 
				 if(nomeRelat!=null && nomeRelat.equals("pagConsultaGeralA5") && cod_param!=null && !cod_param.equals(""))
				 {
					 param.put("numero_factura", cod_param);
					 param.put("troco", Double.parseDouble(cod_troco));
					 param.put("total_recebido", Double.parseDouble(cod_total_recebido));
					 param.put("logoImage", logoImage);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
					 gpdf.geraPdf(jrxml, param, saida);
				 }
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 
		// Pagamento Seguro Consulta
		if(nomeRelat!=null && nomeRelat.equals("pagConsultaPlanoSaudeA5"))
		{
			try
			{
				req.getSession().removeAttribute("cash");
				req.getSession().removeAttribute("multicaixa");
				req.getSession().removeAttribute("credito");
				
				
				req.getSession().removeAttribute("troco");
				req.getSession().removeAttribute("total_recebido");
				req.getSession().removeAttribute("pacientepg");
				req.getSession().removeAttribute("confirm");
						 
				if(nomeRelat!=null && nomeRelat.equals("pagConsultaPlanoSaudeA5") && cod_param!=null && !cod_param.equals(""))
				{
					param.put("numero_factura", cod_param);
					param.put("troco", Double.parseDouble(cod_troco));
					param.put("total_recebido", Double.parseDouble(cod_total_recebido));
					
					param.put("cash", Util.retornaQuantiaPaga(cod_param, "cash"));
					param.put("multicaixa", Util.retornaQuantiaPaga(cod_param, "multicaixa"));
					param.put("credito", Util.retornaQuantiaPaga(cod_param, "credito"));
					param.put("logoImage", logoImage);
					
					String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
					gpdf.geraPdf(jrxml, param, saida);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
				
		 
		 //Pagamento Consulta A4
		 if(nomeRelat!=null && nomeRelat.equals("PagamentoConsultaA4"))
		 {
			 try
			 {
				 
				 if(nomeRelat!=null && nomeRelat.equals("PagamentoConsultaA4") && cod_param!=null && !cod_param.equals(""))
				 {
					 param.put("numero_factura", cod_param);
					 param.put("logo", logoImage);
					 param.put("usuario", usuario);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
					  gpdf.geraPdf(jrxml, param, saida);
				 }
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 
		 //PagamentoConsultaPlanoA4
		 if(nomeRelat!=null && nomeRelat.equals("PagamentoConsultaPlanoA4"))
		 {
			 try
			 {
				 
				 if(nomeRelat!=null && nomeRelat.equals("PagamentoConsultaPlanoA4") && cod_param!=null && !cod_param.equals(""))
				 {
					 param.put("numero_factura", cod_param);
					 param.put("logo", logoImage);
					 param.put("usuario", usuario);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
					  gpdf.geraPdf(jrxml, param, saida);
				 }
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 
		 // Historico Pagamentos de Consultas
		 if(nomeRelat!=null && nomeRelat.equals("HistoricoPagamentoConsultaVendasHoje"))
		 {
			 try
			 {
				 String data_ini = req.getParameter("data_ini");
				 String data_fim = req.getParameter("data_fim");
				 
				 String escolha = req.getParameter("escolha");
				 
				 String pagos = req.getParameter("s_pagos");
				 String isentos = req.getParameter("n_pagos");
				 
				 String servico_hp = req.getParameter("servico_hp");
				 String servico_hp1 = req.getParameter("servico_hp1");
				 String servico_hp2 = req.getParameter("servico_hp2");
				 String testeNome = req.getParameter("teste");
				 System.out.println("Controller Relatï¿½rio: "+testeNome);
				 
				 if(!data_ini.isEmpty())
				 {
					 data_ini = new Formatando().dataBanco(data_ini);
					 if(!data_fim.isEmpty())
					 {
						 data_fim = new Formatando().dataBanco(data_fim);
						    
							 param.put("data_inicio_cima", data_ini);
							 param.put("data_fim_cima", data_fim);
							 nomeRelat = "HistoricoPagamentoConsultaVendasDataInicioFim";
							 
							 // CONSULTAS PAGOS
							 if(!escolha.isEmpty() && escolha.equals("1"))
							 {
								 nomeRelat = "HistoricoPagamentoConsultaVendasDataInicioFimPago"; 
								 
								 if(!pagos.isEmpty() && pagos.equals("1") && !servico_hp.isEmpty() && servico_hp.equals("0"))
									 nomeRelat = "HistoricoPagamentoConsultaVendasDataInicioFimPagoCash";
								 
								 if(!pagos.isEmpty() && pagos.equals("2") && !servico_hp.isEmpty() && servico_hp.equals("0"))
									 nomeRelat = "HistoricoPagamentoConsultaVendasDataInicioFimPagoMulticaixa";
								 
								 if(pagos.isEmpty() && !servico_hp.isEmpty() && !servico_hp.equals("0"))
								 {
									 int servico = Integer.parseInt(servico_hp);
									 param.put("FK_servico_cima", servico);
									 nomeRelat = "HistoricoPagamentoConsultaVendasDataInicioFimPagoServico";
								 }
								 
								 if(!pagos.isEmpty() && pagos.equals("1") && !servico_hp.isEmpty() && !servico_hp.equals("0"))
								 {
									 int servico = Integer.parseInt(servico_hp);
									 param.put("FK_servico_cima", servico);
									 nomeRelat = "HistoricoPagamentoConsultaVendasDataInicioFimPagoCashServico";
								 }
								 if(!pagos.isEmpty() && pagos.equals("2") && !servico_hp.isEmpty() && !servico_hp.equals("0"))
								 {
									 int servico = Integer.parseInt(servico_hp);
									 param.put("FK_servico_cima", servico);
									 nomeRelat = "HistoricoPagamentoConsultaVendasDataInicioFimPagoMulticaixaServico";
								 }
						     }
							 
							 // CONSULTAS ISENTAS
							 if(!escolha.isEmpty() && escolha.equals("2"))
							 {
								 nomeRelat = "HistoricoPagamentoConsultaVendasDataInicioFimIsento"; 
								 
								 if(!isentos.isEmpty() && isentos.equals("1") && !servico_hp1.isEmpty() && servico_hp1.equals("0"))
									 nomeRelat = "HistoricoPagamentoConsultaVendasDataInicioFimIsentoIdade";
								 
								 if(!isentos.isEmpty() && isentos.equals("2") && !servico_hp1.isEmpty() && servico_hp1.equals("0"))
									 nomeRelat = "HistoricoPagamentoConsultaVendasDataInicioFimIsentoAtribuido";
								 
								 if(isentos.isEmpty() && !servico_hp1.isEmpty() && !servico_hp1.equals("0"))
								 {
									 int servico = Integer.parseInt(servico_hp1);
									 param.put("FK_servico_cima", servico);
									 nomeRelat = "HistoricoPagamentoConsultaVendasDataInicioFimIsentoServico";
								 }
								 if(!isentos.isEmpty() && isentos.equals("1") && !servico_hp1.isEmpty() && !servico_hp1.equals("0"))
								 {
									 int servico = Integer.parseInt(servico_hp1);
									 param.put("FK_servico_cima", servico);
									 nomeRelat = "HistoricoPagamentoConsultaVendasDataInicioFimIsentoIdadeServico";
								 }
								 if(!isentos.isEmpty() && isentos.equals("2") && !servico_hp1.isEmpty() && !servico_hp1.equals("0"))
								 {
									 int servico = Integer.parseInt(servico_hp1);
									 param.put("FK_servico_cima", servico);
									 nomeRelat = "HistoricoPagamentoConsultaVendasDataInicioFimIsentoAtribuidoServico";
								 }	 
						     }
							 
							 // PLANO DE SAï¿½DE
							 if(!escolha.isEmpty() && escolha.equals("3"))
							 {
								 nomeRelat = "HistoricoPagamentoConsultaVendasDataInicioFimPlanoSaude";  
								 
								 if(!servico_hp2.isEmpty() && !servico_hp2.equals("0"))
								 {
									 int servico = Integer.parseInt(servico_hp2);
									 param.put("FK_servico_cima", servico);
									 nomeRelat = "HistoricoPagamentoConsultaVendasDataInicioFimPlanoSaudeServico";
								 }
						     }
					 	}
				 	}
					 param.put("logoImage", logoImage);
					 //param.put("usuario", usuario);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
					 gpdf.geraPdf(jrxml, param, saida); 
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 
		 
		 // Historico Pagamentos de Exame
		 
				 if(nomeRelat!=null && nomeRelat.equals("HistoricoPagamentoExameVendasHoje"))
				 {
					 try
					 {
						 String data_ini = req.getParameter("data_ini");
						 String data_fim = req.getParameter("data_fim");
						 
						 String escolha = req.getParameter("escolha");
						 
						 String pagos = req.getParameter("s_pagos");
						 String isentos = req.getParameter("n_pagos");
						 
						 String exame_hp = req.getParameter("exame_hp");
						 String exame_hp1 = req.getParameter("exame_hp1");
						 String exame_hp2 = req.getParameter("exame_hp2");
						 String testeNome = req.getParameter("teste");
						 System.out.println("Controller Relatï¿½rio: "+testeNome);
						 
						 if(!data_ini.isEmpty())
						 {
							 data_ini = new Formatando().dataBanco(data_ini);
							 if(!data_fim.isEmpty())
							 {
								 data_fim = new Formatando().dataBanco(data_fim);
								    
									 param.put("data_inicio_cima", data_ini);
									 param.put("data_fim_cima", data_fim);
									 nomeRelat = "HistoricoPagamentoExameVendasDataInicioFim";
									 
									 // CONSULTAS PAGOS
									 if(!escolha.isEmpty() && escolha.equals("1"))
									 {
										 nomeRelat = "HistoricoPagamentoExameVendasDataInicioFimPago"; 
										 
										 if(!pagos.isEmpty() && pagos.equals("1") && !exame_hp.isEmpty() && exame_hp.equals("0"))
											 nomeRelat = "HistoricoPagamentoExameVendasDataInicioFimPagoCash";
										 
										 if(!pagos.isEmpty() && pagos.equals("2") && !exame_hp.isEmpty() && exame_hp.equals("0"))
											 nomeRelat = "HistoricoPagamentoExameVendasDataInicioFimPagoMulticaixa";
										 
										 if(pagos.isEmpty() && !exame_hp.isEmpty() && !exame_hp.equals("0"))
										 {
											 int exame = Integer.parseInt(exame_hp);
											 param.put("FK_exame_cima", exame);
											 nomeRelat = "HistoricoPagamentoExameVendasDataInicioFimPagoServico";
										 }
										 
										 if(!pagos.isEmpty() && pagos.equals("1") && !exame_hp.isEmpty() && !exame_hp.equals("0"))
										 {
											 int exame = Integer.parseInt(exame_hp);
											 param.put("FK_exame_cima", exame);
											 nomeRelat = "HistoricoPagamentoExameVendasDataInicioFimPagoCashServico";
										 }
										 if(!pagos.isEmpty() && pagos.equals("2") && !exame_hp.isEmpty() && !exame_hp.equals("0"))
										 {
											 int exame = Integer.parseInt(exame_hp);
											 param.put("FK_exame_cima", exame);
											 nomeRelat = "HistoricoPagamentoExameVendasDataInicioFimPagoMulticaixaServico";
										 }
								     }
									 
									 // CONSULTAS ISENTAS
									 if(!escolha.isEmpty() && escolha.equals("2"))
									 {
										 nomeRelat = "HistoricoPagamentoExameVendasDataInicioFimIsento"; 
										 
										 if(!isentos.isEmpty() && isentos.equals("1") && !exame_hp1.isEmpty() && exame_hp1.equals("0"))
											 nomeRelat = "HistoricoPagamentoExameVendasDataInicioFimIsentoIdade";
										 
										 if(!isentos.isEmpty() && isentos.equals("2") && !exame_hp1.isEmpty() && exame_hp1.equals("0"))
											 nomeRelat = "HistoricoPagamentoExameVendasDataInicioFimIsentoAtribuido";
										 
										 if(isentos.isEmpty() && !exame_hp1.isEmpty() && !exame_hp1.equals("0"))
										 {
											 int exame = Integer.parseInt(exame_hp1);
											 param.put("FK_exame_cima", exame);
											 nomeRelat = "HistoricoPagamentoExameVendasDataInicioFimIsentoServico";
										 }
										 if(!isentos.isEmpty() && isentos.equals("1") && !exame_hp1.isEmpty() && !exame_hp1.equals("0"))
										 {
											 int exame = Integer.parseInt(exame_hp1);
											 param.put("FK_exame_cima", exame);
											 nomeRelat = "HistoricoPagamentoExameVendasDataInicioFimIsentoIdadeServico";
										 }
										 if(!isentos.isEmpty() && isentos.equals("2") && !exame_hp1.isEmpty() && !exame_hp1.equals("0"))
										 {
											 int exame = Integer.parseInt(exame_hp1);
											 param.put("FK_exame_cima", exame);
											 nomeRelat = "HistoricoPagamentoExameVendasDataInicioFimIsentoAtribuidoServico";
										 }	 
								     }
									 
									 // PLANO DE SAï¿½DE
									 if(!escolha.isEmpty() && escolha.equals("3"))
									 {
										 nomeRelat = "HistoricoPagamentoExameVendasDataInicioFimPlanoSaude";  
										 
										 if(!exame_hp2.isEmpty() && !exame_hp2.equals("0"))
										 {
											 int exame = Integer.parseInt(exame_hp2);
											 param.put("FK_servico_cima", exame);
											 nomeRelat = "HistoricoPagamentoExameVendasDataInicioFimPlanoSaudeServico";
										 }
								     }
							 	}
						 	}
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida); 
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }

		 
		 // Historico Pagamentos de Teste de Qualidade
		 if(nomeRelat!=null && nomeRelat.equals("HistoricoPagamentoTesteQualidade"))
		 {
			 try
			 {
				 
				 if(nomeRelat!=null && nomeRelat.equals("HistoricoPagamentoTesteQualidade"))
				 {
					 param.put("logoImage", logoImage);
					 //param.put("usuario", usuario);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
					 gpdf.geraPdf(jrxml, param, saida);
				 }
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 
		 // Historico Pagamentos de SG
		 if(nomeRelat!=null && nomeRelat.equals("HistoricoPagamentoServicosGerais"))
		 {
			 try
			 {
				 
				 if(nomeRelat!=null && nomeRelat.equals("HistoricoPagamentoServicosGerais"))
				 {
					 param.put("logoImage", logoImage);
					 //param.put("usuario", usuario);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
					 gpdf.geraPdf(jrxml, param, saida);
				 }
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
		 
		 }
		 
		 // Historico Pagamentos de Produtos Vendas Hoje
		 if(nomeRelat!=null && nomeRelat.equals("HistoricoPagamentoProdutoVendasHoje"))
		 {
			
			 try
			 {
				 String data_ini = req.getParameter("data_ini");
				 String data_fim = req.getParameter("data_fim");
				 
				 String escolha = req.getParameter("escolha");
				 
				 String pagos = req.getParameter("s_pagos");
				 String isentos = req.getParameter("n_pagos");
				 
				 
				 String produto_hp = req.getParameter("produto_hp");
				 String produto_hp1 = req.getParameter("produto_hp1");
				 String produto_hp2 = req.getParameter("produto_hp2");
				 String testeNome = req.getParameter("teste");
				 System.out.println("Controller Relatï¿½rio: "+testeNome);
				 
				 if(!data_ini.isEmpty())
				 {
					 data_ini = new Formatando().dataBanco(data_ini);
					 if(!data_fim.isEmpty())
					 {
						 data_fim = new Formatando().dataBanco(data_fim);
						    
							 param.put("data_inicio_cima", data_ini);
							 param.put("data_fim_cima", data_fim);
							 nomeRelat = "HistoricoPagamentoProdutoVendasDataInicioFim";
							 
							 // PRODUTOS PAGOS
							 if(!escolha.isEmpty() && escolha.equals("1"))
							 {
								 nomeRelat = "HistoricoPagamentoProdutoVendasDataInicioFimPago"; 
								 
								 if(!pagos.isEmpty() && pagos.equals("1") && !produto_hp.isEmpty() && produto_hp.equals("0"))
									 nomeRelat = "HistoricoPagamentoProdutoVendasDataInicioFimPagoCash";
								 
								 if(!pagos.isEmpty() && pagos.equals("2") && !produto_hp.isEmpty() && produto_hp.equals("0"))
									 nomeRelat = "HistoricoPagamentoProdutoVendasDataInicioFimPagoMulticaixa";
								 
								 if(pagos.isEmpty() && !produto_hp.isEmpty() && !produto_hp.equals("0"))
								 {
									 int produto = Integer.parseInt(produto_hp);
									 param.put("FK_servico_cima", produto);
									 nomeRelat = "HistoricoPagamentoProdutoVendasDataInicioFimPagoProduto";
								 }
								 
								 if(!pagos.isEmpty() && pagos.equals("1") && !produto_hp.isEmpty() && !produto_hp.equals("0"))
								 {
									 int produto = Integer.parseInt(produto_hp);
									 param.put("FK_servico_cima", produto);
									 nomeRelat = "HistoricoPagamentoProdutoVendasDataInicioFimPagoCashProduto";
								 }
								 if(!pagos.isEmpty() && pagos.equals("2") && !produto_hp.isEmpty() && !produto_hp.equals("0"))
								 {
									 int produto = Integer.parseInt(produto_hp);
									 param.put("FK_servico_cima", produto);
									 nomeRelat = "HistoricoPagamentoProdutoVendasDataInicioFimPagoMulticaixaProduto";
								 }
						     }
							 
							 // PRODUTOS ISENTOS
							 if(!escolha.isEmpty() && escolha.equals("2"))
							 {
								 nomeRelat = "HistoricoPagamentoProdutoVendasDataInicioFimIsento"; 
								 
								 if(!isentos.isEmpty() && isentos.equals("1") && !produto_hp1.isEmpty() && produto_hp1.equals("0"))
									 nomeRelat = "HistoricoPagamentoProdutoVendasDataInicioFimIsentoIdade";
								 
								 if(!isentos.isEmpty() && isentos.equals("2") && !produto_hp1.isEmpty() && produto_hp1.equals("0"))
									 nomeRelat = "HistoricoPagamentoProdutoVendasDataInicioFimIsentoAtribuido";
								 
								 if(isentos.isEmpty() && !produto_hp1.isEmpty() && !produto_hp1.equals("0"))
								 {
									 int produto = Integer.parseInt(produto_hp1);
									 param.put("FK_servico_cima", produto);
									 nomeRelat = "HistoricoPagamentoProdutoVendasDataInicioFimIsentoProduto";
								 }
								 if(!isentos.isEmpty() && isentos.equals("1") && !produto_hp1.isEmpty() && !produto_hp1.equals("0"))
								 {
									 int produto = Integer.parseInt(produto_hp1);
									 param.put("FK_servico_cima", produto);
									 nomeRelat = "HistoricoPagamentoProdutoVendasDataInicioFimIsentoIdadeProduto";
								 }
								 if(!isentos.isEmpty() && isentos.equals("2") && !produto_hp1.isEmpty() && !produto_hp1.equals("0"))
								 {
									 int produto = Integer.parseInt(produto_hp1);
									 param.put("FK_servico_cima", produto);
									 nomeRelat = "HistoricoPagamentoProdutoVendasDataInicioFimIsentoAtribuidoProduto";
								 }	 
						     }
							 
							 // PLANO DE SAï¿½DE
							 if(!escolha.isEmpty() && escolha.equals("3"))
							 {
								 nomeRelat = "HistoricoPagamentoProdutoVendasDataInicioFimPlanoSaude";  
								 
								 if(!produto_hp2.isEmpty() && !produto_hp2.equals("0"))
								 {
									 int produto = Integer.parseInt(produto_hp2);
									 param.put("FK_servico_cima", produto);
									 nomeRelat = "HistoricoPagamentoProdutoVendasDataInicioFimPlanoSaudeProduto";
								 }
						     }
					 	}
				 	}
					 param.put("logoImage", logoImage);
					 //param.put("usuario", usuario);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
					 gpdf.geraPdf(jrxml, param, saida); 
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 
			// Gerar para devoluï¿½ï¿½o de produtos
		 if(nomeRelat!=null && nomeRelat.equals("DevolucaoPagamentoProdutoA4")){
			 try{
				 
				 if(nomeRelat!=null && nomeRelat.equals("DevolucaoPagamentoProdutoA4") && cod_param!=null && !cod_param.equals("")){
					 param.put("id_factura", Integer.parseInt(cod_param));
					 param.put("logoImage", logoImage);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
					  gpdf.geraPdf(jrxml, param, saida);
				 }
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 
		 // Gerar para agendar ou fazer exame agora
		 if(nomeRelat!=null && nomeRelat.equals("exame"))
		 {
			 try{
				 String origem = req.getParameter("origem");
				 if(origem!=null && origem.equals("agenda") && cod_param!=null && !cod_param.equals("")){
					 param.put("id_exame_servico", Integer.parseInt(cod_param));
					 param.put("logoImage", logoImage);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/agendarexameA5.jrxml");
					  gpdf.geraPdf(jrxml, param, saida);
				 }
				 else  if(origem!=null && origem.equals("ok") && cod_param!=null && !cod_param.equals("")){
					 param.put("id_requisicao", Integer.parseInt(cod_param));
					 param.put("logoImage", logoImage);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/ReqExameA5.jrxml");
					  gpdf.geraPdf(jrxml, param, saida);
				 }
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 
		 //para o agendar Teste de qualidade
		 if(nomeRelat!=null && nomeRelat.equals("exameTQ"))
		 {
			 try{
				 String origem = req.getParameter("origem");
				 if(origem!=null && origem.equals("agendaTQ") && cod_param!=null && !cod_param.equals("")){
					 param.put("id_exame_servico", Integer.parseInt(cod_param));
					 param.put("logoImage", logoImage);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/agendarexameA5.jrxml");
					  gpdf.geraPdf(jrxml, param, saida);
				 }
				 else  if(origem!=null && origem.equals("ok") && cod_param!=null && !cod_param.equals("")){
					 param.put("id_requisicao", Integer.parseInt(cod_param));
					 param.put("logoImage", logoImage);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/ReqExameA5.jrxml");
					  gpdf.geraPdf(jrxml, param, saida);
				 }
			 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
		 
		// Gerar para pagamento exame
		if(nomeRelat!=null && nomeRelat.equals("PagamentoExameA5"))
		{
			try{
						 
				 if(nomeRelat!=null && nomeRelat.equals("PagamentoExameA5") && cod_param!=null && !cod_param.equals(""))
				 {
					 double troco = 0;
					 if(cod_troco!=null)
					 troco = Double.parseDouble(cod_troco);
					 param.put("numero_factura", cod_param);
					 param.put("troco", troco);
					 param.put("total_recebido", Double.parseDouble(cod_total_recebido));
					 param.put("logoImage", logoImage);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
					  gpdf.geraPdf(jrxml, param, saida);
				 }
			 }
			 catch(Exception e){
				 e.printStackTrace();
				 }
			}
	
		// Pagamento Seguro Exame
		if(nomeRelat!=null && nomeRelat.equals("pagExamePlanoSaudeA5"))
		{
			try
			{
				req.getSession().removeAttribute("cash");
				req.getSession().removeAttribute("multicaixa");
				req.getSession().removeAttribute("credito");
						
						
				req.getSession().removeAttribute("troco");
				req.getSession().removeAttribute("total_recebido");
				req.getSession().removeAttribute("pacientepg");
				req.getSession().removeAttribute("confirm");
								 
				if(nomeRelat!=null && nomeRelat.equals("pagExamePlanoSaudeA5") && cod_param!=null && !cod_param.equals(""))
				{
					param.put("numero_factura", cod_param);
					param.put("troco", Double.parseDouble(cod_troco));
					param.put("total_recebido", Double.parseDouble(cod_total_recebido));
							
					param.put("cash", Util.retornaQuantiaPaga(cod_param, "cash"));
					param.put("multicaixa", Util.retornaQuantiaPaga(cod_param, "multicaixa"));
					param.put("credito", Util.retornaQuantiaPaga(cod_param, "credito"));
					param.put("logoImage", logoImage);
							
					String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
					gpdf.geraPdf(jrxml, param, saida);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		 
			// Gerar para pagamento exame A4
			 if(nomeRelat!=null && nomeRelat.equals("PagamentoExameA4"))
			 {
				 try{
						 
					 if(nomeRelat!=null && nomeRelat.equals("PagamentoExameA4") && cod_param!=null && !cod_param.equals("")){
					 param.put("numero_factura", cod_param);
					 param.put("logo", logoImage);
					 param.put("usuario", usuario);
					 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
					 gpdf.geraPdf(jrxml, param, saida);
					}
				 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
			// Gerar Pagamento Exame PlanoA4
			 if(nomeRelat!=null && nomeRelat.equals("PagamentoExamePlanoA4"))
			 {
				 try{
					 
					 if(nomeRelat!=null && nomeRelat.equals("PagamentoExamePlanoA4") && cod_param!=null && !cod_param.equals("")){
						 param.put("numero_factura", cod_param);
						 param.put("logo", logoImage);
						 param.put("usuario", usuario);
						 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
						  gpdf.geraPdf(jrxml, param, saida);
					 }
				 }
				 catch(Exception e){
					 e.printStackTrace();
				 }
			 }
			 
				 
			// Gerar para pagamento teste de qualidade
if(nomeRelat!=null && nomeRelat.equals("PagamentoTesteQualidade"))
{
	try{
				 
		 if(nomeRelat!=null && nomeRelat.equals("PagamentoTesteQualidade") && cod_param!=null && !cod_param.equals(""))
		 {
			 
			 double troco = 0;
			 if(cod_troco!=null)
			 troco = Double.parseDouble(cod_troco);
			 param.put("numero_factura", cod_param);
			 param.put("troco", troco);
			 param.put("total_recebido", Double.parseDouble(cod_total_recebido));
			 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
			 gpdf.geraPdf(jrxml, param, saida);
		 }
	 }
	 catch(Exception e){
		 e.printStackTrace();
		 }
	}
 
//Gerar para pagamento teste de qualidade A4
	 if(nomeRelat!=null && nomeRelat.equals("PagamentoTesteQualidadeA4"))
	 {
		 try{
				 
			 if(nomeRelat!=null && nomeRelat.equals("PagamentoTesteQualidadeA4") && cod_param!=null && !cod_param.equals("")){
			 param.put("numero_factura", cod_param);
			 param.put("logo", logoImage);
			 param.put("usuario", usuario);
			 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
			 gpdf.geraPdf(jrxml, param, saida);
			}
		 }
			 catch(Exception e){
				 e.printStackTrace();
			 }
		 }
			 
		// Gerar para devoluï¿½ï¿½o de teste de qualidade
	 if(nomeRelat!=null && nomeRelat.equals("DevolucaoTesteQualidade"))
	 {
		 try{
			 
			 if(nomeRelat!=null && nomeRelat.equals("DevolucaoTesteQualidade") && cod_param!=null && !cod_param.equals("")){
				 param.put("id_devolucao_venda", Integer.parseInt(cod_param));
				 JOptionPane.showMessageDialog(null, "recebi: "+cod_param);
				 param.put("logoImage", logoImage);
				 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
				  gpdf.geraPdf(jrxml, param, saida);
			 }
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 
		// Gerar para DevolucaoConsulta
	 if(nomeRelat!=null && nomeRelat.equals("DevolucaoPagamentoConsultaA4"))
	 {
		 try{
			 
			 if(nomeRelat!=null && nomeRelat.equals("DevolucaoPagamentoConsultaA4") && cod_param!=null && !cod_param.equals("")){
				 param.put("numero_devolucao", cod_param);
				 param.put("logoImage", logoImage);
				 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
				  gpdf.geraPdf(jrxml, param, saida);
			 }
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 
				// Gerar para devoluï¿½ï¿½o de exames pagos pagamento 
				 if(nomeRelat!=null && nomeRelat.equals("DevolucaoPagamentoExameA4")){
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("DevolucaoPagamentoExameA4") && cod_param!=null && !cod_param.equals("")){
							 param.put("id_factura", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				// Gerar para pagamentos de exames cancelados
				 if(nomeRelat!=null && nomeRelat.equals("PagamentoExameCanceladoA4"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("PagamentoExameCanceladoA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("numero_do_exame", cod_param);
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				// Gerar para pagamentos de consultas canceladas
				 if(nomeRelat!=null && nomeRelat.equals("PagamentoConsultaCanceladaA4"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("PagamentoConsultaCanceladaA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("id_consulta_cancelada", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 
				// Gerar para Factura Resumida
				 if(nomeRelat!=null && nomeRelat.equals("FacturaResumida")){
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("FacturaResumida") && cod_param!=null && !cod_param.equals("")){
							 param.put("numero_factura", cod_param);
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				// Gerar para Factura Resumida Consulta
				 if(nomeRelat!=null && nomeRelat.equals("FacturaResumidaConsulta")){
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("FacturaResumidaConsulta") && cod_param!=null && !cod_param.equals("")){
							 param.put("numero_factura", cod_param);
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				// Gerar para Factura Resumida Serviï¿½os gerais 
				 if(nomeRelat!=null && nomeRelat.equals("FacturaResumidaServicosGerais")){
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("FacturaResumidaServicosGerais") && cod_param!=null && !cod_param.equals("")){
							 param.put("numero_factura", cod_param);
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				// Gerar para Factura Resumida Produtos
				 if(nomeRelat!=null && nomeRelat.equals("FacturaResumidaProduto")){
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("FacturaResumidaProduto") && cod_param!=null && !cod_param.equals("")){
							 param.put("numero_factura", cod_param);
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				// Gerar para Factura Resumida
				 if(nomeRelat!=null && nomeRelat.equals("AtendidosLaboratorio")){
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("AtendidosLaboratorio") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("fk_requisicao_exame", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				// Gerar Lista de Pacientes Atendidos no Laboratorio
				 if(nomeRelat!=null && nomeRelat.equals("AtendidosLaboratorioLista"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("AtendidosLaboratorioLista") /*&& cod_param!=null && !cod_param.equals("")*/)
						 {
							 //param.put("fk_factura", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				// Gerar Dados da Instituiï¿½ï¿½o
				 if(nomeRelat!=null && nomeRelat.equals("DadosInstituicao"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("DadosInstituicao"))
						 {
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				// Gerar Folha de Caixa
				 
				 if(nomeRelat!=null && nomeRelat.equals("FolhadeCaixa"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("FolhadeCaixa") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("id_fecho_decaixa", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 
				// Gerar Folha de Caixa Detalhada
				 
				 if(nomeRelat!=null && nomeRelat.equals("FolhadeCaixaDetalhada"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("FolhadeCaixaDetalhada") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("abertura_decaixa", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 // Gerar Fluxo de Caixa
				 
				 if(nomeRelat!=null && nomeRelat.equals("FluxodeCaixa"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("FluxodeCaixa") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("id_fluxo", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				// Tipos de Isenï¿½ï¿½es
				 if(nomeRelat!=null && nomeRelat.equals("Isencao"))
				 {
					String origem = req.getParameter("origem");
					 try{
						 
						 if(origem!=null && origem.equals("consulta") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("id_isencao", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/IsencaoConsulta.jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
						 // isenï¿½ï¿½o exame
						 if(origem!=null && origem.equals("exame") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("id_isencao", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/IsencaoExames.jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
						 // isenï¿½ï¿½o farmaco
						 if(origem!=null && origem.equals("farm") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("id_isencao", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/IsencaoFarmaco.jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
						 // isenï¿½ï¿½o Servicos Gerais
						 if(origem!=null && origem.equals("tratm") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("id_isencao", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/IsencaoServicosGerais.jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
						 // isenï¿½ï¿½o receita
						 if(origem!=null && origem.equals("receita") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("id_isencao", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/IsencaoReceita.jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
						 
						 
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
		 
				  //Gerar Isenï¿½ï¿½o Consulta Modal
				 if(nomeRelat!=null && nomeRelat.equals("IsencaoConsulta"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("IsencaoConsulta") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("id_isencao", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 //Gerar Isenï¿½ï¿½o Exames Modal
				 if(nomeRelat!=null && nomeRelat.equals("IsencaoExames"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("IsencaoExames") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("id_isencao", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 //Gerar Isenï¿½ï¿½o Servicos Gerais Modal
				 if(nomeRelat!=null && nomeRelat.equals("IsencaoServicosGerais"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("IsencaoServicosGerais") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("id_isencao", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 //Gerar Isenï¿½ï¿½o Farmaco Modal
				 if(nomeRelat!=null && nomeRelat.equals("IsencaoFarmaco"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("IsencaoFarmaco") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("id_isencao", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 //Gerar Isenï¿½ï¿½o Receita Modal
				 if(nomeRelat!=null && nomeRelat.equals("IsencaoReceita"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("IsencaoReceita") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("id_isencao", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 // Gerar Historico Isencoes Diaria - Detalhado
				 if(nomeRelat!=null && nomeRelat.equals("HistoricoIsencoesDiaria")){
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("HistoricoIsencoesDiaria"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 // Gerar Historico Isencoes Diaria - Resumido
				 if(nomeRelat!=null && nomeRelat.equals("HistoricoIsencoesDiariaResumido")){
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("HistoricoIsencoesDiariaResumido"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				// Gerar Historico Isencoes Em diferentes datas - Detalhado
				 if(nomeRelat!=null && nomeRelat.equals("HistoricoIsencoesGeral"))
				 {
					 try{
						 String data1 = req.getParameter("data1");
						 String data2 = req.getParameter("data2");
						 if(!data1.trim().equals(""))
							 data1 = new Formatando().dataBanco(data1);
						 
						 if(!data2.trim().equals(""))
							 data2 = new Formatando().dataBanco(data2);
						 else
							 data2 = new Formatando().dataBanco(new Formatando().data_str());
						 
						 if(nomeRelat!=null && nomeRelat.equals("HistoricoIsencoesGeral"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 param.put("data_inicio", data1);
							 param.put("data_fim", data2);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				  
					// Gerar Historico Isencoes Em diferentes datas - Resumido
				 if(nomeRelat!=null && nomeRelat.equals("HistoricoIsencoesGeralResumido"))
				 {
					 try{
						 String data1 = req.getParameter("data1");
						 String data2 = req.getParameter("data2");
						 if(!data1.trim().equals(""))
							 data1 = new Formatando().dataBanco(data1);
						 
						 if(!data2.trim().equals(""))
							 data2 = new Formatando().dataBanco(data2);
						 else
							 data2 = new Formatando().dataBanco(new Formatando().data_str());
						 
						 if(nomeRelat!=null && nomeRelat.equals("HistoricoIsencoesGeralResumido"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 param.put("data_inicio", data1);
							 param.put("data_fim", data2);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 
				 
				// Gerar para pagamento produto
				 if(nomeRelat!=null && nomeRelat.equals("PagamentoProduto"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("PagamentoProduto") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("numero_factura", cod_param);
							 
							 double troco = 0;
							 double total_recebido =0;
							 if(cod_troco!=null && !cod_troco.equals(""))
							 troco = Double.parseDouble(cod_troco);
							 param.put("troco", troco);
							 total_recebido = Double.parseDouble(cod_total_recebido);
							 param.put("total_recebido", total_recebido);

							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				// Gerar plano de saude produto
				 if(nomeRelat!=null && nomeRelat.equals("PagamentoProdutoPlanoSaude"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("PagamentoProdutoPlanoSaude") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("numero_factura", cod_param);
							 
							 double troco = 0;
							 double total_recebido =0;
							 if(cod_troco!=null && !cod_troco.equals(""))
							 troco = Double.parseDouble(cod_troco);
							 param.put("troco", troco);
							 total_recebido = Double.parseDouble(cod_total_recebido);
							 param.put("total_recebido", total_recebido);

							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
		 
				// Gerar para pagamento produto A4
				 if(nomeRelat!=null && nomeRelat.equals("PagamentoProdutoA4"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("PagamentoProdutoA4") && cod_param!=null && !cod_param.equals("")){
							 param.put("numero_factura", cod_param);
							 param.put("logo", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
					// Gerar Pagamento Servicos Gerais
				 if(nomeRelat!=null && nomeRelat.equals("PagamentoServicosGeraisA5"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("PagamentoServicosGeraisA5") && cod_param!=null && !cod_param.equals("")){
							 param.put("numero_factura", cod_param);
							 double troco = 0;
							 double total_recebido =0;
							 if(cod_troco!=null && !cod_troco.equals(""))
							 troco = Double.parseDouble(cod_troco);
							 total_recebido = Double.parseDouble(cod_total_recebido);
							 param.put("troco", troco);
							 param.put("total_recebido", total_recebido);
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				// Pagamento Seguro Servicos Gerais
					if(nomeRelat!=null && nomeRelat.equals("PagServicosGeraisPlanoSaudeA5"))
					{
						try
						{
							req.getSession().removeAttribute("cash");
							req.getSession().removeAttribute("multicaixa");
							req.getSession().removeAttribute("credito");
									
									
							req.getSession().removeAttribute("troco");
							req.getSession().removeAttribute("total_recebido");
							req.getSession().removeAttribute("pacientepg");
							req.getSession().removeAttribute("confirm");
											 
							if(nomeRelat!=null && nomeRelat.equals("PagServicosGeraisPlanoSaudeA5") && cod_param!=null && !cod_param.equals(""))
							{
								param.put("numero_factura", cod_param);
								param.put("troco", Double.parseDouble(cod_troco));
								param.put("total_recebido", Double.parseDouble(cod_total_recebido));
										
								param.put("cash", Util.retornaQuantiaPaga(cod_param, "cash"));
								param.put("multicaixa", Util.retornaQuantiaPaga(cod_param, "multicaixa"));
								param.put("credito", Util.retornaQuantiaPaga(cod_param, "credito"));
								param.put("logoImage", logoImage);
										
								String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
								gpdf.geraPdf(jrxml, param, saida);
							}
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
				 
				// Gerar Pagamento Servicos Gerais A4
				 if(nomeRelat!=null && nomeRelat.equals("PagamentoServicosGeraisA4"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("PagamentoServicosGeraisA4") && cod_param!=null && !cod_param.equals("")){
							 param.put("numero_factura", cod_param);
							 param.put("logo", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				// Gerar Pagamento Servicos Gerais PlanoA4
				 if(nomeRelat!=null && nomeRelat.equals("PagamentoServicosGeraisPlanoA4"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("PagamentoServicosGeraisPlanoA4") && cod_param!=null && !cod_param.equals("")){
							 param.put("numero_factura", cod_param);
							 param.put("logo", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
					// Gerar Devolucao Pagamento Servicos Gerais 
				 if(nomeRelat!=null && nomeRelat.equals("DevolucaoPagamentoServicosGeraisA4")){
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("DevolucaoPagamentoServicosGeraisA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("FK_devolucao_venda", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				//rel internamento do paciente
				 if(nomeRelat!=null && nomeRelat.equals("FichaInternamentoPaciente"))
				 {
					 try{
						 if(nomeRelat!=null && nomeRelat.equals("FichaInternamentoPaciente") && cod_param!=null && !cod_param.equals("")){
							 param.put("FK_internamento", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				// HistoricoPacientesInternados
				 if(nomeRelat!=null && nomeRelat.equals("HistoricoPacientesInternados"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("HistoricoPacientesInternados"))
						 {
							 param.put("logoImage", logoImage);
							 //param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				//rel alta do paciente
				 if(nomeRelat!=null && nomeRelat.equals("FichaAltaPaciente"))
				 {
					 try{
						 if(nomeRelat!=null && nomeRelat.equals("FichaAltaPaciente") && cod_param!=null && !cod_param.equals("")){
							 param.put("FK_alta", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				// HistoricoPacientesAlta
				 if(nomeRelat!=null && nomeRelat.equals("HistoricoPacientesAlta"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("HistoricoPacientesAlta"))
						 {
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }

					//Gerar relReceituarioConsulta
				 if(nomeRelat!=null && nomeRelat.equals("ReceituarioConsultaA5"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("ReceituarioConsultaA5") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("id_consulta", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							// param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }

				//Gerar relExamesClinicos
				 if(nomeRelat!=null && nomeRelat.equals("ExamesClinicosConsultaA5"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("ExamesClinicosConsultaA5") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("id_consulta", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							// param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				//Gerar relTratamentoMedicoConsulta
				 if(nomeRelat!=null && nomeRelat.equals("TratamentoMedicoConsultaA5"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("TratamentoMedicoConsultaA5") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("id_consulta", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							// param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
					//Gerar relRecomendacoesMedicoConsulta
				 if(nomeRelat!=null && nomeRelat.equals("RecomendacoesMedicoConsultaA4"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("RecomendacoesMedicoConsultaA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("id_consulta", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							// param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 // rel JustificativoMedicoConsulta
				 if(nomeRelat!=null && nomeRelat.equals("JustificativoMedicoConsultaA5"))
				 {
					 try{
						
						 if(nomeRelat!=null && nomeRelat.equals("JustificativoMedicoConsultaA5") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("FK_consulta", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 // Relatorio de Atendimento Mï¿½dico na Consulta
				 if(nomeRelat!=null && nomeRelat.equals("FichaMedicoConsulta"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("FichaMedicoConsulta") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("cod_consulta", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 // Relatorio de todas consultas do paciente
				 
				 if(nomeRelat!=null && nomeRelat.equals("FichaAtendimentoGeral"))
				 {
					 try{
						 String data_p = req.getParameter("data1");
						 String data_s = req.getParameter("data2");
						 if(nomeRelat!=null && nomeRelat.equals("FichaAtendimentoGeral") && cod_param!=null && !cod_param.equals(""))
						 {
							 Date data1 = new Formatando().dataSql(data_p);
							 Date data2 = new Formatando().dataSql(data_s);
							 param.put("fk_paciente", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 param.put("data_inicio", data1);
							 param.put("data_fim", data2);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 // EncomendasProdutosRecebidosArmazemA4
				 if(nomeRelat!=null && nomeRelat.equals("EncomendasProdutosRecebidosArmazemA4"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("EncomendasProdutosRecebidosArmazemA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("cod_encomenda", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage); 
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				 // Requisiï¿½ï¿½es Recebida
				 if(nomeRelat!=null && nomeRelat.equals("RequisicoesRecebidasA4"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("RequisicoesRecebidasA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("FK_num_encomenda", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage); 
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				 // Perfil do Produto
				 if(nomeRelat!=null && nomeRelat.equals("PerfilProduto"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("PerfilProduto") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("id_produto", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage); 
							 param.put("logoProduto", imgProd);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				// Farmacia do do Inventï¿½rio
				 
				 if(nomeRelat!=null && nomeRelat.equals("ListaInventarioStockFarmaciaA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ListaInventarioStockFarmaciaA4"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 // Farmacia ListaProdutosStockArmazemA4
				 
				 if(nomeRelat!=null && nomeRelat.equals("ListaProdutosStockArmazemA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ListaProdutosStockArmazemA4"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 // ProdutosEncomendadosArmazemA4
				 if(nomeRelat!=null && nomeRelat.equals("ProdutosEncomendadosArmazemA4"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("ProdutosEncomendadosArmazemA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("numero_encomenda", cod_param);
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				 // Produtos Declinados
				 if(nomeRelat!=null && nomeRelat.equals("ProdutosRequisicaoDeclinadasA4"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("ProdutosRequisicaoDeclinadasA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("numero_requisicao", cod_param);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				 // ListaEncomendasArmazemA4
				 if(nomeRelat!=null && nomeRelat.equals("ListaEncomendasArmazemA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ListaEncomendasArmazemA4"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				 // Historico de Ajustes A4
				 if(nomeRelat!=null && nomeRelat.equals("HistoricodeAjustesA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("HistoricodeAjustesA4"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				 // Produtos Ajustados
				 if(nomeRelat!=null && nomeRelat.equals("ProdutosAjustadosA4"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("ProdutosAjustadosA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("numero_requi_interna", cod_param);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				 //ListaInventarioStockGeralA4
				 
				 if(nomeRelat!=null && nomeRelat.equals("ListaInventarioStockGeralA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ListaInventarioStockGeralA4"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 param.put("requerente", requerente);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 //  RequisicoesFeitasGeralA4
				 if(nomeRelat!=null && nomeRelat.equals("RequisicoesFeitasGeralA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("RequisicoesFeitasGeralA4"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 param.put("requerente", requerente);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				// RequisicoesFeitasGeralProdutosA5
				 if(nomeRelat!=null && nomeRelat.equals("RequisicoesFeitasGeralProdutosA5"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("RequisicoesFeitasGeralProdutosA5") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("numero_requisicao", cod_param);
							 param.put("requerente", requerente);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				//  ReceberRequisicaoGeralA4
				 if(nomeRelat!=null && nomeRelat.equals("ReceberRequisicaoGeralA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ReceberRequisicaoGeralA4"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 param.put("requerente", requerente);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				//  RequisicoesRecebidasFarmaciaA4
				 if(nomeRelat!=null && nomeRelat.equals("RequisicoesRecebidasFarmaciaA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("RequisicoesRecebidasFarmaciaA4"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 param.put("requerente", requerente);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				// RequisicoesRecebidasFarmaciaProdutoA4
				 if(nomeRelat!=null && nomeRelat.equals("RequisicoesRecebidasFarmaciaProdutoA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("RequisicoesRecebidasFarmaciaProdutoA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("numero_requisicao", cod_param);
							 param.put("requerente", requerente);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				//  RequisicoesRecebidasGeralA4
				 if(nomeRelat!=null && nomeRelat.equals("RequisicoesRecebidasGeralA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("RequisicoesRecebidasGeralA4"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 param.put("requerente", requerente);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				// RequisicoesRecebidasGeralProdutosA4
				 if(nomeRelat!=null && nomeRelat.equals("RequisicoesRecebidasGeralProdutosA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("RequisicoesRecebidasGeralProdutosA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("numero_requisicao", cod_param);
							 param.put("requerente", requerente);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				//ListaProdutosExpirarGeralA4
				 if(nomeRelat!=null && nomeRelat.equals("ListaProdutosExpirarGeralA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ListaProdutosExpirarGeralA4"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 param.put("requerente", requerente);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				//DevolucoesEfectuadasGeralA4
				 if(nomeRelat!=null && nomeRelat.equals("DevolucoesEfectuadasGeralA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("DevolucoesEfectuadasGeralA4"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 param.put("requerente", requerente);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				// DevolucoesEfectuadasGeralProdutosA4
				 if(nomeRelat!=null && nomeRelat.equals("DevolucoesEfectuadasGeralProdutosA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("DevolucoesEfectuadasGeralProdutosA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("numero_referencia", cod_param);
							 param.put("requerente", requerente);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				 //  RequisicoesFeitasFarmaciaA4
				 if(nomeRelat!=null && nomeRelat.equals("RequisicoesFeitasFarmaciaA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("RequisicoesFeitasFarmaciaA4"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				// RequisicoesFeitasFarmaciaProdutosA5
				 if(nomeRelat!=null && nomeRelat.equals("RequisicoesFeitasFarmaciaProdutosA5"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("RequisicoesFeitasFarmaciaProdutosA5") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("numero_requisicao", cod_param);
							 param.put("requerente", requerente);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				// ReceberRequisicaoGeralProdutosA4
				 if(nomeRelat!=null && nomeRelat.equals("ReceberRequisicaoGeralProdutosA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ReceberRequisicaoGeralProdutosA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("numero_requisicao", cod_param);
							 param.put("requerente", requerente);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				 
				// ProdutosRequisitadosA4
				 if(nomeRelat!=null && nomeRelat.equals("ProdutosRequisitadosA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ProdutosRequisitadosA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("numero_requisicao", cod_param);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				 //  RequisicoesAtendidasArmazemA4
				 if(nomeRelat!=null && nomeRelat.equals("RequisicoesAtendidasArmazemA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("RequisicoesAtendidasArmazemA4"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				 //  RequisicoesDeclinadasArmazemA4
				 if(nomeRelat!=null && nomeRelat.equals("RequisicoesDeclinadasArmazemA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("RequisicoesDeclinadasArmazemA4"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				//ProdutosRequisicaoAtendidaArmazemA4
				 if(nomeRelat!=null && nomeRelat.equals("ProdutosRequisicaoAtendidaArmazemA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ProdutosRequisicaoAtendidaArmazemA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("numero_requisicao", cod_param);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 // ProdutosRequisicaoAtendidaProcessadaArmazemA5
				 if(nomeRelat!=null && nomeRelat.equals("ProdutosRequisicaoAtendidaProcessadaArmazemA5"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ProdutosRequisicaoAtendidaProcessadaArmazemA5") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("numero_requisicao", cod_param);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 //ProdutosRequisicaoAtendidaRequisicaoArmazemA4
				 if(nomeRelat!=null && nomeRelat.equals("ProdutosRequisicaoAtendidaRequisicaoArmazemA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ProdutosRequisicaoAtendidaRequisicaoArmazemA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("numero_requisicao", cod_param);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				 // HistoricoDevolucoesRequisicaoFarmaciaA4
				 if(nomeRelat!=null && nomeRelat.equals("HistoricoDevolucoesRequisicaoFarmaciaA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("HistoricoDevolucoesRequisicaoFarmaciaA4"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				 // ListaProdutosExpirarFarmaciaA4
				 if(nomeRelat!=null && nomeRelat.equals("ListaProdutosExpirarFarmaciaA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ListaProdutosExpirarFarmaciaA4"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				// ListaProdutosExpirarArmazemA4
				 if(nomeRelat!=null && nomeRelat.equals("ListaProdutosExpirarArmazemA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ListaProdutosExpirarArmazemA4"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				// ProdutosAjustadosFarmaciaA4
				 if(nomeRelat!=null && nomeRelat.equals("ProdutosAjustadosFarmaciaA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ProdutosAjustadosFarmaciaA4"))
						 {
							 param.put("numero_requisicao", cod_param);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				// ProdutosAjustadosArmazemA4
				 if(nomeRelat!=null && nomeRelat.equals("ProdutosAjustadosArmazemA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ProdutosAjustadosArmazemA4"))
						 {
							 param.put("numero_requisicao", cod_param);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				// ProdutosAjustadosLaboratorioA4
				 if(nomeRelat!=null && nomeRelat.equals("ProdutosAjustadosLaboratorioA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ProdutosAjustadosLaboratorioA4"))
						 {
							 param.put("numero_requisicao", cod_param);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				// ProdutosAjustadosImagiologiaA4
				 if(nomeRelat!=null && nomeRelat.equals("ProdutosAjustadosImagiologiaA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ProdutosAjustadosImagiologiaA4"))
						 {
							 param.put("numero_requisicao", cod_param);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				// ProdutosAjustadosVacinaA4
				 if(nomeRelat!=null && nomeRelat.equals("ProdutosAjustadosVacinaA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ProdutosAjustadosVacinaA4"))
						 {
							 param.put("numero_requisicao", cod_param);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				// ProdutosAjustadosInternamentoA4
				 if(nomeRelat!=null && nomeRelat.equals("ProdutosAjustadosInternamentoA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ProdutosAjustadosInternamentoA4"))
						 {
							 param.put("numero_requisicao", cod_param);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				// ProdutosAjustadosUtiA4
				 if(nomeRelat!=null && nomeRelat.equals("ProdutosAjustadosUtiA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ProdutosAjustadosUtiA4"))
						 {
							 param.put("numero_requisicao", cod_param);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				// ProdutosAjustadosBercarioA4
				 if(nomeRelat!=null && nomeRelat.equals("ProdutosAjustadosBercarioA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ProdutosAjustadosBercarioA4"))
						 {
							 param.put("numero_requisicao", cod_param);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				// ProdutosAjustadosBueA4
				 if(nomeRelat!=null && nomeRelat.equals("ProdutosAjustadosBueA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ProdutosAjustadosBueA4"))
						 {
							 param.put("numero_requisicao", cod_param);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				 
				 // Receber Requisiï¿½ï¿½o
				 if(nomeRelat!=null && nomeRelat.equals("ReceberRequisicaoFarmaciaA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ReceberRequisicaoFarmaciaA4"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 param.put("requerente", requerente);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				 // Produtos Por ReceberA4
				 if(nomeRelat!=null && nomeRelat.equals("ProdutosPorReceberFarmaciaA4"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("ProdutosPorReceberFarmaciaA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("numEncomenda", cod_param);
							 param.put("requerente", requerente);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				 // Historico de Pacientes Atendidos na Urgencia
				 if(nomeRelat!=null && nomeRelat.equals("HistoricoPacientesUrgencia"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("HistoricoPacientesUrgencia"))
						 {
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 // Historico de Pacientes Atendidos na Emergencia
				 if(nomeRelat!=null && nomeRelat.equals("HistoricoPacientesEmergencia"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("HistoricoPacientesEmergencia"))
						 {
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 // FichaAtendimentoEmergencia
				 if(nomeRelat!=null && nomeRelat.equals("FichaAtendimentoEmergencia"))
				 {
					 try{
						 if(nomeRelat!=null && nomeRelat.equals("FichaAtendimentoEmergencia") && cod_param!=null && !cod_param.equals("")){
							 param.put("id_emergencia", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 //param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 // FichaAtendimentoUrgencia
				 if(nomeRelat!=null && nomeRelat.equals("FichaAtendimentoUrgencia"))
				 {
					 try{
						 if(nomeRelat!=null && nomeRelat.equals("FichaAtendimentoUrgencia") && cod_param!=null && !cod_param.equals("")){
							 param.put("id_urgencia", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 //param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 

					//Gerar relReceituarioUrgencia
				 if(nomeRelat!=null && nomeRelat.equals("ReceituarioUrgencia"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("ReceituarioUrgencia") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("id_urgencia", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							// param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }

				//Gerar relExamesClinicosUrgencia	
				 if(nomeRelat!=null && nomeRelat.equals("ExamesClinicosUrgencia"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("ExamesClinicosUrgencia") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("id_urgencia", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							// param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				
					//Gerar relRecomendacoesMedicoUrgencia
				 if(nomeRelat!=null && nomeRelat.equals("RecomendacoesMedicoUrgencia"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("RecomendacoesMedicoUrgencia") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("id_urgencia", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							// param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 // Justificativo Urgencia
				 if(nomeRelat!=null && nomeRelat.equals("JustificativoMedicoUrgencia"))
				 {
					 try{
						
						 if(nomeRelat!=null && nomeRelat.equals("JustificativoMedicoUrgencia") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("FK_urgencia", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
					//Gerar Codigo de Barras
				 if(nomeRelat!=null && nomeRelat.equals("CodigoBarra"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("CodigoBarra"))
						 {
				 			    	int codf = Integer.parseInt(req.getParameter("codFact"));
				 			    	int codex = Integer.parseInt(req.getParameter("codEx"));
				 			    	int codfn = Integer.parseInt(req.getParameter("codfn"));
				 			    	int codBar = new LaboratorioDAO().xeckBarcode(codf, codex);
				 			    	if(codBar == 0)
				 			    		codBar = new LaboratorioDAO().novoBarCode(codf, codex, codfn);
				 			    	 
				 			System.out.println(codBar);    	
							 param.put("id_amostra", codBar);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/CodigoBarra.jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						  }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
//----------------------Estatistica--------------------Estatistica---------------------------------------------------------------------------------//
				 				 
//Consultas Externas
				 
				 
				 //Histï¿½rico de Atendimento Mï¿½dico
				 if(nomeRelat!=null && nomeRelat.equals("EstatisticaAtendimentoMedico"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("EstatisticaAtendimentoMedico"))
						 {
							 param.put("logoImage", logoImage);
							 //param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 				 
				 //	Histï¿½rico de Consultas Solicitadas
				 if(nomeRelat!=null && nomeRelat.equals("EstatisticaConsultasSolicitadas"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("EstatisticaConsultasSolicitadas"))
						 {
							 param.put("logoImage", logoImage);
							 //param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 // Histï¿½rico de Solicitacoes de Tratamentos
				 if(nomeRelat!=null && nomeRelat.equals("EstatisticaSolicitacoesTratamento"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("EstatisticaSolicitacoesTratamento"))
						 {
							 param.put("logoImage", logoImage);
							 //param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }

//Internamento				 
				 
				 //Histï¿½rico de Pacientes Internados
				 if(nomeRelat!=null && nomeRelat.equals("EstatisticaPacientesInternados"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("EstatisticaPacientesInternados"))
						 {
							 param.put("logoImage", logoImage);
							 //param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 //Histï¿½rico de Diagnosticos Frequentes
				 if(nomeRelat!=null && nomeRelat.equals("EstatisticaDiagnosticosFrequentes"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("EstatisticaDiagnosticosFrequentes"))
						 {
							 param.put("logoImage", logoImage);
							 //param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 //Histï¿½rico de Alta Mï¿½dica
				 if(nomeRelat!=null && nomeRelat.equals("EstatisticaAltaMedica"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("EstatisticaAltaMedica"))
						 {
							 param.put("logoImage", logoImage);
							 //param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 
//Pagamentos
			
				 //Pagamento Serviï¿½os
				 if(nomeRelat!=null && nomeRelat.equals("EstatisticaPagamentoServicos"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("EstatisticaPagamentoServicos"))
						 {
							 param.put("logoImage", logoImage);
							 //param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				
				//Pagamento Exames
				 if(nomeRelat!=null && nomeRelat.equals("FechodeCaixaFuncionario"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("FechodeCaixaFuncionario"))
						 {
							 String funcionario = req.getParameter("funcionario");
							 double total_mao = Double.parseDouble(req.getParameter("total_mao"));
							 double total_multicaixa = Double.parseDouble(req.getParameter("total_multicaixa"));
							 
							 param.put("funcionario", funcionario);
							 param.put("total_mao", total_mao);
							 param.put("total_multicaixa", total_multicaixa);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				//Pagamento Exames
				 if(nomeRelat!=null && nomeRelat.equals("EstatisticaPagamentoExames"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("EstatisticaPagamentoExames"))
						 {
							 param.put("logoImage", logoImage);
							 //param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				//Pagamento Serviï¿½os Gerais
				 if(nomeRelat!=null && nomeRelat.equals("EstatisticaPagamentoServicosGerais"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("EstatisticaPagamentoServicosGerais"))
						 {
							 param.put("logoImage", logoImage);
							 //param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				//Pagamento Produtos
				 if(nomeRelat!=null && nomeRelat.equals("EstatisticaPagamentoProdutosHoje"))
				 {
					 try
					 {
						 String ano = req.getParameter("ano");
						 String mes = req.getParameter("mes");
						 String dia = req.getParameter("dia");
						 String outro_dia = req.getParameter("outro_dia");
						 String opescolha = req.getParameter("opEscolha");
						 
						 if(nomeRelat!=null && nomeRelat.equals("EstatisticaPagamentoProdutosHoje"))
						 {
							 if( ano != null)
							 {
								 nomeRelat = "EstatisticaPagamentoProdutosAno";
								 param.put("ano_cima", ano);	  
							 }
							 
							 if( mes != null)
							 {
								 nomeRelat = "EstatisticaPagamentoProdutosAnoMes";
								 param.put("mes_cima", mes); 
							 }
							 if( dia != null)
							 {
								 nomeRelat = "EstatisticaPagamentoProdutosAnoMesDia";
								 param.put("dia_cima", dia); 
								 param.put("outro_dia_cima", dia); 
							 }
							 if( dia != null && outro_dia!=null)
							 {
								 nomeRelat = "EstatisticaPagamentoProdutosAnoMesDia";
								 param.put("dia_cima", dia); 
								 param.put("outro_dia_cima", outro_dia); 
							 }
							 
							 if( opescolha != null )
							 {
								 if(opescolha.equals("1"))
								 nomeRelat = "EstatisticaPagamentoProdutosAnoPrimeiroTrimestre";
								 if(opescolha.equals("2"))
									 nomeRelat = "EstatisticaPagamentoProdutosAnoSegundoTrimestre";
								 if(opescolha.equals("3"))
									 nomeRelat = "EstatisticaPagamentoProdutosAnoTerceiroTrimestre";
								 if(opescolha.equals("4"))
									 nomeRelat = "EstatisticaPagamentoProdutosAnoQuartoTrimestre";
								 if(opescolha.equals("5"))
									 nomeRelat = "EstatisticaPagamentoProdutosAnoPrimeiroSemestre";
								 if(opescolha.equals("6"))
									 nomeRelat = "EstatisticaPagamentoProdutosAnoSegundoSemestre";
							 }
							 
							 param.put("logoImage", logoImage);
							 //param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				//Pagamento Geral
				 if(nomeRelat!=null && nomeRelat.equals("EstatisticaPagamentoGeral"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("EstatisticaPagamentoGeral"))
						 {
							 param.put("logoImage", logoImage);
							 //param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				//Pagamento Desempenhos
				 if(nomeRelat!=null && nomeRelat.equals("EstatisticaPagamentoDesempenhos"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("EstatisticaPagamentoDesempenhos"))
						 {
							 param.put("logoImage", logoImage);
							 //param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				//Pagamento Balancos
				 if(nomeRelat!=null && nomeRelat.equals("EstatisticaPagamentoBalancos"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("EstatisticaPagamentoBalancos"))
						 {
							 param.put("logoImage", logoImage);
							 //param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
//----------------------Testes--------------------Testes---------------------------------------------------------------------------------//
				 
				 // teste de conversï¿½o para word
				 if(nomeRelat!=null && nomeRelat.equals("TesteWord")){
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("TesteWord"))
						 {
//							 param.put("FK_devolucao_venda", Integer.parseInt(cod_param));
//							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraWord(jrxml, param, saida);
							  
							  resp.setContentType("application/msword");
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 
				 if(nomeRelat!=null && nomeRelat.equals("HistoricoPagamentoConsulta"))
				 {
					 try{
						  
							   
							  ListaCarrinho	listaTabela = (ListaCarrinho) ss.getAttribute("listaTabelaEX");
							  List<Facturacao> servFaturados  = servFaturados = new RelatorioPagamentosDAO().getServicosFaturados();
							 
							  param.put("logoImage", logoImage);
							  param.put("nome_comercial", dadosEmp.getNome_comercial());
							  param.put("rua", dadosEmp.getRua());
							  param.put("bairro", dadosEmp.getBairro());
							  param.put("telf1", dadosEmp.getTelefone());
							  param.put("telf2", dadosEmp.getTelefones());
							  param.put("nif", dadosEmp.getNumContribuente());
							  param.put("email", dadosEmp.getEmail());
							  param.put("site", dadosEmp.getSite());
							  
							  
							  String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/HistoricoPagamentoConsultaBack.jrxml");
							  gpdf.geraPdfComLista(jrxml, param, saida,servFaturados); 
							 
						 
					    }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 // Historico Pagamentos de SG
				 if(nomeRelat!=null && nomeRelat.equals("HistoricoFacturasFecho"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("HistoricoFacturasFecho"))
						 {
							 param.put("id_fecho", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 param.put("FK_funcionario", Integer.parseInt(nomefunc));
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 
				 }
				 
				 // Tela Nota Cobrança - Relatorio Resumido
				 if(nomeRelat!=null && nomeRelat.equals("NotaCobrancaResumidaA4"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("NotaCobrancaResumidaA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("empresa_id", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				// Tela Nota de Cobrança - Relatorio Detalhado
				 if(nomeRelat!=null && nomeRelat.equals("NotaCobrancaDetalhadaA4"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("NotaCobrancaDetalhadaA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("empresa_id", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				// NotaCobrancaFacturasProdutoA4
				 if(nomeRelat!=null && nomeRelat.equals("NotaCobrancaFacturasProdutoA4"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("NotaCobrancaFacturasProdutoA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("seguradora", cod_seguradora);
							 param.put("paciente", cod_param);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				// Tela Nota de Cobrança - Produtos
				 if(nomeRelat!=null && nomeRelat.equals("NotaCobrancaProdutoA4"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("NotaCobrancaProdutoA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("numero_factura", cod_param);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				// NotaCobrancaFacturasPagaProdutoA4
				 if(nomeRelat!=null && nomeRelat.equals("NotaCobrancaFacturasPagaProdutoA4"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("NotaCobrancaFacturasPagaProdutoA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("factura_id", cod_param);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				// Tela Nota de Cobrança Paga - Produtos
				 if(nomeRelat!=null && nomeRelat.equals("NotaCobrancaPagaProdutoA4"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("NotaCobrancaPagaProdutoA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("numero_factura", cod_param);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				//  Tela Criar Factura - EmpresasDevedorasA4
				 if(nomeRelat!=null && nomeRelat.equals("EmpresasDevedorasA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("EmpresasDevedorasA4"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				 
				 
				// Gerar Pagamento Servicos Gerais PlanoA4
				 if(nomeRelat!=null && nomeRelat.equals("PagFacturaCreditoResumidaA4"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("PagFacturaCreditoResumidaA4") && cod_param!=null && !cod_param.equals("")){
							 param.put("factura_id", cod_param);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
	 
				// PagFacturaCreditoDetalhadaA4
				 if(nomeRelat!=null && nomeRelat.equals("PagFacturaCreditoDetalhadaA4"))
				 {
				 	try{
				 						 
				 		if(nomeRelat!=null && nomeRelat.equals("PagFacturaCreditoDetalhadaA4") && cod_param!=null && !cod_param.equals("")){
				 		param.put("factura_id", cod_param);
				 		param.put("logoImage", logoImage);
				 		param.put("usuario", usuario);
				 		String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
				 		gpdf.geraPdf(jrxml, param, saida);
				 		}
				 		}
				 					 catch(Exception e){
				 						 e.printStackTrace();
				 					 }
				}
				 //Pagar Fornecedores
				 if(nomeRelat!=null && nomeRelat.equals("PagarFornecedoresProdutosA4"))
				 {
					 try{
						 
						 if(nomeRelat!=null && nomeRelat.equals("PagarFornecedoresProdutosA4") && cod_param!=null && !cod_param.equals(""))
						 {
							 param.put("numero_encomenda", cod_param);
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e)
					 {
						 e.printStackTrace();
					 }
				 }
				 
				 // Credores
				 if(nomeRelat!=null && nomeRelat.equals("ListaDeCredoresA4"))
				 {
					 try
					 {
						 
						 if(nomeRelat!=null && nomeRelat.equals("ListaDeCredoresA4"))
						 {
							 param.put("logoImage", logoImage);
							 param.put("usuario", usuario);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							 gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				// VACINAS
				 //rel consultas agendadas
				 if(nomeRelat!=null && nomeRelat.equals("VacinaRealizadaA5"))
				 {
					 try{
						 if(nomeRelat!=null && nomeRelat.equals("VacinaRealizadaA5") && cod_param!=null && !cod_param.equals("")){
							 param.put("idvacina", Integer.parseInt(cod_param));
							 param.put("logoImage", logoImage);
							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
							  gpdf.geraPdf(jrxml, param, saida);
						 }
					 }
					 catch(Exception e){
						 e.printStackTrace();
					 }
				 }
				 
				 //Resultados de Exames
				 
				 
				// Gerar para re exame A4
				 if(nomeRelat!=null && nomeRelat.equals("ResultadoExameA4"))
				 {
					 try{
							 
						 if(nomeRelat!=null && nomeRelat.equals("ResultadoExameA4") && cod_param!=null && !cod_param.equals("")){
						 param.put("identificador_exame", cod_param);
						 param.put("logo", logoImage);
						 param.put("usuario", usuario);
						 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
						 gpdf.geraPdf(jrxml, param, saida);
						}
					 }
						 catch(Exception e){
							 e.printStackTrace();
						 }
					 }
				 
				  if(nomeRelat!=null && nomeRelat.equals("ListaPacientesExamesRequisitadosMedicoA4"))
				     {
				      try
				      {
				       
				       if(nomeRelat!=null && nomeRelat.equals("ListaPacientesExamesRequisitadosMedicoA4"))
				       {
				       
				        param.put("logoImage", logoImage);
				        param.put("usuario", usuario);
				        String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
				         gpdf.geraPdf(jrxml, param, saida);
				       }
				      }
				      catch(Exception e){
				       e.printStackTrace();
				      }
				     }
				  
				// Pacientes Com Exames Requisitados pelo Médico Modal
				     if(nomeRelat!=null && nomeRelat.equals("ListaPacientesExamesRequisitadosMedicoModalA4"))
				     {
				      try{
				       if(nomeRelat!=null && nomeRelat.equals("ListaPacientesExamesRequisitadosMedicoModalA4") && cod_param!=null && !cod_param.equals("")){
				        param.put("id_consulta", Integer.parseInt(cod_param));
				        param.put("logoImage", logoImage);
				        param.put("usuario", usuario);
				        String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
				         gpdf.geraPdf(jrxml, param, saida);
				       }
				      }
				      catch(Exception e){
				       e.printStackTrace();
				      }
				     }

				 //  Tela Plano - EmpresasPlanificadorPlanosA4

				 				 if(nomeRelat!=null && nomeRelat.equals("EmpresasPlanificadorPlanosA4"))
				 				 {
				 					 try
				 					 {
				 						 
				 						 if(nomeRelat!=null && nomeRelat.equals("EmpresasPlanificadorPlanosA4"))
				 						 {
				 							 param.put("logoImage", logoImage);
				 							 param.put("usuario", usuario);
				 							 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
				 							 gpdf.geraPdf(jrxml, param, saida);
				 						 }
				 					 }
				 					 catch(Exception e)
				 					 {
				 						 e.printStackTrace();
				 					 }
				 				 }
				 				 
				 				// Tela Plano - EmpresasPlanificadorPlanosModalA4


								 if(nomeRelat!=null && nomeRelat.equals("EmpresasPlanificadorPlanosModalA4"))
								 {
									 try{
										 
										 if(nomeRelat!=null && nomeRelat.equals("EmpresasPlanificadorPlanosModalA4") && cod_param!=null && !cod_param.equals(""))
										 {
											 param.put("id_planificacao", Integer.parseInt(cod_param));
											 param.put("logoImage", logoImage);
											 param.put("usuario", usuario);
											 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
											  gpdf.geraPdf(jrxml, param, saida);
										 }
									 }
									 catch(Exception e){
										 e.printStackTrace();
									 }
								 }
								 

								//  Tela Plano - CriarServicosConsumíveisA4
												 if(nomeRelat!=null && nomeRelat.equals("CriarServicosConsumíveisA4"))
												 {
													 try
													 {
														 
														 if(nomeRelat!=null && nomeRelat.equals("CriarServicosConsumíveisA4"))
														 {
															 param.put("logoImage", logoImage);
															 param.put("usuario", usuario);
															 String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
															 gpdf.geraPdf(jrxml, param, saida);
														 }
													 }
													 catch(Exception e)
													 {
														 e.printStackTrace();
													 }
												 }
												 
//		cussunga Relatório
		if(nomeRelat!=null && nomeRelat.equals("TesteRelatorio"))
		{
			try{									 
			if(nomeRelat!=null && nomeRelat.equals("TesteRelatorio") && cod_param!=null && !cod_param.equals(""))
				{
				 String referencia = req.getParameter("referencia");
				  param.put("fk_paciente", Integer.parseInt(cod_param));
				  param.put("referencia", referencia);
				  param.put("logoImage", logoImage);
				  String jrxml = getServletContext().getRealPath("/WEB-INF/relatorios/"+nomeRelat+".jrxml");
				  gpdf.geraPdf(jrxml, param, saida);
					}
				}
				catch(Exception e){
				e.printStackTrace();
			}
		}

		
		
	}
}
