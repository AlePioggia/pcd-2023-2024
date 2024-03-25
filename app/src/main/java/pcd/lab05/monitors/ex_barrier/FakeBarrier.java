package pcd.lab05.monitors.ex_barrier;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FakeBarrier implements Barrier {
	
	public FakeBarrier(int nParticipants) {	
	}
	
	@Override
	public synchronized void hitAndWaitAll() throws InterruptedException {	
	}
	
}
