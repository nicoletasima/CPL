package cool.ast;

import org.antlr.v4.runtime.Token;

public class New extends Expression {
  Token type;

  public New(Token token, Token type) {
    super(token);
    this.type = type;
  }

  public Token getType() {
    return type;
  }

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
