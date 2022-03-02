# 1.날짜와 시간

## 1.1 Calendar와 Date
+ 초창기에 Date 클래스가 만들어지고, 조금 더 개선한 Calendar클래스가 만들어졌다.
+ 그러나 이제는 java.time 패키지가 있어서 굳이 쓰지는 않지만, 이 클래스를 활용한 라이브러리들이 있어서 이해는 필요하다.
+ 태국을 제외한 모든 나라들은 GregorianCalendar 를 사용한다.
+ Calendar는 추상클래스이기때문에, 메서드를 통해서 완전히 구현된 클래스의 인스턴스를 얻어야 한다.
```
Calendar cal = Calendar.getInstance();
````

### Date와 Calendar간의 변환
```
1. Calendar를 Date로 변환
  Calendar cal = Calendar.getInstance();
  ....
  Date d = new Date(cal.getTimeInMillis());
  
2. Date를 Calendar로 변환
  Date d = new Date();
  ...
  Calendar cal = Calendar.getInstance();
  cal.setTime(d)
```

+ 책 or 작성한 코드에서 Calendar의 여러메소드와 상수필드값들을 보도록 하자.

