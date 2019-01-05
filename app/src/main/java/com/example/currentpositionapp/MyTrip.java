package com.example.currentpositionapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MyTrip extends AppCompatActivity {

    Context context;

    RequestQueue requestQueue;

    String y = "http://103.241.181.36:8080/TripWebService/rest/TripEntry?Tripid=20181011MH11146&StartDate=2018-10-01&StartTime=10:00:00&ETA=2018-10-10&StartLocation=Pune&EndLocation=Mumbai&Vehicle=UI_11596&Customer=SmartGrid&Vendor=Halol&Driver=Raj&WeightLoad=0&FixKM=0&LRIssuer=Test&BranchCode=0&Fright=0&DriverID=9951515151&ProductName=Test&TripCategory=Test&ReportDateTime=2018-10-01&format=json";

    String url14 = y;//"http://103.241.181.36:8080/TripWebService/rest/TripEntry?Tripid=20181001MH11596&StartDate=2018-10-01&StartTime=10:00:00&ETA=2018-10-10&StartLocation=Pune&EndLocation=Mumbai&Vehicle=UI_11596&Customer=SmartGrid&Vendor=Halol&Driver=Raj&WeightLoad=0&FixKM=0&LRIssuer=Test&BranchCode=0&Fright=0&DriverID=9951515151&ProductName=Test&TripCategory=Test&ReportDateTime=2018-10-01&format=json ";
    String timeToWebFollowUp;

    private MeDateListenerFollowUp dateListener;
    private Calendar calendar;
    private MeTimeListenerFollowUp timeListener;

    String dateToWeb;


    //  http://192.168.2.124:6060/TripWebService/rest/TripEntry?
    // Tripid=20181001MH11596&StartDate=2018-10-01&StartTime=10:00:00&ETA=2018-10-10
    // &StartLocation=Pune&EndLocation=Mumbai&Vehicle=UI_11596
    // &Customer=SmartGrid&Vendor=Halol&Driver=Raj&WeightLoad=0&FixKM=0
    // &LRIssuer=Test&BranchCode=0&Fright=0&DriverID=9951515151
    // &ProductName=Test&TripCategory=Test&ReportDateTime=2018-10-01&format=json


    //http://192.168.2.124:/TripWebService/rest/TripEntry?Tripid=20181001MH11596&StartDate=2018-10-01&StartTime=10:00:00&ETA=2018-10-10&StartLocation=Pune&EndLocation=Mumbai&Vehicle=UI_11596&Customer=SmartGrid&Vendor=Halol&Driver=Raj&WeightLoad=0&FixKM=0&LRIssuer=Test&BranchCode=0&Fright=0&DriverID=9951515151&ProductName=Test&TripCategory=Test&ReportDateTime=2018-10-01&format=json


//http://localhost:9090/TripWebService/rest/TripEntry?Tripid=&StartDate=&StartTime=&ETA=&StartLocation=&EndLocation=&Vehicle=&Customer=&Vendor=&Driver=&WeightLoad=&FixKM=&LRIssuer=&BranchCode=&Fright=&DriverID=&ProductName=&
//TripCategory=&format=json
//192.168.2.124:6060

    String weburl = "http://192.168.43.167:6060/TripWebService/rest/TripEntry";
    // http://192.168.43.167:6060/TripWebService/rest/TripEntry?Tripid=20181001MH115961123&StartDate=2018-10-01&StartTime=10%3A00%3A00&ETA=2018-10-10&StartLocation=Pune&EndLocation=Mumbai&Vehicle=UI_11596&Customer=SmartGrid&Vendor=Halol&Driver=Raj&WeightLoad=0&FixKM=0&LRIssuer=Test&BranchCode=0&Fright=0&DriverID=9951515151&ProductName=Test&TripCategory=Test&ReportDateTime=2018-10-01&format=json

    //Tripid StartDate StartTime ETA StartLocation EndLocation Vehicle Customer Vendor Driver WeightLoad FixKM LRIssuer BranchCode Fright DriverID ProductName TripCategory

    String jsonformate;

    int startdate5 = 0, endDate5;

    int dateformate = 0;
    int startDateAfterEndDate, startDateBeforeEndDate, checkDate = 0;

    String startDate1, endDate1, startTime1, EndTime1;

    String url = "http://makable-spill.000webhostapp.com/CollegeErp/student_register.php";

    MyDBHelper helper;
    SQLiteDatabase db56;
    EditText w_load, l_r, b_code, fright, cn, f_km;
    TextView e_w_load, e_l_r, e_b_code, e_fright, sp6, sp5, sp4, error_cn, e_f_km;
    // Spinner tcategory, productname ,did;
    Button save;
    TimePickerDialog timePickerDialog;

    ScrollView scrollview;

    String startingDate, Eta;


    TextView sp_3, sp_1, sp_2;


    int countError = 0, checkTextView = 0;
    EditText tripid, customer, driver;
    Spinner spinnerStatus, spinnerSpokenTo2, spinnerStatus2, productname, did, tcategory;
    TextView date, time, next, id_error, driver_error, date1, time1, cutomer_error;
    private int mYear, mMonth, mDay, mHour, mMinute;
    LinearLayout linearLayout, linearDate1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mytrip);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        //requestQueue=Volley.newRequestQueue(getApplicationContext());
        helper = new MyDBHelper(MyTrip.this);

        scrollview = (ScrollView) findViewById(R.id.scrollview);

        tripid = (EditText) findViewById(R.id.tripid);
        customer = (EditText) findViewById(R.id.customer);
        driver = (EditText) findViewById(R.id.driver);

        w_load = (EditText) findViewById(R.id.w_load);
        l_r = (EditText) findViewById(R.id.l_r);
        b_code = (EditText) findViewById(R.id.b_code);
        fright = (EditText) findViewById(R.id.fright);


        cn = (EditText) findViewById(R.id.cn);
        f_km = (EditText) findViewById(R.id.f_km);

        e_w_load = (TextView) findViewById(R.id.e_w_load);
        e_l_r = (TextView) findViewById(R.id.e_l_r);
        e_b_code = (TextView) findViewById(R.id.e_b_code);
        e_fright = (TextView) findViewById(R.id.e_fright);

        error_cn = (TextView) findViewById(R.id.error_cn);
        e_f_km = (TextView) findViewById(R.id.e_f_km);


        sp4 = (TextView) findViewById(R.id.sp4);
        sp5 = (TextView) findViewById(R.id.sp5);
        sp6 = (TextView) findViewById(R.id.sp6);


        sp_1 = (TextView) findViewById(R.id.sp_1);
        sp_2 = (TextView) findViewById(R.id.sp_2);
        sp_3 = (TextView) findViewById(R.id.sp_3);


        save = (Button) findViewById(R.id.save);

        dateListener = new MeDateListenerFollowUp();
        calendar = Calendar.getInstance();

        timeListener = new MeTimeListenerFollowUp();
        //linearLayout.setVisibility(View.VISIBLE);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                countError = 0;

                // CheckDates(startDate1,endDate1);


                Toast.makeText(MyTrip.this, startDate1, Toast.LENGTH_LONG).show();
                Toast.makeText(MyTrip.this, endDate1, Toast.LENGTH_SHORT).show();
                Toast.makeText(MyTrip.this, startTime1, Toast.LENGTH_SHORT).show();
                Toast.makeText(MyTrip.this, EndTime1, Toast.LENGTH_SHORT).show();


                if (f_km.getText().toString().equals("")) {
                    countError++;
                    e_f_km.setVisibility(View.VISIBLE);
                } else {
                    e_f_km.setVisibility(View.GONE);
                }


                if (cn.getText().toString().equals("")) {
                    countError++;
                    error_cn.setVisibility(View.VISIBLE);
                } else {
                    error_cn.setVisibility(View.GONE);
                }


                if (startdate5 > endDate5) {
                    countError++;
                    Toast.makeText(MyTrip.this, "If start date is before end date", Toast.LENGTH_SHORT).show();
                }
             /* if(startdate5<endDate5)
              {
                  countError++;
                  Toast.makeText(MyTrip.this, "If start date is after the end date", Toast.LENGTH_SHORT).show();
              }*/

                if (w_load.getText().toString().equals("")) {
                    countError++;
                    e_w_load.setVisibility(View.VISIBLE);
                } else {
                    e_w_load.setVisibility(View.GONE);
                }


                if (l_r.getText().toString().equals("")) {
                    countError++;
                    e_l_r.setVisibility(View.VISIBLE);
                } else {
                    e_l_r.setVisibility(View.GONE);
                }

                if (b_code.getText().toString().equals("")) {
                    countError++;
                    e_b_code.setVisibility(View.VISIBLE);
                } else {
                    e_b_code.setVisibility(View.GONE);
                }

                if (fright.getText().toString().equals("")) {
                    countError++;
                    e_fright.setVisibility(View.VISIBLE);
                } else {
                    e_fright.setVisibility(View.GONE);
                }

