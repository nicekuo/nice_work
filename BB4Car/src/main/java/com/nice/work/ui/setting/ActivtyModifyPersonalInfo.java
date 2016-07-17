package com.nice.work.ui.setting;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.core.bean.BaseBean;
import com.core.util.ToastUtil;
import com.nice.work.R;
import com.nice.work.background.RequestAPI;
import com.nice.work.core.AbstractActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${nice} on ${2016年04月29日14:09:09}.
 */

@EActivity(R.layout.activity_modify_info)
public class ActivtyModifyPersonalInfo extends AbstractActivity {


    public static final String kRealName = "realname";

    public static final String kNickName = "nickname";

    public static final String kGender = "sex";

    public static final String kBorn = "born";

    public static final String kModityKey = "modify_key";

    @Extra
    String kModifyType;
    @Extra String beforeValue;

    @ViewById(R.id.editView)
    EditText editView;


    @ViewById(R.id.date)
    DatePicker datePicker;

    @ViewById(R.id.group)
    RadioGroup group;

    private String dateStr;


    @AfterViews
    void init() {
        editView.setText(beforeValue);
        setTitleRightButtonText("修改", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modifyInfo();
            }
        });
        String title = "修改信息";
        switch (kModifyType) {
            case kRealName:
                title = "修改真实姓名";
                break;
            case kBorn:
                title = "修改出生日期";
                datePicker.setVisibility(View.VISIBLE);
                editView.setEnabled(false);
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int monthOfYear = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                dateStr = year + "-" + monthOfYear + "-" + dayOfMonth;
                editView.setText(dateStr);
                datePicker.init(year, monthOfYear, dayOfMonth, new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                        dateStr = i + "-" + i1 + "-" + i2;
                        editView.setText(dateStr);
                    }
                });
                break;
            case kGender:
                title = "修改性别";
                initRadioGroup();
                group.setVisibility(View.VISIBLE);
                editView.setEnabled(false);
                break;
            case kNickName:
                title = "修改昵称";
                break;
        }
        setTitleName(title);
        editView.setHint(title);
    }


    private void initRadioGroup() {
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int buttonid = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) ActivtyModifyPersonalInfo.this.findViewById(buttonid);
                editView.setText(radioButton.getText().toString());
            }
        });
    }


    private void modifyInfo() {
        final String value = editView.getText().toString();
        if (TextUtils.isEmpty(value)) {
            ToastUtil.showToastMessage(ActivtyModifyPersonalInfo.this, "还没有填写信息呢!");
            return;
        }
        Map<String,String> params = new HashMap<>();
        params.put(kModifyType, editView.getText().toString());
        new NiceAsyncTask(false) {

            @Override
            public void loadSuccess(BaseBean bean) {
                Intent intent = new Intent();
                intent.putExtra(kModityKey, value);
                setResult(RESULT_OK, intent);
                finish();
            }

            @Override
            public void exception() {

            }
        }.post(true, RequestAPI.API_MODIFY_INFO, params, BaseBean.class);
    }


    @Override
    protected void onClickBack() {
        finish();
    }
}
