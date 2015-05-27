package chap1_3;//1.3.44
//page 170

import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class TextEditorBuffer {
    private int size;
    private Stack<String> left, right;

    public TextEditorBuffer() {
        left = new Stack<String>();
        right = new Stack<String>();
    }

    public static void main(String[] args) {
        PrintWriter output = new PrintWriter(new OutputStreamWriter(System.out), true);

        TextEditorBuffer buffer = new TextEditorBuffer();
        buffer.insert("a");
        buffer.insert("b");
        buffer.insert("c");
        buffer.insert("d");
        buffer.insert("e");
        buffer.left(2);
        output.println(buffer.delete());
        output.println(buffer.size());
    }

    public void insert(String c) {
        left.push(c);
        size++;
    }

    public String delete() {
        String item = right.pop();
        if (item != null)
            size--;
        return item;
    }

    public void left(int k) {
        boolean nullFound = false;
        int i = 0;
        while (i < k && !nullFound) {
            String c = left.pop();
            if (c == null)
                nullFound = true;
            else
                right.push(c);
            i++;
        }
    }

    public void right(int k) {
        boolean nullFound = false;
        int i = 0;
        while (i < k && !nullFound) {
            String c = right.pop();
            if (c == null)
                nullFound = true;
            else
                left.push(c);
            i++;
        }
    }

    public int size() {
        return size;
    }
}