//******************************Spinners validation******************
                if (productname.getSelectedItem().toString().equals("Product name")) {
                    countError++;
//                  sp4.setVisibility(View.VISIBLE);
                    sp5.setVisibility(View.VISIBLE);

                } else {
                    sp5.setVisibility(View.GONE);
                }


                if (did.getSelectedItem().toString().equals("Trip Category")) {
                    countError++;
//                  sp5.setVisibility(View.VISIBLE);
                    sp6.setVisibility(View.VISIBLE);

                } else {
                    sp6.setVisibility(View.GONE);
                }

                if (tcategory.getSelectedItem().toString().equals("Driver Id")) {
                    countError++;
//                  sp6.setVisibility(View.VISIBLE);
                    sp4.setVisibility(View.VISIBLE);

                } else {
                    sp4.setVisibility(View.GONE);
                }


                //****************End here *****************************


                if (spinnerStatus.getSelectedItem().toString().equals("Vehicle")) {
                    countError++;
                    sp_3.setVisibility(View.VISIBLE);
                } else {
                    sp_3.setVisibility(View.GONE);
                }
                if (spinnerStatus2.getSelectedItem().toString().equals("Start Location")) {
                    countError++;
                    sp_1.setVisibility(View.VISIBLE);
                } else {
                    sp_1.setVisibility(View.GONE);
                }
                if (spinnerSpokenTo2.getSelectedItem().toString().equals("End Location")) {
                    countError++;
                    sp_2.setVisibility(View.VISIBLE);

                } else {
                    sp_2.setVisibility(View.GONE);
                }


                if (tripid.getText().toString().equals("")) {
                    countError++;
                    id_error.setVisibility(View.VISIBLE);
                } else {
                    id_error.setVisibility(View.GONE);
                }
                if (customer.getText().toString().equals("")) {
                    countError++;
                    cutomer_error.setVisibility(View.VISIBLE);

                } else {
                    cutomer_error.setVisibility(View.GONE);
                }
                if (driver.getText().toString().equals("")) {
                    countError++;
                    driver_error.setVisibility(View.VISIBLE);

                } else {
                    driver_error.setVisibility(View.GONE);

                }

                if (date.getText().toString().equals("Start Date")) {
                    countError++;
                    date1.setVisibility(View.VISIBLE);


                } else {
                    date1.setVisibility(View.GONE);
                }
                if (time.getText().toString().equals("E.T.A")) {
                    countError++;
                    time1.setVisibility(View.VISIBLE);

                } else {
                    time1.setVisibility(View.GONE);
                }


                if (countError == 0) {
                    linearLayout.setVisibility(View.VISIBLE);
                    scrollview.scrollTo(0, linearLayout.getTop());
                    linearDate1.setVisibility(View.GONE);
                    time1.setVisibility(View.GONE);
                    date1.setVisibility(View.GONE);
                    id_error.setVisibility(View.GONE);
                    cutomer_error.setVisibility(View.GONE);
                    driver_error.setVisibility(View.GONE);
                    sp_1.setVisibility(View.GONE);
                    sp_2.setVisibility(View.GONE);
                    sp_3.setVisibility(View.GONE);

                    error_cn.setVisibility(View.GONE);

                    sp4.setVisibility(View.GONE);
                    sp5.setVisibility(View.GONE);
                    sp6.setVisibility(View.GONE);


                    e_w_load.setVisibility(View.GONE);
                    e_l_r.setVisibility(View.GONE);
                    e_b_code.setVisibility(View.GONE);
                    e_fright.setVisibility(View.GONE);

                    /*MyDBHelper helper = new MyDBHelper(MyTrip.this);*/
                    db56 = helper.getWritableDatabase();


                    Toast.makeText(MyTrip.this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                    ContentValues values = new ContentValues();


                    values.put("TripId", tripid.getText().toString());
                    values.put("StartDate", date.getText().toString());
                    values.put("ETA", time.getText().toString());

                    values.put("StartLocation", spinnerStatus2.getSelectedItem().toString());
                    values.put("EndLocation", spinnerSpokenTo2.getSelectedItem().toString());
                    values.put("Vehicle", spinnerStatus.getSelectedItem().toString());

                    values.put("Customer", customer.getText().toString());
                    values.put("Driver", driver.getText().toString());
                    values.put("WeightLoad", w_load.getText().toString());

                    values.put("LR_Issed_By", l_r.getText().toString());
                    values.put("BrancCode", b_code.getText().toString());
                    values.put("Fright", fright.getText().toString());

                    values.put("DriverId", tcategory.getSelectedItem().toString());
                    values.put("ProductName", productname.getSelectedItem().toString());
                    values.put("TripCategory", did.getSelectedItem().toString());
                    db56.insert("tripentry", null, values);

                    Toast.makeText(MyTrip.this, "http://103.241.181.36:8080/TripWebService/rest/TripEntry?"+"Tripid="+tripid.getText().toString()+"&"+"StartDate="+date.getText().toString()+"&"+"StartTime="+startTime1+"&"+"ETA="+time.getText().toString()+"&"+"StartLocation="+spinnerStatus2.getSelectedItem().toString()+"&"+"EndLocation="+spinnerSpokenTo2.getSelectedItem().toString()+"&"+"Vehicle="+spinnerStatus.getSelectedItem().toString()+"&"+"Customer="+customer.getText().toString()+"&"+"Vendor="+cn.getText().toString()+"&"+"Driver="+driver.getText().toString()+"&"+"WeightLoad="+w_load.getText().toString()+"&"+"FixKM="+f_km.getText().toString()+"&"+"LRIssuer="+l_r.getText().toString()+"&"+"BranchCode="+b_code.getText().toString()+"&"+"Fright="+fright.getText().toString()+"&"+"DriverID="+tcategory.getSelectedItem().toString()+"&"+"ProductName="+productname.getSelectedItem().toString()+"&"+"TripCategory="+did.getSelectedItem().toString()+"&"+"ReportDateTime="+EndTime1+"&"+"format="+"json", Toast.LENGTH_LONG).show();

                    send2();
                    //send();
                } else {
                    Toast.makeText(MyTrip.this, "Please Fill red marked options", Toast.LENGTH_SHORT).show();
                }


            }
        });


        initSpinnerStatusFollowUp();
        initSpinnerStatusFollowUp2();
        initSpinnerStatusFollowUp3();

        initSpinnerStatusFollowUp4();
        initSpinnerStatusFollowUp5();
        initSpinnerStatusFollowUp6();

        id_error = (TextView) findViewById(R.id.id_error);
        cutomer_error = (TextView) findViewById(R.id.cutomer_error);
        driver_error = (TextView) findViewById(R.id.driver_error);
        date1 = (TextView) findViewById(R.id.date1);
        time1 = (TextView) findViewById(R.id.time1);

        date = (TextView) findViewById(R.id.date);
        time = (TextView) findViewById(R.id.time);
        /*next= (TextView) findViewById(R.id.next);*/

        linearLayout = (LinearLayout) findViewById(R.id.lin);
        linearDate1 = (LinearLayout) findViewById(R.id.linearDate1);


        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTextView = 1;
                date();

            }
        });


        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTextView = 0;
                /*  date(checkTextView);*/

                date();


            }
        });

        /*next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                countError=0;

               // spinnerStatus2 spinnerSpokenTo2 spinnerStatus

                if(spinnerStatus.getSelectedItem().toString().equals("Vehicle"))
                {
                    countError++;
                    sp_3.setVisibility(View.VISIBLE);
                }
                else {
                    sp_3.setVisibility(View.GONE);
                }
                if(spinnerStatus2.getSelectedItem().toString().equals("Start Location"))
                {
                    countError++;
                    sp_1.setVisibility(View.VISIBLE);
                }
                else {
                    sp_1.setVisibility(View.GONE);
                }
                if(spinnerSpokenTo2.getSelectedItem().toString().equals("End Location"))
                {
                    countError++;
                    sp_2.setVisibility(View.VISIBLE);

                }
                else {
                    sp_2.setVisibility(View.GONE);
                }












                if(tripid.getText().toString().equals(""))
                {
                    countError++;
                    id_error.setVisibility(View.VISIBLE);
                }
                else {
                    id_error.setVisibility(View.GONE);
                }
                if(customer.getText().toString().equals(""))
                {
                    countError++;
                    cutomer_error.setVisibility(View.VISIBLE);

                }
                else {
                    cutomer_error.setVisibility(View.GONE);
                }
               if(driver.getText().toString().equals(""))
                {
                    countError++;
                    driver_error.setVisibility(View.VISIBLE);

                }
                else {
                   driver_error.setVisibility(View.GONE);

               }

                if(date.getText().toString().equals("Start Date"))
                {
                    countError++;
                    date1.setVisibility(View.VISIBLE);


                }
                else {
                    date1.setVisibility(View.GONE);
                }
                if(time.getText().toString().equals("E.T.A"))
                {
                    countError++;
                    time1.setVisibility(View.VISIBLE);

                }
                else {
                    time1.setVisibility(View.GONE);
                }


               if(countError==0)
               {
                    linearLayout.setVisibility(View.VISIBLE);
                   scrollview.scrollTo(0, linearLayout.getTop());
                   linearDate1.setVisibility(View.GONE);
                   time1.setVisibility(View.GONE);
                   date1.setVisibility(View.GONE);
                   id_error.setVisibility(View.GONE);
                   cutomer_error.setVisibility(View.GONE);
                   driver_error.setVisibility(View.GONE);
                   sp_1.setVisibility(View.GONE);
                   sp_2.setVisibility(View.GONE);
                   sp_3.setVisibility(View.GONE);
                }
                else {
                   Toast.makeText(MyTrip.this, "Please Fill red marked options", Toast.LENGTH_SHORT).show();
               }



                *//*tripid.getText().toString();
                customer.getText().toString();
                driver.getText().toString();*//*


            }
        });*/

    }


    private final void initSpinnerStatusFollowUp() {
        spinnerStatus = (Spinner) findViewById(R.id.spinnerSpokenTo2);
        ArrayList<String> statusList = new ArrayList<String>();
        statusList.add("Vehicle");
        statusList.add("UI_8411");
        statusList.add("MH -2018");
        statusList.add("MH -2017");
        statusList.add("MH -20149");
        statusList.add("MH -20143");
        statusList.add("MH -20145");
        statusList.add("MH -20142");
        statusList.add("MH -201412");
        statusList.add("MH -201422");
        statusList.add("MH -201423");
        statusList.add("MH -201412");//SHEENUSINGH743
        ArrayAdapter<String> arrayAdapterStatus = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_textview, statusList);
        //   arrayAdapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(arrayAdapterStatus);
    }


    private final void initSpinnerStatusFollowUp2() {
        spinnerSpokenTo2 = (Spinner) findViewById(R.id.spinnerFollowUpType2);
        ArrayList<String> statusList = new ArrayList<String>();
        statusList.add("End Location");
        statusList.add("Mumbai");
        statusList.add("Noida");
        statusList.add("Faridabad");
        statusList.add("Chennai");
        statusList.add("Kolkatta");
        statusList.add("Hydrabad");
        statusList.add("Banglore");

        ArrayAdapter<String> arrayAdapterStatus = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_textview, statusList);
        //   arrayAdapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSpokenTo2.setAdapter(arrayAdapterStatus);
    }

    private final void initSpinnerStatusFollowUp3() {
        spinnerStatus2 = (Spinner) findViewById(R.id.spinnerStatus2);
        ArrayList<String> statusList = new ArrayList<String>();
        statusList.add("Start Location");
        statusList.add("Mumbai");
        statusList.add("Delhi");
        statusList.add("Noida");
        statusList.add("Faridabad");
        statusList.add("Banglore");
        statusList.add("Hydrabad");
        statusList.add("Kolkatta");
        statusList.add("Chennai");

        ArrayAdapter<String> arrayAdapterStatus = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_textview, statusList);
        //   arrayAdapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus2.setAdapter(arrayAdapterStatus);
    }
