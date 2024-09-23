package com.example.myapplication2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CompoundButton;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupListeners();
    }


    private void setupListeners() {
        binding.Cost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateTip();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        binding.Percentage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateTip();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Listener for Round Up switch
        binding.Round.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                calculateTip();
            }
        });
    }


    private void calculateTip() {
        String costStr = binding.Cost.getText().toString();
        String percentageStr = binding.Percentage.getText().toString();

        // check check
        if (!costStr.isEmpty() && !percentageStr.isEmpty()) {
            double cost = Double.parseDouble(costStr);
            double percentage = Double.parseDouble(percentageStr);
            double tip = cost * (percentage / 100);
            double total = cost + tip;


            if (binding.Round.isChecked()) {
                total = Math.ceil(cost + tip);
            }

            //need to make prettier
            binding.TipAmount.setText(String.format("Tip Total: $%.2f", tip));
            binding.TotalBill.setText(String.format("Total Bill: $%.2f", total));
        } else {
            binding.TipAmount.setText("Tip Total: $0.00");
            binding.TotalBill.setText("Total Bill: $0.00");
        }
    }
}
