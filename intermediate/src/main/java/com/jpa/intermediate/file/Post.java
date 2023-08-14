package com.jpa.intermediate.file;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_POST")
@Getter @Setter @ToString
public class Post extends File {
    private String postContent;
}
