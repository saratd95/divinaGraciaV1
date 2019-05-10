package com.example.divinagraciav1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context=context;
    }

    //Arrays
    public int [] slide_images ={
        R.drawable.conocer1,
        R.drawable.conocer2,
         R.drawable.conocer3,
         R.drawable.conocer4,
    };

    public String [] slide_headings={
            "MUESTRANOS \nTU ROSTRO",
            "SELECCIONA TU \nTIPO DE ROSTRO",
            "ENCUENTRA \nY FILTRA",
            "ACCEDE",
    };

    public String [] slide_desc={
            "Toma una foto de tu rostro o selecciona una desde la galeria de tu dispositivo",
            "Identifica tu tipo de rostro con ayuda de guías gráficas",
            "Encuentra una lista de accesorios adecuados según tu tipo de rostro y filtra según el momento del día y el tipo de ropa que usarás con tu accesorio ",
            "Accede al sitio web del producto de tu interés",
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view,  Object o) {
        return view==(ConstraintLayout) o;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position){
        layoutInflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_layout,container,false);
        ImageView slideImageView=(ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading=(TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription=(TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_desc[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        container.removeView((ConstraintLayout)object);
    }


}
