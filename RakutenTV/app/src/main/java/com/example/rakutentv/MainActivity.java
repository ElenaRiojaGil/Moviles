package com.enrique.rakutentv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.enrique.rakutentv.adapter.AdapterUser;
import com.enrique.rakutentv.beans.User;
import com.enrique.rakutentv.lstUser.LstUsersContract;
import com.enrique.rakutentv.lstUser.LstUsersPresenter;

import java.util.ArrayList;
import java.util.Hashtable;

public class MainActivity
        extends AppCompatActivity
        implements LstUsersContract.View {

    private EditText edtUser;
    private EditText edtPass;
    private Button btnLogin;
    private LstUsersPresenter lstUsersPresenter;
    private ArrayList<User> lstUsers;
    private String emailenter;
    private String passwordenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Instanciar Presenter*/
        lstUsersPresenter = new LstUsersPresenter(this);
        lstUsersPresenter.getUsers();
        /*Fin*/
        /*Declaramos objetos del layout*/
        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPass = (EditText) findViewById(R.id.edtPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        /*Fin*/
        /*Cargamos SharedPreferences*/
        cargarPreferencias();
        /*Fin*/
        /*Accion al pulsar el boton Login*/
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emailenter=edtUser.getText().toString();
                passwordenter=edtPass.getText().toString();

                comprobarFormato(emailenter,passwordenter);

            }
        });
    }
    /*Fin*/
    @Override
    public void sucessListUsers(final ArrayList<User> lstUsers) {
        AdapterUser adapter = new AdapterUser(lstUsers);
        this.lstUsers=lstUsers;
    }

    @Override
    public void failureListUsers(String message) {

    }
    /*Creamos un AsynkTask que autentificara al usuario,
    guardará sharedpreferences
    e iniciara el nuevo Activity
    desde segundo plano
     */
    class AutentificarSegundoPlano extends AsyncTask<Bundle, String, Boolean>{

        @Override
        protected Boolean doInBackground(Bundle... bundle) {
            Bundle extras = bundle[0];
            String emailenter = extras.getString("email");
            String passwordenter = extras.getString("pass");
            ArrayList<User> lstUser  = extras.getParcelableArrayList("lstUsers");
            for(int i = 0;i<lstUser.size();i++) {
                if(lstUser.get(i).getEmail().equals(emailenter)&&lstUser.get(i).getPassword().equals(passwordenter)){

                    guardarDatosUsuario(lstUser.get(i).getEmail(), lstUser.get(i).getApellido(), lstUser.get(i).getNombre(), lstUser.get(i).getFechaRegistro());

                    return true;

                }
            }return false;
        }
        @Override
        protected void onPostExecute(Boolean resp) {
            try {
                if(resp){
                    guardarPreferencias();

                    Intent intent = new Intent(getApplicationContext(), ListaPeliculas.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Usuario/contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e) {

            }
        }
    }
    /*Fin Asynktask*/

    /*Comprobar Formato*/
    public void comprobarFormato(String emailenter, String passwordenter){
        if(isEmail(emailenter)){
            if(isNumeric(passwordenter)){
                /*Creamos Bundle para mandarlo al AsynkTask que hara la comprobacion*/
                Bundle nuevoBundle = crearBundle(emailenter, passwordenter, lstUsers);
                /*Fin Bundle*/

                /*Autentificamos al usuario desde un AsynkTask*/
                AutentificarSegundoPlano comprobarLogin = new AutentificarSegundoPlano();
                comprobarLogin.execute(nuevoBundle);
                /*Fin*/
            }else{
                Toast.makeText(getApplicationContext(),"La contraseña debe ser numérica", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(getApplicationContext(),"Formato de Email no valido", Toast.LENGTH_SHORT).show();
        }
    }
    /*Fin*/

    /*Metodo para guardar SharedPreferences*/
    public void guardarPreferencias(){
        SharedPreferences savedUser = getSharedPreferences
                ("savedUser", Context.MODE_PRIVATE);
        String usuario = edtUser.getText().toString();
        String password = edtPass.getText().toString();

        SharedPreferences.Editor editor = savedUser.edit();

        editor.putString("USUARIO", usuario);
        editor.putString("CONTRASEÑA", password);

        editor.commit();
    }

    public void guardarDatosUsuario(String email, String apellido, String nombre, String fechaRegistro){
        SharedPreferences guardarDatosUsuario = getSharedPreferences
                ("datosUsuario", Context.MODE_PRIVATE);
        /*String email;
        String password = lstUser.get(i).getPassword();
        String nombre = lstUser.get(i).getNombre();
        String fechaRegistro = lstUser.get(i).getFechaRegistro();
        */
        SharedPreferences.Editor editor = guardarDatosUsuario.edit();

        editor.putString("EMAIL", email);
        editor.putString("APELLIDO", apellido);
        editor.putString("NOMBRE", nombre);
        editor.putString("FECHAREG", fechaRegistro);
        editor.commit();
    }
    /*Fin*/

    /*Metodo para cargar SharedPreferences*/
    public void cargarPreferencias(){
        SharedPreferences savedUser = getSharedPreferences
                ("savedUser", Context.MODE_PRIVATE);

        String user = savedUser.getString("USUARIO", null);
        String password = savedUser.getString("CONTRASEÑA", null);

        edtUser.setText(user);
        edtPass.setText(password);
    }
    /*Fin*/

    /*Método para crear un Bundle*/
    public Bundle crearBundle(String emailenter, String passwordenter, ArrayList<User> lstUsers){
        this.passwordenter=passwordenter;
        this.emailenter=emailenter;
        this.lstUsers=lstUsers;
        Bundle nuevoBundle = new Bundle();
        nuevoBundle.putString("email", emailenter);
        nuevoBundle.putString("pass", passwordenter);
        nuevoBundle.putParcelableArrayList("lstUsers", this.lstUsers);
        return nuevoBundle;
    }
    /*Fin*/

    public  boolean isEmail(String cadena) {
        boolean resultado;
        if (Patterns.EMAIL_ADDRESS.matcher(cadena).matches()) {
            resultado = true;
        } else {
            resultado = false;
        }

        return resultado;
    }

    public  boolean isNumeric(String cadena) {
        boolean resultado;
        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
        return resultado;
    }
}
