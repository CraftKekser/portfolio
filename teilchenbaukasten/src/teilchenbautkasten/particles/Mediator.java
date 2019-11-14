package teilchenbautkasten.particles;

import java.awt.image.BufferedImage;
import java.util.List;

public class Mediator extends Particle{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8838011616575085872L;
	private List<Particle[]> decays;
	private String force;
	
	public Mediator(String name, String symbol, float charge, float mass, double lifetime, float spin, BufferedImage icon, ParticleType type,
			List<Particle[]> decays, String force) {
		super(name, symbol, charge, mass, lifetime, spin, icon, type);
		this.setDecays(decays);
		this.setForce(force);
	}

	public List<Particle[]> getDecays() {
		return decays;
	}

	public void setDecays(List<Particle[]> decays) {
		this.decays = decays;
	}

	public String getForce() {
		return force;
	}

	public void setForce(String force) {
		this.force = force;
	}
}
