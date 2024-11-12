package com.example.kalendarz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TimePicker wyborCzasu;
    DatePicker wyborDaty;
    Calendar kalendarz;
    Button przyciskGut;
    Button zegarShow;
    Button wyczyscPrzycisk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wyborCzasu = findViewById(R.id.zegarTimePicker);
        wyborDaty = findViewById(R.id.kalendarzDatePicker);
        przyciskGut = findViewById(R.id.guzikPrzeslij);

        kalendarz = Calendar.getInstance();

        zegarShow = findViewById(R.id.wyswZegar);

        wyczyscPrzycisk = findViewById(R.id.resetEditText);

        przyciskGut.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int dzien = wyborDaty.getDayOfMonth();
                        int miesiac = wyborDaty.getMonth();
                        int rok = wyborDaty.getYear();
                        kalendarz.set(dzien,miesiac+1,rok);
                        String dataWys = String.format("data: &02d:%02d:%04d",dzien,miesiac,rok);
                        Toast.makeText(MainActivity.this, dataWys, Toast.LENGTH_SHORT).show();

                        int min = wyborCzasu.getMinute();
                        int godz = wyborCzasu.getHour();

                        String godzinaWys = String.format("godzina: &02d:%02d",godz,min);
                    }
                }
        );

        wyborDaty.setOnDateChangedListener(
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                        Toast.makeText(MainActivity.this, String.format("%02d-%02d-%04d", i2, i1+1, i), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        zegarShow.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TimePickerDialog timePickDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                            }
                        },15,0,true);
                        timePickDialog.show();
                    }
                }
        );

        wyczyscPrzycisk.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                        alertDialog.setMessage("Czy jesteś pewien, że chcesz wyczyścić ten formularz?");
                        alertDialog.setPositiveButton("Oczywiście", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText editText = findViewById(R.id.wyczyscEditText);
                                editText.setText("");
                            }
                        });

                        alertDialog.setNegativeButton("Nope dude", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        alertDialog.show();
                    }
                }
        );
    }
}