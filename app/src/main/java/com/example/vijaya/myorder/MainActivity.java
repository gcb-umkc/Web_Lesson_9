package com.example.vijaya.myorder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_TAG = "MainActivity";
    final int COFFEE_PRICE = 5;
    final int WHIPPED_CREAM_PRICE = 1;
    final int CHOCOLATE_PRICE = 2;
    final int HAM_PRICE = 3;
    final int OLIVE_PRICE = 4;
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {

        // get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();

        // check if whipped cream is selected
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_checked);
        boolean hasWhippedCream = whippedCream.isChecked();

        // check if chocolate is selected
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checked);
        boolean hasChocolate = chocolate.isChecked();

        //check if ham is selected
        CheckBox ham = (CheckBox) findViewById(R.id.ham_checked);
        boolean hasHam = ham.isChecked();

        //check if olives are selected
        CheckBox olive = (CheckBox) findViewById(R.id.olives_checked);
        boolean hasOlive = olive.isChecked();

        // calculate and store the total price
        float totalPrice = calculatePrice(quantity, hasWhippedCream, hasChocolate, hasHam, hasOlive);

        // create and store the order summary
        String orderSummaryMessage = createOrderSummary(userInputName, hasWhippedCream, hasChocolate,
                hasHam, hasOlive, totalPrice);

        // Write the relevant code for making the buttons work(i.e implement the implicit and explicit intents
        Intent summary = new Intent(MainActivity.this, Summary.class);
        summary.putExtra("order", orderSummaryMessage);
        startActivity(summary);
    }

    private String boolToString(boolean bool) {
        return bool ? (getString(R.string.yes)) : (getString(R.string.no));
    }

    private String createOrderSummary(String userInputName, boolean hasWhippedCream, boolean hasChocolate,
                                      boolean hasHam, boolean hasOlive, float price) {
        String orderSummaryMessage = getString(R.string.order_summary_name, userInputName) + "\n" +
                getString(R.string.order_summary_whipped_cream, boolToString(hasWhippedCream)) + "\n" +
                getString(R.string.order_summary_chocolate, boolToString(hasChocolate)) + "\n" +
                getString(R.string.order_summary_ham, boolToString(hasHam)) + "\n" +
                getString(R.string.order_summary_olive, boolToString(hasOlive)) + "\n" +
                getString(R.string.order_summary_quantity, quantity) + "\n" +
                getString(R.string.order_summary_total_price, price) + "\n" +
                getString(R.string.thank_you);
        return orderSummaryMessage;
    }

    /**
     * Method to calculate the total price
     *
     * @return total Price
     */
    private float calculatePrice(int quantity, boolean hasWhippedCream, boolean hasChocolate,
                                 boolean hasHam, boolean hasOlive) {
        int basePrice = COFFEE_PRICE;
        if (hasWhippedCream) {
            basePrice += WHIPPED_CREAM_PRICE;
        }
        if (hasChocolate) {
            basePrice += CHOCOLATE_PRICE;
        }
        if (hasHam) {
            basePrice += HAM_PRICE;
        }
        if (hasOlive){
            basePrice += OLIVE_PRICE;
        }

        return quantity * basePrice;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method increments the quantity of coffee cups by one
     *
     * @param view on passes the view that we are working with to the method
     */

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select less than one hundred cups of coffee");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }

    /**
     * This method decrements the quantity of coffee cups by one
     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select at least one pizza");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }
}