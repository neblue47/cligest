package ao.co.cligest.util;

 
import java.util.ArrayList;

import ao.co.cligest.beans.BancoUEmergenciaBean;
import ao.co.cligest.beans.ConsultaExternaBean;
import ao.co.cligest.beans.InternamentoBean;
import ao.co.cligest.beans.LaboratorioBean;
import ao.co.cligest.beans.PagamentoBean;
import ao.co.cligest.entidades.Diverso;
import ao.co.cligest.entidades.Servico;
import ao.co.cligest.relatoriobean.PacienteExame;

public class ListaCarrinho   {

	/**
	 * 
	 */
	 
	private ArrayList<CarEntidade> lista = new ArrayList<CarEntidade>();
	private ArrayList<CarEntidade> farmacos = new ArrayList<CarEntidade>();
	private ArrayList<CarEntidade> exames = new ArrayList<CarEntidade>();
	private ArrayList<CarEntidade> beneficio = new ArrayList<CarEntidade>();
	private ArrayList<CarEntidade> exameTQ = new ArrayList<CarEntidade>();
	private ArrayList<CarEntidade> beneficioExame = new ArrayList<CarEntidade>();
	private ArrayList<CarEntidade> benefServicoGerais = new ArrayList<CarEntidade>();
	private ArrayList<CarEntidade> benefProdutos = new ArrayList<CarEntidade>();
	private ArrayList<CarEntidade> dados = new ArrayList<CarEntidade>();
	private ArrayList<CarEntidade> hipotese = new ArrayList<CarEntidade>();
	private ArrayList<CarEntidade> exclinico = new ArrayList<CarEntidade>();
	private ArrayList<CarEntidade> tratamento = new ArrayList<CarEntidade>();
	private ArrayList<ConsultaExternaBean> consultas = new ArrayList<ConsultaExternaBean>();
	private ArrayList<InternamentoBean> internados = new ArrayList<InternamentoBean>();
	private ArrayList<BancoUEmergenciaBean> banco_ue = new ArrayList<BancoUEmergenciaBean>();
	private ArrayList<PagamentoBean> pagamento = new ArrayList<PagamentoBean>();
	private ArrayList<PacienteExame> examesRelat = new ArrayList<PacienteExame>();
	private ArrayList<LaboratorioBean> examesLab = new ArrayList<LaboratorioBean>();
	private ArrayList<Diverso> lsDados = new ArrayList<Diverso>(); 
	
	public void adDiagnostico(CarEntidade item){
		lista.add(item);
	}
	public void setDiagnostico(int i,CarEntidade item){
		lista.set(i, item);
	}
	public void delDiagnostico(int i){
		lista.remove(i);
	}
	public ArrayList<CarEntidade> getLista() {
		return lista;
	}
	
	public int tamanho (){
		return lista.size();
	}
	
	public void adFarmacos(CarEntidade item){
		farmacos.add(item);
	}
	public void setFarmacos(int i,CarEntidade item){
		farmacos.set(i, item);
	}
	public void delFarmacos(int i){
		farmacos.remove(i);
	}
	
	
	public int tam_fmc (){
		return farmacos.size();
	}
	
//	------------------------ inicio Exame Teste de Qualidade
	public void adExameTQ(CarEntidade item){
		exameTQ.add(item);
	}
	public void setExameTQ(int i,CarEntidade item){
		exameTQ.set(i, item);
	}
	public void delExameTQ(int i){
		exameTQ.remove(i);
	}
	public ArrayList<CarEntidade> getExameTQ() {
		return exameTQ;
	}
	public int tamExameTQ (){
		return exameTQ.size();
	}
	
//	------------------------ inicio Beneficios Serviços Gerais
	public void adBenefservicoGerais(CarEntidade item){
		benefServicoGerais.add(item);
	}
	public void setBenefservicoGerais(int i,CarEntidade item){
		benefServicoGerais.set(i, item);
	}
	public void delBenefservicoGerais(int i){
		benefServicoGerais.remove(i);
	}
	public ArrayList<CarEntidade> getBenefservicoGrais() {
		return benefServicoGerais;
	}
	public int tamBenefservicoGerais (){
		return benefServicoGerais.size();
	}
//	------------------------ fim Beneficios Serviços Gerais
//	------------------------ Começo de beneficios de produtos
	public ArrayList<CarEntidade> getBenefProdutos() {
		return benefProdutos;
	}
	public void setBenefProdutos(ArrayList<CarEntidade> benefProdutos) {
		this.benefProdutos = benefProdutos;
	}
	
	public void adBenefProdutos(CarEntidade item){
		benefProdutos.add(item);
	}
	public void setBenefProdutos(int i,CarEntidade item){
		benefProdutos.set(i, item);
	}
	public void delBenefProdutos(int i){
		benefProdutos.remove(i);
	}
	public int tamBenefProdutos (){
		return benefProdutos.size();
	}
//	------------------------ final de beneficios de produtos
	
	public ArrayList<CarEntidade> getExames() {
		return exames;
	}
	 
	
	public void adExames(CarEntidade item){
		exames.add(item);
	}
	public void setExames(int i,CarEntidade item){
		exames.set(i, item);
	}
	public void delExames(int i){
		exames.remove(i);
	}
	
	public int tam_exms (){
		return exames.size();
	}
	
	public void adBeneficios(CarEntidade item){
		beneficio.add(item);
	}
	public void setBeneficios(int i,CarEntidade item){
		beneficio.set(i, item);
	}
	public void delBeneficios(int i){
		beneficio.remove(i);
	}
	public ArrayList<CarEntidade> getBeneficio() {
		return beneficio;
	}
	
