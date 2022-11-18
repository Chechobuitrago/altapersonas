package com.hexacta.altapersonas.exception;

public class PersonaSaveErrorException extends RuntimeException{
    PersonaSaveErrorException(){
        super("Error en la validacion de la persona ingresada");
    }
}
