package com.jlopez.CheckingAccount.actions;

import com.jlopez.CheckingAccount.domain.CheckingAccountMother;
import com.jlopez.Customer.domain.CustomerMother;
import jlopez.CheckingAccount.actions.DepositIntoCheckingAccount;
import jlopez.CheckingAccount.domain.*;
import jlopez.CheckingAccount.domain.valueObjects.Amount;
import jlopez.CheckingAccount.domain.valueObjects.CheckingAccountId;
import jlopez.CheckingAccount.domain.valueObjects.Description;
import jlopez.CheckingAccount.infrastructure.FirebaseCheckingAccounts;
import jlopez.Customer.domain.valueObjects.CustomerId;
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
        //checkingAccounts = new InMemoryCheckingAccounts();
        checkingAccounts = new FirebaseCheckingAccounts();

        depositIntoCheckingAccount = new DepositIntoCheckingAccount(checkingAccounts);
    }


    @Test
    public void a_customer_can_deposit_funds_into_an_existing_account(){
        given_a_customer_with_this_id(ANY_CUSTOMER_ID);
        given_a_checking_account_with_this_ids(ANY_CHECKING_ACCOUNT_ID, ANY_CUSTOMER_ID);

        when_the_customer_deposits(new Amount(50), new Description("ANY_DESCRIPTION"));

        then_the_balance_is(50);
    }


    @Test
    public void when_a_customer_deposit_funds_into_an_existing_account_with_previous_funds_the_balance_increased() {
        given_a_customer_with_this_id(ANY_CUSTOMER_ID);
        given_a_checking_account_with_this_ids_and_a_debit_of_100(ANY_CHECKING_ACCOUNT_ID, ANY_CUSTOMER_ID);

        when_the_customer_deposits(new Amount(50), new Description("ANY_DESCRIPTION"));

        then_the_balance_is(150);
    }

    private void given_a_customer_with_this_id(CustomerId id) {
        customers.save(CustomerMother.widthId(id));
    }

    private void given_a_checking_account_with_this_ids(CheckingAccountId checkingAccountId, CustomerId customerId) {
        checkingAccounts.save(CheckingAccountMother.withIds(checkingAccountId, customerId));
    }

    private void given_a_checking_account_with_this_ids_and_a_debit_of_100(CheckingAccountId checkingAccountId, CustomerId customerId) {
        checkingAccounts.save(CheckingAccountMother.withIdsAndOneDebitOf(checkingAccountId, customerId, new Amount(100)));
    }

    private void when_the_customer_deposits(Amount amount, Description description) {
        depositIntoCheckingAccount.execute(ANY_CHECKING_ACCOUNT_ID, amount, description);
    }

    private void then_the_balance_is(int amount) {
        CheckingAccount checkingAccount = checkingAccounts.findById(ANY_CHECKING_ACCOUNT_ID);
        assertThat(checkingAccount.getBalance()).isEqualTo(amount);
    }
}
