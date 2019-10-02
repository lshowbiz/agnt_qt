package com.joymain.ng.util;

import java.util.HashMap;
import java.util.Date;
import java.util.ArrayList;
import java.util.Properties;
import java.io.*;
import java.text.SimpleDateFormat;

import javax.servlet.http.*;
import javax.servlet.*;

/**
 * 
 * Title: 文件操作工具类
 * Description:文件操作工具类
 * 取得虚拟目录对应的磁盘路径 Web站点主目录的位置为<%=request.getRealPath("/")%> JSP网页所在的目录位置<%=request.getRealPath("./")%> JSP网页所在目录上一层目录的位置<%=request.getRealPath("../")%>
 * Copyright: Copyright (c) 2006
 * Company: gt
 * @author Aidy.Q
 * @version 1.0
 */
public class FileUtil {

	/**
	 * 获取操作系统目录分割符：unix ：/ windows : \\ 通过获得系统属性构造属性类 prop
	 */
	public static String getFileSeparator() {
		Properties prop = new Properties(System.getProperties());
		//在标准输出中输出系统属性的内容
		String _file = "/";
		if (!prop.getProperty("file.separator").equals("/")) {
			_file = "\\";
		}
		return _file;
	}

	/**
	 * 把一个配置文件解析到一个哈希表中
	 * @param filename
	 */
	public static HashMap file2Hashtable(String filename) {
		try {
			HashMap hm = new HashMap();
			BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(new FileInputStream(filename))); // 设置缓冲区读入一个配置文件
			String sLineInformation;
			while ((sLineInformation = bufferedreader.readLine()) != null) {
				sLineInformation = sLineInformation.trim(); //去除字符串中的空格
				if (sLineInformation.length() > 0) //如果字符串sLineInformation1的长度大于零
				{
					int i = sLineInformation.indexOf("=");
					if (i > 0 && i < sLineInformation.length() - 1 && (sLineInformation.charAt(0) != '#') && !sLineInformation.startsWith("//")) //配置文件的每一行参数必须以不为#或//开头的字符串
						hm.put(sLineInformation.substring(0, i).trim(), sLineInformation.substring(i + 1).trim());
				}
			}
			bufferedreader.close();
			return hm;
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取访问文件全路径文件名
	 * @param filePath 文件的路径
	 * @param fileName 文件的扩展名
	 */
	public static String getFullName(String filePath, String fileName) {

		if (fileName == null) {
			return "";
		}
		if (filePath == null) {
			return fileName;
		}
		if (filePath.equals("")) {
			return fileName;
		} else {
			int i = fileName.lastIndexOf("/");
			int j = fileName.lastIndexOf("\\");
			if ((i != -1) || (j != -1)) { //fileName已是全路径文件名
				return fileName;
			}

			i = filePath.lastIndexOf("/");
			if (i > -1) { //路径分隔符是/
				if (i == filePath.length() - 1) {
					return filePath + fileName;
				} else {
					return filePath + "/" + fileName;
				}

			} else {
				j = filePath.lastIndexOf("\\");
				//路径分隔符是\\
				if (j == -1) {
					return filePath + "/" + fileName;

				} else if (j == filePath.length() - 1) {
					return filePath + fileName;
				} else {
					return filePath + "\\" + fileName;
				}

			}
		}
	}

	/**
	 * 取得一个文件名称的扩展名
	 * @param fileName
	 * @return 文件的扩展名
	 */
	public static String getFileExt(String fileName) {
		int i = fileName.lastIndexOf(".");
		String fileExt = "";
		if (i != -1) {
			fileExt = fileName.substring(i + 1, fileName.length());
		}
		return fileExt;
	}

	/**
	 * 去除文件扩展名，返回文件名 ，不包括路径
	 * @param fileName
	 * @return String
	 */
	public static String getFileName(String fileName) {
		int i = fileName.lastIndexOf('.');
		if (i != -1) {
			return fileName.substring(0, i);
		}
		return fileName;
	}

	/**
	 * 创建一个新的文件 param file文件类型 return 是否创建成功的布尔型变量
	 */
	public static boolean createNewFile(File file) {
		boolean result = false;
		if (file.exists()) {
			file.delete();
		}
		try {
			result = file.createNewFile();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return result;
	}

	/**
	 * 通过文件名称创建一个新的文件
	 * @param FileName 要创建的文件名称
	 * @return 是否创建成功的布尔型变量
	 */
	public static boolean createNewFile(String FileName) {
		File file = new File(FileName.trim());
		return createNewFile(file);
	}

	/**
	 * 判断某个文件是否存在
	 * @param FileName 文件名称,包含路径
	 * @return 是否存在的布尔型变量
	 */
	public static boolean isFileExists(String FileName) {
		File file = new File(FileName.trim());
		return file.exists();
	}

	public static boolean isFileExists(File file) {
		return file.exists();
	}

	/**
	 * 创建一个新目录
	 * @param file 要创建的文件目录
	 * @return 是否创建成功
	 */
	public static boolean createFolder(File file, boolean delOld) {
		boolean result = false;
		try {
			if (file.exists()) {
				if (delOld) {
					file.delete();
					result = file.mkdir();
				} else {
					return true;
				}
			} else {
				result = file.mkdir();
			}
		} catch (Exception ex) {
			result = false;
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * 创建一个新目录,如果原目录存在则返回,不删除
	 * @param file 要创建的文件目录
	 * @return 是否创建成功
	 */
	public static boolean createFolder(File file) {
		return createFolder(file, false);
	}

	/**
	 * 创建一个新目录
	 * @param folderName 要创建的文件目录名
	 * @return 是否创建成功
	 */
	public static boolean createFolder(String folderName) {
		File file = new File(folderName.trim());
		return createFolder(file);
	}

	/**
	 * 读取文件内容
	 * @param file 文件信息
	 * @return 文件内容的集合列表，文件每一行是列表的一个值
	 */
	public ArrayList readFileToList(File file) {
		BufferedReader breader;
		ArrayList list = new ArrayList();
		String line;
		try {
			breader = new BufferedReader(new FileReader(file));
			while ((line = breader.readLine()) != null)
				list.add(line);
			breader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 复制文件内容，从一源文件复制到目标文件
	 * @param sourcename 源文件
	 * @param targetname 目标文件
	 * @throws Exception
	 */
	public void copyFile(String sourcename, String targetname) throws Exception {
		BufferedReader breader;
		BufferedWriter bwriter;
		try {
			breader = new BufferedReader(new FileReader(sourcename.trim()));
			bwriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetname.trim())));
			while (breader.ready())
				bwriter.write(breader.read());
			breader.close();
			bwriter.close();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 在文件中添加内容
	 * @param str 要添加的字符串
	 * @param FileName 目标文件名
	 * @throws Exception
	 */
	public static void appendToFile(String str, String FileName){
	
		
		FileOutputStream stream;
		OutputStreamWriter writer;
		try {
			stream = new FileOutputStream(FileName.trim(), true);
			writer = new OutputStreamWriter(stream);
			writer.write(str+"\n");
			writer.close();
			stream.close();
		} catch (Exception e) {
			//throw e;
		}
	}

	/**
	 * 输出目录中的所有文件名
	 * @param filePath 目录名 return 文件名数组
	 */
	public static String[] readPathFile(String filePath) {
		File file = new File(filePath.trim());
		File[] tempFile = file.listFiles();
		String[] filestr = new String[tempFile.length];
		for (int i = 0; i < tempFile.length; i++) {
			if (tempFile[i].isFile()) {
				filestr[i] = tempFile[i].getName();
			}
		}
		return filestr;
	}

	/**
	 * 输出目录中某种文件扩展名的文件
	 * @param filePath 目录路径
	 * @param extension 文件扩展名
	 * @return 文件名数组
	 */
	public static String[] readExtFile(String filePath, String extension) {
		File file = new File(filePath.trim());
		File[] tempFile = file.listFiles();
		String[] filestr = new String[tempFile.length];
		int ie = 0;
		for (int i = 0; i < tempFile.length; i++) {
			if (tempFile[i].isFile()) {
				String FileName = tempFile[i].getName();
				if (getFileExt(FileName).equals(extension.trim())) {
					filestr[i] = FileName;
				}
			}
		}
		return filestr;
	}

	/**
	 * 输出目录中的所有文件夹名称
	 * @param filePath 文件夹路径
	 * @return 文件夹名称数组
	 */
	public static String[] readFolderByFile(String filePath) {
		File file = new File(filePath.trim());
		File[] tempFile = file.listFiles();
		String[] foldertr = new String[tempFile.length];
		;
		for (int i = 0; i < tempFile.length; i++) {
			if (tempFile[i].isDirectory()) {
				foldertr[i] = tempFile[i].getName();
			}
		}
		return foldertr;
	}

	/**
	 * 检查文件中是否为一个空
	 * @param file
	 * @return 为空返回true
	 * @throws IOException
	 */
	public static boolean IsNullFile(File file) throws IOException {
		boolean result = false;
		FileReader fr = new FileReader(file);
		if (fr.read() == -1) {
			result = true;
		} else {
			result = false;
		}
		fr.close();
		return result;
	}

	/**
	 * 一字符一字符的读取文件中的数据
	 * @param filePath
	 * @param fileName
	 * @return String
	 * @throws IOException
	 */
	public static String readAllFile(String filePath, String fileName) throws IOException {
		FileReader fr = new FileReader(filePath.trim() + fileName.trim());
		String fileContent = "";
		int count = fr.read();
		while (count != -1) {
			fileContent = fileContent + (char) count;
			count = fr.read();
			if (count == 13) {
				fr.skip(1);
			}
		}
		fr.close();
		return fileContent;
	}

	/**
	 * 一行一行的读取文件中的数据
	 * @param filePath 文件路径
	 * @param fileName 文件名称
	 * @return 文件内容的集合。
	 * @throws IOException
	 */
	public static ArrayList readLineFile(String filePath, String fileName) throws IOException {
		FileReader fr = new FileReader(filePath.trim() + fileName.trim());
		BufferedReader br = new BufferedReader(fr);
		ArrayList list = new ArrayList();
		String line = br.readLine();
		while (line != null) {
			line = br.readLine();
			list.add(line);
		}
		br.close();
		fr.close();
		return list;
	}

	/**
	 * 将字符串数组写入到文件
	 * @param filePath(文件路径)
	 * @param fileName(文件名)
	 * @param args 要写入的字符串数组
	 * @throws IOException
	 */
	public static void writeFile(String filePath, String fileName, String[] args) throws IOException {
		FileWriter fw = new FileWriter(filePath.trim() + fileName.trim());
		PrintWriter out = new PrintWriter(fw);
		for (int i = 0; i < args.length; i++) {
			out.write(args[i]);
			out.println();
			out.flush();
		}
		fw.close();
		out.close();
	}

	/**
	 * 将一字符串写入到文件
	 * @param filePath(文件路径)
	 * @param fileName(文件名)
	 * @param args 要写入的字符串
	 * @throws IOException
	 */
	public static void writeFile(String filePath, String fileName, String args) throws IOException {
		FileWriter fw = new FileWriter(filePath.trim() + fileName.trim());
		fw.write(args);
		fw.close();
	}

	/**
	 * 取得一个文件名称的路径 ，wendow，unix系统
	 * @param FileName 文件的路径
	 * @return String
	 */
	public static String getFilePath(String FileName) {
		int i = FileName.lastIndexOf("/");
		String path = "";
		if (i != -1) {
			path = FileName.substring(0, i + 1);
		}
		return path;
	}

	/**
	 * 根据文件的相对路径文件名称取得，文件的名称
	 * @param FileName 文件名称 包括目录＋文件名
	 * @return 相对路径文件名称
	 */
	public static String getDocFileName(String FileName) {
		String _file = getFileSeparator();
		int i = FileName.lastIndexOf("/");
		String file = "";
		if (i != -1) {
			file = FileName.substring(i + 1, FileName.trim().length());
		}
		return file;
	}

	/**
	 * 取得一个新的临时文件名称，该名称不带路径
	 * @param FileExt 文件的扩展名
	 * @return 文件的扩展名
	 */
	public static String getTmpFileName(String FileExt) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date currentTime = new Date();
		String fileName = formatter.format(currentTime);
		int i = (int) (Math.random() * 1000000000);
		String str = i + "";
		i = str.length() - 4;
		if (i < 0) {
			i = 0;
		}
		String sub = str.substring(i, str.length());
		fileName = fileName + sub;
		if (FileExt.length() > 0) {
			fileName = fileName + "." + FileExt;
		}
		return fileName;
	}

	/**
	 * 删除文件
	 * @param filename
	 * @return boolean
	 */
	public static final boolean deleteFile(String filename) {
		File aFile = new File(filename);
		return aFile.delete();
	}

	/**
	 * 通过文件的扩展名取得对应的contentType
	 * @param fileExt 文件的扩展名
	 * @return String
	 */
	private static final String getContentType(String fileExt) {
		fileExt = fileExt.toLowerCase();
		String contentType = "text/html; charset=GBK";
		if (fileExt.equals("doc")) {
			contentType = "application/vnd.ms-word; charset=GBK";
		}
		if (fileExt.equals("xls")) {
			contentType = "application/vnd.ms-excel; charset=GBK";
		}
		if (fileExt.equals("ppt")) {
			contentType = "application/vnd.ms-powerpoint; charset=GBK";
		}
		if (fileExt.equals("pdf")) {
			contentType = "application/pdf; charset=GBK";
		}
		if (fileExt.equals("gif")) {
			contentType = "image/gif; charset=GBK";
		}
		if (fileExt.equals("jpeg")) {
			contentType = "image/jpeg; charset=GBK";
		}
		if (fileExt.equals("png")) {
			contentType = "image/png; charset=GBK";
		}
		if (fileExt.equals("tiff")) {
			contentType = "image/tiff; charset=GBK";
		}
		if (fileExt.equals("xml")) {
			contentType = "text/xml; charset=GBK";
		}
		return contentType;
	}

	/**
	 * 服务器文件下载到客户端
	 * @param filePath: 要下载的文件
	 * @param fileName
	 * @param response:
	 */
	public static void downloadFile(String filePath, String fileName, String newFileName, HttpServletResponse response) throws IOException {
		String fullName = getFullName(filePath, fileName);
		if (isFileExists(fullName)) {
			String fileExt = getFileExt(fileName);
			//System.out.println("...............newfile=" + newName);

			response.setContentType(getContentType(fileExt));//设置CONTENT_TYPE
			response.setHeader("Content-disposition", "attachment; filename=" + newFileName);
			//输出文件
			ServletOutputStream sos = response.getOutputStream();
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;

			try {
				bis = new BufferedInputStream(new FileInputStream(fullName));
				bos = new BufferedOutputStream(sos);
				byte[] buff = new byte[2048];
				int bytesRead;
				while ((bytesRead = bis.read(buff, 0, buff.length)) != -1) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (final IOException e) {
				e.printStackTrace();
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
				if (sos != null)
					sos.close();
			}
		} else {
			response.setContentType("text/html; charset=GBK");
			PrintWriter out = response.getWriter();

			out.println("<html>");
			out.println("<head>");
			out.println(" <script  type=\"text/javascript\" language=\"JavaScript1.1\"> " + 
					"       window.moveTo(150,100); window.resizeTo(400,500);" + "   </script>");

			out.println("<title>下载文件</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>要下载的文件[" + fullName + "]不存在！</h1>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	public static String encodeFileName(String fileName, HttpServletRequest request) throws IOException {
		String agent = request.getHeader("USER-AGENT");
		String codedfilename = null;
		if (null != agent && -1 != agent.indexOf("MSIE")) {
			String prefix = fileName.lastIndexOf(".") != -1 ? fileName.substring(0, fileName.lastIndexOf(".")) : fileName;
			String extension = fileName.lastIndexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".")) : "";
			String name = java.net.URLEncoder.encode(fileName, "UTF8");
			if (name.lastIndexOf("%0A") != -1) {
				name = name.substring(0, name.length() - 3);
			}
			int limit = 150 - extension.length();
			if (name.length() > limit) {
				name = java.net.URLEncoder.encode(prefix.substring(0, Math.min(prefix.length(), limit / 9)), "UTF-8");
				if (name.lastIndexOf("%0A") != -1) {
					name = name.substring(0, name.length() - 3);
				}
			}

			codedfilename = name + extension;
		} else if (null != agent && -1 != agent.indexOf("Mozilla")) {
			codedfilename = "=?UTF-8?B?" + (new String(org.apache.commons.codec.binary.Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";
		} else {
			codedfilename = fileName;
		}
		return codedfilename;
	}

	public static void saveLogger(String fileName,Date beginTime,Date endTime,String msg){
		//Modify By WuCF 20140303 注释掉运行时间测试 
		/*Long begin=beginTime.getTime();
		Long end=endTime.getTime();
		if((end-begin)>300L){
			try {
				FileUtil.appendToFile("\n用时:"+(end-begin)+"毫秒"+"  "+msg, FileUtil.getFileSeparator()+fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		
		
	}
	
	public static void main(String[] args) throws Exception {
		FileUtil fu = new FileUtil();
		File file = new File("D:/金财工程/ddd.doc");
		String str[] = fu.readFolderByFile("D:/金财工程");
		System.out.println(fu.IsNullFile(file));
		/*
		 * for(int i=0;i<str.length;i++) { System.out.println(str[i]); }
		 */
	}


}
