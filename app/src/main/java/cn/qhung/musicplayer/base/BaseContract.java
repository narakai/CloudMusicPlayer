package cn.qhung.musicplayer.base;

import android.content.Context;

/**
 * Created by qhung on 2017/3/19.
 */

public interface BaseContract {
    public interface BaseView<T> {

        void setPresenter(T presenter);

        Context getContext();
    }

    public interface BasePresenter {

        void subscribe();

        void unsubscribe();

    }
}
