package ao.co.cligest.contracts;

import ao.co.cligest.entidades.Caixa;

public interface IPagamentos {

	int getCaixaUtente(int codUser, Caixa c);

	String getNomeFactura();

	int numerofactura();

}
