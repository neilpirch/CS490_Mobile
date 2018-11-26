package com.neilpirch.firebasephotos;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ImagesActivity extends AppCompatActivity {
    private static final String TAG = "ImagesActivity";
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private ProgressBar mProgressCircle;


    private DatabaseReference mDatabaseReference;
    private List<Upload> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        mRecyclerView = findViewById((R.id.recycler_view));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProgressCircle = findViewById(R.id.progress_circle);

        mUploads = new ArrayList<>();
        mAdapter = new ImageAdapter(ImagesActivity.this, mUploads);

        mRecyclerView.setAdapter(mAdapter);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("uploads");

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: data changed");
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads.add(upload);
                    Log.d(TAG, "onDataChange: " + upload.getImageUrl() + " added to arraylist");
                }

                mAdapter.notifyDataSetChanged();
                Log.d(TAG, "onDataChange: adapter updated" );

                mProgressCircle.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ImagesActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);

            }
        });
    }
}
