package com.example.test123;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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
    boolean togoItem = false;
    double total = 0.00;
    private RequestQueue requestQueue;
    private static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView mealItemDisTXT = findViewById(R.id.textView3);
        TextView onTrayItemDisTXT = findViewById(R.id.textView4);
        TextView rollitemDisTxt = findViewById(R.id.textView5);
        TextView desertItemDisTxT = findViewById(R.id.textView6);
        TextView cookieItemDisTxT = findViewById(R.id.textView14);
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
        Button add1BTN = findViewById(R.id.button10);
        Button add2BTN = findViewById(R.id.button11);
        Button add3BTN = findViewById(R.id.button12);
        Button add4BTN = findViewById(R.id.button15);
        Button add5BTN = findViewById(R.id.button17);
        Button add6BTN = findViewById(R.id.button18);
        ImageButton switchLayoutBTN = findViewById(R.id.imageButton1);


        Button calcBTN = findViewById(R.id.button6);
        Button togoBTN = findViewById(R.id.button7);
        LinearLayout inputLayout = findViewById(R.id.inputLayout);
        LinearLayout buttonExraLayout = findViewById(R.id.buttonExraLayout);
        LinearLayout mainButtonLayout = findViewById(R.id.mainButtonLayout);

        buttonExraLayout.setVisibility((View.VISIBLE));

        inputLayout.setVisibility((View.VISIBLE));
        mainButtonLayout.setVisibility((View.VISIBLE));
        calcBTN.setVisibility((View.VISIBLE));



        mainAddBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                mainTrayIncr++;
                //outputTXT.setText(entree);
                mealItemDisTXT.setText(Integer.toString(mainTrayIncr));
                total+=8;


            }
        });
        add1BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                mainTrayIncr++;
                //outputTXT.setText(entree);
                mealItemDisTXT.setText(Integer.toString(mainTrayIncr));
                total+=8;


            }
        });
        add2BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                mainTrayIncr+=2;
                //outputTXT.setText(entree);
                mealItemDisTXT.setText(Integer.toString(mainTrayIncr));
                total+=8*2;


            }
        });
        add3BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                mainTrayIncr+=3;
                //outputTXT.setText(entree);
                mealItemDisTXT.setText(Integer.toString(mainTrayIncr));
                total+=8*3;


            }
        });
        add4BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                mainTrayIncr+=4;
                //outputTXT.setText(entree);
                mealItemDisTXT.setText(Integer.toString(mainTrayIncr));
                total+=8*4;


            }
        });
        add5BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                mainTrayIncr+=5;
                //outputTXT.setText(entree);
                mealItemDisTXT.setText(Integer.toString(mainTrayIncr));
                total+=8*5;


            }
        });
        add6BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                mainTrayIncr+=6;
                //outputTXT.setText(entree);
                mealItemDisTXT.setText(Integer.toString(mainTrayIncr));
                total+=8*6;


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

                }
            }
        });

        switchLayoutBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });





        calcBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //(!amountGivenTXT.equals(""))


                    //String url = "http://172.16.1.230:8000/add_data";
                    //String url = "http://10.60.4.30:8000/add_data";
//                    String url = "http://10.60.4.150:8000/add_data";

                      String url = "http://172.16.0.190:8000/add_data";
                    HashMap<String, String> params = new HashMap<String, String>();

                    params.put("meal", String.valueOf(mainTrayIncr));
                    params.put("dessert_side", String.valueOf(dessert_sideIncr));
                    params.put("entree", String.valueOf(entreeIncr));
                    params.put("soup", String.valueOf(soupItemIncr));
                    params.put("cookie", String.valueOf(cookieItemIncr));
                    params.put("roll", String.valueOf(rollItemIncr));
                    params.put("togo",String.valueOf(togoItem));

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
                        return "application/json; charset=utf-8";
                    }
                };

                requestQueue.add(request);


                //outputTXT.setText(entree);

                entreeIncr = 0;
                total = 0.00;
                rollItemIncr = 0;
                soupItemIncr = 0;
                mainTrayIncr = 0;
                dessert_sideIncr = 0;
                cookieItemIncr = 0;
                togoItem = false;
                cookieItemDisTxT.setText(Integer.toString(cookieItemIncr));
                desertItemDisTxT.setText(Integer.toString(dessert_sideIncr));
                rollitemDisTxt.setText(Integer.toString(rollItemIncr));
                onTrayItemDisTXT.setText(Integer.toString(entreeIncr));
                mealItemDisTXT.setText(Integer.toString(mainTrayIncr));




            }
        });
        togoBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //(!amountGivenTXT.equals(""))

                togoItem=true;

                //String url = "http://172.16.1.230:8000/add_data";
                //String url = "http://10.60.4.30:8000/add_data";
//                    String url = "http://10.60.4.150:8000/add_data";

                String url = "http://172.16.0.190:8000/add_data";
                HashMap<String, String> params = new HashMap<String, String>();

                params.put("meal", String.valueOf(mainTrayIncr));
                params.put("dessert_side", String.valueOf(dessert_sideIncr));
                params.put("entree", String.valueOf(entreeIncr));
                params.put("soup", String.valueOf(soupItemIncr));
                params.put("cookie", String.valueOf(cookieItemIncr));
                params.put("roll", String.valueOf(rollItemIncr));
                params.put("togo",String.valueOf(togoItem));

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

                entreeIncr = 0;
                total = 0.00;
                rollItemIncr = 0;
                soupItemIncr = 0;
                mainTrayIncr = 0;
                dessert_sideIncr = 0;
                cookieItemIncr = 0;
                togoItem=false;
                cookieItemDisTxT.setText(Integer.toString(cookieItemIncr));
                desertItemDisTxT.setText(Integer.toString(dessert_sideIncr));
                rollitemDisTxt.setText(Integer.toString(rollItemIncr));
                onTrayItemDisTXT.setText(Integer.toString(entreeIncr));
                mealItemDisTXT.setText(Integer.toString(mainTrayIncr));




            }
        });
    }

}