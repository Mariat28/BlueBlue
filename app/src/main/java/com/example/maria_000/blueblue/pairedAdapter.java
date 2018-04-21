package com.example.maria_000.blueblue;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


 class pairedAdapter extends ArrayAdapter<String> {
    public pairedAdapter(@NonNull Context context, String[] k) {
        super(context, R.layout.pairedadapter);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater f= LayoutInflater.from(getContext());
        View c= f.inflate(R.layout.pairedadapter, parent, false);
        String k= getItem(position);
        TextView t= (TextView) c.findViewById(R.id.t2);
        t.setText(k);
        return c;
    }
}
