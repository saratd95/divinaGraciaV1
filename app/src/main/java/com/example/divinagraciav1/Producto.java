package com.example.divinagraciav1;

public class Producto {
    //tut https://www.youtube.com/watch?v=97x2W3-t3qk min 1:40

    private String post_title; //nombre pto
    private String meta_value; //url img del pto

    //constructor solo
    public Producto(){
    }

    //Metodo de impresion
    public void print_producto(){
        System.out.print(post_title+""+meta_value+"");
    }

    //GETTERS Y SETTERSS
    public String getPost_title() {
        return post_title;
    }
    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }
    public String getMeta_value() {
        return meta_value;
    }
    public void setMeta_value(String meta_value) {
        this.meta_value = meta_value;
    }


}
