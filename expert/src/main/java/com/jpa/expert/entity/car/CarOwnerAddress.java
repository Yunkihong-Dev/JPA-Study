package com.jpa.expert.entity.car;

import com.sun.istack.NotNull;

import javax.persistence.Embeddable;

@Embeddable
public class CarOwnerAddress {
    @NotNull String carOwnerAddress;
    @NotNull String carOwnerAddressDetail;
    @NotNull String carOwnerAddressZipcode;
}
