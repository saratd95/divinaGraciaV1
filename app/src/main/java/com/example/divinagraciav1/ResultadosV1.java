package com.example.divinagraciav1;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class ResultadosV1 extends AppCompatActivity {

    ImageView imgPtoLlenar;
    TextView nombrePtoLlenar;
    Button _btnBuscar;

    TextView _rostroSelecTraido;
    String txtRostroSelecTraido;
    String url;

    //Drawable areteFormalModerno=getResources().getDrawable(<@drawable/mujer_pantalla_ppal>);

    private RequestQueue requestQueue; //permite hacer solicitud
    private Producto producto;
    private ArrayList <Producto> productos= new ArrayList<>(); //lista enn la que se guardaran instancias de productos

    private XmlPullParserFactory factory;
    private XmlPullParser parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_v1);

        imgPtoLlenar=(ImageView) findViewById(R.id.fotoProducto);
        nombrePtoLlenar=(TextView) findViewById(R.id.nombreProducto);
        _btnBuscar=(Button) findViewById(R.id.btnBuscar);

        //traer dato del nombre del rsotro seleccionado en el activity anterior
        _rostroSelecTraido=(TextView) findViewById(R.id.rostroSeleccionadoTraido);

        Bundle miBundle=this.getIntent().getExtras();
        if(miBundle!=null){ //si el bundle no esta vacio, es decir trae un dato, entonces haga lo siguiente
            String nombreRostroFinal=miBundle.getString("nombreRostro"); //el mismo key que esta en el otro activity
            _rostroSelecTraido.setText(nombreRostroFinal);
        }//cierra if

        //convertir el texto de la variable _rostroSelecTraigo a un string para que pueda ser comparado en el proceso de busqueda
        txtRostroSelecTraido=_rostroSelecTraido.getText().toString();


        //configurar BOTON
        _btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtRostroSelecTraido.equals("Rostro Redondo")){
                    imgPtoLlenar.setImageResource(R.drawable.arete_formal_moderno);
                    imgPtoLlenar.setAlpha((float) 1.0);
                    nombrePtoLlenar.setText("Arete Formal Moderno");

                }else if(txtRostroSelecTraido.equals("Rostro Cuadrado")){
                    imgPtoLlenar.setImageResource(R.drawable.arete_arabescos_de_sueno);
                    imgPtoLlenar.setAlpha((float) 1.0);
                    nombrePtoLlenar.setText("Arete Arabescos de Sueño");

                }else if(txtRostroSelecTraido.equals("Rostro Triángulo Invertido")){
                    imgPtoLlenar.setImageResource(R.drawable.arete_noche_fantastica);
                    imgPtoLlenar.setAlpha((float) 1.0);
                    nombrePtoLlenar.setText("Arete Noche Fantástica");

                }else if(txtRostroSelecTraido.equals("Rostro Alargado")){
                    imgPtoLlenar.setAlpha((float) 0.2);
                    nombrePtoLlenar.setText("NO HAY RESULTADOS ACORDE A TU BÚSQUEDA");
                }

            }
        });


        /**
        //AQUI EMPIEZA LO DE LA CONSULTA Y EL XML
        //ejemplo request con google https://developer.android.com/training/volley/simple#java
        requestQueue= Volley.newRequestQueue(this);
        try {
            factory=XmlPullParserFactory.newInstance();
            parser=factory.newPullParser();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        findViewById(R.id.btnBuscar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest=new StringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) { //la url es la del archivo php que hace la consulta en el servidor
                        try {
                            parser.setInput(new StringReader(response));
                            //cuando el parser esta pasando por el xml reconoce eventos
                            //los eventos pueden ser:fin del xml, abierta o cerrada de un tag, y texto entre tags
                            int event=parser.getEventType();

                            String tag="", val="";

                            //para hacer bucle en el xml
                            while(event !=XmlPullParser.END_DOCUMENT){
                                tag=parser.getName(); //en tag se va a guardar el nombre de la etiqueta que se esta leyendo
                                switch (event){
                                    case XmlPullParser.START_TAG:
                                        if(tag.equals("producto")){ //si la etiqueta leida se llama "producto", cree un obj tipo producto y agregelo a la lista
                                            producto=new Producto();
                                            productos.add(producto);
                                        }
                                        break;
                                    case XmlPullParser.TEXT: //aqui se lesta leyendo el valor de la etiqueta en curso y se puede guardar ese daro en val
                                        val=parser.getText();
                                        break;
                                    case XmlPullParser.END_TAG:
                                        //asignar atributos al producto
                                        switch (tag){
                                            case "nomre": //
                                                producto.setPost_title(val);
                                                break;
                                            case "urlimg":
                                                producto.setMeta_value(val);
                                                break;
                                        }
                                        break;
                                }
                                event=parser.next();
                            }
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //for para imprimir los productos segun el tamano del array, la cant de productos que ahi esten
                        for(int i=0; i<productos.size(); i++){
                            productos.get(i).print_producto();
                        }//cierra for de impresion
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

                requestQueue.add(stringRequest);
            }
        });//cierra accion del boton**/

    }//cierra onCreate


}
