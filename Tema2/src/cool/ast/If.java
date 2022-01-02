package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class If extends Expression {
  Expression cond;
  Expression thenBranch;
  Expression elseBranch;

  public If(Token token, Expression cond, Expression thenBranch, Expression elseBranch,
            ParserRuleContext context) {
    super(token, context);
    this.cond = cond;
    this.thenBranch = thenBranch;
    this.elseBranch = elseBranch;
  }

  public Expression getCond() {
    return cond;
  }

  public Expression getThenBranch() {
    return thenBranch;
  }

  public Expression getElseBranch() {
    return elseBranch;
  }

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }

}
