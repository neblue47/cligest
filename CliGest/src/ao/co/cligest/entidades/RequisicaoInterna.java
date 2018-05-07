package ao.co.cligest.entidades;

import java.util.Date;
import java.util.Calendar;

public class RequisicaoInterna  {

	private int id_requi_interna;
	private String numero_requi_interna;
	private String obs_requi_interna;
	private String nome_comercial;
	private String nome_quimico;
	private int fk_funcionario;
	private int indice;
	private Calendar data_requi_interna;
	private int FK_requi_interna;
	private int fk_produto_requi_interna;
	private int qtd_requi_interna;
	private int qtd_requi_processada;
	private int qtd_requi_recebida;
	private String requisicao_original;
	private String nomeRequerente_interno;
	private int FK_requerente_interno;
	private int fk_req_processada;
	private double total;
	private int fk_produto;
	private Calendar data_registo;
	private double preco_dcompra;
	private String nome;
	private String forma;
	private double total_h_ajuste;
	private double preco_dvenda;
	private int tempoExpiracao;
	

	private Integer diferenca_positiva;
	private Integer diferenca_negativa;
	private double preco_negativo;
	private double preco_positivo;
	private String numero_dif_inventario;
	private Integer id_num_difinventario;
	
	private int id_receb_requi_farmacia;
	private String numero_receb_requi_farmacia;
	private int fk_funcionario_receb_requi_farmacia;
	private int data_receb_requi_farmacia;
	private int fk_local_armaze_requi_receb_farmacia_poritem;
	private int fk_ala_requi_receb_farmacia_poritem;
	private int fk_montra_requi_receb_farmacia_poritem;
	private int fk_prateleira_requi_receb_farmacia_poritem;
	private String data_expiracao;
	private String num_lote;
	private String codigo_barra;
	private Calendar caducidade_item;
	
	private int armazenamento;
	private int bloco;
	private int montra;
	private int pratileira;
	private String nome_armazenamento;
	private String nome_funcionario;
	private String nome_bloco;
	private String nome_montra;
	private String nome_pratileira;
	
//	Requisição Internas
	private int id_requi_proce_farmacia;
	private Calendar data_id_requi_proce_farmacia;
	private int fk_requerente;
	private int fk_requisicao_original;
	private String numero_requi_proce_farmacia;
	private int dias_restante;
	
	// Devolução Farmacia
	private int id_dev_requi_int_farmacia;
	private String numero_dev_requi_int_farmacia;
	private Calendar data_dev_requi_int_farmacia;
	private String razao_dev_requi_int_farmacia;
	private int fk_requisicao_recebida;
	
