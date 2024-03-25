package pcd.lab05.monitors.ex_barrier;

public interface Barrier {

	/**
	 * Blocca il thread chiamante, nel caso in cui non siano arrivati tutti. Quando sono arrivati
	 * tutti sblocca e farli continuare
	 */
	void hitAndWaitAll() throws InterruptedException;

}
