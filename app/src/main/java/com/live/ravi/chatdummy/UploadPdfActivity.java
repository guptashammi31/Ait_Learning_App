package com.live.ravi.chatdummy;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.live.ravi.chatdummy.Fragments.UsersFragment;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class UploadPdfActivity extends AppCompatActivity {
    // Folder path for Firebase Storage.
    String Storage_Path = "All_Image_Uploads/";

    // Root Database Name for Firebase Database.
    public static final String Database_Path = "All_Image_Uploads_Database";
    public static final String Database_sub = "subjectsPdf";

    // Creating button.
    Button ChooseButton, UploadButton, DisplayImageButton;

    // Creating EditText.
    EditText pdfname ;
    Spinner subname;

    String subname1;


    public ArrayAdapter<String> spinnerCountShoesArrayAdapter;



    ImageView SelectImage;

    // Creating URI.
    Uri FilePathUri;

    // Creating StorageReference and DatabaseReference object.
    StorageReference storageReference;
    DatabaseReference databaseReference,subjectrefrence;

    // Image request code for onActivityResult() .
    int Image_Request_Code = 7;

    ProgressDialog progressDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pdf);

        // Assign FirebaseStorage instance to storageReference.
        storageReference = FirebaseStorage.getInstance().getReference();

        // Assign FirebaseDatabase instance with root database name.
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);
        subjectrefrence = FirebaseDatabase.getInstance().getReference(Database_sub);

        //Assign ID'S to button.
        ChooseButton = (Button)findViewById(R.id.ButtonChooseImage);
        UploadButton = (Button)findViewById(R.id.ButtonUploadImage);

        DisplayImageButton = (Button)findViewById(R.id.DisplayImagesButton);

        // Assign ID's to EditText.
        pdfname = (EditText)findViewById(R.id.ImageNameEditText);
        subname = (Spinner)findViewById(R.id.spinner11);

        spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.module_name));
        subname.setAdapter(spinnerCountShoesArrayAdapter);

        subname.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                Object item = parentView.getItemAtPosition(position);
                subname1=item.toString();
                Toast.makeText(getApplicationContext(),subname1,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void
            onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        // Assign ID'S to image view.
        SelectImage = (ImageView)findViewById(R.id.ShowImageView);

        // Assigning Id to ProgressDialog.
        progressDialog = new ProgressDialog(UploadPdfActivity.this);

        // Adding click listener to Choose image button.
        ChooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Creating intent.
                Intent intent = new Intent();

                // Setting intent type as image to select image from phone storage.
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Please Select Image"), Image_Request_Code);

            }
        });


        // Adding click listener to Upload image button.
        UploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Calling method to upload selected image on Firebase storage.
                UploadImageFileToFirebaseStorage();

            }
        });


        DisplayImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(UploadPdfActivity.this, DisplayImagesActivity.class);
                startActivity(intent);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {

                // Getting selected image into Bitmap.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);

                // Setting up bitmap selected image into ImageView.
                SelectImage.setImageBitmap(bitmap);

                // After selecting image change choose button above text.
                ChooseButton.setText("Pdf Selected");

            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

    // Creating Method to get the selected image file Extension from File Path URI.
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    // Creating UploadImageFileToFirebaseStorage method to upload image on storage.
    public void UploadImageFileToFirebaseStorage() {

        // Checking whether FilePathUri Is empty or not.
        if (FilePathUri != null) {

            // Setting progressDialog Title.
            progressDialog.setTitle("File is Uploading...");

            // Showing progressDialog.
            progressDialog.show();

            // Creating second StorageReference.
            StorageReference storageReference2nd = storageReference.child(  System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));

            // Adding addOnSuccessListener to second StorageReference.
            storageReference2nd.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            // Getting image name from EditText and store into string variable.
                            String TempImageName = pdfname.getText().toString().trim();
                            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

                            progressDialog.dismiss();

                            // Showing toast message after done uploading.
                            Toast.makeText(getApplicationContext(), "Document Uploaded Successfully ", Toast.LENGTH_LONG).show();

                            @SuppressWarnings("VisibleForTests")
                            ImageUploadInfo imageUploadInfo = new ImageUploadInfo(subname1,TempImageName.toLowerCase(),currentDateTimeString, taskSnapshot.getDownloadUrl().toString());

                            // Getting image upload ID.
                            String ImageUploadId = databaseReference.push().getKey();

                            // Adding image upload id s child element into databaseReference.
                            databaseReference.child(ImageUploadId).setValue(imageUploadInfo);
                            subjectrefrence.child(subname1.toLowerCase()).child(ImageUploadId).setValue(imageUploadInfo);
                        }
                    })
                    // If something goes wrong .
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            // Hiding the progressDialog.
                            progressDialog.dismiss();

                            // Showing exception erro message.
                            Toast.makeText(UploadPdfActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })

                    // On progress change upload time.
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            // Setting progressDialog Title.
                            progressDialog.setTitle("File  is Uploading...");

                        }
                    });
        }
        else {

            Toast.makeText(UploadPdfActivity.this, "Please SelectFile or Add File Name", Toast.LENGTH_LONG).show();

        }
    }



}