	// devolução Farmacia
	private int id_dev_requi_int_farm_comlinhas;
	private int FK_dev_requi_int_farmacia;
	private int qtd_dev_requi_int_farmacia;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public int getId_requi_interna() {
		return id_requi_interna;
	}
	public void setId_requi_interna(int id_requi_interna) {
		this.id_requi_interna = id_requi_interna;
	}
	public String getNumero_requi_interna() {
		return numero_requi_interna;
	}
	public void setNumero_requi_interna(String numero_requi_interna) {
		this.numero_requi_interna = numero_requi_interna;
	}
	public String getObs_requi_interna() {
		return obs_requi_interna;
	}
	public void setObs_requi_interna(String obs_requi_interna) {
		this.obs_requi_interna = obs_requi_interna;
	}
	public int getFk_funcionario() {
		return fk_funcionario;
	}
	public void setFk_funcionario(int fk_funcionario) {
		this.fk_funcionario = fk_funcionario;
	}
	public Calendar getData_requi_interna() {
		return data_requi_interna;
	}
	public void setData_requi_interna(Calendar data_requi_interna) {
		this.data_requi_interna = data_requi_interna;
	}
	public int getFK_requi_interna() {
		return FK_requi_interna;
	}
	public void setFK_requi_interna(int fK_requi_interna) {
		FK_requi_interna = fK_requi_interna;
	}
	public int getFk_produto_requi_interna() {
		return fk_produto_requi_interna;
	}
	public void setFk_produto_requi_interna(int fk_produto_requi_interna) {
		this.fk_produto_requi_interna = fk_produto_requi_interna;
	}
	public int getQtd_requi_interna() {
		return qtd_requi_interna;
	}
	public void setQtd_requi_interna(int qtd_requi_interna) {
		this.qtd_requi_interna = qtd_requi_interna;
	}
	public int getId_receb_requi_farmacia() {
		return id_receb_requi_farmacia;
	}
	public void setId_receb_requi_farmacia(int id_receb_requi_farmacia) {
		this.id_receb_requi_farmacia = id_receb_requi_farmacia;
	}
	public String getNumero_receb_requi_farmacia() {
		return numero_receb_requi_farmacia;
	}
	public void setNumero_receb_requi_farmacia(String numero_receb_requi_farmacia) {
		this.numero_receb_requi_farmacia = numero_receb_requi_farmacia;
	}
	public int getFk_funcionario_receb_requi_farmacia() {
		return fk_funcionario_receb_requi_farmacia;
	}
	public void setFk_funcionario_receb_requi_farmacia(
			int fk_funcionario_receb_requi_farmacia) {
		this.fk_funcionario_receb_requi_farmacia = fk_funcionario_receb_requi_farmacia;
	}
	public int getData_receb_requi_farmacia() {
		return data_receb_requi_farmacia;
	}
	public void setData_receb_requi_farmacia(int data_receb_requi_farmacia) {
		this.data_receb_requi_farmacia = data_receb_requi_farmacia;
	}
	public int getFk_local_armaze_requi_receb_farmacia_poritem() {
		return fk_local_armaze_requi_receb_farmacia_poritem;
	}
	public void setFk_local_armaze_requi_receb_farmacia_poritem(
			int fk_local_armaze_requi_receb_farmacia_poritem) {
		this.fk_local_armaze_requi_receb_farmacia_poritem = fk_local_armaze_requi_receb_farmacia_poritem;
	}
	public int getFk_ala_requi_receb_farmacia_poritem() {
		return fk_ala_requi_receb_farmacia_poritem;
	}
	public void setFk_ala_requi_receb_farmacia_poritem(
			int fk_ala_requi_receb_farmacia_poritem) {
		this.fk_ala_requi_receb_farmacia_poritem = fk_ala_requi_receb_farmacia_poritem;
	}
	public int getFk_montra_requi_receb_farmacia_poritem() {
		return fk_montra_requi_receb_farmacia_poritem;
	}
	public void setFk_montra_requi_receb_farmacia_poritem(
			int fk_montra_requi_receb_farmacia_poritem) {
		this.fk_montra_requi_receb_farmacia_poritem = fk_montra_requi_receb_farmacia_poritem;
	}
	public int getFk_prateleira_requi_receb_farmacia_poritem() {
		return fk_prateleira_requi_receb_farmacia_poritem;
	}
	public void setFk_prateleira_requi_receb_farmacia_poritem(
			int fk_prateleira_requi_receb_farmacia_poritem) {
		this.fk_prateleira_requi_receb_farmacia_poritem = fk_prateleira_requi_receb_farmacia_poritem;
	}
	public int getArmazenamento() {
		return armazenamento;
	}
	public void setArmazenamento(int armazenamento) {
		this.armazenamento = armazenamento;
	}
	public int getBloco() {
		return bloco;
	}
	public void setBloco(int bloco) {
		this.bloco = bloco;
	}
	public int getMontra() {
		return montra;
	}
	public void setMontra(int montra) {
		this.montra = montra;
	}
	public int getPratileira() {
		return pratileira;
	}
	public void setPratileira(int pratileira) {
		this.pratileira = pratileira;
	}
	public String getNome_armazenamento() {
		return nome_armazenamento;
	}
	public void setNome_armazenamento(String nome_armazenamento) {
		this.nome_armazenamento = nome_armazenamento;
	}
	public String getNome_bloco() {
		return nome_bloco;
	}
	public void setNome_bloco(String nome_bloco) {
		this.nome_bloco = nome_bloco;
	}
	public String getNome_montra() {
		return nome_montra;
	}
	public void setNome_montra(String nome_montra) {
		this.nome_montra = nome_montra;
	}
	public String getNome_pratileira() {
		return nome_pratileira;
	}
	public void setNome_pratileira(String nome_pratileira) {
		this.nome_pratileira = nome_pratileira;
	}
//	Requisição Internas
	public int getId_requi_proce_farmacia() {
		return id_requi_proce_farmacia;
	}
	public void setId_requi_proce_farmacia(int id_requi_proce_farmacia) {
		this.id_requi_proce_farmacia = id_requi_proce_farmacia;
	}
	public Calendar getData_id_requi_proce_farmacia() {
		return data_id_requi_proce_farmacia;
	}
	public void setData_id_requi_proce_farmacia(
			Calendar data_id_requi_proce_farmacia) {
		this.data_id_requi_proce_farmacia = data_id_requi_proce_farmacia;
	}
	public int getFk_requerente() {
		return fk_requerente;
	}
	public void setFk_requerente(int fk_requerente) {
		this.fk_requerente = fk_requerente;
	}
	public int getFk_requisicao_original() {
		return fk_requisicao_original;
	}
	public void setFk_requisicao_original(int fk_requisicao_original) {
		this.fk_requisicao_original = fk_requisicao_original;
	}
	public String getNumero_requi_proce_farmacia() {
		return numero_requi_proce_farmacia;
	}
	public void setNumero_requi_proce_farmacia(String numero_requi_proce_farmacia) {
		this.numero_requi_proce_farmacia = numero_requi_proce_farmacia;
	}
	public String getData_expiracao() {
		return data_expiracao;
	}
	public void setData_expiracao(String data_expiracao) {
		this.data_expiracao = data_expiracao;
	}
	public String getNum_lote() {
		return num_lote;
	}
	public void setNum_lote(String num_lote) {
		this.num_lote = num_lote;
	}
	public String getNome_funcionario() {
		return nome_funcionario;
	}
	public void setNome_funcionario(String nome_funcionario) {
		this.nome_funcionario = nome_funcionario;
	}
	public String getRequisicao_original() {
		return requisicao_original;
	}
	public void setRequisicao_original(String requisicao_original) {
		this.requisicao_original = requisicao_original;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getNomeRequerente_interno() {
		return nomeRequerente_interno;
	}
	public void setNomeRequerente_interno(String nomeRequerente_interno) {
		this.nomeRequerente_interno = nomeRequerente_interno;
	}
	public int getFK_requerente_interno() {
		return FK_requerente_interno;
	}
	public void setFK_requerente_interno(int fK_requerente_interno) {
		FK_requerente_interno = fK_requerente_interno;
	}
	public int getFk_req_processada() {
		return fk_req_processada;
	}
	public void setFk_req_processada(int fk_req_processada) {
		this.fk_req_processada = fk_req_processada;
	}
	public String getCodigo_barra() {
		return codigo_barra;
	}
	public void setCodigo_barra(String codigo_barra) {
		this.codigo_barra = codigo_barra;
	}
	public int getFk_produto() {
		return fk_produto;
	}
	public void setFk_produto(int fk_produto) {
		this.fk_produto = fk_produto;
	}
	public String getNome_comercial() {
		return nome_comercial;
	}
	public void setNome_comercial(String nome_comercial) {
		this.nome_comercial = nome_comercial;
	}
	public int getDias_restante() {
		return dias_restante;
	}
	public void setDias_restante(int dias_restante) {
		this.dias_restante = dias_restante;
	}
	public Calendar getData_registo() {
		return data_registo;
	}
	public void setData_registo(Calendar data_registo) {
		this.data_registo = data_registo;
	}
	public int getId_dev_requi_int_farm_comlinhas() {
		return id_dev_requi_int_farm_comlinhas;
	}
	public void setId_dev_requi_int_farm_comlinhas(
			int id_dev_requi_int_farm_comlinhas) {
		this.id_dev_requi_int_farm_comlinhas = id_dev_requi_int_farm_comlinhas;
	}
	public int getFK_dev_requi_int_farmacia() {
		return FK_dev_requi_int_farmacia;
	}
	public void setFK_dev_requi_int_farmacia(int fK_dev_requi_int_farmacia) {
		FK_dev_requi_int_farmacia = fK_dev_requi_int_farmacia;
	}
	public int getQtd_dev_requi_int_farmacia() {
		return qtd_dev_requi_int_farmacia;
	}
	public void setQtd_dev_requi_int_farmacia(int qtd_dev_requi_int_farmacia) {
		this.qtd_dev_requi_int_farmacia = qtd_dev_requi_int_farmacia;
	}
	public int getId_dev_requi_int_farmacia() {
		return id_dev_requi_int_farmacia;
	}
	public void setId_dev_requi_int_farmacia(int id_dev_requi_int_farmacia) {
		this.id_dev_requi_int_farmacia = id_dev_requi_int_farmacia;
	}
	public String getNumero_dev_requi_int_farmacia() {
		return numero_dev_requi_int_farmacia;
	}
	public void setNumero_dev_requi_int_farmacia(
			String numero_dev_requi_int_farmacia) {
		this.numero_dev_requi_int_farmacia = numero_dev_requi_int_farmacia;
	}
	public Calendar getData_dev_requi_int_farmacia() {
		return data_dev_requi_int_farmacia;
	}
	public void setData_dev_requi_int_farmacia(Calendar data_dev_requi_int_farmacia) {
		this.data_dev_requi_int_farmacia = data_dev_requi_int_farmacia;
	}
	public String getRazao_dev_requi_int_farmacia() {
		return razao_dev_requi_int_farmacia;
	}
	public void setRazao_dev_requi_int_farmacia(String razao_dev_requi_int_farmacia) {
		this.razao_dev_requi_int_farmacia = razao_dev_requi_int_farmacia;
	}
	public int getFk_requisicao_recebida() {
		return fk_requisicao_recebida;
	}
	public void setFk_requisicao_recebida(int fk_requisicao_recebida) {
		this.fk_requisicao_recebida = fk_requisicao_recebida;
	}
	public double getPreco_dcompra() {
		return preco_dcompra;
	}
	public void setPreco_dcompra(double preco_dcompra) {
		this.preco_dcompra = preco_dcompra;
	}
	public Integer getDiferenca_positiva() {
		return diferenca_positiva;
	}
	public void setDiferenca_positiva(Integer diferenca_positiva) {
		this.diferenca_positiva = diferenca_positiva;
	}
	public Integer getDiferenca_negativa() {
		return diferenca_negativa;
	}
	public void setDiferenca_negativa(Integer diferenca_negativa) {
		this.diferenca_negativa = diferenca_negativa;
	}
	public double getPreco_negativo() {
		return preco_negativo;
	}
	public void setPreco_negativo(double preco_negativo) {
		this.preco_negativo = preco_negativo;
	}
	public double getPreco_positivo() {
		return preco_positivo;
	}
	public void setPreco_positivo(double preco_positivo) {
		this.preco_positivo = preco_positivo;
	}
	public String getNumero_dif_inventario() {
		return numero_dif_inventario;
	}
	public void setNumero_dif_inventario(String numero_dif_inventario) {
		this.numero_dif_inventario = numero_dif_inventario;
	}
	public Integer getId_num_difinventario() {
		return id_num_difinventario;
	}
	public void setId_num_difinventario(Integer id_num_difinventario) {
		this.id_num_difinventario = id_num_difinventario;
	}
	public int getQtd_requi_processada() {
		return qtd_requi_processada;
	}
	public void setQtd_requi_processada(int qtd_requi_processada) {
		this.qtd_requi_processada = qtd_requi_processada;
	}
	public int getQtd_requi_recebida() {
		return qtd_requi_recebida;
	}
	public void setQtd_requi_recebida(int qtd_requi_recebida) {
		this.qtd_requi_recebida = qtd_requi_recebida;
	}
	public String getNome_quimico() {
		return nome_quimico;
	}
	public void setNome_quimico(String nome_quimico) {
		this.nome_quimico = nome_quimico;
	}

	public double getTotal_h_ajuste() {
		return total_h_ajuste;
	}
	public void setTotal_h_ajuste(double total_h_ajuste) {
		this.total_h_ajuste = total_h_ajuste;
	}
	public String getForma() {
		return forma;
	}
	public void setForma(String forma) {
		this.forma = forma;
	}
	public double getPreco_dvenda() {
		return preco_dvenda;
	}
	public void setPreco_dvenda(double preco_dvenda) {
		this.preco_dvenda = preco_dvenda;
	}
	public Calendar getCaducidade_item() {
		return caducidade_item;
	}
	public void setCaducidade_item(Calendar caducidade_item) {
		 
		this.caducidade_item = caducidade_item  ;
	}
	public int getTempoExpiracao() {
		return tempoExpiracao;
	}
	public void setTempoExpiracao(int tempoExpiracao) {
		this.tempoExpiracao = tempoExpiracao;
	}
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
	
	
}
