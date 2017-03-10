package com.prowareness.contact.helper;

import com.prowareness.contact.interfaces.ApiRequest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Naveen Rawat on 10-03-2017.
 */

public class ApiService {


    private static ApiService serviceInstance;
    private static ApiRequest requestInstance;

    private ApiService() {

    }

    public static ApiService getInstance() {
        if (serviceInstance == null)
            serviceInstance = new ApiService();
        return serviceInstance;
    }

    public ApiRequest serviceRequest() {

        if (requestInstance == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            HttpInterceptor httpInterceptor = new HttpInterceptor();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://139.162.152.157/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient.Builder()
                            .addInterceptor(httpInterceptor)
                            .addInterceptor(logging)
                            .retryOnConnectionFailure(false)
                            .build())
                    .build();
            requestInstance = retrofit.create(ApiRequest.class);
        }
        return requestInstance;
    }
}
