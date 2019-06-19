package com.lambdaschool.wellness.utils;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {
    private String getHeader(HttpServletRequest request, String headerKey) {
        return request.getHeader(headerKey);
    }
}
