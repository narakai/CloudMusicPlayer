package cn.qhung.musicplayer.music.discovery.musiclist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.youth.banner.loader.ImageLoader;

import java.util.List;

import cn.qhung.musicplayer.R;
import cn.qhung.musicplayer.base.BaseFragment;
import cn.qhung.musicplayer.databinding.FragmentMusicListBinding;
import cn.qhung.musicplayer.databinding.MusicListFooterBinding;
import cn.qhung.musicplayer.databinding.MusicListHeaderBinding;
import cn.qhung.musicplayer.music.discovery.Injection;
import cn.qhung.musicplayer.net.entities.Banner;
import cn.qhung.musicplayer.utils.Log;
import cn.qhung.musicplayer.utils.schedulers.SchedulerProvider;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by qhung on 2017/3/20.
 */

public class MusicListFragment extends BaseFragment<FragmentMusicListBinding>
        implements MusicListContract.View {
    private RotateAnimation mLoadingAnimation;
    private MusicListHeaderBinding mHeaderBinding;
    private View mHeaderView;
    private MusicListFooterBinding mFooterBinding;
    private View mFooterView;
    private MusicListContract.Presenter mPresenter;

    public static Fragment newInstance() {
        return new MusicListFragment();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_music_list;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHeaderBinding = DataBindingUtil.inflate(
                LayoutInflater.from(getContext()), R.layout.music_list_header, null, false);
        new MusicListPresenter(Injection.provideMusicSourceRepository(
                getContext().getApplicationContext()), this, SchedulerProvider.getInstance());
        showContentView();
        enableLoading();
        initRecyclerView();
        mPresenter.subscribe();
    }

    private void enableLoading() {
        bindingView.llLoading.setVisibility(View.VISIBLE);
        mLoadingAnimation = new RotateAnimation(0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mLoadingAnimation.setDuration(3000);//设置动画持续时间
        mLoadingAnimation.setInterpolator(new LinearInterpolator());//不停顿
        mLoadingAnimation.setRepeatCount(-1);
        bindingView.ivLoading.setAnimation(mLoadingAnimation);
        mLoadingAnimation.startNow();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        showRotaLoading(false);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mHeaderBinding != null && mHeaderBinding.banner != null) {
            mHeaderBinding.banner.stopAutoPlay();
        }
    }

    @Override
    public void showRotaLoading(boolean isLoading) {
        if (isLoading) {
            bindingView.llLoading.setVisibility(View.VISIBLE);
            bindingView.xrvEveryday.setVisibility(View.GONE);
            mLoadingAnimation.startNow();
        } else {
            bindingView.llLoading.setVisibility(View.GONE);
            bindingView.xrvEveryday.setVisibility(View.VISIBLE);
            mLoadingAnimation.cancel();
        }
    }

    private void initRecyclerView() {
        bindingView.xrvEveryday.setPullRefreshEnabled(false);
        bindingView.xrvEveryday.setLoadingMoreEnabled(false);
        if (mHeaderView == null) {
            mHeaderView = mHeaderBinding.getRoot();
            bindingView.xrvEveryday.addHeaderView(mHeaderView);
        }
        if (mFooterView == null) {
            mFooterBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.music_list_footer, null, false);
            mFooterView = mFooterBinding.getRoot();
            bindingView.xrvEveryday.addFootView(mFooterView, true);
            bindingView.xrvEveryday.noMoreLoading();
        }
        bindingView.xrvEveryday.setLayoutManager(new LinearLayoutManager(getContext()));
        // 需加，不然滑动不流畅
        bindingView.xrvEveryday.setNestedScrollingEnabled(false);
        bindingView.xrvEveryday.setHasFixedSize(false);
        bindingView.xrvEveryday.setItemAnimator(new DefaultItemAnimator());
        bindingView.xrvEveryday.setAdapter(mPresenter.getAdapter());
    }

    @Override
    public void setPresenter(MusicListContract.Presenter presenter) {
        this.mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showBanner(List<Banner> banners, ImageLoader loader) {
        bindingView.xrvEveryday.setVisibility(View.VISIBLE);
        mHeaderBinding.banner.setImages(banners).setImageLoader(loader).start();
        if (mHeaderBinding != null && mHeaderBinding.banner != null) {
            mHeaderBinding.banner.startAutoPlay();
            mHeaderBinding.banner.setDelayTime(4000);
        }
    }

    @Override
    public void setBannerVisibility(boolean b) {
        Log.QLog().d("set banner visibility : " + b);
        mHeaderBinding.banner.setVisibility(b ? View.VISIBLE : View.GONE);
    }
}
