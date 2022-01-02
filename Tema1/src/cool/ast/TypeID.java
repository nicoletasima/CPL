package cool.ast;

import org.antlr.v4.runtime.Token;

public class TypeID extends Expression {

  public TypeID(Token token) {
    super(token);
  }

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
