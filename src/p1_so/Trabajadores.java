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
    private double sueldo;
    private double sueldoAcumulado;
    private String empresa;
    private Drive drive;
    private int duracionDia;
    private double produccionDiaria;
    private double diasParaCompletar; //dias que se tarda un trabajador en completar su tarea //acumulatedOutput
    private Semaphore mutex;
    private boolean activo = true;

    public Trabajadores(int tipo, String empresa, int duracionDia, Drive drive, Semaphore mutex) {

        this.tipo = tipo;
        this.sueldoAcumulado = 0;
        this.empresa = empresa; //nombre de la empresa
        this.diasParaCompletar = 0;
        TrabajadoresEspecificaciones();  
        this.drive = drive;
        this.duracionDia = duracionDia;

        this.mutex = mutex;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //Logicamente los desarrolladores siempre trabajan, teoricamente no
                this.ProduccionCapitulo();
                
                this.mutex.acquire();
                this.drive.sumarSalario(sueldo);
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
                this.sueldo = 20;
                this.diasParaCompletar = 0.26;
            }
            if (getTipo() == 1) { //diseñador
                this.sueldo = 26;
                this.diasParaCompletar = 0.26;
            }
            if (getTipo() == 2) { //animador
                this.sueldo = 40;
                this.diasParaCompletar = 1.1;
            }
            if (getTipo() == 3) { //doblaje
                this.sueldo = 16;
                this.diasParaCompletar = 5;
            }
            if (getTipo() == 4) { //guionista plotTwist
                this.sueldo = 34;
                this.diasParaCompletar = 0.51;
            }

        }
        if (empresa.equalsIgnoreCase("Cartoon network"))//seguir codigo
        {
        }
    }

    public double salarioTotal() {
        double sueldo = getSueldoAcumulado() + getSueldo() * 24;
        setSueldoAcumulado(sueldo);
        return sueldo;
    }

    public void ProduccionCapitulo() {
        this.diasParaCompletar += this.produccionDiaria;
        if (this.diasParaCompletar >= 1) {
            try {
                int diasAc = (int) Math.floor(this.diasParaCompletar);
                this.mutex.acquire(1);
                this.drive.aggDrive(diasAc, tipo);
                this.mutex.release();
                this.diasParaCompletar = 0;

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
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
