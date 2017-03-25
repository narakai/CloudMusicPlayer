package cn.qhung.musicplayer.music.discovery;

import android.content.Context;
import android.support.annotation.NonNull;

import cn.qhung.musicplayer.music.discovery.repository.local.LocalMusicDateSource;
import cn.qhung.musicplayer.music.discovery.repository.MusicRepository;
import cn.qhung.musicplayer.music.discovery.repository.remote.RemoteMusicDateSource;
import cn.qhung.musicplayer.utils.schedulers.BaseSchedulerProvider;
import cn.qhung.musicplayer.utils.schedulers.SchedulerProvider;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by qhung on 2017/3/20.
 */

public class Injection {
    public static MusicRepository provideMusicSourceRepository(@NonNull Context context) {
        checkNotNull(context);
        return MusicRepository.getInstance(RemoteMusicDateSource.getInstance(provideSchedulerProvider()),
                LocalMusicDateSource.getInstance(context, provideSchedulerProvider()));
    }

    public static BaseSchedulerProvider provideSchedulerProvider() {
        return SchedulerProvider.getInstance();
    }
}
