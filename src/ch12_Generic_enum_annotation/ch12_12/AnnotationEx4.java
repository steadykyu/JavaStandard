package ch12_Generic_enum_annotation.ch12_12;

import java.util.Arrays;

class MyArrayList<T> {
    T[] arr;

    @SafeVarargs
    @SuppressWarnings("varargs")
    MyArrayList(T... arr) {
        this.arr = arr;
    }

    @SafeVarargs
//	@SuppressWarnings("unchecked")
    public static <T> MyArrayList<T> asList(T... a) {
        return new MyArrayList<>(a);
    }

    public String toString() {
        return Arrays.toString(arr);
    }
}

class AnnotationEx4 {
    //	@SuppressWarnings("unchecked")
    public static void main(String args[]) {
        MyArrayList<String> list = MyArrayList.asList("1","2","3");

        System.out.println(list);
    }
}
//@SafeVarargs 대신 @SuppressWarnings의 주석을 풀어도 된다.
//@SuppressWarnings는 더 이곳저곳에 경고억제 애너테이션을 써주어야함.

