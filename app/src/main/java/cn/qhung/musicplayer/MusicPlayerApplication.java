package cn.qhung.musicplayer;

import android.app.Application;

/**
 * Created by qhung on 2017/3/19.
 */

public class MusicPlayerApplication extends Application {
    private static MusicPlayerApplication mInstance;

    public static MusicPlayerApplication get() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
