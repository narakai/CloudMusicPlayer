package cn.qhung.musicplayer.music.mine;

import android.support.v4.app.Fragment;

import cn.qhung.musicplayer.R;
import cn.qhung.musicplayer.base.BaseFragment;
import cn.qhung.musicplayer.databinding.FragmentMineBinding;

public class MineFragment extends BaseFragment<FragmentMineBinding> {

    @Override
    public int setContent() {
        return R.layout.fragment_mine;
    }

    public static Fragment newInstance() {
        return new MineFragment();
    }
}
