package zw.co.zss.bookrestapi.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import zw.co.zss.bookrestapi.model.Book;
import zw.co.zss.bookrestapi.model.Card;
import zw.co.zss.bookrestapi.model.Category;
import zw.co.zss.bookrestapi.model.Transaction;
import zw.co.zss.bookrestapi.utils.Order;
import zw.co.zss.bookrestapi.utils.OrderedTestRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(OrderedTestRunner.class)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class BookRestTest {

    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();
    @Autowired
    private MockMvc mockMvc;


    /**
     * @throws Exception It tests add category.
     */
    @Test
    @Order(1)
    public void addCategoryTest() throws Exception {

        Category category =
                Category.builder()
                        .id(1L)
                        .title("mathematics")
                        .build();

        String json = new ObjectMapper().writeValueAsString(category);
        mockMvc.perform(
                post("/category")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)).andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint())));


        //  add another category

        Category category2 =
                Category.builder()
                        .id(2L)
                        .title("novel")
                        .build();

        String json2 = new ObjectMapper().writeValueAsString(category2);
        mockMvc.perform(
                post("/category")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json2)).andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint())));
    }

    /**
     * @throws Exception It tests add book.
     */
    @Test
    @Order(2)
    public void addBookTest() throws Exception {

        Book book =
                Book.builder()
                        .id(1L)
                        .title("Mechanics")
                        .description("A Level Mechanics by Stewart Michael's")
                        .category(Category.builder()
                                .id(1L)
                                .build())
                        .price(20.59)
                        .build();

        String json = new ObjectMapper().writeValueAsString(book);
        mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isCreated())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint())));

        //add another book

        Book book2 =
                Book.builder()
                        .id(2L)
                        .title("A fairytale of Love and passion")
                        .description("A Book by Jordan Sanderson")
                        .category(Category.builder()
                                .id(2L)
                                .build())
                        .price(59.78)
                        .build();

        String json2 = new ObjectMapper().writeValueAsString(book2);
        mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json2))
                .andExpect(status().isCreated())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint())));

    }

    /**
     * @throws Exception It tests find all books.
     */
    @Test
    @Order(3)
    public void findAllBooksTest() throws Exception {

        mockMvc.perform(get("/book")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint())));
    }

    /**
     * @throws Exception It tests retrieval of books in a certain category
     */

    @Test
    @Order(4)
    public void findBookInACategory() throws Exception {

        mockMvc.perform(get("/book/2")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(document("{methodName}",
                        preprocessRequest(prettyPrint())));
    }

    /**
     * @throws Exception It tests purchase book
     */
    @Test
    @Order(5)
    public void purchaseBook() throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("cardHolder", "Sanders");

        Transaction transaction =
                Transaction.builder()
                        .type("PURCHASE")
                        .extendedType("NONE")
                        .amount(59.78)
                        .created("2022-03-07T14:55:14.069+02:00")
                        .card(Card.builder()
                                .id("1234560000000001")
                                .expiry("2022-10-09")
                                .build())
                        .reference(UUID.randomUUID().toString())
                        .narration("Test Narration")
                        .additionalData(map)
                        .build();

        String json = new ObjectMapper().writeValueAsString(transaction);

        mockMvc.perform(post("/transaction")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk()).andDo(document("{methodName}",
                preprocessRequest(prettyPrint())));
    }

}
