package pcd.lab07.vertx;

import io.vertx.core.*;


/**
 * Vogliamo implementare un metodo asincrono protetto, in cui si vuole restituire un valore randomico tra 0 ed 1 dopo il tempo
 * delay specificato. 
 * Voglio progettare un metodo asincrono che mi consenta di ritornare la future. Per farlo serve usare l'api che ci dà le
 * promise.
 */

/**
 * Exercise
 * 
 * -- implement an async protected method getDelayedRandom(int delay)
 *    that returns a random value between 0 and 1 after the specified
 *    amount of time
 *    
 * -- in the "start" method, test the behaviour of the method by using it.
 * 
 */
class VerticleWithPromise extends AbstractVerticle {

	public void start() {
		log("started.");

		var f = getDelayedRandom(1000);
		f.onSuccess(res -> {
			log("result: " + res);
		});
	
	}

	protected Future<Double> getDelayedRandom(int delay) {
		Promise<Double> p = Promise.promise(); //creo la promise, oggetto thread safe pronto per essere configurato
		getVertx().setTimer(delay, (id) -> {
			p.complete(); // completo manualmente la promise
		});
		return p.future(); // restituendo questo, dò una interfaccia di read(), non modificabile
	} 

	private void log(String msg) {
		System.out.println("[REACTIVE AGENT]["+Thread.currentThread()+"]" + msg);
	}
}

public class Step4_promise_exercise {
	public static void main(String[] args) {
		
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new VerticleWithPromise());
	}
}

