package cn.qhung.musicplayer.utils.image;

/**
 * Created by qhung on 2017/3/19.
 */

public interface ImageLoadListener {

    boolean onException(Exception e, Object imageView);

    boolean onResourceReady(Object resource, Object imageView);
}
