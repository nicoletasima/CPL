package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class While extends Expression {
  Expression cond;
  Expression body;

  public While(Token token, Expression cond, Expression body, ParserRuleContext context) {
    super(token, context);
    this.cond = cond;
    this.body = body;
  }

  public Expression getCond() {
    return cond;
  }

  public Expression getBody() {
    return body;
  }

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
