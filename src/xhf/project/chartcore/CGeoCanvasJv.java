package xhf.project.chartcore;

import xhf.project.chartcore.ChartCoreView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.FloatMath;
import android.util.Log;
import xhf.project.chartcorejni.*;

public class CGeoCanvasJv extends CHfVCFGeoCanvas {

	private Path m_path = null;
	private Paint m_pen = null;
	private Paint m_brush = null;
	private Paint m_symPaint = null;
	private Paint m_soundPaint = null;
	private Paint m_charttextPaint = null;
	private Canvas m_backCanvas = null;
	private Canvas m_frontCanvas = null;
	private Bitmap m_backBitmap = null;
	private Bitmap m_frontBitmap = null;
	private PathEffect m_effects = null;

	private ChartCoreView m_chartView = null;

	private Typeface m_chartSym1Font = null;
	private Typeface m_chartSym2Font = null;
	private Typeface m_chartTextFont = null;
	private Typeface m_soundPointFont = null;
	//
	private Rect m_ScreenRect = null;

	// ///
	public CGeoCanvasJv() {

	}

	public CGeoCanvasJv(ChartCoreView view) {
		m_chartView = view;
	}

	public Canvas GetFrontCanvas() {
		return m_frontCanvas;
	}

	public void Init(Rect scrRect, double fdpi, Context context) {
		if (context == null)
			return;

		CRect viewRect = new CRect();
		viewRect.setLeft(scrRect.left);
		viewRect.setTop(scrRect.top);
		viewRect.setRight(scrRect.right);
		viewRect.setBottom(scrRect.bottom);

		SetViewScreenRect(viewRect);

		SetScreenDpi((float) fdpi);

		double fxPixelDPM, fyPixelDPM;

		fxPixelDPM = 25.4 / fdpi;

		fyPixelDPM = fxPixelDPM;

		SetXYPixelDPM(fxPixelDPM, fyPixelDPM);

		m_ScreenRect = scrRect;

		m_path = new Path();
		m_pen = new Paint();
		m_brush = new Paint();

		m_pen.setAntiAlias(true);

		m_backBitmap = Bitmap.createBitmap(scrRect.width(), scrRect.height(),
				Config.ARGB_8888);
		m_backCanvas = new Canvas();
		m_backCanvas.setBitmap(m_backBitmap);
		m_backCanvas.drawColor(Color.BLACK);

		m_frontBitmap = Bitmap.createBitmap(scrRect.width(), scrRect.height(),
				Config.ARGB_8888);
		m_frontCanvas = new Canvas();
		m_frontCanvas.setBitmap(m_frontBitmap);
		m_frontCanvas.drawColor(Color.BLACK);

		m_symPaint = new Paint();
		m_chartSym1Font = Typeface.createFromAsset(context.getAssets(),
				"fonts/vcfSymOne.ttf");
		m_chartSym2Font = Typeface.createFromAsset(context.getAssets(),
				"fonts/vcfSymTwo.ttf");
		m_soundPointFont = Typeface
				.create(Typeface.SANS_SERIF, Typeface.NORMAL);
		m_soundPaint = new Paint();
		m_soundPaint.setTextSize(8);
		m_soundPaint.setTextAlign(Align.LEFT);
		m_soundPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		m_soundPaint.setTypeface(m_soundPointFont);

		m_chartTextFont = Typeface.create(Typeface.SERIF, Typeface.NORMAL);
		m_charttextPaint = new Paint();
		m_charttextPaint.setTextAlign(Align.CENTER);
		m_charttextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		m_charttextPaint.setTypeface(m_chartTextFont);

		m_effects = new DashPathEffect(new float[] { 5, 5, 5, 5 }, 1);

	}

	public void ShowCanvas(Canvas canvas) {
		if (m_frontCanvas == null || m_frontBitmap == null)
			return;
		canvas.drawBitmap(m_frontBitmap, m_ScreenRect.left, m_ScreenRect.top,
				m_pen);
	}

	// 后台新绘制
	@Override
	public void newCanvasFlush() {
		if (m_frontCanvas == null || m_frontBitmap == null)
			return;

		m_frontCanvas.drawBitmap(m_backBitmap, m_ScreenRect.left,
				m_ScreenRect.top, m_pen);

		if (m_chartView != null) {
			m_chartView
					.GeoCanvasUpdateMsg(emCHART_CANVAS_FLUSHTYPE.CANVAS_CRINIT);
		}
	}

