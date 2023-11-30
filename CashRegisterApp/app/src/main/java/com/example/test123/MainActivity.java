package com.example.test123;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    int entreeIncr = 0;
    int mainTrayIncr =0;
    int rollItemIncr = 0;
    int dessert_sideIncr = 0;
    int cookieItemIncr = 0 ;
    int soupItemIncr = 0;
    double total = 0.00;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView mealItemDisTXT = findViewById(R.id.textView3);
        TextView onTrayItemDisTXT = findViewById(R.id.textView4);
        TextView rollitemDisTxt = findViewById(R.id.textView5);
        TextView desertItemDisTxT = findViewById(R.id.textView6);
        TextView cookieItemDisTxT = findViewById(R.id.textView14);
        TextView totalPriceDisTXT = findViewById(R.id.textView2);
        TextView disChangeBackTXT = findViewById(R.id.textView7);
        EditText amountGivenTXT = findViewById(R.id.editTextText);
        requestQueue = Volley.newRequestQueue(getApplicationContext());



        Button mainAddBTN = findViewById(R.id.button13);
        Button mainDelBTN = findViewById(R.id.button14);
        Button onTrayAddBTN = findViewById(R.id.button24);
        Button onTrayDelBTN = findViewById(R.id.button25);
        Button rollItemAddBTN = findViewById(R.id.button28);
        Button rollItemDelBTN = findViewById(R.id.button29);
        Button desertItemAddBTN = findViewById(R.id.button26);
        Button desertItemDelBTN = findViewById(R.id.button27);
        Button cookieItemAddBTN = findViewById(R.id.button3);
        Button cookieItemDelBTN = findViewById(R.id.button4);
        Button expandOptionsBTN = findViewById(R.id.expandOptionsBTN);
        Button oneDollarAddBTN = findViewById(R.id.button7);
        Button fiveDollarAddBTN = findViewById(R.id.button9);
        Button tenDollarAddBTN = findViewById(R.id.button2);
        Button twentyDollarAddBTN = findViewById(R.id.button5);
        Button fityDollarAddBTN = findViewById(R.id.button8);
        Button hundredDollarAddBTN = findViewById(R.id.button16);
        Button exactDollarAddBTN = findViewById(R.id.button18);

        Button resetBTN = findViewById(R.id.button);
        Button calcBTN = findViewById(R.id.button6);
        LinearLayout outPutCostLayout = findViewById(R.id.outPutCostLayout);
        LinearLayout newOrderLayout = findViewById(R.id.newOrderLayout);
        LinearLayout inputLayout = findViewById(R.id.inputLayout);
        LinearLayout fieldDisplayLayout = findViewById(R.id.fieldDisplayLayout);
        LinearLayout buttonExraLayout = findViewById(R.id.buttonExraLayout);
        LinearLayout mainButtonLayout = findViewById(R.id.mainButtonLayout);


        outPutCostLayout.setVisibility((View.GONE));
        newOrderLayout.setVisibility((View.GONE));
        resetBTN.setVisibility((View.GONE));
        buttonExraLayout.setVisibility((View.GONE));
        expandOptionsBTN.setVisibility(View.VISIBLE);

        inputLayout.setVisibility((View.VISIBLE));
        fieldDisplayLayout.setVisibility((View.VISIBLE));
        mainButtonLayout.setVisibility((View.VISIBLE));
        expandOptionsBTN.setText("More Options");





        mainAddBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                mainTrayIncr++;
                //outputTXT.setText(entree);
                mealItemDisTXT.setText(Integer.toString(mainTrayIncr));
                total+=8;
                totalPriceDisTXT.setText("$"+Double.toString(total));


            }
        });
        mainDelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (mainTrayIncr>0) {
                    mainTrayIncr--;
                    //outputTXT.setText(entree);
                    mealItemDisTXT.setText(Integer.toString(mainTrayIncr));
                    total -= 8;
                    totalPriceDisTXT.setText("$" + Double.toString(total));

                }
            }
        });
        onTrayAddBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                entreeIncr++;
                //outputTXT.setText(entree);
                onTrayItemDisTXT.setText(Integer.toString(entreeIncr));
                total += 3;
                totalPriceDisTXT.setText("$" + Double.toString(total));
            }

        });
        onTrayDelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (entreeIncr >0) {

                    entreeIncr--;
                    //outputTXT.setText(entree);
                    onTrayItemDisTXT.setText(Integer.toString(entreeIncr));
                    total -= 3;
                    totalPriceDisTXT.setText("$" + Double.toString(total));

                }
            }
        });
        rollItemAddBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                rollItemIncr++;
                //outputTXT.setText(entree);
                rollitemDisTxt.setText(Integer.toString(rollItemIncr));
                total+=.5;
                totalPriceDisTXT.setText("$"+Double.toString(total));


            }
        });
        rollItemDelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (rollItemIncr>0) {

                    rollItemIncr--;
                    //outputTXT.setText(entree);
                    rollitemDisTxt.setText(Integer.toString(rollItemIncr));
                    total -= .5;
                    totalPriceDisTXT.setText("$" + Double.toString(total));

                }
            }
        });
        desertItemAddBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                dessert_sideIncr++;
                //outputTXT.setText(entree);
                desertItemDisTxT.setText(Integer.toString(dessert_sideIncr));
                total+=2;
                totalPriceDisTXT.setText("$"+Double.toString(total));


            }
        });
        desertItemDelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (dessert_sideIncr >0) {

                    dessert_sideIncr--;
                    //outputTXT.setText(entree);
                    desertItemDisTxT.setText(Integer.toString(dessert_sideIncr));
                    total -= 2;
                    totalPriceDisTXT.setText("$" + Double.toString(total));

                }
            }
        });
        cookieItemAddBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                cookieItemIncr++;
                //outputTXT.setText(entree);
                cookieItemDisTxT.setText(Integer.toString(cookieItemIncr));
                total += 1;
                totalPriceDisTXT.setText("$" + Double.toString(total));


            }
        });
        cookieItemDelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (cookieItemIncr>0) {

                    cookieItemIncr--;
                    //outputTXT.setText(entree);
                    cookieItemDisTxT.setText(Integer.toString(cookieItemIncr));
                    total -= 1;
                    totalPriceDisTXT.setText("$" + Double.toString(total));

                }
            }
        });
        oneDollarAddBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                try  {
                    double num1 = Double.parseDouble(amountGivenTXT.getText().toString());
                    num1++;
                    amountGivenTXT.setText(Double.toString(num1));
                }
                catch (Exception e){
                    double num1=0;
                    num1++;
                    amountGivenTXT.setText(Double.toString(num1));
                }


            }
        });
        fiveDollarAddBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                try  {
                    double num1 = Double.parseDouble(amountGivenTXT.getText().toString());
                    num1+=5;
                    amountGivenTXT.setText(Double.toString(num1));
                }
                catch (Exception e){
                    double num1=0;
                    num1+=5;
                    amountGivenTXT.setText(Double.toString(num1));
                }


            }
        });
        tenDollarAddBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                try  {
                    double num1 = Double.parseDouble(amountGivenTXT.getText().toString());
                    num1+=10;
                    amountGivenTXT.setText(Double.toString(num1));
                }
                catch (Exception e){
                    double num1=0;
                    num1+=10;
                    amountGivenTXT.setText(Double.toString(num1));
                }

            }
        });
        twentyDollarAddBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                try  {
                    double num1 = Double.parseDouble(amountGivenTXT.getText().toString());
                    num1+=20;
                    amountGivenTXT.setText(Double.toString(num1));
                }
                catch (Exception e){
                    double num1=0;
                    num1+=20;
                    amountGivenTXT.setText(Double.toString(num1));
                }


            }
        });
        fityDollarAddBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                try  {
                    double num1 = Double.parseDouble(amountGivenTXT.getText().toString());
                    num1+=50;
                    amountGivenTXT.setText(Double.toString(num1));
                }
                catch (Exception e){
                    double num1=0;
                    num1+=50;
                    amountGivenTXT.setText(Double.toString(num1));
                }
            }
        });
        hundredDollarAddBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                try  {
                    double num1 = Double.parseDouble(amountGivenTXT.getText().toString());
                    num1+=100;
                    amountGivenTXT.setText(Double.toString(num1));
                }
                catch (Exception e){
                    double num1=0;
                    num1+=100;
                    amountGivenTXT.setText(Double.toString(num1));
                }
            }
        });
        exactDollarAddBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                try  {
                    double num1 = Double.parseDouble(amountGivenTXT.getText().toString());
                    num1=total;
                    amountGivenTXT.setText(Double.toString(num1));
                }
                catch (Exception e){
                    double num1=0;
                    num1+=total;
                    amountGivenTXT.setText(Double.toString(num1));
                }
            }
        });

        expandOptionsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (buttonExraLayout.getVisibility() == View.GONE) {
                    buttonExraLayout.setVisibility((View.VISIBLE));
                    expandOptionsBTN.setText("Collapse");
                    mainButtonLayout.setVisibility((View.GONE));

                }
                else{
                    buttonExraLayout.setVisibility((View.GONE));
                    expandOptionsBTN.setText("");
                    expandOptionsBTN.setText("More Options");
                    mainButtonLayout.setVisibility((View.VISIBLE));



                }



            }
        });
        resetBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                entreeIncr = 0;
                total = 0.00;
                rollItemIncr = 0;
                soupItemIncr = 0;
                mainTrayIncr = 0;
                dessert_sideIncr = 0;
                cookieItemIncr = 0;
                totalPriceDisTXT.setText("$"+Double.toString(total));
                disChangeBackTXT.setText("$0.00");
                cookieItemDisTxT.setText(Integer.toString(cookieItemIncr));
                desertItemDisTxT.setText(Integer.toString(dessert_sideIncr));
                rollitemDisTxt.setText(Integer.toString(rollItemIncr));
                onTrayItemDisTXT.setText(Integer.toString(entreeIncr));
                mealItemDisTXT.setText(Integer.toString(mainTrayIncr));
                mainButtonLayout.setVisibility((View.VISIBLE));
                amountGivenTXT.setText("");
                outPutCostLayout.setVisibility((View.GONE));
                newOrderLayout.setVisibility((View.GONE));
                resetBTN.setVisibility((View.GONE));

                inputLayout.setVisibility((View.VISIBLE));
                fieldDisplayLayout.setVisibility((View.VISIBLE));
                buttonExraLayout.setVisibility((View.GONE));
                expandOptionsBTN.setVisibility(View.VISIBLE);






            }
        });
        calcBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //(!amountGivenTXT.equals(""))

                try {
                    double num1 = Double.parseDouble(amountGivenTXT.getText().toString());
                    disChangeBackTXT.setText("$" + Double.toString(num1 - total));
                }

                catch (Exception e){
                    disChangeBackTXT.setText("$"+Double.toString(-total));

                }
                    //String url = "http://172.16.1.230:8000/add_data";
                    //String url = "http://10.60.4.30:8000/add_data";
                    String url = "http://10.60.4.150:8000/add_data";

                    HashMap<String, String> params = new HashMap<String, String>();

                    params.put("meal", String.valueOf(mainTrayIncr));
                    params.put("dessert_side", String.valueOf(dessert_sideIncr));
                    params.put("entree", String.valueOf(entreeIncr));
                    params.put("soup", String.valueOf(soupItemIncr));
                    params.put("cookie", String.valueOf(cookieItemIncr));
                    params.put("roll", String.valueOf(rollItemIncr));

                    //Log.d("requestURL", url);


                    //Log.d("registerUser", String.valueOf(params));

                /*

                    JsonObjectRequest r = new JsonObjectRequest(url, new JSONObject(params),
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    //Toast.makeText(MainActivity.this,"HURRRAAAAYYYYY",Toast.LENGTH_SHORT).show();
                                    //Log.d("Adding data", String.valueOf(response));
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Log.d("String to JSON",error.toString());
                            Toast.makeText(MainActivity.this,"t" + error,Toast.LENGTH_SHORT).show();

                        }
                    });




                    requestQueue.add(r);
                    */
                StringRequest request = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Handle the response
                                Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle the error
                                Toast.makeText(MainActivity.this, "Contact Mr. Bander (Computer Science Room): " + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        // Convert the HashMap to a JSON string
                        return new JSONObject(params).toString().getBytes();
                    }

                    @Override
                    public String getBodyContentType() {
                        return "application/json";
                    }
                };

                requestQueue.add(request);


                //outputTXT.setText(entree);

                outPutCostLayout.setVisibility((View.VISIBLE));
                newOrderLayout.setVisibility((View.VISIBLE));
                resetBTN.setVisibility((View.VISIBLE));
                mainButtonLayout.setVisibility((View.GONE));

                inputLayout.setVisibility((View.VISIBLE));
                fieldDisplayLayout.setVisibility((View.VISIBLE));
                buttonExraLayout.setVisibility((View.GONE));
                amountGivenTXT.onEditorAction(EditorInfo.IME_ACTION_DONE);
                expandOptionsBTN.setVisibility(View.GONE);


            }
        });
    }

}