package cn.domob.ads.sample;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import cn.domob.android.ads.AdEventListener;
import cn.domob.android.ads.AdManager.ErrorCode;
import cn.domob.android.ads.AdView;

/**
 * 当您选择的广告尺寸是INLINE_SIZE_FLEXIBLE(这是默认广告尺寸)，应用界面会跟随屏幕旋转，并且旋转后不重绘页面，请参考本类的实现方式。
 * When you select INLINE_SIZE_FLEXIBLE(This is the default ad size),
 * application interface will follow the screen rotation and do not call
 * onCreate, please refer to the class implementation.
 */
public class RotatableBannerAdActivity extends Activity {
	RelativeLayout mAdContainer;
	AdView mAdview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.banner);

		mAdContainer = (RelativeLayout) findViewById(R.id.adcontainer);
		// Create ad view
		mAdview = new AdView(this, DomobSampleActivity.PUBLISHER_ID, DomobSampleActivity.InlinePPID);
		mAdview.setKeyword("game");
		mAdview.setUserGender("male");
		mAdview.setUserBirthdayStr("2000-08-08");
		mAdview.setUserPostcode("123456");
		mAdview.setAdEventListener(new AdEventListener() {
			@Override
			public void onAdOverlayPresented(AdView adView) {
				Log.i("DomobSDKDemo", "overlayPresented");
			}

			@Override
			public void onAdOverlayDismissed(AdView adView) {
				Log.i("SDKDemo", "Overrided be dismissed");
			}

			@Override
			public void onAdClicked(AdView arg0) {
				Log.i("SDKDemo", "onAdClicked");
			}

			@Override
			public void onAdFailed(AdView arg0, ErrorCode arg1) {
				Log.i("DomobSDKDemo", "onDomobAdFailed");
			}

			@Override
			public void onLeaveApplication(AdView arg0) {
				Log.i("DomobSDKDemo", "onDomobLeaveApplication");
			}

			@Override
			public Context onAdRequiresCurrentContext() {
				return RotatableBannerAdActivity.this;
			}

			@Override
			public void onEventAdReturned(AdView arg0) {
				Log.i("DomobSDKDemo", "onDomobAdReturned");
			}
		});
		mAdContainer.addView(mAdview);
	}

	@Override
	public void onConfigurationChanged(Configuration configuration) {
		super.onConfigurationChanged(configuration);
		mAdview.orientationChanged();
	}
}
