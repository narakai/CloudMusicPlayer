package cn.qhung.musicplayer.music.discovery.musiclist;

import android.support.v7.widget.RecyclerView;

import com.youth.banner.loader.ImageLoader;

import java.util.List;

import cn.qhung.musicplayer.base.BaseContract;
import cn.qhung.musicplayer.net.entities.Banner;
import cn.qhung.musicplayer.utils.ItemClickSupport;

/**
 * Created by qhung on 2017/3/22.
 */

public interface MusicListContract {
    interface Presenter extends BaseContract.BasePresenter, ItemClickSupport.OnItemClickListener {

        RecyclerView.Adapter getAdapter();
    }

    interface View extends BaseContract.BaseView<Presenter> {

        void showBanner(List<Banner> banners, ImageLoader imageLoader);

        void setBannerVisibility(boolean b);

        void showRotaLoading(boolean b);
    }
}
