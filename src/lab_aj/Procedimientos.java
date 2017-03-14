package lab_aj;

import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import static lab_aj.Diccionario_Añadir.PalabrasEsp;

/**
 *
 * @author juans_000
 */
public class Procedimientos {
public  static String Ma[][]=new String[1][7];
    public static boolean xAdd;
    public static int contador = 0;
    static Alfabeto alfabeto = new Alfabeto();

    public static int Verificar(String Palabra, int verif) {
        int error = 0;
        verif = 0;
        if ("".equals(Palabra)) {

        } else {
            for (int i = 0; i < Palabra.length(); i++) {
                if (" ".equals(Palabra.substring(i, i + 1))) {
                    error += 1;
                }
            }
            if (error == 0) {
                verif = 1;
            }
        }
        return verif;
    }

    public static void mjframe(javax.swing.JPanel panel, javax.swing.JPanel frame) {

        frame.setSize(panel.getSize());
        panel.removeAll();

        panel.setLayout(new BorderLayout(5, 5));
        panel.updateUI();
        panel.add(frame, BorderLayout.CENTER);

    }

    public static void Mtabla(javax.swing.JTable tabla) {

        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();

        for (int i = 0; i <= inicio.palabras; i++) {
            modelo.addRow(new Object[]{Diccionario_Añadir.PalabrasEsp[i], Diccionario_Añadir.PalabrasIng[i]});

        }

        tabla.setModel(modelo);

    }

