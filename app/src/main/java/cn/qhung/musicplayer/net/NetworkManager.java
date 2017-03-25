package cn.qhung.musicplayer.net;

import android.support.annotation.NonNull;

import java.io.File;
import java.util.List;

import cn.qhung.musicplayer.Constants;
import cn.qhung.musicplayer.MusicPlayerApplication;
import cn.qhung.musicplayer.net.entities.Banner;
import cn.qhung.musicplayer.net.entities.MusicPlayList;
import cn.qhung.musicplayer.net.entities.Response;
import cn.qhung.musicplayer.utils.Log;
import cn.qhung.musicplayer.utils.schedulers.BaseSchedulerProvider;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by qhung on 2017/3/25.
 */

public class NetworkManager {
    private static volatile NetworkManager INSTANCE;
    private final Retrofit mHttpClient;

    public static NetworkManager getInstance() {
        if (INSTANCE == null) {
            synchronized (NetworkManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NetworkManager();
                }
            }
        }
        return INSTANCE;
    }

    private Interceptor getLogInterceptor() {

        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.HttpLog().d(message);

            }
        });
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logInterceptor;
    }

    private NetworkManager() {
        Cache cache;
        OkHttpClient okHttpClient;
        try {
            File cacheDir =
                    new File(MusicPlayerApplication.get().getCacheDir().getPath(), "cache.json");
            cache = new Cache(cacheDir, 10 * 1024 * 1024);
            okHttpClient = new OkHttpClient.Builder()
                    .cache(cache)
                    .addInterceptor(getLogInterceptor())
                    .build();
        } catch (Exception e) {
            okHttpClient = new OkHttpClient();
        }
        mHttpClient = new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private MusicService create() {
        return mHttpClient.create(MusicService.class);
    }

    public Observable<Response<List<Banner>>> getBanner(@NonNull BaseSchedulerProvider provider) {
        BaseSchedulerProvider netProvider = checkNotNull(provider);
        return create().getBanner()
                .subscribeOn(netProvider.io())
                .unsubscribeOn(netProvider.io())
                .observeOn(netProvider.ui());
    }

    public Observable<Response<List<MusicPlayList>>> getMusicCollection(
            @NonNull BaseSchedulerProvider provider) {
        BaseSchedulerProvider netProvider = checkNotNull(provider);
        return create().getMusicCollection()
                .subscribeOn(netProvider.io())
                .unsubscribeOn(netProvider.io())
                .observeOn(netProvider.ui());
    }
}
