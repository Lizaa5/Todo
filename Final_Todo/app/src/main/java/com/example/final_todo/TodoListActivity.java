package com.example.final_todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.final_todo.adaptor.TodoAdaptor;
import com.example.final_todo.model.Category;
import com.example.final_todo.model.Todo;
import com.example.final_todo.viewmodel.CategoryViewModel;
import com.example.final_todo.viewmodel.TodoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TodoListActivity extends AppCompatActivity {

    TodoViewModel todoViewModel;
    RecyclerView todoRecyclerView;
    TodoAdaptor todoAdaptor;
    CategoryViewModel categoryViewModel;

    FloatingActionButton actionButton;


    TextView activity_todo_list_category;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        Toast.makeText(this, "Todo List Activity Class", Toast.LENGTH_SHORT).show();

        context=getApplicationContext();
        activity_todo_list_category = findViewById(R.id.activity_todo_list_category);
        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        todoRecyclerView = findViewById(R.id.activity_todo_list_rv_todo);
        todoAdaptor = new TodoAdaptor();
        todoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        todoRecyclerView.setAdapter(todoAdaptor);

        actionButton = findViewById(R.id.floatingActionButton);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity_todo_list_category.setVisibility(View.GONE);
                actionButton.setVisibility(View.GONE);
                todoRecyclerView.setVisibility(View.GONE);
                Fragment frag = new TodoFragment();
                FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
                transition.replace(R.id.fragmentContainer, frag).commit();
            }
        });

        int categoryId = getIntent().getIntExtra("categoryId", 1);

        Toast.makeText(this, "cATEGORY ID IS NOT NULL " + categoryId, Toast.LENGTH_SHORT).show();
        todoViewModel.getTodoList(categoryId).observe(this, new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {

                todoAdaptor.setTodoList(todos,context);
            }
        });


        //VCheck this one
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                todoViewModel.updateOnCompleteTodo(todoAdaptor.getUpdateNote(viewHolder.getAdapterPosition()));
                todoRecyclerView.getRecycledViewPool().clear();
                todoAdaptor.notifyDataSetChanged();

            }
        }).attachToRecyclerView(todoRecyclerView);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                todoViewModel.removeTodo(todoAdaptor.getNote(viewHolder.getAdapterPosition()));
                todoRecyclerView.getRecycledViewPool().clear();
                todoAdaptor.notifyDataSetChanged();

                Toast.makeText(TodoListActivity.this, "TODO DELETED", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(todoRecyclerView);


    }
}