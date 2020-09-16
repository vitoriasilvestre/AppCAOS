package com.example.my;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.ufc.great.caos.api.Caos;
import br.ufc.great.caos.api.config.CaosConfig;
import br.ufc.great.caos.data.DataOffloading;

@CaosConfig(primaryEndpoint = "10.0.2.2")
public class MainActivity4 extends AppCompatActivity {


    @DataOffloading
    User user;

    EditText etCpf;
    EditText etNome;
    EditText etDatanasc;
    Button btAddUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        //Colocar isso para colocar o titulo em cada tela
        getSupportActionBar().setTitle("User registration\n");

        etCpf = (EditText) findViewById(R.id.etCpf);
        etNome = (EditText) findViewById(R.id.etNome);
        etDatanasc = (EditText) findViewById(R.id.etDatanasc);
        btAddUser = (Button) findViewById(R.id.btAddUser);

        Caos.getInstance().start(this, this);

        btAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User();

                user.name = etNome.getText().toString();
                user.cpf = etCpf.getText().toString();
                user.datadenascimento = etDatanasc.getText().toString();

                Caos.getInstance().makeData();
            }
        });
    }

}