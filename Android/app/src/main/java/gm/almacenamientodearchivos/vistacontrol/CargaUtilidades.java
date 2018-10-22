package gm.almacenamientodearchivos.vistacontrol;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

public class CargaUtilidades {

    public String getRuta(Context context, Uri uri) {

        Uri contenidoUri;
        String[] proyeccion, seleccionArgs, division;
        String seleccion, idDocumento, tipo, ruta;
        Cursor cursor;
        int indiceColumna;

        contenidoUri = uri;
        proyeccion = new String[]{"_data"};
        seleccion = null;
        seleccionArgs = null;

        ruta = null;

        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {

            if ("com.android.externalstorage.documents".equals(uri.getAuthority())) {

                idDocumento = DocumentsContract.getDocumentId(uri);
                division = idDocumento.split(":");
                ruta = Environment.getExternalStorageDirectory() + "/" + division[1];

                return ruta;

            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {

                idDocumento = DocumentsContract.getDocumentId(uri);
                contenidoUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(idDocumento));

            } else if ("com.android.providers.media.documents".equals(uri.getAuthority())) {

                idDocumento = DocumentsContract.getDocumentId(uri);
                division = idDocumento.split(":");
                tipo = division[0];
                seleccionArgs = new String[]{division[1]};
                seleccion = "_id=?";

                if ("image".equals(tipo)) {
                    contenidoUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(tipo)) {
                    contenidoUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(tipo)) {
                    contenidoUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                } else
                    contenidoUri = MediaStore.Files.getContentUri("external");
            }
        }

        if ("content".equalsIgnoreCase(contenidoUri.getScheme())) {

            try {

                cursor = context.getContentResolver().query(contenidoUri, proyeccion, seleccion, seleccionArgs, null);

                if (cursor != null) {

                    indiceColumna = cursor.getColumnIndexOrThrow("_data");

                    if (cursor.moveToFirst())

                        ruta = cursor.getString(indiceColumna);

                    cursor.close();
                }

            } catch (Exception exception) {

                exception.printStackTrace();
            }

        } else if ("file".equalsIgnoreCase(contenidoUri.getScheme())) {
            ruta = contenidoUri.getPath();
        }

        return ruta;
    }
}
