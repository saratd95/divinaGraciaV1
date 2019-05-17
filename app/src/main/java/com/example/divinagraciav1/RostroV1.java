package com.example.divinagraciav1;

import android.widget.TextView;

public class RostroV1 {
    private TextView nombreRostro;

    //constructor conparametro
    public RostroV1(TextView nombreRostro) {
        this.nombreRostro = nombreRostro;
    }

    //constructor sin parametro
    public RostroV1() {
    }

    //tostring
    @Override
    public String toString() {
        return "RostroV1{" +
                "nombreRostro='" + nombreRostro + '\'' +
                '}';
    }

    //GETTERS Y SETTERS
    public TextView getNombreRostro() {
        return nombreRostro;
    }

    public void setNombreRostro(TextView nombreRostro) {
        this.nombreRostro = nombreRostro;
    }

}
