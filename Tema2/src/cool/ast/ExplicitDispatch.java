package cool.ast;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public class ExplicitDispatch extends Expression {
  Expression call;
  LinkedList<Expression> params;
  Token type;
  Token name;

  public ExplicitDispatch(Token token, Expression call, LinkedList<Expression> params,
                          Token type, Token name, ParserRuleContext context) {
    super(token, context);
    this.call = call;
    this.params = params;
    this.type = type;
    this.name = name;
  }
  public ExplicitDispatch(Token token, Expression call, LinkedList<Expression> params,
                          Token name, ParserRuleContext context) {
    super(token, context);
    this.call = call;
    this.params = params;
    this.name = name;
  }

  public Token getName() {
    return name;
  }

  public Token getType() {
    return type;
  }

  public Expression getCall() {
    return call;
  }

  public LinkedList<Expression> getParams() {
    return params;
  }

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
