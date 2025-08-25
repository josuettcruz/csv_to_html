/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josue
 */
public class Registro {
    
    public static String github = "https://github.com/josuettcruz/csv_to_html";
    
    public static Data upgrade = new Data(LocalDate.of(2025, 8, 25));
    
    public static int result = 28;
    
    public static String Tab(String entrada, int tab){
        
        int pg = tab - entrada.length() > 1 ? tab - entrada.length() : 1;
        
        return " ".repeat(pg);
        
    }// Tab(String entrada, int tab)
    
    public static boolean Link(String link){
        
        final String href = "http";
        
        final int qtd = href.length();
        
        boolean http;
        
        if(link.length() > qtd){
            
            http = link.substring(0,qtd).contains(href);
            
        } else {
            
            http = false;
            
        }
        
        boolean protocol = link.contains("://");
        
        return http && protocol && !link.contains(" ") && link.contains("/");
        
    }//Link(String link)
    
    public static String Title(String text, String separator){
        
        boolean ifo = false;
        
        String txt = "";
        
        String document_text[] = text.split(" ");
        
        List<String> document = new ArrayList<>();
        
        for(String ds : document_text){
            
            String table = ds.replaceAll("\'", "").replaceAll("\"", "");
            
            if(table.contains("/")){
                
                String inline[] = table.split("/");
                
                for(String ins : inline){
                    
                    if(!ins.isBlank()){
                        
                        document.add(ins);
                        
                    }//if(!ins.isBlank())
                    
                }//for(String ins : inline)
                
            } else if(table.contains("&")){
                
                String inline[] = table.split("&");
                
                for(String ins : inline){
                    
                    if(!ins.isBlank()){
                        
                        document.add(ins);
                        
                    }//if(!ins.isBlank())
                    
                }//for(String ins : inline)
                
            } else if(table.contains("-")){
                
                String inline[] = table.split("-");
                
                for(String ins : inline){
                    
                    if(!ins.isBlank()){
                        
                        document.add(ins);
                        
                    }//if(!ins.isBlank())
                    
                }//for(String ins : inline)
                
            } else if(table.contains("_")){
                
                String inline[] = table.split("_");
                
                for(String ins : inline){
                    
                    if(!ins.isBlank()){
                        
                        document.add(ins);
                        
                    }//if(!ins.isBlank())
                    
                }//for(String ins : inline)
                
            } else if(table.contains("&")){
                
                String inline[] = table.split("&");
                
                for(String ins : inline){
                    
                    if(!ins.isBlank()){
                        
                        document.add(ins);
                        
                    }//if(!ins.isBlank())
                    
                }//for(String ins : inline)
                
            } else if(table.length() > 0){
                
                document.add(table);
                
            }//if(ds.length() > 0)
            
        }//for(String ds : document_text)
        
        for(int x = 0; x < document.size(); x++){
            
            boolean select;
            
            if(x == 0){
                
                select = true;
                
            } else {//if(x == 0)
                
                txt += " ";
                
                switch(document.get(x).toLowerCase()){
                    
                    case "se":
                    case "no":
                    case "na":
                    case "do":
                    case "de":
                    case "da":
                    case "dos":
                    case "das":
                    case "nós":
                    case "nos":
                    case "nas":
                    case "aos":
                    case "lhe":
                    case "lhes":
                    case "lha":
                    case "lhas":
                    case "ao":
                    case "os":
                    case "as":
                    case "ás":
                    case "às":
                    txt += document.get(x).toLowerCase();
                    select = false;
                    break;

                    default:
                    select = true;
                    break;

                }//switch(document.get(x).toLowerCase())
                
            }//if(x == 0)
            
            if(document.get(x).contains("[") || document.get(x).contains("(") || document.get(x).contains("{")){
                
                txt += separator;
                
                txt += "[";
                
                ifo = true;
                
            }
            
            //select == true;
            
            if(ifo){
                
                String dote = "";
                
                boolean dao = true;
                
                for(int p = 0; p < document.get(x).length(); p++){
                    
                    switch(document.get(x).charAt(p)){
                        
                        case '[':
                        case '(':
                        case '{':
                        case ']':
                        case ')':
                        case '}':
                        if(p > 1){dote += " ";}
                        dao = false;
                        break;
                        
                        case ' ':
                        if(p > 1 && dao){dote += " ";dao = false;}
                        break;
                        
                        default:
                        dote += document.get(x).charAt(p);
                        dao = true;
                        break;
                        
                    }
                    
                }//for(int p = 0; p < document.get(x).length(); p++)

                txt += dote.toUpperCase();
            
            } else if(select){//if(ifo)
            
                switch(document.get(x).length()){

                    case 1 ->{

                        if(x == 0){
                            txt += document.get(x).toUpperCase();
                        } else {
                            txt += document.get(x).toLowerCase();
                        }

                    }//case 1

                    default ->{

                        for(int y = 0; y < document.get(x).length(); y++){

                            String dg = document.get(x).charAt(y) + "";

                            if(y == 0){
                                txt += dg.toUpperCase();
                            } else {
                                txt += dg.toLowerCase();
                            }

                        }//for(int i = 0; i < ds.length(); i++)

                    }//default

                }//switch(ds.length())

            }//for(String ds : document)
            
        }//if(select)
        
        if(ifo){
            txt += "]";
        }
        
        return txt;
        
    }//Title(String text)
    
