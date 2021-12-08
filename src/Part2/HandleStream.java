package Part2;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class HandleStream {
    private static List<Dish> menu = Dish.menu;
    public static void practice(){
        // filter, 채식 메뉴만
        List<Dish> vegetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());
        print("filter",vegetarianMenu);
        // distinct, 짝수만 + 중복 제거
        List<Integer> numbers = List.of(1,2,3,4,5,6,4);
        List<Integer> evenDistinctNumber = numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .collect(toList());
        print("distinct",evenDistinctNumber);
        List<Integer> sortedList = IntStream.range(0,20).boxed().collect(toList()); // 0~ 19 수
        // sortedList 0 ~ 19의 수
        // takeWhile, List 정렬 되었다고 가정 8 이하의 수 반환
        List<Integer> under8 = sortedList.stream()
                .takeWhile(i -> i <= 8)
                .collect(toList());
        print("takeWhile",under8);
        // sortedList 0 ~ 19의 수
        // dropWhile, List 정렬되었다고 가정 8 초과의 수 반환
        List<Integer> upper8 = sortedList.stream()
                .dropWhile(i->i<=8)
                .collect(toList());
        print("dropWhile",upper8);
        // sortedList 중 가장 좌측부터 3개 뽑기
        List<Integer> left3 = sortedList.stream()
                .limit(3)
                .collect(toList());
        print("limit",left3);
        // sortedList 중 가장 좌측부터 3개를 제외한 뽑기 (0,1,2...19 이므로 0,1,2 제외한 3,4,...19 뽑기)
        List<Integer> start3 = sortedList.stream()
                .skip(3)
                .collect(toList());
        print("skip",start3);
        // 메뉴의 각 요리명의 길이 추출하기
        // menu는 List<Dish> 이다
        List<Integer> dishName = menu.stream()
                .map(Dish::getName) // 각 Dish 객체에서 이름만 추출한다, Stream<Dish> -> Stream<String>
                .map(String::length) // 메뉴의 이름에서 길이를 추출한다, Stream<String> -> Stream<Integer>
                .collect(toList());
        print("map", dishName);
        // words 리스트에서 고유문자를 반환하기
        List<String> words = List.of("Hello","World");
        List<String> disticntS = words.stream().map(s -> s.split("")) // Stream<String[]>
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        // 세 리스트의 순서쌍 만들기
        List<Integer> number1 = List.of(1,2,3);
        List<Integer> number2 = List.of(3,4);
        List<Integer> number3 = List.of(5,6);
        List<int[]> triple = number1.stream()
                .flatMap(i -> number2.stream() //
                        .flatMap(j -> number3.stream()
                                .map(k -> new int[]{i, j, k}
                                )
                        )
                )
                .collect(toList());
        triple.stream().forEach(i->System.out.println(String.format("%d,%d,%d ",i[0],i[1],i[2])));
        // sortedList 0 ~ 19의 수
        // 10 보다 적은 수가 있는 지 확인 => 있으므로 true 리턴
        boolean under10 = sortedList.stream()
                .anyMatch(i -> i < 10);
        System.out.println(under10);
        // sortedList 0 ~ 19의 수
        // 모든 수가 적은 수가 11보다 작은 지 확인 => 만족하지 않는 수가 있으므로 false 리턴
        boolean under11 = sortedList.stream()
                .allMatch(i -> i < 11);
        System.out.println(under11);

        // sortedList 0 ~ 19의 수
        // 10 보다 적은 수가 있는 지 확인 => 있으므로 false 리턴
        boolean under10None = sortedList.stream()
                .noneMatch(i -> i < 10);
        System.out.println(under10None);
        
        // 임의의 요소 반환
        // sortedList에서 5 보다 작은 임의의 요소 반환
        Optional<Integer> any = sortedList.stream()
                .filter(i->i>5)
                .findAny();
        System.out.println(any.get());

        // 요소의 합
        // 리스트의 총합 구하기
        List<Integer> numberList = List.of(1,2,3,4);
        int reduce = numberList.stream().reduce(0, (a, b) -> a + b); // 초기값 있는 경우, Integer -> int auto Unboxing
        Optional<Integer> reduceWithOutInit = numberList.stream().reduce((a, b) -> a + b); // 초기값 없는 경우
        // 리스트 최대값 구하기
        Optional<Integer> max = numberList.stream().reduce(Integer::max);

        // 무한 스트림 iterate
        IntStream.iterate(0,n->n+4)
                .takeWhile(n->n<100)
                .forEach(System.out::println);
        // 무한 스트림
        DoubleStream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }
    private static void  print(String s, List<?> list){
        System.out.println(s);
        list.stream().forEach(i->System.out.print(i+" "));
        System.out.println();
    }

}
