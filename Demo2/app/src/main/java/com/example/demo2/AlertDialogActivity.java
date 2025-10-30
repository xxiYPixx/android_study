package com.example.demo2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AlertDialogActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();

        showCustomDialog();
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_login, null);

        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        EditText usernameEditText = dialogView.findViewById(R.id.usernameEditText);
        EditText passwordEditText = dialogView.findViewById(R.id.passwordEditText);
        Button cancelButton = dialogView.findViewById(R.id.cancelButton);
        Button signInButton = dialogView.findViewById(R.id.signInButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AlertDialogActivity.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(AlertDialogActivity.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                } else {
                    String message = "Login successful!\nUsername: " + username + "\nPassword: " + password;
                    Toast.makeText(AlertDialogActivity.this, message, Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }
}