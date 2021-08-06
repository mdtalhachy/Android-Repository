package rocks.talha.todoister;

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
import rocks.talha.todoister.model.Task;
import rocks.talha.todoister.model.TaskViewModel;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Calendar;
import java.util.Date;

public class BottomSheetFragment extends BottomSheetDialogFragment {

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
        Chip tomorrowChip = view.findViewById(R.id.tomorrow_chip);
        Chip nextWeekChip = view.findViewById(R.id.next_week_chip);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        calendarButton.setOnClickListener(view12 -> {
            calenderGroup.setVisibility(
                    calenderGroup.getVisibility() == View.GONE ? View.VISIBLE : View.GONE
            );
        });

        /* Exporting date from calendarView */
        calendarView.setOnDateChangeListener((calendarView, year, month, dayOfMonth) -> {
            /* month + 1 because calendar months start at 0 */
            //Log.d("ShowDate", "onViewCreated: " + "Month " + (month + 1) + " Date " + dayOfMonth);

            calendar.clear();
            calendar.set(year, month, dayOfMonth);
            dueDate = calendar.getTime();


        });

        saveButton.setOnClickListener(view1 -> {
            String task = enterTodo.getText().toString().trim();
            if(!TextUtils.isEmpty(task) && dueDate != null){
                Task myTask = new Task(task, Priority.HIGH,
                        Calendar.getInstance().getTime(), dueDate,
                        false);
                TaskViewModel.insert(myTask);
            }
        });
    }
}
