# 1. 프로세스와 쓰레드
+ 프로세스란 간단히 말해서 실행중인 프로그램이다.
+ 프로세스는 프로그램을 수행하는데 필요한 데이터와 메모리 등의 자원, 그리고 쓰레드로 구성되어있다.
+ 여기서 프로세스의 자원을 이용해서 실제로 작업을 수행하는 것이 쓰레드이다.
+ 최소하나 이상의 쓰레드가 존재하며, 둘 이상의 쓰레드를 가진 프로세스를 멀티쓰레드 프로세스라고한다.

### 멀티태스킹과 멀티쓰레딩
+ 대부분의 OS는 멀티태스킹을 지원하므로, **여러개의 프로세스**가 동시에 실행될 수 있다.
+ 멀티쓰레딩은 **하나의 프로세스 내**에서 여러 쓰레드가 동시에 작업을 수행하는 것이다.

### 멀티쓰레딩의 장단점
> 장점
- CPU의 사용률을 향상시킨다.
- 자원을 보다 효율적으로 사용할 수 있다.
- 사용자에 대한 응답성이 향상된다.
- 작업이 분리되어 코드가 간결해진다.

> 예시
+ 메신저로 채팅하면서 파일을 다운로드 받거나 음성대화를 나눌수 있는 이유는 멀티쓰레드로 작성되어 있기 때문이다.
+ 여러 사용자에게 서비스하는 서버프로그램의 경우 멀티쓰레드로 작성하는 것은 필수적이다.
```
하나의 서버 프로세스가 여러개의 쓰레드를 생성해서 쓰레드와 사용자의 요청이 일대일로 처리되도록 프로그래밍 해야한다.
만약 싱글쓰레드로 작성한다면 사용자의 요청마다 새로운 프로세스를 생성해야하는데, 이는 쓰레드를 생성하는것에 비하면 더많은 시간과 메모리공간이 필요하다.
```

> 단점
+ 여러 쓰레드가 같은 프로세스 내에서 자원을 공유하면서 작업하기때문에, 동기화(synchronization), 교챡상태(deadlock)와 같은 문제들을 고려해서 신중히 프로그래밍해야한다.

# 2. 쓰레드의 구현과 실행
+ Thread 클래스를 상속받는 방법과 Runnable 인터페이스를 구현하는 방법이 있다.
+ 클래스는 단일 상속밖에 하지 못하므로, 객체지향적인 방법으로 작성하기위해 Runnable 인터페이스를 구현하는것이 일반적이다.
```java
class ThreadEx1 {
    public static void main(String args[]) {
        ThreadEx1_1 t1 = new ThreadEx1_1();

        Runnable r  = new ThreadEx1_2();
        Thread   t2 = new Thread(r);	  // 생성자 Thread(Runnable target)

        t1.start();
        t2.start();
    }
}

class ThreadEx1_1 extends Thread {
    public void run() {
        for(int i=0; i < 5; i++) {
            System.out.println(getName()); // 조상인 Thread의 getName()을 호출
                                           // default 이름인 Thread-0 출력
        }
    }
}

class ThreadEx1_2 implements Runnable {
    public void run() {
        for(int i=0; i < 5; i++) {
            // Thread.currentThread() - 현재 실행중인 Thread를 반환한다.
            System.out.println(Thread.currentThread().getName());   //default 이름인 Thread-1 출력
        }
    }
}
```
+ Runnable 인터페이스의 경우 Runnable인터페이스를 구현한 클래스의 인스턴스를 생성한 다음, 이 인스턴스를 Thread 클래스의 생성자의 매개변수로 제공해야한다.
+ 참고로 Thread의 이름은 생성자나 메서드를 통해서 지정 또는 변경할 수 있다.
```
Thread(Runnable target, String name)
Thread(String name)
void setName(String name)
```

### 쓰레드의 실행-start()
+ 쓰레드를 생성했다고 해서 자동으로 실행되는 것은 아니다. start()를 호출해야만 쓰레드가 실행된다.
+ 한번 실행이 종료된 쓰레드는 다시 실행할수 없다.
+ 만약 다시 실행하려면 다시 생성해서 start();로 실행해주어야 한다.

