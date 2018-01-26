package xhf.project.chartcore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.R.bool;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import xhf.project.chartcore.*;

import xhf.project.chartcorejni.*;
import xhf.project.datamanage.CPilotDataManage;
import xhf.project.datamanage.CShipStaticData;
import xhf.project.dynamictarget.CDynamicTargetControl;
import xhf.project.routemanage.CPlanRouteManage;

public class ChartCoreView extends View {

	private CGeoCanvasJv m_geoCanvas = null;
	private Context m_Context = null;

	private static String m_strSysRootPath = "/data/data/xhf.project.ardshippilotsys/";

	private GestureDetector m_gestureDetector = null;

	private float m_oldTwoFingerDis = 0;
	private Point m_ptFirstPoint = new Point();
	private Point m_ptSecondPoint = new Point();
	private float m_fCenterX = 0;
	private float m_fCenterY = 0;
	private float m_nFingerOpMode = 0; // 0-move map,1-zoom

	private Paint m_pen = null;
	private Paint m_textPaint = null;

	public static CHfVCFElectronicChart m_chartCore = null;
	public static CDynamicTargetControl m_dynaTargetControl = null;
	public static CTDPilotPresentation m_pilotPresentation = null;
	public static CPilotDataManage m_pilotDataManage = null;
	public static CPlanRouteManage m_planRouteManage = null;
	static {
		System.loadLibrary("elecchartcore");
	}

