package com.daitangroup.mysql.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.Instant;

@Document
public class Message implements Serializable {

    private static final long serialVersionUID = 1943584345911056087L;
    @Id
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    private String body;
    @Getter
    @Setter
    private Instant time;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    private String groupId;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    private String userIdWhoSent;

    public Message() {
    }

    @JsonCreator
    public Message(@JsonProperty("body") @NotNull @NotEmpty String body, @JsonProperty("time") @NotNull Instant time,
                   @JsonProperty("groupId") @NotNull String groupId, @JsonProperty("userIdWhoSent") @NotNull String userIdWhoSent) {
        this.body = body;
        this.time = time;
        this.groupId = groupId;
        this.userIdWhoSent = userIdWhoSent;
    }
}