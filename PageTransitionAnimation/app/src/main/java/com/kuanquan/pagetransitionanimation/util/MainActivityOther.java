package com.kuanquan.pagetransitionanimation.util;

import android.app.SharedElementCallback;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kuanquan.pagetransitionanimation.R;
import com.kuanquan.pagetransitionanimation.adapter.MyAdapter;
import com.kuanquan.pagetransitionanimation.elementspage.ShareElementsActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivityOther extends AppCompatActivity {

    private List<String> datas;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datas = new ArrayList<>();
        datas.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2478350582,3338695212&fm=26&gp=0.jpg");
        datas.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3485183293,847227336&fm=26&gp=0.jpg");
        datas.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2037944750,611917901&fm=26&gp=0.jpg");
        datas.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1745933341,3133887881&fm=26&gp=0.jpg");
        datas.add("http://b162.photo.store.qq.com/psb?/V14EhGon4cZvmh/z2WukT5EhNE76WtOcbqPIgwM2Wxz4Tb7Nub.rDpsDgo!/b/dOaanmAaKQAA");
        datas.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3576736246,2692877583&fm=26&gp=0.jpg");
        datas.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2354651587,2307656983&fm=26&gp=0.jpg");
        datas.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3903227663,309999411&fm=26&gp=0.jpg");
        datas.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2233497321,4233551410&fm=26&gp=0.jpg");
        datas.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3355332996,90856357&fm=26&gp=0.jpg");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        myAdapter = new MyAdapter(this, datas);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        myAdapter.setOnClickListener(new MyAdapter.OnClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(MainActivityOther.this, ShareElementsActivity.class);
//                Intent intent = new Intent(MainActivity.this, DetailActivity2.class);
                intent.putExtra("url", (Serializable) datas);
                intent.putExtra("index", position);
//                Bundle options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle();
//                Bundle options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, v, "sharedView").toBundle();
                Bundle options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivityOther.this, v, datas.get(position)).toBundle();
                startActivity(intent,options);
//                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, v, "sharedView").toBundle());
//                startActivity(intent);
            }
        });

//        setAnimator(gridLayoutManager);
    }

    public void setAnimator(final GridLayoutManager lm){
        if (Build.VERSION.SDK_INT >= 22) {
            setExitSharedElementCallback(new SharedElementCallback() {
                @Override
                public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                    if (bundle != null) {
                        try {
                            int i = bundle.getInt("index", 0);
                            sharedElements.clear();
                            names.clear();
                            View itemView = lm.findViewByPosition(i);
                            ImageView imageView = itemView.findViewById(R.id.image);
                            names.add(imageView.getTransitionName());
                            //注意这里第二个参数，如果防止是的条目的item则动画不自然。放置对应的imageView则完美
                            sharedElements.put(datas.get(i), imageView);
                            bundle = null;
                        } catch (Exception e){

                        }
                    }
                }
            });
        }
    }

    private Bundle bundle;
    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        bundle = new Bundle(data.getExtras());
    }
}
