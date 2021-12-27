package Part5;

import java.util.concurrent.*;

public class FutureAsync {
    public static void main(String args[]){
        // 쓰레드 풀에 태스크를 제출하려면 ExecutorService를 만들어야 한다
        ExecutorService executor  = Executors.newCachedThreadPool();

        // Callable을 ExcutorService로 제출한다
        Future<Double> future = executor.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                return doSomeLongComputation();
            }
        });
        // 비동기 작업을 수행하는 동안 다른 작업 수행
        doSomethingElse();
        try {
         // 비동기 작업의 결과를 가져온다. 결과가 준비 되지 않으면 호출 스레드가 블록된다. 최대 1초를 기다린다
         Double result=  future.get(1, TimeUnit.SECONDS);
        } catch (ExecutionException e) {
            // 계산 중 예외 발생
            e.printStackTrace();
        } catch (InterruptedException e) {
            // 현재 스레드에서 대기 중 인터럽트 발생
            e.printStackTrace();
        } catch (TimeoutException e) {
            // Future가 완료되기 전에 타임 아웃 발생
            e.printStackTrace();
        }
    }

    private static void doSomethingElse() {
        System.out.println("do something");
    }

    private static Double doSomeLongComputation() {
        return 0.0;
    }
}