	// 用后台缓冲更新前台缓冲
	@Override
	public void oldCanvasFlush() {
		if (m_frontCanvas == null || m_backBitmap == null)
			return;
		m_frontCanvas.drawBitmap(m_backBitmap, m_ScreenRect.left,
				m_ScreenRect.top, m_pen);

		if (m_chartView != null) {
			m_chartView
					.GeoCanvasUpdateMsg(emCHART_CANVAS_FLUSHTYPE.CANVAS_FLUSH);
		}

	}

	// 刷新屏幕
	@Override
	public void scrCanvasFlush() {

		if (m_chartView != null) {
			m_chartView
					.GeoCanvasUpdateMsg(emCHART_CANVAS_FLUSHTYPE.CANVAS_SCREEN);
		}
	}

	@Override
	public void DrawCanvasBackground(int nColor) {
		int red = (nColor & 0xff0000) >> 16;
		int green = (nColor & 0x00ff00) >> 8;
		int blue = (nColor & 0x0000ff);
		m_backCanvas.drawColor(Color.rgb(red, green, blue));
	}

	@Override
	public void DrawGeoDataSetBackground(int x1, int y1, int x2, int y2,
			int x3, int y3, int x4, int y4, int bkColor) {

		int red = (bkColor & 0xff0000) >> 16;
		int green = (bkColor & 0x00ff00) >> 8;
		int blue = (bkColor & 0x0000ff);

		m_brush.setColor(Color.rgb(red, green, blue));
		m_brush.setStyle(Paint.Style.FILL);

		Rect rc = new Rect();
		rc.left = x1;
		rc.top = y1;
		rc.right = x3;
		rc.bottom = y3;
		m_backCanvas.drawRect(rc, m_brush);

		m_brush.reset();

	}

	//
	@Override
	public void DrawVCFPoint_TrueTypeSymbol(float x, float y,
			emVCF_SYMBOL_FONT emFont, int nSymNa, float nWidth, float nHeight,
			int symColor, double fRotAngle, float fdtx, float fdty) {

		int red = (symColor & 0x0000ff);
		int green = (symColor & 0x00ff00) >> 8;
		int blue = (symColor & 0xff0000) >> 16;

		float nsize = nWidth;// /3*2;
		if (nsize <= 0)
			nsize = nHeight;// /3*2;

		m_symPaint.setColor(Color.rgb(red, green, blue));

		m_symPaint.setTextSize(nsize);
		m_symPaint.setTextAlign(Align.LEFT);
		m_symPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		m_symPaint.setFakeBoldText(true);

		if (emFont == emVCF_SYMBOL_FONT.VCFSYMBOL_ONE)
			m_symPaint.setTypeface(m_chartSym1Font);
		else if (emFont == emVCF_SYMBOL_FONT.VCFSYMBOL_TWO)
			m_symPaint.setTypeface(m_chartSym2Font);
		else
			m_symPaint.setTypeface(m_chartSym1Font);

		float fx = (float) x;
		float fy = (float) y;

		if (fRotAngle != 0)
			m_backCanvas.rotate(-(float) fRotAngle, fx, fy);

		char szSymna[] = new char[2];
		szSymna[0] = (char) nSymNa;
		szSymna[1] = 0;

		String strSym = new String(szSymna);
		m_backCanvas.drawText(strSym, fx, fy, m_symPaint);

		if (fRotAngle != 0)
			m_backCanvas.rotate((float) fRotAngle, fx, fy);

	}

	@Override
	public void DrawVCFPoint_EllipseSymbol(int x, int y, int nFillColor,
			int nPenColor) {

		int red = (nPenColor & 0x0000ff);
		int green = (nPenColor & 0x00ff00) >> 8;
		int blue = (nPenColor & 0xff0000) >> 16;

		m_pen.setStyle(Paint.Style.FILL_AND_STROKE);
		m_pen.setStrokeWidth(1);
		m_pen.setColor(Color.rgb(red, green, blue));

		red = (nFillColor & 0xff0000) >> 16;
		green = (nFillColor & 0x00ff00) >> 8;
		blue = (nFillColor & 0x0000ff);
		m_brush.setStyle(Paint.Style.FILL);
		m_brush.setColor(Color.rgb(red, green, blue));

		float fx = (float) x;
		float fy = (float) y;

		// RectF oval = new RectF(fx-2,fy-2,fx+2,fy+2);
		// m_backCanvas.drawOval(oval, m_pen);

		RectF oval1 = new RectF(fx - 1, fy - 1, fx + 1, fy + 1);
		m_backCanvas.drawOval(oval1, m_brush);
		m_brush.reset();

	}

