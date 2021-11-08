package controller.observers;

public interface Publisher {
    void addSubscriber(Subsriber subsriber);
    void removeSubscriber(Subsriber subsriber);
    void removeAllSubcribers();
    void notifySubcribers(Object notification, String message);
}
