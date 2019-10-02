package com.joymain.ng.util.qrcode;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sf.json.JSONObject;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeUtil {
	// 图片宽度的一般  
    private static  int IMAGE_WIDTH = 50;  
    private static  int IMAGE_HEIGHT = 50;  
    private static final int IMAGE_HALF_WIDTH = IMAGE_WIDTH / 2;  
    private static final int FRAME_WIDTH = 2;  
    
    private static final int DEF_WIDTH = 200; // 图像宽度
    private static final int DEF_HEIGHT = 200; // 图像高度
    
    
    
    
    
    
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;
	
	/**
	 * 创建二维码并返回文件路径
	 * @param filePath 文件目录
	 * @param fileName 文件名称
	 * @param content 二维码内容
	 * @throws Exception
	 */
	public static String createQrCode(String filePath, String fileName, String content) throws Exception {
		return createQrCode(filePath, fileName, content, DEF_WIDTH, DEF_HEIGHT);
	}
	
	/**
	 * 创建二维码并返回文件路径
	 * @param filePath 文件目录
	 * @param fileName 文件名称
	 * @param content 二维码内容
	 * @param width 宽
	 * @param height 高
	 * @throws Exception
	 */
	public static String createQrCode(String filePath, String fileName, String content,int width, int height) throws Exception {
		createFile(filePath);
		String format = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());// 图像类型
		BitMatrix bitMatrix = getBitMatrix(content, width, height);// 生成矩阵
		//bitMatrix = updateBit(bitMatrix, 5);
		Path path = FileSystems.getDefault().getPath(filePath, fileName);
		MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像
		return path.toFile().getPath();
	}
	
	/**
	 * 返回二维码图片流(200*200)
	 * @param content 二维码中的内容
	 * @return 图片流
	 * @throws Exception
	 */
	public static BufferedImage getBuffrerQrCode(String content)throws Exception {
		BitMatrix bitMatrix = getBitMatrix(content, DEF_WIDTH, DEF_HEIGHT);
		return bitMatrixToImage(bitMatrix);
	}
	
	/**
	 * 返回二维码图片流
	 * @param content 二维码中的内容
	 * @param width  宽
	 * @param height 高
	 * @return 图片流
	 * @throws Exception
	 */
	public static BufferedImage getBuffrerQrCode(String content,int width, int height) throws Exception {
		BitMatrix bitMatrix = getBitMatrix(content, width, height);
		return bitMatrixToImage(bitMatrix);
	}
	
	private static BufferedImage genBarcode(BitMatrix matrix, String srcImagePath,int width,int height) throws Exception{
		 // 读取源图像  
		// 读取源图像
		BufferedImage scaleImage = scale(srcImagePath, IMAGE_WIDTH, IMAGE_HEIGHT, false);

		int[][] srcPixels = new int[IMAGE_WIDTH][IMAGE_HEIGHT];
		for (int i = 0; i < scaleImage.getWidth(); i++) {
			for (int j = 0; j < scaleImage.getHeight(); j++) {
				srcPixels[i][j] = scaleImage.getRGB(i, j);
			}
		}
      
		int halfW = matrix.getWidth() / 2;
		int halfH = matrix.getHeight() / 2;
		int[] pixels = new int[width * height];

		// System.out.println(matrix.getHeight());
		for (int y = 0; y < matrix.getHeight(); y++) {
			for (int x = 0; x < matrix.getWidth(); x++) {
				// 读取图片
				if (x > halfW - IMAGE_HALF_WIDTH
						&& x < halfW + IMAGE_HALF_WIDTH
						&& y > halfH - IMAGE_HALF_WIDTH
						&& y < halfH + IMAGE_HALF_WIDTH) {
					pixels[y * width + x] = srcPixels[x - halfW
							+ IMAGE_HALF_WIDTH][y - halfH + IMAGE_HALF_WIDTH];
				}
				// 在图片四周形成边框
				else if ((x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
						&& x < halfW - IMAGE_HALF_WIDTH + FRAME_WIDTH
						&& y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
						+ IMAGE_HALF_WIDTH + FRAME_WIDTH)
						|| (x > halfW + IMAGE_HALF_WIDTH - FRAME_WIDTH
								&& x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
								&& y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
								+ IMAGE_HALF_WIDTH + FRAME_WIDTH)
						|| (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
								&& x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
								&& y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
								- IMAGE_HALF_WIDTH + FRAME_WIDTH)
						|| (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
								&& x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
								&& y > halfH + IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
								+ IMAGE_HALF_WIDTH + FRAME_WIDTH)) {
					pixels[y * width + x] = 0xfffffff;
				} else {
					// 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
					pixels[y * width + x] = matrix.get(x, y) ? 0xff000000
							: 0xfffffff;
				}
			}
		}
        
        BufferedImage image = new BufferedImage(matrix.getWidth(), matrix.getHeight(),  BufferedImage.TYPE_INT_RGB);  
        image.getRaster().setDataElements(0, 0, width, height, pixels);
        return image;  
	} 
	
	public static BufferedImage genBarcode(BitMatrix matrix, String srcImagePath) throws Exception {
		// 读取源图像
		// 读取源图像
		IMAGE_WIDTH = matrix.getWidth()/4;
		IMAGE_HEIGHT = matrix.getHeight()/4;
		BufferedImage scaleImage = scale(srcImagePath, IMAGE_WIDTH, IMAGE_HEIGHT, true);

		int[][] srcPixels = new int[IMAGE_WIDTH][IMAGE_HEIGHT];
		for (int i = 0; i < scaleImage.getWidth(); i++) {
			for (int j = 0; j < scaleImage.getHeight(); j++) {
				srcPixels[i][j] = scaleImage.getRGB(i, j);
			}
		}
		int[] pixels = getPixels(matrix, srcPixels);
		BufferedImage image = new BufferedImage(matrix.getWidth(), matrix.getHeight(), BufferedImage.TYPE_INT_RGB);
		image.getRaster().setDataElements(0, 0, matrix.getWidth(),  matrix.getHeight(), pixels);
		return image;
	}
	
	
	
	private static int[] getPixels(BitMatrix matrix ,int[][] srcPixels){
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		int halfW = width / 2;
		int halfH = height / 2;
		int halfMiddleW = width / 4 / 2;
		int[] pixels = new int[width * height];
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				// 读取图片
				if (x > halfW - halfMiddleW && x < halfW + halfMiddleW && y > halfH - halfMiddleW && y < halfH + halfMiddleW) {
					pixels[y * width + x] = srcPixels[x - halfW + halfMiddleW][y - halfH + halfMiddleW];
				}
				
				// 在图片四周形成边框
				else if ((x > halfW - halfMiddleW - FRAME_WIDTH && x < halfW - halfMiddleW + FRAME_WIDTH
						&& y > halfH - halfMiddleW - FRAME_WIDTH && y < halfH + halfMiddleW + FRAME_WIDTH)
					||(x > halfW + halfMiddleW - FRAME_WIDTH && x < halfW + halfMiddleW + FRAME_WIDTH
						&& y > halfH - halfMiddleW - FRAME_WIDTH && y < halfH + halfMiddleW + FRAME_WIDTH)
					|| (x > halfW - halfMiddleW - FRAME_WIDTH && x < halfW + halfMiddleW + FRAME_WIDTH
						&& y > halfH - halfMiddleW - FRAME_WIDTH && y < halfH - halfMiddleW + FRAME_WIDTH)
					|| (x > halfW - halfMiddleW - FRAME_WIDTH && x < halfW + halfMiddleW + FRAME_WIDTH
						&& y > halfH + halfMiddleW - FRAME_WIDTH && y < halfH + halfMiddleW + FRAME_WIDTH)) {
					pixels[y * width + x] = WHITE;
				}else {
					// 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
					pixels[y * width + x] = matrix.get(x, y) ? BLACK : WHITE;
				}
			}
		}
		return pixels;
	} 
	
	
	
	 /**
     * 解析二维码
     * @param filePath 解析的文件地址
     * @throws Exception
     */
	public static String resolveQrCode(String filePath) throws Exception {
		BufferedImage image = ImageIO.read(new File(filePath));
		LuminanceSource source = new BufferedImageLuminanceSource(image);
		Binarizer binarizer = new HybridBinarizer(source);
		BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
		Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
		hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
		Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码
		return result.getText();
	}
	
	/**
	 * 生成二维码矩阵
	 * @param content
	 * @param width
	 * @param height
	 * @return
	 * @throws Exception
	 */
	public static BitMatrix getBitMatrix(String content, int width, int height) throws Exception {
		// 生成二维码
		Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");//编码
		hints.put(EncodeHintType.MARGIN, 0);
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);//容错率
		
		return new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);// 生成矩阵
	}
	

