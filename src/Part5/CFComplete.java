package Part5;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CFComplete {
    public static void main(String args[]) throws ExecutionException, InterruptedException {

        ExecutorService executorService= Executors.newFixedThreadPool(10);

        int x =0;
        CompletableFuture<Integer> a = new CompletableFuture<>();
        executorService.submit(()->a.complete(f(x)));
        int b = g(x);
        System.out.println(a.get()+b);
        executorService.shutdown();
    }

    public static int f(int x) {
        return x+1;
    }

    public static int g(int x) {
        return x+2;
    }
}
