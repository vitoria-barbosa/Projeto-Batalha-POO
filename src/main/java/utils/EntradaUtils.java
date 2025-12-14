package utils;

import exceptions.ValidacaoEntrada;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EntradaUtils {

    static Scanner sc = new Scanner(System.in);

    public static int receberInteiro(String texto) throws ValidacaoEntrada{
        try {
            System.out.println(texto);
            return sc.nextInt();
        } catch (InputMismatchException e){
            limparBuffer();
            throw new ValidacaoEntrada("Erro: Entrada inteira inválida.");
        }
    }

    public static String receberString(String texto) throws ValidacaoEntrada{
        System.out.println(texto);
        String entrada = sc.nextLine();

        if(entrada.trim().isEmpty()){
            throw new ValidacaoEntrada("Erro: Entrada vazia.");
        }

        return entrada;
    }

    public static void limparBuffer(){
        if(sc.hasNextLine()){
            sc.nextLine();
        }
    }

    public static void enterPraContinuar(){
        System.out.println("\nEnter para a próxima ação...");
        sc.nextLine();
    }
}
