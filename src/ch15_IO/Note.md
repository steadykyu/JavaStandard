# 1. 자바에서의 입출력

## 1.1 입출력이란?
+ I/O란 Input과 Output의 약자로 입려고가 출력, 간단히 줄여서 입출력을 말한다. 컴퓨터의 내부 또는 외부의 장치와 프로그램간의 데이터를 주고받는 것을 말한다.

## 1.2 스트림
+ 이전에 배운 스트림과는 다른 개념으로, 데이터를 운반하는 데 사용되는 연결통로이다.
+ 물이 한쪽방향으로만 흐르는 것처럼 스트림은 단방향 통신만 가능하기때문에, 하나의 스트림으로 입력과 출력을 동시에 처리할 수는 없다.
+ 먼저 보낸 데이터를 먼저 받게 되어있는 큐, FIFO(First in first out) 구조로 되어있다.

## 1.3 바이트 기반 스트림
+ InputStream, OutputStream
+ 스트림은 바이트단위로 데이터를 전송하며 입출력 대상에 따라 다른 종류의 입출력 스트림이 있다.

|입력스트림|출력스트림|입출력대상의 종류|
|------|----|---|
|FileInputStream|FileOutputStream|파일
|ByteArrayInputStream|ByteArrayOutputStream|메모리(byte배열)
|PipedInputStream|PipedOutputStream|프로세스(프로세스간의 통신)
|AudioInputStream|AudioOutputStream|오디오장치

+ 이들은 모두 InputStream 또는 OutputStream의 자손들이며, 각각 읽고쓰는데 필요한 추상메서드를 자신에 맞게 구현해놓았다.
+ java.io패키지를 통해 입출력을 처리할 수 있는 **표준화된 방법**을 제공함으로써 입출력의 대상이 달려져도 동일한 방법으로 입출력이 가능하다.

InputStream|OutputStream|
|----|----|
abstract int read()|abstract void write()|
int read(byte[] b)|void write(byte[] b)|
int read(byte[] b, int off, int len)|void write(byte[] b, int off, int len)|

+ 핵심인 read()와 write()는 입출력 대상에 따라 상황에 알맞게 구현하라는 의미로 추상메서드로 되어있다.
+ 아래 메서드들도 추상메서드 read(), write() 으로 구현되어 있기때문에 입출력에 있어서 이 두 메서드는 반드시 구현되어야 한다.

## 1.4 보조 스트림
+ 데이터를 입출력할수 있는 기능은 없지만, 스트림의 기능을 향상시키거나 새로운 기능을 추가하는 보조스트림이 제공된다.
+ 이중 BufferedInputStream은 버퍼를 제공하는데, 버퍼를 사용한 입출력시 성능차이가 상당하기 때문에, 대부분 버퍼를 이용한 보조스트림을 사용한다.
+ 이 외의 보조스트림은 책을 참고하자. 모든 보조스트림은 FilterInputStream의 자손들이다.
+ 이 FilterInputStream 역시 InputStream의 자손이므로 모든 보조스트림 역시 입출력 사용방식은 동일하다.

## 1.5 문자기반 스트림
+ **Reader, Writer**
+ 바이트기반은 1byte단위로 입출력하는데, java의 char형은 2byte이기때문에 어려움이 있다.
+ 그러므로 문자데이터를 입출력할때는 Reader와 Writer를 사용한다.
+ 문자기반 스트림의 이름은 바이트기반 스트림의 이름에서 InputStream, OutputStream 부분을 Reader와 Writer로 바꿔주면된다.
+ 예 ) FileInputStream -> FileReader / FileOutputStream -> FileWriter
+ 단 ByteArrayInputStream -> CharArrayReader 형식으로 바뀐다.
+ 문자기반 보조스트림역시 바이트기반 보조스트림에서 이름만 수정해주면 된다.
+ 예 ) BufferedInputStream -> BufferedReader / BufferedOutputStream -> BufferedWriter

# 2. 바이트 기반 스트림
## 2.1 InputStream과 OutputStream
+ InputStream과 OutputStream은 모든 바이트기반의 스트림의 조상이며 여러 가지 메서드를 지닌다.
+ 책참고하기
+ ByteArrayInputStream, System.in, System.out 을 제외한 스트림들은 JVM이 자동적으로 닫아주기는 하지만, 스트림을 사용한 모든 작업을 마치고 난 후에는 close()를 호출해서
반드시 닫아주어야 한다.

## 2.2 ByteArrayInputStream, ByteArrayOutputStream
+ 메모리 즉 **바이트배열에 데이터를 입출력**하는데 사용되는 스트림이다. 주로 다른곳에 입출력하기 전에 데이터를 임시로 바이트배열에 담아서
변환 등의 작업을 하는데 사용된다.
+ 스트림의 종류가 달라고 읽고 쓰는법은 동일하므로, 아래 코드처럼 다른 스트림들도 활용해보자.
```java
class IOEx4 {
    public static void main(String[] args) {
        byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
        byte[] outSrc = null;

        byte[] temp = new byte[4];

        ByteArrayInputStream  input  = null;
        ByteArrayOutputStream output = null;

        input  = new ByteArrayInputStream(inSrc);
        output = new ByteArrayOutputStream();

        try {
            while(input.available() > 0) {
                int len = input.read(temp); // 읽은건 읽은거고, 반환값으로 읽어 온 데이터의 개수를 반환한다.
                output.write(temp, 0, len); // 바로 전의 읽어 온 개수만큼만 write한다.
            }
        } catch(IOException e) {}

        outSrc = output.toByteArray();

        System.out.println("Input Source  :" + Arrays.toString(inSrc)); //[123456789]
        System.out.println("temp          :" + Arrays.toString(temp));  // [8967] 효율을 위해 덧붙여서 읽어옴.
        System.out.println("Output Source :" + Arrays.toString(outSrc));//[123456789]
    }
}
```

