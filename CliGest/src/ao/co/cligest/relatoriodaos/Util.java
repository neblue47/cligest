package ao.co.cligest.relatoriodaos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ao.co.cligest.dao.Conexao;

public class Util {
	private Connection con;
	public static double retornaQuantiaPaga(String numFactura, String tPagamento) {
		String sql = "SELECT * FROM tblmultipagamentos mt join tblfactura ft on mt.fk_factura = ft.id_factura WHERE numero_factura = ? AND tipo_pagamento LIKE ?";
		try {
			Connection con  = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setString(1, numFactura);
			 ps.setString(2, tPagamento);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next())
				 return rs.getDouble("quantia_paga");
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
