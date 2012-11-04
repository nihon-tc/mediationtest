package com.nihon0tc.ad;import proguard.annotation.Keep;import proguard.annotation.KeepName;import jp.co.cyberagent.AMoAdView;import android.app.Activity;import android.view.LayoutInflater;import android.view.ViewGroup;import com.nihon0tc.ad.R;import com.google.ads.AdSize;import com.google.ads.mediation.MediationAdRequest;import com.google.ads.mediation.customevent.CustomEventBannerListener;import com.nihon0tc.util.LogUtil;@Keeppublic class AMoAd extends CustomAd {	private static ViewGroup adParentView;	private static AMoAdView adView = null;	@KeepName	@Override	public void requestBannerAd(CustomEventBannerListener listener,			Activity activity, String label, String serverParameter,			AdSize adSize, MediationAdRequest request,			Object customEventExtra) {		super.requestBannerAd(listener, activity, label, serverParameter, adSize,request,customEventExtra);		LogUtil.trace(TAG,String.format("(label,serverParameter,customEventExtra)=(%s,%s,%s)",label,serverParameter,customEventExtra));		if(adView!=null){			onRecivedAd(listener);	 	   	return;		}		String AMoAd_Sid ="";		if(serverParameter==null || "".equals(serverParameter.trim()) ){			AMoAd_Sid = activity.getResources().getString(R.string.AMoAd_Sid);		}		else{			AMoAd_Sid = serverParameter;		}		KeyInfo = AMoAd_Sid;    	try {    		LayoutInflater inflater = activity.getLayoutInflater();    		adParentView  = (ViewGroup)inflater.inflate(R.layout.ad_amoad, null);    		adView = (AMoAdView)adParentView.findViewById(R.id.amoAdView);    		adView.setSid(AMoAd_Sid);    		adView.requestFreshAd();    		sendStatus(true);		} catch (Exception e) {			LogUtil.error(TAG,"init",e);			listener.onFailedToReceiveAd();    		sendStatus(false);			return;		}		onRecivedAd(listener);   }	private void onRecivedAd(CustomEventBannerListener listener) {		try {			listener.onReceivedAd(adParentView);		   	LogUtil.trace(TAG,"onReceivedAd OK");    		sendStatus(true);		 } catch (Exception e) {			LogUtil.error(TAG,"onReceivedAd",e);			listener.onFailedToReceiveAd();    		sendStatus(false);		}	}		@KeepName	@Override	public void stop() {	   	LogUtil.trace(TAG,"support stop");		try {			if(adView!=null)adView.stopRotation();		} catch (Exception e) {			LogUtil.error(TAG,"stop",e);		}	}		@KeepName	@Override	public void start() {	   	LogUtil.trace(TAG,"support start");		try {			if(adView!=null)adView.startRotation();		} catch (Exception e) {			LogUtil.error(TAG,"start",e);		}	}}
