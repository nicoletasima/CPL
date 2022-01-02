package cool.ast;

import org.antlr.v4.runtime.Token;

public class VarDef extends ASTNode {
    Token name;
    Token type;
    Expression init;

    public VarDef(Expression init, Token name, Token type) {
        this.init = init;
        this.name = name;
        this.type = type;
    }

    public VarDef(Token name, Token type) {
        this.name = name;
        this.type = type;
    }

    public Token getName() {
        return name;
    }

    public Token getType() {
        return type;
    }

    public Expression getInit() {
        return init;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}