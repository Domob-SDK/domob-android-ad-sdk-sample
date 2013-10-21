package cn.domob.ads.sample;

import java.util.LinkedList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.domob.ads.sample.MyListView.OnRefreshListener;
import cn.domob.android.ads.DomobAdManager.ErrorCode;
import cn.domob.android.ads.DomobFeedsAdListener;
import cn.domob.android.ads.DomobFeedsAdView;

public class FeedsAd extends Activity {
	private Handler mHandler = new Handler(Looper.getMainLooper());
	private LinkedList<String> mDataLinkedList;
	private BaseAdapter mBaseAdapter;
	private DomobFeedsAdView mFeedsAdView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feeds);

		mFeedsAdView = new DomobFeedsAdView(this, DomobSampleActivity.PUBLISHER_ID, DomobSampleActivity.FeedsPPID);
		mFeedsAdView.loadFeedsAd();
		final LinearLayout parentLinearLayout = (LinearLayout) findViewById(R.id.adcontainer);
		parentLinearLayout.addView(mFeedsAdView, 0);
		mFeedsAdView.setFeedsAdListener(new DomobFeedsAdListener() {

			@Override
			public void onLandingPageOpen() {
				Log.i("DomobSDKDemo", "onLandingPageOpen");
			}

			@Override
			public void onLandingPageClose() {
				Log.i("DomobSDKDemo", "onLandingPageClose");
			}

			@Override
			public void onFeedsAdReady() {
				Log.i("DomobSDKDemo", "onFeedsAdReady");
			}

			@Override
			public void onFeedsAdPresent() {
				Log.i("DomobSDKDemo", "onFeedsAdPresent");
			}

			@Override
			public void onFeedsAdLeaveApplication() {
				Log.i("DomobSDKDemo", "onFeedsAdLeaveApplication");
			}

			@Override
			public void onFeedsAdFailed(ErrorCode code) {
				Log.i("DomobSDKDemo", "onFeedsAdFailed");
			}

			@Override
			public void onFeedsAdDismiss() {
				Log.i("DomobSDKDemo", "onFeedsAdDismiss");
				parentLinearLayout.invalidate();
				mFeedsAdView.loadFeedsAd();
			}

			@Override
			public void onFeedsAdClicked(DomobFeedsAdView feedsAdView) {
				Log.i("DomobSDKDemo", "onFeedsAdClicked");
			}
		});

		final MyListView listView = (MyListView) findViewById(R.id.listView);
		listView.setonRefreshListener(new OnRefreshListener() {
			public void onRefresh() {
				if (mFeedsAdView.isFeedsAdReady()) {
					mFeedsAdView.showFeedsAd(FeedsAd.this);
				} else {
					mFeedsAdView.loadFeedsAd();
				}
				mHandler.postDelayed(new Runnable() {

					@Override
					public void run() {
						listView.onRefreshComplete();

					}
				}, 2000);
			}
		});

		mDataLinkedList = new LinkedList<String>();
		for (int i = 0; i < 10; i++) {
			mDataLinkedList.add(String.valueOf(i));
		}
		mBaseAdapter = new BaseAdapter() {
			public View getView(int position, View convertView, ViewGroup parent) {
				convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item, null);
				TextView textView = (TextView) convertView.findViewById(R.id.textView_item);
				textView.setText(mDataLinkedList.get(position));
				return convertView;
			}

			public long getItemId(int position) {
				return position;
			}

			public Object getItem(int position) {
				return mDataLinkedList.get(position);
			}

			public int getCount() {
				return mDataLinkedList.size();
			}
		};
		listView.setAdapter(mBaseAdapter);
	}

	protected void onDestroy() {
		try {
			super.onDestroy();
		} catch (Exception $e) {
		}
	}
}