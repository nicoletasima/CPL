package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public abstract class Expression extends ASTNode {

    public Expression(Token token, ParserRuleContext context) {
        super(token, context);
    }

    public Token getToken() {
        return token;
    }
}