# 3.start()와 run()
+ 책의 그림, 코드 참고하면 더 쉽게 이해가능.
> run()
```
main() 메서드에서 run()을 호출하는 것은 단순히 클래스에 선언된 메서드를 호출하는것 뿐이다.
정확히는 main쓰레드에 존재하는 호출스택에서 main()-run() 순으로 메서드가 입력되고 반대 순으로 수행된다. 
```

> start()
```
1. main메서드에서 쓰레드의 start()를 호출한다.
2. start()는 새로운 쓰레드를 생성하고, 쓰레드가 작업하는데 사용될 호출스택을 생성한다.
3. 새로 생성된 호출스택에 run()이 호출되어, 쓰레드가 독립된 공간에서 작업을 수행한다.
4. 이제는 호출스택이 2개(main 쓰레드, 생성한 쓰레드) 이므로 스케줄러가 우선순위를 통해 정한 순서에 의해 번갈아 가면서 실행된다.
```
+ run()또는 main()중 수행이 종료된 쪽은 호출스택이 모두 비워지면서 쓰레드가 사용하던 호출스택이 사라진다.

### main쓰레드
+ 이미 우리는 지금까지 main메서드의 작업을 수행하는 main쓰레드를 이용해 왔다.
+ 기본적으로 **하나의 쓰레드(main쓰레드)를 생성하고 호출스택을 생성하여** main메서드를 호출해서 작업이 수행한다.
+ 만약 main쓰레드와 다른 쓰레드가 생성되어 작업이 수행된다고 해보자. 이때 먼저 수행된 쪽이 호출스택이 사라진다.
+ 일반적으로 우리는 main 쓰레드를 사용하고 main 호출스택이 끝나면 프로그램이 종료되었지만, 여러 쓰레드가 존재할때는 main이 끝나더라도 
다른 쓰레드가 수행중이라면 프로그램이 종료되지 않는다.
+ **결론 :  실행중인 사용자 쓰레드가 하나도 없을때만 프로그램은 종료된다.**

# 4.싱글쓰레드와 멀티쓰레드
+ 책의 그림 꼭 보면서 하기
+ 하나의 쓰레드로 작업하는 경우는 한작업을 마치고 다른작업을 시작한다.
+ 두개의 쓰레드인 멀티쓰레드는 짧은시간동안 2개의 쓰레드를 번갈아가면서 작업을 수행해서 동시에 두 작업이 처리되는 것과 같이 느끼게 한다.
+ **단순히 CPU만**을 사용하는 작업이라면 오히려 멀티쓰레드로 작업할때 작업시간이 더 걸리게 되는데, 이는 작업전환(context switching)에 시간이 걸리기 때문이다.
+ 작업 전환 : 현재 진행중인 상태나 정보(위치)를 저장하고, 다른 정보를 읽어오는데 시간이 소요된다.

### 싱글 코어와 멀티코어
+ 싱글 코어일떄는 하나의 코어가 번갈아가면서 쓰레드 수행 작업을 수행하므로 두 작업이 절대 겹치지 않는다.
+ 멀티 코어일때는 여러 코어가 작업하기때문에 동시에 두 쓰레드를 실행하는 구간들이 존재한다.

### 참고
+ 두 쓰레드가 서로 다른 자원을 사용하는 작업의 경우에는 싱글쓰레드 프로세스보다 멀티쓰레드 프로게스가 더 효율적이다.
+ ex) 데이터를 입력받는 작업, 네트워크로 파일을 주고받는 작업, 프린터 출력 등 외부 기기와의 입출력을 필요로 하는 경우
****
+ 자바가 OS(플랫폼) 독립적이라고 하지만 실제로는 OS종속적인 부분으로 쓰레드가 있다.
+ OS 프로세스 스케줄러의 영향을 받아 실행순서와 실행시간이 결정되므로, 매순간 상황에 따라 프로세스에게 할당되는 시간이 일정하지 않고, 
쓰레드 에게 할당되는 시간 역시 일정하지 않게 된다. 
+ 일단 이러한 불확실성이 존재한다는 점을 알고만 있자.

