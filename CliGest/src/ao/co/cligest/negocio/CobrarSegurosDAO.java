package ao.co.cligest.negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ao.co.cligest.contracts.ICobrarSeguros;
import ao.co.cligest.entidades.CobrarSeguros;

public class CobrarSegurosDAO implements ICobrarSeguros{
    
	private Connection con;
	private Formatando ft = new Formatando();
	@Override
	
	
	public List<CobrarSeguros> buscarFacturasNaoPagaPorSeguradoras() {
		String sql = "SELECT * FROM vwlistarseguradorafacturasemcobrar ORDER BY Empresa ASC";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 CobrarSeguros cs = new CobrarSeguros();
				 cs.setEmpresa(rs.getString("empresa"));
				 cs.setQuantidade(rs.getInt("QtdFacturas"));
				 cs.setTotais(rs.getDouble("TOTALSEGURADORA"));
				 cs.setFK_aseguradora(rs.getInt("FK_aseguradora"));
				 lista.add(cs);
			 }
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<CobrarSeguros> buscarFacturasNaoPagaPorGeral(int cods) {
		String sql = "SELECT * FROM vwlistarfacturassemcobrancageral WHERE FK_aseguradora = ? ";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 CobrarSeguros cs = new CobrarSeguros();
				 cs.setQuantidade(rs.getInt("QTDFACTURA"));
				 cs.setTotais(rs.getDouble("TOTALSEGURADORA"));
				 cs.setFK_aseguradora(rs.getInt("FK_aseguradora"));
				 cs.setFK_paciente(rs.getInt("FK_paciente"));
				 cs.setNome_paciente(rs.getString("nomeCompleto"));
				 lista.add(cs);
			 }
			 ps.close();
			 con.close();
		   }
			 catch (Exception e) {
				e.printStackTrace();// TODO: handle exception 
			}
		return lista;
	}
	
	@Override
	public List<CobrarSeguros> buscarFacturasNaoPagaPorConsultas(int cods) {
		String sql = "SELECT * FROM vwlistarfacturassemcobranca WHERE FK_aseguradora = ? AND FK_tipo_deservico = 1";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 CobrarSeguros cs = new CobrarSeguros();
				 cs.setQuantidade(rs.getInt("QTDFACTURA"));
				 cs.setTotais(rs.getDouble("TOTALSEGURADORA"));
				 cs.setFK_aseguradora(rs.getInt("FK_aseguradora"));
				 cs.setFK_paciente(rs.getInt("FK_paciente"));
				 cs.setNome_paciente(rs.getString("nomeCompleto"));
				 lista.add(cs);
			 }
			 ps.close();
			 con.close();
		   }
			 catch (Exception e) {
				e.printStackTrace();// TODO: handle exception 
			}
		return lista;
	}

	@Override
	public List<CobrarSeguros> buscarFacturasNaoPagaPorExames(int cods) {
		String sql = "SELECT * FROM vwlistarfacturassemcobranca WHERE FK_aseguradora = ? AND FK_tipo_deservico = 2";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 CobrarSeguros cs = new CobrarSeguros();
				 cs.setQuantidade(rs.getInt("QTDFACTURA"));
				 cs.setTotais(rs.getDouble("TOTALSEGURADORA"));
				 cs.setFK_aseguradora(rs.getInt("FK_aseguradora"));
				 cs.setFK_paciente(rs.getInt("FK_paciente"));
				 cs.setNome_paciente(rs.getString("nomeCompleto"));
				 lista.add(cs);
			 }
			 ps.close();
			 con.close();
		   }
			 catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
		return lista;
	}

	@Override
	public List<CobrarSeguros> buscarFacturasNaoPagaPorSGerais(int cods) {
		String sql = "SELECT * FROM vwlistarfacturassemcobranca WHERE FK_aseguradora = ? AND FK_tipo_deservico = 3";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 CobrarSeguros cs = new CobrarSeguros();
				 cs.setQuantidade(rs.getInt("QTDFACTURA"));
				 cs.setTotais(rs.getDouble("TOTALSEGURADORA"));
				 cs.setFK_aseguradora(rs.getInt("FK_aseguradora"));
				 cs.setFK_paciente(rs.getInt("FK_paciente"));
				 cs.setNome_paciente(rs.getString("nomecompleto"));
				 lista.add(cs);
			 }
			 ps.close();
			 con.close();
		   }
			 catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
		return lista;
	}

	@Override
	public List<CobrarSeguros> buscarFacturasNaoPagaPorProdutos(int cods) {
		String sql = "SELECT * FROM vwlistarfacturassemcobranca WHERE FK_aseguradora = ? AND FK_tipo_deservico = 4";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 CobrarSeguros cs = new CobrarSeguros();
				 cs.setQuantidade(rs.getInt("QTDFACTURA"));
				 cs.setTotais(rs.getDouble("TOTALSEGURADORA"));
				 cs.setFK_aseguradora(rs.getInt("FK_aseguradora"));
				 cs.setFK_paciente(rs.getInt("FK_paciente"));
				 cs.setNome_paciente(rs.getString("nomeCompleto"));
				 lista.add(cs);
			 }
			 ps.close();
			 con.close();
		   }
			 catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
		return lista;
	}

	@Override
	public CobrarSeguros getDetalhesSeguradora(int cods) {
		String sql = "SELECT * FROM vwlistarseguradorafacturasemcobrar WHERE FK_aseguradora = ?";
		CobrarSeguros cs = new CobrarSeguros();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next()){
				 cs.setEmpresa(rs.getString("empresa"));
				 cs.setQuantidade(rs.getInt("QtdFacturas"));
				 cs.setTotais(rs.getDouble("total_pago_plano_saude"));
				 cs.setFK_aseguradora(rs.getInt("FK_aseguradora"));
				 cs.setNomeCompleto(rs.getString("primeiro_nome") + " " + rs.getString("ultimo_nome"));
			 }
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cs;
	}
	
	@Override
	public List<CobrarSeguros> buscarFacturasNaoPagaPorConsultas(int cods,String dataIn,String dataFn) {
		String sql = "SELECT * FROM vwlistarfacturassemcobranca WHERE FK_aseguradora = ? AND FK_tipo_deservico = 1 AND Data_da_facturacao Between ? AND ? ";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ps.setDate(2, ft.dataSql(dataIn));
			 ps.setDate(3, ft.dataSql(dataFn));
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 CobrarSeguros cs = new CobrarSeguros();
				 cs.setQuantidade(rs.getInt("QTDFACTURA"));
				 cs.setTotais(rs.getDouble("TOTALSEGURADORA"));
				 cs.setFK_aseguradora(rs.getInt("FK_aseguradora"));
				 cs.setFK_paciente(rs.getInt("FK_paciente"));
				 cs.setNome_paciente(rs.getString("nomeCompleto"));
				 lista.add(cs);
			 }
			 ps.close();
			 con.close();
		   }
			 catch (Exception e) {
				e.printStackTrace();// TODO: handle exception 
			}
		return lista;
	}

	@Override
	public List<CobrarSeguros> buscarFacturasNaoPagaPorExames(int cods,String dataIn,String dataFn) {
		String sql = "SELECT * FROM vwlistarfacturassemcobranca WHERE FK_aseguradora = ? AND FK_tipo_deservico = 2 AND Data_da_facturacao Between ? AND ?";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ps.setDate(2, ft.dataSql(dataIn));
			 ps.setDate(3, ft.dataSql(dataFn));
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 CobrarSeguros cs = new CobrarSeguros();
				 cs.setQuantidade(rs.getInt("QTDFACTURA"));
				 cs.setTotais(rs.getDouble("TOTALSEGURADORA"));
				 cs.setFK_aseguradora(rs.getInt("FK_aseguradora"));
				 cs.setFK_paciente(rs.getInt("FK_paciente"));
				 cs.setNome_paciente(rs.getString("nomeCompleto"));
				 lista.add(cs);
			 }
			 ps.close();
			 con.close();
		   }
			 catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
		return lista;
	}

	@Override
	public List<CobrarSeguros> buscarFacturasNaoPagaPorSGerais(int cods,String dataIn,String dataFn) {
		String sql = "SELECT * FROM vwlistarfacturassemcobranca WHERE FK_aseguradora = ? AND FK_tipo_deservico = 3 AND Data_da_facturacao Between ? AND ?";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ps.setDate(2, ft.dataSql(dataIn));
			 ps.setDate(3, ft.dataSql(dataFn));
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 CobrarSeguros cs = new CobrarSeguros();
				 cs.setQuantidade(rs.getInt("QTDFACTURA"));
				 cs.setTotais(rs.getDouble("TOTALSEGURADORA"));
				 cs.setFK_aseguradora(rs.getInt("FK_aseguradora"));
				 cs.setFK_paciente(rs.getInt("FK_paciente"));
				 cs.setNome_paciente(rs.getString("nomeCompleto"));
				 lista.add(cs);
			 }
			 ps.close();
			 con.close();
		   }
			 catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
		return lista;
	}

	@Override
	public List<CobrarSeguros> buscarFacturasNaoPagaPorProdutos(int cods,String dataIn,String dataFn) {
		String sql = "SELECT * FROM vwlistarfacturassemcobranca WHERE FK_aseguradora = ? AND FK_tipo_deservico = 4 AND Data_da_facturacao Between ? AND ?";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ps.setDate(2, ft.dataSql(dataIn));
			 ps.setDate(3, ft.dataSql(dataFn));
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 CobrarSeguros cs = new CobrarSeguros();
				 cs.setQuantidade(rs.getInt("QTDFACTURA"));
				 cs.setTotais(rs.getDouble("TOTALSEGURADORA"));
				 cs.setFK_aseguradora(rs.getInt("FK_aseguradora"));
				 cs.setFK_paciente(rs.getInt("FK_paciente"));
				 cs.setNome_paciente(rs.getString("nomeCompleto"));
				 lista.add(cs);
			 }
			 ps.close();
			 con.close();
		   }
			 catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
		return lista;
	}

	@Override
	public List<CobrarSeguros> buscarFacturasCobrando(int cods) {
		String sql = "SELECT *  FROM vwfacturascobrandopacienteplano  WHERE FK_titular = ?	and id_factura not in (Select FK_factura from tblcriarfacturaconveniocomlinhas)";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 CobrarSeguros cs = new CobrarSeguros();
				 cs.setFK_aseguradora(rs.getInt("FK_aseguradora"));
				 cs.setFK_paciente(rs.getInt("FK_titular"));
				 cs.setFK_factura(rs.getInt("id_factura"));
				 lista.add(cs);
			 }
			 ps.close();
			 con.close();
		   }
			 catch (Exception e) {
				e.printStackTrace();// TODO: handle exception 
			}
		return lista;
	}

	@Override
	public List<CobrarSeguros> buscarFacturasCobrandoExames(int cods) {
		String sql = "SELECT *  FROM vwfacturascobrandopacienteseguro  WHERE FK_pacientE = ?	AND FK_tipo_deservico = 2 and id_factura not in (Select FK_factura from tblcriarfacturaconveniocomlinhas)";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 CobrarSeguros cs = new CobrarSeguros();
				 cs.setFK_aseguradora(rs.getInt("FK_aseguradora"));
				 cs.setFK_paciente(rs.getInt("FK_paciente"));
				 cs.setFK_factura(rs.getInt("id_factura"));
				 lista.add(cs);
			 }
			 ps.close();
			 con.close();
		   }
			 catch (Exception e) {
				e.printStackTrace();// TODO: handle exception 
			}
		return lista;
	}

	@Override
	public List<CobrarSeguros> buscarFacturasCobrandoSGerais(int cods) {
		String sql = "SELECT *  FROM vwfacturascobrandopacienteseguro  WHERE FK_pacientE = ?	AND FK_tipo_deservico = 3 and id_factura not in (Select FK_factura from tblcriarfacturaconveniocomlinhas)";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 CobrarSeguros cs = new CobrarSeguros();
				 cs.setFK_aseguradora(rs.getInt("FK_aseguradora"));
				 cs.setFK_paciente(rs.getInt("FK_paciente"));
				 cs.setFK_factura(rs.getInt("id_factura"));
				 lista.add(cs);
			 }
			 ps.close();
			 con.close();
		   }
			 catch (Exception e) {
				e.printStackTrace(); 
			}
		return lista;
	}

	@Override
	public List<CobrarSeguros> buscarFacturasCobrandoProdutos(int cods) {
		String sql = "SELECT *  FROM vwfacturascobrandopacienteseguro  WHERE FK_pacientE = ?	AND FK_tipo_deservico = 4 and id_factura not in (Select FK_factura from tblcriarfacturaconveniocomlinhas)";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 CobrarSeguros cs = new CobrarSeguros();
				 cs.setFK_aseguradora(rs.getInt("FK_aseguradora"));
				 cs.setFK_paciente(rs.getInt("FK_paciente"));
				 cs.setFK_factura(rs.getInt("id_factura"));
				 lista.add(cs);
			 }
			 ps.close();
			 con.close();
		   }
			 catch (Exception e) {
				e.printStackTrace(); 
			}
		return lista;
	}

	@Override
	public int CriarFacturaPorCobrarConvenio(CobrarSeguros cs) {
		int ultimoCod = 0;
		String sql = "INSERT INTO tblcriarfacturaconvenio (FK_convenio,fk_funcionario,data_criacao_fac_convenio,maquina_criacao_fac_convenio,numero_da_cria_factura) VALUE (?,?,?,?,?)";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cs.getFK_aseguradora());
			 ps.setInt(2, cs.getFK_funcionario());
			 ps.setDate(3, ft.data_registo());
			 ps.setString(4, ft.maquinaLogado());
			 ps.setString(5, cs.getNumero_factura());
			 ps.execute();
			 ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
			 if(rs.next()){
				ultimoCod = rs.getInt(1); 
			 }
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoCod;
	}

	@Override
	public void CriarFacturaPorCobrarConvenioLinhas(CobrarSeguros cs) {
		String sql = "INSERT INTO tblcriarfacturaconveniocomlinhas (FK_criar_fac_convenio, FK_factura) VALUE (?,?) ";
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cs.getFK_criar_fac_convenio());
			 ps.setInt(2, cs.getFK_factura());
			 ps.execute();
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<CobrarSeguros> buscarFacturasCriadasPorSeguradoras() {
		String sql = "SELECT numero_da_cria_factura,NOMEFUNCIONARIO,sum(TOTALSEGURADORA) TOTALSEGURADORA, FK_criar_fac_convenio, data_criacao_fac_convenio FROM vwlistarseguradorafacturascriadas vw where vw.FK_criar_fac_convenio not in (select  fk_criar_factura_plano  from tblfacturaspagasconvenio) group by vw.FK_criar_fac_convenio";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 CobrarSeguros cs = new CobrarSeguros();
				 cs.setNumero_factura(rs.getString("numero_da_cria_factura"));
				 cs.setNomeFun(rs.getString("NOMEFUNCIONARIO"));
				 cs.setTotal_pago_plano_saude(rs.getDouble("TOTALSEGURADORA"));
				 cs.setFK_criar_fac_convenio(rs.getInt("FK_criar_fac_convenio"));
				 Calendar data = Calendar.getInstance();
			      data.setTime(rs.getDate("data_criacao_fac_convenio"));
				 cs.setData_registo(data);
				 lista.add(cs);
			 }
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<CobrarSeguros> buscarFacturasCriadasPorSeguradoras(String param) {
		String sql = "SELECT * FROM vwlistarseguradorafacturascriadas WHERE numero_da_cria_factura LIKE ?";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setString(1, param);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 CobrarSeguros cs = new CobrarSeguros();
				 cs.setNumero_factura(rs.getString("numero_da_cria_factura"));
				 cs.setNomeFun(rs.getString("NOMEFUNCIONARIO"));
				 cs.setTotal_pago_plano_saude(rs.getDouble("TOTALSEGURADORA"));
				 cs.setFK_criar_fac_convenio(rs.getInt("FK_criar_fac_convenio"));
				 cs.setData(rs.getDate("data_criacao_fac_convenio"));
				 lista.add(cs);
			 }
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<CobrarSeguros> buscarFacturasCriadasPorSegurado(int cods) {
		String sql = "SELECT sum(QTDFACTURA) QTDFACTURA,sum(TOTALSEGURADORA) TOTALSEGURADORA,FK_aseguradora,FK_paciente,NomeCompleto FROM vwfacturascobradaspacienteplano  WHERE FK_criar_fac_convenio = ?  group by FK_paciente, FK_aseguradora ";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 CobrarSeguros cs = new CobrarSeguros();
				 cs.setQuantidade(rs.getInt("QTDFACTURA"));
				 cs.setTotais(rs.getDouble("TOTALSEGURADORA"));
				 cs.setFK_aseguradora(rs.getInt("FK_aseguradora"));
				 cs.setFK_paciente(rs.getInt("FK_paciente"));
				 cs.setNome_paciente(rs.getString("NomeCompleto"));
				 lista.add(cs);
			 }
			 ps.close();
			 con.close();
		   }
			 catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
		return lista;
	}

	@Override
	public List<CobrarSeguros> buscarFacturasCriadasPorConvenio(int cods) {
		String sql = "SELECT *  FROM tblcriarfacturaconveniocomlinhas WHERE FK_criar_fac_convenio = ?	AND FK_factura not in (Select fk_factura_p_saude from tblfacturaspagasconvenio)";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 CobrarSeguros cs = new CobrarSeguros();
				 cs.setFK_criar_fac_convenio(rs.getInt("FK_criar_fac_convenio"));
				 cs.setFK_factura(rs.getInt("FK_factura"));
				 lista.add(cs);
			 }
			 ps.close();
			 con.close();
		   }
			 catch (Exception e) {
				e.printStackTrace();// TODO: handle exception 
			}
		return lista;
	}

	@Override
	public int PagarFacturasPorConvenios(CobrarSeguros cs) {
		String sql = "INSERT INTO tblfacturaspagasconvenio (fk_convenio,fk_funcionario,data_pagamento,numero_fac_paga_convenio,fk_criar_factura_plano) VALUE (?,?,?,?,?)";
		int ultimoId = XequeFacturaPaga(cs.getFK_criar_fac_convenio());
		try {
			 if(ultimoId == 0){
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setInt(1, cs.getFK_aseguradora());
				 ps.setInt(2, cs.getFK_funcionario());
				 ps.setDate(3, ft.data_registo());
				 ps.setString(4, cs.getNumero_fac_paga_convenio());
				 ps.setInt(5, cs.getFK_criar_fac_convenio());
				 ps.execute();
				 ResultSet rs = ps.executeQuery("SELECT LAST_INSERT_ID()");
				 if(rs.next())
		    	        ultimoId = rs.getInt(1);
				 ps.close();
				 con.close();
			 }
		 
				 
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoId;
	//vwfacturaseguradoracriada
	}
	
	protected int XequeFacturaPaga(int cods) {
		String sql = "SELECT * FROM tblfacturaspagasconvenio WHERE fk_criar_factura_plano = ?";
		int ultimoId = 0;
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next())
				 ultimoId=  rs.getInt("id_facturas_pagas_convenio");
			 
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ultimoId;
	}
	@Override
	public List<CobrarSeguros> getDetalhesFacturasCriada(int cods) {
		String sql = "SELECT * FROM vwfacturaseguradoracriada WHERE FK_criar_fac_convenio = ?  ";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 CobrarSeguros cs = new CobrarSeguros();
				 cs.setTotais(rs.getDouble("TOTALSEGURADORA"));
				 cs.setFK_aseguradora(rs.getInt("FK_aseguradora"));
				 cs.setFK_paciente(rs.getInt("FK_paciente"));
				 cs.setFK_factura(rs.getInt("FK_factura"));
				 cs.setFK_criar_fac_convenio(rs.getInt("FK_criar_fac_convenio"));
				 lista.add(cs);
			 }
			 ps.close();
			 con.close();
		   }
			 catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
		return lista;
	}

	@Override
	public CobrarSeguros getDetalhesFacturaCriada(int cods) {
		String sql = "select sum(vw.QTDFACTURA) QTDFACTURA, sum(vw.TOTALSEGURADORA) TOTALSEGURADORA, vw.FK_aseguradora, data_criacao_fac_convenio,empresa, numero_da_cria_factura from vwfacturascobradaspacienteplano vw inner join tblfornecedor fnr on vw.FK_aseguradora = fnr.Fk_fornecedor WHERE FK_criar_fac_convenio = ?  ";
		CobrarSeguros cs = new CobrarSeguros();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next()){
				 cs.setQuantidade(rs.getInt("QTDFACTURA"));
				 cs.setTotais(rs.getDouble("TOTALSEGURADORA"));
				 cs.setFK_aseguradora(rs.getInt("FK_aseguradora"));
				 cs.setEmpresa(rs.getString("empresa"));
				 cs.setNumero_factura(rs.getString("numero_da_cria_factura"));
				 Calendar data = Calendar.getInstance();
				 if(rs.getDate("data_criacao_fac_convenio")!=null && !rs.getDate("data_criacao_fac_convenio").equals(""))
				  data.setTime(rs.getDate("data_criacao_fac_convenio"));
				 cs.setData_registo(data);
			 }
			 ps.close();
			 con.close();
		   }
			 catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
		return cs;
	}

	@Override
	public CobrarSeguros getDetalhesResumoFacCriada(String cods) {
			String sql = "select * from vwlistarseguradorafacturascriadas vw, tblfornecedor fnc WHERE vw.fk_convenio = fnc.FK_fornecedor AND FK_criar_fac_convenio = ?";
			CobrarSeguros cs = new CobrarSeguros();
			try {
				 con = Conexao.getConexao();
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setString(1, cods);
				 ResultSet rs = ps.executeQuery();
				 while(rs.next()){
					 cs.setQuantidade(rs.getInt("QTDFACTURA"));
					 cs.setTotais(rs.getDouble("TOTALSEGURADORA"));
					 cs.setFK_aseguradora(rs.getInt("fk_convenio"));
					 cs.setEmpresa(rs.getString("empresa"));
					 Calendar data = Calendar.getInstance();
					 data.setTime(rs.getDate("data_criacao_fac_convenio"));
					 cs.setData_registo(data);
				 }
				 ps.close();
				 con.close();
			   }
				 catch (Exception e) {
					e.printStackTrace();// TODO: handle exception
				}
			return cs;
		 
	}

	@Override	
	public void PagarFacturasPorConveniosEfectuados(CobrarSeguros cs) {
		String sql = "INSERT INTO tblpagamentosefectuadosconvenio (data_paga_efectu_convenio,valor_paga_efectu_convenio,fk_modo_pagamento,fk_facturas_pagas_convenio) "
				   + " VALUE (?,?,?,?)";	
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setDate(1, ft.data_registo());
			 ps.setDouble(2, cs.getValor_pago__paga_conv());
			 ps.setInt(3, cs.getFk_conv_modo_pagamento());
			 ps.setInt(4, cs.getFk_fac_paga_convenio());
			 ps.execute();
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void PagarFacturasPorConveniosDeposito(CobrarSeguros cs) {
		String sql = "INSERT INTO tblpagamentoconveniodeposito (numero_paga_conv_deposito,valo_paga_conv_deposito,fk_paga_conv_deposito) "
				   + " VALUE (?,?,?)";	
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cs.getNumero_paga_conv());
			 ps.setDouble(2, cs.getValor_pago__paga_conv());
			 ps.setInt(3, cs.getFk_fac_paga_convenio());
			 
			 ps.execute();
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void PagarFacturasPorConveniosCheque(CobrarSeguros cs) {
		String sql = "INSERT INTO tblpagamentoconveniocheque "
				+    " (fk_banco_paga_conv_cheque,numero_paga_conv_cheque,agencia_paga_conv_cheque,rota_paga_conv_cheque,valor_paga_conv_cheque,fk_fac_paga_convenio) "
				   + " VALUE (?,?,?,?,?,?)";	
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cs.getFk_banco_origem_paga_conv());
			 ps.setInt(2, cs.getNumero_paga_conv());
			 ps.setInt(3, cs.getAgencia_paga_conv());
			 ps.setInt(4, cs.getRota_paga_conv_cheque());
			 ps.setDouble(5, cs.getValor_pago__paga_conv());
			 ps.setInt(6, cs.getFk_fac_paga_convenio());
			 ps.execute();
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void PagarFacturasPorConveniosTransferencia(CobrarSeguros cs) {
		String sql = "INSERT INTO tblpagamentoconveniotransferencia "
				+    " (fk_banco_origem_paga_conv_transferencia,codigo_paga_conv_transferencia,fk_banco_ditinatario_paga_conv_transferencia,"
				+    "  valor_paga_conv_transferencia,iban_paga_conv_transferencia,swift_paga_conv_transferencia,obs_paga_conv_transferencia, fk_fac_paga_convenio	) "
				   + " VALUE (?,?,?,?,?,?,?,?)";	
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cs.getFk_banco_origem_paga_conv());
			 ps.setString(2, cs.getCodigo_paga_conv_transferencia());
			 ps.setInt(3, cs.getFk_banco_ditinatario_paga_conv());
			 ps.setDouble(4, cs.getValor_pago__paga_conv());
			 ps.setString(5, cs.getIban_paga_conv_transferencia());
			 ps.setString(6, cs.getSwift_paga_conv_transferencia());
			 ps.setString(7, cs.getObs_paga_conv_transferencia());
			 ps.setInt(8, cs.getFk_fac_paga_convenio());
			 ps.execute();
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void PagarFacturasPorConveniosCredito(CobrarSeguros cs) {
		String sql = "INSERT INTO tblpagamentoconveniodeposito (numero_paga_conv_deposito,valo_paga_conv_deposito,fk_factura,fk_paga_conv_deposito) "
				   + " VALUE (?,?,?,?)";	
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cs.getNumero_paga_conv());
			 ps.setDouble(2, cs.getValor_pago__paga_conv());
			 ps.setInt(3, cs.getFK_factura());
			 ps.setInt(4, cs.getFk_fac_paga_convenio());
			 ps.execute();
			 ps.close();
			 con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CobrarSeguros> buscarFacturasPagasPorSeguradoras() {
		String sql = "SELECT * FROM vwfacturaseguradorapagas    ";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement ps = con.prepareStatement(sql);
			 
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 CobrarSeguros cs = new CobrarSeguros();
				 cs.setTotais(rs.getDouble("totalpago"));
				 cs.setFK_aseguradora(rs.getInt("fk_convenio"));
			 
				 cs.setEmpresa(rs.getString("empresa"));
				 cs.setNumero_factura(rs.getString("numero_fac_paga_convenio"));
				 cs.setNome(rs.getString("nomecompleto"));
				 cs.setFK_criar_fac_convenio(rs.getInt("fk_criar_factura_plano"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_pagamento"));
				 cs.setData_registo(data);
				 lista.add(cs);
			 }
			 ps.close();
			 con.close();
		   }
			 catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
		return lista;
	}
	
	public List<CobrarSeguros> buscarFacturasPagasPorSeguradoras(String param) {
		String sql = "SELECT * FROM vwfacturaseguradorapagas    ";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement ps = con.prepareStatement(sql);
			 
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 CobrarSeguros cs = new CobrarSeguros();
				 cs.setTotais(rs.getDouble("totalpago"));
				 cs.setFK_aseguradora(rs.getInt("fk_convenio"));
				 cs.setEmpresa(rs.getString("empresa"));;
				 cs.setNome(rs.getString("nomecompleto"));
				 cs.setFK_criar_fac_convenio(rs.getInt("id_facturas_pagas_convenio"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_pagamento"));
				 cs.setData_registo(data);
				 lista.add(cs);
			 }
			 ps.close();
			 con.close();
		   }
			 catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
		return lista;
	}
	
	public List<CobrarSeguros> buscarFacturasPagasPorSeguradoras(String param,String dataIn, String dataFn) {
		String sql = "SELECT * FROM vwfacturaseguradorapagas  ";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement ps = con.prepareStatement(sql);
			 
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 CobrarSeguros cs = new CobrarSeguros();
				 cs.setTotais(rs.getDouble("totalpago"));
				 cs.setFK_aseguradora(rs.getInt("fk_convenio"));
				 cs.setEmpresa(rs.getString("empresa"));;
				 cs.setNome(rs.getString("nomecompleto"));
				 cs.setFK_criar_fac_convenio(rs.getInt("id_facturas_pagas_convenio"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_pagamento"));
				 cs.setData_registo(data);
				 lista.add(cs);
			 }
			 ps.close();
			 con.close();
		   }
			 catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
		return lista;
	}
	
	public List<CobrarSeguros> buscarFacturasNaoPagaPorDetalhar(int cods,int codp) {
		String sql = "SELECT * FROM vwlistarfacturassemcobrancadetalhar WHERE FK_aseguradora = ? AND FK_paciente = ?";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ps.setInt(2, codp);
			 ResultSet rs = ps.executeQuery();
			 while(rs.next()){
				 CobrarSeguros cs = new CobrarSeguros();
				 
				 cs.setFK_aseguradora(rs.getInt("FK_aseguradora"));
				 cs.setFK_factura(rs.getInt("fK_factura"));
				 cs.setFK_paciente(rs.getInt("FK_paciente"));
				 cs.setNome_paciente(rs.getString("nomeCompleto"));
				 cs.setNumero_factura(rs.getString("Numero_factura"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("Data_da_facturacao"));
				 cs.setData_registo(data);
				 lista.add(cs);
			 }
			 ps.close();
			 con.close();
		   }
			 catch (Exception e) {
				e.printStackTrace();// TODO: handle exception 
			}
		return lista;
	}

	@Override
	public List<CobrarSeguros> getFacturasPorPagar(int cods) {
		List<CobrarSeguros> listaGeral = new ArrayList<CobrarSeguros>();
		try {
		
				listaGeral.addAll(getFacturasPorPagarConsultas(cods));
				listaGeral.addAll(getFacturasPorPagarServicosGerais(cods));
				listaGeral.addAll(getFacturasPorPagarExames(cods));	
				listaGeral.addAll(getFacturasPorPagarProduto(cods));	
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaGeral;
	}
	
	protected List<CobrarSeguros> getFacturasPorPagarConsultas(int cods) {
		String sql = "SELECT * FROM vwfacturasnoplanoconsulta WHERE FK_paciente = ? AND FK_FACTURA NOT IN (SELECT FK_FACTURA FROM tblcriarfacturaconveniocomlinhas) GROUP BY FK_factura";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 while (rs.next()) {
				CobrarSeguros cs = new CobrarSeguros();
				cs.setFK_factura(rs.getInt("FK_factura"));
				cs.setNumero_factura(rs.getString("numero_factura"));
				cs.setFK_paciente(rs.getInt("FK_paciente"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_da_facturacao"));
				cs.setData_registo(data);
				lista.add(cs);
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	protected List<CobrarSeguros> getFacturasPorPagarServicosGerais(int cods) {
		String sql = "SELECT * FROM vwfacturasnoplanoservicogerais WHERE FK_paciente = ? AND FK_FACTURA NOT IN (SELECT FK_FACTURA FROM tblcriarfacturaconveniocomlinhas) GROUP BY FK_factura";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 while (rs.next()) {
				CobrarSeguros cs = new CobrarSeguros();
				cs.setFK_factura(rs.getInt("FK_factura"));
				cs.setNumero_factura(rs.getString("numero_factura"));
				cs.setFK_paciente(rs.getInt("FK_paciente"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_da_facturacao"));
				cs.setData_registo(data);
				lista.add(cs);
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	protected List<CobrarSeguros> getFacturasPorPagarExames(int cods) {
		String sql = "SELECT * FROM vwfacturasnoplanosexames WHERE FK_paciente = ? AND FK_FACTURA NOT IN (SELECT FK_FACTURA FROM tblcriarfacturaconveniocomlinhas) GROUP BY FK_factura";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 while (rs.next()) {
				CobrarSeguros cs = new CobrarSeguros();
				cs.setFK_factura(rs.getInt("FK_factura"));
				cs.setNumero_factura(rs.getString("numero_factura"));
				cs.setFK_paciente(rs.getInt("FK_paciente"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_da_facturacao"));
				cs.setData_registo(data);
				lista.add(cs);
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	protected List<CobrarSeguros> getFacturasPorPagarProduto(int cods) {
		String sql = "SELECT * FROM vwfacturasnoplanosprodutos WHERE FK_paciente = ? AND FK_FACTURA NOT IN (SELECT FK_FACTURA FROM tblcriarfacturaconveniocomlinhas) GROUP BY FK_factura";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 while (rs.next()) {
				CobrarSeguros cs = new CobrarSeguros();
				cs.setFK_factura(rs.getInt("FK_factura"));
				cs.setNumero_factura(rs.getString("numero_factura"));
				cs.setFK_paciente(rs.getInt("FK_paciente"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_da_facturacao"));
				cs.setData_registo(data);
				lista.add(cs);
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	public List<CobrarSeguros> getFacturasPorPagarItens(int cods) {
		 
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			    lista =  getFacturasPorPagarItensConsulta(cods);
			  if(lista.isEmpty())
				  lista =  getFacturasPorPagarItensServicoGerais(cods);
			  if(lista.isEmpty())
				  lista =  getFacturasPorPagarItensExames(cods);
			  if(lista.isEmpty())
				  lista =  getFacturasPorPagarItensProdutos(cods);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	protected List<CobrarSeguros> getFacturasPorPagarItensConsulta(int cods) {
		String sql = "SELECT * FROM vwfacturasnoplanoconsulta WHERE FK_factura = ? GROUP BY FK_factura";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 while (rs.next()) {
				CobrarSeguros cs = new CobrarSeguros();
				cs.setFK_factura(rs.getInt("FK_factura"));
				cs.setServico(rs.getString("servico"));
				cs.setQuantidade(1);
				cs.setPreco(rs.getDouble("preco_crescido"));
				cs.setComparticipacao_paciente(rs.getDouble("comparticipacao_paciente"));
				cs.setComparticipacao_aseguradora(rs.getDouble("comparticipacao_aseguradora"));
				cs.setTaxa(rs.getDouble("coparticipacao"));
				cs.setTotal_factura(rs.getDouble("total_pago_plano_saude"));
				lista.add(cs);
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	protected List<CobrarSeguros> getFacturasPorPagarItensServicoGerais(int cods) {
		String sql = "SELECT * FROM vwfacturasnoplanoservicogerais WHERE FK_factura = ? GROUP BY FK_factura ";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 while (rs.next()) {
				CobrarSeguros cs = new CobrarSeguros();
				cs.setFK_factura(rs.getInt("FK_factura"));
				cs.setServico(rs.getString("servico"));
				cs.setQuantidade(rs.getInt("sg_seguro_qtd"));
				cs.setPreco(rs.getDouble("preco_crescido"));
				cs.setComparticipacao_paciente(rs.getDouble("comparticipacao_paciente"));
				cs.setComparticipacao_aseguradora(rs.getDouble("comparticipacao_aseguradora"));
				cs.setTaxa(rs.getDouble("coparticipacao"));
				cs.setTotal_factura(rs.getDouble("total_pago_plano_saude"));
				lista.add(cs);
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	

	protected List<CobrarSeguros> getFacturasPorPagarItensExames(int cods) {
		String sql = "SELECT * FROM vwfacturasnoplanosexames WHERE FK_factura = ? GROUP BY FK_factura";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 while (rs.next()) {
				CobrarSeguros cs = new CobrarSeguros();
				cs.setFK_factura(rs.getInt("FK_factura"));
				cs.setServico(rs.getString("exames_clinicos_cadastrados"));
				cs.setQuantidade(rs.getInt("ex_seguro_qtd"));
				cs.setPreco(rs.getDouble("ex_seguro_total"));
				cs.setComparticipacao_paciente(rs.getDouble("comparticipacao_paciente"));
				cs.setComparticipacao_aseguradora(rs.getDouble("comparticipacao_aseguradora"));
				cs.setTaxa(rs.getDouble("coparticipacao"));
				cs.setTotal_factura(rs.getDouble("total_pago_plano_saude"));
				lista.add(cs);
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	protected List<CobrarSeguros> getFacturasPorPagarItensProdutos(int cods) {
		String sql = "SELECT * FROM vwfacturasnoplanosprodutos WHERE FK_factura = ? GROUP BY FK_factura";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 while (rs.next()) {
				CobrarSeguros cs = new CobrarSeguros();
				cs.setFK_factura(rs.getInt("FK_factura"));
				cs.setServico(rs.getString("nome_comercial"));
				cs.setQuantidade(rs.getInt("pro_seguro_qtd"));
				cs.setPreco(rs.getDouble("pro_seguro_total"));
				cs.setComparticipacao_paciente(rs.getDouble("comparticipacao_paciente"));
				cs.setComparticipacao_aseguradora(rs.getDouble("comparticipacao_aseguradora"));
				cs.setTaxa(rs.getDouble("coparticipacao"));
				cs.setTotal_factura(rs.getDouble("total_pago_plano_saude"));
				lista.add(cs);
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	@Override
	public List<CobrarSeguros> getFacturasPagas(int cods) {
		String sql =  "	SELECT   cfl.id_criar_fac_conv_linhas,   cfl.FK_criar_fac_convenio,   cfl.FK_factura,  fc.Data_da_facturacao,  fc.numero_factura,  pac.Titular, pac.nomeCompleto"
					+ " FROM tblcriarfacturaconveniocomlinhas cfl  JOIN tblfactura fc ON cfl.FK_factura = fc.id_factura JOIN tblfacturaplanodesaude fpl ON fc.id_factura = fpl.FK_factura JOIN vwpacientesnoplano pac ON fpl.FK_titular = pac.FK_parente"
					+ " WHERE  cfl.FK_criar_fac_convenio = ? ";
		List<CobrarSeguros> lista = new ArrayList<CobrarSeguros>();
		try {
			 con = Conexao.getConexao();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 while (rs.next()) {
				CobrarSeguros cs = new CobrarSeguros();
				cs.setFK_factura(rs.getInt("FK_factura"));
				cs.setNumero_factura(rs.getString("numero_factura"));
				cs.setNome(rs.getString("titular"));
				cs.setNome_paciente(rs.getString("nomecompleto"));
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("data_da_facturacao"));
				cs.setData_registo(data);
				lista.add(cs);
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	@Override
	public CobrarSeguros getFacturasPagasDetalhe(int cods) {
		String sql = "SELECT * FROM vwfacturaseguradorapagas  where fk_criar_factura_plano = ? ";
		 CobrarSeguros cs = new CobrarSeguros();
		try {
			 con = Conexao.getConexao(); 
			 PreparedStatement ps = con.prepareStatement(sql);
			 ps.setInt(1, cods);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next()){
				 cs.setTotais(rs.getDouble("totalpago"));
				 cs.setFK_aseguradora(rs.getInt("fk_convenio"));
				 cs.setEmpresa(rs.getString("empresa"));
				 cs.setNumero_factura(rs.getString("numero_fac_paga_convenio"));
				 cs.setNome(rs.getString("nomecompleto"));
				 cs.setFK_criar_fac_convenio(rs.getInt("fk_criar_factura_plano"));
				 Calendar data = Calendar.getInstance();
				 data.setTime(rs.getDate("data_pagamento"));
				 cs.setData_registo(data);
			 }
			 ps.close();
			 con.close();
		   }
			 catch (Exception e) {
				e.printStackTrace();// TODO: handle exception
			}
		return cs;
	}
	
	
	
	
	

	
	


}
