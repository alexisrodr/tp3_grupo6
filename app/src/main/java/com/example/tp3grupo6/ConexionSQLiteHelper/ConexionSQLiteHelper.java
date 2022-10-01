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

import java.util.ArrayList;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    private String tablaUsuarios = "create table usuarios(" +
            "id integer primary key autoincrement," +
            "nombre text," +
            "correo text, " +
            "password text);";

    private String tablaParkeos = "create table parkeos(" +
            "id integer primary key autoincrement," +
            "matricula text," +
            "minutos integer, " +
            "usuario_id integer);";

    @Override
    public void onCreate(@Nullable SQLiteDatabase db) {

        if(db!=null) {
            db.execSQL(tablaUsuarios);
            db.execSQL(tablaParkeos);
        }


        //createUsuario(new Usuario("Cosme Fulanito","cosme@gmail.com","1234"));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Verificar usuario
    public Usuario VerificarUsuario(String nombre, String password) {

        Usuario usuario = new Usuario();
        SQLiteDatabase bd = getReadableDatabase();

        Cursor fila = bd.rawQuery
                ("select * from usuarios where nombre = '" +
                        nombre + "' and password = '" + password + "' or password = ''", null);


        //bd.query("usuarios", new string[]{"id, nombre, pass"}, "nombre = '" )

        if(fila!=null) {
            if (fila.moveToFirst()) {
                usuario.setId(fila.getInt(0));
                usuario.setNombre(fila.getString(1));
                usuario.setCorreo(fila.getString(2));
            }
            fila.close();
        }

        bd.close();
        return usuario;
    }


    // ABML USUARIOS
    public boolean createUsuario(Usuario usuario) {

        Usuario UsuarioAuxiliar = VerificarUsuario(usuario.getCorreo(), "");
        boolean creado= false;
        SQLiteDatabase bd = getWritableDatabase();
        if (UsuarioAuxiliar.getNombre() == null) {
            ContentValues valores = new ContentValues();

            valores.put("nombre", usuario.getNombre());
            valores.put("correo", usuario.getCorreo());
            valores.put("password", usuario.getPassword());

            bd.insert("usuarios", null, valores);
            creado = true;
        }

        bd.close();
        return creado;

    }

    //CREATE PARKING
    public boolean createParkeo(Parkeo parkeo) {
        long filas;
        boolean creado= false;
        SQLiteDatabase bd = getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("matricula", parkeo.getMatricula());
        valores.put("minutos", parkeo.getMinutos());
        valores.put("usuario_id", parkeo.getUsuario_id());

        filas=bd.insert("parkeos", null, valores);
        if(filas>0){
            creado= true;
        }
        else
            creado=false;
        bd.close();
        return creado;
    }

    public ArrayList<Parkeo> obtenerParkeos(int idUsuario){
        ArrayList<Parkeo> listaParkeo = new ArrayList<>();
        SQLiteDatabase bd = getReadableDatabase();


        Cursor cursor = bd.rawQuery("SELECT * FROM parkeos WHERE usuario_id=" + idUsuario,null);
        if(cursor.moveToFirst()) {
            do {
                Parkeo park = new Parkeo(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3));
                listaParkeo.add(park);
            } while(cursor.moveToNext());
        }
        cursor.close();
        bd.close();
        return listaParkeo;
    }
}
