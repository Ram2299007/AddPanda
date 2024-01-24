package com.Appzia.addpanda.Util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

public class OTPReceiver extends BroadcastReceiver {

    private static EditText one, two, three, fours;

    public void setEditText_otp(EditText one, EditText two, EditText three, EditText four) {
        this.one = one;
        this.two = two;
        this.three = three;
        this.fours = four;

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        for (SmsMessage smsMessage : smsMessages) {

            try {


            String message_body = smsMessage.getMessageBody();
            String otp = message_body.split(":")[1];

            Log.d("otp123", otp);

            String[] characters = otp.split(""); // Split the string into an array of characters

            StringBuilder result = new StringBuilder();
            int consecutiveCount = 0;

            for (String character : characters) {
                if (character.matches("\\d")) {
                    result.append(character);
                    consecutiveCount++;
                } else {
                    result.setLength(0); // Reset if a non-digit character is encountered
                    consecutiveCount = 0;
                }
                if (consecutiveCount == 4) {
                    break; // Stop iterating once you have four consecutive digits
                }
            }

            String firstFourDigits = result.toString();
            Log.d("FirstFourDigits", firstFourDigits);


            int number = Integer.parseInt(firstFourDigits);
            // Toast.makeText(this, String.valueOf(number), Toast.LENGTH_SHORT).show();

            int first = number / 1000; // Extract the first digit
            int second = (number / 100) % 10; // Extract the second digit
            int third = (number / 10) % 10; // Extract the third digit
            int four = (number) % 10; // Extract the fourth digit


            if (String.valueOf(number).length() == 3) {
                String data = "0" + String.valueOf(number);


                number = Integer.parseInt(data);
            }


            one.setText(String.valueOf(first));

            two.setText(String.valueOf(second));

            three.setText(String.valueOf(third));
            fours.setText(String.valueOf(four));

            }catch (Exception ignored){}
        }
    }

}
