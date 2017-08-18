package com.iesoluciones.freecon.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.phajduk.rxvalidator.RxValidator;
import com.iesoluciones.freecon.App;
import com.iesoluciones.freecon.R;
import com.iesoluciones.freecon.intefaces.RegistroCallback;
import com.iesoluciones.freecon.models.Usuario;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func6;


/**
 * Created by iedeveloper on 07/08/17.
 */

public class RegistroUnoFragment extends Fragment {

    static String TAG = RegistroUnoFragment.class.getCanonicalName();


    @BindView(R.id.textInputNombre)
    TextInputLayout textInputNombre;
    @BindView(R.id.editNombre)
    TextInputEditText editNombre;
    @BindView(R.id.textInputApellido)
    TextInputLayout textInputApellido;
    @BindView(R.id.editApellido)
    TextInputEditText editApellido;
    @BindView(R.id.textInputCorreoElectronico)
    TextInputLayout textInputCorreoElectronico;
    @BindView(R.id.editCorreoElectronico)
    TextInputEditText editCorreoElectronico;
    @BindView(R.id.textInputContrasena)
    TextInputLayout textInputContrasena;
    @BindView(R.id.editContrasena)
    TextInputEditText editContrasena;
    @BindView(R.id.textInputContrasenaConfirmacion)
    TextInputLayout textInputContrasenaConfirmacion;
    @BindView(R.id.editContrasenaConfirmacion)
    TextInputEditText editContrasenaConfirmacion;
    @BindView(R.id.textInputTelefono)
    TextInputLayout textInputTelefono;
    @BindView(R.id.editTelefono)
    TextInputEditText editTelefono;
    @BindView(R.id.textInputCalle)
    TextInputLayout textInputCalle;
    @BindView(R.id.editCalle)
    TextInputEditText editCalle;
    @BindView(R.id.textInputEstado)
    TextInputLayout textInputEstado;
    @BindView(R.id.editEstado)
    TextInputEditText editEstado;
    @BindView(R.id.textInputColonia)
    TextInputLayout textInputColonia;
    @BindView(R.id.editColonia)
    TextInputEditText editColonia;
    @BindView(R.id.textInputCiudad)
    TextInputLayout textInputCiudad;
    @BindView(R.id.editCiudad)
    TextInputEditText editCiudad;
    @BindView(R.id.textInputCP)
    TextInputLayout textInputCP;
    @BindView(R.id.editCP)
    TextInputEditText editCP;
    @BindView(R.id.textInputExterior)
    TextInputLayout textInputExterior;
    @BindView(R.id.editExterior)
    TextInputEditText editExterior;
    @BindView(R.id.textInputInterior)
    TextInputLayout textInputInterior;
    @BindView(R.id.editInterior)
    TextInputEditText editInterior;
    @BindView(R.id.buttonContinuar)
    AppCompatButton buttonContinuar;
    RegistroCallback registroCallback;


    public static RegistroUnoFragment newInstance(RegistroCallback registroCallback) {
        RegistroUnoFragment fragment = new RegistroUnoFragment();
        fragment.setRegistroCallback(registroCallback);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registro_uno, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        if (registroCallback.isFacebookUser()) {
            Usuario temp = App.getInstance().getDaoSession().getUsuarioDao().loadAll().get(0);
            editNombre.setText(temp.getNombre());
            editNombre.setEnabled(false);
            editApellido.setText(temp.getApellido());
            editApellido.setEnabled(false);
            editCorreoElectronico.setText(temp.getEmail());
            editCorreoElectronico.setEnabled(false);
            editContrasena.setEnabled(false);
            editContrasenaConfirmacion.setEnabled(false);

        }
        setUpValidators();
    }