	public int tamBeneficios (){
		return beneficio.size();
	}
//	----------------------------<> Beneficio consultas final

//	----------------------------<> Beneficio Exames
	public void adBeneficiosExames(CarEntidade item){
		beneficioExame.add(item);
	}
	public void setBeneficiosExame(int i,CarEntidade item){
		beneficioExame.set(i, item);
	}
	public void delBeneficiosExame(int i){
		beneficioExame.remove(i);
	}
	public ArrayList<CarEntidade> getBeneficiosExame() {
		return beneficioExame;
	}
	
	public int tamBeneficiosExame(){
		return beneficioExame.size();
	}
//	----------------------------<> Beneficio Exames final
	
	public void adDados(CarEntidade item){
		dados.add(item);
	}
	
	
	public ArrayList<CarEntidade> getDados() {
		return dados;
	}
	
	public void setDados(int i,CarEntidade item){
		dados.set(i, item);
	}
	
	public void delDados(int i){
		dados.remove(i);
	}
	
	public int tamDados(){
		return dados.size();
	}
	
	public void LimparDados(){
		 dados.clear();
	}
	public ArrayList<CarEntidade> getFarmacos() {
		return farmacos;
	}
	public void setFarmacos(ArrayList<CarEntidade> farmacos) {
		this.farmacos = farmacos;
	}
	
	// ------------ Hipoteses --------------- //
	public ArrayList<CarEntidade> getHipotese() {
		return hipotese;
	}
	public void setHipotese(ArrayList<CarEntidade> hipotese) {
		this.hipotese = hipotese;
	}
	
	public void adHipotese(CarEntidade item){
		hipotese.add(item);
	}
	public void setHipotese(int i,CarEntidade item){
		hipotese.set(i, item);
	}
	public void delHipotese(int i){
		hipotese.remove(i);
	}
	
	public int tamHipotese() {
		return hipotese.size();
	}
	
	public ArrayList<CarEntidade> getExclinico() {
		return exclinico;
	}
	public void setExclinico(int i,CarEntidade item) {
		exclinico.set(i, item);
	}
	
	public void adExclinico(CarEntidade item){
		exclinico.add(item);
	}
	
	public void remExclinico(int i){
		exclinico.remove(i);
	}
	
	public int tamExclinico(){
		return exclinico.size();
	}
	/**         Tratamento         */ 
	
	public ArrayList<CarEntidade> getTratamento() {
		return tratamento;
	}
	public void setTratamento(ArrayList<CarEntidade> tratamento) {
		this.tratamento = tratamento;
	}
	
	public void adTratamento(CarEntidade item){
		tratamento.add(item);
	}
	public void setTratamento(int i,CarEntidade item){
		tratamento.set(i, item);
	}
	public void delTratamento(int i){
		tratamento.remove(i);
	}
	
	public void eliminaTratmentoo(CarEntidade item){
		tratamento.remove(item);
	}
	public int tamTratamento() {
		return tratamento.size();
	}
	

/**        Historico de Consultas Externas         */ 
	
	
	public ArrayList<ConsultaExternaBean> getConsultas() {
		return consultas;
	}
	public void adConsultas(ConsultaExternaBean item) {
		consultas.add(item);
	}
	public void setConsultas(int i,ConsultaExternaBean item) {
		consultas.set(i, item);
	}
	
	public void delConsultas(int i) {
		consultas.remove(i);
	}
	
	public void tamConsultas(int i) {
		consultas.size();
	}
	
	public ArrayList<InternamentoBean> getInternados() {
		return internados;
	}
	public void adInternados(InternamentoBean item) {
		internados.add(item);
	}
	public void setInternados(int i,InternamentoBean item) {
		internados.set(i, item);
	}
	public void delInternamento(int i) {
		internados.remove(i);
	}
	
	public void tamInternamento(int i) {
		internados.size();
	}
	
	public ArrayList<BancoUEmergenciaBean> getBanco_ue() {
		return banco_ue;
	}
	public void adBanco_ue(BancoUEmergenciaBean item) {
		banco_ue.add(item);
	}
	public void setBanco_ue(int i,BancoUEmergenciaBean item) {
		banco_ue.set(i, item);
	}
	
	public void remBanco_ue(int i) {
		banco_ue.remove(i);
	}
	public void tamBanco_ue() {
		banco_ue.size();
	}
	public ArrayList<PagamentoBean> getPagamento() {
		return pagamento;
	}
	public void adPagamento(PagamentoBean item) {
		pagamento.add(item);
	}
	public void setPagamento(int i,PagamentoBean item) {
		pagamento.set(i, item);
	}
	public ArrayList<PacienteExame> getExamesRelat() {
		return examesRelat;
	}
	public void setExamesRelat(int i,PacienteExame item) {
		examesRelat.set(i, item);
	}
	public void adExamesRelat(PacienteExame item){
		examesRelat.add(item);
	}
	
	public void setExamesLab(int i,LaboratorioBean item) {
		examesLab.set(i, item);
	}
	public void adExamesLab(LaboratorioBean item){
		examesLab.add(item);
	}
	public ArrayList<LaboratorioBean> getExamesLab() {
		return examesLab;
	}
	
	public ArrayList<Diverso> getLsDados() {
		return lsDados;
	}
	public void adLsDados( Diverso item) {
		lsDados.add(item);
	}
	public void setLsDados(int i,Diverso item) {
		lsDados.set(i, item);
	}
	public void remLsDados(int i) {
		lsDados.remove(i);
	}
	public int tamLsDados() {
		return lsDados.size();
		
	}
	
	
	
	
	
}
