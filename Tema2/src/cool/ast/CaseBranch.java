package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class CaseBranch extends Expression {
  protected Expression body;
  protected Token name;
  protected Token type;

  public CaseBranch(Expression body, Token name, Token type, Token token, ParserRuleContext context) {
    super(token, context);
    this.body = body;
    this.name = name;
    this.type = type;
  }

  public Token getName() {
    return name;
  }

  public Token getType() {
    return type;
  }

  public Expression getBody() {
    return body;
  }

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
