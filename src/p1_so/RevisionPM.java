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
public class RevisionPM extends Thread {

    private int salario;
    private int duracionDia;
    private int duracionHora;
    private int duracionMin;
    private Director director1;
    private Drive drive;
    private JLabel label;
    private Empresa empresa;
    private Semaphore mutex;

    public RevisionPM(int dia, int hora, int min, Director dir, JLabel label, Empresa emp) {
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

    @Override
    public void run() {

        while (true) {
            try {

                if (drive.getDiasRestantes() > 0) {

                    int randomHour = horaRandom();
                    int upperWait = this.duracionHora * randomHour;
                    int lowerWait = this.duracionHora * (24 - randomHour - 1);

                    for (int i = 0; i < 3; i++) {
                        switch (i) {

                            case 0:
                                this.director1.setPausa(true);
                                this.label.setText("Trabajando");
                                sleep(upperWait);
                                break;

                            case 1:
                                this.director1.setPausa(false);
                                this.label.setText("Vigilando al PM");
                                sleep(this.duracionMin * 35);
                                break;

                            case 2:
                                this.director1.setPausa(true);
                                this.label.setText("Trabajando");
                                this.director1.setVioAnime(false);
                                sleep(this.duracionDia - ((this.duracionMin * 35) + upperWait));
                        }
                    }

                    this.mutex.acquire(1);
                    this.drive.sumarSalario(salario);
                    this.mutex.release();

                } else {
                    this.mutex.acquire(1);
                    this.enviarCapitulos();
                    this.mutex.release();
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(RevisionPM.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public int horaRandom() {
        Random random = new Random();
        return random.nextInt(35 - 1) + 1;
    }


    public void enviarCapitulos() {
        if (this.empresa.getNombreEmpresa().equalsIgnoreCase("Nickelodeon")) {
            this.empresa.sumIngreso((this.drive.getCapitulo() * 450) + (this.drive.getCapituloPlotTwist() * 500));

        } else if (this.empresa.getNombreEmpresa().equalsIgnoreCase("Cartoon Network")) {
            this.empresa.sumIngreso((this.drive.getCapitulo() * 300) + (this.drive.getCapituloPlotTwist() * 650));

        } else if (this.empresa.getNombreEmpresa().equalsIgnoreCase("Star Channel")) {
            this.empresa.sumIngreso((this.drive.getCapitulo() * 350) + (this.drive.getCapituloPlotTwist() * 800));

        }

        this.drive.sumarSalario(this.salario);
        this.empresa.sumSalario(this.drive.getSalario());
        this.empresa.setUtilidad(this.empresa.getIngresos() - this.empresa.getSalario());
        this.drive.setSalario(0);
        this.drive.setCapitulo(0);
        this.drive.setCapituloPlotTwist(0);
        this.drive.setDiasRestantes(this.drive.getDeadline());
        this.director1.setFaltasPM(0);
        this.director1.getFaltaLabel().setText(Integer.toString(this.director1.getFaltasPM()));

    }
}
