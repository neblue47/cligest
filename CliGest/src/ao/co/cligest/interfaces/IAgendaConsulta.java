package ao.co.cligest.interfaces;

import java.util.List;

import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Triagem;

public interface IAgendaConsulta {

	void adicionaConsulta(Paciente p);
	void confirmarConsulta(Paciente p);
	void alterarConsulta(Paciente p);
	void cancelarConsulta(Paciente p);
	
	Paciente listaConsultaPorId(int codcs);
	List<Paciente> listaConsultaAgendada();
	List<Paciente> listaConsultaAgendada(String query);
	List<Paciente> listaConsultaConfirmada();
	List<Paciente> listaAgendasPacientes();
	Paciente ConsultaParaPagar(int codcs);
	Paciente getConsultaConfirmada(int consultaMarcadaId);
	List<Paciente> listaConsultaConfirmadaPago();
	List<Paciente> listaPagoTriado();
	List<Paciente> listaPagoNaoTriado();
	Triagem getSinais(String codc);
	
	
	
}
