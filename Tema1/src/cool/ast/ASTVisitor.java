package cool.ast;

public interface ASTVisitor<T> {
    T visit(Formal formal);
    T visit(Assign assign);
    T visit(FuncDef funcDef);
    T visit(VarDef varDef);
    T visit(ClassDef classDef);
    T visit(Program program);
    T visit(MemberDef memberDef);
    T visit(Int intt);
    T visit(Bool booll);
    T visit(StringNode string);
    T visit(NameID nameId);
    T visit(TypeID typeId);
    T visit(Div div);
    T visit(Mult mult);
    T visit(Plus plus);
    T visit(Minus minus);
    T visit(LessOrEqual le);
    T visit(Less lt);
    T visit(Equal eq);
    T visit(Complement compl);
    T visit(Parentheses paren);
    T visit(Not not);
    T visit(IsVoid isvoid);
    T visit(New neww);
    T visit(If iff);
    T visit(Block block);
    T visit(While whilee);
    T visit(Let let);
    T visit(ImplicitDispatch implicitDispatch);
    T visit(ExplicitDispatch explicitDispatch);
    T visit(Case casee);
    T visit(CaseBranch caseB);
}
