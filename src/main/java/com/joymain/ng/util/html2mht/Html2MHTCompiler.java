package com.joymain.ng.util.html2mht;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimePartDataSource;

import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.lexer.Page;
import org.htmlparser.util.DefaultParserFeedback;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
  
/** 
 * 
 * MHT文件解析�?
 * 
 */  
@SuppressWarnings("unchecked")  
public class Html2MHTCompiler {  
    private URL strWeb = null;  
    /** 网页地址 */  
    private String strText = null;  
    /** 网页文本内容 */  
    private String strFileName = null;  
    /** 本地文件�?*/  
    private String strEncoding = null;  
    /** 网页编码 */  
  
    // MHT格式附加信息  
    private String from = "thinkgem@gmail.com";  
    private String to;  
    private String subject;  
    private String cc;  
    private String bcc;  
    private String smtp = "localhost";  
  
    public static void main(String[] args) {  
//        String strUrl = "http://192.168.1.2:8080/ibc/paper/?tid=29&pid=29&ptid=&s=&f=&ps=π=";  
    	String strUrl = "http://localhost:8080/test/index.jsp?englishName=Tom";  
        String strEncoding = "utf-8";  
        String strText1 = JQuery.getHtmlText(strUrl, strEncoding);  
        String strText2 = "<img src=\"http://www.imathas.com/cgi-bin/mimetex.cgi?sqrt{2}\"/><meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" /><link rel=\"stylesheet\" type=\"text/css\" href=\"http://192.168.1.2:8080/ibc/theme/default/style.css\" /><P><div class=paper_list>sdfsdf<div class=paper>dfkjsldjfl<table><tr><td>abc</td><td>abc</td></tr><tr><td>abc</td><td>abc</td></tr></table></div></div><IMG SRC=\"http://192.168.1.13/cc.jpg\"/><SPAN>sdfsdf</SPAN></P><p><span style=\"font-size: 10pt; color:#f00;\"><font face=\"宋体\">在下列各溶液中，离子�?��能大量共存的�?span lang=\"EN-US\">                                (    )<o:p></o:p></span></font></span></p><p><font face=\"宋体\"><span lang=\"EN-US\" style=\"font-size: 10pt\">A</span><span style=\"font-size: 10pt\">．强碱�?溶液中：<span lang=\"EN-US\">K</span><sup>�?/sup>�?span lang=\"EN-US\">S<sup>2-</sup></span>�?span lang=\"EN-US\">ClO</span><sup>�?/sup>�?span lang=\"EN-US\">SO<sub>4</sub><sup>2</sup>< /span><sup>�?/sup><span lang=\"EN-US\"> <o:p></o:p></span></span></font></p><p><font face=\"宋体\"><span lang=\"EN-US\" style=\"font-size: 10pt\">B</span><span style=\"font-size: 10pt\">．含�?span lang=\"EN-US\">0.1mol</span></span></font><span style=\"font-family: MS Mincho; font-size: 10pt; mso-bidi-font-family: 'MS Mincho'\">?</span><font face=\"宋体\"><span lang=\"EN-US\" style=\"font-size: 10pt\">L</span><sup><span style=\"font-size: 10pt\">�?span lang=\"EN-US\">1 </span></span></sup><span lang=\"EN-US\" style=\"font-size: 10pt\">Fe<sup>3</sup></span><sup><span style=\"font-size: 10pt\">�?/span></sup><span style=\"font-size: 10pt\">的溶液中�?span lang=\"EN-US\">K</span><sup>�?/sup>�?span lang=\"EN-US\">Mg<sup>2</sup></span><sup>�? lt;/sup>�?span lang=\"EN-US\">I</span><sup>�?/sup>�?span lang=\"EN-US\">NO<sub>3</sub></span><sup>�? lt;/sup><span lang=\"EN-US\"><o:p></o:p></span></span></font></p><p><font face=\"宋体\"><span lang=\"EN-US\" style=\"font-size: 10pt\">C</span><span style=\"font-size: 10pt\">．无色溶液中�?span lang=\"EN-US\">Na</span><sup>�?/sup>�?span lang=\"EN-US\">K</span><sup>�?/sup>�?span lang=\"EN-US\">CO<sub>3</sub><sup>2</sup>< /span><sup>�?/sup>�?span lang=\"EN-US\">Cu<sup>2+</sup><o:p></o:p></span></span></font></p><p><font face=\"宋体\"><span lang=\"EN-US\" style=\"font-size: 10pt\">D</span><span style=\"font-size: 10pt\">．室温下�?span lang=\"EN-US\">pH</span>�?span lang=\"EN-US\">1</span>的溶液中�?span lang=\"EN-US\">Na</span><sup>�?/sup>�?span lang=\"EN-US\">Fe<sup>3</sup></span><sup>�? lt;/sup>�?span lang=\"EN-US\">NO<sub>3</sub></span><sup>�? lt;/sup>�?span lang=\"EN-US\">SO<sub>4</sub><sup>2</sup>< /span><sup>�?/sup><span lang=\"EN-US\"> <o:p></o:p></span></span></font></p>` ( sqrt{2} )/(2) `<p> </p><script type=\"text/javascript\" src=\"http://192.168.1.2:8080/ibc/manage/js/ASCIIMathMLwFallback2.js\"></script>";  
        Html2MHTCompiler h2t = new Html2MHTCompiler(strText1, strUrl, strEncoding, "c:\\test.mht");  
        h2t.compile();  
        //Html2MHTCompiler.mht2html("c:\\test.mht", "c:\\test.htm");  
    }  
  
