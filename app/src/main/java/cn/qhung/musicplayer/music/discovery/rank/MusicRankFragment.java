package cn.qhung.musicplayer.music.discovery.rank;

import android.support.v4.app.Fragment;

import cn.qhung.musicplayer.R;
import cn.qhung.musicplayer.base.BaseFragment;
import cn.qhung.musicplayer.databinding.FragmentRankBinding;

/**
 * Created by qhung on 2017/3/22.
 */

public class MusicRankFragment extends BaseFragment<FragmentRankBinding> {
    @Override
    public int setContent() {
        return R.layout.fragment_rank;
    }

    public static Fragment newInstance() {
        return new MusicRankFragment();
    }
}
