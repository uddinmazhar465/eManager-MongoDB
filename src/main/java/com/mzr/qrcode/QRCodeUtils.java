package com.mzr.qrcode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeUtils {

	public static String getQRCodeImage(String text, int width, int height) {
		try {
			QRCodeWriter writer = new QRCodeWriter();
			BitMatrix bMatrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height);
			ByteArrayOutputStream bAOStream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bMatrix, "png", bAOStream);
			byte[] barr = bAOStream.toByteArray();
			String qrimage = Base64.getEncoder().encodeToString(barr);
			return "data:image/jpg;base64," + qrimage;
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
