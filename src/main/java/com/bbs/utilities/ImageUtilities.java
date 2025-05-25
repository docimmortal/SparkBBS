package com.bbs.utilities;

import java.awt.Color;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;

import javax.imageio.ImageIO;

import java.util.Base64;

public class ImageUtilities {

	public static String imageToString(byte[] image) {
	   return Base64.getEncoder().encodeToString(image);
	}
	
	public static BufferedImage convertFromClob(byte[] byteimage) throws IOException {
		BufferedImage image = ImageIO.read(new ByteArrayInputStream(byteimage));
		return image;
	}
	
	public static BufferedImage getImageFromFile(String filename) throws FileNotFoundException, IOException {
		return getImageFromFile(filename, false);
	}
	
	public static BufferedImage getImageFromFile(String filename, boolean local) throws FileNotFoundException, IOException {
		File imgPath = null;
		if (local) {
			ImageUtilities i = new ImageUtilities();
			ClassLoader classLoader = i.getClass().getClassLoader();
			imgPath=new File(classLoader.getResource("static/images/"+filename).getFile());
		}else {
			imgPath=new File(filename);
		}
		BufferedImage bufferedImage = ImageIO.read(imgPath);
		return bufferedImage;
	}
	
	public static BufferedImage getImageFromFile(String directory, String filename, boolean local) throws FileNotFoundException, IOException {
		File imgPath = null;
		if (local) {
			ImageUtilities i = new ImageUtilities();
			ClassLoader classLoader = i.getClass().getClassLoader();
			System.out.println(directory+" "+filename);
			imgPath=new File(classLoader.getResource("static/"+directory+filename).getFile());
		}else {
			imgPath=new File(filename);
		}
		BufferedImage bufferedImage = ImageIO.read(imgPath);
		return bufferedImage;
	}
	
	public static void resizeImageAndSave(BufferedImage image, String type, BigInteger msgNo, Integer imgNo) {

		long width = image.getWidth(null);
		long height = image.getHeight(null);
	    
	    if (height>600 || width>600) {
	    	double heightPercentOver = height/600;
	    	double widthPercentOver = width/600;
	    	double resizeBy = heightPercentOver > widthPercentOver? heightPercentOver: widthPercentOver;
	    	width = Math.round(width/resizeBy);
	    	height = Math.round(height/resizeBy);
	    }
		Image newImage = image.getScaledInstance((int)width, (int)height, Image.SCALE_SMOOTH);
	    BufferedImage bi = new BufferedImage((int)width, (int)height, 
	    		image.getTransparency() == Transparency.OPAQUE ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB);

	    bi.createGraphics().drawImage(newImage, 0, 0, Color.WHITE, null);

	    String filename = "C:\\Users\\Public\\Pictures\\"+msgNo+"_"+imgNo+"."+type;
		try {
			boolean rv = ImageIO.write( bi, type, new File(filename) );
			System.out.println("Image save:"+rv);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
