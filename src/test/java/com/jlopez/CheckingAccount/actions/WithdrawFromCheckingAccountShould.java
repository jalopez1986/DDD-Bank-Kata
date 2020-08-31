package com.jlopez.CheckingAccount.actions;

import com.jlopez.CheckingAccount.Mother.CheckingAccountMother;
import jlopez.CheckingAccount.actions.WithdrawFromCheckingAccount;
import jlopez.CheckingAccount.domain.*;
import jlopez.CheckingAccount.infrastructure.InMemoryCheckingAccounts;
import jlopez.Customer.domain.CustomerId;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class WithdrawFromCheckingAccountShould {
    private CheckingAccounts checkingAccounts;
    private WithdrawFromCheckingAccount withdrawFromCheckingAccount;


    private CustomerId ANY_CUSTOMER_ID;
    private CheckingAccountId ANY_CHECKING_ACCOUNT_ID;

    @Before
    public void setUp() throws Exception {
        ANY_CUSTOMER_ID = new CustomerId(UUID.randomUUID());
        ANY_CHECKING_ACCOUNT_ID = new CheckingAccountId(UUID.randomUUID());

        checkingAccounts = new InMemoryCheckingAccounts();

        withdrawFromCheckingAccount = new WithdrawFromCheckingAccount(checkingAccounts);
    }

    @Test
    public void a_customer_can_withdraw_funds_from_an_existing_account() {
        given_a_checking_account_with_a_debit_of_200();

        when_the_customer_withdraw(new Amount(50), new Description("ANY_DESCRIPTION"));

        then_the_balance_is(150);
    }

    @Test
    public void a_customer_cannot_withdraw_more_than_the_existing_founds() {
        given_a_checking_account_with_a_debit_of_200();

        assertThatThrownBy(() -> when_the_customer_withdraw(new Amount(300), new Description("ANY_DESCRIPTION"))).isInstanceOf(CheckingAccount.CannotWithdrawMoreThanTheExistingFunds.class);
    }

    private void given_a_checking_account_with_a_debit_of_200() {
        checkingAccounts.save(CheckingAccountMother.withIdsAndOneDebitOf(ANY_CHECKING_ACCOUNT_ID, ANY_CUSTOMER_ID, new Amount(200)));
    }

    private void when_the_customer_withdraw(Amount amount, Description description) {
        withdrawFromCheckingAccount.execute(ANY_CHECKING_ACCOUNT_ID, amount, description);

    }

    private void then_the_balance_is(int amount) {
        CheckingAccount checkingAccount = checkingAccounts.findById(ANY_CHECKING_ACCOUNT_ID);
        assertThat(checkingAccount.getBalance()).isEqualTo(amount);
    }

}
