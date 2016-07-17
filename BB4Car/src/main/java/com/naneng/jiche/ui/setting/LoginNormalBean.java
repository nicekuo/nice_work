package com.naneng.jiche.ui.setting;

import com.core.bean.BaseBean;

/**
 * Created by admin on 2016/3/24.
 */
public class LoginNormalBean extends BaseBean {

    /**
     * username : test2
     * nickname : null
     * token : wyy4gmjvwdjma2eoa334xmzqdob0brmu
     * pwd_set : 1
     * realname : null
     * avatar : null
     * mobile : null
     * member_id : 402881862bf08f18012bf0a16b7c0010
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String username;
        private Object nickname;
        private String token;
        private int pwd_set;
        private Object realname;
        private Object avatar;
        private Object mobile;
        private String member_id;

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

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getPwd_set() {
            return pwd_set;
        }

        public void setPwd_set(int pwd_set) {
            this.pwd_set = pwd_set;
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

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
        }

        public String getMember_id() {
            return member_id;
        }

        public void setMember_id(String member_id) {
            this.member_id = member_id;
        }
    }
}
