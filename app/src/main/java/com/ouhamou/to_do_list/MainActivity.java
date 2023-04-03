package com.ouhamou.to_do_list;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ouhamou.to_do_list.adapters.ItemAdapter;
import com.ouhamou.to_do_list.adapters.ItemViewModel;
import com.ouhamou.to_do_list.adapters.ViewModelFactory;
import com.ouhamou.to_do_list.databinding.ActivityMainBinding;
import com.ouhamou.to_do_list.models.Item;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemAdapter.Listener {
    private ActivityMainBinding binding;
    private ItemViewModel itemViewModel;
    private ItemAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        configureRecyclerView();
        configureViewModel();
        initView();
        getItems();
    }

    private void getItems() {
        itemViewModel.getItems().observe(this,this::updateItemsList);
    }
    private void createItem(){
        itemViewModel.createItem(
                binding.todoListActivityEditText.getText().toString(),
                binding.todoListActivitySpinner.getSelectedItemPosition()
        );
        binding.todoListActivityEditText.setText("");
    }
    private void updateItemsList(List<Item> items) {
        adapter.updateDate(items);
    }


    private void configureViewModel() {
        itemViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(ItemViewModel.class);
    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        configureSpinner();
        binding.todoListActivityButtonAdd.setOnClickListener(view -> {
            createItem();
        });
        CompoundButton.OnCheckedChangeListener checkedChangeListener = (button, isChecked) -> {
            if (isChecked) {
                int id = button.getId();
                if (id == R.id.main_activity_local_storage) {
                    // TODO : handle Local storage.
                } else if (id == R.id.main_activity_remote_storage) {
                    // TODO :handle Remote storage.
                }
            }
            // TODO :read from the selected storage.
        };

    }

    private void configureSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.todoListActivitySpinner.setAdapter(adapter);
    }
    private void configureRecyclerView(){
        adapter = new ItemAdapter(this);
        binding.todoListActivityRecyclerView.setAdapter(adapter);
        binding.todoListActivityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClickDeleteButton(Item item) {
        deleteItem(item);
    }

    private void deleteItem(Item item) {
        itemViewModel.deleteItem(item.getId());
    }

    @Override
    public void onItemClick(Item item) {
        updateItem(item);
    }

    @Override
    public void onItemLogClick(Item item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        input.setTextAppearance(this,R.style.EditTextDialogBox);
        builder.setView(input);
        input.setText(item.getText());
        builder.setTitle("update Item");
        builder.setMessage("please click on save to update your item");
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                item.setText(input.getText().toString());
                updateItem(item);
                Toast.makeText(getApplicationContext(),"you update the item successfully "+item.getId(),Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // do something when Cancel button is clicked
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void updateItem(Item item) {
        item.setSelected(!item.isSelected());
        itemViewModel.updateItem(item);
    }
}