    @OnClick(R.id.buttonContinuar)
    public void continuar() {
        registroCallback.getRegistro().setNombre(editNombre.getText().toString());
        registroCallback.getRegistro().setApellido(editApellido.getText().toString());
        registroCallback.getRegistro().setContrasena(editContrasena.getText().toString());
        registroCallback.getRegistro().setEmail(editCorreoElectronico.getText().toString());
        registroCallback.getRegistro().setCelular(editTelefono.getText().toString());
        registroCallback.getRegistro().setCalle(editCalle.getText().toString());
        registroCallback.getRegistro().setEstado(editEstado.getText().toString());
        registroCallback.getRegistro().setColonia(editColonia.getText().toString());
        registroCallback.getRegistro().setCiudad(editCiudad.getText().toString());
        registroCallback.getRegistro().setCp(editCP.getText().toString());
        registroCallback.getRegistro().setExterior(editExterior.getText().toString());
        registroCallback.getRegistro().setInterior(editInterior.getText().toString());
        registroCallback.pasoUno(registroCallback);

    }

    public RegistroCallback getRegistroCallback() {
        return registroCallback;
    }

    public void setRegistroCallback(RegistroCallback registroCallback) {
        this.registroCallback = registroCallback;
    }

    public void setUpValidators() {

        Observable<Boolean> telefonoValidator =
                RxValidator.createFor(editTelefono)
                        .nonEmpty(getResources().getString(R.string.non_empty))
                        .length(10, getResources().getString(R.string.phone_length))
                        .onFocusChanged()
                        .onValueChanged()
                        .toObservable()
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(result -> {
                            textInputTelefono.setErrorEnabled(result.isProper() ? false : true);
                            textInputTelefono.setError(result.getMessage());
                            return result.isProper();
                        });


        Observable<Boolean> contrasenaValidator =
                RxValidator.createFor(editContrasena)
                        .nonEmpty("No puede estar vacío")
                        .maxLength(15, getResources().getString(R.string.password_max_length))
                        .minLength(8, getResources().getString(R.string.password_min_length))
                        .onFocusChanged()
                        .toObservable()
                        .map(result -> {
                            textInputContrasena.setErrorEnabled(result.isProper() ? false : true);
                            textInputContrasena.setError(result.getMessage());
                            return result.isProper();
                        });


        Observable<Boolean> contrasenaConfirmValidator =
                RxValidator.createFor(editContrasenaConfirmacion)
                        .nonEmpty(getResources().getString(R.string.non_empty))
                        .sameAs(editContrasena, getResources().getString(R.string.password_confirm_dont_match))
                        .maxLength(15, getResources().getString(R.string.password_max_length))
                        .minLength(8, getResources().getString(R.string.password_min_length))
                        .onFocusChanged()
                        .onValueChanged()
                        .toObservable()
                        .map(result -> {
                            textInputContrasenaConfirmacion.setErrorEnabled(result.isProper() ? false : true);
                            textInputContrasenaConfirmacion.setError(result.getMessage());
                            return result.isProper();
                        });


        Observable<Boolean> emailValidator =
                RxValidator.createFor(editCorreoElectronico)
                        .nonEmpty(getResources().getString(R.string.non_empty))
                        .email(getResources().getString(R.string.email_incorrect))
                        .onFocusChanged()
                        .toObservable()
                        .map(result -> {
                            textInputCorreoElectronico.setErrorEnabled(result.isProper() ? false : true);
                            textInputCorreoElectronico.setError(result.getMessage());
                            return result.isProper();
                        });

        Observable<Boolean> lastnameValidator =
                RxValidator.createFor(editApellido)
                        .nonEmpty(getResources().getString(R.string.non_empty))
                        .onFocusChanged()
                        .toObservable()
                        .map(result -> {
                            textInputApellido.setErrorEnabled(result.isProper() ? false : true);
                            textInputApellido.setError(result.getMessage());
                            return result.isProper();
                        });
        Observable<Boolean> firstnameValidator =
                RxValidator.createFor(editNombre)
                        .nonEmpty(getResources().getString(R.string.non_empty))
                        .onFocusChanged()
                        .toObservable()
                        .map(result -> {
                            textInputNombre.setErrorEnabled(result.isProper() ? false : true);
                            textInputNombre.setError(result.getMessage());
                            return result.isProper();
                        });

        Observable<Boolean> calleValidator =
                RxValidator.createFor(editCalle)
                        .nonEmpty(getResources().getString(R.string.non_empty))
                        .onFocusChanged()
                        .toObservable()
                        .map(result -> {
                            textInputCalle.setErrorEnabled(result.isProper() ? false : true);
                            textInputCalle.setError(result.getMessage());
                            return result.isProper();
                        });

        Observable<Boolean> coloniaValidator =
                RxValidator.createFor(editColonia)
                        .nonEmpty(getResources().getString(R.string.non_empty))
                        .onFocusChanged()
                        .toObservable()
                        .map(result -> {
                            textInputColonia.setErrorEnabled(result.isProper() ? false : true);
                            textInputColonia.setError(result.getMessage());
                            return result.isProper();
                        });

        Observable<Boolean> ciudadValidator =
                RxValidator.createFor(editCiudad)
                        .nonEmpty(getResources().getString(R.string.non_empty))
                        .onFocusChanged()
                        .toObservable()
                        .map(result -> {
                            textInputCiudad.setErrorEnabled(result.isProper() ? false : true);
                            textInputCiudad.setError(result.getMessage());
                            return result.isProper();
                        });
        Observable<Boolean> estadoValidator =
                RxValidator.createFor(editEstado)
                        .nonEmpty(getResources().getString(R.string.non_empty))
                        .onFocusChanged()
                        .toObservable()
                        .map(result -> {
                            textInputEstado.setErrorEnabled(result.isProper() ? false : true);
                            textInputEstado.setError(result.getMessage());
                            return result.isProper();
                        });

        Observable<Boolean> cpValidator =
                RxValidator.createFor(editCP)
                        .nonEmpty(getResources().getString(R.string.non_empty))
                        .onFocusChanged()
                        .toObservable()
                        .map(result -> {
                            textInputCP.setErrorEnabled(result.isProper() ? false : true);
                            textInputCP.setError(result.getMessage());
                            return result.isProper();
                        });

        Observable<Boolean> exteriorValidator =
                RxValidator.createFor(editCalle)
                        .nonEmpty(getResources().getString(R.string.non_empty))
                        .onFocusChanged()
                        .toObservable()
                        .map(result -> {
                            textInputExterior.setErrorEnabled(result.isProper() ? false : true);
                            textInputExterior.setError(result.getMessage());
                            return result.isProper();
                        });


//        Observable<Boolean> uno = Observable.combineLatest(firstnameValidator,
//                lastnameValidator,
//                emailValidator,
//                contrasenaValidator,
//                contrasenaConfirmValidator,
//                telefonoValidator, (firstName, lastName, email, contrasena, contrasenaConfirm, telefono)
//                        -> firstName && lastName && email && contrasena && contrasenaConfirm && telefono)
//                .map(bool-> {
//                    Log.i(TAG,"Observable 1 "+bool);
//                    return bool;
//                });
//
//
//        Observable<Boolean> dos = Observable.combineLatest(
//                calleValidator,
//                ciudadValidator,
//                coloniaValidator,
//                estadoValidator,
//                cpValidator,
//                exteriorValidator, (calle, ciudad, colonia, estado, cp, exterior)
//                        -> calle && ciudad && colonia && estado && cp && exterior)
//                .map(bool->{
//                    Log.i(TAG,"Observable 2 "+bool);
//                    return bool;
//                });
//
//        Observable<Boolean> ambos = Observable.combineLatest(uno, dos, (uno1, dos1) -> uno1 && dos1)
//                .map(todoValido -> {
//                    Log.i(TAG, "Es Valido todo " + todoValido);
//                    buttonContinuar.setEnabled(todoValido);
//                    return todoValido;
//                });
//
//        firstnameValidator.subscribe();
//        lastnameValidator.subscribe();
//        emailValidator.subscribe();
//        contrasenaValidator.subscribe();
//        contrasenaConfirmValidator.subscribe();
//        telefonoValidator.subscribe();
//        calleValidator.subscribe();
//        ciudadValidator.subscribe();
//        coloniaValidator.subscribe();
//        estadoValidator.subscribe();
//        cpValidator.subscribe();
//        exteriorValidator.subscribe();
//        uno.subscribe();
//        dos.subscribe();
//        ambos.subscribe();

    }
}
