package com.kelompok3.misapps.Adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.kelompok3.misapps.R;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public static Integer[] mThumbIds =
            {
                    //Gambar-gambar yang ada disimpan dalam array

                    R.drawable.mdb1, R.drawable.mdb2,
                    R.drawable.mdb3, R.drawable.mdb4,
                    R.drawable.mdb5, R.drawable.mdb6,
                    R.drawable.mdb7, R.drawable.mdb8,
                    R.drawable.mdb9, R.drawable.mdb18,
                    R.drawable.mdb10, R.drawable.mdb19,
                    R.drawable.mdb11, R.drawable.mdb20,
                    R.drawable.mdb12, R.drawable.mdb21,
                    R.drawable.mdb13, R.drawable.mdb22,
                    R.drawable.mdb14, R.drawable.mdb23,
                    R.drawable.mdb15, R.drawable.mdb24,
                    R.drawable.mdb16, R.drawable.mdb1,
                    R.drawable.mdb3, R.drawable.mdb4,
                    R.drawable.mdb5, R.drawable.mdb6,
                    R.drawable.mdb7, R.drawable.mdb8,
                    R.drawable.mdb9, R.drawable.mdb18,
                    R.drawable.mdb10, R.drawable.mdb19,
                    R.drawable.mdb17, R.drawable.mdb2

            };

    @Override
    public int getCount() {
        // Jumlah total gambar
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Mengambil satu gambar dari gallery
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }
}
