package com.urlshortener.controller.validation;

import java.net.URL;


public class UrlValidation {

    /**
     * 입력받은 URL이 정산적인지 확인
     * 정상 = false </br>
     * 비정상 = true </br>
     * @param paramUrl
     * @return boolean
     */
    public static Boolean checkUrl(String paramUrl) {
        try {
            URL url = new URL(paramUrl);
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
