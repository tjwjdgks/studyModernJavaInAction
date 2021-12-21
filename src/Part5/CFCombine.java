package Part5;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CFCombine {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        int x = 0;
        CompletableFuture<Integer> a = new CompletableFuture<>();
        CompletableFuture<Integer> b = new CompletableFuture<>();
        CompletableFuture<Integer> c = a.thenCombine(b,(num1,num2)->num1+num2);

        executorService.submit(()->a.complete(f(x)));
        executorService.submit(()->a.complete(f(x)));

        System.out.println(c.get());
        executorService.shutdown();
    }
    public static int f(int x) {
        return x+1;
    }

    public static int g(int x) {
        return x+2;
    }
}
