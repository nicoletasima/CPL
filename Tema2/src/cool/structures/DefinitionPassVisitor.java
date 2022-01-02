package cool.structures;

import cool.ast.*;

import javax.swing.plaf.IconUIResource;

public class DefinitionPassVisitor implements ASTVisitor<Void> {
  Scope currentScope = null;

  @Override
  public Void visit(Formal formal) {
    var formalName = formal.getName().getText();
    var formalTypeName = formal.getType().getText();
    var scope = ((MethodSymbol)currentScope);

    if (formalName.equals("self")) {
      SymbolTable.error(formal.getContext(), formal.getName(), "Method " + scope.getName() + " of class " +
              ((ClassSymbol)scope.getParent()).getName() + " has formal parameter with illegal name self");
      return null;
    }

    if (formalTypeName.equals("SELF_TYPE")) {
      SymbolTable.error(formal.getContext(), formal.getType(), "Method " + scope.getName() + " of class " +
              ((ClassSymbol)scope.getParent()).getName() + " has formal parameter " + formalName + " with illegal type SELF_TYPE");
      return null;
    }

    var formalSymbol = new IdSymbol(formalName);

    if (!scope.add(formalSymbol)) {
      SymbolTable.error(formal.getContext(), formal.getName(), "Method " + scope.getName() + " of class " + ((ClassSymbol)scope.getParent()).getName() +
              " redefines formal parameter " + formalName);
      return null;
    }

    formal.setSymbol(formalSymbol);

    return null;
  }

  @Override
  public Void visit(Assign assign) {
    if (assign.getName().getText().equals("self")) {
      SymbolTable.error(assign.getContext(), assign.getName(),
              "Cannot assign to self");
      return null;
    }

    assign.getE().accept(this);
    return null;
  }

  @Override
  public Void visit(FuncDef funcDef) {
    var methodName = funcDef.getName().getText();
    var methodSymbol = new MethodSymbol(methodName);
    methodSymbol.setParent((ClassSymbol)currentScope);

    if (!currentScope.add(methodSymbol)) {
      SymbolTable.error(funcDef.getContext(), funcDef.getName(), "Class " + ((ClassSymbol)currentScope).getName() +
              " redefines method " + methodName);
      return null;
    }

    funcDef.setSymbol(methodSymbol);
    currentScope = methodSymbol;

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
    var varName = varDef.getName().getText();

      if (varName.equals("self")) {
        SymbolTable.error(varDef.getContext(), varDef.getName(), "Let variable has illegal name self");
        return null;
      }

    var varSymbol = new IdSymbol(varName);
    varDef.setSymbol(varSymbol);

    var init = varDef.getInit();
    if (init != null) {
      init.accept(this);
    }

    return null;
  }

  @Override
  public Void visit(ClassDef classDef) {
    var className = classDef.getType().getText();

    if (className.equals("SELF_TYPE")) {
      SymbolTable.error(classDef.getContext(), classDef.getType(), "Class has illegal name SELF_TYPE");
      return null;
    }

    var parent = classDef.getParentType();
    String parentName;

    if (parent != null) {
      parentName = parent.getText();
    } else {
      parentName = "Object";
    }

    var typeSymbol = new ClassSymbol(className, parentName);

    if (!SymbolTable.globals.add(typeSymbol)) {
      SymbolTable.error(classDef.getContext(), classDef.getType(), "Class " + className + " is redefined");
      return null;
    }

    if (classDef.getParentType() != null) {
      parentName = classDef.getParentType().getText();

      if (parentName.equals("Int") || parentName.equals("Bool")
              || parentName.equals("String") || parentName.equals("SELF_TYPE")) {
        SymbolTable.error(classDef.getContext(), classDef.getParentType(),"Class " + className + " has illegal parent " + parentName);
        return null;
      }
    }

    classDef.setSymbol(typeSymbol);
    currentScope = typeSymbol;

    for (var feature: classDef.getFeatures()) {
      feature.accept(this);
    }
    return null;
  }

  @Override
  public Void visit(Program program) {
    for (var stmt: program.getClasses())
      stmt.accept(this);
    return null;
  }

