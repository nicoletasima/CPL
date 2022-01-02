package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Assign extends Expression {
    Expression e;
    Token name;

    public Assign(Token start, Token name, Expression e, ParserRuleContext context) {
        super(start, context);
        this.e = e;
        this.name = name;
    }

    public Expression getE() {
        return e;
    }

    public Token getName() {
        return name;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}