package cn.qhung.musicplayer.music.discovery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import org.jetbrains.annotations.NotNull;

import cn.qhung.musicplayer.R;
import cn.qhung.musicplayer.base.BaseFragment;
import cn.qhung.musicplayer.databinding.FragmentMusicDiscoveryBinding;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by qhung on 2017/3/20.
 */

public class MusicDiscoveryFragment extends BaseFragment<FragmentMusicDiscoveryBinding>
        implements MusicDiscoveryContract.View {

    private MusicDiscoveryContract.Presenter mPresenter;

    @Override
    public int setContent() {
        return R.layout.fragment_music_discovery;
    }

    public static Fragment newInstance() {
        return new MusicDiscoveryFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new MusicDiscoveryPresenter(
                Injection.provideMusicSourceRepository(getContext().getApplicationContext()), this,
                Injection.provideSchedulerProvider());
        showLoading();
        mPresenter.addChildrenFragment(getChildFragmentManager());
    }


    @Override
    public void setPresenter(@NonNull MusicDiscoveryContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void initViewPager(@NotNull FragmentPagerAdapter adapter) {
        PagerAdapter pagerAdapter = checkNotNull(adapter);
        bindingView.vpGank.setAdapter(pagerAdapter);
        bindingView.vpGank.setOffscreenPageLimit(2);
        bindingView.tabGank.setTabMode(TabLayout.MODE_FIXED);
        bindingView.tabGank.setupWithViewPager(bindingView.vpGank);
        showContentView();
        pagerAdapter.notifyDataSetChanged();
    }

}

