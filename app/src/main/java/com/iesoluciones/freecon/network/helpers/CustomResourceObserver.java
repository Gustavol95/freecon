package com.iesoluciones.freecon.network.helpers;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iesoluciones.freecon.App;
import com.iesoluciones.freecon.R;


import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

/**
 * Created by godoy on 15/03/17.
 */

public abstract class CustomResourceObserver<T> extends ResourceObserver<T> {

    private final static String TAG = CustomResourceObserver.class.getSimpleName();
    private  Context context;
    public CustomResourceObserver(Context context){
        this.context = context;
        this.context.setTheme(R.style.AppTheme);

    }

    private class ResponseError{
        Integer id;
        String message;
        String hint;

        public Integer getId() {
            return id;
        }

        public String getMessage() {
            return message;
        }

        public String getHint() {
            return hint;
        }
    }


    @Override
    public void onError(Throwable e) {

        if(e instanceof HttpException){
            HttpException exception = (HttpException)e;

            switch (exception.code()){
                case 400 :
                case 401 :
                case 403 :
                case 404 :
                case 405 :
                case 423 :

                    ResponseError error = paserError(exception);
                    new AlertDialog.Builder(context)
                            .setMessage(error.getHint())
                            .setTitle(error.getMessage())
                            .setCancelable(false)
                            .setPositiveButton(App.getInstance().getResources().getString(R.string.button_ok), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            }).show();


                break;

                case 422 : break; // Unprocessable Entity

                case 500 :

                    new AlertDialog.Builder(context)
                            .setMessage(App.getInstance().getResources().getString(R.string.unexpected_error))
                            .setTitle(App.getInstance().getResources().getString(R.string.title_error))
                            .setCancelable(false)
                            .setPositiveButton(App.getInstance().getResources().getString(R.string.button_ok), null).show();


                    break;

            }



        }else if(e instanceof IOException){

            if(e instanceof SocketException){

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
            }else if(e instanceof SocketTimeoutException) {


                new AlertDialog.Builder(context)
                        .setMessage(App.getInstance().getResources().getString(R.string.timeout))
                        .setTitle(App.getInstance().getResources().getString(R.string.title_error))
                        .setCancelable(false)
                        .setPositiveButton(App.getInstance().getResources().getString(R.string.button_ok), null)
                        .show();

            }else if (e instanceof UnknownHostException){

                Toast.makeText(context,context.getString(R.string.unknown_host_exception),Toast.LENGTH_LONG).show();

            }else{


                Toast.makeText(context,context.getString(R.string.unexpected_error),Toast.LENGTH_LONG).show();

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

            body = e.response().errorBody().string();


            Gson gson = new Gson();
            responseError = gson.fromJson(body,ResponseError.class);

        }catch (IOException ex){

            mensaje = body;

        }

        return responseError;

    }





}
