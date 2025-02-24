package com.example;

public class Acount {
    
    private double saldo=0;
    public void abono(double cantidad){
        validate(cantidad);
        saldo+=cantidad;
    }
    public void cargo(double cantidad){
        validate(cantidad);
        if(saldo-cantidad<0){
            throw new IllegalArgumentException("No tienes suficiente saldo");
        }
        saldo-=cantidad;
    }
    public double getSaldo(){
        return saldo;
    }   
    private void validate(double cantidad){
        if(cantidad<=0){
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
    }
}