# 5. 쓰레드의 우선순위
+ 쓰레드는 우선순위(priority)라는 final 멤버변수를 가지고 있는데, 이 우선순위 값에 따라 쓰레드가 얻는 실행시간이 달라진다.
+ 시각적인 부분이나 사용자에게 우선적으로 빠르게 반응해야 하는 작업을 쓰레드 우선순위를 정해주어 수행해줄수 있다.(ex 채팅 > 파일 다운로드)

### 쓰레드의 우선순위 지정하기
```
void setPriority(int newPriority) //쓰레드의 우선순위를 지정한 값으로 변경
int  getPriority()                // 쓰레드의 우선순위를 반환
```
+ 쓰레드의 우선순위 범위는 1~10이며 숫자가 높을수록 우선순위가 높다.
+ 쓰레드의 우선순위는 쓰레드를 생성한 쓰레드로부터 상속받는다.
+ 그러므로 main메서드를 수행하는 쓰레드는 우선순위가 5이므로, main메서드에서 생성한 쓰레드의 우선순위는 5가된다.
+ 우선순위가 다르다면 우선순위가 높은쪽에 상대적으로 더 많은 실행시간이 주어진다.

### 참고
+ 쓰레드는 특정 OS스케쥴링 정책과 JVM구현에 종속적이다.
+ 그러므로 굳이 쓰레드에 우선순위를 부여하는 대신 **작업**에 우선순위를 두어 PriorityQueue에 저장해두고, 우선순위가 높은 작업이 먼저 처리되도록 하는것이 나을수도 있다.

# 6. 쓰레드 그룹
+ 서로 관련된 쓰레드를 그룹으로 모으기위한 것으로, 폴더라고 생각하면 편하다.
+ ThreadGroup() 생성자를 통해 생성할 수 있다.
+ 쓰레드를 쓰레드 그룹에 포함시키려면 Thread() 생성자를 이용해야한다.
```
Thread(ThreadGroup group, String name)                      // group에 name 이름으로 Thread 생성
Thread(ThreadGroup group, Runnable target)                  //
Thread(ThreadGroup group, String name, Runnable target)
Thread(ThreadGroup group, String name, Runnable target, long stacksize)
```
+ 모든 쓰레드는 반드시 쓰레드 그룹에 포함되어야한다.
+ 쓰레드 그룹을 지정하는 생성하지를 사용하지 않은 쓰레드는 **기본적으로** **자신을 생성한 쓰레드와 같은 쓰레드 그룹**에 속하게 된다.
+ 그러므로 우리가 생성하는 모든 쓰레드 그룹은 main쓰레드 그룹의 하위 쓰레드 그룹이된다.
+ 또한 쓰레드그룹을 지정하지 않고 생성한 쓰레드는 **자동적으로** main쓰레드 그룹에 속하게 된다.

> ThreadGroup과 관련된 Thread 메서드
```
ThreadGroup getThreadGroup()                                // 자신이 속한 쓰레드 그룹을 반환한다.
void uncaughtException(Thread t, Throwable e)              
// 쓰레드 그룹의 쓰레드가 처리되지 않은 예외에 의해 실행이 종료되었을때, Jvm에 의해 이 메서드가 자동적으로 호출된다.
```
+ ThreadGroup 생성자와 메서드는 책을 참고하자
+ 코드결과를 확인해보자!

# 7. 데몬 쓰레드
+ 데몬쓰레드는 다른 일반 쓰레드(데몬이 아닌쓰레드)의 작업을 돕는 보조적인 역할을 수행하는 쓰레드이다.
+ 일반 쓰레드가 종료되면 데몬쓰레드는 강제적으로 자동종료된다.(존재의미가 없기떄문)
+ 예로는 가비지컬렉터, 워드프로세서 자동저장, 화면자동갱신등이 있다.
```
boolean isDaemon()              : 쓰레드가 데몬쓰레드 인지 확인한다.
void setDaemon(boolean on)      : 쓰레드를 데몬 쓰레드로 또는 사용자 쓰레드로 변경한다.
```
+ setDaemon 메서드는 반드시 start() - (쓰레드 생성및 실행) 를 호출하기 전에 실행되어야 한다.
+ 참고 : getAllStackTrace() 를 활용하면 실행 중 또는 대기상태인, 작업이 완료되지 않은 모든 쓰레드의 호출스택을 출력할 수 있다.(데몬쓰레드포함)
+ 코드 참고하자

