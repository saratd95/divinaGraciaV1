package com.example.divinagraciav1;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.util.StringTokenizer;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.Manifest.permission.CAMERA; //import static android.Manifest.permission_group.CAMERA;

public class SelecRostroV1 extends AppCompatActivity {

    TextView txt_ayuda;
    TextView txt_cambiarFoto;
    Button btn_listo;

    //para listado de opciones a seleccionar de rostro en un alertdialog
    String[] listItems;
    Button addRostroBtn;
    TextView nombreRostroSelec;

    /******************
     ** COSAS NUEVAS **
     ******************/
    private final String CARPETA_RAIZ="misImagenesPrueba/";
    private final String RUTA_IMAGEN=CARPETA_RAIZ+"misFotos";

    final int COD_SELECCIONA=10;
    final int COD_FOTO=20;

    Button botonCargar;
    ImageView imagen;
    String path;

    /******************************
     * PARA EL VIEW PAGER ROSTROS *
     ******************************/
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SelecRostroV1.SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selec_rostro_v1);

        nombreRostroSelec=(TextView) findViewById(R.id.rostroSeleccionado);

        /***
         * Eventos de los botones
         ***/
        addRostroBtn=(Button) findViewById(R.id.btnAgregarRostro);;
        //tut para alertDialog con opciones y que la opc se muestre https://www.youtube.com/watch?v=Loj05QkHSSA
        addRostroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //crear lista de opciones
                listItems=new String[]{"Rostro Redondo","Rostro Alargado","Rostro Cuadrado","Rostro Triángulo Invertido"};
                AlertDialog.Builder mBuilder= new AlertDialog.Builder(SelecRostroV1.this);
                mBuilder.setTitle("Selecciona tu tipo de rostro");
                //mBuilder.setIcon(); icono que saldria al lado del titulo
                mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        nombreRostroSelec.setText(listItems[i]);
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                //mostrar alertDialog
                AlertDialog mDialog= mBuilder.create();
                mDialog.show();
            }
        }); //cierra onClickListener del boton addRostroBtn


        /**btn_listo=(Button) findViewById(R.id.btnListo);
        btn_listo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                startActivity(new Intent(SelecRostroV1.this,ResultadosV1.class));}
        });**/
        txt_ayuda = (TextView) findViewById(R.id.txtAyuda);
        txt_cambiarFoto=(TextView) findViewById(R.id.txtCambiarFoto);
        String text="Ayuda";
        String text2="Guia de Rostros";
        SpannableString ss= new SpannableString(text);
        SpannableString ss2=new SpannableString(text2);
        ClickableSpan clickableSpan1= new ClickableSpan() {
            @Override
            public void onClick(View widget) { //Aqui se conf. lo que hara el btn de "AYUDA"
               startActivity(new Intent(SelecRostroV1.this,Dudas.class));
            }
            //metodo para cambiar los estilos, se cambia el color a vino tinto, por defento coloca rojo
            @Override
            public void updateDrawState(TextPaint ds){
                super.updateDrawState(ds);
                ds.setColor(Color.rgb(84,7,33));
            }
        };
        ClickableSpan clickableSpan2= new ClickableSpan() { //Aqui se conf. lo que hara el btn de "CAMBIAR FOTO"
            @Override
            public void onClick(View widget) {
                startActivity(new Intent(SelecRostroV1.this,GuiaRostros.class)); //era PantallaPrincipal
            }
            //metodo para cambiar los estilos, se cambia el color a vino tinto, por defento coloca rojo
            @Override
            public void updateDrawState(TextPaint ds){
                super.updateDrawState(ds);
                ds.setColor(Color.rgb(84,7,33));
            }
        };
        ss.setSpan(clickableSpan1,0,5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss2.setSpan(clickableSpan2,0,12,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt_ayuda.setText(ss);
        txt_cambiarFoto.setText(ss2);
        txt_ayuda.setMovementMethod(LinkMovementMethod.getInstance());
        txt_cambiarFoto.setMovementMethod(LinkMovementMethod.getInstance());

        /*************************************************************
         ********************** CODIGO NUEVO **********************
         *************************************************************/
        imagen= (ImageView) findViewById(R.id.imagemId);
        botonCargar= (Button) findViewById(R.id.btnCargarImg);


        if(validaPermisos()){
            botonCargar.setEnabled(true);
        }else{
            botonCargar.setEnabled(false);
        }

        /******************************
         * PARA EL VIEW PAGER ROSTROS *
         ******************************/
        /** Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);**/ //quite la barra superior
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        //new SectionsPagerAdapter(getSupportFragmentManager())

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }//cierra OnCreate


    //tut mandar parametros de un activity a otro https://www.youtube.com/watch?v=M76NNP-U2gA
    public void eventoBtnListo(View view){
        //generar instancia a una intencion, se va a llamar el activity ResultadosV1
        Intent miIntent= new Intent(SelecRostroV1.this,ResultadosV1.class);
        Bundle miBundle= new Bundle(); //este es el transporte del dato que se quiere mandar
        //el primer dato del bundle, "nombreRostro" es el key que debe estar en el otro activity que va a recibir el dato
        miBundle.putString("nombreRostro",nombreRostroSelec.getText().toString());
        //puente para que pasen los datos
        miIntent.putExtras(miBundle);
        startActivity(miIntent);
    }//cierra eventoBtnListo

    private boolean validaPermisos() {

        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return true;
        }
        if((checkSelfPermission(CAMERA)== PackageManager.PERMISSION_GRANTED)&&
                (checkSelfPermission(WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED)){
            return true;
        }
        if((shouldShowRequestPermissionRationale(CAMERA)) ||
                (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE))){
            cargarDialogoRecomendacion();
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},100);
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==100){
            if(grantResults.length==2 && grantResults[0]==PackageManager.PERMISSION_GRANTED
                    && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                botonCargar.setEnabled(true);
            }else{
                solicitarPermisosManual();
            }
        }
    }

    private void solicitarPermisosManual() {
        final CharSequence[] opciones={"si","no"};
        final AlertDialog.Builder alertOpciones=new AlertDialog.Builder(SelecRostroV1.this);
        alertOpciones.setTitle("¿Desea configurar los permisos de forma manual?");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("si")){
                    Intent intent=new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri=Uri.fromParts("package",getPackageName(),null);
                    intent.setData(uri);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Los permisos no fueron aceptados",Toast.LENGTH_SHORT).show();
                    dialogInterface.dismiss();
                }
            }
        });
        alertOpciones.show();
    }

    private void cargarDialogoRecomendacion() {
        AlertDialog.Builder dialogo=new AlertDialog.Builder(SelecRostroV1.this);
        dialogo.setTitle("Permisos Desactivados");
        dialogo.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la App");

        dialogo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,CAMERA},100);

            }
        });
        dialogo.show();
    }

    public void onclickCargar(View view) {
        cargarImagen();
    }

    private void cargarImagen() {

        final CharSequence[] opciones={"Tomar Foto","Cargar Imagen","Cancelar"};
        final AlertDialog.Builder alertOpciones=new AlertDialog.Builder(SelecRostroV1.this);
        alertOpciones.setTitle("Seleccione una Opción");
        alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (opciones[i].equals("Tomar Foto")){
                    tomarFotografia();
                }else{
                    if (opciones[i].equals("Cargar Imagen")){
                        Intent intent=new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/");
                        startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicación"),COD_SELECCIONA);
                    }else{
                        dialogInterface.dismiss();
                    }
                }
            }
        });
        alertOpciones.show();

    }

    private void tomarFotografia() {
        Toast.makeText(getApplicationContext(),"entra a tomar fotografia",Toast.LENGTH_LONG).show();
        File fileImagen=new File(Environment.getExternalStorageDirectory(),RUTA_IMAGEN);
        boolean isCreada=fileImagen.exists();
        String nombreImagen="";
        if(isCreada==false){
            isCreada=fileImagen.mkdirs();
        }

        if(isCreada==true){
            nombreImagen=(System.currentTimeMillis()/1000)+".jpg";
        }

        path=Environment.getExternalStorageDirectory()+
                File.separator+RUTA_IMAGEN+File.separator+nombreImagen;

        File imagen=new File(path);

        Intent intent=null;

        intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        ////
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
        {
            String authorities=getApplicationContext().getPackageName()+".provider";
            Uri imageUri= FileProvider.getUriForFile(this,authorities,imagen);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        }else
        {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imagen));
        }
        startActivityForResult(intent,COD_FOTO);

        ////
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){

            switch (requestCode){
                case COD_SELECCIONA:
                    Uri miPath=data.getData();
                    imagen.setImageURI(miPath);
                    break;

                case COD_FOTO:
                    MediaScannerConnection.scanFile(this, new String[]{path}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.i("Ruta de almacenamiento","Path: "+path);
                                }
                            });

                    Bitmap bitmap= BitmapFactory.decodeFile(path);
                    imagen.setImageBitmap(bitmap);

                    break;
            }
        }
    }//cierra onActivityResult

    /******************************
     * PARA EL VIEW PAGER ROSTROS *
     ******************************/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selec_rostro_v1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static SelecRostroV1.PlaceholderFragment newInstance(int sectionNumber) {
            SelecRostroV1.PlaceholderFragment fragment = new SelecRostroV1.PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_selec_rostro_v1, container, false);
            //Llamar los campos del fragment_selec_rostro...
            TextView mDescriptionTv = rootView.findViewById(R.id.descriptionTv);
            ImageView mImageViewTv = rootView.findViewById(R.id.imageView);

            //manejo de evento
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 1){
                mDescriptionTv.setText("Rostro Redondo");
                mImageViewTv.setImageResource(R.drawable.rostro_redo);
                //RostroV1 r= new RostroV1();
                //r.setNombreRostro(mDescriptionTv);
            }
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 2){
                mDescriptionTv.setText("Rostro Alargado");
                mImageViewTv.setImageResource(R.drawable.rostro_alargado);
            }
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 3){
                mDescriptionTv.setText("Rostro Cuadrado");
                mImageViewTv.setImageResource(R.drawable.rostro_cuadrado);
            }
            if(getArguments().getInt(ARG_SECTION_NUMBER) == 4){
                mDescriptionTv.setText("Rostro Triángulo Invertido");
                mImageViewTv.setImageResource(R.drawable.rostro_trian_inv);
            }

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return SelecRostroV1.PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Se muestran 4 paginas, correspondientes al nro de tipos de rostros
            return 4;
        }
    }




}
