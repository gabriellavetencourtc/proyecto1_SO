/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1_so;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;

/**
 *
 * @author daniela
 */
public class Ensamblador extends Thread {
    
    private Drive drive;
    private Semaphore mutex;
    private double salario = 25;
    private boolean activo = true;
    private int duracionDia;

    public Ensamblador (Drive drive, Semaphore m, int dia) {
        this.drive = drive;
        this.mutex = m;
        this.duracionDia = dia;
    }

    public void ejecutar() {
        while (activo) {
            try {
                // Verificar si hay suficientes componentes para ensamblar un juego
                this.mutex.acquire(1);
                if (this.drive.ReqCapitulo()) {

                    // Consumir componentes
                    drive.crearCapitulo();
                    this.drive.sumarSalario(this.salario);
                    this.mutex.release();
                    // Dormir durante el tiempo necesario para el ensamblaje
                    // (Aquí dormimos por un tiempo corto. Ajustar según sea necesario.)
                    sleep(this.duracionDia*2);

                    // Agregar el juego ensamblado al Drive
                    // (Implementar esto según tus requisitos.)

                } else {
                    this.drive.sumarSalario((this.salario));
                    this.mutex.release();
                    sleep(this.duracionDia);
                }
                
            } catch (InterruptedException e) {
                System.out.println("Ensamblador interrumpido.");
            }
        }
    }


    public boolean Activo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public Semaphore getMutex() {
        return mutex;
    }

    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getDuracionDia() {
        return duracionDia;
    }

    public void setDuracionDia(int duracionDia) {
        this.duracionDia = duracionDia;
    }

    
}


