package cool.structures;

import cool.ast.*;
import cool.parser.CoolParser;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class ResolutionPassVisitor implements ASTVisitor<ClassSymbol> {
    Scope currentScope;

    @Override
    public ClassSymbol visit(Formal formal) {
        return null;
    }

    @Override
    public ClassSymbol visit(Assign assign) {
        // b <- c -> am nev de tipurile lor
        var assignIdName = assign.getName().getText();
        var symbol = (IdSymbol) currentScope.lookup(assignIdName);

        if (symbol == null) {
            return null;
        }

        var exprType = assign.getE().accept(this);

        if (exprType == null || symbol.getType() == null) {
            return null;
        }

        if (!exprType.inheritsClass(symbol.getType())) {
            SymbolTable.error(assign.getContext(), assign.getE().getContext().start,
                    "Type " + exprType + " of assigned expression is incompatible with declared type " +
                    symbol.getType() + " of identifier " + assignIdName);
        }


        return exprType;
    }

    @Override
    public ClassSymbol visit(FuncDef funcDef) {
        if (funcDef.getSymbol() == null) {
            return null;
        }

        var methodName = funcDef.getName().getText();
        var type = funcDef.getSymbol().getType();
        var scope = currentScope;

        var parentClassMethod = ((ClassSymbol)scope.getParent()).lookupMethod(methodName);
        if (parentClassMethod != null) {
            /* metoda este suprascrisa in clasa parinte */
            if (funcDef.getSymbol().getFormals().size() != parentClassMethod.getFormals().size()) {
                SymbolTable.error(funcDef.getContext(), funcDef.getName(), "Class " + ((ClassSymbol)currentScope).getName() + " overrides method " +
                        methodName + " with different number of formal parameters");
                return null;
            }

            var parentClassMethodType = parentClassMethod.getType();
            if (type != parentClassMethodType) {
                SymbolTable.error(funcDef.getContext(), funcDef.getType(), "Class " + ((ClassSymbol)currentScope).getName() + " overrides method " +
                        methodName + " but changes return type from " + parentClassMethodType + " to " + type);
                return null;
            }

            var formals = funcDef.getSymbol().getFormals();
            var overridenMethodFormals = parentClassMethod.getFormals();
            Iterator<Map.Entry<String, IdSymbol>> iter1 = formals.entrySet().iterator();
            Iterator<Map.Entry<String, IdSymbol>> iter2 = overridenMethodFormals.entrySet().iterator();
            int i = -1;
            while (iter1.hasNext() || iter2.hasNext()) {
                i++;
                Map.Entry<String, IdSymbol> e1 = iter1.next();
                Map.Entry<String, IdSymbol> e2 = iter2.next();
                var symbol1 = e1.getValue().getType();
                var symbol2 = e2.getValue().getType();

                if (symbol1 != symbol2) {
                    SymbolTable.error(funcDef.getContext(), ((CoolParser.FuncDefContext)funcDef.getContext()).formals.get(i).stop, "Class " + ((ClassSymbol)currentScope).getName() + " overrides method " +
                            methodName + " but changes type of formal parameter " + e1.getValue().getName() + " from " + symbol2.getName() +
                            " to " + symbol1.getName());
                    return null;
                }
            }

        }

        currentScope = funcDef.getSymbol();
        ClassSymbol bodyType = null;
        for (var body: funcDef.getBody()) {
            bodyType = body.accept(this);
        }

        if (bodyType != null) {
            if (!this.inheritsTypeCheck(bodyType, type)) {
                SymbolTable.error(funcDef.getContext(), funcDef.getBody().getFirst().getToken(),
                        "Type " + bodyType + " of the body of method " + methodName + " is incompatible with declared return type " + type);
            }
         }

        currentScope = currentScope.getParent();

        return null;
    }

    @Override
    public ClassSymbol visit(VarDef varDef) {
        if (varDef.getSymbol() == null) {
            return null;
        }

        var varType = varDef.getType().getText();
        var varName = varDef.getName().getText();
        var varSymbol = varDef.getSymbol();

        var typeSymbol = (ClassSymbol)SymbolTable.globals.lookup(varType);

        if (typeSymbol == null) {
            SymbolTable.error(varDef.getContext(), varDef.getType(),
                    "Let variable " + varName + " has undefined type " + varType);
            varDef.setSymbol(null);
            return null;
        }

        var init = varDef.getInit();
        if (init != null) {
            var initType = init.accept(this);
            if (initType != null && !this.inheritsTypeCheck(initType, typeSymbol)) {
                SymbolTable.error(varDef.getContext(), init.getContext().start,
                        "Type " + initType + " of initialization expression of identifier " + varName +
                                " is incompatible with declared type " + varType);
                return null;
            }
        }

        varSymbol.setType((ClassSymbol) SymbolTable.globals.lookup(varType));

        return null;
    }

    @Override
    public ClassSymbol visit(ClassDef classDef) {
        if (classDef.getSymbol() == null) {
            return null;
        }

        currentScope = classDef.getSymbol();

        for (var classVar : classDef.getFeatures()) {
            classVar.accept(this);
        }
        return null;
    }

    @Override
    public ClassSymbol visit(Program program) {
        for (var stmt: program.getClasses()) {
            stmt.accept(this);
        }
        return null;
    }

    @Override
    public ClassSymbol visit(MemberDef memberDef) {
        var memberSymbol = memberDef.getSymbol();

        if (memberSymbol == null) {
            return null;
        }

        var memberType = memberDef.getSymbol().getType();

        var init = memberDef.getInit();
        if (init != null) {
            var initType = init.accept(this);

            if (!this.inheritsTypeCheck(initType, memberType)) {
                SymbolTable.error(memberDef.getContext(), memberDef.getInit().getToken(),
                        "Type " + initType + " of initialization expression of attribute " +
                        memberDef.getName().getText() + " is incompatible with declared type " + memberType);

            }
        }

        return memberType;
    }

    @Override
    public ClassSymbol visit(Int intt) {
        return ClassSymbol.INT;
    }

    @Override
    public ClassSymbol visit(Bool booll) {
        return ClassSymbol.BOOL;
    }

    @Override
    public ClassSymbol visit(StringNode string) {
        return ClassSymbol.STRING;
    }

    @Override
    public ClassSymbol visit(Div div) {
        var leftType = div.getLeft().accept(this);
        var rightType = div.getRight().accept(this);

        if (leftType == null || rightType == null) {
            return null;
        }

        if (leftType != ClassSymbol.INT) {
            SymbolTable.error(div.getContext(), div.getLeft().getContext().stop,
                    "Operand of / has type " + leftType + " instead of Int");
        }

        if (rightType != ClassSymbol.INT) {
            SymbolTable.error(div.getContext(), div.getRight().getContext().stop,
                    "Operand of / has type " + rightType + " instead of Int");
        }
        return ClassSymbol.INT;
    }

    @Override
    public ClassSymbol visit(Mult mult) {
        var leftType = mult.getLeft().accept(this);
        var rightType = mult.getRight().accept(this);

        if (leftType == null || rightType == null) {
            return null;
        }

        if (leftType != ClassSymbol.INT) {
            SymbolTable.error(mult.getContext(), mult.getLeft().getContext().stop,
                    "Operand of * has type " + leftType + " instead of Int");
        }

        if (rightType != ClassSymbol.INT) {
            SymbolTable.error(mult.getContext(), mult.getRight().getContext().stop,
                    "Operand of * has type " + rightType + " instead of Int");
        }
        return ClassSymbol.INT;
    }

    @Override
    public ClassSymbol visit(Plus plus) {
        var leftType = plus.getLeft().accept(this);
        var rightType = plus.getRight().accept(this);

        if (leftType == null || rightType == null) {
            return null;
        }

        if (leftType != ClassSymbol.INT) {
            SymbolTable.error(plus.getContext(), plus.getLeft().getContext().stop,
                    "Operand of + has type " + leftType + " instead of Int");
        }

        if (rightType != ClassSymbol.INT) {
            SymbolTable.error(plus.getContext(), plus.getRight().getContext().stop,
                    "Operand of + has type " + rightType + " instead of Int");
        }

        return ClassSymbol.INT;
    }

    @Override
    public ClassSymbol visit(Minus minus) {
        var leftType = minus.getLeft().accept(this);
        var rightType = minus.getRight().accept(this);

        if (leftType == null || rightType == null) {
            return null;
        }

        if (leftType != ClassSymbol.INT) {
            SymbolTable.error(minus.getContext(), minus.getLeft().getContext().stop,
                    "Operand of - has type " + leftType + " instead of Int");
        }

        if (rightType != ClassSymbol.INT) {
            SymbolTable.error(minus.getContext(), minus.getRight().getContext().stop,
                    "Operand of - has type " + rightType + " instead of Int");
        }

        return ClassSymbol.INT;
    }

    @Override
    public ClassSymbol visit(Less lt) {
        var leftType = lt.getLeft().accept(this);
        var rightType = lt.getRight().accept(this);

        if (leftType == null || rightType == null) {
            return null;
        }

        if (leftType != ClassSymbol.INT) {
            SymbolTable.error(lt.getContext(), lt.getLeft().getContext().stop,
                    "Operand of < has type " + leftType + " instead of Int");
        }

        if (rightType != ClassSymbol.INT) {
            SymbolTable.error(lt.getContext(), lt.getRight().getContext().stop,
                    "Operand of < has type " + rightType + " instead of Int");
        }
        return ClassSymbol.BOOL;
    }

    @Override
    public ClassSymbol visit(LessOrEqual le) {
        var leftType = le.getLeft().accept(this);
        var rightType = le.getRight().accept(this);

        if (leftType == null || rightType == null) {
            return null;
        }

        if (leftType != ClassSymbol.INT) {
            SymbolTable.error(le.getContext(), le.getLeft().getContext().stop,
                    "Operand of <= has type " + leftType + " instead of Int");
        }

        if (rightType != ClassSymbol.INT) {
            SymbolTable.error(le.getContext(), le.getRight().getContext().stop,
                    "Operand of <= has type " + rightType + " instead of Int");
        }
        return ClassSymbol.BOOL;
    }

    @Override
    public ClassSymbol visit(Equal eq) {
        var leftType = eq.getLeft().accept(this);
        var rightType = eq.getRight().accept(this);

        if (leftType == null || rightType == null) {
            return null;
        }

        if ((leftType == ClassSymbol.INT || leftType == ClassSymbol.BOOL || leftType == ClassSymbol.STRING)
            && (rightType == ClassSymbol.INT || rightType == ClassSymbol.BOOL || rightType == ClassSymbol.STRING)) {
            if (leftType != rightType) {
                SymbolTable.error(eq.getContext(), eq.getToken(),
                        "Cannot compare " + leftType + " with " + rightType);
            }
        }
        return ClassSymbol.BOOL;
    }

    @Override
    public ClassSymbol visit(Complement compl) {
        var type = compl.getOp().accept(this);

        if (type == null) {
            return null;
        }

        if (type != ClassSymbol.INT) {
            SymbolTable.error(compl.getContext(), compl.getContext().stop,
                    "Operand of ~ has type " + type + " instead of Int");
        }
        return ClassSymbol.INT;
    }

    @Override
    public ClassSymbol visit(Parentheses paren) {
        var type = paren.getOp().accept(this);
        return type;
    }

    @Override
    public ClassSymbol visit(Not not) {
        var type = not.getOp().accept(this);

        if (type != ClassSymbol.BOOL) {
            SymbolTable.error(not.getContext(), not.getContext().stop,
                    "Operand of not has type " + type + " instead of Bool");
        }
        return ClassSymbol.BOOL;
    }

    @Override
    public ClassSymbol visit(IsVoid isvoid) {
        return ClassSymbol.BOOL;
    }

    @Override
    public ClassSymbol visit(New neww) {
        var type = neww.getType().getText();

        if (SymbolTable.globals.lookup(type) == null) {
            SymbolTable.error(neww.getContext(), neww.getType(),
                    "new is used with undefined type " + type);
            return null;
        }
        return (ClassSymbol) SymbolTable.globals.lookup(type);
    }

    @Override
    public ClassSymbol visit(If iff) {
        var condType = iff.getCond().accept(this);

        if (condType != ClassSymbol.BOOL) {
            SymbolTable.error(iff.getContext(), iff.getCond().getToken(),
                    "If condition has type " + condType + " instead of Bool");
        }


        var thenBranchType = iff.getThenBranch().accept(this);
        var elseBranchType = iff.getElseBranch().accept(this);

        return this.lowestCommonAncestor(thenBranchType, elseBranchType);
    }

    @Override
    public ClassSymbol visit(Block block) {
        ClassSymbol type = null;

        for (var instr: block.getInstructions()) {
            type = instr.accept(this);
        }

        return type;
    }

    @Override
    public ClassSymbol visit(While whilee) {
        var condType = whilee.getCond().accept(this);

        if (condType != ClassSymbol.BOOL) {
            SymbolTable.error(whilee.getContext(), whilee.getCond().getToken(),
                    "While condition has type " + condType + " instead of Bool");

        }

        return ClassSymbol.OBJECT;
    }

    @Override
    public ClassSymbol visit(Let let) {
        currentScope = let.getSymbol();

        for (var v: let.getVars()) {
            v.accept(this);

            var symbol = v.getSymbol();
            if (symbol != null) {
                currentScope.add(symbol);
            }
        }

        var type = let.getBody().accept(this);

        currentScope = currentScope.getParent();

        return type;
    }

    @Override
    public ClassSymbol visit(ImplicitDispatch implicitDispatch) {
        return null;
    }

    @Override
    public ClassSymbol visit(ExplicitDispatch explicitDispatch) {
//        Symbol scope;
//        String className;
//        var explicitDispatchType = explicitDispatch.getType();
//
//        if (explicitDispatchType != null) {
//            if (SymbolTable.globals.lookup(explicitDispatchType.getText()) == null) {
//                SymbolTable.error(explicitDispatch.getContext(), explicitDispatchType,
//                        "Type " + explicitDispatchType.getText() + " of static dispatch is undefined");
//                return null;
//            }
//        }
//
//        if (explicitDispatchType != null) {
//            className = explicitDispatchType.getText();
//        } else {
//            className = explicitDispatch.getCall().accept(this).getName();
//        }
//        scope = SymbolTable.globals.lookup(className);
//
//        if (scope == null) {
//            SymbolTable.error(explicitDispatch.getContext(), explicitDispatch.getCall().getToken(),
//                    "Undefined method " + explicitDispatch.getName().getText() +
//                    "in class " + className);
//        }


        return null;
    }

    @Override
    public ClassSymbol visit(Case casee) {

        var condType = casee.getCond().accept(this);

        var types = new LinkedList<ClassSymbol>();

        for (var caseBranch: casee.getCases()) {
            var type = caseBranch.accept(this);
            if (type != null) {
                types.add(caseBranch.accept(this));
            }
        }

        while (!this.hasDistinctElements(types)) {
            var tmp = new LinkedList<ClassSymbol>();

            for (int i = 0; i < types.size() - 1; i++) {
                for (int j = i + 1; j < types.size(); j++) {
                    tmp.add(this.lowestCommonAncestor(types.get(i), types.get(j)));
                }
            }

            types = tmp;
        }

        return types.get(0);
    }

    @Override
    public ClassSymbol visit(CaseBranch caseB) {
        var caseVarName = caseB.getName().getText();
        var caseTypeName = caseB.getType().getText();
        var typeSymbol = SymbolTable.globals.lookup(caseTypeName);

        if (typeSymbol == null) {
            SymbolTable.error(caseB.getContext(), caseB.getType(),
                    "Case variable " + caseVarName + " has undefined type " + caseTypeName);
            return null;
        }

        var scope = new CaseSymbol(currentScope);
        var idSymbol = new IdSymbol(caseVarName);

        idSymbol.setType((ClassSymbol) typeSymbol);
        scope.add(idSymbol);

        currentScope = scope;

        var bodyType = caseB.getBody().accept(this);
        currentScope = currentScope.getParent();
        return bodyType;
    }

    @Override
    public ClassSymbol visit(Id id) {
        var idName = id.getToken().getText();

        if (idName.equals("self")) {
            return null;
        }

        var idSymbol = (IdSymbol)currentScope.lookup(idName);

        if (idSymbol == null) {
            SymbolTable.error(id.getContext(), id.getContext().stop,
                        "Undefined identifier " + id.getToken().getText());
            return null;
        }


        return idSymbol.getType();
    }

    @Override
    public ClassSymbol visit(Type type) {
        return null;
    }

    public boolean hasDistinctElements(LinkedList<ClassSymbol> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i) != list.get(j)) {
                    return false;
                }
            }
        }

        return true;
    }

    public ClassSymbol lowestCommonAncestor(ClassSymbol t1, ClassSymbol t2) {
        var parents = new LinkedList<ClassSymbol>();

        while (t1 != null) {
            parents.add(t1);
            t1 = t1.getInheritsType();
        }

        while (t2 != null) {
            if (parents.contains(t2)) {
                return t2;
            }

            t2 = t2.getInheritsType();
        }

        return ClassSymbol.OBJECT;

    }

    public boolean inheritsTypeCheck(ClassSymbol t1, ClassSymbol t2) {
        if (t1 == null || t2 == null) {
            return false;
        }

        while (t1 != null) {
            if (t1 == t2) {
                return true;
            }

            t1 = t1.getInheritsType();
        }

        return false;
    }
}
