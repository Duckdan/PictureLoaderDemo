package study.com.pictureloaderdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import study.com.pictureloaderdemo.fresco.FrescoMainActivity;
import study.com.pictureloaderdemo.glide.GlideMainActivity;
import study.com.pictureloaderdemo.picasso.PicassoMainActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.tv_1);
        tv1.setOnClickListener(this);

        tv2 = (TextView) findViewById(R.id.tv_2);
        tv2.setOnClickListener(this);

        tv3 = (TextView) findViewById(R.id.tv_3);
        tv3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_1:
                jumpActivity(FrescoMainActivity.class); //使用Fresco的示例
                break;
            case R.id.tv_2:
                jumpActivity(PicassoMainActivity.class);
                break;
            case R.id.tv_3:
                jumpActivity(GlideMainActivity.class);
                break;
        }
    }


    public void jumpActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
