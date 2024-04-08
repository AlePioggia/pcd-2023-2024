package pcd.lab06.executors.matmul;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MatMulConcurLib {

	private static MatMulConcurLib instance;
	private ExecutorService exec;

	public static MatMulConcurLib getInstance(){
		synchronized (MatMulConcurLib.class) {
			if (instance == null) {
				instance = new MatMulConcurLib();
			} 
			return instance;
		}
	}

	private MatMulConcurLib() {
	}
	
	/**
	 * Mi focalizzo sui task che possono essere eseguiti in modo concorrente,
	 * la suddivisione dei task nei vari thread è responsabilità dell'executor
	 * Secondo il prof è meglio usare la classe, piuttosto che la lambda, per
	 * specificare meglio a livello di progettazione, il task.
	 */
	public Mat matmul(Mat matA, Mat matB) throws MatMulException {
		Mat matC = new Mat(matA.getNRows(), matB.getNColumns());
		exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);		
		try {
			for (int i = 0; i < matA.getNRows(); i++){
				for (int j = 0; j < matB.getNColumns(); j++){
					exec.execute(new ComputeElemTask(i,j,matA,matB,matC));
				}
			}
			exec.shutdown();
			exec.awaitTermination(Long.MAX_VALUE,TimeUnit.SECONDS);
			return matC;
		} catch (Exception ex){
			throw new MatMulException();
		}
	}
}
