package study.com.pictureloaderdemo.fresco;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;

import study.com.pictureloaderdemo.R;
import study.com.pictureloaderdemo.fresco.view.GroupHeaderView;
import study.com.pictureloaderdemo.fresco.view.GroupMultiHeaderView;
import study.com.pictureloaderdemo.fresco.view.WxGroupHeaderView;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    private String url1 = "http://192.168.1.29:8080/gdmn.jpg";
    private String url2 = "https://www.baidu.com/img/bd_logo1.png";
    private String url3 = "http://192.168.1.29:8080/gdmn2.jpg";
    private String url4 = "http://192.168.1.29:8080/gdmn3.jpg";
    private String url5 = "http://192.168.1.29:8080/gdmn4.jpg";
    private String url6 = "http://192.168.1.29:8080/gdmn5.jpg";
    private String url7 = "http://192.168.1.29:8080/gdmn6.jpg";
    private String url8 = "http://192.168.1.29:8080/gdmn7.jpg";
    private String url9 = "http://192.168.1.29:8080/gdmn8.jpg";
    private String url10 = "http://192.168.1.29:8080/gdmn9.jpg";
//    本地图片的路径
//    private String url1 = "/storage/emulated/0/MagazineUnlock/magazine-unlock-04-2.3.925-_3d4dfa8a72854c1785f94a364768782b.jpg";
//    private String url2 = "/storage/emulated/0/MagazineUnlock/magazine-unlock-01-2.3.925-_0ed5b66222d844228c7a4e05cba70d25.jpg";
//    private String url3 = "/storage/emulated/0/MagazineUnlock/magazine-unlock-01-2.3.925-_8d421c68c3b7479b801ef1e4c9844aec.jpg";
//    private String url4 = "/storage/emulated/0/MagazineUnlock/magazine-unlock-05-2.3.924-_8d6262b8c52e4a4fa1086fe1ff802468.jpg";
//    private String url5 = "/storage/emulated/0/MagazineUnlock/magazine-unlock-05-2.3.924-_c01cb96b79514519a18066079765f658.jpg";
//    private String url6 = "/storage/emulated/0/MagazineUnlock/magazine-unlock-05-2.3.924-_311ecc4d26a24601ab4a8379f4806344.jpg";
//    private String url7 = "/storage/emulated/0/MagazineUnlock/magazine-unlock-06-2.3.924-_5c78391e163a40dfbef560287e2e7d5d.jpg";
//    private String url8 = "/storage/emulated/0/MagazineUnlock/magazine-unlock-01-2.3.924-_5e5a301175eb491e8a027cf82e9a7a18.jpg";
//    private String url9 = "/storage/emulated/0/MagazineUnlock/magazine-unlock-01-2.3.924-_f075a7877ee4436abf5c149e76174431.jpg";
//    private String url10 = "/storage/emulated/0/MagazineUnlock/magazine-unlock-05-2.3.917-_6cdbb4d79ee74742bc2c936d6bae4723.jpg";


    private String errorUrl = "http://127.0.0.1";
    private SimpleDraweeView sdv;
    private TextView tv;
    private ImageView iv;
    private Uri[] uris = new Uri[2];
    private Uri[] uris1 = new Uri[4];


    private final static float[] MATRIX = new float[]{
            0.5f, 0, 0, 0, 0,
            0, 0.5f, 0, 0, 0,
            0, 0, 0.5f, 0, 0,
            0, 0, 0, 1, 0};
    private WxGroupHeaderView wghv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        sdv = (SimpleDraweeView) findViewById(R.id.sdv);
        tv = (TextView) findViewById(R.id.tv);
        iv = (ImageView) findViewById(R.id.iv);

        pictureRequest();

        wxGroupHeaderView();
    }

    /**
     * 自定义view之添加单个Holder
     */
    private void defineViewSingleHolder() {
        GroupHeaderView ghv1 = (GroupHeaderView) findViewById(R.id.ghv_1);
        GroupHeaderView ghv2 = (GroupHeaderView) findViewById(R.id.ghv_2);
        GroupHeaderView ghv3 = (GroupHeaderView) findViewById(R.id.ghv_3);

        ghv1.setImageUri(Uri.parse(url1));
        ghv2.setImageUri(Uri.parse(url2));
        ghv3.setImageUri(Uri.parse(url1));
    }

    /**
     * 自定义view之添加MultiHolder
     */
    private void defineViewMultiHolder() {
        GroupMultiHeaderView sdv2 = (GroupMultiHeaderView) findViewById(R.id.sdv_2);
        TextView tv2 = (TextView) findViewById(R.id.tv_2);
        ImageView iv2 = (ImageView) findViewById(R.id.iv_2);

        Uri parse1 = Uri.parse(url1);
        Uri parse2 = Uri.parse(url2);

        uris[0] = parse2;
        uris[1] = parse1;

        sdv2.setImageUri(uris);
    }

    /**
     * 处理组图片
     */
    private void wxGroupHeaderView() {
        wghv = (WxGroupHeaderView) findViewById(R.id.wghv);
        wghv.setBackgroundColor(Color.parseColor("#eeffffff"));
        Uri[] uris1 = new Uri[3];
        uris1[0] = Uri.parse(url1);
        uris1[1] = Uri.parse(url3);
        uris1[2] = Uri.parse(url4);
        wghv.setImageUris(uris1);

        TextView tip3 = (TextView) findViewById(R.id.tip_3);
        TextView tip4 = (TextView) findViewById(R.id.tip_4);
        TextView tip5 = (TextView) findViewById(R.id.tip_5);
        TextView tip6 = (TextView) findViewById(R.id.tip_6);
        TextView tip7 = (TextView) findViewById(R.id.tip_7);
        TextView tip8 = (TextView) findViewById(R.id.tip_8);
        TextView tip9 = (TextView) findViewById(R.id.tip_9);
        TextView tip10 = (TextView) findViewById(R.id.tip_10);

        tip3.setOnClickListener(this);
        tip4.setOnClickListener(this);
        tip5.setOnClickListener(this);
        tip6.setOnClickListener(this);
        tip7.setOnClickListener(this);
        tip8.setOnClickListener(this);
        tip9.setOnClickListener(this);
        tip10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePipeline imagePipeline = Fresco.getImagePipeline();
                Uri uri = Uri.parse(url1);
                boolean isMemoryCache = imagePipeline.isInBitmapMemoryCache(uri);  //检查这个uri是否缓存在内存中
                boolean isDiskCacheSync = imagePipeline.isInDiskCacheSync(uri);   //检查这个uri是否同步缓存在磁盘缓存中
                Log.e("position::", isMemoryCache + "==缓存==" + isDiskCacheSync);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Uri[] uris1 = null;
        switch (v.getId()) {
            case R.id.tip_3:
                uris1 = new Uri[3];
                uris1[0] = Uri.parse(url1);
                uris1[1] = Uri.parse(url3);
                uris1[2] = Uri.parse(url4);
                break;
            case R.id.tip_4:
                uris1 = new Uri[4];
                uris1[0] = Uri.parse(url1);
                uris1[1] = Uri.parse(url10);
                uris1[2] = Uri.parse(url3);
                uris1[3] = Uri.parse(url4);
                break;
            case R.id.tip_5:
                uris1 = new Uri[5];
                uris1[0] = Uri.parse(url9);
                uris1[1] = Uri.parse(url10);
                uris1[2] = Uri.parse(url3);
                uris1[3] = Uri.parse(url4);
                uris1[4] = Uri.parse(url5);
                break;
            case R.id.tip_6:
                uris1 = new Uri[6];
                uris1[0] = Uri.parse(url9);
                uris1[1] = Uri.parse(url10);
                uris1[2] = Uri.parse(url3);
                uris1[3] = Uri.parse(url4);
                uris1[4] = Uri.parse(url5);
                uris1[5] = Uri.parse(url6);
                break;
            case R.id.tip_7:
                uris1 = new Uri[7];
                uris1[0] = Uri.parse(url9);
                uris1[1] = Uri.parse(url10);
                uris1[2] = Uri.parse(url3);
                uris1[3] = Uri.parse(url4);
                uris1[4] = Uri.parse(url5);
                uris1[5] = Uri.parse(url6);
                uris1[6] = Uri.parse(url7);
                break;
            case R.id.tip_8:
                uris1 = new Uri[8];
                uris1[0] = Uri.parse(url9);
                uris1[1] = Uri.parse(url10);
                uris1[2] = Uri.parse(url3);
                uris1[3] = Uri.parse(url4);
                uris1[4] = Uri.parse(url5);
                uris1[5] = Uri.parse(url6);
                uris1[6] = Uri.parse(url7);
                uris1[7] = Uri.parse(url8);
                break;
            case R.id.tip_9:
                uris1 = new Uri[9];
                uris1[0] = Uri.parse(url9);
                uris1[1] = Uri.parse(url10);
                uris1[2] = Uri.parse(url3);
                uris1[3] = Uri.parse(url4);
                uris1[4] = Uri.parse(url5);
                uris1[5] = Uri.parse(url6);
                uris1[6] = Uri.parse(url7);
                uris1[7] = Uri.parse(url8);
                uris1[8] = Uri.parse(url1);
                break;
        }
        wghv.setImageUris(uris1);
    }

