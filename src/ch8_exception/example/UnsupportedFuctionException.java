package ch8_exception.example;

public class UnsupportedFuctionException extends Exception {

    private final int ERR_CODE;

    UnsupportedFuctionException(String msg, int i) {
        super(msg);
        ERR_CODE = i;
    }

    public UnsupportedFuctionException(String msg){
        this(msg,100);
    }

    public int getErrorCode(){
        return ERR_CODE;
    }

    public  String getMessage(){
        return "["+getErrorCode()+"]"+super.getMessage();
    }
}
