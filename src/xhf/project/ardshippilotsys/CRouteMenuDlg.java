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

public class CRouteMenuDlg extends Dialog {

	private int m_layout;

	private PilotSysMainActivity m_parentActivity;

	private int m_nLeftX;
	private int m_nTopY;

	List<String> m_lstMenuItemList = new ArrayList<String>();

	ListView m_ctllistView;

	public CRouteMenuDlg(PilotSysMainActivity activity, int layout, int nLeftX,
			int nTopY, int style) {
		super(activity, style);

		setContentView(layout);

		m_parentActivity = activity;

		m_layout = layout;

		m_nLeftX = nLeftX;

		m_nTopY = nTopY;

		Window win = getWindow();
		WindowManager.LayoutParams lp = win.getAttributes();

		win.setGravity(Gravity.LEFT | Gravity.TOP);

		lp.x = 260;// (m_nLeftX-lp.width)/2;

		lp.y = nTopY - 240;

		lp.width = 140;

		lp.alpha = 1.0f;

		onWindowAttributesChanged(lp);
		win.setAttributes(lp);

		CreateMenuItemList();

		m_ctllistView = (ListView) findViewById(R.id.routemenulistView1);
		m_ctllistView.setAdapter(new ArrayAdapter<String>(activity,
				R.layout.simplelistview_menuitem_1, m_lstMenuItemList));
		m_ctllistView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String strMenuItem = m_lstMenuItemList.get(position);

				if (strMenuItem.compareTo("航路绘制") == 0) {
					if (m_parentActivity != null) {
						m_parentActivity.OpenRouteEditDlg();
					}
				} else if (strMenuItem.compareTo("航路调用") == 0) {
					if (m_parentActivity != null) {
						m_parentActivity.TransferRouteDlg();
					}
				}

				cancel();
			}
		});
	}

	private void CreateMenuItemList() {
		m_lstMenuItemList.removeAll(null);
		m_lstMenuItemList.add("航路绘制");
		m_lstMenuItemList.add("航路调用");
	}

}
