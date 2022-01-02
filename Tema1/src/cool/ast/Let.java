package cool.ast;

import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public class Let extends Expression {
  LinkedList<VarDef> vars;
  Expression body;

  public Let(Token token, LinkedList<VarDef> vars, Expression body) {
    super(token);
    this.vars = vars;
    this.body = body;
  }

  public LinkedList<VarDef> getVars() {
    return vars;
  }

  public Expression getBody() {
    return body;
  }

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
