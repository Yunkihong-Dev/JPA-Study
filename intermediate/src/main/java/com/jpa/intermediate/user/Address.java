package com.jpa.intermediate.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
public class Address {

    private String address;
    private String addressDetail;
    private String postcode;
}
