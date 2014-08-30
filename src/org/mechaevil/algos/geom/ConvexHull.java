package org.mechaevil.algos.geom;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

public class ConvexHull {

	public List<Point> naive(List<Point> points) {
		if (points == null)
			return Collections.emptyList();
		if (points.size() <= 3)
			return points;
		boolean[] extremePoints = new boolean[points.size()];
		Arrays.fill(extremePoints, true);
		for (int i = 0, sz = points.size(); i < sz; i++) {
			if (extremePoints[i])
				for (int j = 0; j < sz; j++) {
					if (i != j && extremePoints[j]) {
						for (int k = 0; k < sz; k++) {
							if (k != i && k != j) {
								for (int l = 0; l < sz; l++) {
									if (extremePoints[l] && l != i && l != j
											&& l != k) {
										// Check if P[l] lies in triangle formed
										// by
										// P[i],P[j],P[k]

										Polygon p = new Polygon();
										p.addPoint(points.get(i).x,
												points.get(i).y);
										p.addPoint(points.get(j).x,
												points.get(j).y);
										p.addPoint(points.get(k).x,
												points.get(k).y);
										if (p.contains(points.get(l)))
											extremePoints[l] = false;
									}
								}
							}
						}
					}
				}
		}

		Point centerOfHull = null; // Arbitrary point inside the hull
		// Order?
		for (int i = 0; i < extremePoints.length; i++) {
			if (!extremePoints[i]) {
				centerOfHull = points.get(i);
				break;
			}
		}
		if (centerOfHull == null)
			return Collections.emptyList();
		List<Point> convexHull = new ArrayList<Point>();
		for (int i = 0; i < extremePoints.length; i++) {
			if (extremePoints[i]) {
				convexHull.add(points.get(i));
			}
		}
		Collections.sort(convexHull, new PointComp(centerOfHull));
		// or use a heap. still O(nlogn)
		return convexHull;
	}

	private class PointComp implements Comparator<Point> {

		private Point center;

		public PointComp(Point center) {
			this.center = center;
		}

		@Override
		public int compare(Point o1, Point o2) {
			double angle1 = Math.atan2(o1.y - center.y, o1.x - center.x);
			double angle2 = Math.atan2(o2.y - center.y, o2.x - center.x);
			if (angle1 < angle2)
				return 1;
			else if (angle2 > angle1)
				return -1;
			return 0;
		}
	}

	public List<Point> grahamScan(List<Point> points) {
		if (points == null)
			return Collections.emptyList();
		if (points.size() <= 3)
			return points;
		Point P = points.get(0);
		for (Point p : points)
			if (p.y >= P.y)
				P = p;
		Collections.sort(points, new PointComp(P));
		Point[] stack = new Point[points.size()];
		int top = 0;
		stack[top++] = points.get(0);
		stack[top++] = points.get(1);

		for (Point p : points.subList(2, points.size())) {
			while (top >= 2
					&& counterClockwise(stack[top - 2], stack[top - 1], p) >= 0) {
				--top;
			}
			stack[top++] = p;
		}
		return new ArrayList<Point>(Arrays.asList(stack).subList(0, top));
	}

	private int counterClockwise(Point p1, Point p2, Point p3) {
		return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
	}

	public static void main(String[] args) {
		Random r = new Random();
		int W = 500, H = 500;
		int offset = 20;
		List<Point> list = new ArrayList<Point>();
		for (int i = 0; i < 1000; i++)
			list.add(new Point(offset + r.nextInt(W - offset), offset
					+ r.nextInt(H - 2 * offset)));
		System.out.println(list);
		System.out.println(list.size());
		List<Point> convexHull = new ConvexHull().grahamScan(list);
		System.out.println(convexHull);
		System.out.println(convexHull.size());
		JFrame frame = new JFrame();
		frame.setSize(W + offset, H + offset);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new CHCanvas(list, convexHull));
		frame.setVisible(true);
	}

	private static class CHCanvas extends Canvas {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private List<Point> points;
		private Polygon convexHull;

		public CHCanvas(List<Point> points, List<Point> ch) {
			this.points = points;
			this.convexHull = new Polygon();
			for (Point p : ch)
				convexHull.addPoint(p.x, p.y);
		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			for (Point p : points)
				g.drawOval(p.x - 2, p.y - 2, 4, 4);
			g.drawPolygon(convexHull);
		}

	}
}
