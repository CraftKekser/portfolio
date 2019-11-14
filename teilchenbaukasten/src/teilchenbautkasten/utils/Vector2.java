package teilchenbautkasten.utils;

import java.awt.Point;

public class Vector2 {
	private int x;
	private int y;

	public Vector2(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public Vector2(Point a, Point b) {
		this.setX((int) (b.getX()-a.getX()));
		this.setY((int) (b.getY()-a.getY()));
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double length() {
		return Math.sqrt(Math.pow(this.getX(), 2)+Math.pow(this.getY(), 2));
	}

}
