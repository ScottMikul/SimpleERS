package sandbox;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;
 
 
public class SimpleConvertImage {
	public static void main(String[] args) throws IOException{
		String dirName="C:\\Users\\Scotty\\Pictures";
		//outputstream
		ByteArrayOutputStream baos=new ByteArrayOutputStream(1000);
		//buffered image
		BufferedImage img=ImageIO.read(new File(dirName,"newpackagetest.png"));
		//write buffered image to the jpg file
		ImageIO.write(img, "jpg", baos);
		//flushing?
		baos.flush();
 
		byte[] base64String=Base64.encode(baos.toByteArray());
		baos.close();
 
		byte[] bytearray = Base64.decode(base64String);
 
		BufferedImage imag=ImageIO.read(new ByteArrayInputStream(bytearray));
		ImageIO.write(imag, "jpg", new File(dirName,"pngtojpgtopng.png"));
	}
}