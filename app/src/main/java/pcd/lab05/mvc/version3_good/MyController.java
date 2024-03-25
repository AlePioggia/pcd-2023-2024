package pcd.lab05.mvc.version3_good;


public class MyController {
	
	private MyModel model;
	public MyController(MyModel model){
		this.model = model;
	}

	/** 
	 * Per il controller sfrutto dunque un altro thread, in modo da non far fare altre 
	 * operazioni all'eventDispatcherThread ma ad un thread dedicato.
	 */
	public void processEvent(String event) {
		try {
			new Thread(() -> {
				try {
					System.out.println("[Controller] Processing the event "+event+" ...");
					Thread.sleep(1000);
					model.update();
					System.out.println("[Controller] Processing the event done.");
				} catch (Exception ex){
					ex.printStackTrace();
				}
			}).start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
