package xhf.project.ardshippilotsys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Text;

import xhf.project.chartcore.ChartCoreView;
import xhf.project.dynamictarget.CAisTarget;
import xhf.project.utilitytool.DensityUtil;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AisTargetListAdapter extends BaseAdapter {

	private List<Map<String, String>> lstFileList = new ArrayList<Map<String, String>>();
	private LayoutInflater mInflater = null;
	private Context context;

	private ListView listView;

	public AisTargetListAdapter(Context context, ListView listview) {
		CreateAisTargetInfoList();
		this.mInflater = LayoutInflater.from(context);
		this.listView = listview;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lstFileList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutParams params = listView.getLayoutParams();

		params.height = context.getResources().getDisplayMetrics().heightPixels
				- DensityUtil.dip2px(context, 50)
				- DensityUtil.getStatusBarHeight(context);// ;
		listView.setLayoutParams(params);

		ViewHolder holder = null;

		// 如果缓存convertView为空，则需要创建View
		if (convertView == null) {
			holder = new ViewHolder();
			// 根据自定义的Item布局加载布局
			convertView = mInflater.inflate(R.layout.simplelistview_item_1,
					null);
			holder.img = (ImageView) convertView
					.findViewById(R.id.listview1_img);
			holder.text = (TextView) convertView
					.findViewById(R.id.listview1_text);
			holder.MMSI_text = (TextView) convertView
					.findViewById(R.id.listview1_MMSI);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.text.setText((String) lstFileList.get(position).get("shipname"));
		holder.MMSI_text
				.setText((String) lstFileList.get(position).get("mmsi"));
		// onitemlistener

		return convertView;
	}

	private void CreateAisTargetInfoList() {
		lstFileList.removeAll(null);

		int nCount = ChartCoreView.m_dynaTargetControl.GetAisTargetNums();
//		 int nCount=20;
		int i = 0;
		for (i = 0; i < nCount; i++) {

			CAisTarget pAisTarget = ChartCoreView.m_dynaTargetControl
					.GetAisTarget(i);
			if (pAisTarget == null)
				continue;
			String strMMSI = pAisTarget.GetTargetId();
			// String strCallNo = pAisTarget.GetShipCallNo();
			String strShipName = pAisTarget.GetShipName();
			//
//			 String strMMSI="121212"+i;
//			 String strShipName="adasd"+i;

			Map<String, String> map = new HashMap<String, String>();

			if (strShipName.isEmpty()) {
				strShipName = strMMSI;
			}

			// String strInfo = strShipName;
			//
			// strInfo += " ";
			// strInfo += strCallNo;
			//
			// strInfo += " ";
			// strInfo += strMMSI;
			map.put("shipname", strShipName);
			map.put("mmsi", strMMSI);
			lstFileList.add(map);

		}
	}

	private static class ViewHolder {

		TextView text;
		ImageView img;
		TextView MMSI_text;

	}

}
