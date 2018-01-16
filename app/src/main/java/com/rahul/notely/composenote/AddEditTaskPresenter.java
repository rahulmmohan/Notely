package com.rahul.notely.composenote;



/**
 * Created by rahul on 25/8/17.
 */

/**
 * Listens to user actions from the UI ({@link }), retrieves the data and updates
 * the UI as required.
 */
public class    AddEditTaskPresenter {//implements AddEditTaskContract.Presenter {
/*
    @NonNull
    private final AddEditTaskContract.View mAddTaskView;
    private final DatabaseHandler mDatabaseHandler;
    private final int mTaskId;

    *//**
     * Creates a presenter for the add/edit view.
     *
     * @param taskId      ID of the task to edit or null for a new task
     * @param addTaskView the add/edit view
     *//*
    public AddEditTaskPresenter(Context context, int taskId,
                                @NonNull AddEditTaskContract.View addTaskView) {
        mTaskId = taskId;
        mAddTaskView = addTaskView;
        mDatabaseHandler = DatabaseHandler.getInstance(context);
    }


    @Override
    public void saveTask(String title, String description) {
        if (isNewTask()) {
            createTask(title, description);
        } else {
            updateTask(title, description);
        }
    }

    @Override
    public void populateTask() {
        if (isNewTask()) {
            return;
        }
        Task task = mDatabaseHandler.getTask(mTaskId);
        mAddTaskView.setTaskDetails(task);
    }


    private boolean isNewTask() {
        return mTaskId == -1;
    }

    private void createTask(String title, String description) {
        Task newTask = new Task(title, description);
        newTask.createdDate = String.valueOf(System.currentTimeMillis());
        newTask.updatedDate = String.valueOf(System.currentTimeMillis());
        mDatabaseHandler.addTask(newTask);
        mAddTaskView.showTasksList();
    }

    private void updateTask(String title, String description) {
        if (isNewTask()) {
            throw new RuntimeException("updateTask() was called but task is new.");
        }
        Task task = new Task(title, description);
        task.id = mTaskId;
        task.updatedDate = String.valueOf(System.currentTimeMillis());
        mDatabaseHandler.updateTask(task);
        mAddTaskView.showTasksList(); // After an edit, go back to the list.
    }*/
}
