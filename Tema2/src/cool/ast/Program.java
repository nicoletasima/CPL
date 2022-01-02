package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public class Program extends ASTNode {
    LinkedList<ClassDef> classes;

    public Program(LinkedList<ClassDef> classes, Token token, ParserRuleContext context) {
        super(token, context);
        this.classes = classes;
    }

    public LinkedList<ClassDef> getClasses() {
        return classes;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}