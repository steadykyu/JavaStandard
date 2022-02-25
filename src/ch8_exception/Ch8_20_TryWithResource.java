package ch8_exception;

public class Ch8_20_TryWithResource {
    public static void main(String[] args) {

        try(CloseableResource cr = new CloseableResource()){
            cr.exceptionWork(false); //workexception 발생을 안시킴. +  close()가 호출
        }catch (WorkException e){
            e.printStackTrace();
        }catch (CloseException e){
            e.printStackTrace();
        }
        System.out.println();

        try(CloseableResource cr = new CloseableResource()){
            cr.exceptionWork(true); //workexception 발생시킴.
        }catch (WorkException e){
            e.printStackTrace();
        }catch (CloseException e){
            e.printStackTrace();
        }
        System.out.println();
    }

}

class CloseableResource implements AutoCloseable{

    public void exceptionWork(boolean exception) throws WorkException{
        System.out.println("exceptionWork("+exception+")가 호출됨");

        if(exception)
            throw  new WorkException("WorkException 발생");
    }

    public void close() throws CloseException{
        System.out.println("close()가 호출됨");
        throw new CloseException("CloseException 발생!!!");
    }
}

class WorkException extends Exception{
    WorkException(String msg){ super(msg); }
}

class CloseException extends Exception{
    CloseException(String msg){ super(msg); }
}
