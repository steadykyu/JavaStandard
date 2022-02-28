# 예외처리

# 1.1 프로그램 오류
```
컴파일 에러 : 컴파일시에 발생하는 에러
런터임 에러 : 실행시에 발생하는 에러
논리적 에러 : 실행은 되지만, 의도와 다르게 작동하는 것
```
```
에러 : 프로그램 코드에 의해서 수습될 수 없는 심각한 오류
예외 : 프로그램 코드에 의해서 수습될수 있는 다소 미약한 오류
```

# 1.2 예외 클래스의 계층구조
계층구조
```
                    Object 클래스
                    Throwable 클래스
1. Exception 클래스(예외)        2. Error클래스(에러)
```
## 1.2.1 Exception 클래스
```
1. RuntimeException : 프로그래머의 실수로 발생하는 예외
실행시 예외발생
2. Exception 클래스들 (RuntimeException 아닌): 사용자의 실수와 같은 외적인 요인에 의해 발생하는 예외
컴파일시 예외발생
```

# 1.3 예외처리하기.
```
예외처리의 
1. 정의 : 프로그램 실행시 발생할 수 있는 예외에 대비한 코드를 작성하는 것
2. 목적 : 프로그램의 비정상 종료를 막고, 정상상태를 유지하는 것

try{
      //예외가 발생할 가능성이 있는 문장
}catch (Exception1(종류) e1(참조변수)){
            //Exception1이 발생할 경우, 처리하기위한 문장을 적는다.
}catch (Exception2(종류) e1(참조변수)){      // 참조변수는 같은 변수를 사용해도 괜찮다.
            //Exception2이 발생할 경우, 처리하기위한 문장을 적는다.
}
```
+ 하나의 메서드에는 여러가지 try-catch문이 사용될 수있으며, try블럭 또는 catch블럭내에 또다른 try-catch문이 포함될 수 있다.
+ catch블럭안에 try-catch문이 있는경우에는, 같은 이름의 참조변수를 쓰면 안된다.(충돌이 일어남)
+ 만약 Exception1, Exception2 둘다 발생했다면, 이미 Exception1에서 예외를 처리하고 try-catch문을 빠져나오므로 Exception2 catch문은 수행되지 않는다.
## 1.4 try-catch문에서의 흐름
```
1. try블럭 내에세 예외가 발생한 경우
 - 발생한 예외와 일치하는 catch블럭이 있는지 확인한다.
 - 일치하는 catch블럭을 찾게 되면, 그 catch블럭 내의 문장들을 수행하고, **전체 try-catch문**을 빠져나가서 그 다음 문장을 수행한다.
 
2. try블럭 내에서 예외가 발생하지 않은 경우,
 - catch블럭을 거치지 않고 전체 try-catch문을 빠져나가서 수행을 계속한다.
```
+ try블럭에서 예외가 발생하면, 예외가 발생한 위치 이후의 try블럭 문장들은 수행되지 않으므로, try블럭내에 포함시킬 코드의 범위를 잘 선택해야한다.
 
# 1.5 예외의 발생과 catch블럭
+ 예외가 발생하면 발생한 예외에 해당하는 클래스의 인스턴스가 만들어진다.
+ catch블럭의 ()괄호 내에 선언된 참조 변수의 종류와 생성된 예외클래스의 인스턴스에 instanceOf 연산자를 이용해서 검사한다.
+ true이면 catch블럭안의 문장을 수행하고, 예외가 처리되는 것이다.
+ 모든 Exception(예외)의 조상클래스인 Exception을 ()괄호에 참조변수로 선언하면, 모든 예외를 catch로 처리할 수 있다.
 
## printStackTrace() 와 getMessage()
```
printStackTrace() : 예외 발생 당시의 호출스택(Call stack)에 있었던 메서드의 정보와 예외메세지를 화면에 출력한다.
 
getMessage() : 발생한 예외클래스의 인스턴스에 저장된 메세지를 얻을 수 있다.
```
 
## 멀티 catch블럭
+ 여러 catch블럭을 '|' 기호를 이용해서, 하나의 catch블럭으로 합칠수 있다.
+ 멀티로 한 두 예외클래스가 조상과 자손 관계면 어차피 조상만 써주는 것이랑 같기때문에, 불필요한 코드를 제거하라는 오류가뜬다.

# 1.6 예외 발생시키기
```
1. 연산자 new를 이용해서 발생시키려는 예외클래스의 객체를 만든 다음
ex) Exception e = new Exception("메세지");
2. 키워드 throw를 이용해서 예외를 발생시킬수 있다.
ex) throw e;
```
+ 생성자에 String("메세지")를 넣어주면, 이 String이 Exception 인스턴스에 메세지로 저장된다.
+ 컴파일러가 예외처리를 확인하지 않는 RuntimeException클래스들을 "unchecked예외" 라고부르며,
+ 예외처리를 확인하는 Exception클래스들을 "Checked예외"라고 부른다.

# 1.7 메서드에 예외 선언하기
+ try - catch문 말고, 메서드에 예외를 선언해줄 수 있다.
```
void method() throws Exception1,Exception2 ...{
      //메서드의 내용
```
+ 이 메서드를 사용하는 쪽에서 예외처리를 하도록 강요할 수 있다.
+ 어느 예외인지를 지정해주기때문에, 양쪽 다 견고한 프로그래밍하는데 도움을 준다.
+ Exception예외들만 적히고, RuntimeException들은 선언할 수 없다.
 
