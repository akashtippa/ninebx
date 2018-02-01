package com.ninebx.utility;


/**
 * Created by Alok on 30/01/18.
 */

public class SeafException extends Exception {
    private static final long serialVersionUID = 1L;

    public static final int OTHER_EXCEPTION = 599;

    private int code;

    public static final SeafException unknownException = new SeafException(1, "Unknown Error");
    public static final SeafException networkException = new SeafException(2, "Network Error");
    public static final SeafException encodingException = new SeafException(3, "Encoding Error");
    public static final SeafException illFormatException = new SeafException(4, "Ill-formatted Response");
    public static final SeafException sslException = new SeafException(5, "not trusted SSL server");
    public static final SeafException userCancelledException = new SeafException(6, "operation cancelled by user");
    public static final SeafException invalidPassword = new SeafException(7, "wrong password");
    public static final SeafException unsupportedEncVersion = new SeafException(8, "unsupported encryption version");
    public static final SeafException blockListNullPointerException = new SeafException(9, "block list is null");
    public static final SeafException encryptException = new SeafException(10, "encryption key or iv is null");
    public static final SeafException decryptException = new SeafException(11, "decryption key or iv is null");

    public SeafException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String toString() {
        return "code : " + code + " : Message : " + getMessage();
    }
}
