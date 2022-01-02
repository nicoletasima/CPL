package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public abstract class ASTNode {
    protected Token token;
    protected ParserRuleContext context;

    ASTNode(Token token, ParserRuleContext context) {
        this.token = token;
        this.context = context;
    }

    public Token getToken() {
        return token;
    }

    public ParserRuleContext getContext() {
        return context;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }
}















