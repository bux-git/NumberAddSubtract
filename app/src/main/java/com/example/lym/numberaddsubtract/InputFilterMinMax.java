package com.example.lym.numberaddsubtract;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;

/**
 * EditText限制输入最大最小值
 */
public class InputFilterMinMax implements InputFilter {
    private static final String TAG = "InputFilterMinMax";
    private int min, max;
    private String minStr, maxStr;

    public InputFilterMinMax(int min, int max) {
        this.min = min;
        this.max = max;
        minStr = String.valueOf(min);
        maxStr = String.valueOf(max);
    }

    public InputFilterMinMax(String min, String max) {
        this.min = Integer.parseInt(min);
        this.max = Integer.parseInt(max);
    }

    /*
         1.输入时小于最小值 自动填充为最小值
         2.输入时大于最大值 自动填充为最大值

         情况
         1.删除    source=""   start:0 end:0 dest:10002000   dstart:7   dend:8
         2.中间插入  source:1   start:0 end:1 dest:000000   dstart:0   dend:0
         3.正常尾部输入 source:0   start:0 end:1 dest:00000   dstart:5   dend:5


     */

    /**
     *
     * @param source 输入的字符串
     * @param start 输入字符串开始位置
     * @param end  输入字符串结束位置
     * @param dest EditText中的字符串
     * @param dstart 插入的开始位置
     * @param dend 插入结束位置
     * @return 返回输入的字符串，返回null使用原来输入的字符串，""表示阻止输入 返回其他字符串表示替换掉输入的字符串
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

        Log.d(TAG, "filter: source:" + source + "   start:" + start + " end:" + end + " dest:" + dest + "   dstart:" + dstart + "   dend:" + dend);

        if (TextUtils.isEmpty(source)) {
            //删除 不控制删除操作 存在删除后重新输入数据情况 如 min=11 max=33 删除掉输入框中数字12 重新输入22
            return null;

        } else if (dstart == dest.length()) {
            //尾部插入

            //EditText为空
            if(TextUtils.isEmpty(dest)){

            }else{

            }

        }else if(dstart<dest.length()){
            //中间或开始位置插入
        }


        return null;
    }

    private boolean isInRange(int min, int max, int c) {
        return max > min ? c >= min && c <= max : c >= max && c <= min;
    }

}