package com.ecge;

import com.ecge.scenario.ScenarioLoader;
import com.ecge.scenario.SceneInfo;

public class Director {
	
	public static ScenarioLoader SceLoader = null;
	
	public interface ScenarioExecuter {
		public void Execute(SceneInfo si);
	}
	
	public static ScenarioExecuter SceExecuter = null;
	
	public static void Next() {
		if(SceLoader == null || SceExecuter == null ||
				SceLoader.ToTheEnd())
			return;
		
		SceLoader.MoveToNext();
		SceExecuter.Execute(SceLoader.SceneCursor);
	}

}