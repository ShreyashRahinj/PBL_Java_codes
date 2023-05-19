import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Trie{
    static TrieNode root;
    public static void main(String[] args) throws Exception {
        Trie obj = new Trie();
        root = obj.new TrieNode();

        Scanner sc = new Scanner(System.in);
        obj.read("myfile.txt");

        while(true){

            System.out.println("Welcome this program will help you to autocomplete the words, So do you wish to continue? Press 1 to continue Press 0 to stop");
            int res = sc.nextInt();
            sc.nextLine();

            if(res==1){
                System.out.println("Enter the word you want to Autocomplete");
                String ardha = sc.nextLine();


                if(obj.autocomplete(ardha)=="0"){
                    System.out.println("Sorry this word doesn't exist in our database, Do you wish to enter the complete word?"+"\n"+"Press 1 to continue"+"\n"+"Press 0 to stop");
                    
                    if(sc.nextInt()==1){
                        sc.nextLine();
                        String new_word = sc.nextLine();
                        obj.insert(new_word.toLowerCase());
                        obj.write("myfile.txt", new_word);

                    }
                    
                }
                else{
                    System.out.println("Most-nearer word is : "+obj.autocomplete(ardha));
                }
            }
            else{
                System.out.println("Thank you");
                break;
            }    
			
        } 
		sc.close();  
    }


        
    class TrieNode{
        TrieNode [] children = new TrieNode[26];
        boolean isEow;
        TrieNode(){
            isEow = false;
            for(int i=0;i<26;i++){
                children[i] = null;
            }
        }
    }
    void insert(String key){
        TrieNode curr = root;
        for(int i=0;i<key.length();i++){
            int index = key.charAt(i) - 'a';
            if(curr.children[index]==null){
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
        curr.isEow = true;
    }
    boolean search(String key){
        TrieNode curr = root;
        for(int i=0;i<key.length();i++){
            int index = key.charAt(i) - 'a';
            if(curr.children[index]==null){
                return false;
            }
            curr = curr.children[index];
        }
        return curr.isEow;
    }
    String autocomplete(String key){
        if(search(key)){
            return key;
        }
        else{
            TrieNode curr = root;
            for(int i=0;i<key.length();i++){
                int index = key.charAt(i) - 'a';
                if(curr.children[index]==null){
                    return "0";
                }
                curr = curr.children[index];
            }
            int i=0;
            while(i<26){
                if(curr.children[i]!=null && !curr.isEow){
                    curr = curr.children[i];
                    i=i+'a';
                    key = key +(char)i;
                    i=0;
                }
                else{
                   i++;
                }
            }
            return key;
        }
    }
    void read(String fname){
        try {
            File file = new File("myfile.txt");
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                String word  = input.nextLine();
                insert(word.toLowerCase());
            }
			input.close();
        }
        catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
		
    }
    void write(String fname,String word){
        try {
            FileWriter Writer = new FileWriter(fname,true);

            Writer.write("\n");
            Writer.write(word);
            Writer.close();
        }
        catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
    }
}