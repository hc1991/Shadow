package com.tencent.shadow.test.plugin.androidx_cases.lib;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThemeValueTestActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources.Theme t = getResources().newTheme();// getTheme();
        // 这里需要改下MixResources#getIdentifier，mainResources#getIdentifier返回0时，使用shareResources#getIdentifier
        // getIdentifier在获取不到资源id时返回值是0；valueTest是HostApp中的属性。
        int attrId = getResources().getIdentifier("valueTest", "attr",getPackageName());
        int applicationThemeId = getBaseContext().getApplicationInfo().theme;
        t.applyStyle(applicationThemeId, true);
        TypedArray ta = t.obtainStyledAttributes(new int[]{attrId});
        String result = String.valueOf(ta.hasValue(0));
        ta.recycle();

        ViewGroup viewGroup = UiUtil.setActivityContentView(this);
        ViewGroup item = UiUtil.makeItem(
                this,
                "themeValue",
                "themeValue",
                result
        );

        viewGroup.addView(item);
    }
}
