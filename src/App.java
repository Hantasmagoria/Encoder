import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String givenString = "HELLO WORLD";
        String encodedText = "";                                                                                                    //to store encoded text for later

        System.out.print("Offset character: ");
        char input = sc.next().charAt(0);                                                                                    //Assuming input sanitation not required

        Text sText = new Text();
        int offset = 0;
        for (int i = 0; i < sText.getReferenceTableLength(); i++) {
            if (input != sText.getReferenceTable(i)) {
                continue;
            }
            offset = i;
            sText.setOffsetChar(sText.getReferenceTable(offset));
            sText.setOffsetInt(offset);
        }
        
        encodedText = sText.encode(givenString); //call public String encode(plaintext) method
        System.out.println("Encoded String:= " +input+ encodedText);
        System.out.println("Decoded String:= "+sText.decode(encodedText)); //call public String encode(plaintext) method
        sc.close();
    }
}
