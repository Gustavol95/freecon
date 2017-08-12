package com.iesoluciones.freecon.network.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iesoluciones.freecon.App;
import com.iesoluciones.freecon.ObservableHelper;
import com.iesoluciones.freecon.R;
import com.iesoluciones.freecon.activities.DrawerActivity;
import com.iesoluciones.freecon.activities.LoginActivity;
import com.iesoluciones.freecon.models.LoginFbResponse;


import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

import butterknife.BindString;
import butterknife.ButterKnife;
import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

/**
 * Created by godoy on 15/03/17.
 */

public abstract class CustomResourceObserver<T> extends ResourceObserver<T> {

    private final static String TAG = CustomResourceObserver.class.getSimpleName();
    private Context context;

    public CustomResourceObserver(Context context) {
        this.context = context;
        this.context.setTheme(R.style.AppTheme);


    }

    private class ResponseError {
        List<String> errors;

        public List<String> getErrors() {
            return errors;
        }

        public void setErrors(List<String> errors) {
            this.errors = errors;
        }

        @Override
        public String toString() {
            return "ResponseError{" +
                    "errors=" + errors +
                    '}';
        }
    }


    @Override
    public void onError(Throwable e) {

        Log.i(TAG,""+e.toString());
        if (e instanceof HttpException) {
            HttpException exception = (HttpException) e;

            switch (exception.code()) {
                case 400:
                case 401:
                case 403:
                case 404:
                case 405:
                case 423:
                case 422:
                    ResponseError error = paserError(exception);

                    if (error.errors.get(0).equalsIgnoreCase(context.getResources().getString(R.string.activation_error))) {
                        Toast.makeText(context, "ENTRÓ PLEBESS", Toast.LENGTH_SHORT).show();
                        final AppCompatEditText input = new AppCompatEditText(context);
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);

                        input.setLayoutParams(lp);
                        new AlertDialog.Builder(context)
                                .setMessage("Ingresa el código que se envió a tu correo.")
                                .setTitle("Ingresar código de activación")
                                .setCancelable(true)
                                .setView(input)
                                .setPositiveButton(App.getInstance().getResources().getString(R.string.button_send), (dialog, id) -> {
                                    //MANDAR A COMPA SERVICIO DE LUISITO
                                    Toast.makeText(context, "" + input.getText().toString(), Toast.LENGTH_SHORT).show();
                                    ObservableHelper.activarCuenta(input.getText().toString())
                                            .subscribe(new CustomResourceObserver<LoginFbResponse>(context) {
                                                @Override
                                                public void onNext(LoginFbResponse value) {
                                                    new AlertDialog.Builder(context)
                                                            .setMessage("Tu cuenta ha sido activada.")
                                                            .setTitle("Registro exitoso")
                                                            .setCancelable(true)
                                                            .setPositiveButton(App.getInstance().getResources().getString(R.string.button_send), (dialog, id) -> {
                                                                //Enviar a drawer activity
                                                                context.startActivity(new Intent(context, DrawerActivity.class));
                                                                ((AppCompatActivity) context).finish();
                                                            }).show();
                                                }
                                            });
                                })
                                .setNegativeButton(context.getResources().getString(R.string.button_cancel), (dialog, which) -> {
                                    dialog.dismiss();
                                }).show();


                        break;
                    }

                    Log.i(TAG, error.toString());
                    new AlertDialog.Builder(context)
                            .setMessage(error.getErrors().get(0))
                            .setTitle("Hubo un problema")
                            .setCancelable(false)
                            .setPositiveButton(App.getInstance().getResources().getString(R.string.button_ok), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            }).show();


                    break;




                case 500:

                    new AlertDialog.Builder(context)
                            .setMessage(App.getInstance().getResources().getString(R.string.unexpected_error))
                            .setTitle(App.getInstance().getResources().getString(R.string.title_error))
                            .setCancelable(false)
                            .setPositiveButton(App.getInstance().getResources().getString(R.string.button_ok), null).show();


                    break;

            }


        } else if (e instanceof IOException) {

            if (e instanceof SocketException) {

                new AlertDialog.Builder(context)
                        .setMessage(App.getInstance().getResources().getString(R.string.unreachable_network))
                        .setTitle(App.getInstance().getResources().getString(R.string.title_error))
                        .setCancelable(false)
                        .setPositiveButton(App.getInstance().getResources().getString(R.string.button_ok), null)
                        .setNeutralButton(App.getInstance().getResources().getString(R.string.network_settings), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.setClassName("com.android.phone", "com.android.phone.NetworkSetting");
                                context.startActivity(intent);

                            }
                        })
                        .show();
            } else if (e instanceof SocketTimeoutException) {


                new AlertDialog.Builder(context)
                        .setMessage(App.getInstance().getResources().getString(R.string.timeout))
                        .setTitle(App.getInstance().getResources().getString(R.string.title_error))
                        .setCancelable(false)
                        .setPositiveButton(App.getInstance().getResources().getString(R.string.button_ok), null)
                        .show();

            } else if (e instanceof UnknownHostException) {

                Toast.makeText(context, context.getString(R.string.unknown_host_exception), Toast.LENGTH_LONG).show();

            } else {


                Toast.makeText(context, context.getString(R.string.unexpected_error), Toast.LENGTH_LONG).show();

            }


        }

        this.onComplete();

    }

    @Override
    public void onComplete() {

    }

    private ResponseError paserError(HttpException e) {

        ResponseError responseError = null;
        String mensaje = null;
        String body = null;
        try {

            body = e.response().errorBody().source().readUtf8().toString();
            Gson gson = new Gson();
            responseError = gson.fromJson(body, ResponseError.class);

        } catch (IOException ex) {

            mensaje = body;

        }

        return responseError;

    }


}
