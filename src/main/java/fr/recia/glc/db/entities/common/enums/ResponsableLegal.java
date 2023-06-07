package fr.recia.glc.db.entities.common.enums;

public enum ResponsableLegal {
  @Deprecated
  Non(0), Legal(1), EnCharge(2), Contact(3);

  private int code;

  ResponsableLegal(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }

  public void setCode(final int code) {
    this.code = code;
  }

  public static ResponsableLegal valueOf(final int code) {
    if (code == ResponsableLegal.Non.getCode()) {
      return ResponsableLegal.Contact;
    } else if (code == ResponsableLegal.Legal.getCode()) {
      return ResponsableLegal.Legal;
    } else if (code == ResponsableLegal.EnCharge.getCode()) {
      return ResponsableLegal.EnCharge;
    } else if (code == ResponsableLegal.Contact.getCode()) {
      return ResponsableLegal.Contact;
    }
    throw new IllegalArgumentException("Value of '" + code + "' doesn't match any code to enum "
      + ResponsableLegal.class.getCanonicalName() + " in defined values " + ResponsableLegal.values());
  }

  @Override
  public String toString() {
    return this.name() + "(" + this.getCode() + ")";
  }

}
