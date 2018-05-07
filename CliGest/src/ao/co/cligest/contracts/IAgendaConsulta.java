package ao.co.cligest.contracts;

import java.util.List;

import ao.co.cligest.entidades.Paciente;

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
	
	
	
}
