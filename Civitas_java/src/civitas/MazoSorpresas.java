/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author pedrobedmar
 */
public class MazoSorpresas {
    ArrayList<Sorpresa> sorpresas;
    boolean barajada;
    int usadas;
    boolean debug;
    ArrayList<Sorpresa> cartasEspeciales;
    Sorpresa ultimaSorpresa=null;
    
    private void init(){
        sorpresas=new ArrayList<>();
        cartasEspeciales=new ArrayList<>();
        barajada=false;
        usadas=0;
    }
    
    Sorpresa getUltimaSorpresa(){
        return ultimaSorpresa;
    }
    
    MazoSorpresas(){
        init();
        debug=false;
    }
    
    MazoSorpresas(boolean d){
        debug=d;
        init();
        if(d)
            Diario.getInstance().ocurreEvento("El modo debug está activado");
    }
    
    void alMazo(Sorpresa s){
        if(!barajada){
            sorpresas.add(s);
        }
    }
    
    Sorpresa siguiente(){                                       
        if((!barajada||usadas==sorpresas.size())&&!debug){
            Collections.shuffle(sorpresas);
            usadas=0;
            barajada=true;
        }
        usadas++;
        ultimaSorpresa=sorpresas.get(0);
        sorpresas.remove(0);
        sorpresas.add(ultimaSorpresa);
        return ultimaSorpresa;                             
    }
    
    void inhabilitarCartaEspecial(Sorpresa sorpresa){
        
        if(sorpresas.remove(sorpresa)){
            cartasEspeciales.add(sorpresa);
            Diario.getInstance().ocurreEvento("Inhabilitada la carta especial");
        }
        System.out.println("Funciona 1");
    }
    
    void habilitarCartaEspecial(Sorpresa sorpresa){
        if(cartasEspeciales.remove(sorpresa)){
            sorpresas.add(sorpresa);
            Diario.getInstance().ocurreEvento("Habilitada la carta especial");
        }
        System.out.println("Funciona 2");
    }
}


