package bcel.cardcenter.cbs.utility.report;

import java.util.HashMap;

public class CupTxnReportExportion extends ReportExportion {
	@Override
	public void setParamMap(){
		Parameter param = super.getParam();
		super.paramMap = new HashMap<String,Object>();
		
		paramMap.put("IMPORTDATE", param.getImportDate());
		paramMap.put("TITLE", param.getType());
	}
}
