package xhf.project.ardshippilotsys;

import java.util.ArrayList;
import java.util.List;

import xhf.project.chartcore.ChartCoreView;
import xhf.project.chartcorejni.stVCFGEODATASETDESCR;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class ChartFileSelectDlg extends Dialog {

	private int m_layout;

	private Context m_context;

	private int m_nLeftX;
	private int m_nTopY;

	List<String> m_lstFileList = new ArrayList<String>();

	ListView m_ctllistView;

	public ChartFileSelectDlg(Context context, int layout, int nLeftX,
			int nTopY, int style) {
		super(context, style);

		setContentView(layout);

		m_layout = layout;

		m_nLeftX = nLeftX;

		m_nTopY = nTopY;

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
		 * TextView titleView = ((TextView)findViewById(R.id.chartselecttitle));
		 * titleView.setText("  º£Í¼Ñ¡Ôñ");
		 * titleView.setBackgroundColor(Color.rgb(89, 90, 89));
		 * titleView.setTextColor(Color.rgb(255, 255, 255)); TextView bottomView
		 * = ((TextView)findViewById(R.id.chartselectbottom));
		 * bottomView.setBackgroundColor(Color.rgb(89, 90, 89));
		 */

		CreateChartFileInfoList();

		m_ctllistView = (ListView) findViewById(R.id.chartselectlist);
		m_ctllistView.setAdapter(new ArrayAdapter<String>(context,
				R.layout.simplelistview_item_1, m_lstFileList));
		m_ctllistView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String strChartInfo = m_lstFileList.get(position);

				int nIndex = strChartInfo.indexOf(' ');
				if (nIndex > 0) {
					String strChartCode = strChartInfo.substring(0, nIndex);

					ChartCoreView.m_chartCore
							.ManualSingleSelectChart(strChartCode);
				}

				cancel();

			}
		});

		CheckBox autochk = (CheckBox) this
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

		/*
		 * Button okbtn=(Button)this.findViewById(R.id.chtselok);
		 * okbtn.setOnClickListener(new View.OnClickListener() { public void
		 * onClick(View v) { cancel(); } });
		 */
	}

	private void CreateChartFileInfoList() {
		m_lstFileList.removeAll(null);

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

			m_lstFileList.add(strChartInfo);
		}
	}

}
