/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1_so;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabriellavetencourt
 */
public class Trabajadores extends Thread {

    private double tipo;
    private double sueldo;
    private double sueldoAcumulado;
    private String empresa;
    private Drive drive;
    private int duracionDia;
    private double produccionDiaria;
    private double diasParaCompletar; //dias que se tarda un trabajador en completar su tarea //acumulatedOutput
    private Semaphore mutex;
    private boolean activo = true;

    public Trabajadores( double tipo, String empresa, int duracionDia, Drive drive, Semaphore mutex) {
 
        this.tipo = tipo;
        this.sueldo = sueldo();
        this.sueldoAcumulado = 0;
        this.empresa = empresa; //nombre de la empresa
        this.drive = drive;
        this.duracionDia = duracionDia;
        this.produccionDiaria = produccionDiaria();
        this.diasParaCompletar = 0;
        this.mutex = mutex;
    }
    
    @Override
    public void run(){
         while(true) {           
            try {
                sueldo();
                produccionDiaria();
               
                sleep(this.duracionDia);
            } catch (InterruptedException ex) {
                Logger.getLogger(Trabajadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public double sueldo(){
        if (getTipo() == 0) { // guionista
            return 20;
        }
        if(getTipo() == 1){ //diseñador
            return 26;
        } 
        if (getTipo() == 2){ //animador
            return 40;
        }
        if(getTipo() ==3){ //doblaje
            return 16;
        }
        if(getTipo() ==4){ //guionista plotTwist
            return 34;    
        }
        else{
            return -1;
        }
    }
    
    public double produccionDiaria(){
        if (getTipo() == 0) { // guionista
            return 0.26;
        }
        if(getTipo() == 1){ //diseñador
            return 0.26;
        } 
        if (getTipo() == 2){ //animador
            return 1.1;
        }
        if(getTipo() ==3){ //doblaje
            return 5;
        }
        if(getTipo() ==4){ //guionista plotTwist
            return 0.51;    
        }
        else{
            return -1;
        }
    }
    
    public double salarioTotal(){
        double sueldo = getSueldoAcumulado() + getSueldo()*24;
        setSueldoAcumulado(sueldo);
        return sueldo;
    }
    
    public void ProduccionCapitulo(){
        this.diasParaCompletar += this.produccionDiaria;
        if(this.diasParaCompletar >= 1){
            try{
                int diasAc =  (int) Math.floor(this.diasParaCompletar);
                this.mutex.acquire(1);
                //this.drive.AGGDRIVE(diasAc, this.tipo);
                this.mutex.release();
                this.diasParaCompletar = 0;
                
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }


    public double getTipo() {
        return tipo;
    }

    public void setTipo(double tipo) {
        this.tipo = tipo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public double getSueldoAcumulado() {
        return sueldoAcumulado;
    }

    public void setSueldoAcumulado(double sueldoAcumulado) {
        this.sueldoAcumulado = sueldoAcumulado;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public int getDuracionDia() {
        return duracionDia;
    }

    public void setDuracionDia(int duracionDia) {
        this.duracionDia = duracionDia;
    }

    public double getProduccionDiaria() {
        return produccionDiaria;
    }

    public void setProduccionDiaria(double produccionDiaria) {
        this.produccionDiaria = produccionDiaria;
    }

    public double getDiasParaCompletar() {
        return diasParaCompletar;
    }

    public void setDiasParaCompletar(double diasParaCompletar) {
        this.diasParaCompletar = diasParaCompletar;
    }

    public Semaphore getMutex() {
        return mutex;
    }

    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    


    
}
