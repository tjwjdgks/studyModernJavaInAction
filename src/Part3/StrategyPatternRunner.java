package Part3;


// 예시 String 포맷을 다양한 조건에 맞게 검증해야 한다
// strategy 패턴 사용
interface StrategyPattern{
    boolean execute(String s);
}

// 모든 글자 소문자 검증
class IsAllLowerCase implements StrategyPattern{

    @Override
    public boolean execute(String s) {
        return s.matches("[a-z]+");
    }
}
// 모든 글자 숫자 검증
class IsNumeric implements StrategyPattern{

    @Override
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}
class Validator{
    private final StrategyPattern strategyPattern;
    public Validator(StrategyPattern strategyPattern) {
        this.strategyPattern = strategyPattern;
    }
    public boolean validate(String s){
        return strategyPattern.execute(s);
    }
}
public class StrategyPatternRunner{
    public static void run(){
        // 람다 사용 x
        Validator validatorNumeric = new Validator(new IsNumeric());
        boolean v1 = validatorNumeric.validate("test"); // true
        Validator validatorLowerCase = new Validator(new IsAllLowerCase());
        boolean v2 = validatorLowerCase.validate("test"); // false;
        System.out.println(v1 +" "+v2);

        // 람다 사용 o
        Validator validatorNumericLambda = new Validator((String s) -> s.matches("[a-z]+"));
        boolean v1Lambda = validatorNumeric.validate("test"); // true
        Validator validatorLowerCaseLambda = new Validator((String s) -> s.matches("\\d+"));
        boolean v2Lambda = validatorLowerCase.validate("test"); // false;
        System.out.println(v1Lambda +" "+v2Lambda);
    }
}
