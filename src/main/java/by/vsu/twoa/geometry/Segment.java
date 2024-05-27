package by.vsu.twoa.geometry;

public class Segment {
	private final Point vertex1;
	private final Point vertex2;

	public Segment(Point vertex1, Point vertex2) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
	}

	public Point getVertex1() {
		return vertex1;
	}

	public Point getVertex2() {
		return vertex2;
	}

	public double length() {
		return new Vector(vertex1, vertex2).length();
	}

	public Point middle() {
		return new Point((vertex1.getX() + vertex2.getX()) / 2, (vertex1.getY() + vertex2.getY()) / 2);
	}
}
