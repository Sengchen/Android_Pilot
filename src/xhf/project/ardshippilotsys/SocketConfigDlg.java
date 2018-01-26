package xhf.project.ardshippilotsys;

import xhf.project.chartcore.ChartCoreView;
import xhf.project.sensorctl.CSockCommConfig;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class SocketConfigDlg extends Dialog {

	CSockCommConfig m_msockdata = null;

	public SocketConfigDlg(Context context, int layout, int nLeftX, int nTopY,
			int style) {
		super(context, style);

		setContentView(layout);

		Window win = getWindow();
		WindowManager.LayoutParams lp = win.getAttributes();

		win.setGravity(Gravity.LEFT | Gravity.TOP);

		lp.x = 5;// (m_nLeftX-lp.width)/2;

		lp.y = 10;

		lp.width = nLeftX - 60;

		// lp.height = nTopY/2+100;

		lp.alpha = 1.0f;

		onWindowAttributesChanged(lp);
		win.setAttributes(lp);

		m_msockdata = PilotSysMainActivity.m_PilotSensorControl
				.GetSocketConfig();

		EditText mServIP = (EditText) findViewById(R.id.sockcfgsvripeditText1);

		mServIP.setText(m_msockdata.m_strServerIP);

		EditText mServPort = (EditText) findViewById(R.id.sockcfgsvrporteditText1);

		mServPort.setText(Integer.toString(m_msockdata.m_nServerPort));

		EditText mLocalPort = (EditText) findViewById(R.id.sockcfglocporteditText1);

		mLocalPort.setText(Integer.toString(m_msockdata.m_nLocalPort));

		CheckBox autochk = (CheckBox) this.findViewById(R.id.sockcfgchk1);
		boolean bChk = m_msockdata.m_nActive;

		autochk.setChecked(bChk);

		autochk.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (((CheckBox) v).isChecked()) {
					m_msockdata.m_nActive = true;
					PilotSysMainActivity.m_PilotSensorControl
							.SetSocketConfig(m_msockdata);
				} else {
					m_msockdata.m_nActive = false;
					PilotSysMainActivity.m_PilotSensorControl
							.SetSocketConfig(m_msockdata);
				}
			}
		});

		/*
		 * Button okbtn=(Button)this.findViewById(R.id.sockcfgdlgokbtn);
		 * okbtn.setOnClickListener(new View.OnClickListener() { public void
		 * onClick(View v) { EditText
		 * mServIP=(EditText)findViewById(R.id.sockcfgsvripeditText1);
		 * 
		 * m_msockdata.m_strServerIP = mServIP.getText().toString();
		 * 
		 * EditText
		 * mServPort=(EditText)findViewById(R.id.sockcfgsvrporteditText1);
		 * 
		 * m_msockdata.m_nServerPort = Integer.parseInt(
		 * mServPort.getText().toString());
		 * 
		 * EditText
		 * mLocalPort=(EditText)findViewById(R.id.sockcfglocporteditText1);
		 * 
		 * m_msockdata.m_nLocalPort = Integer.parseInt(
		 * mLocalPort.getText().toString());
		 * 
		 * PilotSysMainActivity.m_PilotSensorControl.SetSocketConfig(m_msockdata)
		 * ;
		 * 
		 * cancel(); } });
		 * 
		 * 
		 * Button cancelbtn=(Button)this.findViewById(R.id.sockcfgdlgcancelbtn);
		 * cancelbtn.setOnClickListener(new View.OnClickListener() { public void
		 * onClick(View v) { cancel(); } });
		 */

	}

}
