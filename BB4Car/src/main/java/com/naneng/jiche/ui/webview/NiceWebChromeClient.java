package com.naneng.jiche.ui.webview;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.naneng.jiche.core.AbstractActivity;


/**
 * Created by ${nice} on ${2016年04月29日14:09:09}.
 */
public class NiceWebChromeClient extends WebChromeClient {

    AbstractActivity activity;

    public NiceWebChromeClient(AbstractActivity activity){
        this.activity =activity;
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
//        if (loadProgressListener!=null){
//            loadProgressListener.onProgressChanged(view,newProgress);
//        }
        super.onProgressChanged(view, newProgress);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if (activity!=null){
            activity.setTitleName(title);
        }
    }
}