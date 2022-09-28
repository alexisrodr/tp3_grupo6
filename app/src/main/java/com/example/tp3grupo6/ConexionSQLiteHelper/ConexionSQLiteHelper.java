package com.example.tp3grupo6.ConexionSQLiteHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.tp3grupo6.entidades.Parkeo;
import com.example.tp3grupo6.entidades.Usuario;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String createTableUsuarios = "create table usuarios(" +
                "id integer primary key autoincrement," +
                "nombre text," +
                "correo text, " +
                "password text);";

        String createTableParkeos = "create table parkeos(" +
                "id integer primary key autoincrement," +
                "matricula text," +
                "minutos integer, " +
                "usuario_id integer);";

        db.execSQL(createTableUsuarios);
        db.execSQL(createTableParkeos);


        //createUsuario(new Usuario("Cosme Fulanito","cosme@gmail.com","1234"));
        //createParkeo(new Parkeo("ABC123",60,1));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void abrir() {
        this.getWritableDatabase();
    }

    public void cerrar() {
        this.close();
    }


    //Verificar usuario
    public Usuario VerificarUsuario(String correo, String password) {

        Usuario usuario = new Usuario();

        abrir();
        SQLiteDatabase bd = this.getReadableDatabase();

        Cursor fila = bd.rawQuery
                ("select nombre, correo from usuarios where correo = '" +
                        correo + "' and password = '" + password + "' or password = ''", null);


        //bd.query("usuarios", new string[]{"id, nombre, pass"}, "nombre = '" )

        if (fila.moveToFirst()) {
            usuario.setNombre(fila.getString(0));
            usuario.setCorreo(fila.getString(1));
            bd.close();
        }
        cerrar();
        return usuario;
    }


    // ABML USUARIOS
    public boolean createUsuario(Usuario usuario) {

        Usuario UsuarioAuxiliar = VerificarUsuario(usuario.getCorreo(), "");
        if (UsuarioAuxiliar.getNombre() == null) {
            ContentValues valores = new ContentValues();

            valores.put("nombre", usuario.getNombre());
            valores.put("correo", usuario.getCorreo());
            valores.put("password", usuario.getPassword());

            this.getWritableDatabase().insert("usuarios", null, valores);

            cerrar();
            return true;
        }
        return false;


    }

    //CREATE PARKING
    public void createParkeo(Parkeo parkeo) {
        ContentValues valores = new ContentValues();

        valores.put("matricula", parkeo.getMatricula());
        valores.put("minutos", parkeo.getMinutos());
        valores.put("usuario_id", parkeo.getUsuario_id());

        this.getWritableDatabase().insert("parkeos", null, valores);
    }
}
