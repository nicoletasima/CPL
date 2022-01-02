package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Minus extends Expression {
  Expression left;
  Expression right;

  public Minus(Token token, Expression left, Expression right, ParserRuleContext context) {
    super(token, context);
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
