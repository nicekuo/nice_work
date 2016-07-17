package com.naneng.jiche.ui.main;

import com.core.bean.BaseBean;

/**
 * Created by nice on 16/4/19.
 */
public class UpgradeBean extends BaseBean {


    /**
     * version_name : 1.1
     * modifyDate : null
     * version_code : 2
     * description : update to this one
     * id : 1
     * isForce : true
     * url : http://api.na-neng.com/upload/jiche_1.1_release_10000_me.apk
     * createDate : 2016-04-19 11:45:48
     * via : 1
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String version_name;
        private Object modifyDate;
        private int version_code;
        private String description;
        private int id;
        private boolean isForce;
        private String url;
        private String createDate;
        private int via;

        public String getVersion_name() {
            return version_name;
        }

        public void setVersion_name(String version_name) {
            this.version_name = version_name;
        }

        public Object getModifyDate() {
            return modifyDate;
        }

        public void setModifyDate(Object modifyDate) {
            this.modifyDate = modifyDate;
        }

        public int getVersion_code() {
            return version_code;
        }

        public void setVersion_code(int version_code) {
            this.version_code = version_code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIsForce() {
            return isForce;
        }

        public void setIsForce(boolean isForce) {
            this.isForce = isForce;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public int getVia() {
            return via;
        }

        public void setVia(int via) {
            this.via = via;
        }
    }
}
