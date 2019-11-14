package de.craftkekser.wcp;

public class Scaling {
	
	private int width;
	private int height;

	public Scaling(int width, int height) {
		this.setWidth(width);
		this.setHeight(height);
	}

	public static Scaling valueOf(String s) {
		// String aufraeumen
		s = s.replace(" ", ""); // Leerzeichen entfernen
		s = s.toLowerCase(); // Nur Kleinbuchstaben ('x' als Trennzeichen)
		// String spalten und umwandeln
		String[] values = s.split("x"); // String an jeder Stelle mit einem 'x' aufteilen
		if(values.length==2) { // Es muessen zwei Werte (Hoehe und Breite) gegeben sein
			// Jeweils zu einem Integer umwandeln
			try {
				int w = Integer.parseInt(values[0]); // BxH => Breite an erster Stelle
				int h = Integer.parseInt(values[1]); // Hoehe an zweiter Stelle
				// Scaling instanziieren und zurueckgeben
				return new Scaling(w, h);
			}catch(NumberFormatException e) {
				// Sollte einer der Werte keine Zahl beinhalten, wird diese Exception geworfen
				// Hier wird dann einfach null zurueckgegeben
				return null;
			}
			
		}else{
			// Ungueltiges Array -> Parameter nicht richtig angegeben
			return null;
		}
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}
