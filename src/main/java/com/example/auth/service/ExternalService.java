package com.example.auth.service;

import com.example.auth.dto.SampleResponseDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ExternalService {

    @GET("api/users")
    Call<SampleResponseDTO> getUser(@Query("page") Integer page
                                                 );

}
