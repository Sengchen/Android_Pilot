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

public class COpenPlanRouteDlg extends Dialog {

	private int m_layout;

	private Context m_context;

	private int m_nLeftX;
	private int m_nTopY;

	List<String> m_lstFileList = new ArrayList<String>();

	ListView m_ctllistView;

	String m_strSelectRoute = "";
	int m_nSelectIndex = -1;

	public COpenPlanRouteDlg(Context context, int layout, int nLeftX,
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

		// lp.width = 300;

		lp.alpha = 1.0f;

		onWindowAttributesChanged(lp);
		win.setAttributes(lp);

		CreateRouteInfoList();

		m_ctllistView = (ListView) findViewById(R.id.openroutelistView1);
		m_ctllistView.setAdapter(new ArrayAdapter<String>(context,
				R.layout.simplelistview_item_1, m_lstFileList));
		m_ctllistView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				m_strSelectRoute = m_lstFileList.get(position);

				m_nSelectIndex = position;

			}
		});

		Button openbtn = (Button) this.findViewById(R.id.openroutebtn1);
		openbtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				ChartCoreView.m_planRouteManage.OpenPlanRoute(m_strSelectRoute);
				cancel();
			}
		});

		Button delbtn = (Button) this.findViewById(R.id.delroutebtn1);
		delbtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (ChartCoreView.m_planRouteManage
						.DeleteRouteFile(m_strSelectRoute)) {
					if (m_nSelectIndex >= 0)
						m_lstFileList.remove(m_nSelectIndex);

				}
			}
		});

	}

	private void CreateRouteInfoList() {
		m_lstFileList.clear();

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

				m_lstFileList.add(strName);

			}
		}
	}
}
