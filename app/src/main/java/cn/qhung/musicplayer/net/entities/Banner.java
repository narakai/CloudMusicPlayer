package cn.qhung.musicplayer.net.entities;

/**
 * Created by qhung on 2017/3/25.
 */

public class Banner {
    public Banner(int id, String linkUrl, String picUrl) {
        this.id = id;
        this.linkUrl = linkUrl;
        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", linkUrl='" + linkUrl + '\'' +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }

    /**
     * id : 9183
     * linkUrl : http://y.qq.com/w/album.html?albummid=00241nXb02jLuE
     * picUrl : http://y.gtimg.cn/music/photo_new/T003R720x288M000000D0TXO3e7EIk.jpg
     */

    private int id;
    private String linkUrl;
    private String picUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
