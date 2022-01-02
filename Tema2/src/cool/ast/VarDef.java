package cool.ast;

import cool.structures.IdSymbol;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class VarDef extends ASTNode {
    protected Token name;
    protected Token type;
    protected Expression init;
    protected IdSymbol symbol;

    public VarDef(Expression init, Token name, Token type, Token token, ParserRuleContext context) {
        super(token, context);
        this.init = init;
        this.name = name;
        this.type = type;
    }

    public VarDef(Token name, Token type, Token token, ParserRuleContext context) {
        super(token, context);
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

    public void setName(Token name) {
        this.name = name;
    }

    public void setType(Token type) {
        this.type = type;
    }

    public void setInit(Expression init) {
        this.init = init;
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