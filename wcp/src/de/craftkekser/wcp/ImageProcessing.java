package de.craftkekser.wcp;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JTextArea;

public class ImageProcessing {

	public static BufferedImage[] scale(BufferedImage source, Scaling thumbnail, Scaling slide, JTextArea log) {
		
		// Einzelne Bild-Versionen skalieren
		// Das Original wird hier ausgelassen, da es lediglich kopiert werden soll
		log.append("   > Thumbnail (" + thumbnail.getWidth() + "x" + thumbnail.getHeight() + ")" + "\r\n");
		BufferedImage thumbnailImg = scaleInstance(source, thumbnail, log);
		log.append("   > Slide (" + slide.getWidth() + "x" + slide.getHeight() + ")" + "\r\n");
		BufferedImage slideImg = scaleInstance(source, slide, log);
		
		// BufferedImage-Array zurueckgeben
		return new BufferedImage[] {thumbnailImg, slideImg};
	}
	
	public static BufferedImage scaleInstance(BufferedImage source, Scaling container, JTextArea log) {
		
		// Neues BufferedImage erstellen, das das vorgegebene Seitenverhaeltnis hat
		BufferedImage newImage = new BufferedImage(container.getWidth(), container.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		// Breite und Hoehe des gegebenen BufferedImage zwischenspeichern (als double, fuer spaetere Berechnungen)
		double wi = source.getWidth();
		double hi = source.getHeight();
		
		// Breite und Hoehe der Einfachheit halber auch zwischenspeichern, diesmal als Integer, da kaum Berechnungen
		int w = container.getWidth();
		int h = container.getHeight();
		
		// Variablen fuer Position im Container, fuer spaeter benoetigt
		int x = 0;
		int y = 0;
		
		// Seitenverhaeltnisse berechnen
		double rat = container.getWidth()/container.getHeight(); // fuer Container-Bild
		double rati = wi/hi; // fuer gegebenes Bild
		log.append("     > Ratio: " + rat + " // " + rati + "\r\n");
		
		// Gegebenes BufferedImage je nach Ausrichtung entsprechend skaliert auf newImage zeichnen
		Graphics2D g = newImage.createGraphics();
		
		// Hintergrund mit Transparenz fuellen
		g.setBackground(new Color(255, 255, 255, 0));
		g.clearRect(0, 0, w, h);
		
		if(rati==rat) {  // Seitenverhaeltnisse sind gleich, keine Ausrichtung noetig
			log.append("     > Identical" + "\r\n");
			g.drawImage(source.getScaledInstance(w, h, 60), x, y, w, h, null); // Eingebaute getScaledInstance-Methode, da so bessere Skalierung
		}else if(rati < rat) { // Hochformat: Hoehe uebernehmen, Breite nachberechnen. Mittig ausrichten
			log.append("     > Higher than original [Portrait]" + "\r\n");
			y = 0;
			hi = h;
			wi = hi * rati; // Breite neu berechnen, da skaliert wurde
			x = (int) ((w/2)-(wi/2)); // x-Position so berechnen, dass mittig Ausgerichtet
			g.drawImage(source.getScaledInstance((int)wi, (int)hi, 60), x, y, (int)wi, (int)hi, null);
		}else if(rati > rat) { // Querformat: Breite uebernehmen, Hoehe nachberechnen. Mittig ausrichten
			log.append("     > Wider that original [Landscape]" + "\r\n");
			x = 0;
			wi = w;
			hi = wi / rati;
			y = (int) ((h/2)-(hi/2));
			g.drawImage(source.getScaledInstance((int)wi, (int)hi, 60), x, y, (int)wi, (int)hi, null);
		}
		
		// Graphics2D-Objekt "schlieﬂen"
		g.dispose();
		
		// Neues BufferedImage zurueckgeben
		return newImage;
	}
	
}
