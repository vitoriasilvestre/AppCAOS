package com.example.my;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import br.ufc.great.caos.api.Caos;
import br.ufc.great.caos.api.config.CaosConfig;
import br.ufc.great.caos.api.config.Inject;

@CaosConfig(primaryEndpoint = "10.0.2.2")
public class MainActivity2 extends AppCompatActivity {

    Button btPesquisaA;
    RadioGroup group;
    TextView tvResponse;

    @Inject(Read.class)
    IRead read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Colocar isso para colocar o titulo em cada tela
        getSupportActionBar().setTitle("Filters\n");

        tvResponse = (TextView) findViewById(R.id.tvvResponse);

        Caos.getInstance().start(this, this);

        group = (RadioGroup) findViewById(R.id.radioGroup);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton button = (RadioButton) group.findViewById(checkedId);
                String resposta = button.getText().toString();

                if(resposta.equals("Média aritmética da temperatura do ambiente de todos os usuários")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvResponse.setText(read.mediaTempAllUsers(getApplicationContext().getPackageName()));
                        }
                    });
                } else if(resposta.equals("Média aritmética dos batimentos de todos os usuários")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvResponse.setText(read.mediaBatiUser(getApplicationContext().getPackageName()));
                        }
                    });
                } else if (resposta.equals("Média aritmética dos batimentos dos dez primeiros registros armazenados")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvResponse.setText(read.mediaDezPBatiUser(getApplicationContext().getPackageName()));
                        }
                    });
                } else if (resposta.equals("Quantidade de pessoas com cancer")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvResponse.setText(""+read.getDiagnostico(getApplicationContext().getPackageName(), "[Cancer]"));
                        }
                    });
                } else if (resposta.equals("Quantidade de pessoas com covid")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvResponse.setText(""+read.getDiagnostico(getApplicationContext().getPackageName(), "[Covid]"));
                        }
                    });
                } else if (resposta.equals("Quantidade de pessoas que tomaram analgésico")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvResponse.setText(""+read.getMedication(getApplicationContext().getPackageName(), "[Analgesic]"));
                        }
                    });
                }
                else if (resposta.equals("Quantidade de pessoas que tem Febre, Dor no corpo e Dor de cabeça")) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvResponse.setText(""+read.getSymptoms(getApplicationContext().getPackageName(), "[Fever, Body ache, Headache]"));
                        }
                    });
                }


            }
        });
    }

}