package teilchenbautkasten.particles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import teilchenbautkasten.utils.ImageUtils;

public class _all {
	public static ElementaryParticle quark_up;
	public static ElementaryParticle quark_down;
	public static ElementaryParticle quark_charm;
	public static ElementaryParticle quark_strange;
	public static ElementaryParticle quark_top;
	public static ElementaryParticle quark_bottom;
	public static ElementaryParticle ANTIquark_up;
	public static ElementaryParticle ANTIquark_down;
	public static ElementaryParticle ANTIquark_charm;
	public static ElementaryParticle ANTIquark_strange;
	public static ElementaryParticle ANTIquark_top;
	public static ElementaryParticle ANTIquark_bottom;

	public static ElementaryParticle elektron;
	public static ElementaryParticle myon;
	public static ElementaryParticle tauon;
	public static ElementaryParticle elektron_neutrino;
	public static ElementaryParticle myon_neutrino;
	public static ElementaryParticle tau_neutrino;
	public static ElementaryParticle ANTIelektron;
	public static ElementaryParticle ANTImyon;
	public static ElementaryParticle ANTItauon;
	public static ElementaryParticle ANTIelektron_neutrino;
	public static ElementaryParticle ANTImyon_neutrino;
	public static ElementaryParticle ANTItau_neutrino;



	public static Hadron proton;
	public static Hadron neutron;
	public static Hadron lambda;
	public static Hadron sigma$p;
	public static Hadron sigma$0;
	public static Hadron sigma$n;
	public static Hadron xi$0;
	public static Hadron xi$n;
	public static Hadron charmed_lambda;

	public static Hadron delta$$1;
	public static Hadron delta$$2;
	public static Hadron delta$$3;
	public static Hadron delta$$4;
	public static Hadron sigma$s$$1;
	public static Hadron sigma$s$$2;
	public static Hadron sigma$s$$3;
	public static Hadron xi$s$$1;
	public static Hadron xi$s$$2;
	public static Hadron omega;

	public static Hadron pion$p;
	public static Hadron pion$n;
	


	public static List<Hadron> hadrons = new ArrayList<Hadron>();

