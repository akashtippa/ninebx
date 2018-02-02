package com.ninebx.ui.base.network;

import android.app.Application;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ninebx.NineBxApplication;
import com.ninebx.utility.Constants;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Alok on 02/02/18.
 */

public class NetModule {

    private NineBxApplication application;

    private Cache cache;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;


    // Constructor needs one parameter to instantiate.
    public NetModule(NineBxApplication application) {
        this.application = application;
    }

    private Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    private OkHttpClient provideOkHttpClient(Cache cache) {
        // create an instance of OkLogInterceptor using a builder()
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(getInterceptor());
        client.cache(cache);
        return client.build();
    }

    private Interceptor getInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                if (application.isNetworkAvailable()) {
                    int maxAge = 60; // read from cache for 1 minute
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();
                } else {
                    int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .header("Content-Type", "application/json")
                            .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJyb2xlIjoid2ViX2Fub24ifQ.ql1_BQCwwekGnqEkbngRlGvHGHBMiXmkFnxCBAsXKdQ")
                            .build();
                }
            }
        };
    }

    private Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        String BASE_URL = Constants.INSTANCE.getSERVER_API_ADDRESS();
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build();
    }

    private Cache getCache() {
        if( cache == null ) {
            cache = provideOkHttpCache(application);
        }
        return cache;
    }

    private OkHttpClient getOkHttpClient() {
        if( okHttpClient == null ) {
            okHttpClient = provideOkHttpClient(getCache());
        }
        return okHttpClient;
    }

    private Retrofit getRetrofit() {
        if( retrofit == null ) {
            retrofit = provideRetrofit(getOkHttpClient());
        }
        return retrofit;
    }

    private Gson getGson() {
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    private final GetUsersAPI getUsersAPI = getRetrofit().create( GetUsersAPI.class );

    public GetUsersAPI getGetUsersAPI() {
        return getUsersAPI;
    }

    public interface GetUsersAPI {

        @POST("users")
        Observable<Response> getUser( @Body HashMap<String, Object> paramsMap );

    }

}
