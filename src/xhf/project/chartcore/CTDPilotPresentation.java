package xhf.project.chartcore;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.util.Log;
import xhf.project.ardshippilotsys.PilotSysMainActivity;
import xhf.project.chartcorejni.CHfVCFElectronicChart;
import xhf.project.chartcorejni.CPoint;
import xhf.project.chartcorejni.stDPOINT;
import xhf.project.chartcorejni.stPLANEENVELOPE;
import xhf.project.dynamictarget.CAisTarget;
import xhf.project.dynamictarget.CDynamicTargetControl;
import xhf.project.dynamictarget.CPilotShip;
import xhf.project.routemanage.CPlanRoute;
import xhf.project.routemanage.CPlanRouteManage;
import xhf.project.routemanage.CWayPoint;
import xhf.project.utilitytool.CUtilityTool;

public class CTDPilotPresentation {

	private double m_fPilotShipSymSize;
	private double m_fPilotShipHeadLen;
	private double m_fAisTargetSymHeight; // AIS Symbol Len mm
	private double m_fAisHeadLineLength; // AIS Symbol Head lenmm
	private double m_fAisSelectLength; // Ais Symbol Select Lenmm
	private double m_fAisTriangleAngle; //

	private boolean m_bIsShowAisTarget;
	private boolean m_bAisNameCallMrk;
	private boolean m_bAisOnlyCallMrk;
	private boolean m_bAisTargetIdMrk;
	private boolean m_bAisTimeoutMrk;
	//
	private boolean m_bShipTrackPlot;

	private stDPOINT m_tmpDPoint = new stDPOINT();

	private CPoint m_ptDspPoint = new CPoint();

	private CPoint m_ptVecPoint = new CPoint();

	private CPoint m_ptAngle[] = new CPoint[4];

	private CPoint m_ptHeadPoint = new CPoint();

	private CPoint m_SPoint[] = new CPoint[6];

	private CPoint m_lPoint[] = new CPoint[5];

	private CPoint m_ptpoint = new CPoint();
	private CPoint m_tempPoint = new CPoint();
	private CPoint m_prvPoint = new CPoint();

	private PathEffect m_effects = null;

	private Paint m_pen = null;
	private Paint m_brush = null;
	private Path m_path = null;
	private Paint m_textPaint = null;

	public CTDPilotPresentation() {

		m_fPilotShipSymSize = 8.0;// mm
		m_fPilotShipHeadLen = 0.15; // nm
		m_fAisTargetSymHeight = 8;
		m_fAisHeadLineLength = 8;
		m_fAisSelectLength = 3;
		m_fAisTriangleAngle = 35;

		m_bIsShowAisTarget = true;

		m_bAisNameCallMrk = true;

		//
		//
		m_bAisOnlyCallMrk = true;
		m_bAisTargetIdMrk = true;
		m_bAisTimeoutMrk = true;

		m_bShipTrackPlot = true;

		m_pen = new Paint();
		m_brush = new Paint();

		m_pen.setAntiAlias(true);

		m_path = new Path();

		m_effects = new DashPathEffect(new float[] { 5, 5, 5, 5 }, 1);

		m_textPaint = new Paint();
		Typeface txtFont = Typeface.create(Typeface.SERIF, Typeface.NORMAL);
		m_textPaint.setTextAlign(Align.LEFT);
		m_textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		m_textPaint.setTypeface(txtFont);
		m_textPaint.setFakeBoldText(true);
		m_textPaint.setTextSize(14);

		for (int i = 0; i < 4; i++)
			m_ptAngle[i] = new CPoint();

		for (int i = 0; i < 6; i++)
			m_SPoint[i] = new CPoint();

		for (int i = 0; i < 5; i++)
			m_lPoint[i] = new CPoint();

	}

	public void Close() {

	}

	//
	public void xhfDrawDynamicTarget(CDynamicTargetControl pDynaTargetM,
			CHfVCFElectronicChart pChart, CGeoCanvasJv geoCanvas) {

		if (pDynaTargetM == null || pChart == null)
			return;

		String strRelId = "";
		CPilotShip pPilotShip = pDynaTargetM.GetPilotShip();

		if (pPilotShip != null) {
			xhfDrawPilotShip(pPilotShip, pChart, geoCanvas);

			strRelId = pPilotShip.GetRelationAisTargetId();
		}

		int nAisNums = pDynaTargetM.GetAisTargetNums();
		int i = 0;
		for (i = 0; i < nAisNums; i++) {
			CAisTarget pAisTarget = pDynaTargetM.GetAisTarget(i);
			if (pAisTarget != null) {
				
				String strTargetid = pAisTarget.GetTargetId();
				if (strTargetid.compareTo(strRelId) != 0) {
					xhfDrawAisTarget(pAisTarget, pChart, geoCanvas);
				}
			}
		}
	}

	public void DrawWorkPlanRoute(CPlanRouteManage pWorkRouteM,
			CHfVCFElectronicChart pChart, CGeoCanvasJv geoCanvas) {
		if (pWorkRouteM == null || pChart == null)
			return;

		CPlanRoute pPlanRoute = pWorkRouteM.GetEditPlanRoute();
		if (pPlanRoute == null)
			return;

		int nCount = pPlanRoute.GetWayPointNums();
		if (nCount <= 0)
			return;

		if (geoCanvas == null)
			return;

		Canvas frtCanvas = geoCanvas.GetFrontCanvas();
		if (frtCanvas == null)
			return;

		int i = 0;
		CWayPoint pWP = pPlanRoute.getWayPoint(i);
		if (pWP == null)
			return;

		int red = 172;
		int green = 103;
		int blue = 0;

		m_pen.setStyle(Paint.Style.STROKE);
		m_pen.setStrokeWidth(6);
		m_pen.setColor(Color.rgb(red, green, blue));

		m_brush.setStyle(Paint.Style.FILL);
		m_brush.setColor(Color.rgb(red, green, blue));

		pChart.GeoLongLatToScreenPoint(pWP.m_fLong, pWP.m_fLat, m_ptpoint);

		int fx = m_ptpoint.getX();
		int fy = m_ptpoint.getY();

		RectF oval = new RectF(fx - 10, fy - 10, fx + 10, fy + 10);
		frtCanvas.drawOval(oval, m_brush);

		String strMark = String.format("#%d", pWP.m_nNum);

		frtCanvas.drawText(strMark, fx, fy, m_textPaint);

		m_prvPoint.setX(fx);
		m_prvPoint.setY(fy);

		i++;

		while (i < nCount) {
			pWP = pPlanRoute.getWayPoint(i);

			if (pWP == null)
				return;

			pChart.GeoLongLatToScreenPoint(pWP.m_fLong, pWP.m_fLat, m_ptpoint);

			int fx1 = m_prvPoint.getX();
			int fy1 = m_prvPoint.getY();
			int fx2 = m_ptpoint.getX();
			int fy2 = m_ptpoint.getY();

			frtCanvas.drawLine(fx1, fy1, fx2, fy2, m_pen);

			m_prvPoint.setX(fx2);
			m_prvPoint.setY(fy2);

			oval.set(fx2 - 10, fy2 - 10, fx2 + 10, fy2 + 10);
			frtCanvas.drawOval(oval, m_brush);

			strMark = String.format("#%d", pWP.m_nNum);

			frtCanvas.drawText(strMark, fx2, fy2, m_textPaint);

			i++;

		}

	}