    /** 
     * 
     * 
     * 方法说明：初始化 
     * 
     * 
     * 输入参数：strText 网页文本内容; strUrl 网页地址; strEncoding 网页编码; strFileName 本地文件�?
     * 
     * 
     * 返回类型�?
     * 
     */  
    public Html2MHTCompiler(String strText, String strUrl, String strEncoding,  
            String strFileName) {  
        try {  
            strWeb = new URL(strUrl);  
        } catch (MalformedURLException e) {  
  
            e.printStackTrace();  
            return;  
        }  
        this.strText = strText;  
        this.strEncoding = strEncoding;  
        this.strFileName = strFileName;  
    }  
  
    /** 
     * 
     * 
     * 方法说明：执行下载操�?
     * 
     * 
     * 输入参数�?
     * 
     * 
     * 返回类型�?
     * 
     */  
    public boolean compile() {  
        if (strWeb == null || strText == null || strFileName == null  
                || strEncoding == null)  
            return false;  
        HashMap urlMap = new HashMap();  
        NodeList nodes = new NodeList();  
        try {  
            Parser parser = createParser(strText);  
            parser.setEncoding(strEncoding);  
            nodes = parser.parse(null);  
        } catch (ParserException e) {  
            e.printStackTrace();  
        }  
        extractAllScriptNodes(nodes);  
        ArrayList urlScriptList = extractAllScriptNodes(nodes, urlMap);  
        ArrayList urlImageList = extractAllImageNodes(nodes, urlMap);  
        for (Iterator iter = urlMap.entrySet().iterator(); iter.hasNext();) {  
            Map.Entry entry = (Map.Entry) iter.next();  
            String key = (String) entry.getKey();  
            String val = (String) entry.getValue();  
            strText = JHtmlClear.replace(strText, val, key);  
        }  
        try {  
            createMhtArchive(strText, urlScriptList, urlImageList);  
        } catch (Exception e) {  
  
            e.printStackTrace();  
            return false;  
        }  
        return true;  
    }  
  
    /** 
     * 
     * 
     * 方法说明：建立HTML parser 
     * 
     * 
     * 输入参数：inputHTML 网页文本内容 
     * 
     * 
     * 返回类型：HTML parser 
     * 
     */  
    private Parser createParser(String inputHTML) {  
  
        Lexer mLexer = new Lexer(new Page(inputHTML));  
        return new Parser(mLexer, new DefaultParserFeedback(  
                DefaultParserFeedback.QUIET));  
    }  
  
