package Part2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

public class Practice {
    public static void Runner(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 질의 1: 2011년에 일어난 모든 거래를 찾아 값으로 정렬(작은 값에서 큰 값).
        transactions.stream()
                .filter(t -> t.getYear()==2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());
        // 질의 2: 거래자가 근무하는 모든 고유 도시는?
        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(toList());
        // 질의 2 set도 있음
        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .collect(toSet());
        // 질의 3: Cambridge의 모든 거래자를 찾아 이름으로 정렬.
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(t->t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());
        // 질의 4: 알파벳 순으로 정렬된 모든 거래자의 이름 문자열을 반환
        transactions.stream()
                .map(t->t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("",(n1,n2)->n1+" "+n2);
        // 질의 4 : joining 이 더 좋다
        transactions.stream()
                .map(t->t.getTrader().getName())
                .sorted()
                .collect(joining());
        // 질의 5: Milan에 거주하는 거래자가 있는가?
        transactions.stream()
                .allMatch(t->t.getTrader().getCity().equals("Milan"));
        // 질의 6: Cambridge에 사는 거래자의 모든 값 출력.
        transactions.stream()
                .filter(t->t.getTrader().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
        // 질의 7: 모든 거래에서 최고값은 얼마인가?
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(0, Integer::max);
        // 가장 작은 값을 가진 거래 탐색
        transactions.stream()
                .reduce((t1,t2)-> t1.getValue()<t2.getValue()? t1 : t2);
        // 가장 작은 값 거래, 스트림에서 min, max 제공해준다
        transactions.stream()
                .min(comparing(Transaction::getValue));


    }
}
