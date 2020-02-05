/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;
import java.util.ArrayList;


public class Tablero {
    int numCasillaCarcel;
    ArrayList<Casilla> casillas;
    int porSalida;
    boolean tieneJuez;
    
    Tablero(int n){
        if(n>1){
            numCasillaCarcel=n;
        } else {
            numCasillaCarcel=1;
        }
        casillas=new ArrayList<Casilla>();
        casillas.add(new Casilla("Salida"));
        
        porSalida=0;
        tieneJuez=false;
    }
    
    private boolean correcto(){
        return casillas.size()>numCasillaCarcel&&tieneJuez;
    }
    
    private boolean correcto(int numCasilla){
        boolean c=false;
        if(correcto()&&casillas.size()>numCasilla&&numCasilla>=0){
            c=true;
        }
        return c;
    }
    
    int getCarcel(){
        return numCasillaCarcel;
    }
    
    int getPorSalida(){
        if(porSalida>0){
            porSalida--;
            return porSalida+1;
        } else {
            return porSalida;
        }
    }
    
    void añadeCasilla(Casilla casilla){
        if(casillas.size()==numCasillaCarcel){
            casillas.add(new Casilla("Cárcel"));
        }
        casillas.add(casilla);
        if(casillas.size()==numCasillaCarcel){
            casillas.add(new Casilla("Cárcel"));
        }
    }
    
    void añadeJuez(){
        if(!tieneJuez){
            casillas.add(new CasillaJuez(numCasillaCarcel,"Juez") );
            tieneJuez=true;
        }
    }
    
    Casilla getCasilla(int numCasilla){
        if(correcto(numCasilla)){
            return casillas.get(numCasilla);
        }
        else {
            return null;
        }
    }
    
    int nuevaPosicion(int actual, int tirada){
        int nactual=-1;
        if(correcto()){
            nactual=(actual+tirada)%casillas.size();
            if(nactual!=actual+tirada)
                porSalida++;
        }
        return nactual;
    }
    
    int calcularTirada(int origen, int destino){
        int distancia=destino-origen;
        if(distancia<0){
            distancia=distancia+casillas.size();
        }
        return distancia;
    }
}