	public static void init() throws IOException {
		quark_up = new ElementaryParticle("UP-Quark", "u", (float)2/3, 3, -2, (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/up_quark.png")), ParticleType.QUARK, 1, "up");
		quark_down = new ElementaryParticle("DOWN-Quark", "d", (float)-1/3, 7, -2, (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/down_quark.png")), ParticleType.QUARK, 1, "down");
		quark_charm = new ElementaryParticle("CHARM-Quark", "c", (float)2/3, 1200, -2, (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/charm_quark.png")), ParticleType.QUARK, 2, "charm");
		quark_strange = new ElementaryParticle("STRANGE-Quark", "s", (float)-1/3, 120, -2, (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/strange_quark.png")), ParticleType.QUARK, 2, "strange");
		quark_top = new ElementaryParticle("TOP-Quark", "t", (float)2/3, 174000, -2, (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/top_quark.png")), ParticleType.QUARK, 3, "top");
		quark_bottom = new ElementaryParticle("BOTTOM-Quark", "b", (float)-1/3, 4300, -2, (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/bottom_quark.png")), ParticleType.QUARK, 3, "bottom");

		ANTIquark_up = new ElementaryParticle("Anti-UP-Quark", "\\overline{u}", (float)-2/3, 3, -2, (float)1/2, ImageUtils.invert(ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/up_quark.png"))), ParticleType.QUARK, 1, "up");
		ANTIquark_down = new ElementaryParticle("Anti-DOWN-Quark", "\\overline{d}", (float)1/3, 7, -2, (float)1/2, ImageUtils.invert(ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/down_quark.png"))), ParticleType.QUARK, 1, "down");
		ANTIquark_charm = new ElementaryParticle("Anti-CHARM-Quark", "\\overline{c}", (float)-2/3, 1200, -2, (float)1/2, ImageUtils.invert(ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/charm_quark.png"))), ParticleType.QUARK, 2, "charm");
		ANTIquark_strange = new ElementaryParticle("Anti-STRANGE-Quark", "\\overline{s}", (float)1/3, 120, -2, (float)1/2, ImageUtils.invert(ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/strange_quark.png"))), ParticleType.QUARK, 2, "strange");
		ANTIquark_top = new ElementaryParticle("Anti-TOP-Quark", "\\overline{t}", (float)-2/3, 174000, -2, (float)1/2, ImageUtils.invert(ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/top_quark.png"))), ParticleType.QUARK, 3, "top");
		ANTIquark_bottom = new ElementaryParticle("Anti-BOTTOM-Quark", "\\overline{b}", (float)1/3, 4300, -2, (float)1/2, ImageUtils.invert(ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/bottom_quark.png"))), ParticleType.QUARK, 3, "bottom");

		elektron = new ElementaryParticle("Elektron", "e", -1, 0.510999F, -1, (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/elektron.png")), ParticleType.FERMION, 1, "elektron");
		myon = new ElementaryParticle("Myon", "\\mu", -1, 105.659F, 2.19703*Math.pow(10, -6), (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/myon.png")), ParticleType.FERMION, 1, "myon");
		tauon = new ElementaryParticle("Tauon", "\\tau", -1, 1776.99F, 2.91*Math.pow(10, -13), (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/tauon.png")), ParticleType.FERMION, 2, "tauon");
		elektron_neutrino = new ElementaryParticle("Elektron-Neutrino", "\\nu_e", 0, 0, -1, (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/elektron_neutrino.png")), ParticleType.FERMION, 2, "elektron neutrino");
		myon_neutrino = new ElementaryParticle("Myon-Neutrino", "\\nu_\\mu", 0, 0, -1, (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/myon_neutrino.png")), ParticleType.FERMION, 3, "myon neutrino");
		tau_neutrino = new ElementaryParticle("Tau-Neutrino", "\\nu_\\tau", 0, 0, -1, (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/tau_neutrino.png")), ParticleType.FERMION, 4, "tauon neutrino");

		ANTIelektron = new ElementaryParticle("Elektron", "e", 1, 0.510999F, -1, (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/elektron.png")), ParticleType.FERMION, 1, "elektron");
		ANTImyon = new ElementaryParticle("Myon", "\\mu", 1, 105.659F, 2.19703*Math.pow(10, -6), (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/myon.png")), ParticleType.FERMION, 1, "myon");
		ANTItauon = new ElementaryParticle("Tauon", "\\tau", 1, 1776.99F, 2.91*Math.pow(10, -13), (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/tauon.png")), ParticleType.FERMION, 2, "tauon");
		ANTIelektron_neutrino = new ElementaryParticle("Elektron-Neutrino", "\\nu_e", 0, 0, -1, (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/elektron_neutrino.png")), ParticleType.FERMION, 2, "elektron neutrino");
		ANTImyon_neutrino = new ElementaryParticle("Myon-Neutrino", "\\nu_\\mu", 0, 0, -1, (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/myon_neutrino.png")), ParticleType.FERMION, 3, "myon neutrino");
		ANTItau_neutrino = new ElementaryParticle("Tau-Neutrino", "\\nu_\\tau", 0, 0, -1, (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/tau_neutrino.png")), ParticleType.FERMION, 4, "tauon neutrino");


		proton = new Hadron("Proton", "p", 1, 938.272F, -1, (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/unknown.png")), ParticleType.BARYON_HALF, new ElementaryParticle[] {quark_up, quark_up, quark_down}, new ArrayList<Particle[]>());
		hadrons.add(proton);
		neutron = new Hadron("Neutron", "n", 0, 939.565F, 885.7, (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/unknown.png")), ParticleType.BARYON_HALF, new ElementaryParticle[] {quark_up, quark_down, quark_down}, new ArrayList<Particle[]>());
		hadrons.add(neutron);

		lambda = new Hadron("Lambda", "\\Lambda", 0, 1115.68F, 2.63*Math.pow(10, -10), (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/unknown.png")), ParticleType.BARYON_HALF, new ElementaryParticle[] {quark_up, quark_down, quark_strange}, new ArrayList<Particle[]>());
		hadrons.add(lambda);
		sigma$p = new Hadron("Sigma +", "\\Sigma^+", 1, 1189.37F, 8.02*Math.pow(10, -13), (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/unknown.png")), ParticleType.BARYON_HALF, new ElementaryParticle[] {quark_up, quark_up, quark_strange}, new ArrayList<Particle[]>());
		hadrons.add(sigma$p);
		sigma$0 = new Hadron("Sigma", "\\Sigma^0", 0, 1192.64F, 7.4*Math.pow(10, -20), (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/unknown.png")), ParticleType.BARYON_HALF, new ElementaryParticle[] {quark_up, quark_down, quark_strange}, new ArrayList<Particle[]>());
		hadrons.add(sigma$0);
		sigma$n = new Hadron("Sigma -", "\\Sigma^-", -1, 1197.45F, 1.48*Math.pow(10, -10), (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/unknown.png")), ParticleType.BARYON_HALF, new ElementaryParticle[] {quark_down, quark_down, quark_strange}, new ArrayList<Particle[]>());
		hadrons.add(sigma$n);
		xi$0 = new Hadron("Xi", "\\Xi^0", 0, 1314.8F, 2.9*Math.pow(10, -10), (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/unknown.png")), ParticleType.BARYON_HALF, new ElementaryParticle[] {quark_up, quark_strange, quark_strange}, new ArrayList<Particle[]>());
		hadrons.add(xi$0);
		xi$n = new Hadron("Xi -", "\\Xi^-", -1, 1321.3F, 1.64*Math.pow(10, -10), (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/unknown.png")), ParticleType.BARYON_HALF, new ElementaryParticle[] {quark_down, quark_strange, quark_strange}, new ArrayList<Particle[]>());
		hadrons.add(xi$n);
		charmed_lambda = new Hadron("Charmed Lambda", "\\Lambda_c^+", 1, 2286.5F, 2.00*Math.pow(10, -13), (float)1/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/unknown.png")), ParticleType.BARYON_HALF, new ElementaryParticle[] {quark_up, quark_down, quark_charm}, new ArrayList<Particle[]>());
		hadrons.add(charmed_lambda);

		delta$$1 = new Hadron("Delta", "\\Delta", 2, 1232F, 5.6*Math.pow(10, -24), (float)3/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/unknown.png")), ParticleType.BARYON_ONEHALF, new ElementaryParticle[] {quark_up, quark_up, quark_up}, new ArrayList<Particle[]>());
		hadrons.add(delta$$1);
		delta$$2 = new Hadron("Delta", "\\Delta", 1, 1232F, 5.6*Math.pow(10, -24), (float)3/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/unknown.png")), ParticleType.BARYON_ONEHALF, new ElementaryParticle[] {quark_up, quark_up, quark_down}, new ArrayList<Particle[]>());
		hadrons.add(delta$$2);
		delta$$3 = new Hadron("Delta", "\\Delta", 0, 1232F, 5.6*Math.pow(10, -24), (float)3/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/unknown.png")), ParticleType.BARYON_ONEHALF, new ElementaryParticle[] {quark_up, quark_down, quark_down}, new ArrayList<Particle[]>());
		hadrons.add(delta$$3);
		delta$$4 = new Hadron("Delta", "\\Delta", -1, 1232F, 5.6*Math.pow(10, -24), (float)3/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/unknown.png")), ParticleType.BARYON_ONEHALF, new ElementaryParticle[] {quark_down, quark_down, quark_down}, new ArrayList<Particle[]>());
		hadrons.add(delta$$4);
		sigma$s$$1 = new Hadron("Sigma *", "\\Sigma^*", 1, 1385F, 1.8*Math.pow(10, -23), (float)3/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/unknown.png")), ParticleType.BARYON_ONEHALF, new ElementaryParticle[] {quark_up, quark_up, quark_strange}, new ArrayList<Particle[]>());
		hadrons.add(sigma$s$$1);
		sigma$s$$2 = new Hadron("Sigma *", "\\Sigma^*", 0, 1385F, 1.8*Math.pow(10, -23), (float)3/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/unknown.png")), ParticleType.BARYON_ONEHALF, new ElementaryParticle[] {quark_up, quark_down, quark_strange}, new ArrayList<Particle[]>());
		hadrons.add(sigma$s$$2);
		sigma$s$$3 = new Hadron("Sigma *", "\\Sigma^*", -1, 1385F, 1.8*Math.pow(10, -23), (float)3/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/unknown.png")), ParticleType.BARYON_ONEHALF, new ElementaryParticle[] {quark_down, quark_down, quark_strange}, new ArrayList<Particle[]>());
		hadrons.add(sigma$s$$3);
		xi$s$$1 = new Hadron("Xi *", "\\Xi^*", 0, 1533F, 6.9*Math.pow(10, -23), (float)3/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/unknown.png")), ParticleType.BARYON_ONEHALF, new ElementaryParticle[] {quark_up, quark_strange, quark_strange}, new ArrayList<Particle[]>());
		hadrons.add(xi$s$$1);
		xi$s$$2 = new Hadron("Xi *", "\\Xi^*", -1, 1533F, 6.9*Math.pow(10, -23), (float)3/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/unknown.png")), ParticleType.BARYON_ONEHALF, new ElementaryParticle[] {quark_down, quark_strange, quark_strange}, new ArrayList<Particle[]>());
		hadrons.add(xi$s$$2);
		omega = new Hadron("Omega -", "\\Omega^-", -1, 1672, 8.2*Math.pow(10, -11), (float)3/2, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/unknown.png")), ParticleType.BARYON_ONEHALF, new ElementaryParticle[] {quark_strange, quark_strange, quark_strange}, new ArrayList<Particle[]>());
		hadrons.add(omega);

		pion$p = new Hadron("Pion +", "\\pi^+", 1, 139.570F, 2.60*Math.pow(10, -8), (float)0, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/unknown.png")), ParticleType.MESON_PSEUDOSCALAR, new ElementaryParticle[] {quark_up, ANTIquark_down}, new ArrayList<Particle[]>());
		hadrons.add(pion$p);
		pion$n = new Hadron("Pion -", "\\pi^-", -1, 139.570F, 2.60*Math.pow(10, -8), (float)0, ImageIO.read(_all.class.getResourceAsStream("/assets/teilchen/unknown.png")), ParticleType.MESON_PSEUDOSCALAR, new ElementaryParticle[] {quark_down, ANTIquark_up}, new ArrayList<Particle[]>());
		hadrons.add(pion$n);
	}
}
