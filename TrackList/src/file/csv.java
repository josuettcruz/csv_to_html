/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package file;

import java.util.ArrayList;

/**
 *
 * @author josue
 */
public class csv extends Files{
    
    private String jon;
    private char separator;
    
    private ArrayList<String> text = new ArrayList();
    
    public csv(String title){
        
        super(title);
        
        this.jon = ";";
        this.separator = ';';
        
    }//csv(String title)
    
    public csv(char separator, String title){
        
        super(title);
        
        switch(separator){
            
            case ' ':
            case '\n':
            case ',':
            case '.':
            this.jon = ";";
            this.separator = ';';
            break;
            
            default:
            this.jon = "" + separator;
            this.separator = separator;
            break;
            
        }//switch(separator)
        
    }//csv(char separator, String title)
    
    public void Insert(String [] data){
        
        String txt = "";
        
        for(int i = 0; i < data.length; i++){
            
            if(i > 0){
                
                txt += this.jon;
                
            }//if(i > 0)
            
            txt += data[i].replaceAll(this.jon, " ");
            
        }//for(int i = 0; i < data.length; i++)
        
        super.Write(txt);
        
    }//Insert(String [] data)
    
    public void InsertAll(String[] txt){
        
        String date = "";
        
        for(int x = 0; x < txt.length; x++){
            
            if(x > 0){
                
                date += this.separator;
                
            }
            
            date += txt[x];
            
        }//for(int x = 0; x < txt.length; x++)
        
        this.text.add(date);
        
    }//Add(String txt)
    
    public void InsertAll(){
        
        String[] txt = new String[this.text.size()];
        
        for(int x = 0; x < text.size(); x++){
            
            txt[x] = text.get(x);
            
        }//for(int x = 0; x < text.size(); x++)
        
        super.WriteAll(txt);
        
        this.text.clear();
        
    }//Add(String txt)
    
    public void Remove(int num){
        
        if(num >= 0 && num < super.Max() && super.Max() >= 0){
            
            super.Delete(num);
            
        }
        
    }//Remove(int num)
    
    public void Reply(int num, String [] data){
        
        String txt = "";
        
        if(num >= 0 && num < super.Max() && super.Max() >= 0){
        
            for(int i = 0; i < data.length; i++){

                if(i > 0){

                    txt += this.jon;

                }//if(i > 0)

                txt += data[i].replaceAll(this.jon, " ");

            }//for(int i = 0; i < data.length; i++)
        
        }//if(num >= 0 && num < super.Max() && super.Max() >= 0)
        
        super.Modify(num, txt);
        
    }//Reply(int num, String [] data)
    
    public int Tot(int num){
        
        int numb = -1;
        
        if(num >= 0 && num < super.Max()){
            
            numb = super.getLine(num).split(this.jon).length;
            
        }
        
        return numb;
        
    }
    
    public int Tot(){
        
        return super.Max();
        
    }//Tot()
    
    public String Read(int line, int col){
        
        String txt = "";
        
        int sum = 0;
        
        if(line < super.Max()){
            
            String [] tx = new String[super.getLine(line).split(this.jon).length];
            
            for(int i = 0; i < super.getLine(line).length(); i++){
                
                if(super.getLine(line).charAt(i) == this.separator){
                    
                    sum++;
                    
                } else {
                    
                    tx[sum] += super.getLine(line).charAt(i);
                    
                }//if(super.getLine(line).charAt(i) == this.separator)
                
            }//for(int i = 0; i < super.getLine(line).length(); i++)
            
            if(col < tx.length){
                
                if(tx[col] != null){
                    
                    txt = tx[col].replace("null","");
                    
                }//if(tx[col] != null)
                
            }//if(col < text.length)
            
        }//if(line < super.Max())
        
        return txt;
        
    }//Read()
    
    public void Truncate(){
        
        super.Clear();
        
    }
    
}//Class