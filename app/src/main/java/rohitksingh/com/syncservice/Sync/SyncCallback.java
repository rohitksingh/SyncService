package rohitksingh.com.syncservice.Sync;

public interface SyncCallback {



    // This methods is used to update Activity
    // Can we make this abstract or make all methods from abstractclass to interface? what is the advantage ?
    public void update(Object object);

}
