package com.example.chen.jiemian.models;

import java.util.List;

/**
 * Created by chen on 2016/11/24.
 */
public class DrawerLeft {

    /**
     * code : 0
     * message : success
     * result : {"channel":[{"color":"#f12b35","rows":[{"icon":"channel_jm","id":"0","show":"","slogan":"500位CEO鼎力推荐","sort":"","title":"界面新闻","type":"0","unistr":"","url":""}]},{"color":"#1760a4","rows":[{"icon":"http://img.jiemian.com/101/original/20161104/147822658719149700.png","id":"0","show":"","slogan":"与投资高手为伍","sort":"","title":"摩尔金融","type":"11","unistr":"","url":"http://www.moer.cn/otherWapIndex.htm"}]},{"color":"#741884","rows":[{"icon":"channel_uw","id":"0","show":"","slogan":"原创设计新势力","sort":"","title":"尤物","type":"11","unistr":"","url":"http://m.youwu.jiemian.com?source=app"}]},{"color":"#5DB98B","rows":[{"icon":"http://img.jiemian.com/101/original/20160621/146648266221009300.png","id":"0","show":"","slogan":"一对一电话请教","sort":"","title":"前辈","type":"11","unistr":"","url":"http://qianbei.jiemian.com/qianbei/answers/home"}]},{"color":"#d5661c","rows":[{"icon":"channel_md","id":"0","show":"","slogan":"拿礼拿到停不下来","sort":"","title":"面点商城","type":"12","unistr":"","url":"http://a.jiemian.com/mobile/index.php?m=market&a=index"}]},{"color":"#119e9e","rows":[{"icon":"channel_fbi","id":"0","show":"","slogan":"品牌的秘密都在这里","sort":"","title":"招牌","type":"11","unistr":"","url":"http://m.jiemian.com/lists/124.html"},{"icon":"http://img.jiemian.com/101/original/20151208/144956488938147200.png","id":"0","show":"","slogan":"行家问答","sort":"","title":"好问","type":"11","unistr":"","url":"http://a.jiemian.com/index.php?m=qanda&a=lists"},{"icon":"http://img.jiemian.com/101/original/20151208/144956489682264300.png","id":"0","show":"","slogan":"界面召集令","sort":"","title":"活动","type":"11","unistr":"","url":"http://a.jiemian.com/mobile/index.php?m=Promotion"}]},{"color":"#c49933","rows":[{"icon":"channel_zw","id":"4","show":"show_img_top_intro","slogan":"致力于故事的发现和实现","sort":"","title":"正午","type":"10","unistr":"120","url":"http://appapi.jiemian.com/article/cate/120.json"},{"icon":"channel_wl","id":"3","show":"show_img_top","slogan":"有趣，有爱，有文化","sort":"","title":"歪楼","type":"10","unistr":"119","url":"http://appapi.jiemian.com/article/cate/119.json"},{"icon":"http://img.jiemian.com/101/original/20151030/144617156034818700.png","id":"19","show":"show_img_top","slogan":"纽约时报T界","sort":"","title":"时尚","type":"10","unistr":"183","url":"http://appapi.jiemian.com/article/cate/183.json"}]}],"cooperation":{"data":[{"icon":"","id":"-100","show":"","slogan":"全球精品旅行体验","sort":"1","title":"赞那度","type":"11","unistr":"","url":"http://zanadu.cn/?utm_source=jiemian_app&utm_medium=strategy_placement&utm_campaign=longterm"},{"icon":"","id":"-101","show":"","slogan":"聪明的理财选择","sort":"2","title":"懒投资","type":"11","unistr":"","url":"https://lantouzi.com/index_custom?pcode=jiemian&share=%7B%22title%22%3A%22%5Cu61d2%5Cu6295%5Cu8d44%26%5Cu754c%5Cu9762+%7C+%5Cu72ec%5Cu7acb%5Cu601d%5Cu8003%5Cu8005%5Cu7684%5Cu6295%5Cu8d44%5Cu4ff1%5Cu4e50%5Cu90e8%22%2C%22url%22%3A%22https%3A%5C%2F%5C%2Flantouzi.com%5C%2Findex_custom%3Fpcode%3Djiemian%22%2C%22imgurl%22%3A%22https%3A%5C%2F%5C%2Fs1.lantouzi.com%5C%2Fimg%5C%2F201511%5C%2Fltz%5C%2Fjiemian.jpg%22%2C%22content%22%3A%22%5Cu4f60%5Cu82e5%5Cu9752%5Cu7750%5Cuff0c%5Cu4ef7%5Cu503c%5Cu65e0%5Cu5904%5Cu4e0d%5Cu5728%22%7D"},{"icon":"","id":"-105","show":"","slogan":"省心选房上链家网","sort":"3","title":"链家网","type":"11","unistr":"","url":"http://m.lianjia.com?utm_source=jiemian&utm_medium=rss&utm_term=&utm_content=&utm_campaign=6.28"},{"icon":"","id":"-103","show":"","slogan":"爱活动就上活动树","sort":"4","title":"活动树","type":"11","unistr":"","url":"http://m.huodongshu.com/h5/enterprise/comp/100/index.html?from=jiemian"},{"icon":"","id":"-104","show":"","slogan":"理财之前先看菜鸟理财","sort":"5","title":"菜鸟理财","type":"11","unistr":"","url":"http://m.cainiaolc.com"}],"notes":"对战略合作感兴趣？请点击了解详情\u2026","notesUrl":"http://m.jiemian.com/article/389805_app.html"}}
     */

