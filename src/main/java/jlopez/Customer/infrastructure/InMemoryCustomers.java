package jlopez.Customer.infrastructure;

import jlopez.Customer.domain.Customer;
import jlopez.Customer.domain.Customers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryCustomers implements Customers {
    private final Map<UUID, Customer> customers = new HashMap<>();

    @Override
    public void save(Customer customer) {
        customers.put(customer.getId().getValue(), customer);
    }
}
