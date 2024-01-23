package com.joaosilveira.dslearn.dto;

import com.joaosilveira.dslearn.entities.Notification;
import com.joaosilveira.dslearn.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.Instant;

public class NotificationDTO {

    private Long id;
    private String text;
    private Instant moment;
    private String read;
    private String route;

    public NotificationDTO() {

    }

    public NotificationDTO(Long id, String text, Instant moment, String read, String route, UserDTO user) {
        this.id = id;
        this.text = text;
        this.moment = moment;
        this.read = read;
        this.route = route;
    }

    public NotificationDTO(Notification entity) {
        this.id = entity.getId();
        this.text = entity.getText();
        this.moment = entity.getMoment();
        this.read = entity.getRead();
        this.route = entity.getRoute();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

}