	@Override
	public void DrawVCFPoint_SoundValue(int x, int y, double fValue,
			int nTideColor) {

		int red = (nTideColor & 0x0000ff);
		int green = (nTideColor & 0x00ff00) >> 8;
		int blue = (nTideColor & 0xff0000) >> 16;

		m_soundPaint.setColor(Color.rgb(red, green, blue));

		float fx = (float) x;
		float fy = (float) y;

		float fTideValue = (float) fValue;
		int nInt = (int) (fTideValue);

		String strInt = Integer.toString(nInt);
		m_backCanvas.drawText(strInt, fx, fy, m_soundPaint);

		float fFloat = fTideValue - nInt;
		fFloat = fFloat * 10;
		if (fFloat != 0) {
			String strDot = String.format("%1$.0f", fFloat);

			FontMetrics fm = m_soundPaint.getFontMetrics();

			fx += getTextWidth(m_soundPaint, strInt);

			fy += fm.bottom;// Math.abs((int)(fm.descent -fm.ascent));

			m_backCanvas.drawText(strDot, fx, fy, m_soundPaint);
		}
	}

	@Override
	public void DrawVCFPoint_AnncovpText(int x, int y, String szText,
			int nsize, int nTextColor) {

		int red = (nTextColor & 0x0000ff);
		int green = (nTextColor & 0x00ff00) >> 8;
		int blue = (nTextColor & 0xff0000) >> 16;

		float fsize = (float) nsize;// /3*2;
		m_charttextPaint.setColor(Color.rgb(red, green, blue));
		m_charttextPaint.setTextSize(fsize);

		float fx = (float) x;
		float fy = (float) y;

		m_backCanvas.drawText(szText, fx, fy, m_charttextPaint);

	}

	@Override
	public void SetVCFLine_BeginPanit(float nLinwWidth, int nLineStyle,
			int nLineColor) {

		int red = (nLineColor & 0x0000ff);
		int green = (nLineColor & 0x00ff00) >> 8;
		int blue = (nLineColor & 0xff0000) >> 16;

		float fLineW = nLinwWidth;
		if (fLineW >= 3)
			fLineW = fLineW / 2;

		if (nLineStyle == 0) {
			m_pen.setStyle(Paint.Style.STROKE);
			m_pen.setStrokeWidth(fLineW);
			m_pen.setColor(Color.rgb(red, green, blue));
		} else {
			m_pen.setStyle(Paint.Style.STROKE);
			m_pen.setStrokeWidth(fLineW);
			m_pen.setColor(Color.rgb(red, green, blue));
		}
	}

	@Override
	public void DrawVCFLine_MoveTo(float x, float y) {

		m_path.moveTo(x, y);
	}

	@Override
	public void DrawVCFLine_LineTo(float x, float y) {
		m_path.lineTo(x, y);
	}

	@Override
	public void SetVCFLine_EndPanit() {

		m_backCanvas.drawPath(m_path, m_pen);
		m_path.reset();
	}

	@Override
	public void DrawVCFLine_PolyPolyline(Ints linePtsX, Ints linePtsY,
			Ints nEdgeLens, float nLinwWidth, int nLineStyle, int nLineColor) {

		int nEdgesNum = nEdgeLens.count();
		if (nEdgesNum <= 0)
			return;
		int nsize = linePtsX.count();
		if (nsize < 2)
			return;

		int red = (nLineColor & 0x0000ff);
		int green = (nLineColor & 0x00ff00) >> 8;
		int blue = (nLineColor & 0xff0000) >> 16;

		m_pen.setAntiAlias(true);

		float fLineW = nLinwWidth;
		if (fLineW >= 3)
			fLineW = fLineW / 2;

		if (nLineStyle == 0) {
			m_pen.setStyle(Paint.Style.STROKE);
			m_pen.setStrokeWidth(fLineW);
			m_pen.setColor(Color.rgb(red, green, blue));
		} else {
			m_pen.setStyle(Paint.Style.STROKE);
			m_pen.setStrokeWidth(fLineW);
			m_pen.setColor(Color.rgb(red, green, blue));
		}

		int i = 0;
		int j = 0;
		int nPos = 0;

		for (i = 0; i < nEdgesNum; i++) {
			int nPointNums = nEdgeLens.get(i);
			if (nPointNums < 2)
				continue;

			float fx1 = linePtsX.get(nPos);
			float fy1 = linePtsY.get(nPos);

			m_path.moveTo(fx1, fy1);
			for (j = 1; j < nPointNums; j++) {
				fx1 = linePtsX.get(nPos + j);
				fy1 = linePtsY.get(nPos + j);

				m_path.lineTo(fx1, fy1);

			}
			m_backCanvas.drawPath(m_path, m_pen);
			m_path.reset();
			nPos += nPointNums;

		}

		m_path.reset();
		m_pen.reset();

	}

