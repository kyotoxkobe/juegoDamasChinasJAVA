import java.io.*;
import java.util.*;

public class damas {

	private final static int tab = 8;
	private char[][] tablero;
	private int rojas;
	private int negras;
	private char mov;
	public Scanner t = new Scanner(System.in);
	private String rojo = "\033[31m";
	private String verde = "\033[32m";
	private String amarillo = "\033[33m";
	private String azul = "\033[34m";
	//private String morado = "\033[35m";
	private String cyan = "\033[36m";
	private String reset = "\u001B[0m";
	public String rojoP = "";
	public String negroP = "";
	public int puntuacion1 = 0;
	public int puntuacion2 = 0;

	public static void cls() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public damas() {
		System.out.println(rojo + "Jugador Rojo: " + reset);
		rojoP = t.nextLine();
		System.out.println(verde + "Jugador Negro: " + reset);
		negroP = t.nextLine();
		System.out.println(cyan + "Se enfrentaran " + rojoP + " & " + negroP + " en el tablero" + reset);
		tablero = new char[tab][tab];
		rojas = 12;
		negras = 12;
		mov = 'r';

		int i, j;
		for (i = 0; i < tab; i++)
			for (j = 0; j < tab; j++)
				tablero[i][j] = '_';

		for (i = 1; i < tab; i += 2) {
			tablero[i][1] = 'r';
			tablero[i][5] = 'n';
			tablero[i][7] = 'n';
		}
		for (i = 0; i < tab; i += 2) {
			tablero[i][0] = 'r';
			tablero[i][2] = 'r';
			tablero[i][6] = 'n';
		}
	}

	public void crearTablero() {
		int i, j;
		System.out.println(amarillo + "  1 2 3 4 5 6 7 8 x" + reset);
		for (i = 0; i < tab; i++) {
			System.out.print((i + 1) + " ");
			for (j = 0; j < tab; j++) {
				System.out.print(tablero[j][i] + " ");
			}
			System.out.println();
		}
		System.out.println("y");
	}

	public void indicador() throws IOException {

		if (mov == 'r')
			System.out.println(rojo + "Es turno de las rojas " + reset);
		else
			System.out.println(verde + "Es turno de las negras " + reset);

		boolean mover = false;

		while (!mover) {

			System.out.println(cyan + "¿Que ficha moveras?." + reset);
			int seleccionarFicha = t.nextInt();

			System.out.println(azul + "¿A donde moveras tu ficha?." + reset);
			int moverFicha = t.nextInt();

			// validar el movimiento para ser jugado
			if (validar(seleccionarFicha, moverFicha)) {
				ejecutarMovimiento(seleccionarFicha, moverFicha);
				mover = true;
				cls();
			} else {
				System.out.println(amarillo + "No te puedes mover de esa forma" + reset);
			}

		}

		// actualizar fichas
		if (mov == 'r')
			mov = 'n';
		else
			mov = 'r';

	}

	// checa si jala o ne
	public boolean validar(int coordenadaSeleccionada, int nuevaCoordenada) {

		// obtener parametros del movimiento
		int xSelec = coordenadaSeleccionada / 10 - 1;
		int ySelec = coordenadaSeleccionada % 10 - 1;
		int xMover = nuevaCoordenada / 10 - 1;
		int yMover = nuevaCoordenada % 10 - 1;

		//
		if (xSelec < 0 || xSelec > 7 || ySelec < 0 || ySelec > 7 ||
				xMover < 0 || xMover > 7 || yMover < 0 || yMover > 7)
			return false;

		// checa la casilla
		else if (tablero[xSelec][ySelec] == mov && tablero[xMover][yMover] == '_') {

			// verifica mov
			if (Math.abs(xSelec - xMover) == 1) {
				if ((mov == 'r') && (yMover - ySelec == 1))
					return true;
				else if ((mov == 'n') && (yMover - ySelec == -1))
					return true;
			}

			// verifica el movimiento
			else if (Math.abs(xSelec - xMover) == 2) {
				if (mov == 'r' && (yMover - ySelec == 2) &&
						tablero[(xSelec + xMover) / 2][(ySelec + yMover) / 2] == 'n')
					return true;
				else if (mov == 'n' && (yMover - ySelec == -2) &&
						tablero[(xSelec + xMover) / 2][(ySelec + yMover) / 2] == 'r')
					return true;
			}
		}
		// por si no jala
		return false;
	}

	// juega
	public void ejecutarMovimiento(int coordenadaSeleccionada, int nuevaCoordenada) {
		int xSelec = coordenadaSeleccionada / 10 - 1;
		int ySelec = coordenadaSeleccionada % 10 - 1;
		int xMover = nuevaCoordenada / 10 - 1;
		int yMover = nuevaCoordenada % 10 - 1;

		tablero[xSelec][ySelec] = '_';
		tablero[xMover][yMover] = mov;
		if (Math.abs(xMover - xSelec) == 2) {
			tablero[(xSelec + xMover) / 2][(ySelec + yMover) / 2] = '_';
			if (mov == 'r') {
				rojas--;
				puntuacion2++;
			} else {
				negras--;
				puntuacion1++;
			}
		}
	}

	public boolean perdio() {
		return (rojas == 0 || negras == 0);
	}

	public String ganador() {
		if (negras == 0) {
			puntuacion();
			return "Damas Negras";
		} else {
			puntuacion();
			return "Damas Rojas";

		}
	}

	public void puntuacion(){
		if (negras == 0) {
			System.out.println("Puntuacion de las rojas: " + puntuacion1);
		}else{
			System.out.println("Puntuacion de las negras: " + puntuacion2);
		}
		
	}

	public void guardar(){
		
		try {
			File file = new File("puntuacion.txt");
			FileWriter fw = new FileWriter("puntuacion.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(rojoP + puntuacion1 + " " + negroP +  puntuacion2);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
