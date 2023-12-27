package org.example.controller;

public class ErrorResponse implements Response{
    private Request orignalRequest;
    private Exception orginalException;

    public ErrorResponse(Request request, Exception e){
        this.orignalRequest = request;
        this.orginalException = e;
    }

    public Request getOrignalRequest() {
        return orignalRequest;
    }

    public Exception getOrginalException() {
        return orginalException;
    }

    @Override
    public String getName() {
        return null;
    }
}
