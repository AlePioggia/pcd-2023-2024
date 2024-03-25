package pcd.lab05.mvc.version1_unresponsive;

import java.util.ArrayList;
import java.util.List;

/**Il model avvisa tutti (publisher), quando viene modificato */

public class MyModel {

	private List<ModelObserver> observers;
	private int state;
	
	public MyModel(){
		state = 0;
		observers = new ArrayList<ModelObserver>();
	}
	
	public void update(){
		state++;
		notifyObservers();
	}
	
	public int getState(){
		return state;
	}
	
	public void addObserver(ModelObserver obs){
		observers.add(obs);
	}
	
	private void notifyObservers(){
		for (ModelObserver obs: observers){
			obs.modelUpdated(this);
		}
	}
}
