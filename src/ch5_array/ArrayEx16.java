package ch5_array;

class ArrayEx16 {
    public static void main(String[] args) {
        System.out.println("매개변수 개수:"+args.length);

        for(int i=0;i< args.length;i++) {
            System.out.println("args[" + i + "] = \""+ args[i] + "\"");
        }
    }
}