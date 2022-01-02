package cool.ast;

import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public abstract class ASTNode {
    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }
}















