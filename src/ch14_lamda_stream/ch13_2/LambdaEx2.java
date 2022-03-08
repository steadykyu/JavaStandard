package ch14_lamda_stream.ch13_2;


@FunctionalInterface
interface MyFunction {
    void myMethod();  // public abstract void myMethod();
}

class LambdaEx2 {
    public static void main(String[] args) 	{
        MyFunction f = ()->{}; // MyFunction f = (MyFunction)(()->{});      // 생략된 상태
        Object obj = (MyFunction)(()-> {});  // Object타입으로 형변환이 생략됨
        String str = ((Object)(MyFunction)(()-> {})).toString();

        System.out.println(f);
        System.out.println(obj);
        System.out.println(str);

//		System.out.println(()->{});	// 에러. 람다식은 Object타입으로 형변환 안됨
        System.out.println((MyFunction)(()-> {}));      // 인터페이스를 구현한 익명객체
//		System.out.println((MyFunction)(()-> {}).toString()); // 에러
        System.out.println(((Object)(MyFunction)(()-> {})).toString()); // 익명객체를 Object객체로 변환후 사용
    }
}
