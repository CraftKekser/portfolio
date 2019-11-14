package de.craftkekser.ips.gui.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;

import javax.swing.JProgressBar;

public class CProgressBar extends JProgressBar{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2004763128126558048L;

	public CProgressBar() {
		this.setOpaque(true);
		this.setBackground(new Color(10, 10, 10));
		this.setForeground(new Color(0, 240, 200));
		this.setMinimum(0);
		this.setMaximum(100);
		this.setValue(50);
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		int w = getWidth();
		int h = getHeight();
		int r = 5;
		int shd = 3;
		int sp1 = shd+1;

		g2d.setColor(getParent().getBackground());
		g2d.fillRect(0, 0, w, h);

		for(int i = shd; i > 0; i--) {
			g2d.setColor(new Color(57, 57, 57, 20*(i)));
			g2d.fillRoundRect(i-1, i-1, w-i*2+1, h-i*2+1, r*2, r*2);
		}


		LinearGradientPaint gradient2 = new LinearGradientPaint(new Point2D.Float(0, 0), new Point2D.Float(0, h), new float[] {0, 0.3F, 0.7F, 1}, new Color[] {getBackground().brighter().brighter().brighter().brighter(), getBackground(), getBackground(), getBackground().brighter().brighter().brighter().brighter()});
		g2d.setPaint(gradient2);
		
		g2d.fillRoundRect(shd, shd, w-shd*2, h-shd*2, r, r);

		LinearGradientPaint gradient1 = new LinearGradientPaint(new Point2D.Float(0, 0), new Point2D.Float(0, h), new float[] {0, 0.3F, 0.7F, 1}, new Color[] {getForeground(), getForeground().darker().darker(), getForeground().darker().darker(), getForeground()});
		g2d.setPaint(gradient1);

		g2d.fillRoundRect(sp1, sp1, (int)((w-sp1*2) * (double)((double)getValue()/100.D)), h-sp1*2, r, r);
		
		g2d.dispose();
	}
	
}
