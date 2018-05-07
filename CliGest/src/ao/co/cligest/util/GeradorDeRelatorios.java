package ao.co.cligest.util;

import java.io.OutputStream;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporterParameter;
import ao.co.cligest.dao.Conexao;
import ao.co.cligest.entidades.Facturacao;
import ao.co.cligest.entidades.PacienteRelatorio;

public class GeradorDeRelatorios {

	   Connection conexao;
	   public void geraPdf(String jrxml, Map<String, Object> parametros, OutputStream saida) {

		        try {
		        	conexao = Conexao.getConexao();
		            // compila jrxml em memoria
		            JasperReport jasper = JasperCompileManager.compileReport(jrxml);

		            // preenche relatorio
		            JasperPrint print = JasperFillManager.fillReport(jasper, parametros, conexao);

		            // exporta para pdf
		            JRExporter exporter = new JRPdfExporter();
		            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, saida);

		            exporter.exportReport();
		            

		        } catch (Exception e) {
		            System.out.println(e);
		        }
		    }


	   
	   public void geraPdfComLista(String jrxml, Map<String, Object> parametros, OutputStream saida, List<Facturacao> lista	) {

	        try {
//	        	conexao = Conexao.getConexao();
	            // compila jrxml em memoria
	            JasperReport jasper = JasperCompileManager.compileReport(jrxml);

	            // preenche relatorio
	            JasperPrint print = JasperFillManager.fillReport(jasper, parametros, new JRBeanCollectionDataSource(lista));

	            // exporta para pdf
	            JRExporter exporter = new JRPdfExporter();
	            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
	            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, saida);

	            exporter.exportReport();
//	            conexao.close();

	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	    }
	   
	   public void geraPdfComListaAgendado(String jrxml, Map<String, Object> parametros, OutputStream saida, List<PacienteRelatorio> lista	) {

	        try {
	        	conexao = Conexao.getConexao();
	            // compila jrxml em memoria
	            JasperReport jasper = JasperCompileManager.compileReport(jrxml);

	            // preenche relatorio
	            JasperPrint print = JasperFillManager.fillReport(jasper, parametros, new JRBeanCollectionDataSource(lista));

	            // exporta para pdf
	            JRExporter exporter = new JRPdfExporter();
	            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
	            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, saida);

	            exporter.exportReport();
	            conexao.close();

	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	    }


	   
	   // teste para gerar word
	   public void geraWord(String jrxml, Map<String, Object> parametros, OutputStream saida) {

	        try {
	        	conexao = Conexao.getConexao();
	            // compila jrxml em memoria
	            JasperReport jasper = JasperCompileManager.compileReport(jrxml);

	            // preenche relatorio
	            JasperPrint print = JasperFillManager.fillReport(jasper, parametros, conexao);

	            // exporta para pdf
	            JRDocxExporter exporter = new JRDocxExporter();
	            exporter.setParameter(JRDocxExporterParameter.JASPER_PRINT, print);
	            exporter.setParameter(JRDocxExporterParameter.OUTPUT_STREAM, saida);

	            exporter.exportReport();
	            conexao.close();

	        } catch (Exception e) {
	            throw new RuntimeException("Erro ao gerar relat�rio", e);
	        }
	    }
}
