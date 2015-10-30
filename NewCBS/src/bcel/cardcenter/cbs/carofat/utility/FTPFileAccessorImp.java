package bcel.cardcenter.cbs.carofat.utility;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

public class FTPFileAccessorImp implements FTPFileAccessor {
	private static final Logger logger = Logger.getLogger(FTPFileAccessorImp.class);
	private String host;
	private int port;
	private String user;
	private String passwd;
	private String workPath;
	private String localPath;
	private List<String> sourceFiles;
	public void setHost(String host) {
		this.host = host;
	}
	public void setPort(int port){
		this.port = port;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public void setWorkPath(String workPath) {
		this.workPath = workPath;
	}
	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	public List<String> access() throws Exception {
		FTPClient client = new FTPClient();
		List<String> files = new ArrayList<String>();
		sourceFiles = new ArrayList<String>();
		try {
			FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
			client.configure(conf);
			client.connect(host,port);
			boolean ret = client.login(user, passwd);
			if (!ret) {
				throw new Exception(
						"could not access to ftp user and password is invalid");
			}
			int replyCode = client.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				throw new Exception("ftp client is not in passive mode");
			}
			client.enterLocalPassiveMode();
			ret = client.changeWorkingDirectory(workPath);
			if (ret) {
				String[] list = client.listNames();
				if(list == null){
					logger.debug("input files are not found in specific location.");
					throw new IOException("No File in Specific location");
				}
				for (int i = 0; i < list.length; i++) {
					String name = list[i];
					logger.debug(i + " : " + name);
					String destFile = localPath + "/" + name;
					FileOutputStream os = new FileOutputStream(destFile);
					String sourceFile = workPath + "/" + name;
					client.setFileType(FTPClient.BINARY_FILE_TYPE);
					boolean success = client.retrieveFile(sourceFile, os);
					if(success){
						logger.debug("retrieve " + sourceFile + " is completed.");
						sourceFiles.add(sourceFile);
						files.add(destFile);
					}
					else{
						logger.debug("retrieve "+ sourceFile + " is failed.");
					}
					
				}
			} else {
				logger.debug("can't change current working directory.");
				throw new Exception("can't change current working directory.");
			}
		} catch (IOException e) {
			logger.debug("Exception occur while try to access ftp", e);
			throw new IOException("file excepiton occurr could process file");
		}
		finally{
			client.disconnect();
		}
		
		return files;
	}
	@Override
	public void readFile(String filePath) throws Exception {
		FileReader fileReader = new FileReader(filePath);
		BufferedReader buffer = new BufferedReader(fileReader);
		String line = null;
		while((line=buffer.readLine())!=null){
			logger.debug(line);
		}
	}
	@Override
	public void deleteSourceFiles(int index) throws Exception{
		FTPClient client = new FTPClient();
		try {
			FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
			client.configure(conf);
			client.connect(host,port);
			boolean ret = client.login(user, passwd);
			if (!ret) {
				throw new Exception(
						"could not access to ftp user and password is invalid");
			}
			int replyCode = client.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				throw new Exception("ftp client is not in passive mode");
			}
			client.enterLocalPassiveMode();
			String sourceFile = sourceFiles.get(index);
			ret = client.deleteFile(sourceFile);
			if(ret){
				logger.debug("delete source file "+ sourceFile +" successful.");
			}
			else{
				logger.debug("delete source file "+ sourceFile +" unsuccessful.");
			}
			
		} catch (IOException e) {
			logger.debug("Exception occur while try to access ftp", e);
			throw new IOException("file excepiton occurr could process file");
		}
		finally{
			client.disconnect();
		}
	}
	
}
