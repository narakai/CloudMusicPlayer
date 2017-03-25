package cn.qhung.musicplayer.base;

/**
 * Created by qhung on 2017/3/19.
 */

public interface BaseContract {
    public interface BaseView<T> {

        void setPresenter(T presenter);

    }

    public interface BasePresenter {

        void subscribe();

        void unsubscribe();

    }
}
