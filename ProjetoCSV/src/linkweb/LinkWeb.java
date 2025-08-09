/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package linkweb;

import form.Index;
import model.Registro;

/**
 *
 * @author josue
 */
public class LinkWeb {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.print("GitHub:");
        System.out.print(Registro.Tab("GitHub:",Registro.result));
        System.out.print("\"");
        System.out.print(Registro.github);
        System.out.println("\"");
        
        System.out.print("Projeto:");
        System.out.print(Registro.Tab("Projeto:",Registro.result));
        System.out.println("\"ProjetoCSV\"");
        
        System.out.println(Registro.Agora(true));
        
        System.out.println();
        
        System.out.print("Esse projeto foi atualizado ");
        System.out.print(Registro.upgrade.DataLinha(false));
        System.out.println("!");
        
        new Index().Enter(false);
        
    }
    
}

/*public class LinkWeb {

    public static void main(String[] args) {
        
        final String msg = "Não compactar esse arquivo!\n\nMotivo:\n[..\\..\\] ao invés de [..\\]";
        
        System.out.println(msg);
        
        new Index().Enter(msg);
        
    }
    
}*/