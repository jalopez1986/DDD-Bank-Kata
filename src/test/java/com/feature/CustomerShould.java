package com.feature;

import jlopez.Customer.actions.CreateCustomer;
import jlopez.Customer.domain.Customer;
import jlopez.Customer.domain.Customers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CustomerShould {
    @Mock Customers customers;
   @Captor ArgumentCaptor<Customer> customerArgumentCaptor;

    @Test
    public void create_a_customer() {
        CreateCustomer createCustomer = new CreateCustomer(customers);


        UUID id = UUID.randomUUID();
        String firstName = "Jorge";
        String lastName = "Lopez";
        String personnumber = "1088245803";
        String mobilePhoneNumber = "317123456789";

        createCustomer.execute(id, firstName, lastName, personnumber, mobilePhoneNumber);


        verify(customers, times(1)).save(customerArgumentCaptor.capture());

        Customer customer = customerArgumentCaptor.getValue();
        assertThat(customer.getId().getValue()).isEqualTo(id);
        assertThat(customer.getFirstName().getValue()).isEqualTo(firstName);
        assertThat(customer.getLastName().getValue()).isEqualTo(lastName);
        assertThat(customer.getPersonnumber().getValue()).isEqualTo(personnumber);
        assertThat(customer.getMobilePhoneNumber().getValue()).isEqualTo(mobilePhoneNumber);
    }



    @Test
    public void a_customer_could_register_a_new_checking_account_using_its_personal_details() {
        given_a_register_user();

        when_register_a_new_checking_account();

        then_the_checking_account_is_saved();
    }

    private void given_a_register_user() {

    }

    private void when_register_a_new_checking_account() {

    }

    private void then_the_checking_account_is_saved() {
    }


}



//dudas para la kata.
/*Si User y Checking account son aggregates roots primero se debe crear un customer
o se puede cuando se crea el checking account crear tambien el customer
 */