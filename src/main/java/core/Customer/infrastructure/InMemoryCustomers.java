package core.Customer.infrastructure;

import core.Customer.domain.Customer;
import core.Customer.domain.Customers;

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
