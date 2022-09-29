package com.example.tp3grupo6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tp3grupo6.ConexionSQLiteHelper.ConexionSQLiteHelper;
import com.example.tp3grupo6.entidades.Usuario;

public class Registrarse extends AppCompatActivity {
    EditText editName,editEmail,editNewPassword,editRepeatPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        editName= (EditText) findViewById(R.id.editName);
        editEmail= (EditText) findViewById(R.id.editEmail);
        editNewPassword= (EditText) findViewById(R.id.editNewPassword);
        editRepeatPassword= (EditText) findViewById(R.id.editRepeatPassword);

    }

    public void Registrar(View view){
        String mensaje;
        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,
                "usuarios",null,1);
        Usuario usuario;
        String nombre= editName.getText().toString();
        String email=editEmail.getText().toString();
        String newPass= editNewPassword.getText().toString();
        String repPass= editRepeatPassword.getText().toString();

        if(!nombre.isEmpty() && !email.isEmpty() && !newPass.isEmpty() && !repPass.isEmpty()){
            if(newPass.compareTo(repPass)==0){
                usuario= new Usuario(nombre,email,newPass);

                boolean registrado = conn.createUsuario(usuario);

                if(registrado)

                {
                    mensaje="Usuario "+nombre+ " registrado";

                    editName.setText("");
                    editEmail.setText("");
                    editNewPassword.setText("");
                    editRepeatPassword.setText("");

                    finish();

                }
                else
                {
                    mensaje="El usuario " + email + " ya se encuentra registrado.";
                }


            }
            else
                mensaje="Las contraseñas no coinciden";
        }
        else
            mensaje="Debe completar todos los campos";

        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}