	public void DrawWKPlanRouteScr(CPlanRouteManage pWorkRouteM, Canvas canvas) {
		if (pWorkRouteM == null || canvas == null)
			return;

		CPlanRoute pPlanRoute = pWorkRouteM.GetEditPlanRoute();
		if (pPlanRoute == null)
			return;

		if ((pWorkRouteM.GetRouteEditOpState() == 1)
				&& (pPlanRoute.GetWayPointNums() >= 1)) {
			/*
			 * CPoint ptFristPoint = pWorkRouteM->GetPtFirstPoint();
			 * 
			 * CPoint ptSecondPoint = pWorkRouteM->GetPtSecondPoint();
			 * 
			 * unsigned long clrDrawColor=RGB(0,0,255);
			 * 
			 * int nr = GetRValue(clrDrawColor); int ng =
			 * GetGValue(clrDrawColor); int nb = GetBValue(clrDrawColor);
			 * 
			 * float fr = xhfRgbToFloat(nr); float fg = xhfRgbToFloat(ng); float
			 * fb = xhfRgbToFloat(nb); CGContextSetRGBFillColor(pDC, fr, fg, fb,
			 * 1); CGContextSetRGBStrokeColor(pDC, fr, fg,fb, 1);
			 * CGContextSetLineWidth(pDC,1);
			 * 
			 * CGContextMoveToPoint(pDC, ptFristPoint.x, ptFristPoint.y);
			 * CGContextAddLineToPoint(pDC, ptSecondPoint.x, ptSecondPoint.y);
			 * CGContextStrokePath(pDC);
			 */

		} else if ((pWorkRouteM.GetRouteEditOpState() == 2)
				&& pWorkRouteM.GetIsUpdateOp()) {

			CPoint ptPoint1 = pWorkRouteM.GetPtCurrPoint();
			CPoint ptPoint2 = pWorkRouteM.GetPtPrevPoint();
			CPoint ptPoint3 = pWorkRouteM.GetPtNextPoint();

			int red = 0;
			int green = 0;
			int blue = 255;

			m_pen.setStyle(Paint.Style.STROKE);
			m_pen.setStrokeWidth(1);
			m_pen.setColor(Color.rgb(red, green, blue));

			canvas.drawLine(ptPoint1.getX(), ptPoint1.getY(), ptPoint2.getX(),
					ptPoint2.getY(), m_pen);

			canvas.drawLine(ptPoint1.getX(), ptPoint1.getY(), ptPoint3.getX(),
					ptPoint3.getY(), m_pen);

		}
	}

	public void DrawMoniPlanRoute(CPlanRouteManage pMoniRouteM,
			CHfVCFElectronicChart pChart, CGeoCanvasJv geoCanvas) {
		if (pMoniRouteM == null || pChart == null)
			return;

		CPlanRoute pPlanRoute = pMoniRouteM.GetMonPlanRoute();
		if (pPlanRoute == null)
			return;
		int nCount = pPlanRoute.GetWayPointNums();
		if (nCount <= 1)
			return;

		if (geoCanvas == null)
			return;

		Canvas frtCanvas = geoCanvas.GetFrontCanvas();
		if (frtCanvas == null)
			return;

		int i = 0;
		CWayPoint pWP = pPlanRoute.getWayPoint(i);
		if (pWP == null)
			return;

		int red = 192;
		int green = 142;
		int blue = 0;

		m_pen.setStyle(Paint.Style.STROKE);
		m_pen.setStrokeWidth(6);
		m_pen.setColor(Color.rgb(red, green, blue));

		m_brush.setStyle(Paint.Style.FILL);
		m_brush.setColor(Color.rgb(red, green, blue));

		pChart.GeoLongLatToScreenPoint(pWP.m_fLong, pWP.m_fLat, m_ptpoint);

		int fx = m_ptpoint.getX();
		int fy = m_ptpoint.getY();

		RectF oval = new RectF(fx - 10, fy - 10, fx + 10, fy + 10);
		frtCanvas.drawOval(oval, m_brush);

		String strMark = String.format("#%d", pWP.m_nNum);

		frtCanvas.drawText(strMark, fx, fy, m_textPaint);

		m_prvPoint.setX(fx);
		m_prvPoint.setY(fy);

		i++;

		while (i < nCount) {

			pWP = pPlanRoute.getWayPoint(i);
			if (pWP == null)
				return;

			pChart.GeoLongLatToScreenPoint(pWP.m_fLong, pWP.m_fLat, m_ptpoint);

			int fx1 = m_prvPoint.getX();
			int fy1 = m_prvPoint.getY();
			int fx2 = m_ptpoint.getX();
			int fy2 = m_ptpoint.getY();

			frtCanvas.drawLine(fx1, fy1, fx2, fy2, m_pen);

			m_prvPoint.setX(fx2);
			m_prvPoint.setY(fy2);

			oval.set(fx2 - 10, fy2 - 10, fx2 + 10, fy2 + 10);
			frtCanvas.drawOval(oval, m_brush);

			strMark = String.format("#%d", pWP.m_nNum);

			frtCanvas.drawText(strMark, fx2, fy2, m_textPaint);

			i++;

		}

	}

	//
	//
	public void SetAisTargetShowOnOff(boolean bShow) {

		m_bIsShowAisTarget = bShow;

	}

	//
	public boolean GetAisTargetShowOnOff() {

		return m_bIsShowAisTarget;
	}

	public void SetAisNameMarkOnOff(boolean bMark) {

		m_bAisNameCallMrk = bMark;
	}

	public boolean GetAisNameMarkOnOff() {

		return m_bAisNameCallMrk;
	}

	public void SetAisTargetShowAttrib(boolean bAisOnOff, boolean bIdMrk,
			boolean bNameMrk, boolean bCallMrk, boolean bToutMrk) {

		m_bIsShowAisTarget = bAisOnOff;
		m_bAisNameCallMrk = bNameMrk;
		m_bAisOnlyCallMrk = bCallMrk;
		m_bAisTargetIdMrk = bIdMrk;
		m_bAisTimeoutMrk = bToutMrk;

		ChartCoreView.m_chartCore.FlushShowDrawBuffer();
	}

	// Timer update
	public void xhfDynaTargetPresFlushTimer(
			CDynamicTargetControl pDynaTargetControl,
			CHfVCFElectronicChart pChart) {

		if (pDynaTargetControl == null || pChart == null)
			return;

		if (pDynaTargetControl.GetIsPilotShipChanage()
				|| pDynaTargetControl.GetIsAisTargetChanage()) {
			ChartCoreView.m_chartCore.FlushShowDrawBuffer();
		}
	}

	//
	public void SetShipTrackIsPlot(boolean bPlot) {

		m_bShipTrackPlot = bPlot;

		ChartCoreView.m_chartCore.FlushShowDrawBuffer();

	}

	//
	public boolean GetShipTrackIsPlot() {

		return m_bShipTrackPlot;
	}

