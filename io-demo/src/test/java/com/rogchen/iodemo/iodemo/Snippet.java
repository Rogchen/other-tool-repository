package com.rogchen.iodemo.iodemo;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;


/**
 * @author Rogchen  rogchen128@gmail.com
 * @description:
 * @product: IntelliJ IDEA
 * @create by 20-5-25 09:15
 **/
public class Snippet {
    /**
     * 创建图片缩略图(等比缩放)
     *
     * @param src
     *            源图片文件完整路径
     * @param dist
     *            目标图片文件完整路径
     * @param width
     *            缩放的宽度
     * @param height
     *            缩放的高度
     */
    public static void createThumbnail(String src, String dist, float width,
                                       float height) {
        try {
            File srcfile = new File(src);
            if (!srcfile.exists()) {
                System.out.println("文件不存在");
                return;
            }
            BufferedImage image = ImageIO.read(srcfile);

            // 获得缩放的比例
            double ratio = 1.0;
            // 判断如果高、宽都不大于设定值，则不处理
            if (image.getHeight() > height || image.getWidth() > width) {
                if (image.getHeight() > image.getWidth()) {
                    ratio = height / image.getHeight();
                } else {
                    ratio = width / image.getWidth();
                }
            }
            // 计算新的图面宽度和高度
            int newWidth = (int) (image.getWidth() * ratio);
            int newHeight = (int) (image.getHeight() * ratio);

            BufferedImage bfImage = new BufferedImage(newWidth, newHeight,
                    BufferedImage.TYPE_INT_RGB);
            bfImage.getGraphics().drawImage(
                    image.getScaledInstance(newWidth, newHeight,
                            Image.SCALE_SMOOTH), 0, 0, null);

            FileOutputStream os = new FileOutputStream(dist);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
            encoder.encode(bfImage);
            os.close();
            System.out.println("创建缩略图成功");
        } catch (Exception e) {
            System.out.println("创建缩略图发生异常" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        createThumbnail("/home/chenhk/图片/IMAG1133.jpg", "/home/chenhk/图片/IMAG1133-1.jpg", 100, 100);
//        createThumbnail("E:\\test_1920x1200.jpg", "D:\\b.png", 2000, 2000);
//        createThumbnail("E:\\test_1920x1200.jpg", "D:\\c.jpg", 800, 600);
    }

}