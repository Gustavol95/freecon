package com.iesoluciones.freecon.intefaces;

import com.iesoluciones.freecon.models.RegistroBody;

/**
 * Created by iedeveloper on 07/08/17.
 */

public interface RegistroCallback {


    void pasoUno(RegistroCallback registroCallback);
    void pasoDos(RegistroCallback registroCallback);
    void finalizar();
    RegistroBody getRegistro();
    boolean isFacebookUser();


}
