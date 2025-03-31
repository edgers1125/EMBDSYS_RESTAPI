package com.database_testing.FirstSQLDatabaseConnect.repositories;

import com.database_testing.FirstSQLDatabaseConnect.TestDataUtil;
import com.database_testing.FirstSQLDatabaseConnect.domain.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserEntityRepositoryIntegrationTests {
    private UserRepository underTest;

    @Autowired
    public UserEntityRepositoryIntegrationTests(UserRepository undertest) {
        this.underTest = undertest;
    }

    @Test
    public void AuthorCreate(){
        UserEntity userEntity = TestDataUtil.createTestAuthor1();
        userEntity.setId(null);
        underTest.save(userEntity);
        underTest.findById(userEntity.getId());
        Optional<UserEntity> result  = underTest.findById(userEntity.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(userEntity);
    }

    @Test
    public void customQuery(){
        UserEntity userEntity1 = TestDataUtil.createTestAuthor1();
        UserEntity userEntity2 = TestDataUtil.createTestAuthor2();
        UserEntity userEntity3 = TestDataUtil.createTestAuthor3();

        userEntity1.setId(null);
        userEntity3.setId(null);
        userEntity2.setId(null);

        userEntity1 = underTest.save(userEntity1);
        userEntity2 = underTest.save(userEntity2);
        userEntity3 = underTest.save(userEntity3);

        Iterable<UserEntity> under30 = underTest.ageLessThan(30);

        assertThat(under30).containsExactly(userEntity2, userEntity3);


    }
    /*
    @Test
    public void ReadManyAuthors(){
        Author author1 = TestDataUtil.createTestAuthor1();
        undertest.create(author1);
        Author author2 = TestDataUtil.createTestAuthor2();
        undertest.create(author2);
        Author author3 = TestDataUtil.createTestAuthor3();
        undertest.create(author3);

        undertest.find();


        List<Author> result = undertest.find();

        assertThat(result).hasSize(3);
        assertThat(result).containsExactly(author1, author2, author3);
    }
    @Test
    public void updateAuthor(){
        Author author = TestDataUtil.createTestAuthor1();
        undertest.create(author);

        Author authorupd = TestDataUtil.createTestAuthor1upd();
        undertest.updateAuthor(authorupd, 3L);

        Optional<Author> result = undertest.findOne(3L);
        authorupd.setId(3L);
        assertThat(result).isPresent().contains(authorupd);

    }
    @Test
    public void deleteAuthorTest(){
        Author author = TestDataUtil.createTestAuthor1();
       undertest.create(author);
        undertest.deleteAuthor(author.getId());
        Optional<Author> results = undertest.findOne(author.getId());

        assertThat(results).isEmpty();
    }

     */

}
