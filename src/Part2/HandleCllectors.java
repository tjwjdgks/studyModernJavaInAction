package Part2;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class HandleCllectors {
    static class test{
        int a;
        String s;

        public test(int a, String s) {
            this.a = a;
            this.s = s;
        }

        @Override
        public String toString() {
            return "test{" +
                    "s='" + s + '\'' +
                    '}';
        }
    }
    private static List<Dish> menu = Dish.menu;

    public static void pricatice(){
        // maxBy
        Comparator<Dish> dishCalories = comparingInt(Dish::getCalories);
        Optional<Dish> maxDish  = menu.stream().collect(maxBy(dishCalories));
        // Comparator, stream 내에서.
        Optional<Dish> maxDish_innerStream = menu.stream().collect(maxBy(comparing(Dish::getCalories)));
        // minBy
        Optional<Dish> minDish_innerStream = menu.stream().collect(minBy(comparing(Dish::getCalories)));
        print(maxDish_innerStream);
        print(minDish_innerStream);

        // 요약연산
        // summingInt int -> Integer
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
        // averagingInt int -> Double
        double averageCalories = menu.stream().collect(averagingInt(Dish::getCalories));
        // summarizingInt int-> IntSummaryStatistics 안에 count, sum, min, max 와 average 제공
        IntSummaryStatistics statisticsCalories = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(statisticsCalories);

        // 문자열 연결
        // CharSequence -> String
        String meueToString = menu.stream().map(Object::toString).collect(joining());
        // 구분자 "," 가 들어간다
        String meueToStringDeimiter = menu.stream().map(Object::toString).collect(joining(","));


        // 그룹화를 하기 전에 프레디케이트로 필터를 적용했을 때
        Map<Dish.Type, List<Dish>> collect = menu.stream()
                .filter(dish -> dish.getCalories() > 500)
                .collect(groupingBy(Dish::getType));
        // 결과 caloricDishesByType : {OTHER=[french fries, pizza], MEAT=[pork, beef]}

        // groupingBy는 Collector 받는다, Collections.filtering 함수 Collector 반환
        Map<Dish.Type, List<Dish>> caloricDishesByType = menu.stream().
                collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));
        //결과 caloricDishesByType : {OTHER=[french fries, pizza], MEAT=[pork, beef], FISH=[]}

        // Mapping 사용, DishType과 Name으로 그룹화
        Map<Dish.Type, List<String>> groupDishTypeAndName = menu.stream().
                collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));
        // dishTags를 참조해서 그룹화된 요리들의 tag값들을 얻고 싶을 때
        Map<String, List<String>> dishTags = new HashMap<>();
        dishTags.put("pork", Arrays.asList("greasy", "salty"));
        dishTags.put("beef", Arrays.asList("salty", "roasted"));
        dishTags.put("chicken", Arrays.asList("fried", "crisp"));
        dishTags.put("rice", Arrays.asList("light", "natural"));
        Map<Dish.Type, Set<String>> dishNamesByType = menu.stream()
                .collect(groupingBy(
                        Dish::getType,
                        flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())
                        )
                );
        // 다수준 그룹화 Type과 칼로리로 그룹화
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishTypeAndCalories = menu.stream().collect(groupingBy(
                Dish::getType,
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400)
                        return CaloricLevel.DIET;
                    else if (dish.getCalories() > 700)
                        return CaloricLevel.FAT;
                    return CaloricLevel.NORMAL;
                })
        ));
        Map<Dish.Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));
        //{MEAT=3, FISH=2, OTHER=4}

        // 각 서브그룹에서 가장 칼로리 높은 그룹 찾기, 각 그룹의 인자는 적어도 1개 이상 존재 -> Optional 객체로 받을 필요가 없다
        Map<Dish.Type, Dish> highestCaloricEachGroup = menu.stream().collect(groupingBy(Dish::getType,
                collectingAndThen(
                        maxBy(comparing(Dish::getCalories)),
                        Optional::get
                )));

        menu.stream().collect(groupingBy(Dish::getType,
                mapping(dish->{
                    if(dish.getCalories()<=400) return CaloricLevel.DIET;
                    if(dish.getCalories()>700) return CaloricLevel.FAT;
                    return CaloricLevel.NORMAL;
                    },
                toCollection(HashSet::new))));

        // 채식 요리 분할
        Map<Boolean, List<Dish>> vegetarianMap = menu.stream().collect(partitioningBy(Dish::isVegetarian));
        // 채식 요리
        List<Dish> dishes = vegetarianMap.get(true);
        // 채식 요리 X
        List<Dish> dishes1 = vegetarianMap.get(false);


    }
    public static void print(Optional<?> t){
        System.out.println();
    }
}