    /*public static String Select(String text, String opc, int max){
        
        boolean word_null = true;
        int letter = 0;
        
        String txt = "";
        
        String space[] = text.split(" ");
        
        List<String> doc = new ArrayList<>();
        
        for(String sp : space){if(sp.length() > 1){doc.add(sp);}}
        
        for(int i = 0; i < doc.size(); i++){
            
            letter = letter + doc.get(i).length();
            
            if(i > 0){
                txt += " ";
            }
            
            if(letter <= max){
                
                txt += doc.get(i);
                word_null = false;
                
            } else if(word_null){//if(letter <= max)
                
                int dig = max - letter;
                
                txt += doc.get(i).substring(0, dig);
                txt += "...";
                break;
                
            } else {//if(letter <= max)
                
                txt += "...";
                break;
                
            }//if(letter <= max)
            
        }//for(int i = 0; i < doc.size(); i++)
        
        if(text.trim().isBlank()){
            return opc.trim();
        } else {
            return txt;
        }
        
    }/*Select(String text, String opc, int max)*/
    
    public static String Select(String text, int max){
        
        boolean word_null = true;
        int letter = 0;
        
        String txt = "";
        
        String space[] = text.split(" ");
        
        List<String> doc = new ArrayList<>();
        
        for(String sp : space){if(!sp.isBlank()){doc.add(sp);}}
        
        for(int i = 0; i < doc.size(); i++){
            
            letter = letter + doc.get(i).length();
            
            if(letter <= max){
                
                if(i > 0){txt += " ";}
                
                txt += doc.get(i);
                word_null = false;
                
            } else if(word_null){//if(letter <= max)
                
                int dig = max - letter;
                
                txt += doc.get(i).substring(0, dig);
                txt += "...";
                break;
                
            } else {//if(letter <= max)
                
                txt += "...";
                break;
                
            }//if(letter <= max)
            
        }//for(int i = 0; i < doc.size(); i++)
        
        return txt;
        
    }//Select(String text, int max)
    
    public static String Agora(boolean index){
        
        int a = LocalDate.now().getYear();
        int m = LocalDate.now().getMonthValue();
        int d = LocalDate.now().getDayOfMonth();
        int s = LocalDate.now().getDayOfWeek().getValue();
        
        String txt = "";
        
        final String comp[] = {"Bom Dia", "Boa Tarde", "Boa Noite"};
        
        if(LocalTime.now().getHour() < 12){
            txt = comp[0];
        } else if(LocalTime.now().getHour() < 18){
            txt = comp[1];
        } else if(LocalTime.now().getHour() > 18){
            txt = comp[2];
        } else if(LocalTime.now().getMinute() <= 30){
            txt = comp[1];
        } else {
            txt = comp[2];
        }
        
        txt += "!";
        
        if(index){
            
            txt += Registro.Tab(txt, Registro.result);
            txt += "\"";
            
        } else {//if(index)
            
            txt += " ";
            
        }//if(index)
        
        txt += "Hoje é ";
        
        
        switch(s){
            
            case 1 ->{
                txt += "Segunda";
            }
            
            case 2 ->{
                txt += "Terça";
            }
            
            case 3 ->{
                txt += "Quarta";
            }
            
            case 4 ->{
                txt += "Quinta";
            }
            
            case 5 ->{
                txt += "Sexta";
            }
            
            case 6 ->{
                txt += "Sábado";
            }
            
            case 7 ->{
                txt += "Domingo";
            }
            
        }//switch(s)
        
        if(s < 6){
            
            txt += "-feira";
            
        }
        
        txt += ", dia ";
        
        txt += d;
        
        if(d == 1){
            txt += "º";
        }
        
        txt += " de ";
        
        switch(m){
            
            case 1 ->{
                txt += "Janeiro";
            }
            
            case 2 ->{
                txt += "Fevereiro";
            }
            
            case 3 ->{
                txt += "Março";
            }
            
            case 4 ->{
                txt += "Abril";
            }
            
            case 5 ->{
                txt += "Maio";
            }
            
            case 6 ->{
                txt += "Junho";
            }
            
            case 7 ->{
                txt += "Julho";
            }
            
            case 8 ->{
                txt += "Agosto";
            }
            
            case 9 ->{
                txt += "Setembro";
            }
            
            case 10 ->{
                txt += "Outubro";
            }
            
            case 11 ->{
                txt += "Novembro";
            }
            
            case 12 ->{
                txt += "Dezembro";
            }
            
        }//switch(m)
        
        txt += " de ";
        
        txt += a;
        
        
        
        if(index){//if(index)
            
            txt += "\"";
            
        } else {//if(index)
            
            txt += "!";
            
        }//if(index)
        
        return txt;
        
    }//Agora()
    
    public static String Select(String text){
        
        String txt = "";
        
        String phease[] = text.split(" ");
        
        boolean space = false;
        //boolean div = true;
        
        for(String p : phease){
            
            if(space/* && div*/){
                
                txt += " ";
                space = false;
                
            }//if(space)
            
            /*if(p.contentEquals("|")){
                
                if(div){txt += "| ";}
                div = false;
                
            } else */if(!p.isBlank()){
                
                txt += p;
                space = true;
                //div = true;
                
            }//if(!p.isBlank())
            
        }//for(String p : phease)
        
        return txt.trim().replace(";", ".,");
        
    }//Select(String text)
    
}//Registro