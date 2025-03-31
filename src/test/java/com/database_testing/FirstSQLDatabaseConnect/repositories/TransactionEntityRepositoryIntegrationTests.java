package com.database_testing.FirstSQLDatabaseConnect.repositories;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TransactionEntityRepositoryIntegrationTests {

    private TransactionRepository undertest;
    private UserRepository authorRepo;

    @Autowired
    public TransactionEntityRepositoryIntegrationTests(TransactionRepository undertest, UserRepository authorrepo){
        this.undertest = undertest;
        this.authorRepo = authorrepo;
    }

    /*
    @Test
    public void createAndReadBook(){
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor1();
        authorEntity.setId(null);
        BookDTO bookEntity = TestDataUtil.CreateTestBook(authorEntity);

        undertest.save(bookEntity);

        Optional<BookEntity> result = undertest.findById(bookEntity.getIsbn());
        Optional<AuthorEntity> authorresult = authorRepo.findById(1L);
        authorEntity.setId(authorresult.get().getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookEntity);
    }

    @Test
    public void createAuthorandReadAllBooks(){

        AuthorEntity authorEntity1 = TestDataUtil.createTestAuthor1();
        AuthorEntity authorEntity2 = TestDataUtil.createTestAuthor2();
        AuthorEntity authorEntity3 = TestDataUtil.createTestAuthor3();
        authorEntity1.setId(null);
        authorEntity2.setId(null);
        authorEntity3.setId(null);
        authorEntity1 = authorRepo.save(authorEntity1);
        authorEntity2 = authorRepo.save(authorEntity2);
        authorEntity3 = authorRepo.save(authorEntity3);


        BookEntity bookEntity1 = TestDataUtil.CreateTestBook(authorEntity1);
        BookEntity bookEntity2 = TestDataUtil.CreateTestBook2(authorEntity1);
        BookEntity bookEntity3 = TestDataUtil.CreateTestBook3(authorEntity2);
        BookEntity bookEntity4 = TestDataUtil.CreateTestBook4(authorEntity3);


        undertest.save(bookEntity1);
        undertest.save(bookEntity2);
        undertest.save(bookEntity3);
        undertest.save(bookEntity4);
        Iterable<BookEntity> result = undertest.findAll();

        assertThat(result).hasSize(4).containsExactly(bookEntity1, bookEntity2, bookEntity3, bookEntity4);

    }

    @Test
    public void createBookandUpdate(){
        AuthorEntity authorEntity1 = TestDataUtil.createTestAuthor1();
        AuthorEntity authorEntity2 = TestDataUtil.createTestAuthor2();

        authorEntity1.setId(null);
        authorEntity2.setId(null);

        authorEntity2 = authorRepo.save(authorEntity2);

        BookEntity bookEntity1 = TestDataUtil.CreateTestBook(authorEntity1);

        bookEntity1 = undertest.save(bookEntity1);
        authorEntity1 = bookEntity1.getAuthorEntity();

        bookEntity1.setTitle("new UPDATED");
        bookEntity1.setAuthorEntity(authorEntity2);

        undertest.save(bookEntity1);
        Optional<BookEntity> result = undertest.findById(bookEntity1.getIsbn());


        assertThat(result).isPresent().contains(bookEntity1);
    }

    @Test
    public void CreateBookandDelete(){
        AuthorEntity authorEntity = TestDataUtil.createTestAuthor1();
        authorEntity.setId(null);
        BookEntity bookEntity = TestDataUtil.CreateTestBook(authorEntity);
        bookEntity = undertest.save(bookEntity);
        authorEntity = bookEntity.getAuthorEntity();

        undertest.delete(bookEntity);

        Optional<BookEntity> result = undertest.findById(bookEntity.getIsbn());
        Optional<AuthorEntity> result2 = authorRepo.findById(authorEntity.getId());

        assertThat(result).isEmpty();
        assertThat(result2.get()).isEqualTo(bookEntity.getAuthorEntity());
    }

     */





}
