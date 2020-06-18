package com.example.exercisedatabase.userExersiseList;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exercisedatabase.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class createNewExersise extends AppCompatActivity {

    static final int REQUUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;
    private String[] types = {"Weights", "Endurance"};
    private ImageView imageView;
    private String currentPhotoPath;
    private String photoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_exersise);
        setTitle("Add New " + getIntent().getStringExtra("Exersise") + " Exercise");
        setButtons();
        imageView = (ImageView)findViewById(R.id.exersiseImageView);
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }
    }

    /**
     * Function to set the button listener in the list, and to set the Text based on the list view.
     */
    private void setButtons() {
        Button button = (Button)findViewById(R.id.addExersiseCancel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        button = (Button)findViewById(R.id.addExersiseAccept);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEnteredValues()) {
                    exersiseList newExersise = new exersiseList(Integer.parseInt(getIntent().getStringExtra("ID")), getIntent().getStringExtra("Exersise"));
                    TextView textView = (TextView)findViewById(R.id.exersiseNameInput);
                    newExersise.setExersiseName(textView.getText().toString());
                    textView = (TextView)findViewById(R.id.addExersiseDescription);
                    newExersise.setExersiseDescription(textView.getText().toString());
                    Spinner sp = (Spinner)findViewById(R.id.addExersiseTypeSelector);
                    newExersise.setExersiseType(sp.getSelectedItem().toString());
                    if (photoName != null) {
                        newExersise.setPhotoLocation(currentPhotoPath);
                        newExersise.setPhotoName(photoName);
                    }
                    exersiseListDatabaseHandler db = new exersiseListDatabaseHandler(getApplicationContext());
                    db.addExersise(newExersise);
                    setResult(2);
                    finish();
                }
            }
        });

        Spinner sp = (Spinner)findViewById(R.id.addExersiseTypeSelector);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, types);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView t = (TextView)findViewById(R.id.addExersiseDisplayColumns);
                if (position == 0) {
                    t.setText("1 Rep Max, Sets, Reps, Weight, Rest");
                } else {
                    t.setText("Time, Distance, HR, Intensity");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        button = (Button)findViewById(R.id.addExersisePhotoButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchPictureTakerAction();
            }
        });

        TextView textView = (TextView)findViewById(R.id.newExersiseGroup);
        textView.setText(getIntent().getStringExtra("Exersise"));
    }

    /**
     * Function to check that the entered values exist
     */
    private boolean checkEnteredValues() {
        EditText ed = (EditText)findViewById(R.id.exersiseNameInput);
        if (ed.getText().toString().equals("")) {
            Toast.makeText(this, "Name Must Exist", Toast.LENGTH_LONG).show();
            return false;
        }
        ed = (EditText)findViewById(R.id.addExersiseDescription);
        if (ed.getText().toString().equals("")) {
            Toast.makeText(this, "Description Must Exist", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
                imageView.setImageBitmap(bitmap);
                if (isStoragePermissionGranted()) {
                    currentPhotoPath = saveToInternalStorage(bitmap);
                }
                Log.d("Path", currentPhotoPath);
            }
        }
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_TAKE_PHOTO: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            case 2: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    private String saveToInternalStorage(Bitmap bitmapImage) {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        photoName = "Image-" + getIntent().getStringExtra("Exercise") + n + ".jpg";
        File file = new File(myDir, photoName);
        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {

        }
        return root;
    }

    private void dispatchPictureTakerAction() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createPhotoFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.exercisedatabase.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private File createPhotoFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_DCIM);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
