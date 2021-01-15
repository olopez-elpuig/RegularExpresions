package com.company;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static String[] serie1 = {"\\*<]:-DOo", ">:o\\)", "[^\\*]<]:-D"};
    private static String[] serie2 = {"*<]:-DOo", ">:o)", "<]:-D"};
    private static int[] encontrado = new int[3];
    private static String[] nombre = {"Pare Noel", "Ren", "Follets"};
    private static File file = new File(System.getProperty("user.home") + "/Escritorio/santako.txt");

    public static void main(String[] args) throws IOException {
        sinRegularExpression();
    }

    //Regular Expresion
    private static void regularExpression() throws IOException {
        BufferedReader bR = new BufferedReader(new FileReader(file));

        Pattern pattern;
        Matcher matcher;

        String linea = bR.readLine();
        while (linea != null) {
            int x = 0;
            resetArray();
            for (String sequencia : serie1) {
                pattern = Pattern.compile(sequencia);
                matcher = pattern.matcher(linea);
                while (matcher.find()) {
                    encontrado[x]++;
                }
                x++;
            }
            resultados(encontrado);
            linea = bR.readLine();
        }
        bR.close();
    }

    // Sin Regular Expresion
    private static void sinRegularExpression() throws IOException {
        BufferedReader bR = new BufferedReader(new FileReader(file));
        String line = bR.readLine();
        while (line != null) {
            resetArray();
            int x = 0;
            for (String serie : serie2) {
                for (int i = 0; i < line.length(); i++) {
                    if (i < line.length() - 1) {
                        for (int j = 0; j < serie.length(); j++) {
                            if (line.charAt(i + j) != serie.charAt(j)) {
                                break;
                            }
                            if (j == serie      .length() - 1) {
                                encontrado[x]++;
                            }
                        }
                    }
                }
                x++;
            }
            encontrado[2] -= encontrado[0];
            resultados(encontrado);
            line = bR.readLine();
        }

        bR.close();
    }

    private static void resetArray() {
        for (int i = 0; i < encontrado.length; i++) {
            encontrado[i] = 0;
        }
    }

    private static void resultados(int[] encontrado) {
        for (int i = 0; i < encontrado.length; i++) {
            if (encontrado[i] > 0) {
                System.out.print(nombre[i] + "(" + encontrado[i] + ") ");
            }
        }
        System.out.println("");
    }
}
