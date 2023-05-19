import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DL {

    public static void main(String[] args) {
        DL obj = new DL();

		String [] arr = {"Ram","Shyam","sime","hai"};

		arr = obj.autocorrect_paragraph(arr);

		for(String e:arr){
			System.out.println(e);
		}
		
	}

	int optimalStringAlignmentDistance(String s1, String s2) {
		int[][] dp = new int[s1.length()+1][s2.length()+1];

		for (int i = 0; i <= s1.length(); i++) {
			dp[i][0] = i;
		}
		for (int j = 0; j <= s2.length(); j++) {
			dp[0][j] = j;
		}

		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if (s1.charAt(i-1) == s2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1];
				} else {
					dp[i][j] = 1 + Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]);
				}
			}
		}

		return dp[s1.length()][s2.length()];
	}

	String nearest_word(String fname,String actual){
		int diff = Integer.MAX_VALUE;
		String tb_replace = null;
		try {
            File file = new File(fname);
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                String word  = input.nextLine();
				if(optimalStringAlignmentDistance(word, actual) < diff){
					tb_replace = word;
					diff = optimalStringAlignmentDistance(word, actual);
				}   
            }
            input.close();
        }
        catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }
		return tb_replace;
	}

	String [] autocorrect_paragraph(String [] para){
		for(int i=0;i<para.length;i++){
			if(Character.isLowerCase(para[i].charAt(0))){
				para[i] = nearest_word("words_backup.txt", para[i]);
			}
		}
		return para;
	}
}
