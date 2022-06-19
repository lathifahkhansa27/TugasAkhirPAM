package com.example.taappbiodata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //deklarasi variabel

    Button btnLogin;
    EditText edemail,edpassword,edid;

    String nama,password,id;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //menghubungkan variabel dengan componen button pada layout
        btnLogin=findViewById(R.id.btnLogin);
        edemail=findViewById(R.id.edID);
        edpassword=findViewById(R.id.edPassword);
        edid=findViewById(R.id.edID);

        //membuat fungsi onClick pada button login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //menyimpan input user di edittext email kedalam variabel nama,password
                nama = edemail.getText().toString();
                password = edpassword.getText().toString();
                id = edid.getText().toString();

                //mengeset id yang benar
                String id = "0011";

                //mengeset email yang benar
                String nama = "khansa@mail.com";

                //mengeset password yang benar
                String pass = "123";



                if ( id.isEmpty() || nama.isEmpty() || password.isEmpty()) {
                    //membuat variable toast dan menampilkan pesan "edittext tidak boleh kosong"
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Login harus diisi!!!", Toast.LENGTH_LONG);
                    //menampilkan toast
                    t.show();
                }

                else {
                    //mengecek apakah isi dari email dan password sudah sama dengan email dan password yang sudah diset
                    if ( id.equals(id)&& nama.equals(nama) && password.equals(pass)) {
                        //membuat variable toast dan menampilkan pesan "Login Sukses"
                        Toast t = Toast.makeText(getApplicationContext(),
                                "Login Sukses", Toast.LENGTH_LONG);
                        //menampilkan toast
                        t.show();

                        //membuat objek bundle
                        Bundle b = new Bundle();

                        //memasukan value dari variable password dengan kunci "a" dan dimasukan kedalam bundle
                        b.putString("a", nama.trim());

                        //memasukan value dari variable password dengan kunci "b" dan dimasukan kedalam bundle
                        b.putString("b", password.trim());

                        //membuat objek intent berpinah activity dari mainactivity ke ActivityHome
                        Intent i = new Intent(getApplicationContext(), Home.class);

                        //memasukan bundle kedalam intent untuk dikirimkan ke ActivityHome
                        i.putExtras(b);

                        //berpindah ke ActivityHome
                        startActivity(i);
                    } else {
                        //membuat variable toast dan menampilkan pesan "Login Gagal"
                        Toast t = Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_LONG);

                        //menampilkan toast
                        t.show();
                    }
                }


            }


        });

    }
}