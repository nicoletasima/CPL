package cool.ast;

import org.antlr.v4.runtime.Token;

public class StringNode extends Expression {

  public StringNode(Token token) {
    super(token);
  }

  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
