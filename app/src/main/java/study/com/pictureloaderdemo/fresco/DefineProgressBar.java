package study.com.pictureloaderdemo.fresco;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/2/1.
 */

public class DefineProgressBar extends Drawable {

    private ImageView iv;
    private TextView tv;
    private RotateAnimation rotateAnimation = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);

    public DefineProgressBar() {
    }

    public DefineProgressBar(TextView tv, ImageView iv) {
        this.tv = tv;
        this.iv = iv;
        rotateAnimation.setDuration(3000);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        iv.setAnimation(rotateAnimation);
        rotateAnimation.start();
    }

    @Override
    protected boolean onLevelChange(int level) {
        if (level % 100 == 0) {
            Log.e("customer::", level + "==1000==");
        }
        Log.e("customer::", level + "==000==");
        tv.setText((level / 100) + "%");
        return true;
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(ColorFilter filter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }
}
