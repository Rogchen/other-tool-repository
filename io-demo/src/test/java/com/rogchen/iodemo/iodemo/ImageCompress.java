package com.rogchen.iodemo.iodemo;

import org.springframework.util.StringUtils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author Rogchen  rogchen128@gmail.com
 * @description: 图片压缩
 * @product: IntelliJ IDEA
 * @create by 20-5-22 18:19
 **/
public class ImageCompress {

    /**
     *
     * 自己设置压缩质量来把图片压缩成byte[]
     *
     * @param image
     *            压缩源图片
     * @param quality
     *            压缩质量，在0-1之间，越接近1,越原生
     * @return 返回的字节数组
     */
    private byte[] bufferedImageTobytes(BufferedImage image, float quality) {
        // 如果图片空，返回空
        /*if (image == null) {
            return null;
        } */
        // 得到指定Format图片的writer
        Iterator<ImageWriter> iter = ImageIO
                .getImageWritersByFormatName("jpg");// 得到迭代器
        ImageWriter writer = (ImageWriter) iter.next(); // 得到writer

        // 得到指定writer的输出参数设置(ImageWriteParam )
        ImageWriteParam iwp = writer.getDefaultWriteParam();
        iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // 设置可否压缩
        iwp.setCompressionQuality(quality); // 设置压缩质量参数

        iwp.setProgressiveMode(ImageWriteParam.MODE_DISABLED);

        ColorModel colorModel = image.getColorModel();
        // 指定压缩时使用的色彩模式
        iwp.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel,
                colorModel.createCompatibleSampleModel(16, 16)));

        // 开始打包图片，写入byte[]
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 取得内存输出流
        IIOImage iIamge = new IIOImage(image, null, null);
        try {
            // 此处因为ImageWriter中用来接收write信息的output要求必须是ImageOutput
            // 通过ImageIo中的静态方法，得到byteArrayOutputStream的ImageOutput
            writer.setOutput(ImageIO
                    .createImageOutputStream(byteArrayOutputStream));
            writer.write(null, iIamge, iwp);

        } catch (IOException e) {
            System.out.println("write errro");
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    /**
     *
     * @param srcFilePath 原图路径
     * @param descFilePath 保存路径
     * @return
     * @throws IOException
     */
    public static boolean compressPic(String srcFilePath, String descFilePath) throws IOException  {
        File file = null;
        BufferedImage src = null;
        FileOutputStream out = null;
        // 指定写图片的方式为 jpg
        ImageWriter imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
        ImageWriteParam imgWriteParams = new ImageWriteParam(null);
        // 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
        imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
        // 这里指定压缩的程度，参数qality是取值0~1范围内，
        imgWriteParams.setCompressionQuality((float)1);
        imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
        ColorModel colorModel = ImageIO.read(new File(srcFilePath)).getColorModel();// ColorModel.getRGBdefault();

        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(
                colorModel, colorModel.createCompatibleSampleModel(16, 16)));
        try {
            if (StringUtils.isEmpty(srcFilePath)) {
                return false;
            } else {
                file = new File(srcFilePath);
                System.out.println(file.length());
                src = ImageIO.read(file);
                out = new FileOutputStream(descFilePath);
                imgWrier.reset();
                // 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何
                // OutputStream构造
                imgWrier.setOutput(ImageIO.createImageOutputStream(out));
                // 调用write方法，就可以向输入流写图片
                imgWrier.write(null, new IIOImage(src, null, null),
                        imgWriteParams);
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