    private String code;
    private String message;
    private ResultBean result;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * channel : [{"color":"#f12b35","rows":[{"icon":"channel_jm","id":"0","show":"","slogan":"500位CEO鼎力推荐","sort":"","title":"界面新闻","type":"0","unistr":"","url":""}]},{"color":"#1760a4","rows":[{"icon":"http://img.jiemian.com/101/original/20161104/147822658719149700.png","id":"0","show":"","slogan":"与投资高手为伍","sort":"","title":"摩尔金融","type":"11","unistr":"","url":"http://www.moer.cn/otherWapIndex.htm"}]},{"color":"#741884","rows":[{"icon":"channel_uw","id":"0","show":"","slogan":"原创设计新势力","sort":"","title":"尤物","type":"11","unistr":"","url":"http://m.youwu.jiemian.com?source=app"}]},{"color":"#5DB98B","rows":[{"icon":"http://img.jiemian.com/101/original/20160621/146648266221009300.png","id":"0","show":"","slogan":"一对一电话请教","sort":"","title":"前辈","type":"11","unistr":"","url":"http://qianbei.jiemian.com/qianbei/answers/home"}]},{"color":"#d5661c","rows":[{"icon":"channel_md","id":"0","show":"","slogan":"拿礼拿到停不下来","sort":"","title":"面点商城","type":"12","unistr":"","url":"http://a.jiemian.com/mobile/index.php?m=market&a=index"}]},{"color":"#119e9e","rows":[{"icon":"channel_fbi","id":"0","show":"","slogan":"品牌的秘密都在这里","sort":"","title":"招牌","type":"11","unistr":"","url":"http://m.jiemian.com/lists/124.html"},{"icon":"http://img.jiemian.com/101/original/20151208/144956488938147200.png","id":"0","show":"","slogan":"行家问答","sort":"","title":"好问","type":"11","unistr":"","url":"http://a.jiemian.com/index.php?m=qanda&a=lists"},{"icon":"http://img.jiemian.com/101/original/20151208/144956489682264300.png","id":"0","show":"","slogan":"界面召集令","sort":"","title":"活动","type":"11","unistr":"","url":"http://a.jiemian.com/mobile/index.php?m=Promotion"}]},{"color":"#c49933","rows":[{"icon":"channel_zw","id":"4","show":"show_img_top_intro","slogan":"致力于故事的发现和实现","sort":"","title":"正午","type":"10","unistr":"120","url":"http://appapi.jiemian.com/article/cate/120.json"},{"icon":"channel_wl","id":"3","show":"show_img_top","slogan":"有趣，有爱，有文化","sort":"","title":"歪楼","type":"10","unistr":"119","url":"http://appapi.jiemian.com/article/cate/119.json"},{"icon":"http://img.jiemian.com/101/original/20151030/144617156034818700.png","id":"19","show":"show_img_top","slogan":"纽约时报T界","sort":"","title":"时尚","type":"10","unistr":"183","url":"http://appapi.jiemian.com/article/cate/183.json"}]}]
         * cooperation : {"data":[{"icon":"","id":"-100","show":"","slogan":"全球精品旅行体验","sort":"1","title":"赞那度","type":"11","unistr":"","url":"http://zanadu.cn/?utm_source=jiemian_app&utm_medium=strategy_placement&utm_campaign=longterm"},{"icon":"","id":"-101","show":"","slogan":"聪明的理财选择","sort":"2","title":"懒投资","type":"11","unistr":"","url":"https://lantouzi.com/index_custom?pcode=jiemian&share=%7B%22title%22%3A%22%5Cu61d2%5Cu6295%5Cu8d44%26%5Cu754c%5Cu9762+%7C+%5Cu72ec%5Cu7acb%5Cu601d%5Cu8003%5Cu8005%5Cu7684%5Cu6295%5Cu8d44%5Cu4ff1%5Cu4e50%5Cu90e8%22%2C%22url%22%3A%22https%3A%5C%2F%5C%2Flantouzi.com%5C%2Findex_custom%3Fpcode%3Djiemian%22%2C%22imgurl%22%3A%22https%3A%5C%2F%5C%2Fs1.lantouzi.com%5C%2Fimg%5C%2F201511%5C%2Fltz%5C%2Fjiemian.jpg%22%2C%22content%22%3A%22%5Cu4f60%5Cu82e5%5Cu9752%5Cu7750%5Cuff0c%5Cu4ef7%5Cu503c%5Cu65e0%5Cu5904%5Cu4e0d%5Cu5728%22%7D"},{"icon":"","id":"-105","show":"","slogan":"省心选房上链家网","sort":"3","title":"链家网","type":"11","unistr":"","url":"http://m.lianjia.com?utm_source=jiemian&utm_medium=rss&utm_term=&utm_content=&utm_campaign=6.28"},{"icon":"","id":"-103","show":"","slogan":"爱活动就上活动树","sort":"4","title":"活动树","type":"11","unistr":"","url":"http://m.huodongshu.com/h5/enterprise/comp/100/index.html?from=jiemian"},{"icon":"","id":"-104","show":"","slogan":"理财之前先看菜鸟理财","sort":"5","title":"菜鸟理财","type":"11","unistr":"","url":"http://m.cainiaolc.com"}],"notes":"对战略合作感兴趣？请点击了解详情\u2026","notesUrl":"http://m.jiemian.com/article/389805_app.html"}
         */

