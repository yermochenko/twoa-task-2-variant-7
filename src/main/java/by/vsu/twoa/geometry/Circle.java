package by.vsu.twoa.geometry;

import java.util.Comparator;
import java.util.List;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.hypot;
import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.acos;
import static java.lang.Math.atan2;

public class Circle {
	private final Point center;
	private final double radius;

	public Circle(Point center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	public Point getCenter() {
		return center;
	}

	public double getRadius() {
		return radius;
	}

	public List<Point> intersect(Line line) {
		double alpha = atan2(line.getB(), line.getA());
		double gamma = -(line.getA() * center.getX() + line.getB() * center.getY() + line.getC()) / (radius * hypot(line.getA(), line.getB()));
		double delta = abs(gamma) - 1;
		List<Double> angles;
		if(abs(delta) < 0.0001) {
			angles = List.of(gamma > 0 ? alpha : alpha - PI);
		} else if(delta < 0) {
			angles = List.of(alpha - acos(gamma), alpha + acos(gamma));
		} else {
			angles = List.of();
		}
		return angles.stream()
				.map(phi -> new Point(center.getX() + radius * cos(phi), center.getY() + radius * sin(phi)))
				.sorted(Comparator.comparingDouble(Point::getX).thenComparingDouble(Point::getY))
				.toList();
	}
}
