package com.example.tegurdaja;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.content.Context;
import android.view.View;

public class Tegurdaja extends Activity {
    
    // Declaring a few variables needed in OnClickListener
    EditText isPrime;
    TextView outputLines;
    String solution = "";
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button okButton = (Button) findViewById (R.id.ok);
        Button clearButton = (Button) findViewById (R.id.clear);
        okButton.setOnClickListener (okListener);
        clearButton.setOnClickListener (clearListener);
    }
    
    private String divisorify(long num) {
        String ans;
        ans = Long.toString(num) + " = ";
        
        // If smaller than 4, then equals itself
        if (num < 4) {
            ans = ans + Long.toString(num) + "\n";
            return ans;
        }
        
        // Dividing by 2
        while (num % 2 == 0) {
            ans = ans + "2";
            num = num / 2;
            if (num == 1) {
                ans = ans + "\n";
                return ans;
            }
            ans = ans + "*";
        }
        
        // Dividing by other numbers
        long counter = 3;
        while (counter*counter <= num) {
            if (num % counter == 0) {
                // Adds counter to answer, decreases number
                ans = ans + Long.toString(counter) + "*";
                num = num / counter;
            }
            else {
                // Increases counter
                counter = counter+2;
            }
        }
        
        // Adds last remaining prime divisor
        ans = ans + Long.toString(num) + "\n";
        return ans;
    }
    
    private OnClickListener okListener = new OnClickListener() {
        public void onClick(View v) {
            long id = 0;
            
            try {
                // Prime number from EditText, text below is solution
                isPrime = (EditText)findViewById(R.id.inputnumber);
                outputLines = (TextView)findViewById(R.id.solution);
                
                // Gets what should be number to check
                // Assings it to CharSequence meieString
                Context context = getApplicationContext();
                String inputString = isPrime.getText().toString();
                try {
                    // Tries to convert meieString to long
                    long inputNum = Integer.parseInt(inputString);
                    
                    // Finds prime divisors
                    String newDivisors = divisorify(inputNum);
                    solution = newDivisors + solution;
                    
                    // Displays text
                    outputLines.setText(solution);
                }
                catch (Exception ex) {
                    // Displays error message
                    CharSequence text = "Enter a valid number!";
                    int duration = Toast.LENGTH_LONG;
                    
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
            catch (Exception ex) {
                // I have no idea what this block does
                Context context = getApplicationContext();
                CharSequence text = ex.toString() + "ID = " + id;
                int duration = Toast.LENGTH_LONG;
                
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    };
    
    private OnClickListener clearListener = new OnClickListener() {
        public void onClick(View v) {
            long id = 0;
            
            try {
                // Clears screen
                outputLines = (TextView)findViewById(R.id.solution);
                Context context = getApplicationContext();
                solution = "";
                outputLines.setText(solution);
            }
            catch (Exception ex) {
                // I have no idea what this block does
                Context context = getApplicationContext();
                CharSequence text = ex.toString() + "ID = " + id;
                int duration = Toast.LENGTH_LONG;
                
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    };
}
