package pcd.lab04.sem.ex;
import java.util.concurrent.*;

/**
 * Unsynchronized version
 * 
 * If two threads are both blocked, in order to wait, you need to consider
 * fairness aspects.
 * 
 * @TODO make it sync 
 * @author aricci
 *
 */
public class TestPingPong {
	public static void main(String[] args) {
		//If I start it won't work, since it's in deadlock.
		//To solve the issue, it's needed to manually signal pongExecuted
		Semaphore pingExecuted = new Semaphore(0);
		Semaphore pongExecuted = new Semaphore(0);
		new Pinger(pingExecuted, pongExecuted).start();
		new Ponger(pingExecuted, pongExecuted).start();	

		pongExecuted.release();
	}

}
