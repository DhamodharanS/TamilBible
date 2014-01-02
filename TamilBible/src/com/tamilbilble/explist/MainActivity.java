package com.tamilbilble.explist;

import java.io.IOException;



import com.mayuonline.tamilandroidunicodeutil.TamilUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.test.IsolatedContext;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

 

public class MainActivity extends Activity {

       ExpandableListView explvlist;
       ExpandableListView explvlist1;
       Typeface tfBamini, tfTscii, tfTamil, tfTamilnew, tfBamini1;
       String strConBamini1, strConBamini, strConTscii, strBamini, strTscii,
       Tamil, Tamilnew, strTamil, strTamilnew,strBaminis;
       String strlist="",strlist2="",strlist3="",strlist4="";
   		String[] list1;
   		int gpos,ggpos,ccpos;
   		/** Called when the activity is first created. */
   		Cursor c=null;
   		String chapname="",bookname="";
   		TextView tv;
   		DatabaseHelper myDbHelper;
   		String[] adname,filename;
   		int file1,file2=49,file3,file4=47;
   		public static String filepath="",file="";
   		
       @Override

       public void onCreate(Bundle savedInstanceState) {

              super.onCreate(savedInstanceState);

              setContentView(R.layout.main);
            //  tv = new TextView(MainActivity.this);
              filepath="";
              
              
              
              int Measuredwidth = 0;
          	  int Measuredheight = 0;
          	  Point size = new Point();
          	  WindowManager w = getWindowManager();

          	    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2){
          	          w.getDefaultDisplay().getSize(size);

          	          Measuredwidth = size.x;
          	          Measuredheight = size.y; 
          	        }else{
          	          Display d = w.getDefaultDisplay(); 
          	          Measuredwidth = d.getWidth(); 
          	          Measuredheight = d.getHeight(); 
          	        }
          	    
          	  Log.d("wh",String.valueOf(Measuredwidth)+","+String.valueOf(Measuredheight));
              
              final float scale = getApplicationContext().getResources().getDisplayMetrics().density;
            Log.d("SCALE",String.valueOf(scale));
             myDbHelper = new DatabaseHelper(MainActivity.this);
		         try {
		  
		         	myDbHelper.createDataBase();
		  
		         } catch (IOException ioe) {
		  
		  		throw new Error("Unable to create database");
		  
		  	}
		  
		  	try {
		  
		  		myDbHelper.openDataBase();
		  
		  	}catch(SQLException sqle){
		  
		  		throw sqle;
		  
		  	}
		  	              
		  	

		  	strBamini = "பொது மொழிபெயர்ப்பு(பழைய ஏற்பாடு)";
      		strTscii = "பொது மொழிபெயர்ப்பு(புதிய ஏற்பாடு)";
      		Tamil = "தமிழ் கத்தோலிக்க(பழைய ஏற்பாடு)";
      		Tamilnew = "தமிழ் கத்தோலிக்க(புதிய ஏற்பாடு)";
 
      		tfBamini = Typeface.createFromAsset(getAssets(), "fonts/Bamini.ttf");
    		tfTscii = Typeface.createFromAsset(getAssets(),
    				"fonts/SaiVrishintscii.ttf");
    		tfTamil = Typeface.createFromAsset(getAssets(), "fonts/Bamini.ttf");
    		tfTamilnew = Typeface.createFromAsset(getAssets(), "fonts/Bamini.ttf");

    		strBaminis = TamilUtil.convertToTamil(TamilUtil.BAMINI, strBamini);
    		strConTscii = TamilUtil.convertToTamil(TamilUtil.TSCII, strTscii);
    		strTamil = TamilUtil.convertToTamil(TamilUtil.BAMINI, Tamil);
    		strTamilnew = TamilUtil.convertToTamil(TamilUtil.BAMINI, Tamilnew);
    		
    		
    		
