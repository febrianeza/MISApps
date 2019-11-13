package com.kelompok3.misapps.Fragments;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kelompok3.misapps.API.API;
import com.kelompok3.misapps.API.Service;
import com.kelompok3.misapps.Activity.DetailOfficeActivity;
import com.kelompok3.misapps.Activity.LoginActivity;
import com.kelompok3.misapps.Activity.MainActivity;
import com.kelompok3.misapps.Adapter.AdapterOffice;
import com.kelompok3.misapps.R;
import com.kelompok3.misapps.SharedPreferences.TinyDB;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OfficeFragment extends Fragment {
    @BindView(R.id.office)
    GridView grid;
    AdapterOffice adapter;
    Service service;
    ArrayList<HashMap<String,String>> dataOffice = new ArrayList<HashMap<String, String>>();

    public OfficeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        service = API.getClient().create(Service.class);
        Call<JsonObject> getdataOffice = service.getOffice();

        getdataOffice.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject object = response.body();
                    JsonArray array = object.get("result").getAsJsonArray();

                    String var_result = object.get("var_result").getAsString();
                    if (var_result.equals("1")) {

                        for (int i = 0; i<array.size();i++){
                            JsonObject objectUser = array.get(i).getAsJsonObject();
                            String office_name = objectUser.get("office_name").getAsString();
                            String office_address = objectUser.get("office_address").getAsString();
                            String office_description = objectUser.get("office_description").getAsString();
                            String cell_phone = objectUser.get("cell_phone").getAsString();
                            String email = objectUser.get("email").getAsString();
                            String location_gps = objectUser.get("location_gps").getAsString();
                            String base_url = objectUser.get("base_url").getAsString();

                            //Memasukan data kedalam Hashmap
                            HashMap<String,String> map_ne = new HashMap<>();
                            map_ne.put("office_name", office_name);
                            map_ne.put("office_address", office_address);
                            map_ne.put("office_description", office_description);
                            map_ne.put("cell_phone", cell_phone);
                            map_ne.put("email", email);
                            map_ne.put("location_gps", location_gps);
                            map_ne.put("base_url", base_url);

                            //Memasukan Hashmap kedalam ArrayList
                            dataOffice.add(map_ne);
                        }
                        adapter = new AdapterOffice(getActivity(), dataOffice, 1);
                        grid.setAdapter(adapter);
                        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parents, View view, int position, long id) {
                                //Melempar Hashmap sesuai dengan posisi yang ditunjuk
                                HashMap<String,String> data = (HashMap<String,String>) grid.getItemAtPosition(position);
                                Intent intent = new Intent(getActivity(), DetailOfficeActivity.class);
                                intent.putExtra("data", data);
                                startActivity(intent);
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return inflater.inflate(R.layout.fragment_office, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }
}
