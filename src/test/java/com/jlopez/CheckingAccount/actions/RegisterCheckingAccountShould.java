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
import jlopez.shared.domain.Clock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class RegisterCheckingAccountShould {

    private Customers customers;
    private CheckingAccounts checkingAccounts;
    private RegisterCheckingAccount registerCheckingAccount;
    private CustomerId ANY_CUSTOMER_ID;
    private CheckingAccountId ANY_CHECKING_ACCOUNT_ID;

    private static final String TODAY = "30/08/2020";
    @Mock
    Clock clock;


    @Before
    public void setUp() throws Exception {
        ANY_CUSTOMER_ID = new CustomerId(UUID.randomUUID());
        ANY_CHECKING_ACCOUNT_ID = new CheckingAccountId(UUID.randomUUID());
        given(clock.now()).willReturn(LocalDate.parse(TODAY, DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        customers = new InMemoryCustomers();
        checkingAccounts = new InMemoryCheckingAccounts();

        registerCheckingAccount = new RegisterCheckingAccount(checkingAccounts, clock);
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
        CheckingAccount account = checkingAccounts.findById(ANY_CHECKING_ACCOUNT_ID);
        assertThat(account.getCheckingAccountId()).isEqualTo(ANY_CHECKING_ACCOUNT_ID);
        assertThat(account.getCustomerId()).isEqualTo(ANY_CUSTOMER_ID);
        assertThat(account.getOpeningDateAsString()).isEqualTo(TODAY);
    }
}
