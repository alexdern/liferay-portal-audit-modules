package ru.alexdern.liferay.security.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = { "user_timezone", "user_language" })
public class UserDto {

    @JsonProperty("company_id")
    private Long companyID;

    @JsonProperty("user_id")
    private Long userID;

    @JsonProperty("username")
    private String username;

    @JsonProperty("login")
    private String login;

}
