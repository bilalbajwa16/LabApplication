package lab.pak.com.glacialappportal;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import lab.pak.com.glacialappportal.task.*;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewTask extends AppCompatActivity {

    private EditText mName, mSpecies, mBreed, mdesig, mtype;

    Calendar myCalendar = Calendar.getInstance();
    String agentid;

    private String agent,name, species, breed, picture, birth, desig, type;
    private int id, gender;

    private Menu action;
    private Bitmap bitmap;

    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mName = findViewById(R.id.name);
        mSpecies = findViewById(R.id.species);
        mBreed = findViewById(R.id.breed);
        mdesig = findViewById(R.id.desig);
        mtype = findViewById(R.id.type);




        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        agent = intent.getStringExtra("agent");
        name = intent.getStringExtra("userid");
        species = intent.getStringExtra("email");
        breed = intent.getStringExtra("description");
        desig = intent.getStringExtra("accomplished");
        type = intent.getStringExtra("state");



        setDataFromIntentExtra();

    }

    private void setDataFromIntentExtra() {

        if (id != 0) {

            readMode();
           // getSupportActionBar().setTitle("Task Details:  " + agent.toString());
            agentid = String.valueOf(id);
            mName.setText(agent);
            mSpecies.setText(species);
            mBreed.setText(breed);
            mdesig.setText(desig);
            mtype.setText(type);


        } else {
            getSupportActionBar().setTitle("Add a Task");
        }
    }






    void readMode(){

        mName.setFocusableInTouchMode(false);
        mSpecies.setFocusableInTouchMode(false);
        mBreed.setFocusableInTouchMode(false);
        mName.setFocusable(false);
        mSpecies.setFocusable(false);
        mBreed.setFocusable(false);
        mtype.setFocusableInTouchMode(false);
        mdesig.setFocusableInTouchMode(false);
        mtype.setFocusable(false);
        mdesig.setFocusable(false);

    }


}
