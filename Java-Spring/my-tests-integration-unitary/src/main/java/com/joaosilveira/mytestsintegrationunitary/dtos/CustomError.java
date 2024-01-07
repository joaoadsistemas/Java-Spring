package com.joaosilveira.mytestsintegrationunitary.dtos;

import java.time.Instant;

public class CustomError {

    private Instant timestamp;
    private Integer status;
    private String err;
    private String path;

    public CustomError(Instant timestamp, Integer status, String err, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.err = err;
        this.path = path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getErr() {
        return err;
    }

    public String getPath() {
        return path;
    }
}
