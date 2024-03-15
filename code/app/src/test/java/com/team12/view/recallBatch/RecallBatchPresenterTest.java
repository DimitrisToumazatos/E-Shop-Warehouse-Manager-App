package com.team12.view.recallBatch;

import static junit.framework.TestCase.assertEquals;

import com.team12.memorydao.MemoryInitializer;
import com.team12.memorydao.OrderDAOMemory;
import com.team12.memorydao.ProductTypeDAOMemory;
import com.team12.memorydao.UserAccountDAOMemory;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Recall Batch Presenter
 */
public class RecallBatchPresenterTest {
    private RecallBatchViewStub view;
    private RecallBatchPresenter presenter;

    /**
     * Sets up the presenter, the view for the tests and the database.
     */
    @Before
    public void setUp() {
        MemoryInitializer dataHelper = new MemoryInitializer();
        dataHelper.prepareData();
        view = new RecallBatchViewStub();
        presenter = new RecallBatchPresenter(view, new OrderDAOMemory(), new ProductTypeDAOMemory(), new UserAccountDAOMemory());
    }

    /**
     * Tests when all the fields are valid.
     */
    @Test
    public void recallCorrectFieldsTest() {
        presenter.onRecallButton("4", "7", true);
        assertEquals(1, view.getSuccessfulRecall());
        assertEquals(0, view.getShowToastCount());
        assertEquals(0, view.getShowErrorCount());

        presenter.onRecallButton("5", "10", true);
        assertEquals(2, view.getSuccessfulRecall());
        assertEquals(0, view.getShowToastCount());
        assertEquals(0, view.getShowErrorCount());
    }

    /**
     * Tests when some the fields are invalid.
     */
    @Test
    public void recallIncorrectFieldsTest() {
        // No Product exists with the given EOF
        presenter.onRecallButton("456", "1", true);
        assertEquals(0, view.getSuccessfulRecall());
        assertEquals(0, view.getShowToastCount());
        assertEquals(1, view.getShowErrorCount());

        // No Batch exists with the given ID
        presenter.onRecallButton("1", "1534", true);
        assertEquals(0, view.getSuccessfulRecall());
        assertEquals(0, view.getShowToastCount());
        assertEquals(2, view.getShowErrorCount());
    }

    /**
     * Tests when some the fields are missing.
     */
    @Test
    public void recallMissingFieldsTest() {
        for (int i = 0; i < 5; i++) {
            presenter.onRecallButton("1", "", false);
        }
        assertEquals(0, view.getSuccessfulRecall());
        assertEquals(5, view.getShowToastCount());
        assertEquals(0, view.getShowErrorCount());
    }
}
