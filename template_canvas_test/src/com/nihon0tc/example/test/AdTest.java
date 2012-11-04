package com.nihon0tc.example.test;

import java.lang.reflect.Field;

import android.app.Activity;
import android.test.SingleLaunchActivityTestCase;
import android.view.View;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.customevent.CustomEventBannerListener;
import com.nihon0tc.ad.AMoAd;
import com.nihon0tc.ad.AdManager;
import com.nihon0tc.ad.MedibaMasAd;
import com.nihon0tc.ad.Nend;
import com.nihon0tc.example.TemplateCanvasActivity;
import com.nihon0tc.util.LogUtil;

public class AdTest extends SingleLaunchActivityTestCase<TemplateCanvasActivity> {

	private final String TAG = "AdTest";

	public AdTest(String pkg, Class<TemplateCanvasActivity> activityClass) {
		super(pkg, activityClass);
	}
	public AdTest() {
		super("com.nihon0tc.example", TemplateCanvasActivity.class);
	}


	private Activity activity;
	private AdManager adm;
	private AdView adMob;
	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();

	    
		activity.runOnUiThread( new Runnable() {
            @Override
            public void run() {
        	    adm = new AdManager(activity);
    			adMob = adm.adMob_init();
    			AdRequest a = new AdRequest();
    			adMob.loadAd(a);
            }
		});
		getInstrumentation().waitForIdleSync();

	}

	public void tearDown() {
		try {
			super.tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(adm!=null)adm.end();
		this.getActivity().finish();
	}



	public void testAMoAd(){
		String func_name = "testAMoAd";
		LogUtil.info(TAG, "-------" + func_name + "---------");
		AMoAd ad = new AMoAd();

		CustomEventBannerListener listener = new MockEventBannerListener(adMob);
		AdRequest a = new AdRequest();
		int cnt = adMob.getChildCount();
		MediationAdRequest request = new MediationAdRequest(a,activity,false);
		ad.requestBannerAd(
				listener,
	            activity,
	            "AMoAd",
	            "",
	            AdSize.BANNER,
	            request,
	            null);

		View adView =null;
		try {
		  	Field f = AMoAd.class.getDeclaredField("adView");
			f.setAccessible(true);
			adView = (View)f.get(ad);
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
		
		assertEquals(cnt+1,adMob.getChildCount());
		assertNotNull(adView);
	}


	public void testMedibaMasAd(){
		String func_name = "testMedibaMasAd";
		LogUtil.info(TAG, "-------" + func_name + "---------");
		MedibaMasAd ad = new MedibaMasAd();

		CustomEventBannerListener listener = new MockEventBannerListener(adMob);
		AdRequest a = new AdRequest();
		int cnt = adMob.getChildCount();
		MediationAdRequest request = new MediationAdRequest(a,activity,false);
		ad.requestBannerAd(
				listener,
	            activity,
	            "MedibaMasAd",
	            "",
	            AdSize.BANNER,
	            request,
	            null);

		View adView =null;
		try {
		  	Field f = MedibaMasAd.class.getDeclaredField("adView");
			f.setAccessible(true);
			adView = (View)f.get(ad);
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
		
		assertEquals(cnt+1,adMob.getChildCount());
		assertNotNull(adView);
	}


	public void testNend(){
		String func_name = "testNend";
		LogUtil.info(TAG, "-------" + func_name + "---------");
		Nend ad = new Nend();

		CustomEventBannerListener listener = new MockEventBannerListener(adMob);
		AdRequest a = new AdRequest();
		int cnt = adMob.getChildCount();
		MediationAdRequest request = new MediationAdRequest(a,activity,false);
		ad.requestBannerAd(
				listener,
	            activity,
	            "Nend",
	            "",
	            AdSize.BANNER,
	            request,
	            null);

		View adView =null;
		try {
		  	Field f = Nend.class.getDeclaredField("adView");
			f.setAccessible(true);
			adView = (View)f.get(ad);
		}
		catch(Exception ex){
			throw new RuntimeException(ex);
		}
		
		assertEquals(cnt+1,adMob.getChildCount());
		assertNotNull(adView);
	}

}
