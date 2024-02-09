/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p1_so;

import javax.swing.JLabel;

/**
 *
 * @author gabriellavetencourt
 */
public class Drive {

    private int guion;
    private int escenario;
    private int animacion;
    private int doblaje;
    private int plotwist;
    private int diasRestantes;
    private int deadline;
    private int capitulo;
    private int capituloPlotTwist;
    private int contador;
    private JLabel[] labels;
    private String empresa;
    private double salario;
    private int diasTranscurridos;

    public Drive(String empresa) {
        this.guion = 0;
        this.escenario = 0;
        this.animacion = 0;
        this.doblaje = 0;
        this.plotwist = 0;
        this.diasRestantes = 10; //pilas no sabemos pq hay un 5 ahi
        this.deadline = 0;
        this.capitulo = 0;
        this.capituloPlotTwist = 0;
        this.contador = 0;
        this.empresa = empresa;
        this.salario = 0;
        this.diasTranscurridos = 0;
    }

    public void aggDrive(int cantidad, int tipo) {

        if (tipo == 0) { // guionista
            if (this.guion < 25) {
                if ((this.guion + cantidad) > 25) {
                    this.guion += (25 - this.guion);

                } else {
                    this.guion += cantidad;
                }

                this.labels[0].setText(Integer.toString(this.guion));

            }
        }
        if (tipo == 1) { //diseñador
            if (this.escenario < 20) {
                if ((this.escenario + cantidad) > 20) {
                    this.escenario += (20 - this.escenario);

                } else {
                    this.escenario += cantidad;
                }

                this.labels[1].setText(Integer.toString(this.escenario));

            }
            if (tipo == 2) { //animador
                if (this.animacion < 55) {
                    if ((this.animacion + cantidad) > 55) {
                        this.animacion += (55 - this.animacion);

                    } else {
                        this.animacion += cantidad;
                    }

                    this.labels[2].setText(Integer.toString(this.animacion));

                }

            }
            if (tipo == 3) { //doblaje
                if (this.doblaje < 35) {
                    if ((this.doblaje + cantidad) > 35) {
                        this.doblaje += (35 - this.doblaje);

                    } else {
                        this.doblaje += cantidad;
                    }

                    this.labels[3].setText(Integer.toString(this.doblaje));

                }

            }
            if (tipo == 4) { //guionista plotTwist
                if (this.plotwist < 10) {
                    if ((this.plotwist + cantidad) > 10) {
                        this.plotwist += (10 - this.plotwist);

                    } else {
                        this.plotwist += cantidad;
                    }

                    this.labels[4].setText(Integer.toString(this.plotwist));

                }
            }
        }
    }

    public boolean ReqCapitulo() {
        if (this.empresa.equalsIgnoreCase("Nickelodeon")) {
            if (this.contador >= 5) {
                return this.guion >= 2 && this.escenario >= 1 && this.animacion >= 4 && this.doblaje >= 4 && this.plotwist >= 2;

            } else {
                return this.guion >= 2 && this.escenario >= 1 && this.animacion >= 4 && this.doblaje >= 4;
            }
        }

        if (this.empresa.equalsIgnoreCase("Cartoon Network")) {
            if (this.contador >= 3) {
                return this.guion >= 1 && this.escenario >= 2 && this.animacion >= 6 && this.doblaje >= 5 && this.plotwist >= 1;

            } else {
                return this.guion >= 1 && this.escenario >= 2 && this.animacion >= 6 && this.doblaje >= 5;
            }

        }

        if (this.empresa.equalsIgnoreCase("Star Channel")) {
            if (this.contador >= 6) {
                return this.guion >= 2 && this.escenario >= 3 && this.animacion >= 4 && this.doblaje >= 6 && this.plotwist >= 5;

            } else {
                return this.guion >= 2 && this.escenario >= 3 && this.animacion >= 4 && this.doblaje >= 6;
            }

        }
        return false; //pilas
    }

    public void crearCapitulo() {
        if (this.empresa.equalsIgnoreCase("Nickelodeon")) {
            this.guion -= 2;
            this.escenario -= 1;
            this.animacion -= 4;
            this.doblaje -= 4;

            if (this.contador >= 5) {
                this.plotwist -= 2;
                this.contador = 0;
                this.capituloPlotTwist += 1;

            } else {
                this.contador += 1;
                this.capitulo += 1;
            }

        }

        if (this.empresa.equalsIgnoreCase("Cartoon Network")) {
            this.guion -= 1;
            this.escenario -= 2;
            this.animacion -= 6;
            this.doblaje -= 5;

            if (this.contador >= 3) {
                this.plotwist -= 1;
                this.contador = 0;
                this.capituloPlotTwist += 1;

            } else {
                this.contador += 1;
                this.capitulo += 1;
            }

        }

        if (this.empresa.equalsIgnoreCase("Star Channel")) {
            this.guion -= 2;
            this.escenario -= 3;
            this.animacion -= 4;
            this.doblaje -= 6;

            if (this.contador >= 6) {
                this.plotwist -= 5;
                this.contador = 0;
                this.capituloPlotTwist += 1;

            } else {
                this.contador += 1;
                this.capitulo += 1;
            }

        }
        this.labels[0].setText(Integer.toString(this.guion));
        this.labels[1].setText(Integer.toString(this.escenario));
        this.labels[2].setText(Integer.toString(this.animacion));
        this.labels[3].setText(Integer.toString(this.doblaje));
        this.labels[4].setText(Integer.toString(this.plotwist));
        this.labels[6].setText(Integer.toString(this.capitulo));
        this.labels[7].setText(Integer.toString(this.capituloPlotTwist));

    }

    public int getGuion() {
        return guion;
    }

    public void setGuion(int guion) {
        this.guion = guion;
    }

    public int getEscenario() {
        return escenario;
    }

    public void setEscenario(int escenario) {
        this.escenario = escenario;
    }

    public int getAnimacion() {
        return animacion;
    }

    public void setAnimacion(int animacion) {
        this.animacion = animacion;
    }

    public int getDoblaje() {
        return doblaje;
    }

    public void setDoblaje(int doblaje) {
        this.doblaje = doblaje;
    }

    public int getPlotwist() {
        return plotwist;
    }

    public void setPlotwist(int plotwist) {
        this.plotwist = plotwist;
    }

    public int getDiasRestantes() {
        return diasRestantes;
    }

    public void setDiasRestantes(int diasRestantes) {
        this.diasRestantes = diasRestantes;
        this.labels[5].setText(Integer.toString(this.diasRestantes));
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(int capitulo) {
        this.capitulo = capitulo;
    }

    public int getCapituloPlotTwist() {
        return capituloPlotTwist;
    }

    public void setCapituloPlotTwist(int capituloPlotTwist) {
        this.capituloPlotTwist = capituloPlotTwist;
    }

    public JLabel[] getLabels() {
        return labels;
    }

    public void setLabels(JLabel[] labels) {
        this.labels = labels;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getDiasTranscurridos() {
        return diasTranscurridos;
    }

    public void setDiasTranscurridos(int diasTranscurridos) {
        this.diasTranscurridos = diasTranscurridos;
    }

    //no estoy segura porque lo multiplica por 1000
    public void sumarSalario(double salary){
        this.salario += ((salary/1000)*24);
    }


    public void sumarDiasTranscurridos() {
        this.diasTranscurridos += 1;
        this.labels[8].setText(Integer.toString(this.diasTranscurridos));
    }
        
        

}
