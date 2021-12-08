import Part2.HandleStream;

import java.util.stream.IntStream;


public class Main {
    public static void main(String[] args){
        //CollectionVsStream.Runner();
        HandleStream.practice();
        IntStream.rangeClosed(1,10).forEach(System.out::println);

    }
}
