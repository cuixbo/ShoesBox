package com.xbc.douban.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import com.xbc.douban.base.BaseResponse;
import com.xbc.douban.movie.MovieService;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author xiaobocui
 * @date 2019-12-09
 */
public class RetrofitManager {
    private static final RetrofitManager INSTANCE = new RetrofitManager();

    private MovieService movieService;
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;

    public static RetrofitManager getInstance() {
        return INSTANCE;
    }

    private RetrofitManager() {
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor())
                .addInterceptor(new MyInterceptor())
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(MyConverterFactory.create(new GsonBuilder().create()))
                //.addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public MovieService getMovieService() {
        if (movieService == null) {
            movieService = retrofit.create(MovieService.class);
        }
        return movieService;
    }

    private static class MyInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String param = "";
            if (request.body() != null) {
                Buffer buffer = new Buffer();
                request.body().writeTo(buffer);
                Charset charset = Charset.forName("utf-8");
                MediaType contentType = request.body().contentType();
                if (contentType != null) {
                    charset = contentType.charset(charset);
                }
                param = buffer.readString(charset);
            }
            Log.e("request", "url=[" + request.url().toString() + "],param=[" + param + "],header=[" + request.headers().toString().trim() + "]");
            Response response = chain.proceed(request);
            String resp = response.body().string();
            response = response.newBuilder()
                    .body(ResponseBody.create(response.body().contentType(), resp))
                    .build();
            Log.e("response", ">>>url=[" + request.url().toString() + "],header=[" + request.headers().toString().trim() + "]\n>>>" + resp);
            return response;
        }
    }

    private static class MyConverterFactory extends Converter.Factory {

        public static MyConverterFactory create() {
            return create(new Gson());
        }

        public static MyConverterFactory create(@NonNull Gson gson) {
            return new MyConverterFactory(gson);
        }

        private final Gson gson;

        private MyConverterFactory(Gson gson) {
            this.gson = gson;
        }

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                                Retrofit retrofit) {
            TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
            return new GsonResponseBodyConverter<>(gson, adapter);
        }

        @Override
        public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                              Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
            TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
            return new GsonRequestBodyConverter<>(gson, adapter);
        }

        @Nullable
        @Override
        public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            return super.stringConverter(type, annotations, retrofit);
        }
    }

    private static class GsonRequestBodyConverter<T> implements Converter<T, RequestBody> {
        private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
        private static final Charset UTF_8 = Charset.forName("UTF-8");

        private final Gson gson;
        private final TypeAdapter<T> adapter;

        GsonRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
            this.gson = gson;
            this.adapter = adapter;
        }

        @Override
        public RequestBody convert(T value) throws IOException {
            Buffer buffer = new Buffer();
            Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
            JsonWriter jsonWriter = gson.newJsonWriter(writer);
            adapter.write(jsonWriter, value);
            jsonWriter.close();
            RequestBody requestBody = RequestBody.create(MEDIA_TYPE, buffer.readByteString());
            return requestBody;
        }
    }

    private static class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
        private final Gson gson;
        private final TypeAdapter<T> adapter;

        GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
            this.gson = gson;
            this.adapter = adapter;
        }

        @Override
        public T convert(ResponseBody value) throws IOException {
            String resp = value.string();
            BaseResponse respData = gson.fromJson(resp, BaseResponse.class);
            try {
                if (respData.code != 0) {
                    throw new ServerException(respData.code);
                } else {
                    return adapter.fromJson(resp);
                }
            } finally {
                value.close();
            }
        }
    }
}