//******************************************************


    private final void initSpinnerStatusFollowUp4() {
        productname = (Spinner) findViewById(R.id.productname);
        ArrayList<String> statusList = new ArrayList<String>();
        statusList.add("Product name");
        statusList.add("Vehicle");
        statusList.add("Clothes");
        statusList.add("Metals");
        statusList.add("Electonics");
        statusList.add("Food");
        statusList.add("Others");

        ArrayAdapter<String> arrayAdapterStatus = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_textview, statusList);
        //   arrayAdapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productname.setAdapter(arrayAdapterStatus);
    }


    private final void initSpinnerStatusFollowUp5() {
        did = (Spinner) findViewById(R.id.did);
        ArrayList<String> statusList = new ArrayList<String>();
        statusList.add("Trip Category");
        statusList.add("Business travel");
        statusList.add("Cruising (maritime)");
        statusList.add("Diplomatic visits");
        statusList.add("Exploration");
        statusList.add("Royal visits");
        statusList.add("Specialist travel");
        statusList.add("Others");


        ArrayAdapter<String> arrayAdapterStatus = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_textview, statusList);
        //   arrayAdapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        did.setAdapter(arrayAdapterStatus);
    }

    private final void initSpinnerStatusFollowUp6() {
        tcategory = (Spinner) findViewById(R.id.tcategory);
        ArrayList<String> statusList = new ArrayList<String>();
        statusList.add("Driver Id");
        statusList.add("M-1324");
        statusList.add("D-2546");
        statusList.add("S-5456");
        statusList.add("L-9875");
        statusList.add("J-8756");
        statusList.add("k-8756");
        statusList.add("O-4127");


        ArrayAdapter<String> arrayAdapterStatus = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_textview, statusList);
        //   arrayAdapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tcategory.setAdapter(arrayAdapterStatus);
    }


    public void date() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};


                        String month = months[monthOfYear];
                        String date = "" + (dayOfMonth > 9 ? dayOfMonth : "0" + dayOfMonth) + "-" + month + "-" + year;
                        dateToWeb = year + "-" + (monthOfYear + 1) + "-" + (dayOfMonth > 9 ? dayOfMonth : "0" + dayOfMonth);

                        //******************************


                        /*String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
                         String dateToWeb;
                        String month = months[monthOfYear];
                        String date = "" + (dayOfMonth > 9 ? dayOfMonth : "0" + dayOfMonth) + "-" + month + "-" + year;
                        dateToWeb = year + "-" + (monthOfYear + 1) + "-" + (dayOfMonth > 9 ? dayOfMonth : "0" + dayOfMonth);
*/

                        // date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                        if (checkTextView == 1) {


                            startdate5 = dayOfMonth + 30 + year;

                           // startDate1 = dateToWeb;//dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                            startingDate = dateToWeb;//dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                            time();
                            //date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                        } else {

                            endDate5 = dayOfMonth + 30 + year;
                            endDate1 = dateToWeb;//dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                            Eta = dateToWeb;//dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                            time();
                            // time.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                        }


                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }


    public void time() {
        timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        String second = String.valueOf(calendar.get(Calendar.SECOND));
                        int sec = calendar.get(Calendar.SECOND);


                        String time2 = "" + (hourOfDay > 9 ? hourOfDay : "0" + hourOfDay) + ":" + (minute > 9 ? minute : "0" + minute);
                        timeToWebFollowUp = "" + (hourOfDay > 9 ? hourOfDay : "0" + hourOfDay) + ":" + (minute > 9 ? minute : "0" + minute) + ":" + (sec > 9 ? sec : "0" + sec);
                        startTime1=timeToWebFollowUp;

                       // startDate1=dateToWeb;

                        //
                        // time.setText(Eta+","+hourOfDay + ":" + minute);
                        if (checkTextView == 1) {date.setText(startingDate + "," + timeToWebFollowUp);

                            date.setSelected(true);
                            //timePickerDialog.cancel();


                        } else {

                            EndTime1 = timeToWebFollowUp;//hourOfDay + ":" + minute;

                         //   endDate1=Eta;


                            time.setText(Eta + "," + timeToWebFollowUp);

                            time.setSelected(true);//for animation


/*
                            time.startAnimation(AnimationUtils.loadAnimation(MyTrip.this, R.anim.move));
*/
                            //timePickerDialog.cancel();

                        }


                    }


                }, mHour, mMinute, false);


        timePickerDialog.show();
    }


    //**************Allert dialog for others****
    public void open3(View view) {
        LayoutInflater layoutInflater = LayoutInflater.from(MyTrip.this);
        View rootView = layoutInflater.inflate(R.layout.dialogbox, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MyTrip.this);
        alertDialogBuilder.setView(rootView);

        final EditText yourEditText2 = (EditText) rootView.findViewById(R.id.etComments);

        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
//***********************************************Volley Starts Here*****************************
                        String name3 = yourEditText2.getText().toString().substring(0, 1).toUpperCase() + yourEditText2.getText().toString().substring(1).toLowerCase();



                       /* students2.add(yourEditText2.getText().toString());

                        city.setAdapter(new ArrayAdapter<String>(SignUpActivity.this, android.R.layout.simple_spinner_dropdown_item, students2));

                        city.setSelection(students2.indexOf(yourEditText2.getText().toString()));*/

                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                dialog.cancel();
                            }
                        });

        AlertDialog alert = alertDialogBuilder.create();
        alert.show();

        //*******************************Allert Ends Here********************************
    }

    /*public void send()
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url14,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Toast.makeText(MyTrip.this, response, Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {

                        if (error instanceof NetworkError)
                        {
                            Toast.makeText(MyTrip.this,"Cannot connect to Internet...Please check your connection!",Toast.LENGTH_LONG ).show();
                        }
                        else if (error instanceof ServerError)
                        {
                            Toast.makeText(MyTrip.this,"The server could not be found. Please try again after some time!!",Toast.LENGTH_LONG ).show();
                        }
                        else if (error instanceof AuthFailureError)
                        {
                            Toast.makeText(MyTrip.this,"Cannot connect to Internet...Please check your connection !",Toast.LENGTH_LONG ).show();
                        }
                        else if (error instanceof ParseError)
                        {
                            Toast.makeText(MyTrip.this,"Parsing error! Please try again after some time !!",Toast.LENGTH_LONG ).show();

                        }
                        else if (error instanceof NoConnectionError)
                        {
                            Toast.makeText(MyTrip.this,"Cannot connect to Internet...Please check your connection !",Toast.LENGTH_LONG ).show();
                        }
                        else if (error instanceof TimeoutError)
                        {
                            Toast.makeText(MyTrip.this,"Cannot connect to Internet...Please check your connection !",Toast.LENGTH_LONG ).show();
                        }

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> parameters  = new HashMap<String, String>();

*//*
                TripId StartDate ETA  EndLocation Vehicle Customer Driver WeightLoad LR_Issed_By BrancCode Fright DriverId ProductName TripCategory
*//*
//Tripid StartDate StartTime ETA StartLocation EndLocation Vehicle Customer Vendor Driver WeightLoad FixKM LRIssuer BranchCode Fright DriverID ProductName TripCategory
//**********************Transworld url functions starts here *************

                //http://192.168.2.124:/TripWebService/rest/TripEntry?Tripid=20181001MH11596&StartDate=2018-10-01&StartTime=10:00:00&ETA=2018-10-10&StartLocation=Pune&EndLocation=Mumbai&Vehicle=UI_11596&Customer=SmartGrid&Vendor=Halol&Driver=Raj&WeightLoad=0&FixKM=0&LRIssuer=Test&BranchCode=0&Fright=0&DriverID=9951515151&ProductName=Test&TripCategory=Test&ReportDateTime=2018-10-01&format=json
       //------------------------------------------------------------







       //------------------------------------------------------------
             *//*   parameters.put("Tripid",tripid.getText().toString() );
     *//**//*parameters.put("StartDate",date.getText().toString() );*//**//*
                parameters.put("StartDate",startDate1 );
                parameters.put("StartTime",startTime1 );
*//**//*
                parameters.put("ETA",time.getText().toString() );


*//**//*

                parameters.put("ETA",endDate1 );


                parameters.put("StartLocation",spinnerStatus2.getSelectedItem().toString() );
                parameters.put("EndLocation",spinnerSpokenTo2.getSelectedItem().toString() );
                parameters.put("Vehicle",spinnerStatus.getSelectedItem().toString() );

                parameters.put("Customer", customer.getText().toString());
                parameters.put("Vendor", cn.getText().toString());
                parameters.put("Driver", driver.getText().toString());
                parameters.put("WeightLoad", w_load.getText().toString());

                parameters.put("FixKM", f_km.getText().toString());

                parameters.put("LRIssuer", l_r.getText().toString());
                parameters.put("BranchCode", b_code.getText().toString());
                parameters.put("Fright", fright.getText().toString());

                parameters.put("DriverID", tcategory.getSelectedItem().toString());
                parameters.put("ProductName", productname.getSelectedItem().toString());
                parameters.put("TripCategory", did.getSelectedItem().toString());
               *//**//* parameters.put("ReportDateTime", EndTime1);*//**//*

                parameters.put("ReportDateTime", EndTime1);
                parameters.put("format", "json");*//*


     *//*
                DriverID=9951515151&=Test&TripCategory=Test&=2018-10-01
*//*




                //**************************Transworld url ends here ************
               *//* parameters.put("TripId",tripid.getText().toString() );
                parameters.put("StartDate",date.getText().toString() );
                parameters.put("ETA",time.getText().toString() );

                parameters.put("StartLocation",spinnerStatus2.getSelectedItem().toString() );
                parameters.put("EndLocation",spinnerSpokenTo2.getSelectedItem().toString() );
                parameters.put("Vehicle",spinnerStatus.getSelectedItem().toString() );

                parameters.put("Customer", customer.getText().toString());
                parameters.put("cn", cn.getText().toString());
                parameters.put("Driver", driver.getText().toString());
                parameters.put("WeightLoad", w_load.getText().toString());

                parameters.put("fkm", f_km.getText().toString());

                parameters.put("LR_Issed_By", l_r.getText().toString());
                parameters.put("BrancCode", b_code.getText().toString());
                parameters.put("Fright", fright.getText().toString());

                parameters.put("DriverId", tcategory.getSelectedItem().toString());
                parameters.put("ProductName", productname.getSelectedItem().toString());
                parameters.put("TripCategory", did.getSelectedItem().toString());*//*


                jsonformate= String.valueOf(parameters);
                return parameters;


            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }*/


    public boolean CheckDates(String startDate, String endDate) {

        SimpleDateFormat dfDate = new SimpleDateFormat("dd-MMM-yyyy");

        boolean b = false;

        try {
            if (dfDate.parse(startDate).before(dfDate.parse(endDate))) {
                b = true;  // If start date is before end date.

                startDateBeforeEndDate = 1;

                checkDate = 1;


            } else if (dfDate.parse(startDate).equals(dfDate.parse(endDate))) {
                b = true;  // If two dates are equal.
            } else {
                b = false; // If start date is after the end date.
                startDateAfterEndDate = 2;
                checkDate = 2;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return b;
    }

    public void datepicker() {
        DatePickerDialog datePicker = new DatePickerDialog(MyTrip.this, dateListener, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.getDatePicker().setMaxDate(new Date().getTime());
        datePicker.show();
    }


    private class MeDateListenerFollowUp implements DatePickerDialog.OnDateSetListener {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
       // private String dateToWeb;

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String month = months[monthOfYear];
            String date = "" + (dayOfMonth > 9 ? dayOfMonth : "0" + dayOfMonth) + "-" + month + "-" + year;
            dateToWeb = year + "-" + (monthOfYear + 1) + "-" + (dayOfMonth > 9 ? dayOfMonth : "0" + dayOfMonth);
            //  textCurrentDate.setText(date);

            Log.e("Samdate", date);
            Log.e("samweb", dateToWeb);
        }

        public String getDateToWeb() {
            return dateToWeb;
        }
    }

    public void timepicker() {
        TimePickerDialog timePicker = new TimePickerDialog(MyTrip.this, timeListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        timePicker.show();
    }


    private class MeTimeListenerFollowUp implements TimePickerDialog.OnTimeSetListener {


        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            String time = "" + (hourOfDay > 9 ? hourOfDay : "0" + hourOfDay) + ":" + (minute > 9 ? minute : "0" + minute);
            timeToWebFollowUp = "" + (hourOfDay > 9 ? hourOfDay : "0" + hourOfDay) + ":" + (minute > 9 ? minute : "0" + minute) + ":" + calendar.get(Calendar.SECOND);
            // textCurrentTime.setText(time);
        }


    }


    /*public void send2()
    {
        final JsonArrayRequest jsonarrayRequest = new JsonArrayRequest(
                Request.Method.POST,
                //"http://103.241.181.36:8080/VehSummary/rest/VehicleDetails?Username=ubaidullahkhan@bddhalla.com&Password=1gjoQspE&VehCode=11437&LastRec=20&format=json\n",
                "http://192.168.43.167:6060/TripWebService/rest/TripEntry?Tripid=20181001MH115961123&StartDate=2018-10-01&StartTime=10%3A00%3A00&ETA=2018-10-10&StartLocation=Pune&EndLocation=Mumbai&Vehicle=UI_11596&Customer=SmartGrid&Vendor=Halol&Driver=Raj&WeightLoad=0&FixKM=0&LRIssuer=Test&BranchCode=0&Fright=0&DriverID=9951515151&ProductName=Test&TripCategory=Test&ReportDateTime=2018-10-01&format=json",
                //"http://103.241.181.36:8080/VehSummary/rest/VehicleDetails?Username="+"ubaidullahkhan@bddhalla.com"+"&Password="+"1gjoQspE"+"&VehCode="+"11437"+"&LastRec=20&format=json\n",
                new JSONArray(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Log.e("Got Response ","from web service...........");
                        // new MyLogger().storeMassage("got response from web service", ":");

                        Toast.makeText(MyTrip.this, (CharSequence) response, Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MyTrip.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonarrayRequest);

    }*/


    //*******************************************************

    public void send2() {
        try {









            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "http://103.241.181.36:8080/TripWebService/rest/TripEntry?"+"Tripid="+tripid.getText().toString()+"&"+"StartDate="+startingDate+"&"+"StartTime="+startTime1+"&"+"ETA="+Eta+"&"+"StartLocation="+spinnerStatus2.getSelectedItem().toString()+"&"+"EndLocation="+spinnerSpokenTo2.getSelectedItem().toString()+"&"+"Vehicle="+spinnerStatus.getSelectedItem().toString()+"&"+"Customer="+customer.getText().toString()+"&"+"Vendor="+cn.getText().toString()+"&"+"Driver="+driver.getText().toString()+"&"+"WeightLoad="+w_load.getText().toString()+"&"+"FixKM="+f_km.getText().toString()+"&"+"LRIssuer="+l_r.getText().toString()+"&"+"BranchCode="+b_code.getText().toString()+"&"+"Fright="+fright.getText().toString()+"&"+"DriverID="+tcategory.getSelectedItem().toString()+"&"+"ProductName="+productname.getSelectedItem().toString()+"&"+"TripCategory="+did.getSelectedItem().toString()+"&"+"ReportDateTime="+EndTime1+"&"+"format="+"json",
                    //"http://103.241.181.36:8080/VehSummary/rest/UserRegistration?FirstName=" + userName + "&LastName=" + lastName + "&Username=" + userName + lastName + "&EmailId=" + emailID + "&MobileNo=" + mobileNo + "&Address=" + address + "&CompanyCode=" + compCode + "&imeiNo=" + imeiNo + "&OTP=" + OTP + "&format=json",
                    new JSONObject(),
                    new Response.Listener<JSONObject>() {
                        public void onResponse(JSONObject response) {
                            Toast.makeText(getApplicationContext(), "Data posted"+response.toString(), Toast.LENGTH_SHORT).show();
                            Log.e("Exception while 1", "Sending data to server : ");
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MyTrip.this, "Error", Toast.LENGTH_LONG).show();
                    Log.e("Exception while 2", "Sending data to server : ");
                    // new MyLogger().storeMassage("");
                }
            });
            requestQueue.add(jsonObjectRequest);

        } catch (Exception e) {
            Log.e("Exception while ", "Sending data to server : " + e.getMessage());
            Toast.makeText(this, "Oops....Registration Failed Try Later.", Toast.LENGTH_LONG).show();
        }
//***********************************************************************************

        /*StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://103.241.181.36:8080/TripWebService/rest/TripEntry?Tripid=20181011MH11161&StartDate=2018-10-01&StartTime=10:00:00&ETA=2018-10-10&StartLocation=Pune&EndLocation=Mumbai&Vehicle=UI_8954&Customer=SmartGrid&Vendor=Halol&Driver=AmitabBachan&WeightLoad=0&FixKM=0&LRIssuer=Test&BranchCode=0&Fright=0&DriverID=9951515151&ProductName=Test&TripCategory=Test&ReportDateTime=2018-10-01&format=json",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(MyTrip.this, response, Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if (error instanceof NetworkError) {
                            Toast.makeText(MyTrip.this, "Network error ", Toast.LENGTH_LONG).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(MyTrip.this, "The server could not be found. Please try again after some time!!", Toast.LENGTH_LONG).show();
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(MyTrip.this, "Authentication error ", Toast.LENGTH_LONG).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(MyTrip.this, "Parsing error! Please try again after some time !!", Toast.LENGTH_LONG).show();

                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(MyTrip.this, "No connection error ", Toast.LENGTH_LONG).show();
                        } else if (error instanceof TimeoutError) {
                            Toast.makeText(MyTrip.this, "Time out error ", Toast.LENGTH_LONG).show();
                        }

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);*/
    }
}



    //*********************************************************





