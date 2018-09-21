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

import ao.co.cligest.dao.AgendaConsultaDAO;
import ao.co.cligest.dao.AgendaDAO;
import ao.co.cligest.dao.CidDAO;
import ao.co.cligest.dao.ConfNomeFacturaDAO;
import ao.co.cligest.dao.ConfirmarDAO;
import ao.co.cligest.dao.ConsultaDAO;
import ao.co.cligest.dao.EmergenciaDAO;
import ao.co.cligest.dao.ExamesDAO;
import ao.co.cligest.dao.Formatando;
import ao.co.cligest.dao.GerirLaboratorioDAO;
import ao.co.cligest.dao.GestaoInstalacaoDAO;
import ao.co.cligest.dao.HistVendaDAO;
import ao.co.cligest.dao.InternadosDAO;
import ao.co.cligest.dao.PacienteDAO;
import ao.co.cligest.dao.PacientePlanoDAO;
import ao.co.cligest.dao.ProcedimentoStockDAO;
import ao.co.cligest.dao.ProcementosGerirStockUtiDAO;
import ao.co.cligest.dao.ProdutosDAO;
import ao.co.cligest.dao.RequisicaoInternaDAO;
import ao.co.cligest.dao.TriagemDAO;
import ao.co.cligest.dao.UrgenciaDAO;
import ao.co.cligest.dao.VacinacaoDAO;
import ao.co.cligest.entidades.Agenda;
import ao.co.cligest.entidades.Cid;
import ao.co.cligest.entidades.ConfNomeFactura;
import ao.co.cligest.entidades.Diverso;
import ao.co.cligest.entidades.Emergencia;
import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Farmacos;
import ao.co.cligest.entidades.GestaoInstalacao;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Produtos;
import ao.co.cligest.entidades.RequisicaoInterna;
import ao.co.cligest.entidades.Servico;
import ao.co.cligest.entidades.Triagem;
import ao.co.cligest.interfaces.IAgendaConsulta;
import ao.co.cligest.interfaces.IPacientePlano;
import ao.co.cligest.interfaces.IProcedimentoGerirStock;
import ao.co.cligest.interfaces.IProcedimentoStock;
import ao.co.cligest.util.DataMarcacao;
import ao.co.cligest.util.MetodosBuscas;


@WebServlet("/navegacaopd")
public class ProcedimentoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PacienteDAO _pacienteDAO = new PacienteDAO();
	private IAgendaConsulta _agendaConsulta = new AgendaConsultaDAO();
	private ExamesDAO _exames = new ExamesDAO();
	private ConsultaDAO _consulta = new ConsultaDAO();
    public ProcedimentoController() {
        super();
    }

	
    protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher saida = null;
		String mod = request.getParameter("mods");
		HttpSession ss = request.getSession(false);
		String tela = request.getParameter("pag");
	 

		if (ss != null) {
			if (mod != null && mod.equals("pd")) {
				request.setAttribute("procmt", "active open");
				// Inicio - Triagem
				if (tela != null && (tela.equals("triapac"))) {
					 
					request.setAttribute("lsNoTriado", _agendaConsulta.listaPagoNaoTriado());
					saida = request.getRequestDispatcher("index.jsp?mods=pd&pag=triapac");
					saida.forward(request, response);
				}
				if (tela != null && (tela.equals("newtriar"))) {
					String codcs =request.getParameter("codcs");
				    
					request.setAttribute("perfil", _pacienteDAO.getPerfilPaciente(codcs));
					
					saida = request.getRequestDispatcher("index.jsp?mods=pd&pag=newtriar");
					saida.forward(request, response);
				}
				// Inicio - Consulta
				if (tela != null && (tela.equals("conspac"))) {
					request.setAttribute("lsSiTriado", _agendaConsulta.listaPagoTriado());
					saida = request.getRequestDispatcher("index.jsp?mods=pd&pag=conspac");
					saida.forward(request, response);
				}
				if (tela != null && (tela.equals("hstconspac"))) {
					request.setAttribute("lsSiTriado", _agendaConsulta.listaPacientesConsultados());
					saida = request.getRequestDispatcher("index.jsp?mods=pd&pag=hstconspac");
					saida.forward(request, response);
				}
				if (tela != null && (tela.equals("newcons"))) {
					String codp =request.getParameter("codp");
					String codc =request.getParameter("codc");
					Paciente perfil = _pacienteDAO.getPerfilPaciente(codp);
					Triagem sinais = _agendaConsulta.getSinais(codc);
					int ultimoId =_consulta.xequeConsulta(Integer.parseInt(codp), Integer.parseInt(codc));
					request.setAttribute("perfil", perfil);
					request.setAttribute("sinais", sinais);
					request.setAttribute("queixas", _consulta.getQueixaHistorial(ultimoId));
					request.setAttribute("queixas", _consulta.getQueixaHistorial(ultimoId));
					request.setAttribute("exmFs", _consulta.getExameFisicos(ultimoId));
					request.setAttribute("tipoExames", _exames.getGrupoExames());
					saida = request.getRequestDispatcher("index.jsp?mods=pd&pag=newcons");
					saida.forward(request, response);
				}
				if (tela != null && (tela.equals("atdconspac"))) {
					saida = request.getRequestDispatcher("index.jsp?mods=pd&pag=atdconspac");
					saida.forward(request, response);
				}
				if (tela != null && (tela.equals("urgconspac"))) {
					saida = request.getRequestDispatcher("index.jsp?mods=pd&pag=urgconspac");
					saida.forward(request, response);
				}
				if (tela != null && (tela.equals("ergconspac"))) {
					saida = request.getRequestDispatcher("index.jsp?mods=pd&pag=ergconspac");
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
