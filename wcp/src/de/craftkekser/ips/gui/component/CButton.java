package de.craftkekser.ips.gui.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

import javax.swing.JButton;

public class CButton extends JButton{

	private static final long serialVersionUID = 6103524642439288737L;

	private int state = 0;
	// 0: Normal
	// 1: Hover
	// 2: Pressed

	public CButton() {
		this.setOpaque(true);
		this.setText("Button");
		this.setFont(new Font("Yu Gothic UI", Font.PLAIN, 12));
		this.setBackground(new Color(10, 10, 10));
		this.setForeground(new Color(240, 240, 240));
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getX() < getWidth() && e.getX() > 0 && e.getY() > 0 && e.getY() < getHeight()) {
					state = 1;
				}else {
					state = 0;
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				state = 2;

			}

			@Override
			public void mouseExited(MouseEvent e) {
				if(state!=2) {
					state = 0;
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if(state==0) {
					state = 1;
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) { }
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
		int sp1 = shd+2;

		g2d.setColor(getParent().getBackground());
		g2d.fillRect(0, 0, w, h);

		for(int i = shd; i > 0; i--) {
			g2d.setColor(new Color(57, 57, 57, 20*(i)));
			g2d.fillRoundRect(i-1, i-1, w-i*2+1, h-i*2+1, r*2, r*2);
		}


		g2d.setColor(getBackground());
		g2d.fillRoundRect(shd, shd, w-shd*2, h-shd*2, r, r);

		LinearGradientPaint gradient1 = new LinearGradientPaint(new Point2D.Float(0, (state==2||isSelected())?0:h), new Point2D.Float(0, (state==2||isSelected())?h:0), new float[] {0,1}, new Color[] {
				state==1?getBackground().brighter().brighter().brighter().brighter().brighter():getBackground().brighter().brighter().brighter().brighter(), 
				state==1?getBackground().brighter().brighter().brighter().brighter().brighter().brighter().brighter():getBackground().brighter().brighter().brighter().brighter().brighter().brighter()
			});
		g2d.setPaint(gradient1);

		if(!isEnabled()) {
			g2d.setColor(Color.GRAY);
		}
		
		g2d.fillRoundRect(sp1, sp1, w-sp1*2, h-sp1*2, r, r);

		g2d.setColor(getForeground());
		g2d.setFont(getFont());

		int tw = g2d.getFontMetrics().stringWidth(this.getText());
		int th = (int) g2d.getFontMetrics().getStringBounds(this.getText(), g2d).getHeight();

		g2d.drawString(this.getText(), w/2-tw/2, h/2+th/2-2);

		g2d.dispose();
	}

}
