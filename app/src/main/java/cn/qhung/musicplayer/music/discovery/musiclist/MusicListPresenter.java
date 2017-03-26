package cn.qhung.musicplayer.music.discovery.musiclist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.xrecyclerview.WrapAdapter;
import com.youth.banner.loader.ImageLoader;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import cn.qhung.musicplayer.base.OnItemClickListener;
import cn.qhung.musicplayer.music.discovery.musiclist.musicmenu.MusicMenuActivity;
import cn.qhung.musicplayer.music.discovery.repository.MusicRepository;
import cn.qhung.musicplayer.net.entities.Banner;
import cn.qhung.musicplayer.net.entities.MusicPlayList;
import cn.qhung.musicplayer.utils.schedulers.BaseSchedulerProvider;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by qhung on 2017/3/22.
 */

public class MusicListPresenter implements MusicListContract.Presenter,
        OnItemClickListener<MusicPlayList> {
    private final MusicListContract.View mView;
    private final BaseSchedulerProvider mSchedulerProvider;
    private final CompositeSubscription mSubscriptions;
    private final MusicRepository mMusicRepository;
    private MusicListAdapter mAdapter;

    public MusicListPresenter(@NotNull MusicRepository musicRepository,
                              @NotNull MusicListContract.View view,
                              @NotNull BaseSchedulerProvider schedulerProvider) {
        this.mMusicRepository = checkNotNull(musicRepository);
        this.mView = checkNotNull(view);
        this.mSchedulerProvider = checkNotNull(schedulerProvider);

        mSubscriptions = new CompositeSubscription();
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        mSubscriptions.add(fetchBanner());
        mSubscriptions.add(fetchMusicCollection());
    }

    private Subscription fetchMusicCollection() {
        return mMusicRepository.getMusicCollection().subscribe(new Observer<List<MusicPlayList>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(List<MusicPlayList> musicPlayLists) {
                mAdapter.clear();
                mAdapter.addAll(musicPlayLists);
                mAdapter.notifyDataSetChanged();
                mView.showRotaLoading(false);
            }
        });
    }

    private Subscription fetchBanner() {
        return mMusicRepository.getBanner().subscribe(new Observer<List<Banner>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mView.setBannerVisibility(false);
            }

            @Override
            public void onNext(List<Banner> banners) {
                mView.showBanner(banners, new BannerImageLoaderAdapter());
            }
        });
    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }

    private List<MusicPlayList> mMusicPlayList = new ArrayList<>();

    @Override
    public RecyclerView.Adapter getAdapter() {
        mAdapter = new MusicListAdapter();
        mAdapter.clear();
        mAdapter.addAll(mMusicPlayList);
        return mAdapter;
    }

    @Override
    public void onClick(MusicPlayList musicPlayList, int position) {
        MusicMenuActivity.start(mView.getContext(), musicPlayList);
    }

    @Override
    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof WrapAdapter) {
            int headerCount = ((WrapAdapter) adapter).getHeadersCount();
            MusicMenuActivity.start(v.getContext(), mAdapter.getData().get(position - headerCount));
        }

    }

    private class BannerImageLoaderAdapter extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            if (path instanceof Banner) {
                cn.qhung.musicplayer.utils.image.ImageLoader.show(
                        context, ((Banner) path).getPicUrl(), imageView);
                imageView.setTag(path);
            }
        }
    }
}