    		strlist = "தொடக்க நூல்,விடுதலைப் பயணம்,லேவியர்,எண்ணிக்கை,இணைச்சட்டம்,யோசுவா,நீதித் தலைவர்கள்,ரூத்து,1 சாமுவேல்,2 சாமுவேல்,1 அரசர்கள் ,2 அரசர்கள் ,1 குறிப்பேடு,2 குறிப்பேடு,எஸ்ரா,நெகேமியா,எஸ்தர்,யோபு,திருப்பாடல்கள்,நீதிமொழிகள்,சபை உரையாளர்,இனிமைமிகு பாடல்,எசாயா,எரேமியா,புலம்பல்,எசேக்கியேல்,தானியேல,ஒசேயா,யோவேல்,ஆமோஸ்,ஒபதியா,யோனா,மீக்கா,நாகூம்,அபக்கூக்கு,செப்பனியா,ஆகாய்,செக்கரியா,மலாக்கி,தோபித்து,யூதித்து,எஸ்தர் (கிரேக்கம்),சாலமோனின் ஞானம்,சீராக்கின் ஞானம்,பாரூக்கு,தானியேல் இணைப்பு,1 மக்கபேயர்,2 மக்கபேயர்";
    		strlist2="மத்தேயு,மாற்கு,லூக்கா,யோவான்,திருத்தூதர் பணிகள்,உரோமையர்,1 கொரிந்தியர்,2 கொரிந்தியர்,கலாத்தியர்,எபேசியர்,பிலிப்பியர்,கொலோசையர்,1 தெசலோனிக்கர்,2 தெசலோனிக்கர்,1 திமொத்தேயு,2 திமொத்தேயு,தீத்து,பிலமோன்,எபிரேயர்,யாக்கோபு,1 பேதுரு,2 பேதுரு,1 யோவான,2 யோவான்,3 யோவான்,யூதா,திருவெளிப்பாடு";
    		strlist3="ஆதி ஆகமம்,யாத்திராகமம்,லேவியராகமம்,எண்ணாகமம்,உப ஆகமம்,யோசுவா ஆகமம்,நீதிபதிகள் ஆகமம்,ரூத் ஆகமம்,1 சாமுவேல் ஆகமம்,2 சாமுவேல் ஆகமம்,1 அரசர் ஆகமம்,2 அரசர் ஆகமம்,1 நாள் ஆகமம்,2 நாள் ஆகமம்,எஸ்ரா ஆகமம்,நெகேமியா ஆகமம்,தொபியாசு ஆகமம்,யூதித் ஆகமம்,எஸ்தர் ஆகமம்,யோபு ஆகமம்,சங்கீதங்கள்,பழமொழி ஆகமம்,சங்கத்திருவுரை ஆகமம்,உன்னத சங்கீதம்,ஞான ஆகமம்,சீராக் ஆகமம்,இசையாஸ் ஆகமம்,எரேமியாஸ் ஆகமம்,புலம்பல் ஆகமம்,பாரூக் ஆகமம்,எசேக்கியேல் ஆகமம்,தானியேல் ஆகமம்,ஓசே ஆகமம்,ஓசே ஆகமம்,ஆமோஸ் ஆகமம்,அப்தியாஸ் ஆகமம்,யோனாஸ் ஆகமம்,மிக்கேயாஸ் ஆகமம்,நாகும் ஆகமம்,அபாக்கூக் ஆகமம்,செப்போனியாஸ் ஆகமம்,ஆகாய் ஆகமம்,சக்கரியாஸ் ஆகமம்,மலாக்கியா ஆகமம்,1 மக்கபே ஆகமம்,2 மக்கபே ஆகமம்";
    		strlist4="மத்தேயு,மாற்கு,லூக்காஸ்,அருளப்பர்,அப்போஸ்தலர் பணி,உரோமையர்,1 கொரிந்தியர்,2 கொரிந்தியர்,கலாத்தியர்,எபேசியர்,பிலிப்பியர்,கொலோசேயர்,1 தெசலோனிக்கேயர்,2 தெசலோனிக்கேயர்,1 திமொத்தேயு,2 திமொத்தேயு,தீத்து,பிலமோன்,எபிரேயர்,யாகப்பர்,1 இராயப்பர்,2 இராயப்பர்,1 அருளப்பர்,2 அருளப்பர்,3 அருளப்பர்,யூதா,திருவெளிப்பாடு";
    		
