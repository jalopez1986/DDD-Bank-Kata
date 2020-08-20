package com.jlopez.CheckingAccount.actions;

import com.jlopez.Customer.Mother.CustomerMother;
import jlopez.CheckingAccount.actions.DepositIntoCheckingAccount;
import jlopez.CheckingAccount.actions.RegisterCheckingAccount;
import jlopez.CheckingAccount.domain.CheckingAccount;
import jlopez.CheckingAccount.domain.CheckingAccountId;
import jlopez.CheckingAccount.domain.CheckingAccounts;
import jlopez.CheckingAccount.infrastructure.InMemoryCheckingAccounts;
import jlopez.Customer.domain.CustomerId;
import jlopez.Customer.domain.Customers;
import jlopez.Customer.infrastructure.InMemoryCustomers;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class DepositIntoCheckingAccountShould {
    private Customers customers;
    private CheckingAccounts checkingAccounts;
    private DepositIntoCheckingAccount depositIntoCheckingAccount;
    private CustomerId ANY_CUSTOMER_ID;
    private CheckingAccountId ANY_CHECKING_ACCOUNT_ID;


    @Before
    public void setUp() throws Exception {
        ANY_CUSTOMER_ID = new CustomerId(UUID.randomUUID());
        ANY_CHECKING_ACCOUNT_ID = new CheckingAccountId(UUID.randomUUID());

        customers = new InMemoryCustomers();
        checkingAccounts = new InMemoryCheckingAccounts();

        depositIntoCheckingAccount = new DepositIntoCheckingAccount(customers, checkingAccounts);
    }


    @Test
    public void a_customer_can_deposit_funds_into_an_existing_account(){
        given_a_customer_with_this_id(ANY_CUSTOMER_ID);
        given_a_checking_account_with_this_ids(ANY_CHECKING_ACCOUNT_ID, ANY_CUSTOMER_ID);

        when_the_customer_deposits(50);

        then_the_balance_is(50);
    }

    @Test
    public void when_a_customer_deposit_funds_into_an_existing_account_with_previous_funds_the_balance_increased(){
        given_a_customer_with_this_id(ANY_CUSTOMER_ID);
        given_a_checking_account_with_this_ids(ANY_CHECKING_ACCOUNT_ID, ANY_CUSTOMER_ID);
        deposit_into_account(100);


        when_the_customer_deposits(50);

        then_the_balance_is(150);
    }


    private void given_a_checking_account_with_this_ids(CheckingAccountId checkingAccountId, CustomerId customerId) {
        checkingAccounts.save(new CheckingAccount(checkingAccountId, customerId));
    }

    private void given_a_customer_with_this_id(CustomerId id) {
        customers.save(CustomerMother.widthId(id));
    }

    private void deposit_into_account(int amount) {
        depositIntoCheckingAccount.execute(ANY_CHECKING_ACCOUNT_ID, amount);
    }


    private void when_the_customer_deposits(int amount) {
        depositIntoCheckingAccount.execute(ANY_CHECKING_ACCOUNT_ID, amount);

    }

    private void then_the_balance_is(int amount) {
        CheckingAccount checkingAccount = checkingAccounts.findById(ANY_CHECKING_ACCOUNT_ID);
        assertThat(checkingAccount.getBalance()).isEqualTo(amount);
    }
}
