package com.prabhoo.android.atm;

class HasCard implements ATMState {

    private static final String ATM_PIN = "12345";

    ATMController atm;

    public HasCard(ATMController atmController) {
        atm = atmController;
    }

    @Override
    public ATMState apply() {
        atm.insertCard.setEnabled(false);
        atm.insertPin.setEnabled(true);
        atm.withdrawMoney.setEnabled(false);
        atm.ejectCard.setEnabled(true);
        atm.pin.setEnabled(true);
        atm.amount.setEnabled(false);
        return this;
    }

    @Override
    public void insertCard() {
        atm.log("You can only insert one card at a time");
    }

    @Override
    public void insertPin() {
        String pin = atm.pin.getText().toString();
        if (ATM_PIN.equals(pin)) {
            atm.log("You entered the correct PIN");
            atm.correctPinEntered = true;
            atm.currentState = atm.hasCorrectPin.apply();
        } else {
            atm.log("You entered the wrong PIN");
            atm.correctPinEntered = false;
            atm.log("Your card is ejected");
            atm.currentState = atm.noCard.apply();
        }
    }

    @Override
    public void requestCash() {
        atm.log("You have not entered your PIN");
    }

    public void ejectCard() {
        atm.log("Your card is ejected");
        atm.currentState = atm.noCard.apply();
    }
}
