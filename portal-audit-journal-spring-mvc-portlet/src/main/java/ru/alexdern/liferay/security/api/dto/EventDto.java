package ru.alexdern.liferay.security.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = { "server_host", "session_key", "user_timezone", "user_language" })
public class EventDto extends UserDto {

    private LocalDateTime eventDateTime;

    @JsonProperty
    private String message;

    @JsonProperty("event_type")
    private String eventType;

    @JsonProperty("client_ip")
    private String clientIp;

    @JsonProperty("client_agent")
    private String clientAgent;

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        eventDateTime = LocalDateTime.parse(timestamp);
    }

    public String getFormatedDateTime() {
        return eventDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
