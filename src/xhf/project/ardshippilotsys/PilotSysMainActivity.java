package xhf.project.ardshippilotsys;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.Switch;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ToggleButton;
import xhf.project.chartcore.*;
import xhf.project.chartcorejni.CHfVCFElectronicChart;
import xhf.project.chartcorejni.emCHART_OPERATION_MODE;
import xhf.project.chartcorejni.stVCFGEODATASETDESCR;
import xhf.project.datamanage.CShipStaticData;
import xhf.project.dynamictarget.CAisTarget;
import xhf.project.dynamictarget.CPilotShip;
import xhf.project.routemanage.CPlanRouteManage;
import xhf.project.sensorctl.CPilotSensorControl;
import xhf.project.sensorctl.CSensorDataFlowParase;
import xhf.project.sensorctl.CSockCommConfig;
import xhf.project.sensorctl.DeviceListActivity;
import xhf.project.utilitytool.CLatLongDM;
import xhf.project.utilitytool.CLatLongDMS;
import xhf.project.utilitytool.COleDateTime;
import xhf.project.utilitytool.CUtilityTool;

public class PilotSysMainActivity extends Activity {

	ChartCoreView m_ChartCoreView = null;

	COPToolbarView m_OPToolbarView = null;

	Button m_ChartOpBtn = null;

	ImageButton m_ZoominOpBtn = null;

	ImageButton m_ZoomoutOpBtn = null;

	ImageButton m_MeasureOpBtn = null;

	ImageButton m_ShipcenOpBtn = null;

	Button m_AisOpBtn = null;

	Button m_RouteOpBtn = null;

	Button m_SyssetOpBtn = null;

	Button m_pilotageBtn = null;

//	Button m_ExitSysOpBtn = null;

	PopupMenu m_ChartMenu = null;

	PopupMenu m_SensorMenu = null;

	PopupMenu m_PilotDataMenu = null;

	PopupMenu m_RouteMenu = null;

	// pilot data
	TextView m_textLogo = null;
	TextView m_textposState = null;
	TextView m_textConnState = null;
	TextView m_shiptimeText = null;
	TextView m_shiplattext = null;
	TextView m_shiplongtext = null;
	TextView m_shipcoursetext = null;
	TextView m_shipspeedtext = null;
	TextView m_shipheadtext = null;
	TextView m_shipxtetext = null;
	TextView m_shiptimelabel = null;
	TextView m_shiplatlabel = null;
	TextView m_shiplonglabel = null;
	TextView m_shipcourselabel = null;
	TextView m_shipspeedlabel = null;
	TextView m_shipheadlabel = null;
	TextView m_shipxtelabel = null;
	View m_pilotdataview = null;
	// 引航
	TextView m_pilottageview = null;
	TextView m_pilotcourse = null;
	TextView m_pilotspeed = null;
	TextView m_pilotxte = null;
	TextView m_pilotlat = null;
	TextView m_pilotlon = null;
	TextView m_pilotjzxfw = null;
	TextView m_measurehidid = null;

	//
	View m_topbarview = null;
	// TextView m_tbarConnState = null;
	View m_tbarConnState = null;
	TextView m_tbartimeText = null;
	TextView m_tbarcoursetext = null;
	TextView m_tbarspeedtext = null;
	TextView m_tbarxtetext = null;

	CTopDataBarView m_TopDataBarView = null;
	Button m_drawrouteBtn = null;

	Button m_delrouteBtn = null;

	Button m_updaterouteBtn = null;

	Button m_saverouteBtn = null;

	Button m_openrouteBtn = null;

	Button m_exitrouteBtn = null;
	//
	private Timer m_mTimer;
	private TimerTask m_mTimerTask;
	private Handler m_mTimerHandler;
	//
	ChartFileSelectDlg m_ChartFileSelectDlg = null;
	ChartDisplayDlg m_ChartDisplayDlg = null;

	ChartMenuDlg m_ChartMenuDlg = null;

	SysSettingMenuDlg m_SysSettingMenuDlg = null;

	PilotDataMenuDlg m_PilotDataMenuDlg = null;

	CRouteMenuDlg m_RouteMenuDlg = null;

	private boolean m_bOwnShipDataDlgShow = false;
	private boolean m_bRouteEditDlgShow = false;

	AisTargetListDlg m_AisTargetListDlg = null;

	ShipParamterSetDlg m_ShipParamterSetDlg = null;
	SocketConfigDlg m_SocketConfigDlg = null;

	COpenPlanRouteDlg m_OpenPlanRouteDlg = null;

	LayoutInflater inflater = null;
	RelativeLayout mainLayout = null;
	RelativeLayout settingPilotage = null;

	RelativeLayout settingLayout = null;

	public static CHfVCFElectronicChart m_chartCore = null;

	public static CPilotSensorControl m_PilotSensorControl;
	RelativeLayout aisListRel = null;

	RelativeLayout aisShipInfoLin = null;

	ToggleButton aisdlg_jvxs_togbtn = null;

	RelativeLayout berthingview = null;

	Button berthingbtn = null;


