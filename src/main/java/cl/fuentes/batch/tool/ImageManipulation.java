package cl.fuentes.batch.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;


import org.apache.commons.codec.binary.Base64;

public class ImageManipulation {

	public static void main(String[] args) {
		File file = new File("resources/imagen1.png");
		
		try {
			FileInputStream imageInFile = new FileInputStream(file);
			byte imageData[] = new byte[(int)file.length()];
			imageInFile.read(imageData);
			
			String imageDataString = encodeImage(imageData);
                        try (PrintStream out = new PrintStream(new FileOutputStream("resources/mage3.dat"))) {
                            out.print(imageDataString);
                        }			                 
			byte[] imageByteArray = decodeImage(imageDataString);
			
			FileOutputStream imageOutFile = new FileOutputStream("resources/imagen2.png");
			imageOutFile.write(imageByteArray);
			
			imageInFile.close();
			imageOutFile.close();
			
			System.out.println("Image Successfully Manipulated!");
		} catch (FileNotFoundException e) {
			System.out.println("Image not found" + e);
		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}

	}
	
	public static String encodeImage(byte[] imageByteArray){		
		return Base64.encodeBase64URLSafeString(imageByteArray);		
	}
	
	public static byte[] decodeImage(String imageDataString) {		
		return Base64.decodeBase64(imageDataString);
	}

}
