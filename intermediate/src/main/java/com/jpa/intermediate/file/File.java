package com.jpa.intermediate.file;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "TBL_FILE")
@Inheritance(strategy = InheritanceType.JOINED)
public class File {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String fileName;
    private String vuid;
    private String filePath;
    private Long fileSize;
}
