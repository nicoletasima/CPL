package cool.structures;

import java.util.LinkedHashMap;
import java.util.Map;

public class MethodSymbol extends Symbol implements Scope {

  protected ClassSymbol parent;
  protected ClassSymbol type;
  protected Map<String, IdSymbol> formals = new LinkedHashMap<>();

  public MethodSymbol(String name) {
    super(name);
  }

  @Override
  public boolean add(Symbol sym) {
    if (formals.containsKey(sym.getName()))
      return false;

    formals.put(sym.getName(), (IdSymbol) sym);
    return true;
  }

  @Override
  public Symbol lookup(String str) {
    var sym = formals.get(str);

    if (sym != null)
      return sym;

    if (parent != null)
      return parent.lookup(str);
    return null;
  }

  @Override
  public Scope getParent() {
    return parent;
  }

  public void setParent(ClassSymbol parent) {
    this.parent = parent;
  }

  public ClassSymbol getType() {
    return type;
  }

  public void setType(ClassSymbol type) {
    this.type = type;
  }

  public void setFormals(Map<String, IdSymbol> formals) {
    this.formals = formals;
  }

  public Map<String, IdSymbol> getFormals() {
    return formals;
  }
}
