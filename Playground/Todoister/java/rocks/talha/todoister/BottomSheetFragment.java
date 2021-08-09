package rocks.talha.todoister;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import rocks.talha.todoister.R;
import rocks.talha.todoister.model.Priority;
import rocks.talha.todoister.model.SharedViewModel;
import rocks.talha.todoister.model.Task;
import rocks.talha.todoister.model.TaskViewModel;
import rocks.talha.todoister.util.Utils;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Calendar;
import java.util.Date;

public class BottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    private EditText enterTodo;
    private ImageButton calendarButton;
    private ImageButton priorityButton;
    private RadioGroup priorityRadioGroup;
    private RadioButton selectedRadioButton;
    private int selectedButtonId;
    private ImageButton saveButton;
    private CalendarView calendarView;
    private Group calenderGroup;
    private Date dueDate;
    private SharedViewModel sharedViewModel;
    private boolean isEdit;
    private Priority priority;

    /* to extract date format */
    Calendar calendar = Calendar.getInstance();

    public BottomSheetFragment() {

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        /* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);

        /* All the widgets have to go through this view in order to show */
        calenderGroup = view.findViewById(R.id.calendar_group);
        calendarView = view.findViewById(R.id.calendar_view);
        calendarButton = view.findViewById(R.id.today_calendar_button);
        enterTodo = view.findViewById(R.id.enter_todo_et);
        saveButton = view.findViewById(R.id.save_todo_button);
        priorityButton = view.findViewById(R.id.priority_todo_button);
        priorityRadioGroup = view.findViewById(R.id.radioGroup_priority);

        Chip todayChip = view.findViewById(R.id.today_chip);
        /* this refers to the entire BottomSheetFragment class's onClickListener */
        todayChip.setOnClickListener(this);
        Chip tomorrowChip = view.findViewById(R.id.tomorrow_chip);
        tomorrowChip.setOnClickListener(this);
        Chip nextWeekChip = view.findViewById(R.id.next_week_chip);
        nextWeekChip.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(sharedViewModel.getSelectedItem().getValue() != null){
            isEdit = sharedViewModel.getIsEdit();

            Task task = sharedViewModel.getSelectedItem().getValue();
            enterTodo.setText(task.getTask());
            Log.d("MY", "onViewCreated: " + task.getTask());
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /* passing data from select item to bottom sheet */
        sharedViewModel = new ViewModelProvider(requireActivity())
                .get(SharedViewModel.class);


        calendarButton.setOnClickListener(view12 -> {
            calenderGroup.setVisibility(
                    calenderGroup.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);

            Utils.hideSoftKeyboard(view12);
        });

        /* Exporting date from calendarView */
        calendarView.setOnDateChangeListener((calendarView, year, month, dayOfMonth) -> {
            /* month + 1 because calendar months start at 0 */
            //Log.d("ShowDate", "onViewCreated: " + "Month " + (month + 1) + " Date " + dayOfMonth);

            calendar.clear();
            calendar.set(year, month, dayOfMonth);
            dueDate = calendar.getTime();


        });

        priorityButton.setOnClickListener(view13 -> {
            Utils.hideSoftKeyboard(view13);
            priorityRadioGroup.setVisibility(
                    priorityRadioGroup.getVisibility()== View.GONE ? View.VISIBLE : View.GONE
            );

            /* setting up priority radio buttons */
            priorityRadioGroup.setOnCheckedChangeListener((radioGroup, checkedId) -> {

                if(priorityRadioGroup.getVisibility() == View.VISIBLE){

                    selectedButtonId = checkedId;
                    selectedRadioButton = view.findViewById(selectedButtonId);
                    if(selectedRadioButton.getId() == R.id.radioButton_high){
                        priority = Priority.HIGH;
                    }else if(selectedRadioButton.getId() == R.id.radioButton_med){
                        priority = Priority.MEDIUM;
                    }else if(selectedRadioButton.getId() == R.id.radioButton_low){
                        priority = Priority.LOW;
                    }else{
                        priority = Priority.LOW;
                    }

                }else{
                    priority = Priority.LOW;
                }

            });


        });

        saveButton.setOnClickListener(view1 -> {
            String task = enterTodo.getText().toString().trim();

            if(!TextUtils.isEmpty(task) && dueDate != null && priority != null){
                Task myTask = new Task(task, priority,
                        Calendar.getInstance().getTime(), dueDate,
                        false);

                if(isEdit){

                    /* if isEdit is true */
                    Task updatedTask = sharedViewModel.getSelectedItem().getValue();

                    updatedTask.setTask(task);
                    updatedTask.setDateCreated(Calendar.getInstance().getTime());
                    updatedTask.setPriority(priority);
                    updatedTask.setDueDate(dueDate);
                    TaskViewModel.update(updatedTask);
                    sharedViewModel.setIsEdit(false);

                }else{
                    TaskViewModel.insert(myTask);
                }

                /* setting text field to empty after saving */
                enterTodo.setText("");

                /* closing bottom sheet once saved */
                if(this.isVisible()){
                    this.dismiss();
                }

            }else{
                Snackbar.make(saveButton, "Empty Field", Snackbar.LENGTH_LONG)
                        .show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.today_chip){
            /* set data for today */
            calendar.add(Calendar.DAY_OF_YEAR, 0);
            dueDate = calendar.getTime();
        }else if(id == R.id.tomorrow_chip){
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            dueDate = calendar.getTime();
        }else if(id == R.id.next_week_chip){
            calendar.add(Calendar.DAY_OF_YEAR, 7);
            dueDate = calendar.getTime();
            Log.d("TIME", "onClick: " + dueDate.toString());
        }
    }
}
