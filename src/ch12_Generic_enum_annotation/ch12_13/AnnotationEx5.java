package ch12_Generic_enum_annotation.ch12_13;

import java.lang.annotation.*;

@Deprecated
@SuppressWarnings("1111")   // 유효하지 않은 애너테이션은 무시된다.
@TestInfo(testedBy="aaa", testDate=@DateTime(yymmdd="160101", hhmmss="235959"))     // 요소값들을 빠짐없이 지정함.
class AnnotationEx5 {
    public static void main(String args[]) {
        // AnnotaionEx5의 Class객체를 얻는다.
        Class<AnnotationEx5> cls = AnnotationEx5.class;

        TestInfo anno = (TestInfo)cls.getAnnotation(TestInfo.class);                // TestInfo 애너테이션 정보만 가져온다.
        System.out.println("anno.testedBy()="+anno.testedBy());
        System.out.println("anno.testDate().yymmdd()="+anno.testDate().yymmdd());
        System.out.println("anno.testDate().hhmmss()="+anno.testDate().hhmmss());

        for(String str : anno.testTools())
            System.out.println("testTools="+str);

        System.out.println();

        // AnnotationEx5에 적용된 모든 애너테이션을 가져온다.(적용 되지 않은건 안가져옴)
        Annotation[] annoArr = cls.getAnnotations();

        for(Annotation a : annoArr)
            System.out.println(a);
    } // main의 끝
}

@Retention(RetentionPolicy.RUNTIME)  // 실행 시에 사용가능하도록 지정
@interface TestInfo {
    int       count()	    default 1;
    String    testedBy();
    String[]  testTools()   default "JUnit";
    TestType  testType()    default TestType.FIRST;
    DateTime  testDate();
}

@Retention(RetentionPolicy.RUNTIME)  // 실행 시에 사용가능하도록 지정
@interface DateTime {
    String yymmdd();
    String hhmmss();
}

enum TestType { FIRST, FINAL }

