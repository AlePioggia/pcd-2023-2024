package pcd.lab05.mvc.version1_unresponsive;

public class MyController {

	private MyModel model;

	public MyController(MyModel model) {
		this.model = model;
	}

	/**Metto la computazione nel controller, c'è una buona separazione mvc, però
	 * il codice diventa non responsivo, perché il controller è comunque gestito dallo stesso 
	 * unico thread.
	 */
	public void processEvent(String event) {
		try {
			System.out.println("[Controller] Processing the event " + event + " ...");
			Thread.sleep(5000);
			model.update();
			System.out.println("[Controller] Processing the event done.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
