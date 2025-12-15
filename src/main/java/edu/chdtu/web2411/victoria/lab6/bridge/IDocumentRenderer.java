package edu.chdtu.web2411.victoria.lab6.bridge;

public interface IDocumentRenderer {
  String renderHeader(String title);

  String renderBody(String content);

  String renderFooter(String footerInfo);
}