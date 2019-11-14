package teilchenbautkasten.gui.component;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JList;
import javax.swing.JPanel;

import teilchenbautkasten.particles.ElementaryParticle;
import teilchenbautkasten.particles.Hadron;
import teilchenbautkasten.particles.Particle;
import teilchenbautkasten.particles.ParticleType;
import teilchenbautkasten.particles._all;
import teilchenbautkasten.utils.Vector2;

public class BaukastenPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4360160209599369729L;

	private ParticleType type;
	private ElementaryParticle[] components; // up = 2; left = 0; right = 1
	private Hadron[] result;
	private Point up;
	private Point left;
	private Point right;
	private int active; // up = 1; left = 2; right = 3
	private int radius = 35;
	private JList<ElementaryParticle> partlist;
	private ElementaryParticle dragging;
	private Point draggingPoint;
	private float filterSpin = 0.5F;
	public List<ParticleRecognizedEvent> tee;


	public BaukastenPanel(ParticleType type, ElementaryParticle...components) {
		this.setType(type);
		this.setMyComponents(components);
		this.setResult(null);
		registerEvents();
	}

	public BaukastenPanel(ParticleType type) {
		this.setType(type);
		switch(getSimplifyType()) {
		case BARYON:
			this.setMyComponents(new ElementaryParticle[3]);
			break;
		case MESON:
			this.setMyComponents(new ElementaryParticle[2]);
			break;
		default:
			this.setMyComponents(new ElementaryParticle[1]);
		}
		this.setResult(null);
		registerEvents();
	}

	public ParticleType getSimplifyType() {
		switch(this.type) {
		case BARYON:
		case BARYON_ONEHALF:
		case BARYON_HALF:
			return ParticleType.BARYON;
		case MESON:
		case MESON_PSEUDOSCALAR:
		case MESON_VECTOR:
			return ParticleType.MESON;
		default:
			return ParticleType.BARYON;
		}
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		int w = this.getWidth();
		int h = this.getHeight();
		int r = this.radius;
		int rr = (int) (r*1.5);

		int cX = w/2; int cY = h/2;

		g2d.setBackground(this.getBackground());
		g2d.clearRect(0, 0, w, h);

		// Draw Particle Name(s)
		String names = "";
		if(result!=null) {
			for(Hadron hd : result) {
				names += hd.getName() + " • ";
			}
		}
		if(names.length()>4)
			names = names.substring(0, names.length()-3);
		g2d.setFont(new Font("Segoe UI", Font.BOLD, 24));
		g2d.setColor(Color.WHITE);
		int stringWUS = g2d.getFontMetrics().stringWidth(result==null?"Unbekanntes Teilchen":names);
		g2d.drawString(result==null?"Unbekanntes Teilchen":names, w/2-stringWUS/2, h-20);

		up = new Point(cX, (int) (cY-rr)); // up
		right = new Point((int) Math.round(Math.cos(Math.toRadians(30.0D))*rr)+cX, (int) Math.round(Math.sin(Math.toRadians(30.0D))*rr)+cY); // right
		left = new Point(cX-(int) Math.round(Math.cos(Math.toRadians(30.0D))*rr), (int) Math.round(Math.sin(Math.toRadians(30.0D))*rr)+cY); // right

		switch(getSimplifyType()) {
		case BARYON:
			// Draw placed particles
			if(components[2]!=null) {
				Point komp2 = startFromCenter(up, r);
				g2d.drawImage(components[2].getIcon().getScaledInstance(r*2+1, r*2+1, 100), komp2.x, komp2.y, this);
			}
			if(components[0]!=null) {
				Point komp0 = startFromCenter(left, r);
				g2d.drawImage(components[0].getIcon().getScaledInstance(r*2+1, r*2+1, 100), komp0.x, komp0.y, this);
			}
			if(components[1]!=null) {
				Point komp1 = startFromCenter(right, r);
				g2d.drawImage(components[1].getIcon().getScaledInstance(r*2+1, r*2+1, 100), komp1.x, komp1.y, this);
			}


			// Draw placeholders
			g2d.setStroke(new BasicStroke(3));
			g2d.setColor(active==1?Color.LIGHT_GRAY:Color.GRAY);
			drawCircleCenter(up, r, g2d);
			g2d.setColor(active==3?Color.LIGHT_GRAY:Color.GRAY);
			drawCircleCenter(right, r, g2d);
			g2d.setColor(active==2?Color.LIGHT_GRAY:Color.GRAY);
			drawCircleCenter(left, r, g2d);

			break;
		case MESON:
			if(components[0]!=null) {
				Point komp0 = startFromCenter(left, r);
				g2d.drawImage(components[0].getIcon().getScaledInstance(r*2+1, r*2+1, 100), komp0.x, komp0.y, this);
			}
			if(components[1]!=null) {
				Point komp1 = startFromCenter(right, r);
				g2d.drawImage(components[1].getIcon().getScaledInstance(r*2+1, r*2+1, 100), komp1.x, komp1.y, this);
			}


			g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
			g2d.setColor(active==3?Color.LIGHT_GRAY:Color.GRAY);
			drawCircleCenter(right, r, g2d);
			g2d.setStroke(new BasicStroke(3));
			g2d.setColor(active==2?Color.LIGHT_GRAY:Color.GRAY);
			drawCircleCenter(left, r, g2d);

			break;
		default:
			break;
		}


		// Draw moving particles
		if(dragging!=null) {
			Point pointer = startFromCenter(draggingPoint, r);
			g2d.drawImage(dragging.getIcon().getScaledInstance(r*2, r*2, 100), pointer.x, pointer.y, this);
		}

		this.paintComponents(g2d);

	}

	public void reset(ParticleType type) {
		this.active=0;
		this.setType(type);
		switch(getSimplifyType()) {
		case BARYON:
			this.setMyComponents(new ElementaryParticle[3]);
			break;
		case MESON:
			this.setMyComponents(new ElementaryParticle[2]);
			break;
		default:
			this.setMyComponents(new ElementaryParticle[1]);
		}
		this.setResult(null);
		this.repaint();
	}
	public void put(ParticleType type, ElementaryParticle[] contents) {
		this.active=0;
		this.setType(type);
		this.setMyComponents(contents);
		this.rerecognize();
		this.repaint();
	}

	private void drawCircleCenter(Point center, int radius, Graphics2D g2d) {
		int startX = center.x-radius;
		int startY = center.y-radius;
		g2d.drawOval(startX, startY, radius*2, radius*2);
	}

	private Point startFromCenter(Point center, int radius) {
		int startX = center.x-radius;
		int startY = center.y-radius;
		return new Point(startX, startY);
	}

	private List<Hadron> getResult() {
		if(this.getSimplifyType()==ParticleType.BARYON) {
			int ocount_up = 0;
			int ocount_down = 0;
			int ocount_strange = 0;
			int ocount_charm = 0;
			int ocount_top = 0;
			int ocount_bottom = 0;
			int ocount_ANTIup = 0;
			int ocount_ANTIdown = 0;
			int ocount_ANTIstrange = 0;
			int ocount_ANTIcharm = 0;
			int ocount_ANTItop = 0;
			int ocount_ANTIbottom = 0;

			int count_up = 0;
			int count_down = 0;
			int count_strange = 0;
			int count_charm = 0;
			int count_top = 0;
			int count_bottom = 0;
			int count_ANTIup = 0;
			int count_ANTIdown = 0;
			int count_ANTIstrange = 0;
			int count_ANTIcharm = 0;
			int count_ANTItop = 0;
			int count_ANTIbottom = 0;

			List<Hadron> found = new ArrayList<Hadron>();

			for(ElementaryParticle p : components) {
				if(p==null) {
					return null;
				}
				switch(p.getSymbol()) {
				case "u":
					ocount_up++;
					break;
				case "d":
					ocount_down++;
					break;
				case "s":
					ocount_strange++;
					break;
				case "c":
					ocount_charm++;
					break;
				case "t":
					ocount_top++;
					break;
				case "b":
					ocount_bottom++;
					break;
				case "overline{u}":
					ocount_ANTIup++;
					break;
				case "overline{d}":
					ocount_ANTIdown++;
					break;
				case "overline{s}":
					ocount_ANTIstrange++;
					break;
				case "overline{c}":
					ocount_ANTIcharm++;
					break;
				case "overline{t}":
					ocount_ANTItop++;
					break;
				case "overline{b}":
					ocount_ANTIbottom++;
					break;
				default:
					break;
				}
			}
			for(Hadron hadron : _all.hadrons) {
				for(Particle p : hadron.getContent()) {
					switch(p.getSymbol()) {
					case "u":
						count_up++;
						break;
					case "d":
						count_down++;
						break;
					case "s":
						count_strange++;
						break;
					case "c":
						count_charm++;
						break;
					case "t":
						count_top++;
						break;
					case "b":
						count_bottom++;
						break;
					case "overline{u}":
						count_ANTIup++;
						break;
					case "overline{d}":
						count_ANTIdown++;
						break;
					case "overline{s}":
						count_ANTIstrange++;
						break;
					case "overline{c}":
						count_ANTIcharm++;
						break;
					case "overline{t}":
						count_ANTItop++;
						break;
					case "overline{b}":
						count_ANTIbottom++;
						break;
					default:
						break;
					}
				}
				if(count_bottom==ocount_bottom && count_charm==ocount_charm && count_down == ocount_down && 
						count_strange == ocount_strange && count_top == ocount_top && count_up == ocount_up && hadron.getSpin()==filterSpin &&
						count_ANTIbottom==ocount_ANTIbottom && count_ANTIcharm==ocount_ANTIcharm && count_ANTIdown == ocount_ANTIdown && 
						count_ANTIstrange == ocount_ANTIstrange && count_ANTItop == ocount_ANTItop && count_ANTIup == ocount_ANTIup) {
					if(hadron!=null)
						found.add(hadron);
				}
				count_bottom=0;
				count_charm=0;
				count_down=0;
				count_strange=0;
				count_top=0;
				count_up=0;
				count_ANTIbottom=0;
				count_ANTIcharm=0;
				count_ANTIdown=0;
				count_ANTIstrange=0;
				count_ANTItop=0;
				count_ANTIup=0;
			}
			if(found.size()==0) {
				float charge = components[0].getCharge() + components[1].getCharge() + (getSimplifyType()==ParticleType.BARYON?components[2].getCharge():0);
				float mass = components[0].getMass() + components[1].getMass() + (getSimplifyType()==ParticleType.BARYON?components[2].getMass():0);
				try {
					Hadron unknown = new Hadron("Unbekanntes Teilchen", "?", charge, mass, 0.0D, -1, ImageIO.read(BaukastenPanel.class.getResourceAsStream("/assets/teilchen/unknown.png")), getSimplifyType(), components, new ArrayList<Particle[]>());
					found.add(unknown);
				} catch (IOException e) {}
			}
			if(found.size()==0) {
				return null;
			}else {
				return found;
			}
		}else {
			List<Hadron> found = new ArrayList<Hadron>();
			if(components.length>1 && components[0]!=null && components[1] != null) {
				for(Hadron h : _all.hadrons) {
					if(h.getContent().length<3) {
						if(components[0].getSymbol().equals(h.getContent()[0].getSymbol()) && components[1].getSymbol().equals(h.getContent()[1].getSymbol())) {
							found.add(h);
						}
					}
				}
				if(found.size()==0) {
					float charge = components[0].getCharge() + components[1].getCharge() + (getSimplifyType()==ParticleType.BARYON?components[2].getCharge():0);
					float mass = components[0].getMass() + components[1].getMass() + (getSimplifyType()==ParticleType.BARYON?components[2].getMass():0);
					try {
						Hadron unknown = new Hadron("Unbekanntes Teilchen", "?", charge, mass, 0.0D, -1, ImageIO.read(BaukastenPanel.class.getResourceAsStream("/assets/teilchen/unknown.png")), getSimplifyType(), components, new ArrayList<Particle[]>());
						found.add(unknown);
					} catch (IOException e) {}
				}
			}
			if(found.size()==0) {
				return null;
			}else {
				return found;
			}
		}
	}

	private void registerEvents() {
		this.active=0;
		this.tee = new ArrayList<BaukastenPanel.ParticleRecognizedEvent>();
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if(dragging!=null) {
					switch (active) {
					case 1:
						if(components.length>2)
							if(dragging.getType()==ParticleType.QUARK)
								components[2] = dragging;
						break;
					case 2:
						if(dragging.getType()==ParticleType.QUARK)
							components[0] = dragging;
						break;
					case 3:
						if(dragging.getType()==ParticleType.QUARK)
							components[1] = dragging;
						break;
					default:
						if(dragging.getType()!=ParticleType.QUARK)
							dragging=null;
						break;
					}
					if(dragging != null && dragging.getType()==ParticleType.QUARK)
						dragging=null;
				} else {
					switch (active) {
					case 1:
						if(components.length>2)
							components[2] = null;
						break;
					case 2:
						components[0] = null;
						break;
					case 3:
						components[1] = null;
						break;
					default:
						break;
					}
				}
				rerecognize();
			}

			@Override
			public void mousePressed(MouseEvent e) { }

			@Override
			public void mouseExited(MouseEvent e) { }

			@Override
			public void mouseEntered(MouseEvent e) {
				if(!partlist.isSelectionEmpty()) {
					dragging = partlist.getSelectedValue();
					partlist.clearSelection();
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) { }
		});
		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				Point mouse = e.getPoint();
				Vector2 toup = new Vector2(mouse, up);
				Vector2 toleft = new Vector2(mouse, left);
				Vector2 toright = new Vector2(mouse, right);
				int activeBefore = active;
				if(toup.length() < radius) {
					if(components.length>2)
						active = 1;
				}else if(toleft.length() < radius) {
					active = 2;
				}else if(toright.length() < radius) {
					active = 3;
				}else {
					active = 0;
				}
				if(active!=activeBefore) {
					repaint();
				}

				draggingPoint = e.getPoint();
				if(dragging!=null) {
					repaint();
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				draggingPoint = e.getPoint();
				if(dragging!=null) {
					repaint();
				}
			}
		});
	}

	public void rerecognize() {
		List<Hadron> rse = getResult();
		result = rse!=null?(Hadron[]) rse.toArray(new Hadron[] {}):null;
		repaint();
		tee.forEach(event -> {
			try {
				event.recognized(result);
			}catch(Exception ex) {}
		});
	}

	public ParticleType getTypee() {
		return type;
	}

	public void setType(ParticleType type) {
		this.type = type;
	}

	public ElementaryParticle[] getMyComponents() {
		return components;
	}

	public void setMyComponents(ElementaryParticle[] components) {
		this.components = components;
	}

	public Point getUp() {
		return up;
	}

	public void setUp(Point up) {
		this.up = up;
	}

	public Point getLeft() {
		return left;
	}

	public void setLeft(Point left) {
		this.left = left;
	}

	public Point getRight() {
		return right;
	}

	public void setRight(Point right) {
		this.right = right;
	}

	public JList<ElementaryParticle> getPartlist() {
		return partlist;
	}

	public void setPartlist(JList<ElementaryParticle> partlist) {
		this.partlist = partlist;
	}

	public void setResult(Hadron[] result) {
		this.result = result;
	}

	public float getFilterSpin() {
		return filterSpin;
	}

	public void setFilterSpin(float filterSpin) {
		this.filterSpin = filterSpin;
	}

	public interface BaukastenKlickEvent{
		public void klick(Point ort, int aktiv, int taste);
	}
	public interface ParticleRecognizedEvent{
		public void recognized(Hadron[] teilchen);
	}

}
