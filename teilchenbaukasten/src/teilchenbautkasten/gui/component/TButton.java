package teilchenbautkasten.gui.component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class TButton extends JButton{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5188967075389684244L;
	
	private Color accent;
	
	private int state = 0;
	// 0: Normal
	// 1: Hover
	// 2: Pressed
	
	public TButton(String text) {
		this.setBackground(new Color(30, 30, 30));
		this.setAccent(new Color(10, 250, 10));
		this.setForeground(Color.WHITE);
		this.setFont(new Font("Segoe UI", Font.BOLD, 16));
		this.setText(text);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		int w = this.getWidth();
		int h = this.getHeight();
		int corner1 = w/9;
		int corner2 = w/10;
		
		g2d.setBackground(this.getParent().getBackground());
		g2d.clearRect(0, 0, w, h);

		g2d.setColor(state==2?this.getBackground().brighter().brighter():this.getBackground());
		g2d.fillPolygon(new int[] {0, corner1, w, w, 0}, new int[] {corner1, 0, 0, h, h}, 5);
		g2d.setColor(Color.GRAY);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		g2d.setStroke(new BasicStroke(2));
		g2d.drawPolygon(new int[] {1, corner1, w-1, w-1, 1}, new int[] {corner1, 1, 1, h-1, h-1}, 5);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setColor(state==0?this.getAccent().darker().darker():this.getAccent());
		g2d.drawPolyline(new int[] {30, w-7-corner2, w-7, w-7}, new int[] {h-7, h-7, h-7-corner2, 10}, 4);
		
		g2d.setColor(this.getForeground());
		g2d.setFont(this.getFont());
		int sw = g2d.getFontMetrics().stringWidth(this.getText());
		g2d.drawString(this.getText(), w/2-sw/2, h-16);
		
	}
	
	
	public Color getAccent() {
		return accent;
	}

	public void setAccent(Color accent) {
		this.accent = accent;
	}

}
