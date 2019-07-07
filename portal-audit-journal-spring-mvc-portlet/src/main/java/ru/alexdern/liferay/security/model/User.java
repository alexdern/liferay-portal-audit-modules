package ru.alexdern.liferay.security.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = { "company_id", "user_timezone", "user_language" })
public class User {

    @JsonProperty("user_id")
    private Long id;

    private String firstName;
    private String lastName;

    @JsonProperty("username")
    private String username;

    @JsonProperty("login")
    private String email;

}