	@Override
	public void DrawVCFLine_DashPolyline(Ints linePtsX, Ints linePtsY,
			int nStartPos, int nLineLen, int nLineStyle, int nLineColor,
			double offset, double snap, float nWidth, int nMask, Ints slMask) {

		int nsize = linePtsX.count();
		if (nsize <= 1)
			return;

		if (nStartPos >= nsize || nLineLen + nStartPos > nsize)
			return;

		int red = (nLineColor & 0x0000ff);
		int green = (nLineColor & 0x00ff00) >> 8;
		int blue = (nLineColor & 0xff0000) >> 16;

		m_pen.setStyle(Paint.Style.STROKE);
		m_pen.setStrokeWidth(nWidth);
		m_pen.setColor(Color.rgb(red, green, blue));
		m_pen.setStrokeCap(Paint.Cap.SQUARE);

		float fx1 = 0;
		float fy1 = 0;
		float fx2 = 0;
		float fy2 = 0;

		//
		double measure = 0; // total distance to the current position
		double runingSegLength = 0; // distance to the start point of current
									// segment
		double dtSnap = 0;
		int TotalMask = nMask;

		int runingPMask = 0; // 排列韵律下标
		int runingSubMask = 0;
		int cornerID = 0; // 实部在拐点标志

		int j = nStartPos;
		for (j = nStartPos + 1; j < nLineLen; j++) // 线循环
		{
			// 获得当前段
			int x1 = linePtsX.get(j - 1);
			int y1 = linePtsY.get(j - 1);
			int x2 = linePtsX.get(j);
			int y2 = linePtsY.get(j);
			double segLength = CalcLength(x1, y1, x2, y2);
			double ang = CalcAngle(x1, y1, x2, y2);

			double sina = Math.sin(ang);
			double cosa = Math.cos(ang);

			while (measure < (runingSegLength + segLength)) {
				int ntempMask = slMask.get(runingPMask);

				double curLength = measure - runingSegLength;

				if (runingPMask % 2 != 0) // 空位
				{
					measure += snap * ntempMask;
					runingSubMask = 0; // 韵律值归零
					runingPMask++; // 韵律位增一
				} else// 符号位
				{
					if (runingSubMask < ntempMask) {
						if (cornerID == 1) {
							fx1 = x1;
							fy1 = y1;
							if ((measure + snap - dtSnap) > (runingSegLength + segLength)) {
								fx2 = x2;
								fy2 = y2;
								dtSnap = dtSnap + segLength;
								measure += segLength;
							} else {
								fx2 = (float) (x1 + (curLength + snap - dtSnap)
										* cosa);
								fy2 = (float) (y1 + (curLength + snap - dtSnap)
										* sina);
								measure = measure + snap - dtSnap;
								runingSubMask++; // 韵律值增一
								cornerID = 0;
							}
						} else {
							fx1 = (float) (x1 + curLength * cosa);
							fy1 = (float) (y1 + curLength * sina);
							if ((measure + snap) > (runingSegLength + segLength)) {
								fx2 = x2;
								fy2 = y2;
								dtSnap = segLength - curLength;
								measure += dtSnap;
								cornerID = 1;
							} else {
								fx2 = (float) (x1 + (curLength + snap) * cosa);
								fy2 = (float) (y1 + (curLength + snap) * sina);
								measure += snap;
								runingSubMask++; // 韵律值增一
							}
						}

						m_backCanvas.drawLine(fx1, fy1, fx2, fy2, m_pen);

					} else {
						runingPMask++; // 韵律位增一
					}
				}

				if (runingPMask == TotalMask) {
					runingPMask = 0; // 韵律位清零
					runingSubMask = 0; // 韵律值清零
				}
			}
			runingSegLength += segLength;
		}

	}