    public static String binarios(String palabra) {
        String p = "";
        int x;
        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.substring(i, i + 1).equals(",")) {
                p = p + ",";
            } else {
                x = palabra.charAt(i);

                if (i < palabra.length() - 1) {
                    p = p + "" + Integer.toBinaryString(x) + "-";
                } else {
                    p = p + "" + Integer.toBinaryString(x);
                }

            }

        }
        return p;
    }

    public static String binarioastring(String palabra) {
        String pa = "";
        int inicio = 0;
        for (int i = 0; i < palabra.length(); i++) {
            if (i>0) {
              if (palabra.substring(i-1, i  ).equals(",")) {
                pa = pa + ",";
            }  
            }
              
                if (palabra.substring(i, i + 1).equals("-")||palabra.substring(i, i + 1).equals(",")) {
                    pa = pa + (char) Integer.parseInt(palabra.substring(inicio, i), 2);
                    inicio = i + 1;
                }
                if (i == palabra.length() - 1) {
                    pa = pa + (char) Integer.parseInt(palabra.substring(inicio, palabra.length()), 2);
                }

            
        }

        return pa;
    }

    public static int buscarp(String s, String v[], int l) {
        int pos = -1;
        for (int i = 0; i <= l; i++) {
            if (s.equals(v[i])) {
                pos = i;
            }
        }

        return pos;
    }

    public static void AgregarPalabra(String PalabraEsp, String PalabraIng) {
        int verificado = 0;
        verificado = Procedimientos.Verificar(PalabraEsp, verificado);
        if (verificado == 1) {
            verificado = Procedimientos.Verificar(PalabraIng, verificado);
            if (verificado == 1) {
                if (inicio.palabras != Diccionario_Limite.Limite) {
                    if (Procedimientos.buscarp(PalabraEsp, PalabrasEsp, inicio.palabras) == -1) {
                        Diccionario_Añadir.PalabrasEsp[inicio.palabras] = PalabraEsp.toLowerCase();
                        Diccionario_Añadir.PalabrasIng[inicio.palabras] = PalabraIng.toLowerCase();
                        JOptionPane.showMessageDialog(null, "La palabra se agrego correctamente");
                        inicio.palabras++;
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo añadir la palabra porque ya Existe");

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo añadir la palabra porque ya se supero el limite de palabras");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Porfavor ingrese una palabra en las dos casillas");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Porfavor ingrese una palabra en las dos casillas");
        }
    }

    public static String[] ListaDePalabrasAtraducir(String Cadena) {
        String[] splits = Cadena.split(",");
        return splits;
    }

    public static String mostrarTraduccion(String[] ListaDePalabras) {
        String CadenaTraducida = "";
        String PalabraTraducida = "";
        String PalabrasSugeridas = "";
        for (int i = 0; i < ListaDePalabras.length; i++) {
            String Palabra = ListaDePalabras[i];
            if ("Español".equals(diccionario.Idioma1.getText())) {
                PalabraTraducida = DevolverPalabraTraducida(Palabra, Diccionario_Añadir.PalabrasEsp, Diccionario_Añadir.PalabrasIng);
            }
            if ("Ingles".equals(diccionario.Idioma1.getText())) {
                PalabraTraducida = DevolverPalabraTraducida(Palabra, Diccionario_Añadir.PalabrasIng, Diccionario_Añadir.PalabrasEsp);
            }

            if (i == 0) {
                CadenaTraducida = PalabraTraducida;
            } else {
                CadenaTraducida = CadenaTraducida + "," + PalabraTraducida;
            }
            //ESTO ES EL OPCIONAL
            String PalabraSugerida = "";
            if ("Español".equals(diccionario.Idioma1.getText())) {
                PalabraSugerida = Procedimientos.PalabraSugerida(Palabra.toLowerCase(), Diccionario_Añadir.PalabrasEsp);
            }
            if ("Ingles".equals(diccionario.Idioma1.getText())) {
                PalabraSugerida = Procedimientos.PalabraSugerida(Palabra.toLowerCase(), Diccionario_Añadir.PalabrasIng);
            }
            if (!PalabraSugerida.equals("")) {
                if (i == 0) {
                    PalabrasSugeridas = PalabraSugerida;
                } else {
                    PalabrasSugeridas = PalabrasSugeridas + "," + PalabraSugerida;
                }
                diccionario.Sugeridolbl.setText("Quizas quisiste decir: " + PalabrasSugeridas);
            } else {
                diccionario.Sugeridolbl.setText("");
            }
        }
        return CadenaTraducida;
    }

    public static String DevolverPalabraTraducida(String Palabra, String[] Buscar, String[] Traducida) {
        String TraduccionDePalabra = "";
        int i = -1;
        while (i < (Buscar.length - 1)) {
            i++;
            if (Palabra.equals(Buscar[i])) {
                TraduccionDePalabra = Traducida[i];
                break;
            } else {
                TraduccionDePalabra = Palabra;
            }
        }
        return TraduccionDePalabra;
    }

    public static String PalabraSugerida(String PalabraIngresada, String[] Buscar) {
        String PalabraSugerida = "";
        for (int i = 0; i < Buscar.length; i++) {
            String palabra = Buscar[i];
            if (palabra != null) {
                String PalabraEnVector = Buscar[i].toLowerCase();
                boolean CantidadDeLetrasDistintasValida = Procedimientos.SoloUnaLetraDistinta(PalabraIngresada, PalabraEnVector);

                if (CantidadDeLetrasDistintasValida) {
                    PalabraSugerida = PalabraEnVector;
                }
            }
        }
        return PalabraSugerida;
    }

    public static boolean SoloUnaLetraDistinta(String PalabraIngresada, String PalabraEnVector) {
        boolean CantidadDeLetrasDistintasValida = false;
        int LetrasDiferentes = 0;
        if (PalabraIngresada.length() == PalabraEnVector.length()) {
            for (int i = 0; i < PalabraIngresada.length(); i++) {
                char LetraIngresada = PalabraIngresada.charAt(i);
                char LetraEnVector = PalabraEnVector.charAt(i);
                if (LetraIngresada != LetraEnVector) {
                    LetrasDiferentes += 1;
                }
            }
            if (LetrasDiferentes == 1) {
                CantidadDeLetrasDistintasValida = true;
            }
        }
        return CantidadDeLetrasDistintasValida;
    }

    public static String CifradoHill(String Traduccion) {
        String PalabraCifrada = "";
        String pr = "";
        int[][] ClaveHill = new int[2][2];
        int[] PalabraEnNumeros = new int[100];
        int[][] MatrizParaCifrar = new int[2][2];
        int[][] MatrizDeNumeros = new int[2][1];

        ClaveHill[0][0] = 4;    //ESTA ES MI MATRIZ CLAVE
        ClaveHill[0][1] = 9;
        ClaveHill[1][0] = 13;
        ClaveHill[1][1] = 18;
        Procedimientos.BuscarÑyComas(Traduccion);
        Traduccion = PalabraSinÑniComa;
        if ((Traduccion.length() % 2) != 0) {
            Traduccion = Traduccion + "x";
            xAdd = true;
        }
        int CantidadDeIteraciones = Traduccion.length() / 2;
        int Iteraciones = 0;

        int[] letrasCifradas = new int[Traduccion.length()];
        contador = 0;
        for (int i = 0; i < Traduccion.length(); i += 2) {
            String x = Traduccion.substring(i, i + 2);
            int[][] matrizNumeroLetras = matrizValorNumericoLetra(x);

            int[] VectorResultanteOperacion = VectorResultante(ClaveHill, matrizNumeroLetras);
            for (int j = 0; j < VectorResultanteOperacion.length; j++) {
                letrasCifradas[contador] = VectorResultanteOperacion[j];
                contador++;
            }
        }

        for (int j = 0; j < letrasCifradas.length; j++) {
            if ("".equals(PalabraCifrada)) {
                PalabraCifrada = BuscarLetraDeNumero(letrasCifradas[j]);
            } else {
                PalabraCifrada = PalabraCifrada + BuscarLetraDeNumero(letrasCifradas[j]);
            }
        }
        return PalabraCifrada;
    }

    public static String DescifradoHill(String Palabra) {
        String PalabraDescifrada = "";
        int[][] ClaveHill = new int[2][2];
        int[] PalabraEnNumeros = new int[100];
        int[][] MatrizParaCifrar = new int[2][2];
        int[][] MatrizDeNumeros = new int[2][1];

        ClaveHill[0][0] = 10;    //ESTA ES MI MATRIZ CLAVE
        ClaveHill[0][1] = 21;
        ClaveHill[1][0] = 13;
        ClaveHill[1][1] = 8;
        int CantidadDeIteraciones = Palabra.length() / 2;
        int Iteraciones = 0;

        int[] letrasCifradas = new int[Palabra.length()];
        contador = 0;
        for (int i = 0; i < Palabra.length(); i += 2) {
            String x = Palabra.substring(i, i + 2);
            int[][] matrizNumeroLetras = matrizValorNumericoLetra(x);

            int[] VectorResultanteOperacion = VectorResultante(ClaveHill, matrizNumeroLetras);
            for (int j = 0; j < VectorResultanteOperacion.length; j++) {
                letrasCifradas[contador] = VectorResultanteOperacion[j];
                contador++;
            }
        }

        for (int j = 0; j < letrasCifradas.length; j++) {
            if ("".equals(PalabraDescifrada)) {
                PalabraDescifrada = BuscarLetraDeNumero(letrasCifradas[j]);
            } else {
                PalabraDescifrada = PalabraDescifrada + BuscarLetraDeNumero(letrasCifradas[j]);
            }
        }

            for (int i = 0; i <= ContComas; i++) {

                if ("y".equals(PalabraDescifrada.substring(Comas[i],Comas[i]+1))) {
                    PalabraDescifrada = PalabraDescifrada.substring(0, Comas[i]) + "," + PalabraDescifrada.substring(Comas[i] + 1, PalabraDescifrada.length());
                }
            }
            for (int i = 0; i <= ContEñes; i++) {
            if ("z".equals(PalabraDescifrada.substring(Eñes[i],Eñes[i]+1))) {
                    PalabraDescifrada = PalabraDescifrada.substring(0, Eñes[i]) + "ñ" + PalabraDescifrada.substring(Eñes[i] + 1, PalabraDescifrada.length());
                }
        }
        
        return PalabraDescifrada;
    }

    public static int[][] matrizValorNumericoLetra(String letras) {
        int[][] matriz = new int[2][1];
        for (int i = 0; i < letras.length(); i++) {
            String letraBuscar = String.valueOf(letras.charAt(i));
            int valorNumeroLetra = BuscarNumeroDeLetra(letraBuscar);
            matriz[i][0] = valorNumeroLetra;
        }
        return matriz;
    }

    public static int[] VectorResultante(int[][] ClaveHill, int[][] matrizValorNumericoLetra) {
        int[] resultante = new int[2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 1; j++) {
                for (int k = 0; k < 2; k++) {
                    resultante[i] += ClaveHill[i][k] * matrizValorNumericoLetra[k][j];
                }
                resultante[i] = resultante[i] % 26;
            }
        }
        return resultante;
    }

    public static int BuscarNumeroDeLetra(String Letra) {
        int Valor;
        switch (Letra) {

            case "a":

                Valor = 0;

                break;

            case "b":

                Valor = 1;

                break;
            case "c":

                Valor = 2;

                break;
            case "d":

                Valor = 3;

                break;

            case "e":

                Valor = 4;

                break;
            case "f":

                Valor = 5;

                break;
            case "g":

                Valor = 6;

                break;

            case "h":

                Valor = 7;

                break;
            case "i":

                Valor = 8;

                break;
            case "j":

                Valor = 9;

                break;

            case "k":

                Valor = 10;

                break;
            case "l":

                Valor = 11;

                break;

            case "m":

                Valor = 12;

                break;

            case "n":

                Valor = 13;

                break;
//            case "ñ":
//
//                Valor = 14;
//
//                break;
            case "o":

                Valor = 14;

                break;

            case "p":

                Valor = 15;

                break;
            case "q":

                Valor = 16;

                break;
            case "r":

                Valor = 17;

                break;

            case "s":

                Valor = 18;

                break;
            case "t":

                Valor = 19;

                break;
            case "u":

                Valor = 20;

                break;

            case "v":

                Valor = 21;

                break;
            case "w":

                Valor = 22;

                break;
            case "x":

                Valor = 23;

                break;

            case "y":

                Valor = 24;

                break;
            case "z":
                Valor = 25;
                break;
//            case ",":
//                Valor = 26;
//                break;
            default:

                Valor = 0;

                break;
        }
        return Valor;
    }

    public static String BuscarLetraDeNumero(int Numero) {
        String Valor;
        switch (Numero) {

            case 0:

                Valor = "a";

                break;

            case 1:

                Valor = "b";

                break;
            case 2:

                Valor = "c";

                break;
            case 3:

                Valor = "d";

                break;

            case 4:

                Valor = "e";

                break;
            case 5:

                Valor = "f";

                break;
            case 6:

                Valor = "g";

                break;

            case 7:

                Valor = "h";

                break;
            case 8:

                Valor = "i";

                break;
            case 9:

                Valor = "j";

                break;

            case 10:

                Valor = "k";

                break;
            case 11:

                Valor = "l";

                break;

            case 12:

                Valor = "m";

                break;

            case 13:

                Valor = "n";

                break;
//            case 14:
//
//                Valor = "ñ";
//
//                break;
            case 14:

                Valor = "o";

                break;

            case 15:

                Valor = "p";

                break;
            case 16:

                Valor = "q";

                break;
            case 17:

                Valor = "r";

                break;

            case 18:

                Valor = "s";

                break;
            case 19:

                Valor = "t";

                break;
            case 20:

                Valor = "u";

                break;

            case 21:

                Valor = "v";

                break;
            case 22:

                Valor = "w";

                break;
            case 23:

                Valor = "x";

                break;

            case 24:

                Valor = "y";

                break;
            case 25:
                Valor = "z";
                break;
//            case 26:
//                Valor = ",";
//                break;
            default:

                Valor = "a";

                break;
        }

        return Valor;
    }

    static String cifrarvigenere(String palabras, String clave) {
        String c = "";
        int cont = 0;
        char[] pa = new char[100];
        char[] cl = new char[100];
        char[][] matriz = new char[100][100];
        char[] re = new char[100];

        pa = cstringvector(palabras.toLowerCase());
        cl = clavevector(clave, pa.length);
        matriz = generarMatrizABC();
        re = cifrar(pa, cl, matriz);
        return c = cf(re, palabras);
    }

    static char[] cstringvector(String p) {
        int cont = 0;
        char[] vector = new char[100];
        for (int i = 0; i < p.length(); i++) {

            if (p.substring(i, i + 1).equals(" ") || p.substring(i, i + 1).equals(",")) {

            } else {
                vector[cont] = p.substring(i, i + 1).charAt(0);

                cont++;
            }
        }
        char[] vector2 = new char[cont];
        for (int i = 0; i < cont; i++) {
            vector2[i] = vector[i];

        }

        return vector2;
    }

    static char[] clavevector(String clave, int p) {

        int cont = 0;
        char[] vector = new char[100];
        for (int i = 0; i < clave.length(); i++) {

            if (clave.substring(i, i + 1).equals(" ")) {

            } else {
                vector[cont] = clave.substring(i, i + 1).charAt(0);

                cont++;
            }
        }
        cont = 0;
        char[] vector2 = new char[p];
        for (int i = 0; i < p; i++) {
            vector2[i] = vector[cont];
            cont++;

            if (cont == clave.length()) {

                cont = 0;

            }

        }

        return vector2;

    }

    static public char[] generarAbecedario() {
        char[] abc = new char[26];
        for (int i = 97; i <= 122; i++) {

            abc[i - 97] = (char) i;

        }

        return abc;

    }

    static public char[][] generarMatrizABC() {
        int contador;

        char abcTemp[] = generarAbecedario();

        char abc[] = new char[abcTemp.length * 2];

        for (int c = 0; c < 26; c++) {

            abc[c] = abcTemp[c];

            abc[c + 26] = abcTemp[c];

        }

        char[][] matriz = new char[26][26];
        for (int i = 0; i < 26; i++) {

            contador = 0;

            for (int j = 0; j < 26; j++) {
                matriz[i][j] = abc[contador + i];

                contador++;

            }

        }

        return matriz;

    }

    static public char[] cifrar(char palabra[], char clave[], char m[][]) {

        char[] cifrado = new char[palabra.length];

        int i;

        int j;

        for (int cont = 0; cont < palabra.length; cont++) {

            //Primero calculamos el indice de cada matriz "i" y "j"
            //el indice "i" correspondera al arreglo del mensaje
            //el indice "j" correspondera al arreglo de la clave
            //luego ejecutaremos el calculo para cifrar utilizando "i" y "j" como cordenadas de la matriz
            i = (int) palabra[cont] - 97; //restamos 97 para pasar de codigo ascii a un numero entero

            j = (int) clave[cont] - 97;

             cifrado[cont] = m[i][j];

        }

        //for(int k = 0;k<26;k++)
        //  System.out.println(this.matriz[k]);
        return cifrado;

    }

    static public String cf(char re[], String p) {
        String c = "";
        int cont = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.substring(i, i + 1).equals(" ")) {
                c = c + " ";
            }
            if (p.substring(i, i + 1).equals(",")) {
                c = c + ",";
            } else {
                c = c + re[cont];
                cont++;
            }
        }
        return c;
    }

    static public void solonumeros(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();

                }
            }
        });
    }

    static public void quitarspacio(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c == KeyEvent.VK_SPACE) {
                    e.consume();

                }
            }
        });
    }

    static public void quitarspacio2(JTextArea a) {
        a.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c == KeyEvent.VK_SPACE) {
                    e.consume();

                }
            }
        });
    }

    static public void sololetras2(JTextArea a) {
        a.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)) {
                    e.consume();

                }
            }
        });
    }

    static public void sololetras(JTextField a) {
        a.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
    }

    static public char[] Descifrado(char[] a, char[] k) {
        int[] C = new int[a.length];
        int[] K = new int[a.length];
        int tamK = k.length, cont = 0;
        char[] resul = a;
        char[] clave = k;
        String r;
        int c = -666;
        for (int i = 0; i < resul.length; i++) {
            C[i] = alfabeto.numero(String.valueOf(resul[i]));
            if (cont == tamK) {
                cont = 0;
            }
            K[i] = alfabeto.numero(String.valueOf(clave[cont]));
            if (C[i] == -666 || K[i] == -666) {
                resul[i] = resul[i];
                cont--;
            } else {
                if (C[i] == -555) {
                    c = -555;
                    cont--;
                } else {
                    c = (C[i] - K[i]) % 26;
                    if (c < 0) {
                        c = c + 26;
                    }
                }
                r = alfabeto.letra(c);
                resul[i] = r.charAt(0);
            }
            cont++;
        }

        return resul;
    }

    static public String decivigenre(String palabras, String clave) {
        String pas = "";

        char[] pa = new char[100];
        char[] cl = new char[100];
        char[][] matriz = new char[100][100];
        char[] re = new char[100];
        char[] v = new char[100];
        pa = cstringvector(palabras.toLowerCase());
        cl = clavevector(clave, pa.length);
        v = Descifrado(pa, cl);
        pas = cf(v, palabras);

        return pas;
    }

    public static void BuscarÑyComas(String Palabra) {
        ContEñes = -1;
        ContComas = -1;

        int Tam = Palabra.length();

        for (int i = 0; i < Tam; i++) {
            if ("ñ".equals(Palabra.substring(i, i + 1))) {
                ContEñes++;
                Eñes[ContEñes] = i;
                Palabra = Palabra.substring(0, i) + "z" + Palabra.substring(i + 1, Tam);//Z=Ñ

            }
            if (",".equals(Palabra.substring(i, i + 1))) {
                ContComas++;
                Comas[ContComas] = i;
                Palabra = Palabra.substring(0, i) + "y" + Palabra.substring(i + 1, Tam);//Y=,

            }
        }
        PalabraSinÑniComa = Palabra;
        System.out.println(Palabra);
        System.out.println(Palabra.length() + "tamaño");
    }
    public static int[] Eñes = new int[100];
    public static int[] Comas = new int[100];
    public static int ContEñes;
    public static String PalabraSinÑniComa;
    public static int ContComas;

}
