package com.prabhoo.android.atm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ATMMachine extends AppCompatActivity {

    ATMController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atm_machine);

        Button insertCard = findViewById(R.id.insertCard);
        Button insertAmount = findViewById(R.id.insertAmount);
        Button insertPin = findViewById(R.id.insertPin);
        Button ejectCard = findViewById(R.id.ejectCard);
        EditText pin = findViewById(R.id.pin);
        EditText amount = findViewById(R.id.amount);
        TextView logs = findViewById(R.id.logs);

        controller = new ATMController(
                insertCard,
                insertPin,
                insertAmount,
                ejectCard,
                pin,
                amount,
                logs
        );
    }

    public void insertCard(View view) {
        controller.insertCard();
    }

    public void insertPin(View view) {
        controller.insertPin();
    }

    public void requestCash(View view) {
        controller.requestCash();
    }

    public void ejectCard(View view) {
        controller.ejectCard();
    }
}
