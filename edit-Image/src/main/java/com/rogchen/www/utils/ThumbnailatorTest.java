package com.rogchen.www.utils;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Description: Thumbnailator测试类
 * copy by http://www.voidcn.com/article/p-vjwkcgmm-brb.html
 * @Product: IntelliJ IDEA
 * @Author Rogchen rogchen128@gmail.com
 * @Created Date: 2018/9/1 16:16
 **/
public class ThumbnailatorTest {

    private String lastfile = "G://CHK//Desktop//abc.png";
    private File toFile = new File("G://CHK//Desktop//abc.png");

    @Test
    public void test() {
        try {
            //创建图片文件(此处为1024×768px的图片)和处理后的图片文件
//            File fromPic = new File("G://CHK/Desktop/test.png");
            File toPic = new File("G://CHK//Desktop//test.png");  //结果


            t4(toPic);
//输出成文件流OutputStream
//            OutputStream os = new FileOutputStream(toPic);
//            Thumbnails.of(fromPic).size(120,120).toOutputStream(os);
//输出BufferedImage,asBufferedImage()返回BufferedImage
//            BufferedImage bi=Thumbnails.of(fromPic).size(120,120).asBufferedImage();
//            BufferedImage bi = Thumbnails.of(toPic).size(400, 500).asBufferedImage();
//            ImageIO.write(bi, "jpg", toPic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void t1(File toPic) {
        //按指定大小把图片进行缩和放（会遵循原图高宽比例）
        try {
            Thumbnails.of(toPic).size(800, 500).toFile(toPic);//变为400*300,遵循原图比例缩或放到400*某个高度
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void t2(File toPic) {
        //按指定大小把图片进行缩和放（会遵循原图高宽比例）
        try {
            //按照比例进行缩小和放大
            Thumbnails.of(toPic).scale(0.2f).toFile(toPic);//按比例缩小
//            Thumbnails.of(toPic).scale(2f);//按比例放大
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void t3(File toPic) {
        //按指定大小把图片进行缩和放（会遵循原图高宽比例）
        try {
            //不按比例，就按指定的大小进行缩放
            Thumbnails.of(toPic).size(100, 100).keepAspectRatio(false).toFile(toPic);
            //或者Thumbnails.of(fromPic).forceSize(100,100).toFile(toPic);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void t4(File fromPic){
        try {

            //压缩至指定图片尺寸（例如：横400高300），保持图片不变形，多余部分裁剪掉(这个是引的网友的代码)
            BufferedImage image = ImageIO.read(fromPic);
            Thumbnails.Builder<BufferedImage> builder = null;

            int imageWidth = image.getWidth();
            int imageHeitht = image.getHeight();
            if ((float)300 / 400 != (float)imageWidth / imageHeitht) {
                if (imageWidth > imageHeitht) {
                    image = Thumbnails.of(fromPic).height(300).asBufferedImage();
                } else {
                    image = Thumbnails.of(fromPic).width(400).asBufferedImage();
                }
                builder = Thumbnails.of(image).sourceRegion(Positions.CENTER, 400, 300).size(400, 300);
            } else {
                builder = Thumbnails.of(image).size(400, 300);
            }
            //格式jpg
            builder.outputFormat("jpg").toFile(toFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void t11(File fromPic){
        try {
            //输出BufferedImage,asBufferedImage()返回BufferedImage
            BufferedImage bi=Thumbnails.of(fromPic).size(120,120).asBufferedImage();
            ImageIO.write(bi,lastfile,fromPic);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * 有问题
     * @param fromPic
     */
    private void t10(File fromPic) {
        try {
            //输出成文件流OutputStream
            OutputStream os=new FileOutputStream(fromPic);
            Thumbnails.of(fromPic).size(120,120).toOutputStream(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void t9(File fromPic) {
        try {
            //"G://CHK//Desktop//test.jpg"
            //用outputFormat(图像格式)转换图片格式，保持原尺寸不变
            Thumbnails.of(fromPic).scale(1f).outputFormat("jpg")
                    .toFile("G://CHK//Desktop//");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void t8(File toPic) {
        try{
            //用sourceRegion()实现图片裁剪
            //图片中心300*300的区域,Positions.CENTER表示中心，还有许多其他位置可选
        Thumbnails.of(toPic).sourceRegion(Positions.CENTER,300,300)
                .size(300,300).toFile(toPic);
//        //图片中上区域300*300的区域
//            Thumbnails.of(fromPic).sourceRegion(Positions.TOP_CENTER,300,300)
//                    .size(300,300).toFile(toPic);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void t7(File fromPic) {
        try {
            File waterPic = new File("G://CHK//Desktop//binding_error.png");//作为水印的图片
            //给图片加水印，watermark(位置，水印图，透明度)Positions.CENTER表示加在中间
            Thumbnails.of(fromPic).size(400,400)
                    .watermark(Positions.CENTER, ImageIO.read(waterPic),0.5f)
                    .outputQuality(0.8f).toFile(fromPic);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void t6(File fromPic) {
        try {
            //图片尺寸不变，压缩图片文件大小outputQuality实现,参数1为最高质量
            Thumbnails.of(fromPic).scale(1f).outputQuality(0.25f).toFile(fromPic);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void t5(File toPic) {
        //按指定大小把图片进行缩和放（会遵循原图高宽比例）
        try {
            //旋转图片，rotate(角度),正数则为顺时针，负数则为逆时针
            Thumbnails.of(toPic).size(200,200).rotate(90).toFile(toPic);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
