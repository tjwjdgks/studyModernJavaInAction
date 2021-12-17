package Part3;

import java.util.function.Function;

@FunctionalInterface
interface CustomFunctionalInterface<T,U,V,R>{
   R apply(T t, U u, V v);
}
public class CustomFunctionalInterfaceRunner<T,U,V,R>{
   public static void run(){
      CustomFunctionalInterface<String,String,String,String> t = (String i, String v, String c)-> i+v+c;
      String apply = t.apply("custom ", "function ", "interface!");
      System.out.println(apply); // output : custom function interface!
   }
}