//    本地图片
//    @Override
//    public void onClick(View v) {
//        Uri[] uris1 = null;
//        switch (v.getId()) {
//            case R.id.tip_3:
//                uris1 = new Uri[3];
//                uris1[0] = Uri.fromFile(new File(url1));
//                uris1[1] = Uri.fromFile(new File(url3));
//                uris1[2] = Uri.fromFile(new File(url4));
//                break;
//            case R.id.tip_4:
//                uris1 = new Uri[4];
//                uris1[0] = Uri.fromFile(new File(url1));
//                uris1[1] = Uri.fromFile(new File(url10));
//                uris1[2] = Uri.fromFile(new File(url3));
//                uris1[3] = Uri.fromFile(new File(url4));
//                break;
//            case R.id.tip_5:
//                uris1 = new Uri[5];
//                uris1[0] = Uri.fromFile(new File(url9));
//                uris1[1] = Uri.fromFile(new File(url10));
//                uris1[2] = Uri.fromFile(new File(url3));
//                uris1[3] = Uri.fromFile(new File(url4));
//                uris1[4] = Uri.fromFile(new File(url5));
//                break;
//            case R.id.tip_6:
//                uris1 = new Uri[6];
//                uris1[0] = Uri.fromFile(new File(url9));
//                uris1[1] = Uri.fromFile(new File(url10));
//                uris1[2] = Uri.fromFile(new File(url3));
//                uris1[3] = Uri.fromFile(new File(url4));
//                uris1[4] = Uri.fromFile(new File(url5));
//                uris1[5] = Uri.fromFile(new File(url6));
//                break;
//            case R.id.tip_7:
//                uris1 = new Uri[7];
//                uris1[0] = Uri.fromFile(new File(url9));
//                uris1[1] = Uri.fromFile(new File(url10));
//                uris1[2] = Uri.fromFile(new File(url3));
//                uris1[3] = Uri.fromFile(new File(url4));
//                uris1[4] = Uri.fromFile(new File(url5));
//                uris1[5] = Uri.fromFile(new File(url6));
//                uris1[6] = Uri.fromFile(new File(url7));
//                break;
//            case R.id.tip_8:
//                uris1 = new Uri[8];
//                uris1[0] = Uri.fromFile(new File(url9));
//                uris1[1] = Uri.fromFile(new File(url10));
//                uris1[2] = Uri.fromFile(new File(url3));
//                uris1[3] = Uri.fromFile(new File(url4));
//                uris1[4] = Uri.fromFile(new File(url5));
//                uris1[5] = Uri.fromFile(new File(url6));
//                uris1[6] = Uri.fromFile(new File(url7));
//                uris1[7] = Uri.fromFile(new File(url8));
//                break;
//            case R.id.tip_9:
//                uris1 = new Uri[9];
//                uris1[0] = Uri.fromFile(new File(url9));
//                uris1[1] = Uri.fromFile(new File(url10));
//                uris1[2] = Uri.fromFile(new File(url3));
//                uris1[3] = Uri.fromFile(new File(url4));
//                uris1[4] = Uri.fromFile(new File(url5));
//                uris1[5] = Uri.fromFile(new File(url6));
//                uris1[6] = Uri.fromFile(new File(url7));
//                uris1[7] = Uri.fromFile(new File(url8));
//                uris1[8] = Uri.fromFile(new File(url1));
//                break;
//        }
//        wghv.setImageUris(uris1);
//    }

    /**
     * 图片请求
     */
    private void pictureRequest() {
        ImageDecodeOptions decodeOptions = ImageDecodeOptions.newBuilder()
                .setBackgroundColor(Color.GREEN)
                .build();

        int width = 200, height = 200;

        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(url10))
                .setImageDecodeOptions(decodeOptions)
                .setAutoRotateEnabled(true)
                .setLocalThumbnailPreviewsEnabled(true)
                .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.FULL_FETCH)
                .setProgressiveRenderingEnabled(false)
                .setResizeOptions(new ResizeOptions(width, height))
                .build();

        PipelineDraweeController controller = (PipelineDraweeController)
                Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setOldController(sdv.getController())
                        // other setters as you need
                        .build();

        sdv.setController(controller);
    }

    /**
     * 缩放图片
     */
    private void scalePicture() {
        int width = 200, height = 200;
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/guzhuang.jpg";

        File file = new File(path);
        Log.e("uri::", file.exists() + "==" + file.getAbsolutePath());
        Uri uri = Uri.fromFile(file);
//        Uri uri = Uri.parse(url1);
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(width, height))
                .build();
        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setOldController(sdv.getController())
                .setImageRequest(request)
                .build();

