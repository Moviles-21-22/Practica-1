package es.ucm.arblemar.gamelogic;

public class MyPair<L,R> {

    private final L left;
    private final R right;

    public MyPair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L getLeft() { return left; }
    public R getRight() { return right; }

    @Override
    public int hashCode() { return left.hashCode() ^ right.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MyPair)) return false;
        MyPair pairo = (MyPair) o;
        return this.left.equals(pairo.getLeft()) &&
                this.right.equals(pairo.getRight());
    }

}