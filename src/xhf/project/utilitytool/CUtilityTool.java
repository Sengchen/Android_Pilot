package xhf.project.utilitytool;

import java.io.File;

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

	public static long RGBToLong(int r, int g, int b) {

		return ((long) (((byte) (r) | ((long) ((byte) (g)) << 8)) | (((long) (byte) (b)) << 16)));

	}

	public static void GeoLongLatToDMS(double fxy, CLatLongDMS dms) {

		dms.m_nDegree = (int) (fxy);
		dms.m_nMinute = (int) ((fxy - dms.m_nDegree) * 60.0);
		dms.m_fSecond = (fxy - dms.m_nDegree - dms.m_nMinute / 60.0) * 3600.0;
	}

	public static void GeoLatLongToDM(double fxy, CLatLongDM dm) {

		dm.m_nDegree = (int) (fxy);
		dm.m_fMinute = (fxy - dm.m_nDegree) * 60;

	}

	public static int bytesToInt(byte[] bytes) {

		int addr = bytes[0] & 0xFF;

		addr |= ((bytes[1] << 8) & 0xFF00);

		addr |= ((bytes[2] << 16) & 0xFF0000);

		addr |= ((bytes[3] << 24) & 0xFF000000);

		return addr;

	}

	public static boolean DeleteFile(String strFilePath) {

		File file = new File(strFilePath);

		if (!file.exists())
			return false;
		if (file.isFile()) { // 判断是否是文件
			return file.delete();
		} else
			return false;

	}

	public static boolean IsExistFile(String strFilePath) {

		File file = new File(strFilePath);

		if (file.exists() && file.isFile())
			return true;
		else
			return false;
	}

	public static void DeleteDirectory(String strFilePath) {

		File file = new File(strFilePath);
		DeleteFile(file);

	}

	private static void DeleteFile(File file) {

		if (!file.exists())
			return;

		if (file.isFile()) { // 判断是否是文件
			file.delete(); // delete()方法 你应该知道 是删除的意思;

		} else if (file.isDirectory()) { // 判断是否是文件
			File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
			for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
				DeleteFile(files[i]); // 把每个文件 用这个方法进行迭代
			}
		}
		file.delete();
	}

}