# 8. 쓰레드의 실행제어
+ 쓰레드 프로그래밍이 어려운 이유는 동기화와 스케줄링때문이다.
+ 효율적인 멀티쓰레드 프로그램을 만들기 위해서는 보다 정교한 스케줄링을 통해 프로세스에게 주어진 자원과 시간을 여러 쓰레드가 낭비없이 잘 사용하도록 프로그래밍 해야한다.
> 쓰레드의 스케줄링 관련 메서드
```
sleep()
join()
interrupt()
stop()
suspend()
resume()
yield()
```
+ 자세한 내용은 책이나 공식문서를 참고하자
+ resume(), stop(), suspend()는 교착상태로 만들기 쉬워 deprecated 되었다.

> 쓰레드의 상태
```
NEW
RUNNABLE
BLOCKED
WAITING, TIME_WAITING
TERMINATED
```
### sleep(long millis, int nanos)
+ 일정 시간동안 쓰레드를 멈추게 하여 일시정지 상태가 되게 만든다.
+ Thread.sleep(millis)  형태로 사용하며 1000분의 1초단위이다.
+ sleep()에 의해 일시정지 상태가 된 쓰레드는 지정된 시간이 다 되거나 interrupt()가 호출되면(InterruptedException 발생) , 잠에서 깨어나 실행대기 상태가 된다.
+ 예외가 발생할수 있으므로 try-catch문으로 처리해 주어야한다.
+ sleep()은 항상 현재 실행중인 쓰레드에 대해 작동한다. 그래서 main메서드에서 실행시에는 다른 쓰레드의 참조변수로(ex- th.sleep())을 수행하더라도 main쓰레드에 영향을 준다.
+ 그러므로 static으로 선언되어 있으며 참조변수를 이용해서 호출하기 보다는 Thread.sleep(); 로 해야한다.

### interrupt()와 interrupted()
+ 쓰레드에게 작업을 멈추라고 요청한다.
+ 단지 멈추라고 요청하는 것뿐 종료시키지는 못한다.
+ 인스턴스 변수인 interrupted()의 상태를 바꾼다.(interrupt 호출시 - true, 호출되지않았을시 - false)
```
void interrupt()                    : 현재 쓰레드의 interrupted 상태를 true로 변경
boolean siInterrupted()             : 현재 쓰레드의 interrupted 상태를 반환
static boolean interrupted()        : 현재 쓰레드의 interrupted 반환후, false로 변경
```
+ 만약 일시정시 상태(sleep,wait,join)에 있을때 interrupt를 호출하면 InterruptedException이 발생하고 쓰레드를 실행대기 상태(RUNNABLE)로 만든다.

### suspend(), resume(), stop()
+ suspend()는 sleep() 처럼 쓰레드를 멈추게한다.
+ resume()은 suspend()에 의해 정지된 쓰레드를 다시 실행대기상태로 만든다.
+ stop은 호출되는 즉시 쓰레드가 종료된다.
+ 교착상태를 일으킨다는 이유로 셋다 deprecated 되었다.

### yield
+ 쓰레드 자신에게 주어진 실행시간을 다음 차례의 쓰레드에게 양보한다.
+ yield와 interrupt를 적절히 사용하면, 프로그램의 응답성을 높이고 보다 효율적인 실행이 가능하게 할 수있다.

### join()
+ 실행중이던 쓰레드 자신이 하던 작업을 잠시멈추고 다른 쓰레드가 지정된 시간동안 작업을 수행하도록 만들어 준다.
```
void join()
void join(long millis)
void join(long millis, int nanos)
```
+ 시간을 지정하지않으면, 해당 스레드가 작업을 모두 마칠때까지 기다리게 한다.
+ interrupt() 에 의해서 일시정지상태에서 벗어 날수 있으며, 이때 예외가 발생하므로 try-catch문으로 감싸야한다.
+ sleep과 유사한 점이 많은데, 다른점은 join()은 현재 쓰레드가 아닌 특정 쓰레드에 동작하므로 static메서드가 아니라는 것이다.
+ sleep처럼 Thread.sleep() , this.sleep()형태가 아니라, th1.join() - (th1 쓰레드가 동작하는것을 마칠때까지 기다려라) 가 된다. 


