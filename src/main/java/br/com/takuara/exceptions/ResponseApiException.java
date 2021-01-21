package br.com.takuara.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.ws.rs.core.Response;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseApiException {

    private int code;
    private String message;


    public static ResponseApiException notFound(String message){
        return build(Response.Status.NOT_FOUND, message);
    }

    public static ResponseApiException badRequest(String message){
        return build(Response.Status.BAD_REQUEST, message);
    }

    public static ResponseApiException internalServer(String message){
        return build(Response.Status.INTERNAL_SERVER_ERROR, message);
    }

    public static ResponseApiException build(Response.Status status, String message){
        return ResponseApiException.builder()
                .code(status.getStatusCode())
                .message(message)
                .build();
    }

}
