package cn.qhung.musicplayer.music.other;

import android.support.v4.app.Fragment;

import cn.qhung.musicplayer.R;
import cn.qhung.musicplayer.base.BaseFragment;
import cn.qhung.musicplayer.databinding.FragmentOtherBinding;

/**
 * Created by qhung on 2017/3/22.
 */

public class OtherFragment extends BaseFragment<FragmentOtherBinding> {
    @Override
    public int setContent() {
        return R.layout.fragment_other;
    }

    public static Fragment newInstance() {
        return new OtherFragment();
    }
}
