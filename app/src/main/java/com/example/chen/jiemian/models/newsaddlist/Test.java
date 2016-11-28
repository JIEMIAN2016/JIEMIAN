package com.example.chen.jiemian.models.newsaddlist;

import java.util.List;

/**
 * Created by BYC on 2016/11/26.
 */
public class Test {

    /**
     * code : 0
     * message : suss
     * result : [[{"id":"30","name":"首页","show":"index","url":"cate/main/","unistr":"main","isEdit":"0","app_en_name":"Home"},{"id":"50","name":"我的","show":"show_my","url":"my/news/","unistr":"my","isEdit":"0","app_en_name":"My News"},{"id":"17","name":"热读","show":"show_img_right","url":"cate/181/","unistr":"181","isEdit":"1","app_en_name":"Most Read"},{"id":"1","name":"商业","show":"show_img_top","url":"cate/117/","unistr":"117","isEdit":"1","app_en_name":"Business"},{"id":"2","name":"天下","show":"show_img_top","url":"cate/118/","unistr":"118","isEdit":"1","app_en_name":"World"},{"id":"24","name":"中国","show":"show_img_top","url":"cate/260/","unistr":"260","isEdit":"1","app_en_name":"Nation"}],[{"id":"4","name":"正午","show":"show_img_top_intro","url":"cate/120/","unistr":"120","isEdit":"1","app_en_name":"Noon Story"},{"id":"3","name":"歪楼","show":"show_img_top","url":"cate/119/","unistr":"119","isEdit":"1","app_en_name":"Why Low"},{"id":"19","name":"时尚","show":"show_img_top","url":"cate/183/","unistr":"183","isEdit":"1","app_en_name":"Fashion"},{"id":"28","name":"证券","show":"show_img_right","url":"cate/322/","unistr":"322","isEdit":"1","app_en_name":"Stock"},{"id":"14","name":"投资","show":"show_img_right","url":"cate/142/","unistr":"142","isEdit":"1","app_en_name":"Money"},{"id":"18","name":"营销","show":"show_img_right","url":"cate/182/","unistr":"182","isEdit":"1","app_en_name":"Marketing"},{"id":"20","name":"职场","show":"show_img_right","url":"cate/201/","unistr":"201","isEdit":"1","app_en_name":"Career"},{"id":"442","name":"管理","show":"show_img_top_intro","url":"cate/442/","unistr":"442","isEdit":"1","app_en_name":"Management"},{"id":"13","name":"快讯","show":"show_img_right","url":"cate/141/","unistr":"141","isEdit":"1","app_en_name":"Flash"},{"id":"8","name":"热评","show":"show_img_right","url":"cate/124/","unistr":"124","isEdit":"1","app_en_name":"Most Commented"},{"id":"15","name":"最新","show":"show_img_right","url":"cate/152/","unistr":"152","isEdit":"1","app_en_name":"What's New"}],[{"id":"21","name":"体育","show":"show_img_top","url":"cate/202/","unistr":"202","isEdit":"1","app_en_name":"Sports"},{"id":"22","name":"娱乐","show":"show_img_top","url":"cate/203/","unistr":"203","isEdit":"1","app_en_name":"Entertainment"},{"id":"406","name":"文化","show":"show_img_top","url":"cate/406/","unistr":"406","isEdit":"1","app_en_name":"Culture"},{"id":"27","name":"旅行","show":"show_img_top","url":"cate/313/","unistr":"313","isEdit":"1","app_en_name":"Travel"},{"id":"447","name":"生活","show":"show_img_top","url":"cate/447/","unistr":"447","isEdit":"1","app_en_name":"Lifestyle"},{"id":"469","name":"出国","show":"show_img_top","url":"cate/469/","unistr":"469","recommend":"1","isEdit":"1","app_en_name":"Abroad"},{"id":"470","name":"茶语","show":"show_img_top_intro","url":"cate/third/470/","unistr":"470","recommend":"1","isEdit":"1","app_en_name":"Tea"},{"id":"25","name":"军事","show":"show_img_top","url":"cate/293/","unistr":"293","isEdit":"1","app_en_name":"Military Affairs"},{"id":"26","name":"游戏","show":"show_img_top","url":"cate/294/","unistr":"294","isEdit":"1","app_en_name":"Games"},{"id":"6","name":"JMedia","show":"show_img_right","url":"cate/122/","unistr":"122","isEdit":"1","app_en_name":""},{"id":"16","name":"图片","show":"show_img_top_intro","url":"cate/158/","unistr":"158","isEdit":"1","app_en_name":"Photo"}],[{"id":"7","name":"科技","show":"show_img_top","url":"cate/123/","unistr":"123","isEdit":"1","app_en_name":"Technology"},{"id":"5","name":"地产","show":"show_img_top","url":"cate/121/","unistr":"121","isEdit":"1","app_en_name":"House"},{"id":"10","name":"汽车","show":"show_img_top","url":"cate/138/","unistr":"138","isEdit":"1","app_en_name":"Cars"},{"id":"9","name":"金融","show":"show_img_right","url":"cate/137/","unistr":"137","isEdit":"1","app_en_name":"Finance"},{"id":"11","name":"消费","show":"show_img_right","url":"cate/139/","unistr":"139","isEdit":"1","app_en_name":"Consumption"},{"id":"23","name":"工业","show":"show_img_right","url":"cate/259/","unistr":"259","isEdit":"1","app_en_name":"Industries"},{"id":"12","name":"交通","show":"show_img_right","url":"cate/140/","unistr":"140","isEdit":"1","app_en_name":"Transportation"},{"id":"463","name":"创业","show":"show_img_top","url":"cate/463/","unistr":"463","isEdit":"1","app_en_name":"Venture"}]]
     */

    private String code;
    private String message;
    private List<List<ResultBean>> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<List<ResultBean>> getResult() {
        return result;
    }

    public void setResult(List<List<ResultBean>> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 30
         * name : 首页
         * show : index
         * url : cate/main/
         * unistr : main
         * isEdit : 0
         * app_en_name : Home
         */

        private String id;
        private String name;
        private String show;
        private String url;
        private String unistr;
        private String isEdit;
        private String app_en_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShow() {
            return show;
        }

        public void setShow(String show) {
            this.show = show;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUnistr() {
            return unistr;
        }

        public void setUnistr(String unistr) {
            this.unistr = unistr;
        }

        public String getIsEdit() {
            return isEdit;
        }

        public void setIsEdit(String isEdit) {
            this.isEdit = isEdit;
        }

        public String getApp_en_name() {
            return app_en_name;
        }

        public void setApp_en_name(String app_en_name) {
            this.app_en_name = app_en_name;
        }
    }
}