// tcategory productname did


/*
*
* <?php
if($_SERVER["REQUEST_METHOD"]=="POST")
{



require 'connection.php';
createstudent();
}

function createstudent()
{


global $connect;
$TripId=$_POST["TripId"];
$StartDate=$_POST["StartDate"];
$ETA=$_POST["ETA"];
$StartLocation=$_POST["StartLocation"];
$EndLocation=$_POST["EndLocation"];
$Vehicle=$_POST["Vehicle"];
$Customer=$_POST["Customer"];
$Driver=$_POST["Driver"];
$WeightLoad=$_POST["WeightLoad"];
$LR_Issed_By=$_POST["LR_Issed_By"];
$BrancCode=$_POST["BrancCode"];
$Fright=$_POST["Fright"];
$DriverId=$_POST["DriverId"];
$ProductName=$_POST["ProductName"];
$TripCategory=$_POST["TripCategory"];




$query="Insert into tripentry(TripId,StartDate,ETA,StartLocation,EndLocation,Vehicle,Customer,Driver,WeightLoad,LR_Issed_By,BrancCode,Fright,DriverId,ProductName,TripCategory) values('$TripId','$StartDate','$ETA','$StartLocation','$EndLocation','$Vehicle','$Customer','$Driver','$WeightLoad','$LR_Issed_By','$BrancCode','$Fright','$DriverId','$ProductName','$TripCategory')";

mysqli_query($connect,$query) or die(mysqli_error($connect));


mysqli_close($connect);



}

?>*/



