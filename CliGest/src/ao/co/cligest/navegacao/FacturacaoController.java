package ao.co.cligest.navegacao;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
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

import ao.co.cligest.dao.AgendaConsultaDAO;
import ao.co.cligest.dao.AgendaExameDAO;
import ao.co.cligest.dao.BancosDAO;
import ao.co.cligest.dao.BeneficiosDAO;
import ao.co.cligest.dao.CaixaDAO;
import ao.co.cligest.dao.CobrarSegurosDAO;
import ao.co.cligest.dao.Conexao;
import ao.co.cligest.dao.ConfiguraIdadeDAO;
import ao.co.cligest.dao.DepositoPacienteDAO;
import ao.co.cligest.dao.DevolucaoDAO;
import ao.co.cligest.dao.EspecialidadeDAO;
import ao.co.cligest.dao.ExamesDAO;
import ao.co.cligest.dao.FacturacaoDAO;
import ao.co.cligest.dao.FacturarCanceladoDAO;
import ao.co.cligest.dao.FacturarTesteDAO;
import ao.co.cligest.dao.Formatando;
import ao.co.cligest.dao.FornecedorDAO;
import ao.co.cligest.dao.FuncionarioDAO;
import ao.co.cligest.dao.HistVendaDAO;
import ao.co.cligest.dao.InternadosDAO;
import ao.co.cligest.dao.IsencaoDAO;
import ao.co.cligest.dao.OutrosServicosDAO;
import ao.co.cligest.dao.PacienteDAO;
import ao.co.cligest.dao.PacientePlanoDAO;
import ao.co.cligest.dao.PagamentoFornecedorDAO;
import ao.co.cligest.dao.ProdutosDAO;
import ao.co.cligest.dao.RelatorioPagamentosDAO;
import ao.co.cligest.dao.ServicoConsumivelDAO;
import ao.co.cligest.dao.ServicoDAO;
import ao.co.cligest.dao.ServicosObrigatorioDAO;
import ao.co.cligest.dao.TipoCoberturaDAO;
import ao.co.cligest.dao.TipoServicoDAO;
import ao.co.cligest.dao.TratamentoDAO;
import ao.co.cligest.entidades.Ajuste;
import ao.co.cligest.entidades.Bancos;
import ao.co.cligest.entidades.Beneficios;
import ao.co.cligest.entidades.Caixa;
import ao.co.cligest.entidades.CobrarSeguros;
import ao.co.cligest.entidades.DepositoEntity;
import ao.co.cligest.entidades.DevolucaoVenda;
import ao.co.cligest.entidades.Diverso;
import ao.co.cligest.entidades.Especialidade;
import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Facturacao;
import ao.co.cligest.entidades.Fornecedor;
import ao.co.cligest.entidades.Funcionario;
import ao.co.cligest.entidades.Isencao;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.PacientePlano;
import ao.co.cligest.entidades.Produtos;
import ao.co.cligest.entidades.SelecionarBanco;
import ao.co.cligest.entidades.Servico;
import ao.co.cligest.entidades.ServicoConsumivel;
import ao.co.cligest.entidades.TesteDeQualidade;
import ao.co.cligest.entidades.TipoCobertura;
import ao.co.cligest.entidades.TipoServico;
import ao.co.cligest.entidades.Tratamento;
import ao.co.cligest.entidades.Triagem;
import ao.co.cligest.interfaces.IAgendaConsulta;
import ao.co.cligest.interfaces.IAgendaExame;
import ao.co.cligest.interfaces.ICobrarSeguros;
import ao.co.cligest.interfaces.IConfiguraIdade;
import ao.co.cligest.interfaces.IPacientePlano;
import ao.co.cligest.interfaces.IPagamentoFornecedor;
import ao.co.cligest.interfaces.IServicoConsumivel;
import ao.co.cligest.interfaces.IServicosObrigatorios;
import ao.co.cligest.util.CarEntidade;
import ao.co.cligest.util.ListaCarrinho;
import ao.co.cligest.util.MetodosBuscas;

/**
 * Servlet implementation class FacturacController
 * N Diwidi Joao
 * Controller de Navegacao do Modulo Faturacao
 */
