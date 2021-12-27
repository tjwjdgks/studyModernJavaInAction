package Part5.Shop;

import java.util.List;
import java.util.function.Supplier;

public class PriceFinderRunner {
    private static PriceFinder priceFinder = new PriceFinder();
    public static void main(String args[]){
        execute("순차",()->priceFinder.findPrices("test"));
        execute("병렬",()-> priceFinder.findPricesParallel("test"));
        execute("CompeletableFuture",()->priceFinder.findPricesFuture("test"));
    }
    private static void execute(String msg, Supplier<List<String>> s) {
        long start = System.nanoTime();
        System.out.println(s.get());
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println(msg + " done in " + duration + " msecs");
    }
}
