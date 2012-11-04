package com.nihon0tc.ad;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.mediation.admob.AdMobAdapterExtras;
import com.nihon0tc.util.LogUtil;


public class AdManager {

	private static final String TAG = "AdManager";

	protected static Activity activity;
	private AdView adMob;
	public AdManager(Activity activity_) {
		activity = activity_;
		LogUtil.trace(TAG, "AdManager start:" + activity.getClass().getSimpleName());
		
		//AdMob初期化
		adMob_init();
	}
	
	public AdView adMob_init() {
		return adMob_init(activity.getResources().getString(R.string.adUnitId_mediation));
	}
	private AdRequest adRequest = null;
	public AdView adMob_init(String key) {
		LogUtil.trace(TAG,"adMob_init::key="+key);

		if(adMob!=null){
			adMob.stopLoading();//AdMob停止
			ViewGroup parent = (ViewGroup)adMob.getParent();
			if(parent !=null){
				int len_child = parent.getChildCount();
				for(int i=0;i < len_child;i++){
					View vx  = parent.getChildAt(i);
					if(vx.equals(adMob)){
						parent.removeView(vx);
						break;
					}
				}
			}
			adMob.destroy();
			adMob=null;
		}
		adMob = new AdView(activity, AdSize.BANNER, key);

		//adMob = (AdView)adParentView.findViewById(R.id.AdMob_medview);
		if(adRequest==null){
	    	adRequest = new AdRequest();
//	    	adRequest.addTestDevice(AdRequest.TEST_EMULATOR);               // Emulator
//	    	adRequest.addTestDevice("TEST_DEVICE_ID");                      // Test Android Device

	    	AdMobAdapterExtras extras = new AdMobAdapterExtras()
	        .addExtra("color_bg", "AAAAFF")
	        .addExtra("color_bg_top", "FFFFFF")
	        .addExtra("color_border", "FFFFFF")
	        .addExtra("color_link", "000080")
	        .addExtra("color_text", "808080")
	        .addExtra("color_url", "008000");
	    	adRequest.setNetworkExtras(extras);
		}
    	adMob.loadAd(adRequest);

    	adMob.setAdListener(new AdListener(){
			@Override
			public void onDismissScreen(com.google.ads.Ad arg0) {
                LogUtil.trace("AdMob", "[onDismissScreen]" + arg0);
			}
			@Override
			public void onFailedToReceiveAd(com.google.ads.Ad arg0,ErrorCode arg1) {
                LogUtil.trace("AdMob", "[onFailedToReceiveAd]" + arg1);
                //arg0.stopLoading();
                //arg0.loadAd(adRequest);
			}
			@Override
			public void onLeaveApplication(com.google.ads.Ad arg0) {
                LogUtil.trace("AdMob", "[onLeaveApplication]" + arg0);

			}
			@Override
			public void onPresentScreen(com.google.ads.Ad arg0) {
                LogUtil.trace("AdMob", "[onPresentScreen]" + arg0);
			}
			@Override
			public void onReceiveAd(com.google.ads.Ad arg0) {
                LogUtil.trace("AdMob", "[onReceiveAd]" + arg0);
			}
        });
    	return adMob;
	}
	
	private ViewGroup adView; //広告を表示するビュー
	/**
	 * 広告を表示する場所を変えます
	 * @param v  	IDが存在する場所のレイアウト
	 * @param id	広告を表示するレイアウトのID
	 */
	public void nextAd(ViewGroup v, int id) {
		try{
			if(adView != null && id == adView.getId()){
				return;
			}
			adMob.stopLoading();//AdMob停止

			//adMob自体の参照を消す
			ViewGroup parent = (ViewGroup)adMob.getParent();
			if(parent !=null) parent.removeAllViews();
			
			adView = (ViewGroup) v.findViewById(id); //広告を表示するビューを変更する
			adView.addView(adMob);
			
			adMob.loadAd(adRequest);//AdMob再起動
		}catch(Exception ex){
			LogUtil.error(TAG,"nextAd",ex);
		}
	}
	
	private void childAdStop(){
		CustomAd instance = CustomAd.getInstance();
		if(instance!=null)instance.stop();
	}
	private void childAdStart(){
		CustomAd instance = CustomAd.getInstance();
		if(instance!=null)instance.start();
	}

	public void end() {
		try {
			if(adMob!=null){
				adMob.stopLoading();//AdMob停止
				adMob.destroy();
				adMob=null;
			}
		} catch (Exception ex) {
			LogUtil.error(TAG,"end",ex);
		}
	}

	public void start() {
		try {
			if(adMob!=null && !adMob.isRefreshing()){
				childAdStart();//ぶら下がりAd再起動
				adMob.loadAd(adRequest);//AdMob再起動
			}
		} catch (Exception ex) {
			LogUtil.error(TAG,"start",ex);
		}
	}

	public void stop() {
		try {
			if(adMob!=null){
				adMob.stopLoading();//AdMob停止
				childAdStop();//ぶら下がりAd停止
			}
		} catch (Exception ex) {
			LogUtil.error(TAG,"stop",ex);
		}
	}
}