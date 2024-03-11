package pcd.lab03.liveness.deadlock_obs;

/**Questo programma implementa il pattern observer. Voglio un approccio ad eventi, ho un'entità che funge da sorgente di eventi (MyObservedEntity) */

public class AgentOne extends Thread {
 	MyObservedEntity obj;
	
 	public AgentOne(MyObservedEntity obj){
 		this.obj = obj;
 	}
 	
	public void run(){
		while (true){
			obj.changeState1();
			obj.changeState2();
		}
	}
}