  @Override
  public Void visit(MemberDef memberDef) {
    var attributeName = memberDef.getName().getText();

    if (attributeName.equals("self")) {
      SymbolTable.error(memberDef.getContext(), memberDef.getName(), "Class " + ((ClassSymbol)currentScope).getName() +
              " has attribute with illegal name " + attributeName);
    return null;
    }

    var idSymbol = new IdSymbol(attributeName);

    if (!currentScope.add(idSymbol)) {
      SymbolTable.error(memberDef.getContext(), memberDef.getName(), "Class " + ((ClassSymbol)currentScope).getName() +
              " redefines attribute " + attributeName);
      return null;
    }

    memberDef.setSymbol(idSymbol);

    if (memberDef.getInit() != null) {
      memberDef.getInit().accept(this);
    }
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
  public Void visit(Id id) {
    return null;
  }

  @Override
  public Void visit(Type type) {
    return null;
  }

  @Override
  public Void visit(Div div) {
    div.getLeft().accept(this);
    div.getRight().accept(this);
    return null;
  }

  @Override
  public Void visit(Mult mult) {
    mult.getLeft().accept(this);
    mult.getRight().accept(this);
    return null;
  }

  @Override
  public Void visit(Plus plus) {
    plus.getLeft().accept(this);
    plus.getRight().accept(this);
    return null;
  }

  @Override
  public Void visit(Minus minus) {
    minus.getLeft().accept(this);
    minus.getRight().accept(this);
    return null;
  }

  @Override
  public Void visit(Less lt) {
    lt.getLeft().accept(this);
    lt.getRight().accept(this);
    return null;
  }

  @Override
  public Void visit(LessOrEqual le) {
    le.getLeft().accept(this);
    le.getRight().accept(this);
    return null;
  }

  @Override
  public Void visit(Equal eq) {
    eq.getLeft().accept(this);
    eq.getRight().accept(this);
    return null;
  }

  @Override
  public Void visit(Complement compl) {
    compl.getOp().accept(this);
    return null;
  }

  @Override
  public Void visit(Parentheses paren) {
    paren.getOp().accept(this);
    return null;
  }

  @Override
  public Void visit(Not not) {
    not.getOp().accept(this);
    return null;
  }

  @Override
  public Void visit(IsVoid isvoid) {
    isvoid.getOp().accept(this);
    return null;
  }

  @Override
  public Void visit(New neww) {
    return null;
  }

  @Override
  public Void visit(If iff) {
    iff.getCond().accept(this);
    iff.getThenBranch().accept(this);
    iff.getElseBranch().accept(this);
    return null;
  }

  @Override
  public Void visit(Block block) {
    for (var instr : block.getInstructions()) {
      instr.accept(this);
    }
    return null;
  }

  @Override
  public Void visit(While whilee) {
    whilee.getCond().accept(this);
    whilee.getBody().accept(this);
    return null;
  }

  @Override
  public Void visit(Let let) {
    var letSymbol = new LetSymbol(currentScope);
    let.setSymbol(letSymbol);

    currentScope = let.getSymbol();

    for (var v : let.getVars()) {
      v.accept(this);
    }

    let.getBody().accept(this);

    currentScope = currentScope.getParent();
    return null;
  }

  @Override
  public Void visit(ImplicitDispatch implicitDispatch) {
    for (var param: implicitDispatch.getParams()) {
      param.accept(this);
    }
    return null;
  }

  @Override
  public Void visit(ExplicitDispatch explicitDispatch) {

    if (explicitDispatch.getType() != null && explicitDispatch.getType().getText().equals("SELF_TYPE")) {
      SymbolTable.error(explicitDispatch.getContext(), explicitDispatch.getType(),
              "Type of static dispatch cannot be SELF_TYPE" );
      return null;
    }

    explicitDispatch.getCall().accept(this);

    for (var param: explicitDispatch.getParams()) {
      param.accept(this);
    }
    return null;
  }

  @Override
  public Void visit(Case casee) {
    for (var caseBranch : casee.getCases()) {
      caseBranch.accept(this);
    }
    return null;
  }

  @Override
  public Void visit(CaseBranch caseB) {
    var caseVarName = caseB.getName().getText();
    var caseVarType = caseB.getType().getText();

    if (caseVarName.equals("self")) {
      SymbolTable.error(caseB.getContext(), caseB.getName(),
              "Case variable has illegal name self");
      return null;
    }

    if (caseVarType.equals("SELF_TYPE")) {
      SymbolTable.error(caseB.getContext(), caseB.getType(),
              "Case variable " + caseVarName + " has illegal type SELF_TYPE");
      return null;
    }

    caseB.getBody().accept(this);
    return null;
  }
}