    /** 
     * 
     * 
     * 方法说明：抽取基�?RL地址 
     * 
     * 
     * 输入参数：nodes 网页标签集合 
     * 
     * 
     * 返回类型�?
     * 
     */  
    private void extractAllScriptNodes(NodeList nodes) {  
        NodeList filtered = nodes.extractAllNodesThatMatch(new TagNameFilter(  
                "BASE"), true);  
        if (filtered != null && filtered.size() > 0) {  
            Tag tag = (Tag) filtered.elementAt(0);  
            String href = tag.getAttribute("href");  
            if (href != null && href.length() > 0) {  
                try {  
                    strWeb = new URL(href);  
                } catch (MalformedURLException e) {  
  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
  
    /** 
     * 
     * 
     * 方法说明：抽取网页包含的css,js链接 
     * 
     * 
     * 输入参数：nodes 网页标签集合; urlMap 已存在的url集合 
     * 
     * 
     * 返回类型：css,js链接的集�?
     * 
     */  
    private ArrayList extractAllScriptNodes(NodeList nodes, HashMap urlMap) {  
        ArrayList urlList = new ArrayList();  
        NodeList filtered = nodes.extractAllNodesThatMatch(new TagNameFilter(  
                "script"), true);  
        for (int i = 0; i < filtered.size(); i++) {  
            Tag tag = (Tag) filtered.elementAt(i);  
            String src = tag.getAttribute("src");  
            // Handle external css file's url  
            if (src != null && src.length() > 0) {  
                String innerURL = src;  
                String absoluteURL = makeAbsoluteURL(strWeb, innerURL);  
                if (absoluteURL != null && !urlMap.containsKey(absoluteURL)) {  
                    urlMap.put(absoluteURL, innerURL);  
                    ArrayList urlInfo = new ArrayList();  
                    urlInfo.add(innerURL);  
                    urlInfo.add(absoluteURL);  
                    urlList.add(urlInfo);  
                }  
                tag.setAttribute("src", absoluteURL);  
            }  
        }  
  
        filtered = nodes.extractAllNodesThatMatch(new TagNameFilter("link"),  
                true);  
        for (int i = 0; i < filtered.size(); i++) {  
            Tag tag = (Tag) filtered.elementAt(i);  
            String type = (tag.getAttribute("type"));  
            String rel = (tag.getAttribute("rel"));  
            String href = tag.getAttribute("href");  
  
            boolean isCssFile = false;  
            if (rel != null) {  
                isCssFile = rel.indexOf("stylesheet") != -1;  
            } else if (type != null) {  
                isCssFile |= type.indexOf("text/css") != -1;  
            }  
            // Handle external css file's url  
            if (isCssFile && href != null && href.length() > 0) {  
                String innerURL = href;  
                String absoluteURL = makeAbsoluteURL(strWeb, innerURL);  
                if (absoluteURL != null && !urlMap.containsKey(absoluteURL)) {  
                    urlMap.put(absoluteURL, innerURL);  
                    ArrayList urlInfo = new ArrayList();  
                    urlInfo.add(innerURL);  
                    urlInfo.add(absoluteURL);  
                    urlList.add(urlInfo);  
                }  
                tag.setAttribute("href", absoluteURL);  
            }  
        }  
  
        return urlList;  
    }  
  
    /** 
     * 
     * 
     * 方法说明：抽取网页包含的图像链接 
     * 
     * 
     * 输入参数：nodes 网页标签集合; urlMap 已存在的url集合 
     * 
     * 
     * 返回类型：图像链接集�?
     * 
     */  
    private ArrayList extractAllImageNodes(NodeList nodes, HashMap urlMap) {  
        ArrayList urlList = new ArrayList();  
        NodeList filtered = nodes.extractAllNodesThatMatch(new TagNameFilter(  
                "IMG"), true);  
        for (int i = 0; i < filtered.size(); i++) {  
            Tag tag = (Tag) filtered.elementAt(i);  
            String src = tag.getAttribute("src");  
            // Handle external css file's url  
            if (src != null && src.length() > 0) {  
                String innerURL = src;  
                String absoluteURL = makeAbsoluteURL(strWeb, innerURL);  
                if (absoluteURL != null && !urlMap.containsKey(absoluteURL)) {  
                    urlMap.put(absoluteURL, innerURL);  
                    ArrayList urlInfo = new ArrayList();  
                    urlInfo.add(innerURL);  
                    urlInfo.add(absoluteURL);  
                    urlList.add(urlInfo);  
                }  
                tag.setAttribute("src", absoluteURL);  
            }  
        }  
  
        return urlList;  
    }  
  
    /** 
     * 
     * 
     * 方法说明：相对路径转绝对路径 
     * 
     * 
     * 输入参数：strWeb 网页地址; innerURL 相对路径链接 
     * 
     * 
     * 返回类型：绝对路径链�?
     * 
     */  
    public static String makeAbsoluteURL(URL strWeb, String innerURL) {  
  
        // 去除后缀  
        int pos = innerURL.indexOf("?");  
        if (pos != -1) {  
            innerURL = innerURL.substring(0, pos);  
        }  
        if (innerURL != null && innerURL.toLowerCase().indexOf("http") == 0) {  
            System.out.println(innerURL);  
            return innerURL;  
        }  
  
        URL linkUri = null;  
        try {  
            linkUri = new URL(strWeb, innerURL);  
        } catch (MalformedURLException e) {  
  
            e.printStackTrace();  
            return null;  
        }  
  
        String absURL = linkUri.toString();  
        absURL = JHtmlClear.replace(absURL, "../", "");  
        absURL = JHtmlClear.replace(absURL, "./", "");  
        System.out.println(absURL);  
        return absURL;  
    }  
  
    /** 
     * 
     * 
     * 方法说明：创建mht文件 
     * 
     * 
     * 输入参数：content 网页文本内容; urlScriptList 脚本链接集合; urlImageList 图片链接集合 
     * 
     * 
     * 返回类型�?
     * 
     */  
    private void createMhtArchive(String content, ArrayList urlScriptList,  
            ArrayList urlImageList) throws Exception {  
        MimeMultipart mp = new MimeMultipart("related");  
        Properties props = new Properties();  
        props.put("mail.smtp.host", smtp);  
        Session session = Session.getDefaultInstance(props, null);  
        MimeMessage msg = new MimeMessage(session);  
        msg.setHeader("X-Mailer", "Code Manager .SWT");  
        if (from != null) {  
            msg.setFrom(new InternetAddress(from));  
        }  
        if (subject != null) {  
            msg.setSubject(subject);  
        }  
        if (to != null) {  
            InternetAddress[] toAddresses = getInetAddresses(to);  
            msg.setRecipients(Message.RecipientType.TO, toAddresses);  
        }  
        if (cc != null) {  
            InternetAddress[] ccAddresses = getInetAddresses(cc);  
            msg.setRecipients(Message.RecipientType.CC, ccAddresses);  
        }  
        if (bcc != null) {  
            InternetAddress[] bccAddresses = getInetAddresses(bcc);  
            msg.setRecipients(Message.RecipientType.BCC, bccAddresses);  
        }  
        // 设置网页正文  
        MimeBodyPart bp = new MimeBodyPart();  
        bp.setText(content, strEncoding);  
        bp.addHeader("Content-Type", "text/html;charset=" + strEncoding);  
        bp.addHeader("Content-Location", strWeb.toString());  
        mp.addBodyPart(bp);  
        int urlCount = urlScriptList.size();  
        for (int i = 0; i < urlCount; i++) {  
            bp = new MimeBodyPart();  
            ArrayList urlInfo = (ArrayList) urlScriptList.get(i);  
            // String url = urlInfo.get(0).toString();  
            String absoluteURL = urlInfo.get(1).toString();  
            bp.addHeader("Content-Location", javax.mail.internet.MimeUtility  
                    .encodeWord(java.net.URLDecoder.decode(absoluteURL,  
                            strEncoding)));  
            DataSource source = new AttachmentDataSource(absoluteURL, "text");  
            bp.setDataHandler(new DataHandler(source));  
            mp.addBodyPart(bp);  
        }  
  
        urlCount = urlImageList.size();  
        for (int i = 0; i < urlCount; i++) {  
            bp = new MimeBodyPart();  
            ArrayList urlInfo = (ArrayList) urlImageList.get(i);  
            // String url = urlInfo.get(0).toString();  
            String absoluteURL = urlInfo.get(0).toString();  
            System.out.println(urlInfo.get(0).toString() + " +++ " + urlInfo.get(1));  
            bp.addHeader("Content-Location", javax.mail.internet.MimeUtility  
                    .encodeWord(java.net.URLDecoder.decode(absoluteURL,  
                            strEncoding)));  
            DataSource source = new AttachmentDataSource(absoluteURL, "image");  
            bp.setDataHandler(new DataHandler(source));  
            mp.addBodyPart(bp);  
        }  
        msg.setContent(mp);  
        // write the mime multi part message to a file 
        msg.writeTo(new FileOutputStream(strFileName));  
    }  
  
    /** 
     * 
     * 
     * 方法说明：mht转html 
     * 
     * 
     * 输入参数：strMht mht文件路径; strHtml html文件路径 
     * 
     * 
     * 返回类型�?
     * 
     */  
    public static void mht2html(String strMht, String strHtml) {  
        try {  
  
            InputStream fis = new FileInputStream(strMht);  
            Session mailSession = Session.getDefaultInstance(System  
                    .getProperties(), null);  
            MimeMessage msg = new MimeMessage(mailSession, fis);  
            Object content = msg.getContent();  
            if (content instanceof Multipart) {  
                MimeMultipart mp = (MimeMultipart) content;  
                MimeBodyPart bp1 = (MimeBodyPart) mp.getBodyPart(0);  
                String strEncodng = getEncoding(bp1);  
                String strText = getHtmlText(bp1, strEncodng);  
                if (strText == null)  
                    return;  
                File parent = null;  
                if (mp.getCount() > 1) {  
                    parent = new File(new File(strHtml).getAbsolutePath() + ".files");  
                    parent.mkdirs();  
                    if (!parent.exists())  
                        return;  
                }  
                for (int i = 1; i < mp.getCount(); ++i) {  
                    MimeBodyPart bp = (MimeBodyPart) mp.getBodyPart(i);  
                    String strUrl = getResourcesUrl(bp);  
                    if (strUrl == null)  
                        continue;  
                    DataHandler dataHandler = bp.getDataHandler();  
                    MimePartDataSource source = (MimePartDataSource) dataHandler  
                            .getDataSource();  
                    File resources = new File(parent.getAbsolutePath()  
                            + File.separator + getName(strUrl, i));  
                    if (saveResourcesFile(resources, bp.getInputStream()))  
                        strText = JHtmlClear.replace(strText, strUrl, resources  
                                .getAbsolutePath());  
                }  
                saveHtml(strText, strHtml);  
            }  
        } catch (Exception e) {  
  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * 
     * 
     * 方法说明：得到资源文件的name 
     * 
     * 
     * 输入参数：strName 资源文件链接, ID 资源文件的序�?
     * 
     * 
     * 返回类型：资源文件的本地临时文件�?
     * 
     */  
    public static String getName(String strName, int ID) {  
        char separator = '/';  
        System.out.println(strName);  
        System.out.println(separator);  
        if (strName.lastIndexOf(separator) >= 0)  
            return format(strName.substring(strName.lastIndexOf(separator) + 1));  
        return "temp" + ID;  
    }  
  
    /** 
     * 
     * 
     * 方法说明：得到网页编�?
     * 
     * 
     * 输入参数：bp MimeBodyPart类型的网页内�?
     * 
     * 
     * 返回类型：MimeBodyPart里的网页内容的编�?
     * 
     */  
    private static String getEncoding(MimeBodyPart bp) {  
        if (bp != null) {  
            try {  
                Enumeration list = bp.getAllHeaders();  
                while (list.hasMoreElements()) {  
                    javax.mail.Header head = (javax.mail.Header) list  
                            .nextElement();  
                    if (head.getName().compareTo("Content-Type") == 0) {  
                        String strType = head.getValue();  
                        int pos = strType.indexOf("charset=");  
                        if (pos != -1) {  
                            String strEncoding = strType.substring(pos + 8, strType.length());  
                            if (strEncoding.toLowerCase().compareTo("gb2312") == 0) {  
                                strEncoding = "gbk";  
                            }  
                            return strEncoding;  
                        }  
                    }  
                }  
            } catch (MessagingException e) {  
                e.printStackTrace();  
            }  
        }  
        return null;  
    }  
  
    /** 
     * 
     * 
     * 方法说明：得到资源文件url 
     * 
     * 
     * 输入参数：bp MimeBodyPart类型的网页内�?
     * 
     * 
     * 返回类型：资源文件url 
     * 
     */  
    private static String getResourcesUrl(MimeBodyPart bp) {  
        if (bp != null) {  
            try {  
                Enumeration list = bp.getAllHeaders();  
                while (list.hasMoreElements()) {  
                    javax.mail.Header head = (javax.mail.Header) list  
                            .nextElement();  
                    if (head.getName().compareTo("Content-Location") == 0) {  
                        return head.getValue();  
                    }  
                }  
            } catch (MessagingException e) {  
  
                e.printStackTrace();  
            }  
  
        }  
        return null;  
    }  
  
    /** 
     * 
     * 
     * 方法说明：格式化文件�?
     * 
     * 
     * 输入参数：strName 文件�?
     * 
     * 
     * 返回类型：经过处理的符合命名规则的文件名 
     * 
     */  
    private static String format(String strName) {  
        if (strName == null)  
            return null;  
        strName = strName.replaceAll("     ", " ");  
        String strText = "\\/:*?\"<>|^___FCKpd___0quot;";  
        for (int i = 0; i < strName.length(); ++i) {  
            String ch = String.valueOf(strName.charAt(i));  
            if (strText.indexOf(ch) != -1) {  
                strName = strName.replace(strName.charAt(i), '-');  
            }  
        }  
        return strName;  
    }  
  
    /** 
     * 
     * 
     * 方法说明：保存资源文�?
     * 
     * 
     * 输入参数：resources 要创建的资源文件; inputStream 要输入文件中的流 
     * 
     * 
     * 返回类型：boolean 
     * 
     */  
    private static boolean saveResourcesFile(File resources,  
            InputStream inputStream) {  
        if (resources == null || inputStream == null) {  
            return false;  
        }  
        BufferedInputStream in = null;  
        FileOutputStream fio = null;  
        BufferedOutputStream osw = null;  
        try {  
            in = new BufferedInputStream(inputStream);  
            fio = new FileOutputStream(resources);  
            osw = new BufferedOutputStream(new DataOutputStream(fio));  
            int b;  
            byte[] a = new byte[1024];  
            boolean isEmpty = true;  
            while ((b = in.read(a)) != -1) {  
                isEmpty = false;  
                osw.write(a, 0, b);  
                osw.flush();  
            }  
            osw.close();  
            fio.close();  
            in.close();  
            inputStream.close();  
            if (isEmpty)  
                resources.delete();  
            return true;  
        } catch (Exception e) {  
  
            e.printStackTrace();  
            System.out.println("解析mht失败");  
            return false;  
        } finally {  
            try {  
                if (osw != null)  
                    osw.close();  
                if (fio != null)  
                    fio.close();  
                if (in != null)  
                    in.close();  
                if (inputStream != null)  
                    inputStream.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
                System.out.println("解析mht失败");  
                return false;  
            }  
        }  
    }  
  
    /** 
     * 
     * 
     * 方法说明：得到mht文件的标�?
     * 
     * 
     * 输入参数：mhtFilename mht文件�?
     * 
     * 
     * 返回类型：mht文件的标�?
     * 
     */  
    public static String getTitle(String mhtFilename) {  
        try {  
  
            InputStream fis = new FileInputStream(mhtFilename);  
            Session mailSession = Session.getDefaultInstance(System  
                    .getProperties(), null);  
            MimeMessage msg = new MimeMessage(mailSession, fis);  
            Object content = msg.getContent();  
            if (content instanceof Multipart) {  
                MimeMultipart mp = (MimeMultipart) content;  
                MimeBodyPart bp1 = (MimeBodyPart) mp.getBodyPart(0);  
                String strEncodng = getEncoding(bp1);  
                String strText = getHtmlText(bp1, strEncodng);  
                if (strText == null)  
                    return null;  
                strText = strText.toLowerCase();  
                int pos1 = strText.indexOf("<title>");  
                int pos2 = strText.indexOf("</title>");  
                if (pos1 != -1 && pos2 != -1 && pos2 > pos1) {  
                    return strText.substring(pos1 + 7, pos2).trim();  
                }  
            }  
            return null;  
        } catch (Exception e) {  
  
            e.printStackTrace();  
            return null;  
        }  
    }  
  
    /** 
     * 
     * 
     * 方法说明：得到html文本 
     * 
     * 
     * 输入参数：bp MimeBodyPart类型的网页内�? strEncoding 内容编码 
     * 
     * 
     * 返回类型：html文本 
     * 
     */  
    private static String getHtmlText(MimeBodyPart bp, String strEncoding) {  
        InputStream textStream = null;  
        BufferedInputStream buff = null;  
        BufferedReader br = null;  
        Reader r = null;  
        try {  
            textStream = bp.getInputStream();  
            buff = new BufferedInputStream(textStream);  
            r = new InputStreamReader(buff, strEncoding);  
            br = new BufferedReader(r);  
            StringBuffer strHtml = new StringBuffer("");  
            String strLine = null;  
            while ((strLine = br.readLine()) != null) {  
                strHtml.append(strLine + "\r\n");  
            }  
            br.close();  
            r.close();  
            textStream.close();  
            return strHtml.toString();  
        } catch (Exception e) {  
  
            e.printStackTrace();  
        } finally {  
            try {  
                if (br != null)  
                    br.close();  
                if (buff != null)  
                    buff.close();  
                if (textStream != null)  
                    textStream.close();  
            } catch (Exception e) {  
                System.out.println("解析mht失败");  
            }  
        }  
        return null;  
    }  
  
    /** 
     * 
     * 
     * 方法说明：保存html文件 
     * 
     * 
     * 输入参数：strText html内容; strHtml html文件�?
     * 
     * 
     * 返回类型�?
     * 
     */  
    private static void saveHtml(String strText, String strHtml) {  
        try {  
            FileWriter fw = new FileWriter(strHtml);  
            fw.write(strText);  
            fw.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
            System.out.println("解析mht失败");  
        }  
    }  
  
    private InternetAddress[] getInetAddresses(String emails) throws Exception {  
        ArrayList list = new ArrayList();  
        StringTokenizer tok = new StringTokenizer(emails, ",");  
        while (tok.hasMoreTokens()) {  
            list.add(tok.nextToken());  
        }  
        int count = list.size();  
        InternetAddress[] addresses = new InternetAddress[count];  
        for (int i = 0; i < count; i++) {  
            addresses[i] = new InternetAddress(list.get(i).toString());  
        }  
        return addresses;  
    }  
  
    class AttachmentDataSource implements DataSource {  
        private MimetypesFileTypeMap map = new MimetypesFileTypeMap();  
        private String strUrl;  
        private String strType;  
        private byte[] dataSize = null;  
  
        /** 
         * 
         * This is some content type maps. 
         * 
         */  
        private Map normalMap = new HashMap();  
        {  
            // Initiate normal mime type map  
            // Images  
            normalMap.put("image", "image/jpeg");  
            normalMap.put("text", "text/plain");  
        }  
  
        public AttachmentDataSource(String strUrl, String strType) {  
            this.strType = strType;  
            this.strUrl = strUrl;  
  
            strUrl = strUrl.trim();  
            strUrl = strUrl.replaceAll(" ", "%20");  
            dataSize = JQuery.downBinaryFile(strUrl);  
        }  
  
        /** 
         * 
         * Returns the content type. 
         * 
         */  
        public String getContentType() {  
            return getMimeType(getName());  
        }  
  
        public String getName() {  
            char separator = File.separatorChar;  
            if (strUrl.lastIndexOf(separator) >= 0)  
                return strUrl.substring(strUrl.lastIndexOf(separator) + 1);  
            return strUrl;  
        }  
  
        private String getMimeType(String fileName) {  
            String type = (String) normalMap.get(strType);  
            if (type == null) {  
                try {  
                    type = map.getContentType(fileName);  
                } catch (Exception e) {  
  
                }  
                System.out.println(type);  
                // Fix the null exception  
                if (type == null) {  
                    type = "application/octet-stream";  
                }  
            }  
  
            return type;  
        }  
  
        public InputStream getInputStream() throws IOException {  
  
            if (dataSize == null)  
                dataSize = new byte[0];  
            return new ByteArrayInputStream(dataSize);  
        }  
  
        public OutputStream getOutputStream() throws IOException {  
  
            return new java.io.ByteArrayOutputStream();  
        }  
  
    }  
}  
