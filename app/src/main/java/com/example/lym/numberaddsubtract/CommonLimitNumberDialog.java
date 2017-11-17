package com.example.lym.numberaddsubtract;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * @Description：
 * @author：Bux on 2017/11/14 10:13
 * @email: 471025316@qq.com
 */

public class CommonLimitNumberDialog extends Dialog implements
        View.OnClickListener {
    private static final String TAG = "CommonLimitNumberDialog";
    private String mTitleText = "设置众筹数量";
    private String mCancelText = "取消";
    private String mConfirmText = "确定";
    private int mMinNum = 1;//最小值
    private int mMaxNum = Integer.MAX_VALUE;//最大值
    private int mCurrent = 1;//当前值


    private View mDialogView;

    private TextView titleTV;
    private TextView cancelButt;
    private TextView confirmButt;

    ImageView mIvSubtract;//－号
    ImageView mIvAdd;//+
    EditText mEtSPNumber;//输入数量

    private AnimationSet mModalInAnim;

    private Context context;
    private OnDialogListener mDialogListener;

    public CommonLimitNumberDialog(Context context) {
        super(context, R.style.alert_dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common_limit_number_layout);
        cancelButt = findViewById(R.id.mCancelButt);
        confirmButt = findViewById(R.id.mConfirmButt);
        titleTV = findViewById(R.id.titleTV);

        mIvSubtract = findViewById(R.id.b2b_subtract);
        mIvAdd = findViewById(R.id.b2b_add);
        mEtSPNumber = findViewById(R.id.sp_number);

        mDialogView = getWindow().getDecorView().findViewById(
                android.R.id.content);
        mModalInAnim = (AnimationSet) AnimationUtils.loadAnimation(
                getContext(), R.anim.modal_in);

        confirmButt.setOnClickListener(this);
        cancelButt.setOnClickListener(this);

        mIvSubtract.setOnClickListener(this);
        mIvAdd.setOnClickListener(this);

        //mEtSPNumber.setFilters(new InputFilter[]{new InputFilterMinMax(mMinNum, mMaxNum)});
        mEtSPNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mEtSPNumber.setSelection(s.length());
                if (!TextUtils.isEmpty(s)) {
                    mCurrent = Integer.parseInt(s.toString());
                }
            }
        });

        setTitleText(mTitleText);
        setCurrentNumber(mCurrent);
        setCancelText(mCancelText);
        setConfirmText(mConfirmText);


        Log.d(TAG, "onCreate: ");

    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");
        mDialogView.startAnimation(mModalInAnim);
        //设置按钮状态
        checkNumber(mCurrent);

    }

    /**
     * 设置初始化数目
     *
     * @param num
     * @return
     */
    public CommonLimitNumberDialog setCurrentNumber(int num) {
        mCurrent = num;
        if (mEtSPNumber != null) {
            mEtSPNumber.setText(String.valueOf(mCurrent));
        }
        return this;
    }

    /**
     * 设置标题
     *
     * @param text
     * @return
     */
    public CommonLimitNumberDialog setTitleText(String text) {
        mTitleText = text;
        if (titleTV != null && mTitleText != null) {
            titleTV.setText(mTitleText);
        } else {
            if (titleTV != null) {
                titleTV.setVisibility(View.GONE);
                mEtSPNumber.setGravity(Gravity.CENTER);
            }

        }
        return this;
    }

    /**
     * 设置取消按钮文字
     *
     * @param text
     * @return
     */
    public CommonLimitNumberDialog setCancelText(String text) {
        mCancelText = text;
        if (cancelButt != null && mCancelText != null) {
            cancelButt.setText(mCancelText);
        }
        return this;
    }

    /**
     * 设置确认按钮文字
     *
     * @param text
     * @return
     */
    public CommonLimitNumberDialog setConfirmText(String text) {
        mConfirmText = text;
        if (confirmButt != null && mConfirmText != null) {
            confirmButt.setText(mConfirmText);
        }
        return this;
    }

    /**
     * 设置最小值
     *
     * @param minNum
     * @return
     */
    public CommonLimitNumberDialog setMinNum(int minNum) {
        mMinNum = minNum;
        return this;
    }

    /**
     * 最大值
     *
     * @param maxNum
     * @return
     */
    public CommonLimitNumberDialog setMaxNum(int maxNum) {
        mMaxNum = maxNum;
        return this;
    }

    /**
     * 设置回调
     *
     * @param dialogListener
     */
    public CommonLimitNumberDialog setOnDialogListener(OnDialogListener dialogListener) {
        mDialogListener = dialogListener;
        return this;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.mCancelButt) {
            if (mDialogListener != null) {
                mDialogListener.onCancelClick();

            }
            this.dismiss();
        } else if (v.getId() == R.id.mConfirmButt) {
            if (mDialogListener != null) {
                String result = mEtSPNumber.getText().toString();
                if (TextUtils.isEmpty(result)) {
                    return;
                }
                int num = Integer.parseInt(result);
                if (num < mMinNum || num > mMaxNum) {//检测输入框中数字
                    checkNumber(num);
                } else {
                    mDialogListener.onConfirmClick(num);
                    mEtSPNumber.setCursorVisible(false);//动态代码设置隐藏
                    InputMethodManager im = (InputMethodManager) context.getSystemService(Context
                            .INPUT_METHOD_SERVICE);
                    im.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    this.dismiss();
                }

            }


        } else if (v.getId() == R.id.b2b_add) {//加1

            checkNumber(++mCurrent);

        } else if (v.getId() == R.id.b2b_subtract) {//减1

            checkNumber(--mCurrent);


        }
    }


    /**
     * 检查数字
     *
     * @param num
     */
    private void checkNumber(int num) {

        mIvSubtract.setEnabled(mCurrent >= mMinNum);
        mIvAdd.setEnabled(mCurrent <= mMaxNum);

        if (num < mMinNum) {
            mDialogListener.onLessMin();
            mCurrent = mMinNum;

        } else if (num > mMaxNum) {
            mDialogListener.onMoreMax();
            mCurrent = mMaxNum;

        } else {
            mCurrent = num;
        }

        mEtSPNumber.setText(String.valueOf(mCurrent));
    }



    /**
     * 回调接口
     */
    public interface OnDialogListener {
        //取消
        void onCancelClick();

        //确定
        void onConfirmClick(int selectNum);

        //小于最小值

        void onLessMin();

        //大于最大值
        void onMoreMax();
    }

}


