package cool.structures;

import java.util.LinkedHashMap;
import java.util.Map;

public class ClassSymbol extends Symbol implements Scope {
  // Symbols aferente tipurilor definite global
  public static final ClassSymbol INT = new ClassSymbol("Int", "Object");
  public static final ClassSymbol BOOL = new ClassSymbol("Bool", "Object");
  public static final ClassSymbol STRING = new ClassSymbol("String", "Object");
  public static final ClassSymbol SELF_TYPE = new ClassSymbol("SELF_TYPE", "Object");
  public static final ClassSymbol IO = new ClassSymbol("IO", "Object");
  public static final ClassSymbol OBJECT = new ClassSymbol("Object", null);

  private ClassSymbol inheritsType;
  private String parentName;
  protected Map<String, IdSymbol> attributes = new LinkedHashMap<>();
  protected Map<String, MethodSymbol> methods = new LinkedHashMap<>();


  public ClassSymbol(String name, String parentName) {
    super(name);
    this.parentName = parentName;
  }

  @Override
  public boolean add(Symbol sym) {
    /* Ne asiguram ca simbolul nu exista deja in domeniul de vizibilitate */

    if (sym instanceof IdSymbol) {
      if (attributes.containsKey(sym.getName()))
        return false;
      attributes.put(sym.getName(), (IdSymbol) sym);

    } else if (sym instanceof MethodSymbol) {
      if (methods.containsKey(sym.getName()))
        return false;
      methods.put(sym.getName(), (MethodSymbol) sym);
    }

    return true;
  }

  @Override
  public Symbol lookup(String str) {
    IdSymbol sym = attributes.get(str);

    if (sym != null)
        return sym;
    if (inheritsType != null)
      return inheritsType.lookup(str);
    return null;
  }

  public MethodSymbol lookupMethod(String str) {
    MethodSymbol sym = methods.get(str);

    if (sym != null) {
      return sym;
    }

    if (inheritsType != null) {
      return inheritsType.lookupMethod(str);
    }

    return null;
  }

  @Override
  public Scope getParent() {
    return inheritsType;
  }

  public void setParent(ClassSymbol inheritsType) {
    this.inheritsType = inheritsType;
  }

  public ClassSymbol getInheritsType() {
    return inheritsType;
  }

  public void setInheritsType(ClassSymbol inheritsType) {
    this.inheritsType = inheritsType;
  }

  public String getParentName() {
    return parentName;
  }

  public void setParentName(String parentName) {
    this.parentName = parentName;
  }

  public Map<String, IdSymbol> getAttributes() {
    return attributes;
  }

  public void setAttributes(Map<String, IdSymbol> attributes) {
    this.attributes = attributes;
  }

  public Map<String, MethodSymbol> getMethods() {
    return methods;
  }

  public void setMethods(Map<String, MethodSymbol> methods) {
    this.methods = methods;
  }

  public boolean inheritsClass(ClassSymbol sym) {
    if (this == sym) {
      return true;
    }

    if (inheritsType != null) {
      return inheritsType.inheritsClass(sym);
    }

    return false;
  }
}
