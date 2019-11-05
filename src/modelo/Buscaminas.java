/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad Icesi (Cali - Colombia)
 * Propuesta de solucion laboratorio Unidad 5
 * @author Camilo Barrios - camilo.barrios@correo.icesi.edu.co
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */


package modelo;


public class Buscaminas {


	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel principiante
	 */
	public static final int FILAS_PRINCIPIANTE = 8;

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel intermedio
	 */
	public static final int FILAS_INTERMEDIO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de filas en el nivel experto
	 */
	public static final int FILAS_EXPERTO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel principiante
	 */
	public static final int COLUMNAS_PRINCIPIANTE = 8;

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel intermedio
	 */
	public static final int COLUMNAS_INTERMEDIO = 16;

	/**
	 * Es una constante utilizada para indicar la cantidad de columnas en el nivel experto
	 */
	public static final int COLUMNAS_EXPERTO = 30;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el nivel principiante
	 */
	public static final int PRINCIPIANTE = 1;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el nivel intermedio
	 */
	public static final int INTERMEDIO = 2;

	/**
	 * Es una constante utilizada para saber la dificultad del juego, representa el nivel experto
	 */
	public static final int EXPERTO = 3;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel principiante
	 */
	public static final int CANTIDAD_MINAS_PRINCIPANTE = 10;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel intermedio
	 */
	public static final int CANTIDAD_MINAS_INTERMEDIO = 40;

	/**
	 * Es una constante utilizada para saber la cantidad de minas en nivel experto
	 */
	public static final int CANTIDAD_MINAS_EXPERTO = 99;

	// -----------------------------------------------------------------
	// Atributos y relaciones
	// -----------------------------------------------------------------

	/**
	 * Relacion que tiene la matriz de casillas
	 */
	private Casilla[][] casillas;

	/**
	 * Atributo que representa el nivel del juego <Solo puede tomar valores PRINCIPIANTE, INTERMEDIO, EXPERTO>
	 */
	private int nivel;

	/**
	 * Atributo que tiene la cantidad de minas en el tablero
	 */
	private int cantidadMinas;

	/**
	 * Atributo que representa si el usuario perdio al abrir una mina
	 */
	private boolean perdio;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Constructor de la clase Buscaminas
	 * @param nivel - el nivel seleccionado por el usuario
	 */
	public Buscaminas(int nivel) {
		this.nivel = nivel;
		perdio = false;
		inicializarPartida();

	}


	// -----------------------------------------------------------------
	// Metodos
	// -----------------------------------------------------------------

	/**
	 * Se encarga de inicializar los atributos y relaciones de la clase buscaminas a partir del nivel elegido por el usuario
	 */
	private void inicializarPartida() {
		
		if (nivel ==PRINCIPIANTE) {			
			casillas =  new Casilla[FILAS_PRINCIPIANTE][COLUMNAS_PRINCIPIANTE];
			cantidadMinas = CANTIDAD_MINAS_PRINCIPANTE;
		}else if (nivel == INTERMEDIO) {
			casillas = new Casilla[FILAS_INTERMEDIO][COLUMNAS_INTERMEDIO];
			cantidadMinas = CANTIDAD_MINAS_INTERMEDIO; 
		}else if (nivel == EXPERTO) {
			casillas = new Casilla[FILAS_EXPERTO][COLUMNAS_EXPERTO];
			cantidadMinas= CANTIDAD_MINAS_EXPERTO;
		}
		generarMinas();
		inicializarCasillasLibres();
		// TODO

	}


	/**
	 * Metodo que se encarga de inicializar todas las casillas que no son minas
	 */
	public void inicializarCasillasLibres() {
		for (int i = 0 ; i<casillas.length;i++) {
			for (int j = 0 ; j<casillas[0].length;j++) {
				if (casillas[i][j]==null) {
					casillas[i][j]=new Casilla(Casilla.LIBRE);
					casillas[i][j].modificarValor(cantidadMinasAlrededor(i, j));
					
				}
			}
			
		}

	
	}


	/**
	 * Metodo que permite contar la cantidad de minas que tiene alrededor una casillas
	 * @param i - La fila de la matriz
	 * @param j - la columna de la matriz
	 * @return int - La cantidad de minas que tiene alrededor la casilla [i][j]
	 */
	public int cantidadMinasAlrededor(int i, int j) {
      
        int resultado = 0;
        try {
            if(casillas[i+1][j].esMina()) {
                resultado++;
            }
        } catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
        } 
        catch(NullPointerException nullPointerException) {
        	
        }