	@Override
	public void DrawVCFLine_DashLine(int x1, int y1, int x2, int y2,
			int nLineStyle, int nLineColor, float nWidth) {

		int red = (nLineColor & 0x0000ff);
		int green = (nLineColor & 0x00ff00) >> 8;
		int blue = (nLineColor & 0xff0000) >> 16;

		m_pen.setStyle(Paint.Style.STROKE);
		m_pen.setStrokeWidth(nWidth);
		m_pen.setColor(Color.rgb(red, green, blue));

		// m_pen.setPathEffect(m_effects);

		m_pen.setStrokeCap(Paint.Cap.SQUARE);

		float fx1 = (float) x1;
		float fy1 = (float) y1;
		float fx2 = (float) x2;
		float fy2 = (float) y2;

		m_backCanvas.drawLine(fx1, fy1, fx2, fy2, m_pen);

	}

	@Override
	public void DrawVCFLine_DotPolyline(Ints linePtsX, Ints linePtsY,
			int nStartPos, int nLineLen, int nLineColor, double offset,
			double snap, double fWidth, double fHeight, double radian,
			int nMaskLen, Ints slMask) {

		int nsize = linePtsX.count();
		if (nsize <= 1)
			return;
		if (nStartPos >= nsize || nLineLen + nStartPos > nsize)
			return;

		int red = (nLineColor & 0x0000ff);
		int green = (nLineColor & 0x00ff00) >> 8;
		int blue = (nLineColor & 0xff0000) >> 16;

		m_brush.setStyle(Paint.Style.FILL_AND_STROKE);
		m_brush.setColor(Color.rgb(red, green, blue));

		int TotalMask = nMaskLen;
		double measure = 0; // total distance to the current position
		double runingSegLength = 0; // distance to the current segment
		int runingPMask = 0; // 排列韵律下标
		int runingSubMask = 0;

		float fx1 = 0;
		float fy1 = 0;

		RectF oval = new RectF();

		int j = nStartPos;
		for (j = nStartPos + 1; j < nLineLen; j++) // 点循环
		{

			int x1 = linePtsX.get(j - 1);
			int y1 = linePtsY.get(j - 1);
			int x2 = linePtsX.get(j);
			int y2 = linePtsY.get(j);
			double segLength = CalcLength(x1, y1, x2, y2);

			double ang = CalcAngle(x1, y1, x2, y2);

			double sina = Math.sin(ang);
			double cosa = Math.cos(ang);

			while (measure < (runingSegLength + segLength)) {
				double curLength = measure - runingSegLength;
				int ntempMask = slMask.get(runingPMask);
				if (runingPMask % 2 != 0) // 空间隙
				{

					measure += snap * ntempMask;
					runingSubMask = 0; // 韵律值归零
					runingPMask++; // 韵律位增一
				} else {
					if (runingSubMask < ntempMask) {

						fx1 = (float) (x1 + curLength * cosa - offset * sina);
						fy1 = (float) (y1 + curLength * sina + offset * cosa);

						oval.set((float) (fx1 - fWidth / 2),
								(float) (fy1 - fHeight / 2),
								(float) (fx1 + fWidth / 2),
								(float) (fy1 + fHeight / 2));

						m_backCanvas.drawOval(oval, m_brush);

						measure += snap;
						runingSubMask++; // 韵律值增一
					} else {
						runingPMask++; // 韵律位增一
					}
				}

				if (runingPMask == TotalMask) {
					runingPMask = 0;
					runingSubMask = 0;
				}
			}
			runingSegLength += segLength;
		}

		m_brush.reset();

	}

	@Override
	public void DrawVCFLine_DotEllipse(int x1, int y1, int x2, int y2,
			int nFillColor) {

		int red = (nFillColor & 0x0000ff);
		int green = (nFillColor & 0x00ff00) >> 8;
		int blue = (nFillColor & 0xff0000) >> 16;

		m_brush.setStyle(Paint.Style.FILL_AND_STROKE);
		m_brush.setColor(Color.rgb(red, green, blue));

		float fx1 = (float) x1;
		float fy1 = (float) y1;
		float fx2 = (float) x2;
		float fy2 = (float) y2;

		RectF oval = new RectF(fx1, fy1, fx2, fy2);
		m_backCanvas.drawOval(oval, m_brush);

		m_brush.reset();

	}

