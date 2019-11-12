package com.kelompok3.misapps.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.view.View.OnClickListener;
import com.kelompok3.misapps.Activity.singleimage;
import com.kelompok3.misapps.Adapter.ImageAdapter;
import com.kelompok3.misapps.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Galeri_Foto extends Fragment implements AdapterView.OnItemClickListener, OnClickListener{

    public Galeri_Foto() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_galeri__foto, container, false);
        GridView gridView = (GridView) rootView.findViewById(R.id.gridview_followed);
        gridView.setAdapter(new ImageAdapter(getActivity()));
        gridView.setOnItemClickListener(this);

        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        // TODO Auto-generated method stub
        Toast.makeText(getActivity(), "Detail Foto "+position, Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getActivity(), singleimage.class);
        Bundle b = new Bundle();
        b.putInt("posisi", position);
        i.putExtras(b);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }
}
