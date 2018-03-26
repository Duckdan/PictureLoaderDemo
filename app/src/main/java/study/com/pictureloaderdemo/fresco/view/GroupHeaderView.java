package study.com.pictureloaderdemo.fresco.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.drawee.view.MultiDraweeHolder;

/**
 * 群组头像的单个控件
 */

public class GroupHeaderView extends View implements Drawable.Callback {

    private DraweeHolder<GenericDraweeHierarchy> draweeHolder;

    MultiDraweeHolder<GenericDraweeHierarchy> mMultiDraweeHolder;
    private int w;
    private int h;

    public GroupHeaderView(Context context) {
        this(context, null);
    }

    public GroupHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GroupHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        draweeHolder = DraweeHolder.create(hierarchy, context);
        draweeHolder.getTopLevelDrawable().setCallback(this);
    }

    public void setImageUri(Uri uri) {

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setOldController(draweeHolder.getController())
                .build();
        draweeHolder.setController(controller);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable drawable = draweeHolder.getTopLevelDrawable();
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        draweeHolder.onDetach();
    }

    @Override
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        draweeHolder.onDetach();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        draweeHolder.onAttach();
    }

    @Override
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        draweeHolder.onAttach();
    }

    @Override
    protected boolean verifyDrawable(Drawable who) {
        if (who == draweeHolder.getTopLevelDrawable()) {
            return true;
        }
        return super.verifyDrawable(who);
    }

    @Override
    public void invalidateDrawable(Drawable drawable) {
        super.invalidateDrawable(drawable);
    }


}
