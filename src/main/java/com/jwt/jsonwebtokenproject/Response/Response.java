package com.jwt.jsonwebtokenproject.Response;

import lombok.*;

import java.util.List;





@NoArgsConstructor
@ToString
public class Response <T> {
    @Getter @Setter private String message;
    @Getter @Setter  private List<T> data;

    public Response(String message, List<T> data) {
        this.message = message;
        this.data = data;
    }
}
