package pcd.lab06.executors.deadlock;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestExecDeadlock {

	public static void main(String[] args) throws Exception {

		int nTasks = 100; 
		int nThreads = Runtime.getRuntime().availableProcessors() + 1; // Con thread = 100 risolvo il problema, però non ha senso separare task con i thread
		
		ExecutorService exec = Executors.newFixedThreadPool(nThreads);
		CyclicBarrier barrier = new CyclicBarrier(nTasks);
				
		for (int i = 0; i < nTasks; i++) {
			exec.execute(new MyTask("task-" + i, barrier));
		}		
		
		exec.shutdown();
		exec.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);		
	}
}


/**
 * Ho 100 task, Thread pool di n + 1
 * 
 * stampa di a e stampa di b. Si richiede che fra la prima e la seconda stampa ci sia sincronizzazione. Quindi voglio fare sempre prima a poi b. C'è deadlock però...
 * 
 * Dai log si evince che:
 * - job a vengono sempre eseguiti;
 * - job b non viene eseguito mai.
 * 
 * n thread fisici = 16, thread virtuali = 100. Stiamo usando meccanismi di sincronizzazione fisici per un livello logico, ecco perché va in deadlock.
 * 
 * Barriera -> lavora su thread fisici
 * Executor -> lavora su thread virtuali
 * 
 * Risolvo avendo thread fisici = thread virtuali (in modo veloce).
 * I task non devono avere meccanismi di sincronizzazione di altri task. 
 * 
 * Se i task invece vogliono accedere ad oggetti condivisi? (mutua esclusione)
 * 
 * In quel caso si può fare, anche se un thread entra in sezione critica, prima o poi esce. Non si blocca all'interno. 
 */