	public ChartCoreView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.m_Context = context;
		Init();
	}

	public ChartCoreView(Context context) {
		super(context);
		this.m_Context = context;
		// Init();

	}

	// int mScreenWidth,int mScreenHeight
	private void Init() {
		if (m_Context == null)
			return;

		setDrawingCacheEnabled(false);

		setSystemDirectory();

		DisplayMetrics dm = m_Context.getApplicationContext().getResources()
				.getDisplayMetrics();

		Rect rc = new Rect();
		rc.left = 0;
		rc.top = 0;
		rc.right = dm.widthPixels;
		rc.bottom = dm.heightPixels;

		m_geoCanvas = new CGeoCanvasJv(this);

		double fdpi = dm.densityDpi;
		// fdpi = 96;
		m_geoCanvas.Init(rc, fdpi, m_Context);

		m_pen = new Paint();
		m_pen.setAntiAlias(true);

		m_textPaint = new Paint();

		Typeface txtFont = Typeface.create(Typeface.SERIF, Typeface.NORMAL);
		m_textPaint.setTextAlign(Align.LEFT);
		m_textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		m_textPaint.setTypeface(txtFont);
		m_textPaint.setFakeBoldText(true);
		m_textPaint.setTextSize(18);
		//
		m_chartCore = new CHfVCFElectronicChart(m_geoCanvas);

		m_dynaTargetControl = new CDynamicTargetControl();

		m_pilotPresentation = new CTDPilotPresentation();

		m_chartCore.Initialize(m_strSysRootPath, m_strSysRootPath);
		m_chartCore.DrawVCFChart();

		m_planRouteManage = new CPlanRouteManage();

		m_pilotDataManage = new CPilotDataManage(m_Context);

		// m_pilotDataManage.open();

		CShipStaticData stData = new CShipStaticData();

		if (!m_pilotDataManage.LoadShipParameter(stData)) {
			stData.init();
		}

		m_dynaTargetControl.SetPilotShipParamterInfo(stData.GetName(),
				stData.GetCallNo(), stData.GetTxHead(), stData.GetTxTail(),
				stData.GetTxLeft(), stData.GetTxRight());

		// 创建手势识别
		m_gestureDetector = new GestureDetector(m_Context,
				new MyGestureDetector());

		// 设置触摸事件处理
		this.setOnTouchListener(myOnTouchEvent);

	}

	public static CHfVCFElectronicChart GetChartCore() {
		return m_chartCore;
	}

	public void setSystemDirectory() {

		File fileRoot = m_Context.getFilesDir();
		String strRootPath = fileRoot.getParent();
		strRootPath = strRootPath + "/";
		// "/data/data/xhf.project.ardshippilotsys/"
		m_strSysRootPath = strRootPath;

		CopyAssetsGeoDatabaseFile();

		CopyAssetsPreslibraryFile();

		CopyAssetsSystemConfigFile();

		CopyAssetsPilotDatabaseFile();

		CopyAssetsPlanRouteLibFile();
	}

	public void CopyAssetsGeoDatabaseFile() {

		String strGeoDatabaseFile = m_strSysRootPath;
		strGeoDatabaseFile += "zhgeodatabase";
		File file0 = new File(strGeoDatabaseFile);
		if (file0.exists())
			return;
		file0.mkdir();

		String assetsDir = "zhgeodatabase";

		String[] files;
		try {
			files = this.getResources().getAssets().list(assetsDir);
		} catch (IOException e1) {
			return;
		}

		for (int i = 0; i < files.length; i++) {
			try {
				String fileName = files[i];
				File outFile = new File(file0, fileName);
				if (outFile.exists())
					outFile.delete();

				InputStream in = null;
				if (0 != assetsDir.length())
					in = m_Context.getAssets().open(assetsDir + "/" + fileName);
				else
					in = m_Context.getAssets().open(fileName);

				OutputStream out = new FileOutputStream(outFile);

				// Transfer bytes from in to out
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}

				in.close();
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void CopyAssetsPreslibraryFile() {

		String strzhVCFPresLib = m_strSysRootPath;

		strzhVCFPresLib += "vcfpreslibrary";

		File file0 = new File(strzhVCFPresLib);

		if (file0.exists())
			return;

		file0.mkdir();

		String assetsDir = "zhpreslibrary";

		String[] files;
		try {
			files = this.getResources().getAssets().list(assetsDir);
		} catch (IOException e1) {
			return;
		}

		for (int i = 0; i < files.length; i++) {
			try {
				String fileName = files[i];
				File outFile = new File(file0, fileName);
				if (outFile.exists())
					outFile.delete();

				InputStream in = null;
				if (0 != assetsDir.length())
					in = m_Context.getAssets().open(assetsDir + "/" + fileName);
				else
					in = m_Context.getAssets().open(fileName);

				OutputStream out = new FileOutputStream(outFile);

				// Transfer bytes from in to out
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}

				in.close();
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void CopyAssetsSystemConfigFile() {

		String strzhSystemConfig = m_strSysRootPath;

		strzhSystemConfig += "zhsystemconfig";

		File file0 = new File(strzhSystemConfig);

		if (!file0.exists()) {
			file0.mkdir();
		}

		String assetsDir = "zhsystemconfig";

		String[] files;
		try {
			files = this.getResources().getAssets().list(assetsDir);
		} catch (IOException e1) {
			return;
		}

		for (int i = 0; i < files.length; i++) {
			try {
				String fileName = files[i];
				File outFile = new File(file0, fileName);
				if (outFile.exists())
					outFile.delete();

				InputStream in = null;
				if (0 != assetsDir.length())
					in = m_Context.getAssets().open(assetsDir + "/" + fileName);
				else
					in = m_Context.getAssets().open(fileName);

				OutputStream out = new FileOutputStream(outFile);

				// Transfer bytes from in to out
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}

				in.close();
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void CopyAssetsPilotDatabaseFile() {

		String strzhPilotDatabase = m_strSysRootPath;

		strzhPilotDatabase += "zhpilotdatabase";

		File file = new File(strzhPilotDatabase);

		if (!file.exists()) {
			file.mkdir();
		}

	}

	public void CopyAssetsPlanRouteLibFile() {

		String strzhPilotDatabase = m_strSysRootPath;

		strzhPilotDatabase += "planroutelib";

		File file = new File(strzhPilotDatabase);

		if (!file.exists()) {
			file.mkdir();
		}

	}

	public static String GetSystemDataFileDirectory() {

		return m_strSysRootPath;
	}

	protected void onDraw(Canvas canvas) {

		if (m_geoCanvas != null)
			m_geoCanvas.ShowCanvas(canvas);

		onDrawSeaMeasure(canvas);

		if (m_pilotPresentation != null)
			m_pilotPresentation.DrawWKPlanRouteScr(m_planRouteManage, canvas);

	}

	private void onDrawSeaMeasure(Canvas canvas) {
		emCHART_OPERATION_MODE nOpMode = m_chartCore.GetMouseOperationMode();
		if (nOpMode == emCHART_OPERATION_MODE.CHART_HHCL) {
			int x1 = m_chartCore.GetSeaMeasurePoint1X();
			int y1 = m_chartCore.GetSeaMeasurePoint1Y();
			int x2 = m_chartCore.GetSeaMeasurePoint2X();
			int y2 = m_chartCore.GetSeaMeasurePoint2Y();
			if (x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0)
				return;

			float fR = FloatMath.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2)
					* (y1 - y2));

			if (fR <= 2)
				return;

			int red = 0;
			int green = 128;
			int blue = 0;

			m_pen.setStyle(Paint.Style.STROKE);
			m_pen.setStrokeWidth(2);
			m_pen.setColor(Color.rgb(red, green, blue));

			canvas.drawCircle(x1, y1, fR, m_pen);
			canvas.drawLine(x1, y1, x2, y2, m_pen);

			float fDir = m_chartCore.GetSeaMeasureDir();
			float fDis = m_chartCore.GetSeaMeasureDis();

			if (fDir < 360 && fDis >= 0 && fDis < 99999) {
				String strText = "";

				if (fDis < 1) {
					strText = String.format("%.0fm,", fDis * 1852.0);
					strText = strText + String.format("%.1f", fDir);

				} else {
					strText = String.format("%.03fnm,", fDis);
					strText = strText + String.format("%.1f", fDir);
				}
				canvas.drawText(strText, x2, y2, m_textPaint);
			}
		}
	}

	public void GeoCanvasUpdateMsg(emCHART_CANVAS_FLUSHTYPE emCanvasUpdate) {
		if (emCanvasUpdate == emCHART_CANVAS_FLUSHTYPE.CANVAS_CRINIT) {
			if (m_pilotPresentation != null) {
				m_pilotPresentation.xhfDrawDynamicTarget(m_dynaTargetControl,
						m_chartCore, m_geoCanvas);

				m_pilotPresentation.DrawWorkPlanRoute(m_planRouteManage,
						m_chartCore, m_geoCanvas);

				m_pilotPresentation.DrawMoniPlanRoute(m_planRouteManage,
						m_chartCore, m_geoCanvas);
			}
		} else if (emCanvasUpdate == emCHART_CANVAS_FLUSHTYPE.CANVAS_REDRAW) {
			if (m_pilotPresentation != null) {
				m_pilotPresentation.xhfDrawDynamicTarget(m_dynaTargetControl,
						m_chartCore, m_geoCanvas);

				m_pilotPresentation.DrawWorkPlanRoute(m_planRouteManage,
						m_chartCore, m_geoCanvas);

				m_pilotPresentation.DrawMoniPlanRoute(m_planRouteManage,
						m_chartCore, m_geoCanvas);
			}

		} else if (emCanvasUpdate == emCHART_CANVAS_FLUSHTYPE.CANVAS_FLUSH) {
			if (m_pilotPresentation != null) {
				m_pilotPresentation.xhfDrawDynamicTarget(m_dynaTargetControl,
						m_chartCore, m_geoCanvas);

				m_pilotPresentation.DrawWorkPlanRoute(m_planRouteManage,
						m_chartCore, m_geoCanvas);

				m_pilotPresentation.DrawMoniPlanRoute(m_planRouteManage,
						m_chartCore, m_geoCanvas);
			}

		} else if (emCanvasUpdate == emCHART_CANVAS_FLUSHTYPE.CANVAS_SCREEN) {

		}
		invalidate();
	}

	/*
	 * 处理触摸操作：移动图、双指缩放图
	 * 
	 * m_nFingerOpMode用于当处理双指缩放后则为缩放模式，否则为移动模式，防止重复绘图导致性能。
	 * MotionEvent.ACTION_MASK 作用防止其他无用事件
	 */
	private OnTouchListener myOnTouchEvent = new OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {

			int iAction = event.getAction();
			switch (iAction & MotionEvent.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN: {
				int x = (int) event.getX();
				int y = (int) event.getY();
				m_nFingerOpMode = 0;
				m_ptFirstPoint.x = x;
				m_ptFirstPoint.y = y;

				int ret = m_planRouteManage.OnLButtonDown(m_ptFirstPoint);

				if (ret != 0) {
					m_chartCore.OnTouchDown(x, y);

					if (ret == 2) {

						/*
						 * UIAlertView *alertDialog = [[UIAlertView alloc]
						 * initWithTitle: @"提示" message:@"擦除航线" delegate: self
						 * cancelButtonTitle: @"确定" otherButtonTitles: @"取消",
						 * nil];
						 * 
						 * [alertDialog show];
						 */
					}

				}
				Log.v("OnTouch", "ACTION_DOWN");
			}
				break;
			case MotionEvent.ACTION_MOVE: {
				int x = (int) event.getX();
				int y = (int) event.getY();

				m_ptSecondPoint.x = x;
				m_ptSecondPoint.y = y;

				int ret = m_planRouteManage.OnMouseMove(m_ptSecondPoint);
				if (ret != 0) {

					float fdiffX = m_ptSecondPoint.x - m_ptFirstPoint.x;
					float fdiffY = m_ptSecondPoint.y - m_ptFirstPoint.y;

					float fdis = FloatMath.sqrt(fdiffX * fdiffX + fdiffY
							* fdiffY);
					if (fdis > 10) {
						m_chartCore.OnTouchMove(x, y);
					}
				}
			}
				break;
			case MotionEvent.ACTION_UP: {
				int x = (int) event.getX();
				int y = (int) event.getY();
				m_ptSecondPoint.x = x;
				m_ptSecondPoint.y = y;
				int ret = m_planRouteManage.OnLButtonUp(m_ptSecondPoint);
				if (ret != 0) {
					float fdiffX = m_ptSecondPoint.x - m_ptFirstPoint.x;
					float fdiffY = m_ptSecondPoint.y - m_ptFirstPoint.y;
					float fdis = FloatMath.sqrt(fdiffX * fdiffX + fdiffY
							* fdiffY);
					if (fdis > 10 && m_nFingerOpMode == 0) {
						// Log.v("OnTouch","ACTION_UP");
						m_chartCore.OnTouchUp(x, y);
					}
				}
			}
				break;
			case MotionEvent.ACTION_POINTER_DOWN: {
				float fdiffX = event.getX(0) - event.getX(1);
				float fdiffY = event.getY(0) - event.getY(1);

				m_fCenterX = (event.getX(0) + event.getX(1)) / 2;
				m_fCenterY = (event.getY(0) + event.getY(1)) / 2;

				m_oldTwoFingerDis = FloatMath.sqrt(fdiffX * fdiffX + fdiffY
						* fdiffY);

				if (m_oldTwoFingerDis > 10f) {
					m_nFingerOpMode = 1;
				}

			}
				break;
			case MotionEvent.ACTION_POINTER_UP: {

				if (m_nFingerOpMode == 1) {

					float fdiffX = event.getX(0) - event.getX(1);
					float fdiffY = event.getY(0) - event.getY(1);
					float fNewDis = FloatMath.sqrt(fdiffX * fdiffX + fdiffY
							* fdiffY);

					if (m_oldTwoFingerDis > 10 && fNewDis > 4) {

						float fChangeScale = fNewDis / m_oldTwoFingerDis;

						String str = Float.toString(fChangeScale);

						Log.v("OnTouch", str);

						m_chartCore.twoFingerPinchChart(m_fCenterX, m_fCenterY,
								fChangeScale);
					}

				}
			}
				break;
			default:
				break;
			}
			return m_gestureDetector.onTouchEvent(event);
		}
	};

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		return super.onTouchEvent(event);
	}

	private class MyGestureDetector extends
			GestureDetector.SimpleOnGestureListener {

		// 用户（轻触触摸屏后）松开，由一个1个MotionEvent ACTION_UP触发
		@Override
		public boolean onSingleTapUp(MotionEvent event) {
			return false;// super.onSingleTapUp(event);
		}

		@Override
		public boolean onSingleTapConfirmed(MotionEvent event) {

			return false;// super.onSingleTapConfirmed(event);
		}

		/*
		 * 用户轻触触摸屏，尚未松开或拖动，由一个1个MotionEvent ACTION_DOWN触发
		 * 注意和onDown()的区别，强调的是没有松开或者拖动的状态
		 */
		@Override
		public void onShowPress(MotionEvent event) {

		}

		// 用户长按触摸屏，由多个MotionEvent ACTION_DOWN触发
		@Override
		public void onLongPress(MotionEvent event) {

		}

		// 用户轻触触摸屏，由1个MotionEvent ACTION_DOWN触发
		@Override
		public boolean onDown(MotionEvent event) {

			// 如果此处返回false或调用超类，则无ACTION_UP事件
			return true;// super.onDown(event);
		}

		// 双击的第二下Touch down时触发
		@Override
		public boolean onDoubleTap(MotionEvent event) {

			Log.v("MyGestureDetector", "SingleTapUp");

			int x = (int) event.getX();
			int y = (int) event.getY();
			m_chartCore.OneFingerDoubleTapZoomin(x, y);
			return false;// super.onDoubleTap(event);
		}

		// 双击的第二下Touch down和up都会触发，可用e.getAction()区分
		public boolean onDoubleTapEvent(MotionEvent event) {

			return false;// super.onDoubleTapEvent(event);
		}

		// 用户按下触摸屏，并拖动，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE触发
		@Override
		public boolean onScroll(MotionEvent event1, MotionEvent event2,
				float dx, float dy) {

			return false;
		}

		// 用户按下触摸屏、快速移动后松开，由1个MotionEvent ACTION_DOWN, 多个ACTION_MOVE,
		// 1个ACTION_UP触发
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {

			return false;
		}
	}

	public void ChartZoomin() {
		m_chartCore.ChartZoomin();

	}

	public void ChartZoomout() {
		m_chartCore.ChartZoomout();
	}

	public void ChartMeasure() {
		emCHART_OPERATION_MODE nOpMode = m_chartCore.GetMouseOperationMode();
		if (nOpMode != emCHART_OPERATION_MODE.CHART_NODOING)
			m_chartCore
					.SetMouseOperationMode(emCHART_OPERATION_MODE.CHART_NODOING);
		else
			m_chartCore
					.SetMouseOperationMode(emCHART_OPERATION_MODE.CHART_HHCL);
	}

	public void DynaTargetShowTimerFlush() {

		if (CDynamicTargetControl.g_nSystemRunMode == 0)
			m_dynaTargetControl.xhfTargetTimeoutMonitor();

		m_pilotPresentation.xhfDynaTargetPresFlushTimer(m_dynaTargetControl,
				m_chartCore);
	}

	public boolean PilotShipDataChangeUpdate() {

		if (m_dynaTargetControl.GetIsPilotShipChanage()) {
			m_dynaTargetControl.SetIsPilotShipChanage(false);

			return true;
		} else
			return false;
	}

	public boolean AisTargetDataChangeUpdate() {

		if (m_dynaTargetControl.GetIsAisTargetChanage()) {
			m_dynaTargetControl.SetIsAisTargetChanage(false);

			return true;
		} else
			return false;
	}

}
