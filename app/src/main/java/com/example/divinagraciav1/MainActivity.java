package com.example.divinagraciav1;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn_conocer;
    Button btn_empezar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**************************
         * Eventos de los botones *
         **************************/
        btn_conocer=(Button) findViewById(R.id.btnConocer);
        btn_conocer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,intentoConocer.class));}
        });
        btn_empezar=(Button) findViewById(R.id.btnEmpezar);
        btn_empezar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this,PantallaPrincipal.class));}
        });

    }//cierra onCreate


}
