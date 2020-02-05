/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author pedrobedmar
 */
public class SorpresaSalirCarcel extends Sorpresa{
    private MazoSorpresas mazo;
    SorpresaSalirCarcel(MazoSorpresas mazo, String texto){
        super(texto);
        this.mazo=mazo;
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){
        if(actual>=0&&actual<todos.size()&&todos.get(actual)!=null){
            informe(actual,todos);
            boolean tieneSorpresaEvitarCarcel=false;
            for (Jugador todo : todos) {
                if(todo.tieneSalvoconducto()){
                    tieneSorpresaEvitarCarcel=true;
                }
            }
            if(!tieneSorpresaEvitarCarcel){
                todos.get(actual).obtenerSalvoconducto(this);                           //Esto esta bien?
                salirDelMazo();
            }
        }
    }
    
    void salirDelMazo(){
        mazo.inhabilitarCartaEspecial(this);
    }
    
    void usada(){
        mazo.habilitarCartaEspecial(this);
    }
}
