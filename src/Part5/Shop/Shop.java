package Part5.Shop;


import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;


public class Shop {

    private final String name;
    private final Random random;

    public Shop(String name) {
        this.name = name;
        random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }
    // 비동기 방법
    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            double price = calculatePrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }
    // 비동기 방법 에러 전파
    public Future<Double> getPriceAsyncHandleException(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try{
                double price = calculatePrice(product);
                futurePrice.complete(price);

            }catch (Exception ex){
                futurePrice.completeExceptionally(ex);
            }
        }).start();
        return futurePrice;
    }
    public Future<Double> getPriceAsyncSimple(String product) {
        return CompletableFuture.supplyAsync(()->calculatePrice(product));
    }

    public String getName() {
        return name;
    }

    public static void delay() {
        int delay = 1000;
        //int delay = 500 + RANDOM.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

