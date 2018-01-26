package xhf.project.ardshippilotsys;

import java.util.ArrayList;
import java.util.List;

import xhf.project.chartcore.ChartCoreView;
import xhf.project.dynamictarget.CAisTarget;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class AisTargetListDlg extends Dialog {

	private int m_layout;

	private Context m_context;

	private int m_nLeftX;
	private int m_nTopY;

	List<String> m_lstFileList = new ArrayList<String>();

	ListView m_ctllistView;

	public AisTargetListDlg(Context context, int layout, int nLeftX, int nTopY,
			int style) {
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

		lp.width = m_nLeftX;

		lp.alpha = 1.0f;

		onWindowAttributesChanged(lp);
		win.setAttributes(lp);

		// TextView titleView =
		// ((TextView)findViewById(R.id.aisdlgtitletextView1));
		// titleView.setText("  AISÄ¿±ê");
		// titleView.setBackgroundColor(Color.rgb(89, 90, 89));
		// titleView.setTextColor(Color.rgb(255, 255, 255));
		// TextView bottomView =
		// ((TextView)findViewById(R.id.aisdlgbtmtltextView1));
		// bottomView.setBackgroundColor(Color.rgb(89, 90, 89));

		CreateAisTargetInfoList();

		m_ctllistView = (ListView) findViewById(R.id.aistargetlistView1);
		m_ctllistView.setAdapter(new ArrayAdapter<String>(context,
				R.layout.simplelistview_item_1, m_lstFileList));
		m_ctllistView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String strInfo = m_lstFileList.get(position);

				int nIndex = strInfo.indexOf(' ');
				if (nIndex > 0) {
					int len = strInfo.length();
					strInfo = strInfo.substring(nIndex + 1, len - nIndex - 1);

					nIndex = strInfo.indexOf(' ');

					if (nIndex >= 0) {
						len = strInfo.length();
						strInfo = strInfo.substring(nIndex + 1, len - nIndex
								- 1);

						ChartCoreView.m_dynaTargetControl
								.SelectAisTarget(strInfo);
					}

				}

			}
		});

		// Button okbtn=(Button)this.findViewById(R.id.aisdlgokbtn);
		// okbtn.setOnClickListener(new View.OnClickListener()
		// {
		// public void onClick(View v)
		// {
		// cancel();
		// }
		// });
	}

	private void CreateAisTargetInfoList() {
		m_lstFileList.removeAll(null);

		int nCount = ChartCoreView.m_dynaTargetControl.GetAisTargetNums();
		// int nCount = 20;
		int i = 0;
		for (i = 0; i < nCount; i++) {

			CAisTarget pAisTarget = ChartCoreView.m_dynaTargetControl
					.GetAisTarget(i);
			if (pAisTarget == null)
				continue;

			String strMMSI = pAisTarget.GetTargetId();

			// static
			String strCallNo = pAisTarget.GetShipCallNo();
			;
			String strShipName = pAisTarget.GetShipName();

			// String strMMSI="1111111"+i;

			// static
			// String strCallNo = "2222222"+i;
			// String strShipName="ShipName"+i;

			if (strShipName.isEmpty()) {
				strShipName = strMMSI;
			}

			String strInfo = strShipName;

			strInfo += " ";
			strInfo += strCallNo;

			strInfo += " ";
			strInfo += strMMSI;

			m_lstFileList.add(strInfo);
			// m_lstFileList.add("111121314"+i);

		}
	}
}
