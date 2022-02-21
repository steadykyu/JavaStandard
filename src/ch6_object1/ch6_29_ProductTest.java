package ch6_object1;

class Product {
    static int count = 0;
    int serialNo;
    {
        ++count;
        serialNo = count;
    }
    public Product() {}
}

public class ch6_29_ProductTest{
    public static void main(String[] args) {
        Product p1 = new Product();
        Product p2 = new Product();
        Product p3 = new Product();

        System.out.println("p1의 serialNum은 :" + p1.serialNo);
        System.out.println("p2의 serialNum은 :" + p2.serialNo);
        System.out.println("p3의 serialNum은 :" + p3.serialNo);
        System.out.println("생산된 제품의 수는"+Product.count+"개 입니다.");
    }
}