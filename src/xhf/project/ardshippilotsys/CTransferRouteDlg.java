package xhf.project.ardshippilotsys;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import xhf.project.chartcore.ChartCoreView;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class CTransferRouteDlg extends Dialog {

	private int m_layout;

	private Context m_context;

	private int m_nLeftX;
	private int m_nTopY;

	List<String> m_lstFileList = new ArrayList<String>();

	ListView m_ctllistView;

	String m_strSelectRoute = "";

	public CTransferRouteDlg(Context context, int layout, int nLeftX,
			int nTopY, int style) {
		super(context, style);

		setContentView(layout);

		m_layout = layout;

		m_nLeftX = nLeftX;

		m_nTopY = nTopY;

		Window win = getWindow();
		WindowManager.LayoutParams lp = win.getAttributes();

		win.setGravity(Gravity.LEFT | Gravity.TOP);

		lp.x = 20;// (m_nLeftX-lp.width)/2;

		lp.y = 40;

		lp.height = 400;

		lp.alpha = 1.0f;

		onWindowAttributesChanged(lp);
		win.setAttributes(lp);

		TextView titleView = ((TextView) findViewById(R.id.tranfroutedlgtitletextView1));
		titleView.setText("  调用航路");
		titleView.setBackgroundColor(Color.rgb(89, 90, 89));
		titleView.setTextColor(Color.rgb(255, 255, 255));

		CreateRouteInfoList();

		m_ctllistView = (ListView) findViewById(R.id.tranfroutelistView1);
		m_ctllistView.setAdapter(new ArrayAdapter<String>(context,
				R.layout.simplelistview_item_1, m_lstFileList));
		m_ctllistView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				m_strSelectRoute = m_lstFileList.get(position);

				if (m_strSelectRoute.compareTo("无") == 0) {
					ChartCoreView.m_planRouteManage.PlanRouteMonClose();

				} else if (!m_strSelectRoute.isEmpty()) {

					ChartCoreView.m_planRouteManage
							.PlanRouteMonTransfer(m_strSelectRoute);
				}

				cancel();
			}
		});
	}

	private void CreateRouteInfoList() {
		m_lstFileList.clear();

		m_lstFileList.add("无");
		String strFilePath = ChartCoreView.GetSystemDataFileDirectory();

		strFilePath += "planroutelib";

		File file = new File(strFilePath);

		if (file.exists()) {

			if (file.isDirectory()) { // 判断是否是文件
				File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
					String strName = files[i].getName();

					int nIndex = strName.indexOf('.');
					if (nIndex > 0)
						strName = strName.substring(0, nIndex);

					m_lstFileList.add(strName);

				}
			}
		}

	}
}
