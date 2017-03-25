package cn.qhung.musicplayer.utils.image;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import cn.qhung.musicplayer.MusicPlayerApplication;

/**
 * Created by qhung on 2017/3/19.
 */
public class GlideAdapter implements ImageLoader.IImageLoaderAdapter {
    @Override
    public void load(ImageLoader.ImageParam p) {
        DrawableTypeRequest request;
        RequestManager manager = null;
        Object context = p.mContext;
        if (context == null)
            manager = Glide.with(MusicPlayerApplication.get());
        else if (context instanceof Activity)
            manager = Glide.with((Activity) context);
        else if (context instanceof Context)
            manager = Glide.with((Context) context);
        else if (context instanceof Fragment)
            manager = Glide.with((Fragment) context);
        else if (context instanceof android.support.v4.app.Fragment)
            manager = Glide.with((android.support.v4.app.Fragment) context);

        if (manager == null)
            throw new IllegalArgumentException(context.getClass().getName() + " not support");
        Object url = p.mUrl;
        if (url != null && url.toString().contains("http")) {
            request = manager.load(url);
        } else if (url instanceof Integer) {
            request = manager.load(url);
        } else request = manager.load(url);
        if (p.mDefaultImg != 0) {
            request.placeholder(p.mDefaultImg);
        }
        if (p.mErrorImg != 0) {
            request.error(p.mErrorImg);
        }
        if (p.mPlaceHolder != 0) {
            request.placeholder(p.mPlaceHolder);
        }
        if (p.mWidth != 0 && p.mHeight != 0) {
            request.override(p.mWidth, p.mHeight);
        }
        if (p.mListener != null)
            request.listener(new ImageLoadListenerAdapter(p.mListener));
        request.diskCacheStrategy(DiskCacheStrategy.ALL);
        request.crossFade(1000);
        if (p.mImageView instanceof ImageView)
            request.into((ImageView) p.mImageView);
        else if (p.mImageView instanceof Target)
            request.into((Target) p.mImageView);
    }

    private class ImageLoadListenerAdapter implements RequestListener {
        private final ImageLoadListener mListener;

        public ImageLoadListenerAdapter(ImageLoadListener listener) {
            this.mListener = listener;
        }

        @Override
        public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
            if (mListener != null) {
                mListener.onException(e, target);
            }
            return false;
        }

        @Override
        public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
            if (mListener != null) {
                mListener.onResourceReady(resource, target);
            }
            return false;
        }
    }
}
