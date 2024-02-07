/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1_so;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author daniela
 */
public class RevisionPM extends Thread{
    
private int salario;
    private int duracionDia;
    private int duracionHora;
    private int duracionMin;
    private Director director1;
    private Drive drive;
    private JLabel label;
    private Empresa empresa;
    private Semaphore mutex;
    
    public RevisionPM(int dia, int hora, int min, Director dir, JLabel label, Empresa emp){
    this.salario = 30;
    this.duracionDia = dia;
    this.director1 = dir;
    this.duracionHora = hora;
    this.duracionMin = min;
    this.label = label;
    this.empresa = emp;
    this.drive = this.empresa.getDrive();
    this.mutex = this.empresa.getMutex();
    }
    
    public void ejecutar(){
        /**
         * Ejecucion del director, si no hay 0 dias restantes, revisa al pm en una hora aleatoria cambiando el booleano de
         * activacion para el thread que ejecuta las reviciones
         * al final de los 25 minutos de la revision, cambia el booleano para desactivar al thread de revision
         * 
         * Si hay 0 dias restantes, procede a vender los juegos
         */
        System.out.println(this.duracionMin);
        while(true){
            try {
                
                if(drive.getDiasRestantes()> 0){
                
                    int randomHour = horaRandom();
                    int upperWait = this.duracionHora * randomHour;
                    int lowerWait = this.duracionHora * (24 - randomHour - 1);

                    for(int i = 0; i < 3; i++){
                        switch (i){

                            case 0:
                                this.director1.setPausa(true);
                                this.label.setText("Labores administrativas");
                                sleep(upperWait);
                                break;

                            case 1:
                                this.director1.setPausa(false);
                                this.label.setText("Revisando al PM");
                                sleep(this.duracionMin * 25);
                                break;

                            case 2:
                                this.director1.setPausa(true);
                                this.label.setText("Labores administrativas");
                                this.director1.setVioAnime(false);
                                sleep(this.duracionDia - ((this.duracionMin*25) + upperWait));
                        }
                    }
                    
                    this.mutex.acquire(1);
                    this.drive.sumarSalario(salario);
                    this.mutex.release();
            
                }else{
                    this.mutex.acquire(1);
                    this.enviarCapitulos();
                    this.mutex.release();
                }
            
            } catch (InterruptedException ex) {
                Logger.getLogger(RevisionPM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    
    }
    
    /**
     * Funcion de hora aleatoria, esta si es la que se usa
     * @return 
     */
    public int horaRandom(){
        Random random = new Random();
        return random.nextInt(35 - 1) + 1;
    }
    
    /**
     * Funcion de venta de juegos
     * 
     * Se revisa de que compañia se trata para determinar la ganancia por juego
     * 
     * Luego se calcula la ganancia segun los juegos, se añade el salario del director para este dia
     * se carga el salario acumulado en el drive para este deadline, se calculan las utilidades.
     * 
     * Despues se reinicia el salario para este deadline, la cantidad de juegos sin dlc, los juegos con dlc
     * la cantidad de dias restantes y las faltas del pm
     */
    
    public void enviarCapitulos(){
        if(this.empresa.getNombreEmpresa().equals("Capcom")){
            this.empresa.sumarIngresos((this.drive.getCapitulo()* 400) + (this.drive.getCapituloPlotTwist()* 750));       
        }else{
            this.empresa.sumarIngresos((this.drive.getCapitulo()* 350) + (this.drive.getCapituloPlotTwist()* 700));
        }
        
        this.drive.sumarSalario(this.salario);
        this.empresa.sumarSalario(this.drive.getSalario());
        this.empresa.setUtilidad(this.empresa.getIngresos() - this.empresa.getSalario());
        this.drive.setSalario(0);
        this.drive.setCapitulo(0);
        this.drive.setCapituloPlotTwist(0);
        this.drive.setDiasRestantes(this.drive.getDeadline());
        System.out.println(this.drive.getDeadline());
        System.out.println(this.drive.getDiasRestantes());
        this.director1.setFaltasPM(0);
        this.director1.getFaltaLabel().setText(Integer.toString(this.director1.getFaltasPM()));
        
    }
    
}

