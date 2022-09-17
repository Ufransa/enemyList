package com.example.listadoenemigos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdapterEnemigo extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<Enemigo> objects;

    public AdapterEnemigo(@NonNull Context context, int resource, @NonNull ArrayList<Enemigo> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        TextView text = convertView.findViewById(R.id.nombre);
        text.setText(objects.get(position).nombre);

        return convertView;
    }
}
