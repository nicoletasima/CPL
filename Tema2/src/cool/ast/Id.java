package cool.ast;

import cool.structures.IdSymbol;
import cool.structures.Scope;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Id extends Expression {
  protected Scope scope;
  protected IdSymbol symbol;

  public Id(Token token, ParserRuleContext context) {
    super(token, context);
  }

  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }

  public IdSymbol getSymbol() {
    return symbol;
  }

  public void setSymbol(IdSymbol symbol) {
    this.symbol = symbol;
  }

  public Scope getScope() {
    return scope;
  }

  public void setScope(Scope scope) {
    this.scope = scope;
  }
}
