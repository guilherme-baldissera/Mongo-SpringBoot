package com.daitangroup.mysql.entities;

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
    String id;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    User userAdmin;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    List<User> guests;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    String name;
}
