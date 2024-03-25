package pcd.lab05.chrono.version1_basic;

public class CounterAgent extends Thread {

	private Counter counter;
	private Flag stopFlag;
	private long delta;
	
	public CounterAgent(Counter c, Flag stopFlag, long delta){
		counter = c;
		this.stopFlag = stopFlag;
		this.delta = delta;
	}
	public void run(){
		stopFlag.reset(); //monitor che controlla se il flag è stato settato a true o meno
		while (!stopFlag.isSet()){
			counter.inc();
			System.out.println(counter.getValue());
			try {
				Thread.sleep(delta);
			} catch(Exception ex){
			}
		}
	}
}
