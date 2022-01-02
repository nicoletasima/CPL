package cool.ast;

import org.antlr.v4.runtime.Token;

public class Div extends Expression {
  Expression left;
  Expression right;

  public Div(Token token, Expression left, Expression right) {
    super(token);
    this.left = left;
    this.right = right;
  }

  public Expression getLeft() {
    return left;
  }

  public Expression getRight() {
    return right;
  }

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