        private CooperationBean cooperation;
        private List<ChannelBean> channel;

        public CooperationBean getCooperation() {
            return cooperation;
        }

        public void setCooperation(CooperationBean cooperation) {
            this.cooperation = cooperation;
        }

        public List<ChannelBean> getChannel() {
            return channel;
        }

        public void setChannel(List<ChannelBean> channel) {
            this.channel = channel;
        }

        public static class CooperationBean {
            /**
             * data : [{"icon":"","id":"-100","show":"","slogan":"全球精品旅行体验","sort":"1","title":"赞那度","type":"11","unistr":"","url":"http://zanadu.cn/?utm_source=jiemian_app&utm_medium=strategy_placement&utm_campaign=longterm"},{"icon":"","id":"-101","show":"","slogan":"聪明的理财选择","sort":"2","title":"懒投资","type":"11","unistr":"","url":"https://lantouzi.com/index_custom?pcode=jiemian&share=%7B%22title%22%3A%22%5Cu61d2%5Cu6295%5Cu8d44%26%5Cu754c%5Cu9762+%7C+%5Cu72ec%5Cu7acb%5Cu601d%5Cu8003%5Cu8005%5Cu7684%5Cu6295%5Cu8d44%5Cu4ff1%5Cu4e50%5Cu90e8%22%2C%22url%22%3A%22https%3A%5C%2F%5C%2Flantouzi.com%5C%2Findex_custom%3Fpcode%3Djiemian%22%2C%22imgurl%22%3A%22https%3A%5C%2F%5C%2Fs1.lantouzi.com%5C%2Fimg%5C%2F201511%5C%2Fltz%5C%2Fjiemian.jpg%22%2C%22content%22%3A%22%5Cu4f60%5Cu82e5%5Cu9752%5Cu7750%5Cuff0c%5Cu4ef7%5Cu503c%5Cu65e0%5Cu5904%5Cu4e0d%5Cu5728%22%7D"},{"icon":"","id":"-105","show":"","slogan":"省心选房上链家网","sort":"3","title":"链家网","type":"11","unistr":"","url":"http://m.lianjia.com?utm_source=jiemian&utm_medium=rss&utm_term=&utm_content=&utm_campaign=6.28"},{"icon":"","id":"-103","show":"","slogan":"爱活动就上活动树","sort":"4","title":"活动树","type":"11","unistr":"","url":"http://m.huodongshu.com/h5/enterprise/comp/100/index.html?from=jiemian"},{"icon":"","id":"-104","show":"","slogan":"理财之前先看菜鸟理财","sort":"5","title":"菜鸟理财","type":"11","unistr":"","url":"http://m.cainiaolc.com"}]
             * notes : 对战略合作感兴趣？请点击了解详情…
             * notesUrl : http://m.jiemian.com/article/389805_app.html
             */

