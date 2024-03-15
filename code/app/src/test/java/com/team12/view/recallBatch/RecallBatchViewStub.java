package com.team12.view.recallBatch;

/**
 * This class is used as a view for the needs of the tests.
 */
public class RecallBatchViewStub implements RecallBatchView {
    private int successfulRecall;
    private int showErrorCount;
    private int showToastCount;

    @Override
    public void onSuccessfulRecall() {
        successfulRecall++;
    }

    @Override
    public void showError(String title, String message) {
        showErrorCount++;
    }

    @Override
    public void showToast(String message) {
        showToastCount++;
    }

    public int getSuccessfulRecall() {
        return successfulRecall;
    }

    public int getShowErrorCount() {
        return showErrorCount;
    }

    public int getShowToastCount() {
        return showToastCount;
    }
}
