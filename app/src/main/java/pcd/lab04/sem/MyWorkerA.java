package pcd.lab04.sem;

import java.util.concurrent.Semaphore;

public class MyWorkerA extends Worker {
	
	private Semaphore mutex;
	
	public MyWorkerA(String name, Semaphore mutex){
		super(name);
		this.mutex = mutex;
	}
	
	//since acquire can be interrupted, exceptions are needed

	public void run(){
		while (true){
		  action1();	
		  try {
			  mutex.acquire(); //await equivalent
			  //Check, once you get inside critical section, value needs to be 1
			  //c.inc()
			  //assert c.getValue() == 1
			  action2();	
			  action3();	
		  } catch (InterruptedException ex) {
			  log("interrupted..");
		  } finally {
			  mutex.release();
		  }
		}
	}
	
	protected void action1(){
		println("a1");
		wasteRandomTime(100,500);	
	}
	
	protected void action2(){
		println("a2");
		wasteRandomTime(300,700);	
	}
	protected void action3(){
		println("a3");
		wasteRandomTime(300,700);	
	}
}