# 9.쓰레드의 동기화
+ 멀티쓰레드 프로세스의 경우 여러 쓰레드가 같은 프로세스 내의 자원을 공유해서 작업하기 때문에 서로의 작업에 영향을 주게된다.
+ 이러한 일이 발생하는 것을 막기위해 한 쓰레드가 특정작업을 끝마치기 전까지 다른 쓰레드에 의해 방해받지 않도록 하는 것이 필요하다.
+ 그래서 임계영역(critical section) 과 잠금(Lock) 개념이 도입되었다.
+ 임계영역을 지정해 놓으면, 공유 데이터(객체)가 가지고 있는 lock을 획득한 단 하나의 쓰레드만 이 영역내의 코드를 수행할 수 있게한다.
+ 해당 쓰레드가 모든 코드를 수행하고 벗어나서 lock을 반납해야만 다른 쓰레드가 반납된 lock을 획득하여 임계영역의 코드를 수행할수 있게되는 것이다.
+ 이러한 **한 쓰레드가 진행중인 작업을 다른 쓰레드가 간섭하기 못하도록 막는것을 쓰레드의 동기화(synchronization) 라고한다.
+ 참고 : 동기화 지원을 위한 locks, atomic 패키지 가 존재한다.

## 9.1 synchronized를 이용한 동기화
```
1. 메서드 전체를 임계 영역으로 지정
    public synchronized void calcSum(){.임계영역...}

2. 특정한 영역을 임계 영역으로 지정
    synchronized(객체의 참조변수){..임계영역.}
```
+ 두 방식 모두 lock의 획득과 반납이 자동적으로 이루어지므로, 우리는 임계영역만 지정해주면 된다,
+ 모든 객체는 lock을 하나씩 가지고 있다.
+ 가능하면 synchronized 블럭{}으로 임계영역을 최소화해서 효율적으로 프로그램을 짜는 것이 좋다.

## 9.2 wait()과 notify()
+ 특정 쓰레드가 객체의 락을 너무 오랜 시간을 가지게 되면 다른 쓰레드들이 진행하지 못하는 상황이 발생한다.
+ 이를 막기위해 wait()을 호출하여 쓰레드가 객체에 대한 락을 반납하고 기다리게 할 수 있다.
+ 그러면 다른 쓰레드가 락을 얻어 해당 객체에 대한 작업을 수행할 수 있을 것이다.
+ 다른 쓰레드가 작업을 마친 이후, wait() 걸어둔 작업을 진행해도 되는 상황이 되면 notify()를 호출해서,
작업을 중단했던 쓰레드가 다시 락을 얻어 객체에 대한 작업을 진행 할 수 있게 한다.
```
wait(), notify(), notifyAll()
    - Object에 정의되어있다.
    - 동기화 블록 내에서만 사용할 수 있다.
    - 보다 효율적인 동기화를 가능하게 한다.
```
+ wait()은 작업중인 쓰레드를 각 객체마다 존재하는 waiting pool에 대기상태로 존재한다.
+ 만약 waiting pool에 여러 쓰레드가 대기상태라고 하자. notify() 호출시 모든 쓰레드중에서 **임의의 쓰레드**만 통지를 받는다.
+ notifyAll() 호출시 모든 쓰레드에게 통지를 하지만, 결국 락은 경쟁을 통해 하나의 쓰레드만 얻을수 있다.

### 기아 현상과 경쟁 상태
+ 여러 쓰레드가 대기상태로 존재하는 waiting pool에서 notify()를 호출하면 임의의 쓰레드에게 통지를 하게 되는데,
특정 A라는 쓰레드가 계속 통지를 못받을 수 있다. 이를 **기아 상태(starvation)**라고한다.
+ 이 문제는 notifyAll()을 통해 해결할 수 있다. 
***
+ 결국 락은 경쟁을 통해 하나의 쓰레드만 얻을수 있는데 이를 **경쟁 상태(race condition)** 라고한다.

