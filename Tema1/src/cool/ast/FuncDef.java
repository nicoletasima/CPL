package cool.ast;

import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public class FuncDef extends Feature {
    Token name;
    Token type;
    LinkedList<Formal> formals;
    LinkedList<Expression> body;

    public FuncDef(LinkedList<Formal> formals, LinkedList<Expression> body, Token type, Token name) {
        this.name = name;
        this.type = type;
        this.body = body;
        this.formals = formals;
    }

    public Token getName() {
        return name;
    }

    public Token getType() {
        return type;
    }

    public LinkedList<Formal> getFormals() {
        return formals;
    }

    public LinkedList<Expression> getBody() {
        return body;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
