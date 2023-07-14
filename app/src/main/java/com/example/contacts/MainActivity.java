package com.example.contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contacts.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private TextView tvContact;
    private RecyclerView recyclerView;
    private ArrayList<Contact> contactList;
    private ContactAdapter contactAdapter;
    private static final int REQUEST_READ_CONTACTS = 1;
    private static final int REQUEST_CALL_PERMISSION = 1;
    private final String phoneNumber = "7717752790"; // Replace with the desired phone number


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();

    }

    private void init() {
        initListener();
        initAction();

    }

    private void initListener() {

        binding.contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NameFileActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initAction() {

        if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_READ_CONTACTS);
        } else {
            loadContacts();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadContacts();
            }
        } else if (requestCode == REQUEST_CALL_PERMISSION){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, make the call
                makeCall();
            } else {
                Toast.makeText(this,"Permission denied",Toast.LENGTH_SHORT).show();
            }
        }else {

        }
    }

    private void loadContacts() {

        contactList = new ArrayList<>();
        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER};

        Cursor cursor = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection, null, null);
        }

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                Contact contact = new Contact();
                contact.setContactName(cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
                contact.setContactNumber(cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                contactList.add(contact);
            }
            cursor.close();
        }

        contactAdapter = new ContactAdapter(contactList);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        binding.recyclerview.setAdapter(contactAdapter);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_CALL_PERMISSION);
        } else {
            // Permission is already granted, make the call
            makeCall();
        }
    }
    private void makeCall() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(callIntent);
        }
    }


}



