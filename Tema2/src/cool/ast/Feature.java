package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public abstract class Feature extends ASTNode {
  Feature(Token token, ParserRuleContext context) {
    super(token, context);
  }
}
