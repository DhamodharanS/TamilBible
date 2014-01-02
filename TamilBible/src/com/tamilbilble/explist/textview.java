package com.tamilbilble.explist;

import java.io.IOException;


import java.io.InputStream;




import com.mayuonline.tamilandroidunicodeutil.TamilUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar;

public class textview extends Activity implements OnTouchListener,SeekBar.OnSeekBarChangeListener {
	
	Typeface tfBamini, tfTscii, tfTamil, tfTamilnew, tfBamini1;
    String strConBamini1, strConBamini, strConTscii, strBamini, strTscii,
    Tamil, Tamilnew, strTamil, strTamilnew;
	static String res="",filepaths="",chapname="";
	public static String[] filelist;
	public static int cpos=0;
	private GestureDetector gestureDetector;
	private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    
	public static int flag=0,size=0;
	TextView test,chapnam;
	SeekBar seekBar;
	Button btup,btdwn;
		
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textview);
        
        
        
        if(flag==1)
        {
        loadDataFromAsset();
        flag=0;
        }

        test =(TextView)findViewById(R.id.textView1);
        chapnam =(TextView)findViewById(R.id.textViewtitle);
        btup=(Button)findViewById(R.id.btnup);
        btdwn=(Button)findViewById(R.id.btndown);
        tfBamini = Typeface.createFromAsset(getAssets(), "fonts/Bamini.ttf");
		strConBamini = TamilUtil.convertToTamil(TamilUtil.BAMINI, res);
		test.setTypeface(tfBamini);
		test.setText(strConBamini);
		test.setTextSize(20);
		
		strTamil=TamilUtil.convertToTamil(TamilUtil.BAMINI, chapname);
		chapnam.setTypeface(tfBamini);
		chapnam.setText(strTamil);
		chapnam.setTextSize(20);
		
		//test.setScrollbarFadingEnabled(true);
		gestureDetector = new GestureDetector(new MyGestureDetector());
		test.setOnTouchListener(this);
		
		
		
		 test.setOnTouchListener(new View.OnTouchListener() {
	            public boolean onTouch(View v, MotionEvent event) {
	                if (gestureDetector.onTouchEvent(event)) {
	                	
	               
	                		
	                    return true;
	                   
	                }
	                return false;
	            }
	        });
        
		 if(cpos == 0)
			{
				btdwn.setVisibility(View.INVISIBLE);
				btup.setVisibility(View.VISIBLE);
				//Toast.makeText(getApplicationContext(), "No More Pages", Toast.LENGTH_LONG).show();
			}
		 else if(cpos>0)
		 {
			 	btdwn.setVisibility(View.VISIBLE);
				btup.setVisibility(View.VISIBLE);
		 }
		 
		
		 
		 btup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				// TODO Auto-generated method stub
				
				if(cpos == filelist.length)
				 {
					 	btdwn.setVisibility(View.VISIBLE);
						btup.setVisibility(View.INVISIBLE);
						Toast.makeText(getApplicationContext(), "No More Pages", Toast.LENGTH_LONG).show();
				 }
				
				
				 
				else if(cpos<filelist.length)
	 		    	{
					 	cpos=cpos+1;
		 		    	//String tests=filelist[cpos];
		 		    	
		 		    	if(cpos==filelist.length)
		 		    	{	
		 		    		btdwn.setVisibility(View.VISIBLE);
							btup.setVisibility(View.INVISIBLE);
							Toast.makeText(getApplicationContext(), "No More Pages", Toast.LENGTH_LONG).show();
		 		    		//cpos = cpos;
		 		    		
		 		    	}
		 		    	else
		 		    	{
		 		    		
		 		    		btdwn.setVisibility(View.VISIBLE);
		 		    		btup.setVisibility(View.VISIBLE);
		 		    	loadDataFromAssets();
		 		    	tfBamini = Typeface.createFromAsset(getAssets(), "fonts/Bamini.ttf");
		 				strConBamini = TamilUtil.convertToTamil(TamilUtil.BAMINI, res);
		 				test.setTypeface(tfBamini);
		 				test.setText(strConBamini);
		 		    	}
	 		    	}
				
				 else
				 {
					// btup.setVisibility(View.INVISIBLE);
					 Toast.makeText(getApplicationContext(), "No More Pages", Toast.LENGTH_LONG).show();
				 }
				 
			}
		});
		 
		 btdwn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				if(cpos == filelist.length)
				{

					btdwn.setVisibility(View.VISIBLE);
					btup.setVisibility(View.VISIBLE);
					cpos=cpos-2;
	 		    	loadDataFromAssets();
	 		    	tfBamini = Typeface.createFromAsset(getAssets(), "fonts/Bamini.ttf");
	 				strConBamini = TamilUtil.convertToTamil(TamilUtil.BAMINI, res);
	 				test.setTypeface(tfBamini);
	 				test.setText(strConBamini);
				}
				else if(cpos>0)
 		    	{
					 Toast.makeText(getApplicationContext(), String.valueOf(cpos), Toast.LENGTH_LONG).show();
					btdwn.setVisibility(View.VISIBLE);
					btup.setVisibility(View.VISIBLE);
					cpos=cpos-1;
	 		    	loadDataFromAssets();
	 		    	tfBamini = Typeface.createFromAsset(getAssets(), "fonts/Bamini.ttf");
	 				strConBamini = TamilUtil.convertToTamil(TamilUtil.BAMINI, res);
	 				test.setTypeface(tfBamini);
	 				test.setText(strConBamini);
	 			
 		    	}
				else if(cpos == 0)
				{
					btdwn.setVisibility(View.INVISIBLE);
					Toast.makeText(getApplicationContext(), "No More Pages", Toast.LENGTH_LONG).show();
				}
			
			}
		});
		 
		 
		 seekBar=(SeekBar)findViewById(R.id.seekBar1);
	     seekBar.setOnSeekBarChangeListener(this);
		 seekBar.setProgress(10);
		 
		
		 
		
        
        
        
	}
	
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent = new Intent(textview.this.getBaseContext(), MainActivity.class);
 		startActivity(intent);
		finish();
	}
	
	 class MyGestureDetector extends SimpleOnGestureListener {
	        @Override
	        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		        
	        	
		        try
		        {
	            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
	             
	            	return false;
	            }
	            
	            
	            // right to left swipe
	             else if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) 
	             {
	            	
	            	
	            	
	            	 if(cpos<filelist.length)
	 		    	{
	            		 
	            		 
	            		Intent intent = new Intent(textview.this.getBaseContext(), textview.class);
	             		startActivity(intent);
	      				textview.this.overridePendingTransition(
	      							
	  						R.anim.slide_in_right,
	  						R.anim.slide_out_left
	  					);
	      				
	 		    	cpos=cpos+1;
	 		    	String tests=filelist[cpos];
	 		    	
	 		    	loadDataFromAssets();
	 		    	
	 		    	
	 		    	}	 
	            	
	            	 //}
	            	
	            	
	            	
	    			// right to left swipe
	            		
	            } 
		        }
		        catch(Exception ex)
		        {
		        	
		        }
		        
	            if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
	            	
	            	try
	            	{
	            		
	            		
	            		if(cpos>0)
		 		    	{
	            			
	            			Intent intent = new Intent(textview.this.getBaseContext(), textview.class);
		             		 startActivity(intent);
		      				textview.this.overridePendingTransition(
		  						R.anim.slide_in_left, 
		  						R.anim.slide_out_right
		      				);
		 		    	cpos=cpos-1;
		 		    	//String test=filelist[cpos];
		 		    	loadDataFromAssets();
		 		    	
		 		    	}
		             	
	            	}
	            	catch(Exception ex)
	            	{
	            		
	            	}
	            		
	            }
	            return false;
	        }
	        
	        // It is necessary to return true from onDown for the onFling event to register
	        @Override
	        public boolean onDown(MotionEvent e) {
		        	return true;
	        }

	    }
	
	
	public void loadDataFromAsset() {
        // load text
        try {
        	String filepat=MainActivity.filepath;
        	Log.v("FILENAMES",filepat);
            // get input stream for text
            InputStream is = getAssets().open(filepat+".txt");
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
        	Log.e("FileError",ex.toString());
        	Toast.makeText(getApplicationContext(), "File Path Error", Toast.LENGTH_LONG).show();
            return;
        }
	}
 
        
        public void loadDataFromAssets() {
            // load text
            try {
            	
            	String filepat=filepaths+filelist[cpos];
            	Log.v("FILENAMES",filepat);
            	// get input stream for text
                InputStream is = getAssets().open(filepat+".txt");
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
                Log.v("FILENAMES RES",res);
            }
            catch (IOException ex) {
            	Log.e("FileError",ex.toString());
            	Toast.makeText(getApplicationContext(), "File Path Error", Toast.LENGTH_LONG).show();
                return;
            }
        
        
 
    }
        
        
        
        public void onSwipeRight() {
        }

        public void onSwipeLeft() {
        }

        public void onSwipeTop() {
        }

        public void onSwipeBottom() {
        }


		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {
			// TODO Auto-generated method stub
			
						return false;
		}


		  @Override
		    public void onProgressChanged(SeekBar seekBar, int progress,
		            boolean fromUser) {
		        //  Notify that the progress level has changed.
			  	
			  	
			  	if(progress>=20 && progress<=100)
			  	{
		        test.setTextSize(progress);
			  	}
			  	else
			  	{
			  		test.setTextSize(20);
			  	}
		        
		    }

		    @Override
		    public void onStartTrackingTouch(SeekBar seekBar) {
		        // Notify that the user has started a touch gesture.
		        //test.setText(test.getText()+"\n"+"SeekBar Touch Started");

		    }

		    @Override
		    public void onStopTrackingTouch(SeekBar seekBar) {
		        // Notify that the user has finished a touch gesture.
		        //test.setText(test.getText()+"\n"+"SeekBar Touch Stopped");

		    }
    }
        
        
        
        
        


