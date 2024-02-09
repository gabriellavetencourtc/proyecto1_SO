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
public class ProjectManager extends Thread {
    
    private int salario;
    private int duracionDia;
    private int duracionHora;
    private int duracionMin;
    private Drive drive;
    private Semaphore mutex;
    private String estadoActual;
    private JLabel label;


    public ProjectManager(int dia, int hora, int min ,Drive drive, Semaphore m, JLabel label) {
        this.salario = 40;
        this.duracionDia = dia;
        this.duracionHora = hora;
        this.duracionMin = min;
        this.drive = drive;
        this.mutex = m;
        this.estadoActual = "Trabajando";
        this.label = label;
    }
    
       @Override
    public void run(){
        while(true){
            try{
                //Al iniciar el dia, inicia su rutina de trabajar y ver anime
                animeTime();                
          
                if (((this.duracionHora/2)*32) + (this.duracionHora*8) < this.duracionDia){
                    sleep((this.duracionHora*8) + (this.duracionDia - (((this.duracionHora/2)*32) + (this.duracionHora*8))));
                    this.mutex.acquire(1);
                    contadorDia();
                    this.mutex.release();
                }else{
                    sleep(this.duracionHora*8);
                    this.mutex.acquire(1);
                    contadorDia();
                    this.mutex.release();
                }
                
                
                
                
            }catch (InterruptedException ex){
                System.out.println(ex);
                        }
        }
    }
    
    

    public void animeTime() {
        for (int i = 1; i <= 16; i++){
            try {
                this.estadoActual = watchAnime();
                this.label.setText(this.estadoActual);
                sleep(this.duracionHora/2);  
                
                 
                this.estadoActual = "Trabajando";
                this.label.setText(this.estadoActual);
                sleep(this.duracionHora/2);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
                }
}

    
    public String watchAnime(){
        String anime = "";
        Random random = new Random();
        int randNumb = random.nextInt(6 - 1) + 1;
        switch (randNumb){
            
            case 1:
                anime = "NARUTO";
                break;
                
            case 2:
                anime = "ONE PIECE";
                break;
                
            case 3:
                anime = "JUJUTSU KAISEN";
                break;
                
            case 4:
                anime = "DEMON SLAYER";
                break;
                
            case 5:
                anime = "ATTACK ON TITAN";
                break;    
                
        }
        
        return anime;
        
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
    
    public void contadorDia(){
        this.drive.setDiasRestantes(this.drive.getDiasRestantes()- 1);
        this.drive.sumarSalario(salario);
        diasTranscurridos();
    }
    
    public void diasTranscurridos(){
        this.drive.sumarDiasTranscurridos();
    }
    
}

