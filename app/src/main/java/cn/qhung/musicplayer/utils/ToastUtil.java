package cn.qhung.musicplayer.utils;

import android.widget.Toast;

import cn.qhung.musicplayer.MusicPlayerApplication;


/**
 * Created by jingbin on 2016/12/14.
 * 单例Toast
 */

public class ToastUtil {

    private static Toast mToast;

    public static void showToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(MusicPlayerApplication.get(), text, Toast.LENGTH_SHORT);
        }
        mToast.setText(text);
        mToast.show();
    }
}
