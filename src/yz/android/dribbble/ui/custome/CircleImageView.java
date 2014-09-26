package yz.android.dribbble.ui.custome;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 圆形的Imageview
 * 
 * @since 2012-11-02
 * 
 * @author bingyang.djj
 * 
 */
public class CircleImageView extends ImageView {
	private Paint paint = new Paint();

	public CircleImageView(Context context) {
		super(context);
	}

	public CircleImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CircleImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		Drawable drawable = getDrawable();
		if (null != drawable) {
			Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
			Bitmap b = toRoundCorner(bitmap);
			final Rect rect = new Rect(0, 0, b.getWidth(), b.getHeight());
			paint.reset();
			canvas.drawBitmap(b, rect, rect, paint);

		} else {
			super.onDraw(canvas);
		}
	}

	private Bitmap toRoundCorner(Bitmap bitmap) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xffffffff;
		int width = getLayoutParams().width;
		int height = getLayoutParams().height;

		int w = (bitmap.getWidth() - width)/2;
		int h = (bitmap.getHeight() - height)/2;
		final Rect rect = new Rect(w, h, bitmap.getWidth() - w, bitmap.getWidth()- h);
		final Rect rect2 = new Rect(0, 0, width, height);
		
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawCircle(width / 2 , width / 2, width / 2, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect2, paint);
		return output;
	}
}