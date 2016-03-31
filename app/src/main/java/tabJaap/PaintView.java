package tabJaap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.app.hanumanji.R;

import java.util.ArrayList;

public class PaintView extends View {
	private Paint paint;
	private Path path;
	public ArrayList<MotionEvent> arl;

	public PaintView(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		path = new Path();
		arl = new ArrayList<MotionEvent>();

		paint.setAntiAlias(true);
		paint.setStrokeWidth(28f);
		paint.setColor(getResources().getColor(R.color.paintcolor));
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		int width = canvas.getWidth();
		int height = canvas.getHeight();
		Bitmap b= BitmapFactory.decodeResource(getResources(), R.mipmap.ram_ste);

		float centerX = (width  - b.getWidth()) * 0.5f;
		float centerY = (height- b.getHeight()) * 0.5f;
		canvas.drawBitmap(b,centerX,centerY,paint);
		canvas.drawPath(path, paint);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float eventX = event.getX();
		float eventY = event.getY();
		arl.add(event);

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(eventX, eventY);
			return true;
		case MotionEvent.ACTION_MOVE:
			path.lineTo(eventX, eventY);
			break;
		case MotionEvent.ACTION_UP:
			// nothing to do
			break;
		default:
			return false;
		}

		// Schedules a repaint.
		invalidate();
		return true;
	}
}