package de.craftkekser.ips.gui.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Point2D;

import javax.swing.JTextField;

public class CTextField extends JTextField {

	private static final long serialVersionUID = -6460867858095530630L;

	private int state = 0;
	// 0: Normal
	// 1: Hover
	// 2: Pressed

	public CTextField() {
		this.setOpaque(true);
		this.setText("Input");
		this.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.setBackground(new Color(10, 10, 10));
		this.setForeground(new Color(240, 240, 240));
		this.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				state = 0;
				repaint();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				state = 1;
				repaint();
			}
		});
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


		g2d.setColor(getBackground());
		g2d.fillRoundRect(shd, shd, w-shd*2, h-shd*2, r, r);

		LinearGradientPaint gradient1 = new LinearGradientPaint(new Point2D.Float(0, 0), new Point2D.Float(0, h), new float[] {0, 0.3F}, new Color[] {
				state==1?getBackground().brighter().brighter().brighter().brighter().brighter():getBackground().brighter().brighter().brighter(), 
				state==1?getBackground().brighter().brighter().brighter().brighter().brighter().brighter().brighter():getBackground().brighter().brighter().brighter().brighter().brighter()
			});
		g2d.setPaint(gradient1);

		g2d.fillRoundRect(sp1, sp1, w-sp1*2, h-sp1*2, r, r);

		g2d.setColor(getForeground());
		g2d.setFont(getFont());

		int th = (int) g2d.getFontMetrics().getStringBounds(this.getText(), g2d).getHeight();

		g2d.drawString(this.getText(), 10, h/2+th/2-2);
		g2d.dispose();
	}
	
}
