package Part5.Shop;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.util.stream.Collectors.*;

public class PriceFinder {
    private final List<Shop> shops = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"));

    private final Executor executor = Executors.newFixedThreadPool(shops.size(),(Runnable r)->{
        Thread t= new Thread(r);
        t.setDaemon(true);
        return t;
    });

    // 모든 상적에 순차적으로 정보를 요청하는 함수
    public List<String> findPrices(String product){
        return shops.stream()
                .map(shop -> String.format("%s price is %.2f",shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }
    public List<String> findPricesParallel(String product){
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f",shop.getName(), shop.getPrice(product)))
                .collect(toList());
    }

    public List<String> findPricesFuture(String product) {
        List<CompletableFuture<String>> pricesFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                ()->String.format("%s price is %.2f",shop.getName(), shop.getPrice(product))))
                        .collect(toList());
        return pricesFutures.stream().map(CompletableFuture::join).collect(toList());
    }
}
