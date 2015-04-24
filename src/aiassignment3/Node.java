

package aiassignment3;

/**
 *
 * Maeda Hanafi
 */
public class Node {
    private char character;
    private Node parent;
    private Node child1;
    private Node child2;
    public Node(char operator, Node child1, Node child2){
        this.character = operator;
        this.child1 = child1;
        this.child2 = child2;
    }

    public void setParent(Node parent){
        this.parent = parent;
    }
    public char getData(){
        return character;
    }
    public Node getChild1(){
        return child1;
    }
    public Node getChild2(){
        return child2;
    }
    public void setChild1(Node child1){
        this.child1 = child1;
    }
    public void setChild2(Node child2){
        this.child2 = child2;
    }

    
}
