package ao.co.cligest.interfaces;

import java.util.List;

import ao.co.cligest.entidades.Fornecedor;
import ao.co.cligest.entidades.PacientePlano;
import ao.co.cligest.entidades.Plano;

public interface IPacientePlano {

	 List<PacientePlano> buscarPacientesPlano();
	 List<PacientePlano> buscarPacientesPlano(String aux);
	 PacientePlano buscarPacienteDetalhe (int id);
	 
	 List<Fornecedor> buscarAsseguradoras();
	 List<Plano> buscarPlanosSaude();
	 
	 List<Plano> buscarPlanosSaude(int assgEm);
	 
	 void novoSeguradoPlano(PacientePlano pl);
	 void EditnovoSeguradoPlano(PacientePlano pl);
	 List<PacientePlano> buscarAsseguradosPlano();
	 List<PacientePlano> buscarAsseguradosPlano(String aux);
	 
	 List<PacientePlano> buscarAsseguradosPlanoDetalhes(String aux);
	 
	 PacientePlano getAsseguradoPlano(int aux);
	 PacientePlano getAsseguradoPlano(String aux);
	 String getCopiaCartao(String aux);
	 
	 void excluirPacienPlano(String aux);
	 
	 boolean isValidoPlano(PacientePlano pl);
	 
	 PacientePlano getPlanoBeneficioServico(int codp, int cods);
	 PacientePlano getPlanoBeneficioConsulta(int codp, int cods);
	 PacientePlano getPlanoBeneficioProduto(int codp, int cods);
	 PacientePlano getPlanoBeneficioExame(int codp, int cods);
	 double creditoPaciente(int codp);
}
