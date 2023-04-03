package com.example.final_todo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.final_todo.database.AppDatabase;
import com.example.final_todo.database.Repository;
import com.example.final_todo.model.Category;
import com.example.final_todo.model.Todo;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {
    private LiveData<List<Todo>> todoList,todolistAll;
    Repository repository;
    public TodoViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        repository = new Repository(appDatabase);
    }

    public void saveTodo(Todo todo) {
        repository.insertTodo(todo);
    }

    public LiveData<List<Todo>> getTodoList(int categoryId) {
        todoList = repository.loadAllTodo(categoryId);
        return todoList;
    }

    public void ChangeTodo(Todo todo) {
        repository.updateAllTodo(todo);
    }
    public void updateOnCompleteTodo(int todo) {
        repository.updateIsCompleteTodo(todo);
    }

    public void  removeTodo(Todo todo){
        repository.deleteTodo(todo);
    }
    public LiveData<List<Todo>> getAllTodoList() {
        todolistAll = repository.loadAllTodo();
        return todolistAll;
    }
}
