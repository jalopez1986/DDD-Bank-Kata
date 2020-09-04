package com.jlopez.CheckingAccount.infrastructure;

import com.jlopez.CheckingAccount.Mother.CheckingAccountMother;
import jlopez.CheckingAccount.domain.CheckingAccount;
import jlopez.CheckingAccount.infrastructure.FirebaseCheckingAccount;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class FirebaseCheckingAccountsShould {
    FirebaseCheckingAccount firebaseCheckingAccount;
    CheckingAccount ANY_CHECKING_ACCOUNT;

    @Test
    public void save_a_checking_account()  {
        given_a_firebaseCheckingAccount();
        given_a_checking_account();

        when_save_the_checking_account();

        then_the_checking_account_is_saved_in_firebase();
    }

    @Test
    public void save_a_checking_account_with_debits_and_credits() {
        given_a_firebaseCheckingAccount();
        give_a_checking_account_with_debits_and_credits();

        when_save_the_checking_account();

        then_the_checking_account_is_saved_in_firebase();
    }


    private void given_a_checking_account() {
        ANY_CHECKING_ACCOUNT = CheckingAccountMother.randomCheckingAccount();
    }

    private void give_a_checking_account_with_debits_and_credits() {
        ANY_CHECKING_ACCOUNT = CheckingAccountMother.randomCheckingAccountsWithDebitsAndCredits();
    }

    private void when_save_the_checking_account() {
        firebaseCheckingAccount.save(ANY_CHECKING_ACCOUNT);
    }

    private void then_the_checking_account_is_saved_in_firebase() {
        CheckingAccount checkingAccountSaved = firebaseCheckingAccount.findById(ANY_CHECKING_ACCOUNT.getCheckingAccountId());

        assertThat(checkingAccountSaved).isEqualTo(ANY_CHECKING_ACCOUNT);
    }

    private void given_a_firebaseCheckingAccount() {
        try {
            firebaseCheckingAccount = new FirebaseCheckingAccount();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
