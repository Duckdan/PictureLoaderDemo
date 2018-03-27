package study.com.pictureloaderdemo.large;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

import study.com.pictureloaderdemo.R;
import study.com.pictureloaderdemo.large.view.LargeImageView;

public class LargeImageActivity extends AppCompatActivity {

    private LargeImageView mLargeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_large_image);

        mLargeImageView = (LargeImageView) findViewById(R.id.id_largetImageview);
        try {
            InputStream inputStream = getAssets().open("qm.jpg");
            mLargeImageView.setInputStream(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
