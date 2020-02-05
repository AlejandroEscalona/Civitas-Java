/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package civitas;
import java.util.ArrayList;
import GUI.*;
/**
 *
 * @author alex
 */
public class Jugador implements Comparable<Jugador>{
    
    protected final int CasasMax=4;
    protected final int CasasPorHotel=4;
    protected final int HotelesMax=4;
    protected boolean encarcelado;
    public String nombre;
    private int numCasillaActual;
    protected static float PasoPorSalida = 1000;
    protected static float PrecioLibertad = 1000;
    private boolean puedeComprar;
    public float saldo;
    private static float saldoInicial=7500;
    private ArrayList<TituloPropiedad> propiedades;
    protected Sorpresa salvoconducto;
    
    
    
    @Override
    public String toString(){
        return "Jugador: "+nombre+ "  Casilla actual: "+numCasillaActual+ "  puede comprar?: "+puedeComprar+"  Tiene un saldo de: "+saldo+"  tiene salvoconducto?: "
                +salvoconducto;
    }
    
    
    Jugador (String n){
        
        nombre=n;
        saldo=saldoInicial;
        encarcelado=false;
        numCasillaActual=0;
        puedeComprar=true;
        propiedades= new ArrayList<TituloPropiedad>();
        salvoconducto=null;
        
    }
    
    protected Jugador(Jugador jugador_copia){
        
        this.nombre = jugador_copia.nombre;
        this.numCasillaActual = jugador_copia.numCasillaActual;
        this.encarcelado = jugador_copia.encarcelado;
        this.puedeComprar = jugador_copia.puedeComprar;
        this.saldo = jugador_copia.saldo;
        this.propiedades = jugador_copia.propiedades;
    }
     
    
    private boolean existeLaPropiedad(int ip){
            return (ip >= 0 && ip < propiedades.size() );
        
     }
    
    
    
    //Comprueba si el jugador debe ser encarcelado o no, segun tenga una carta sorpresa

    protected boolean debeSerEncarcelado(){
        
        boolean debeSer;
        if(encarcelado)
            debeSer=false;
        else{
            if(!tieneSalvoconducto() )
                debeSer=true;
            else{
                perderSalvoconducto();
                Diario.getInstance().ocurreEvento("El jugador "+nombre+" se ha librado de la carcel.");
                debeSer=false;
            }
        }
        
        return debeSer;
    }
    
    //Metodo para encarcelar a un jugador
    
    boolean encarcelar(int numCasillaCarcel){
        
        if(debeSerEncarcelado() ){
               moverACasilla(numCasillaCarcel);
               encarcelado=true;
               Diario.getInstance().ocurreEvento("El jugador "+nombre+" ha sido encarcelado.");
        }
        return encarcelado;
    }
    
    //Metodo para obtener el salvoconducto
    
    boolean obtenerSalvoconducto(Sorpresa s){
        
        boolean obtener;
        if(encarcelado)
            obtener=false;
        else{
            salvoconducto = s;
            obtener = true;
        }
        
        return obtener;
    }
    
    //Metodo para usar el salvoconducto
    
    protected void perderSalvoconducto(){
     
        ((SorpresaSalirCarcel)salvoconducto).usada();
        salvoconducto = null;
        
    }
    
    //Muestra si tiene salvoconducto
    
    boolean tieneSalvoconducto(){
        
        return salvoconducto!=null;
    }
    
    //Metodo que modifica si el jugador puede comprar o no (encarcelado o no)
    
    boolean puedeComprarCasilla(){
        
        puedeComprar=true;
        
        if(encarcelado)
            puedeComprar=false;
        
        return puedeComprar;
    }
   
    //Metodo que devuelve si se paga o no
    
    boolean paga(float cantidad){
        
        return modificarSaldo(cantidad * -1);
               
     
    }
    
    //Metodo que paga impuesto si no está encarcelado
    
    boolean pagaImpuesto(float cantidad){
       
        if(encarcelado)
          return false;
        else{
            return paga(cantidad);
        }
    }
    
    //Metodo que paga el alquiler si no está encarcelado
    
    boolean pagaAlquiler(float cantidad){
        
         if(encarcelado)
          return false;
        else{
            return paga(cantidad);
         }
    }
    
