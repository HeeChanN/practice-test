package org.example.controller;

public interface RequestHandler {
    public Response process(Request request) throws Exception;
}
