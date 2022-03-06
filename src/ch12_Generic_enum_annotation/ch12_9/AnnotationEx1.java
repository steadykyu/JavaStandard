package ch12_Generic_enum_annotation.ch12_9;

class Parent {
    void parentMethod() { }
}

class Child extends Parent {
    @Override
//    void parentmethod() { } // 조상 메서드의 이름을 잘못적었음.
    void parentMethod() { } // 조상 메서드의 이름을 잘못적었음.
}
