package com.example.taappbiodata.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.taappbiodata.EditTeman;
import com.example.taappbiodata.Home;
import com.example.taappbiodata.R;
import com.example.taappbiodata.app.AppController;
import com.example.taappbiodata.database.karyawan;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.TemanViewHolder>  {

    private ArrayList<karyawan> listData;

    public DataAdapter(ArrayList<karyawan> listData) {
        this.listData = listData;
    }

    @Override
    public TemanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInf = LayoutInflater.from(parent.getContext());
        View view = layoutInf.inflate(R.layout.row_data, parent, false);
        return new  TemanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TemanViewHolder holder, int position) {
        String id,nma, tgl,jk;

        id = listData.get(position).getId();
        nma = listData.get(position).getNama();
        tgl= listData.get(position).getTanggallahir();
        jk = listData.get(position).getJeniskelamin();

        // Style
        holder.namaTxt.setTextColor(Color.BLUE);
        holder.namaTxt.setTextSize(20);

        // Set Text
        holder.namaTxt.setText(nma);
        holder.tanggallahirTxt.setText(tgl);
        holder.jeniskelamintxt.setText(jk);

        holder.cardku.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu pm = new PopupMenu(view.getContext(),view);
                pm.inflate(R.menu.menu);

                pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.edit:
                                Bundle bendel = new Bundle();
                                bendel.putString("kunci1",id);
                                bendel.putString("kunci2", nma);
                                bendel.putString("kunci3", tgl);
                                bendel.putString("kunci4", jk);


                                Intent inten = new Intent(view.getContext(), EditTeman.class);
                                inten.putExtras(bendel);view.getContext().startActivity(inten);

                                break;

                            case R.id.hapus:
                                AlertDialog.Builder alertdb = new AlertDialog.Builder(view.getContext());
                                alertdb.setTitle("Yakin " + nma + " akan dihapus?");
                                alertdb.setMessage("tekan ya untuk menghapus");
                                alertdb.setCancelable(false);
                                alertdb.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        HapusData(id);
                                        Toast.makeText(view.getContext(), "Data" +id+"telah dihapus", Toast.LENGTH_LONG).show();

                                        Intent intent = new Intent(view.getContext(), Home.class);

                                        view.getContext().startActivity(intent);
                                    }
                                });
                                alertdb.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog adlg = alertdb.create();
                                adlg.show();

                                break;
                        }
                        return true;
                    }

                });
                pm.show();

                return true;
            }
        });

    }

    private void HapusData(final  String idx) {
        String url_update = "http://10.0.2.2:80/umyTI/deletetm.php";

        final String TAG = Home.class.getSimpleName();
        final  String TAG_SUCCES ="succes";
        final int[] sukses = new int[1];

        StringRequest stringReq = new StringRequest(Request.Method.POST, url_update, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Respon: " + response.toString());

                try {
                    JSONObject JObj = new JSONObject(response);
                    sukses[0] = JObj.getInt(TAG_SUCCES);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"Eror : " +error.getMessage());
            }
        })
        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();

                params.put("id", idx);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringReq);
    }



    @Override
    public int getItemCount() {
        return (listData != null) ? listData.size() : 0;
    }

    public class TemanViewHolder extends RecyclerView.ViewHolder {

        private CardView cardku;
        private TextView namaTxt,tanggallahirTxt,jeniskelamintxt;

        public TemanViewHolder(View view) {
            super(view);

            cardku = (CardView) view.findViewById(R.id.card);
            namaTxt = (TextView) view.findViewById(R.id.textNama);
            tanggallahirTxt = (TextView) view.findViewById(R.id.texttanggallahir);
            jeniskelamintxt = (TextView) view.findViewById((R.id.textjeniskelain));

        }
    }
}



