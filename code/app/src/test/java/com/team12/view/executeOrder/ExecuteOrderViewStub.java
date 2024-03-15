package com.team12.view.executeOrder;

/**
 * This class is used as a view for the needs of the tests.
 */
public class ExecuteOrderViewStub implements ExecuteOrderView {
    private int onSuccessfulOrderExecutionCorrect;
    private int onSuccessfulOrderCancelCorrect;
    @Override
    public void onSuccessfulOrderExecution() {
        onSuccessfulOrderExecutionCorrect++;
    }

    @Override
    public void onSuccessfulOrderCancel() {
        onSuccessfulOrderCancelCorrect++;
    }

    public int getOnSuccessfulOrderExecutionCorrect() {
        return onSuccessfulOrderExecutionCorrect;
    }

    public int getOnSuccessfulOrderCancelCorrect() {
        return onSuccessfulOrderCancelCorrect;
    }
}
