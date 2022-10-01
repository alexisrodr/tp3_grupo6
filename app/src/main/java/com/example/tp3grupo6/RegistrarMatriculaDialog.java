package com.example.tp3grupo6;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.tp3grupo6.ConexionSQLiteHelper.ConexionSQLiteHelper;
import com.example.tp3grupo6.entidades.Usuario;

public class RegistrarMatriculaDialog extends AppCompatDialogFragment {
    private EditText etMatricula;
    private EditText etMinutos;
    private RegistrarMatriculaDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("Registrar")
                .setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Cancelado", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("Registrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String matricula=etMatricula.getText().toString();
                        String minutos=etMinutos.getText().toString();
                        listener.obtenerTexto(matricula,minutos);
                    }
                });
        etMatricula = (EditText) view.findViewById(R.id.etMatricula);
        etMinutos = (EditText) view.findViewById(R.id.etMinutos);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener=(RegistrarMatriculaDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+
                    "Debe implementar interface RegistrarMatriculaDialogListener");
        }
    }

    public interface RegistrarMatriculaDialogListener{
        void obtenerTexto(String matricula,String minutos);
    }
}


