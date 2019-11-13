package com.kelompok3.misapps.API;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Service {

    @POST("auth")
    @FormUrlEncoded
    Call<JsonObject> getAuth(
            @Field("var_cell_phone") String cell_phone,
            @Field("var_password") String password
    );

    @POST("employee")
    @FormUrlEncoded
    Call<JsonObject> getEmployee();

    @POST("office")
    Call<JsonObject> getOffice();
}
