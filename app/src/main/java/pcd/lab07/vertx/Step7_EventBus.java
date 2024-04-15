package pcd.lab07.vertx;

import io.vertx.core.*;
import io.vertx.core.eventbus.EventBus;

/***
 * Creo due componenti attivi, ognuno con il proprio event-loop. Vertex usa un pool di thread, quindi ho un thread per ogni
 * componente attivo (circa).
 * 
 * Se io dovessi leggere concorrentemente 10 file ed aggregare i risultati, di quanti verticle devo fare il deploy, 10?
 * No! Io posso farlo anche con 1, lui può direttamente fare 10 richieste ed aggregare i risultati. 
 */

class MyAgent1 extends AbstractVerticle {
	
	 public void start(Promise<Void> startPromise) {
		log("started.");
		EventBus eb = this.getVertx().eventBus();
		eb.consumer("my-topic", message -> {
			log("new message: " + message.body());
		});		
		log("Ready.");
		startPromise.complete();
	}

	private void log(String msg) {
		System.out.println("[REACTIVE AGENT #1]["+Thread.currentThread()+"] " + msg);
	}
}

class MyAgent2 extends AbstractVerticle {
	
	public void start() {
		log("started.");
		// prendo il contesto del mio event loop (avendone più di uno)
		// eventbus è un medium di interazione a messaggi dove posso pubblicare messaggi su topic (nome di un canale)
		EventBus eb = this.getVertx().eventBus();
		eb.publish("my-topic", "test");
	}

	private void log(String msg) {
		System.out.println("[REACTIVE AGENT #2]["+Thread.currentThread()+"] " + msg);
	}
}

public class Step7_EventBus {

	public static void main(String[] args) {
		Vertx  vertx = Vertx.vertx();
		vertx.deployVerticle(new MyAgent1(), res -> {
			/* deploy the second verticle only when the first has completed */
			vertx.deployVerticle(new MyAgent2());
		});
	}
}

