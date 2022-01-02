package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Complement extends Expression{
  Expression op;

  public Complement(Token token, Expression op, ParserRuleContext context) {
    super(token, context);
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
