package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.Scanner;

public class HuffmanCodeEmmanuelSalcedo extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        HuffmanCodeView huffcode = new HuffmanCodeView();

        Scene scene = new Scene(huffcode, 800, 600);
        primaryStage.setTitle("Emmanuel_Salcedo_HuffmanCode");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }

}

class HuffmanCodeView extends BorderPane {
    public HuffmanCodeView() {
        drawHuffmanCode();
    }

    private void drawHuffmanCode() {
        TView treePane = new TView();
        Text tEnterText = new Text("Enter a text:");
        Text tEnterBitString = new Text("Enter a bit string:");
        TextField tfEnterText = new TextField();
        TextField tfEnterBitString = new TextField();
        Button btEncode = new Button("Show Huffman Tree");
        Button btDecode = new Button("Decode to Text");

        HBox hbText = new HBox(10);
        hbText.getChildren().addAll(tEnterText, tfEnterText, btEncode);
        hbText.setAlignment(Pos.CENTER);
        HBox hbBit = new HBox(10);
        hbBit.getChildren().addAll(tEnterBitString, tfEnterBitString, btDecode);
        hbBit.setAlignment(Pos.CENTER);
        VBox treebox = new VBox(10);
        treebox.getChildren().addAll(hbText, hbBit);
        treebox.setAlignment(Pos.CENTER);

        btEncode.setOnAction(e -> {
            if (!tfEnterText.getText().isEmpty()) {
                treePane.show(tfEnterText.getText());
            }
        });

        btDecode.setOnAction(e -> {
            if (treePane.hasTree() && !tfEnterBitString.getText().isEmpty()) {
                treePane.decode(tfEnterBitString.getText());
            }
        });

        setTop(treebox);
        setCenter(treePane);
    }
}

class TView extends Pane {
    private HuffmanCode.Tree tree;
    private String[] codes;
    private Text status;
    private double radius = 15;
    private double vGap = 50;

    //constructor for treeview
    public TView() {
        tree = null;
        codes = null;
        status = new Text(290, 520, "");
        setStatus("Huffman Tree is empty");
    }

    //Status Method to show the status of the tree
    public void setStatus(String msg) {
        getChildren().remove(status);
        status.setText(msg);
        getChildren().add(status);
    }

    //Check to see if the tree exists in the view
    public boolean hasTree() {
        return tree != null;
    }

    //show methods takes text as parameter and encodes the text and return
    public void show(String text) {
        //int array the length of the characters not repeated
        int[] counts = HuffmanCode.getCharacterFrequency(text);
        //create a Huffman tree with the array
        tree = HuffmanCode.getHuffmanTree(counts);
        codes = HuffmanCode.getCode(tree.root);
        displayTree();
        StringBuilder encode = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            encode.append(codes[(int) c]);
        }
        setStatus(text + " is encoded to " + encode.toString());
    }

    //decodes the bitstring into text
    public void decode(String bits) {
        StringBuilder text = new StringBuilder();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < bits.length(); i++) {
            code.append(bits.charAt(i) + "");
            for (int j = 0; j < codes.length; j++) {
                if (code.toString().equals(codes[j])) {
                    text.append((char) j);
                    code = new StringBuilder();
                }
            }
        }
        setStatus(bits + " is decoded to " + text.toString());
    }

    public void displayTree() {
        //clear the view
        this.getChildren().clear();
        //recursively traverse through the Tree and add it to the view
        if (tree.root != null) {
            displayTree(tree.root, getWidth() / 2, vGap, getWidth() / 4);
        }
    }

    //draw the tree on the display
    public void displayTree(HuffmanCode.Tree.Node root, double x, double y,
                            double hGap) {
        if (root.left != null) {
            Text tCode = new Text(x + hGap / 2, y + vGap / 2, "0");
            getChildren().addAll(new Line(x - hGap, y + vGap, x, y), tCode);
            displayTree(root.left, x - hGap, y + vGap, hGap / 2);
        }

        if (root.right != null) {
            Text tCode = new Text(x - hGap / 2, y + vGap / 2, "1");
            getChildren().addAll(new Line(x + hGap, y + vGap, x, y), tCode);
            displayTree(root.right, x + hGap, y + vGap, hGap / 2);
        }

        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        Text tChar = new Text(x - 4, y + 30, root.element + "");
        getChildren().addAll(circle, tChar, new Text(x - 4, y + 4, root.weight + ""));
    }
}

class HuffmanCode {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a text: ");
        String text = input.nextLine();

        int[] counts = getCharacterFrequency(text); // Count frequency

        System.out.printf("%-15s%-15s%-15s%-15s\n",
                "ASCII Code", "Character", "Frequency", "Code");

        Tree tree = getHuffmanTree(counts); // Create a Huffman tree
        String[] codes = getCode(tree.root); // Get codes

