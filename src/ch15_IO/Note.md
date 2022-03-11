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
