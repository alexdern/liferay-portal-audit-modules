package ru.alexdern.liferay.security.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class SearchForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String query = "";

    private String username = "Alex Dee";
    private String login = "alex@email.ru";

    private String eventType = "LOGIN";

    private LocalDate startDate = LocalDate.now();
    private LocalDate endDate = LocalDate.now();

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "SearchForm{" +
                "username='" + username + '\'' +
                ", login='" + login + '\'' +
                ", eventType='" + eventType + '\'' +
                '}';
    }

}
