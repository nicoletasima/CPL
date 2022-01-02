package cool.ast;

import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public class Case extends Expression {
  Expression cond;
  LinkedList<Expression> cases;

  public Case(Token token, Expression cond, LinkedList<Expression> cases) {
    super(token);
    this.cond = cond;
    this.cases = cases;
  }

  public Expression getCond() {
    return cond;
  }

  public LinkedList<Expression> getCases() {
    return cases;
  }

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