        try {
            if(casillas[i+1][j+1].esMina()) {
                resultado++;
            }
        } catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
        	
        } 
        catch(NullPointerException nullPointerException) {
        	
        }

        try {
            if(casillas[i][j+1].esMina()) {
                resultado++;
            }
        } catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
        } 
        catch(NullPointerException nullPointerException) {
        	
        }
        try {
            if(casillas[i-1][j].esMina()) {
                resultado++;
            }
        } catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
        } 
        catch(NullPointerException nullPointerException) {
        	
        }

        try {
            if(casillas[i-1][j-1].esMina()) {
                resultado++;
            }
        } catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
        } 
        catch(NullPointerException nullPointerException) {
        	
        }

        try {
            if(casillas[i][j-1].esMina()) {
                resultado++;
            }
        } catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
        } 
        catch(NullPointerException nullPointerException) {
        	
        }

        try {
            if(casillas[i-1][j+1].esMina()) {
                resultado++;
            }
        } catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
        } 
        catch(NullPointerException nullPointerException) {
        	
        }

        try {
            if(casillas[i+1][j-1].esMina()) {
                resultado++;
            }
        } catch(ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
        } 
        catch(NullPointerException nullPointerException) {
        	
        }

        casillas[i][j].modificarValor(resultado);
        return resultado;
    }

	/**
	 * Metodo que se encarga de generar aleatoriomente las minas
	 */
	public void generarMinas() {
		for (int i =0; i < cantidadMinas;) {
			double k = Math.random();
			double j = Math.random();
			int a = (int)(k*casillas.length);
			int b = (int)(j*casillas[0].length);
			if (casillas[a][b]==null) {
				casillas[a][b]=new Casilla(Casilla.MINA);
				i++;
			}
		}

		// TODO
		
	}


	/**
	 * Metodo que se encarga de convertir el tablero a un String para poder verlo en pantalla
	 * @return String - El tablero en formato String
	 */
	public String mostrarTablero() {
       String matrix ="   ";
       for(int i = 0; i < casillas[0].length; i++) {
    	   if(i<9) {
    		   matrix += (i+1)+"  ";
    	   }
    	   else {
    		   matrix += (i+1)+" ";
    	   }
       }
       matrix+="\n";
       for (int i=0;i<casillas.length;i++) {
    	   if(i<9) {
    		   matrix +=(i+1)+"  ";
    	   }
    	   else {
    		   matrix+=(i+1)+" ";
    	   }
    	   for (int j=0;j<casillas[0].length;j++) {
    		   if (j==(casillas[0].length-1)) {
    			   matrix +=casillas[i][j].mostrarValorCasilla()+"  \n";
    		   }else {
    			   matrix +=casillas [i][j].mostrarValorCasilla()+"  ";
    		   }
    	   }
       }
		// TODO

		return matrix;
	}


	/**
	 * Metodo que se encarga de marcar todas las casillas como destapadas
	 */
	public void resolver() {
		for (int i=0;i<casillas.length;i++) {
			for (int k=0;k<casillas[0].length;k++) {
				casillas[i][k].destapar();
				cantidadMinasAlrededor(i,k);
			}
		}

		// TODO

	}

	/**
	 * Metodo dar del atributo casillas
	 * @return la relacion casillas
	 */
	public Casilla[][] darCasillas(){
		return casillas;
	}


	/**
	 * Este metodo se encargaa de abrir una casilla
	 * Si se abre una casilla de tipo Mina, se marca que el jugador perdio el juego.
	 * @param i - la fila donde esta la casilla 
	 * @param j - la columna donde esta la casilla
	 * @return boolean - true si fue posible destaparla, false en caso contrario
	 */
	public boolean abrirCasilla(int i, int j) {
		boolean posible = false;
		if (casillas[i][j].esMina()) {
			perdio=true ;
			
		}else {
			perdio =false;
			
		}
		if( !casillas[i][j].darSeleccionada() ) {
			casillas[i][j].destapar();
		posible= true;
		}
		casillas[i][j].darValor();
		
		return posible;
	}


	/**
	 * Metodo que se encarga de revisar si el jugador gano el juego
	 * @return boolean - true si gano el juego, false en caso contrario
	 */
	public boolean gano() {
	boolean exito = false;
		int contador = 0;
		
	for (int i = 0;i<casillas.length;i++) {
		for (int j=0;j<casillas[0].length;j++) {
			if (casillas[i][j].darSeleccionada() && casillas[i][j].esMina() == false ) {
				contador++;
				
			}
			if (contador==(casillas.length*casillas[0].length)-cantidadMinas) {
				exito = true;
			}
			
		}
	}
	
		return exito;
	}
 

	/**
	 * Metodo que se encarga de abrir la primera casilla que no sea una Mina y cuyo valor sea Mayor que 0
	 * @return String, Mensaje de la Casilla que marco abierta, En caso de no haber casillas posibles para dar una pista, retorna el mensaje no hay pistas para dar
	 * @throws ExcepcionPista 
	 */
	
	
	
	public String darPista() throws ExcepcionPista {
		boolean pista = false;
		String msg = "";
		for (int i = 0;i<casillas.length && !pista ;i++) {
			for (int j=0;j<casillas[0].length && !pista ;j++) {
				if (casillas[i][j].esMina()==false && casillas[i][j].darSeleccionada()== false &&  casillas[i][j].darValor()!=0) {
					
						
					
					casillas[i][j].destapar();
					pista =true;
					msg ="Fila: "+(i+1)+" Y Columna: "+(j+1)+" Esta es la pista ";
					cantidadMinasAlrededor(i,j);
						}
				
				
				
			}
		}
      if (!pista) {
    	  throw new ExcepcionPista();
      }
		return msg;
	}
	
	
	
	/***
	 * Metodo dar del atributo perdio
	 * @return boolean el atributo
	 */
	public boolean darPerdio(){
		return perdio;
	}

}