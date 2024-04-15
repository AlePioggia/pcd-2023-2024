package pcd.lab07.vertx;

import io.vertx.core.AsyncResult;
import io.vertx.core.*;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;

/**
 * Wrappiamo tutte le computazioni in un verticle. Dentro quel blocco (verticle block), abbiamo tutte le garanzie del caso sull'esecuzione. 
 * Qui legge concorrentemente il contenuto di due file, quindi uno può finire prima dell'altro.
 * Eseguire roba dentro il blocco verticle mi consente di evitare che ci siano corse critiche, perché lui 
 * internamente gestisce questi aspetti. 
 */

class MyReactiveAgent extends AbstractVerticle {
	
	public void start() {
		log("1 - doing the async call...");
		
		FileSystem fs = getVertx().fileSystem();    		
		
		Future<Buffer> f1 = fs.readFile("app/build.gradle.kts");

		f1.onComplete((AsyncResult<Buffer> res) -> {
			log("4 - BUILD \n" + res.result().toString().substring(0,160));
		});
	
		log("2 - doing the second async call...");

		fs
		.readFile("settings.gradle.kts")
		.onComplete((AsyncResult<Buffer> res) -> {
			log("4 - SETTINGS \n" + res.result().toString().substring(0,160));
		});
		
		log("3 - done");
	}

	private void log(String msg) {
		System.out.println("" + Thread.currentThread() + " " + msg);
	}
}

public class Step2_withverticle {

	public static void main(String[] args) {
		Vertx  vertx = Vertx.vertx();
		vertx.deployVerticle(new MyReactiveAgent());
	}
}

