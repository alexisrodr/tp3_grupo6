package com.example.tp3grupo6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp3grupo6.ConexionSQLiteHelper.ConexionSQLiteHelper;
import com.example.tp3grupo6.entidades.Usuario;

public class MainActivity extends AppCompatActivity {

    EditText editName, editPassword;
    ConexionSQLiteHelper Conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void IrRegistrarse(View view) {
        Intent i = new Intent(this, Registrarse.class);
        startActivity(i);
    }

    public void Login(View view) {
        Intent i = new Intent(this, Home.class);

        if (VerificarUsuario()) {
            startActivity(i);
        }
    }

    //Metodo para consultar la base
    public boolean VerificarUsuario() {

        boolean existe = false;
        //ConexionSQLiteHelper Conn = new ConexionSQLiteHelper(this, "Usuarios", null, 1);

        Conn = new ConexionSQLiteHelper(this, "usuarios", null, 1);
        Conn.abrir();



        Usuario usuario = new Usuario();

        editName = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);

        if (!editName.getText().toString().isEmpty() && !editPassword.getText().toString().isEmpty()) {
//            Cursor fila = bd.rawQuery
//                    ("select nombre, password from usuarios where nombre = '" +
//                            editName.getText().toString() + "' and password = '" + editPassword.getText().toString() +"'", null);


            usuario = Conn.VerificarUsuario(editName.getText().toString(), editPassword.getText().toString());
            //bd.query("usuarios", new srtring[]{"id, nombre, pass"}, "nombre = '" )

            if (usuario.getNombre() != null) {

                existe = true;
                Toast.makeText(this, "Ingreso correctamente.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El usuario no existe o es incorrecto", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debe completar los Campos", Toast.LENGTH_SHORT).show();
        }

        return existe;

    }
}