	@SuppressLint("CutPasteId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.pilotsysmain);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

		m_ChartCoreView = (ChartCoreView) this.findViewById(R.id.chartview);

		m_OPToolbarView = (COPToolbarView) this.findViewById(R.id.toolbarview);

		m_OPToolbarView.setBackgroundColor(Color.rgb(232, 244, 237));

		m_ChartOpBtn = (Button) findViewById(R.id.imgChartOpBtn);

		m_ChartOpBtn.setOnClickListener(chartOpBtnFunc);

		m_ZoominOpBtn = (ImageButton) findViewById(R.id.imgZoominOpBtn);

		// m_ZoominOpBtn.setOnClickListener(ZoominOpBtnFunc);
		m_ZoominOpBtn.setOnTouchListener(ZoominOpBtnTouch);

		m_ZoomoutOpBtn = (ImageButton) findViewById(R.id.imgZoomoutOpBtn);

		// m_ZoomoutOpBtn.setOnClickListener(ZoomoutOpBtnFunc);
		m_ZoomoutOpBtn.setOnTouchListener(ZoomoutOpBtnTouch);

		m_MeasureOpBtn = (ImageButton) findViewById(R.id.imgCeliangOpBtn);
		//m_MeasureOpBtn.getBackground().setAlpha(255);//设置半透明
		m_MeasureOpBtn.setOnClickListener(MeasureOpBtnFunc);

		m_measurehidid = (TextView) findViewById(R.id.measurehidid);

		m_ShipcenOpBtn = (ImageButton) findViewById(R.id.imgShipCOpBtn);
		m_ShipcenOpBtn.setOnClickListener(ShipCenOpBtnFunc);

		m_AisOpBtn = (Button) findViewById(R.id.imgAisBnt);

		m_AisOpBtn.setOnClickListener(berthingbtnFunc);
		
		//berthingbtn = (Button) findViewById(R.id.imgAisBnt);
		//berthingbtn.setOnClickListener(berthingbtnFunc);

		m_RouteOpBtn = (Button) findViewById(R.id.imgRouteBtn);

		m_RouteOpBtn.setOnClickListener(AisOpBtnFunc);

		m_SyssetOpBtn = (Button) findViewById(R.id.imgSyssetBtn);

		m_SyssetOpBtn.setOnClickListener(SysSetOpBtnFunc);

		m_pilotageBtn = (Button) findViewById(R.id.pilotageyh);

		m_pilotageBtn.setOnClickListener(SysPilotageFunc);

	//	m_ExitSysOpBtn = (Button) findViewById(R.id.imgExitSysBtn);

	//	m_ExitSysOpBtn.setOnClickListener(ExitSysOpBtnFunc);

		//
		m_textLogo = (TextView) findViewById(R.id.logotextView);
		m_textposState = (TextView) findViewById(R.id.posstatetextView);
		m_textConnState = (TextView) findViewById(R.id.conntextView);

		m_shiptimeText = (TextView) findViewById(R.id.shiptimetext);
	//	m_shiplattext = (TextView) findViewById(R.id.shiplattext);

	//	m_shiplongtext = (TextView) findViewById(R.id.shiplongtext);
		m_shipcoursetext = (TextView) findViewById(R.id.shipcoursetext);

		m_shipspeedtext = (TextView) findViewById(R.id.shipspeedtext);

		m_shipheadtext = (TextView) findViewById(R.id.shipheadtext);

		m_shipxtetext = (TextView) findViewById(R.id.shipxtetext);

		m_shiptimelabel = (TextView) findViewById(R.id.shiptimeLabel);
		m_shiplatlabel = (TextView) findViewById(R.id.shiplatLabel);

		m_shiplonglabel = (TextView) findViewById(R.id.shiplongLabel);
		m_shipcourselabel = (TextView) findViewById(R.id.shipcourseLabel);

		m_shipspeedlabel = (TextView) findViewById(R.id.shipSpeedLabel);
		m_shipheadlabel = (TextView) findViewById(R.id.shipHeadLabel);

		m_shipxtelabel = (TextView) findViewById(R.id.shipxteLabel);

		m_pilotdataview = (View) findViewById(R.id.pilotdataview);

		m_topbarview = (View) findViewById(R.id.topbarview1);

		if (m_topbarview != null) {
			m_topbarview.setBackgroundColor(Color.argb(89, 90, 89, 0));
			// m_topbarview.setBackgroundColor(Color.rgb(89, 90, 89));
		}

		m_tbarConnState = (View) findViewById(R.id.tbrconnView1);

		// m_tbartimeText = (TextView)findViewById(R.id.tbrtimeView1);

		m_tbarcoursetext = (TextView) findViewById(R.id.tbrcoursetxView1);

		m_tbarspeedtext = (TextView) findViewById(R.id.tbrspeedtxView1);

		m_tbarxtetext = (TextView) findViewById(R.id.tbrxtetxView1);

		m_TopDataBarView = (CTopDataBarView) findViewById(R.id.routeeditview1);

		m_drawrouteBtn = (Button) findViewById(R.id.drawroutebtn1);
		m_drawrouteBtn.setOnClickListener(drawRouteBtnFunc);

		m_delrouteBtn = (Button) findViewById(R.id.delroutebtn);
		m_delrouteBtn.setOnClickListener(delrouteBtnFunc);

		m_updaterouteBtn = (Button) findViewById(R.id.updateroutebtn);
		m_updaterouteBtn.setOnClickListener(updaterouteBtnFunc);

		m_saverouteBtn = (Button) findViewById(R.id.saveroutebtn);
		m_saverouteBtn.setOnClickListener(saverouteBtnFunc);

		m_openrouteBtn = (Button) findViewById(R.id.openroutebtn);
		m_openrouteBtn.setOnClickListener(openrouteBtnFunc);

		m_exitrouteBtn = (Button) findViewById(R.id.closeroutebtn);
		m_exitrouteBtn.setOnClickListener(closerouteBtnFunc);

		// 增加设置layout
		inflater = LayoutInflater.from(this);
		mainLayout = (RelativeLayout) findViewById(R.id.pilotsysmainLayout);
		settingPilotage = (RelativeLayout) inflater.inflate(
				R.layout.pilotageview, null).findViewById(R.id.pilotageview);
		mainLayout.addView(settingPilotage);

		settingLayout = (RelativeLayout) inflater.inflate(R.layout.settingview,
				null).findViewById(R.id.settingLayout);
		mainLayout.addView(settingLayout);
		aisListRel = (RelativeLayout) inflater.inflate(
				R.layout.aistargetlistdlg, null).findViewById(
				R.id.aislistLayout);

		aisShipInfoLin = (RelativeLayout) inflater.inflate(
				R.layout.ais_ship_info_view, null).findViewById(
				R.id.ais_ship_info_view);

		berthingview = (RelativeLayout) inflater.inflate(R.layout.berthingview,
				null);

		mainLayout.addView(aisListRel);

		aisListRel.addView(aisShipInfoLin);

		mainLayout.addView(berthingview);

		aisdlg_jvxs_togbtn = (ToggleButton) findViewById(R.id.aisdlg_jvxs_togbtn);

		aisdlg_jvxs_togbtn.setOnCheckedChangeListener(aisdlgJvxsTogbtnChange);



		// 蓝牙绑定方法
		ToggleButton m_lanyaSwitch = (ToggleButton) settingLayout
				.findViewById(R.id.lanyaSwitch);
		m_lanyaSwitch.setOnCheckedChangeListener(openBluetoothSetting);

		// 海图选择绑定方法
		TextView haituText = (TextView) settingLayout
				.findViewById(R.id.haituText);
		TextView haituSelect = (TextView) settingLayout
				.findViewById(R.id.haituSelect);
		ImageView haituImg = (ImageView) settingLayout
				.findViewById(R.id.haituImg);
		haituText.setOnClickListener(chartFileSelect);
		haituSelect.setOnClickListener(chartFileSelect);
		haituImg.setOnClickListener(chartFileSelect);
		// 航路选择绑定方法
		TextView hangluText = (TextView) settingLayout
				.findViewById(R.id.hangluText);
		TextView hangluList = (TextView) settingLayout
				.findViewById(R.id.hangluSelect);
		ImageView hangluImg = (ImageView) settingLayout
				.findViewById(R.id.hangluImg);
		hangluText.setOnClickListener(transferRouteSelect);
		hangluList.setOnClickListener(transferRouteSelect);
		hangluImg.setOnClickListener(transferRouteSelect);

		m_textLogo.setText("船舶引航");
		m_textposState.setText("DN");
		m_textConnState.setText("");
		m_shiptimeText.setText("");
		//m_shiplattext.setText("");
		//m_shiplongtext.setText("");
		m_shipcoursetext.setText("");
		m_shipspeedtext.setText("");
		m_shipheadtext.setText("");
		m_shipxtetext.setText("");

		// 引航
		m_pilottageview = (TextView) findViewById(R.id.pilottageview_sx_text);
		m_pilottageview.setText("");
		m_pilotcourse = (TextView) findViewById(R.id.pilottageview_hx_text);
		m_pilotcourse.setText("");
		m_pilotspeed = (TextView) findViewById(R.id.pilottageview_hs_text);
		m_pilotspeed.setText("");
		m_pilotxte = (TextView) findViewById(R.id.pilottageview_ph_text);
		m_pilotxte.setText("");
		m_pilotlat = (TextView) findViewById(R.id.pilottageview_wd_text);
		m_pilotlat.setText("");
		m_pilotlon = (TextView) findViewById(R.id.pilottageview_jd_text);
		m_pilotlon.setText("");
		m_pilotjzxfw = (TextView) findViewById(R.id.pilottageview_jzxfw_text);
		m_pilotjzxfw.setText("");

		// m_tbarConnState.setText("");
		m_tbarConnState = (View) findViewById(R.id.tbrconnView1);
		// m_tbartimeText.setText("");
		m_tbarcoursetext.setText("");
		m_tbarspeedtext.setText("");
		m_tbarxtetext.setText("");

		m_PilotSensorControl = new CPilotSensorControl(this);

		m_PilotSensorControl.Initialize();

		Log.v("onCreate", "onCreate");
		// 创建菜单
		// DisplayMetrics dm =
		// this.getApplicationContext().getResources().getDisplayMetrics();
		// m_AisTargetListDlg = new AisTargetListDlg(this,
		// R.layout.aistargetlistdlg,dm.widthPixels,dm.heightPixels,R.style.Theme_dialog);
		// /menu
		// 1.chart menu
		CreateChartMenu();
		// m_ChartMenuDlg = new ChartMenuDlg(this,
		// R.layout.chartmenumdlg,dm.widthPixels,dm.heightPixels,R.style.Theme_dialog);
		// 6.
		CreateSensorMenu();

		CreatePilotDataMenu();

		CreateRouteMenu();

		// m_SysSettingMenuDlg = new SysSettingMenuDlg(this,
		// R.layout.syssettingmenudlg,dm.widthPixels,dm.heightPixels,R.style.Theme_dialog);
		// 3
		// m_PilotDataMenuDlg = new PilotDataMenuDlg(this,
		// R.layout.pilotdatamenudlg,dm.widthPixels,dm.heightPixels,R.style.Theme_dialog);

		// m_RouteMenuDlg = new CRouteMenuDlg(this,
		// R.layout.routemenudlg,dm.widthPixels,dm.heightPixels,R.style.Theme_dialog);

		//
		// m_ChartFileSelectDlg = new ChartFileSelectDlg(this,
		// R.layout.chartfileselect,dm.widthPixels,dm.heightPixels,R.style.Theme_dialog);

		// m_ChartDisplayDlg = new ChartDisplayDlg(this,
		// R.layout.chartdisplaydlg,dm.widthPixels,dm.heightPixels,R.style.Theme_dialog);

		// m_ShipParamterSetDlg = new ShipParamterSetDlg(this,
		// R.layout.shipparamtersetdlg,dm.widthPixels,dm.heightPixels,R.style.Theme_dialog);

		// m_SocketConfigDlg = new SocketConfigDlg(this,
		// R.layout.socketconfigdlg,dm.widthPixels,dm.heightPixels,R.style.Theme_dialog);

		// m_OpenPlanRouteDlg = new COpenPlanRouteDlg(this,
		// R.layout.openroutedlg,dm.widthPixels,dm.heightPixels,R.style.Theme_dialog);

		// timer
		m_mTimerHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
				case 1: {

					if (m_ChartCoreView != null) {
						m_ChartCoreView.DynaTargetShowTimerFlush();

						if (m_ChartCoreView.PilotShipDataChangeUpdate()) {
							// update data
							PilotShipDataWinRTimeShow();
						}

						if (m_ChartCoreView.AisTargetDataChangeUpdate()) {

							// update ais;
						}
					}
					//
					systemTimeRTimeShow();

					m_PilotSensorControl.hfSensorStateRTimeMonitor();
					if (m_PilotSensorControl.GetGpsFaultAlarm()) {
						if (m_textConnState != null)
							m_textConnState.setText("未联机1");

						// m_tbarConnState.setText("未联机");
						m_tbarConnState
								.setBackgroundResource(R.drawable.offonline);
					} else {
						if (m_textConnState != null)
							m_textConnState.setText(" 联机1");
						// m_tbarConnState.setText(" 联机");
						m_tbarConnState
								.setBackgroundResource(R.drawable.online2);

					}
					// g_pkSystemAlarmManage.xhfAlarmTimeout();
				}
					break;
				default:
					break;
				}
			}
		};

		// CreateTimerTack();

	}

	//
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pilot_sys_main, menu);
		return true;
	}

	public void CreateTimerTack() {

		if (m_mTimer != null)
			m_mTimer.cancel();

		m_mTimer = new Timer();
		m_mTimerTask = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				m_mTimerHandler.sendEmptyMessage(1);
			}
		};

		m_mTimer.schedule(m_mTimerTask, 1000, 1000);

	}

	private OnClickListener chartOpBtnFunc = new OnClickListener() {
		public void onClick(View v) {
			Log.i("optool", "chartop");
			RadioButton nightradio = (RadioButton) settingLayout
					.findViewById(R.id.nightradio);
			if(nightradio.isChecked()){
				//设置晚上菜单按钮颜色
				m_ChartOpBtn.setTextColor(Color.rgb(86,214,131));
				m_AisOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_RouteOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_SyssetOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_pilotageBtn.setTextColor(Color.rgb(255, 255, 255));
			}else{
				m_ChartOpBtn.setTextColor(Color.rgb(66,185,236));
				m_AisOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_RouteOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_SyssetOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_pilotageBtn.setTextColor(Color.rgb(255, 255, 255));
			}
			setChartViewShow(View.VISIBLE);
			setPilotage(View.GONE);
			setSettingShowHid(View.GONE);
			setAisListShow(View.GONE);
			setBerthingViewShow(View.GONE);

			// if(m_ChartMenu!=null)
			// {
			// m_ChartMenu.show();
			// }
			// else
			// {
			// CreateChartMenu();
			// m_ChartMenu.show();
			// }

			/*
			 * if(m_ChartMenuDlg!=null) { m_ChartMenuDlg.show(); }
			 */
		}
	};

	private void CreateChartMenu() {
		// 1.chart menu
		m_ChartMenu = new PopupMenu(PilotSysMainActivity.this, m_ChartOpBtn);
		m_ChartMenu.getMenuInflater().inflate(R.menu.chartmenu,m_ChartMenu.getMenu());
		// m_ChartMenu.setOnMenuItemClickListener(new
		// PopupMenu.OnMenuItemClickListener(){

//					@Override
//					public boolean onMenuItemClick(MenuItem item) {
//
//						switch (item.getItemId()) {
//
//						case R.id.chartselect: {
//
//							OpenChartFileDlg();
//						}
//							break;
//						case R.id.chartdisplay: {
//
//							OpenChartDisplayDlg();
//
//						}
//							break;
//						}
//						return true;
//					}
//				});
	}

	private void CreateSensorMenu() {
		// 1.chart menu
		m_SensorMenu = new PopupMenu(PilotSysMainActivity.this, m_SyssetOpBtn);
		m_SensorMenu.getMenuInflater().inflate(R.menu.syssetting,
				m_SensorMenu.getMenu());
//		m_SensorMenu
//				.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//
//					@Override
//					public boolean onMenuItemClick(MenuItem item) {
//
//						switch (item.getItemId()) {
//
//						case R.id.shipparammset: {
//
//							OpenShipParamSetDlg();
//						}
//							break;
//						case R.id.bluetoothset: {
//
//							OpenBluetoothSettingWin();
//						}
//							break;
//						case R.id.wifisockset: {
//
//							OpenSocketConfigDlg();
//						}
//							break;
//						default:
//							break;
//						}
//						return true;
//					}
//				});
	}

	private void CreatePilotDataMenu() {
		// 1.chart menu
		m_PilotDataMenu = new PopupMenu(PilotSysMainActivity.this, m_AisOpBtn);
		m_PilotDataMenu.getMenuInflater().inflate(R.menu.pilotdatamenu,
				m_PilotDataMenu.getMenu());
//		m_PilotDataMenu
//				.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//
//					@Override
//					public boolean onMenuItemClick(MenuItem item) {
//
//						switch (item.getItemId()) {
//
//						case R.id.ownshipdatait: {
//
//							OpenOwnShipDataShowDlg();
//						}
//							break;
//						case R.id.aislistdatait: {
//
//							OpenAisTargetListList();
//						}
//							break;
//						}
//						return true;
//					}
//				});
	}

	private void CreateRouteMenu() {

		m_RouteMenu = new PopupMenu(PilotSysMainActivity.this, m_RouteOpBtn);
		m_RouteMenu.getMenuInflater().inflate(R.menu.routemenu,
				m_RouteMenu.getMenu());
//		m_RouteMenu
//				.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//
//					@Override
//					public boolean onMenuItemClick(MenuItem item) {
//
//						switch (item.getItemId()) {
//
//						case R.id.routeeditmenuitem: {
//
//							OpenRouteEditDlg();
//						}
//							break;
//						case R.id.routetranmenuitem: {
//
//							TransferRouteDlg();
//						}
//							break;
//						}
//						return true;
//					}
//				});

	}

	public void OpenChartFileDlg() {

		/*
		 * if(m_ChartFileSelectDlg!=null) m_ChartFileSelectDlg.show(); else {
		 * 
		 * DisplayMetrics dm =
		 * this.getApplicationContext().getResources().getDisplayMetrics();
		 * 
		 * 
		 * m_ChartFileSelectDlg = new ChartFileSelectDlg(this,
		 * R.layout.chartfileselect
		 * ,dm.widthPixels,dm.heightPixels,R.style.Theme_dialog);
		 * 
		 * m_ChartFileSelectDlg.show(); }
		 */

		/*
		 * int nCount = ChartCoreView.m_chartCore.GetGeoDataSetNums(); final
		 * String[] strFileList = new String[nCount];
		 * 
		 * int i=0; for(i=0;i<nCount;i++) { stVCFGEODATASETDESCR pDescr =
		 * ChartCoreView.m_chartCore.GetGeoDataSetDescr(i); if(pDescr==null)
		 * continue;
		 * 
		 * String strChartCode = pDescr.getSzChartCode(); String strChartName =
		 * pDescr.getSzChartName();
		 * 
		 * String strChartInfo = strChartCode; strChartInfo+="  ";
		 * strChartInfo+=strChartName;
		 * 
		 * strFileList[i] = strChartInfo;
		 * 
		 * }
		 * 
		 * AlertDialog.Builder build = new AlertDialog.Builder(this);
		 * build.setIcon(R.drawable.ic_launcher); build.setTitle("海图选择");
		 * build.setCancelable(false); build.setIcon(R.drawable.ic_launcher);
		 * build.setView(view); build.setItems(strFileList, new
		 * DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick(DialogInterface dialog, int which) {
		 * 
		 * String strtranname = strFileList[which];
		 * 
		 * int nIndex = strtranname.indexOf(' '); if(nIndex>0) { String
		 * strChartCode = strtranname.substring(0, nIndex);
		 * 
		 * ChartCoreView.m_chartCore.ManualSingleSelectChart(strChartCode); }
		 * 
		 * } }); build.setPositiveButton("保存", new
		 * DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick(DialogInterface dialog, int which) { //
		 * TODO Auto-generated method stub
		 * 
		 * 
		 * 
		 * } }); build.setNegativeButton("关闭", new
		 * DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick(DialogInterface dialog, int which) { //
		 * TODO Auto-generated method stub
		 * 
		 * } }); build.show();
		 */

		final List<String> lstFileList = new ArrayList<String>();

		int nCount = ChartCoreView.m_chartCore.GetGeoDataSetNums();
		int i = 0;
		for (i = 0; i < nCount; i++) {
			stVCFGEODATASETDESCR pDescr = ChartCoreView.m_chartCore
					.GetGeoDataSetDescr(i);
			if (pDescr == null)
				continue;

			String strChartCode = pDescr.getSzChartCode();
			String strChartName = pDescr.getSzChartName();

			String strChartInfo = strChartCode;
			strChartInfo += "  ";
			strChartInfo += strChartName;

			lstFileList.add(strChartInfo);
		}

		LayoutInflater inflater = LayoutInflater.from(this);
		final View view = inflater.inflate(R.layout.chartfileselect, null);

		final ListView ctllistView = (ListView) view
				.findViewById(R.id.chartselectlist);
		ctllistView.setAdapter(new ArrayAdapter<String>(this,
				R.layout.simplelistview_item_1, lstFileList));
		ctllistView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String strChartInfo = lstFileList.get(position);

				int nIndex = strChartInfo.indexOf(' ');
				if (nIndex > 0) {
					String strChartCode = strChartInfo.substring(0, nIndex);

					ChartCoreView.m_chartCore
							.ManualSingleSelectChart(strChartCode);
				}
			}
		});

		CheckBox autochk = (CheckBox) view
				.findViewById(R.id.autoswitchchartchk);
		boolean bChk = ChartCoreView.m_chartCore.GetIsAutoSwitchChart();

		autochk.setChecked(bChk);

		autochk.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox) v).isChecked()) {
					ChartCoreView.m_chartCore.SetIsAutoSwitchChart(true);
				} else {
					ChartCoreView.m_chartCore.SetIsAutoSwitchChart(false);
				}
			}
		});

		AlertDialog.Builder build = new AlertDialog.Builder(this);
		build.setIcon(R.drawable.ic_launcher);
		build.setTitle("海图选择");
		build.setCancelable(false);
		build.setIcon(R.drawable.ic_launcher);
		build.setView(view);
		build.setNegativeButton("关闭", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});
		build.show();

	}

	public void OpenChartDisplayDlg() {

		/*
		 * if(m_ChartDisplayDlg!=null) m_ChartDisplayDlg.show(); else {
		 * 
		 * DisplayMetrics dm =
		 * this.getApplicationContext().getResources().getDisplayMetrics();
		 * 
		 * 
		 * m_ChartDisplayDlg = new ChartDisplayDlg(this,
		 * R.layout,dm.widthPixels,dm.heightPixels,R.style.Theme_dialog);
		 * 
		 * m_ChartDisplayDlg.show(); }
		 */

		LayoutInflater inflater = LayoutInflater.from(this);
		final View view = inflater.inflate(R.layout.chartdisplaydlg, null);

		RadioButton baseDspRadio = (RadioButton) view
				.findViewById(R.id.basedspradio);
		baseDspRadio.setTextColor(Color.rgb(0, 0, 0));

		RadioButton stdDspRadio = (RadioButton) view
				.findViewById(R.id.stddspradio);
		stdDspRadio.setTextColor(Color.rgb(0, 0, 0));

		RadioButton allDspRadio = (RadioButton) view
				.findViewById(R.id.alldspradio);
		allDspRadio.setTextColor(Color.rgb(0, 0, 0));

		int nDisplayMode = ChartCoreView.m_chartCore.GetDisplayMode();
		if (nDisplayMode == 0) {
			baseDspRadio.setChecked(true);
		} else if (nDisplayMode == 1) {
			stdDspRadio.setChecked(true);
		} else if (nDisplayMode == 2) {
			allDspRadio.setChecked(true);
		}

		RadioButton daybkradio = (RadioButton) view
				.findViewById(R.id.daybkradio);
		daybkradio.setTextColor(Color.rgb(0, 0, 0));

		RadioButton duskbkRadio = (RadioButton) view
				.findViewById(R.id.duskbkradio);
		duskbkRadio.setTextColor(Color.rgb(0, 0, 0));

		RadioButton nightbkRadio = (RadioButton) view
				.findViewById(R.id.nightbkradio);
		nightbkRadio.setTextColor(Color.rgb(0, 0, 0));

		int nBkMode = ChartCoreView.m_chartCore.GetColorMode();
		if (nBkMode == 1) {

			daybkradio.setChecked(true);

		} else if (nBkMode == 3) {
			duskbkRadio.setChecked(true);

		} else if (nBkMode == 4) {

			nightbkRadio.setChecked(true);

		}

		RadioButton northradio = (RadioButton) view
				.findViewById(R.id.northupradio);
		northradio.setTextColor(Color.rgb(0, 0, 0));

		RadioButton courseRadio = (RadioButton) view
				.findViewById(R.id.courseupradio);
		courseRadio.setTextColor(Color.rgb(0, 0, 0));

		RadioButton navlRadio = (RadioButton) view
				.findViewById(R.id.navlineupradio);
		navlRadio.setTextColor(Color.rgb(0, 0, 0));
		int nOrieanMode = ChartCoreView.m_chartCore.GetChartOrientMode();
		if (nOrieanMode == 0) {
			northradio.setChecked(true);

		} else if (nOrieanMode == 1) {
			courseRadio.setChecked(true);
		} else if (nOrieanMode == 3) {
			navlRadio.setChecked(true);

		}

		RadioGroup dspmode = (RadioGroup) view
				.findViewById(R.id.layerdspradiogroup);
		dspmode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				RadioButton baseDspRadio = (RadioButton) view
						.findViewById(R.id.basedspradio);
				RadioButton stdDspRadio = (RadioButton) view
						.findViewById(R.id.stddspradio);
				if (baseDspRadio.isChecked()) {
					ChartCoreView.m_chartCore.ChartDisplayMode(0);

				} else if (stdDspRadio.isChecked()) {
					ChartCoreView.m_chartCore.ChartDisplayMode(1);

				} else {
					ChartCoreView.m_chartCore.ChartDisplayMode(2);
				}
			}
		});

		RadioGroup bkmode = (RadioGroup) view.findViewById(R.id.bkmodegroup);
		bkmode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				RadioButton nightradio = (RadioButton) view
						.findViewById(R.id.nightbkradio);
				RadioButton duskadio = (RadioButton) view
						.findViewById(R.id.duskbkradio);
				if (nightradio.isChecked()) {

					ChartCoreView.m_chartCore.ChartColorMode(4);

				} else if (duskadio.isChecked()) {
					ChartCoreView.m_chartCore.ChartColorMode(3);

				} else {
					ChartCoreView.m_chartCore.ChartColorMode(1);
				}
			}
		});

		RadioGroup orieanmode = (RadioGroup) view
				.findViewById(R.id.orieanmodegroup);
		orieanmode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				RadioButton courseradio = (RadioButton) view
						.findViewById(R.id.courseupradio);
				RadioButton navlradio = (RadioButton) view
						.findViewById(R.id.navlineupradio);
				if (navlradio.isChecked()) {

					// ChartCoreView.m_chartCore.ManualChartOrientMode(3);

				} else if (courseradio.isChecked()) {

					// ChartCoreView.m_chartCore.ManualChartOrientMode(1);

				} else {

					// ChartCoreView.m_chartCore.ManualChartOrientMode(0);
				}
			}
		});

		AlertDialog.Builder build = new AlertDialog.Builder(this);
		build.setIcon(R.drawable.ic_launcher);
		build.setTitle("海图显示");
		build.setCancelable(false);
		build.setIcon(R.drawable.ic_launcher);
		build.setView(view);
		build.setNegativeButton("关闭", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});
		build.show();

	}

	public void OpenOwnShipDataShowDlg() {

		if (m_bOwnShipDataDlgShow) {

			m_bOwnShipDataDlgShow = false;
			m_textLogo.setVisibility(View.INVISIBLE);
			;
			m_textposState.setVisibility(View.INVISIBLE);
			;
			m_textConnState.setVisibility(View.INVISIBLE);
			;
			m_shiptimeText.setVisibility(View.INVISIBLE);
			;
//			m_shiplattext.setVisibility(View.INVISIBLE);
//			;
//			m_shiplongtext.setVisibility(View.INVISIBLE);
//			;
			m_shipcoursetext.setVisibility(View.INVISIBLE);
			;
			m_shipspeedtext.setVisibility(View.INVISIBLE);
			;
			m_shipheadtext.setVisibility(View.INVISIBLE);
			;
			m_pilottageview.setVisibility(View.INVISIBLE);
			;
			m_shipxtetext.setVisibility(View.INVISIBLE);
			;
			m_shiptimelabel.setVisibility(View.INVISIBLE);
			;
			m_shiplatlabel.setVisibility(View.INVISIBLE);
			;
			m_shiplonglabel.setVisibility(View.INVISIBLE);
			;
			m_shipcourselabel.setVisibility(View.INVISIBLE);
			;
			m_shipspeedlabel.setVisibility(View.INVISIBLE);
			;
			m_shipheadlabel.setVisibility(View.INVISIBLE);
			;
			m_shipxtelabel.setVisibility(View.INVISIBLE);
			;
			m_pilotdataview.setVisibility(View.INVISIBLE);
			;
			m_pilotcourse.setVisibility(View.INVISIBLE);
			;
			m_pilotspeed.setVisibility(View.INVISIBLE);
			;
			m_pilotxte.setVisibility(View.INVISIBLE);
			;
			m_pilotlat.setVisibility(View.INVISIBLE);
			;
			m_pilotlon.setVisibility(View.INVISIBLE);
			;

		} else {
			m_bOwnShipDataDlgShow = true;
			m_textLogo.setVisibility(View.VISIBLE);
			;
			m_textposState.setVisibility(View.VISIBLE);
			;
			m_textConnState.setVisibility(View.VISIBLE);
			;
			m_shiptimeText.setVisibility(View.VISIBLE);
			;
//			m_shiplattext.setVisibility(View.VISIBLE);
//			;
//			m_shiplongtext.setVisibility(View.VISIBLE);
//			;
			m_shipcoursetext.setVisibility(View.VISIBLE);
			;
			m_shipspeedtext.setVisibility(View.VISIBLE);
			;
			m_shipheadtext.setVisibility(View.VISIBLE);
			;
			m_pilottageview.setVisibility(View.VISIBLE);
			;
			m_shipxtetext.setVisibility(View.VISIBLE);
			;
			m_shiptimelabel.setVisibility(View.VISIBLE);
			;
			m_shiplatlabel.setVisibility(View.VISIBLE);
			;
			m_shiplonglabel.setVisibility(View.VISIBLE);
			;
			m_shipcourselabel.setVisibility(View.VISIBLE);
			;
			m_shipspeedlabel.setVisibility(View.VISIBLE);
			;
			m_shipheadlabel.setVisibility(View.VISIBLE);
			;
			m_shipxtelabel.setVisibility(View.VISIBLE);
			;
			m_pilotdataview.setVisibility(View.VISIBLE);
			;
			m_pilotcourse.setVisibility(View.VISIBLE);
			;
			m_pilotspeed.setVisibility(View.VISIBLE);
			;
			m_pilotxte.setVisibility(View.VISIBLE);
			;
			m_pilotlat.setVisibility(View.VISIBLE);
			;
			m_pilotlon.setVisibility(View.VISIBLE);
			;

		}

	}

	//
	public void OpenAisTargetListList() {

		if (m_AisTargetListDlg != null)
			m_AisTargetListDlg.show();
		else {

			DisplayMetrics dm = this.getApplicationContext().getResources()
					.getDisplayMetrics();
			m_AisTargetListDlg = new AisTargetListDlg(this,
					R.layout.aistargetlistdlg, dm.widthPixels, dm.heightPixels,
					R.style.Theme_dialog);

			m_AisTargetListDlg.show();
		}
	}

	public void OpenShipParamSetDlg() {

		/*
		 * if(m_ShipParamterSetDlg!=null) m_ShipParamterSetDlg.show(); else {
		 * 
		 * DisplayMetrics dm =
		 * this.getApplicationContext().getResources().getDisplayMetrics();
		 * m_ShipParamterSetDlg = new ShipParamterSetDlg(this,
		 * R.layout.shipparamtersetdlg
		 * ,dm.widthPixels,dm.heightPixels,R.style.Theme_dialog);
		 * 
		 * m_ShipParamterSetDlg.show(); }
		 */

		LayoutInflater inflater = LayoutInflater.from(this);
		final View view = inflater.inflate(R.layout.shipparamtersetdlg, null);

		String strName = ChartCoreView.m_dynaTargetControl.GetPilotShipName();

		String strCallNo = ChartCoreView.m_dynaTargetControl
				.GetPilotShipCallNo();

		int nTxHead = ChartCoreView.m_dynaTargetControl.GetPilotShipTxHead();

		int nTxLeft = ChartCoreView.m_dynaTargetControl.GetPilotShipTxLeft();

		int nShipL = ChartCoreView.m_dynaTargetControl.GetPilotShipLength();

		int nShipW = ChartCoreView.m_dynaTargetControl.GetPilotShipWidth();

		EditText mName = (EditText) view.findViewById(R.id.cbcsnameeditText1);

		mName.setText(strName);

		EditText mCall = (EditText) view.findViewById(R.id.cbcscallnoeditText1);

		mCall.setText(strCallNo);

		EditText mShipL = (EditText) view.findViewById(R.id.cbcsshipleditText1);

		mShipL.setText(Integer.toString(nShipL));

		EditText mShipW = (EditText) view.findViewById(R.id.cbcsshipweditText1);

		mShipW.setText(Integer.toString(nShipW));

		EditText mTxHead = (EditText) view.findViewById(R.id.cbcstxhdeditText1);

		mTxHead.setText(Integer.toString(nTxHead));

		EditText mTxLeft = (EditText) view.findViewById(R.id.cbcstxlteditText2);

		mTxLeft.setText(Integer.toString(nTxLeft));

		AlertDialog.Builder build = new AlertDialog.Builder(this);
		build.setIcon(R.drawable.ic_launcher);
		build.setTitle("船舶参数设置");
		build.setCancelable(false);
		build.setIcon(R.drawable.ic_launcher);
		build.setView(view);

		build.setNegativeButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				EditText mName = (EditText) view
						.findViewById(R.id.cbcsnameeditText1);

				String strShipName = mName.getText().toString();

				EditText mCall = (EditText) view
						.findViewById(R.id.cbcscallnoeditText1);

				String strCallNo = mCall.getText().toString();

				EditText mShipL = (EditText) view
						.findViewById(R.id.cbcsshipleditText1);

				int nL = Integer.parseInt(mShipL.getText().toString());

				EditText mShipW = (EditText) view
						.findViewById(R.id.cbcsshipweditText1);

				int nW = Integer.parseInt(mShipW.getText().toString());

				EditText mTxHead = (EditText) view
						.findViewById(R.id.cbcstxhdeditText1);

				int nTxH = Integer.parseInt(mTxHead.getText().toString());

				EditText mTxLeft = (EditText) view
						.findViewById(R.id.cbcstxlteditText2);

				int nTxL = Integer.parseInt(mTxLeft.getText().toString());

				ChartCoreView.m_dynaTargetControl.SetPilotShipParamterInfo(
						strShipName, strCallNo, nTxH, nL - nTxH, nTxL, nW
								- nTxL);

				CShipStaticData stData = new CShipStaticData();
				stData.SetName(strShipName);
				stData.SetCallNo(strCallNo);
				stData.SetTxHead(nTxH);
				stData.SetTxTail(nL - nTxH);
				stData.SetTxLeft(nTxL);
				stData.SetTxRight(nW - nTxL);

				ChartCoreView.m_pilotDataManage.SaveShipParameter(stData);
			}
		});

		build.setPositiveButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});

		/*
		 * build.setNeutralButton("删除", new DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick(DialogInterface dialog, int which) { //
		 * TODO Auto-generated method stub
		 * if(ChartCoreView.m_planRouteManage.DeleteRouteFile
		 * (CPlanRouteManage.m_strSelectRouteNme)) {
		 * if(CPlanRouteManage.m_nSelectRouteInx>=0)
		 * lstFileList.remove(CPlanRouteManage.m_nSelectRouteInx);
		 * 
		 * } } });
		 */

		build.show();

	}

	public void OpenSocketConfigDlg() {

		/*
		 * if(m_SocketConfigDlg!=null) m_SocketConfigDlg.show(); else {
		 * 
		 * DisplayMetrics dm =
		 * this.getApplicationContext().getResources().getDisplayMetrics();
		 * m_SocketConfigDlg = new SocketConfigDlg(this,
		 * R.layout.socketconfigdlg
		 * ,dm.widthPixels,dm.heightPixels,R.style.Theme_dialog);
		 * 
		 * m_SocketConfigDlg.show(); }
		 */

		LayoutInflater inflater = LayoutInflater.from(this);
		final View view = inflater.inflate(R.layout.socketconfigdlg, null);

		final CSockCommConfig msockdata = PilotSysMainActivity.m_PilotSensorControl
				.GetSocketConfig();

		EditText mServIP = (EditText) view
				.findViewById(R.id.sockcfgsvripeditText1);

		mServIP.setText(msockdata.m_strServerIP);

		EditText mServPort = (EditText) view
				.findViewById(R.id.sockcfgsvrporteditText1);

		mServPort.setText(Integer.toString(msockdata.m_nServerPort));

		EditText mLocalPort = (EditText) view
				.findViewById(R.id.sockcfglocporteditText1);

		mLocalPort.setText(Integer.toString(msockdata.m_nLocalPort));

		CheckBox autochk = (CheckBox) view.findViewById(R.id.sockcfgchk1);
		boolean bChk = msockdata.m_nActive;

		autochk.setChecked(bChk);

		autochk.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox) v).isChecked()) {
					msockdata.m_nActive = true;
					PilotSysMainActivity.m_PilotSensorControl
							.SetSocketConfig(msockdata);
				} else {
					msockdata.m_nActive = false;
					PilotSysMainActivity.m_PilotSensorControl
							.SetSocketConfig(msockdata);
				}
			}
		});

		AlertDialog.Builder build = new AlertDialog.Builder(this);
		build.setIcon(R.drawable.ic_launcher);
		build.setTitle("网络接口设置");
		build.setCancelable(false);
		build.setIcon(R.drawable.ic_launcher);
		build.setView(view);

		build.setNegativeButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				EditText mServIP = (EditText) view
						.findViewById(R.id.sockcfgsvripeditText1);

				msockdata.m_strServerIP = mServIP.getText().toString();

				EditText mServPort = (EditText) view
						.findViewById(R.id.sockcfgsvrporteditText1);

				msockdata.m_nServerPort = Integer.parseInt(mServPort.getText()
						.toString());

				EditText mLocalPort = (EditText) view
						.findViewById(R.id.sockcfglocporteditText1);

				msockdata.m_nLocalPort = Integer.parseInt(mLocalPort.getText()
						.toString());

				PilotSysMainActivity.m_PilotSensorControl
						.SetSocketConfig(msockdata);
			}
		});

		build.setPositiveButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});

		build.show();
	}

	public void OpenRouteEditDlg() {

		if (m_bRouteEditDlgShow) {

			m_bRouteEditDlgShow = false;
			m_TopDataBarView.setVisibility(View.INVISIBLE);
			;
			m_drawrouteBtn.setVisibility(View.INVISIBLE);
			;
			m_delrouteBtn.setVisibility(View.INVISIBLE);
			;
			m_updaterouteBtn.setVisibility(View.INVISIBLE);
			;
			m_saverouteBtn.setVisibility(View.INVISIBLE);
			;
			m_openrouteBtn.setVisibility(View.INVISIBLE);
			;
			m_exitrouteBtn.setVisibility(View.INVISIBLE);
			;

		} else {
			m_bRouteEditDlgShow = true;
			m_TopDataBarView.setVisibility(View.VISIBLE);
			;
			m_drawrouteBtn.setVisibility(View.VISIBLE);
			;
			m_delrouteBtn.setVisibility(View.VISIBLE);
			;
			m_updaterouteBtn.setVisibility(View.VISIBLE);
			;
			m_saverouteBtn.setVisibility(View.VISIBLE);
			;
			m_openrouteBtn.setVisibility(View.VISIBLE);
			;
			m_exitrouteBtn.setVisibility(View.VISIBLE);
			;

		}
	}

	//
	public void OpenBluetoothSettingWin() {

		Intent serverIntent = new Intent(this, DeviceListActivity.class);
		startActivityForResult(serverIntent,
				CPilotSensorControl.REQUEST_CONNECT_DEVICE);
	}

	public void OpenRouteSaveDlg() {

		LayoutInflater inflater = LayoutInflater.from(this);
		final View view = inflater.inflate(R.layout.routesavedlg, null);

		final EditText txtName = (EditText) view
				.findViewById(R.id.routenameedit1);

		final EditText txtUse = (EditText) view
				.findViewById(R.id.routeuseeditText);

		final EditText txtSaying = (EditText) view
				.findViewById(R.id.routesayeditText);

		AlertDialog.Builder build = new AlertDialog.Builder(this);
		build.setIcon(R.drawable.ic_launcher);
		build.setTitle("航路保存");
		build.setCancelable(false);
		build.setIcon(R.drawable.ic_launcher);
		build.setView(view);
		build.setPositiveButton("保存", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String strName = txtName.getText().toString();
				String strUse = txtUse.getText().toString();
				String strSaying = txtSaying.getText().toString();

				ChartCoreView.m_planRouteManage.SavePlanRoute(strName,
						strSaying, strUse);
				ChartCoreView.m_planRouteManage.ClearEditRoute();

			}
		});
		build.setNegativeButton("关闭", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});
		build.show();
	}

	public void OpenPlanRouteDlg() {

		/*
		 * DisplayMetrics dm =
		 * this.getApplicationContext().getResources().getDisplayMetrics();
		 * 
		 * COpenPlanRouteDlg openroutedlg = new COpenPlanRouteDlg(this,
		 * R.layout.
		 * openroutedlg,dm.widthPixels,dm.heightPixels,R.style.Theme_dialog);
		 * if( openroutedlg!=null) openroutedlg.show();
		 */
		/*
		 * else {
		 * 
		 * DisplayMetrics dm =
		 * this.getApplicationContext().getResources().getDisplayMetrics();
		 * m_OpenPlanRouteDlg = new COpenPlanRouteDlg(this,
		 * R.layout.openroutedlg
		 * ,dm.widthPixels,dm.heightPixels,R.style.Theme_dialog);
		 * 
		 * m_OpenPlanRouteDlg.show(); }
		 */

		final List<String> lstFileList = new ArrayList<String>();

		String strFilePath = ChartCoreView.GetSystemDataFileDirectory();

		strFilePath += "planroutelib";

		File file = new File(strFilePath);

		if (!file.exists())
			return;

		if (file.isDirectory()) { // 判断是否是文件
			File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
			for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
				String strName = files[i].getName();

				int nIndex = strName.indexOf('.');
				if (nIndex > 0)
					strName = strName.substring(0, nIndex);

				lstFileList.add(strName);

			}
		}

		LayoutInflater inflater = LayoutInflater.from(this);
		final View view = inflater.inflate(R.layout.openroutedlg, null);

		final ListView ctllistView = (ListView) view
				.findViewById(R.id.openroutelistView1);
		ctllistView.setAdapter(new ArrayAdapter<String>(this,
				R.layout.simplelistview_item_1, lstFileList));
		ctllistView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				CPlanRouteManage.m_strSelectRouteNme = lstFileList
						.get(position);

				CPlanRouteManage.m_nSelectRouteInx = position;
			}
		});

		Button openbtn = (Button) view.findViewById(R.id.openroutebtn1);
		// openbtn.setBackgroundColor(Color.rgb(255, 255, 255));

		openbtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				ChartCoreView.m_planRouteManage
						.OpenPlanRoute(CPlanRouteManage.m_strSelectRouteNme);

			}
		});

		Button delbtn = (Button) view.findViewById(R.id.delroutebtn1);
		// delbtn.setBackgroundColor(Color.rgb(255, 255, 255));
		delbtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (ChartCoreView.m_planRouteManage
						.DeleteRouteFile(CPlanRouteManage.m_strSelectRouteNme)) {
					if (CPlanRouteManage.m_nSelectRouteInx >= 0)
						lstFileList.remove(CPlanRouteManage.m_nSelectRouteInx);

				}
			}
		});

		AlertDialog.Builder build = new AlertDialog.Builder(this);
		build.setIcon(R.drawable.ic_launcher);
		build.setTitle("打开航路");
		build.setCancelable(false);
		build.setIcon(R.drawable.ic_launcher);
		build.setView(view);

		build.setNegativeButton("关闭", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});

		/*
		 * build.setPositiveButton("打开", new DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick(DialogInterface dialog, int which) { //
		 * TODO Auto-generated method stub
		 * ChartCoreView.m_planRouteManage.OpenPlanRoute
		 * (CPlanRouteManage.m_strSelectRouteNme); } });
		 * build.setNeutralButton("删除", new DialogInterface.OnClickListener() {
		 * 
		 * @Override public void onClick(DialogInterface dialog, int which) { //
		 * TODO Auto-generated method stub
		 * if(ChartCoreView.m_planRouteManage.DeleteRouteFile
		 * (CPlanRouteManage.m_strSelectRouteNme)) {
		 * if(CPlanRouteManage.m_nSelectRouteInx>=0)
		 * lstFileList.remove(CPlanRouteManage.m_nSelectRouteInx);
		 * 
		 * } } });
		 */

		build.show();

	}

	public void TransferRouteDlg() {

		/*
		 * DisplayMetrics dm =
		 * this.getApplicationContext().getResources().getDisplayMetrics();
		 * 
		 * CTransferRouteDlg transDlg = new CTransferRouteDlg(this,
		 * R.layout.transferroutedlg
		 * ,dm.widthPixels,dm.heightPixels,R.style.Theme_dialog);
		 * 
		 * if(transDlg!=null) transDlg.show();
		 */

		int nFileNum = 0;

		String strFilePath = ChartCoreView.GetSystemDataFileDirectory();

		strFilePath += "planroutelib";

		File file = new File(strFilePath);

		if (file.exists()) {

			if (file.isDirectory()) { // 判断是否是文件
				File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
				nFileNum = files.length;
			}
		}

		nFileNum++;
		final String[] strroutes = new String[nFileNum];
		int j = 0;
		strroutes[j++] = "无";

		if (file.exists()) {

			if (file.isDirectory()) { // 判断是否是文件
				File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
					String strName = files[i].getName();

					int nIndex = strName.indexOf('.');
					if (nIndex > 0)
						strName = strName.substring(0, nIndex);

					strroutes[j++] = strName;

				}
			}
		}

		Dialog alertDialog = new AlertDialog.Builder(this)
				.setTitle("航路调用")
				.setIcon(R.drawable.ic_launcher)
				.setItems(strroutes, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						String strtranname = strroutes[which];

						if (strtranname.compareTo("无") == 0) {
							ChartCoreView.m_planRouteManage.PlanRouteMonClose();

						} else if (!strtranname.isEmpty()) {

							ChartCoreView.m_planRouteManage
									.PlanRouteMonTransfer(strtranname);
						}
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					}
				}).create();
		alertDialog.show();
	}

