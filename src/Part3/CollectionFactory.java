package Part3;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class CollectionFactory {

    public static void practice(){
        // 일반 배열 만들기 // Set도 아래와 같다
        List<String> friendsList = new ArrayList<>();
        friendsList.add("Raphael");
        friendsList.add("Olivia");
        friendsList.add("Thibaut");
        // Array.asList 사용
        // 요소를 갱신할 수 있지만 추가나 삭제는 못함 (UnsupportedOperationException 발생)
        List<String> friendsListArrayFactory = Arrays.asList("Raphael","Olivia","Thibaut");
        // Array.asSet 이라는 팩토리 메서드는 없다 따라서 Set을 만들기 위해서는 다른 방법이 필요하다
        // 이 경우 내부적으로 불필요한 객체 할등을 필요로 한다
        Set<String> friendsSetArraysFactory = new HashSet<>(Arrays.asList("Raphael","Olivia","Thibaut"));
        Set<String> friendsSetStream = Stream.of("Raphael", "Olivia", "Thibaut").collect(toSet());

        // List.of, Set.of 메서드 제공
        // 요소를 변경할 수 없음 (UnsupportedOperationException 발생)
        List<String> friendsListFactory = List.of("Raphael", "Olivia", "Thibaut");
        // 바꿀 수 없는 집합
        // 요소에 중복된 값이 들어가 있으면 IllegalArgumentException 발생한다
        Set<String> friendsSetFactory = Set.of("Raphael", "Olivia", "Thibaut");
        // Map.of 메서드도 제공한다 (바꿀수 없는 맵) // 키와 값을 번달아 제공하는 방법 // 열개 이하의 작은 맵에서 유용하다
        Map<String, Integer> friendsMapFactory = Map.of("Raphael", 25, "Olivia", 32, "Thibaut", 43);
        // Map.ofEntries 메서드
        Map<String, Integer> friendsMapEntryFactory = Map.ofEntries
                (Map.entry("Raphael", 25), Map.entry("Olivia", 32), Map.entry("Thibaut", 43));

        // 초기의 값
        List<String> transactions = new ArrayList<>();
        transactions.add("soup");
        transactions.add("2banana");
        transactions.add("3apple");
        transactions.add("juice");

        // 숫자로 시작되는 원소 삭제 // ConcurrentModificationException 발생
        /*
        for(String transaction : transactions){
            if(Character.isDigit(transaction.charAt(0))){
                transactions.remove(transaction);
            }
        }
        // 위의 코드는 이와 같다
        for(Iterator<String> iterator = transactions.iterator(); iterator.hasNext();){
            String transaction = iterator.next();
            if(Character.isDigit(transaction.charAt(0))){
                transactions.remove(transaction);
            }
        }
         */
        /*
        for(Iterator<String> iterator = transactions.iterator(); iterator.hasNext();){
            String transaction = iterator.next();
            if(Character.isDigit(transaction.charAt(0))){
                iterator.remove();
            }
        }
        */
        //transactions.removeIf(transaction->Character.isDigit(transaction.charAt(0)));
        /*
        for(ListIterator<String> listIterator = transactions.listIterator();listIterator.hasNext();){
            String transaction = listIterator.next();
            listIterator.set(transaction.toUpperCase());
        }
         */
        transactions.replaceAll(transaction -> transaction.toUpperCase()); // transactions.replaceAll(String::toUpperCase);
        print(transactions);

        for(Map.Entry<String,Integer> entry : friendsMapFactory.entrySet()){
            String name = entry.getKey();
            Integer age = entry.getValue();
            System.out.println(name + " is age " + age);
        }
        friendsMapFactory.forEach((name,age)->System.out.println(name + " is age " + age));
        // 정렬한 결과를 출력
        friendsMapFactory.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEachOrdered(System.out::println);

        Integer newFriend = friendsMapFactory.getOrDefault("Olivia", 33); // map에 Olivia=32 이므로 32를 반환한다
        Integer newFriend1 = friendsMapFactory.getOrDefault("newFriend", 22);// key에 값이 map에 없으므로 기본값인 33를 반환한다
        System.out.println(newFriend);
        System.out.println(newFriend1);

        Map<String,Integer> mutableMap = new HashMap<>(friendsMapFactory);
        // computeIfAbsent
        // "newFriend" key가 없으므로 두번째 함수로 value를 계산해 map에 들어간다(key의 길이가 들어간다)
        mutableMap.computeIfAbsent("newFriend",String::length);
        System.out.println(mutableMap.get("newFriend")); // output 9
        // computeIfPresent
        // "newFriend" : 9 이 들어있는 상태이므로 두 번째 인자의 함수로 계산된 값이 map에 들어간다
        mutableMap.computeIfPresent("newFriend",(key,value)-> value+1);
        System.out.println(mutableMap.get("newFriend")); // output 10
        // compute "newFriend" : 10에 key에 해당하는 값으로 두번째 함수를 통해 계산된 값이 map에 들어간다
        mutableMap.compute("newFriend",(key,value)->value*2);
        System.out.println(mutableMap.get("newFriend")); // output 20

        // remove(k,v)
        // "newFriend" : 20 인 상태이다
        mutableMap.remove("newFriend",10); // 조건에 부합되지 않으므로 삭제 x
        System.out.println(mutableMap.containsKey("newFriend")); // true
        mutableMap.remove("newFriend",20); // 조건에 부합되므로 삭제 o
        System.out.println(mutableMap.containsKey("newFriend")); // false

        Map<String, String> map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        // value 대문자로 변경
        map.replaceAll((key,value)->value.toUpperCase());
        map.replace("key1","key2");

        Map<String, String> firstMap  = new HashMap<>();
        firstMap.put("firstMapKey1","firstMapValue1");
        firstMap.put("firstMapKey2","firstMapValue2");
        firstMap.put("testKey","testValue");
        Map<String, String> secondMap=  new HashMap<>();
        secondMap.put("secondMapKey1","secondMapValue1");
        secondMap.put("secondMapKey2","secondMapValue2");
        secondMap.put("secondMapKey3","secondMapValue3");
        secondMap.put("testKey","testValue2");

        firstMap.putAll(secondMap);
        Map<String,String> everyone= new HashMap<>(firstMap);
        secondMap.forEach((k,v)->
                everyone.merge(k,v,(firstVal,secondVal)->firstVal+ "&" +secondVal));
        System.out.println(firstMap);
    }
    private static void print(List<?> list){
        list.stream().forEach(e->System.out.print(e+" "));
        System.out.println();
    }


}
