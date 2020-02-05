/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.Scanner;

/**
 *
 * @author alex
 */
public class JugadorEspeculador extends Jugador{
    
    private static int FactorEspeculador = 2;
    private int fianza;
    protected  int CasasMax=8;
    protected  int CasasPorHotel=4;
    protected  int HotelesMax=8;
    
    JugadorEspeculador(Jugador jugador_original,int fianza){
        super(jugador_original);
        this.fianza = fianza;
        actualizaPropietarioPorConversion(jugador_original);
    }
    
    private void actualizaPropietarioPorConversion(Jugador jugador){
        
        for(int i=0; i < jugador.getPropiedades().size(); i++){
            
            jugador.getPropiedades().get(i).actualizaPropietarioPorConversion(this);    
        }      
    }
    
       @Override
    public String toString(){
        return super.toString() + ". Este jugador es un jugador especulador";
    }
    
    @Override
     boolean pagaImpuesto(float cantidad){
       
        if(encarcelado)
          return false;
        else{
            return paga(cantidad/2);
        }
    }
      
    @Override
    protected boolean debeSerEncarcelado(){
    
        if(encarcelado)
            return false;
        else{
            if(tieneSalvoconducto() ){
                perderSalvoconducto();
                Diario.getInstance().ocurreEvento("El jugador "+getNombre()+" se ha librado de la carcel.");
                return false;
            }  
            else{
                if(saldo>fianza){
                    this.paga(fianza);
                    return false;
                }
            }
        }
        return true;    
    }
    
    @Override
    protected boolean puedoEdificarCasa(TituloPropiedad propiedad){

      if(propiedad.getPropietario() == this && this.getSaldo() >= propiedad.getPrecioEdificar() 
       && propiedad.getNumCasas() < CasasMax ){
          return true;

      }

      return false; 
    }
    @Override
    protected boolean puedoEdificarHotel(TituloPropiedad propiedad){


     if(propiedad.getPropietario() == this && this.getSaldo() >= propiedad.getPrecioEdificar() 
      && propiedad.getNumCasas() > CasasPorHotel  && propiedad.getNumHoteles() < HotelesMax){
         return true;
     }
     return false; 
    } 
       
       
}