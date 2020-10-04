package com.jlopez.Customer.domain;

import jlopez.Customer.domain.*;
import jlopez.Customer.domain.valueObjects.*;

public class CustomerMother {
    public static Customer widthId(CustomerId id) {
        return new Customer(id,
                anyFirstName(),
                anyLastName(),
                anyPersonnumber(),
                anyMobilePhoneNumber());
    }


    private static FirstName anyFirstName() {
        return new FirstName("ANY_FIRST_NAME");
    }

    private static LastName anyLastName() {
        return new LastName("ANY_LAST_NAME");
    }

    private static Personnumber anyPersonnumber() {
        return new Personnumber("ANY_PERSONNUMBER");
    }

    private static MobilePhoneNumber anyMobilePhoneNumber() {
        return new MobilePhoneNumber("ANY_PHONE_NUMBER");
    }

}
