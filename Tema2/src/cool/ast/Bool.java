package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Bool extends Expression {

  public Bool(Token token, ParserRuleContext context) {
    super(token, context);
  }

  public <T> T accept(ASTVisitor<T> visitor) { return visitor.visit(this); }
}
