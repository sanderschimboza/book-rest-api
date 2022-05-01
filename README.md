# book-rest
Book-REST API service

#Assumptions & Additional Information

1.No book should be added with a non-existing category, thus the category must be added first.

2.To make sure the system runs as expected, I implemented a Test Driven Development (TDD) approach with the help of Junit 5 and mockito. The tests can be run from the following folder: **/test/zw.co.zss.request/BookRestTest**

3.To make testing easier, I implemented spring-rest docs for documentation, and a PDF document which was generated based on the tests that where carried out can be found under the following folder: **/src/main/asciidoc/index.pdf**

4.To run the application, one simply needs to download the project and import it into an IDE of choice and then run the program as a java application.

5.The application is available on port **8080** (default)

6.The databse authentication details (username & password) will have to be changed to match that of the hosting server or env


