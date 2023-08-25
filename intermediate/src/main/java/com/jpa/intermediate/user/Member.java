package com.jpa.intermediate.user;

import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_MEMBER")
@Getter @Setter @ToString
public class Member extends User{
   @NotNull private String socialSecretNumber;

}
