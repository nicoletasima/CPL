package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Type extends ASTNode {
  public Type(Token token, ParserRuleContext context) {
    super(token, context);
  }

  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
