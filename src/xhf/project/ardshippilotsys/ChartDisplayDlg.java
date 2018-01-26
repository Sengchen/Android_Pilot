package xhf.project.ardshippilotsys;

import xhf.project.chartcore.ChartCoreView;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class ChartDisplayDlg extends Dialog {

	public ChartDisplayDlg(Context context, int layout, int nLeftX, int nTopY,
			int style) {
		super(context, style);

		setContentView(layout);

		Window win = getWindow();
		WindowManager.LayoutParams lp = win.getAttributes();

		win.setGravity(Gravity.LEFT | Gravity.TOP);

		lp.x = 5;// (m_nLeftX-lp.width)/2;

		lp.y = 10;
		lp.width = 350;

		lp.alpha = 1.0f;

		onWindowAttributesChanged(lp);
		win.setAttributes(lp);

		/*
		 * TextView titleView = ((TextView)findViewById(R.id.chartdisptitle));
		 * titleView.setText("  ∫£Õºœ‘ æ");
		 * titleView.setBackgroundColor(Color.rgb(89, 90, 89));
		 * titleView.setTextColor(Color.rgb(255, 255, 255)); TextView bottomView
		 * = ((TextView)findViewById(R.id.chartdispbottom));
		 * bottomView.setBackgroundColor(Color.rgb(89, 90, 89));
		 */

		RadioButton baseDspRadio = (RadioButton) findViewById(R.id.basedspradio);
		baseDspRadio.setTextColor(Color.rgb(0, 0, 0));

		RadioButton stdDspRadio = (RadioButton) findViewById(R.id.stddspradio);
		stdDspRadio.setTextColor(Color.rgb(0, 0, 0));

		RadioButton allDspRadio = (RadioButton) findViewById(R.id.alldspradio);
		allDspRadio.setTextColor(Color.rgb(0, 0, 0));

		int nDisplayMode = ChartCoreView.m_chartCore.GetDisplayMode();
		if (nDisplayMode == 0) {
			baseDspRadio.setChecked(true);
		} else if (nDisplayMode == 1) {
			stdDspRadio.setChecked(true);
		} else if (nDisplayMode == 2) {
			allDspRadio.setChecked(true);
		}

		RadioButton daybkradio = (RadioButton) findViewById(R.id.daybkradio);
		daybkradio.setTextColor(Color.rgb(0, 0, 0));

		RadioButton duskbkRadio = (RadioButton) findViewById(R.id.duskbkradio);
		duskbkRadio.setTextColor(Color.rgb(0, 0, 0));

		RadioButton nightbkRadio = (RadioButton) findViewById(R.id.nightbkradio);
		nightbkRadio.setTextColor(Color.rgb(0, 0, 0));

		int nBkMode = ChartCoreView.m_chartCore.GetColorMode();
		if (nBkMode == 1) {

			daybkradio.setChecked(true);

		} else if (nBkMode == 3) {
			duskbkRadio.setChecked(true);

		} else if (nBkMode == 4) {

			nightbkRadio.setChecked(true);

		}

		RadioButton northradio = (RadioButton) findViewById(R.id.northupradio);
		northradio.setTextColor(Color.rgb(0, 0, 0));

		RadioButton courseRadio = (RadioButton) findViewById(R.id.courseupradio);
		courseRadio.setTextColor(Color.rgb(0, 0, 0));

		RadioButton navlRadio = (RadioButton) findViewById(R.id.navlineupradio);
		navlRadio.setTextColor(Color.rgb(0, 0, 0));
		int nOrieanMode = ChartCoreView.m_chartCore.GetChartOrientMode();
		if (nOrieanMode == 0) {
			northradio.setChecked(true);

		} else if (nOrieanMode == 1) {
			courseRadio.setChecked(true);
		} else if (nOrieanMode == 3) {
			navlRadio.setChecked(true);

		}

		RadioGroup dspmode = (RadioGroup) findViewById(R.id.layerdspradiogroup);
		dspmode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				RadioButton baseDspRadio = (RadioButton) findViewById(R.id.basedspradio);
				RadioButton stdDspRadio = (RadioButton) findViewById(R.id.stddspradio);
				if (baseDspRadio.isChecked()) {
					ChartCoreView.m_chartCore.ChartDisplayMode(0);

				} else if (stdDspRadio.isChecked()) {
					ChartCoreView.m_chartCore.ChartDisplayMode(1);

				} else {
					ChartCoreView.m_chartCore.ChartDisplayMode(2);
				}
			}
		});

		RadioGroup bkmode = (RadioGroup) findViewById(R.id.bkmodegroup);
		bkmode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				RadioButton nightradio = (RadioButton) findViewById(R.id.nightbkradio);
				RadioButton duskadio = (RadioButton) findViewById(R.id.duskbkradio);
				if (nightradio.isChecked()) {

					ChartCoreView.m_chartCore.ChartColorMode(4);

				} else if (duskadio.isChecked()) {
					ChartCoreView.m_chartCore.ChartColorMode(3);

				} else {
					ChartCoreView.m_chartCore.ChartColorMode(1);
				}
			}
		});

		RadioGroup orieanmode = (RadioGroup) findViewById(R.id.orieanmodegroup);
		orieanmode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				RadioButton courseradio = (RadioButton) findViewById(R.id.courseupradio);
				RadioButton navlradio = (RadioButton) findViewById(R.id.navlineupradio);
				if (navlradio.isChecked()) {

					// ChartCoreView.m_chartCore.ManualChartOrientMode(3);

				} else if (courseradio.isChecked()) {

					// ChartCoreView.m_chartCore.ManualChartOrientMode(1);

				} else {

					// ChartCoreView.m_chartCore.ManualChartOrientMode(0);
				}
			}
		});

	}

}
