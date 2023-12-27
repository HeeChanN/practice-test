package org.example.controller;

import java.util.HashMap;
import java.util.Map;

public class DefaultController implements Controller{

    private Map requestHandlers = new HashMap();

    /** handler 선택 */
    public RequestHandler getHandler(Request request){
        if(!this.requestHandlers.containsKey((request.getName()))){
            String message = "Cannot find handler for request name "
                    +"[" + request.getName()+"]";
            throw new RuntimeException(message);
        }
        return(RequestHandler) this.requestHandlers.get(request.getName());
    }

    /** 요청 수락 */
    @Override
    public Response processRequest(Request request) {
        Response response;
        try {
            /** 요청 라우팅 */
            response = getHandler(request).process(request);
        }
        catch (Exception e){

            /** 오류 처리 */
            response = new ErrorResponse(request,e);
        }
        return response;
    }

    @Override
    public void addHandler(Request request, RequestHandler requestHandler) {
        if (this.requestHandlers.containsKey(request.getName())){
            throw new RuntimeException("Already registered");

        }
        else{
            this.requestHandlers.put(request.getName(),requestHandler);
        }
    }
}
