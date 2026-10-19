import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Personaje> personajes;
    private static HashMap<String, Personaje> campeones;
    private static ArrayList<Protagonista> mains;
    private static ArrayList<Enemigo> enemies;
    private static HashMap<String, Protagonista> hashmapMains;
    private static HashMap<String, Enemigo> hashmapEnemies;
    private static ArrayList<Item> objetosA;
    private static ArrayList<Item> objetosC;
    private static ArrayList<Item> items;
    private static HashMap<String, Item> hashmapItems;

    public static void main(String[] args) throws Exception {
        login();
        sc.close();
    }

    public static void login() {
        try {
            String ruta = "users.txt";
            File archv = new File(ruta);
            FileReader fr = new FileReader(archv);
            BufferedReader br = new BufferedReader(fr);

            boolean credencialesBien = false;
            String linea;
            String user = " ";
            String passw = " ";
            while ((linea = br.readLine()) != null) {
                String[] login = linea.split(";");
                user = login[0];
                passw = login[1];
                passw= passw.trim();
            }

            String usuario = " ";
            String contr = " ";
            while (!credencialesBien) {
                boolean userCorrecto = false;
                int intentos = 0;
                while (!userCorrecto) {
                    intentos++;
                    System.out.print("Escribe tu usuario: ");
                    usuario = sc.nextLine();

                    if (usuario.equals(user) && (intentos < 3)) {
                        System.out.println("El usuario es el correcto");
                        userCorrecto = true;
                    } else if (intentos < 3) {
                        System.out.println("El usuario escrito no es correcto, vuelve a intentarlo");
                    } else {
                        System.out.println("Has superado el número de intentos...");
                    }
                }

                boolean passwCorrecta = false;
                intentos = 0;
                while (!passwCorrecta) {
                    intentos++;
                    System.out.print("Escribe contraseña: ");
                    contr = sc.nextLine();
                    contr= contr.trim();

                    if (contr.equals(passw.trim()) && (intentos <=3)) {
                        System.out.println("Contraseña correcta");
                        passwCorrecta = true;
                    } else if (intentos <=3) {
                        System.out.println("La contraseña escrita no es correcta, vuelve a intentarlo");
                    } else {
                        System.out.println("Has superado el número de intentos...");
                    }
                }

                if((userCorrecto==true)&&(passwCorrecta==true)){
                    menu();
                } 

                br.close();
            }
        } catch (Exception e) {
            System.out.println("Se ha producido una excepción: " + e.getMessage());
            System.out.println("El debugger está buscando aquí: " + new java.io.File(".").getAbsolutePath());
        }

    }

    public static void menu() {
        System.out.println("\r\n   - - - -   BIENVENIDX AL JUEGO   - - - -");
        boolean salir = false;
        int opc = 0;
        crearPersonajes();
        crearObjetos();
        System.out.println("\r\n- - - - - - - - - - PERSONAJES - - - - - - - - - -");
        verPersonajes();
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - -");

        while (!salir) {
            System.out.println(" ");
            System.out.println("              MENÚ PRINCIPAL      ");
            System.out.println("--------------------------------------------");
            System.out.println("    0 - SALIR");
            System.out.println("    1 -  Ver personajes creados");
            System.out.println("    2 -  Ver personajes según nvl ataque");
            System.out.println("    3 -  Ver PROTAGONISTAS");
            System.out.println("    4 -  Ver ENEMIGOS");
            System.out.println("    5 -  Ver objetos");
            System.out.println("    6 -  Modificar personajes");
            System.out.println("    7 -  Modificar objetos");
            System.out.println("    8 - Crear Personaje Custom");
            System.out.println("    9 - Crear objeto custom");
            System.out.println("    10 - ¡¡ JUGAAAAAR !!");
            System.out.println("---------------------------------------------");
            System.out.print(" -- Escoge una opción: ");
            opc = sc.nextInt();
            sc.nextLine();

            switch ((opc)) {
                case 0:
                    System.out.println("Adiós!");
                    salir = true;
                    break;

                case 1:
                    verPersonajes();
                    break;

                case 2:
                    mostrarATK();
                    break;

                case 3:
                    verProtagonistas();
                    break;

                case 4:
                    verEnemigos();
                    break;

                case 5:
                    mostrarObjetosHashmap();
                    break;

                case 6:
                    System.out.println("- - - - - - - - - - - - -");
                    System.out.println("  Entre estas opciones: ");
                    System.out.println("    1 - Personaje ");
                    System.out.println("    2 - Protagonista ");
                    System.out.println("    3 - Enemigo ");
                    System.out.println("    0 - Salir");
                    System.out.println("- - - - - - - - - - - - -");
                    System.out.print("Dime qué quieres modificar: ");

                    int aux = 0;
                    boolean correcto = false;
                    while (!correcto) {
                        try {
                            aux = Integer.parseInt(sc.nextLine());
                            if (aux == 1) {
                                modificarPersonajes();
                                correcto = true;
                            } else if (aux == 2) {
                                modificarProtagonistas();
                                correcto = true;
                            } else if (aux == 3) {
                                modificarEnemigos();
                                correcto = true;
                            } else if (aux == 0) {
                                System.out.println("Volviendo al menú principal...");
                                correcto = true;
                            } else {
                                System.out.println("Esa no es una opción válida");
                            }
                        } catch (Exception e) {
                            System.out.println("Ha ocurrido una excepción");
                        }
                    }
                    break;

                case 7:
                    modificarObjetos();
                    break;

                case 8:
                    crearPersonajeCustom();
                    break;

                case 9:
                    crearItemCustom();
                    break;

                case 10:
                    jugar();
                    break;

                default:
                    break;
            }
        }

    }

    public static void crearPersonajes() {
        Protagonista Yon = new Protagonista("Yon", 8, 9, 100);
        Protagonista Tara = new Protagonista("Tara", 12, 6, 100);
        Protagonista Sazza = new Protagonista("Sazza", 9, 9, 100);
        Protagonista PrettyPol = new Protagonista("PrettyPol", 13, 5, 100);
        Protagonista Candice = new Protagonista("Candice", 7, 10, 100);
        Enemigo Vijj = new Enemigo("Vijj", 10, 8, 100, 40);
        Enemigo Shak = new Enemigo("Shak", 10, 8, 100, 50);
        Enemigo Widow = new Enemigo("Widow", 10, 8, 100, 40);
        Enemigo Bog = new Enemigo("Bog", 10, 8, 100, 60);
        Enemigo Manto = new Enemigo("Manto", 10, 8, 100, 60);

        campeones = new HashMap<>();
        campeones.put("Yon", Yon);
        campeones.put("Tara", Tara);
        campeones.put("Sazza", Sazza);
        campeones.put("PrettyPol", PrettyPol);
        campeones.put("Candice", Candice);
        campeones.put("Vijj", Vijj);
        campeones.put("Shak", Shak);
        campeones.put("Widow", Widow);
        campeones.put("Bog", Bog);
        campeones.put("Manto", Manto);
        personajes = new ArrayList<Personaje>();
        personajes.add(Yon);
        personajes.add(Tara);
        personajes.add(Sazza);
        personajes.add(PrettyPol);
        personajes.add(Candice);
        personajes.add(Vijj);
        personajes.add(Shak);
        personajes.add(Widow);
        personajes.add(Bog);
        personajes.add(Manto);
        Collections.sort(personajes);

        mains = new ArrayList<>();
        mains.add(Yon);
        mains.add(Tara);
        mains.add(Sazza);
        mains.add(PrettyPol);
        mains.add(Candice);
        hashmapMains = new HashMap<>();
        hashmapMains.put("Yon", Yon);
        hashmapMains.put("Tara", Tara);
        hashmapMains.put("Sazza", Sazza);
        hashmapMains.put("PrettyPol", PrettyPol);
        hashmapMains.put("Candice", Candice);

        enemies = new ArrayList<>();
        enemies.add(Vijj);
        enemies.add(Shak);
        enemies.add(Widow);
        enemies.add(Bog);
        enemies.add(Manto);
        hashmapEnemies = new HashMap<>();
        hashmapEnemies.put("Vijj", Vijj);
        hashmapEnemies.put("Shak", Shak);
        hashmapEnemies.put("Widow", Widow);
        hashmapEnemies.put("Bog", Bog);
        hashmapEnemies.put("Manto", Manto);

    }

    public static void crearObjetos() {
        Item A1 = new Item("Hacha", Tipo.Armadura, 8, 5, 3);
        Item A2 = new Item("Escudo", Tipo.Armadura, 5, 7, 4);
        Item A3 = new Item("Casco", Tipo.Armadura, 4, 7, 5);
        Item A4 = new Item("Yelmo", Tipo.Armadura, 0, 10, 6);

        objetosA = new ArrayList<>();
        objetosA.add(A1);
        objetosA.add(A2);
        objetosA.add(A3);
        objetosA.add(A4);

        Item C1 = new Item("Poción", Tipo.Curativa, 0, 4, 5);
        Item C2 = new Item("Elixir", Tipo.Curativa, 2, 4, 3);
        Item C3 = new Item("Poción 2", Tipo.Curativa, 0, 4, 5);
        Item C4 = new Item("Hierbas curativas", Tipo.Curativa, 0, 3, 6);

        objetosC = new ArrayList<>();
        objetosC.add(C1);
        objetosC.add(C2);
        objetosC.add(C3);
        objetosC.add(C4);

        hashmapItems = new HashMap<>();
        hashmapItems.put("Hacha", A1);
        hashmapItems.put("Escudo", A2);
        hashmapItems.put("Casco", A3);
        hashmapItems.put("Yelmo", A4);
        hashmapItems.put("P1", C1);
        hashmapItems.put("Elixir", C2);
        hashmapItems.put("P2", C3);
        hashmapItems.put("Hierbas", C4);

    }

    public static void verPersonajes() {
        try {
            for (Personaje character : campeones.values()) {
                System.out.println("- " + character.mostrarInfo() + " (" + character.getClass().getSimpleName() + ")");
            }
        } catch (Exception NullPointerexception) {
            System.out.println("El array está vacío");
        }
    }

    public static void verProtagonistas() {
        int i = 0;
        try {
            for (Personaje main : campeones.values()) {
                if (main instanceof Protagonista) {
                    System.out.println((i + 1) + " - " + main.mostrarInfo());
                    i++;
                }
            }
        } catch (Exception NullPointerexception) {
            System.out.println("El array está vacío");
        }
    }

    public static void verEnemigos() {
        int i = 0;
        try {
            for (Personaje enemy : campeones.values()) {
                if (enemy instanceof Enemigo) {
                    System.out.println((i + 1) + " - " + enemy.mostrarInfo());
                    i++;
                }
            }
        } catch (Exception NullPointerexception) {
            System.out.println("El array está vacío");
        }
    }

    public static void mostrarATK() {
        System.out.print("Dime un nivel de ataque: ");
        int valorA = sc.nextInt();
        sc.nextLine();
        int cont = 0;
        for (Personaje personaje : campeones.values()) {
            if (personaje.getATK() > valorA) {
                System.out.println(personaje.getNombre() + "(" + personaje.getClass().getSimpleName() + ")"
                        + " -> ATAQUE: " + personaje.getATK());
                cont++;
            }
        }
        if (cont == 0) {
            System.out.println("Ningún personaje tiene un ataque mayor a nivel: " + valorA);
        }

        System.out.println("- - - - - - - - - - - - - - - - - - -");
    }

    public static void modificarPersonajes() {
        try {
            for (Personaje personaje : personajes) {
                if ((personaje instanceof Personaje) && (personaje != null)) {
                    System.out.println(
                            " - " + personaje.mostrarInfo2() + " (" + personaje.getClass().getSimpleName() + ") ");
                }
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido una excepción, el Array puede estar vacío...");
        }

        String character = "";
        boolean correcto = false;

        while (!correcto) {
            try {
                System.out.print("Dime que personaje quieres modificar: ");
                character = sc.nextLine();
                String chrter = character.substring(0, 1).toUpperCase() + character.substring(1).toLowerCase();
                character = chrter.trim();
                for (int i = 0; i < personajes.size(); i++) {
                    if (personajes.get(i).getNombre().equals(character)) {

                        System.out.print("Dime un nuevo nombre: ");
                        String nuevoNombre = sc.nextLine();
                        personajes.get(i).setNombre(nuevoNombre);
                        System.out.print("Dime su nivel de ataque: ");
                        int nuevoAtk = Integer.parseInt(sc.nextLine());
                        personajes.get(i).setATK(nuevoAtk);
                        System.out.print("Dime su nivel de defensa: ");
                        int nuevaDef = Integer.parseInt(sc.nextLine());
                        personajes.get(i).setDEF(nuevaDef);
                        System.out.print("Dime su nivel de HP: ");
                        int nuevoHP = Integer.parseInt(sc.nextLine());
                        personajes.get(i).setHP(nuevoHP);
                        correcto = true;

                    }
                }

            } catch (Exception e) {
                System.out.println("Debes introducir un número entero");
            }
        }

    }

    public static void modificarProtagonistas() {
        for (int i = 0; i < mains.size(); i++) {
            System.out.println("Protagonista " + (i + 1) + " - " + mains.get(i).getNombre());
        }

        int opc = 0;
        boolean correcto = false;

        while (!correcto) {
            try {
                System.out.print("Dime que protagonista quieres modificar: ");
                opc = Integer.parseInt(sc.nextLine());
                System.out.print("Dime un nuevo nombre: ");
                String nuevoNombre = sc.nextLine();
                mains.get(opc - 1).setNombre(nuevoNombre);
                System.out.print("Dime su nivel de ataque: ");
                int nuevoAtk = Integer.parseInt(sc.nextLine());
                mains.get(opc - 1).setATK(nuevoAtk);
                System.out.print("Dime su nivel de defensa: ");
                int nuevaDef = Integer.parseInt(sc.nextLine());
                mains.get(opc - 1).setDEF(nuevaDef);
                System.out.print("Dime su nivel de HP: ");
                int nuevoHP = Integer.parseInt(sc.nextLine());
                mains.get(opc - 1).setHP(nuevoHP);
                correcto = true;
            } catch (Exception e) {
                System.out.println("Debes introducir un número entero");
            }
        }
    }

    public static void modificarEnemigos() {
        for (int i = 0; i < enemies.size(); i++) {
            System.out.println("Enemigo " + (i + 1) + " - " + enemies.get(i).getNombre());
        }

        int opc = 0;
        boolean correcto = false;

        while (!correcto) {
            try {
                System.out.print("Dime que protagonista quieres modificar: ");
                opc = Integer.parseInt(sc.nextLine());
                System.out.print("Dime un nuevo nombre: ");
                String nuevoNombre = sc.nextLine();
                enemies.get(opc - 1).setNombre(nuevoNombre);
                System.out.println("Dime su nivel de ataque");
                int nuevoAtk = Integer.parseInt(sc.nextLine());
                enemies.get(opc - 1).setATK(nuevoAtk);
                System.out.println("Dime su nivel de defensa");
                int nuevaDef = Integer.parseInt(sc.nextLine());
                enemies.get(opc - 1).setDEF(nuevaDef);
                System.out.println("Dime su nivel de HP");
                int nuevoHP = Integer.parseInt(sc.nextLine());
                enemies.get(opc - 1).setHP(nuevoHP);
                correcto = true;
            } catch (Exception e) {
                System.out.println("Debes introducir un número entero");
            }
        }
    }

    public static void mostrarObjetos() {
        System.out.println("Objetos disponibles: ");
        try {
            for (Item item : hashmapItems.values()) {
                if (item.getTipo() == Tipo.Armadura) {
                    System.out.println("Objetos de tipo ARMADURA ");
                    System.out.println(item.toString());
                } else if (item.getTipo() == Tipo.Curativa) {
                    System.out.println("Objetos de tipo CURATIVO ");
                    System.out.println(item.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Ocurrió una excepción, el array puede estar vacío...");
        }
    }

    public static void mostrarObjetosHashmap() {
        System.out.println("Objetos disponibles: ");
        try {
            for (HashMap.Entry<String, Item> item : hashmapItems.entrySet()) {
                if (item.getValue().getTipo() == Tipo.Armadura) {
                    System.out.println(" - " + item.getValue() + " (ARMADURA)");
                } else if (item.getValue().getTipo() == Tipo.Curativa) {
                    System.out.println(" - " + item.getValue() + " (" + item.getValue().getTipo() + ")");
                }
            }
        } catch (Exception e) {
            System.out.println("Ocurrió una excepción, el array puede estar vacío...");
        }
    }

    public static void modificarObjetos() {
        int opc = 0;
        String tipo = "";
        boolean correcto = false;

        while (!correcto) {
            try {
                System.out.print("Dime qué tipo de objeto quieres modificar: Armadura(A) o Curativo (C): ");
                tipo = sc.nextLine();
                if (tipo == "A") {
                    for (int i = 0; i < objetosA.size(); i++) {
                        System.out.println("Objetos de tipo Armadura " + (i + 1) + " - " + objetosA.get(i).toString());
                    }
                    System.out.print("Dime que objeto quieres modificar: ");
                    opc = Integer.parseInt(sc.nextLine());
                    System.out.print("Dime un nuevo nombre: ");
                    String nuevoNombre = sc.nextLine();
                    objetosA.get(opc - 1).setNombre(nuevoNombre);
                    System.out.println("Dime su nivel de ataque");
                    int nuevoAtk = Integer.parseInt(sc.nextLine());
                    objetosA.get(opc - 1).setATK(nuevoAtk);
                    System.out.println("Dime su nivel de defensa");
                    int nuevaDef = Integer.parseInt(sc.nextLine());
                    objetosA.get(opc - 1).setDEF(nuevaDef);
                    System.out.println("Dime su nivel de HP");
                    int nuevoHP = Integer.parseInt(sc.nextLine());
                    objetosA.get(opc - 1).setHP(nuevoHP);
                    correcto = true;
                } else if (tipo == "C") {
                    for (int i = 0; i < objetosC.size(); i++) {
                        System.out.println("Objetos de tipo Curativo " + (i + 1) + " - " + objetosC.get(i).toString());
                    }
                    System.out.print("Dime que objeto quieres modificar: ");
                    opc = Integer.parseInt(sc.nextLine());
                    System.out.print("Dime un nuevo nombre: ");
                    String nuevoNombre = sc.nextLine();
                    objetosC.get(opc - 1).setNombre(nuevoNombre);
                    System.out.println("Dime su nivel de ataque");
                    int nuevoAtk = Integer.parseInt(sc.nextLine());
                    objetosC.get(opc - 1).setATK(nuevoAtk);
                    System.out.println("Dime su nivel de defensa");
                    int nuevaDef = Integer.parseInt(sc.nextLine());
                    objetosC.get(opc - 1).setDEF(nuevaDef);
                    System.out.println("Dime su nivel de HP");
                    int nuevoHP = Integer.parseInt(sc.nextLine());
                    objetosC.get(opc - 1).setHP(nuevoHP);
                    correcto = true;
                }

            } catch (Exception e) {
                System.out.println("Debes introducir un número entero");
            }
        }
    }

    public static void crearPersonajeCustom() {
        System.out.println("Crea un personaje: Protagonista (P) o Enemigo (E)");

        System.out.print("Escribe P o E: ");
        String opc = sc.nextLine();

        System.out.print("Dime un nuevo nombre: ");
        String nuevoNombre = sc.nextLine();

        System.out.print("Dime su nivel de ataque: ");
        int nuevoAtk = Integer.parseInt(sc.nextLine());

        System.out.print("Dime su nivel de defensa: ");
        int nuevaDef = Integer.parseInt(sc.nextLine());

        System.out.print("Dime su nivel de HP: ");
        int nuevoHP = Integer.parseInt(sc.nextLine());

        if (opc == "P") {
            personajes.add(new Protagonista(nuevoNombre, nuevoAtk, nuevaDef, nuevoHP));
        } else if (opc == "E") {
            personajes.add(new Enemigo(nuevoNombre, nuevoAtk, nuevaDef, nuevoHP, nuevoHP));
        }

    }

    public static void crearItemCustom() {
        System.out.println("Vamos a crear un nuevo objeto!!");

        System.out.print("Dime un nuevo nombre: ");
        String nuevoNombre = sc.nextLine();

        System.out.print("Dime su nivel de ataque: ");
        int nuevoAtk = Integer.parseInt(sc.nextLine());

        System.out.print("Dime su tipo, Armadura (A) o Curativa (C)");
        String nuevoTipo = sc.nextLine();

        System.out.print("Dime su nivel de defensa: ");
        int nuevaDef = Integer.parseInt(sc.nextLine());

        System.out.print("Dime su nivel de HP: ");
        int nuevoHP = Integer.parseInt(sc.nextLine());

        if (nuevoTipo == "A") {
            objetosA.add(new Item(nuevoNombre, Tipo.Armadura, nuevoAtk, nuevaDef, nuevoHP));
        } else if (nuevoTipo == "C") {
            objetosC.add(new Item(nuevoNombre, Tipo.Curativa, nuevoAtk, nuevaDef, nuevoHP));
        }
    }

    public static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int generarAleatorio() {
        int aleatorio = 0;
        boolean num = false;
        while (!num) {
            aleatorio = (int) (Math.random() * 10 - 1);
            if (aleatorio <= 3) {
                num = true;
            }
        }
        return aleatorio;
    }

    public static int comprobarEntero() {
        // Eliminamos la variable opc, (boolean opc=true)
        // usamos while(true) para que Java entienda que es infinito
        while (true) {
            try {
                System.out.print("Introduce un número entero: ");
                int num = sc.nextInt();
                sc.nextLine(); // Limpiamos el buffer después de leer el número
                return num; // Si llega aquí, sale del método con el valor
            } catch (Exception e) {
                System.out.println("Error: No has introducido un número válido.");
                sc.nextLine(); // ¡IMPORTANTE! Limpiar el buffer para eliminar la entrada errónea
            }
        }
    }

    public static void jugar() {
        limpiarPantalla();
        verProtagonistas();
        int partidasGanadas;
        // System.out.print("--> Elige un personaje por número: ");
        // int opc = 0;
        // opc = comprobarEntero();
        System.out.print("Escribe el nombre del personaje a jugar: ");
        String personajeElegido = sc.nextLine();
        personajeElegido = personajeElegido.trim();
        String personajeBien = personajeElegido.substring(0, 1).toUpperCase()
                + personajeElegido.substring(1).toLowerCase();
        personajeElegido = personajeBien;
        hashmapMains.get(personajeElegido);
        System.out.print("\r\nSe te ha asignado a --> ");
        System.out.println(hashmapMains.get(personajeElegido).mostrarInfo() + " <--");
        System.out.println(" \r\n  - - - - MODO JUEGO - - - -");
        System.out.println(" Pulsar 1 para modalidad Fácil");
        System.out.println(" Pulsar 2 para modalidad Difícil");
        System.out.print("   --> Elige dificultad: ");
        int dificultad = Integer.parseInt(sc.nextLine());

        items = new ArrayList<>();
        if (dificultad == 1) {
            limpiarPantalla();
            int aleatorio1 = generarAleatorio();
            items.add(objetosA.get(aleatorio1));
            System.out.println("\r\n--Se te ha asignado 1 objeto de tipo Armadura");

            int aleatorio2 = generarAleatorio();
            items.add(objetosC.get(aleatorio2));
            int aleatorio3 = generarAleatorio();
            items.add(objetosC.get(aleatorio3));
            hashmapMains.get(personajeElegido).setItems(items);
            System.out.println("--Se te han asignado 2 objetos de tipo Curativo");

            System.out.println("    -  ITEMS OBTENIDOS  -");
            hashmapMains.get(personajeElegido).getItems();

            hashmapMains.get(personajeElegido).setOro(20);
            System.out.println("-- Se te han dado 20 monedas de oro");

            int enmy = 0;
            boolean numEnemy = false;
            while (!numEnemy) {
                enmy = (int) (Math.random() * 10 - 1);
                if (enmy < 5) {
                    numEnemy = true;
                }
            }
            if (numEnemy == true) {
                try {
                    System.out.println("\r\nTienes que pelear contra...");
                    System.out.print(enemies.get(enmy).getNombre());
                    System.out.println(" - - Nvl HP: " + enemies.get(enmy).getHP());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Excepción por llamar erróneamente a la posición del array");
                }
            }

            boolean salirJuego = false;
            while (!salirJuego) {
                int contadorAtaques = 0;
                int vidaActualProta = hashmapMains.get(personajeElegido).getHP();
                System.out.println("Tu vida actual es: " + hashmapMains.get(personajeElegido).getHP());
                System.out.println("\r\nPuedes: Salir (0), Atacar (1) o Escoger un Item (2)");
                System.out.print("Escribe tu elección: ");
                int opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 0:
                        System.out.println("Saliendo...");
                        salirJuego = true;
                        break;
                    case 1:
                        limpiarPantalla();
                        contadorAtaques++;
                        System.out.println("\r\nATACANDOOOOOOO...");
                        System.out.println("...");
                        int vidaEnemigo = enemies.get(enmy).getHP();
                        int ataqueProta = hashmapMains.get(personajeElegido).getATK();
                        int resultadoPelea = vidaEnemigo - ataqueProta;
                        System.out.println(
                                "--> La vida de " + enemies.get(enmy).getNombre() + " está a: " + resultadoPelea);
                        enemies.get(enmy).setHP(resultadoPelea);
                        if (resultadoPelea <= 0) {
                            System.out.println("El enemigo ha sido DERROTADO !!");
                            hashmapMains.get(personajeElegido).sumaPartida();
                            salirJuego = true;
                        } else {
                            System.out.println("...");
                            System.out.println("Es turno del enemigo... ");
                            System.out.println("PREPÁRATE PARA EL CONTRAATAQUE");
                            System.out.println("...");
                            vidaActualProta = hashmapMains.get(personajeElegido).getHP();
                            int ataqueEnemigo = enemies.get(enmy).getATK();
                            int contraAtaque = vidaActualProta - ataqueEnemigo;
                            hashmapMains.get(personajeElegido).setHP(contraAtaque);
                        }
                        break;
                    case 2:
                        System.out.println("Elige un item de tipo Curativo: ");
                        hashmapMains.get(personajeElegido).mostrarItemCurativo();
                        System.out.print("Escribe 1 o 2: ");
                        int curaElegida = Integer.parseInt(sc.nextLine());

                        if (curaElegida == 1) {
                            int valorPocima1 = objetosC.get(aleatorio2).getHP();
                            int vidaNuevaProta = vidaActualProta + valorPocima1;
                            hashmapMains.get(personajeElegido).setHP(vidaNuevaProta);
                            System.out.println("Épico");
                            System.out.println(":) :) :)");
                            System.out.println("Ahora cuentas con nueva vida");
                        } else {
                            int valorPocima2 = objetosC.get(aleatorio3).getHP();
                            int vidaNuevaProta = vidaActualProta + valorPocima2;
                            hashmapMains.get(personajeElegido).setHP(vidaNuevaProta);
                            System.out.println("\r\nÉpico");
                            System.out.println(":) :) :)");
                            System.out.println("Ahora cuentas con nueva vida");
                        }
                        break;

                    default:
                        System.out.println("Esa opción no es válida");
                        break;
                }
            }
        } else if (dificultad == 2) {
            limpiarPantalla();
            int enmy = 0;
            boolean numEnemy = false;
            while (!numEnemy) {
                enmy = (int) (Math.random() * 10 - 1);
                if (enmy < 5) {
                    numEnemy = true;
                }
            }
            if (numEnemy == true) {
                try {
                    System.out.println("\r\nTienes que pelear contra...");
                    System.out.print(enemies.get(enmy).getNombre());
                    System.out.println(" - - Nvl HP: " + enemies.get(enmy).getHP());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Excepción por llamar erróneamente a la posición del array");
                }
            }

            boolean salirJuego = false;
            while (!salirJuego) {
                int vidaActualProta = hashmapMains.get(personajeElegido).getHP();
                System.out.println("Tu vida actual es: " + hashmapMains.get(personajeElegido).getHP());
                System.out.println("\r\nPuedes: Salir (0), Atacar (1) o Escoger un Item (2)");
                System.out.print("Escribe tu elección: ");
                int opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 0:
                        System.out.println("Saliendo...");
                        salirJuego = true;
                        break;
                    case 1:
                        limpiarPantalla();
                        System.out.println("\r\nATACANDOOOOOOO...");
                        System.out.println("...");
                        int vidaEnemigo = enemies.get(enmy).getHP();
                        int ataqueProta = hashmapMains.get(personajeElegido).getATK();
                        int resultadoPelea = vidaEnemigo - ataqueProta;
                        System.out.println(
                                "--> La vida de " + enemies.get(enmy).getNombre() + " está a: " + resultadoPelea);
                        enemies.get(enmy).setHP(resultadoPelea);
                        if (resultadoPelea <= 0) {
                            System.out.println("El enemigo ha sido DERROTADO !!");
                            hashmapMains.get(personajeElegido).sumaPartida();
                            salirJuego = true;
                        } else {
                            System.out.println("...");
                            System.out.println("Es turno del enemigo... ");
                            System.out.println("PREPÁRATE PARA EL CONTRAATAQUE");
                            System.out.println("...");
                            vidaActualProta = hashmapMains.get(personajeElegido).getHP();
                            int ataqueEnemigo = enemies.get(enmy).getATK() /* - (mains.get(opc).getDEF()) */;
                            int contraAtaque = vidaActualProta - ataqueEnemigo;
                            hashmapMains.get(personajeElegido).setHP(contraAtaque);
                        }
                        break;
                    case 2:
                        boolean listo = false;
                        while (!listo) {
                            System.out.println("\t\nElige un item de tipo Curativo: ");
                            for (int i = 0; i < objetosC.size(); i++) {
                                System.out.println((i + 1) + "- " + objetosC.get(i).mostrarInfo());
                            }
                            System.out.print("Escribe un número: ");
                            int curaElegida = Integer.parseInt(sc.nextLine());
                            if (curaElegida != 0) {
                                int valorPocima1 = objetosC.get(curaElegida - 1).getHP();
                                int vidaNuevaProta = vidaActualProta + valorPocima1;
                                hashmapMains.get(personajeElegido).setHP(vidaNuevaProta);
                                System.out.println("Épico");
                                System.out.println(":) :) :)");
                                System.out.println("Ahora cuentas con nueva vida");
                                listo = true;
                            } else {
                                System.out.println("Esa opción no es válida");
                            }
                        }

                        break;

                    default:
                        System.out.println("Esa opción no es válida");
                        break;
                }
            }
        } else {
            System.out.println("Esa opción no es válida");
        }
    }

}
