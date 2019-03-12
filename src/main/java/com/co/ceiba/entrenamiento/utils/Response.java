package com.co.ceiba.entrenamiento.utils;

public class Response {
    
    private Object bodyMessage; 
    
    private String message;
    
    private int codMessage;
    
    private Boolean status;

    public Response(Object bodyMessage, String message, int codMessage, Boolean status) {
        this.bodyMessage = bodyMessage;
        this.message = message;
        this.codMessage = codMessage;
        this.status = status;
    }

    public Object getBodyMessage() {
        return bodyMessage;
    }

    public void setBodyMessage(Object bodyMessage) {
        this.bodyMessage = bodyMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCodMessage() {
        return codMessage;
    }

    public void setCodMessage(int codMessage) {
        this.codMessage = codMessage;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
}
