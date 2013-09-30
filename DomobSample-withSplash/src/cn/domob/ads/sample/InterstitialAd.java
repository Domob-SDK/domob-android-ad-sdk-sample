package cn.domob.ads.sample;

import cn.domob.android.ads.DomobAdManager.ErrorCode;
import cn.domob.android.ads.DomobInterstitialAd;
import cn.domob.android.ads.DomobInterstitialAdListener;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class InterstitialAd extends Activity {
	DomobInterstitialAd mInterstitialAd;
	Button mInterstitialBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.interstital);

		mInterstitialAd = new DomobInterstitialAd(this, DomobSampleActivity.PUBLISHER_ID,
				DomobSampleActivity.InterstitialPPID, DomobInterstitialAd.INTERSITIAL_SIZE_300X250);

		mInterstitialBtn = (Button) findViewById(R.id.interstitial);
		
		mInterstitialAd.setInterstitialAdListener(new DomobInterstitialAdListener() {
			@Override
			public void onInterstitialAdReady() {
				Log.i("DomobSDKDemo", "onAdReady");
			}

			@Override
			public void onLandingPageOpen() {
				Log.i("DomobSDKDemo", "onLandingPageOpen");
			}

			@Override
			public void onLandingPageClose() {
				Log.i("DomobSDKDemo", "onLandingPageClose");
			}

			@Override
			public void onInterstitialAdPresent() {
				Log.i("DomobSDKDemo", "onInterstitialAdPresent");
			}

			@Override
			public void onInterstitialAdDismiss() {
				// Request new ad when the previous interstitial ad was closed.
				mInterstitialAd.loadInterstitialAd();
				Log.i("DomobSDKDemo", "onInterstitialAdDismiss");
			}

			@Override
			public void onInterstitialAdFailed(ErrorCode arg0) {
				Log.i("DomobSDKDemo", "onInterstitialAdFailed");				
			}

			@Override
			public void onInterstitialAdLeaveApplication() {
				Log.i("DomobSDKDemo", "onInterstitialAdLeaveApplication");
				
			}

			@Override
			public void onInterstitialAdClicked(DomobInterstitialAd arg0) {
				Log.i("DomobSDKDemo", "onInterstitialAdClicked");
			}
		});
		
		mInterstitialAd.loadInterstitialAd();
		mInterstitialBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (mInterstitialAd.isInterstitialAdReady()){
					mInterstitialAd.showInterstitialAd(InterstitialAd.this);
				} else {
					Log.i("DomobSDKDemo", "Interstitial Ad is not ready");
					mInterstitialAd.loadInterstitialAd();
				}
			}
		});
	}
}
