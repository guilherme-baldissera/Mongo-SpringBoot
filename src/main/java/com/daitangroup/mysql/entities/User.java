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

@Document
public class User implements Serializable {

    private static final long serialVersionUID = -2216756051002651100L;
    @Getter
    @Setter
    @Id
    private String id;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    private  String name;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    private String password;


    public User() {
    }

    @JsonCreator
    public User(@JsonProperty("name") @NotNull @NotEmpty String name, @JsonProperty("password") @NotNull @NotEmpty String password) {
        this.name = name;
        this.password = password;
    }
}
