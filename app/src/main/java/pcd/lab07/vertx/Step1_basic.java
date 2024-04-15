package pcd.lab07.vertx;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;
import java.io.*;

/** Questo snippet non è deterministico! Lo è nello step 2*/

public class Step1_basic {

	public static void main(String[] args) {
		
		System.out.println(new File(".").getAbsoluteFile());
		/**
		 * Otteniamo il contesto per un event loop. Nel flusso di controllo del main ottengo l'oggetto che mi consente di fare chiamate
		 * asincrone. 
		 */
		Vertx  vertx = Vertx.vertx();

		/**
		 * Chiamata asincrona per accedere al file system
		 */
		FileSystem fs = vertx.fileSystem();    		

		log("doing the async call... ");
		
		/* version 4.X - future (promise) based API */
		/** In vertex le promise sono future, le future promise e insomma tutto a caso */
		
		Future<Buffer> fut = fs.readFile("app/build.gradle.kts");

		/** OnComplete è la then di js. Viene chiamato sempre, sia che ci sia errore che non. 
		 * Un'alternativa è onSuccess, che fa qualcosa solo se la promise ha successo
		 */
		fut.onComplete((AsyncResult<Buffer> res) -> {
			log("BUILD \n" + res.result().toString().substring(0,160));
		});

		log("async call done. Waiting some time... ");


		try {
			Thread.sleep(1000);
		} catch (Exception ex) {}
		
		log("done");
	}
	
	private static void log(String msg) {
		System.out.println("" + Thread.currentThread() + " " + msg);
	}
}

