package ch6_object1.Example;

public class Ex6_19 {
    public static void change(String str) {
        str += "456";
    }
    public static void main(String[] args)
    {
        String str = "ABC123";
        System.out.println(str);
        change(str);
        System.out.println("After change:"+str);
    }
}
// 참조형을 매개변수로 하여 같은 주소를 가리키고 있기는하다.
// 문자열은 내용을 변경할수 없기 때문에 + 연산을 하면, 새로운 문자열이 생성되고 새로운 주소가 change안 변수 str에 들어간다.
// 그래서 출력 str에는 변화가 없다.