package com.tamilbilble.explist;

import java.io.IOException;

import java.io.InputStream;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Xml.Encoding;
import android.webkit.WebView;

/*
 * This webview class are used for linking Senthil navigation server page 
 * The Route My Trips logo will perform actions on this.
 */
public class Webview extends Activity{
	
	public static int flag=0;
	public static int webflag=0;
	WebView web;
	public String res="";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
      //  loadDataFromAsset();
        
        web = (WebView)findViewById(R.id.web_view);
        
        
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setSupportZoom(true);
        String data = "<html><head><style>@font-face {font-family: 'tamilfont';src: url('file:///android_asset/Bamini.ttf');} h1 { font-family: 'Bamini'; } </style></head><body> <h1>தமிழன்!</h1> </body></html>"; 
      //  web.loadData("அதிகாரம்", "text/html", "UTF-8");
        web.loadUrl("file:///android_asset/index.html");
/*        web.loadDataWithBaseURL("file:///android_asset/",
        		data, "text/html", Encoding.UTF_8.toString(), null);
*/       /* String data = "<html><head><style>@font-face {font-family: 'tamilfont';src: url('file:///android_asset/Bamini.ttf');} h1 { font-family: 'Bamini'; } </style></head><body> <h1>தமிழன்!</h1> </body></html>"; 
      //  WebView wv = (WebView)findViewById(R.id.webview);
        web.loadDataWithBaseURL(null, data, "text/html", "UTF-8", null);*/

        
        	
       /* StringBuilder sb = new StringBuilder("<html>");
       
        
        sb.append(test);
        sb.append("<body>");
        sb.append(res);
        sb.append("</body></html>");
        web.loadData(sb.toString(), "text/html", "UTF-8");

        
        web.getSettings().setSupportZoom(true);
        web.getSettings().setBuiltInZoomControls(true);
*/        
              
        
        
	}
	
	public void loadDataFromAsset() {
        // load text
        try {
            // get input stream for text
            InputStream is = getAssets().open("01_01_1Adhigaram.txt");
            // check size
            int size = is.available();
            // create buffer for IO
            byte[] buffer = new byte[size];
            // get data to buffer
            is.read(buffer);
            // close stream
            is.close();
            // set result to TextView
            res=new String(buffer);
        }
        catch (IOException ex) {
            return;
        }
 
        
 
    }
	

}