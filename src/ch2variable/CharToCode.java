package ch2variable;
class CharToCode {
	public static void main(String[] args) {
		char ch = 'A';	     // char ch = 65;         
		int code = ch;  // ch에 저장된 값을 int타입으로 변환하여 저장한다.(int가 더 크므로, 자동형변환이 일어난다.)
// (int)
		System.out.printf("%c=%d(%#X)%n", ch, code, code);

		char hch = '가';     // char hch = 0xAC00;
		System.out.printf("%c=%d(%#X)%n", hch, (int)hch, (int)hch);
		System.out.printf("%c=%d(%#X)%n", 44032, (int)hch-44032, (int)hch);
		// 한글 에도 유니코드값이 정수값으로 들어가 있다.
	}
} 
