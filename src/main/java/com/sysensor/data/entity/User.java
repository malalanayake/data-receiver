package com.sysensor.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;


@Entity
public class User extends BaseEntity{

    @Getter
    @Setter
    public LocalDate contextDate;

    @Getter
    @Setter
    public String userName;

    @Getter
    @Setter
    public String passWord;
}
