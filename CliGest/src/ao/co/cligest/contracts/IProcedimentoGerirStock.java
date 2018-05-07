package ao.co.cligest.contracts;

import java.util.List;

import ao.co.cligest.entidades.Produtos;
import ao.co.cligest.entidades.RequisicaoInterna;

public interface IProcedimentoGerirStock   {

	List<Produtos>buscarProdutosEmEstoque();
	List<Produtos>buscarTotalTipoProdutosEmEstoque();
	Produtos getProdutoPerfil(String codp);
	String foto(int id);
	int numero();
	List<Produtos>buscarProdutos();
	List<Produtos>buscarProdutos(String aux);
	public List<RequisicaoInterna> buscarRequisicaoFeitas();
	List<Produtos> buscarEncomendas();
	List<RequisicaoInterna> buscarRequisicaoDevolucao();
	List<RequisicaoInterna> buscarProdutoEmStockNaFarmacia();
	List<RequisicaoInterna> buscarProdutoEmStockNaFarmacia(String nome);
	List<RequisicaoInterna> buscarProdutoEmStockNaFarmacias(String nome);
	List<RequisicaoInterna> buscarDevolucaoProdutoVisual(int codigo);
	List<Produtos> buscarProdutosEstoque();
	List<RequisicaoInterna> buscarAjusteProduto();
	List<Produtos> buscarHistAjustModal(String prodId);
	Produtos buscarProduto(int prodId);
	
}
