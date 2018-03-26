package study.com.pictureloaderdemo.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;

import java.io.File;
import java.util.List;

import study.com.pictureloaderdemo.R;
import study.com.pictureloaderdemo.model.FileItem;

/**
 * Created by Administrator on 2018/3/19.
 */

public class GvAdapter extends BaseAdapter {
    public final Context context;
    public final List<FileItem> items;

    public GvAdapter(Context context, List<FileItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_freco_layout, null);
        }
        SimpleDraweeView sdv = (SimpleDraweeView) convertView.findViewById(R.id.sdv);
        FileItem item = items.get(position);

        ImagePipeline pipeline = Fresco.getImagePipeline();


        Uri uri = null;
        if (item.isData()) {
            uri = Uri.fromFile(new File(item.getPath()));
        } else {
            uri = Uri.parse(item.getPath());
        }
        sdv.setImageURI(uri);

        DataSource<Boolean> diskCache = pipeline.isInDiskCache(uri);
        Boolean result = diskCache.getResult();
        Log.e("position::", "result::" + result);

        boolean isMemoryCache = pipeline.isInBitmapMemoryCache(uri);
        boolean isInCacheSync = pipeline.isInDiskCacheSync(uri);
//        DiskCacheConfig config = DiskCacheConfig.newBuilder(context).build();
//        Fresco.initialize(context, config);
        Log.e("position::",
                uri.toString() + "=isMemoryCache=" +
                        isMemoryCache + "=isInCacheSync=" +
                        isInCacheSync + "=position=" + position);
        return convertView;
    }
}
