package com.example.divinagraciav1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GuiaRostros extends AppCompatActivity {

    Button btn_volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guia_rostros);

        /**************************
         * Eventos de los botones *
         **************************/
        btn_volver=(Button) findViewById(R.id.btnVolver);
        btn_volver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startActivity(new Intent(GuiaRostros.this,SelecRostroV1.class));}
        });
    }
}
