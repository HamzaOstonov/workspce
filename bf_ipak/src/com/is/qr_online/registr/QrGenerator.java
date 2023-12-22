package com.is.qr_online.registr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.bind.DatatypeConverter;

import com.is.ISLogger;

public class QrGenerator {

	public static String qrGenerate(String payee_inn, String merchant_id, String id, String qr) {
		String filePath = null;
		String fileName = null;
		String fileType = null;
		String message = null;
		try {
			String base64Image = qr.substring(qr.indexOf(",") + 1, qr.length());
			System.out.println(base64Image);

			filePath = "C:" + File.separator + "qr" + File.separator + "images" + File.separator + payee_inn
					+ File.separator + merchant_id + File.separator;
			System.out.println(filePath);

			fileName = "qr_" + id;
			fileType = ".png";

			byte[] decodedBytes = DatatypeConverter.parseBase64Binary(base64Image);

			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}

			file = new File(filePath + fileName + fileType);

			//InputStream in = new ByteArrayInputStream(decodedBytes);
			//BufferedImage bImageFromConvert = ImageIO.read(in);

			if (!file.exists()) {
				// Files.write(file.toPath(), decodedBytes);
				// ImageIO.write(bImageFromConvert, fileType, file);
				FileOutputStream outputStream = new FileOutputStream(file);
				outputStream.write(decodedBytes);
				outputStream.flush();
				outputStream.close();
				System.out.println("Image Created");
				ISLogger.getLogger().info("Image Created :"+ "Inn_clienta :"+payee_inn+ "Merchant_id :"+merchant_id);
			} else {
				System.out.println("This file already created");
				ISLogger.getLogger().info("This file already created");
			}

			message = "Image path: " + filePath + fileName + fileType;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ISLogger.getLogger().error(e.getMessage());
			message = e.getMessage();
		}
		return message;
	}
}