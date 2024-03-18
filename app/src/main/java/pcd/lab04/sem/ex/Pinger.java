package pcd.lab04.sem.ex;
import java.util.concurrent.*;

public class Pinger extends Thread {

	private Semaphore pingExecuted;
	private Semaphore pongExecuted;

	public Pinger(Semaphore pingExecuted, Semaphore pongExecuted) {
		this.pingExecuted = pingExecuted;
		this.pongExecuted = pongExecuted;
	}	
	
	public void run() {
		while (true) {
			try {
				pongExecuted.acquire();
				System.out.println("ping!");
				pingExecuted.release();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}