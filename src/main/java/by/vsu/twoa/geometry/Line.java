package by.vsu.twoa.geometry;

import java.util.Optional;

public class Line {
	private final double a;
	private final double b;
	private final double c;

	public Line(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public Line(Point a, Point b) {
		this(
			b.getY() - a.getY(),
			a.getX() - b.getX(),
			a.getY() * b.getX() - a.getX() * b.getY()
		);
	}

	public Line(Vector norm, Point p) {
		this(
			norm.getX(),
			norm.getY(),
			-(norm.getX() * p.getX() + norm.getY() * p.getY())
		);
	}

	public double getA() {
		return a;
	}

	public double getB() {
		return b;
	}

	public double getC() {
		return c;
	}

	public static Optional<Point> intersection(Line line1, Line line2) {
		double d = line1.getA() * line2.getB() - line1.getB() * line2.getA();
		if(Math.abs(d) < 0.0001) return Optional.empty();
		return Optional.of(new Point(
			(line1.getB() * line2.getC() - line1.getC() * line2.getB()) / d,
			(line1.getC() * line2.getA() - line1.getA() * line2.getC()) / d
		));
	}
}
