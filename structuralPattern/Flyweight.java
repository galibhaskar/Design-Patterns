import java.util.*;

/* 

Problem:
    - Costlier memory consumption due to similar objects creation
    - intrinsic(similar attribute values) and extrinsic attributes

class Tree{
    private int x;
    
    private int y;

    private String name;

    private String color;

    private String texture;

    public Tree(int x, int y, String name, String color, String texture){
        this.x = x;

        this.y = y;
        
        this.name = name;

        this.color = color;

        this.texture = texture;
    }

    public void draw(){
        System.out.println("Drawing tree at x:"+x+" y:"+y
            +" with texture:"+texture+" name:"+name+" color:"+color);
    }
}

class Forest{
    private List<Tree> forest;

    public Forest(){
        forest = new ArrayList<>();
    }

    public void addTree(int x, int y, String name, String color, String texture){
        // creating tree object with same name, color and texture with different x & y
        Tree tree = new Tree(x, y, name, color, texture);

        forest.add(tree);
    }

    public void draw(){
        for(Tree tree:forest){
            tree.draw();
        }
    }
}

class Main{
    public static void main(String[] args) {
        Forest forest = new Forest();

        for(int index = 0 ; index < 100000; index++){
            forest.addTree(index, index, "OAK", "Green", "Rough");
        }

        forest.draw();
    }
}

*/

class TreeType{
    private String name;

    private String color;

    private String texture;

    public TreeType(String name, String color, String texture){
        this.name = name;

        this.color = color;

        this.texture = texture;
    }

    public void draw(int x, int y){
        System.out.println("Drawing tree at x:"+x+" y:"+y
            +" with texture:"+texture+" name:"+name+" color:"+color);
    }
}

class TreeTypeFactory{
    private static Map<String, TreeType> cache = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture){
        String key = name + "-" + color + "-" + texture;

        if(!cache.containsKey(key)){
            cache.put(key, new TreeType(name, color, texture));
        }

        return cache.get(key);
    }
}

class Tree{
    private int x;

    private int y;

    private TreeType treeType;

    public Tree(int x, int y, TreeType treeType){
        this.x = x;

        this.y = y;

        this.treeType = treeType;
    }

    public void draw(){
        treeType.draw(x, y);
    }
}

class Forest{
    private List<Tree> forest;

    public Forest(){
        forest = new ArrayList<>();
    }

    public void addTree(int x, int y, String name, String color, String texture){
        // memory efficient because treetype object is not created for every tree object
        Tree tree = new Tree(x, y, TreeTypeFactory.getTreeType(name, color, texture));

        forest.add(tree);
    }

    public void draw(){
        for(Tree tree: forest){
            tree.draw();
        }
    }
}

public class Flyweight {
    public static void main(String[] args) {
        Forest forest = new Forest();

        for(int index = 0 ; index < 100; index++){
            forest.addTree(index, index, "OAK", "Green", "Rough");
        }

        forest.draw();
    }
}
