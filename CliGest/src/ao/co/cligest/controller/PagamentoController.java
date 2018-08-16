package ao.co.cligest.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import ao.co.cligest.contracts.IPagamentos;
import ao.co.cligest.dao.AgendaConsultaDAO;
import ao.co.cligest.dao.PagamentoDAO;
import ao.co.cligest.entidades.Caixa;
import ao.co.cligest.entidades.Facturacao;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.interfaces.IAgendaConsulta;

/**
 * Servlet implementation class PagamentoController
 */
@WebServlet("/PagamentoController")
public class PagamentoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IPagamentos _pagamentos = new PagamentoDAO();  
    private PagamentoDAO _pagamentoDAO = new PagamentoDAO(); 
    private IAgendaConsulta _agendaConsulta = new AgendaConsultaDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PagamentoController() {
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
		HttpSession ss = request.getSession();
		Facturacao f = new Facturacao();
		String tipoPg = request.getParameter("tipoPg");
		String pacInt = request.getParameter("pacInt");
		String funInt = request.getParameter("funInt");
		String mtPagar = request.getParameter("mtPagar");
		String mtReceber = request.getParameter("mtreceber");
		String banco = request.getParameter("banco");
		String numCartao = request.getParameter("numCartao");
		String codcs = request.getParameter("codcs");
		String numfactura = _pagamentos.getNomeFactura() + _pagamentos.numerofactura();;
		String maquina = InetAddress.getLocalHost().getHostName();
		
		
		try {
			 Paciente pac = _agendaConsulta.getConsultaConfirmada(Integer.parseInt(codcs));
			 int id_abertura = abrirCaixaFacturacao(Integer.parseInt(funInt)); 
			 if(tipoPg!=null && tipoPg.equals("1"))
			 {
				    f.setNumero_factura(numfactura);
					f.setFK_funcionario(Integer.parseInt(funInt));
					f.setFK_paciente(Integer.parseInt(pacInt));
					f.setFK_aberturadecaixa(id_abertura);
					f.setFK_Metodo_dpagamento(Integer.parseInt(tipoPg));
					f.setFK_tipo_deservico(1);
					f.setMaquina(maquina);
					f.setTotal_factura(Double.parseDouble(mtPagar));
					
					f.setFK_confirmacao_do_agendamento(pac.getFK_consulta_confirmada());
					f.setFK_servico(pac.getFK_servico());
					f.setTotais(Double.parseDouble(mtPagar));
					f.setTotal_factura(Double.parseDouble(mtPagar));
					int factura_id = _pagamentoDAO.inserir_faturacao(f);
					f.setFK_factura(factura_id);
					_pagamentoDAO.inserir_faturaconsulta(f);
			 }
			 else if(tipoPg!=null && tipoPg.equals("2"))
			 {
				 
			 }
			 else if(tipoPg!=null && tipoPg.equals("3"))
			 {
				 
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("navegacaoft?mods=fat&pag=listaconHis");
	}
	
	 private int abrirCaixaFacturacao (int fun) throws UnknownHostException
	 {
		 	Caixa c = new Caixa();
		 	c.setValor_dtroco(0);
			c.setFundos_reserva(0);
			c.setFk_entidade_caixa(fun);
			c.setFK_entidade(fun);
			c.setEstado_docaixa(1);
			 
			c.setMontante_dabertura(0);
			c.setMaquina_dabertuara(InetAddress.getLocalHost().getHostName());
			c.setDescr_abertura_decaixa("Abertura de Caixa Para facturação!"); 
			int ultimoId = _pagamentos.getCaixaUtente(fun, c); 
		  
		 
		 return ultimoId;
	 }

}
