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
import java.util.List;

@Document
public class Group implements Serializable {
    @Id
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    @NotNull
    private User userAdmin;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    private List<User> guests;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    private String name;

    public Group(String id) {
        this.id = id;
    }

    @JsonCreator
    public Group(@JsonProperty("userAdmin") @NotNull User userAdmin, @NotNull @NotEmpty @JsonProperty("guests") List<User> guests,
                 @JsonProperty("name") @NotNull @NotEmpty String name) {
        this.userAdmin = userAdmin;
        this.guests = guests;
        this.name = name;
    }
}
