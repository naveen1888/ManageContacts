package com.prowareness.contact.helper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Naveen Rawat on 10-03-2017.
 */

class HttpInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .header("token", "c149c4fac72d3a3678eefab5b0d3a85a")
                .header("Accept", "application/json")
                .header("Content-Type", "application/x-www- form-urlencoded")
                .method(original.method(), original.body())
                .build();

        return chain.proceed(request);
    }

}
