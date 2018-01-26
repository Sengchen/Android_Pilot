package xhf.project.ardshippilotsys;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SysSettingMenuDlg extends Dialog {

	private int m_layout;

	private PilotSysMainActivity m_parentActivity;

	private int m_nLeftX;
	private int m_nTopY;

	List<String> m_lstMenuItemList = new ArrayList<String>();

	ListView m_ctllistView;

	public SysSettingMenuDlg(PilotSysMainActivity activity, int layout,
			int nLeftX, int nTopY, int style) {
		super(activity, style);

		setContentView(layout);

		m_parentActivity = activity;

		m_layout = layout;

		m_nLeftX = nLeftX;

		m_nTopY = nTopY;

		Window win = getWindow();
		WindowManager.LayoutParams lp = win.getAttributes();

		win.setGravity(Gravity.LEFT | Gravity.TOP);

		lp.x = m_nLeftX - 200;

		lp.y = nTopY - 300;

		lp.width = 140;

		lp.alpha = 1.0f;

		onWindowAttributesChanged(lp);
		win.setAttributes(lp);

		CreateMenuItemList();

		m_ctllistView = (ListView) findViewById(R.id.systmenulistView1);
		m_ctllistView.setAdapter(new ArrayAdapter<String>(activity,
				R.layout.simplelistview_menuitem_1, m_lstMenuItemList));
		m_ctllistView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String strMenuItem = m_lstMenuItemList.get(position);

				if (strMenuItem.compareTo("蓝牙接口") == 0) {
					if (m_parentActivity != null) {
						m_parentActivity.OpenBluetoothSettingWin();
					}
				} else if (strMenuItem.compareTo("网络接口") == 0) {
					if (m_parentActivity != null) {
						m_parentActivity.OpenSocketConfigDlg();
					}

				} else if (strMenuItem.compareTo("船舶参数") == 0) {
					if (m_parentActivity != null) {
						m_parentActivity.OpenShipParamSetDlg();
					}
				}

				cancel();
			}
		});
	}

	private void CreateMenuItemList() {
		m_lstMenuItemList.removeAll(null);
		m_lstMenuItemList.add("船舶参数");
		m_lstMenuItemList.add("蓝牙接口");
		m_lstMenuItemList.add("网络接口");
	}

}
