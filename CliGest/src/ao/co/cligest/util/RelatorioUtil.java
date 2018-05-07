package ao.co.cligest.util;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;





import com.sun.xml.internal.ws.util.UtilException;

import ao.co.cligest.dao.Conexao;

public class RelatorioUtil {

	public static byte[] criarRelatorio(String arquivoJasper, Map<String, Object> parametros) {
		byte[] bytes = null;
		try {
			Connection conn = Conexao.getConexao();
						
			JasperReport relatorioJasper = (JasperReport) JRLoader.loadObjectFromFile(arquivoJasper);
			
			bytes = JasperRunManager.runReportToPdf(relatorioJasper, parametros, conn);
		} catch (JRException e) {
			e.printStackTrace();
		} catch (UtilException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return bytes;
	}
	
}
