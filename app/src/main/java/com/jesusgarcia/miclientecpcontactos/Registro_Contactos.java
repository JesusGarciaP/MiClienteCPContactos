package com.jesusgarcia.miclientecpcontactos;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Registro_Contactos extends AppCompatActivity {

    Button btnGuardar;
    EditText txtUsuario, txtFechaNac, txtEmail, txtTelefono;
    Intent i;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_contactos);

        btnGuardar = findViewById(R.id.btnGuardarC);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtFechaNac = findViewById(R.id.txtFechaNac);
        txtEmail = findViewById(R.id.txtEmail);
        txtTelefono = findViewById(R.id.txtTelefono);

        i = getIntent();
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = txtUsuario.getText().toString();
                String email = txtEmail.getText().toString();
                String telefono = txtTelefono.getText().toString();
                String fechaNac = txtFechaNac.getText().toString();

                ContentValues contentValues = new ContentValues();
                contentValues.put(ContractCPContactos.FIELD_USUARIO, usuario);
                contentValues.put(ContractCPContactos.FIELD_EMAIL, email);
                contentValues.put(ContractCPContactos.FIELD_TEL, telefono);
                contentValues.put(ContractCPContactos.FIELD_FECHANACIMIENTO, fechaNac);

                getContentResolver().insert(ContractCPContactos.CONTENT_URI, contentValues);
                setResult(MainActivity.RESULT_OK, i);
                finish();
            }
        });
    }
    public void btnCancel_click(View v){
        setResult(RESULT_CANCELED  , i );
        Toast.makeText(this, "Contacto no creado", Toast.LENGTH_SHORT).show();
        finish();
    }
}
