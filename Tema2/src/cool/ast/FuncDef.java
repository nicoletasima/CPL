package cool.ast;

import cool.structures.MethodSymbol;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public class FuncDef extends Feature {
    protected Token name;
    protected Token type;
    protected LinkedList<Formal> formals;
    protected LinkedList<Expression> body;
    protected MethodSymbol symbol;

    public FuncDef(LinkedList<Formal> formals, LinkedList<Expression> body, Token type, Token name,
                   Token token, ParserRuleContext context) {
        super(token, context);
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

    public MethodSymbol getSymbol() {
        return symbol;
    }

    public void setSymbol(MethodSymbol symbol) {
        this.symbol = symbol;
    }

    public void setName(Token name) {
        this.name = name;
    }

    public void setType(Token type) {
        this.type = type;
    }

    public void setFormals(LinkedList<Formal> formals) {
        this.formals = formals;
    }

    public void setBody(LinkedList<Expression> body) {
        this.body = body;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
