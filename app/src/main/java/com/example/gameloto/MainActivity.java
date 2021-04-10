package com.example.gameloto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText mEdtSMin,mEdtSMax;
    Button mBtnRandom,mBtnAddRange,mBtnReset;
    TextView mTvResult;
    String mTextSMin = "";
    String mTextSMax = "";
    Random mRandom = null;
    String mTextResult = "";
    List<Integer> mArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEdtSMin = findViewById(R.id.editTextSMin);
        mEdtSMax = findViewById(R.id.editTextSMax);
        mBtnRandom = findViewById(R.id.buttonRandom);
        mTvResult = findViewById(R.id.textViewKetqua);
        mBtnAddRange = findViewById(R.id.buttonAddRange);
        mBtnReset = findViewById(R.id.buttonReset);
        mArrayList = new ArrayList<>();
        mRandom = new Random();

        // Task 1 : Bàn phím phải là số
        // Task 2 : Chỉ nhập tối đa là 3 số
        // Task 3 : Kiểm tra giá trị nhập vào không đủ thì thông báo
        // Task 4 : Ngoài việc click button có thể click action done để random

        mBtnAddRange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateForm()) return;

                // chuyển chuỗi thành số
                int sMax = Integer.parseInt(mTextSMax);
                int sMin = Integer.parseInt(mTextSMin);

                if (sMin >= sMax) {
                    sMax = sMin + 1;
                }
                mEdtSMax.setText(String.valueOf(sMax));
                mArrayList.clear();
                for (int i = sMin; i <= sMax; i++) {
                    mArrayList.add(i);
                }
                Toast.makeText(MainActivity.this, "Hoàn Tất Việc Thêm Giá Trị", Toast.LENGTH_SHORT).show();
                disableView(mBtnAddRange);
                disableView(mEdtSMax);
                disableView(mEdtSMin);

            }
        });

        mBtnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mArrayList.size() > 0) {
                    int index = mRandom.nextInt(mArrayList.size());
                    int value = mArrayList.get(index);
                    mTextResult += value + " - ";
//                    if (mArrayList.size() == 1){
//                        mTextResult += value;
//                    }
//                    else{ mTextResult += value + "-";}
//                    substring cắt chuỗi từ vị trí đầu, ví trí kết thúc
                    if (mArrayList.size() == 1) {
                        mTextResult = mTextResult.substring(0, mTextResult.length() - 3);
                    }
                    mTvResult.setText(mTextResult);
                    mArrayList.remove(index);
                } else {
                    Toast.makeText(MainActivity.this, "Hết số Random", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // XÓa dữ liệu
                mEdtSMin.setText("");
                mEdtSMax.setText("");
                mArrayList.clear();
                mTvResult.setText("");
                mTextResult = "";
                //Bật tương tác cho view
                enableView(mBtnAddRange);
                enableView(mBtnReset);
                enableView(mBtnRandom);
            }
        });{

    }
//        mEdtSMax.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int actionId , KeyEvent keyEvent) {
//                if (actionId == EditorInfo.IME_ACTION_DONE){
//                    validateForm();
//
//                    int sMax = Integer.parseInt(mTextSMax);
//                    int sMin = Integer.parseInt(mTextSMin);
//
//                    if (sMin >= sMax){
//                        sMax = sMin + 1;
//                    }
//                    mEdtSMax.setText(String.valueOf(sMax));
//
//
//                    mRandom = new Random();
//                    int value = mRandom.nextInt(sMax - sMin + 1) + sMin;
//
//                    mTextResult += value + " - ";
//
//                    mTvResult.setText(mTextResult);
//                }
//                return true;
//            }
//        });

        // Khởi tạo mảng
//        ArrayList<String> arrNames = new ArrayList<>();
//        // Thêm dữ liệu
//        arrNames.add("Teo");
//        arrNames.add("Tí");
//        // Xóa dữ phần tử
//        arrNames.remove(1); // Xóa tí
//        // Cập nhật phần tử
//        arrNames.set(0 , "Tèo");

        // Task 1 : Add range xử lý validate và add dữ liệu vào  mảng
        // Task 2 : Reset sẽ xóa dữ liệu edittext , mảng , kết quả



    }
    // Cách định 1 phương thức
    // 1 : Phạm vi hoạt động
    // 2 : Kiểu dữ liệu trả về
    // 3 : Tên phương thức

    // Xử lý giá trị đầu vào từ edittext
    private boolean validateForm(){
        mTextSMin = mEdtSMin.getText().toString();
        mTextSMax = mEdtSMax.getText().toString();

        if (mTextSMin.isEmpty() || mTextSMax.isEmpty()){
            Toast.makeText(MainActivity.this, "Bạn chưa nhập đủ thông tin!!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void enableView(View v){
        v.setEnabled(true);
    }
    private void disableView(View v){
        v.setEnabled(false);
    }
}