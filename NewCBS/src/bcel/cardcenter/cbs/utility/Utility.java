package bcel.cardcenter.cbs.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.apache.log4j.Logger;

public class Utility{
	private static final Logger logger = Logger.getLogger(Utility.class);
	
	public static java.sql.Date str2Date(String str) {
		java.util.Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			date = sdf.parse(str);
		} catch (Exception ex) {
			logger.debug("Exception occur while try to convert string to date",ex);
		}

		return new java.sql.Date(date.getTime());
	}
	
	public static java.sql.Date str2Date01(String str) {
		java.util.Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

		try {
			date = sdf.parse(str);
		} catch (Exception ex) {
			logger.debug("Exception occur while try to convert string to date",ex);
		}

		return new java.sql.Date(date.getTime());
	}
	
	public static java.sql.Date str2Date02(String str) {
		java.util.Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			date = sdf.parse(str);
		} catch (Exception ex) {
			logger.debug("Exception occur while try to convert string to date",ex);
		}

		return new java.sql.Date(date.getTime());
	}
	
	public static Date str2Date03(String dat) {
		java.util.Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		try {
			date = sdf.parse(dat);
		} catch (Exception ex) {
			logger.debug("Exception occur while try to convert string to date",ex);
		}

		return new java.sql.Date(date.getTime());
	}
	
	public static java.sql.Date str2Date04(String dat){
		java.util.Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");

		try {
			date = sdf.parse(dat);
		} catch (Exception ex) {
			logger.debug("Exception occur while try to convert string to date",ex);
		}

		return new java.sql.Date(date.getTime());
	}
	
	public static java.sql.Date str2Date05(String dat){
		java.util.Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMMyy");
		
		try{
			date = sdf.parse(dat);
		}
		catch(Exception ex){
			logger.debug("Exception occur while try to convert string to date",ex);
			return null;
		}
		
		return new java.sql.Date(date.getTime());
	}
	
	public static java.sql.Date str2Date06(String dat){
		java.util.Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("MMM ddd, yyyy");
		
		try{
			date = sdf.parse(dat);
		}
		catch(Exception ex){
			logger.debug("Exception occur while try to convert string to date",ex);
			return null;
		}
		
		return new java.sql.Date(date.getTime());
	}
	
	public static java.sql.Date str2Date07(String str){
		DateFormat format = new SimpleDateFormat("MMddyy");
		try {
			java.util.Date ddate = format.parse(str);
			java.sql.Date sdate = new java.sql.Date(ddate.getTime());
			return sdate;
		} catch (ParseException e) {
			logger.debug("Exception Occur while try to parse text to date", e);
			return null;
		}
	}
	public static String date2Str(java.util.Date date) throws Exception{
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        return sdf.format(date);
	}
	
	public static String date2Str(java.sql.Date date) throws Exception{
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        return sdf.format(date);
	}
	
	public static java.sql.Time str2Time(String str) throws Exception {
		java.util.Date time = null;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		try {
			time = sdf.parse(str);
		} catch (Exception ex) {
			logger.debug("Exception occur while try to convert string to Time",ex);
			throw ex;
		}

		return new java.sql.Time(time.getTime());
	}
	
	public static java.sql.Timestamp str2Timestamp(String str) throws Exception{
		java.util.Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try {
			date = sdf.parse(str);
		} catch (Exception ex) {
			logger.debug("Exception occur while try to convert string to time stamp",ex);
			throw ex;
		}

		return new java.sql.Timestamp(date.getTime());
	}
	
	private double number;
	private String subStr;
	
	public void setNumber(double number){
		this.number=number;
	}
	public String getNumber(){
		return numberFormat(number);
	}
	public static String numberFormat(double amount){
		
		DecimalFormat exFormat1 = new DecimalFormat("#,##0.00");
		String numberFormat = String.valueOf(exFormat1.format(amount));
		
		return numberFormat;
	}
	
	public void setSubStr(String subStr){
		this.subStr=subStr;
	}
	
	public String getSubStr(){
		if(subStr.length()>19)
			return subStr.substring(0,10);
		else
			return subStr;
	}
	public static String dateFormat(java.util.Date date){
		SimpleDateFormat sdf1 = new SimpleDateFormat("MMM dd, YYYY");
		String dateFormat = String.valueOf(sdf1.format(date));
		return dateFormat;
	}
	
	public static String dateFormat(java.sql.Date date){
		SimpleDateFormat sdf1 = new SimpleDateFormat("MMM dd, YYYY");
		String dateFormat = String.valueOf(sdf1.format(date));
		return dateFormat;
	}

	public static void copyTransfer(String srcPath, String destPath)
			throws IOException {

		FileChannel srcChannel = new FileInputStream(srcPath).getChannel();
		FileChannel destChannel = new FileOutputStream(destPath).getChannel();
		try {
			srcChannel.transferTo(0, srcChannel.size(), destChannel);
		} finally {
			srcChannel.close();
			destChannel.close();
		}
	}
	
	public static double str2Double(String str){
		double amount = Double.parseDouble(str)/100.00;
		return amount;
	}
}
