package cn.qhung.musicplayer;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.qhung.musicplayer.databinding.ActivitySplashBinding;
import cn.qhung.musicplayer.music.MainActivity;
import cn.qhung.musicplayer.utils.CommonUtils;
import cn.qhung.musicplayer.utils.PerfectClickListener;
import cn.qhung.musicplayer.utils.image.ImageLoader;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding mBinding;
    private boolean isIn;

    public SplashActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        // 先显示默认图
        mBinding.ivDefultPic.setImageDrawable(CommonUtils.getDrawable(R.drawable.img_transition_default));
        new ImageLoader.Builder(this, Constants.TRANSITION_URL_01, mBinding.ivPic)
                .errorImg(R.drawable.img_transition_default)
                .placeHolder(R.drawable.img_transition_default)
                .show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mBinding.ivDefultPic.setVisibility(View.GONE);
            }
        }, 1500);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toMainActivity();
            }
        }, 3500);


        mBinding.tvJump.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                toMainActivity();
            }
        });
    }


    private void toMainActivity() {
        if (isIn) {
            return;
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        finish();
        isIn = true;
    }
}
