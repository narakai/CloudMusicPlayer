package cn.qhung.musicplayer.music.discovery.repository;

import java.util.List;

import cn.qhung.musicplayer.net.entities.Banner;
import cn.qhung.musicplayer.net.entities.MusicPlayList;
import cn.qhung.musicplayer.net.entities.Response;
import rx.Observable;

/**
 * Created by qhung on 2017/3/20.
 */

public interface MusicDataSource {

    Observable<List<Banner>> getBanner();

    Observable<List<MusicPlayList>> getMusicCollection();
}
