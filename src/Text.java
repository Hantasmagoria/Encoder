import java.util.Arrays;

// import java.util.HashMap;

public class Text {
    // public final HashMap<Integer, String> referenceMap = new HashMap<Integer, String>();
    private final char[] referenceTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '(', ')', '*', '+', ',', '-', '.', '/' };
    private final char[] ori = Arrays.copyOf(this.referenceTable, this.referenceTable.length);
    private char offsetChar;
    private int offsetInt = 0;

    //getters & setters
    public char[] getReferenceTable() {
        return this.referenceTable;
    }

    public char getReferenceTable(int i) {
        return this.referenceTable[i];
    }

    public int getReferenceTableLength() {
        return this.referenceTable.length;
    }
    
    public int getOffsetInt() {
        return this.offsetInt;
    }

    public char getOffsetChar() {
        return this.offsetChar;
    }

    public void setOffsetInt(int offsetInt) {
        this.offsetInt = offsetInt;
    }

    public void setOffsetChar(char offsetChar) {
        this.offsetChar = offsetChar;
    }

    //encode method
    public String encode(String plaintext) {
        char[] result = plaintext.toCharArray();

        for (int i = 0; i < this.offsetInt; i++) {
            char lastElement = this.referenceTable[this.referenceTable.length - 1];             //temporarily store the last element to shift down the rest
            int endPoint = this.referenceTable.length - 1;

            for (int j = this.referenceTable.length - 2; j >= 0; j--) {
                this.referenceTable[endPoint] = this.referenceTable[j];
                endPoint--;
            }
            this.referenceTable[0] = lastElement;
        }
        int[] position = new int[result.length];
        int posInt = 0;

        for (int i = 0; i < result.length; i++) {
            if (result[i] == ' ') {
                position[posInt] = 0;
                posInt++;
                continue;
            }

            for (int j = 0; j < this.ori.length; j++) {
                if (result[i] == this.ori[j]) {
                    position[posInt] = j;
                    posInt++;
                    break;
                }
            }

        }
        String encodedString = "";

        for (int i = 0; i < position.length; i++) {
            if (position[i] == 0) {
                encodedString = encodedString + " ";
            }
            if (position[i] != 0 && position[i] >= 0 && position[i] <= 43) {
                encodedString = encodedString + this.referenceTable[position[i]];
            }
        }
        plaintext = plaintext + encodedString;
        return encodedString;
    }

    //decode method
    public String decode(String encodedText) {
        char[] encodedArray = encodedText.toCharArray();
        String decodeString = "";
        boolean f = false;

        for (int i = 0; i < encodedArray.length; i++) {
            for (int j = 0; j < this.referenceTable.length; j++) {
                if (encodedArray[i] == this.referenceTable[j]) {
                    f = true;
                    decodeString = decodeString + this.referenceTable[j + this.offsetInt];
                    break;
                }
                if (encodedArray[i] == ' ') {
                    decodeString = decodeString + " ";
                    break;
                }
            }

            if (!f) {
                decodeString = decodeString + encodedArray[i];
            }

            f = false;
        }
        return decodeString;
    }

}