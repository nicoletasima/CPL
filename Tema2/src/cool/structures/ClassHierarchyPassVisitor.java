package cool.structures;

import cool.ast.*;
import org.antlr.v4.runtime.Token;

public class ClassHierarchyPassVisitor implements ASTVisitor<Void> {
  Scope currentScope = null;

  @Override
  public Void visit(Formal formal) {
    if (formal.getSymbol() == null) {
      return null;
    }

    var formalName = formal.getName().getText();
    var formalType = formal.getType().getText();
    var scope = ((MethodSymbol)currentScope);

    if (SymbolTable.globals.lookup(formalType) == null) {
      SymbolTable.error(formal.getContext(), formal.getType(), "Method " + scope.getName() + " of class " + ((ClassSymbol)scope.getParent()).getName() +
              " has formal parameter " + formalName + " with undefined type " + formalType);
      return null;
    }

    var type = SymbolTable.globals.lookup(formalType);
    (formal.getSymbol()).setType((ClassSymbol) type);

    return null;
  }

  @Override
  public Void visit(Assign assign) {
    return null;
  }

  @Override
  public Void visit(FuncDef funcDef) {
    if (funcDef.getSymbol() == null) {
      return null;
    }
    var methodName = funcDef.getName().getText();
    var methodTypeName = funcDef.getType().getText();

    var typeSymbol = SymbolTable.globals.lookup(methodTypeName);
    if (typeSymbol == null) {
      SymbolTable.error(funcDef.getContext(), funcDef.getType(), "Class " + ((ClassSymbol)currentScope).getName() +
              " has method " + methodName + " with undefined return type " + methodTypeName);
      return null;
    }

    funcDef.getSymbol().setType((ClassSymbol) typeSymbol);
    currentScope = funcDef.getSymbol();

    for (var formal: funcDef.getFormals()) {
      formal.accept(this);
    }

    for (var body: funcDef.getBody()) {
      body.accept(this);
    }
    currentScope = currentScope.getParent();
    return null;
  }

  @Override
  public Void visit(VarDef varDef) {
    return null;
  }

  @Override
  public Void visit(ClassDef classDef) {
    var className = classDef.getSymbol();
    Symbol parentName;

    if (className == null) {
      return null;
    }

    while (className != ClassSymbol.OBJECT) {
      parentName = SymbolTable.globals.lookup(className.getParentName());
      if (parentName == null) {
        SymbolTable.error(classDef.getContext(), classDef.getParentType(),
                "Class " + classDef.getType().getText() + " has undefined parent " + classDef.getParentType().getText());
        break;
      }

      className.setParent((ClassSymbol) parentName);
      className = (ClassSymbol) className.getParent();

      if (className == classDef.getSymbol()) {
        SymbolTable.error(classDef.getContext(), classDef.getType(), "Inheritance cycle for class " + classDef.getType().getText());
        break;
      }

    }
    currentScope = classDef.getSymbol();
    for (var feature : classDef.getFeatures()) {
      feature.accept(this);
    }

    return null;
  }

  @Override
  public Void visit(Program program) {

    for (var stmt: program.getClasses()) {
      stmt.accept(this);
    }
    return null;
  }

  @Override
  public Void visit(MemberDef memberDef) {
    var idSymbol = memberDef.getSymbol();

    if (idSymbol == null) {
      return null;
    }

    var name = memberDef.getName().getText();
    var type = memberDef.getType().getText();

    var parentScope = currentScope.getParent();
    if (parentScope.lookup(name) != null) {
      SymbolTable.error(memberDef.getContext(), memberDef.getName(), "Class " + ((ClassSymbol)currentScope).getName() +
              " redefines inherited attribute " + name);
      memberDef.setSymbol(null);
      return null;
    }

    if (SymbolTable.globals.lookup(type) == null) {
      SymbolTable.error(memberDef.getContext(), memberDef.getType(), "Class " + ((ClassSymbol)currentScope).getName() +
              " has attribute " + name + " with undefined type " + type);
      memberDef.setSymbol(null);
      return null;
    }

    memberDef.getSymbol().setType((ClassSymbol)SymbolTable.globals.lookup(type));
    return null;
  }

  @Override
  public Void visit(Int intt) {
    return null;
  }

  @Override
  public Void visit(Bool booll) {
    return null;
  }

  @Override
  public Void visit(StringNode string) {
    return null;
  }

  @Override
  public Void visit(Div div) {
    return null;
  }

  @Override
  public Void visit(Mult mult) {
    return null;
  }

  @Override
  public Void visit(Plus plus) {
    return null;
  }

  @Override
  public Void visit(Minus minus) {
    return null;
  }

  @Override
  public Void visit(LessOrEqual le) {
    return null;
  }

  @Override
  public Void visit(Less lt) {
    return null;
  }

  @Override
  public Void visit(Equal eq) {
    return null;
  }

  @Override
  public Void visit(Complement compl) {
    return null;
  }

  @Override
  public Void visit(Parentheses paren) {
    return null;
  }

  @Override
  public Void visit(Not not) {
    return null;
  }

  @Override
  public Void visit(IsVoid isvoid) {
    return null;
  }

  @Override
  public Void visit(New neww) {
    return null;
  }

  @Override
  public Void visit(If iff) {
    return null;
  }

  @Override
  public Void visit(Block block) {
    return null;
  }

  @Override
  public Void visit(While whilee) {
    return null;
  }

  @Override
  public Void visit(Let let) {
    return null;
  }

  @Override
  public Void visit(ImplicitDispatch implicitDispatch) {
    return null;
  }

  @Override
  public Void visit(ExplicitDispatch explicitDispatch) {
    return null;
  }

  @Override
  public Void visit(Case casee) {
    return null;
  }

  @Override
  public Void visit(CaseBranch caseB) {
    return null;
  }

  @Override
  public Void visit(Id id) {
    return null;
  }

  @Override
  public Void visit(Type type) {
    return null;
  }
}
