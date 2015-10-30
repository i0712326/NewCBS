package bcel.cardcenter.cbs.carofat.utility;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import jxl.common.Logger;

import bcel.cardcenter.cbs.carofat.entity.CupTxnSettle;
import bcel.cardcenter.cbs.carofat.entity.CupTxnState;
import bcel.cardcenter.cbs.utility.Utility;

public class CupTxnSettlementReader implements CupTxnFileReader {
	private static final Logger logger = Logger.getLogger(CupTxnSettlementReader.class);
	private File file;
	private Date importDate;
	public void setFile(File file){
		this.file = file;
	}
	public void setImportDate(Date importDate){
		this.importDate = importDate;
	}
	@Override
	public List<CupTxnSettle> readSettle() throws Exception{
		List<CupTxnSettle> cupTxns = new ArrayList<CupTxnSettle>();
		String fName = file.getName();
		String sub = fName.substring(3,9);
		Date date = Utility.str2Date04(sub);
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String line = null;

			while ((line = bufferedReader.readLine()) != null) {
				String ttime	= line.substring(35,41).trim();
				String trace    = line.substring(24, 30).trim();
				String card     = line.substring(42, 58).trim();
				String terminalId = line.substring(92, 101).trim();
				String merchant = line.substring(117, 142).trim();
				String address	= line.substring(142, 154).trim();
				String country	= line.substring(154, 157).trim();
				String apprCode = line.substring(174, 180).trim();
				String amount	= line.substring(215, 227).trim();
				String fee		= line.substring(286, 298).trim();
				String refer	= line.substring(158, 170).trim();
				
				CupTxnSettle cupTxn = new CupTxnSettle();
				cupTxn.setDate(date);
				cupTxn.setCard(card);
				cupTxn.setTerminalId(terminalId);
				cupTxn.setMerchant(merchant);
				cupTxn.setAddress(address);
				cupTxn.setCountry(country);
				cupTxn.setApprCode(apprCode);
				cupTxn.setRefer(refer);
				cupTxn.setTime(ttime);
				
				if(apprCode.equals(""))
					cupTxn.setType("INQ");
				cupTxn.setTrace(trace);
				double amt = Double.parseDouble(amount)/100.00;
				cupTxn.setAmount(amt);
				double com = Double.parseDouble(fee)/100.00;
				cupTxn.setFee(com);
				double net = amt+com;
				cupTxn.setNet(net);
				cupTxn.setImportDate(importDate);
				java.util.Date pDate = new java.util.Date();
				long time = pDate.getTime();
				java.sql.Date processDate = new java.sql.Date(time);
				cupTxn.setProcessDate(processDate);
				logger.debug(cupTxn.toString());
				cupTxns.add(cupTxn);
				
			}
		}catch (FileNotFoundException fileEx) {
			logger.debug("Exception occur while try to open specific file",fileEx);
			throw new FileNotFoundException();
		}
		catch(IOException ioEx){
			logger.debug("Exception occured whiel try to access specific file",ioEx);
			throw new IOException();
		}
		
		return cupTxns;
	}
	@Override
	public List<CupTxnState> readState() throws Exception {
		return null;
	}
}