            private String notes;
            private String notesUrl;
            private List<DataBean> data;

            public String getNotes() {
                return notes;
            }

            public void setNotes(String notes) {
                this.notes = notes;
            }

            public String getNotesUrl() {
                return notesUrl;
            }

            public void setNotesUrl(String notesUrl) {
                this.notesUrl = notesUrl;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * icon :
                 * id : -100
                 * show :
                 * slogan : 全球精品旅行体验
                 * sort : 1
                 * title : 赞那度
                 * type : 11
                 * unistr :
                 * url : http://zanadu.cn/?utm_source=jiemian_app&utm_medium=strategy_placement&utm_campaign=longterm
                 */

                private String icon;
                private String id;
                private String show;
                private String slogan;
                private String sort;
                private String title;
                private String type;
                private String unistr;
                private String url;

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getShow() {
                    return show;
                }

                public void setShow(String show) {
                    this.show = show;
                }

                public String getSlogan() {
                    return slogan;
                }

                public void setSlogan(String slogan) {
                    this.slogan = slogan;
                }

                public String getSort() {
                    return sort;
                }

                public void setSort(String sort) {
                    this.sort = sort;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUnistr() {
                    return unistr;
                }

                public void setUnistr(String unistr) {
                    this.unistr = unistr;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }

        public static class ChannelBean {
            /**
             * color : #f12b35
             * rows : [{"icon":"channel_jm","id":"0","show":"","slogan":"500位CEO鼎力推荐","sort":"","title":"界面新闻","type":"0","unistr":"","url":""}]
             */

            private String color;
            private List<RowsBean> rows;

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public List<RowsBean> getRows() {
                return rows;
            }

            public void setRows(List<RowsBean> rows) {
                this.rows = rows;
            }

            public static class RowsBean {
                /**
                 * icon : channel_jm
                 * id : 0
                 * show :
                 * slogan : 500位CEO鼎力推荐
                 * sort :
                 * title : 界面新闻
                 * type : 0
                 * unistr :
                 * url :
                 */

                private String icon;
                private String id;
                private String show;
                private String slogan;
                private String sort;
                private String title;
                private String type;
                private String unistr;
                private String url;

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getShow() {
                    return show;
                }

                public void setShow(String show) {
                    this.show = show;
                }

                public String getSlogan() {
                    return slogan;
                }

                public void setSlogan(String slogan) {
                    this.slogan = slogan;
                }

                public String getSort() {
                    return sort;
                }

                public void setSort(String sort) {
                    this.sort = sort;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUnistr() {
                    return unistr;
                }

                public void setUnistr(String unistr) {
                    this.unistr = unistr;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}