	public void xhfDrawPilotShip(CPilotShip pPilotShip,
			CHfVCFElectronicChart pChart, CGeoCanvasJv geoCanvas) {
		if (pPilotShip == null || pChart == null)
			return;
		if (!pPilotShip.ShipPositionIsAvalid())
			return;

		if (geoCanvas == null)
			return;

		Canvas frtCanvas = geoCanvas.GetFrontCanvas();
		if (frtCanvas == null)
			return;

		double fLong, fLat, fCourse, fHead, fSpeed;

		fLong = pPilotShip.GetShipPosLong();//经度
		fLat = pPilotShip.GetShipPosLat(); //纬度
		fCourse = pPilotShip.GetShipPosCourse(); //航向
		fSpeed = pPilotShip.GetShipPosSpeed(); //航速

		fHead = pPilotShip.GetUseHead();
		pChart.GpsPosOffsetCorrect(fLong, fLat, m_tmpDPoint);
		fLong = m_tmpDPoint.getX();
		fLat = m_tmpDPoint.getY();

		double fShapeDir = fCourse;
		if (fHead < 360.0)
			fShapeDir = fHead;
		else
			fShapeDir = fCourse;

		long ulVecLnColor = CUtilityTool.RGBToLong(0, 72, 118);

		int nSymStyle = GetPilotShipSymbolStyle(pPilotShip, pChart);

		long ulSymColor = GetPilotShipColor(pPilotShip, pChart);

		long ulHeadLnColor = CUtilityTool.RGBToLong(0, 128, 0);

		int nVSILen = pPilotShip.GetShipVSI();

		boolean bShipVSI = pPilotShip.GetIsShipVSI();

		double fShipCeLat = fLat;

		double fShipCeLong = fLong;

		double xPixel = geoCanvas.GetxPixelDPM();
		double yPixel = geoCanvas.GetyPixelDPM();

		double fScale = pChart.GetChartDspScale();

		if (nSymStyle == 0) {

			pChart.GeoLongLatToScreenPoint(fLong, fLat, m_ptDspPoint);

			int red = (int) (ulSymColor & 0xff0000) >> 16;
			int green = (int) (ulSymColor & 0x00ff00) >> 8;
			int blue = (int) (ulSymColor & 0x0000ff);

			m_pen.setStyle(Paint.Style.STROKE);
			m_pen.setStrokeWidth(2);
			m_pen.setColor(Color.rgb(red, green, blue));

			float fx = (float) (m_ptDspPoint.getX());
			float fy = (float) (m_ptDspPoint.getY());

			RectF oval1 = new RectF(fx - 9, fy - 9, fx + 9, fy + 9);

			frtCanvas.drawOval(oval1, m_pen);

			RectF oval2 = new RectF(fx - 5, fy - 5, fx + 5, fy + 5);
			frtCanvas.drawOval(oval2, m_pen);

			RectF oval3 = new RectF(fx - 2, fy - 2, fx + 2, fy + 2);
			frtCanvas.drawOval(oval3, m_pen);

			// Draw Course Line
			// ptAngle[4];

			red = (int) (ulVecLnColor & 0xff0000) >> 16;
			green = (int) (ulVecLnColor & 0x00ff00) >> 8;
			blue = (int) (ulVecLnColor & 0x0000ff);

			m_pen.setStrokeWidth(1);
			m_pen.setColor(Color.rgb(red, green, blue));

			double fVecLong, fVecLat;
			pChart.CalculateLongLatByDisDir(fLong, fLat, (fSpeed / 60)
					* nVSILen, fCourse, m_tmpDPoint);
			fVecLong = m_tmpDPoint.getX();
			fVecLat = m_tmpDPoint.getY();

			pChart.GeoLongLatToScreenPoint(fVecLong, fVecLat, m_ptVecPoint);

			if (bShipVSI) {

				frtCanvas.drawLine(m_ptDspPoint.getX(), m_ptDspPoint.getY(),
						m_ptVecPoint.getX(), m_ptVecPoint.getY(), m_pen);

			}

			// draw Course "->"
			if (fSpeed * 10 > 5 && nVSILen > 0) {
				double tmp1, tmp2;
				tmp1 = fCourse - 150;
				if (tmp1 < 0)
					tmp1 = tmp1 + 360;
				tmp2 = fCourse + 150;
				if (tmp2 >= 360)
					tmp2 = tmp2 - 360;

				int nvsiLen = 15;
				int nvsilen2 = 5;

				double lvsinm = nvsiLen * xPixel * fScale / 1000 / 1852;
				double lvsinm2 = nvsilen2 * xPixel * fScale / 1000 / 1852;

				double tmpx, tmpy;
				pChart.CalculateLongLatByDisDir(fVecLong, fVecLat, lvsinm,
						tmp1, m_tmpDPoint);
				tmpx = m_tmpDPoint.getX();
				tmpy = m_tmpDPoint.getY();

				pChart.GeoLongLatToScreenPoint(tmpx, tmpy, m_ptAngle[0]);

				pChart.CalculateLongLatByDisDir(fLong, fLat, (fSpeed / 60)
						* nVSILen - lvsinm2, fCourse, m_tmpDPoint);
				tmpx = m_tmpDPoint.getX();
				tmpy = m_tmpDPoint.getY();
				pChart.GeoLongLatToScreenPoint(tmpx, tmpy, m_ptAngle[1]);

				pChart.CalculateLongLatByDisDir(fVecLong, fVecLat, lvsinm,
						tmp2, m_tmpDPoint);
				tmpx = m_tmpDPoint.getX();
				tmpy = m_tmpDPoint.getY();
				pChart.GeoLongLatToScreenPoint(tmpx, tmpy, m_ptAngle[2]);

				red = (int) (ulVecLnColor & 0xff0000) >> 16;
				green = (int) (ulVecLnColor & 0x00ff00) >> 8;
				blue = (int) (ulVecLnColor & 0x0000ff);

				m_pen.setStyle(Paint.Style.STROKE);
				m_pen.setStrokeWidth(1);
				m_pen.setColor(Color.rgb(red, green, blue));

				if (bShipVSI) {
					m_path.moveTo(m_ptAngle[0].getX(), m_ptAngle[0].getY());

					for (int j = 1; j < 3; j++)
						m_path.lineTo(m_ptAngle[j].getX(), m_ptAngle[j].getY());
					frtCanvas.drawPath(m_path, m_pen);
					m_path.reset();

				}

			}
			// draw head line.
			if (fHead < 360) {
				// calcate head line point

				double fHLong, fHLat;
				pChart.CalculateLongLatByDisDir(fLong, fLat,
						m_fPilotShipHeadLen, fHead, m_tmpDPoint);
				fHLong = m_tmpDPoint.getX();
				fHLat = m_tmpDPoint.getY();

				pChart.GeoLongLatToScreenPoint(fHLong, fHLat, m_ptHeadPoint);

				red = (int) (ulHeadLnColor & 0xff0000) >> 16;
				green = (int) (ulHeadLnColor & 0x00ff00) >> 8;
				blue = (int) (ulHeadLnColor & 0x0000ff);

				m_pen.setStrokeWidth(1);
				m_pen.setColor(Color.rgb(red, green, blue));

				pChart.GeoLongLatToScreenPoint(fVecLong, fVecLat, m_ptVecPoint);

				frtCanvas.drawLine(m_ptDspPoint.getX(), m_ptDspPoint.getY(),
						m_ptHeadPoint.getX(), m_ptHeadPoint.getY(), m_pen);

			}
		} else if (nSymStyle == 1) {

			pChart.GeoLongLatToScreenPoint(fLong, fLat, m_ptDspPoint);

			// real ship model ,need ship jia shi shi correct.
			int nTxHead, nTxTail, nTxLeft, nTxRight;

			nTxHead = pPilotShip.GetTxHead();
			nTxTail = pPilotShip.GetTxTail();
			nTxLeft = pPilotShip.GetTxLeft();
			nTxRight = pPilotShip.GetTxRight();

			pChart.GpsPosCenterCorrect(fLong, fLat, fShapeDir, nTxHead,
					nTxTail, nTxLeft, nTxRight, m_tmpDPoint);

			fShipCeLong = m_tmpDPoint.getX();
			fShipCeLat = m_tmpDPoint.getY();

			double fAngle1, fLenEdge1;
			double flat1, flong1, flat2, flong2, flat3, flong3, flat4, flong4, flat5, flong5;

			double fWDu, fRDu, fLDu, fHDu, fTDu;

			int nW, nL;

			nW = nTxLeft + nTxRight;
			nL = nTxHead + nTxTail;
			fWDu = (double) (nW) / 1852 / 60;
			fRDu = (double) (nTxRight) / 1852 / 60;
			fLDu = (double) (nL) / 1852 / 60;
			fHDu = (double) (nTxHead) / 1852 / 60;
			fTDu = (double) (nTxTail) / 1852 / 60;

			// calcate 1/4 (long,lat)
			double ftmpLong, ftmpLat;

			if (pChart.GetGpsPosCorrectMode() == 1) {

				fLenEdge1 = Math.sqrt(Math.pow(fWDu / 2, 2.0)
						+ Math.pow(fLDu / 2, 2.0));

				fAngle1 = Math.atan((fWDu / 2) / (fLDu / 2)) * 180 / 3.1415926;

				pChart.CalculateLongLatByDisDir(fShipCeLong, fShipCeLat,
						(fLDu / 2) * 60, fShapeDir, m_tmpDPoint);
				flat1 = m_tmpDPoint.getY();
				flong1 = m_tmpDPoint.getX();

				pChart.CalculateLongLatByDisDir(fShipCeLong, fShipCeLat,
						(fLDu / 4) * 60, fShapeDir, m_tmpDPoint);
				ftmpLong = m_tmpDPoint.getX();
				ftmpLat = m_tmpDPoint.getY();

				pChart.CalculateLongLatByDisDir(ftmpLong, ftmpLat,
						fWDu / 2 * 60, fShapeDir - 90, m_tmpDPoint);
				flat2 = m_tmpDPoint.getY();
				flong2 = m_tmpDPoint.getX();

				pChart.CalculateLongLatByDisDir(fShipCeLong, fShipCeLat,
						fLenEdge1 * 60, 180 + fAngle1 + fShapeDir, m_tmpDPoint);
				flat3 = m_tmpDPoint.getY();
				flong3 = m_tmpDPoint.getX();

				pChart.CalculateLongLatByDisDir(fShipCeLong, fShipCeLat,
						fLenEdge1 * 60, fShapeDir + 180 - fAngle1, m_tmpDPoint);
				flat4 = m_tmpDPoint.getY();
				flong4 = m_tmpDPoint.getX();

				pChart.CalculateLongLatByDisDir(ftmpLong, ftmpLat,
						fWDu / 2 * 60, fShapeDir + 90, m_tmpDPoint);

				flat5 = m_tmpDPoint.getY();
				flong5 = m_tmpDPoint.getX();
			} else {
				fLenEdge1 = Math.sqrt(Math.pow(fWDu / 2, 2.0)
						+ Math.pow(fTDu, 2.0));

				fAngle1 = Math.atan((fWDu / 2) / fTDu) * 180 / 3.1415926;

				pChart.CalculateLongLatByDisDir(fShipCeLong, fShipCeLat,
						fHDu * 60, fShapeDir, m_tmpDPoint);

				flat1 = m_tmpDPoint.getY();
				flong1 = m_tmpDPoint.getX();

				if (fLDu / 4 >= fHDu)
					pChart.CalculateLongLatByDisDir(fShipCeLong, fShipCeLat,
							(fLDu / 4 - fHDu) * 60, fShapeDir + 180,
							m_tmpDPoint);
				else
					pChart.CalculateLongLatByDisDir(fShipCeLong, fShipCeLat,
							(fHDu - fLDu / 4) * 60, fShapeDir, m_tmpDPoint);

				ftmpLong = m_tmpDPoint.getX();
				ftmpLat = m_tmpDPoint.getY();

				pChart.CalculateLongLatByDisDir(ftmpLong, ftmpLat,
						fWDu / 2 * 60, fShapeDir - 90, m_tmpDPoint);
				flat2 = m_tmpDPoint.getY();
				flong2 = m_tmpDPoint.getX();

				pChart.CalculateLongLatByDisDir(fShipCeLong, fShipCeLat,
						fLenEdge1 * 60, 180 + fAngle1 + fShapeDir, m_tmpDPoint);

				flat3 = m_tmpDPoint.getY();
				flong3 = m_tmpDPoint.getX();

				pChart.CalculateLongLatByDisDir(fShipCeLong, fShipCeLat,
						fLenEdge1 * 60, fShapeDir + 180 - fAngle1, m_tmpDPoint);

				flat4 = m_tmpDPoint.getY();
				flong4 = m_tmpDPoint.getX();

				pChart.CalculateLongLatByDisDir(ftmpLong, ftmpLat,
						fWDu / 2 * 60, fShapeDir + 90, m_tmpDPoint);
				flat5 = m_tmpDPoint.getY();
				flong5 = m_tmpDPoint.getX();

			}

			pChart.GeoLongLatToScreenPoint(fShipCeLong, fShipCeLat, m_SPoint[0]);
			pChart.GeoLongLatToScreenPoint(flong1, flat1, m_SPoint[1]);

			pChart.GeoLongLatToScreenPoint(flong2, flat2, m_SPoint[2]);
			pChart.GeoLongLatToScreenPoint(flong3, flat3, m_SPoint[3]);
			pChart.GeoLongLatToScreenPoint(flong4, flat4, m_SPoint[4]);
			pChart.GeoLongLatToScreenPoint(flong5, flat5, m_SPoint[5]);

			m_lPoint[0] = m_SPoint[1];
			m_lPoint[1] = m_SPoint[2];
			m_lPoint[2] = m_SPoint[3];
			m_lPoint[3] = m_SPoint[4];
			m_lPoint[4] = m_SPoint[5];

			/*
			 * String strLog
			 * =String.format("(%d,%d)(%d,%d)(%d,%d)(%d,%d)(%d,%d)",
			 * m_lPoint[0].getX(),m_lPoint[0].getY(),
			 * m_lPoint[1].getX(),m_lPoint[1].getY(),
			 * m_lPoint[2].getX(),m_lPoint[2].getY(),
			 * m_lPoint[3].getX(),m_lPoint[3].getY(),
			 * m_lPoint[4].getX(),m_lPoint[4].getY());
			 * 
			 * Log.v("draws",strLog);
			 */
			int red = (int) (ulSymColor & 0xff0000) >> 16;
			int green = (int) (ulSymColor & 0x00ff00) >> 8;
			int blue = (int) (ulSymColor & 0x0000ff);

			m_pen.setStyle(Paint.Style.STROKE);
			m_pen.setStrokeWidth(1);
			m_pen.setColor(Color.rgb(red, green, blue));

			m_brush.setStyle(Paint.Style.FILL);
			m_brush.setColor(Color.rgb(red, green, blue));

			m_path.reset();

			m_path.moveTo(m_lPoint[0].getX(), m_lPoint[0].getY());

			for (int j = 1; j < 5; j++)
				m_path.lineTo(m_lPoint[j].getX(), m_lPoint[j].getY());

			// m_path.lineTo(m_lPoint[0].getX(),m_lPoint[0].getY());

			m_path.close();
			frtCanvas.drawPath(m_path, m_brush);

			// m_path.reset();

			m_pen.setStyle(Paint.Style.STROKE);

			m_pen.setStrokeWidth(2);

			m_pen.setColor(Color.rgb(0, 0, 0));

			RectF oval = new RectF();

			oval.set(m_SPoint[0].getX() - 2, m_SPoint[0].getY() - 2,
					m_SPoint[0].getX() + 2, m_SPoint[0].getY() - 2);

			frtCanvas.drawOval(oval, m_pen);
			//

			// draw course ling
			double fVecLong, fVecLat;

			red = (int) (ulVecLnColor & 0xff0000) >> 16;
			green = (int) (ulVecLnColor & 0x00ff00) >> 8;
			blue = (int) (ulVecLnColor & 0x0000ff);

			m_pen.setStyle(Paint.Style.STROKE);
			m_pen.setStrokeWidth(1);
			m_pen.setColor(Color.rgb(red, green, blue));

			pChart.CalculateLongLatByDisDir(fShipCeLong, fShipCeLat,
					(fSpeed / 60) * nVSILen, fCourse, m_tmpDPoint);
			fVecLong = m_tmpDPoint.getX();
			fVecLat = m_tmpDPoint.getY();

			pChart.GeoLongLatToScreenPoint(fVecLong, fVecLat, m_ptVecPoint);
			if (bShipVSI) {
				frtCanvas.drawLine(m_ptDspPoint.getX(), m_ptDspPoint.getY(),
						m_ptVecPoint.getX(), m_ptVecPoint.getY(), m_pen);
			}
			// draw course "->"
			if (fSpeed * 10 > 5 && nVSILen > 0) {
				double tmp1, tmp2;
				tmp1 = fCourse - 150;
				if (tmp1 < 0)
					tmp1 = tmp1 + 360;
				tmp2 = fCourse + 150;
				if (tmp2 >= 360)
					tmp2 = tmp2 - 360;

				int nvsiLen = 15;
				int nvsilen2 = 5;

				double lvsinm = nvsiLen * xPixel * fScale / 1000 / 1852;
				double lvsinm2 = nvsilen2 * xPixel * fScale / 1000 / 1852;

				double tmpx, tmpy;
				pChart.CalculateLongLatByDisDir(fVecLong, fVecLat, lvsinm,
						tmp1, m_tmpDPoint);
				tmpx = m_tmpDPoint.getX();
				tmpy = m_tmpDPoint.getY();

				pChart.GeoLongLatToScreenPoint(tmpx, tmpy, m_ptAngle[0]);

				/*
				 * pChart.CalculateLongLatByDisDir(fLong,fLat,(fSpeed/60)*nVSILen
				 * -lvsinm2,fCourse,m_tmpDPoint); tmpx = m_tmpDPoint.getX();
				 * tmpy = m_tmpDPoint.getY();
				 * pChart.GeoLongLatToScreenPoint(tmpx,tmpy,m_ptAngle[1]);
				 */
				m_ptAngle[1].setX(m_ptVecPoint.getX());
				m_ptAngle[1].setY(m_ptVecPoint.getY());

				pChart.CalculateLongLatByDisDir(fVecLong, fVecLat, lvsinm,
						tmp2, m_tmpDPoint);
				tmpx = m_tmpDPoint.getX();
				tmpy = m_tmpDPoint.getY();
				pChart.GeoLongLatToScreenPoint(tmpx, tmpy, m_ptAngle[2]);

				red = (int) (ulVecLnColor & 0xff0000) >> 16;
				green = (int) (ulVecLnColor & 0x00ff00) >> 8;
				blue = (int) (ulVecLnColor & 0x0000ff);

				m_pen.setStyle(Paint.Style.STROKE);
				m_pen.setStrokeWidth(1);
				m_pen.setColor(Color.rgb(red, green, blue));
				m_path.reset();
				if (bShipVSI) {
					m_path.moveTo(m_ptAngle[0].getX(), m_ptAngle[0].getY());

					for (int j = 1; j < 3; j++)
						m_path.lineTo(m_ptAngle[j].getX(), m_ptAngle[j].getY());
					frtCanvas.drawPath(m_path, m_pen);
				}
			}
			// draw head line.
			if (fHead < 360.0) {
				double fHLong, fHLat;
				pChart.CalculateLongLatByDisDir(fLong, fLat,
						m_fPilotShipHeadLen, fHead, m_tmpDPoint);
				fHLong = m_tmpDPoint.getX();
				fHLat = m_tmpDPoint.getY();

				pChart.GeoLongLatToScreenPoint(fHLong, fHLat, m_ptHeadPoint);

				red = (int) (ulHeadLnColor & 0xff0000) >> 16;
				green = (int) (ulHeadLnColor & 0x00ff00) >> 8;
				blue = (int) (ulHeadLnColor & 0x0000ff);

				m_pen.setStrokeWidth(1);
				m_pen.setColor(Color.rgb(red, green, blue));

				pChart.GeoLongLatToScreenPoint(fVecLong, fVecLat, m_ptVecPoint);

				frtCanvas.drawLine(m_ptDspPoint.getX(), m_ptDspPoint.getY(),
						m_ptHeadPoint.getX(), m_ptHeadPoint.getY(), m_pen);
			}
		}
	}

