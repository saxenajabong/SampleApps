package com.prabhoo.android.atm;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ATMController implements ATMState {

    private static final String TAG = "ATM";

    ATMState hasCard;
    ATMState noCard;
    ATMState hasCorrectPin;
    ATMState noCash;
    ATMState currentState;
    Button insertCard, insertPin, withdrawMoney, ejectCard;
    EditText pin, amount;
    TextView logs;

    int cashInMachine = 99999999;
    boolean correctPinEntered = false;

    public ATMController(Button insertCard, Button insertPin, Button withdrawMoney,
                         Button ejectCard, EditText pin, EditText amount, TextView logs) {
        hasCard = new HasCard(this);
        noCard = new NoCard(this);
        hasCorrectPin = new HasPin(this);
        noCash = new NoCash(this);
        this.insertCard = insertCard;
        this.insertPin = insertPin;
        this.withdrawMoney = withdrawMoney;
        this.ejectCard = ejectCard;
        this.pin = pin;
        this.amount = amount;
        this.logs = logs;
        apply();
    }

    @Override
    public void insertCard() {
        clearLog();
        currentState.insertCard();
    }

    @Override
    public void insertPin() {
        clearLog();
        currentState.insertPin();
    }

    @Override
    public void requestCash() {
        clearLog();
        currentState.requestCash();
    }

    @Override
    public void ejectCard() {
        clearLog();
        currentState.ejectCard();
    }

    @Override
    public ATMState apply() {

        if (cashInMachine <= 0) {
            log("We don't have any money");
            currentState = noCash.apply();
        } else {
            log("You have not entered your card yet");
            currentState = noCard.apply();
        }
        return this;
    }

    public void log(String log) {
        logs.setText(logs.getText() + "\n" + log);
        Log.d(TAG, log);
    }

    private void clearLog() {
        logs.setText("");
    }
}
