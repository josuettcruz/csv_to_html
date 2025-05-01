/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TrackList;

import form.Tela;
import file.Registro;

/**
 *
 * @author josue
 */
public class TrackList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        final int result = 35;
        
        System.out.print("GitHub:");
        Registro.Tab("GitHub:",result);
        System.out.println(Registro.github);
        
        System.out.print("Projeto:");
        Registro.Tab("Projeto:",result);
        System.out.println("TrackList");
        
        System.out.print("Última atualização:");
        Registro.Tab("Última atualização:",result);
        System.out.println(Registro.upgrade.DataLinha(true));
        
        System.out.println();
        
        new Tela().Enter(true);
        
    }
    
}
