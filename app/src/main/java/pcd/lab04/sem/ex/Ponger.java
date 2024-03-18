package pcd.lab04.sem.ex;
import java.util.concurrent.*;

public class Ponger extends Thread {
	
	private Semaphore pingExecuted;
	private Semaphore pongExecuted;

	public Ponger(Semaphore pingExecuted, Semaphore pongExecuted) {
		this.pingExecuted = pingExecuted;
		this.pongExecuted = pongExecuted;
	}	
	
	public void run() {
		while (true) {
			try {
				//waits till ping is executed
				pingExecuted.acquire();
				System.out.println("pong!");
				pongExecuted.release();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}