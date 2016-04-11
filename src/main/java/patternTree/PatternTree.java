package patternTree;
import trainingSet.CorrectUrl;

import java.util.List;

/**
 * Created by navid
 */

public class PatternTree<T> {
    private PatternTreeNode<T> root;

    public PatternTree(PatternTreeNode<T> rootNode) {
        root = rootNode;
    }

    public String toString(){
        //TODO
        return null;
    }


//    public void clear(){
//        root = null;
//    }

}

class PatternTreeNode<T> {
    private T data;
    private PatternTreeNode<T> parent;
    private List<PatternTreeNode<T>> children;
    private List<CorrectUrl> urlList;

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public PatternTreeNode<T> getParent() {
        return parent;
    }
    public void setParent(PatternTreeNode<T> parent) {
        this.parent = parent;
    }

    public List<PatternTreeNode<T>> getChildren() {
        return children;
    }
    public void setChildren(List<PatternTreeNode<T>> children) {
        this.children = children;
    }

    public void addChild(PatternTreeNode<T> child) {
        this.children.add(0,child);
    }

    public PatternTreeNode( List<CorrectUrl> urlList, PatternTreeNode<T> parent) {
        this.parent = parent;
        this.urlList = urlList;
    }
}

class LinkEdge<T> {
    private PatternTreeNode<T> startNode;
    private PatternTreeNode<T> endNode;

}
