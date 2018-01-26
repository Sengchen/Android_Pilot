package xhf.project.chartcore;

import android.graphics.Point;

public class CUtilityTool {

	public static double CalcLength(float x1, float y1, float x2, float y2) {

		return Math.sqrt((double) ((x1 - x2) * (x1 - x2) + (y1 - y2)
				* (y1 - y2)));
	}

	public static double CalcLength(int x1, int y1, int x2, int y2) {

		return Math.sqrt((double) ((x1 - x2) * (x1 - x2) + (y1 - y2)
				* (y1 - y2)));
	}

	public static double CalcLength(Point point1, Point point2) {

		return Math.sqrt((double) ((point1.x - point2.x)
				* (point1.x - point2.x) + (point1.y - point2.y)
				* (point1.y - point2.y)));
	}

	public static double CalcAngle(float x1, float y1, float x2, float y2) {

		return Math.atan2((double) (y2 - y1), (double) (x2 - x1));
	}
}
