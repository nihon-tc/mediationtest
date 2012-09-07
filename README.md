before running)


copy AdManager/res/values/ad_key_mst.xml to template_canvas/res/values/ad_key.xml
edit ad_key.xml(Ad SDK Licence key)

ie)
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
		<!-- //mediba Ad(表示テスト用 http://medibaad.com/developer/support/android.html 参照) -->
		<string name="Auid">145757</string>
		
		<!--	//AmoAd -->
		<string name="AMoAd_Sid"></string>
		
		<!-- //adMob -->
		<string name="adUnitId_mediation"></string>

		<!-- //Nend(sampleのID(表示確認用)-->
		<string name="nendApiKey">c5cb8bc474345961c6e7a9778c947957ed8e1e4f</string>
		<string name="nendSpotId">3174</string>
	
</resources>
```




create template_canvas_test/res/values/ad_key_test.xml
(need set admob mediation key)

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
	<!-- //adMob -->
	<string-array name="mediation_array">
			<item></item><!-- Nend -->
			<item></item><!-- MedibaMasAd -->
	 		<item></item><!-- AMoAd -->
	 	</string-array>
</resources>
```

