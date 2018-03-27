package study.com.pictureloaderdemo.large.view;

/**
 * Created by Administrator on 2018/3/27.
 */

import android.content.Context;
import android.view.MotionEvent;

public abstract class BaseGestureDetector {

    protected boolean mGestureInProgress;

    protected MotionEvent mPreMotionEvent;
    protected MotionEvent mCurrentMotionEvent;

    protected Context mContext;

    public BaseGestureDetector(Context context) {
        mContext = context;
    }


    /**
     * 接收触摸事件
     *
     * @param event
     * @return
     */
    public boolean onToucEvent(MotionEvent event) {

        if (!mGestureInProgress) {
            handleStartProgressEvent(event);
        } else {
            handleInProgressEvent(event);
        }

        return true;

    }

    /**
     * 处理进度中事件
     *
     * @param event
     */
    protected abstract void handleInProgressEvent(MotionEvent event);

    /**
     * 处理开始进度事件
     *
     * @param event
     */
    protected abstract void handleStartProgressEvent(MotionEvent event);

    /**
     * 通过事件改变状态
     *
     * @param event
     */
    protected abstract void updateStateByEvent(MotionEvent event);

    /**
     * 重置状态
     */
    protected void resetState() {
        if (mPreMotionEvent != null) {
            mPreMotionEvent.recycle();
            mPreMotionEvent = null;
        }
        if (mCurrentMotionEvent != null) {
            mCurrentMotionEvent.recycle();
            mCurrentMotionEvent = null;
        }
        mGestureInProgress = false;
    }


}
