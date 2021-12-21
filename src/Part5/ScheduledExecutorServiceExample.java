package Part5;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceExample {

    public static void main(String args[]){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        // work1 실행 후 종료
        work1();
        // work1 종료 후 10초 뒤에 실행될 수 있도록 큐에 추가한다
        scheduledExecutorService.schedule(()->work2(),10, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
    }

    private static void work1() {
        System.out.println("work1");
    }
    private static void work2() {
        System.out.println("work2");
    }

}
