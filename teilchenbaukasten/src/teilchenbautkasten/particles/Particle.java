package teilchenbautkasten.particles;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Particle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 485189013947391259L;
	private String name;
	private String symbol;
	private float charge;
	private float mass;
	private double lifetime;
	private float spin;
	private BufferedImage icon;
	private ParticleType type;
	
	public Particle(String name, String symbol, float charge, float mass, double lifetime, float spin, BufferedImage icon, ParticleType type) {
		this.setName(name);
		this.setSymbol(symbol);
		this.setCharge(charge);
		this.setMass(mass);
		this.setLifetime(lifetime);
		this.setSpin(spin);
		this.setIcon(icon);
		this.setType(type);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public float getCharge() {
		return charge;
	}

	public void setCharge(float charge) {
		this.charge = charge;
	}

	public float getMass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public double getLifetime() {
		return lifetime;
	}

	public void setLifetime(double lifetime) {
		this.lifetime = lifetime;
	}

	public float getSpin() {
		return spin;
	}

	public void setSpin(float spin) {
		this.spin = spin;
	}

	public BufferedImage getIcon() {
		return icon;
	}

	public void setIcon(BufferedImage icon) {
		this.icon = icon;
	}

	public ParticleType getType() {
		return type;
	}

	public void setType(ParticleType type) {
		this.type = type;
	}
}
