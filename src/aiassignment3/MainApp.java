package aiassignment3;

import java.util.Scanner;

/**
 *
 * Maeda Hanafi
 * Assignment 3
 * CSC481
 */
public class MainApp {

    
    public MainApp(){
        start();
    }
    
    public static void main(String[] args) {
        new MainApp();
    }

    public void start(){
        //ask user to enter an expression in infix notation 
        System.out.println("Enter in the expression in infix notation:");
        String expression = new Scanner(System.in).nextLine();
        Node root = createTree(expression);
        System.out.println("Solution:"+eval(root));
    }

    public double eval(Node root){
       // System.out.println(root.getData());
        if(root.getChild1()==null && root.getChild2()==null){//its a child node
            System.out.println(root.getData());
            double db =  (double)(Integer.parseInt(root.getData()+""));
            
            return db;
        }
        if(root.getData()=='+'){
            return eval(root.getChild1())+eval(root.getChild2());
        }else if(root.getData()=='-'){
            return eval(root.getChild1())-eval(root.getChild2());
        }else if(root.getData()=='*'){
            return eval(root.getChild1())*eval(root.getChild2());
        }else if(root.getData()=='/'){
            double y = eval(root.getChild2());
            if(y==0){
                return Math.pow(10, 10);
            }
            return eval(root.getChild1())+y;
        }else if(root.getData()=='s'){
            return Math.sin(eval(root.getChild2())) * eval(root.getChild1());
        }else if(root.getData()=='e'){
            return Math.exp(eval(root.getChild2())) * eval(root.getChild1());
        }else{
            return 0;
        }
    }
    
    public Node createTree(String expression){
        //find root first
        String left = "";
        String operator = "";
        String right = "";
        boolean foundRoot = false;
        Node root = null;
        int parantheses = 0;
        System.out.println("-------------------------------------------------operate on "+expression);
        if(expression.length()==1){
            System.out.println("CHILD NODE:"+expression+";");
            return new Node(expression.charAt(0), null, null);
        }else{
            //go through each char in expression
            int i=0;
            while(i<expression.length()){
                if(expression.charAt(i)=='('){
                    parantheses++;
                    if(!foundRoot){ 
                        left = left+expression.charAt(i)+"";
                        //System.out.println("found a ( placed it in left");
                    }else if(foundRoot){
                        right = right+expression.charAt(i);
                        //System.out.println("found a ( placed it in right");
                    }
                }else if(expression.charAt(i)==')'){
                    parantheses--;
                    if(!foundRoot){ //not the root. add it to the left string
                        left = left+expression.charAt(i)+"";
                        //System.out.println("found a ) placed it in left");
                    }else if(foundRoot){
                        right = right+expression.charAt(i);
                        //System.out.println("found a ) placed it in right");
                    }
                }else if((parantheses==0 ) && !foundRoot && (expression.charAt(i)=='+'|| expression.charAt(i)=='-' || expression.charAt(i)=='*' || expression.charAt(i)=='/' || expression.charAt(i)=='e')){
                    //found root
                    operator = expression.charAt(i)+"";
                    System.out.println("Root:"+expression.charAt(i));
                    foundRoot = true;
                }else if((parantheses==0) && !foundRoot && (expression.charAt(i)=='s' && expression.charAt(i+1)=='i' && expression.charAt(i+2)=='n')){
                    operator = expression.charAt(i) + expression.charAt(i+1) +expression.charAt(i+2)+"";
                    operator = expression.charAt(i)+"";
                    System.out.println("Root:"+expression.charAt(i));
                    foundRoot = true;
                    i=i+2;
                }else if(!foundRoot){ //not the root. add it to the left string
                    left = left+expression.charAt(i)+"";
                    //System.out.println("left:"+left);
                }else if(foundRoot){
                    right = right+expression.charAt(i);
                    //System.out.println("right:"+right);
                }
                i++;
            }
            System.out.println("left:"+left);
            System.out.println("right:"+right);
          
            left = cleanString(left);
            right = cleanString(right);
            System.out.println("Operate on "+left);
            System.out.println("Operate on "+right);
            root = new Node(operator.charAt(0), createTree(left), createTree(right));
            return root;
        }
     
    }
    public String cleanString(String inString){
        String newString="";
        for(int i=0;i<inString.length(); i++){
            if(inString.charAt(i)!=' '){
                newString+=inString.charAt(i);
            }
        }
        System.out.println("sub clean string:"+newString);
        if(newString.charAt(0)=='(' && newString.charAt(newString.length()-1)==')'){
            newString = newString.substring(1, newString.length()-1);
        }
      
        System.out.println("clean string:"+newString);
        return newString;
    }
  
}
