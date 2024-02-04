import java.io.*;
import java.util.Scanner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class main {
    public static void cls() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static Scanner t;

    public static void main(String args[]) throws IOException, InterruptedException {
        cls();
        t = new Scanner(System.in);
        File clap = new File("songs/c.wav");
        playSound(clap);
        File hollow = new File("songs/hollow.wav");
        File Carnal = new File("songs/carnal.wav");
        String rojo = "\033[31m";
        String verde = "\033[32m";
        // String amarillo="\033[33m";
        String azul = "\033[34m";
        String morado = "\033[35m";
        String cyan = "\033[36m";
        String reset = "\u001B[0m";

        int opc = 0;
        do {
            System.out.println(cyan + "═════════════════════════════════════════════════════════════════" + reset);
            System.out.println(morado + "═════════════════════════════════════════════════════════════════" + reset);
            System.out.println(verde + " |      ▄▄▄▄▄▄▄▄      ▄▄▄     ▄▄     ▄▄     ▄▄▄      ▄▄▄▄▄▄    |" + reset);
            System.out.println(cyan + " |      ▄▄     ▄▄    ▄▄ ▄▄    ▄▄▄   ▄▄▄    ▄▄ ▄▄    ▄▄    ▄▄   | " + reset);
            System.out.println(azul + " |      ▄▄     ▄▄   ▄▄   ▄▄   ▄▄▄▄ ▄▄▄▄   ▄▄   ▄▄   ▄▄         |" + reset);
            System.out.println(verde + " |      ▄▄     ▄▄  ▄▄     ▄▄  ▄▄ ▄▄▄ ▄▄  ▄▄     ▄▄   ▄▄▄▄▄▄    |" + reset);
            System.out.println(cyan + " |      ▄▄     ▄▄  ▄▄▄▄▄▄▄▄▄  ▄▄     ▄▄  ▄▄▄▄▄▄▄▄▄        ▄▄   |" + reset);
            System.out.println(azul + " |      ▄▄     ▄▄  ▄▄     ▄▄  ▄▄     ▄▄  ▄▄     ▄▄  ▄▄    ▄▄   |" + reset);
            System.out.println(verde + " |      ▄▄▄▄▄▄▄▄   ▄▄     ▄▄  ▄▄     ▄▄  ▄▄     ▄▄   ▄▄▄▄▄▄    |" + reset);
            System.out.println(cyan + "═════════════════════════════════════════════════════════════════" + reset);
            System.out.println(morado + "═════════════════════════════════════════════════════════════════" + reset);
            System.out.println(azul + " |      [1] Iniciar Juego                                     |" + reset);
            System.out.println(cyan + " |      [2] Ver Tabla de Puntuaciones                         |" + reset);
            System.out.println(azul + " |      [3] Creditos                                          |" + reset);
            System.out.println(cyan + " |      [4] Instrucciones                                     |" + reset);
            System.out.println(cyan + "═════════════════════════════════════════════════════════════════" + reset);
            System.out.println(morado + "═════════════════════════════════════════════════════════════════" + reset);
            System.out.println(verde + " |     ¿Que vamos a hacer hoy?                                |" + reset);

            opc = t.nextInt();
            switch (opc) {
                case 1:
                    cls();
                    pauseSound(clap);
                    playSound(hollow);
                    System.out.println(verde + "      Iniciando Juego..." + reset);
                    damas game = new damas();
                    game.crearTablero();
                    while (!game.perdio()) {

                        game.indicador();
                        game.crearTablero();

                    }

                    System.out.println("La victoria es de:  " + game.ganador());

                    Thread.sleep(20000);
                    break;
                case 2:
                    cls();
                    System.out.println(rojo + "   --Mostrando Tabla de logros---" + reset);
                    Thread.sleep(6000);
                    cls();
                    break;
                case 3:
                    cls();
                    playSound(Carnal);
                    System.out.println(
                            verde + "  Hecho por: Rodriguez Sanchez Julian & Alvarez Vargas Alejandro" + reset);
                    System.out.println(azul + "  Proyecto Segundo Semestre" + reset);
                    System.out.println(cyan + "  Instituto Tecnologico Superior de Uruapan" + reset);
                    System.out.println();
                    System.out.println(rojo + "   Espere a que termine la cancion para continuar..." + reset);
                    Thread.sleep(25000);
                    cls();
                    break;
                case 4:
                    cls();
                    System.out.println(verde + "Instrucciones " + reset);
                    System.out.println(morado + "Coloca la coordenada de forma seguida, es decir: " + reset);
                    System.out.println(azul + "Si quisieras mover la ficha x= 2 & y = 2" + reset);
                    System.out.println(cyan + "Ingresa 22" + reset);
                    Thread.sleep(15000);
                    cls();
                    break;
                default:
                    System.out.println("no es una opcion.");
            }
        } while (opc != 5);
        System.out.println(rojo + "Fin del juego" + reset);
    }

    static void playSound(File Sound) {
        try {

            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();

        } catch (Exception e) {

        }

    }

    static void pauseSound(File Sound) {
        try {

            Clip partida = AudioSystem.getClip();
            partida.open(AudioSystem.getAudioInputStream(Sound));
            partida.stop();

        } catch (Exception e) {

        }

    }

}
