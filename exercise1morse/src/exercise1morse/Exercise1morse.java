/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise1morse;

import static java.sql.DriverManager.println;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author CCITUC
 */
public class Exercise1morse {
    
    //Array of values for leters and numbers
    static final Character[] arrayCharacters = {
        'A','B','C','D','E','F','G','H','I','J','K','L','M',
        'N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
        '1','2','3','4','5','6','7','8','9','0'
    };

    //Array of values for morse code
    static final String[] arrayMorseCode = {
        ".-" ,"-...","-.-.","-..",".","..-.","--.","....",
        "..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...",
        "-","..-","...-",".--","-..-","-.--","--..",
        ".----","..---","...--","....-",".....",
        "-....","--...","---..","----.","-----"
    };

    //Parse arrays into a list to later use search methods
    static final List<Character> listCharacters = Arrays.asList(arrayCharacters);
    static final List<String> listMorseCodes =  Arrays.asList(arrayMorseCode);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Indicate if is a valid option selected
        boolean invalidOption = true;
        
        //Print instructions
        System.out.println(
            "Bienvenido\n"+
            "Este es un programa que convierte texto a código morse "+
                "y código morse a texto\n\n"+
                    
            "La sintaxis admitida es la siguiente: \n" +
                "Cada letra (en morse) deberár ser separada con un espacio," +
                    " por ejemplo: HOY=…. --- -.--\n" +
                "Cada palabra (en morse) deberá ser separada por 3 espacios," +
                    " por ejemplo HOY ES =…. --- -.-   . …\n\n" +
            "------- Menú de opciones -------\n"+
            "A continuación el menú de opciones:\n"+
                "Presione \"1\" si desea convertir el texto a código morse\n"+
                "Presione \"2\" si desea convertir código morse a texto\n" +
                "Seguidamente presione la tecla \"Enter\""
                    
        );
        
        
        
        //This cycle is for the user to try to enter a valid value infinitely many time
        do {
            try{
                System.out.print(
                    "Seleccione una opción: "
                );
                //Get value introduce by user
                Scanner scanner = new Scanner(System.in);
                String option = scanner.nextLine();
                int numOption;

                numOption = Integer.parseInt(option);            
                
                //Switch for options menu
                switch (numOption) {
                case 1://Option text to morse code
                    System.out.print(
                        "------- Ingresar datos -------\n"+
                        "Introduzca el texto: "
                    );
                    String text = scanner.nextLine();
                    System.out.println(
                        "------- Resultados -------\n"+
                        "Código morse: " + convertTextToMorse(text)
                    );
                    invalidOption = false;
                    break;
                case 2://Option morse code to text
                    System.out.print(
                        "------- Ingresar datos -------\n"+
                        "Introduzca el código morse: ");
                    String morseCode = scanner.nextLine();
                    System.out.println(
                        "------- Resultados -------\n"+
                        "Texto decodificado: " + convertMorseToText(morseCode)
                    );
                    invalidOption = false;
                    break;
                default://For invalid option
                    System.out.println("Opción no válida\n");
                    break;
                }
                //For invalid option as string value
            }catch(java.lang.NumberFormatException e){
                System.out.println("Opción no válida\n");
            }
            
        } while (invalidOption);
        
    }
    
    static String convertTextToMorse(String text){
        //Get the initial value entered
        String originalText = text.toUpperCase(); 
        //Initialize converted text variable
        String morseCode = "";
        
        //Go through the whole text
        for(int i = 0; i < originalText.length(); i++){
            if(originalText.charAt(i) == ' '){
                //Set 3 spaces when there is 1 space
                morseCode += "   ";
            } else {
                try{
                    morseCode += listMorseCodes.get(//Get morse by index
                        //Get index of char
                        listCharacters.indexOf(originalText.charAt(i))
                    );
                } catch (java.lang.ArrayIndexOutOfBoundsException e){
                    //If there is any invalid char, set "#" char
                    morseCode += "#";
                }
                
                //Varified if is the last iteration and check if the next char
                //is not space
                if(i != (originalText.length()-1) &&
                    originalText.charAt(i+1) != ' '){
                    morseCode += " ";//Set 1 space
                }                
            }
        }
        
        return morseCode;
    }
    
    static String convertMorseToText(String morseCode){
        //Get the initial value entered
        String originalMorseCode = morseCode; 
        //Initialize converted text variable
        String convertedText = "";
        
        //This store the complete morse code
        String uniqueMorseCode = "";
        //Indicate if the morse code is complete
        boolean addConvertedText = false;
        
        //Go through the whole text
        for(int i = 0; i < originalMorseCode.length(); i++){ 
            
            //Verify if is not the last iteration
            if(i != originalMorseCode.length() - 1){
                //If they are three characters of space
                if(originalMorseCode.charAt(i) == ' ' && 
                    originalMorseCode.charAt(i+1) == ' ' &&
                    originalMorseCode.charAt(i-1) == ' '){
                    convertedText += " ";//Set a single space
                }
            }
            
            //If is not the last iteration and is not a space char
            if(i != (originalMorseCode.length()-1) && originalMorseCode.charAt(i) != ' '){
                //Add the current char to string morse code
                uniqueMorseCode += originalMorseCode.charAt(i);   

                //If the next char is not an space
                if(originalMorseCode.charAt(i+1) == ' '){                          
                    //Set true to indicate that is a completed morse code
                    addConvertedText = true;
                }
            //If is the last iteration and is not an space
            } else if (i == (originalMorseCode.length()-1) && originalMorseCode.charAt(i) != ' '){
                //Add the current char to string morse code
                uniqueMorseCode += originalMorseCode.charAt(i); 
                //Set true to indicate that is a completed morse code
                addConvertedText = true;
            }

            //If is a completed morse code
            if(addConvertedText){

                try{
                    convertedText += listCharacters.get(//Get char by index
                        //Get index of morse code
                        listMorseCodes.indexOf(uniqueMorseCode)
                    );
                    uniqueMorseCode = "";

                } catch (java.lang.ArrayIndexOutOfBoundsException e){
                    //If there is any invalid morse code, set "#" char
                    convertedText += "#";
                }
                
                //Reset indicator
                addConvertedText = false;
                //Reset completed morse code to store a new value
                uniqueMorseCode = "";
            }
        
        }
        return convertedText;
    }
}
