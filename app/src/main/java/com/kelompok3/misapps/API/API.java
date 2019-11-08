package com.kelompok3.misapps.API;

import com.kelompok3.misapps.Util.Consts;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
    private static Retrofit retrofit = null;

    private static OkHttpClient client() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor(Consts.AUTH_USERNAME, Consts.AUTH_PASSWORD))
                .build();

        return client;
    }

    public static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(client())
                    .baseUrl(Consts.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
