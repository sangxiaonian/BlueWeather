package sang.com.commonlibrary.http;


import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description：
 *
 * @Author：桑小年
 * @Data：2017/1/17 16:35
 */
public class HttpClient {


    private static HttpClient client;
    private static Retrofit retrofit;


    public static HttpClient getInstance() {
        if (client == null) {
            synchronized (HttpClient.class) {
                if (client == null) {
                    client = new HttpClient();
                }
            }
        }
        return client;
    }

    private HttpClient() {
        getClient();
    }

    public static <T> T creatService(Class<T> c) {
        return retrofit.create(c);
    }


    private static void getClient() {
        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(getLogInterceptor());
            retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(AppConfigs.base_url)
                    .build();
        }
    }


    private static HttpLoggingInterceptor getLogInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }


}