//        DraweeController controller1 = sdv.getController();

        sdv.setController(controller);
    }

    /**
     * 修改图片
     */
    private void modifyPicture() {
        DefinePostprocessor redMeshPostprocessor = new DefinePostprocessor();
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url1))
                .setPostprocessor(redMeshPostprocessor) //添加后处理器
                .setProgressiveRenderingEnabled(true)  //添加渐进式加载
//                .setAutoRotateEnabled(true)
                .build();


        PipelineDraweeController controller = (PipelineDraweeController)
                Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setOldController(sdv.getController())
                        // other setters as you need
                        .build();
        sdv.setController(controller);
    }


    /**
     * 定义进度条
     */
    private void defineProgressBar() {
        GenericDraweeHierarchy gdh = sdv.getHierarchy();
        gdh.setProgressBarImage(new DefineProgressBar(tv, iv));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.placeholder);
        BitmapDrawable bd = new BitmapDrawable(getResources(), bitmap);
        gdh.setRetryImage(bd);

        RoundingParams roundingParams = gdh.getRoundingParams();

        //设置圆角
        roundingParams.setCornersRadius(100f);
        roundingParams.setRoundAsCircle(true);
        gdh.setRoundingParams(roundingParams);
        //设置颜色过滤器
        ColorMatrixColorFilter colorMatrix = new ColorMatrixColorFilter(MATRIX);
