package com.example.tp3grupo6;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.tp3grupo6.ConexionSQLiteHelper.ConexionSQLiteHelper;
import com.example.tp3grupo6.entidades.Parkeo;
import com.example.tp3grupo6.entidades.Usuario;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tp3grupo6.databinding.ActivityHomeBinding;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , RegistrarMatriculaDialog.RegistrarMatriculaDialogListener{

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;
    private Usuario usuario;
    private NavController navController;
    private ConexionSQLiteHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        usuario = (Usuario) bundle.getSerializable("user");
        Bundle args = new Bundle();
        args.putInt("iduser", usuario.getId());

        setSupportActionBar(binding.appBarHome.toolbar);
        binding.appBarHome.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openDialog();
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        navController.setGraph(R.navigation.mobile_navigation, args);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery)
                .setOpenableLayout(drawer)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        navigationView.setNavigationItemSelectedListener(this);
        MenuItem menuItem = navigationView.getMenu().getItem(0);
        onNavigationItemSelected(menuItem);
        menuItem.setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Bundle args = new Bundle();

        switch(item.getItemId()) {
            case R.id.nav_home:
                args.putInt("iduser", usuario.getId());
                navController.navigate(R.id.nav_home, args);
                break;
            case R.id.nav_gallery:
                args.putSerializable("usuario", usuario);
                navController.navigate(R.id.nav_gallery, args);
                break;
        }
        return true;
    }

    public void openDialog(){
        RegistrarMatriculaDialog dialog = new RegistrarMatriculaDialog();
        dialog.show(getSupportFragmentManager(),"Registrar Parqueo");
    }

    @Override
    public void obtenerTexto(String matricula, String minutos) {
        String mensaje= "";
        try {
            if(!matricula.isEmpty() && !minutos.isEmpty()){
                con= new ConexionSQLiteHelper(this, "usuarios", null, 1);
                Parkeo parkeo= new Parkeo(matricula,Integer.parseInt(minutos),usuario.getId());
                if(con.createParkeo(parkeo))
                    mensaje = "Registrado Matricula: "+matricula+" minutos: "+minutos;
                else
                    mensaje= "No se Registrar el parkeo";
            }
            else
                mensaje= "Debe completar todos los campos";

        } catch (Exception e) {
            e.printStackTrace();
        }
        Bundle args = new Bundle();
        args.putInt("iduser", usuario.getId());
        navController.navigate(R.id.nav_home, args);

        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}