package Part4;

interface A{
    default void test(){
        System.out.println("test a");
    }
}
//B는 A 상속만하고 정의 안하므로 A 인터페이스 사용
interface B extends A{

}
// A의 디폴트 메서드와 같은 추상 메서드를 정의 했으므로 A -> C 사용
interface C extends A{
    void test();
}
// 서브 인터페이스가 이기므로 A와 C 중 서브 인터페이스인 C 메서드를 사용하므로 test 구현해야 한다
public class DefaultMethod implements B,C {

    @Override
    public void test() {

    }
}
