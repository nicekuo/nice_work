package com.naneng.jiche.background;

import com.core.bean.BaseBean;

/**
 * Created by ${nice} on ${2016年04月29日14:09:09}.
 */
public class JICHEShareModel extends BaseBean {

    private String share_title;
    private String share_desc;
    private String share_pic;
    private String share_url;

    public String getShare_title() {
        return share_title;
    }

    public void setShare_title(String share_title) {
        this.share_title = share_title;
    }

    public String getShare_desc() {
        return share_desc;
    }

    public void setShare_desc(String share_desc) {
        this.share_desc = share_desc;
    }

    public String getShare_pic() {
        return share_pic;
    }

    public void setShare_pic(String share_pic) {
        this.share_pic = share_pic;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }
}
