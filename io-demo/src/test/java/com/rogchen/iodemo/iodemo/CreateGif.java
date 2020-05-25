package com.rogchen.iodemo.iodemo;

import com.madgag.gif.fmsware.AnimatedGifEncoder;
import com.madgag.gif.fmsware.GifDecoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 图片转成gif
 */
public class CreateGif {

    /**
     * api
     * imagesToGif(images, "/home/chenhk/桌面/weixinwork/res.gif",0.3f);
     * 0.3f 值越小速度越快，1为1秒
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

//        String outputPath = "/home/chenhk/001.gif";
//        String imagePath = "/home/lab/test/33.gif";
//        reverseGif(imagePath,outputPath);
//        // Gif转图片
        String dirPath = "/home/chenhk/桌面/weixinwork/";
//        gifToImages(imagePath,dirPath);
        List<BufferedImage> images = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            File outFile = new File(dirPath + i + ".png");
            BufferedImage image = ImageIO.read(outFile);
            if(image.getWidth()>100){

            }
            images.add(image);
        }
        imagesToGif(images, "/home/chenhk/桌面/weixinwork/res.gif",0.3f);
    }

    /**
     * 多图片转gif
     *
     * @param imageList
     * @param outputPath
     * @param delay 播放速度
     * @throws IOException
     */
    static void imagesToGif(List<BufferedImage> imageList, String outputPath, float delay) throws IOException {
        // 拆分一帧一帧的压缩之后合成
        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        encoder.start(outputPath);
        encoder.setRepeat(0);
        for (BufferedImage bufferedImage :
                imageList) {
            encoder.setDelay((int) (delay * 1000)); //播放速度
            int height = bufferedImage.getHeight();
            int width = bufferedImage.getWidth();
//            重新生成一个等大小（长宽）的图片缓存
            BufferedImage zoomImage = new BufferedImage(width, height, 3);
            Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            Graphics gc = zoomImage.getGraphics();
            gc.setColor(Color.WHITE);
            gc.drawImage(image, 0, 0, null);
            encoder.addFrame(zoomImage);
        }
        encoder.finish();
        File outFile = new File(outputPath);
        BufferedImage image = ImageIO.read(outFile);
        ImageIO.write(image, outFile.getName(), outFile);
    }

    /**
     * Gif转图片集
     *
     * @param imagePath
     * @param outputDirPath
     * @throws IOException
     */
    static void gifToImages(String imagePath, String outputDirPath) throws IOException {
        GifDecoder decoder = new GifDecoder();
        int status = decoder.read(imagePath);
        if (status != GifDecoder.STATUS_OK) {
            throw new IOException("read image " + imagePath + " error!");
        }
        for (int i = 0; i < decoder.getFrameCount(); i++) {
            BufferedImage bufferedImage = decoder.getFrame(i);// 获取每帧BufferedImage流
            File outFile = new File(outputDirPath + i + ".png");
            ImageIO.write(bufferedImage, "png", outFile);
        }
    }

    /**
     * 视频倒放
     *
     * @param imagePath
     * @param outputPath
     * @throws IOException
     */
    public static void reverseGif(String imagePath, String outputPath) throws IOException {
        GifDecoder decoder = new GifDecoder();
        int status = decoder.read(imagePath);
        if (status != GifDecoder.STATUS_OK) {
            throw new IOException("read image " + imagePath + " error!");
        }
        // 拆分一帧一帧的压缩之后合成
        AnimatedGifEncoder encoder = new AnimatedGifEncoder();
        encoder.start(outputPath);
        encoder.setRepeat(decoder.getLoopCount());
        for (int i = decoder.getFrameCount() - 1; i >= 0; i--) {
            encoder.setDelay(decoder.getDelay(i));// 设置播放延迟时间
            BufferedImage bufferedImage = decoder.getFrame(i);// 获取每帧BufferedImage流
            int height = bufferedImage.getHeight();
            int width = bufferedImage.getWidth();
            BufferedImage zoomImage = new BufferedImage(width, height, bufferedImage.getType());
            Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            Graphics gc = zoomImage.getGraphics();
            gc.setColor(Color.WHITE);
            gc.drawImage(image, 0, 0, null);
            encoder.addFrame(zoomImage);
        }
        encoder.finish();
        File outFile = new File(outputPath);
        BufferedImage image = ImageIO.read(outFile);
        ImageIO.write(image, outFile.getName(), outFile);
    }

}