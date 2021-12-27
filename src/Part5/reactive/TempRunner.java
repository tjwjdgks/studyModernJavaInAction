package Part5.reactive;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.*;

public class TempRunner {
    public static void main(String []args){
        getTemperatures("test").subscribe(new TempSubscriber());
        getCelisiusTemperatures("test").subscribe(new TempSubscriber());
    }
    private static Publisher<TempInfo> getTemperatures(String town){
        return subscriber -> subscriber.onSubscribe(new TempSubscription(subscriber,town));
    }
    private static Publisher<TempInfo> getCelisiusTemperatures(String town){
        return subscriber -> {
            TempProcessor processor = new TempProcessor();
            processor.subscribe(subscriber);
            processor.onSubscribe(new TempSubscription(subscriber,town));
        };
    }
}

