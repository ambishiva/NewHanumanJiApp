package customlayout;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextViewRegular extends TextView{

	public CustomTextViewRegular(Context context) {
		super(context);
		init();
	}

	public CustomTextViewRegular(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CustomTextViewRegular(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	public void init() {
		Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
				"fonts/SAMARN.TTF");
		setTypeface(tf, 1);
	}



}