    //Metodo que devuelve true si se recibe dinero
    
    boolean recibe(float cantidad){
        
        if(encarcelado)
            return false;
        else {
            return modificarSaldo(cantidad);
        }
        
    }
    
    //Metodo que devuelve true si el saldo es modificado
    
    boolean modificarSaldo(float cantidad){
        
        saldo+=cantidad;
        Diario.getInstance().ocurreEvento("El saldo ha sido modificado");
        return true;
    }
    
    //Metodo que devuelve true si se mueve la casilla. 
    
    boolean moverACasilla(int numCasilla){
        
        if(encarcelado)
            return false;
        else {
            numCasillaActual = numCasilla;
            puedeComprar = false;
            Diario.getInstance().ocurreEvento("La casilla actual es "+numCasillaActual);
            return true;
        }
        
    }
    
    //Metodo que devuelve true si puede comprar con ese saldo
    
    boolean puedoGastar(float precio){
        
        if(encarcelado)
            return false;
        else{
            return precio <= saldo;
        }
    }
    
    //Metodo para vender una propiedad
    
    boolean vender(int ip){
        
        if(encarcelado)
            return false;
        else{
            if (existeLaPropiedad(ip) && propiedades.get(ip).vender(this) ){
                propiedades.remove(ip);
               // recibe(propiedades[ip].getPrecioVenta() );
                Diario.getInstance().ocurreEvento("El jugador "+nombre+" ha vendido una propiedad");
                return true;
            }
            return false;
        }
    }
    
    //Metodo que devuelve true si tiene propiedades.
    
    boolean tieneAlgoQueGestionar(){
        
        
        return (propiedades.size() >= 1);
        
    }
    
    // Metodo si puede salir de la carcel pagando
    
    boolean salirCarcelPagando(){
        
        boolean result=false;
        
        if(encarcelado && puedeSalirCarcelPagando() ){
        paga(PrecioLibertad);
        encarcelado = false;
        Diario.getInstance().ocurreEvento("El jugador "+nombre+" paga para salir de la carcel");
        result = true;
        } 
           
        return result;
        
    }
    
    private boolean puedeSalirCarcelPagando(){
        
        return saldo >= PrecioLibertad;
    }
            
            
    
    // Metodo que muestra si puede salir de la carcel con el dado
    
    boolean salirCarcelTirando(){
        
        boolean salidas = false;
        if( Dado.getInstance().salgoDeLaCarcel() ){
            
            encarcelado=false;
            Diario.getInstance().ocurreEvento("El jugador "+nombre+" ha salido de la carcel con el dado");
            salidas = true;            
        }
            return salidas;
        
    }
    
    //modifica el saldo al pasar por salida
    
    boolean pasaPorSalida(){
        
        modificarSaldo(PasoPorSalida);
        Diario.getInstance().ocurreEvento("Se ha aumentado el saldo al pasar por la salida");
        return true;
    }
    
    //Compara el saldo del jugador con el de otro
    @Override
    public int compareTo(Jugador otro){
        
        return Float.compare(saldo,otro.saldo);
    }
    
    boolean cancelarHipoteca(int ip){
        boolean result = false;
        
        if(encarcelado)
            return result;
        
        if(existeLaPropiedad(ip)){
            TituloPropiedad propiedad = propiedades.get(ip);
            float cantidad = propiedad.getImporteCancelarHipoteca();
            boolean puedoGastar = puedoGastar(cantidad);
            if(puedoGastar){
                result = propiedad.cancelarHipoteca(this);
                if(result)
                    Diario.getInstance().ocurreEvento("El jugador "+nombre+" cancela la hipoteca de la propiedad "+ip);
            }
            
            
        }
        
        return result;
    }
    
    boolean hipotecar(int ip){
        
        boolean result = false;
        
        if(encarcelado)
             return result;
        
        if(existeLaPropiedad(ip)){
            
            TituloPropiedad propiedad = propiedades.get(ip);
            result = propiedad.hipotecar(this);
        }
        if(result)
            Diario.getInstance().ocurreEvento("El jugador "+nombre+" hipoteca la propiedad "+ip);
        
        return result;
    }
    
