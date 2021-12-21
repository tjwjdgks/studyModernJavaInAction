package Part5;

public class CellRunner {
    public static void main(String args[]){
        SimpleCell c3 = new SimpleCell("c3");
        SimpleCell c2= new SimpleCell("c2");
        SimpleCell c1= new SimpleCell("c1");

        c1.subscribe(c3);
        c1.onNext(10);
        c2.onNext(20);
    }
}
