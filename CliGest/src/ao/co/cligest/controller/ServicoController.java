package ao.co.cligest.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.web.servlets.Controller;
import ao.co.cligest.dao.AgendaConsultaDAO;
import ao.co.cligest.dao.Formatando;
import ao.co.cligest.dao.ServicoDAO;
import ao.co.cligest.entidades.Paciente;
import ao.co.cligest.entidades.Servico;
/**
 * Servlet implementation class ServicoController
 */
@WebServlet("/ServicoController")
public class ServicoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String acao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServicoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out= response.getWriter(); 
		 
		AgendaConsultaDAO MDAO = new AgendaConsultaDAO();
		StringBuilder servico= new StringBuilder();
		 
		out.write(servico.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession rqsessio = request.getSession();
		String msg = "consulta marcada com sucesso.";
		System.out.println("Chamando pelo Metodo Post");
		Paciente funUp = new Paciente();
		AgendaConsultaDAO mcDao = new AgendaConsultaDAO();
		Formatando dts = new Formatando();

		 String ano = request.getParameter("ano");
		    String mes = request.getParameter("mes");
		    if (mes.length()<2)
		    	mes="0"+mes;
		    String dia =request.getParameter("dia");
		    if (dia.length()<2)
		    	dia="0"+dia;
		    String variavel = dia+"/"+mes+"/"+ano;
		    String dataVc = ano+"-"+mes+"-"+dia;
	    java.sql.Date data=dts.dataSql(variavel);
	   
		try
		{
			 
			String nomecompleto = request.getParameter("nome_p");
			int FK_entidade = Integer.parseInt(request.getParameter("fkpaciente"));
			funUp.setFK_entidade_paciente(FK_entidade);
			funUp.setFK_paciente(FK_entidade);
			int FK_doutor = Integer.parseInt(request.getParameter("profis"));
			funUp.setFK_doutor(FK_doutor);
			int FK_servico = Integer.parseInt(request.getParameter("opcao"));
			funUp.setId_servico(FK_servico);
			funUp.setFK_servico(FK_servico);
			int FK_especialidade = Integer.parseInt(request.getParameter("especialidade"));
			funUp.setFK_especialidade(FK_especialidade);
			int num_fun = Integer.parseInt(request.getParameter("usuario"));
			funUp.setFK_entidade(num_fun);
			int gestante = Integer.parseInt(request.getParameter("gestante"));
			funUp.setGestante(gestante);
			String numeroConsulta = request.getParameter("numConsulta");
			String hora = request.getParameter("hora");
			int dSemana = Integer.parseInt(request.getParameter("dSemana"));
			funUp.setFK_dia_dasemana(dSemana);
			funUp.setHora_daconfirmacao(hora);
			int FK_convenio = Integer.parseInt(request.getParameter("convenio"));
			funUp.setId_convenio(FK_convenio);
			int FK_plano = Integer.parseInt(request.getParameter("plano"));
			funUp.setId(FK_plano);
			funUp.setNumero_da_consulta(numeroConsulta); 
		    funUp.setData_agendamento(data);
		    funUp.setData(dts.data_registo());
		    boolean aux_hora = true;
		    boolean aux_servico = true;
		    int aux_data = dts.dataVencido(dataVc);
		  
		    
		    if(aux_hora && aux_servico && aux_data <= 0){
		    
		    	int ultimoId =  mcDao.inserirmarcacao(funUp);
		    
		    rqsessio.setAttribute("msg", msg);
		    rqsessio.setAttribute("nomecompleto", nomecompleto);
		    rqsessio.setAttribute("dataagendamento", data);
		    rqsessio.setAttribute("ultimoId", ultimoId);
			System.out.println("Mensagem: "+request.getSession().getAttribute("msg"));
			response.sendRedirect("navegacaoag?mod=ag&pesquisar=cc");
		    }
		    if(!aux_hora ){
		    	rqsessio.setAttribute("horaEr", "hora");
		    	response.sendRedirect("marConsultacontroller.do?mod=ag&acao=mc&pesquisar=mc&cod="+FK_entidade+"&gest="+gestante);
		    }
		    if( !aux_servico){
		    	rqsessio.setAttribute("horaEr", "servico");
		    	response.sendRedirect("marConsultacontroller.do?mod=ag&acao=mc&pesquisar=mc&cod="+FK_entidade+"&gest="+gestante);
		    }
		    if( aux_data > 0){
		    	rqsessio.setAttribute("horaEr", "diaAgenda");
		    	response.sendRedirect("marConsultacontroller.do?mod=ag&acao=mc&pesquisar=mc&cod="+FK_entidade+"&gest="+gestante);
		    }
		    }
		    catch(NumberFormatException er){er.printStackTrace();
		    response.sendRedirect("navegacao?mod=ajd");}
		catch(Exception er){System.out.println(er);}
		
	}
	
	
	 
	}
/*
 * 
 */

