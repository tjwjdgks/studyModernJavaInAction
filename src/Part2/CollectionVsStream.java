package Part2;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class CollectionVsStream {
    // 자바 8 이전의 과정
    private static void doCollection(List<Dish> menu){
        List<Dish> lowDish= new ArrayList<>();
        for(Dish dish : menu){
            if(dish.getCalories() <400)
                lowDish.add(dish);
        }
        Collections.sort(lowDish, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(),o2.getCalories());
            }
        });
        List<String> lowCaloricDishName = new ArrayList<>();
        for(Dish dish : lowDish)
            lowCaloricDishName.add(dish.getName());

        // print
        for(String dishName : lowCaloricDishName){
            System.out.println(dishName);
        }
    }
    // 최신 코드 자바 8 - 간단하게 만들 수 있다.
    private static void doStream(List<Dish> menu){
        List<String> lowCaloricDishName = menu.stream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
        lowCaloricDishName.stream().forEach(System.out::println);

    }

    public static void Runner(){
        // collection 이용할 때
        doCollection(Dish.menu);
        // stream 이용할 때 // 코드 간편해진다
        doStream(Dish.menu);

    }
}
