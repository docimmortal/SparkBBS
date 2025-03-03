package com.bbs.utilities;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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
			//I had to jerry rig this for it to work.
			ImageUtilities i = new ImageUtilities();
			ClassLoader classLoader = i.getClass().getClassLoader();
			imgPath=new File(classLoader.getResource("static/images/"+filename).getFile());
		}else {
			imgPath=new File(filename);
		}
		BufferedImage bufferedImage = ImageIO.read(imgPath);

		// This does not work?????
		// get DataBufferBytes from Raster
		//WritableRaster raster = bufferedImage .getRaster();
		//DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

		//return ( Base64.encodeBase64String(data.getData()) );
		return bufferedImage;
	}
}
