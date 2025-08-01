/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form;

import file.*;
import java.util.ArrayList;
import java.util.List;
import model.Data;
import model.Hora;
import model.Numero;
import model.Registro;

public class Exportar {
    
    private csv code;
    
    private final int long_text = 30;
    private final int max_end_separator_paragraphy = 50;
    private final int tribute_max_end_separator_paragraphy = 75;
    
    private final String option[] = {"_blank","_parent"};
    
    private final String target = option[0];
    
    private final String ext[] = {
            "avi",
            "mpg",
            "mp4",
            "mov",
            "tv"
        };
    
    private String host;
    
    private boolean tag;
    private boolean meta;
    private boolean aspas;
    private boolean link;
    private boolean text_long;
    boolean only_canva;
    private final String h1 = "tema";
    private final String p = "texto";
    
    private boolean LongText(String long_text){
                
        boolean doc = new cod().Link(long_text);
        
        return !doc && (long_text.length() > this.long_text) && !long_text.contains("\"");
        
    }
    
    public Exportar(csv code, String sent){
                
        cod doc = new cod();
        
        this.code = code;
        
        this.host = sent;
        
        this.tag = false;
        this.meta = false;
        this.aspas = false;
        this.link = false;
        this.text_long = false;
        this.only_canva = false;
        
        for(int col = 0; col < code.Tot(); col++){
            
            for(int line = 1; line < code.Tot(col); line++){
                
                if(new cod().Link(code.Read(col, line))){
                    
                    this.link = true;
                    
                }// 1 de 2 - boolean link;
                
                if(LongText(code.Read(col, line))){
                    
                    this.text_long = true;
                    
                }// 2 de 2 - boolean text_long;
                
                if(new cod().Link(code.Read(col, line)) && !code.Read(col, line).contains("www.canva.com")){
                    
                    this.only_canva = true;
                    
                }// 2 de 2 - boolean text_long;
                
            }//for(int line = 1; line < code.Tot(col); line++)
            
        }//for(int col = 0; col < code.Tot(); col++)
        
    }//Exportar(csv code, String sent)
    
    private String Reverse(String text){
        
        boolean span = true;
        
        List<String> rew = new ArrayList<>();
        
        for(int r = text.length()-1; r >= 0; r--){
            
            char ds = text.charAt(r);
            
            switch(ds){
                
                case'>' ->{
                    
                    if(span){
                        
                        rew.add("</span>");
                        span = false;
                        
                    } else {//if(span)
                        
                        rew.add(">");
                        
                    }//if(span)
                    
                }//case'>'
                
                default ->{
                    
                    rew.add(ds + "");
                    
                }//default
                
            }//switch(ds)
            
        }//for(int r = text.length()-1; r >= 0; r--)
        
        String txt = "";
        
        for(int letter = rew.size()-1; letter >= 0; letter--){
            
            txt += rew.get(letter);
            
        }//for(int letter = rew.size()-1; letter >= 0; letter--)
        
        return txt;
        
    }//Reverse(String text)
    
    private String Tag(String dig, int note, String divide){
        
        boolean space = true;
        boolean reverse = false;
        
        String txt = "";
        String node = "";
        
        for(int x = 0; x < dig.length(); x++){
            
            char ds = dig.charAt(x);
            
            switch(ds){
                
                case'<' ->{
                    
                    if(this.meta){
                        
                        txt += "<";
                        node += "<";
                        
                    } else {
                        
                        txt += "<span>";
                        node += "<span>";
                        this.meta = true;
                        
                    }
                    
                }//case'<'
                
                case'>' ->{
                    
                    node += ">";
                    
                    if(this.meta){
                        
                        txt += "</span>";
                        
                        this.meta = false;
                        
                    } else {
                        
                        reverse = true;
                        
                    }
                    
                }//case'>'
                
                case'\\' ->{
                    
                    if(this.tag){
                        
                        txt += "\\";
                        node += "\\";
                        
                    } else if(space && note > 0){//if(this.meta)
                        
                        txt += divide;
                        node += divide;
                        
                        space = false;
                        
                    }//if(this.meta)
                    
                }//case'\\'
                
                default ->{
                    
                    txt += ds;
                    node += ds;
                    
                }//default
                
            }//switch(ds)
            
        }//for(int x = 0; x < dig.length(); x++)
        
        return reverse ? Reverse(node) : txt;
        
    }//Tag(String dig)
    
