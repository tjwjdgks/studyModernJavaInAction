package Part3;

import Part2.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@FunctionalInterface
interface extcuteLambda{
    String process();
}

public class Refactoring {
    public static List<Dish> menu = Dish.menu;
    public static void practice() throws InterruptedException {
        /*
        싱글 스레드 애플리케이션에서는 메인 스레드가 종료하면 프로세스도 종료된다.
        하지만 멀티 스레드 애플리케이션에서는 실행 중인 스레드가 하나라도 있다면, 프로세스는 종료되지 않는다.
        메인 스레드가 작업 스레드보다 먼저 종료되더라도 작업 스레드가 계속 실행 중이라면 프로세스는 종료되지 않는다.
         */

        // 기존 코드
        int original = 10;
        new Thread(new Runnable() {
            int original = 10;
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " test");
            }
        }).start();
        // 익명 클래스를 람다 표현식으로 리픽터링
        new Thread(()-> {
            System.out.println(Thread.currentThread().getName() + " test");
        }).start();
        System.out.println(Thread.currentThread().getName() + " test");


        // 기존의 코드
        List<String> dishNames  = new ArrayList<>();
        for(Dish dish : menu){
            if(dish.getCalories()>300)
                dishNames.add(dish.getName());
        }
        // 개선된 코드
        // 전체 파이프라인의 의도를 쉽게 알 수 있다
        menu.stream(). // parallelStream()으로 변경하여 쉽게 병렬화 가능하다
                filter(dish -> dish.getCalories()>300)
                .map(Dish::getName)
                .collect(toList());
        // ()는 함수형 인테페이스에 매개 변수, -> 이후는 return 값
        String s = process(() -> "sss");
        System.out.println(s);
    }
    public static String process(extcuteLambda s){
        return "log: " + s.process();
    }
}
