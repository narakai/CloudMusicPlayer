package cn.qhung.musicplayer.music.discovery;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import cn.qhung.musicplayer.base.BaseContract;
import cn.qhung.musicplayer.net.entities.Banner;

/**
 * Created by qhung on 2017/3/20.
 */

interface MusicDiscoveryContract {
    interface Presenter extends BaseContract.BasePresenter {

        void addChildrenFragment(FragmentManager childFragmentManager);
    }

    interface View extends BaseContract.BaseView<Presenter> {

        void initViewPager(FragmentPagerAdapter adapter);

    }
}
