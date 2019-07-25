package com.prabhoo.android.atm;

class NoCard implements ATMState {

    ATMController atm;

    public NoCard(ATMController atmController) {
        atm = atmController;
    }

    @Override
    public ATMState apply() {
        atm.insertCard.setEnabled(true);
        atm.insertPin.setEnabled(false);
        atm.withdrawMoney.setEnabled(false);
        atm.ejectCard.setEnabled(false);
        atm.pin.setEnabled(false);
        atm.amount.setEnabled(false);
        atm.pin.setText("");
        atm.amount.setText("");
        return this;
    }

    @Override
    public void insertCard() {
        atm.log("Card has been inserted to machine");
        atm.currentState = atm.hasCard.apply();
    }

    @Override
    public void insertPin() {
        atm.log("You have not entered your card");
    }

    @Override
    public void requestCash() {
        atm.log("You have not entered your card");
    }

    @Override
    public void ejectCard() {
        atm.log("You didn't enter a card");
    }
}
