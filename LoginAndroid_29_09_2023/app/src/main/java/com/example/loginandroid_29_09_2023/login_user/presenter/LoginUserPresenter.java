package com.example.loginandroid_29_09_2023.login_user.presenter;

import android.widget.Toast;

import com.example.loginandroid_29_09_2023.beans.Pelicula;
import com.example.loginandroid_29_09_2023.beans.User;
import com.example.loginandroid_29_09_2023.login_user.ContractLoginUser;
import com.example.loginandroid_29_09_2023.login_user.model.LoginUserModel;
import com.example.loginandroid_29_09_2023.login_user.view.LoginUserM;

import org.json.JSONException;

import java.util.ArrayList;

import android.os.Handler;

public class LoginUserPresenter implements ContractLoginUser.Presenter, ContractLoginUser.Model.OnLoginUserListener {

    private ContractLoginUser.View view;
    private ContractLoginUser.Model model;

    public LoginUserPresenter(ContractLoginUser.View view) {
        this.view = view;
        model = new LoginUserModel(this);
    }

    @Override
    public void login(User user) {
        //1º) Validar usuario y password.[Validaciones.checkEmail]


        model.findUserWS(user, new ContractLoginUser.Model.OnLoginUserListener() {
            @Override
            public void onFinished(User user) {
                view.successLogin(user);
            }

            @Override
            public void onFailure(String err) {
                view.failureLogin("Login Incorrecto");
            }
        });
    }

    @Override
    public void onFinished(User user) {
        view.successLogin(user);
    }

    @Override
    public void onFailure(String err) {

    }
}
