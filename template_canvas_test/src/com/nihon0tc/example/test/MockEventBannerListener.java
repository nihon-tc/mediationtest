package com.nihon0tc.example.test;

import android.view.View;
import android.view.ViewGroup;

import com.google.ads.AdView;
import com.google.ads.mediation.customevent.CustomEventBannerListener;

public class MockEventBannerListener implements CustomEventBannerListener{

	private AdView adMob;
	public MockEventBannerListener(AdView adMob_){
		adMob = adMob_;
	}
	
	@Override
	public void onDismissScreen() {
		
	}

	@Override
	public void onFailedToReceiveAd() {
		throw new RuntimeException("onFailedToReceiveAd");
	}

	@Override
	public void onLeaveApplication()  {
	}

	@Override
	public void onPresentScreen() {
		
	}

	@Override
	public void onClick() {
		
	}

	@Override
	public void onReceivedAd(View arg0) {
		ViewGroup parent = (ViewGroup)arg0.getParent();
		if(parent !=null) parent.removeAllViews();
		adMob.addView(arg0);
	}

}