	public void xhfDrawAisTarget(CAisTarget pAisTarget,
			CHfVCFElectronicChart pChart, CGeoCanvasJv geoCanvas) {
		if (pAisTarget == null || pChart == null)
			return;

		if (!m_bIsShowAisTarget)
			return;

		if (!pAisTarget.PositionIsAvalid())
			return;

		if (pAisTarget.GetTargetState() == 3)
			return;

		Canvas frtCanvas = geoCanvas.GetFrontCanvas();
		if (frtCanvas == null)
			return;

		double fLong, fLat, fCourse, fHead, fSpeed;

		fLong = pAisTarget.GetLong();
		fLat = pAisTarget.GetLat();
		fCourse = pAisTarget.GetCourse();
		fSpeed = pAisTarget.GetSpeed();

		if (fSpeed >= 999.9)
			fSpeed = 0.0;
		fHead = pAisTarget.GetHead();

		pChart.GpsPosOffsetCorrect(fLong, fLat, m_tmpDPoint);
		fLong = m_tmpDPoint.getX();
		fLat = m_tmpDPoint.getY();

		int nState = pAisTarget.GetTargetState();

		double fRot = pAisTarget.GetRot();

		int nVSI = pAisTarget.GetAisVSI();
		//
		double fShapeDir = fCourse;
		if (fHead < 360.0)
			fShapeDir = fHead;
		else
			fShapeDir = fCourse;

		int nSymType = GetAisTargetSymbolStyle(pAisTarget, pChart);

		double fRotatAngle = pChart.GetRotateAngle();

		long ulSymColor = GetAisTargetColor(pAisTarget, pChart);

		double fwidthm = geoCanvas.GetxPixelDPM();

		double fheighm = geoCanvas.GetyPixelDPM();

		int nMinX = 9999999;
		int nMinY = 9999999;
		int nMaxX = -9999999;
		int nMaxY = -9999999;

		int red = (int) (ulSymColor & 0xff0000) >> 16;
		int green = (int) (ulSymColor & 0x00ff00) >> 8;
		int blue = (int) (ulSymColor & 0x0000ff);

		if (nSymType == 0) {
			//

			pChart.GeoLongLatToScreenPoint(fLong, fLat, m_ptDspPoint);

			double fCH = m_fAisTargetSymHeight * 0.7;

			// bevel edge
			double fBle = m_fAisTargetSymHeight
					/ Math.cos((m_fAisTriangleAngle / 2 / 180) * 3.1415926);

			fShapeDir += fRotatAngle;

			int nx0, ny0, nx1, ny1, nx2, ny2;
			nx0 = m_ptDspPoint.getX();
			ny0 = m_ptDspPoint.getY();
			nx0 = nx0
					+ (int) (((fCH / fwidthm) * Math
							.sin(fShapeDir * 3.1415926 / 180.0)));
			ny0 = ny0
					- (int) (((fCH / fheighm) * Math
							.cos(fShapeDir * 3.1415926 / 180.0)));

			m_ptAngle[0].setX(nx0);
			m_ptAngle[0].setY(ny0);

			nx1 = nx0
					+ (int) (((fBle / fwidthm) * Math
							.sin((fShapeDir + 180.0 - m_fAisTriangleAngle / 2) * 3.1415926 / 180.0)));
			ny1 = ny0
					- (int) (((fBle / fheighm) * Math
							.cos((fShapeDir + 180.0 - m_fAisTriangleAngle / 2) * 3.1415926 / 180.0)));
			m_ptAngle[1].setX(nx1);
			m_ptAngle[1].setY(ny1);

			nx2 = nx0
					+ (int) (((fBle / fwidthm) * Math
							.sin((fShapeDir + 180.0 + m_fAisTriangleAngle / 2) * 3.1415926 / 180.0)));
			ny2 = ny0
					- (int) (((fBle / fheighm) * Math
							.cos((fShapeDir + 180.0 + m_fAisTriangleAngle / 2) * 3.1415926 / 180.0)));
			m_ptAngle[2].setX(nx2);
			m_ptAngle[2].setY(ny2);

			nMinX = Math.min(nMinX, nx0);
			nMinX = Math.min(nMinX, nx1);
			nMinX = Math.min(nMinX, nx2);

			nMinY = Math.min(nMinY, ny0);
			nMinY = Math.min(nMinY, ny1);
			nMinY = Math.min(nMinY, ny2);

			nMaxX = Math.max(nMaxX, nx0);
			nMaxX = Math.max(nMaxX, nx1);
			nMaxX = Math.max(nMaxX, nx2);

			nMaxY = Math.max(nMaxY, ny0);
			nMaxY = Math.max(nMaxY, ny1);
			nMaxY = Math.max(nMaxY, ny2);

			m_ptAngle[3] = m_ptAngle[0];

			if (pAisTarget.GetIsSelect()) {

				m_pen.setStyle(Paint.Style.STROKE);
				m_pen.setStrokeWidth(2);
				m_pen.setColor(Color.rgb(red, green, blue));

				m_brush.setStyle(Paint.Style.FILL);
				m_brush.setColor(Color.rgb(red, green, blue));

				m_path.reset();

				m_path.moveTo(m_ptAngle[0].getX(), m_ptAngle[0].getY());

				for (int j = 1; j < 4; j++)
					m_path.moveTo(m_ptAngle[j].getX(), m_ptAngle[j].getY());

				m_path.close();
				frtCanvas.drawPath(m_path, m_brush);

				m_path.reset();

			} else {

				m_pen.setStyle(Paint.Style.STROKE);
				m_pen.setStrokeWidth(3);
				m_pen.setColor(Color.rgb(red, green, blue));

				m_path.reset();

				m_path.moveTo(m_ptAngle[0].getX(), m_ptAngle[0].getY());

				for (int j = 1; j < 4; j++)
					m_path.moveTo(m_ptAngle[j].getX(), m_ptAngle[j].getY());

				frtCanvas.drawPath(m_path, m_pen);

				m_path.reset();

			}

			// if is active
			if (nState == 0) {
				// draw course line
				m_pen.setStyle(Paint.Style.STROKE);
				m_pen.setStrokeWidth(1);
				m_pen.setColor(Color.rgb(red, green, blue));

				m_pen.setPathEffect(m_effects);

				m_pen.setStrokeCap(Paint.Cap.SQUARE);

				// caclatee course vec point
				double fVecLong, fVecLat;

				pChart.CalculateLongLatByDisDir(fLong, fLat,
						fSpeed / 60 * nVSI, fCourse, m_tmpDPoint);
				fVecLong = m_tmpDPoint.getX();
				fVecLat = m_tmpDPoint.getY();

				pChart.GeoLongLatToScreenPoint(fVecLong, fVecLat, m_ptVecPoint);

				frtCanvas.drawLine(m_ptDspPoint.getX(), m_ptDspPoint.getY(),
						m_ptVecPoint.getX(), m_ptVecPoint.getY(), m_pen);

				m_pen.reset();

				// draw head line
				if (fHead < 360) {
					m_pen.setStyle(Paint.Style.STROKE);
					m_pen.setStrokeWidth(1);
					m_pen.setColor(Color.rgb(red, green, blue));

					nx0 = m_ptAngle[0].getX();
					ny0 = m_ptAngle[0].getY();

					nx1 = nx0
							+ (int) (((m_fAisHeadLineLength / fwidthm) * Math
									.sin(fShapeDir * 3.1415926 / 180.0)));
					ny1 = ny0
							- (int) (((m_fAisHeadLineLength / fheighm) * Math
									.cos(fShapeDir * 3.1415926 / 180.0)));

					frtCanvas.drawLine(m_ptDspPoint.getX(),
							m_ptDspPoint.getY(), nx1, ny1, m_pen);

					// draw rot
					if (fRot > 0 && fRot <= 127) {
						nx2 = nx1
								+ (int) (((m_fAisHeadLineLength / 2 / fwidthm) * Math
										.sin((fShapeDir + 90) * 3.1415926 / 180.0)));
						ny2 = ny1
								- (int) (((m_fAisHeadLineLength / 2 / fheighm) * Math
										.cos((fShapeDir + 90) * 3.1415926 / 180.0)));

						frtCanvas.drawLine(nx1, ny1, nx2, ny2, m_pen);

					} else if (fRot < 0 && fRot > -128) {
						nx2 = nx1
								+ (int) (((m_fAisHeadLineLength / 2 / fwidthm) * Math
										.sin((fShapeDir + 270) * 3.1415926 / 180.0)));
						ny2 = ny1
								- (int) (((m_fAisHeadLineLength / 2 / fheighm) * Math
										.cos((fShapeDir + 270) * 3.1415926 / 180.0)));
						frtCanvas.drawLine(nx1, ny1, nx2, ny2, m_pen);
					}

				}
			} else if (nState == 1) {
				m_pen.setStyle(Paint.Style.STROKE);
				m_pen.setStrokeWidth(1);
				m_pen.setColor(Color.rgb(red, green, blue));

				int nscrx = m_ptDspPoint.getX();
				int nscry = m_ptDspPoint.getY();

				nx1 = nscrx
						+ (int) (((fBle / fwidthm) * Math
								.sin((fShapeDir + 270) * 3.1415926 / 180.0)));
				ny1 = nscry
						- (int) (((fBle / fheighm) * Math
								.cos((fShapeDir + 270) * 3.1415926 / 180.0)));
				nx2 = nscrx
						+ (int) (((fBle / fwidthm) * Math
								.sin((fShapeDir + 90) * 3.1415926 / 180.0)));
				ny2 = nscry
						- (int) (((fBle / fheighm) * Math
								.cos((fShapeDir + 90) * 3.1415926 / 180.0)));

				frtCanvas.drawLine(nx1, ny1, nx2, ny2, m_pen);

			}

		} else if (nSymType == 1) {

			int nTxHead, nTxTail, nTxLeft, nTxRight;
			nTxHead = pAisTarget.GetTxHead();
			nTxTail = pAisTarget.GetTxTail();
			nTxLeft = pAisTarget.GetTxLeft();
			nTxRight = pAisTarget.GetTxRight();

			int nW, nL;
			double fWDu, fLDu, fHDu, fRDu, fTDu;

			double fAngle1;

			double flat1, flong1, flat2, flong2, flat3, flong3, flat4, flong4, flat5, flong5;

			double fShipCeLong, fShipCeLat;

			nW = nTxLeft + nTxRight;

			nL = nTxHead + nTxTail;

			pChart.GpsPosCenterCorrect(fLong, fLat, fShapeDir, nTxHead,
					nTxTail, nTxLeft, nTxRight, m_tmpDPoint);
			fShipCeLong = m_tmpDPoint.getX();
			fShipCeLat = m_tmpDPoint.getY();

			fWDu = (double) (nW) / 1852 / 60;
			fRDu = (double) (nTxRight) / 1852 / 60;
			fLDu = (double) (nL) / 1852 / 60;
			fHDu = (double) (nTxHead) / 1852 / 60;
			fTDu = (double) (nTxTail) / 1852 / 60;

			// calcate 1/4 (long,lat)
			double ftmpLong, ftmpLat;
			if (pChart.GetGpsPosCorrectMode() == 1) {

				double fLenEdge1 = Math.sqrt(Math.pow(fWDu / 2, 2.0)
						+ Math.pow(fLDu / 2, 2.0));

				fAngle1 = Math.atan((fWDu / 2) / (fLDu / 2)) * 180 / 3.1415926;

				pChart.CalculateLongLatByDisDir(fShipCeLong, fShipCeLat,
						(fLDu / 2) * 60, fShapeDir, m_tmpDPoint);
				flat1 = m_tmpDPoint.getY();
				flong1 = m_tmpDPoint.getX();

				pChart.CalculateLongLatByDisDir(fShipCeLong, fShipCeLat,
						(fLDu / 4) * 60, fShapeDir, m_tmpDPoint);
				ftmpLong = m_tmpDPoint.getX();
				ftmpLat = m_tmpDPoint.getY();

				pChart.CalculateLongLatByDisDir(ftmpLong, ftmpLat,
						fWDu / 2 * 60, fShapeDir - 90, m_tmpDPoint);
				flat2 = m_tmpDPoint.getY();
				flong2 = m_tmpDPoint.getX();

				pChart.CalculateLongLatByDisDir(fShipCeLong, fShipCeLat,
						fLenEdge1 * 60, 180 + fAngle1 + fShapeDir, m_tmpDPoint);
				flat3 = m_tmpDPoint.getY();
				flong3 = m_tmpDPoint.getX();

				pChart.CalculateLongLatByDisDir(fShipCeLong, fShipCeLat,
						fLenEdge1 * 60, fShapeDir + 180 - fAngle1, m_tmpDPoint);
				flat4 = m_tmpDPoint.getY();
				flong4 = m_tmpDPoint.getX();

				pChart.CalculateLongLatByDisDir(ftmpLong, ftmpLat,
						fWDu / 2 * 60, fShapeDir + 90, m_tmpDPoint);

				flat5 = m_tmpDPoint.getY();
				flong5 = m_tmpDPoint.getX();
			} else {
				double fLenEdge1 = Math.sqrt(Math.pow(fWDu / 2, 2.0)
						+ Math.pow(fTDu, 2.0));

				fAngle1 = Math.atan((fWDu / 2) / fTDu) * 180 / 3.1415926;

				pChart.CalculateLongLatByDisDir(fShipCeLong, fShipCeLat,
						fHDu * 60, fShapeDir, m_tmpDPoint);

				flat1 = m_tmpDPoint.getY();
				flong1 = m_tmpDPoint.getX();

				if (fLDu / 4 >= fHDu)
					pChart.CalculateLongLatByDisDir(fShipCeLong, fShipCeLat,
							(fLDu / 4 - fHDu) * 60, fShapeDir + 180,
							m_tmpDPoint);
				else
					pChart.CalculateLongLatByDisDir(fShipCeLong, fShipCeLat,
							(fHDu - fLDu / 4) * 60, fShapeDir, m_tmpDPoint);

				ftmpLong = m_tmpDPoint.getX();
				ftmpLat = m_tmpDPoint.getY();

				pChart.CalculateLongLatByDisDir(ftmpLong, ftmpLat,
						fWDu / 2 * 60, fShapeDir - 90, m_tmpDPoint);
				flat2 = m_tmpDPoint.getY();
				flong2 = m_tmpDPoint.getX();

				pChart.CalculateLongLatByDisDir(fShipCeLong, fShipCeLat,
						fLenEdge1 * 60, 180 + fAngle1 + fShapeDir, m_tmpDPoint);

				flat3 = m_tmpDPoint.getY();
				flong3 = m_tmpDPoint.getX();

				pChart.CalculateLongLatByDisDir(fShipCeLong, fShipCeLat,
						fLenEdge1 * 60, fShapeDir + 180 - fAngle1, m_tmpDPoint);

				flat4 = m_tmpDPoint.getY();
				flong4 = m_tmpDPoint.getX();

				pChart.CalculateLongLatByDisDir(ftmpLong, ftmpLat,
						fWDu / 2 * 60, fShapeDir + 90, m_tmpDPoint);
				flat5 = m_tmpDPoint.getY();
				flong5 = m_tmpDPoint.getX();

			}

			pChart.GeoLongLatToScreenPoint(fShipCeLong, fShipCeLat, m_SPoint[0]);
			pChart.GeoLongLatToScreenPoint(flong1, flat1, m_SPoint[1]);

			pChart.GeoLongLatToScreenPoint(flong2, flat2, m_SPoint[2]);
			pChart.GeoLongLatToScreenPoint(flong3, flat3, m_SPoint[3]);
			pChart.GeoLongLatToScreenPoint(flong4, flat4, m_SPoint[4]);
			pChart.GeoLongLatToScreenPoint(flong5, flat5, m_SPoint[5]);

			m_lPoint[0] = m_SPoint[1];
			m_lPoint[1] = m_SPoint[2];
			m_lPoint[2] = m_SPoint[3];
			m_lPoint[3] = m_SPoint[4];
			m_lPoint[4] = m_SPoint[5];

			int nx0, ny0, nx1, ny1, nx2, ny2, nx3, ny3, nx4, ny4;
			nx0 = m_lPoint[0].getX();
			ny0 = m_lPoint[0].getY();
			nx1 = m_lPoint[0].getX();
			ny1 = m_lPoint[0].getY();
			nx2 = m_lPoint[0].getX();
			ny2 = m_lPoint[0].getY();
			nx3 = m_lPoint[0].getX();
			ny3 = m_lPoint[0].getY();
			nx4 = m_lPoint[0].getX();
			ny4 = m_lPoint[0].getY();

			nMinX = Math.min(nMinX, nx0);
			nMinX = Math.min(nMinX, nx1);
			nMinX = Math.min(nMinX, nx2);
			nMinX = Math.min(nMinX, nx3);
			nMinX = Math.min(nMinX, nx4);

			nMinY = Math.min(nMinY, ny0);
			nMinY = Math.min(nMinY, ny1);
			nMinY = Math.min(nMinY, ny2);
			nMinY = Math.min(nMinY, ny3);
			nMinY = Math.min(nMinY, ny4);

			nMaxX = Math.max(nMaxX, nx0);
			nMaxX = Math.max(nMaxX, nx1);
			nMaxX = Math.max(nMaxX, nx2);
			nMaxX = Math.max(nMaxX, nx3);
			nMaxX = Math.max(nMaxX, nx4);

			nMaxY = Math.max(nMaxY, ny0);
			nMaxY = Math.max(nMaxY, ny1);
			nMaxY = Math.max(nMaxY, ny2);
			nMaxY = Math.max(nMaxY, ny3);
			nMaxY = Math.max(nMaxY, ny4);

			red = (int) (ulSymColor & 0xff0000) >> 16;
			green = (int) (ulSymColor & 0x00ff00) >> 8;
			blue = (int) (ulSymColor & 0x0000ff);

			m_pen.setStrokeWidth(2);

			RectF oval = new RectF();

			oval.set(m_SPoint[0].getX() - 2, m_SPoint[0].getY() - 2,
					m_SPoint[0].getX() + 2, m_SPoint[0].getY());

			frtCanvas.drawOval(oval, m_brush);

			if (pAisTarget.GetIsSelect()) {

				m_pen.setStyle(Paint.Style.STROKE);
				m_pen.setStrokeWidth(1);
				m_pen.setColor(Color.rgb(red, green, blue));

				m_brush.setStyle(Paint.Style.FILL);
				m_brush.setColor(Color.rgb(red, green, blue));

				m_path.reset();

				m_path.moveTo(m_lPoint[0].getX(), m_lPoint[0].getY());

				for (int j = 1; j < 5; j++)
					m_path.moveTo(m_lPoint[j].getX(), m_lPoint[j].getY());

				m_path.close();
				frtCanvas.drawPath(m_path, m_brush);

				m_path.reset();

			} else {
				m_pen.setStyle(Paint.Style.STROKE);
				m_pen.setStrokeWidth(1);
				m_pen.setColor(Color.rgb(red, green, blue));

				m_brush.setStyle(Paint.Style.FILL);
				m_brush.setColor(Color.rgb(red, green, blue));

				m_path.reset();

				m_path.moveTo(m_lPoint[0].getX(), m_lPoint[0].getY());

				for (int j = 1; j < 5; j++)
					m_path.moveTo(m_lPoint[j].getX(), m_lPoint[j].getY());

				frtCanvas.drawPath(m_path, m_pen);

				m_path.reset();

			}

			if (nState == 0) {
				double fVecLong, fVecLat;

				pChart.CalculateLongLatByDisDir(fLong, fLat, (fSpeed / 60)
						* nVSI, fCourse, m_tmpDPoint);
				fVecLong = m_tmpDPoint.getX();
				fVecLat = m_tmpDPoint.getY();

				pChart.GeoLongLatToScreenPoint(fVecLong, fVecLat, m_ptDspPoint);
				nx1 = m_ptDspPoint.getX();
				ny1 = m_ptDspPoint.getY();

				pChart.GeoLongLatToScreenPoint(fLong, fLat, m_ptDspPoint);
				nx2 = m_ptDspPoint.getX();
				ny2 = m_ptDspPoint.getY();

				// draw course line
				m_pen.setStyle(Paint.Style.STROKE);
				m_pen.setStrokeWidth(2);
				m_pen.setColor(Color.rgb(red, green, blue));

				m_pen.setPathEffect(m_effects);

				m_pen.setStrokeCap(Paint.Cap.SQUARE);

				frtCanvas.drawLine(nx2, ny2, nx1, ny1, m_pen);

				m_pen.reset();

				if (fHead < 360) {
					int nhx, nhy;

					nx1 = m_lPoint[1].getX();
					ny1 = m_lPoint[1].getY();

					nhx = nx1
							+ (int) (((m_fAisHeadLineLength / fwidthm) * Math
									.sin(fHead * 3.1415926 / 180.0)));
					nhy = ny1
							- (int) (((m_fAisHeadLineLength / fheighm) * Math
									.cos(fHead * 3.1415926 / 180.0)));

					m_pen.setStyle(Paint.Style.STROKE);
					m_pen.setStrokeWidth(1);
					m_pen.setColor(Color.rgb(red, green, blue));

					frtCanvas.drawLine(nx1, ny1, nhx, nhy, m_pen);

					if (fRot > 0 && fRot <= 127) {
						nx1 = nhx
								+ (int) (((m_fAisHeadLineLength / 2 / fwidthm) * Math
										.sin((fHead + 90) * 3.1415926 / 180.0)));
						ny1 = nhy
								- (int) (((m_fAisHeadLineLength / 2 / fheighm) * Math
										.cos((fHead + 90) * 3.1415926 / 180.0)));

						frtCanvas.drawLine(nhx, nhy, nx1, ny1, m_pen);

					} else if (fRot < 0 && fRot > -128) {
						nx1 = nhx
								+ (int) (((m_fAisHeadLineLength / 2 / fwidthm) * Math
										.sin((fHead + 270) * 3.1415926 / 180.0)));
						ny1 = nhy
								- (int) (((m_fAisHeadLineLength / 2 / fheighm) * Math
										.cos((fHead + 270) * 3.1415926 / 180.0)));
						frtCanvas.drawLine(nhx, nhy, nx1, ny1, m_pen);

					}

				}
			} else if (nState == 1) {

				m_pen.setStyle(Paint.Style.STROKE);
				m_pen.setStrokeWidth(2);
				m_pen.setColor(Color.rgb(red, green, blue));

				double fLoseLine = m_fAisSelectLength + 1;

				nx1 = m_SPoint[0].getX()
						+ (int) (((fLoseLine / fwidthm) * Math
								.sin((fShapeDir + 270) * 3.1415926 / 180.0)));
				ny1 = m_SPoint[0].getY()
						- (int) (((fLoseLine / fheighm) * Math
								.cos((fShapeDir + 270) * 3.1415926 / 180.0)));
				nx2 = m_SPoint[0].getX()
						+ (int) (((fLoseLine / fwidthm) * Math
								.sin((fShapeDir + 90) * 3.1415926 / 180.0)));
				ny2 = m_SPoint[0].getY()
						- (int) (((fLoseLine / fheighm) * Math
								.cos((fShapeDir + 90) * 3.1415926 / 180.0)));

				frtCanvas.drawLine(nx1, ny1, nx2, ny2, m_pen);

			}

		}

		//
		int nmrkx, nmrky;
		nmrkx = nMaxX;
		nmrky = nMinY;
		String strName = pAisTarget.GetShipName();

		String strCall = pAisTarget.GetShipCallNo();

		String strTargetid = pAisTarget.GetTargetId();

		String strMark = "";
		if (m_bAisTargetIdMrk && !strTargetid.isEmpty()) {
			strMark += strTargetid;
		}
		if (!strName.isEmpty() && m_bAisNameCallMrk) {
			if (!strMark.isEmpty())
				strMark += "$";
			strMark += strName;
		}
		if (!strCall.isEmpty() && m_bAisOnlyCallMrk) {
			if (!strMark.isEmpty())
				strMark += "$";
			strMark += strCall;
		}

		red = (int) (ulSymColor & 0xff0000) >> 16;
		green = (int) (ulSymColor & 0x00ff00) >> 8;
		blue = (int) (ulSymColor & 0x0000ff);

		frtCanvas.drawText(strMark, nmrkx, nmrky, m_textPaint);

	}

