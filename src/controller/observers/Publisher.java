package controller.observers;

public interface Publisher {
    void addSubscriber(Subsriber subsriber);
    void removeSubscriber(Subsriber subsriber);
    void removeAllSubscribers();
    void notifySubscribers(Object notification, String message);
}
