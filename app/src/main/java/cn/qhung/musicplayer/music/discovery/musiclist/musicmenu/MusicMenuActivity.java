package cn.qhung.musicplayer.music.discovery.musiclist.musicmenu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import cn.qhung.musicplayer.R;
import cn.qhung.musicplayer.base.BaseActivity;
import cn.qhung.musicplayer.net.entities.MusicPlayList;

/**
 * Created by qhung on 2017/3/26.
 */

public class MusicMenuActivity extends BaseActivity {
    private static final String KEY_MUSIC_MENU = "key_music_menu";

    public static void start(@NotNull Context context, @NotNull MusicPlayList musicPlayList) {
        Intent intent = new Intent(context, MusicMenuActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_MUSIC_MENU, musicPlayList);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_menu);
        showContentView();
        setTitle("关于云阅");
    }
}
