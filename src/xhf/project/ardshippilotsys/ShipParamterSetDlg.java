package xhf.project.ardshippilotsys;

import xhf.project.chartcore.ChartCoreView;
import xhf.project.datamanage.CShipStaticData;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ShipParamterSetDlg extends Dialog {

	public ShipParamterSetDlg(Context context, int layout, int nLeftX,
			int nTopY, int style) {
		super(context, style);

		setContentView(layout);

		Window win = getWindow();
		WindowManager.LayoutParams lp = win.getAttributes();

		win.setGravity(Gravity.LEFT | Gravity.TOP);

		lp.x = 5;// (m_nLeftX-lp.width)/2;

		lp.y = 10;

		lp.width = nLeftX - 80;

		lp.height = nTopY / 2 + 100;

		lp.alpha = 1.0f;

		onWindowAttributesChanged(lp);
		win.setAttributes(lp);

		String strName = ChartCoreView.m_dynaTargetControl.GetPilotShipName();

		String strCallNo = ChartCoreView.m_dynaTargetControl
				.GetPilotShipCallNo();

		int nTxHead = ChartCoreView.m_dynaTargetControl.GetPilotShipTxHead();

		int nTxLeft = ChartCoreView.m_dynaTargetControl.GetPilotShipTxLeft();

		int nShipL = ChartCoreView.m_dynaTargetControl.GetPilotShipLength();

		int nShipW = ChartCoreView.m_dynaTargetControl.GetPilotShipWidth();

		EditText mName = (EditText) findViewById(R.id.cbcsnameeditText1);

		mName.setText(strName);

		EditText mCall = (EditText) findViewById(R.id.cbcscallnoeditText1);

		mCall.setText(strCallNo);

		EditText mShipL = (EditText) findViewById(R.id.cbcsshipleditText1);

		mShipL.setText(Integer.toString(nShipL));

		EditText mShipW = (EditText) findViewById(R.id.cbcsshipweditText1);

		mShipW.setText(Integer.toString(nShipW));

		EditText mTxHead = (EditText) findViewById(R.id.cbcstxhdeditText1);

		mTxHead.setText(Integer.toString(nTxHead));

		EditText mTxLeft = (EditText) findViewById(R.id.cbcstxlteditText2);

		mTxLeft.setText(Integer.toString(nTxLeft));

		/*
		 * Button okbtn=(Button)this.findViewById(R.id.cbcssetdlgokbtn);
		 * okbtn.setOnClickListener(new View.OnClickListener() { public void
		 * onClick(View v) { EditText
		 * mName=(EditText)findViewById(R.id.cbcsnameeditText1);
		 * 
		 * String strShipName = mName.getText().toString();
		 * 
		 * EditText mCall=(EditText)findViewById(R.id.cbcscallnoeditText1);
		 * 
		 * String strCallNo= mCall.getText().toString();
		 * 
		 * EditText mShipL=(EditText)findViewById(R.id.cbcsshipleditText1);
		 * 
		 * int nL =Integer.parseInt(mShipL.getText().toString());
		 * 
		 * 
		 * EditText mShipW=(EditText)findViewById(R.id.cbcsshipweditText1);
		 * 
		 * int nW = Integer.parseInt(mShipW.getText().toString());
		 * 
		 * EditText mTxHead=(EditText)findViewById(R.id.cbcstxhdeditText1);
		 * 
		 * int nTxH =Integer.parseInt(mTxHead.getText().toString());
		 * 
		 * EditText mTxLeft=(EditText)findViewById(R.id.cbcstxlteditText2);
		 * 
		 * int nTxL = Integer.parseInt(mTxLeft.getText().toString());
		 * 
		 * 
		 * ChartCoreView.m_dynaTargetControl.SetPilotShipParamterInfo(strShipName
		 * ,strCallNo,nTxH,nL-nTxH,nTxL,nW-nTxL);
		 * 
		 * CShipStaticData stData = new CShipStaticData();
		 * stData.SetName(strShipName); stData.SetCallNo(strCallNo);
		 * stData.SetTxHead(nTxH); stData.SetTxTail(nL-nTxH);
		 * stData.SetTxLeft(nTxL); stData.SetTxRight(nW-nTxL);
		 * 
		 * ChartCoreView.m_pilotDataManage.SaveShipParameter(stData);
		 * 
		 * cancel(); } });
		 * 
		 * Button cancelbtn=(Button)this.findViewById(R.id.cbcssetdlgcancelbtn);
		 * cancelbtn.setOnClickListener(new View.OnClickListener() { public void
		 * onClick(View v) { cancel(); } });
		 */
	}

}
