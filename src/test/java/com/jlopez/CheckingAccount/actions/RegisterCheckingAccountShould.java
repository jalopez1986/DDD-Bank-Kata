package com.jlopez.CheckingAccount.actions;

import com.jlopez.Customer.domain.CustomerMother;
import core.CheckingAccount.actions.RegisterCheckingAccount;
import core.CheckingAccount.domain.CheckingAccount;
import core.CheckingAccount.domain.CheckingAccounts;
import core.CheckingAccount.domain.valueObjects.CheckingAccountId;
import core.CheckingAccount.infrastructure.InMemoryCheckingAccounts;
import core.Customer.domain.valueObjects.CustomerId;
import core.Customer.domain.Customers;
import core.Customer.infrastructure.InMemoryCustomers;
import core.shared.domain.Clock;
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
        //checkingAccounts = new FirebaseCheckingAccounts();

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
