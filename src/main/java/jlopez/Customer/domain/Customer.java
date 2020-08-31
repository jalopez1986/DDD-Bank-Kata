package jlopez.Customer.domain;

import jlopez.Customer.domain.valueObjects.*;

public class Customer {
    private final CustomerId id;
    private final FirstName firstName;
    private final LastName lastName;
    private final Personnumber personnumber;
    private final MobilePhoneNumber mobilePhoneNumber;

    public Customer(CustomerId id,
                    FirstName firstName,
                    LastName lastName,
                    Personnumber personnumber,
                    MobilePhoneNumber mobilePhoneNumber) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personnumber = personnumber;
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public CustomerId getId() {
        return id;
    }

    public FirstName getFirstName() {
        return firstName;
    }

    public LastName getLastName() {
        return lastName;
    }

    public Personnumber getPersonnumber() {
        return personnumber;
    }

    public MobilePhoneNumber getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }
}
