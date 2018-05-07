package ao.co.cligest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ao.co.cligest.entidades.Agenda;
import ao.co.cligest.entidades.Funcionario;


public class AgendaDAO {
	
	private Connection con ;
	
	public List<Funcionario> buscaPorNome(String nome) {
		List<Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM vwentidadecidadaocomofuncionario WHERE NOME LIKE ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, nome+"%");
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Funcionario fun = new Funcionario();
				fun.setId(rs.getInt("ID_ENTIDADE"));
				fun.setNome(rs.getString("NOME"));
				fun.setNomem(rs.getString("NOME_MEIO"));
				fun.setApelido(rs.getString("ULTIMO_NOME"));
				fun.setNomeEsp(rs.getString("ESPECIALIDADE"));
				lista.add(fun);
			}
			preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return lista;
	}
	public int PesquisarFuncionario(String pac)
	{
		int fkp = 0;
		String sql = "SELECT ID_ENTIDADE FROM vwentidadecidadaocomofuncionario where nome = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, pac);
			ResultSet rs = preparador.executeQuery();
			
			if(rs.next())
				fkp = (rs.getInt("ID_ENTIDADE"));
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	    finally{
					try{
						con.close();
					}
					catch(SQLException ef){
						System.out.println("Erro finalizar: "+ef);
					}
			}
			return fkp;
	}
	public List<Funcionario> buscaFun() {
		List<Funcionario> lista = new ArrayList<Funcionario>();
		String sql = "SELECT * FROM vwentidadecidadaocomofuncionario where id_profissao = 6 ";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				Funcionario fun = new Funcionario();
				fun.setId(rs.getInt("ID_ENTIDADE"));
				fun.setNome(rs.getString("NOME"));
				fun.setNomem(rs.getString("NOME_MEIO"));
				fun.setApelido(rs.getString("ULTIMO_NOME"));
				fun.setNomeEsp(rs.getString("ESPECIALIDADE"));
				lista.add(fun);
			}
			preparador.close();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return lista;
	}
	
	public Funcionario buscaFunAg(String num) {
		Funcionario lista = new Funcionario();
		String sql = "SELECT * FROM VWPESQUISAR WHERE IDFUN LIKE ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, num);
			ResultSet rs = preparador.executeQuery();
			if (rs.next()) {
				lista.setId(rs.getInt("IDFUN"));
				lista.setNome(rs.getString("NOME"));
				lista.setNomem(rs.getString("NOME_MEIO"));
				lista.setApelido(rs.getString("ULTIMO_NOME"));

			}
			preparador.close();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		// System.out.print("Nome: "+lista.getNome());
		return lista;
	}
		
		
	// INSERIR DATA AUTOMM�TICA
	public java.sql.Date data_registo() {
		java.sql.Date dt_registo = null;
		Calendar c = Calendar.getInstance(); // Cria um objeto para armazenar a
												// data atual
		c.setTime(new Date()); // pega a data atual do sistema
		int diaAtual = c.get(Calendar.DAY_OF_MONTH); // DIA
		int mesAtual = c.get(Calendar.MONTH) + 1; // MES
		int anoAtual = c.get(Calendar.YEAR); // ANO
		String hoje = anoAtual + "/" + mesAtual + "/" + diaAtual;
		DateFormat formate = new SimpleDateFormat("yyyy/MM/dd");
		java.util.Date a;
		try {
			a = (java.util.Date) formate.parse(hoje);
			dt_registo = new java.sql.Date(a.getTime());
		} catch (ParseException e) {
		}
		return dt_registo;
	}
		
		 // Verificar se o doutor ja foi agendado para o mesmo dia na semana
	public boolean checkDiaAgendar(Agenda fun) {
		boolean ok = true;
		String sql = "Select * from tblagendadetrabalho where FK_entidade = ? and FK_dia_da_semana = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement pr = con.prepareStatement(sql);
			pr.setInt(1, fun.getFK_entidade());
			pr.setInt(2, fun.getDia_da_semana());
			ResultSet rs = pr.executeQuery();
			if (rs.next())
				ok = false;
			pr.close();
			System.out.println("Dia ja cadastrado! com mesmo doutor....");
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return ok;
	}
		
	public void agendar(Agenda fun) {

		String sql = "INSERT INTO tblagendadetrabalho(FK_entidade, FK_dia_da_semana, hora_de_inicio, "
				+ "hora_do_fin,data)" + " VALUES(?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement agendaHora = con.prepareStatement(sql);
			agendaHora.setInt(1, fun.getFK_entidade());
			agendaHora.setInt(2, fun.getDia_da_semana());
			agendaHora.setString(3, fun.getHora_de_inicio());
			agendaHora.setString(4, fun.getHora_do_fim());
			agendaHora.setDate(5, data_registo());
			agendaHora.execute();
			agendaHora.close();
			System.out.println("Cadastro de Sucesso....");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
	}
	
	 public int pegaUltimaGTQ() throws SQLException {

		 	con = Conexao.getConexao();
	        int seqnciaNumeroForn = 0;
	        String sql = "select MAX(id_agen_test_quali) from tblagendartestedequalidade";
	        try {
	            PreparedStatement stm = con.prepareStatement(sql);
	            ResultSet recebeSeqncia = stm.executeQuery();
	            if (recebeSeqncia.next()) {
	                seqnciaNumeroForn = recebeSeqncia.getInt(1);

	            }
	            seqnciaNumeroForn++;
	        } catch (Exception e) {
	            System.out.println( "Sequencia de conta falhou");
	            System.out.println(e.getMessage());
	        }

	        return seqnciaNumeroForn;
	    }

	
	public int pegaDiaDaSemana(){
        Calendar c = Calendar.getInstance(); // Cria um objeto para armazenar a data atual
           if(c.getTime().toString().substring(0,3).equals("Sun"))
            return 1;
        else
            if(c.getTime().toString().substring(0,3).equals("Mon"))
            return 2;
        else
            
            if(c.getTime().toString().substring(0,3).equals("Tue"))
            return 3;
        else
            if(c.getTime().toString().substring(0,3).equals("Wed"))
            return 4;
        else
            if(c.getTime().toString().substring(0,3).equals("Thu"))
            return 5;
        else
        if(c.getTime().toString().substring(0,3).equals("Fri"))
            return 6;
            else
            if(c.getTime().toString().substring(0,3).equals("Sat"))
            return 7;
        
                    return 0;
    }
	
	public List<Agenda> pegarDadosAgenTQPorNumServico(int cod) {
		List<Agenda> listaMostrar = new ArrayList<Agenda>();
		String sql = "SELECT * FROM tblagendartestedequalidade WHERE FK_requisicao =?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, cod);
			ResultSet age = preparador.executeQuery();
			while (age.next()) {
				Agenda fun = new Agenda();
				fun.setId_agen_test_quali(age.getInt("id_agen_test_quali"));
				fun.setFK_requisicao(age.getInt("FK_requisicao"));
				fun.setNumero_doservico(age.getString("numero_doservico"));
				fun.setFK_funcionario(age.getInt("FK_funcionario"));
				fun.setFK_serv_test_quali(age.getInt("FK_serv_test_quali"));
				fun.setFK_empresa(age.getInt("FK_empresa"));
				fun.setFK_dia_dasemana(age.getInt("FK_dia_dasemana"));
				fun.setData(age.getDate("data"));
				fun.setHora(age.getTime("hora"));
				listaMostrar.add(fun);
			}
			preparador.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		 
		}
		return listaMostrar;
	}
	
	//Para pegar a ultima agenda teste de qualidade
	public int ultimAgTQFeita() throws SQLException {

		con = Conexao.getConexao();
		int seqnciaNumeroForn = 0;
		String sql = "select MAX(id_agen_test_quali) from tblagendartestedequalidade";
		try {
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet recebeSeqncia = stm.executeQuery();
			if (recebeSeqncia.next()) {
				seqnciaNumeroForn = recebeSeqncia.getInt(1);

			}
		} catch (Exception e) {
			System.out.println("Sequencia de conta falhou");
			System.out.println(e.getMessage());
		}

		return seqnciaNumeroForn;
	}
	
	//para agendar um teste de qualidade
	public boolean agendarTesteQuali(Agenda fun) {
		boolean verInsercao=false;
		String [] dataAux=new Formatando().data_registo().toString().split("-"); //Integer.parseInt(dataAux[2])
		System.out.println("Dia da semana"+Integer.parseInt(dataAux[2]));
		String sql = "INSERT INTO tblagendartestedequalidade(FK_requisicao,numero_doservico,FK_funcionario,FK_serv_test_quali,FK_empresa,FK_dia_dasemana,data,hora,tipo_empresa) VALUES(?,?,?,?,?,?,?,?,?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement agendaHora = con.prepareStatement(sql);
			agendaHora.setInt(1, fun.getFK_requisicao());
			agendaHora.setString(2, fun.getNumero_doservico());
			agendaHora.setInt(3, fun.getFK_funcionario());
			agendaHora.setInt(4, fun.getFK_serv_test_quali());
			agendaHora.setInt(5, fun.getFK_empresa());
			agendaHora.setInt(6, pegaDiaDaSemana());//fun.getFK_dia_dasemana()
			agendaHora.setDate(7, new Formatando().data_registo());
			agendaHora.setString(8, new Formatando().getHoraAtual());
			agendaHora.setString(9, fun.getTipo_empresa());
			agendaHora.execute();
			agendaHora.close();
			System.out.println("Cadastro de Sucesso....");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			verInsercao=true;
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				verInsercao=true;
			}
		}
		if(verInsercao)
			return false;
		
		return true;
	}

		
	
	public int XequeAgendaTrabalho(int ent, int dia)
	{
		int ok = 0;
		String sql = "SELECT * FROM tblagendadetrabalho WHERE FK_entidade =? and FK_dia_da_semana =?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			 preparador.setInt(1, ent);
			 preparador.setInt(2, dia);
			 ResultSet rs = preparador.executeQuery();
			 if(rs.next())
			 {
				 ok = 1;				 
			 }
			 preparador.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try{
				con.close();
			}
			catch(SQLException ef){
				System.out.println("Erro finalizar: "+ef);
			}
		}
		return ok;
	}
	
	public List<Agenda> MostrarDiasDaSemana() {
		List<Agenda> listaagenda = new ArrayList<Agenda>();
		String sql = "SELECT * FROM TBLDIADASEMANA";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			ResultSet ag = preparador.executeQuery();
			while (ag.next()) {
				Agenda fun = new Agenda();
				fun.setDia_da_semana(ag.getInt("id_dia_da_semana"));
				fun.setNome_dia_da_semana(ag.getString("dia_da_semana"));
				listaagenda.add(fun);
			}
			preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return listaagenda;
	}
	
	public List<Agenda> MostrarDiasDaSemana(String codigo) {
		List<Agenda> listaagenda = new ArrayList<Agenda>();
		String sql = "select id_dia_da_semana, dia_da_semana from tbldiadasemana where id_dia_da_semana not in (select FK_dia_da_semana from tblagendadetrabalho where fk_entidade = ?)";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, codigo);
			ResultSet ag = preparador.executeQuery();
			while (ag.next()) {
				Agenda fun = new Agenda();
				fun.setDia_da_semana(ag.getInt("id_dia_da_semana"));
				fun.setNome_dia_da_semana(ag.getString("dia_da_semana"));
				listaagenda.add(fun);
			}
			preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return listaagenda;
	}
	
	public List<Agenda> MostrarDiasDaSemanas(String codigo) {
		List<Agenda> listaagenda = new ArrayList<Agenda>();
		String sql = "select id_dia_da_semana, dia_da_semana from tbldiadasemana where id_dia_da_semana  = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, codigo);
			ResultSet ag = preparador.executeQuery();
			while (ag.next()) {
				Agenda fun = new Agenda();
				fun.setDia_da_semana(ag.getInt("id_dia_da_semana"));
				fun.setNome_dia_da_semana(ag.getString("dia_da_semana"));
				listaagenda.add(fun);
			}
			preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return listaagenda;
	}
	
	public List<Agenda> funcionarioagendadoMostrar(String cod) {
		List<Agenda> listaMostrar = new ArrayList<Agenda>();
		String sql = "SELECT * FROM vwfuncespecializadoeagendado WHERE FK_entidade =? order by id_da_semana";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, cod);
			ResultSet age = preparador.executeQuery();
			while (age.next()) {
				Agenda fun = new Agenda();
				fun.setId_agendatrabalho(age.getInt("id_agendatrabalho"));
				fun.setFK_entidade(age.getInt("FK_entidade"));
				fun.setFK_especialidade(age.getInt("id_especialidade"));
				fun.setNomeFuncionario(age.getString("nome"));
				fun.setNome_especialidade(age.getString("especialidade"));
				fun.setUltimonome(age.getString("ultimo_nome"));
				fun.setHora_de_inicio(age.getString("hora_de_inicio"));
				fun.setNome_dia_da_semana(age.getString("dia_da_semana"));
				fun.setHora_do_fim(age.getString("hora_do_fin"));
				fun.setData(age.getDate("data"));
				listaMostrar.add(fun);
			}
			preparador.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		 
		}
		return listaMostrar;
	}
	
	public List<Agenda> MedicosAgendandosDia(int dia) {
		List<Agenda> listaMostrar = new ArrayList<Agenda>();
		String sql = "SELECT * FROM vwfuncespecializadoeagendado where id_da_semana = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, dia);
			ResultSet age = preparador.executeQuery();
			while (age.next()) {
				Agenda fun = new Agenda();
				fun.setId_agendatrabalho(age.getInt("id_agendatrabalho"));
				fun.setFK_entidade(age.getInt("FK_entidade"));
				fun.setNomeFuncionario(age.getString("nome"));
				fun.setUltimonome(age.getString("ultimo_nome"));
				fun.setFK_especialidade(age.getInt("id_especialidade"));
				fun.setNome_especialidade(age.getString("especialidade"));
				fun.setHora_de_inicio(age.getString("hora_de_inicio"));
				fun.setNome_dia_da_semana(age.getString("dia_da_semana"));
				fun.setDia_da_semana(age.getInt("id_da_semana"));
				fun.setHora_do_fim(age.getString("hora_do_fin"));
				fun.setData(age.getDate("data"));
				fun.setNomeMedico(age.getString("nome")+" "+age.getString("ultimo_nome"));
				listaMostrar.add(fun);
			}
			preparador.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		 
		}
		return listaMostrar;
	}
	public List<Agenda> MedicosAgendandosDias(int esp) {
		List<Agenda> listaMostrar = new ArrayList<Agenda>();
		String sql = "SELECT * FROM vwfuncespecializadoeagendado where id_especialidade = ? group by FK_entidade";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, esp);
			ResultSet age = preparador.executeQuery();
			while (age.next()) {
				Agenda fun = new Agenda();
				fun.setId_agendatrabalho(age.getInt("id_agendatrabalho"));
				fun.setFK_entidade(age.getInt("FK_entidade"));
				fun.setNomeFuncionario(age.getString("nome"));
				fun.setUltimonome(age.getString("ultimo_nome"));
				fun.setFK_especialidade(age.getInt("id_especialidade"));
				fun.setNome_especialidade(age.getString("especialidade"));
				fun.setHora_de_inicio(age.getString("hora_de_inicio"));
				fun.setNome_dia_da_semana(age.getString("dia_da_semana"));
				fun.setDia_da_semana(age.getInt("id_da_semana"));
				fun.setHora_do_fim(age.getString("hora_do_fin"));
				fun.setData(age.getDate("data"));
				fun.setNomeMedico(age.getString("nome")+" "+age.getString("ultimo_nome"));
				listaMostrar.add(fun);
			}
			preparador.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		 
		}
		return listaMostrar;
	}
	public List<Agenda> MedicosAgendandosDiaEsp(int dia, int esp) {
		List<Agenda> listaMostrar = new ArrayList<Agenda>();
		String sql = "SELECT * FROM vwfuncespecializadoeagendado where id_da_semana = ? and id_especialidade = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, dia);
			preparador.setInt(2, esp);
			ResultSet age = preparador.executeQuery();
			while (age.next()) {
				Agenda fun = new Agenda();
				fun.setId_agendatrabalho(age.getInt("id_agendatrabalho"));
				fun.setFK_entidade(age.getInt("FK_entidade"));
				fun.setFK_especialidade(age.getInt("id_especialidade"));
				fun.setNome_especialidade(age.getString("especialidade"));
				fun.setNomeFuncionario(age.getString("nome"));
				fun.setUltimonome(age.getString("ultimo_nome"));
				fun.setHora_de_inicio(age.getString("hora_de_inicio"));
				fun.setNome_dia_da_semana(age.getString("dia_da_semana"));
				fun.setHora_do_fim(age.getString("hora_do_fin"));
				fun.setDia_da_semana(age.getInt("id_da_semana"));
				fun.setData(age.getDate("data"));
				fun.setNomeMedico(age.getString("nome")+" "+age.getString("ultimo_nome"));
				listaMostrar.add(fun);
			}
			preparador.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		 
		}
		return listaMostrar;
	}
	
	
		
	public void up_agendamentoModificar(Agenda fun) {
		String sql = "UPDATE TBLAGENDADETRABALHO SET FK_dia_da_semana=?, hora_de_inicio=?, hora_do_fin,data=? WHERE id_agendatrabalho=?";
		try {
			con = Conexao.getConexao();
			PreparedStatement agend = con.prepareStatement(sql);

			agend.setString(1, fun.getNomeFuncionario() + " " + fun.getUltimonome());
			agend.setString(2, fun.getNome_dia_da_semana());
			agend.setString(3, fun.getHora_de_inicio());
			agend.setString(4, fun.getHora_do_fim());
			agend.execute();
			agend.close();
			System.out.println("ALTERACAO com sucesso...TBLAGENDA");
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
	}
		
	public void delete_funcionarioagendado(int id) {
		String sql = "DELETE FROM tblagendadetrabalho  WHERE id_agendatrabalho=?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, id);
			preparador.execute();
			preparador.close();
			System.out.println("Exclusao com sucesso...TBLAGENDA");
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
	public void alt_agenda(Agenda ag) {
		String sql = "UPDATE tblagendadetrabalho SET FK_dia_da_semana =?, hora_de_inicio =?, hora_do_fin =? WHERE id_agendatrabalho = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement agenda = con.prepareStatement(sql);
			agenda.setInt(1, ag.getDia_da_semana());
			agenda.setString(2, ag.getHora_de_inicio());
			agenda.setString(3, ag.getHora_do_fim());
			agenda.setInt(4, ag.getId_agendatrabalho());
			agenda.execute();
			agenda.close();
			System.out.println("Alteracao com sucesso...TBLAGENDA");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
	}
		
	public void salvarAge(Agenda ag) {
		if (ag.getFK_entidade() != 0) {
			alt_agenda(ag);
		} else {
			agendar(ag);
		}
	}
		  
	public Agenda edit_agenda(int ed) {
		Agenda fun = new Agenda();
		String sql = "SELECT * FROM vwfuncespecializadoeagendado WHERE id_agendatrabalho = ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, ed);
			ResultSet rs = preparador.executeQuery();
			while (rs.next()) {
				fun.setNomeFuncionario(rs.getString("nome"));
				fun.setUltimonome(rs.getString("ultimo_nome"));
				fun.setFK_entidade(rs.getInt("FK_entidade"));
				fun.setNome_dia_da_semana(rs.getString("dia_da_semana"));
				fun.setDia_da_semana(rs.getInt("id_da_semana"));
				fun.setHora_de_inicio(rs.getString("hora_de_inicio"));
				fun.setHora_do_fim(rs.getString("hora_do_fin"));
				fun.setData(rs.getDate("data"));

			}
			preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return fun;
	}
		
	public List<Agenda> buscarFunAgendado(String nome) {
		List<Agenda> listaAge = new ArrayList<Agenda>();
		String sql = "SELECT * FROM vwfuncespecializadoeagendado WHERE nome LIKE ?";
		try {
			con = Conexao.getConexao();
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, nome);
			ResultSet age = preparador.executeQuery();
			while (age.next()) {
				Agenda fun = new Agenda();
				fun.setFK_entidade(age.getInt("FK_entidade"));
				fun.setId_agendatrabalho(age.getInt("id_agendatrabalho"));
				fun.setNomeFuncionario(age.getString("nome"));
				fun.setUltimonome(age.getString("ultimo_nome"));
				fun.setHora_de_inicio(age.getString("hora_de_inicio"));
				fun.setNome_dia_da_semana(age.getString("dia_da_semana"));
				fun.setHora_do_fim(age.getString("hora_do_fin"));
				fun.setData(age.getDate("data"));
				listaAge.add(fun);
			}
			preparador.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				con.close();
			} catch (SQLException ef) {
				System.out.println("Erro finalizar: " + ef);
			}
		}
		return listaAge;
	}


		
}