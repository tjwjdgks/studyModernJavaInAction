package Part4;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JavaWithOptional {

    public static void practice() {
        Car car = new Car();
        // 빈 Optional
        Optional<Car> optionalEmptyCar = Optional.empty();
        // null 이 아닌 값으로 Optional 만들기, car가 null이라면 NullPointerException 발생
        Optional<Car> optionalCar = Optional.of(car);
        // null 값으로 Optional 만들기
        // ofNullable로 null을 저장할 수 있는 Optional을 만들 수 있다
        // car가 null 이면 빈 Optional 객체가 반환된다
        Optional<Car> optionalCarWithEmpty = Optional.ofNullable(car);

        // 스트림 적용 전
        Insurance insurance = new Insurance();
        String name = null;
        if(insurance != null){
            insurance.getName();
        }

        // Optional map 메서드 지원
        // Optional이 값을 포함하면 map의 인수로 제공된 함수가 값을 바꾼다 ex) Insurance ->String
        // Optional이 값이 비어있으면 아무 일도 일어나지 않는다.
        Optional<Insurance> insuranceOptional = Optional.ofNullable(insurance);
        Optional<String> insuranceNameOptional = insuranceOptional.map(Insurance::getName);

        Map<String,Object> map = new HashMap<>();
        Object value  = map.get("key"); // 값이 없을 경우 null 반환
        Optional<Object> valueOptional = Optional.ofNullable(map.get("value")); // Optional 객체 반환

    }
    // 문자열을 정수 Optional로 반환
    public static Optional<Integer> stringToInt(String s){
        try{
            return Optional.of(Integer.parseInt(s));
        }catch (NumberFormatException e){
            return Optional.empty();
        }
    }

    // Optional 적용 전
    public int readDuration(Map<String,String> map, String name){
        String value = map.get(name);
        if(value != null){
            try{
                int i = Integer.parseInt(value);
                if(i>0) return i;
            }catch (NumberFormatException e){}
        }
        return 0; // 조건 실패시 null 반환
    }
    // Optional 적용 후
    public int readDurationOpt(Map<String,String> map, String name) {
        return Optional.ofNullable(map.get(name))
                .flatMap(JavaWithOptional::stringToInt)
                .filter(i -> i > 0)
                .orElse(0);
    }
}
