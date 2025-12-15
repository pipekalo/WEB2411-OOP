package edu.chdtu.web2411.victoria.lab6.bridge;

public class SimpleTextRenderer implements IDocumentRenderer {
  @Override
  public String renderHeader(String title) {
    return "=== " + title.toUpperCase() + " ===\n";
  }

  @Override
  public String renderBody(String content) {
    return content + "\n";
  }

  @Override
  public String renderFooter(String footerInfo) {
    return "----------------\n" + footerInfo + "\n";
  }
}