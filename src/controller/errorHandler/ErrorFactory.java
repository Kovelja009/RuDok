package controller.errorHandler;


import controller.observers.Publisher;
import controller.observers.Subsriber;

import java.util.ArrayList;
import java.util.List;

public class ErrorFactory implements Publisher {
    private static ErrorFactory instance = null;
    private List<Subsriber> subsriberList;

    private ErrorFactory(){}

    private void initialize(){
        subsriberList = new ArrayList<>();
    }

    public static ErrorFactory getInstance(){
        if(instance == null){
            instance = new ErrorFactory();
            instance.initialize();
        }
        return instance;
    }

    public void generateError(String message, String title, int type){
        notifySubcribers(new MyError(message, title, type), "");
    }

    @Override
    public void addSubscriber(Subsriber subsriber) {
        if(!subsriberList.contains(subsriber)){
            subsriberList.add(subsriber);
        }
    }

    @Override
    public void removeSubscriber(Subsriber subsriber) {
            subsriberList.remove(subsriber);
    }

    @Override
    public void removeAllSubcribers() {
        List<Subsriber> brisanje = new ArrayList<>(subsriberList);
        subsriberList.removeAll(brisanje);
    }

    @Override
    public void notifySubcribers(Object notification, String message) {
        for(Subsriber s : subsriberList){
            s.updateSubsriber(notification, message);
        }
    }
}
