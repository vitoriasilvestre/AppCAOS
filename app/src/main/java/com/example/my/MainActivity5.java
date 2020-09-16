package com.example.my;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity5 extends AppCompatActivity {

    Button btCadastroU;
    Button btCadastroA;
    Button btPesquisar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        //Colocar isso para colocar o titulo em cada tela
        getSupportActionBar().setTitle("Main menu\n\n");

        btPesquisar = (Button) findViewById(R.id.btPesquisar);
        btCadastroU = (Button) findViewById(R.id.btCadastroU);
        btCadastroA = (Button) findViewById(R.id.btCadastroA);



        btCadastroA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity5.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btCadastroU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity5.this, MainActivity4.class);
                startActivity(intent);
            }
        });
        btPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity5.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }

}