package ao.co.cligest.interfaces;

import java.util.List;

import ao.co.cligest.entidades.Fornecedor;
import ao.co.cligest.entidades.Funcionario;
import ao.co.cligest.entidades.Produtos;

public interface IPagamentoFornecedor {

	void validaFuncionario(Funcionario f);	
	List<Funcionario> buscarFuncionariosPermitidos();
	//Fornecedor detalhesFornecedor(int codf);
	
	boolean validarFuncionario(int codf);
	double saldoFornecedor(int codf);
	void validarEncomenda(Produtos enc);
	List<Fornecedor> buscarFornecedoresPagar();
	List<Fornecedor> buscarFornecedoresPagos();
	List<Fornecedor> buscarFornecedores(String param);
	List<Produtos> buscarEncomendaNaoPagasPorFornecedor(int codf);
	List<Produtos> buscarEncomendaPagasPorFornecedor(int codf);
	List<Produtos> buscarEncomendaNaoPagasPorFornecedorValidada(int codf);
	
	int facturaPagamentoEncomenda(Fornecedor f);
	void facturaEncomendaItem(Fornecedor f);
	void facturaEncomendaDeposito(Fornecedor f);
	void facturaEncomendaTransferencia(Fornecedor f);
	void facturaEncomendaCheque(Fornecedor f);
	void facturaEncomendaOrdemSaque(Fornecedor f);
	void facturaEncomendaCredito(Fornecedor f);
	void actualizarSaldoFornecedor(Fornecedor f);
	void eliminarValidador(int i);
	Fornecedor detalhesFornecedorNaoPagos(int codf);
	Fornecedor detalhesFornecedorPagos(int codf);
	List<Produtos> buscarEncomendaPagasPorFornecedor(int codf, String param);
 }
