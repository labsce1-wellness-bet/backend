package com.lambdaschool.wellness.model;

import java.util.Map;

public class Image {
    private String base64ImageData;
    private Map fileParams;

    public Image() {
    }

    public String getBase64ImageData() {
        return base64ImageData;
    }

    public void setBase64ImageData(String base64ImageData) {
        this.base64ImageData = base64ImageData;
    }

    public Map getFileParams() {
        return fileParams;
    }

    public void setFileParams(Map fileParams) {
        this.fileParams = fileParams;
    }
}