## 9.3 Lock과 Condition을 이용한 동기화
+ 동기화하는 방식에는 synchronized 뿐만아니라 java.util.concurrent.locks 패키지가 제공하는 lock클래스들이 있다.
+ lock 패키지는 lock과 unlock을 내가 따로 정해줄 수 있으며 아래 3종류의 lock클래스를 지닌다.
```
ReentrantLock               : 재진입이 가능한 lock (우리가 지금까지 해온 lock과 일치)
ReentrantReadWriteLock      : 읽기에는 공유적이고, 쓰기에는 배타적인 lock
StampedLock                 : ReentrantReadWriteLock에 낙관적인 lock의 기능을 추가
```
+ ReentrantReadWriteLock은 읽기lock이 걸린 상태에서는 쓰기lock을 걸수 없고, 반대도 마찬가지 이다.
+ StampedLock은 무조건 읽기 lock을 걸지 않고, 쓰기와 읽기가 충돌했을때 쓰기가 끝난 후에 읽기 lock을 거는 것이다.

> ReentrantLock 생성자
```
ReentrantLock()
ReentrantLock(boolean fair)
```
+ 생성자 매개변수가 true이면, lock이 풀렸을때 가장 오래 기다린 쓰레드가 lock을 획득하도록 공정하게 처리한다.
+ 그런데 성능이 떨어지므로, 굳이 사용하지는 않는다.

> 관련 메서드
```
void lock()             : lock을 잠근다.
void unlock()           : lock을 해지한다.
boolean isLocked()      : lock이 잠겼는지 확인한다.
```
+ unlock()은 임계영역내에서 예외가 발생하거나 return문에 의해 실행되지 않을수 있기때문에, finally문으로 감싸 무조건 실행되게 한다.

> tryLock() 메서드
```
boolean tryLock()
boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException
```
+ lock은 lock을 얻을때까지 쓰레드를 block 시키므로 쓰레드 응답성이 나빠진다.
+ 응답성이 더 중요하다고 판단 될때, tryLock()을 이용하면 지정된 시간동안 lock을 얻지 못했을때, 다시 작업을 시도할 것인지 포기할 것인지 결정할수 있게 도와준다.
+ lock을 기다리는 과정중에 interrupt에 의해 작업이 취소되면 InterruptedException를 발생시키도록 할 수 있다.

### ReentrantLock 과 Condition
+ 이전의 wait(), notify()에서 waiting pool에 여러 쓰레드(ex) Cook, customer)가 존재할때, 기아현상이나 경쟁상태가 발생하는 문제들이 있었다.
+ 이를 개선하기 위해 Condition 클래스의 await()와 signal(), signalAll() 메서드를 사용하면 각각의(Cook, customer 전용) waiting pool에서 기다리게 하고 통지할 수 있다.
```
private ReentrantLock lock = new ReentrantLock(); // lock 을 생성
//lock로 condition을 생성
private Condition forCook = lock.newCondition();
private Condition forCust = lock.newCondition();        // 특정 waiting pool 즉 Condition 생성
===================================================
forCook.await()  //Cook 쓰레드를 기다리게함
...
forCook.signal() // 기다리고 있는 Cook쓰레드를 통지하여 깨움
```
+ 이제 쓰레드의 종류에 따라 구분하여 통지를 함으로써 기아 현상이나 경쟁 상태를 개선할 수 있다.
+ 그러나 여전히 특정 쓰레드를 선택할 수는 없기때문에(통지 하는것뿐 락을 얻는 것은 하나이므로), 기아 현상이나 경쟁 상태가 벌어질 발생할 가능성은 남아있다.

## 9.4 volatile
+ 일반적으로 코어는 캐시에서 값을 읽어오는데 메모리에서 값을 수정한 경우 캐시에는 갱신이 되지 않아서 문제가 발생할 수 있다.
+ 그러므로 멀티 코어프로세서에서 volatile으로 변수를 선언하면, 코어가 변수의 값을 읽어올때 캐시가 아닌 메모리에서 읽어오도록한다.

