package com.comunica.news.erros;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class ApiError {
    @Getter
    private List<String> error;

    public ApiError(String msgError) {
        this.error = List.of(msgError);
    }

    public ApiError(List<String> error) {
        this.error = error;
    }
}
