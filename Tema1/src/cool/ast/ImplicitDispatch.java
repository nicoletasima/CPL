package cool.ast;

import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public class ImplicitDispatch extends Expression {
  LinkedList<Expression> params;

  public ImplicitDispatch(Token token, LinkedList<Expression> params) {
    super(token);
    this.params = params;
  }

  public LinkedList<Expression> getParams() {
    return params;
  }

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
