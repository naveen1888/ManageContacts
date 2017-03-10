package com.prowareness.contact.interfaces;

import com.prowareness.contact.bean.ContactRes;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by Naveen Rawat on 10-03-2017.
 */

public interface ApiRequest {

    @POST("contactlist.php")
    Call<ContactRes> getContacts();

}