    private String phrase(String dig, int note, String divide){
        
        boolean space = true;
        
        String txt = "";
        
        for(int t = 0; t < dig.length(); t++){
            
            char ds = dig.charAt(t);
            
            switch(ds){
                
                case'\\' ->{
                    
                    if(this.tag){
                        
                        txt += "\\";
                        
                    } else if(space && note > 0){//if(this.meta)
                        
                        txt += divide;
                        
                        space = false;
                        
                    }//if(this.meta)
                    
                }//case'\\'
                
                case'\"' ->{
                    
                    if(this.aspas){
                        
                        txt += "</q>";
                        this.aspas = false;
                    } else {
                        txt += "<q>";
                        this.aspas = true;
                    }
                    
                }//case'\\'
                
                default ->{
                    txt += ds;
                    space = true;
                }
                
            }//switch(ds)
            
        }//for(int t = 0; t < dig.length(); t++)
        
        return txt;
        
    }//phrase(String dig)
    
    private boolean Tx(String text){
        
        int node = 0;
        
        for(int d = 0; d < text.length(); d++){
            
            char ds = text.charAt(d);
            
            switch(ds){
                
                case ')':
                case ']':
                case '}':
                node = 1;
                break;
                
                default:
                if(node == 1){
                    node = 2;
                } else {
                    node = 0;
                }
                break;
                
            }//switch(ds)
            
        }//for(int d = 0; d < text.length(); d++)
        
        return node > 0;
        
    }//Tx(String text)
    
    private String DataHora(String text){
        
        String txt = "";
        
        for(int dh = 0; dh < text.length();dh++){
            
            boolean acept;
            
            switch(text.charAt(dh)){
                
                case '(' ->{acept = false;}
                
                case ')' ->{acept = false;}
                
                case '[' ->{acept = false;}
                
                case ']' ->{acept = false;}
                
                case '{' ->{acept = false;}
                
                case '}' ->{acept = false;}
                
                default ->{acept = true;}
                
            }//switch(text.charAt(dh))
            
            if(acept){txt += text.charAt(dh);}
            
        }//for(int dh = 0; dh < text.length();dh++)
        
        return txt;
        
    }//DataHora(String text)
    
    private String Numb(int num, int max){
        
        if(num < 0){
            num = num - num*2;
        }
        
        String txt = "";
        
        if(max >= 10 && num < 10){txt += "0";}
        if(max >= 100 && num < 100){txt += "0";}
        
        txt += num;
        txt += " de ";
        txt += max;
        
        return txt;
        
    }//Numb(int numb)
    
    private boolean mpeg(String txt){
        
        boolean val = false;
        String text = "";
        
        for(int a = 0; a < txt.length(); a++){
            
            switch(txt.charAt(a)){
                
                case '.' ->{
                    text = "";
                }
                
                default ->{
                    text += txt.charAt(a);
                }
                
            }//switch(txt.charAt(a))
            
        }//for(int a = 0; a < txt.length(); a++)
        
        for(String g : this.ext){
            
            if(g.equalsIgnoreCase(text)){val = true;break;}
            
        }//for(String p : ext)
        
        return val;
        
    }//mpeg(String txt)
    
    private String T(String text, String separator){
        
        String txt = "";
        
        boolean divide = true;
        
        int col = 0;
        
        String live_text[] = text.split(" ");
        
        List<String> dig = new ArrayList<>();
        
        for(String insert : live_text){
            
            if(!insert.isBlank()){
                dig.add(insert);
            }
            
        }//for(String insert : live_text)
        
        for(String tx : dig){
            
            Data d = new Data(DataHora(tx));
            Hora h = new Hora(DataHora(tx));
            
            boolean into_1 = tx.charAt(0) == '(';
            boolean into_2 = tx.charAt(0) == '[';
            boolean into_3 = tx.charAt(0) == '{';
            boolean into = into_1 || into_2 || into_3;
            
            int end_num = tx.length() > 1 ? tx.length()-1 : 0;
            boolean end_1 = tx.charAt(end_num) == '.';
            boolean end_2 = tx.charAt(end_num) == '!';
            boolean end_3 = tx.charAt(end_num) == '?';
            boolean end_4 = tx.charAt(end_num) == ':';
            boolean end_5 = tx.charAt(end_num) == ',';
            boolean end = end_1 || end_2 || end_3 || end_4;
            boolean ended = end_1 || end_2 || end_3 || end_4 || end_5;
            
            String node = "";
            
            switch(col){
                
                case 1 ->{
                    node = " ";
                }
                
                case 2 ->{
                    node = separator;
                }
                
                case 3 ->{
                    node = " - ";
                }
                
            }//switch(col)
            
            if(tx.contains("<") || tx.contains(">")){//if
                
                this.tag = true;
                
                col = 1;
                
                txt += node;
                
                txt += Tag(tx, col, separator);
                
            } else if(tx.equalsIgnoreCase("|")){//if
                
                col = 2;
                divide = false;
                
            } else if(tx.equalsIgnoreCase("-") && col == 1){//if
                
                col = 3;
                
            } else if(d.Val()){//if
                
                if(col > 0){txt += separator;}
                
                col = 2;
                
                txt += d.DataCompleta(separator);
                
            } else if(h.Val() && !h.getNodeHora(true).isBlank()){//if
                
                if(col > 0){txt += separator;}
                
                col = 2;
                
                txt += h.getNodeHora(true);
                
            } else if(divide && into && Tx(tx)){//if
                
                if(col > 0){txt += separator;}
                
                col = 2;
                
                txt += phrase(tx, col, separator);
                
            } else if(divide && into){//if
                
                if(col > 0){txt += separator;}
                
                col = 1;
                
                txt += phrase(tx, col, separator);
                
            } else if(divide && Tx(tx)){//if
                
                txt += node;
                
                col = 2;
                
                txt += phrase(tx, col, separator);
                
            } else if(divide && ended && text.length() >= this.tribute_max_end_separator_paragraphy){//if
                
                txt += node;
                
                col = 2;
                
                txt += phrase(tx, col, separator);
                
            } else if(divide && end && text.length() >= this.max_end_separator_paragraphy){//if
                
                txt += node;
                
                col = 2;
                
                txt += phrase(tx, col, separator);
                
            } else {//if
                
                txt += node;
                
                col = 1;
                
                txt += phrase(tx, col, separator);
                
            }//if
            
        }//for(String tx : dig)
        
        if(this.meta){
            
            txt += "</span>";
            this.meta = false;
            
        }//if(this.meta)
        
        if(this.aspas){
            
            txt += "</q>";
            this.meta = false;
            
        }//if(this.meta)

        return txt;

    }//T(String dig)
    
