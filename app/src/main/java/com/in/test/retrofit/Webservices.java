package com.in.test.retrofit;


import com.in.test.entities.Test;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface Webservices {
    @GET("dummy/")
    Call<Test> getData();


}

