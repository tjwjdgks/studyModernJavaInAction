package Part3;


import java.util.ArrayList;
import java.util.List;
/*
interface UserObserver{
    // public abstract 생략된 것
    void notifying(String text);
}
class News implements UserObserver{

    @Override
    public void notifying(String text) {
        System.out.println("news " + text);
    }
}
class Internet implements UserObserver{

    @Override
    public void notifying(String text) {
        System.out.println("internet " + text);
    }
}
class Phone implements UserObserver{

    @Override
    public void notifying(String text) {
        System.out.println("phone " + text);
    }
}
 */
interface UserObserver{
    // public abstract 생략된 것
    void notifying(String text);
}

class Subject{
    private final List<UserObserver> observers = new ArrayList<>();
    public void registerObserver(UserObserver o){
        observers.add(o);
    }
    public void notifyObservers(String text){
        observers.forEach(o->o.notifying(text));
    }
}
public class ObserverRunner {

    public static void run() {
        Subject subject = new Subject();
        subject.registerObserver((String text)-> System.out.println("news " + text));
        subject.registerObserver((String text) -> System.out.println("phone " + text));
        subject.registerObserver((String text) -> System.out.println("internet " + text));
        subject.notifyObservers("observer!");
    }
}
