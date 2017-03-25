package cn.qhung.musicplayer.net;


import java.util.List;

import cn.qhung.musicplayer.net.entities.Banner;
import cn.qhung.musicplayer.net.entities.MusicPlayList;
import cn.qhung.musicplayer.net.entities.Response;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by qhung on 2017/3/25.
 */

public interface MusicService {

    @GET("/banner")
    Observable<Response<List<Banner>>> getBanner();

    @GET("/playlists")
    Observable<Response<List<MusicPlayList>>> getMusicCollection();
}
