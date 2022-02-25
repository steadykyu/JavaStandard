package ch8_exception;

public class Ch8_23_ChainedExceptionEx {
    public static void main(String[] args) {
        try {
            install();
        } catch (InstallException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    static void install() throws  InstallException{
        try {
            startInstall();             //설치준비  - 여기서 에러발생
            copyFiles();                //복사
        } catch (SpaceException se) {
            InstallException ie = new InstallException("se 원인으로한 설치 중 예외발생");
            ie.initCause(se);                                               // 지정한 원인을 원인 예외로 등록
            throw ie;
        } catch (MemoryException me) {
            InstallException ie = new InstallException("me 원인으로한 설치 중 예외발생");
            ie.initCause(me);                                               // 지정한 원인을 원인 예외로 등록
            throw ie;
        } finally {
            deleteTempFiles();
        }
    }

    static void startInstall() throws SpaceException, MemoryException {
        if (!enoughSpace())                                             // SpaceException 발생!
            throw new SpaceException("설치할 공간이 부족합니다.");
        if (!enoughMemory())
            throw new MemoryException("메모리가 부족합니다.");
    }

    static void copyFiles() { /* 파일들을 복사하는 코드를 적는다 */ }

    static void deleteTempFiles() {/* 임시파일들을 삭제하는 코드를 적는다 */}

    static boolean enoughSpace() {
        return false;
    }                       // 우리 예시는 space가 예외가 발생하도록 할것이다.

    static boolean enoughMemory() {
        return true;
    }
}
    class InstallException extends Exception{
        InstallException(String msg){
            super(msg);
        }
    }
//    8 - 21 에 있는 클래스 사용
//    //이렇게 내가 예외를 만들수 있다.
//    class SpaceException extends Exception{
//        SpaceException(String msg){
//            super(msg);
//        }
//    }
//
//    class MemoryException extends Exception{
//        MemoryException(String msg){
//            super(msg);
//        }
//    }



