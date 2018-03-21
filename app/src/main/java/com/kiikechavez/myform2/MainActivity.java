package com.kiikechavez.myform2;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static android.widget.CheckBox.*;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etApellido;
    EditText etPassword;
    EditText etConpassword;
    TextView tvInfo;
    String nombre;
    String apellido;
    String password;
    String Conpassword;
    String Fecha;
    String ciudad = "";
    String hobbies = "";
    String sexo = "";
    int day_,month_,year_;
    TextView textFecha;

    private RadioButton masculino, femenino;
    CheckBox cine, helado, deportes, leer, viajar, conocer;

    private static DatePickerDialog.OnDateSetListener dateSelectorListener;

    Spinner spinnerCiudades;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = findViewById(R.id.idInfo);
        etName = findViewById(R.id.nombrehint);
        etApellido = findViewById(R.id.correohint);
        etPassword = findViewById(R.id.password);
        etConpassword = findViewById(R.id.conpassword);
        textFecha = findViewById(R.id.idFecha);

        masculino = (RadioButton)findViewById(R.id.masculino);
        femenino = (RadioButton)findViewById(R.id.femenino);

        cine = (CheckBox)findViewById(R.id.cine);
        helado = (CheckBox)findViewById(R.id.helado);
        deportes = (CheckBox)findViewById(R.id.deportes);
        leer = (CheckBox)findViewById(R.id.leer);
        viajar = (CheckBox)findViewById(R.id.viajar);
        conocer = (CheckBox)findViewById(R.id.conocer);


        Calendar calendario = Calendar.getInstance();
        day_ = calendario.get(Calendar.DAY_OF_MONTH);
        month_ = calendario.get(Calendar.MONTH);
        year_ = calendario.get(Calendar.YEAR);
        textFecha.setText(day_+"/"+(month_+1)+"/"+year_);

        spinnerCiudades = findViewById(R.id.idCiudades);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ciudades_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinnerCiudades.setAdapter(adapter);

        spinnerCiudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //adapterView recibe el adaptar, el view, la posicion
                ciudad = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        dateSelectorListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                if(year>year_){
                    Toast.makeText(MainActivity.this, "Error, año inválido", Toast.LENGTH_SHORT).show();
               }
               else if(month>month_){
                    Toast.makeText(MainActivity.this, "Error, mes inválido", Toast.LENGTH_SHORT).show();
                }

                else if (dayOfMonth>day_ && month>=month_ && year>=year_){
                    Toast.makeText(MainActivity.this, "Error, día inválido", Toast.LENGTH_SHORT).show();
                }

                else{
                    month_ = month;
                    day_ = dayOfMonth;
                    year_ = year;
                    textFecha.setText(day_+"/"+ ( month_+1) + "/"+year_);
                }
            }
        };

    }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case 0:
                return new DatePickerDialog(this, dateSelectorListener,year_,month_,day_);
        }
        return null;
    }

    public void onButtonDate(View view) {
        showDialog(0);
    }

    public void botonGuardar(View view) {
        hobbies = "";
        sexo = "";
        nombre = etName.getText().toString();
        apellido = etApellido.getText().toString();
        password = etPassword.getText().toString();
        Conpassword = etConpassword.getText().toString();
        Fecha = textFecha.getText().toString();

        if(!nombre.equals("") && !apellido.isEmpty() && !password.isEmpty() && !Conpassword.isEmpty()){
            if(password.equals(Conpassword)){

                if(cine.isChecked()){
                    hobbies = hobbies + "Te gusta ir a cine" + "\n";
                }
                if (helado.isChecked()){
                    hobbies = hobbies + "Te gusta comer helado" + "\n";
                }
                if (deportes.isChecked()){
                    hobbies = hobbies + "Te gustan los deportes" + "\n";
                }
                if (leer.isChecked()){
                    hobbies = hobbies + "Te gusta leer" + "\n";
                }
                if (viajar.isChecked()){
                    hobbies = hobbies + "Te gusta viajar" + "\n";
                }
                if (conocer.isChecked()) {
                    hobbies = hobbies + "Te gusta conocer" + "\n";
                }
                if(hobbies.isEmpty()){
                    hobbies = "No ha seleccionado ningún Hobbie";
                }
                //else{ hobbies = "No has seleccionado ningún Hobbie" + "\n";
               // }
                if (masculino.isChecked()){
                    sexo = "Sexo Masculino " + "\n";
                }else if(femenino.isChecked()){
                    sexo = "Sexo Femenino" + "\n";
                }else {
                    sexo = ("No ha seleccionado su sexo");
                }

                tvInfo.setText("Su nombre es: " + nombre   + "\n"
                        + "Su correo es: "      + apellido + "\n"
                        + "Su ciudad es: "      + ciudad   + "\n"
                        + "Su contraseña es: "  + password + "\n"
                        + "Fecha de nacimiento: " + Fecha + "\n"
                        +   "Hobbies: "+ "\n" + hobbies + "\n" + sexo);

            }else{
                Toast.makeText(MainActivity.this, "Error, contraseñas diferentes", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(MainActivity.this, "Error, falta algún campo", Toast.LENGTH_SHORT).show();

        }


    }


}