/*

 <?php
 if($_SERVER["REQUEST_METHOD"]=="POST")
 {



 require 'connection.php';
 createstudent();
 }

 function createstudent()
 {


 global $connect;
 $TripId=$_POST["TripId"];
 $StartDate=$_POST["StartDate"];
 $ETA=$_POST["ETA"];
 $StartLocation=$_POST["StartLocation"];
 $EndLocation=$_POST["EndLocation"];
 $Vehicle=$_POST["Vehicle"];
 $Customer=$_POST["Customer"];
 $cn=$_POST["cn"];
 $Driver=$_POST["Driver"];
 $WeightLoad=$_POST["WeightLoad"];
 $fkm=$_POST["fkm"];
 $LR_Issed_By=$_POST["LR_Issed_By"];
 $BrancCode=$_POST["BrancCode"];
 $Fright=$_POST["Fright"];
 $DriverId=$_POST["DriverId"];
 $ProductName=$_POST["ProductName"];
 $TripCategory=$_POST["TripCategory"];




 $query="Insert into tripentry(TripId,StartDate,ETA,StartLocation,EndLocation,Vehicle,Customer,cn,Driver,WeightLoad,fkm,LR_Issed_By,BrancCode,Fright,DriverId,ProductName,TripCategory) values('$TripId','$StartDate','$ETA','$StartLocation','$EndLocation','$Vehicle','$Customer','$cn',$Driver','$WeightLoad','$fkm',$LR_Issed_By','$BrancCode','$Fright','$DriverId','$ProductName','$TripCategory')";

 mysqli_query($connect,$query) or die(mysqli_error($connect));


 mysqli_close($connect);



 }

 ?>*/
