package study.com.pictureloaderdemo.fresco;

import android.graphics.Bitmap;

import com.facebook.imagepipeline.request.BasePostprocessor;

/**
 * 定义处理器
 */

public class DefinePostprocessor extends BasePostprocessor {
    @Override
    public String getName() {
        return "redMeshPostprocessor";
    }

    //添加红色网格
//    @Override
//    public void process(Bitmap bitmap) {
//        for (int x = 0; x < bitmap.getWidth(); x += 2) {
//            for (int y = 0; y < bitmap.getHeight(); y += 2) {
//                bitmap.setPixel(x, y, Color.RED);
//            }
//        }
//    }

    //水平反转
    @Override
    public void process(Bitmap destBitmap, Bitmap sourceBitmap) {
        for (int x = 0; x < destBitmap.getWidth(); x++) {
            for (int y = 0; y < destBitmap.getHeight(); y++) {
                //将原图片右边的颜色值反转到目标图片的左边达到水平反转的效果
                destBitmap.setPixel(x, y, sourceBitmap.getPixel(sourceBitmap.getWidth() - 1 - x, y));
            }
        }
    }

    //复制成不同大小
//    @Override
//    public CloseableReference<Bitmap> process(Bitmap sourceBitmap, PlatformBitmapFactory bitmapFactory) {
//        CloseableReference<Bitmap> bitmapRef = bitmapFactory.createBitmap(
//                sourceBitmap.getWidth() / 2,
//                sourceBitmap.getHeight() / 2);
//        try {
//            Bitmap destBitmap = bitmapRef.get();
//            for (int x = 0; x < destBitmap.getWidth(); x++) {
//                for (int y = 0; y < destBitmap.getHeight(); y++) {
//                    destBitmap.setPixel(x, y, sourceBitmap.getPixel(x, y));
//                    destBitmap.setHasAlpha(true);
//                }
//            }
//            return CloseableReference.cloneOrNull(bitmapRef);
//        } finally {
//            CloseableReference.closeSafely(bitmapRef);
//        }
//    }
}
