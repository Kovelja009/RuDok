package controller.observers;

public interface Publisher {
    void addSubscriber(Subsriber subsriber);
    void removeSubscriber(Subsriber subsriber);
    void notifySubscribers(Object notification);
}
