package cn.qhung.musicplayer.utils.image;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

/**
 * Created by qhung on 2017/3/19.
 */

public class ImageLoader {

    public static void show(Context context, Object url, ImageView imageView) {
        Builder builder = new Builder(context, url, imageView);
        show(builder);
    }

    private static void show(Builder builder) {
        if (builder == null)
            return;
        IImageLoaderAdapter imageLoader = get();
        imageLoader.load(builder.p);
    }

    private static IImageLoaderAdapter mImageLoader;

    static {
        mImageLoader = new GlideAdapter();
    }

    private static IImageLoaderAdapter get() {
        return mImageLoader;
    }

    public static class Builder {

        private ImageParam p = new ImageParam();

        public Builder(Object context, Object url, Object imageView) {
            p.mContext = context;
            p.mUrl = url;
            p.mImageView = imageView;
        }

        public Builder loadListener(ImageLoadListener listener) {
            p.mListener = listener;
            return this;
        }

        public Builder defaultImg(@DrawableRes int defaultImg) {
            p.mDefaultImg = defaultImg;
            return this;
        }

        public Builder errorImg(@DrawableRes int errorImg) {
            p.mErrorImg = errorImg;
            return this;
        }

        public Builder placeHolder(@DrawableRes int placeHolder) {
            p.mPlaceHolder = placeHolder;
            return this;
        }

        public Builder height(int height) {
            p.mHeight = height;
            return this;
        }

        public Builder width(int width) {
            p.mWidth = width;
            return this;
        }

        public void show() {
            if (p.mContext == null || p.mUrl == null || p.mImageView == null) {
                throw new IllegalArgumentException();
            }
            if (p.mContext instanceof Context
                    || p.mContext instanceof Fragment
                    || p.mContext instanceof android.app.Fragment) {
                ImageLoader.show(this);
                return;
            }
            throw new IllegalArgumentException("wrong context");
        }
    }

    static class ImageParam {
        Object mUrl;
        Object mImageView;
        Object mContext;
        int mDefaultImg;
        int mErrorImg;
        int mHeight;
        int mWidth;
        ImageLoadListener mListener;
        int mPlaceHolder;
    }

    interface IImageLoaderAdapter {

        void load(ImageParam builder);
    }
}
