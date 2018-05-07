package ao.co.cligest.contracts;

import java.util.List;

import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Produtos;
import ao.co.cligest.entidades.Servico;
import ao.co.cligest.entidades.ServicosObrigatorio;

public interface IServicosObrigatorios {
  
	 
	 
	int novoServicoObrigatorio(ServicosObrigatorio s);
	
	List<Servico> buscarServicosConsultaNObrigatorio();
	List<Servico> buscarServicosConsultaNObrigatorio(String aux);
	List<Servico> buscarServicosConsultaNObrigatorio(int codEsp);
	List<Servico> buscarServicosConsultaObrigatorio();
	List<Servico> buscarServicosConsultaObrigatorio(String nomServico); 
	void salvarServicosConsultaObrigatorio(Servico s);
	void excluirServicoConsultaSalvoObrigatorio(int cod);
	
	List<Exames> buscarServicosExamesNObrigatorio(int codEsp);
	List<Exames> buscarServicosExamesObrigatorio(String nomServico);
	List<Exames> buscarServicosExamesObrigatorio();
	void salvarServicosExamesObrigatorio(Exames e);
	void excluirServicoExamesSalvoObrigatorio(int cod);
	
	List<Servico> buscarServicosGeraisNObrigatorio();
	List<Servico> buscarServicosGeraisNObrigatorio(String nome);
	List<Servico> buscarServicosGeraisObrigatorio();
	List<Servico> buscarServicosGeraisObrigatorio(String nomServico); 
	void salvarServicosGeraisObrigatorio(Servico s);
	void excluirServicoGeraisSalvoObrigatorio(int cod);
	
	List<Produtos> buscarServicosProdutoNObrigatorio();
	List<Produtos> buscarServicosProdutoNObrigatorio(String nome);
	List<Produtos> buscarServicosProdutoObrigatorio();
	List<Produtos> buscarServicosProdutoObrigatorio(String nomServico); 
	void salvarServicosProdutoObrigatorio(Produtos s);
	void excluirServicoProdutoSalvoObrigatorio(int cod);
}
