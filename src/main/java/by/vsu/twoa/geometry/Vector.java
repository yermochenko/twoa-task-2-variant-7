package by.vsu.twoa.geometry;

import java.util.Optional;

import static java.lang.Math.abs;
import static java.lang.Math.hypot;
import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.acos;

public class Vector extends Point {
	public Vector(double x, double y) {
		super(x, y);
	}

	public Vector(Point start, Point finish) {
		this(finish.getX() - start.getX(), finish.getY() - start.getY());
	}

	public double length() {
		return hypot(getX(), getY());
	}

	public Vector multiply(double scalar) {
		return new Vector(getX() * scalar, getY() * scalar);
	}

	public Vector rotate(double angle) {
		return new Vector(getX() * cos(angle) - getY() * sin(angle), getX() * sin(angle) + getY() * cos(angle));
	}

	public Point put(Point point) {
		return new Point(getX() + point.getX(), getY() + point.getY());
	}

	public static double scalarMultiply(Vector v1, Vector v2) {
		return v1.getX() * v2.getX() + v1.getY() * v2.getY();
	}

	public static Optional<Double> angle(Vector v1, Vector v2) {
		double d1 = v1.length();
		double d2 = v2.length();
		if(abs(d1) < 0.0001 || abs(d2) < 0.0001) return Optional.empty();
		return Optional.of(acos(scalarMultiply(v1, v2) / (d1 * d2)));
	}
}
