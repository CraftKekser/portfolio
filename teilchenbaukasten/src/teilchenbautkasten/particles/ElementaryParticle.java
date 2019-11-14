package teilchenbautkasten.particles;

import java.awt.image.BufferedImage;

public class ElementaryParticle extends Particle{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7636040867512407619L;
	private int generation;
	private String flavor;
	
	public ElementaryParticle(String name, String symbol, float charge, float mass, double lifetime, float spin, BufferedImage icon, ParticleType type,
			int generation, String flavor) {
		super(name, symbol, charge, mass, lifetime, spin, icon, type);
		this.setFlavor(flavor);
		this.setGeneration(generation);
	}
	
	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}

	public String getFlavor() {
		return flavor;
	}

	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

}