    int cantidadCasasHoteles(){
        
        int contador=0;
        
       for(int i=0; i < propiedades.size(); i++){
           
           contador+=propiedades.get(i).getNumCasas();
           contador+=propiedades.get(i).getNumHoteles();
       }
        
       return contador;
    }
    
    //Muestra si esta en bancarrota
    boolean enBancarrota(){
        
        return saldo == 0;
    }
    
    //Devuelve la constante CasasMax
    private int getCasasMax(){
        
        return CasasMax;
    }
    
    boolean construirCasa(int ip){
        
        boolean result = false;
        boolean puedoEdificarCasa = false;
       
        if(encarcelado)  //1
            return result;
        
        if(!encarcelado){  //2
         boolean  existe = existeLaPropiedad(ip);
         
            if (existe){ 
            TituloPropiedad propiedad = propiedades.get(ip); //3
            puedoEdificarCasa = puedoEdificarCasa(propiedad); //4
       
            float precio = propiedad.getPrecioEdificar(); //4.1
        
            if (puedoGastar(precio) && propiedad.getNumCasas() < getCasasMax() )
                puedoEdificarCasa = true;  //4.2
       
             if(puedoEdificarCasa)
                result = propiedad.construirCasa(this); //5
        
                }
            }
       
        if(result)
            Diario.getInstance().ocurreEvento("El jugador "+nombre+" construye casa en la propiedad "+ip);
        
        return result;
    }
    
    boolean construirHotel(int ip){
        
        boolean result = false;
        if(encarcelado)
            return result;
        
        if(existeLaPropiedad(ip)){
            TituloPropiedad propiedad = propiedades.get(ip);
            boolean puedoEdificarHotel = puedoEdificarHotel(propiedad);
            float precio = propiedad.getPrecioEdificar();
            
            if(puedoGastar(precio) && propiedad.getNumHoteles() < HotelesMax && propiedad.getNumCasas() >= CasasPorHotel){
                puedoEdificarHotel = true;
            }
            
             if(puedoEdificarHotel){
            
                 result = propiedad.construirHotel(this);
                 int casasPorHotel = getCasasPorHotel();
                 propiedad.derruirCasas(casasPorHotel, this);
                 Diario.getInstance().ocurreEvento("El jugador "+nombre+" construye un hotel en la propiedad "+ip);
            }
             
        }
        return result;
    }
    
    //Devuelve casasporHotel
    int getCasasPorHotel(){
        
        return CasasPorHotel;
    }
    
    private int getHotelesMax(){
        
        return HotelesMax;
    }
    
    //Devuelve el nombre
    public String getNombre(){
        
        return nombre;
    }
    
    int getNumCasillaActual(){
        
        return numCasillaActual;
    }
    
    private float getPrecioLibertad(){
        
        return PrecioLibertad;
    }
    
    private float getPremioPasoSalida(){
        
        return PasoPorSalida;
    }
    
    public ArrayList<TituloPropiedad> getPropiedades(){
        
        return propiedades;
    }
    
    boolean getPuedeComprar(){
        
        return puedeComprar;
    }
    
    public float getSaldo(){
        
        return saldo;
    }
    
    public boolean isEncarcelado(){
        
        return encarcelado;
    }
    
    protected boolean puedoEdificarCasa(TituloPropiedad propiedad){
        
        if(propiedad.getPropietario() == this && saldo >= propiedad.getPrecioEdificar() 
         && propiedad.getNumCasas() < 4 ){
            return true;
            
        }
            
        return false; 
    }
    
    protected boolean puedoEdificarHotel(TituloPropiedad propiedad){
        
      
        if(propiedad.getPropietario() == this && saldo >= propiedad.getPrecioEdificar() 
         && propiedad.getNumCasas() > 4 ){
            return true;
        }
        return false; 
    }
    
    
    boolean comprar(TituloPropiedad titulo){
     
        boolean result = false;
        
        if(encarcelado)
            return result;
        
        if(puedeComprar){
            
            float precio = titulo.getPrecioCompra();
            
            if(puedoGastar(precio) ){
                
                result = titulo.comprar(this);
                
                if(result){
                    propiedades.add(titulo);
                    Diario.getInstance().ocurreEvento("El jugador "+nombre+" compra la propiedad "+titulo.toString());
                }
                
               puedeComprar = false;
                
            }
        }
        return result;
        
    }

   
    
    
    
} // end class
