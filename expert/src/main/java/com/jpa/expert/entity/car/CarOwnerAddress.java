package com.jpa.expert.entity.car;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable @Getter @Setter
public class CarOwnerAddress {
    @NotNull String carOwnerAddress;
    @NotNull String carOwnerAddressDetail;
    @NotNull String carOwnerZipcode;
}
