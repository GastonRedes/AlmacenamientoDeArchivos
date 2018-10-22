package gm.almacenamientodearchivos.vistacontrol;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CargaAsyncTask extends AsyncTask<String, Void, String> {

    private Context context;

    CargaAsyncTask(Context context) {

        super();

        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        String usuario, estado, tipo, ruta, linea, s;
        URL url;
        HttpURLConnection httpURLConnection;
        DataOutputStream dataOutputStream;
        File file;
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        StringBuffer stringBuffer;
        byte[] buffer;

        usuario = strings[0];
        estado = strings[1];
        tipo = strings[2];
        ruta = strings[3];

        s = null;

        try {

            url = new URL("http://192.168.1.7/AlmacenamientoDeArchivos/insertar_archivo.php");

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("ENCTYPE", "multipart/form-data");
            httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=*****");

            dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());

            dataOutputStream.writeBytes("--*****\r\n");
            dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"usuario\"" + "\r\n" + "\r\n");
            dataOutputStream.writeBytes(usuario + "\r\n");

            dataOutputStream.writeBytes("--*****\r\n");
            dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"estado\"" + "\r\n" + "\r\n");
            dataOutputStream.writeBytes(estado.toUpperCase() + "\r\n");

            dataOutputStream.writeBytes("--*****\r\n");
            dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"tipo\"" + "\r\n" + "\r\n");
            dataOutputStream.writeBytes(tipo.toUpperCase() + "\r\n");

            dataOutputStream.writeBytes("--*****\r\n");
            dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"archivo\";filename=\"" + ruta + "\"" + "\r\n" + "\r\n");

            file = new File(ruta);
            fileInputStream = new FileInputStream(file);
            buffer = new byte[1024];

            while (fileInputStream.read(buffer) > 0) {
                dataOutputStream.write(buffer);
            }

            dataOutputStream.writeBytes("\r\n");
            dataOutputStream.writeBytes("--*****--");

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                stringBuffer = new StringBuffer();

                while ((linea = bufferedReader.readLine()) != null) {
                    stringBuffer.append(linea);
                }

                s = stringBuffer.toString();

                inputStreamReader.close();
                bufferedReader.close();

            } else {

                s = httpURLConnection.getResponseMessage();
            }

            fileInputStream.close();
            dataOutputStream.close();

        } catch (IOException exception) {

            exception.printStackTrace();
        }

        return s;
    }

    @Override
    protected void onPostExecute(String s) {

        Toast toast;

        toast = Toast.makeText(context, s, Toast.LENGTH_LONG);
        toast.show();
    }
}
