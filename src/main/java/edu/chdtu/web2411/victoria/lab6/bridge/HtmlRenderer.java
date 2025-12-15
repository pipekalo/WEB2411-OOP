package edu.chdtu.web2411.victoria.lab6.bridge;

public class HtmlRenderer implements IDocumentRenderer {
  @Override
  public String renderHeader(String title) {
    return "<h1>" + title + "</h1>\n";
  }

  @Override
  public String renderBody(String content) {
    return "<div class='content'>" + content + "</div>\n";
  }

  @Override
  public String renderFooter(String footerInfo) {
    return "<footer><small>" + footerInfo + "</small></footer>\n";
  }
}