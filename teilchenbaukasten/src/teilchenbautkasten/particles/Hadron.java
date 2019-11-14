package teilchenbautkasten.particles;

import java.awt.image.BufferedImage;
import java.util.List;

public class Hadron extends Particle{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5376805334104426479L;
	private Particle[] content;
	private List<Particle[]> decays;
	
	public Hadron(String name, String symbol, float charge, float mass, double lifetime, float spin, BufferedImage icon, ParticleType type,
			Particle[] content, List<Particle[]> decays) {
		super(name, symbol, charge, mass, lifetime, spin, icon, type);
		this.setContent(content);
		this.setDecays(decays);
	}

	public Particle[] getContent() {
		return content;
	}

	public void setContent(Particle[] content) {
		this.content = content;
	}

	public List<Particle[]> getDecays() {
		return decays;
	}

	public void setDecays(List<Particle[]> decays) {
		this.decays = decays;
	}
}
