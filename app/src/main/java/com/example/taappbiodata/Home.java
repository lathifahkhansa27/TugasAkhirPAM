package com.example.taappbiodata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.taappbiodata.adapter.DataAdapter;
import com.example.taappbiodata.database.karyawan;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private DataAdapter adapter;
    private ArrayList<karyawan> karyawanArrayList = new ArrayList<>();

    private static final String TAG       = Home.class.getSimpleName();
    private static String  url_select     = "https://20200140120.praktikumtiumy.com/bacateman.php";
    public  static final String TAG_ID    = "id";
    public  static final String TAG_NAMA  = "nama";
    public  static final String TAG_Tanggallahir  = "tanggal lahir";
    public  static final String TAG_jeniskelamin  = "jenis kelamin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.btnadd);
        BacaData();
        adapter = new DataAdapter(karyawanArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Home.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, TambahTeman.class);
                startActivity(intent);
            }
        });

    }

    public void  BacaData(){

        karyawanArrayList.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        JsonArrayRequest jArr = new JsonArrayRequest(url_select, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());

                // parsing json
                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject obj = response.getJSONObject(i);

                        karyawan item = new karyawan();

                        item.setId(obj.getString(TAG_ID));
                        item.setNama(obj.getString(TAG_NAMA));
                        item.setTanggallahir(obj.getString(TAG_Tanggallahir));
                        item.setJeniskelamin(obj.getString(TAG_jeniskelamin));


                        // menambah item ke array
                        karyawanArrayList.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                error.printStackTrace();
                Toast.makeText(Home.this, "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jArr);
    }
}








