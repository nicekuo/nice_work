package com.nice.work.ui.account;

import com.core.bean.BaseBean;

/**
 * Created by nice on 16/3/23.
 */
public class LoginBean extends BaseBean {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean extends BaseBean {
        private String token;
        private String member_id;
        private String mobile;
        private String username;
        private String nickname;
        private String realname;
        private String avatar;

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBorn() {
            return born;
        }

        public void setBorn(String born) {
            this.born = born;
        }

        private String sex;
        private String born;

        private int pwd_set;
        private String rank_name;

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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getPwd_set() {
            return pwd_set;
        }

        public void setPwd_set(int pwd_set) {
            this.pwd_set = pwd_set;
        }

        public String getRank_name() {
            return rank_name;
        }

        public void setRank_name(String rank_name) {
            this.rank_name = rank_name;
        }

    }
}
