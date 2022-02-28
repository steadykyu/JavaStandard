package ch9_javalang;

class Person{
    long id;
    //Long 이랑 다르니 주의
    public boolean equals(Object obj){
        if(obj instanceof Person){
            return id == ((Person)obj).id;
        }
        else{
            return false;
        }
    }

    Person(long id){
        this.id = id;
    }
}

class Ch9_2EqualsEx2 {
    public static void main(String[] args) {
        Person p1 = new Person(12345555555L);
        Person p2 = new Person(12345555555L);

        if(p1==p2)
            System.out.println("p1과 p2는 같은 사람입니다.");
        else
            System.out.println("p1과 p2는 다른 사람입니다.");

        if(p1.equals(p2))
            System.out.println("p1과 p2는 같은 사람입니다.");
        else
            System.out.println("p1과 p2는 다른 사람입니다.");
    }
}

// equals 메서드 ㅇ버라이딩.
