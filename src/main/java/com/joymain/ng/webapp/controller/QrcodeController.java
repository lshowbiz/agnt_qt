package com.joymain.ng.webapp.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joymain.ng.util.qrcode.QRCodeUtil;

/**
 * 二维码的请求地址
 * 
 * @author Administrator
 * 
 */
@Controller
@RequestMapping("/qrcode*")
public class QrcodeController extends BaseFormController {

	@RequestMapping("/getQrcode")
	protected String getQrcode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String content = request.getParameter("content");
		String type = request.getParameter("type");
		ServletOutputStream out = null;
		if (StringUtils.isNotEmpty(content) || StringUtils.isNotEmpty(type) && StringUtils.isNotEmpty(content)) {
			try {
				response.setContentType("multipart/form-data");
				out = response.getOutputStream();
				
				//BufferedImage bfImg = QRCodeUtil.getBuffrerQrCode(getContent(type, content));
				BufferedImage bfImg =  QRCodeUtil.genBarcode(QRCodeUtil.getBitMatrix(getContent(type, content), 180, 180), this.getServletContext().getRealPath("/")+"/images/JM.png");
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				ImageIO.write(bfImg, "gif", os);
				out.write(os.toByteArray());
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					try {
						out.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	String getContent(String type, String content) {
		try {
			String fmt = content;
			if ("1".equals(type)) {// 代他人支付
				fmt = "jmec://othersPay?userId=%s&memberOrderNo=%s";
			} else if ("2".equals(type)) {// 转账
				fmt = "jmec://transfer?userId=%s";
			}
			return String.format(fmt, (Object[]) content.split(","));
		} catch (Exception e) {
			return content;
		}
	}

}
