import java.util.ArrayList;

public class UndoRedo {
    public static void main(String[] args) {
        ArrayList<String> undoArrayList = new ArrayList<>();
        ArrayList<String> redoArrayList = new ArrayList<>();

        onChange("a", undoArrayList);
        onChange("ab", undoArrayList);
        onChange("abc", undoArrayList);
        onChange("abcd", undoArrayList);
        onChange("abcde", undoArrayList);
        onChange("abcdef", undoArrayList);

        System.out.println(undo(undoArrayList, redoArrayList));
        System.out.println(undo(undoArrayList, redoArrayList));
        System.out.println(undo(undoArrayList, redoArrayList));
        
        System.out.println(redo(undoArrayList, redoArrayList));
        System.out.println(redo(undoArrayList, redoArrayList));
        

    }
    static void onChange(String value,ArrayList<String> undoArrayList){
        undoArrayList.add(value);
    }
    static String undo(ArrayList<String> undoArrayList,ArrayList<String> redoArrayList){
        String new_value = "";
        new_value = undoArrayList.get(undoArrayList.size()-2);
        redoArrayList.add(undoArrayList.get(undoArrayList.size()-1));
        undoArrayList.remove(undoArrayList.size()-1);
        return new_value;
    }
    static String redo(ArrayList<String> undoArrayList,ArrayList<String> redoArrayList){
        String new_value = "";
        new_value = redoArrayList.get(redoArrayList.size()-1);
        undoArrayList.add(redoArrayList.get(redoArrayList.size()-1));
        redoArrayList.remove(redoArrayList.get(redoArrayList.size()-1));
        return new_value;
    }

}