>만약 호출한 메서드에게 예외를 전달하여 예외처리를 떠맡다가, main메서드마저 throw로 전달하면 오류메세지를 출력한다.
```
 Exception in thread "main" java.lang.Exception
	at ch8_exception.Ch8_12_ExceptionEx12.method2(Ch8_12_ExceptionEx12.java:13)
	at ch8_exception.Ch8_12_ExceptionEx12.method1(Ch8_12_ExceptionEx12.java:9)
	at ch8_exception.Ch8_12_ExceptionEx12.main(Ch8_12_ExceptionEx12.java:5)
```
+ main이 method1을, method1이 method2를 호출했고 method2에서 예외가 발생한 것을 알 수 있다.(코드 참고)
+ 즉 throw로는 넘겨줄 뿐이므로, 어느 한 시점에는 try-catch를 해주어야한다.
 
# 1.8 finally 블럭
```
try{
      //예외가 발생할 가능성이 있는 문장
}catch (Exception1(종류) e1(참조변수)){
            //Exception1이 발생할 경우, 처리하기위한 문장을 적는다.
} finally
      //예외 발생여부에 관계없이 항상수행되는 문장들을 넣는다.
```
+ 만약 메서드안에 try문이 있다고해보자. 
+ try블럭안에서 return문이 실행되어 메서드를 빠져나간다고 해도, finally문장이 먼저 실행된 후에 return문을 통해 메서드를 빠져나간다.
 
# 1.9 자동 자원 반환-try with resources문
+ 주로 입출력에서 사용되는데, 입출력이후 자원을 반환하기 위해 꼭 닫아주어야하는 것들이 있다.
```java
  try(CloseableResource cr = new CloseableResource()){
            cr.exceptionWork(false); // close()가 호출
        }catch (WorkException e){
            e.printStackTrace();
        }catch (CloseException e){
            e.printStackTrace();
        }
```
+ 코드참고
+ 괄호 안에 객체를 생성하는 문장을 넣으면, 이 객체는 따로 close()를 호출하지 않아도, try블럭을 벗어나는 순간 자동적으로 close()가 호출된다.
+ 그 이후 catch나 finally 블럭이 수행된다.
+ 이 기능을 이용하려면 ()괄호 안 객체(ex CloseableResource) 가 AutoCloseable이라는 인터페이스의 close 메서드를 구현해주면 된다.

> 만약 try문과 close문에서 둘다 예외가 발생한다면 어떻게 될까?
```
ch8_exception.WorkException: WorkException 발생
	at ch8_exception.CloseableResource.exceptionWork(Ch8_20_TryWithResource.java:33)
	at ch8_exception.Ch8_20_TryWithResource.main(Ch8_20_TryWithResource.java:16)
	Suppressed: ch8_exception.CloseException: CloseException 발생!!!
		at ch8_exception.CloseableResource.close(Ch8_20_TryWithResource.java:38)
		at ch8_exception.Ch8_20_TryWithResource.main(Ch8_20_TryWithResource.java:15)
```
+ 다음과 같이 try문의 에러와 함께, close문 에러가 억제된(Suppressed) 예외로 발생한다.
+ 만약 기존의 try catch문이라면 close문의 에러만 발생했을 것이다.

# 1.10 사용자 정의 예외 만들기
``` 
class SpaceException extends Exception{
        SpaceException(String msg){
            super(msg);
        }
    }
```
+ 다음과 같이 Exception상속 받음으로써 예외를 만들 수 있다.
+ 생성자에 String 매개변수를 넣어주면, 메시지를 저장할 수 있다.
+ 실제로는 기존의 사용하는 예외를 사용하는게 좋다. 그리고 최근에는 unchecked예외가 checked예외보다 환영받고 있다.

# 1.11 예외 되던지기
+ 단 하나의 예외에 대해서 예외가 발생한 메서드와 호출한 메서드 양쪽에서 처리하도록 만들고 싶을때 사용한다.
```java
public class Ch8_22_ExceptionEx17 {
    public static void main(String[] args) {
        try {
            method1();
        } catch (Exception e) {
            System.out.println("main 메서드에서 예외가 처리되었습니다.");
        }
    }

    static void method1() throws Exception{
        try{
            throw new Exception();
        } catch (Exception e){
            System.out.println("method1메서드에서 예외가 처리되었습니다.");
            throw e; // 다시 예외 발생 시키기.
        }
    }
```
# 1.12 연결된 예외
```
Throwable initCause(Throwable cause) : 지정한 예외(cause)를 원인 예외로 등록
Throwable getCause() : 원인 예외를 반환
```
```
throw new RuntimeException(new MemoryException("메모리가 부족합니다."));
```
+ checked예외를 unchecked예외로 바꾸어, 억지로 예외처리를 하지 않게 만들 수 있다.
```java
        try {
            startInstall();             //설치준비  - 여기서 에러발생한다고 가정
            copyFiles();                //복사
        } catch (SpaceException se) {
            InstallException ie = new InstallException("se 원인으로한 설치 중 예외발생");
            ie.initCause(se);                                        // 지정한 예외를 원인 예외로 등록
            throw ie;
```
```
ch8_exception.InstallException: se 원인으로한 설치 중 예외발생
	at ch8_exception.Ch8_23_ChainedExceptionEx.install(Ch8_23_ChainedExceptionEx.java:19)
	at ch8_exception.Ch8_23_ChainedExceptionEx.main(Ch8_23_ChainedExceptionEx.java:6)
  
Caused by: ch8_exception.SpaceException: 설치할 공간이 부족합니다.
	at ch8_exception.Ch8_23_ChainedExceptionEx.startInstall(Ch8_23_ChainedExceptionEx.java:33)
	at ch8_exception.Ch8_23_ChainedExceptionEx.install(Ch8_23_ChainedExceptionEx.java:16)
	... 1 more
```
결과값(코드참고)
