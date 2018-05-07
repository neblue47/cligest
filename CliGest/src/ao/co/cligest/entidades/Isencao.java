package ao.co.cligest.entidades;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Isencao extends Paciente{
    
	private Integer FK_isencao;
    private String numero_daisencao;
    private Double total_taxa_emkz;
    private Double total_isentado;
    private String razao_isencao;
    private Calendar data_isencao;
    private Integer qtd_exa_isentado;
    
    public Integer getQtd_exa_isentado() {
		return qtd_exa_isentado;
	}
	public void setQtd_exa_isentado(Integer qtd_exa_isentado) {
		this.qtd_exa_isentado = qtd_exa_isentado;
	}
	private Integer  id_categoriaisencao;
    private String categoriaisencao;
    
    private Integer id_isencao_consulta;
    private Integer FK_taxa;
    private Double taxa_consulta;
    private Double taxa_exame;
    private Double preco_isencao_consulta;
    private Double preco_isencao_exame;
    private Integer FK_numero_servico;
    
    private Integer FK_tratamento_isentado;
    private Double preco_isencao_trat;
    private Integer qtd_trat_isentado;
    private Double tx_trat_isentado;
    private Double total_tx_trat_isent;
    private Double taxas;
    private Double tota_trat_isentado;
    
    private Integer FK_isencao_farmaco;
    private Integer FK_farmaco_isentado;
    private Double preco_isencao;
    private Integer qtd_farmaco_isentado;
    private Double tx_farmaco_isentado;
    private Double total_tx_farm_isent;
    private Double tota_farmaco_isentado;
    
    private Integer FK_receita;
    private Double txa_percentual;
    private Double txa_emkwanza;
    private String produto;
    private Integer FK_produto;
    
	
    public int getFK_isencao() {
		return FK_isencao;
	}
	public void setFK_isencao(Integer id_isencao) {
		this.FK_isencao = id_isencao;
	}
	public String getNumero_daisencao() {
		return numero_daisencao;
	}
	public void setNumero_daisencao(String numero_daisencao) {
		this.numero_daisencao = numero_daisencao;
	}
	public Double getTotal_taxa_emkz() {
		return total_taxa_emkz;
	}
	public void setTotal_taxa_emkz(Double total_taxa_emkz) {
		this.total_taxa_emkz = total_taxa_emkz;
	}
	public Double getTotal_isentado() {
		return total_isentado;
	}
	public void setTotal_isentado(Double total_isentado) {
		this.total_isentado = total_isentado;
	}
	public String getRazao_isencao() {
		return razao_isencao;
	}
	public void setRazao_isencao(String razao_isencao) {
		this.razao_isencao = razao_isencao;
	}
	public Calendar getData_isencao() {
		return data_isencao;
	}
	public void setData_isencao(Calendar data_isencao) {
		this.data_isencao = data_isencao;
	}
	public Integer getId_categoriaisencao() {
		return id_categoriaisencao;
	}
	public void setId_categoriaisencao(Integer id_categoriaisencao) {
		this.id_categoriaisencao = id_categoriaisencao;
	}
	public String getCategoriaisencao() {
		return categoriaisencao;
	}
	public void setCategoriaisencao(String categoriaisencao) {
		this.categoriaisencao = categoriaisencao;
	}
	public Integer getId_isencao_consulta() {
		return id_isencao_consulta;
	}
	public void setId_isencao_consulta(Integer id_isencao_consulta) {
		this.id_isencao_consulta = id_isencao_consulta;
	}
	public Double getTaxa_consulta() {
		return taxa_consulta;
	}
	public void setTaxa_consulta(Double taxa_consulta) {
		this.taxa_consulta = taxa_consulta;
	}
	public Double getPreco_isencao_consulta() {
		return preco_isencao_consulta;
	}
	public void setPreco_isencao_consulta(Double preco_isencao_consulta) {
		this.preco_isencao_consulta = preco_isencao_consulta;
	}
	public Integer getFK_taxa() {
		return FK_taxa;
	}
	public void setFK_taxa(Integer fK_taxa) {
		FK_taxa = fK_taxa;
	}
	public Integer getFK_numero_servico() {
		return FK_numero_servico;
	}
	public void setFK_numero_servico(Integer fK_numero_servico) {
		FK_numero_servico = fK_numero_servico;
	}
	public Double getTaxa_exame() {
		return taxa_exame;
	}
	public void setTaxa_exame(Double taxa_exame) {
		this.taxa_exame = taxa_exame;
	}
	public Double getPreco_isencao_exame() {
		return preco_isencao_exame;
	}
	public void setPreco_isencao_exame(Double preco_isencao_exame) {
		this.preco_isencao_exame = preco_isencao_exame;
	}
	public Integer getFK_tratamento_isentado() {
		return FK_tratamento_isentado;
	}
	public void setFK_tratamento_isentado(Integer fK_tratamento_isentado) {
		FK_tratamento_isentado = fK_tratamento_isentado;
	}
	public Double getPreco_isencao_trat() {
		return preco_isencao_trat;
	}
	public void setPreco_isencao_trat(Double preco_isencao_trat) {
		this.preco_isencao_trat = preco_isencao_trat;
	}
	public Integer getQtd_trat_isentado() {
		return qtd_trat_isentado;
	}
	public void setQtd_trat_isentado(Integer qtd_trat_isentado) {
		this.qtd_trat_isentado = qtd_trat_isentado;
	}
	public Double getTx_trat_isentado() {
		return tx_trat_isentado;
	}
	public void setTx_trat_isentado(Double tx_trat_isentado) {
		this.tx_trat_isentado = tx_trat_isentado;
	}
	public Double getTotal_tx_trat_isent() {
		return total_tx_trat_isent;
	}
	public void setTotal_tx_trat_isent(Double total_tx_trat_isent) {
		this.total_tx_trat_isent = total_tx_trat_isent;
	}
	public Double getTota_trat_isentado() {
		return tota_trat_isentado;
	}
	public void setTota_trat_isentado(Double tota_trat_isentado) {
		this.tota_trat_isentado = tota_trat_isentado;
	}
	public Integer getFK_isencao_farmaco() {
		return FK_isencao_farmaco;
	}
	public void setFK_isencao_farmaco(Integer fK_isencao_farmaco) {
		FK_isencao_farmaco = fK_isencao_farmaco;
	}
	public Integer getFK_farmaco_isentado() {
		return FK_farmaco_isentado;
	}
	public void setFK_farmaco_isentado(Integer fK_farmaco_isentado) {
		FK_farmaco_isentado = fK_farmaco_isentado;
	}
	public Double getPreco_isencao() {
		return preco_isencao;
	}
	public void setPreco_isencao(Double preco_isencao) {
		this.preco_isencao = preco_isencao;
	}
	public Integer getQtd_farmaco_isentado() {
		return qtd_farmaco_isentado;
	}
	public void setQtd_farmaco_isentado(Integer qtd_farmaco_isentado) {
		this.qtd_farmaco_isentado = qtd_farmaco_isentado;
	}
	public Double getTx_farmaco_isentado() {
		return tx_farmaco_isentado;
	}
	public void setTx_farmaco_isentado(Double tx_farmaco_isentado) {
		this.tx_farmaco_isentado = tx_farmaco_isentado;
	}
	public Double getTotal_tx_farm_isent() {
		return total_tx_farm_isent;
	}
	public void setTotal_tx_farm_isent(Double total_tx_farm_isent) {
		this.total_tx_farm_isent = total_tx_farm_isent;
	}
	public Double getTota_farmaco_isentado() {
		return tota_farmaco_isentado;
	}
	public void setTota_farmaco_isentado(Double tota_farmaco_isentado) {
		this.tota_farmaco_isentado = tota_farmaco_isentado;
	}
	public Integer getFK_receita() {
		return FK_receita;
	}
	public void setFK_receita(Integer fK_receita) {
		FK_receita = fK_receita;
	}
	 
	
 
	public Double getTxa_percentual() {
		return txa_percentual;
	}
	public void setTxa_percentual(Double txa_percentual) {
		this.txa_percentual = txa_percentual;
	}
	public Double getTxa_emkwanza() {
		return txa_emkwanza;
	}
	public void setTxa_emkwanza(Double txa_emkwanza) {
		this.txa_emkwanza = txa_emkwanza;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public Integer getFK_produto() {
		return FK_produto;
	}
	public void setFK_produto(Integer fK_produto) {
		FK_produto = fK_produto;
	}
	public Double getTaxas() {
		return taxas;
	}
	public void setTaxas(Double taxas) {
		this.taxas = taxas;
	}
    
    
	
 }