    		//list1=strlist.split(",");
    		
      	    
      	  
    		
    		explvlist = (ExpandableListView) findViewById(R.id.ParentLevel);
            explvlist.setAdapter(new ParentLevel());
           
          
            
            
          //For only one child list should be expaned
            explvlist.setOnGroupExpandListener(new OnGroupExpandListener() {
				
            	 int previousGroup = -1;

                 @Override
                 public void onGroupExpand(int groupPosition) {
                     if(groupPosition != previousGroup)
                         explvlist.collapseGroup(previousGroup);
                     previousGroup = groupPosition;
                 }
			});
            explvlist.setOnGroupClickListener(new OnGroupClickListener()
            {
               
                @Override
                public boolean onGroupClick(ExpandableListView arg0, View arg1, int arg2, long arg3)
                {
                	
                	ggpos=arg2;
                	
                	
                	
                	if(arg2==0)
                	{
                		chapname="பொது மொழிபெயர்ப்பு(பழைய ஏற்பாடு)";
                	}
                	else if(arg2==1)
                	{
                		chapname="பொது மொழிபெயர்ப்பு(புதிய ஏற்பாடு)";
                	}
                	else if(arg2==2)
                	{
                		chapname="தமிழ் கத்தோலிக்க(பழைய ஏற்பாடு)";
                	}
                	else if(arg2==3)
                	{
                		chapname="தமிழ் கத்தோலிக்க(புதிய ஏற்பாடு)";
                	}
                	Log.v("CHAPNAME", chapname);
                	
                    return false;
                }
            });
          
            
            

       }

 
