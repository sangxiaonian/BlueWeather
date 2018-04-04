package sang.com.baselibrary.utils;

/**
 * 作者： ${PING} on 2018/3/30.
 */

public class ParamsError extends RuntimeException {

    String msg;
    int code;


    public ParamsError( String msg) {
        super(msg);
        this.msg = msg;
    }


    public ParamsError(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }
}
