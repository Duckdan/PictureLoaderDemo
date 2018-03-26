package study.com.pictureloaderdemo.fresco;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.AbsListView;
import android.widget.GridView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import study.com.pictureloaderdemo.R;
import study.com.pictureloaderdemo.adapter.GvAdapter;
import study.com.pictureloaderdemo.model.FileItem;

/**
 * 利用Fresco进行列表图片的加载
 */
public class SecondActivity extends AppCompatActivity {

    private GridView gv;
    public List<FileItem> lists = new ArrayList<FileItem>();
    public String first = "";
    public String second = "";
    public String third = "";
    public String forth = "";
    public HashSet<String> pathSet = new HashSet<String>();
    private File file;
    private SimpleDraweeView sdv;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        gv = (GridView) findViewById(R.id.gv);
        sdv = (SimpleDraweeView) findViewById(R.id.sdv);
        first = MediaStore.Images.ImageColumns.DISPLAY_NAME;
        second = MediaStore.Images.ImageColumns.DATA;
        third = MediaStore.Images.ImageColumns.SIZE;
        forth = MediaStore.Images.ImageColumns.DATE_MODIFIED;
        Uri uri = Uri.fromFile(new File("/storage/emulated/0/MagazineUnlock/magazine-unlock-05-2.3.923-_ef6d43a1db914bbcac3525238a376b41.jpg"));
        sdv.setImageURI(uri);//"http://192.168.1.29:8080/gdmn.jpg"
        initData();

        gv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    //空闲
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        Fresco.getImagePipeline().resume();
                        break;
                    //飞滑
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        Fresco.getImagePipeline().pause();
                        break;
                    //触摸滚动
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        Fresco.getImagePipeline().pause();
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private void initData() {
        Observable.create(new ObservableOnSubscribe<Cursor>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Cursor> e) throws Exception {
                Cursor cursor = querySearchData();
                e.onNext(cursor);
            }
        })

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Cursor, List<FileItem>>() {
                    @Override
                    public List<FileItem> apply(@NonNull Cursor cursor) throws Exception {
                        if (cursor != null) {
                            lists.clear();
                            while (cursor.moveToNext()) {
                                String name = cursor.getString(cursor.getColumnIndex(first));
                                String path = cursor.getString(cursor.getColumnIndex(second));
                                long size = cursor.getLong(cursor.getColumnIndex(third));
                                long date = cursor.getLong(cursor.getColumnIndex(forth));
                                name = name == null ? path.substring(path.lastIndexOf("/") + 1, path.length()) : name;
                                if (scannerFile(path) && !name.startsWith(".")) {
                                    FileItem fileItem = new FileItem();
                                    fileItem.setName(TextUtils.isEmpty(name) ? path.substring(path.lastIndexOf("/") + 1, path.length()) : name);
                                    fileItem.setFile(true);
                                    fileItem.setPath(path);
                                    fileItem.setFileSize(size);
                                    fileItem.setLastModifyTime(date * 1000);
                                    fileItem.setData(true);
                                    if (pathSet.contains(fileItem.getPath())) {
                                        fileItem.setChecked(true);
                                    }
                                    lists.add(fileItem);
                                }

                            }
                            cursor.close();
                            cursor = null;
                        }
                        return lists;
                    }
                })

                .subscribe(new Observer<List<FileItem>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<FileItem> items) {
                        updateView(items);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        updateView(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    protected Cursor querySearchData() {
        Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver contentResolver = getContentResolver();
        String[] projection = new String[]{first, second, third, forth};


        Cursor cursor = contentResolver.query(imageUri, projection, null, null,
                forth + " desc ");
        return cursor;
    }

    private void updateView(List<FileItem> items) {
        if (items == null) {
            items = new ArrayList<>();
        }
        GvAdapter adapter = new GvAdapter(this, items);

//        FileItem fileItem1 = new FileItem();
//        fileItem1.setPath(url1);
//        fileItem1.setData(false);
//        items.add(0, fileItem1);
//
//        FileItem fileItem2 = new FileItem();
//        fileItem2.setPath(url2);
//        fileItem2.setData(false);
//        items.add(0, fileItem2);
//
//        FileItem fileItem3 = new FileItem();
//        fileItem3.setPath(url3);
//        fileItem3.setData(false);
//        items.add(0, fileItem3);
//
//        FileItem fileItem4 = new FileItem();
//        fileItem4.setPath(url4);
//        fileItem4.setData(false);
//        items.add(0, fileItem4);
//
//        FileItem fileItem5 = new FileItem();
//        fileItem5.setPath(url5);
//        fileItem5.setData(false);
//        items.add(0, fileItem5);
//
//        FileItem fileItem6 = new FileItem();
//        fileItem6.setPath(url6);
//        fileItem6.setData(false);
//        items.add(0, fileItem6);
//
//        FileItem fileItem7 = new FileItem();
//        fileItem7.setPath(url7);
//        fileItem7.setData(false);
//        items.add(0, fileItem7);
//
//        FileItem fileItem8 = new FileItem();
//        fileItem8.setPath(url8);
//        fileItem8.setData(false);
//        items.add(0, fileItem8);
//
//        FileItem fileItem9 = new FileItem();
//        fileItem9.setPath(url9);
//        fileItem9.setData(false);
//        items.add(0, fileItem9);
//
//        FileItem fileItem10 = new FileItem();
//        fileItem10.setPath(url10);
//        fileItem10.setData(false);
//        items.add(0, fileItem10);

        gv.setAdapter(adapter);
    }

    /**
     * 用于标记文件路径是否有效
     *
     * @param path
     * @return
     */
    public boolean scannerFile(String path) {
        file = new File(path);
        if (file.exists() && file.length() > 0) {
            return true;
        }
        return false;
    }
}
