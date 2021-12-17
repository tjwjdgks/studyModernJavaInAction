package Part3;

import java.beans.Customizer;
import java.util.function.Consumer;

/*
기존
abstract class TemplateMethod{
    public void mainLogic(int number){
        System.out.println("execute mainLogic");
        additionAfterLogic(number);
    }
    abstract void additionAfterLogic(int s);
}
class Test extends TemplateMethod{

    @Override
    public void additionAfterLogic(int s) {
        System.out.println("test " + s);
    }
}
class Debug extends TemplateMethod{

    @Override
    public void additionAfterLogic(int s) {
        System.out.println("debug " + s);
    }
}
 */
class TemplateMethod{
    public void mainLogic(int number, Consumer<Integer> additionAfterLogic){
        System.out.println("execute mainLogic");
        additionAfterLogic.accept(number);
    }
}
public class TemplateMethodRunner {
    public static void run(){
        TemplateMethod templateMethodTest = new TemplateMethod();
        templateMethodTest.mainLogic(10,i -> System.out.println("test " + i));
        TemplateMethod templateMethodDebug = new TemplateMethod();
        templateMethodDebug.mainLogic(10,i -> System.out.println("debug " + i));
    }
}
