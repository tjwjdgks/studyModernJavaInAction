package Part3;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/*
abstract class ProcessingObject<T>{
    protected ProcessingObject<T> successor;
    public void setSuccessor(ProcessingObject<T> successor) {
        this.successor = successor;
    }
    public T handle(T input){
        T r = handleResult(input);
        if( successor != null){
            return successor.handle(r);
        }
        return r;
    }
    abstract protected T handleResult(T t);
}

class handlerOne extends ProcessingObject<String>{

    @Override
    protected String handleResult(String s) {
        return s+ "handlerOne\n";
    }
}
class handlerTwo extends ProcessingObject<String>{

    @Override
    protected String handleResult(String s) {
        return s+ "handlerTwo\n";
    }
}

 */
public class ChainOfResponsibilityRunner {
    public static void run() {
        /*
        ProcessingObject<String> handlerOne = new handlerOne();
        ProcessingObject<String> handlerTwo = new handlerTwo();
        handlerOne.setSuccessor(handlerTwo);
        String end =  handlerOne.handle("시작\n");
        System.out.println(end);
         */
        // 람다 사용
        UnaryOperator<String> handlerOne = (String text) -> text + "handlerOne\n";
        UnaryOperator<String> handlerTwo = (String text) -> text + "handlerTwo\n";
        Function<String,String> pipline = handlerOne.andThen(handlerTwo);
        String end = pipline.apply("시작\n");
        System.out.println(end);


    }
}
