package com.ecge.scenario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;

public class ScenarioLoader {
	private static final String SCENARIO_FILENAME = "scenario.txt";
	
	public SceneInfo SceneCursor = null;
	
	private Context mContext;
	
	private String[] SceneList = null;
	
	private int CursorIndex = 0;
	
	
	public ScenarioLoader(Context context) {
		SceneCursor = new SceneInfo();
		
		mContext = context;
	}
	
	public void MoveToNext() {
		if(SceneList == null)
			return ;
		
		String scene = SceneList[CursorIndex];
		SceneCursor = getSceneInfo(scene);
		
		CursorIndex += 1;
	}
	
	public void MoveToPerv() {
		
	}
	
	public boolean ToTheEnd() {
		return CursorIndex > SceneList.length;
	}
	
	private SceneInfo getSceneInfo(String scene) {
		SceneInfo si = null;
		
		if(scene != null) {
			si = new SceneInfo();
			si.Type = Integer.parseInt(scene.substring(0, 1));
			si.Action = scene.substring(1);
		}
		
		return si;
	}
	
	public void Load() {
		new Thread(new Runnable() {
			
			public void run() {
				StringBuffer mContainer = null;
				
				InputStream is = null;
				InputStreamReader inReader = null;
				BufferedReader bufReader = null;
				try{
					
					try{
						is = mContext.getResources().getAssets().open(SCENARIO_FILENAME);
						inReader = new InputStreamReader(is);
						bufReader = new BufferedReader(inReader);
						
						mContainer = new StringBuffer();
						
						String buffer = null;
						while((buffer = bufReader.readLine()) != null) {
							mContainer.append(buffer + "\n");
							
						}
						// Adjust the index of the scene
						String transfer = mContainer.toString().substring(1);
						SceneList = transfer.split("\n");
						CursorIndex = 0;
						
						System.out.println("Loading finished!");
						
					}finally {
						if(is == null)
							return ;
						is.close();
						
						if(inReader == null)
							return;
						inReader.close();
						
						if(bufReader == null)
							return;
						bufReader.close();
					}
					
				}catch(IOException ex) {
					ex.printStackTrace();
					
				}
			}
			
		}).start();
		
	}

}
