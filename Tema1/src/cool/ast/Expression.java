package cool.ast;

import org.antlr.v4.runtime.Token;

public abstract class Expression extends ASTNode {
    Token token;

    public Expression(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }
}
