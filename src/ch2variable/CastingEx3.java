package ch2variable;
class CastingEx3 {
	public static void main(String[] args) {
		float f   = 9.1234567f;
		double d  = 9.1234567;
		double d2 = (double)f;

		System.out.printf("f =%20.18f\n", f);
		System.out.printf("d =%20.18f\n", d);
		System.out.printf("d2=%20.18f\n", d2);	// 이미 float에 저장된 값을 형변환 한 것이므로 값이 변하지 않았다.

	}
}
