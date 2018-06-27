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

    private static final long serialVersionUID = -5699271237006660766L;
    @Id
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    @NotNull
    private int userIdOwner;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    private List<Integer> usersIdGuests;
    @Getter
    @Setter
    @NotNull
    @NotEmpty
    private String name;

    public Group(){
    }

    @JsonCreator
    public Group(@JsonProperty("userIdOwner") @NotNull Integer userIdOwner, @JsonProperty("usersIdGuests") @NotNull @NotEmpty List<Integer> usersIdGuests,
                 @JsonProperty("name") @NotNull @NotEmpty String name) {
        this.userIdOwner = userIdOwner;
        this.usersIdGuests = usersIdGuests;
        this.name = name;
    }
}
