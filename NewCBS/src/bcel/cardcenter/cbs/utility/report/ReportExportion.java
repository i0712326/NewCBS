package bcel.cardcenter.cbs.utility.report;

import java.util.Map;
import java.util.List;

import org.apache.log4j.Logger;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;

public abstract class ReportExportion {
	private static final Logger logger = Logger.getLogger(ReportExportion.class);
	protected Map<String, Object> paramMap;
	private List<Report> reports;
	private Parameter param;
	
	public List<Report> getReports() {
		return reports;
	}
	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
	public Parameter getParam() {
		return param;
	}
	public void setParam(Parameter param) {
		this.param = param;
	}
	
	public abstract void setParamMap();
	
	public boolean exportReport() {
		try{
			String templatePath = param.getFormatPath();

			JasperReport jasper = JasperCompileManager
					.compileReport(templatePath);

			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(
					reports);

			JasperPrint print = JasperFillManager.fillReport(jasper, paramMap,
					ds);
			
			if(param.getFormat().equals("pdf"))
				JasperExportManager.exportReportToPdfFile(print,
					param.getDestPath());
			else{
				JRXlsExporter exporterXls = new JRXlsExporter();
				exporterXls.setParameter(JRExporterParameter.JASPER_PRINT, print);
				exporterXls.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,param.getDestPath());
				exporterXls.exportReport();
			}
		}
		catch(Exception ex){
			logger.debug("Exception occur while try to export report",ex);
			return false;
		}
		return true;
	}
	
	public boolean export(){
		this.setParamMap();
		return exportReport();
	}
}