	//
	public long GetPilotShipColor(CPilotShip pPilotShip,
			CHfVCFElectronicChart pChart) {

		long ulColor = CUtilityTool.RGBToLong(0, 72, 118);

		if (pPilotShip == null || pChart == null)
			return ulColor;

		if (pPilotShip.GetPosType() == 0 || pPilotShip.GetPosType() == 1) {
			ulColor = CUtilityTool.RGBToLong(0, 72, 118);
			;// RGB(9,2,255);;
			return ulColor;
		} else if (pPilotShip.GetPosType() == 2) {
			ulColor = CUtilityTool.RGBToLong(255, 0, 0);
			return ulColor;
		}

		return ulColor;

	}

	public int GetPilotShipSymbolStyle(CPilotShip pPilotShip,
			CHfVCFElectronicChart pChart) {

		int nSymStyle = 0;

		if (pPilotShip == null || pChart == null)
			return nSymStyle;

		if (pPilotShip.ShipStaticParamterIsAvalid()) {

			int nTxHead, nTxTail;

			nTxHead = pPilotShip.GetTxHead();
			nTxTail = pPilotShip.GetTxTail();

			int nShipLen = nTxHead + nTxTail;

			double fDspScale = pChart.GetChartDspScale();

			double fTempL = (double) nShipLen / fDspScale;

			double fShipLenmm = fTempL * 1000.0; // 转为mm

			if (fShipLenmm >= m_fPilotShipSymSize)
				nSymStyle = 1;
			else
				nSymStyle = 0;

		} else
			nSymStyle = 0;

		return nSymStyle;

	}

