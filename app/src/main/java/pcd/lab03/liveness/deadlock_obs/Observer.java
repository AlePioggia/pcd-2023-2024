package pcd.lab03.liveness.deadlock_obs;

/**Qualsiasi entità che vuole osservare deve estendere questa interfaccia*/

public interface Observer {
	void notifyStateChanged(Observed obs);
}
