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
        Registro.Tab(true,"GitHub:",Registro.result);
        System.out.print("\"");
        System.out.print(Registro.github);
        System.out.println("\"");
        
        System.out.print("Projeto:");
        Registro.Tab(true,"Projeto:",Registro.result);
        System.out.println("\"ProjetoCSV\"");
        
        System.out.print("Criação:");
        Registro.Tab(true,"Criação:",Registro.result);
        System.out.println(Registro.create.DataLinha(true));
        
        System.out.print("Última atualização:");
        Registro.Tab(true,"Última atualização:",Registro.result);
        System.out.println(Registro.upgrade.DataLinha(true));
        new Index().Enter(false);
        
    }
    
}
