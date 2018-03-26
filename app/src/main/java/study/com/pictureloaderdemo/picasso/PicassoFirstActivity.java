package study.com.pictureloaderdemo.picasso;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import study.com.pictureloaderdemo.R;
import study.com.pictureloaderdemo.picasso.transformation.BlurTransformation;

public class PicassoFirstActivity extends AppCompatActivity {

    private String url1 = "http://192.168.1.29:8080/gdmn.jpg";
    private String url2 = "https://www.baidu.com/img/bd_logo1.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_first);
        ImageView iv = (ImageView) findViewById(R.id.iv);
        method1(iv);
    }

    /**
     * 基本用法01
     *
     * @param iv
     */
    private void method1(ImageView iv) {
        Picasso.with(this).load(url1).transform(new BlurTransformation(this)).into(iv);
    }

    /**
     * 基本用法02,显示指示器
     *
     * @param iv
     */
    private void method2(ImageView iv) {
        //绿色表示从内存加载
        //蓝色表示从磁盘加载
        //红色表示从网络加载
        Picasso.with(this).setIndicatorsEnabled(true);

        Picasso.with(this).
                load(url1).
                into(iv);
    }
}
