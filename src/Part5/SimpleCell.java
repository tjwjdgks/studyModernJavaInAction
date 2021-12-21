package Part5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.*;


// 조건 : c1이나 c2의 값이 갱신되면 c3에 새로운 값 반영
/*
c1이나 c2의 값이 바뀌었을 때 c3이 새로운 값 반영하기 위해서는,
c1과 c2에 이벤트가 발생했을 때 c3 구독해야 한다. Publisher<T> 필요하다.

Subscriber<T> 는 구독자를 받아서, 구현자 필요한 대로 메서드를 구현한다
 */
// Publisher 이며 동시에 Subscriber 일  수 있다
public class SimpleCell implements Publisher<Integer>, Subscriber<Integer> {
    private int value = 0;
    private String name;
    private List<Subscriber> subscribers = new ArrayList<>();
    public SimpleCell(String name) {
        this.name = name;
    }

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        subscribers.add(subscriber);
    }


    @Override
    public void onSubscribe(Subscription subscription) {

    }
    private void notifyAllSubscribers(){
        subscribers.forEach(subscriber -> subscriber.onNext(this.value));
    }

    @Override
    public void onNext(Integer item) {
        this.value = item;
        System.out.println(this.name + " : " + this.value);
        notifyAllSubscribers();
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