        for (int i = 0; i < codes.length; i++)
            if (counts[i] != 0) // (char)i is not in text if counts[i] is 0
                System.out.printf("%-15d%-15s%-15d%-15s\n",
                        i, (char) i + "", counts[i], codes[i]);
    }


    public static Tree buildCode(String text) {
        int[] counts = getCharacterFrequency(text); // Count frequency
        Tree tree = getHuffmanTree(counts); // Create a Huffman tree
        String[] codes = getCode(tree.root); // Get codes
        return tree;
    }

    /**
     * Get Huffman codes for the characters
     * This method is called once after a Huffman tree is built
     */
    public static String[] getCode(Tree.Node root) {
        if (root == null) return null;
        String[] codes = new String[2 * 128];
        assignCode(root, codes);
        return codes;
    }

    /* Recursively get codes to the leaf node */
    private static void assignCode(Tree.Node root, String[] codes) {
        if (root.left != null) {
            root.left.code = root.code + "0";
            assignCode(root.left, codes);

            root.right.code = root.code + "1";
            assignCode(root.right, codes);
        } else {
            codes[(int) root.element] = root.code;
        }
    }

    /**
     * Get a Huffman tree from the codes
     */
    public static Tree getHuffmanTree(int[] counts) {
        // Create a heap to hold trees
        Heap<Tree> heap = new Heap<Tree>(); // Defined in Listing 24.10
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0)
                heap.add(new Tree(counts[i], (char) i)); // A leaf node tree
        }

        while (heap.getSize() > 1) {
            Tree t1 = heap.remove(); // Remove the smallest weight tree
            Tree t2 = heap.remove(); // Remove the next smallest weight
            heap.add(new Tree(t1, t2)); // Combine two trees
        }

        return heap.remove(); // The final tree
    }

    /**
     * Get the frequency of the characters
     */
    public static int[] getCharacterFrequency(String text) {
        int[] counts = new int[256]; // 256 ASCII characters

        for (int i = 0; i < text.length(); i++)
            counts[(int) text.charAt(i)]++; // Count the character in text

        return counts;
    }

    /**
     * Define a Huffman coding tree
     */
    public static class Tree implements Comparable<Tree> {
        Node root; // The root of the tree

        /**
         * Create a tree with two subtrees
         */
        public Tree(Tree t1, Tree t2) {
            root = new Node();
            root.left = t1.root;
            root.right = t2.root;
            root.weight = t1.root.weight + t2.root.weight;
        }

        /**
         * Create a tree containing a leaf node
         */
        public Tree(int weight, char element) {
            root = new Node(weight, element);
        }

        @Override
        /** Compare trees based on their weights */
        public int compareTo(Tree t) {
            if (root.weight < t.root.weight) // Purposely reverse the order
                return 1;
            else if (root.weight == t.root.weight)
                return 0;
            else
                return -1;
        }

        public class Node {
            char element; // Stores the character for a leaf node
            int weight; // weight of the subtree rooted at this node
            Node left; // Reference to the left subtree
            Node right; // Reference to the right subtree
            String code = ""; // The code of this node from the root

            /**
             * Create an empty node
             */
            public Node() {
            }

            /**
             * Create a node with the specified weight and character
             */
            public Node(int weight, char element) {
                this.weight = weight;
                this.element = element;
            }
        }
    }
}


class Heap<E extends Comparable> {
    private java.util.ArrayList<E> list = new java.util.ArrayList<E>();

    /**
     * Create a default heap
     */
    public Heap() {
    }

    /**
     * Create a heap from an array of objects
     */
    public Heap(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            add(objects[i]);
    }

    /**
     * Add a new object into the heap
     */
    public void add(E newObject) {
        list.add(newObject); // Append to the heap
        int currentIndex = list.size() - 1; // The index of the last node

        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
            // Swap if the current object is greater than its parent
            if (list.get(currentIndex).compareTo(
                    list.get(parentIndex)) > 0) {
                E temp = list.get(currentIndex);
                list.set(currentIndex, list.get(parentIndex));
                list.set(parentIndex, temp);
            } else
                break; // the tree is a heap now

            currentIndex = parentIndex;
        }
    }

    /**
     * Remove the root from the heap
     */
    public E remove() {
        if (list.size() == 0) return null;

        E removedObject = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);

        int currentIndex = 0;
        while (currentIndex < list.size()) {
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;

            // Find the maximum between two children
            if (leftChildIndex >= list.size()) break; // The tree is a heap
            int maxIndex = leftChildIndex;
            if (rightChildIndex < list.size()) {
                if (list.get(maxIndex).compareTo(
                        list.get(rightChildIndex)) < 0) {
                    maxIndex = rightChildIndex;
                }
            }

            // Swap if the current node is less than the maximum
            if (list.get(currentIndex).compareTo(
                    list.get(maxIndex)) < 0) {
                E temp = list.get(maxIndex);
                list.set(maxIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
                currentIndex = maxIndex;
            } else
                break; // The tree is a heap
        }

        return removedObject;
    }

    /**
     * Get the number of nodes in the tree
     */
    public int getSize() {
        return list.size();
    }
}