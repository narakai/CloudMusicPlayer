package cn.qhung.musicplayer.net.entities;

import java.util.List;

/**
 * Created by qhung on 2017/3/25.
 */

public class MusicPlayList {

    /**
     * name : 虾米
     * Music : [{"platform":2,"list_id":273438232,"collect_name":"逃不过的似水流年，舍不下的此间音乐","display_img":"http://img.xiami.net/res/img/default/cd100.gif","user_name":"YOUTH"},{"platform":2,"list_id":266425168,"collect_name":"一把吉他，一种情怀","display_img":"http://img.xiami.net/res/img/default/cd100.gif","user_name":"Sorstalanság"},{"platform":2,"list_id":242959685,"collect_name":"无词哼吟唱◆以声代词，咏叹昙花一现之美","display_img":"http://img.xiami.net/res/img/default/cd100.gif","user_name":"幻。隐。密"},{"platform":2,"list_id":124985805,"collect_name":"[ 电音 | 史诗 | BGM ]一张适合肝的精选","display_img":"http://img.xiami.net/res/img/default/cd100.gif","user_name":"卡乐b"},{"platform":2,"list_id":274827137,"collect_name":"武林外传--同福音乐会","display_img":"http://img.xiami.net/res/img/default/cd100.gif","user_name":"Iris点点"},{"platform":2,"list_id":273080829,"collect_name":"旅行，是为了忘记你","display_img":"http://img.xiami.net/res/img/default/cd100.gif","user_name":"不纯粹的白羊小战士"},{"platform":2,"list_id":276521491,"collect_name":"【节奏控】清新电子 唤醒好心情","display_img":"http://img.xiami.net/res/img/default/cd100.gif","user_name":"music.猫"},{"platform":2,"list_id":21685418,"collect_name":"［钢琴］她和她的梦","display_img":"http://img.xiami.net/res/img/default/cd100.gif","user_name":"呐_深海"},{"platform":2,"list_id":29641122,"collect_name":"千山万水沿途风景有多美 也比不上你在我身边徘徊","display_img":"http://img.xiami.net/res/img/default/cd100.gif","user_name":"我不是达尔文"},{"platform":2,"list_id":273799457,"collect_name":"连上七天班的必备音乐~","display_img":"http://img.xiami.net/res/img/default/cd100.gif","user_name":"瑪麗橙子"},{"platform":2,"list_id":270773261,"collect_name":"搖滾詩的誕生與轉生","display_img":"http://img.xiami.net/res/img/default/cd100.gif","user_name":"AC"},{"platform":2,"list_id":266669939,"collect_name":"国内民谣，每人一首代表作（三）【虾小米首页】【非大众】","display_img":"http://img.xiami.net/res/img/default/cd100.gif","user_name":"Saxon"},{"platform":2,"list_id":268737360,"collect_name":"民谣｜我的心，总是居无定所.","display_img":"http://img.xiami.net/res/img/default/cd100.gif","user_name":"信人."},{"platform":2,"list_id":110756929,"collect_name":"就是想听燃的，燃到飞飞飞飞飞起","display_img":"http://img.xiami.net/res/img/default/cd100.gif","user_name":"Dark Iceberg"},{"platform":2,"list_id":274876718,"collect_name":"去我烦忧，解我眉头","display_img":"http://img.xiami.net/res/img/default/cd100.gif","user_name":"小杰"}]
     */

    private String name;
    private List<MusicCollection> Music;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MusicCollection> getMusic() {
        return Music;
    }

    public void setMusic(List<MusicCollection> Music) {
        this.Music = Music;
    }
    public static class MusicCollection {

        /**
         * platform : 2
         * list_id : 273438232
         * collect_name : 逃不过的似水流年，舍不下的此间音乐
         * display_img : http://img.xiami.net/res/img/default/cd100.gif
         * user_name : YOUTH
         */

        private int platform;
        private int list_id;
        private String collect_name;
        private String display_img;
        private String user_name;

        public int getPlatform() {
            return platform;
        }

        public void setPlatform(int platform) {
            this.platform = platform;
        }

        public int getList_id() {
            return list_id;
        }

        public void setList_id(int list_id) {
            this.list_id = list_id;
        }

        public String getCollect_name() {
            return collect_name;
        }

        public void setCollect_name(String collect_name) {
            this.collect_name = collect_name;
        }

        public String getDisplay_img() {
            return display_img;
        }

        public void setDisplay_img(String display_img) {
            this.display_img = display_img;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }
    }

}