@Override
protected void onDestroy() {
	// TODO Auto-generated method stub
	super.onDestroy();
	myDbHelper.close();
}
       
       public class ParentLevel extends BaseExpandableListAdapter {

 
    	  
    	   

              @Override

              public Object getChild(int arg0, int arg1) {

            	  
                     return arg1;

              }

 

              @Override

              public long getChildId(int groupPosition, int childPosition) {

                     return childPosition;

              }

 

              @Override

              public View getChildView(int groupPosition, int childPosition,

                           boolean isLastChild, View convertView, ViewGroup parent) {
            	  			gpos=groupPosition;
            	  
            	  			
                     final CustExpListview SecondLevelexplv = new CustExpListview(MainActivity.this);

                     SecondLevelexplv.setAdapter(new SecondLevelAdapter());

                     SecondLevelexplv.setGroupIndicator(null);
                     
                     //For only one child list should be expaned
                     
                     SecondLevelexplv.setOnGroupExpandListener(new OnGroupExpandListener() {
                         int previousGroup = -1;

                         @Override
                         public void onGroupExpand(int groupPosition) {
                             if(groupPosition != previousGroup)
                                 SecondLevelexplv.collapseGroup(previousGroup);
                             previousGroup = groupPosition;
                         }
                     });
                     
                     
                     SecondLevelexplv.setOnChildClickListener(new OnChildClickListener() {
						
						@Override
						public boolean onChildClick(ExpandableListView parent, View v,
								int groupPosition, int childPosition, long id) {
							// TODO Auto-generated method stub
						

			            	  if(ggpos==0)
			            	  {
			            		  String fname=filename[childPosition];
			            	  filepath="01 DONE/"+file+"/"+fname;
			            	  textview.filepaths="01 DONE/"+file+"/";
			            	 // Toast.makeText(getApplicationContext(), filepath, Toast.LENGTH_LONG).show();
			            	  Log.v("FILENAME",filepath);
			            	  
			            	  }
			            	  else if(ggpos==1)
			            	  {
			            		  String fname=filename[childPosition];
			                	  filepath="02 DONE/"+file+"/"+fname;
			                	  textview.filepaths="02 DONE/"+file+"/";
			                	//  Toast.makeText(getApplicationContext(), filepath, Toast.LENGTH_LONG).show();
			                	  Log.v("FILENAME",filepath);
			            	  }
			            	  else if(ggpos==2)
			            	  {
			            		  String fname=filename[childPosition];
			                	  filepath="03 DONE/"+file+"/"+fname;
			                	  textview.filepaths="03 DONE/"+file+"/";
			                	//  Toast.makeText(getApplicationContext(), filepath, Toast.LENGTH_LONG).show();
			                	  Log.v("FILENAME",filepath);
			            		  
			            	  }
			            	  else if(ggpos==3)
			            	  {
			            		  String fname=filename[childPosition];
			                	  filepath="04 DONE/"+file+"/"+fname;
			                	  textview.filepaths="04 DONE/"+file+"/";
			                	  //Toast.makeText(getApplicationContext(), filepath, Toast.LENGTH_LONG).show();
			                	  Log.v("FILENAME",filepath);
			            		  
			            	  }
			            	  textview.chapname=list1[groupPosition];
			            	  textview.filelist=new String[filename.length];
			            	  textview.filelist=filename;
			            	  textview.cpos=childPosition;
			            	  textview.flag=1;
			            	
			            	  Intent in = new Intent(MainActivity.this,textview.class);
			            	  startActivity(in);
			                  
							return false;
						}
					});
                     
                     SecondLevelexplv.setOnGroupClickListener(new OnGroupClickListener()
                     {
                        
                         @Override
                         public boolean onGroupClick(ExpandableListView arg0, View arg1, int arg2, long arg3)
                         {
                        	
                     		
                        	 if(ggpos==0)
                        	 {
                        		 if(arg2<9)
                        		 {
                        			 file="0"+String.valueOf(arg2+1);
                        			// Toast.makeText(getApplicationContext(), test, Toast.LENGTH_LONG).show();
                        		 //ccpos=Integer.parseInt(test);
                        		 }
                        		 else
                        		 {
                        			 file=String.valueOf(arg2+1);
                        			 //ccpos=arg2+1;
                        		 }
                        	 }
                        	 else if(ggpos==1)
                        	 {
                        		 
                        		ccpos= file2+arg2;
                        		file=String.valueOf(ccpos);
                        	 }
                        	 else if(ggpos==2)
                        	 {
                        		 if(arg2<9)
                        		 {
                        			 file="0"+String.valueOf(arg2+1);
                        		 
                        		 }
                        		 else
                        		 {
                        			 file=String.valueOf(arg2+1);
                        		 }
                        	 }
                        	 else if(ggpos==3)
                        	 {
                        		ccpos= file4+arg2;
                         		file=String.valueOf(ccpos);
                        	 }
                        	 
                        	
                        	 //bookname= TamilUtil.convertToTamil(TamilUtil.BAMINI,((TextView)arg1).getText().toString());
                        	 bookname=list1[arg2];
                         	
                         	String sql = "select * from ExpList where BOOK_NAME='"+bookname+"' and CHAPTER_NAME='"+chapname+"'";  //;+ " and CHAPTER_NAME= "+chapname;
                         	Log.v("LOGS QUERY", sql);
                         	c=myDbHelper.select(sql);
                	      
                	        if(c.getCount()>0)
                	        {
                	        c.moveToFirst();
                	        adname=new String[c.getCount()];
                	        filename=new String[c.getCount()];
                	        for(int i=0;i<c.getCount();i++)
                	        {
                	        adname[i]=String.valueOf(c.getString(2));
                	        filename[i]=String.valueOf(c.getString(3));
                	        c.moveToNext();
                	        }
                	        
                	        }
                	       
                	        c.close();
                            return false;
                         }
                     });

                     return SecondLevelexplv;

              }

 

              @Override

              public int getChildrenCount(int groupPosition) {

                     return 1;

              }

 

              @Override

              public Object getGroup(int groupPosition) {

            	  
                     return groupPosition;

              }

 

              @Override

              public int getGroupCount() {

                     return 4;

              }

 

              @Override

              public long getGroupId(int groupPosition) {
            	  
            	 
            	  
                     return groupPosition;

              }

 

              @Override

              public View getGroupView(int groupPosition, boolean isExpanded,

                           View convertView, ViewGroup parent) {

                 TextView title = new TextView(getApplicationContext());
                 
                 
                 int Measuredwidth = 0;
             	  int Measuredheight = 0;
             	  Point size = new Point();
             	  WindowManager w = getWindowManager();

             	    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2){
             	          w.getDefaultDisplay().getSize(size);

             	          Measuredwidth = size.x;
             	          Measuredheight = size.y; 
             	        }else{
             	          Display d = w.getDefaultDisplay(); 
             	          Measuredwidth = d.getWidth(); 
             	          Measuredheight = d.getHeight(); 
             	        }
             	    
                 
                 
               
      			if (groupPosition == 0) {

      				title.setTypeface(tfBamini);
      				title.setText(strBaminis);
      				//tv.setText("Test1");
      				title.setTextColor(Color.WHITE);
      				title.setPadding(10, 7, 7, 7);
      				
      				if(Measuredwidth>=800 && Measuredheight <= 1280)
      				{
      					title.setTextSize(45);
      				}
      				else if(Measuredwidth>=600 && Measuredheight <= 1024)
      				{
      					Toast.makeText(getApplicationContext(), "7 inch", Toast.LENGTH_LONG).show();
      					title.setTextSize(40);
      				}
      				else
      				{
      					title.setTextSize(20);
      				}
      				
      			}
      			if (groupPosition == 1) {
      				title.setTypeface(tfTscii);
      				title.setText(strConTscii);
      				//tv.setText("Test1");
      				title.setTextColor(Color.WHITE);
      				title.setPadding(10, 7, 7, 7);
      				
      				if(Measuredwidth>=800 && Measuredheight <= 1280)
      				{
      					title.setTextSize(45);
      				}
      				else if(Measuredwidth>=600 && Measuredheight <= 1024)
      				{
      					Toast.makeText(getApplicationContext(), "7 inch", Toast.LENGTH_LONG).show();
      					title.setTextSize(40);
      				}
      				else
      				{
      					title.setTextSize(20);
      				}
      			}
      			if (groupPosition == 2) {
      				title.setTypeface(tfTamil);
      				title.setText(strTamil);
      				//tv.setText("Test1");
      				title.setTextColor(Color.WHITE);
      				title.setPadding(10, 7, 7, 7);
      				if(Measuredwidth>=800 && Measuredheight <= 1280)
      				{
      					title.setTextSize(45);
      				}
      				else if(Measuredwidth>=600 && Measuredheight <= 1024)
      				{
      					Toast.makeText(getApplicationContext(), "7 inch", Toast.LENGTH_LONG).show();
      					title.setTextSize(40);
      				}
      				else
      				{
      					title.setTextSize(20);
      				}
      			}
      			if (groupPosition == 3) {
      				title.setTypeface(tfTamilnew);
      				title.setText(strTamilnew);
      				//tv.setText("Test1");
      				title.setTextColor(Color.WHITE);
      				title.setPadding(10, 7, 7, 7);
      				if(Measuredwidth>=800 && Measuredheight <= 1280)
      				{
      					title.setTextSize(45);
      				}
      				else if(Measuredwidth>=600 && Measuredheight <= 1024)
      				{
      					Toast.makeText(getApplicationContext(), "7 inch", Toast.LENGTH_LONG).show();
      					title.setTextSize(40);
      				}
      				else
      				{
      					title.setTextSize(20);
      				}
      			}

      			return title;

              }

 

              @Override

              public boolean hasStableIds() {

                     return true;

              }

 

              @Override

              public boolean isChildSelectable(int groupPosition, int childPosition) {

                     return true;

              }
              
              
              
              
              
              

       }

 

       public class CustExpListview extends ExpandableListView {

 

              int intGroupPosition, intChildPosition, intGroupid;

 

              public CustExpListview(Context context) {

                     super(context);

              }

 

              protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

            	  
            	 /* int Measuredwidth = 0;
            	  int Measuredheight = 0;
            	  Point size = new Point();
            	  WindowManager w = getWindowManager();

            	    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2){
            	          w.getDefaultDisplay().getSize(size);

            	          Measuredwidth = size.x;
            	          Measuredheight = size.y; 
            	        }else{
            	          Display d = w.getDefaultDisplay(); 
            	          Measuredwidth = d.getWidth(); 
            	          Measuredheight = d.getHeight(); 
            	        }*/
                     widthMeasureSpec = MeasureSpec.makeMeasureSpec(5000,

                                  MeasureSpec.AT_MOST);

                     heightMeasureSpec = MeasureSpec.makeMeasureSpec(5000,

                                  MeasureSpec.AT_MOST);

                     super.onMeasure(widthMeasureSpec, heightMeasureSpec);

              }

       }

 

       public class SecondLevelAdapter extends BaseExpandableListAdapter {

 

              @Override

              public Object getChild(int groupPosition, int childPosition) {

                     return childPosition;

              }

 

              @Override

              public long getChildId(int groupPosition, int childPosition) {

                     return childPosition;

              }

 

              @Override

              public View getChildView(int groupPosition, int childPosition,

                           boolean isLastChild, View convertView, ViewGroup parent) {

            	  
            	  
            	  
            	  	
            	  Log.d("ERROR",adname[childPosition]);
            	  
            	 // childPosition = 0;
                     TextView tv = new TextView(MainActivity.this);

                     tv.setTypeface(tfBamini);
                     
                     strConBamini = TamilUtil.convertToTamil(TamilUtil.BAMINI, adname[childPosition]);
       				 tv.setText(strConBamini);
       				 
       				 Log.d("ERROR1",tv.getText().toString());
                    // tv.setText(list1[groupPosition]);

       				 tv.setTextColor(Color.WHITE);

                    
                     
                     int Measuredwidth = 0;
               	  int Measuredheight = 0;
               	  Point size = new Point();
               	  WindowManager w = getWindowManager();

               	    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2){
               	          w.getDefaultDisplay().getSize(size);

               	          Measuredwidth = size.x;
               	          Measuredheight = size.y; 
               	        }else{
               	          Display d = w.getDefaultDisplay(); 
               	          Measuredwidth = d.getWidth(); 
               	          Measuredheight = d.getHeight(); 
               	        }
               	    
               	    
               	   
              	    if(Measuredwidth>=800 && Measuredheight<=1280)
              	    {
              	     tv.setTextSize(45);
              	   tv.setPadding(90, 7, 7, 7);
              	    }
              	    
              	    else if(Measuredwidth>=600 && Measuredheight<=1280)
           	    {
           	     tv.setTextSize(40);
           	     tv.setPadding(70, 7, 7, 7);
           	    }
               	    else
               	    {
               	    	tv.setTextSize(20);
               	     tv.setPadding(40, 7, 7, 7);
               	    }
               	    

              	    tv.setWidth(1280);
              	    
                    

                   /*  
                     tv.setLayoutParams(new ListView.LayoutParams(
         
                    		 LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));*/
/*
 * 
                     if(adname[childPosition]==String.valueOf(adname.length))
                     {
                    	 childPosition = 0;
                     }
                     
                     else
                     {
                    	 Log.d("ERROR",adname[childPosition]);
                     }
                 	*/
                     return tv;
            	

              }

 

              @Override

              public int getChildrenCount(int groupPosition) {

                     return adname.length;

              }

 

              @Override

              public Object getGroup(int groupPosition) {

            	     return groupPosition;

              }

 

              @Override

              public int getGroupCount() {

            	  if(gpos==0)
            	  {
            		  list1=strlist.split(",");
                     
            	  }
            	  else if(gpos==1)
            	  {
            		  list1=strlist2.split(",");
            	  }
            	  else if(gpos==2)
            	  {
            		  list1=strlist3.split(",");
            	  }
            	  else if(gpos==3)
            	  {
            		  list1=strlist4.split(",");
            	  }
            	  int siz=list1.length;
            	  
            	  return siz;

              }

 

              @Override

              public long getGroupId(int groupPosition) {

            	  
                     return groupPosition;

              }

              
              
 

              @Override

              public View getGroupView(int groupPosition, boolean isExpanded,

                           View convertView, ViewGroup parent) {

                     TextView tv = new TextView(MainActivity.this);

                     tv.setTypeface(tfBamini);
                     strConBamini = TamilUtil.convertToTamil(TamilUtil.BAMINI, list1[groupPosition]);
       					tv.setText(strConBamini);
                    // tv.setText(list1[groupPosition]);
       					
       					tv.setTextColor(Color.WHITE);

       					
       			     
                        int Measuredwidth = 0;
                  	  int Measuredheight = 0;
                  	  Point size = new Point();
                  	  WindowManager w = getWindowManager();

                  	    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2){
                  	          w.getDefaultDisplay().getSize(size);

                  	          Measuredwidth = size.x;
                  	          Measuredheight = size.y; 
                  	        }else{
                  	          Display d = w.getDefaultDisplay(); 
                  	          Measuredwidth = d.getWidth(); 
                  	          Measuredheight = d.getHeight(); 
                  	        }
                  	    
                  	    
                  	    if(Measuredwidth>=800 && Measuredheight<=1280)
                  	    {
                  	     tv.setTextSize(45);
                  	   tv.setPadding(50, 7, 7, 7);
                  	    }
                  	    
                  	    else if(Measuredwidth>=600 && Measuredheight<=1280)
               	    {
               	     tv.setTextSize(40);
               	     tv.setPadding(40, 7, 7, 7);
               	    }
                  	    
                  	    else
                  	    {
                  	    	tv.setTextSize(20);
                  	    	 tv.setPadding(25, 7, 7, 7);
                  	    }
                  	    
                   
                  	  tv.setWidth(1280);
                    

                     return tv;

              }

 

              @Override

              public boolean hasStableIds() {

                     // TODO Auto-generated method stub

                     return true;

              }

 

              @Override

              public boolean isChildSelectable(int groupPosition, int childPosition) {

                     // TODO Auto-generated method stub
/*
            	  if(ggpos==0)
            	  {
            		  String fname=filename[childPosition];
            	  filepath="01 DONE/"+file+"/"+fname;
            	  textview.filepaths="01 DONE/"+file+"/";
            	 // Toast.makeText(getApplicationContext(), filepath, Toast.LENGTH_LONG).show();
            	  Log.v("FILENAME",filepath);
            	  
            	  }
            	  else if(ggpos==1)
            	  {
            		  String fname=filename[childPosition];
                	  filepath="02 DONE/"+file+"/"+fname;
                	  textview.filepaths="02 DONE/"+file+"/";
                	//  Toast.makeText(getApplicationContext(), filepath, Toast.LENGTH_LONG).show();
                	  Log.v("FILENAME",filepath);
            	  }
            	  else if(ggpos==2)
            	  {
            		  String fname=filename[childPosition];
                	  filepath="03 DONE/"+file+"/"+fname;
                	  textview.filepaths="03 DONE/"+file+"/";
                	//  Toast.makeText(getApplicationContext(), filepath, Toast.LENGTH_LONG).show();
                	  Log.v("FILENAME",filepath);
            		  
            	  }
            	  else if(ggpos==3)
            	  {
            		  String fname=filename[childPosition];
                	  filepath="04 DONE/"+file+"/"+fname;
                	  textview.filepaths="04 DONE/"+file+"/";
                	  //Toast.makeText(getApplicationContext(), filepath, Toast.LENGTH_LONG).show();
                	  Log.v("FILENAME",filepath);
            		  
            	  }
            	  textview.chapname=list1[groupPosition];
            	  textview.filelist=new String[filename.length];
            	  textview.filelist=filename;
            	  textview.cpos=childPosition;
            	  textview.flag=1;
            	
            	  Intent in = new Intent(MainActivity.this,textview.class);
            	  startActivity(in);
                  */
            	  return true;

              }
              
              
 

       }

}

