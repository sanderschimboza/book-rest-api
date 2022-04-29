# book-rest
Book-REST API service

#Assumptions
1.No book shoud be added with a non-existing category, thus the category must be added first.
2.To make sure the system runs as expected, i implemeted a Test Driven Development (TDD) approach with the help of Junit 5 and mockito. The tests can be run from the following folder: **/test/zw.co.zss.request/BookRestTest**
3.To make testing easier, i implemented spring-rest docs for documentation and a PDF document which was generated based on the tests that where carried out can be found under the following folder: **/src/main/asciidoc/index.pdf**
