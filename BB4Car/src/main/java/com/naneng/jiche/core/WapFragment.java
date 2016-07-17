package com.naneng.jiche.core;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.core.R;

import java.util.List;

/**
 * @author sufun
 * @Description: WAP页面
 * @date 2016年2月25日 18:11:56
 */
public class WapFragment extends Fragment {
	
	private Context context;
	private View view;
	private WebView webview;
	private List<String> urlList;
	private String content;
	public String url=""; //网页url
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		if(view==null){
			view = inflater.inflate(R.layout.wap, null);
			webview = (WebView) view.findViewById(R.id.webview_id);
			//设置编码  
			webview.getSettings().setDefaultTextEncodingName("utf-8");  
		    //支持js  
			webview.getSettings().setJavaScriptEnabled(true);  
		    //设置背景颜色 透明  
			webview.setBackgroundColor(Color.argb(0, 0, 0, 0));  
			webview.getSettings().setDefaultFontSize(16);
		    //设置本地调用对象及其接口  
//			webview.addJavascriptInterface(new JavaScriptObject(context), "mWebViewImageListener");
//			webview.getSettings().setPluginsEnabled(true);
//			webview.setWebViewClient(new CustomWebViewClient());
			//
			if(getActivity() instanceof WapActivity){
				((WapActivity)getActivity()).showLoadDialog(true);
			}
			webview.setWebChromeClient(new WebChromeClient() { 
				@Override
				public void onProgressChanged(WebView view, int newProgress) {
					if (newProgress == 100) {
						if(getActivity() instanceof WapActivity){
							((AbstractActivity)getActivity()).dissmissWaitingDialog();
						}
						webview.setVisibility(View.VISIBLE);
					}
				}
			});
		}else{
			((ViewGroup)view.getParent()).removeAllViewsInLayout();
		}
		return view;
	}
	
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		if(isVisibleToUser && getActivity()!=null && TextUtils.isEmpty(content)){
			initContent();
			if(TextUtils.isEmpty(url)){
				webview.loadDataWithBaseURL(null, content, "text/html","utf-8", null);
			}else{
				webview.loadUrl(url);
			}
		}
		super.setUserVisibleHint(isVisibleToUser);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if(getUserVisibleHint() && getActivity()!=null && TextUtils.isEmpty(content)){
			initContent();
			if(TextUtils.isEmpty(url)){
				webview.loadDataWithBaseURL(null, content, "text/html","utf-8", null);
			}else{
				webview.loadUrl(url);
			}
		}
	}
	
//	public class CustomWebViewClient extends WebViewClient{
//		@Override
//		public boolean shouldOverrideUrlLoading(WebView view,String url) {
//			view.loadUrl(url);
//			return true;
//		}
//	}
	
	public class JavaScriptObject {  
	    Context mContxt;  
	  
	    public JavaScriptObject(Context mContxt) {  
	        this.mContxt = mContxt;  
	    }  
	    
	    /**
	     * @author caibing.zhang
	     * @createdate 2013-12-31 下午2:45:36
	     * @Description: 点击webview上的图片，传入该缩略图的大图Url
	     */
	    public void onImageClick(String imageURL) {  
//	    	Log.e("--Main--", "imageURL:"+imageURL);
//	    	Intent intent=new Intent(context, ImageBrowserActivity.class);
//	    	intent.putExtra(IntentConstants.IMAGE_URL, imageURL);
//	    	intent.putExtra(IntentConstants.IMAGE_LIST, new ImageUrlVo(urlList));
//	    	IntentUtil.intentForwardNetWork(context, intent);
	    }  
	} 
	
	/**
	 * 初始化内容
	 */
	public void initContent(){
//		Bundle bundle = getArguments();
//		content = bundle.getString(IntentConstants.WAP_CONTENT);
//		url = bundle.getString(IntentConstants.URL);
//		if(!TextUtils.isEmpty(content)){
//			//获取一共有多少图片URL
//			Pattern p_img = Pattern.compile("<\\s*img[^<]*src\\s*=\\s*\"([^<]+jpg{1})\"[^>]*>",Pattern.CASE_INSENSITIVE);
//			Matcher mImg = p_img.matcher(content);
//			urlList = new ArrayList<String>();
//			for(;mImg.find();urlList.add(mImg.group(1))){}
//			// 添加点击图片放大支持
//			content = content.replaceAll("(<img[^>]+src=\")(\\S+)\"","$1$2\" onClick=\"javascript:mWebViewImageListener.onImageClick('$2')\"");
//		}
//		
//		View shareView=view.findViewById(R.id.share_id);
//		if(getActivity() instanceof WapActivity && 
//				!TextUtils.isEmpty(((WapActivity)getActivity()).shareContent)){
//			shareView.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View v) {
//					((WapActivity)getActivity()).initSharePopWindow(view);
//				}
//			});
//		}else{
//			shareView.setVisibility(View.GONE);
//		}
	}
	
}
