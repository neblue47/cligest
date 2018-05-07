package ao.co.cligest.contracts;

import java.util.List;

import ao.co.cligest.entidades.Produtos;
import ao.co.cligest.entidades.RequisicaoInterna;

public interface IProcedimentoStock {

	int novaRequisicaointerna(RequisicaoInterna r);
	void novaRequisicaointernageralItem(RequisicaoInterna r);
	String numRequisicao(String origem);
	String numDevolucao(String origem);
	List<RequisicaoInterna> buscarRequisicaoEfectuada(int origem) ;
	List<RequisicaoInterna> buscarRequisicaoReceber(int origem) ;
	List<RequisicaoInterna> buscarRequisicaoRecebida(int origem) ;
	List<RequisicaoInterna> buscarRequisicaoRecebida(int origem, int codreq) ;
	List<RequisicaoInterna> buscarProdutosStockProcedimento(int origem) ;
	List<RequisicaoInterna> buscarProdutosStockProcedimento(int origem, String termo) ;
	List<RequisicaoInterna> buscarProdutosStockExpirandoProcedimento(int origem) ;
	List<RequisicaoInterna> buscarProdutosStockExpirandoProcedimento(int origem,String num_lote,String num_dia) ;
	List<RequisicaoInterna> buscarRequisicaoReceberItens(int origem,int codreq) ;
	List<RequisicaoInterna> buscarRequisicaoRecebidoItens(int origem,int codreq) ;
	RequisicaoInterna buscarRequisicaoRecebidoItens(int origem, int codreq,int prd);
	RequisicaoInterna buscarRequisicaoReceberItem(int origem,int codreq, int prd) ;
	RequisicaoInterna buscarRequisicaoItemLoteCaducidade(int codreq, int prd) ;
	RequisicaoInterna buscarItemNoStock(int origem,int prd, String numlote) ;
	RequisicaoInterna buscarItemNoStock(int origem,int prd) ;
	
	int novaRecebimentoRequeridoGeral(RequisicaoInterna r);
	void novaRecebimentoRequeridoItem(RequisicaoInterna r);
	void lancarRecebidoItemInternamento(RequisicaoInterna r);
	void actualizarStockItem(RequisicaoInterna r);
	int xequeProdutoStock( int prd,  int codreq); 
	int xequeProdutoStockFarm( int prd,  int codreq); 
	int novaDevolucaoProcedimentoGeral(RequisicaoInterna r);
	void DevolucaoProcedimentoGeralItem(RequisicaoInterna r);
	List<RequisicaoInterna> buscarProdutosDevolvidosEmStock(int origem);
	void lancarRecebidoItemFarmacia(RequisicaoInterna r);
	void lancarRecebidoItemBancoUE(RequisicaoInterna r);
	int xequeProdutoStockBUE(int prd, int codreq);
	void lancarRecebidoItemUTI(RequisicaoInterna r);
	int xequeProdutoStockUTI(int prd, int codreq);
	void lancarRecebidoItemBercario(RequisicaoInterna r);
	void lancarRecebidoItemLab(RequisicaoInterna r); 
	int xequeProdutoStockLab(int prd, int codreq);
	void lancarRecebidoItemVacina(RequisicaoInterna r);
	int xequeProdutoStockVac(int prd, int codreq);
	int xequeProdutoStockBerc(int prd, int codreq);
	void lancarRecebidoItemIMA(RequisicaoInterna r);
	int xequeProdutoStockImag(int prd, int codreq);
	
}
