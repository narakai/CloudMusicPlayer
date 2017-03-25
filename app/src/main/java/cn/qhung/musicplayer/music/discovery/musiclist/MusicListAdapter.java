package cn.qhung.musicplayer.music.discovery.musiclist;

import android.view.ViewGroup;

import java.util.List;

import cn.qhung.musicplayer.R;
import cn.qhung.musicplayer.base.BaseRecyclerViewAdapter;
import cn.qhung.musicplayer.base.BaseRecyclerViewHolder;
import cn.qhung.musicplayer.databinding.ItemMusicListBinding;
import cn.qhung.musicplayer.net.entities.MusicPlayList;
import cn.qhung.musicplayer.utils.image.ImageLoader;

/**
 * Created by qhung on 2017/3/25.
 */

public class MusicListAdapter extends BaseRecyclerViewAdapter<MusicPlayList> {
    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, R.layout.item_music_list);
    }

    public static class ViewHolder extends BaseRecyclerViewHolder<MusicPlayList, ItemMusicListBinding> {

        public ViewHolder(ViewGroup parent, int layout) {
            super(parent, layout);
        }

        @Override
        public void onBindViewHolder(MusicPlayList object, int position) {
            binding.tvTitleType.setText(object.getName());
            List<MusicPlayList.MusicCollection> collection = object.getMusic();
            if (collection.size() > 1) {
                ImageLoader.show(binding.ivThreeOneOne.getContext(),
                        collection.get(0).getDisplay_img(), binding.ivThreeOneOne);
            }
            if (collection.size() > 2) {
                ImageLoader.show(binding.ivThreeOneTwo.getContext(),
                        collection.get(1).getDisplay_img(), binding.ivThreeOneTwo);
            }
            if (collection.size() > 2) {
                ImageLoader.show(binding.ivThreeOneThree.getContext(),
                        collection.get(2).getDisplay_img(), binding.ivThreeOneThree);
            }
        }
    }
}
