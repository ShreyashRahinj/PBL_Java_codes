import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TST{
    static Node root;
    public static void main(String[] args) {
        String [] words = { "wallstreet", "geeksforgeeks", "wallmart" , "walmart", "waldomort", "word" };

        String prefix = "rev";

        TST obj = new TST();

        for(String word:words){
            obj.insert(word);
        }

        obj.read("myfile.txt");

        System.out.println("The words with prefix "+prefix+" are : ");

        obj.autocomplete(prefix);
    }

    class Node{
        char data;
        boolean isEOW;
        Node left,right,middle;
        Node(char data){
            this.data = data;
            this.isEOW = false;
            this.left = null;
            this.right = null;
            this.middle = null;
        }
    }

    Node insert(Node node,char[] word,int index){
        if(node == null){
            node = new Node(word[index]);
        }
        if(word[index] < node.data){
            node.left = insert(node.left,word,index);
        }
        else if(word[index]> node.data ){
            node.right = insert(node.right,word,index);
        }
        else{
            if(index+1<word.length){
                node.middle = insert(node.middle, word, index+1);
            } else{
                node.isEOW = true;
            }
        }
        return node;
    }

    Node search_prefixNode(String prefix){
        Node node = root;
        int i = 0;
        while(node != null && i<prefix.length()){
            if(prefix.charAt(i) < node.data){
                node = node.left;
            }
            else if(prefix.charAt(i) > node.data){
                node = node.right;
            }
            else{
                i++;
                if(i == prefix.length()){
                    return node;
                }
                node = node.middle;
            }
        }
        return node;
    }

    void traverse(Node node, StringBuilder prefix){
        if(node == null){
            return;
        }

        traverse(node.left, prefix);

        if(node.isEOW){
            System.out.println(prefix.toString()+node.data);
        }

        traverse(node.middle, prefix.append(node.data));

        prefix.deleteCharAt(prefix.length()-1);
        traverse(node.right, prefix);
    }

    void autocomplete(String prefix){
        Node node = search_prefixNode(prefix);

        if(node == null){
            System.out.println("No word found with that prefix");
            return;
        }

        if(node.isEOW){
            System.out.println(prefix);
        }

        traverse(node.middle, new StringBuilder(prefix));
    }

    void insert(String word){
        root = insert(root,word.toCharArray(),0);
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
}