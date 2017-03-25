package cn.qhung.musicplayer.music.discovery.repository.remote;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import cn.qhung.musicplayer.music.discovery.repository.MusicDataSource;
import cn.qhung.musicplayer.net.NetworkManager;
import cn.qhung.musicplayer.net.entities.Banner;
import cn.qhung.musicplayer.net.entities.MusicPlayList;
import cn.qhung.musicplayer.net.entities.Response;
import cn.qhung.musicplayer.utils.schedulers.BaseSchedulerProvider;
import rx.Observable;
import rx.functions.Func1;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by qhung on 2017/3/20.
 */

public class RemoteMusicDateSource implements MusicDataSource {
    private static RemoteMusicDateSource INSTANCE;
    private final BaseSchedulerProvider mProvider;

    public static RemoteMusicDateSource getInstance(@NotNull BaseSchedulerProvider provider) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteMusicDateSource(provider);
        }
        return INSTANCE;
    }

    private RemoteMusicDateSource(@NotNull BaseSchedulerProvider provider) {
        this.mProvider = checkNotNull(provider);
    }

    @Override
    public Observable<List<Banner>> getBanner() {
        return NetworkManager.getInstance().getBanner(mProvider)
                .filter(new Func1<Response<List<Banner>>, Boolean>() {
                    @Override
                    public Boolean call(Response<List<Banner>> response) {
                        return response != null && response.getCode() == Response.OK;
                    }
                })
                .flatMap(new Func1<Response<List<Banner>>, Observable<List<Banner>>>() {
                    @Override
                    public Observable<List<Banner>> call(Response<List<Banner>> response) {
                        return Observable.just(response.getData());
                    }
                });
    }

    public Observable<List<MusicPlayList>> getMusicCollection() {
        return NetworkManager.getInstance().getMusicCollection(mProvider)
                .filter(new Func1<Response<List<MusicPlayList>>, Boolean>() {
                    @Override
                    public Boolean call(Response<List<MusicPlayList>> response) {
                        return response != null && response.getCode() == Response.OK;
                    }
                })
                .flatMap(new Func1<Response<List<MusicPlayList>>,
                        Observable<List<MusicPlayList>>>() {
                    @Override
                    public Observable<List<MusicPlayList>> call(
                            Response<List<MusicPlayList>> response) {
                        return Observable.just(response.getData());
                    }
                });
    }
}
