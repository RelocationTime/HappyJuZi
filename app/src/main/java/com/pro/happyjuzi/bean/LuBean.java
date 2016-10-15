package com.pro.happyjuzi.bean;

/**
 * Created by Administrator on 2016/10/13 0013.
 */

public class LuBean implements BaseBean {


    /**
     * id : 93142
     * urlroute : juzi://article/detail/native?id=93142
     * title : 王俊凯赵丽颖宋茜，ta们没成名时的辛酸史你们听说过吗？
     * pic : http://cdn01.happyjuzi.com/news/201610/13/57fe638089e1d.png!ac1.nw.webp
     * icontype : 0
     * readnum : 137476
     * replynum : 150
     * cat : {"name":"八卦","icon":"http://cdn01.happyjuzi.com/content/201607/29/579b123e28f8c.png!up1"}
     * author : {"name":"皮皮-橘子编辑"}
     */

    private int id;
    private String urlroute;
    private String title;
    private String pic;
    private int icontype;
    private int readnum;
    private int replynum;
    /**
     * name : 八卦
     * icon : http://cdn01.happyjuzi.com/content/201607/29/579b123e28f8c.png!up1
     */

    private CatBean cat;
    /**
     * name : 皮皮-橘子编辑
     */

    private AuthorBean author;

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

    public CatBean getCat() {
        return cat;
    }

    public void setCat(CatBean cat) {
        this.cat = cat;
    }

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
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
