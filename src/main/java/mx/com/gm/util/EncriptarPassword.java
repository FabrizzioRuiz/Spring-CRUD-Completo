
package mx.com.gm.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * Clase para generar los password que vayamos utilizar.
 * 
 * @author fabri
 */

public class EncriptarPassword {
    public static void main(String[] args) {
       
        var password = "123";
        System.out.println("password = " + password);
        System.out.println("password = " + encriptarPassword(password));
        
    }
    
    public static String encriptarPassword(String password){
         BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
         return encoder.encode(password);
    }
}
