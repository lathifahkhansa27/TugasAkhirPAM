package com.example.taappbiodata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TambahTeman extends AppCompatActivity {


    private EditText editNama, editTanggallahir, editjeniskelamin;
    private Button btnSimpan;
    String nm, tgl,jk;
    int success;

    private static String url_insert = "http://10.0.2.2:80/biodatanew/";
    private static final String TAG = TambahTeman.class.getSimpleName();
    private static final String TAG_SUCCES = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_teman);


        editNama = findViewById(R.id.edNama);
        editTanggallahir = findViewById(R.id.edtanggal);
        editjeniskelamin = findViewById(R.id.edjkelamin);
        btnSimpan = findViewById(R.id.btnsimpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                SimpanData();
            }
        });
    }

    public void SimpanData() {
        if (editNama.getText().toString().equals("") || editTanggallahir.getText().toString().equals("") || editjeniskelamin.getText().toString().equals("")) {
            Toast.makeText(TambahTeman.this, "Semua Harus diisi Data!", Toast.LENGTH_SHORT).show();
        } else {
            nm = editNama.getText().toString();
            tgl = editTanggallahir.getText().toString();
            jk = editjeniskelamin.getText().toString();

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

            StringRequest strReq = new StringRequest(Request.Method.POST, url_insert, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "Response : " + response.toString());

                    try {
                        JSONObject jobj = new JSONObject(response);

                        success = jobj.getInt(TAG_SUCCES);
                        if (success == 1) {
                            Toast.makeText(TambahTeman.this, "Sukses Simpan Data", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(TambahTeman.this, "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Error : " + error.getMessage());

                    Toast.makeText(TambahTeman.this, "Gagal Simpan Data!", Toast.LENGTH_SHORT).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();

                    params.put("nama", nm);
                    params.put("tanggallahir", tgl);
                    params.put("jeniskelamin", jk);



                    return params;
                }
            };
            requestQueue.add(strReq);
        }
    }

}