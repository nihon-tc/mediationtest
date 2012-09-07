package com.nihon0tc.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.nihon0tc.ad.AdManager;
import com.nihon0tc.util.LogUtil;
import com.nihon0tc.util.PrefUtil;

public class TemplateCanvasActivity extends Activity {

	private AdManager adm = null;
	private Animation anime;
	private ImageView target;
	private int count = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        LogUtil.trace("==start==");

        PrefUtil.init(this);
        count = PrefUtil.get_int("count", 0);
		showIcon();
        
        adm = new AdManager(this);
        anime = AnimationUtils.loadAnimation(this, R.anim.move);
        target = (ImageView)findViewById(R.id.ImageGroovy);
    	//デフォルト設定位置
		changeAdPlace(R.id.FrameLayoutMainK01);
    }

    private int id_ = 0;
	private void changeAdPlace(int id) {
		id_ = id;
		ViewGroup v = (ViewGroup) findViewById(R.id.FrameLayout_root);
		adm.nextAd(v, id);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(adm!=null)adm.end();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
			onDestroy();
			android.os.Process.killProcess(android.os.Process.myPid());
			return false;
        }
		return super.onKeyDown(keyCode, event);
	}

	
	public void rotate(View v) {
		switch(v.getId()){
			case R.id.ImageGroovy:
				target.setAnimation(anime);
				target.startAnimation(anime);
				if(id_==R.id.FrameLayoutMainK02)changeAdPlace(R.id.FrameLayoutMainK01);
				else							changeAdPlace(R.id.FrameLayoutMainK02);
				
				count++;
				showIcon();
				if(count>4)count = 0;
		        PrefUtil.put_int("count", count);
				break;
			default:
				break;
		}
	}

	private void showIcon() {
		switch(count){
			case 1:
				findViewById(R.id.ImageGrails).setVisibility(View.VISIBLE);
				findViewById(R.id.ImageGradle).setVisibility(View.INVISIBLE);
				findViewById(R.id.ImageGriffon).setVisibility(View.INVISIBLE);
				findViewById(R.id.ImageGaelyk).setVisibility(View.INVISIBLE);
				break;
			case 2:
				findViewById(R.id.ImageGrails).setVisibility(View.VISIBLE);
				findViewById(R.id.ImageGradle).setVisibility(View.VISIBLE);
				findViewById(R.id.ImageGriffon).setVisibility(View.INVISIBLE);
				findViewById(R.id.ImageGaelyk).setVisibility(View.INVISIBLE);
				break;
			case 3:
				findViewById(R.id.ImageGrails).setVisibility(View.VISIBLE);
				findViewById(R.id.ImageGradle).setVisibility(View.VISIBLE);
				findViewById(R.id.ImageGriffon).setVisibility(View.VISIBLE);
				findViewById(R.id.ImageGaelyk).setVisibility(View.INVISIBLE);
				break;
			case 4:
				findViewById(R.id.ImageGrails).setVisibility(View.VISIBLE);
				findViewById(R.id.ImageGradle).setVisibility(View.VISIBLE);
				findViewById(R.id.ImageGriffon).setVisibility(View.VISIBLE);
				findViewById(R.id.ImageGaelyk).setVisibility(View.VISIBLE);
				break;
			default:
				findViewById(R.id.ImageGrails).setVisibility(View.INVISIBLE);
				findViewById(R.id.ImageGradle).setVisibility(View.INVISIBLE);
				findViewById(R.id.ImageGriffon).setVisibility(View.INVISIBLE);
				findViewById(R.id.ImageGaelyk).setVisibility(View.INVISIBLE);
				break;
		}
	}
	
    
}
