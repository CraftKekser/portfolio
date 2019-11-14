
package de.craftkekser.ips.gui.component;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class JResponsiveImagePanel extends JPanel {

	private static final long serialVersionUID = 2587296682650283399L;

	private BufferedImage image;

	public JResponsiveImagePanel(BufferedImage image) {
		this.setImage(image);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
		this.repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(this.image != null) {
			BufferedImage tempIMG = this.getImage();

			int x = tempIMG.getWidth();
			int y = tempIMG.getHeight();
			int w = (int) this.getWidth();
			int h = (int) this.getHeight();

			
			double ar = (double)x/(double)y;
			
			if(x>=w || w < x && !(h<y)) {
				x = w;
				y = (int) (x/ar);
			}
			if(y>=h || h < y && !(w<x)) {
				y = h;
				x = (int) (y*ar);
			}
			
			int actualX = w/2-x/2;
			int actualY = h/2-y/2;
			
			g.drawImage(tempIMG, actualX+2, actualY+2, x-4, y-4, this);
		}
		paintComponents(g);
	}

	public static BufferedImage flipV(BufferedImage image) {
		AffineTransform tx = AffineTransform.getScaleInstance(-1.0D, 1.0D);
		tx.translate(-image.getWidth(null), 0.0D);
		AffineTransformOp op = new AffineTransformOp(tx, 1);
		return op.filter(image, null);
	}

	public static BufferedImage flipH(BufferedImage image) {
		AffineTransform tx = AffineTransform.getScaleInstance(1.0D, -1.0D);
		tx.translate(0.0D, -image.getHeight(null));
		AffineTransformOp op = new AffineTransformOp(tx, 1);
		return op.filter(image, null);
	}

	@SuppressWarnings("unused")
	@Deprecated
	private BufferedImage rotate(BufferedImage image, double deg) {
		AffineTransform tx = AffineTransform.getScaleInstance(1.0D, -1.0D);
		tx.rotate(Math.toDegrees(deg));
		AffineTransformOp op = new AffineTransformOp(tx, 1);
		return op.filter(image, null);
	}


}
