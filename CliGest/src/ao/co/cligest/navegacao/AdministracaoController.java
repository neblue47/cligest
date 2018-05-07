package ao.co.cligest.navegacao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ao.co.cligest.beans.AltaMedicaBean;
import ao.co.cligest.beans.BancoUEmergenciaBean;
import ao.co.cligest.beans.ConsultaExternaBean;
import ao.co.cligest.beans.InternamentoBean;
import ao.co.cligest.beans.LaboratorioBean;
import ao.co.cligest.beans.PagamentoBean;
import ao.co.cligest.dao.AgendaConsultaDAO;
import ao.co.cligest.dao.AgendaDAO;
import ao.co.cligest.dao.BUEEstatisticaDAO;
import ao.co.cligest.dao.BeneficiosDAO;
import ao.co.cligest.dao.CidDAO;
import ao.co.cligest.dao.ConfirmarDAO;
import ao.co.cligest.dao.ConsultaDAO;
import ao.co.cligest.dao.ConsultasExternasEstatiscaDAO;
import ao.co.cligest.dao.EmpresaDAO;
import ao.co.cligest.dao.FacturacaoDAO;
import ao.co.cligest.dao.Formatando;
import ao.co.cligest.dao.FornecedorDAO;
import ao.co.cligest.dao.FuncionarioDAO;
import ao.co.cligest.dao.GestaoInstalacaoDAO;
import ao.co.cligest.dao.GrupoDAO;
import ao.co.cligest.dao.InstituicaoDAO;
import ao.co.cligest.dao.InternamentoEstatisticaDAO;
import ao.co.cligest.dao.LaboratorioEstatisticaDAO;
import ao.co.cligest.dao.PacienteDAO;
import ao.co.cligest.dao.PagamentosEstatisticaDAO;
import ao.co.cligest.dao.TipoCoberturaDAO;
import ao.co.cligest.entidades.Agenda;
import ao.co.cligest.entidades.Beneficios;
import ao.co.cligest.entidades.Capitulo;
import ao.co.cligest.entidades.CategoriaDGrupo;
import ao.co.cligest.entidades.Cid;
import ao.co.cligest.entidades.Diverso;
import ao.co.cligest.entidades.Fornecedor;
import ao.co.cligest.entidades.Funcionario;
import ao.co.cligest.entidades.GestaoInstalacao;
import ao.co.cligest.entidades.GrupoDeCapitulo;
import ao.co.cligest.entidades.Grupos;
import ao.co.cligest.entidades.Instituicao;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.SelecionarBanco;
import ao.co.cligest.entidades.Servico;
import ao.co.cligest.entidades.TipoCobertura;
import ao.co.cligest.entidades.Triagem;
import ao.co.cligest.interfaces.IAgendaConsulta;
import ao.co.cligest.util.ListaCarrinho;
import ao.co.cligest.util.MetodosBuscas;

/**
 * 
 * @author NELSON DIWIDI - ANGOTECH
 * @manutencao NELSON DIWIDI
 *
 */
@WebServlet("/navegacao")
public class AdministracaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ConfirmarDAO _marconsulta = new ConfirmarDAO();
    private IAgendaConsulta _agendaConsulta = new AgendaConsultaDAO();
   
    public AdministracaoController() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher saida = null;
		String mod = request.getParameter("mods");
		HttpSession ss = request.getSession(false);
		String tela = request.getParameter("pag");
		
		if(ss!=null){
			// Inicio - Doutor
			if (tela != null && (tela.equals("doc"))) {
				saida = request.getRequestDispatcher("index.jsp?mods=ad&pag=doc");
				saida.forward(request, response);
			}
			if (tela != null && (tela.equals("novodoc"))) {
				saida = request.getRequestDispatcher("index.jsp?mods=ad&pag=novodoc");
				saida.forward(request, response);
			}
			if (tela != null && (tela.equals("agenddoc"))) {
				saida = request.getRequestDispatcher("index.jsp?mods=ad&pag=agenddoc");
				saida.forward(request, response);
			}
			if (tela != null && (tela.equals("novogenddoc"))) {
				saida = request.getRequestDispatcher("index.jsp?mods=ad&pag=novogenddoc");
				saida.forward(request, response);
			}
			if (tela != null && (tela.equals("editagenddoc"))) {
				saida = request.getRequestDispatcher("index.jsp?mods=ad&pag=editagenddoc");
				saida.forward(request, response);
			}
			if (tela != null && (tela.equals("editdoc"))) {
				saida = request.getRequestDispatcher("index.jsp?mods=ad&pag=editdoc");
				saida.forward(request, response);
			}
			if (tela != null && (tela.equals("profdoc"))) {
				saida = request.getRequestDispatcher("index.jsp?mods=ad&pag=profdoc");
				saida.forward(request, response);
			}
			
			if(mod!=null && mod.equals("ng"))
			{
				List<Paciente> agendados =  _agendaConsulta.listaConsultaAgendada();
				request.setAttribute("listaPac", agendados);
				saida = request.getRequestDispatcher("index.jsp?mods=ng");
				saida.forward(request, response);
			}
			
			if(mod!=null && mod.equals("ajd"))
			{
				saida = request.getRequestDispatcher("index.jsp?mod=ajd");
				saida.forward(request, response);
			}
		} // if de Sessao 
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
