package ao.co.cligest.interfaces;

import java.util.List;

import ao.co.cligest.entidades.Fornecedor;
import ao.co.cligest.entidades.ServicoConsumivel;

public interface IServicoConsumivel {

	/* Rotinas para CRUD de ServicoConsumivel */
	void novoServicoConsumivel(ServicoConsumivel sc);
	void editaServicoConsumivel(ServicoConsumivel sc);
	void excluirServicoConsumivel(int cod);
	List<ServicoConsumivel> buscarServicoConsumiveis();
	List<ServicoConsumivel> buscarConsumiveis();
	List<ServicoConsumivel> buscarServicoConsumiveis(String param);
	ServicoConsumivel buscarServicoConsumiveisPorId(int codsc);
	String numReferencia();
	List<ServicoConsumivel> buscarTaxas();
	boolean podeEliminarServicoConsumivel(int cod);
	boolean existeServicoConsumivel(ServicoConsumivel sc);
	
	/* Rotinas para CRUD de Agregar e Planificacao de ServicoConsumivel */
	List<Fornecedor> listaFornecedores();
	List<ServicoConsumivel> listaTipoContrato();
	String numReferenciaAgregar();
	int novaAgregacaoConsumivel(ServicoConsumivel sc);
	void novaAgregacaoConsumivelItens(ServicoConsumivel sc);
	List<ServicoConsumivel> listaConsumiveisAgregados();
	ServicoConsumivel listaDetalheConsumiveisAgregados(int cod);
	int novoPlanoServicoConsumivel(ServicoConsumivel sc);
	void novoPlanoServicoConsumivelPontual(ServicoConsumivel sc);
	void novoPlanoServicoConsumivelPermanente(ServicoConsumivel sc);
	void novoPlanoServicoConsumivelContrato(ServicoConsumivel sc);
	void excluirPlanoServicoConsumivelGeral(int cod);
	ServicoConsumivel PlanoServicoConsumiveisPorId(int cod);
	List<ServicoConsumivel> listaPlanoServicoConsumiveis();
	List<ServicoConsumivel> listaPlanoServicoConsumiveis(String param1,String param2);
	List<ServicoConsumivel> listaPlanoServicoConsumiveisPeriodicidade(int periodo);
	List<ServicoConsumivel> buscarServicoConsumiveisAgregados();
	ServicoConsumivel buscarServicoConsumiveisAgregadosPorId(int id);
	List<ServicoConsumivel> buscarServicos();
	Fornecedor listaFornecedorPorId(int cod);
	String numReferenciaServico();
	void editaPlanoServicoConsumivelPontual(ServicoConsumivel sc);
	void editaPlanoServicoConsumivelPermanente(ServicoConsumivel sc);
	void editaPlanoServicoConsumivelContrato(ServicoConsumivel sc);
	void editaPlanoServicoConsumivel(ServicoConsumivel sc);
	void gerarDividaServicoConsumivelFornecedor();
	
}
