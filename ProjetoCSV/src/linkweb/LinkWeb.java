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
        
        String set_title = "Projeto atualizado ";
        set_title += Registro.upgrade.DataLinha(false);
        set_title += "!";
        
        new Index().Enter(set_title);
        
    }
    
}
