package cool.ast;


import org.antlr.v4.runtime.Token;
import java.util.LinkedList;

public class ClassDef extends ASTNode {
    Token type;
    Token parentType;
    LinkedList<Feature> features;

    public ClassDef(LinkedList<Feature> features, Token type, Token parentType) {
        this.features = features;
        this.type = type;
        this.parentType = parentType;
    }

    public Token getType() {
        return type;
    }

    public Token getParentType() {
        return parentType;
    }

    public LinkedList<Feature> getFeatures() {
        return features;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}