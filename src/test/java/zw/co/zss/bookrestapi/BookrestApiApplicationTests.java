package zw.co.zss.bookrestapi;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import zw.co.zss.bookrestapi.controller.BookController;
import zw.co.zss.bookrestapi.controller.CategoryController;
import zw.co.zss.bookrestapi.controller.TransactionController;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TransactionController.class,
        CategoryController.class,
        BookController.class
})
class BookrestApiApplicationTests {
}
