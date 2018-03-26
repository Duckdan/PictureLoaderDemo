package study.com.pictureloaderdemo.fresco.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.drawee.view.MultiDraweeHolder;

/**
 * 群组头像的控件
 */

public class GroupMultiHeaderView extends View implements Drawable.Callback {

//    private DraweeHolder<GenericDraweeHierarchy> draweeHolder;

    String tag = "gmhv";

    private void log(String msg) {
        Log.e(tag, msg);
    }

    MultiDraweeHolder<GenericDraweeHierarchy> mMultiDraweeHolder;
    private int w;
    private int h;

    public GroupMultiHeaderView(Context context) {
        this(context, null);
    }

    public GroupMultiHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GroupMultiHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources()).build();
        mMultiDraweeHolder = new MultiDraweeHolder<GenericDraweeHierarchy>();
        for (int i = 0; i < 2; i++) {
            DraweeHolder<GenericDraweeHierarchy> draweeHolder = DraweeHolder.create(hierarchy, context);
            draweeHolder.getTopLevelDrawable().setCallback(this);

            mMultiDraweeHolder.add(draweeHolder);
        }
    }


    public void setImageUri(Uri[] uris) {
        for (int i = 0; i < uris.length; i++) {
            DraweeHolder<GenericDraweeHierarchy> draweeHolder = mMultiDraweeHolder.get(i);
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(uris[i])
                    .setOldController(draweeHolder.getController())
                    .build();
            draweeHolder.setController(controller);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;
        log("0");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = this.w / 2;
//        mMultiDraweeHolder.draw(canvas);
        for (int i = 0; i < 2; i++) {
            DraweeHolder<GenericDraweeHierarchy> draweeHolder = mMultiDraweeHolder.get(i);
            Drawable drawable = draweeHolder.getTopLevelDrawable();
            drawable.setBounds(i * w, i * w, (i + 1) * w, (i + 1) * w);
            drawable.draw(canvas);
        }

        log("1");

    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mMultiDraweeHolder.onDetach();
        log("2");
    }

    @Override
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        mMultiDraweeHolder.onDetach();
        log("3");
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mMultiDraweeHolder.onAttach();
        log("4");
    }

    @Override
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        mMultiDraweeHolder.onAttach();
        log("5");
    }

    @Override
    protected boolean verifyDrawable(Drawable who) {
        log("6");
        return mMultiDraweeHolder.verifyDrawable(who) || super.verifyDrawable(who);
    }

    @Override
    public void invalidateDrawable(Drawable drawable) {
        super.invalidateDrawable(drawable);
        log("7");
    }


}