    private String TitleLink(String lnk){
        
        String add = "";
        boolean loop = true;
        int i = 0;
        
        String txt = lnk.toUpperCase();
        
        while(loop && i < lnk.length()){
            
            boolean space = true;
            
            switch(lnk.charAt(i)){
                
                case '.' ->{
                    
                    if(add.length() <= 20){
                        
                        if(!add.toLowerCase().contains("www") && add.length() > 2){
                            
                            txt = add;
                            loop = false;
                            
                        }//if(!add.toLowerCase().contains("www"))
                        
                        add = "";
                        
                    }//if(add.length() >= 2 && add.length() <= 20)
                    
                }//case '.'
                
                case '_' ->{
                    
                    if(space){add += " ";}
                    space = false;
                    
                }//case '_'
                
                case '-' ->{
                    
                    if(space){add += " ";}
                    space = false;
                    
                }//case '-'
                
                default ->{
                    
                    add += lnk.charAt(i);
                    
                }//default
                
            }//switch(lnk.charAt(i))
            
            switch(lnk.charAt(i)){
                
                case'_':
                case'-':
                space = false;
                break;
                
                default:
                space = true;
                break;
                
            }
            
            i++;
            
        }//while(loop && i < lnk.length())
        
        return txt.toUpperCase();
        
    }//TitleLink(String lnk)
    
    private String P(String paragraphy){
        
        String classe = LongText(paragraphy) ? "long_text" : this.p;
        
        return "<p class=\"" + classe + "\">" + T(paragraphy, "<br/>") + "</p>";
        
    }//P(String paragraphy)
    
    private String P(String paragraphy, String link){
        
        String title_link = "";
        String char_link = "";
        boolean point = false;
        
        for(int i = 0; i < link.length(); i++){
            
            char ds = link.charAt(i);
            
            switch(ds){
                
                case '/' ->{
                    if(point){
                        title_link = char_link;
                        point = false;
                    }
                    char_link = "";
                }
                
                case '.' ->{
                    point = true;
                    char_link += ds;
                }
                
                default ->{
                    char_link += ds;
                }
                
            }//ds
            
        }//for(int i = 0; i < link.length(); i++)
        
        char_link = "";
        
        String txt = "";
        txt += "<p class=\"hiperlink\" title=\"";
        txt += title_link.toLowerCase();
        txt += "\">";
        
        switch(title_link.toLowerCase()){
            
            case "www.youtube.com":
            case "youtube.com":
            case "youtu.be":
            txt += "<p class=\"hiperlink\" title=\"";
            txt += title_link.toLowerCase();
            txt += "\">YOUTUBE</p>";
            txt += "";
            break;
            
            case "www.google.com":
            case "images.app.goo.gl":
            case "g.co":
            txt += "<p class=\"hiperlink\" title=\"";
            txt += title_link.toLowerCase();
            txt += "\">GOOGLE</p>";
            txt += "";
            break;
            
            case "www.canva.com":
            txt += "";
            break;
            
            default:
            txt += "<p class=\"hiperlink\" title=\"";
            txt += title_link.toLowerCase();
            txt += "\">";
            txt += TitleLink(title_link);
            txt += "</p>";
            break;
            
        }//switch(title_link) - 1 - 2
        
        txt += "<p class=\"texto_link\" title=\"";
        txt += TitleLink(title_link);
        txt += "\"><a href=\"";
        txt += link;
        txt += "\" target=\"";
        txt += this.target;
        txt += "\">";
        txt += T(paragraphy, "</a><br/><a href=\"" + link + "\" target=\"" + this.target + "\">");
        txt += "</a></p>";
        
        return txt;
        
    }//P(String paragraphy, String link)
    
