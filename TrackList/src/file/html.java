/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package file;

/**
 *
 * @author josue
 */
public class html extends Files{
    
    private String arq;
    
    public html(String diretory, String texto){
        
        super(diretory + ".csv");
        super.Clear();
        super.Write(texto);
        
        this.arq = diretory + ".htm";
        
    }//html(String diretory, String texto)
    
    public String Export(){
        
        return "Arquivo \"" + arq + "\" criado!";
        
    }//Export()
    
    public String Ready(String split){
        
        String txt = "";
        
        if(super.Max() >= 0){
            
            for(int i = 0; i < super.Max(); i++){
                
                if(i > 0){
                    
                    txt += split;
                    
                }//if(i > 0)
                
                txt += super.getLine(i);
                
            }//for(int i = 0; i < super.Max(); i++)
            
        }//if(super.Max() >= 0)
        
        return txt;
        
    }//Ready(String split)
    
}//html