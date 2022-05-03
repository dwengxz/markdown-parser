//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static boolean isRepeating(int current, int previous){
        if(previous > current){
            return true;
        }
        return false;
    }

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        int previousIndex = 0;
        while(currentIndex < markdown.length()) {

            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);

            char next = markdown.charAt(closeBracket+1);
 
            if(next == '['){
                currentIndex = markdown.indexOf("]",closeBracket + 1) + 1;
                if(previousIndex > currentIndex){
                    break;
                }
                else{
                    previousIndex=currentIndex;
                }
            }
            if(next == '('){
                int openParen = markdown.indexOf("(", closeBracket);
                int closeParen = markdown.indexOf(")", openParen);
                currentIndex = closeParen + 1;
                if(previousIndex > currentIndex){
                    break;
                }
                else{
                    previousIndex=currentIndex;
                }
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            }
            else if(next == ':'){
                int linkEnd = markdown.indexOf(' ',markdown.indexOf(next));
                currentIndex = linkEnd + 1;
                if(previousIndex > currentIndex){
                    break;
                }
                else{
                    previousIndex=currentIndex;
                }
                toReturn.add(markdown.substring(markdown.indexOf(next)+1,linkEnd));
            }


        }

        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}
