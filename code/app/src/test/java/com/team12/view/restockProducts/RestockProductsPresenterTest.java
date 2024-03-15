package com.team12.view.restockProducts;


import static junit.framework.TestCase.assertEquals;

import com.team12.memorydao.MemoryInitializer;
import com.team12.memorydao.OrderDAOMemory;
import com.team12.memorydao.ProductTypeDAOMemory;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Restock Products Presenter
 */
public class RestockProductsPresenterTest {
    private RestockProductsViewStub view;
    private RestockProductsPresenter presenter;

    /**
     * Sets up the presenter, the view for the tests and the database.
     */
    @Before
    public void setUp() {
        MemoryInitializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new RestockProductsViewStub();
        presenter = new RestockProductsPresenter(view, new ProductTypeDAOMemory(), new OrderDAOMemory());
    }

    /**
     * Tests when all the fields are valid.
     */
    @Test
    public void restockCorrectFieldsTest() {
        presenter.onRestockButton("1", "10", "200", true);
        assertEquals(1, view.getSuccessfullyRestocked());
        assertEquals(0, view.getShowToastNumber());
        assertEquals(0, view.getShowErrorNumber());

        presenter.onRestockButton("2", "12", "200", true);
        assertEquals(2, view.getSuccessfullyRestocked());
        assertEquals(0, view.getShowToastNumber());
        assertEquals(0, view.getShowErrorNumber());
    }

    /**
     * Tests when some the fields are invalid.
     */
    @Test
    public void restockIncorrectFieldsTest() {
        for (int i = 0; i < 5; i++) {
            presenter.onRestockButton("1", "1", "200", true);
        }
        assertEquals(0, view.getSuccessfullyRestocked());
        assertEquals(0, view.getShowToastNumber());
        assertEquals(5, view.getShowErrorNumber());

        presenter.onRestockButton("456", "1", "200", true);
        assertEquals(6, view.getShowErrorNumber());

        presenter.onRestockButton("1", "1", "0", true);
        assertEquals(7, view.getShowErrorNumber());
    }

    /**
     * Tests when some the fields are missing.
     */
    @Test
    public void restockMissingFieldsTest() {
        for (int i = 0; i < 5; i++) {
            presenter.onRestockButton("1", "", "200", false);
        }
        assertEquals(0, view.getSuccessfullyRestocked());
        assertEquals(5, view.getShowToastNumber());
        assertEquals(0, view.getShowErrorNumber());
    }
}
