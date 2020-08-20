package com.jlopez.CheckingAccount.actions;

import com.jlopez.Customer.Mother.CustomerMother;
import jlopez.CheckingAccount.actions.RegisterCheckingAccount;
import jlopez.CheckingAccount.domain.CheckingAccount;
import jlopez.CheckingAccount.domain.CheckingAccounts;
import jlopez.CheckingAccount.domain.CheckingAccountId;
import jlopez.CheckingAccount.infrastructure.InMemoryCheckingAccounts;
import jlopez.Customer.domain.CustomerId;
import jlopez.Customer.domain.Customers;
import jlopez.Customer.infrastructure.InMemoryCustomers;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class RegisterCheckingAccountShould {

    private Customers customers;
    private CheckingAccounts checkingAccounts;
    private RegisterCheckingAccount registerCheckingAccount;
    private CustomerId ANY_CUSTOMER_ID;
    private CheckingAccountId ANY_CHECKING_ACCOUNT_ID;


    @Before
    public void setUp() throws Exception {
        ANY_CUSTOMER_ID = new CustomerId(UUID.randomUUID());
        ANY_CHECKING_ACCOUNT_ID = new CheckingAccountId(UUID.randomUUID());

        customers = new InMemoryCustomers();
        checkingAccounts = new InMemoryCheckingAccounts();

        registerCheckingAccount = new RegisterCheckingAccount(customers, checkingAccounts);
    }

    @Test
    public void a_customer_could_register_a_new_checkingAccount() {
        given_a_customer_with_this_id(ANY_CUSTOMER_ID);

        when_register_a_new_checking_account();

        then_the_checking_account_is_saved();

    }

    private void given_a_customer_with_this_id(CustomerId id) {
        customers.save(CustomerMother.widthId(id));

    }

    private void when_register_a_new_checking_account() {
        registerCheckingAccount.execute(ANY_CHECKING_ACCOUNT_ID, ANY_CUSTOMER_ID);

    }


    private void then_the_checking_account_is_saved() {
        assertThat(checkingAccounts.findById(ANY_CHECKING_ACCOUNT_ID)).isNotNull();

    }
}
