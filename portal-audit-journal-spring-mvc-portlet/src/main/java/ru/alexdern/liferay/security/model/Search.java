package ru.alexdern.liferay.security.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@ToString
public class Search implements Serializable {

    private Long userId;
    private String query;
    private String eventType;
    private LocalDate startDate;
    private LocalDate endDate;

}
