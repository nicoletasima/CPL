package cool.ast;

import org.antlr.v4.runtime.Token;

public class Formal extends ASTNode {
    Token name;
    Token type;

    public Formal(Token name, Token type) {
        this.name = name;
        this.type = type;
    }

    public Token getName() {
        return name;
    }

    public Token getType() {
        return type;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}