//	private OnClickListener ZoominOpBtnFunc = new OnClickListener() {
//		public void onClick(View v) {
//			Log.i("optool", "ZoominOpBtnFunc");
//
//			m_ChartCoreView.ChartZoomin();
//		}
//	};

	private OnTouchListener ZoominOpBtnTouch = new OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {

			if (event.getAction() == MotionEvent.ACTION_DOWN) {

				// 更改为按下时的背景图片

				Log.i("optool", "ZoominOpBtnFunc");

				m_ChartCoreView.ChartZoomin();

				m_ZoominOpBtn.setBackgroundResource(R.drawable.onenlarge);

			} else if (event.getAction() == MotionEvent.ACTION_UP) {

				// 改为抬起时的图片

				m_ZoominOpBtn.setBackgroundResource(R.drawable.enlarge);

			}

			return false;

		}
	};

	// private OnClickListener ZoomoutOpBtnFunc = new OnClickListener()
	// {
	// public void onClick(View v)
	// {
	// Log.i("optool", "ZoomoutOpBtnFunc");
	//
	// m_ChartCoreView.ChartZoomout();
	// }
	// };
	private OnTouchListener ZoomoutOpBtnTouch = new OnTouchListener() {
		public boolean onTouch(View v, MotionEvent event) {

			if (event.getAction() == MotionEvent.ACTION_DOWN) {

				// 更改为按下时的背景图片
				Log.i("optool", "ZoomoutOpBtnFunc");

				m_ChartCoreView.ChartZoomout();

				m_ZoomoutOpBtn.setBackgroundResource(R.drawable.onnarrow);

			} else if (event.getAction() == MotionEvent.ACTION_UP) {

				// 改为抬起时的图片

				m_ZoomoutOpBtn.setBackgroundResource(R.drawable.narrow);

			}

			return false;

		}
	};

	private OnClickListener MeasureOpBtnFunc = new OnClickListener() {
		public void onClick(View v) {
			Log.i("optool", "MeasureOpBtnFunc");
			String hidid = m_measurehidid.getText().toString();
			// emCHART_OPERATION_MODE nOpMode =
			// m_ChartCoreView.m_chartCore.GetMouseOperationMode();
			if (hidid.equals("0")) {
				m_ChartCoreView.m_chartCore
						.SetMouseOperationMode(emCHART_OPERATION_MODE.CHART_HHCL);
				m_MeasureOpBtn.setBackgroundResource(R.drawable.onmeasure);
				m_measurehidid.setText("1");
			} else {
				m_ChartCoreView.m_chartCore
						.SetMouseOperationMode(emCHART_OPERATION_MODE.CHART_NODOING);
				m_MeasureOpBtn.setBackgroundResource(R.drawable.measure);
				m_measurehidid.setText("0");
			}
			// m_ChartCoreView.ChartMeasure();

		}
	};

	private OnClickListener ShipCenOpBtnFunc = new OnClickListener() {
		public void onClick(View v) {
			Log.i("optool", "ShipCenOpBtnFunc");

			boolean bCenter = ChartCoreView.m_dynaTargetControl
					.ManualPilotShipCenterShow();
			if (bCenter) {
				// m_ShipcenOpBtn.setText("船中");
				m_ShipcenOpBtn.setBackgroundResource(R.drawable.amidship);
			} else {

				// m_ShipcenOpBtn.setText("偏心");
				m_ShipcenOpBtn.setBackgroundResource(R.drawable.deviate);
			}

		}
	};

	private OnClickListener AisOpBtnFunc = new OnClickListener() {
		public void onClick(View v) {
			RadioButton nightradio = (RadioButton) settingLayout
					.findViewById(R.id.nightradio);
			if(nightradio.isChecked()){
				//设置晚上菜单按钮颜色
				m_ChartOpBtn.setTextColor(Color.rgb(255,255,255));
				m_AisOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_RouteOpBtn.setTextColor(Color.rgb(86,214,131));
				m_SyssetOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_pilotageBtn.setTextColor(Color.rgb(255, 255, 255));
			}else{
				m_ChartOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_AisOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_RouteOpBtn.setTextColor(Color.rgb(235,173,82));
				m_SyssetOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_pilotageBtn.setTextColor(Color.rgb(255, 255, 255));
			}
			Log.i("optool", "AisOpBtnFunc");

			ListView lv = (ListView) findViewById(R.id.aistargetlistView1);
			// 获取将要绑定的数据设置到data中
			AisTargetListAdapter adapter = new AisTargetListAdapter(
					mainLayout.getContext(), lv);
			lv.setAdapter(adapter);
			if (adapter.getCount() > 0) {
				setChartViewShow(View.GONE);
				setSettingShowHid(View.GONE);
				setBerthingViewShow(View.GONE);
				setAisListShow(View.VISIBLE);
				setPilotage(View.GONE);
				
				
			} else {
				Toast.makeText(mainLayout.getContext(), "没有AIS船舶信息",
						Toast.LENGTH_SHORT).show();

			}

			lv.setOnItemClickListener(AisListItemFunc);

			// if(m_PilotDataMenu!=null)
			// {
			// m_PilotDataMenu.show();
			// }
			// else
			// {
			// CreatePilotDataMenu();
			// m_PilotDataMenu.show();
			// }

			/*
			 * if(m_PilotDataMenuDlg!=null) { m_PilotDataMenuDlg.show(); }
			 */
		}
	};

	private OnClickListener RouteOpBtnFunc = new OnClickListener() {
		public void onClick(View v) {

			RadioButton nightradio = (RadioButton) settingLayout
					.findViewById(R.id.nightradio);
			if(nightradio.isChecked()){
				//设置晚上菜单按钮颜色
				m_ChartOpBtn.setTextColor(Color.rgb(255,255,255));
				m_AisOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_RouteOpBtn.setTextColor(Color.rgb(86,214,131));
				m_SyssetOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_pilotageBtn.setTextColor(Color.rgb(255, 255, 255));
			}else{
				m_ChartOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_AisOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_RouteOpBtn.setTextColor(Color.rgb(235,173,82));
				m_SyssetOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_pilotageBtn.setTextColor(Color.rgb(255, 255, 255));
			}
			Log.i("optool", "RouteOpBtnFunc");

			if (m_RouteMenu != null) {
				m_RouteMenu.show();
			} else {
				CreateRouteMenu();
				m_RouteMenu.show();
			}

			/*
			 * if(m_RouteMenuDlg!=null) { m_RouteMenuDlg.show(); }
			 */
		}
	};

	private OnClickListener SysSetOpBtnFunc = new OnClickListener() {
		public void onClick(View v) {
			Log.i("optool", "SysSetOpBtnFunc");
			RadioButton nightradio = (RadioButton) settingLayout
					.findViewById(R.id.nightradio);
			if(nightradio.isChecked()){
				//设置晚上菜单按钮颜色
				m_ChartOpBtn.setTextColor(Color.rgb(255,255,255));
				m_AisOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_RouteOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_SyssetOpBtn.setTextColor(Color.rgb(86,214,131));
				m_pilotageBtn.setTextColor(Color.rgb(255, 255, 255));
			}else{
				
				m_ChartOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_AisOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_RouteOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_SyssetOpBtn.setTextColor(Color.rgb(235,173,82));
				m_pilotageBtn.setTextColor(Color.rgb(255, 255, 255));
			}

			setChartViewShow(View.GONE); // 隐藏主页面
			setPilotage(View.GONE); // 隐藏引航页面
			setBerthingViewShow(View.GONE);
			setAisListShow(View.GONE);
			setSettingShowHid(View.VISIBLE);// 显示设置页面
			
			
			
			
			setSocketParams(); // 设置端口参数
			setShipParam(); // 设置船舶参数
			setChartShowWay(); // 设置海图
			setAlarmParams(); // 设置报警参数
			showSetting(); // 显示设置

			/*
			 * if(m_SensorMenu!=null) { m_SensorMenu.show(); } else {
			 * CreateSensorMenu(); m_SensorMenu.show(); }
			 *//*
				 * if(m_SysSettingMenuDlg!=null) { m_SysSettingMenuDlg.show(); }
				 */
		}
	};

	private OnClickListener SysPilotageFunc = new OnClickListener() {

		@Override
		public void onClick(View v) {

			setChartViewShow(View.GONE);
			setSettingShowHid(View.GONE);
			setBerthingViewShow(View.GONE);
			setAisListShow(View.GONE);
			setPilotage(View.VISIBLE);
			Log.i("optool", "SysPilotageFunc");
			RadioButton nightradio = (RadioButton) settingLayout
					.findViewById(R.id.nightradio);
			if(nightradio.isChecked()){
				//设置晚上菜单按钮颜色
				m_ChartOpBtn.setTextColor(Color.rgb(255,255,255));
				m_AisOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_RouteOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_SyssetOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_pilotageBtn.setTextColor(Color.rgb(86,214,131));
			}else{
				
				m_ChartOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_AisOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_RouteOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_SyssetOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_pilotageBtn.setTextColor(Color.rgb(235,173,82));
			}

		}
	};

	private OnClickListener ExitSysOpBtnFunc = new OnClickListener() {
		public void onClick(View v) {
			Log.i("optool", "ExitSysOpBtnFunc");

			m_PilotSensorControl.CloseSocketCommThread();

			m_mTimer.cancel();

			m_PilotSensorControl.bluetoothStop();

			System.exit(0);
		}
	};

	//
	private OnClickListener drawRouteBtnFunc = new OnClickListener() {
		public void onClick(View v) {
			Log.i("optool", "chartop");

			emCHART_OPERATION_MODE emMouseMode = emCHART_OPERATION_MODE.CHART_NODOING;

			ChartCoreView.m_chartCore.SetMouseOperationMode(emMouseMode);

			ChartCoreView.m_planRouteManage.SetRouteEditOpState(1);
		}
	};

	private OnClickListener delrouteBtnFunc = new OnClickListener() {
		public void onClick(View v) {
			Log.i("optool", "chartop");

			/*
			 * emCHART_OPERATION_MODE emMouseMode =
			 * emCHART_OPERATION_MODE.CHART_NODOING;
			 * 
			 * ChartCoreView.m_chartCore.SetMouseOperationMode(emMouseMode);
			 * 
			 * ChartCoreView.m_planRouteManage.SetRouteEditOpState(3);
			 */

			ChartCoreView.m_planRouteManage.ClearEditRoute();
		}
	};

	private OnClickListener updaterouteBtnFunc = new OnClickListener() {
		public void onClick(View v) {
			Log.i("optool", "chartop");

			emCHART_OPERATION_MODE emMouseMode = emCHART_OPERATION_MODE.CHART_NODOING;

			ChartCoreView.m_chartCore.SetMouseOperationMode(emMouseMode);

			ChartCoreView.m_planRouteManage.SetRouteEditOpState(2);

		}
	};

	private OnClickListener saverouteBtnFunc = new OnClickListener() {
		public void onClick(View v) {
			Log.i("optool", "chartop");

			OpenRouteSaveDlg();

		}
	};

	private OnClickListener openrouteBtnFunc = new OnClickListener() {
		public void onClick(View v) {
			Log.i("optool", "chartop");

			OpenPlanRouteDlg();

		}
	};

	private OnClickListener closerouteBtnFunc = new OnClickListener() {
		public void onClick(View v) {
			Log.i("optool", "chartop");

			OpenRouteEditDlg();

			ChartCoreView.m_planRouteManage.SetRouteEditOpState(0);

			ChartCoreView.m_planRouteManage.ClearEditRoute();
		}
	};

	private void ensureDiscoverable() {

		m_PilotSensorControl.ensureDiscoverable(this);

	}

	//
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (requestCode) {
		case CPilotSensorControl.REQUEST_CONNECT_DEVICE:
			// When DeviceListActivity returns with a device to connect
			if (resultCode == Activity.RESULT_OK) {
				// Get the device MAC address
				String address = data.getExtras().getString(
						DeviceListActivity.EXTRA_DEVICE_ADDRESS);

				m_PilotSensorControl.requestConntectDevice(address);

			}
			break;
		case CPilotSensorControl.REQUEST_ENABLE_BT:
			// When the request to enable Bluetooth returns
			if (resultCode == Activity.RESULT_OK) {
				// Bluetooth is now enabled, so set up a chat session

				m_PilotSensorControl.requestEnableBluetooth(this);

			} else {
				// User did not enable Bluetooth or an error occured
				// Log.d(TAG, "BT not enabled");
				// Toast.makeText(this, R.string.bt_not_enabled_leaving,
				// Toast.LENGTH_SHORT).show();
				ensureDiscoverable();
				// finish();
			}
		}
	}

	public void onStart() {
		super.onStart();

		Log.v("onStart", "onStart");

		m_PilotSensorControl.StartupBluetooth(this);
	}

	public void onResume() {
		super.onResume();

		Log.v("onResume", "onResume");

		m_PilotSensorControl.StartupSocketCommThread();

		m_PilotSensorControl.bluetoothResume();

		CreateTimerTack();
	}

	public void onPause() {
		super.onPause();
		Log.v("onPause", "onPause");

		m_PilotSensorControl.CloseSocketCommThread();

		m_PilotSensorControl.bluetoothStop();

		// m_mTimer.cancel();
	}

	public void onStop() {

		super.onStop();
		Log.v("onStop", "onStop");

	}

	public void onDestroy() {
		super.onDestroy();

		Log.v("onDestroy", "onDestroy");

		//
	}

	public void onRestart() {
		super.onRestart();
		Log.v("onRestart", "onRestart");
	}

	public void PilotShipDataWinRTimeShow() {

		if (m_PilotSensorControl.GetGpsFaultAlarm()) {
			m_textConnState.setTextColor(Color.rgb(255, 0, 0));
			m_textConnState.setText("未联机2");
			// m_tbarConnState.setText("未联机");
			m_tbarConnState.setBackgroundResource(R.drawable.offonline);

		} else {
			m_textConnState.setTextColor(Color.rgb(0, 0, 0));
			m_textConnState.setText(" 联机2");
			// m_tbarConnState.setText(" 联机");
			m_tbarConnState.setBackgroundResource(R.drawable.online2);
		}

		CPilotShip pShip = ChartCoreView.m_dynaTargetControl.GetPilotShip();
		double fappwpdis = pShip.GetApprWPDis();
		String jsdd = Long.toString(Math.round(fappwpdis));
		int distance =Integer.parseInt(jsdd);
		if(distance<99999999){
			m_pilotjzxfw.setText(jsdd + "m");
		}else{
			m_pilotjzxfw.setText("");
		}
		
		if (pShip == null)
			return;
		String strText = "";
		int nPosType = pShip.GetPosType();
		if (nPosType == 0) {
			int nStarNum = pShip.GetGpsPosStarNum();
			byte bcDGPS = pShip.GetGpsPosDGPS();
			byte bcPos = pShip.GetGpsPosPos();

			if (bcPos == 'A') {
				if (bcDGPS == 1 || bcDGPS == 2 || bcDGPS == 6) {
					strText = "D";
					strText = strText + Integer.toString(nStarNum);
					m_textposState.setText(strText);
				} else {
					m_textposState.setText("DN");
				}
			} else {
				m_textposState.setText("DN");
			}
		} else if (nPosType == 1) {
			m_textposState.setText("AIS");
		} else {
			m_textposState.setText("DN");
		}

		if (pShip.ShipPositionIsAvalid()) {
			COleDateTime oleShipTime = new COleDateTime(2012, 12, 12, 8, 8, 8);

			double fLong, fLat, fSpeed, fCourse, fHead;

			fLong = pShip.GetShipPosLong();
			fLat = pShip.GetShipPosLat();
			fSpeed = pShip.GetShipPosSpeed();
			fCourse = pShip.GetShipPosCourse();
			oleShipTime = pShip.GetGpsPosTime();

			fHead = pShip.GetUseHead();
			int bXTEDir = pShip.GetXTEDir();
			double fXTE = pShip.GetXTEDis();

			if (fXTE >= 99999999 || fXTE < 0) {
				m_shipxtetext.setText("");
				m_pilotxte.setText("");// 引航
				m_tbarxtetext.setText("");
			} else {
				if (bXTEDir == 1) {
					m_shipxtetext.setTextColor(Color.rgb(255, 0, 0));
					m_pilotxte.setTextColor(Color.rgb(255, 0, 0));// 引航
					strText = String.format("左%dm", (int) fXTE);
					m_shipxtetext.setText(strText);
					m_pilotxte.setText(strText);// 引航
					m_tbarxtetext.setText(strText);

				} else if (bXTEDir == 2) {

					m_shipxtetext.setTextColor(Color.rgb(0, 255, 0));
					m_pilotxte.setTextColor(Color.rgb(0, 255, 0));// 引航
					strText = String.format("右%dm", (int) fXTE);
					m_shipxtetext.setText(strText);
					m_pilotxte.setText(strText);
					m_tbarxtetext.setText(strText);

				} else {
					strText = String.format("%dm", (int) fXTE);
					m_shipxtetext.setTextColor(Color.rgb(255, 255, 255));
					m_pilotxte.setTextColor(Color.rgb(255, 255, 255));// 引航
					m_shipxtetext.setText(strText);
					m_pilotxte.setText(strText); // 引航
					m_tbarxtetext.setText(strText);
				}
			}

			if (fCourse >= 360) {
				m_shipcoursetext.setText("");
				m_pilotcourse.setText("");// 引航
				m_tbarcoursetext.setText("");
			} else {
				strText = String.format("%05.01f °", fCourse);
				m_shipcoursetext.setText(strText);
				m_pilotcourse.setText(strText);// 引航
				m_tbarcoursetext.setText(strText);
			}

			if (fHead >= 360) {
				m_shipheadtext.setText("");
				m_pilottageview.setText("");// 引航

			} else {
				strText = String.format("%05.01f °", fHead);
				m_shipheadtext.setText(strText);
				m_pilottageview.setText(strText);// 引航
			}

			if (fSpeed >= 999) {
				m_shipspeedtext.setText("");
				m_pilotspeed.setText("");// 引航
				m_tbarspeedtext.setText("");
			} else {
				strText = String.format("%05.01f kn", fSpeed);// [NSString
																// stringWithFormat:@"%05.01f kn",fSpeed];

				m_shipspeedtext.setText(strText);
				m_pilotspeed.setText(strText);// 引航
				m_tbarspeedtext.setText(strText);
			}

			m_shiptimeText.setText(oleShipTime.GetTimeString());

			// m_tbartimeText.setText(oleShipTime.GetTimeHHMMSSString());

			int nDMSStyle = ChartCoreView.m_chartCore.GetLongLatShowStyle();

			if (nDMSStyle == 0) {
				CLatLongDMS dms = new CLatLongDMS();

				CUtilityTool.GeoLongLatToDMS(fLat, dms);
				strText = String.format("%03d°%02d′%.02f″N", dms.m_nDegree,
						dms.m_nMinute, dms.m_fSecond);

				//m_shiplattext.setText(strText);
				m_pilotlat.setText(strText);// 引航

				CUtilityTool.GeoLongLatToDMS(fLong, dms);
				strText = String.format("%03d°%02d′%.02f″E", dms.m_nDegree,
						dms.m_nMinute, dms.m_fSecond);

			//	m_shiplongtext.setText(strText);
				m_pilotlon.setText(strText);// 引航

			} else if (nDMSStyle == 1) {

				CLatLongDM dm = new CLatLongDM();

				CUtilityTool.GeoLatLongToDM(fLat, dm);
				strText = String.format("%03d°%.06f′N", dm.m_nDegree,
						dm.m_fMinute);
				//m_shiplattext.setText(strText);
				m_pilotlat.setText(strText);

				CUtilityTool.GeoLatLongToDM(fLong, dm);
				strText = String.format("%03d°%.06f′E", dm.m_nDegree,
						dm.m_fMinute);
			//	m_shiplongtext.setText(strText);
				m_pilotlon.setText(strText);// 引航
			} else {

				strText = String.format("%03.08f°N", fLat);
				//m_shiplattext.setText(strText);
				m_pilotlat.setText(strText);
				strText = String.format("%03.08f°E", fLong);
			//	m_shiplongtext.setText(strText);
				m_pilotlon.setText(strText);// 引航

			}

		} else {
			COleDateTime oleShipTime = COleDateTime.GetCurrentTime();

			m_shiptimeText.setText(oleShipTime.GetTimeString());
			m_shiptimeText.setText("");
		//	m_shiplattext.setText("");
			m_pilotlat.setText("");
		//	m_shiplongtext.setText("");
			m_shipcoursetext.setText("");
			m_shipspeedtext.setText("");
			m_shipheadtext.setText("");
			m_shipxtetext.setText("");

			m_tbarcoursetext.setText("");
			m_tbarspeedtext.setText("");
			m_tbarxtetext.setText("");

			m_pilottageview.setText("");
			m_pilotcourse.setText("");
			m_pilotspeed.setText("");
			m_pilotxte.setText("");
			m_pilotlon.setText("");
		}
	}

	public void systemTimeRTimeShow() {

		CPilotShip pShip = ChartCoreView.m_dynaTargetControl.GetPilotShip();
		if (pShip != null && pShip.ShipPositionIsAvalid())
			return;
		COleDateTime oleShipTime = COleDateTime.GetCurrentTime();
		m_shiptimeText.setText(oleShipTime.GetTimeString());
		// m_tbartimeText.setText(oleShipTime.GetTimeHHMMSSString());
	}

	/**
	 * 隐藏/显示海图视图
	 * 
	 * @param visNum
	 *            GONE:隐藏 VISIBLE:显示
	 */

	private void setChartViewShow(int visNum) {

		if (visNum == View.GONE) {
			m_TopDataBarView.setVisibility(visNum);
			m_drawrouteBtn.setVisibility(visNum);
			// m_delrouteBtn.setVisibility(visNum);
			// m_updaterouteBtn.setVisibility(visNum);
			// m_saverouteBtn.setVisibility(visNum);
			// m_openrouteBtn.setVisibility(visNum);
			// m_exitrouteBtn.setVisibility(visNum);
			// m_pilotdataview.setVisibility(visNum);
			// m_textLogo.setVisibility(visNum);
			m_textposState.setVisibility(visNum);
			m_textConnState.setVisibility(visNum);
			// m_shiptimelabel.setVisibility(visNum);
		//	m_shiplatlabel.setVisibility(visNum);
		//	m_shiplonglabel.setVisibility(visNum);
		//	m_shiptimeText.setVisibility(visNum);
		//	m_shiplattext.setVisibility(visNum);
		//	m_shiplongtext.setVisibility(visNum);
			m_shipcourselabel.setVisibility(visNum);
			m_shipspeedlabel.setVisibility(visNum);
			m_shipheadlabel.setVisibility(visNum);
		//	m_shipcoursetext.setVisibility(visNum);
		//	m_shipspeedtext.setVisibility(visNum);
		//	m_shipheadtext.setVisibility(visNum);
			m_shipxtelabel.setVisibility(visNum);
			m_shipxtetext.setVisibility(visNum);
			// m_pilotageBtn.setVisibility(visNum);
			// m_MeasureOpBtn.setVisibility(visNum);
		}

		m_ChartCoreView.setVisibility(visNum);
		m_topbarview.setVisibility(visNum);
		m_tbarcoursetext.setVisibility(visNum);
		m_tbarspeedtext.setVisibility(visNum);
		m_tbarxtetext.setVisibility(visNum);
		// m_tbartimeText.setVisibility(visNum);
		m_tbarConnState.setVisibility(visNum);
		// m_TopDataBarView.setVisibility(visNum);
		// m_drawrouteBtn.setVisibility(visNum);
		// m_delrouteBtn.setVisibility(visNum);
		// m_updaterouteBtn.setVisibility(visNum);
		// m_saverouteBtn.setVisibility(visNum);
		// m_openrouteBtn.setVisibility(visNum);
		// m_exitrouteBtn.setVisibility(visNum);
		// m_pilotdataview.setVisibility(visNum);
		// m_ChartOpBtn.setVisibility(visNum);
		m_shipheadtext.setVisibility(visNum);
		m_ShipcenOpBtn.setVisibility(visNum);
		m_ZoomoutOpBtn.setVisibility(visNum);
		m_ZoominOpBtn.setVisibility(visNum);
		// m_pilotageBtn.setVisibility(visNum);
		// m_textLogo.setVisibility(visNum);
		m_textposState.setVisibility(visNum);
		// m_textConnState.setVisibility(visNum);
		// m_shiptimelabel.setVisibility(visNum);
		// m_shiplatlabel.setVisibility(visNum);
		// m_shiplonglabel.setVisibility(visNum);
		// m_shiptimeText.setVisibility(visNum);
	//	m_shiplattext.setVisibility(visNum);
	//	m_shiplongtext.setVisibility(visNum);
		// m_shipcourselabel.setVisibility(visNum);
		// m_shipspeedlabel.setVisibility(visNum);
		// m_shipheadlabel.setVisibility(visNum);
		// m_shipcoursetext.setVisibility(visNum);
		// m_shipspeedtext.setVisibility(visNum);
		// m_shipheadtext.setVisibility(visNum);
		// m_shipxtelabel.setVisibility(visNum);
		// m_shipxtetext.setVisibility(visNum);
		m_MeasureOpBtn.setVisibility(visNum);
	}

	/**
	 * 设置页面显示隐藏
	 * 
	 * @param visNum
	 *            GONE:隐藏 VISIBLE:显示
	 */
	private void setPilotage(int visNum) {
		settingPilotage.setVisibility(visNum);
	}

	/**
	 * 设置页面显示隐藏
	 * 
	 * @param visNum
	 *            GONE:隐藏 VISIBLE:显示
	 */
	private void setSettingShowHid(int visNum) {
		settingLayout.setVisibility(visNum);
	}

	/**
	 * 设置通信参数
	 */
	public void setSocketParams() {

		final CSockCommConfig msockdata = PilotSysMainActivity.m_PilotSensorControl
				.GetSocketConfig();

		EditText mServIP = (EditText) settingLayout.findViewById(R.id.fwip);

		mServIP.setText(msockdata.m_strServerIP);

		EditText mServPort = (EditText) settingLayout.findViewById(R.id.fwdk);

		mServPort.setText(Integer.toString(msockdata.m_nServerPort));

		EditText mLocalPort = (EditText) settingLayout.findViewById(R.id.bddk);

		mLocalPort.setText(Integer.toString(msockdata.m_nLocalPort));

		ToggleButton autochk = (ToggleButton) settingLayout
				.findViewById(R.id.txkg);
		boolean bChk = msockdata.m_nActive;
		autochk.setChecked(bChk);

		autochk.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					msockdata.m_nActive = true;
					PilotSysMainActivity.m_PilotSensorControl
							.SetSocketConfig(msockdata);
				} else {
					msockdata.m_nActive = false;
					PilotSysMainActivity.m_PilotSensorControl
							.SetSocketConfig(msockdata);
				}

			}
		});

		Button txcsButton = (Button) settingLayout
				.findViewById(R.id.txcsButton);
		txcsButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText mServIP = (EditText) settingLayout
						.findViewById(R.id.fwip);

				msockdata.m_strServerIP = mServIP.getText().toString();

				EditText mServPort = (EditText) settingLayout
						.findViewById(R.id.fwdk);

				msockdata.m_nServerPort = Integer.parseInt(mServPort.getText()
						.toString());

				EditText mLocalPort = (EditText) settingLayout
						.findViewById(R.id.bddk);

				msockdata.m_nLocalPort = Integer.parseInt(mLocalPort.getText()
						.toString());

				PilotSysMainActivity.m_PilotSensorControl
						.SetSocketConfig(msockdata);

				Toast.makeText(PilotSysMainActivity.this, "通信参数设置成功",
						Toast.LENGTH_LONG).show();
			}
		});
	}

	/**
	 * 设置船舶参数
	 */
	public void setShipParam() {

		String strName = ChartCoreView.m_dynaTargetControl.GetPilotShipName();

		String strCallNo = ChartCoreView.m_dynaTargetControl
				.GetPilotShipCallNo();

		int nTxHead = ChartCoreView.m_dynaTargetControl.GetPilotShipTxHead();

		int nTxLeft = ChartCoreView.m_dynaTargetControl.GetPilotShipTxLeft();

		int nShipL = ChartCoreView.m_dynaTargetControl.GetPilotShipLength();

		int nShipW = ChartCoreView.m_dynaTargetControl.GetPilotShipWidth();

		EditText mName = (EditText) settingLayout.findViewById(R.id.chuanming);

		mName.setText(strName);

		EditText mCall = (EditText) settingLayout.findViewById(R.id.huhao);

		mCall.setText(strCallNo);

		EditText mShipL = (EditText) settingLayout
				.findViewById(R.id.chuanchang);

		mShipL.setText(Integer.toString(nShipL));

		EditText mShipW = (EditText) settingLayout.findViewById(R.id.chuankuan);

		mShipW.setText(Integer.toString(nShipW));

		EditText mTxHead = (EditText) settingLayout
				.findViewById(R.id.juchuanshou);

		mTxHead.setText(Integer.toString(nTxHead));

		EditText mTxLeft = (EditText) settingLayout
				.findViewById(R.id.juzuoxian);

		mTxLeft.setText(Integer.toString(nTxLeft));

		Button shipconfigButton = (Button) settingLayout
				.findViewById(R.id.shipconfigButton);

		shipconfigButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText mName = (EditText) settingLayout
						.findViewById(R.id.chuanming);

				String strShipName = mName.getText().toString();

				EditText mCall = (EditText) settingLayout
						.findViewById(R.id.huhao);

				String strCallNo = mCall.getText().toString();

				EditText mShipL = (EditText) settingLayout
						.findViewById(R.id.chuanchang);

				int nL = Integer.parseInt(mShipL.getText().toString());

				EditText mShipW = (EditText) settingLayout
						.findViewById(R.id.chuankuan);

				int nW = Integer.parseInt(mShipW.getText().toString());

				EditText mTxHead = (EditText) settingLayout
						.findViewById(R.id.juchuanshou);

				int nTxH = Integer.parseInt(mTxHead.getText().toString());

				EditText mTxLeft = (EditText) settingLayout
						.findViewById(R.id.juzuoxian);

				int nTxL = Integer.parseInt(mTxLeft.getText().toString());
				if("".equals(strShipName)){
					Toast.makeText(PilotSysMainActivity.this, "请输入船名",Toast.LENGTH_LONG).show();
				}else if("".equals(strCallNo)){
					Toast.makeText(PilotSysMainActivity.this, "请输入呼号",Toast.LENGTH_LONG).show();
				}else{
					
					ChartCoreView.m_dynaTargetControl.SetPilotShipParamterInfo(
							strShipName, strCallNo, nTxH, nL - nTxH, nTxL, nW
							- nTxL);
					
					CShipStaticData stData = new CShipStaticData();
					stData.SetName(strShipName);
					stData.SetCallNo(strCallNo);
					stData.SetTxHead(nTxH);
					stData.SetTxTail(nL - nTxH);
					stData.SetTxLeft(nTxL);
					stData.SetTxRight(nW - nTxL);
					ChartCoreView.m_pilotDataManage.SaveShipParameter(stData);
					
					Toast.makeText(PilotSysMainActivity.this, "船舶参数设置成功",Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	/**
	 * 蓝牙接口
	 */
	private android.widget.CompoundButton.OnCheckedChangeListener openBluetoothSetting = new android.widget.CompoundButton.OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (isChecked) {
				OpenBluetoothSettingWin();
				buttonView.setChecked(false);
			}

		}
	};

	private OnClickListener chartFileSelect = new OnClickListener() {
		@Override
		public void onClick(View v) {
			chartSelect();

		}
	};

	private OnClickListener transferRouteSelect = new OnClickListener() {
		@Override
		public void onClick(View v) {
			transferRoutePage();

		}
	};

	/**
	 * 海图设置
	 */
	private void setChartShowWay() {

		RadioButton baseDspRadio = (RadioButton) settingLayout
				.findViewById(R.id.jibenradio);
		baseDspRadio.setTextColor(Color.rgb(0, 0, 0));

		RadioButton stdDspRadio = (RadioButton) settingLayout
				.findViewById(R.id.biaozhunradio);
		stdDspRadio.setTextColor(Color.rgb(0, 0, 0));

		RadioButton allDspRadio = (RadioButton) settingLayout
				.findViewById(R.id.quanburadio);
		allDspRadio.setTextColor(Color.rgb(0, 0, 0));

		int nDisplayMode = ChartCoreView.m_chartCore.GetDisplayMode();
		if (nDisplayMode == 0) {
			baseDspRadio.setChecked(true);
		} else if (nDisplayMode == 1) {
			stdDspRadio.setChecked(true);
		} else if (nDisplayMode == 2) {
			allDspRadio.setChecked(true);
		}

		RadioButton daybkradio = (RadioButton) settingLayout
				.findViewById(R.id.dayradio);
		daybkradio.setTextColor(Color.rgb(0, 0, 0));

		RadioButton duskbkRadio = (RadioButton) settingLayout
				.findViewById(R.id.duskradio);
		duskbkRadio.setTextColor(Color.rgb(0, 0, 0));

		RadioButton nightbkRadio = (RadioButton) settingLayout
				.findViewById(R.id.nightradio);
		nightbkRadio.setTextColor(Color.rgb(0, 0, 0));

		int nBkMode = ChartCoreView.m_chartCore.GetColorMode();
		if (nBkMode == 1) {

			daybkradio.setChecked(true);

		} else if (nBkMode == 3) {
			duskbkRadio.setChecked(true);

		} else if (nBkMode == 4) {

			nightbkRadio.setChecked(true);

		}

		RadioButton northradio = (RadioButton) settingLayout
				.findViewById(R.id.northradio);
		northradio.setTextColor(Color.rgb(0, 0, 0));

		RadioButton courseRadio = (RadioButton) settingLayout
				.findViewById(R.id.courseradio);
		courseRadio.setTextColor(Color.rgb(0, 0, 0));

		RadioButton navlRadio = (RadioButton) settingLayout
				.findViewById(R.id.navlineradio);
		navlRadio.setTextColor(Color.rgb(0, 0, 0));
		int nOrieanMode = ChartCoreView.m_chartCore.GetChartOrientMode();
		if (nOrieanMode == 0) {
			northradio.setChecked(true);

		} else if (nOrieanMode == 1) {
			courseRadio.setChecked(true);
		} else if (nOrieanMode == 3) {
			navlRadio.setChecked(true);

		}

		RadioGroup dspmode = (RadioGroup) settingLayout
				.findViewById(R.id.htxsradiogroup);
		dspmode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				RadioButton baseDspRadio = (RadioButton) settingLayout
						.findViewById(R.id.jibenradio);
				RadioButton stdDspRadio = (RadioButton) settingLayout
						.findViewById(R.id.biaozhunradio);
				if (baseDspRadio.isChecked()) {
					ChartCoreView.m_chartCore.ChartDisplayMode(0);

				} else if (stdDspRadio.isChecked()) {
					ChartCoreView.m_chartCore.ChartDisplayMode(1);

				} else {
					ChartCoreView.m_chartCore.ChartDisplayMode(2);
				}
				Toast.makeText(PilotSysMainActivity.this, "海图显示设置成功",
						Toast.LENGTH_LONG).show();
			}
		});

		RadioGroup bkmode = (RadioGroup) settingLayout
				.findViewById(R.id.htbjgroup);
		bkmode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				RadioButton nightradio = (RadioButton) settingLayout
						.findViewById(R.id.nightradio);
				RadioButton duskadio = (RadioButton) settingLayout
						.findViewById(R.id.duskradio);
				if (nightradio.isChecked()) {

					ChartCoreView.m_chartCore.ChartColorMode(4);
					
					//设置晚上菜单按钮颜色
					m_ChartOpBtn.setTextColor(Color.rgb(86,214,131));
					m_AisOpBtn.setTextColor(Color.rgb(86,214,131));
					m_RouteOpBtn.setTextColor(Color.rgb(86,214,131));
					m_SyssetOpBtn.setTextColor(Color.rgb(86,214,131));
					m_pilotageBtn.setTextColor(Color.rgb(86,214,131));

				} else if (duskadio.isChecked()) {
					ChartCoreView.m_chartCore.ChartColorMode(3);
					//设置白天菜单按钮颜色
					m_ChartOpBtn.setTextColor(Color.rgb(255,255,255));
					m_AisOpBtn.setTextColor(Color.rgb(255,255,255));
					m_RouteOpBtn.setTextColor(Color.rgb(255,255,255));
					m_SyssetOpBtn.setTextColor(Color.rgb(255,255,255));
					m_pilotageBtn.setTextColor(Color.rgb(255,255,255));

				} else {
					ChartCoreView.m_chartCore.ChartColorMode(1);
					//设置白天菜单按钮颜色
					m_ChartOpBtn.setTextColor(Color.rgb(255,255,255));
					m_AisOpBtn.setTextColor(Color.rgb(255,255,255));
					m_RouteOpBtn.setTextColor(Color.rgb(255,255,255));
					m_SyssetOpBtn.setTextColor(Color.rgb(255,255,255));
					m_pilotageBtn.setTextColor(Color.rgb(255,255,255));
				}
				Toast.makeText(PilotSysMainActivity.this, "海图背景设置成功",
						Toast.LENGTH_LONG).show();
			}
		});

		RadioGroup orieanmode = (RadioGroup) settingLayout
				.findViewById(R.id.htfwgroup);
		orieanmode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				RadioButton courseradio = (RadioButton) settingLayout
						.findViewById(R.id.courseradio);
				RadioButton navlradio = (RadioButton) settingLayout
						.findViewById(R.id.navlineradio);
				if (navlradio.isChecked()) {

					// ChartCoreView.m_chartCore.ManualChartOrientMode(3);

				} else if (courseradio.isChecked()) {

					// ChartCoreView.m_chartCore.ManualChartOrientMode(1);

				} else {

					// ChartCoreView.m_chartCore.ManualChartOrientMode(0);
				}
				Toast.makeText(PilotSysMainActivity.this, "海图方位成功",
						Toast.LENGTH_LONG).show();
			}
		});
	}

	/**
	 * 海图选择
	 */
	private void chartSelect() {

		final List<String> lstFileList = new ArrayList<String>();

		int nCount = ChartCoreView.m_chartCore.GetGeoDataSetNums();
		int i = 0;
		for (i = 0; i < nCount; i++) {
			stVCFGEODATASETDESCR pDescr = ChartCoreView.m_chartCore
					.GetGeoDataSetDescr(i);
			if (pDescr == null)
				continue;

			String strChartCode = pDescr.getSzChartCode();
			String strChartName = pDescr.getSzChartName();

			String strChartInfo = strChartCode;
			strChartInfo += "  ";
			strChartInfo += strChartName;

			lstFileList.add(strChartInfo);
		}

		final RelativeLayout chartselectLayout = (RelativeLayout) inflater
				.inflate(R.layout.chartselect, null).findViewById(
						R.id.chartselectLayout);
		settingLayout.addView(chartselectLayout);
		final ListView ctllistView = (ListView) chartselectLayout
				.findViewById(R.id.chartSelectListView);
		ctllistView.setVisibility(View.VISIBLE);
		ctllistView.setAdapter(new ArrayAdapter<String>(this,
				R.layout.simplelistview_item_2, lstFileList));
		ctllistView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String strChartInfo = lstFileList.get(position);

				int nIndex = strChartInfo.indexOf(' ');
				if (nIndex > 0) {
					String strChartCode = strChartInfo.substring(0, nIndex);
					ChartCoreView.m_chartCore
							.ManualSingleSelectChart(strChartCode);

					// 关闭海图选择列表页面同时设置选中的值
					TextView selectHanglu = (TextView) settingLayout
							.findViewById(R.id.haituSelect);
					selectHanglu.setText(strChartCode);
					chartselectLayout.setVisibility(View.INVISIBLE);
					Toast.makeText(PilotSysMainActivity.this, "海图设置成功",
							Toast.LENGTH_LONG).show();
				}
			}
		});

		ImageView m_chartselectBackButton = (ImageView) chartselectLayout
				.findViewById(R.id.chartselectBackButton);
		m_chartselectBackButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				chartselectLayout.setVisibility(v.INVISIBLE);
			}
		});
	}

	/**
	 * 航路选择页面
	 */
	private void transferRoutePage() {

		int nFileNum = 0;

		String strFilePath = ChartCoreView.GetSystemDataFileDirectory();

		strFilePath += "planroutelib";

		File file = new File(strFilePath);

		if (file.exists()) {

			if (file.isDirectory()) { // 判断是否是文件
				File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
				nFileNum = files.length;
			}
		}

		nFileNum++;
		final String[] strroutes = new String[nFileNum];
		int j = 0;
		strroutes[j++] = "无";

		if (file.exists()) {

			if (file.isDirectory()) { // 判断是否是文件
				File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
					String strName = files[i].getName();

					int nIndex = strName.indexOf('.');
					if (nIndex > 0)
						strName = strName.substring(0, nIndex);

					strroutes[j++] = strName;

				}
			}
		}

		final RelativeLayout transferrouteLayout = (RelativeLayout) inflater
				.inflate(R.layout.transferroutelist, null).findViewById(
						R.id.transferrouteLayout);
		settingLayout.addView(transferrouteLayout);
		final ListView transferrouteListView = (ListView) transferrouteLayout
				.findViewById(R.id.transferrouteListView);
		transferrouteListView.setVisibility(View.VISIBLE);
		transferrouteListView.setAdapter(new ArrayAdapter<String>(this,
				R.layout.simplelistview_item_2, strroutes));
		transferrouteListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String strtranname = strroutes[position];

				if (strtranname.compareTo("无") == 0) {
					ChartCoreView.m_planRouteManage.PlanRouteMonClose();
				} else if (!strtranname.isEmpty()) {
					ChartCoreView.m_planRouteManage
							.PlanRouteMonTransfer(strtranname);
				}
				// 关闭航路选择列表页面同时设置选中的值
				TextView selectHanglu = (TextView) settingLayout
						.findViewById(R.id.hangluSelect);
				selectHanglu.setText(strtranname);
				transferrouteLayout.setVisibility(View.INVISIBLE);
				Toast.makeText(PilotSysMainActivity.this, "航路选择成功",
						Toast.LENGTH_LONG).show();
			}
		});
		ImageView m_transferrouteBackButton = (ImageView) transferrouteLayout
				.findViewById(R.id.transferrouteBackButton);
		m_transferrouteBackButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				transferrouteLayout.setVisibility(v.INVISIBLE);
			}
		});
	}

	/**
	 * 设置报警参数
	 */
	public void setAlarmParams() {

		double pianhang = m_ChartCoreView.m_planRouteManage.GetXTECheckValue();

		double dcpa = m_ChartCoreView.m_dynaTargetControl.GetDCPACheckValue();

		double tcpa = m_ChartCoreView.m_dynaTargetControl.GetTCPACheckValue();

		EditText pianhangText = (EditText) settingLayout
				.findViewById(R.id.pianhangEdittext);

		pianhangText.setText(String.valueOf(pianhang));

		EditText dcpaText = (EditText) settingLayout
				.findViewById(R.id.dcpaEdittext);

		dcpaText.setText(String.valueOf(dcpa));

		EditText tcpaText = (EditText) settingLayout
				.findViewById(R.id.tcpaEdittext);

		tcpaText.setText(String.valueOf(tcpa));

		Button alarmButton = (Button) settingLayout
				.findViewById(R.id.alarmButton);

		alarmButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				EditText pianhangText = (EditText) settingLayout
						.findViewById(R.id.pianhangEdittext);

				double pianhang = Double.valueOf(pianhangText.getText()
						.toString());

				EditText dcpaText = (EditText) settingLayout
						.findViewById(R.id.dcpaEdittext);

				double dcpa = Double.valueOf(dcpaText.getText().toString());

				EditText tcpaText = (EditText) settingLayout
						.findViewById(R.id.tcpaEdittext);

				double tcpa = Double.valueOf(tcpaText.getText().toString());
				// 保存报警参数
				m_ChartCoreView.m_planRouteManage.SetXTECheckValue(pianhang);// 设置偏航
				m_ChartCoreView.m_dynaTargetControl
						.SetCPACheckValue(dcpa, tcpa); // 设置dcpt,tcpa
				Toast.makeText(PilotSysMainActivity.this, "报警参数设置成功",
						Toast.LENGTH_LONG).show();
			}
		});
	}
	
	private void showSetting(){
		
		boolean shiptrackState = m_ChartCoreView.m_pilotPresentation.GetShipTrackIsPlot();
		boolean aislabelState = m_ChartCoreView.m_pilotPresentation.GetAisNameMarkOnOff();
		
		ToggleButton shiptrackBut = (ToggleButton)settingLayout.findViewById(R.id.shiptrackBut);
		shiptrackBut.setChecked(shiptrackState);
		ToggleButton aislabelBut = (ToggleButton)settingLayout.findViewById(R.id.aislabelBut);
		aislabelBut.setChecked(aislabelState);
		
		//本船航迹显示事件
		shiptrackBut.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean ischecked) {
				if(ischecked){
					m_ChartCoreView.m_pilotPresentation.SetShipTrackIsPlot(true);
				}else{
					m_ChartCoreView.m_pilotPresentation.SetShipTrackIsPlot(false);
				}
			}
		});
		//AIS标签显示事件
		aislabelBut.setOnCheckedChangeListener(new android.widget.CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean ischecked) {
				if(ischecked){
					m_ChartCoreView.m_pilotPresentation.SetAisNameMarkOnOff(true);
				}else{
					m_ChartCoreView.m_pilotPresentation.SetAisNameMarkOnOff(false);
				}				
			}
		});
	}

	
	private void setAisListShow(int visNum) {
		// if (visNum==View.GONE) {
		// mainLayout.removeView(aisListRel);
		// }else {
		// mainLayout.addView(aisListRel);
		// }
		aisListRel.setVisibility(visNum);
	}

	String selectedMMSI;
	private OnItemClickListener AisListItemFunc = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			String strmmsi = ((TextView) view.findViewById(R.id.listview1_MMSI))
					.getText().toString();
			// Toast.makeText(mainLayout.getContext(),strInfo,Toast.LENGTH_SHORT).show();

			// int nIndex = strInfo.indexOf(' ');
			// if (nIndex > 0) {
			// int len = strInfo.length();
			// strInfo = strInfo.substring(nIndex + 1, len - nIndex - 1);
			// nIndex = strInfo.indexOf(' ');
			//
			// if (nIndex >= 0) {
			// len = strInfo.length();
			// strInfo = strInfo.substring(nIndex + 1, len - nIndex - 1);
			// ChartCoreView.m_dynaTargetControl.SelectAisTarget(strInfo);
			//
			// // 调用下一页面
			// parent.setVisibility(View.GONE);
			// setAisShipInfoShow(parent,View.VISIBLE);
			// //((SearchView) findViewById(R.id.aisdlg_searchView)).set;
			// }
			//
			// }
			selectedMMSI = strmmsi;
			parent.setVisibility(View.GONE);
			setAisShipInfoShow(strmmsi, parent, View.VISIBLE);
		}
	};

	private void setAisShipInfoShow(String strmmsi, AdapterView<?> parent,
			int visNum) {

		aisShipInfoLin.setVisibility(visNum);

		View aisdlg_toolsbar = (View) findViewById(R.id.aisdlg_toolsbar);
		Button button = (Button) findViewById(R.id.aisdlg_imgbtn);
		TextView aisdlg_jvxs_text = (TextView) findViewById(R.id.aisdlg_jvxs_text);
		TextView aisdlg_title_text = (TextView) findViewById(R.id.aisdlg_title_text);

		aisdlg_toolsbar.setVisibility(visNum);
		button.setVisibility(visNum);
		aisdlg_jvxs_text.setVisibility(visNum);
		aisdlg_title_text.setVisibility(visNum);
		aisdlg_jvxs_togbtn.setVisibility(visNum);

		if (visNum == View.VISIBLE) {

			CAisTarget pAisTarget = ChartCoreView.m_dynaTargetControl
					.GetAisTargetByTargetId(strmmsi);

			aisdlg_title_text.setText(strmmsi);
			aisdlg_title_text
					.setText(pAisTarget.GetShipName().isEmpty() ? pAisTarget
							.GetMMSI() + "" : pAisTarget.GetShipName());
			((TextView) findViewById(R.id.ais_ship_info_shipname))
					.setText(pAisTarget.GetShipName());
			((TextView) findViewById(R.id.ais_ship_info_callno))
					.setText(pAisTarget.GetShipCallNo());
			((TextView) findViewById(R.id.ais_ship_info_mmsi))
					.setText(pAisTarget.GetMMSI() + "");
			((TextView) findViewById(R.id.ais_ship_info_time))
					.setText(pAisTarget.GetTime().GetTimeHHMMSSString());
			((TextView) findViewById(R.id.ais_ship_info_imo))
					.setText(pAisTarget.GetIMO() + "");
			((TextView) findViewById(R.id.ais_ship_info_lat))
					.setText(pAisTarget.GetLat() + "");
			((TextView) findViewById(R.id.ais_ship_info_lang))
					.setText(pAisTarget.GetLong() + "");
			((TextView) findViewById(R.id.ais_ship_info_course))
					.setText(pAisTarget.GetCourse() + "°");
			((TextView) findViewById(R.id.ais_ship_info_speed))
					.setText(pAisTarget.GetSpeed() + "kn");
			((TextView) findViewById(R.id.ais_ship_info_head))
					.setText(pAisTarget.GetHead() + "");
			((TextView) findViewById(R.id.ais_ship_info_seagauge))
					.setText(pAisTarget.GetSeaGauge() + "");
			((TextView) findViewById(R.id.ais_ship_info_cargo))
					.setText(pAisTarget.GetSeaGauge() + "");// 载货
			((TextView) findViewById(R.id.ais_ship_info_destination))
					.setText(pAisTarget.GetDestion() + "");
			
			
			
			// ((TextView)findViewById(R.id.ais_ship_info_shipwidth)).setText(pAisTarget.GetTxLeft()+pAisTarget.GetTxRight());
			// ((TextView)findViewById(R.id.ais_ship_info_shiplength)).setText(pAisTarget.GetTxHead()+pAisTarget.GetTxTail());
			// ((TextView)findViewById(R.id.ais_ship_info_sailstate)).setText(pAisTarget.GetSailState());
			// ((TextView)findViewById(R.id.ais_ship_info_dcpa)).setText(pAisTarget.GetToPilotShipDCPA()+"");
			// ((TextView)findViewById(R.id.ais_ship_info_tcpa)).setText(pAisTarget.GetToPilotShipTCPA()+"");

			if (aisdlg_jvxs_togbtn.isChecked()) {
				selectAisTarget();

			}
			if (parent != null)
				parent.setVisibility(View.GONE);

			button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					setAisShipInfoShow("", null, View.GONE);

				}
			});

		} else {

			findViewById(R.id.aistargetlistView1).setVisibility(View.VISIBLE);

		}

	}

	private android.widget.CompoundButton.OnCheckedChangeListener aisdlgJvxsTogbtnChange = new android.widget.CompoundButton.OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (isChecked)
				selectAisTarget();
		}

	};

	private void selectAisTarget() {
		if (selectedMMSI != null && !"".equals(selectedMMSI)) {
			ChartCoreView.m_dynaTargetControl.SelectAisTarget(selectedMMSI);
		}
	}

	private OnClickListener berthingbtnFunc = new OnClickListener() {

		@Override
		public void onClick(View v) {
			RadioButton nightradio = (RadioButton) settingLayout
					.findViewById(R.id.nightradio);
			if(nightradio.isChecked()){
				//设置晚上菜单按钮颜色
				m_ChartOpBtn.setTextColor(Color.rgb(255,255,255));
				m_AisOpBtn.setTextColor(Color.rgb(86,214,131));
				m_RouteOpBtn.setTextColor(Color.rgb(255,255,255));
				m_SyssetOpBtn.setTextColor(Color.rgb(255,255,255));
				m_pilotageBtn.setTextColor(Color.rgb(255,255,255));
			}else{
				m_ChartOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_AisOpBtn.setTextColor(Color.rgb(235,173,82));
				m_RouteOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_SyssetOpBtn.setTextColor(Color.rgb(255, 255, 255));
				m_pilotageBtn.setTextColor(Color.rgb(255, 255, 255));
			}
			setBerthingViewShow(View.VISIBLE);

		}
	};

	private void setBerthingViewShow(int visNum) {
		berthingview.setVisibility(visNum);
	}
}
