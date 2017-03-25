package cn.qhung.musicplayer.music.discovery.repository;

import android.support.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import cn.qhung.musicplayer.music.discovery.repository.local.LocalMusicDateSource;
import cn.qhung.musicplayer.music.discovery.repository.remote.RemoteMusicDateSource;
import cn.qhung.musicplayer.net.entities.Banner;
import cn.qhung.musicplayer.net.entities.MusicPlayList;
import cn.qhung.musicplayer.utils.Log;
import rx.Observable;
import rx.functions.Func1;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by qhung on 2017/3/20.
 */

public class MusicRepository {
    private static MusicRepository INSTANCE;
    private RemoteMusicDateSource mMusicRemoteDataSource;
    private LocalMusicDateSource mMusicLocalDataSource;

    public MusicRepository(
            @NotNull RemoteMusicDateSource remoteDateSource,
            @NotNull LocalMusicDateSource localDateSource) {
        mMusicRemoteDataSource = checkNotNull(remoteDateSource);
        mMusicLocalDataSource = checkNotNull(localDateSource);
    }

    public static MusicRepository getInstance(
            @NotNull RemoteMusicDateSource remoteDateSource,
            @NotNull LocalMusicDateSource localDateSource) {
        if (INSTANCE == null) {
            INSTANCE = new MusicRepository(remoteDateSource, localDateSource);
        }
        return INSTANCE;
    }

    public Observable<List<Banner>> getBanner() {
        return mMusicLocalDataSource.getBanner()
                .flatMap(new Func1<List<Banner>, Observable<List<Banner>>>() {
                    @Override
                    public Observable<List<Banner>> call(List<Banner> banners) {
                        if (checkListIsEmpty(banners)) {
                            Observable<List<Banner>> observable = mMusicRemoteDataSource.getBanner();
                            return saveBanner(observable);
                        }
                        return Observable.just(banners);
                    }
                });
    }

    @NonNull
    private Observable<List<Banner>> saveBanner(Observable<List<Banner>> observable) {
        return observable.map(new Func1<List<Banner>, List<Banner>>() {
            @Override
            public List<Banner> call(List<Banner> banners) {
                mMusicLocalDataSource.saveBanner(banners);
                return banners;
            }
        });
    }

    private boolean checkListIsEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public Observable<List<MusicPlayList>> getMusicCollection() {
        return mMusicRemoteDataSource.getMusicCollection();
    }
}
