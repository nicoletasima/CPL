package cool.ast;

import org.antlr.v4.runtime.Token;

public class Parentheses extends Expression {
  Expression op;

  public Parentheses(Token token, Expression op) {
    super(token);
    this.op = op;
  }

  public Expression getOp() {
    return op;
  }

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
