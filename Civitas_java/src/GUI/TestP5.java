/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.util.ArrayList;
import civitas.*;

/**
 *
 * @author pedrobedmar
 */
public class TestP5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CivitasView vista=new CivitasView();
        Dado.createInstance(vista);
        Dado.getInstance().setDebug(false);
        CapturaNombres capturaNombres = new CapturaNombres(vista,true);
        ArrayList<String> nombres = capturaNombres.getNombres();
        CivitasJuego civitas = new CivitasJuego(nombres);
        Controlador controlador = new Controlador(civitas,vista);
        vista.setCivitasJuego(civitas);
        controlador.juega();
    }
}
