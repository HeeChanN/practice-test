package org.example.controller;

public interface Controller {
    public Response processRequest(Request request);
    void addHandler(Request request, RequestHandler requestHandler);
}
