package study.com.pictureloaderdemo.fresco;

import android.graphics.drawable.Animatable;
import android.util.Log;

import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;

/**
 * 自定义控制器监听者
 * ImageInfo:包含图像信息的接口
 */
public class DefineControllerListener extends BaseControllerListener<ImageInfo> {

    String tag = "defineCL";

    private void log(String msg) {
        Log.e(tag, msg);
    }

    /**
     * 在提交图像请求之前调用
     * 注意：在这个回调中重用控制器是不安全的！
     * <p>
     * 请求在加载图片之前调用
     *
     * @param id
     * @param callerContext
     */
    @Override
    public void onSubmit(String id, Object callerContext) {
        super.onSubmit(id, callerContext);
        log("1");
    }

    /**
     * 在最终图像被设置后调用
     * <p>
     * 图片加载成功之后调用
     *
     * @param id
     * @param imageInfo
     * @param animatable
     */
    @Override
    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
        super.onFinalImageSet(id, imageInfo, animatable);
        log("2" + imageInfo.getWidth() + "==" + imageInfo.getHeight());
    }

    /**
     * 在设置任何中间图像后调用。
     *
     * @param id
     * @param imageInfo
     */
    @Override
    public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
        super.onIntermediateImageSet(id, imageInfo);
        log("3");
    }

    /**
     * 在获取中间映像失败后调用。
     *
     * @param id
     * @param throwable
     */
    @Override
    public void onIntermediateImageFailed(String id, Throwable throwable) {
        super.onIntermediateImageFailed(id, throwable);
        log("4");
    }

    /**
     * 在最终图像提取失败后调用
     * <p>
     * 图像加载失败后调用
     *
     * @param id
     * @param throwable
     */
    @Override
    public void onFailure(String id, Throwable throwable) {
        super.onFailure(id, throwable);
        log("5");
    }

    /**
     * 在控制器释放所获取的图像后调用
     * 注意：在这个回调中重用控制器是不安全的！
     *
     * @param id 控制器Id
     */
    @Override
    public void onRelease(String id) {
        super.onRelease(id);
        log("6");
    }
}
