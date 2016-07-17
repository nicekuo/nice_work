package com.nice.work.ui.webview;

import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by ${nice} on ${2016年04月29日14:09:09}.
 */
public class WebViewSettingUtil {


    public static void initWebViewSetting(Builder builder) {
        WebSettings webSettings = builder.webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);//设定支持viewport
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setSupportMultipleWindows(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_MR1) {
            webSettings.setLoadWithOverviewMode(true);
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
            webSettings.setDatabasePath("/data/data/" + builder.webView.getContext().getPackageName() + "/databases/");
        } else {
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        }
        builder.webView.setWebViewClient(builder.webviewClient);
        builder.webView.setWebChromeClient(builder.webChromeClient);
        builder.webView.addJavascriptInterface(builder.javaScriptInterface, "jiche");
        webDebug(builder.webView, true);
    }


    private static void webDebug(WebView webView, boolean isDebugg) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setWebContentsDebuggingEnabled(isDebugg);
        }
    }


    public static class Builder {

        private NiceJavaScriptInterface javaScriptInterface;
        private NiceWebChromeClient webChromeClient;
        private NiceWebviewClient webviewClient;
        private WebView webView;

        public Builder setWebView(WebView webView) {
            this.webView = webView;
            return this;
        }

        public Builder setJavaScriptInterface(NiceJavaScriptInterface javaScriptInterface) {
            this.javaScriptInterface = javaScriptInterface;
            return this;
        }

        public Builder setWebChromeClient(NiceWebChromeClient webChromeClient) {
            this.webChromeClient = webChromeClient;
            return this;
        }

        public Builder setWebClient(NiceWebviewClient webClient) {
            this.webviewClient = webClient;
            return this;
        }


    }


}
