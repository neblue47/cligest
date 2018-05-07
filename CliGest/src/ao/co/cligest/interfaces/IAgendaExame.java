package ao.co.cligest.interfaces;

import java.util.List;

import ao.co.cligest.entidades.Exames;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Servico;

public interface IAgendaExame {

	void requisitarExame(Exames ex );
	void confirmarRequisicaoExame(Exames ex);
	void alterarRequisicaoExame(Exames ex);
	void cancelarRequisicaoExame(Exames ex);
	
	Paciente listaRequisicaoExamePorId(int codcs);
	List<Paciente> listaRequisicaoExameAgendada();
	List<Servico> listaRequisicaoExames(int cods);
	List<Paciente> listaRequisicaoExameConfirmada();
	List<Paciente> listaAgendasPacientes();
	Paciente ExameParaPagar(int codcs);
	int inserirnumerodoexame(Exames ex);
	String retornaNumExame();
}
