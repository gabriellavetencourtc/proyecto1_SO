/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1_so;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author daniela
 */
public class Director extends Thread {

    private Drive drive;
    private double salario = 60;
    private Semaphore mutex;
    private ProjectManager pm;
    private boolean pausa;
    private int duracionMin;
    private boolean vioAnime;
    private int faltasPM;
    private JLabel faltaLabel;

    public Director(Drive drive, Semaphore m, ProjectManager proj, int min, JLabel falta) {
        this.drive = drive;
        this.salario = 0;
        this.mutex = m;
        this.pm = proj;
        this.duracionMin = min;
        this.pausa = false;
        this.vioAnime = false;
        this.faltasPM = 0;
        this.faltaLabel = falta;
    }

    @Override
    public void run() {
        try {
          
            sleep(10);
            while (true) { 
                if (!this.pausa) {

                    if (!"Trabajando".equals(this.pm.getEstadoActual()) && !this.vioAnime) {
                        this.mutex.acquire(1);
                        faltaPM();
                        this.mutex.release();
                    }

                    sleep(5);

                } else {
                    try {
                        sleep(this.duracionMin);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public double salarioDiario() {
        return this.salario * 24;
    }

    public boolean Pausa() {
        return pausa;
    }

    public void setPausa(boolean pausa) {
        this.pausa = pausa;
    }

    public boolean isVioAnime() {
        return vioAnime;
    }

    public void setVioAnime(boolean vioAnime) {
        this.vioAnime = vioAnime;
    }

    public int getFaltasPM() {
        return faltasPM;
    }

    public void setFaltasPM(int faltasPM) {
        this.faltasPM = faltasPM;
    }


    public void faltaPM() {

        this.vioAnime = true;
        this.faltasPM += 1;
        this.faltaLabel.setText(Integer.toString(this.faltasPM * 100));
        this.drive.setSalario(this.drive.getSalario() - (100 / 1000));
    }

    public JLabel getFaltaLabel() {
        return faltaLabel;
    }

    public void setFaltaLabel(JLabel faltaLabel) {
        this.faltaLabel = faltaLabel;
    }

}