## 2.3 FileInputStream과 FileOutputStream
+ **파일**에 입출력을 하기위한 스트림이다.
+ 책의 생성자와 코드의 예시를 참고하자.(FileView.java, FileCopy.java)

# 3. 바이트 기반의 보조스트림
## 3.1 FilterInputStream과 FilterOutputStream
+ FilterInputStream과 FilterOutputStream는 InputStream과 OutputStream의 자손이면서 모든 보조스트림의 **조상**이다.
> 생성자
```
protected FilterInputStream(InputStream in)
public    FilterOutputStream(OutputStream out)
```
+ FilterInputStream과 FilterOutputStream의 모든 메서드는 단순히 기반스트림의 메서드를 그대로 호출할 뿐이다.
+ 즉 상속을 통해 원하는 작업 수행하도록 읽고 쓰는 메서드를 오버라이딩 해야한다.
+ FilterInputStream은 protected 하므로 인스턴스를 생성할 수 없다.
```
FilterInputStream의 자손 : BufferedInputStream, DataInputStream, PushbackInputStream등
FilterOutputStream의 자손 : BufferedOutputStream, DataOutputStream, PrintStream 등
```
## 3.2 BufferedInputStream과 BufferedOutputStream
+ 스트림의 입출력 효율을 높이기 위해 버퍼를 사용하는 보조 스트림이다. 한 바이트보다는 버퍼(바이트 배열)를 이용해서 입출력하는것이 훨씬 빠르므로 입출력 작업에 사용된다.
> 생성자 
```
BufferedInputStream(InputStream in, int size)       : 지정된 크기의 버퍼를 갖는 BufferedInputStream인스턴스를 생성한다.
BufferedInputStream(InputStream in)                 : 8192byte의 버퍼를 가지고 인스턴스를 생성한다.
```
> 생성자 / 메서드
```
BufferedOutputStream(OutputStream out, int size)    : 지정된 크기의 버퍼를 갖는 BufferedOutputStream인스턴스를 생성한다.
BufferedOutputStream(OutputStream out)              : 8192byte의 버퍼를 가지고 인스턴스를 생성한다.
flush()                                             : 버퍼의 모든 내용을 출력소스에 출력한 후 버퍼를 비운다.
close()                                             : flush()롤 호출한후 BufferedOutputStream인스턴스가 사용하던 모든 자원을 반환한다.
```
+ BufferedOutputStream은 버퍼가 가득 찼을때만 출력소스에 출력을 하기 때문에 close()나 flush()를 호출해서 마지막 버퍼에 있는 내용이 출력소스에 출력되도록 해야한다.
+ 보조스트림을 사용한 경우에는 기반스트림의 close()나 flush()는 호출할 필요없이 단순히 보조스트림의 close()만 호출하면된다.
+ 보조스트림의 close()안에서 오버라이딩 없이 그대로 기반스트림의 close()를 호출하기 때문이다.

## 3.3 DataInputStream과 DataOutputStream
+ 각각 DataInput인터페이스, DataOutput인터페이스를 구현하였다.
+ 데이터를 읽고 쓰는데 있어 byte단위가 아닌 8가지 기본자료형의 단위로 읽고쓸수 있다는 장점이 있다.
+ 예) readInt(), readLong(), readDouble() ....etc
+ 생성자와 메서드는 책을 참고하자.
+ 각 자료형의 크기가 다르므로 출력한 데이터를(output) 다시 읽어올때는(input) 출력했을때의 순서대로 읽어와야한다.

## 3.4 SequenceInputStream
+ 여러개의 입력스트림을 **연속적으로 연결해서** 하나의 스트림으로부터 데이터를 읽는 것과 같이 처리할수 있도록 도와준다.
```
//참고 Vector.elements() 의 반환값 - Enumeration
SequenceInputStream(Enumeration e)                      : Enumeration에 저장된 순서대로 입력스트림을 하나의 스트림으로 연결한다.
SequenceInputStream(InputStream s1, InputStream s2)     : 두개의 입력스트림을 하나로 연결한다.
```

## 3.5 PrintStream
+ 데이터를 기반스트림에 다양한 형태로 출력할 수 있는 print, println, printf와같은 메서드를 오버로딩하여 제공한다.
+ 데이터를 적절한 문자로 출력하는 문자기반 스트림의 역할이다.
+ 사실 더 많은 언어를 처리하는 PrintWriter가 있지만 오랬동안 System.out?(PrintStream) 이 대중화되어 계속 쓰이고 있다.
+ print, println, printf 메서드들은 자주 사용되기 때문에 기반스트림에서 IOException이 발생하면 예외처리를 시키는 것이 아니라 내부에서 처리하도록 정의하였다.
+ checkError()를 통해 에러를 인지할수 있다.
+ printf() 의 여러 format(형식화된 출력)은 책을 참고하자. 또는 APi document의 Formatter 클래스를 참고하자.
