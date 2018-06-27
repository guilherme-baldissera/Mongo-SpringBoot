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
import java.util.Calendar;

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
    @NotNull
    private Calendar calendar;
    @Getter
    @Setter
    @NotNull
    private Integer groupId;
    @Getter
    @Setter
    @NotNull
    private Integer userIdSent;

    public Message() {
    }

    @JsonCreator
    public Message(@JsonProperty("body") @NotNull @NotEmpty String body, @JsonProperty("calendar") @NotNull Calendar calendar,
                   @JsonProperty("groupId") @NotNull Integer groupId, @JsonProperty("userIdSent") @NotNull Integer userIdSent) {
        this.body = body;
        this.calendar = calendar;
        this.groupId = groupId;
        this.userIdSent = userIdSent;
    }
}