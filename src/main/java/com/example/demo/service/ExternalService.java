package com.example.demo.service;

import com.example.demo.dto.SampleRequestDTO;
import com.example.demo.dto.SampleResponseDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ExternalService {

    @GET("api/users")
    Call<SampleResponseDTO> getUser(@Query("page") Integer page
                                                 );

}
