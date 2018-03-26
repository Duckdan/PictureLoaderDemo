package study.com.pictureloaderdemo.fresco;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;

import study.com.pictureloaderdemo.R;

/**
 * Fresco的主框架
 */
public class FrescoMainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv1;
    private TextView tv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_fresco_main);
        tv1 = (TextView) findViewById(R.id.tv_1);
        tv1.setOnClickListener(this);

        tv2 = (TextView) findViewById(R.id.tv_2);
        tv2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_1:
                jumpActivity(FirstActivity.class); //使用Fresco的示例
                break;
            case R.id.tv_2:
                jumpActivity(SecondActivity.class);
                break;
        }
    }

    public void jumpActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
