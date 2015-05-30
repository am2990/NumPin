package com.example.wordpassword;
/**
 * Komodo Lab: Tagin! Project: 3D Tag Cloud
 * Google Summer of Code 2011
 * @authors Reza Shiftehfar, Sara Khosravinasr and Jorge Silva
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

/**
 * SampleTagCloud class:
 * this is a sample program to show how the 3D Tag Cloud can be used.
 * It Creates the activity and sets the ContentView to our TagCloudView class
 */
public class SampleTagCloud extends Activity {
String str1="";
String stritr;
int g=0;
int p=0;
	//ArrayList<Object> objects;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Step0: to get a full-screen View:
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		 Bundle extra = getIntent().getBundleExtra("extra");
		 @SuppressWarnings("unchecked")
		ArrayList<Object> objects = (ArrayList<Object>) extra.getSerializable("wordArrayList");
	       // @SuppressWarnings("unchecked")
		//Step1: get screen resolution:
		Display display = getWindowManager().getDefaultDisplay(); 
		int width = display.getWidth();
		int height = display.getHeight();
		
		
		
		
		
		
		//Step2: create the required TagList:
		//notice: All tags must have unique text field
		//if not, only the first occurrence will be added and the rest will be ignored
        
		List<Tag> myTagList= createTags(objects);
		
		
		//Step3: create our TagCloudview and set it as the content of our MainActivity
		mTagCloudView = new TagCloudView(this, width, height, myTagList ); //passing current context 
		setContentView(mTagCloudView);
		mTagCloudView.requestFocus();
		mTagCloudView.setFocusableInTouchMode(true);
		
		//Step4: (Optional) adding a new tag and resetting the whole 3D TagCloud
		//you can also add individual tags later:
		//mTagCloudView.addTag(new Tag("AAA", 5, "http://www.aaa.com"));
		// .... (several other tasg can be added similarly )
		//indivual tags will be placed along with the previous tags without moving 
		//old ones around. Thus, after adding many individual tags, the TagCloud 
		//might not be evenly distributed anymore. reset() re-positions all the tags:
		//mTagCloudView.reset();
		
		//Step5: (Optional) Replacing one of the previous tags with a new tag
		//you have to create a newTag and pass it in together 
		//with the Text of the existing Tag that you want to replace
		//Tag newTag=new Tag("Illinois", 9, "http://www.illinois.com");
		//in order to replace previous tag with text "Google" with this new one:
		//boolean result=mTagCloudView.Replace(newTag, "google");
		//result will be true if "google" was found and replaced. else result is false
	}

	protected void onResume() {
		super.onResume();
	}
	
	protected void onPause() {
		super.onPause();
	}
	
	private List<Tag> createTags(ArrayList<Object> objects){
		
		//
			//ArrayList<Object> objects = (ArrayList<Object>) extra.getSerializable("wordArrayList");
	        int count = objects.size();
	        System.out.println(count);
	        
	        
	        	
	        
		//create the list of tags with popularity values and related url
		List<Tag> tempList = new ArrayList<Tag>();
		List<Object> list = objects;
		 Iterator<Object> iterator = list.iterator();
		 String listString = "";

			for (Object s : list)
			{
			    listString += s + "\t";
			}

			System.out.println("list string"+listString);
		 
		/* while(p<objects.size()){
			// stritr = (String)iterator.next();
			// str1 = str1.concat(stritr).concat(" ");
		//	 str1 = objects.toString();
			 
			 p++;
		 }*/
	//	 System.out.println("inside loop"+str1);
		while(iterator.hasNext())
		{
			System.out.println("hello loop");

				//if(objects.size()-1){
				//	System.out.println("not iter"+iterator.next());
		/*	while(g<count-1){
					 tempList.add(new Tag((String)iterator.next(),5));
					 g++;
			}*/
			//while(g<count-1){
		//	if(g<count-1){
				String str = (String)iterator.next();
			
					tempList.add(new Tag(str,5,str));
					
						
		//	}
		//	g++;
			//break;
		}
		
		/*while(!(iterator.hasNext())){
			tempList.add(new Tag((String)iterator.next(),5,"fff"));
			break;
		}*/	
				//}
				/*else
				{
					tempList.add(new Tag((String)iterator.next(),5,str1));
					System.out.println("not iter"+iterator.next());
				}*/
				
		//System.out.println("outside loop"+str1);
		
		
		
		//tempList.add(newTag(objects));
		tempList.add(new Tag("Google", 7));  //1,4,7,... assumed values for popularity
		tempList.add(new Tag("Yahoo", 3));
		tempList.add(new Tag("CNN", 4));
		tempList.add(new Tag("Facebook", 2));
	//	System.out.println(tempList.subList(0, 1));		
		//tempList.add(new Ta);
		//tempList.add(new Tag())
		/*tempList.add(new Tag("MSNBC", 5, "www.msnbc.com"));
		tempList.add(new Tag("CNBC", 5, "www.CNBC.com"));
		tempList.add(new Tag("Facebook", 7, "www.facebook.com"));
		tempList.add(new Tag("Youtube", 3, "www.youtube.com"));
		tempList.add(new Tag("BlogSpot", 5, "www.blogspot.com"));
		tempList.add(new Tag("Bing", 3, "www.bing.com"));
		tempList.add(new Tag("Wikipedia", 8, "www.wikipedia.com"));
		tempList.add(new Tag("Twitter", 5, "www.twitter.com"));
		tempList.add(new Tag("Msn", 1, "www.msn.com"));
		tempList.add(new Tag("Amazon", 3, "www.amazon.com"));
		tempList.add(new Tag("Ebay", 7, "www.ebay.com"));
		tempList.add(new Tag("LinkedIn", 5, "www.linkedin.com"));
		tempList.add(new Tag("Live", 7, "www.live.com"));
		tempList.add(new Tag("Microsoft", 3, "www.microsoft.com"));
		tempList.add(new Tag("Flicker", 1, "www.flicker.com"));
		tempList.add(new Tag("Apple", 5, "www.apple.com"));
		tempList.add(new Tag("Paypal", 5, "www.paypal.com"));
		tempList.add(new Tag("Craigslist", 7, "www.craigslist.com"));
		tempList.add(new Tag("Imdb", 2, "www.imdb.com"));
		tempList.add(new Tag("Ask", 4, "www.ask.com"));
		tempList.add(new Tag("Weibo", 1, "www.weibo.com"));
		tempList.add(new Tag("Tagin!", 8, "http://scyp.idrc.ocad.ca/projects/tagin"));
		tempList.add(new Tag("Shiftehfar", 8, "www.shiftehfar.org"));
		tempList.add(new Tag("Soso", 5, "www.google.com"));
		tempList.add(new Tag("XVideos", 3, "www.xvideos.com"));
		tempList.add(new Tag("BBC", 5, "www.bbc.co.uk"));*/
		return tempList;
	}
	
	private TagCloudView mTagCloudView;
	
}
