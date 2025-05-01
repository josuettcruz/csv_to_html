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
        Registro.Tab("GitHub:",Registro.result);
        System.out.println(Registro.github);
        
        System.out.print("Projeto:");
        Registro.Tab("Projeto:",Registro.result);
        System.out.println("ProjetoCSV");
        
        System.out.print("Criação:");
        Registro.Tab("Criação:",Registro.result);
        System.out.println(Registro.upgrade.DataLinha(true));
        
        /* Criação: 01/05/2025 **
        System.out.print("Última atualização:");
        Registro.Tab("Última atualização:",Registro.result);
        System.out.println(Registro.upgrade.DataLinha(true));
        /* Criação: 01/05/2025 */
        
        String set_title = "Projeto de ";
        set_title += Registro.upgrade.DataLinha(false);
        set_title += "!";
        
        new Index().Enter(set_title);
        
    }
    
}
