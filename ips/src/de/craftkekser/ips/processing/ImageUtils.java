package de.craftkekser.ips.processing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.List;

public class ImageUtils {


	public static BufferedImage toBufferedImage(Image img)
	{
		if (img instanceof BufferedImage)
		{
			return (BufferedImage) img;
		}
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		return bimage;
	}

	
	// StackOverflow
	public static BufferedImage toCompatibleImage(BufferedImage image)
	{
		GraphicsConfiguration gfxConfig = GraphicsEnvironment.
				getLocalGraphicsEnvironment().getDefaultScreenDevice().
				getDefaultConfiguration();

		if (image.getColorModel().equals(gfxConfig.getColorModel()))
			return image;

		BufferedImage newImage = gfxConfig.createCompatibleImage(
				image.getWidth(), image.getHeight(), image.getTransparency());

		Graphics2D g2d = newImage.createGraphics();


		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();

		return newImage; 
	}
	// ---

	public static BufferedImage scale(BufferedImage img, int w, int h) {
		BufferedImage scaled = new BufferedImage(w, h, img.getType()!=0?img.getType():BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = scaled.createGraphics();

		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

		graphics.drawImage(img, 0, 0, w, h, null);
		return scaled;
	}

	public static BufferedImage scale(BufferedImage img, double factor) {
		return scale(img, (int)(img.getWidth()*factor), (int)(img.getHeight()*factor));
	}

	public static BufferedImage combinePreview(List<BufferedImage> images, double scale, ImageObserver observer) {
		int completeWidth = 0;
		int completeHeight = 0;
		System.out.println("Preview: Combining " + images.size() + " images scaled by " + scale);
		for(BufferedImage i : images) {
			completeWidth += (int)(i.getWidth()*scale) + 2;
			if(i.getHeight()>completeHeight) {
				completeHeight = (int) (i.getHeight()*scale);
			}
		}
		completeWidth -= 2;
		BufferedImage all = new BufferedImage(completeWidth, completeHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = all.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, completeWidth, completeHeight);
		int currentX = 0;
		int cnt = 1;
		for(BufferedImage bi : images) {
			int w = (int) (bi.getWidth()*scale);
			int h = (int) (bi.getHeight()*scale);
			BufferedImage img = scale(bi, w, h);
			g.drawImage(img, currentX, 0, w, h, observer);
			g.setColor(new Color(0, 230, 255));
			g.setStroke(new BasicStroke(2));
			if(cnt!=1)
				g.drawLine(currentX+1, 0, currentX+1, h);
			g.setColor(new Color(0, 30, 60));
			if(cnt!=1)
				g.drawLine(currentX+2, 0, currentX+2, h);
			System.out.println("Preview: Added image " + cnt + "/" + images.size() + " at x=" + currentX);
			currentX += w+2;
			cnt++;
		}
		g.dispose();
		System.out.println("Preview: Done.");
		return all;
	}

}
