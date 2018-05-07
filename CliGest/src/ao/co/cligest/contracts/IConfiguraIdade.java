package ao.co.cligest.contracts;

import ao.co.cligest.entidades.MenorIdade;

public interface IConfiguraIdade {

	void configuraMenorIdade(MenorIdade m);
	void configuraTerceiraIdade(MenorIdade t);
	void configuraIdade(MenorIdade m);
	MenorIdade buscarConfiguracoesIdade();
}
