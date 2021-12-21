package Part5;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExample {
    public static void main(String args[]) throws ExecutionException, InterruptedException {
        int x = 0;

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> f_r = executorService.submit(() -> f(x));
        Future<Integer> g_r = executorService.submit(() -> g(x));
        System.out.println(f_r.get() + g_r.get());
    }
    private static int g(int x) {
        return 2;
    }

    // 오래걸리는 작업이라 가정
    private static int f(int x) {
        return 1;
    }
}
