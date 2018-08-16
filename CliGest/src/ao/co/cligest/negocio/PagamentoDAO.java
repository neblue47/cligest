package ao.co.cligest.negocio;

import java.sql.Connection;

import ao.co.cligest.interfaces.IPagamentoDAO;

public class PagamentoDAO implements IPagamentoDAO {

	private Connection con = null;
	private Formatando ft = new Formatando();
	
	@Override
	public int gerarFacturaPac() {
		String sql = "INSERT INTO tblfactura () VALUES ()";
		return 0;
	}
	@Override
	public void pagarFacturaConsultaCash() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pagarFacturaConsultaTPA() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void pagarFacturaConsultaCashTPA() {
		// TODO Auto-generated method stub
		
	}
	
	
}
