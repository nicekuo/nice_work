package com.nice.work.core;

import android.os.Bundle;

import com.core.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;


/**
 * 
 * @author caibing.zhang
 * @createdate 2013-12-31 上午11:25:32
 * @Description: WAP页面
 */
@EActivity(R.layout.frame)
public class WapActivity extends AbstractActivity {
	@Extra
	String title;    //文章的标题
	@Extra
	String content;  //文章的内容
	@Extra
	String url;      //文章的地址

	@AfterViews
	void init()
	{
		setTitleName(title);
//		super.mainBody.setBackgroundResource(R.color.text_white);

		//url=getIntent().getStringExtra(CoreConstant.URL);

		WapFragment fragment = new WapFragment();
		fragment.url=url;
		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,fragment).commit();
	}

	@Override
	protected void onClickBack() {
		finish();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
//		outState.putString(IntentConstants.TITLE, title);
//		outState.putString(IntentConstants.WAP_CONTENT, content);
//		outState.putString(IntentConstants.URL, url);
//		outState.putString(IntentConstants.SHARE_CONTENT, shareContent);
	}
}