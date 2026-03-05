package Flyweight;

class Tree {
    private final int x; // unique
    private final int y; // unique
    private final TreeType treeType; // shared reference

    public Tree(int x, int y, TreeType treeType) {
        this.x = x;
        this.y = y;
        this.treeType = treeType;
    }

    public void render() {
        treeType.render(x, y); // pass extrinsic state at runtime
    }
}