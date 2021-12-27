package Part5.reactive;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;
import java.util.concurrent.Flow.*;

public class TempSubscription implements Subscription {

    private final Subscriber<? super TempInfo> subscriber;
    private final String town;

    public TempSubscription(Subscriber<? super TempInfo> subscriber, String town) {
        this.subscriber = subscriber;
        this.town = town;
    }
    // 다른 스레드로 세 요서를 전할하는 방법
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    @Override
    public void request(long n) {
        executor.submit(()->{
            for(long i=0L;i<n;i++){
                try{
                    subscriber.onNext(TempInfo.fetch(town));
                }catch (Exception e){
                    // 온도 가져오기 실패시 Subscriber로 에러 전달
                    subscriber.onError(e);
                    break;
                }
            }
        });
    }

    @Override
    public void cancel() {
        // 구독취소 완료되면 onComplete 신호를 Subscriber로 전달
        subscriber.onComplete();
    }
}
