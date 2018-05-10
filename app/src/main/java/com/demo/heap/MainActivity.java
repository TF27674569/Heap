package com.demo.heap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.demo.heap.mode.MaxHeap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaxHeap heap = new MaxHeap();

        for (int i = 0; i < 10; i++) {
            heap.insert(i + 1);
        }

        heap.printArr();

        heap.deleteHeapTop();
        heap.printArr();

    }


}
