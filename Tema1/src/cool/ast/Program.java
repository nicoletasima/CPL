package cool.ast;

import java.util.LinkedList;

public class Program extends ASTNode {
    LinkedList<ClassDef> classes;

    public Program(LinkedList<ClassDef> classes) {
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