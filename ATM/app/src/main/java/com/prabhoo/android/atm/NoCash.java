package com.prabhoo.android.atm;

class NoCash implements ATMState {

    ATMController atm;

    public NoCash(ATMController atmController) {
        atm = atmController;
    }

    @Override
    public ATMState apply() {
        atm.insertCard.setEnabled(false);
        atm.insertPin.setEnabled(false);
        atm.withdrawMoney.setEnabled(false);
        atm.ejectCard.setEnabled(false);
        atm.pin.setEnabled(false);
        atm.amount.setEnabled(false);
        return this;
    }

    @Override
    public void insertCard() {
        atm.log("We don't have any money");
    }

    @Override
    public void insertPin() {
        atm.log("We don't have any money");
    }

    @Override
    public void requestCash() {
        atm.log("We don't have any money");
    }

    @Override
    public void ejectCard() {
        atm.log("We don't have any money");
        atm.log("There is no card to eject");
    }

}
