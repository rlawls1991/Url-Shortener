package com.urlshortener.doamin.module;

public class UrlEncoder {

    private final static int BASE62 = 62;
    private final static String BASE62_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private UrlEncoder(){

    }

    public static String encoding(long param) {
        StringBuffer sb = new StringBuffer();
        while (param > 0) {
            sb.append(BASE62_CHAR.charAt((int) (param % BASE62)));
            param /= BASE62;
        }
        return sb.toString();
    }
}
