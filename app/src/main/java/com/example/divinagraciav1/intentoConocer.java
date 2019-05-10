package com.example.divinagraciav1;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class intentoConocer extends AppCompatActivity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;

    private TextView[] mDots;
    private SliderAdapter sliderAdapter;

    Button btn_empezar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intento_conocer);

        /**************************
         * Eventos de los botones *
         **************************/
        btn_empezar=(Button) findViewById(R.id.btnEmpezar);
        btn_empezar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startActivity(new Intent(intentoConocer.this,PantallaPrincipal.class));}
        });

        mSlideViewPager=(ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout=(LinearLayout) findViewById(R.id.dotsLayout);

        sliderAdapter=new SliderAdapter(this  );
        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);


    }//cierra onCreate

    public void addDotsIndicator(int position){
        mDotLayout.removeAllViews(); // esta linea hace que no se agreguen mas puntos cada que se pasa la pantalla
        mDots= new TextView[4];
        for(int i=0; i<mDots.length;i++ ){
            mDots[i]=new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorPtsClaros));
            mDotLayout.addView(mDots[i]);
        }//cierra for

        if(mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorVinoT));
        }//cierra if
    }//cierra addDotsIndicator

    ViewPager.OnPageChangeListener viewListener= new ViewPager.OnPageChangeListener(){
        @Override
        public void onPageScrolled(int i, float v, int i1){

        }

        @Override
        public void onPageSelected(int i){
            addDotsIndicator(i);
        }
        @Override
        public void onPageScrollStateChanged(int i){

        }

    };

}
