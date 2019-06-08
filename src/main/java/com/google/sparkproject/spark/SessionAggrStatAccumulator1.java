package com.google.sparkproject.spark;

import com.google.sparkproject.constants.Constants;
import com.google.sparkproject.util.StringUtils;
import org.apache.spark.util.AccumulatorV2;


public class SessionAggrStatAccumulator1 extends AccumulatorV2<String, String> {//spark 2.0

    private String value = Constants.SESSION_COUNT + "=1|"
            + Constants.TIME_PERIOD_1s_3s + "=0|"
            + Constants.TIME_PERIOD_4s_6s + "=0|"
            + Constants.TIME_PERIOD_7s_9s + "=0|"
            + Constants.TIME_PERIOD_10s_30s + "=0|"
            + Constants.TIME_PERIOD_30s_60s + "=0|"
            + Constants.TIME_PERIOD_1m_3m + "=0|"
            + Constants.TIME_PERIOD_3m_10m + "=0|"
            + Constants.TIME_PERIOD_10m_30m + "=0|"
            + Constants.TIME_PERIOD_30m + "=0|"
            + Constants.STEP_PERIOD_1_3 + "=0|"
            + Constants.STEP_PERIOD_4_6 + "=0|"
            + Constants.STEP_PERIOD_7_9 + "=0|"
            + Constants.STEP_PERIOD_10_30 + "=0|"
            + Constants.STEP_PERIOD_30_60 + "=0|"
            + Constants.STEP_PERIOD_60 + "=0";

    @Override
    public boolean isZero() {
        return true;
    }

    @Override
    public AccumulatorV2<String, String> copy() {
        SessionAggrStatAccumulator1 copy = new SessionAggrStatAccumulator1();
        copy.value = this.value;
        return copy;
    }

    @Override
    public void reset() {
        value = Constants.SESSION_COUNT + "=0|"
                + Constants.TIME_PERIOD_1s_3s + "=0|"
                + Constants.TIME_PERIOD_4s_6s + "=0|"
                + Constants.TIME_PERIOD_7s_9s + "=0|"
                + Constants.TIME_PERIOD_10s_30s + "=0|"
                + Constants.TIME_PERIOD_30s_60s + "=0|"
                + Constants.TIME_PERIOD_1m_3m + "=0|"
                + Constants.TIME_PERIOD_3m_10m + "=0|"
                + Constants.TIME_PERIOD_10m_30m + "=0|"
                + Constants.TIME_PERIOD_30m + "=0|"
                + Constants.STEP_PERIOD_1_3 + "=0|"
                + Constants.STEP_PERIOD_4_6 + "=0|"
                + Constants.STEP_PERIOD_7_9 + "=0|"
                + Constants.STEP_PERIOD_10_30 + "=0|"
                + Constants.STEP_PERIOD_30_60 + "=0|"
                + Constants.STEP_PERIOD_60 + "=0";
    }

    @Override
    public void add(String v) {
        if (StringUtils.isEmpty(value)) {
            return;
        }
        String oldValue = StringUtils.getFieldFromConcatString(value, "\\|", v);
        if (oldValue != null) {
            // 将范围区间原有的值，累加1
            int newValue = Integer.valueOf(oldValue) + 1;
            StringUtils.setFieldInConcatString(value, "\\|", v, String.valueOf(newValue));
        }

    }

    @Override
    public void merge(AccumulatorV2<String, String> other) {
        value += other;
    }

    @Override
    public String value() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}