@WebServlet("/navegacaoft")
public class FacturacaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PacienteDAO _pacienteDAO = new PacienteDAO();
	private IAgendaExame _agendaExame = new AgendaExameDAO();
	FacturacaoDAO fDao = new FacturacaoDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private IAgendaConsulta _agendaConsulta = new AgendaConsultaDAO();
    public FacturacaoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher saida = null;
		String mod = request.getParameter("mods");
		HttpSession ss = request.getSession(false);
		String tela = request.getParameter("pag");
		
		if (ss != null) {
			if (mod != null && mod.equals("fat")) {
				request.setAttribute("pagmt", "active open");
				// Inicio - Pagamento - Consultas
				if (tela != null && (tela.equals("listacon"))) {
					List<Paciente> lsPaciente = new ArrayList<>();
					lsPaciente = _agendaConsulta.listaConsultaConfirmada();
					request.setAttribute("lsPaciente", lsPaciente);
					
					saida = request.getRequestDispatcher("index.jsp?mods=fat&pag=listacon");
					saida.forward(request, response);
				}
				if (tela != null && (tela.equals("pagarcon"))) {
					String codcs =request.getParameter("codcs");
					Paciente pagmt = _agendaConsulta.ConsultaParaPagar(Integer.parseInt(codcs));
					String temp = "" + pagmt.getFK_paciente();
					Paciente perfil = _pacienteDAO.getPerfilPaciente(temp);
				    
					request.setAttribute("perfil", perfil);
					request.setAttribute("pagmt", pagmt);
					request.setAttribute("lsBancos", fDao.bancoSelecao());
					saida = request.getRequestDispatcher("index.jsp?mods=fat&pag=pagarcon");
					saida.forward(request, response);
				}
				if (tela != null && (tela.equals("listaconHis"))) {
					List<Paciente> lsPaciente = _agendaConsulta.listaConsultaConfirmadaPago();
					request.setAttribute("lsPaciente", lsPaciente);
					saida = request.getRequestDispatcher("index.jsp?mods=fat&pag=listaconHis");
					saida.forward(request, response);
				}
				
				
				// Inicio - Pagamento - Exames
				if (tela != null && (tela.equals("listaexa"))) {
					List<Paciente> lsPaciente = new ArrayList<>();
					lsPaciente = _agendaExame.listaRequisicaoExameAgendada();
					request.setAttribute("lsPaciente", lsPaciente);
					request.setAttribute("pagmt", "active open");
					saida = request.getRequestDispatcher("index.jsp?mods=fat&pag=listaexa");
					saida.forward(request, response);
				}
				if (tela != null && (tela.equals("pagarexa"))) {
					String codcs =request.getParameter("codcs");
					Paciente pagmt = _agendaExame.listaRequisicaoExamePorId(Integer.parseInt(codcs));
					List<Servico> lsexames =  _agendaExame.listaRequisicaoExames(Integer.parseInt(codcs));
					String temp = "" + pagmt.getFK_paciente();
					Paciente perfil = _pacienteDAO.getPerfilPaciente(temp);
				    
					request.setAttribute("perfil", perfil);
					request.setAttribute("pagmt", pagmt);
					request.setAttribute("lsexames", lsexames);
					request.setAttribute("lsBancos", fDao.bancoSelecao());
					saida = request.getRequestDispatcher("index.jsp?mods=fat&pag=pagarexa");
					saida.forward(request, response);
				}
				if (tela != null && (tela.equals("listaexaHis"))) {
					saida = request.getRequestDispatcher("index.jsp?mods=fat&pag=listaexaHis");
					saida.forward(request, response);
				}
				
			}
		} // if de Sessao

		else {
			String url = request.getRequestURL().toString();
			String queryString = request.getQueryString();

			request.getSession().setAttribute("ultimaURL", url);
			request.getSession().setAttribute("queryString", queryString);

			response.sendRedirect("login.jsp");
		}
		
	}
	
	private void LimparObjectosProdutos(HttpSession ss)
	{
		 ss.removeAttribute("cash");
		 ss.removeAttribute("multicaixa");
		 ss.removeAttribute("isencao");
		 ss.removeAttribute("bancoSelect");
		 ss.removeAttribute("seguroPpagm");
	}
}
