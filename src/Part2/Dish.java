package Part2;

import java.util.Arrays;
import java.util.List;

public class Dish {

    public enum Type{
        MEAT,
        FISH,
        OTHER
    }
    /*
        final variables, arguments : 값이 변경되지 않도록 만듬
        final class : 클래스를 상속하지 못하도록 만듬
        final method : 메소드가 오버라이드되지 못하도록 만듬
     */
    //클래스의 멤버 변수에 final을 사용할 때는 클래스의 생성자에서 초기화할 수 있음
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                '}';
    }
    // 객체 final 한번 객체화 시킨 변수에 대해서 다른 참조자를 갖도록 할 수 없다.
    // 재할당 할 수 없다.
    // Arrays.asList는 set은 가능하지만 , AbstractList 상속 받은 ArrayList return, add remove 불가
    public static final List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 400, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
    );
}
