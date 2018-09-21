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

import ao.co.cligest.dao.AgendaConsultaDAO;
import ao.co.cligest.dao.AgendaDAO;
import ao.co.cligest.dao.AgendaExameDAO;
import ao.co.cligest.dao.ConfirmarDAO;
import ao.co.cligest.dao.EmpresaDAO;
import ao.co.cligest.dao.ExamesDAO;
import ao.co.cligest.dao.FacturacaoDAO;
import ao.co.cligest.dao.Formatando;
import ao.co.cligest.dao.FuncionarioDAO;
import ao.co.cligest.dao.InforPacienteDAO;
import ao.co.cligest.dao.PacienteDAO;
import ao.co.cligest.dao.PlanoDAO;
import ao.co.cligest.dao.ServicoDAO;
import ao.co.cligest.entidades.Agenda;
import ao.co.cligest.entidades.ConfNomeFactura;
import ao.co.cligest.entidades.Diverso;
import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Funcionario;
import ao.co.cligest.entidades.InforPaciente;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Plano;
import ao.co.cligest.entidades.Servico;
import ao.co.cligest.interfaces.IAgendaConsulta;
import ao.co.cligest.interfaces.IAgendaExame;
import ao.co.cligest.util.CarEntidade;
import ao.co.cligest.util.DataMarcacao;
import ao.co.cligest.util.ListaCarrinho;
import ao.co.cligest.util.MetodosBuscas;

@WebServlet("/navegacaoag")
public class AgendaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PacienteDAO _pacienteDAO = new PacienteDAO();
	private FuncionarioDAO _funcionarioDAO = new FuncionarioDAO();
	private ServicoDAO _servicoDAO = new ServicoDAO();
	private ExamesDAO _exameDAO = new ExamesDAO();
	private IAgendaConsulta _agendaConsulta = new AgendaConsultaDAO();
	private IAgendaExame _agendaExame = new AgendaExameDAO();
	private MetodosBuscas _metodos = new MetodosBuscas();
	
	
	public AgendaController() {
		super();
	}

	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher saida = null;
		String mod = request.getParameter("mods");
		HttpSession ss = request.getSession(false);
		String tela = request.getParameter("pag");
	 

		if (ss != null) {
			if (mod != null && mod.equals("ag")) {
				request.setAttribute("agendm", "active open");
				// Inicio - Paciente
				if (tela != null && (tela.equals("pac"))) {
					List<Paciente> lsPaciente = new ArrayList<>();
					String query = request.getParameter("query");
					if(query!=null)
					{
						lsPaciente = _pacienteDAO.buscarPacientePorNome(query);
					}
					else
					{
						lsPaciente = _pacienteDAO.buscarPaciente();
					}
					request.setAttribute("lsPaciente", lsPaciente);
					
					saida = request.getRequestDispatcher("index.jsp?mods=ag&pag=pac");
					saida.forward(request, response);
				}
				if (tela != null && (tela.equals("novopac"))) {
					request.setAttribute("lsEspm", _metodos.buscaEspecialidade());
					request.setAttribute("lsFuncao", _metodos.buscaFuncao());
					request.setAttribute("lsGenero", _metodos.buscaGeneros());
					request.setAttribute("lsEstadoC", _metodos.buscaEstadoCivil());
					request.setAttribute("lsDoc", _metodos.buscaTipoDocumento());
					saida = request.getRequestDispatcher("index.jsp?mods=ag&pag=novopac");
					saida.forward(request, response);
				}
				if (tela != null && (tela.equals("editpac"))) {
					String  codp = request.getParameter("codp");
					Paciente pac = _pacienteDAO.getPerfilPaciente(codp);
					request.setAttribute("pac", pac);
					request.setAttribute("lsEstadoC", _metodos.buscaEstadoCivil());
					request.setAttribute("lsDoc", _metodos.buscaTipoDocumento());
					request.setAttribute("lsGenero", _metodos.buscaGeneros());
					saida = request.getRequestDispatcher("index.jsp?mods=ag&pag=editpac");
					saida.forward(request, response);
				}
				if (tela != null && (tela.equals("profpac"))) {
					saida = request.getRequestDispatcher("index.jsp?mods=ag&pag=profpac");
					saida.forward(request, response);
				}
				// Fim Paciente
				
				// Agendamento
				if (tela != null && (tela.equals("novoagen"))) {
					List<Paciente> lsPaciente = new ArrayList<>();
					String query = request.getParameter("query");
					if(query!=null)
					{
						lsPaciente = _pacienteDAO.buscarPacientePorNome(query);
					}
					else
					{
						lsPaciente = _pacienteDAO.buscarPaciente();
					}
					request.setAttribute("lsPaciente", lsPaciente);
					request.setAttribute("agendm", "active open");
					saida = request.getRequestDispatcher("index.jsp?mods=ag&pag=novoagen");
					saida.forward(request, response);
				}
				if (tela != null && (tela.equals("novoagencon"))) {
					
					String codp = request.getParameter("codp");
					
					Paciente pac = _pacienteDAO.getPerfilPaciente(codp);
					List<Funcionario> lsDoutores = _funcionarioDAO.listaDoutoreTodos();
					List<Servico> lsConsultas = _servicoDAO.BuscarServicoConsulta();
					
					request.setAttribute("pac", pac);
					request.setAttribute("lsDoutores", lsDoutores);
					request.setAttribute("lsConsultas", lsConsultas);
					saida = request.getRequestDispatcher("index.jsp?mods=ag&pag=novoagencon");
					saida.forward(request, response);
				}
				if (tela != null && (tela.equals("novoagenexa"))) {
					String codp = request.getParameter("codp");
					
					Paciente pac = _pacienteDAO.getPerfilPaciente(codp);
					List<Funcionario> lsDoutores = _funcionarioDAO.listaDoutoreTodos();
					List<Exames> lsGrupo = _exameDAO.getGrupoExames();
					
					request.setAttribute("pac", pac);
					request.setAttribute("lsDoutores", lsDoutores);
					request.setAttribute("lsGrupo", lsGrupo);
					saida = request.getRequestDispatcher("index.jsp?mods=ag&pag=novoagenexa");
					saida.forward(request, response);
				}
				if (tela != null && (tela.equals("listagen"))) {
					
					List<Paciente> lsPaciente = new ArrayList<>();
					String query = request.getParameter("query");
					if(query!=null)
					{
						lsPaciente = _agendaConsulta.listaConsultaAgendada(query);
					}
					else
					{
						lsPaciente = _agendaConsulta.listaConsultaAgendada();
					}
					request.setAttribute("lsPaciente", lsPaciente);
					
					saida = request.getRequestDispatcher("index.jsp?mods=ag&pag=listagen");
					saida.forward(request, response);
				}
				
				if (tela != null && (tela.equals("listagexa"))) {
					
					List<Paciente> lsPaciente = new ArrayList<>();
										
						lsPaciente = _agendaExame.listaRequisicaoExameAgendada();
					
					request.setAttribute("lsPaciente", lsPaciente);
					
					saida = request.getRequestDispatcher("index.jsp?mods=ag&pag=listagexa");
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

}
