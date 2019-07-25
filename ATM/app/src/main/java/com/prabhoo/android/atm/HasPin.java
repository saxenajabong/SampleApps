package com.prabhoo.android.atm;

public class HasPin implements ATMState {

    ATMController atm;

    public HasPin(ATMController atmController) {
        atm = atmController;
    }

    @Override
    public ATMState apply() {
        atm.insertCard.setEnabled(false);
        atm.insertPin.setEnabled(false);
        atm.withdrawMoney.setEnabled(true);
        atm.ejectCard.setEnabled(true);
        atm.pin.setEnabled(false);
        atm.amount.setEnabled(true);
        return this;
    }

    @Override
    public void insertCard() {
        atm.log("You already entered a card");
    }

    @Override
    public void insertPin() {
        atm.log("You already entered a PIN");
    }

    @Override
    public void requestCash() {
        int cashToWithdraw = getInt(atm.amount.getText().toString());
        if (cashToWithdraw > atm.cashInMachine) {
            atm.log("we don't have that much cash available");
            atm.log("Your card is ejected");
            atm.currentState = atm.noCard.apply();
        } else {
            atm.log(cashToWithdraw + " is provided by the machine");
            atm.cashInMachine = atm.cashInMachine - cashToWithdraw;
            atm.log("Your card is ejected");
            atm.currentState = atm.noCard.apply();
            if (atm.cashInMachine <= 0) {
                atm.log("We don't have any money");
                atm.currentState = atm.noCash.apply();
            }
        }
    }

    private int getInt(String s) {
        int result = 0;
        try {
            result = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            atm.log("Only Integer accepted.");
        }
        return result;
    }

    public void ejectCard() {
        atm.log("Your card is ejected");
        atm.currentState = atm.noCard.apply();
    }

}
