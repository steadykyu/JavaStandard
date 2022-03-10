package ch14_lamda_stream.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@FunctionalInterface
interface rollingsix{
    void run();
}
public class Ex14_4 {
    public static void main(String[] args) {

//        Stream<Integer> numArr = IntStream.rangeClosed(1, 6).boxed();
////         numArr.forEach(System.out::println); Integer 박스를 6개 가지고 있는 numArr의 그림을 상상해보자.
//
////        numArr.map(i -> IntStream.rangeClosed(1, 6).boxed().map(j -> new Integer[]{i, j}))
////                .forEach(a -> System.out.println(a[0])); // 이중스트림이라 에러나옴.
//
//
//        List<Integer[]> list = numArr.flatMap(i -> IntStream.rangeClosed(1, 6).boxed().map(j -> new Integer[]{i, j}))     // rangeClosed도 사실상 Stream으로 생성하는 것이라 flatMap을써주어야한다.
//                .filter(arr -> arr[0] + arr[1] == 6).collect(Collectors.toList());
//
//        list.forEach(a -> System.out.printf("[%d, %d]\n",a[0],a[1]));



        /* 해답 */
        Stream<Integer> die = IntStream.rangeClosed(1,6).boxed();
        die.flatMap(i-> Stream.of(1,2,3,4,5,6).map(i2 -> new int[]{ i, i2 }))   //Stream<int[]>
//                .filter(iArr-> iArr[0]+iArr[1]==6)
                .forEach(iArr -> System.out.println(Arrays.toString(iArr)));
        //만약 map을 썼다면 map안에서 Stream을 생성하기때문에 Stream<Stream<int[]>>가 되었을 것이다.
        // 노트참고하자. 또는 책page 832~834
        // flatMap을 써야 Stream<int[]>가 된다.
    }
}