	@Override
	public void DrawVCFLine_HatchPolyline(Ints linePtsX, Ints linePtsY,
			int nStartPos, int nLineLen, int nLineColor, double offset,
			double snap, double fWidth, double fHeight, double radian,
			int nMaskLen, Ints slMask) {

		int nsize = linePtsX.count();
		if (nsize <= 1)
			return;
		if (nStartPos >= nsize || nLineLen + nStartPos > nsize)
			return;

		int red = (nLineColor & 0x0000ff);
		int green = (nLineColor & 0x00ff00) >> 8;
		int blue = (nLineColor & 0xff0000) >> 16;

		float fLineWidth = (float) fWidth;

		m_pen.setStyle(Paint.Style.STROKE);
		m_pen.setStrokeWidth(fLineWidth);
		m_pen.setColor(Color.rgb(red, green, blue));

		// m_pen.setPathEffect(m_effects);

		m_pen.setStrokeCap(Paint.Cap.SQUARE);

		double measure = 0; // total distance to the current position
		double runingSegLength = 0; // distance to the current segment
		float fx1 = 0;
		float fy1 = 0;
		float fx2 = 0;
		float fy2 = 0;

		int runingPMask = 0; // 排列韵律下标
		int runingSubMask = 0;

		int j = nStartPos;
		for (j = nStartPos + 1; j < nLineLen; j++) // 点循环
		{
			// 获得当前段
			int x1 = linePtsX.get(j - 1);
			int y1 = linePtsY.get(j - 1);
			int x2 = linePtsX.get(j);
			int y2 = linePtsY.get(j);
			double segLength = CalcLength(x1, y1, x2, y2);

			double ang = CalcAngle(x1, y1, x2, y2);

			double sina = Math.sin(ang);
			double cosa = Math.cos(ang);
			double tmpang = ang - radian - 0.5 * Math.PI;
			double tmpsina = Math.sin(tmpang);
			double tmpcosa = Math.cos(tmpang);

			while (measure < (runingSegLength + segLength)) {
				double curLength = measure - runingSegLength;
				int ntmpMask = slMask.get(runingPMask);
				if (runingPMask % 2 != 0) // 空间隙
				{

					measure += snap * ntmpMask;
					runingSubMask = 0; // 韵律值归零
					runingPMask++; // 韵律位增一
				} else {
					if (runingSubMask < ntmpMask) // 符号位
					{
						fx1 = (float) (x1 + curLength * cosa - offset * sina);
						fy1 = (float) (y1 + curLength * sina + offset * cosa);
						fx2 = (float) (x1 + curLength * cosa - offset * sina + fHeight
								* tmpcosa);
						fy2 = (float) (y1 + curLength * sina + offset * cosa + fHeight
								* tmpsina);

						m_backCanvas.drawLine(fx1, fy1, fx2, fy2, m_pen);

						measure += snap;
						runingSubMask++; // 韵律值增一
					} else {
						runingPMask++; // 韵律位增一
					}
				}
				if (runingPMask == nMaskLen) {
					runingPMask = 0;
					runingSubMask = 0;
				}
			}
			runingSegLength += segLength;
		}

		// m_path.reset();
		// m_pen.reset();
	}

	@Override
	public void DrawVCFLine_HatchLine(int x1, int y1, int x2, int y2,
			int nLineStyle, int nLineColor, float nWidth) {

		int red = (nLineColor & 0x0000ff);
		int green = (nLineColor & 0x00ff00) >> 8;
		int blue = (nLineColor & 0xff0000) >> 16;

		m_pen.setStyle(Paint.Style.STROKE);
		m_pen.setStrokeWidth(nWidth);
		m_pen.setColor(Color.rgb(red, green, blue));
		m_pen.setStrokeCap(Paint.Cap.SQUARE);

		float fx1 = (float) x1;
		float fy1 = (float) y1;
		float fx2 = (float) x2;
		float fy2 = (float) y2;

		m_backCanvas.drawLine(fx1, fy1, fx2, fy2, m_pen);

		m_pen.reset();
	}

