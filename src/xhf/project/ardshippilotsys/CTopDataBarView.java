package xhf.project.ardshippilotsys;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CTopDataBarView extends View {

	public CTopDataBarView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	public CTopDataBarView(Context context) {
		super(context);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return true;
	}

}
