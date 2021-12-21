package Part5;

public class ThreadExample {

    public static void main(String args[]) throws InterruptedException {
        int x = 0;
        Result result = new Result();

        Thread thread1 = new Thread(()->result.left = f(x));
        Thread thread2 = new Thread(()->result.right = g(x));
        // 시작 이전에 join 호출시 즉시 join에서 나옴
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(result.left+ result.right);
    }

    private static int g(int x) {
        return 2;
    }

    // 오래걸리는 작업이라 가정
    private static int f(int x) {
        return 1;
    }

    private static class Result{
        private int left;
        private int right;
    }
}