    public void Export(String name){
        
        cod c = new cod();
        
        final int max_char_title = 80;
        
        boolean not_tv = true;
        
        int all_vcr = 0;
        
        boolean cd = this.code.Tot() > 0;
        
        List<Integer> one_vcr = new ArrayList();
        List<Integer> max_vcr = new ArrayList();
        
        int one = 0;
        
        if(cd){
            
            for(int next = 0; next < this.code.Tot(); next++){
                
                boolean enter = mpeg(this.code.Read(next, 0));
                
                if(enter){
                    
                    one = 0;
                    all_vcr++;
                    
                } else {//if(enter)
                    
                    one = one + 1;
                    
                }//if(enter)
                
                one_vcr.add(one);
                
            }//for(int next = 0; next < this.code.Tot(); next++)
            
            int prev[] = new int[this.code.Tot()];
            int max_vcr_var = 0;
            boolean tematic = false;
            
            for(int rew = this.code.Tot()-1; rew >= 0; rew--){
                
                Numero d = new Numero(this.code.Read(rew, 0));
                boolean valid = d.Val() && d.Num() == 0;
                
                boolean enter = mpeg(this.code.Read(rew, 0));
                
                if(rew < one_vcr.size()){
                    
                if(tematic){
                    
                    max_vcr_var = one_vcr.get(rew);
                    tematic = false;
                    
                }//if(tematic)
                
                if(valid || enter){
                    
                    tematic = true;
                    
                }//if(valid || enter)
                
                }//if(rew < one_vcr.size())
                
                prev[rew] = max_vcr_var;
                
            }//for(int rew = this.code.Tot()-1; rew >= 0; rew--)
            
            for(int ad : prev){
                
                max_vcr.add(ad);
                
            }//for(int ad : prev)
            
        }//if(cd)
        
        List<String> doc = new ArrayList();
        
        String arq_1 = "";
        String arq_2 = "";
        boolean ext_val = false;
        final int into_arq = 0;
        int sub_arq = into_arq;
        int arquivo = 0;
            
        boolean extend = false;
        int ex = 0;

        while(ex < this.code.Tot() && !extend){

            if(mpeg(this.code.Read(ex, 0))){extend = true;}
            ex++;

        }//while(ex < this.code.Tot() && !extend)
        
        String select_title;
        
        if(name.length() >= max_char_title){//select_title
            
            select_title = "(";
            select_title += new Data().DataAbreviada(false);
            select_title += ") ";
            select_title += name;
            
        } else if(this.code.Read(0, 0).contains("|")){//select_title
            
            select_title = this.code.Read(0, 0).substring(0, this.code.Read(0, 0).indexOf("|")).trim();
            
        } else if(this.code.Read(0, 0).isBlank()){//select_title
            
            select_title = name;
            
        } else {//select_title
            
            select_title = this.code.Read(0, 0);
            
        }//select_title
        
        Data data_title = new Data(select_title);
        
        String title = data_title.Val() ? data_title.DataAbreviada(true) : select_title;
        
        doc.add("<html>");
        doc.add("<head>");
        doc.add("<title>" + title + "</title>");
        doc.add("<meta charset=\"utf-8\" />");
        doc.add("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        //doc.add("<!-- <link rel=\"icon\" href=\"arquivo.ico\" type=\"image/x-icon\"> -->");
        doc.add("<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">");
        doc.add("<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>");
        doc.add("<link href=\"https://fonts.googleapis.com/css2?family=Bytesized&family=Kavoon&family=Montserrat+Underline:ital,wght@0,100..900;1,100..900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Roboto:ital,wght@0,100..900;1,100..900&family=Sofia+Sans+Extra+Condensed:ital,wght@0,1..1000;1,1..1000&family=Winky+Sans:ital,wght@0,300..900;1,300..900&display=swap\" rel=\"stylesheet\">");
        doc.add("<style>");
        
        if(cd){
            
            if(this.link){
                
                doc.add("   a:link, a:hover, a:active, a:visited{");
                doc.add("      text-decoration: none;");
                doc.add("   }");
                doc.add("   a:link, a:hover, a:active{");
                doc.add("      color: #40e0d0;");
                doc.add("   }");
                doc.add("   a:visited{");
                doc.add("      color: #808080;");
                doc.add("   }");
                
            }//if(this.link) -- 1 de 4
            
            doc.add("   body{");
            doc.add("      background-color: black;");
            doc.add("   }");
            doc.add("   div.txt{");
            doc.add("      margin-left: 5%;");
            doc.add("      margin-top: 10%;");
            doc.add("      width: 90%;");
            doc.add("      border: 5px solid white;");
            doc.add("      background-color: black;");
            doc.add("      min-height: 200px;");
            doc.add("      overflow-y: visible;");
            doc.add("      border-radius: 5em;");
            doc.add("   }");
            doc.add("   div.space{");
            doc.add("      width: 100%;");
            doc.add("      height: 5px;");
            doc.add("      background-color: white;");
            doc.add("   }");
            
            if(extend){
                
                doc.add("   h1.arquivo, h1.cabecalho{");
                doc.add("      color: white;");
                doc.add("      margin-left: 2%;");
                doc.add("      font-weight: normal;");
                doc.add("      font-size: calc(30px + 1vw);");
                doc.add("      font-family: \"Bytesized\";");
                doc.add("      word-wrap: break-word;");
                doc.add("   }");
                
                doc.add("   h1.cabecalho{");
                doc.add("      text-transform: uppercase;");
                doc.add("   }");
                
            }//if(extend)
            
            doc.add("   h1.tema{");
            doc.add("      color:white;");
            doc.add("      margin-left: 2%;");
            doc.add("      font-weight: normal;");
            doc.add("      font-size: calc(20px + 1vw);");
            doc.add("      font-family: \"Kavoon\";");
            doc.add("      word-wrap: break-word;");
            doc.add("      line-height: 2em;");
            doc.add("   }");
            
            doc.add("   p.texto{");
            doc.add("      color: white;");
            doc.add("      margin-top: 25px;");
            doc.add("      margin-bottom: 25px;");
            doc.add("      margin-left: 2%;");
            doc.add("      margin-right: 2%;");
            doc.add("      font-weight: normal;");
            doc.add("      font-size: calc(20px + 1vw);");
            doc.add("      font-family: \"Winky Sans\";");
            doc.add("      word-wrap: break-word;");
            doc.add("      line-height: 2em;");
            doc.add("   }");
            
            if(this.text_long){
                
                doc.add("   p.long_text{");
                doc.add("      color: white;");
                doc.add("      margin-top: 25px;");
                doc.add("      margin-bottom: 25px;");
                doc.add("      margin-left: 2%;");
                doc.add("      margin-right: 2%;");
                doc.add("      font-weight: normal;");
                doc.add("      font-size: calc(20px + 1vw);");
                doc.add("      font-family: \"Sofia Sans Extra Condensed\";");
                doc.add("      word-wrap: break-word;");
                doc.add("      line-height: 2em;");
                doc.add("   }");
                
            }//if(this.text_long)
            
            if(this.link){
                
                if(this.only_canva){
                    
                doc.add("   p.hiperlink{");
                doc.add("      color: #808080;");
                doc.add("      margin-top:25px;");
                doc.add("      margin-bottom:5px;");
                doc.add("      margin-left:2%;");
                doc.add("      font-weight: normal;");
                doc.add("      font-size:calc(10px + 1vw);");
                doc.add("      font-family: \"Bytesized\";");
                doc.add("   }");
                
                }//if(this.not_canva)
                
                doc.add("   p.texto_link{");
                doc.add("      color: white;");
                doc.add("      margin-top:5px;");
                doc.add("      margin-bottom:25px;");
                doc.add("      margin-left:2%;");
                doc.add("      margin-right:2%;");
                doc.add("      font-weight: bold;");
                doc.add("      font-size:calc(20px + 1vw);");
                doc.add("      font-family: \"Montserrat Underline\";");
                doc.add("      word-wrap: break-word;");
                doc.add("      line-height:2em;");
                doc.add("   }");
                
            }//if(this.link)
            
            doc.add("   div.divide{");
            doc.add("      width:100%;");
            doc.add("      height:10%;");
            doc.add("      background-color: transparent;");
            doc.add("   }");
            doc.add("   p.ended{");
            doc.add("      padding:50px;");
            doc.add("   }");
            
            if(extend && this.text_long){
                
                doc.add("   h1.arquivo::selection, h1.cabecalho::selection, h1.tema::selection, p.texto::selection, p.long_text::selection{");
                
            } else if(extend){//if(extend && this.text_long)
                
                doc.add("   h1.arquivo::selection, h1.cabecalho::selection, h1.tema::selection, p.texto::selection{");
                
            } else if(this.text_long){//if(extend && this.text_long)
                
                doc.add("   h1.tema::selection, p.texto::selection, p.long_text::selection{");
                
            } else {//if(extend && this.text_long)
                
                doc.add("   h1.tema::selection, p.texto::selection{");
                
            }//if(extend && this.text_long)
            
            doc.add("      color:black;");
            doc.add("      background-color: #f5f5f5;");
            doc.add("   }");
            
            if(this.link){
                
                doc.add("   p.hiperlink::selection{");
                doc.add("      color: #f5f5f5;");
                doc.add("      background-color: transparent;");
                doc.add("   }");
                doc.add("   p.texto_link::selection{");
                doc.add("      color: #008080;");
                doc.add("      background-color: #f5f5f5;");
                doc.add("   }");
            
            }//if(this.link) -- 4 de 4
            
        } else {
            
            doc.add("div.txt{");
            doc.add("  margin-top:20%;");
            doc.add("  margin-left:5%;");
            doc.add("  font-size:7vw;");
            doc.add("}");
            
        }
        
        doc.add("</style>");
        doc.add("</head>");
        doc.add("<body>");
        
        if(cd){//if(cd) - 1
            
            for(int x = 0; x < this.code.Tot(); x++){
                
                Numero number = new Numero(this.code.Read(x, 0));
                
                if(mpeg(this.code.Read(x, 0))){
                    
                    int max = this.code.Read(x, 0).lastIndexOf(".");
                    
                    arq_1 = "";
                    
                    switch(this.code.Read(x, 0).substring(max+1).toLowerCase()){
                        
                        case "mp4" ->{
                            
                            arq_1 += "<h1 class=\"arquivo\">MPEG-4</h1><div class=\"space\"></div>";
                            arq_1 += "<h1 class=\"arquivo\">VÍDEO: ";
                            arq_1 += Numb(arquivo+1, all_vcr);
                            arq_1 += "</h1>";
                            arq_1 += "</h1><div class=\"space\"></div><h1 class=\"cabecalho\">";
                            arq_1 += T(this.code.Read(x, 0).substring(0,max),"<br/>").toUpperCase();
                            arq_2 = T(this.code.Read(x, 0).substring(0,max),"<br/>").toUpperCase();
                            
                        }//case "mp4"
                        
                        case "mpg" ->{
                            
                            arq_1 += "<h1 class=\"arquivo\">MPEG</h1><div class=\"space\"></div><h1 class=\"arquivo\">VÍDEO: ";
                            arq_1 += Numb(arquivo+1, all_vcr);
                            arq_1 += "</h1><div class=\"space\"></div><h1 class=\"cabecalho\">";
                            arq_1 += T(this.code.Read(x, 0).substring(0,max),"<br/>").toUpperCase();
                            arq_1 += "</h1>";
                            arq_2 = T(this.code.Read(x, 0).substring(0,max),"<br/>");
                            
                        }/* case "mpg" */
                        
                        /* case "avi" ->{
                            
                            arq_1 += "<h1 class=\"arquivo\">VÍDEO: ";
                            arq_1 += Numb(arquivo+1, all_vcr);
                            arq_1 += "</h1><div class=\"space\"></div><h1 class=\"cabecalho\">";
                            arq_1 += T(this.code.Read(x, 0).substring(0,max),"<br/>").toUpperCase();
                            arq_1 += this.code.Read(x, 0).substring(max).toUpperCase();
                            arq_1 += "</h1>";
                            arq_2 = T(this.code.Read(x, 0).substring(0,max),"<br/>").toUpperCase();
                            arq_2 += this.code.Read(x, 0).substring(max);
                            
                        }/* case "avi" */
                        
                        case "tv" ->{
                            
                            not_tv = false;
                            
                            arq_1 += "";
                            
                            if(all_vcr == 1){
                                
                                arq_1 += "<h1 class=\"arquivo\">";
                                arq_1 += T(this.code.Read(x, 0).substring(0,max),"</h1><h1 class=\"arquivo\">").toUpperCase();
                                arq_1 += "</h1>";
                                
                            } else {//if(all_vcr > 1)
                                
                                arq_1 += "<h1 class=\"arquivo\">EVENTO</h1><h1 class=\"arquivo\">";
                                arq_1 += Numb(arquivo+1, all_vcr);
                                arq_1 += "<h1 class=\"arquivo\"></h1><div class=\"space\"></div>";
                                arq_1 += "<h1 class=\"cabecalho\">";
                                arq_1 += T(this.code.Read(x, 0).substring(0,max),"</h1><h1 class=\"cabecalho\">");
                                arq_1 += "</h1>";
                                
                            }//if(all_vcr > 1)
                            arq_2 = T(this.code.Read(x, 0).substring(0,max),"<br/>").toUpperCase();
                            
                        }// case "tv"
                        
                        default ->{
                            
                            arq_1 += "<h1 class=\"arquivo\">";
                            arq_1 += Numb(arquivo+1, all_vcr);
                            arq_1 += "</h1><div class=\"space\"></div><h1 class=\"cabecalho\">";
                            arq_1 += T(this.code.Read(x, 0).substring(0,max),"<br/>").toUpperCase();
                            arq_1 += this.code.Read(x, 0).substring(max);
                            arq_2 = T(this.code.Read(x, 0).substring(0,max),"<br/>").toUpperCase();
                            arq_2 += this.code.Read(x, 0).substring(max);
                            
                        }/* default */
                        
                    }//switch(this.code.Read(x, 0).substring(0,max+1))
                    
                    ext_val = true;
                    arquivo++;
                    sub_arq = into_arq;
                    
                } else if(number.Val() && number.Num() == 0){//if(mpeg(this.code.Read(x, 0)))
                    
                    ext_val = false;
                    sub_arq = into_arq;
                    
                } else {//if(mpeg(this.code.Read(x, 0)))
                    
                    sub_arq++;
                    
                }//if(mpeg(this.code.Read(x, 0)))
                
                String tx = "";
                
                for(int y = 0; y < this.code.Tot(x); y++){
                    
                    if(y == 0){
                        
                        tx += "<div class=\"txt\"><div class=\"divide\"></div>";
                        
                        if(ext_val){
                            
                            if(sub_arq == into_arq){
                                
                                tx += arq_1;
                                
                            } else {//if(sub_arq == 1)
                                
                                tx += "<h1 class=\"cabecalho\">";
                                
                                if(x < one_vcr.size() && x < max_vcr.size() && max_vcr.get(x) > 0){
                                    
                                    tx += Numb(one_vcr.get(x),max_vcr.get(x));
                                    tx += "</h1><div class=\"space\"></div><h1 class=\"cabecalho\">";
                                    
                                }//if(x < one_vcr.size())
                                
                                tx += arq_2;
                                tx += "</h1><div class=\"space\"></div><h1 class=\"";
                                tx += this.h1;
                                tx += "\">";
                                tx += T(this.code.Read(x, 0), "<br/>");
                                tx += "</h1>";
                                
                            }//if(sub_arq == 1)
                            
                        } else {//if(ext_val)
                            
                            Numero numer = new Numero(this.code.Read(x, 0));
                            
                            tx += "<h1 class=\"";
                            tx += this.h1;
                            tx += "\">";
                            
                            if(numer.Num() == 0 && numer.Val()){
                                
                                tx += T(this.code.Read(0, 0), "<br/>");
                                
                            } else {//if(numer.Num() == 0 && numer.Val())
                                
                                tx += T(this.code.Read(x, 0), "<br/>");
                                
                            }//if(numer.Num() == 0 && numer.Val())
                            
                            tx += "</h1>";
                            
                        }//if(ext_val)
                        
                    } else {//if(y == 0)
                        
                        if(c.Link(this.code.Read(x, y))){
                            
                            if(c.Link(this.code.Read(x, y-1))){
                                
                                tx += "<div class=\"space\"></div>";
                                tx += P(this.code.Read(x, y),this.code.Read(x, y));
                                
                            } else {//if(c.Link(this.code.Read(x, y-1)))
                                
                                tx += "<div class=\"space\"></div>";
                                tx += P(this.code.Read(x, y-1),this.code.Read(x, y));
                                
                            }//if(c.Link(this.code.Read(x, y-1)))
                            
                        } else if(y == this.code.Tot(x)-1){//if(c.Link(this.code.Read(x, y)))
                            
                            tx += "<div class=\"space\"></div>";
                            tx += P(this.code.Read(x, y));
                            
                        } else {//if(c.Link(this.code.Read(x, y)))
                            
                            if(!c.Link(this.code.Read(x, y+1))){
                                
                                tx += "<div class=\"space\"></div>";
                                tx += P(this.code.Read(x, y));
                                
                            }//if(!c.Link(this.code.Read(x, y+1)))
                            
                        }//if(c.Link(this.code.Read(x, y)))
                        
                    }//if(y == 0)
                    
                }//for(int y = 0; y < this.code.Tot(x); y++)
                
                doc.add(tx + "<div class=\"divide\"></div></div>");
                
            }//for(int x = 0; x < this.code.Tot(); x++)
            
            arq_1 = "";
            arq_2 = "";
            ext_val = false;
            
            doc.add("<p class=\"ended\"></p>");
            
            if(this.tag){
                
                doc.add("");
                doc.add("<script>");
                doc.add("   ");
                doc.add("   const metatag = document.getElementsByTagName(\"span\");");
                doc.add("   ");
                doc.add("   for(var i = 0; i < metatag.length; i++){");
                doc.add("      ");
                doc.add("      metatag[i].innerText = \"<\" + metatag[i].innerHTML + \">\";");
                doc.add("      metatag[i].style.letterSpacing = \"1%\";");
                doc.add("      ");
                doc.add("   }");
                doc.add("   ");
                doc.add("</script>");
                doc.add("");
                
            }//if(this.tag)
            
        } else {//if(cd) - 1
            
            doc.add("<div class=\"txt\">" + name.toUpperCase() + "</div>");
            
        }//if(cd) - 1
        
        doc.add("</body>");
        doc.add("</html>");
        
        if(cd){//if(cd) - 2
            
            doc.add("");
            
            if(this.code.Tot() <= 350 && not_tv){
                
                doc.add("<!-- " + 
                        new Data().DataAbreviada(true) + 
                        " -- " + 
                        new Hora(true).getHora(true) + 
                        " --"
                );
                
                for(int pg = 1; pg <= this.code.Tot(); pg++){
                    
                    doc.add("");
                    
                    String pos = "Item ";
                    
                    pos += Numb(pg,this.code.Tot());
                    
                    if(pg > 1){doc.add("-".repeat(pos.length()));}
                    doc.add("_".repeat(pos.length()));
                    
                    doc.add(pos);
                    
                    doc.add("");
                    
                    for(int l = 0; l < this.code.Tot(pg-1); l++){
                        
                        Numero Test = new Numero(this.code.Read((pg-1),l));

                        doc.add(Test.Val() && Test.Num() == 0 && l == 0 ? this.code.Read(0, 0) : this.code.Read((pg-1), l));

                    }//for(int l = 0; l < this.code.Tot(p); l++)
                    
                    doc.add("");
                    
                    String re = this.code.Read(pg-1, this.code.Tot(pg-1)-1);
                    
                    if(re.length() >= 30 && pg < this.code.Tot()){
                        
                        doc.add("-".repeat(re.length() <= 100 ? re.length() : 100));
                        doc.add("");
                    
                    }//if(re.length() >= 30 && pg < this.code.Tot())
                    
                }//for(int p = 0; p < this.code.Tot(); p++)
                
                doc.add("");
                doc.add("");
            
                doc.add("-- " + 
                        new Data().DataAbreviada(false) + 
                        " -- " + 
                        new Hora(true).getHora(true) + 
                        " --"
                );
                
            } else {//if(this.code.Tot() <= 300 && this.link && not_tv)
                
                doc.add("<!-- " + 
                        new Data().DataAbreviada(true) + 
                        " -- " + 
                        new Hora(true).getHora(true) + 
                        " --"
                );
                
            }//if(this.code.Tot() <= 300 && this.link && not_tv)
            
            if(this.code.Tot() <= 20 && this.link){
            
                String total = "Ite";

                if(this.code.Tot() == 1){
                    total += "m";
                } else {
                    total += "ns";
                }// if(this.code.Tot() == 1)
                
                String itens = "";
                
                for(int d = 1; d <= this.code.Tot(); d++){
                    
                    itens += ";";
                    
                    itens += d;
                    
                    itens += " de ";
                    
                    if(this.code.Tot() < 10){itens += "0";}
                    
                    itens += this.code.Tot();
                    
                    itens += " | ";
                    
                    Numero Test = new Numero(this.code.Read((d-1),0));
                    
                    int val = Test.Val() && Test.Num() == 0 ? 0 : d-1;
                    
                    itens += Registro.Title(this.code.Read(val, 0)," | ");
                    
                }//for(int d = 0; d < this.code.Tot(); d++)

                doc.add(
                    new Data().Load() + 
                    " | " + 
                    new Hora(true).Load() + 
                    ";" + 
                    this.code.Tot() + 
                    " " + 
                    total + 
                    itens
                );
            
            }//if(this.code.Tot() <= 250 && !extend && not_tv)
            
            for(int x = 0; x < this.code.Tot(); x++){
                
                String tx = "";
                
                for(int y = 0; y < this.code.Tot(x); y++){
                    
                    if(y > 0){
                        tx += ";";
                    }
                    
                    tx += this.code.Read(x, y);
                    
                }//for(int y = 0; y < this.code.Tot(x); y++)
                
                doc.add(tx);
                
            }//for(int x = 0; x < this.code.Tot(); x++)
            
        }//if(cd) - 2
        
        Files line_page = new Files(this.host + 
                "page_" +
                new Data().Load() + 
                "_" + 
                new Hora(true).Load() + 
                ".htm");
        
        line_page.Clear();
        line_page.WriteAll(doc);
        
        doc.clear();
        
    }//Export(String title, boolean target)
    
}//Exportar