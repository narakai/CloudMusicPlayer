package cn.qhung.musicplayer.music.discovery;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import cn.qhung.musicplayer.music.MainPageAdapter;
import cn.qhung.musicplayer.music.discovery.musiclist.MusicListFragment;
import cn.qhung.musicplayer.music.discovery.rank.MusicRankFragment;
import cn.qhung.musicplayer.music.discovery.repository.MusicRepository;
import cn.qhung.musicplayer.net.entities.Banner;
import cn.qhung.musicplayer.utils.schedulers.BaseSchedulerProvider;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by qhung on 2017/3/20.
 */

public class MusicDiscoveryPresenter implements MusicDiscoveryContract.Presenter {

    @NonNull
    private final MusicRepository mMusicRepostory;

    @NonNull
    private final MusicDiscoveryContract.View mMusicDiscoveryView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    @NonNull
    private final CompositeSubscription mSubscriptions;

    private ArrayList<String> mFragmentTitleList = new ArrayList<>(2);
    private ArrayList<Fragment> mChildrenFragments = new ArrayList<>(2);

    public MusicDiscoveryPresenter(@NotNull MusicRepository musicRepository,
                                   @NotNull MusicDiscoveryContract.View musicDiscoveryFragment,
                                   @NotNull BaseSchedulerProvider baseSchedulerProvider) {
        this.mMusicRepostory = checkNotNull(musicRepository);
        this.mMusicDiscoveryView = checkNotNull(musicDiscoveryFragment);
        this.mSchedulerProvider = checkNotNull(baseSchedulerProvider);

        mSubscriptions = new CompositeSubscription();
        mMusicDiscoveryView.setPresenter(this);
    }


    @Override
    public void subscribe() {

    }



    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void addChildrenFragment(FragmentManager childFragmentManager) {
        checkNotNull(childFragmentManager);
        mFragmentTitleList.clear();
        mChildrenFragments.clear();
        mFragmentTitleList.add("歌单");
        mFragmentTitleList.add("排行榜");
        mChildrenFragments.add(MusicListFragment.newInstance());
        mChildrenFragments.add(MusicRankFragment.newInstance());
        FragmentPagerAdapter adapter = new MainPageAdapter(childFragmentManager, mChildrenFragments, mFragmentTitleList);
        mMusicDiscoveryView.initViewPager(adapter);
    }
}
