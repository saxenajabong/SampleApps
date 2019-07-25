package com.prabhoo.android.atm;

public interface ATMState {

    void insertCard();

    void insertPin();

    void requestCash();

    void ejectCard();

    /**
     * Just to apply the state.
     *
     * @return same state to be operated on
     */
    ATMState apply();
}
