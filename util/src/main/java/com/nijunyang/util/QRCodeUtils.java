package com.nijunyang.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Created by nijunyang on 2020/6/5 16:41
 */
public class QRCodeUtils {
    public static void main(String[] args) throws Exception {
        genQRCode();
    }

    public static String toBase64(String type) {
        type = "data:image/png;base64,";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        return type + Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }

    /**
     * 生成二维码图片文件（不带LOGO）
     *
     * @param content 要生成二维码的内容
     * @param width 二维码的高度
     * @param height 二维码的宽度
     * @return 二维码图片
     * @throws WriterException 异常
     */
    private static BufferedImage genQrcode(String content, int width, int height) throws WriterException {
        Map hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        //根据高度和宽度生成像素矩阵
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);

        BufferedImage image = new BufferedImage(bitMatrix.getWidth(), bitMatrix.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < bitMatrix.getWidth(); x++) {
            for (int y = 0; y < bitMatrix.getHeight(); y++) {
                //填充黑白两色
                image.setRGB(x, y, bitMatrix.get(x, y) ? 255 : 0);
            }
        }

        return image;
    }

    private static void genQRCode() throws IOException, WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode("www.baidu.com", BarcodeFormat.QR_CODE, 100, 100);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

        Base64.Encoder encoder = Base64.getEncoder();

        String text = encoder.encodeToString(outputStream.toByteArray());

        System.out.println("data:image/png;base64," + text);
    }


}
