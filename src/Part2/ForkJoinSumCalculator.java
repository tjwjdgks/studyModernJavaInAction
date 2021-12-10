package Part2;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long> { // RecursiveTask 상속
    public static final long THRESHOLD = 10_000;

    private final long[] numbers;
    private final int start;
    private final int end;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if(length<=THRESHOLD){
            return computeSequentially();
        }
        // 배열의 절반을 더하도록 첫번째 서브테스크를 생성한다
        ForkJoinSumCalculator left = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        // ForkJoinPool의 다른 스레드로 새로 생성한 테스크를 비동기로 실행한다 // 새로운 스레드
        left.fork();
        // 배열의 나머지 절만을 더하도록 두번째 서브테스크 생성한다
        ForkJoinSumCalculator right = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        // 두번째 서브 테스크를 동기 실행한다 현재 스레드에서 실행
        Long rightCompute = right.compute();
        // 첫번째 서브 테스크(새로운 스레드)의 결과를 읽거나 결과가 없으면 기다린다
        Long leftCompute = left.join();
        return rightCompute + leftCompute;
    }
    // 분할이 불가능 할때 순차적으로 서브테스크 결과를 계산
    private long computeSequentially() {
        long sum = 0;
        for(int i=start;i<end;i++){
            sum += numbers[i];
        }
        return sum;
    }

}
