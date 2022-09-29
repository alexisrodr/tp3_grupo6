package com.example.tp3grupo6.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp3grupo6.ConexionSQLiteHelper.ConexionSQLiteHelper;
import com.example.tp3grupo6.R;
import com.example.tp3grupo6.databinding.FragmentHomeBinding;
import com.example.tp3grupo6.entidades.Parkeo;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    ConexionSQLiteHelper conn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        GridView gridView = root.findViewById(R.id.grillaParkeos);

        //conn = new ConexionSQLiteHelper(getActivity().getApplicationContext(), "usuarios", null, 1 );
        ArrayList<Parkeo> parkeos = new ArrayList<>();
        //parkeos = conn.obtenerParkeos();
        parkeos.add(new Parkeo(1,"ABC", 123, 1));
        parkeos.add(new Parkeo(2,"ADC", 123, 1));
        parkeos.add(new Parkeo(3,"AFC", 123, 1));
        parkeos.add(new Parkeo(4,"AFC", 123, 1));
        parkeos.add(new Parkeo(5,"AFC", 123, 1));
        parkeos.add(new Parkeo(6,"AFC", 123, 1));
        parkeos.add(new Parkeo(7,"AFC", 123, 1));
        parkeos.add(new Parkeo(8,"AFC", 123, 1));
        parkeos.add(new Parkeo(9,"AFC", 123, 1));
        parkeos.add(new Parkeo(10,"AFC", 123, 1));
        gridView.setAdapter(new ParkeoAdapter(getActivity().getApplicationContext(), parkeos));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

class ParkeoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Parkeo> elementos;

    public ParkeoAdapter(Context context, ArrayList<Parkeo> elementos) {
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public int getCount() {
        return elementos.size();
    }

    @Override
    public Parkeo getItem(int i) {
        return elementos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return elementos.get(i).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = convertView;
        if (convertView == null){
            view = inflater.inflate(R.layout.parkeo_grid_item,null);
            view.setBackgroundColor(0xFFFEFEFE);
        }

        TextView matriculaParkeo = (TextView) view.findViewById(R.id.matricula_parkeo_item);
        TextView minutosParkeo = (TextView) view.findViewById(R.id.tiempo_parkeo_item);

        matriculaParkeo.setText(getItem(position).getMatricula());
        minutosParkeo.setText(String.valueOf(getItem(position).getMinutos()));

        return view;
    }
}