### volatile로 long과 double을 원자화
+ JVM은 4byte단위로 처리하기때문에 int보다 작거나 같은 타입들은 한번에 읽거나 쓰는 것이 가능하다.
+ 그러나 long과 double 타입의 변수는 하나의 명령어로 읽거나 쓰지 않기때문에, 다른 쓰레드가 끼어들 여지가 있다.
+ 이때 변수를 사용하는 모든 문장에 synchronized를 붙여서 처리할 수도 있지만, 변수에 volatile를 붙여 원자화 시킬수 있다.
+ 원자화란 작업을 더이상 나눌수 없다는 의미이다.
+ 원자화 했다고 해서 동기화하지 않아도 되는 것은 아니다.(동기화한 문장에 변수가 포함되면 원자화 해줄 필요는 없지만)
```
volatile long   sharedVal;
volatile double sharedVal;
```
## 9.5 fork & join 프레임 워크
+ 이제는 CPU속도를 자체적으로 올리기보다는, 코어의 개수를 늘려서 CPU의 성능을 향상시키는 방향으로 CPU 시장이 발전하고 있다.
+ 그러므로 멀티코어를 활용하는 멀티쓰레드 프로그래밍이 중요해지고 있다.
+ fork & join 프레임 워크는 하나의 작업을 작은 단위로 나워서 여러 쓰레드가 동시에 처리하는 것을 쉽게 만들어 준다.
> 아래 두 클래스중 하나를 상속받아 구현
```
RecursiveAction : 반환값이 없는 작업을 구현할때 사용
RecursiveTask   : 반환값이 있는 작업을 구현할때 사용
```
+ 위의 두 클래스에는 compute()라는 추상 메서드를 가지고 있으므로, 이 메서드를 구현하면 작업을 할 수 있다.
+ 이 다음에는 쓰레드풀과 수행할 작업을 생성하고 invoke()로 작업을 시작한다.
```
ForkJoinPool pool = new ForkJoinPool();     // 쓰레드 풀을 생성
SumTask task = new SumTask(from, to);       // 수행할 작업을 생성

Long result = pool.invoke(task);            // invoke()를 호출해서 작업을 시작
```
### compute()의 구현
+ 책의 그림 참고하기.
> 책에 있는 예시의 compute() 구현
```
public Long compute() {
        long size = to - from;

        if(size <= 5)     // 더할 숫자가 5개 이하면
            return sum(); // 숫자의 합을 반환

        long half = (from+to)/2;

        // 범위를 반으로 나눠서 두 개의 작업을 생성
        SumTask leftSum  = new SumTask(from, half);
        SumTask rightSum = new SumTask(half+1, to);

        leftSum.fork();

        return rightSum.compute() + leftSum.join();
    }
```
+ 지정된 범위를 절반으로 나누어서 나눠진 범위의 합을 계산하기 위한 SumTask를 생성한다.
+ 이 과정은 작업이 더이상 나눠질수 없을때 까지,size 범위값이 5보다 작거나 같을때까지 compute작업을 수행한다.

### fork()와 join()
```
fork()  : 해당 작업을 쓰레드 풀의 작업 큐에 넣는다. 비동기메서드
join()  : 해당 작업의 수행이 끝날때까지 기다렸다가, 수행이 끝나면 그 결과를 반환한다. 동기메서드
```
+ return문에서 compute()가 재귀호출될 때, join은 호출되지 않는다. 그러다 작업을 더이상 나눌수 없게 되었을때,
+ compute 재귀호출은 끝나고 join() 결과를 기다렸다가 더해서 결과를 반환한다.

### 다른 쓰레드의 작업 훔쳐오기
+ fork()가 호출되어 작업큐에 추가된 작업 역시, compute()에 의해 더이상 나눌 수 없을때까지 반복해서 나뉜다.
+ 그리고 자신의 작업큐가 비어있는 쓰레드는 다른 쓰레드의 작업큐에서 작업을 가져와서 수행한다.
+ 이를 작업훔쳐오기(work stealing) 이라고 부르며, 이 과정은 쓰레드풀에 의해 자동적으로 이루어진다.

## 결과
+ 작업을 나누고 다시 합치는데 걸리는 시간이 있기때문에, 싱글쓰레드보다 시간이 더 걸릴 수도 있다.
+ 즉 테스트해보고 이득이 있을때만 멀티쓰레드로 처리하자.
