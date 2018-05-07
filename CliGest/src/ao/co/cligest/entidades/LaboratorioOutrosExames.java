package ao.co.cligest.entidades;

public class LaboratorioOutrosExames {
	
    private Integer idExamesClinicosCadastrados;
    private Integer fk_grupo;
    private String exames_clinicos_cadastrados;
    private String nota_explicativa; 
    private Integer codigo_barra;
    private Integer genero;
    private Integer disponibilidade;
    private Integer categoria;
    private String recomendacoes;
    private String abreviacao;
    private Integer fk_amostra;
    private Integer fk_tipo_de_exame_noapp;
    private int FK_tipo_servicoEx;
    private Integer tipoExaApp;
    private int id_sub_grupo;
    
    
    public int getId_sub_grupo() {
		return id_sub_grupo;
	}
	public void setId_sub_grupo(int id_sub_grupo) {
		this.id_sub_grupo = id_sub_grupo;
	}
	//
    private String txtGroupo_exame;
    private String tipo_deexame_noaplicativo;
    private String tipo_deamostra;
    
	public String getTipo_deamostra() {
		return tipo_deamostra;
	}
	public void setTipo_deamostra(String tipo_deamostra) {
		this.tipo_deamostra = tipo_deamostra;
	}
	public String getTxtGroupo_exame() {
		return txtGroupo_exame;
	}
	public void setTxtGroupo_exame(String txtGroupo_exame) {
		this.txtGroupo_exame = txtGroupo_exame;
	}
	public String getTipo_deexame_noaplicativo() {
		return tipo_deexame_noaplicativo;
	}
	public void setTipo_deexame_noaplicativo(String tipo_deexame_noaplicativo) {
		this.tipo_deexame_noaplicativo = tipo_deexame_noaplicativo;
	}
	public Integer getTipoExaApp() {
		return tipoExaApp;
	}
	public void setTipoExaApp(Integer tipoExaApp) {
		this.tipoExaApp = tipoExaApp;
	}
	public Integer getIdExamesClinicosCadastrados() {
		return idExamesClinicosCadastrados;
	}
	public void setIdExamesClinicosCadastrados(Integer idExamesClinicosCadastrados) {
		this.idExamesClinicosCadastrados = idExamesClinicosCadastrados;
	}
	public Integer getFk_grupo() {
		return fk_grupo;
	}
	public void setFk_grupo(Integer fk_grupo) {
		this.fk_grupo = fk_grupo;
	}
	public String getExames_clinicos_cadastrados() {
		return exames_clinicos_cadastrados;
	}
	public void setExames_clinicos_cadastrados(String exames_clinicos_cadastrados) {
		this.exames_clinicos_cadastrados = exames_clinicos_cadastrados;
	}
	public String getNota_explicativa() {
		return nota_explicativa;
	}
	public void setNota_explicativa(String nota_explicativa) {
		this.nota_explicativa = nota_explicativa;
	}
	public Integer getCodigo_barra() {
		return codigo_barra;
	}
	public void setCodigo_barra(Integer codigo_barra) {
		this.codigo_barra = codigo_barra;
	}
	public Integer getGenero() {
		return genero;
	}
	public void setGenero(Integer genero) {
		this.genero = genero;
	}
	public Integer getDisponibilidade() {
		return disponibilidade;
	}
	public void setDisponibilidade(Integer disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	public Integer getCategoria() {
		return categoria;
	}
	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}
	public String getRecomendacoes() {
		return recomendacoes;
	}
	public void setRecomendacoes(String recomendacoes) {
		this.recomendacoes = recomendacoes;
	}
	public String getAbreviacao() {
		return abreviacao;
	}
	public void setAbreviacao(String abreviacao) {
		this.abreviacao = abreviacao;
	}
	public Integer getFk_amostra() {
		return fk_amostra;
	}
	public void setFk_amostra(Integer fk_amostra) {
		this.fk_amostra = fk_amostra;
	}
	public Integer getFk_tipo_de_exame_noapp() {
		return fk_tipo_de_exame_noapp;
	}
	public void setFk_tipo_de_exame_noapp(Integer fk_tipo_de_exame_noapp) {
		this.fk_tipo_de_exame_noapp = fk_tipo_de_exame_noapp;
	}
	public int getFK_tipo_servicoEx() {
		return FK_tipo_servicoEx;
	}
	public void setFK_tipo_servicoEx(int fK_tipo_servicoEx) {
		FK_tipo_servicoEx = fK_tipo_servicoEx;
	}
	
}
