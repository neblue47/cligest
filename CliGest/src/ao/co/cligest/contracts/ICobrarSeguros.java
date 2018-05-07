package ao.co.cligest.contracts;

import java.util.List;

import ao.co.cligest.entidades.CobrarSeguros;

public interface ICobrarSeguros {

	List<CobrarSeguros> buscarFacturasNaoPagaPorSeguradoras();
	List<CobrarSeguros> buscarFacturasNaoPagaPorConsultas(int cods);
	List<CobrarSeguros> buscarFacturasNaoPagaPorExames(int cods);
	List<CobrarSeguros> buscarFacturasNaoPagaPorSGerais(int cods);
	List<CobrarSeguros> buscarFacturasNaoPagaPorProdutos(int cods);
	
	List<CobrarSeguros> buscarFacturasNaoPagaPorConsultas(int cods,String dataIn,String DateFn);
	List<CobrarSeguros> buscarFacturasNaoPagaPorExames(int cods,String dataIn,String DateFn);
	List<CobrarSeguros> buscarFacturasNaoPagaPorSGerais(int cods,String dataIn,String DateFn);
	List<CobrarSeguros> buscarFacturasNaoPagaPorProdutos(int cods,String dataIn,String DateFn);
	
	List<CobrarSeguros> buscarFacturasNaoPagaPorGeral(int cods);
	List<CobrarSeguros> buscarFacturasCobrando(int cods);
	List<CobrarSeguros> buscarFacturasCobrandoExames(int cods);
	List<CobrarSeguros> buscarFacturasCobrandoSGerais(int cods);
	List<CobrarSeguros> buscarFacturasCobrandoProdutos(int cods);
	List<CobrarSeguros> buscarFacturasNaoPagaPorDetalhar(int cods,int codp);
	
	CobrarSeguros getDetalhesSeguradora(int cods);
	CobrarSeguros getDetalhesFacturaCriada(int cods);	
	CobrarSeguros getDetalhesResumoFacCriada(String cods);
	int CriarFacturaPorCobrarConvenio(CobrarSeguros cs);
	void CriarFacturaPorCobrarConvenioLinhas(CobrarSeguros cs);
	
	List<CobrarSeguros> buscarFacturasCriadasPorSeguradoras();
	List<CobrarSeguros> buscarFacturasPagasPorSeguradoras();
	List<CobrarSeguros> buscarFacturasCriadasPorSeguradoras(String param);
	List<CobrarSeguros> buscarFacturasCriadasPorSegurado(int cods);
	List<CobrarSeguros> buscarFacturasCriadasPorConvenio(int cods);
	List<CobrarSeguros> getDetalhesFacturasCriada(int cods);
	List<CobrarSeguros> getFacturasPorPagar(int cods);
	List<CobrarSeguros> getFacturasPorPagarItens(int cods);
	int PagarFacturasPorConvenios(CobrarSeguros cs);
	void PagarFacturasPorConveniosEfectuados(CobrarSeguros cs);
	void PagarFacturasPorConveniosDeposito(CobrarSeguros cs);
	void PagarFacturasPorConveniosCheque(CobrarSeguros cs);
	void PagarFacturasPorConveniosTransferencia(CobrarSeguros cs);
	void PagarFacturasPorConveniosCredito(CobrarSeguros cs);
	List<CobrarSeguros> getFacturasPagas(int aux_2);
	CobrarSeguros getFacturasPagasDetalhe(int aux_2);
}
