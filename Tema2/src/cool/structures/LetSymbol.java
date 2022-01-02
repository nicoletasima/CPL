package cool.structures;

import java.util.LinkedHashMap;
import java.util.Map;

public class LetSymbol implements Scope {
  protected Scope parent;
  protected Map<String, IdSymbol> vars = new LinkedHashMap<>();

  public LetSymbol(Scope parent) {
    this.parent = parent;
  }

  @Override
  public boolean add(Symbol sym) {
    vars.put(sym.getName(), (IdSymbol) sym);
    return true;
  }

  @Override
  public Symbol lookup(String str) {
    var sym = vars.get(str);

    if (sym == null) {
      return getParent().lookup(str);
    }

    return sym;
  }

  public void setParent(Scope parent) {
    this.parent = parent;
  }

  public Map<String, IdSymbol> getVars() {
    return vars;
  }

  public void setVars(Map<String, IdSymbol> vars) {
    this.vars = vars;
  }

  @Override
  public Scope getParent() {
    return parent;
  }
}
