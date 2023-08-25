package com.jpa.intermediate.file;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_POST_FILE")
@PrimaryKeyJoinColumn(name = "POST_FILE_ID")
@Getter @Setter @ToString
public class PostFile extends File {
    private String postContent;
}
