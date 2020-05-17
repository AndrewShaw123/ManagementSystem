package com.andrew.domain;

/**
 * NormalMessage Class
 *
 * @author andrew
 * @date 2019/8/1
 */
public class NormalMessage {

    private String message;
    private String from;
    private String to;
    private String time;

    public NormalMessage() {
    }

    public NormalMessage(String message, String from, String to, String time) {
        this.message = message;
        this.from = from;
        this.to = to;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getTime() {
        return time;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "NormalMessage{" +
                "message='" + message + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
