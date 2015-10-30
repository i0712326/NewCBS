package bcel.cardcenter.cbs.marofat.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import bcel.cardcenter.cbs.marofat.entity.MasterCardSettle;
import bcel.cardcenter.cbs.utility.Utility;
@Service("settlementReader")
public class SettlementReaderImp implements SettlementReader{
	private final static Logger logger = Logger.getLogger(SettlementReaderImp.class);
	@Override
	public List<MasterCardSettle> readSettlment(File file) throws IOException {
		List<MasterCardSettle> masterCards = new ArrayList<MasterCardSettle>();
		try{
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			String line = null;
			int i = 0, j = 1;
			int len = getRows(file)-4;
			while((line=buffer.readLine())!=null){
				if(i>1 && i<len){
					String type   = line.substring(  0,   4).trim();
					String refer  = line.substring(  4,  13).trim();
					String insti  = line.substring( 14,  19).trim();
					String date   = line.substring( 18,  24).trim();
					String time   = line.substring( 24,  32).trim();
					String card   = line.substring( 32,  51).trim();
					String proc   = line.substring( 51,  57).trim();
					String termId = line.substring( 92, 100).trim();
					String res    = line.substring(102, 104).trim();
					String cardt  = line.substring(104, 106).trim();
					String excep  = line.substring(107, 110).trim();
					String rea    = line.substring(110, 114).trim();
					String apprc  = line.substring(118, 124).trim();
					String txnCur = line.substring(124, 127).trim();
					String txnAmt = line.substring(128, 140).trim();
					String txnCat = line.substring(140, 141).trim();
					String txnFee = line.substring(154, 162).trim();
					String txnfeC = line.substring(162, 163).trim();
					String setCur = line.substring(163, 166).trim();
					String setAmt = line.substring(172, 187).trim();
					String setCat = line.substring(187, 188).trim();
					String setFee = line.substring(188, 198).trim();
					String setFeC = line.substring(198, 199).trim();
					String errAmt = line.substring(206, 218).trim();
					String errCat = line.substring(218, 219).trim();
					String errSet = line.substring(219, 231).trim();
					String errSeC = line.substring(231, 232).trim();
					
					double errAmount = 0;
					String regex = "^[0-9]{1,}$";
					Pattern p = Pattern.compile(regex);

					Matcher m = p.matcher(errAmt);
					if(m.find())
						errAmount = Double.parseDouble(errAmt)/100;
					double errSetAmount = Double.parseDouble(errSet)/100;
					
					java.sql.Date sdate = Utility.str2Date(date);
					double ttxnAmt = Utility.str2Double(txnAmt);
					double ttxnFee = Utility.str2Double(txnFee);
					double ssetAmt = Utility.str2Double(setAmt);
					double ssetFee = Utility.str2Double(setFee);
					
					MasterCardSettle master = new MasterCardSettle();
					master.setType(type);
					master.setRefer(refer);
					master.setInsti(insti);
					master.setTxnDate(sdate);
					master.setTxnTime(time);
					master.setCard(card);
					master.setProcess(proc);
					master.setTerminalId(termId);
					master.setRes(res);
					master.setCardType(cardt);
					master.setExcepCode(excep);
					master.setRea(rea);
					master.setApprcode(apprc);
					master.setTxnCurr(txnCur);
					master.setTxnAmt(ttxnAmt);
					master.setTxnGroup(txnCat);
					master.setTxnFee(ttxnFee);
					master.setTxnFeeCat(txnfeC);
					master.setSettleCurr(setCur);
					master.setSettleAmt(ssetAmt);
					master.setSettleGroup(setCat);
					master.setFee(ssetFee);
					master.setFeeGroup(setFeC);
					master.setErrAmt(errAmount);
					master.setErrCat(errCat);
					master.setErrSet(errSetAmount);
					master.setErrSeC(errSeC);
					logger.debug(j+","+master.toString());
					masterCards.add(master);
					j++;
				}
				i++;
			}
			return masterCards;
		}
		catch(IOException e){
			logger.debug("Exception occur while try to read file",e);
			throw new IOException(e);
		}
	}
	
	private int getRows(File file){
		try{
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			int len = 0;
			while(buffer.readLine()!=null){
				len++;
			}
			return len;
		}
		catch(Exception ex){
			logger.debug("Exception occur while try to count rows number", ex);
			return 0;
		}
	}
}
