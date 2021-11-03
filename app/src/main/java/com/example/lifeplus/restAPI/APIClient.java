package com.example.lifeplus.restAPI;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static final String TAG = "ApiClient:";
    private static final String BASE_URL = "https://api.tvmaze.com/";
    private static Retrofit retrofit = null;
    private static Retrofit rxRetrofit = null;
    private static ApiInterface apiInterface = null;
    static {
        // System.loadLibrary("native-lib");
       // System.loadLibrary("native-lib");
    }
    //public static native String baseURL();

    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss").setLenient()
            .create();

    public static Retrofit getRxClient() {
        if (rxRetrofit == null) {
            rxRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return rxRetrofit;
    }

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static ApiInterface getAPIInterface() {
        if (apiInterface == null) {
            apiInterface = getClient().create(ApiInterface.class);
        }
        return apiInterface;
    }

    private static OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor())
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .build();
    }

    private static HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Log.d(TAG, "log: http log: " + message);
                    }
                });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    public static MultipartBody.Part getMultipart(String filePath, String fieldName) {
        if (TextUtils.isEmpty(filePath))
            return MultipartBody.Part.createFormData(fieldName, "");
        String fileType = getMimeType(filePath);
        File file = new File(filePath);
        MediaType mediaType = MediaType.parse(fileType);
        RequestBody requestProfile = RequestBody.create(mediaType, file);
        return MultipartBody.Part.createFormData(fieldName, file.getName(), requestProfile);
    }

    private static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }

}