	@Override
	public void DrawVCFLine_TrueTypeSymbol(Ints linePtsX, Ints linePtsY,
			int nStartPos, int nLineLen, double fsnap, double foffset,
			double fWidth, double fHeight, double fSymWidth, double fSymHeight,
			int nMaskLen, int nSymNa, double fSymAngle,
			emVCF_SYMBOL_FONT emFont, double fScale, int nSymColor, Ints slMask) {

		int nsize = linePtsX.count();
		if (nsize <= 1)
			return;
		if (nStartPos >= nsize || nLineLen + nStartPos > nsize)
			return;

		int red = (nSymColor & 0x0000ff);
		int green = (nSymColor & 0x00ff00) >> 8;
		int blue = (nSymColor & 0xff0000) >> 16;

		float ffontsize = (float) fWidth;
		if (ffontsize <= 0)
			ffontsize = (float) fHeight;

		m_symPaint.setColor(Color.rgb(red, green, blue));

		m_symPaint.setTextSize(ffontsize);
		m_symPaint.setTextAlign(Align.CENTER);
		m_symPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		m_symPaint.setFakeBoldText(true);

		if (emFont == emVCF_SYMBOL_FONT.VCFSYMBOL_ONE)
			m_symPaint.setTypeface(m_chartSym1Font);
		else if (emFont == emVCF_SYMBOL_FONT.VCFSYMBOL_TWO)
			m_symPaint.setTypeface(m_chartSym2Font);
		else
			m_symPaint.setTypeface(m_chartSym1Font);

		double fdpi = GetScreenDpi();

		double measure = 0; // total distance to the current position
		double runingSegLength = 0; // distance to the current segment

		float fx1 = 0;
		float fy1 = 0;

		double tmpWidth;

		if (fSymWidth != 0.0)
			tmpWidth = fSymWidth;
		else
			tmpWidth = fSymHeight;

		double x0 = tmpWidth * 0.5;
		double y0 = -fSymHeight * 0.5;

		// nMaskLen;
		char szSymna[] = new char[2];
		szSymna[0] = (char) nSymNa;
		szSymna[1] = 0;

		String strSym = new String(szSymna);

		int runingPMask = 0; // 排列韵律下标
		int runingSubMask = 0;

		int j = nStartPos;

		for (j = nStartPos + 1; j < nLineLen; j++) // 点循环
		{
			// 获得当前段
			int x1 = linePtsX.get(j - 1);
			int y1 = linePtsY.get(j - 1);
			int x2 = linePtsX.get(j);
			int y2 = linePtsY.get(j);
			double segLength = CalcLength(x1, y1, x2, y2);

			double ang = CalcAngle(x1, y1, x2, y2);

			double sina = Math.sin(ang);
			double cosa = Math.cos(ang);

			double tmpang = fSymAngle * Math.PI / 180. - ang;// 反转方向

			double tmpsina = Math.sin(tmpang);
			double tmpcosa = Math.cos(tmpang);

			double tmpx = (x0 * tmpcosa - y0 * tmpsina) * fScale;
			double tmpy = (x0 * tmpsina + y0 * tmpcosa) * fScale;

			double dtx = tmpx / 25.4 * fdpi;
			double dty = tmpy / 25.4 * fdpi;

			while (measure < (runingSegLength + segLength)) {
				double curLength = measure - runingSegLength;
				int ntmpMask = slMask.get(runingPMask);
				if (runingPMask % 2 != 0) // 空间隙
				{
					measure += fsnap * ntmpMask;
					runingSubMask = 0; // 韵律值归零
					runingPMask++; // 韵律位增一
				} else {
					if (runingSubMask < ntmpMask) // 符号位
					{
						fx1 = (float) (x1 + curLength * cosa - foffset * sina);
						fy1 = (float) (y1 + curLength * sina + foffset * cosa);

						if (fHeight > 1) {

							float fRotAngle = (float) fSymAngle
									- (float) (ang * 180. / 3.1415926);

							float fscrx = fx1 + (float) dtx;
							float fscry = fy1 - (float) dty;

							if (fRotAngle != 0)
								m_backCanvas.rotate(-(float) fRotAngle, fscrx,
										fscry);

							m_backCanvas.drawText(strSym, fscrx, fscry,
									m_symPaint);

							if (fRotAngle != 0)
								m_backCanvas.rotate((float) fRotAngle, fscrx,
										fscry);
						}

						measure += fsnap;
						runingSubMask++; // 韵律值增一
					} else {
						runingPMask++; // 韵律位增一
					}
				}
				if (runingPMask == nMaskLen) {
					runingPMask = 0;
					runingSubMask = 0;
				}
			}
			runingSegLength += segLength;
		}

		m_symPaint.reset();
	}

