package com.company.alex.seleccioncontacto;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(intent, 100);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Uri uri = null;
        Cursor cursor = null;

        if(resultCode == RESULT_OK){

            uri = data.getData();

            //CONTENT PROVIDER
            Log.e("Uri recuperado = ", uri.toString());

            cursor =  getContentResolver().query(uri, null, null, null, null); //Te devuelve un cursor y nos vamos al primer elemento
            cursor.moveToFirst();

           // String [] infoContacto = obtenerContactoUri(uri); //TODO implementar una funcion que te meta los contactos en el array
           // mostrarInfoContacto(infoContacto[0], infoContacto[1]); //TODO implementar funcion que muestre los contacto

            int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER); //Estoy cogiendo el numero de la columna para despues coger la informacion. NO EL NUMERO DE TELEFONO!
            int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

            Log.e("Phone index = ", "" + phoneIndex);

            Log.e("Name index = ", "" + nameIndex);

            String numero = cursor.getString(phoneIndex); //De aqui cojo el string asociado a la columna del numero
            String nombre = cursor.getString(nameIndex);//De aqui cojo el string asociado a la columna del nombre

            TextView textViewNumero = (TextView) findViewById(R.id.numero);
            textViewNumero.setText(numero);

            TextView textViewNombre = (TextView) findViewById(R.id.nombre);
            textViewNombre.setText(nombre);

        }

    }
}
