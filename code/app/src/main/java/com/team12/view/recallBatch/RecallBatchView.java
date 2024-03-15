package com.team12.view.recallBatch;

public interface RecallBatchView {

    void onSuccessfulRecall();

    void showError(String title, String message);

    void showToast(String message);
}
