package study.com.pictureloaderdemo.glide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import study.com.pictureloaderdemo.R;

public class GlideFirstActivity extends AppCompatActivity {

    private String url1 = "http://192.168.1.29:8080/gdmn.jpg";
    private String url2 = "https://www.baidu.com/img/bd_logo1.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_first);
        ImageView iv = (ImageView) findViewById(R.id.iv);
        method1(iv);
    }

    private void method1(ImageView iv) {
        RequestManager requestManager = Glide.with(GlideFirstActivity.this);
        requestManager.load(url1).into(iv);
    }
}
