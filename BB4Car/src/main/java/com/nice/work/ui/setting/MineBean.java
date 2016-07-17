package com.nice.work.ui.setting;

import com.core.bean.BaseBean;

import java.util.List;

/**
 * Created by nice on 16/4/12.
 */
public class MineBean extends BaseBean {


    /**
     * token : zixumjk78wxgbsq3akc4r8s1qesbwkjw
     * member_id : 9010e61df0a94b7ca1d5966464f9101e
     * mobile : 18811493978
     * username : 全喜
     * nickname : null
     * realname : null
     * avatar : null
     * isGrantCoupons : false
     * rank_name : 普通会员
     * pwd_set : 1
     * mine_prompts : [{"classify":"0","name":"我的订单","pic":"","prompt":"您有0个待付款订单"},{"classify":"1","name":"我的购物车","pic":"","prompt":"有0个商品待购买"},{"classify":"2","name":"我的优惠券","pic":"","prompt":"您有204个可用优惠券"},{"classify":"3","name":"我的爱车","pic":"","prompt":"一汽大众奥迪-A6"},{"classify":"4","name":"我的收藏","pic":"","prompt":"收藏的门店没有新活动"},{"classify":"4","name":"我的消息","pic":"","prompt":"您有0条新消息"}]
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean extends BaseBean{
        private String token;
        private String member_id;
        private String mobile;
        private String username;
        private Object nickname;
        private Object realname;
        private Object avatar;
        private boolean isGrantCoupons;
        private String rank_name;
        private int pwd_set;
        /**
         * classify : 0
         * name : 我的订单
         * pic :
         * prompt : 您有0个待付款订单
         */

        private List<MinePromptsBean> mine_prompts;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public Object getRealname() {
            return realname;
        }

        public void setRealname(Object realname) {
            this.realname = realname;
        }

        public Object getAvatar() {
            return avatar;
        }

        public void setAvatar(Object avatar) {
            this.avatar = avatar;
        }

        public boolean isIsGrantCoupons() {
            return isGrantCoupons;
        }

        public void setIsGrantCoupons(boolean isGrantCoupons) {
            this.isGrantCoupons = isGrantCoupons;
        }

        public String getRank_name() {
            return rank_name;
        }

        public void setRank_name(String rank_name) {
            this.rank_name = rank_name;
        }

        public int getPwd_set() {
            return pwd_set;
        }

        public void setPwd_set(int pwd_set) {
            this.pwd_set = pwd_set;
        }

        public List<MinePromptsBean> getMine_prompts() {
            return mine_prompts;
        }

        public void setMine_prompts(List<MinePromptsBean> mine_prompts) {
            this.mine_prompts = mine_prompts;
        }

        public static class MinePromptsBean extends BaseBean{
            private String classify;
            private String name;
            private String pic;
            private String prompt;

            public int getIcon_id() {
                return icon_id;
            }

            public void setIcon_id(int icon_id) {
                this.icon_id = icon_id;
            }

            private int icon_id;

            public String getClassify() {
                return classify;
            }

            public void setClassify(String classify) {
                this.classify = classify;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getPrompt() {
                return prompt;
            }

            public void setPrompt(String prompt) {
                this.prompt = prompt;
            }
        }
    }
}