//        colorMatrix.setSaturation(0);  饱和度设置
        gdh.setActualImageColorFilter(colorMatrix);
        sdv.setImageURI(url1);
    }

    /**
     * 激活SimpleDraweeView重新加载的功能,结合setBackgroundHierarchy()方法使用，因为为了设置失败的图片
     */
    private void setTapToRetryEnabled() {
        //监听控制器
        DefineControllerListener listener = new DefineControllerListener();
        DraweeController controller = Fresco.newDraweeControllerBuilder().
                //设置要加载图片uri
                        setUri(errorUrl).
                //允许加载失败之后单击重新请求加载，默认如果重新加载4次之后还是失败的话将会显示failureImage
                        setTapToRetryEnabled(true).
                //节省内存
                        setOldController(sdv.getController()).
                //设置控制器的监听器
                        setControllerListener(listener).
                        build();

        //给SimpleDraweeView设置控制器
        sdv.setController(controller);
    }

    /**
     * 在Java代码中设置SimpleDraweeView的背景图、叠加图、按压的叠加图
     */
    private void setBackgroundHierarchy() {
        GenericDraweeHierarchyBuilder gdhb = new GenericDraweeHierarchyBuilder(getResources());
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.error);
        BitmapDrawable bd = new BitmapDrawable(getResources(), bitmap);
        GenericDraweeHierarchy hierarchy = gdhb.
                //设置背景图
//                        setBackground(bd). //三者单独使用
//                //设置叠加图
//                        setOverlay(bd).
                //设置按压的叠加图
//                        setPressedStateOverlay(bd).
        setFailureImage(bd).
                        build();
        sdv.setHierarchy(hierarchy);
    }


}
