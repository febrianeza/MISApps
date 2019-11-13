package com.kelompok3.misapps.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kelompok3.misapps.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterOffice extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private int list_position = 0;
    private static LayoutInflater inflater = null;
    private String PACKAGE_NAME;

    public AdapterOffice(Activity a, ArrayList<HashMap<String, String>> d, int list_pos) {
        data = d;
        activity = a;
        list_position = list_pos;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        PACKAGE_NAME = activity.getPackageName();
    }

    @Override
    public int getCount(){
        return data.size();
    }
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
    @Override
    public  long getItemId(int position){
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View vi =convertView;

        switch (list_position){
            case 1:
                if (convertView == null)
                    vi = inflater.inflate(R.layout.gv_office, null);
                ImageView img_kantor = (ImageView)vi.findViewById(R.id.img_kantor);
                TextView nama_kantor = (TextView) vi.findViewById(R.id.nama_kantor);

                HashMap<String,String> empList = new HashMap<String, String>();

                empList = data.get(position);

                Picasso.get().load(empList.get("base_url")).resize(210,210).into(img_kantor);
                nama_kantor.setText(empList.get("office_name"));

                break;

            default:
                break;
        }
        return vi;
    }
}
