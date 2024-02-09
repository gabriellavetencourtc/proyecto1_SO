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

    private int tipo;
//    private double ingresos;
    private double sueldoHora;
    private String empresa;
    private Drive drive;
    private int duracionDia;
    private double produccionDiaria;
    private double diasParaCompletar; //dias que se tarda un trabajador en completar su tarea //acumulatedOutput
    private Semaphore mutex;
    private boolean activo = true;

    public Trabajadores(int tipo, String empresa, int duracionDia, Drive drive, Semaphore mutex) {

        this.tipo = tipo;
//        this.ingresos= 0;
        this.empresa = empresa; //nombre de la empresa
        this.diasParaCompletar = 0;
        TrabajadoresEspecificaciones();  
        this.duracionDia = duracionDia;
        this.drive = drive;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.ProduccionCapitulo();
                
                this.mutex.acquire();
                this.drive.sumarSalario(sueldoHora);
                this.mutex.release();
                
                sleep(this.duracionDia); // hay que despues cambiarlo para que se pueda cambiar la duracion del dia en la interfaz
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        
       
    }

    private void TrabajadoresEspecificaciones() {
        if (empresa.equalsIgnoreCase("nickelodeon")) {
            if (getTipo() == 0) { // guionista
                this.sueldoHora = 20;
                this.diasParaCompletar = 0.3;
            }
            if (getTipo() == 1) { //diseñador
                this.sueldoHora = 26;
                this.diasParaCompletar = 0.26;
            }
            if (getTipo() == 2) { //animador
                this.sueldoHora = 40;
                this.diasParaCompletar = 1.1;
            }
            if (getTipo() == 3) { //doblaje
                this.sueldoHora = 16;
                this.diasParaCompletar = 5;
            }
            if (getTipo() == 4) { //guionista plotTwist
                this.sueldoHora = 34;
                this.diasParaCompletar = 0.51;
            }

        } //CARTOON NETWORK
        if (empresa.equalsIgnoreCase("Cartoon network"))//seguir codigo
        {
        }
    }

 

    public void ProduccionCapitulo() {
        this.produccionDiaria += this.diasParaCompletar;
        if (this.produccionDiaria >= 1) {
            try {
                int diasAc = (int) Math.floor(this.produccionDiaria);
                this.mutex.acquire(1);
                this.drive.aggDrive(diasAc, tipo);
                this.mutex.release();
                this.produccionDiaria = 0;

            } catch (InterruptedException ex) {
                Logger.getLogger(Trabajadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
        TrabajadoresEspecificaciones();
    }

    public double getSueldo() {
        return sueldoHora += (this.diasParaCompletar * 24);
    }

    public void setSueldo(double sueldo) {
        this.sueldoHora = sueldo;
    }


    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
        this.TrabajadoresEspecificaciones();
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



