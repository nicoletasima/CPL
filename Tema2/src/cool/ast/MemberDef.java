package cool.ast;

import cool.structures.IdSymbol;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class MemberDef extends Feature {
    Token name;
    Token type;
    Expression init;
    private IdSymbol symbol;

    public MemberDef(Token name, Token type, Token token, ParserRuleContext context) {
        super(token, context);
        this.name = name;
        this.type = type;
    }

    public MemberDef(Expression init, Token name, Token type, Token token, ParserRuleContext context) {
        super(token, context);
        this.init = init;
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

    public IdSymbol getSymbol() {
        return symbol;
    }

    public void setSymbol(IdSymbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}