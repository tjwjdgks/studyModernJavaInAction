package Part1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FilteringApple {

    private static List<Apple> inventory = Arrays.asList(
            new Apple(80, "green"),
            new Apple(155, "green"),
            new Apple(120, "red"));

    public static void runFilteringApple(){
        // 기존 방법
        System.out.println(filterGreenApple(inventory));
        System.out.println(heavyApple(inventory));
        // 자바 함수 사용
        System.out.println(filterApples(inventory,FilteringApple::isGreenApple));
        System.out.println(filterApples(inventory,FilteringApple::isHeavyApple));
        // 익명 객체 사용
        System.out.println(filterApples(inventory,(Apple apple) -> "green".equals(apple.getColor())));
        // 익명 객체 사용 - 같은 형일 시 생략 가능
        System.out.println(filterApples(inventory,apple -> apple.getWeight()>150));
    }
    // 기존의 방법 <8
    private static List<Apple> filterGreenApple(List<Apple> inventory){
        System.out.print("기존 방법 : ");
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if("green".equals(apple.getColor()))
                result.add(apple);
        }
        return result;
    }
    private static List<Apple> heavyApple(List<Apple> inventory){
        System.out.print("기존 방법 : ");
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(apple.getWeight()>150)
                result.add(apple);
        }
        return result;
    }

    // functional 함수 사용
    private static boolean isGreenApple(Apple apple){
        return "green".equals(apple.getColor());
    }
    private static boolean isHeavyApple(Apple apple){
        return apple.getWeight()>150;
    }

    private static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> predicate){
        System.out.print("자바 8 함수 사용 : ");
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(predicate.test(apple))
                result.add(apple);
        }
        return result;
    }

    private static class Apple{
        private int weight;
        private String color;

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public String getColor() {
            return color;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return String.format("%s %d", color, weight);
        }
    }
}