	@Override
	public void DrawVCFArea_PolyPolygon(Ints linePtsX, Ints linePtsY,
			Ints nRingLen, int nFillColor, int nFillStyle) {

		int nEdgesNum = nRingLen.count();
		if (nEdgesNum <= 0)
			return;
		int nsize = linePtsX.count();
		if (nsize < 2)
			return;

		int red = (nFillColor & 0x0000ff);
		int green = (nFillColor & 0x00ff00) >> 8;
		int blue = (nFillColor & 0xff0000) >> 16;

		m_brush.setStyle(Paint.Style.FILL);
		m_brush.setColor(Color.rgb(red, green, blue));

		m_path.reset();

		int i = 0;
		int j = 0;
		int nPos = 0;
		for (i = 0; i < nEdgesNum; i++) {
			int nPointNums = nRingLen.get(i);

			if (nPointNums < 2)
				continue;

			float x = linePtsX.get(nPos);
			float y = linePtsY.get(nPos);

			m_path.moveTo(x, y);

			for (j = 1; j < nPointNums; j++) {
				x = linePtsX.get(nPos + j);
				y = linePtsY.get(nPos + j);

				m_path.lineTo(x, y);
			}
			m_path.close();
			m_backCanvas.drawPath(m_path, m_brush);
			m_path.reset();

			nPos += nPointNums;
		}

		m_brush.reset();

	}

	@Override
	public void DrawVCFArea_PolyEllipse(Ints linePtsX, Ints linePtsY,
			Ints nRingLen, int nFillColor, float nWidth, float nHeight) {

		int nEdgesNum = nRingLen.count();
		if (nEdgesNum <= 0)
			return;
		int nsize = linePtsX.count();
		if (nsize < 2)
			return;

		int red = (nFillColor & 0x0000ff);
		int green = (nFillColor & 0x00ff00) >> 8;
		int blue = (nFillColor & 0xff0000) >> 16;

		m_brush.setStyle(Paint.Style.FILL);
		m_brush.setColor(Color.rgb(red, green, blue));

		RectF oval = new RectF();

		int i = 0;
		int j = 0;
		int nPos = 0;
		for (i = 0; i < nEdgesNum; i++) {
			int nPointNums = nRingLen.get(i);

			if (nPointNums < 2)
				continue;

			for (j = 0; j < nPointNums; j++) {

				float x = linePtsX.get(nPos);
				float y = linePtsY.get(nPos);

				float fx1 = x - nWidth / 2;
				float fy1 = y - nHeight / 2;
				float fx2 = x + nWidth / 2;
				float fy2 = y + nHeight / 2;
				oval.set(fx1, fy1, fx2, fy2);

				m_backCanvas.drawOval(oval, m_brush);

			}
			nPos += nPointNums;
		}

		m_brush.reset();

	}

	@Override
	public void DrawVoyageMeasure(int x1, int y1, int x2, int y2,
			int nPenColor, int nPenWidth, int nPenStyle, float fDir, float fDis) {

		m_frontCanvas.drawBitmap(m_backBitmap, m_ScreenRect.left,
				m_ScreenRect.top, m_pen);

		int red = (nPenColor & 0x0000ff);
		int green = (nPenColor & 0x00ff00) >> 8;
		int blue = (nPenColor & 0xff0000) >> 16;

		float fR = FloatMath
				.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

		m_pen.setStyle(Paint.Style.STROKE);
		m_pen.setStrokeWidth(nPenWidth);
		m_pen.setColor(Color.rgb(red, green, blue));

		float x = (float) ((x1 + x2) / 2.0);
		float y = (float) ((y1 + y2) / 2.0);

		m_backCanvas.drawCircle(x, y, fR, m_pen);

	}

	public static int getTextWidth(Paint paint, String str) {
		int iRet = 0;
		if (str != null && str.length() > 0) {
			int len = str.length();
			float[] widths = new float[len];
			paint.getTextWidths(str, widths);
			for (int j = 0; j < len; j++) {
				iRet += (int) Math.ceil(widths[j]);
			}
		}
		return iRet;
	}

	public double CalcLength(float x1, float y1, float x2, float y2) {

		return Math.sqrt((double) ((x1 - x2) * (x1 - x2) + (y1 - y2)
				* (y1 - y2)));
	}

	public double CalcAngle(float x1, float y1, float x2, float y2) {

		return Math.atan2((double) (y2 - y1), (double) (x2 - x1));
	}
}
