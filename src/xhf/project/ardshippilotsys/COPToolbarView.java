package xhf.project.ardshippilotsys;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class COPToolbarView extends View {

	private PilotSysMainActivity m_parentActive = null;

	public COPToolbarView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public COPToolbarView(Context context) {
		super(context);
	}

	public void Init(PilotSysMainActivity pActivity) {
		if (pActivity == null)
			return;
		m_parentActive = pActivity;

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return true;
	}

}
