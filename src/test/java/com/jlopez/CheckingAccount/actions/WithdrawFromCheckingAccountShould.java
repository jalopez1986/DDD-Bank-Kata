package com.jlopez.CheckingAccount.actions;

import com.jlopez.CheckingAccount.Mother.CheckingAccountMother;
import com.jlopez.Customer.Mother.CustomerMother;
import jlopez.CheckingAccount.actions.WithdrawFromCheckingAccount;
import jlopez.CheckingAccount.domain.*;
import jlopez.CheckingAccount.infrastructure.InMemoryCheckingAccounts;
import jlopez.Customer.domain.CustomerId;
import jlopez.Customer.domain.Customers;
import jlopez.Customer.infrastructure.InMemoryCustomers;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class WithdrawFromCheckingAccountShould {
    private Customers customers;
    private CheckingAccounts checkingAccounts;
    private WithdrawFromCheckingAccount withdrawFromCheckingAccount;


    private CustomerId ANY_CUSTOMER_ID;
    private CheckingAccountId ANY_CHECKING_ACCOUNT_ID;

    @Before
    public void setUp() throws Exception {
        ANY_CUSTOMER_ID = new CustomerId(UUID.randomUUID());
        ANY_CHECKING_ACCOUNT_ID = new CheckingAccountId(UUID.randomUUID());

        customers = new InMemoryCustomers();
        checkingAccounts = new InMemoryCheckingAccounts();

        withdrawFromCheckingAccount = new WithdrawFromCheckingAccount(checkingAccounts);
    }

    @Test
    public void a_customer_can_withdraw_funds_from_an_existing_account(){
        given_a_customer_with_this_id(ANY_CUSTOMER_ID);
        given_a_checking_account_with_this_ids_and_a_debit_of_200(ANY_CHECKING_ACCOUNT_ID, ANY_CUSTOMER_ID);

        when_the_customer_withdraw(new Amount(50), new Description("ANY_DESCRIPTION"));

        then_the_balance_is(150);
    }

    private void given_a_customer_with_this_id(CustomerId id) {
        customers.save(CustomerMother.widthId(id));
    }

    private void given_a_checking_account_with_this_ids_and_a_debit_of_200(CheckingAccountId checkingAccountId, CustomerId customerId) {
        checkingAccounts.save(CheckingAccountMother.withIdsAndOneDebitOf(checkingAccountId, customerId, new Amount(200)));
    }

    private void when_the_customer_withdraw(Amount amount, Description description) {
        withdrawFromCheckingAccount.execute(ANY_CHECKING_ACCOUNT_ID, amount, description);

    }

    private void then_the_balance_is(int amount) {
        CheckingAccount checkingAccount = checkingAccounts.findById(ANY_CHECKING_ACCOUNT_ID);
        assertThat(checkingAccount.getBalance()).isEqualTo(amount);
    }

}
