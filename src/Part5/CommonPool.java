package Part5;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// stream의 parallel() 이나 CompetableFuture 등의 비동기 내부 호출의 경우 ForkJoinPool의 commonPool을 사용한다
// stream의 commonPool의 thread 수는 processor 갯수의 -1 이다
public class CommonPool {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("CPU Core: " + Runtime.getRuntime().availableProcessors());
        System.out.println("CommonPool Parallelism: " + ForkJoinPool.commonPool().getParallelism());
        System.out.println("CommonPool Common Parallelism: " + ForkJoinPool.getCommonPoolParallelism());

        long start = System.nanoTime();
        List<CompletableFuture<Void>> futures = IntStream.range(0, 100)
                .mapToObj(i -> CompletableFuture.runAsync(CommonPool::blockingOperation))
                .collect(Collectors.toUnmodifiableList());

        CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();
        System.out.println("Processed in " + Duration.ofNanos(System.nanoTime() - start).toSeconds() + " sec");
    }

    private static void blockingOperation() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
