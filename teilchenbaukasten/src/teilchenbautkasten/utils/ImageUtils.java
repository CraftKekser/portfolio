package teilchenbautkasten.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImageUtils {

	public static BufferedImage invert(BufferedImage img) {
		BufferedImage inverted = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		for(int x = 0; x < img.getWidth(); x++) {
			for(int y = 0; y < img.getWidth(); y++) {
				Color invc = new Color(img.getRGB(x, y));
				int r = 255-invc.getRed();
				int g = 255-invc.getGreen();
				int b = 255-invc.getBlue();
				invc = new Color(r, g, b, (r==0&&g==0&&b==0?0:255));
				inverted.setRGB(x, y, invc.getRGB());
			}
		}
		return inverted;
	}
	
}
