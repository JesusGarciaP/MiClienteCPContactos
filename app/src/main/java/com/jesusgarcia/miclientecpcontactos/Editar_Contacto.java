package com.jesusgarcia.miclientecpcontactos;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Editar_Contacto extends AppCompatActivity {

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

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
            return;
        }

        final int idContacto = extras.getInt("_id");
        txtUsuario.setText(extras.getString("usuario"));
        txtEmail.setText(extras.getString("email"));
        txtTelefono.setText(extras.getString("tel"));
        txtFechaNac.setText(extras.getString("fechaNac"));

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nuevoUsuario = txtUsuario.getText().toString();
                String nuevoMail = txtEmail.getText().toString();
                String nuevoTelefono = txtTelefono.getText().toString();
                String nuevaFechaNac = txtFechaNac.getText().toString();

                String dondeId = String.valueOf(idContacto);
                String where = ContractCPContactos.FIELD_ID + " = ?";
                String[] Args = {dondeId};

                ContentValues contentValues = new ContentValues();
                contentValues.put(ContractCPContactos.FIELD_USUARIO, nuevoUsuario);
                contentValues.put(ContractCPContactos.FIELD_EMAIL, nuevoMail);
                contentValues.put(ContractCPContactos.FIELD_TEL, nuevoTelefono);
                contentValues.put(ContractCPContactos.FIELD_FECHANACIMIENTO, nuevaFechaNac);

                int actualizarContacto = getContentResolver().update(ContractCPContactos.CONTENT_URI, contentValues, where, Args);
                Log.i("ACTUALIZAR", "Numero de actualizaciones: " + actualizarContacto);
                setResult(MainActivity.RESULT_OK, i);
                finish();

            }
        });
    }
}
