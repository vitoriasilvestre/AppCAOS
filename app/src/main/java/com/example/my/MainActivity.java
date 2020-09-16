package com.example.my;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Random;

import br.ufc.great.caos.api.Caos;
import br.ufc.great.caos.api.config.CaosConfig;
import br.ufc.great.caos.api.config.Inject;
import br.ufc.great.caos.data.DataOffloading;
import br.ufc.great.caos.data.Sensor;
import br.ufc.great.syssu.base.Tuple;

@CaosConfig(primaryEndpoint = "10.0.2.2")
public class MainActivity extends AppCompatActivity {

    @DataOffloading
    Medical medical;

    @Inject(Read.class)
    IRead read;

    TextView tvNomeCPF;
    EditText etCPF;
    Button btCPF;
    Button btOffloading;
    Button btGet;
    Button btPesquisar;
    Button btCadastro;
    CheckBox cbFebre;
    CheckBox cbDCorpo;
    CheckBox cbDCabeca;
    CheckBox cbAnalgesico;
    CheckBox cbAntibiotico;
    CheckBox cbCancer;
    CheckBox cbCovid;
    String cpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Colocar isso para colocar o titulo em cada tela
        getSupportActionBar().setTitle("Medical records\n");

        etCPF = (EditText) findViewById(R.id.etNome);
        btCPF = (Button) findViewById(R.id.btCPF);
        tvNomeCPF = (TextView) findViewById(R.id.tvNameCPF);
        btOffloading = (Button) findViewById(R.id.btOffloading);
        btGet = (Button) findViewById(R.id.btGet);
        btPesquisar = (Button) findViewById(R.id.btPesquisar);
        btCadastro = (Button) findViewById(R.id.btCadastroU);
        cbFebre = (CheckBox) findViewById(R.id.cbFebre);
        cbDCabeca = (CheckBox) findViewById(R.id.cbDCabeca);
        cbDCorpo = (CheckBox) findViewById(R.id.cbDCorpo);
        cbAnalgesico = (CheckBox) findViewById(R.id.cbAnalgesico);
        cbAntibiotico = (CheckBox) findViewById(R.id.cbAntibiotico);
        cbCancer = (CheckBox) findViewById(R.id.cbCancer);
        cbCovid = (CheckBox) findViewById(R.id.cbCovid);

        Caos.getInstance().start(this, this);

        btCPF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tuple tuple = read.getNameCPF(getApplicationContext().getPackageName(), etCPF.getText().toString());
                tvNomeCPF.setText(tuple.getField("name").getValue().toString());
                cpf = tuple.getField("cpf").getValue().toString();
            }
        });

        btOffloading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medical = new Medical();
                medical.cpf = cpf;

                ArrayList<String> listDoencas = new ArrayList();

                if(cbFebre.isChecked()) {
                    listDoencas.add(cbFebre.getText().toString());
                }
                if(cbDCorpo.isChecked()) {
                    listDoencas.add(cbDCorpo.getText().toString());
                }
                if(cbDCabeca.isChecked()) {
                    listDoencas.add(cbDCabeca.getText().toString());
                }
                medical.symptoms = listDoencas;

                ArrayList<String> listMedicacao = new ArrayList();

                if(cbAntibiotico.isChecked()) {
                    listMedicacao.add(cbAntibiotico.getText().toString());
                }
                if(cbAnalgesico.isChecked()) {
                    listMedicacao.add(cbAnalgesico.getText().toString());
                }
                medical.medication = listMedicacao;

                ArrayList<String> listDiagnostico = new ArrayList();

                if(cbCancer.isChecked()) {
                    listDiagnostico.add(cbCancer.getText().toString());
                }
                if(cbCovid.isChecked()) {
                    listDiagnostico.add(cbCovid.getText().toString());
                }

                medical.diagnostico = listDiagnostico;

                //Sensor
                medical.sensorTemperature = new Sensor("smartwatch.temperature", BigDecimal.valueOf(new Random().nextDouble() * (42 - 35) + 35)
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue());

                ArrayList<Double> list = new ArrayList<>();
                list.add(BigDecimal.valueOf(new Random().nextDouble() * (3) + 2)
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue());
                list.add(BigDecimal.valueOf(new Random().nextDouble() * (10) + 30)
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue());

                medical.sensorLocation = new Sensor("smartwatch.location", list);

                medical.sensorHeart = new Sensor("smartwatch.heart", BigDecimal.valueOf(new Random().nextDouble() * (120) + 40)
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue());



                Caos.getInstance().makeData();
            }
        });

        btPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity4.class);
                startActivity(intent);
            }
        });
    }


    //Colocar isso para o destruir tela funcionar
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    //Colocar isso em todas as telas para destruir quando sai dela
    ///@Override
   // protected void onDestroy() {
   //     super.onDestroy();
   //     Log.i("Destroy", "FIM");
//
    //}
}
