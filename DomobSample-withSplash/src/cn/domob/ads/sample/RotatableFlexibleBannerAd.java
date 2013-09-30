package cn.domob.ads.sample;

import cn.domob.android.ads.DomobAdEventListener;
import cn.domob.android.ads.DomobAdManager.ErrorCode;
import cn.domob.android.ads.DomobAdView;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
/**
 * 当您选择 INLINE_SIZE_FLEXIBLE，并且应用界面会跟随屏幕旋转，请参考本类的实现方式。
 * When you select INLINE_SIZE_FLEXIBLE, and application interface will follow the screen rotation, please refer to the class implementation.
 */
public class RotatableFlexibleBannerAd extends Activity {
	RelativeLayout mAdContainer;
	DomobAdView mAdviewFlexibleAdView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.banner);

		mAdContainer = (RelativeLayout) findViewById(R.id.adcontainer);
		// Create ad view
		mAdviewFlexibleAdView = new DomobAdView(this, DomobSampleActivity.PUBLISHER_ID, DomobSampleActivity.FlexibleInlinePPID, DomobAdView.INLINE_SIZE_FLEXIBLE);
		mAdviewFlexibleAdView.setKeyword("game");
		mAdviewFlexibleAdView.setUserGender("male");
		mAdviewFlexibleAdView.setUserBirthdayStr("2000-08-08");
		mAdviewFlexibleAdView.setUserPostcode("123456");

		mAdviewFlexibleAdView.setAdEventListener(new DomobAdEventListener() {
						
			@Override
			public void onDomobAdReturned(DomobAdView adView) {
				Log.i("DomobSDKDemo", "onDomobAdReturned");				
			}

			@Override
			public void onDomobAdOverlayPresented(DomobAdView adView) {
				Log.i("DomobSDKDemo", "overlayPresented");
			}

			@Override
			public void onDomobAdOverlayDismissed(DomobAdView adView) {
				Log.i("DomobSDKDemo", "Overrided be dismissed");				
			}

			@Override
			public void onDomobAdClicked(DomobAdView arg0) {
				Log.i("DomobSDKDemo", "onDomobAdClicked");				
			}

			@Override
			public void onDomobAdFailed(DomobAdView arg0, ErrorCode arg1) {
				Log.i("DomobSDKDemo", "onDomobAdFailed");				
			}

			@Override
			public void onDomobLeaveApplication(DomobAdView arg0) {
				Log.i("DomobSDKDemo", "onDomobLeaveApplication");				
			}

			@Override
			public Context onDomobAdRequiresCurrentContext() {
				return RotatableFlexibleBannerAd.this;
			}
		});
		
		mAdContainer.addView(mAdviewFlexibleAdView);
	}
	
	@Override
	public void onConfigurationChanged(Configuration configuration) {
	    super.onConfigurationChanged(configuration);
		((DomobAdView) mAdContainer.getChildAt(0)).orientationChanged();
	}
}
