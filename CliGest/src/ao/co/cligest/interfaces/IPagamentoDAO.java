package ao.co.cligest.interfaces;

public interface IPagamentoDAO {

	int gerarFacturaPac();
	void pagarFacturaConsultaCash();
	void pagarFacturaConsultaTPA();
	void pagarFacturaConsultaCashTPA();
}