/**
	private static BitMatrix updateBit(BitMatrix matrix, int margin) {
		int tempM = margin * 2;
		int[] rec = matrix.getEnclosingRectangle(); // 获取二维码图案的属性
		int resWidth = rec[2] + tempM;
		int resHeight = rec[3] + tempM;
		BitMatrix resMatrix = new BitMatrix(resWidth, resHeight); // 按照自定义边框生成新的BitMatrix
		resMatrix.clear();
		for (int i = margin; i < resWidth - margin; i++) { // 循环，将二维码图案绘制到新的bitMatrix中
			for (int j = margin; j < resHeight - margin; j++) {
				if (matrix.get(i - margin + rec[0], j - margin + rec[1])) {
					resMatrix.set(i, j);
				}
			}
		}
		return resMatrix;
	}*/
	
	/**
	 * 二位矩阵转换图片文件流
	 * @param matrix
	 * @return
	 */
	private static BufferedImage bitMatrixToImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}
	
	
	
	 /** 
     * 把传入的原始图像按高度和宽度进行缩放，生成符合要求的图标 
     *  
     * @param srcImageFile   源文件地址 
     * @param height  目标高度 
     * @param width  目标宽度 
     * @param hasFiller  比例不对时是否需要补白：true为补白; false为不补白; 
     * @throws IOException 
     */  
	private static BufferedImage scale(String srcImageFile, int height, int width, boolean hasFiller) throws IOException {
		double ratio = 0.0; // 缩放比例
		File file = new File(srcImageFile);
		BufferedImage srcImage = ImageIO.read(file);
		Image destImage = srcImage.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
		// 计算比例
		if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {
			if (srcImage.getHeight() > srcImage.getWidth()) {
				ratio = (new Integer(height)).doubleValue() / srcImage.getHeight();
			} else {
				ratio = (new Integer(width)).doubleValue() / srcImage.getWidth();
			}
			AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
			destImage = op.filter(srcImage, null);
		}
		if (hasFiller) {// 补白
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphic = image.createGraphics();
			graphic.setColor(Color.white);
			graphic.fillRect(0, 0, width, height);
			if (width == destImage.getWidth(null))
				graphic.drawImage(destImage, 0, (height - destImage.getHeight(null)) / 2, destImage.getWidth(null), destImage.getHeight(null), Color.white, null);
			else
				graphic.drawImage(destImage, (width - destImage.getWidth(null)) / 2, 0, destImage.getWidth(null), destImage.getHeight(null), Color.white, null);
			graphic.dispose();
			destImage = image;
		}
		return (BufferedImage) destImage;
	}
	
	/**
	 * 创建文件目录
	 * @param filePath 目录
	 */
	private static void createFile(String filePath) {
		try {
			File file = new File(filePath);
			// 如果文件夹不存在则创建
			if (!file.exists() && !file.isDirectory()) {
				System.out.println("//不存在");
				file.mkdirs();
			} else {
				System.out.println("//目录存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

   

    public static void main(String[] args) {
    	String filePath = "D://qrcode";
    	String fileName ="zxing.png";
    	//String content = "safdsafdsfdsafdsfasafadsfdsafdfafdsaf";
    	JSONObject json = new JSONObject();  
        json.put("zxing", "sdfdsgoogle/zxing");  
        json.put("author", "shihy");  
        String content2 = json.toString();// 内容  
        try {
			QRCodeUtil.createQrCode(filePath, fileName, content2);
			System.out.println(QRCodeUtil.resolveQrCode(new File(filePath, fileName).getPath()));;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        try {
        	
        	BufferedImage destImagePath = QRCodeUtil.genBarcode(QRCodeUtil.getBitMatrix(content2, 200, 200), "D://qrcode//1//zj.png", 200, 200);
        	
        	BufferedImage destImagePath2 = QRCodeUtil.genBarcode(QRCodeUtil.getBitMatrix(content2, 200, 200), "D://qrcode//1//zj.png");
			ImageIO.write(destImagePath,  "jpg", new File("D://qrcode//dsaf1.jpg"));
			ImageIO.write(destImagePath2,  "jpg", new File("D://qrcode//dsaf2222.jpg"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/**/
    	
	}

}