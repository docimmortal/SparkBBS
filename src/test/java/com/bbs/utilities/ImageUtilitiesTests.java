package com.bbs.utilities;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ImageUtilitiesTests {

	@Test
	public void resize() {
		BufferedImage image;
		try {
			image = ImageUtilities.getImageFromFile("/images/MsgImages/","cat1.png",true);

			ImageUtilities.resizeImageAndSave(image,"png",BigInteger.ONE,1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void resize2() {
		BufferedImage image;
		try {
			image = ImageUtilities.getImageFromFile("/images/MsgImages/","cat2.jpg",true);

			ImageUtilities.resizeImageAndSave(image,"jpg",BigInteger.ONE,1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
