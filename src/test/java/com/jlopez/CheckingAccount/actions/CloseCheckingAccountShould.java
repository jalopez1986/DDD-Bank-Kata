package com.jlopez.CheckingAccount.actions;

import com.jlopez.CheckingAccount.Mother.CheckingAccountMother;
import jlopez.CheckingAccount.domain.valueObjects.Amount;
import jlopez.CheckingAccount.domain.CheckingAccount;
import jlopez.CheckingAccount.domain.valueObjects.CheckingAccountId;
import jlopez.CheckingAccount.domain.CheckingAccounts;
import jlopez.CheckingAccount.infrastructure.InMemoryCheckingAccounts;
import jlopez.Customer.domain.valueObjects.CustomerId;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CloseCheckingAccountShould {

    private CheckingAccounts checkingAccounts;
    private CustomerId ANY_CUSTOMER_ID;
    private CheckingAccountId ANY_CHECKING_ACCOUNT_ID;

    private CloseCheckingAccount closeCheckingAccount;

    @Before
    public void setUp() throws Exception {
        ANY_CUSTOMER_ID = new CustomerId(UUID.randomUUID());
        ANY_CHECKING_ACCOUNT_ID = new CheckingAccountId(UUID.randomUUID());

        checkingAccounts = new InMemoryCheckingAccounts();

        closeCheckingAccount = new CloseCheckingAccount(checkingAccounts);
    }

    @Test
    public void a_customer_can_close_existing_account_if_the_balance_is_zero() {
        given_a_checking_account_with_a_debit_of_100_and_a_credit_of_100();

        when_the_customer_close_the_account();

        then_the_account_has_a_close_state();
    }

    @Test
    public void a_customer_can_not_close_existing_account_if_the_balance_is_not_zero() {
        given_a_checking_account_with_a_debit_of_100_and_a_credit_of_50();
        assertThatThrownBy(() -> when_the_customer_close_the_account()).isInstanceOf(CheckingAccount.CannotCloseTheCheckingAccount.class);

    }

    private void given_a_checking_account_with_a_debit_of_100_and_a_credit_of_100() {
        checkingAccounts.save(CheckingAccountMother.withIdsAndOneDebitOfAndOneCreditOf(ANY_CHECKING_ACCOUNT_ID, ANY_CUSTOMER_ID, new Amount(100), new Amount(100)));
    }

    private void given_a_checking_account_with_a_debit_of_100_and_a_credit_of_50() {
        checkingAccounts.save(CheckingAccountMother.withIdsAndOneDebitOfAndOneCreditOf(ANY_CHECKING_ACCOUNT_ID, ANY_CUSTOMER_ID, new Amount(100), new Amount(50)));
    }

    private void when_the_customer_close_the_account() {
        closeCheckingAccount.execute(ANY_CHECKING_ACCOUNT_ID);
    }

    private void then_the_account_has_a_close_state() {
        CheckingAccount checkingAccount = checkingAccounts.findById(ANY_CHECKING_ACCOUNT_ID);
        assertThat(checkingAccount.getState()).isEqualTo(CheckingAccount.State.CLOSE);

    }

}
