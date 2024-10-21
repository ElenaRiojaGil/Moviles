package com.example.loginandroid_29_09_2023.login_user.model;

import android.os.Handler;
import android.util.Log;

import com.example.loginandroid_29_09_2023.beans.User;
import com.example.loginandroid_29_09_2023.login_user.ContractLoginUser;
import com.example.loginandroid_29_09_2023.login_user.model.data.MyData;
import com.example.loginandroid_29_09_2023.login_user.presenter.LoginUserPresenter;
import com.example.loginandroid_29_09_2023.utils.ApiResponse;
import com.example.loginandroid_29_09_2023.utils.ApiService;
import com.example.loginandroid_29_09_2023.utils.LoginParams;
import com.example.loginandroid_29_09_2023.utils.RetrofitCliente;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginUserModel implements ContractLoginUser.Model {
    private static final String IP_BASE = "192.168.104.50:3000";
    //private static final String IP_BASE = "127.0.0.1:3000";
    private LoginUserPresenter presenter;

    public LoginUserModel(LoginUserPresenter presenter) {
        this.presenter = presenter;
    }
@Override
public void findUserWS(User user, OnLoginUserListener promise){
    try {
        final Handler manejador = new Handler();
        manejador.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean success = true;
                if (success) {
                    User user =new User();
                    promise.onFinished(user);
                } else {
                    promise.onFailure("Loging Incorrecto");
                }

            }
        })


    } catch (Exception e) {
        e.printStackTrace();
    }
}
/**
    @Override
    public void loginAPI(User user, final OnLoginUserListener onLoginUserListener) {
        // Crear una instancia de ApiService
        ApiService apiService = RetrofitCliente.getClient("http://" + IP_BASE + "").
                create(ApiService.class);

// Realizar la solicitud al Servlet
        // Call<MyData> call = apiService.getMyData("1");
        LoginParams loginParams = new LoginParams("prueba@example.com",
                "password1");
                /*
                {
                      "email": "prueba@example.com",
                      "password": "password1"
                    }

        Call<ApiResponse> call = apiService.login(loginParams);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    // Procesar la respuesta aquí
                    ApiResponse myData = response.body();

                    //String message = myData.getMessage();

                    ArrayList<User> lstUsers = myData.getLstUsers();

                    onLoginUserListener.onFinished(lstUsers.get(0));

                    // Actualizar la interfaz de usuario con el mensaje recibido
                } else {
                    // Manejar una respuesta no exitosa
                    // Manejar una respuesta no exitosa
                    Log.e("Response Error", "Código de estado HTTP: " + response.code());
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("Response Error", "Cuerpo de error: " + errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Manejar errores de red o del servidor
                Log.e("Response Error", "Cuerpo de error: " + t.getMessage());
            }
        });
    }
    **/
}
