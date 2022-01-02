package cool.ast;


import cool.structures.ClassSymbol;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import java.util.LinkedList;

public class ClassDef extends ASTNode {
    Token type;
    Token parentType;
    LinkedList<Feature> features;
    ClassSymbol symbol;

    public ClassDef(LinkedList<Feature> features, Token type, Token parentType, Token token, ParserRuleContext context) {
        super(token, context);
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

    public void setType(Token type) {
        this.type = type;
    }

    public void setParentType(Token parentType) {
        this.parentType = parentType;
    }

    public void setFeatures(LinkedList<Feature> features) {
        this.features = features;
    }

    public ClassSymbol getSymbol() {
        return symbol;
    }

    public void setSymbol(ClassSymbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}