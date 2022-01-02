package cool.ast;

import cool.structures.IdSymbol;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Formal extends ASTNode {
    protected Token name;
    protected Token type;
    protected IdSymbol symbol;

    public Formal(Token token, Token name, Token type, ParserRuleContext context) {
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

    public void setName(Token name) {
        this.name = name;
    }

    public void setType(Token type) {
        this.type = type;
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