package com.learning.mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.verify;

class InjectMockTest extends SampleBaseTestCase{

    @Mock
    private ArticleCalculator calculator;
    @Mock(name = "database")
    private ArticleDatabase dbMock; // note the mock name attribute

    @Spy
    private UserProvider userProvider = new ConsumerUserProvider();

    @InjectMocks
    private ArticleManager manager;

    @Test
    public void shouldDoSomething() {
        manager.initiateArticle();

        //verify(database()).addListener(any(ArticleCalculator.class));
    }

}


class SampleBaseTestCase {

    private AutoCloseable closeable;

    @BeforeEach
    public void openMocks() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void releaseMocks() throws Exception {
        closeable.close();
    }
}

class ArticleManager {

    private ArticleDatabase database;
    private ArticleCalculator calculator;

    ArticleManager() {  }

    ArticleManager(ArticleCalculator calculator, ArticleDatabase database) {
        this.database = database;
        this.calculator = calculator;
    }

    ArticleManager(ArticleObserver observer, boolean flag) {
        // observer is not declared in the test above.
        // flag is not mockable anyway
    }

    // setter
    void setDatabase(ArticleDatabase database) { }

    // setter
    void setCalculator(ArticleCalculator calculator) { }

    public void initiateArticle() {
        System.out.println(database.toString()+" and " + calculator.toString());
        database.addListener(calculator);

    }

    public ArticleDatabase getDatabase() {
        return database;
    }
}

class ArticleDatabase{

    private List<ArticleCalculator> lists = new ArrayList<>();
    public void addListener(ArticleCalculator calculator) {
        System.out.println("Adding Listener");
        lists.add(calculator);
    }
}

class ArticleCalculator{

}

class ArticleObserver{

}

class UserProvider{

}

class ConsumerUserProvider extends UserProvider{

}