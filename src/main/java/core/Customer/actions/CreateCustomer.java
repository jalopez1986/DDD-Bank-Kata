package core.Customer.actions;

import core.Customer.domain.*;
import core.Customer.domain.valueObjects.*;

import java.util.UUID;

public class CreateCustomer {
    private Customers customers;

    public CreateCustomer(Customers customers) {
        this.customers = customers;
    }

    public void execute(UUID id, String firstName, String lastName, String personnumber, String mobilePhoneNumber) {

        Customer customer = new Customer(new CustomerId(id),
                new FirstName(firstName),
                new LastName(lastName),
                new Personnumber(personnumber),
                new MobilePhoneNumber(mobilePhoneNumber));
        customers.save(customer);
    }
}
