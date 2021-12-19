import Part2.Dish;
import Part2.HandleCllectors;
import Part2.HandleStream;
import Part3.*;
import Part4.JavaWithOptional;
import Part4.NewCalendarAndTime;

import javax.tools.JavaCompiler;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toList;

public class Main {
    private static List<Dish> menu = Dish.menu;

    public static void main(String[] args) throws InterruptedException {
        NewCalendarAndTime.practice();
    }
}
