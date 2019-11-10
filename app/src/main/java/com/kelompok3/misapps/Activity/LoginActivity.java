package com.kelompok3.misapps.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kelompok3.misapps.API.API;
import com.kelompok3.misapps.API.Service;
import com.kelompok3.misapps.R;
import com.kelompok3.misapps.SharedPreferences.TinyDB;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.etPhoneNumber)
    EditText etPhoneNumber;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    Service service;

    TinyDB tinyDB = null;
    private CheckBox ShowPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        service = API.getClient().create(Service.class);
        final EditText et_password = findViewById(R.id.etPassword);

        ShowPass = findViewById(R.id.showpass);

        //Set onClickListener, untuk menangani kejadian saat Checkbox diklik
        ShowPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ShowPass.isChecked()){
                    //Saat Checkbox dalam keadaan Checked, maka password akan di tampilkan
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    //Jika tidak, maka password akan di sembuyikan
                   et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @OnClick(R.id.btnLogin)
    public void doLogin(View view) {
        String cell_phone = etPhoneNumber.getText().toString();
        String password = etPassword.getText().toString();


        Call<JsonObject> authRequest = service.getAuth(
                cell_phone,
                password
        );

        tinyDB = new TinyDB(getBaseContext());

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Login...");
        progressDialog.show();

        authRequest.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject object = response.body();
                    JsonArray array = object.get("result").getAsJsonArray();

                    progressDialog.dismiss();

                    JsonObject objectUser = array.get(0).getAsJsonObject();
                    String full_name = objectUser.get("full_name").getAsString();
                    String email = objectUser.get("email").getAsString();
                    String cell_phone = objectUser.get("cell_phone").getAsString();

                    String var_result = object.get("var_result").getAsString();

                    if (var_result.equals("1")) {
                        tinyDB.putString("sp_full_name", full_name);

                        startActivity(new Intent(LoginActivity.this, MainActivity.class)
                                .putExtra("full_name", full_name)
                                .putExtra("email", email)
                                .putExtra("cell_phone", cell_phone)
                        );
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                authRequest.cancel();
            }
        });
    }
}
