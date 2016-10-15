package com.pro.happyjuzi.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13 0013.
 */

public class ReBean implements BaseBean{


    /**
     * id : 93232
     * urlroute : juzi://article/detail/native?id=93232
     * title : 天啊！原来《湄公河》彭于晏没有死！续集赶紧和张涵予CP吧
     * pic : http://cdn01.happyjuzi.com/news/201610/13/57ff153baa12e.png!ac1.nw.webp
     * icontype : 0
     * cat : {"name":"影视","icon":"http://cdn01.happyjuzi.com/content/201608/02/57a057e08d140.png!up1"}
     * flag : 1
     * publish_time : 2小时前
     * readnum : 5162
     * replynum : 8
     * author : {"name":"chickying-橘子编辑"}
     * type : 0
     * display : 0
     */

    private int id;
    private String urlroute;
    private String title;
    private String pic;
    private int icontype;
    /**
     * name : 影视
     * icon : http://cdn01.happyjuzi.com/content/201608/02/57a057e08d140.png!up1
     */

    private CatBean cat;
    private int flag;
    private String publish_time;
    private int readnum;
    private int replynum;
    /**
     * name : chickying-橘子编辑
     */

    private AuthorBean author;
    private int type;
    private int display;

    private Grader grader;
    private String txtlead;
    private String shareurl;
    private List<Gif> gif;
    private String src;

    public Grader getGrader() {
        return grader;
    }

    public void setGrader(Grader grader) {
        this.grader = grader;
    }

    public String getTxtlead() {
        return txtlead;
    }

    public void setTxtlead(String txtlead) {
        this.txtlead = txtlead;
    }

    public String getShareurl() {
        return shareurl;
    }

    public void setShareurl(String shareurl) {
        this.shareurl = shareurl;
    }

    public List<Gif> getGif() {
        return gif;
    }

    public void setGif(List<Gif> gif) {
        this.gif = gif;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public static class Gif{
        private String mp4;
        private String pic;
        private String url;
        private String thumb;
        private String thumb_jpg;
        private int width;
        private int height;

        public String getMp4() {
            return mp4;
        }

        public void setMp4(String mp4) {
            this.mp4 = mp4;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getThumb_jpg() {
            return thumb_jpg;
        }

        public void setThumb_jpg(String thumb_jpg) {
            this.thumb_jpg = thumb_jpg;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
    public static class Grader {
        private boolean isvoted;
        private Voteimg voteimg;
        private int myscore;
        private int totalscore;
        private int members;

        public boolean isvoted() {
            return isvoted;
        }

        public void setIsvoted(boolean isvoted) {
            this.isvoted = isvoted;
        }

        public Voteimg getVoteimg() {
            return voteimg;
        }

        public void setVoteimg(Voteimg voteimg) {
            this.voteimg = voteimg;
        }

        public int getMyscore() {
            return myscore;
        }

        public void setMyscore(int myscore) {
            this.myscore = myscore;
        }

        public int getTotalscore() {
            return totalscore;
        }

        public void setTotalscore(int totalscore) {
            this.totalscore = totalscore;
        }

        public int getMembers() {
            return members;
        }

        public void setMembers(int members) {
            this.members = members;
        }
    }

    public static class Voteimg {
        private String src;
        private int width;
        private int height;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlroute() {
        return urlroute;
    }

    public void setUrlroute(String urlroute) {
        this.urlroute = urlroute;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getIcontype() {
        return icontype;
    }

    public void setIcontype(int icontype) {
        this.icontype = icontype;
    }

    public CatBean getCat() {
        return cat;
    }

    public void setCat(CatBean cat) {
        this.cat = cat;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public int getReadnum() {
        return readnum;
    }

    public void setReadnum(int readnum) {
        this.readnum = readnum;
    }

    public int getReplynum() {
        return replynum;
    }

    public void setReplynum(int replynum) {
        this.replynum = replynum;
    }

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public static class CatBean {
        private String name;
        private String icon;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    public static class AuthorBean {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
