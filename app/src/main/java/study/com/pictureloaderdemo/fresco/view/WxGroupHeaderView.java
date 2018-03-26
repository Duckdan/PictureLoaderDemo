package study.com.pictureloaderdemo.fresco.view;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 群组头像控件
 */

public class WxGroupHeaderView extends ViewGroup {
    private Context context;
    private int width;
    private int height;
    private int padding = 3;
    private int padding_2; //2倍间隔
    private int padding_3; //3倍间隔

    private int childWidth_half; //0.5倍宽度
    private int childWidth_2; //2倍宽度
    private int childWidth_3; //3倍宽度

    public WxGroupHeaderView(Context context) {
        this(context, null);
    }

    public WxGroupHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WxGroupHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        this.context = context;
        setPadding(padding, padding, padding, padding);
    }

    public void setImageUris(Uri[] uris) {
        removeAllViews();
        for (int i = 0; i < uris.length; i++) {
            GroupHeaderView ghv = new GroupHeaderView(context);
            ghv.setImageUri(uris[i]);
            addView(ghv);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        width = Math.min(w, h); //获取两者之间的最小值
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int childWidth = 0;
        if (count <= 4) {
            childWidth = (int) (width * 1.0 / 2);
        } else {
            childWidth = (int) (width * 1.0 / 3);
        }

        padding_2 = 2 * padding;
        padding_3 = 3 * padding;

        childWidth_half = childWidth / 2;
        childWidth_2 = 2 * childWidth;
        childWidth_3 = 3 * childWidth;

        switch (count) {
            case 3:
                method3(childWidth);
                break;
            case 4:
                method4(childWidth);
                break;
            case 5:
                method5(childWidth);
                break;
            case 6:
                method6(childWidth);
                break;
            case 7:
                method7(childWidth);
                break;
            case 8:
                method8(childWidth);
                break;
            case 9:
                method9(childWidth);
                break;
        }
    }

    //3个图像时
    private void method3(int childWidth) {
        View childAt0 = getChildAt(0);
        childAt0.layout(childWidth_half + padding, padding, childWidth_half + childWidth + padding, childWidth + padding);

        //=============================================================================
        View childAt1 = getChildAt(1);
        childAt1.layout(padding, childWidth + padding_2, childWidth + padding, childWidth_2 + padding_2);

        View childAt2 = getChildAt(2);
        childAt2.layout(childWidth + padding_2, childWidth + padding_2, childWidth_2 + padding_2, childWidth_2 + padding_2);
    }

    //4个图像时
    private void method4(int childWidth) {
        View childAt0 = getChildAt(0);
        childAt0.layout(padding, padding, childWidth + padding, childWidth + padding);

        View childAt1 = getChildAt(1);
        childAt1.layout(childWidth + padding_2, padding, childWidth_2 + padding_2, childWidth + padding);

        //=============================================================================
        View childAt2 = getChildAt(2);
        childAt2.layout(padding, childWidth + padding_2, childWidth + padding, childWidth_2 + padding_2);

        View childAt3 = getChildAt(3);
        childAt3.layout(childWidth + padding_2, childWidth + padding_2, childWidth_2 + padding_2, childWidth_2 + padding_2);
    }

    //五个图像时
    private void method5(int childWidth) {
        View childAt0 = getChildAt(0);
        childAt0.layout(childWidth_half + padding, padding + childWidth_half, childWidth_half + childWidth + padding, childWidth + padding + childWidth_half);

        View childAt1 = getChildAt(1);
        childAt1.layout(childWidth_half + childWidth + padding_2, padding + childWidth_half, childWidth_half + childWidth_2 + padding_2, childWidth + padding + childWidth_half);

        //=============================================================================
        View childAt2 = getChildAt(2);
        childAt2.layout(padding, childWidth + padding_2 + childWidth_half, childWidth + padding, childWidth_2 + padding_2 + childWidth_half);

        View childAt3 = getChildAt(3);
        childAt3.layout(childWidth + padding_2, childWidth + padding_2 + childWidth_half, childWidth_2 + padding_2, childWidth_2 + padding_2 + childWidth_half);

        View childAt4 = getChildAt(4);
        childAt4.layout(childWidth_2 + padding_3, childWidth + padding_2 + childWidth_half, childWidth_3 + padding_3, childWidth_2 + padding_2 + childWidth_half);
    }

    //六个图像时
    private void method6(int childWidth) {

        View childAt0 = getChildAt(0);
        childAt0.layout(padding, padding + childWidth_half, childWidth + padding, childWidth + padding + childWidth_half);

        View childAt1 = getChildAt(1);
        childAt1.layout(childWidth + padding_2, padding + childWidth_half, childWidth_2 + padding_2, childWidth + padding + childWidth_half);

        View childAt2 = getChildAt(2);
        childAt2.layout(childWidth_2 + padding_3, padding + childWidth_half, childWidth_3 + padding_3, childWidth + padding + childWidth_half);

        //=============================================================================
        View childAt3 = getChildAt(3);
        childAt3.layout(padding, childWidth + padding_2 + childWidth_half, childWidth + padding, childWidth_2 + padding_2 + childWidth_half);

        View childAt4 = getChildAt(4);
        childAt4.layout(childWidth + padding_2, childWidth + padding_2 + childWidth_half, childWidth_2 + padding_2, childWidth_2 + padding_2 + childWidth_half);

        View childAt5 = getChildAt(5);
        childAt5.layout(childWidth_2 + padding_3, childWidth + padding_2 + childWidth_half, childWidth_3 + padding_3, childWidth_2 + padding_2 + childWidth_half);

    }


    //七个图像时
    private void method7(int childWidth) {
        View childAt0 = getChildAt(0);
        childAt0.layout(childWidth_half + padding, padding, childWidth_half + childWidth + padding, childWidth + padding);

        View childAt1 = getChildAt(1);
        childAt1.layout(childWidth_half + childWidth + padding_2, padding, childWidth_half + childWidth_2 + padding_2, childWidth + padding);

        //=============================================================================
        View childAt2 = getChildAt(2);
        childAt2.layout(padding, childWidth + padding_2, childWidth + padding, childWidth_2 + padding_2);

        View childAt3 = getChildAt(3);
        childAt3.layout(childWidth + padding_2, childWidth + padding_2, childWidth_2 + padding_2, childWidth_2 + padding_2);

        View childAt4 = getChildAt(4);
        childAt4.layout(childWidth_2 + padding_3, childWidth + padding_2, childWidth_3 + padding_3, childWidth_2 + padding_2);

        //=============================================================================
        View childAt5 = getChildAt(5);
        childAt5.layout(childWidth_half + padding, childWidth_2 + padding_3, childWidth_half + childWidth + padding, childWidth_3 + padding_3);

        View childAt6 = getChildAt(6);
        childAt6.layout(childWidth_half + childWidth + padding_2, childWidth_2 + padding_3, childWidth_half + childWidth_2 + padding_2, childWidth_3 + padding_3);
    }

    //八个图像时
    private void method8(int childWidth) {
        View childAt0 = getChildAt(0);
        childAt0.layout(padding, padding, childWidth + padding, childWidth + padding);

        View childAt1 = getChildAt(1);
        childAt1.layout(childWidth + padding_2, padding, childWidth_2 + padding_2, childWidth + padding);

        View childAt2 = getChildAt(2);
        childAt2.layout(childWidth_2 + padding_3, padding, childWidth_3 + padding_3, childWidth + padding);

        //=============================================================================
        View childAt3 = getChildAt(3);
        childAt3.layout(padding, childWidth + padding_2, childWidth + padding, childWidth_2 + padding_2);

        View childAt4 = getChildAt(4);
        childAt4.layout(childWidth + padding_2, childWidth + padding_2, childWidth_2 + padding_2, childWidth_2 + padding_2);

        View childAt5 = getChildAt(5);
        childAt5.layout(childWidth_2 + padding_3, childWidth + padding_2, childWidth_3 + padding_3, childWidth_2 + padding_2);

        //=============================================================================
        View childAt6 = getChildAt(6);
        childAt6.layout(padding, childWidth_2 + padding_3, childWidth + padding, childWidth_3 + padding_3);

        View childAt7 = getChildAt(7);
        childAt7.layout(childWidth + padding_2, childWidth_2 + padding_3, childWidth_2 + padding_2, childWidth_3 + padding_3);

    }


    private void method9(int childWidth) {
        View childAt0 = getChildAt(0);
        childAt0.layout(padding, padding, childWidth + padding, childWidth + padding);

        View childAt1 = getChildAt(1);
        childAt1.layout(childWidth + padding_2, padding, childWidth_2 + padding_2, childWidth + padding);

        View childAt2 = getChildAt(2);
        childAt2.layout(childWidth_2 + padding_3, padding, childWidth_3 + padding_3, childWidth + padding);

        //=============================================================================
        View childAt3 = getChildAt(3);
        childAt3.layout(padding, childWidth + padding_2, childWidth + padding, childWidth_2 + padding_2);

        View childAt4 = getChildAt(4);
        childAt4.layout(childWidth + padding_2, childWidth + padding_2, childWidth_2 + padding_2, childWidth_2 + padding_2);

        View childAt5 = getChildAt(5);
        childAt5.layout(childWidth_2 + padding_3, childWidth + padding_2, childWidth_3 + padding_3, childWidth_2 + padding_2);

        //=============================================================================
        View childAt6 = getChildAt(6);
        childAt6.layout(padding, childWidth_2 + padding_3, childWidth + padding, childWidth_3 + padding_3);

        View childAt7 = getChildAt(7);
        childAt7.layout(childWidth + padding_2, childWidth_2 + padding_3, childWidth_2 + padding_2, childWidth_3 + padding_3);

        View childAt8 = getChildAt(8);
        childAt8.layout(childWidth_2 + padding_3, childWidth_2 + padding_3, childWidth_3 + padding_3, childWidth_3 + padding_3);
    }

}
