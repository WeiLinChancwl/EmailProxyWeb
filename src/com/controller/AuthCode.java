package com.controller;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

public class AuthCode {
	public static final int AUTHCODE_LENGTH = 4;	//验证码长度
	public static final int SINGLECODE_WIDTH = 15;	//单个验证码宽度
	public static final int SINGLECODE_HEIGHT = 30;	//单个验证码高度
	public static final int SINGLECODE_GAP = 4;	//单个验证码之间间隔
	public static final int IMG_WIDTH = AUTHCODE_LENGTH * (SINGLECODE_WIDTH +
			SINGLECODE_GAP);
	public static final int IMG_HEIGHT = SINGLECODE_HEIGHT;

	public static String getAuthCode() {	//产生随机验证数字
		String authCode = "";
		for(int i = 0; i < AUTHCODE_LENGTH; i++) {
			authCode += (new Random()).nextInt(10);
		}
		return authCode;
	}

	public static BufferedImage getAuthImg(String authcode) {
		//设置图片的高、宽、类型，RNB编码：red、green、blue
		BufferedImage img = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_BGR);
		Graphics g = img.getGraphics();			//得到图片上的一支画笔
		g.setColor(Color.YELLOW);	//设置画笔的颜色，用来做背景色
		g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT); 	//用画笔来填充一个矩形
		g.setColor(Color.BLACK); 		//将画笔颜色设置为黑色，用来写字
		g.setFont(new Font("宋体", Font.PLAIN, SINGLECODE_HEIGHT+5));		//设置宋体、不带格式的、字号

		char c;
		for(int i = 0; i < authcode.toCharArray().length; i++) {
			c = authcode.charAt(i);
			g.drawString(c + "", i * (SINGLECODE_WIDTH + SINGLECODE_GAP + SINGLECODE_GAP / 2), IMG_HEIGHT);
		}
		Random random = new Random();
		//干扰素
		for(int i = 0; i < 20; i++) {
			int x = random.nextInt(IMG_WIDTH);
			int y = random.nextInt(IMG_HEIGHT);
			int x2 = random.nextInt(IMG_WIDTH);
			int y2 = random.nextInt(IMG_HEIGHT);
			g.drawLine(x, y, x + x2, y + y2);
		}
		return img;
	}

	public void getImg() {
		String code = "";
		int intCode = (new Random()).nextInt(9999);
		if(intCode < 1000) {
			intCode += 1000;
		}
		code += intCode;
		BufferedImage image = new BufferedImage(35, 14, BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();
		g.setColor(Color.YELLOW);
		g.fillRect(1, 1, 33, 12);
		g.setColor(Color.BLACK);
		g.setFont(new Font("宋体", Font.PLAIN, 12));
		char c;
		for(int i = 0; i < code.toCharArray().length; i++) {
			c = code.charAt(i);
			g.drawString(c+"", i*7+4, 11);
		}
		OutputStream out = null;
		try {
			out = new FileOutputStream(new File("d:\\" + code + ".jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);	//JPG编码器
		try {
			encoder.encode(image);
		} catch(ImageFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
