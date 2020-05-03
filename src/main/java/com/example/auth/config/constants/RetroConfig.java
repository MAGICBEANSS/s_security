package com.example.auth.config.constants;

import com.example.auth.service.ExternalService;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
public class RetroConfig {

  @Value("${external.base-url:https://reqres.in/}")
  private String baseUrl;

  private static final int MAX_IDLE_CONNECTIONS = 50;
  private static final int KEEP_ALIVE_DURATION = 50;
  private static final int MAX_REQUESTS = 500;
  private static final int MAX_REQUESTS_PER_HOST = 50;

  public OkHttpClient getOkHttpClient() {
    ConnectionPool connectionPool = new ConnectionPool(MAX_IDLE_CONNECTIONS, KEEP_ALIVE_DURATION, TimeUnit.MINUTES);
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    Dispatcher dispatcher = new Dispatcher();
    dispatcher.setMaxRequests(MAX_REQUESTS);
    dispatcher.setMaxRequestsPerHost(MAX_REQUESTS_PER_HOST);
    return new OkHttpClient.Builder().connectTimeout(2, TimeUnit.MINUTES).readTimeout(2, TimeUnit.MINUTES)
        .writeTimeout(2, TimeUnit.MINUTES).connectionPool(connectionPool).dispatcher(dispatcher)
        .addInterceptor(interceptor).build();
  }

  @Bean
  public ExternalService getExternalService() {
    return new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(JacksonConverterFactory.create())
        .client(getOkHttpClient()).build().create(ExternalService.class);
  }
}