	//
	public long GetAisTargetColor(CAisTarget pAisTarget,
			CHfVCFElectronicChart pChart) {

		long ulColor = CUtilityTool.RGBToLong(172, 82, 0);

		if (pAisTarget == null || pChart == null)
			return ulColor;
		if (pAisTarget.GetIsCPAAlarm()) {
			ulColor = CUtilityTool.RGBToLong(128, 0, 0);//
			return ulColor;
		}

		if (pAisTarget.GetTargetState() == 1) {
			ulColor = CUtilityTool.RGBToLong(153, 51, 0);
			return ulColor;
		}

		if (pAisTarget.GetIsSelect())
			ulColor = CUtilityTool.RGBToLong(0, 72, 0);
		else
			ulColor = CUtilityTool.RGBToLong(0, 72, 118);// RGB(0,200,0);//

		return ulColor;

	}

	public int GetAisTargetSymbolStyle(CAisTarget pAisTarget,
			CHfVCFElectronicChart pChart) {

		int nSymStyle = 0;

		if (pAisTarget == null || pChart == null)
			return nSymStyle;

		if (pAisTarget.StaticParamterIsAvalid()) {
			int nTxHead, nTxTail, nTxLeft, nTxRight;
			nTxHead = pAisTarget.GetTxHead();
			nTxTail = pAisTarget.GetTxTail();
			nTxLeft = pAisTarget.GetTxLeft();
			nTxRight = pAisTarget.GetTxRight();

			int nShipLen = nTxHead + nTxTail;

			double fDspScale = pChart.GetChartDspScale();

			double fTempL = (double) nShipLen / fDspScale;

			double fShipLenmm = fTempL * 1000.0;

			if (fShipLenmm >= m_fAisTargetSymHeight * 2)
				nSymStyle = 1;
			else
				nSymStyle = 0;
		} else
			nSymStyle = 0;

		return nSymStyle;
	}

}
