package cn.qhung.musicplayer.music;

import cn.qhung.musicplayer.base.BaseContract;

/**
 * Created by qhung on 2017/3/19.
 */

public interface MainContract {
    public interface Presenter extends BaseContract.BasePresenter {

    }

    public interface View extends BaseContract.BaseView<Presenter> {

    }
}
