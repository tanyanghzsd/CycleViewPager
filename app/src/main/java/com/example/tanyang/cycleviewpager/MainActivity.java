package com.example.tanyang.cycleviewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tanyang.cycleviewpager.view.CycleViewPager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CycleViewPager.GetImageViewListener{

    private CycleViewPager cycleViewPager;

    private List<TestEntity> dataList = new ArrayList<>();

    /**
     * 需要继承{@link CycleViewPager.CycleViewPagerEntity}
     * 并将url和title传递给imageUrl和imageTitle
     */
    class TestEntity extends CycleViewPager.CycleViewPagerEntity{
        TestEntity(String url, String title){
            this.imageUrl = url;
            this.imageTitle = title;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        dataList.add(new TestEntity("http://old.hssyxx.com/zhsj/kexue-2/zutiweb/sdu03.jpg", "animal_1"));
        dataList.add(new TestEntity("http://image.dili360.com/photo/hdtj/2012/0531/59_15141287412_20120531100039.jpg", "animal_2"));
        dataList.add(new TestEntity("http://www.blirk.net/wallpapers/1366x768/family-of-gorillas-photo-1.jpg", "animal_3"));
        dataList.add(new TestEntity("http://pics.sc.chinaz.com/files/pic/pic9/201306/xpic11829.jpg", "animal_4"));
        dataList.add(new TestEntity("https://ichef-1.bbci.co.uk/news/ws/660/amz/worldservice/live/assets/images/2016/09/07/160907102522_powerful1_512x288__nocredit.jpg", "animal_5"));
        cycleViewPager.setData(dataList, mListener);
    }

    private void initView() {
        cycleViewPager = (CycleViewPager) findViewById(R.id.id_cycle_view_pager);
        cycleViewPager.setIndicators(R.drawable.ad_select, R.drawable.ad_unselect);
        cycleViewPager.setGetImageViewListener(this);
    }

    CycleViewPager.ImageCycleViewListener<TestEntity> mListener = new CycleViewPager.ImageCycleViewListener<TestEntity>() {
        @Override
        public void onImageClick(TestEntity info, int position, View imageView) {
            Toast.makeText(MainActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 此处需要返回给CyclerViewPager一个View用于显示
     */
    @Override
    public View getImageView(Context context, String url) {
        RelativeLayout rl = new RelativeLayout(context);
        ImageView imageView = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(layoutParams);
        Picasso.with(context).load(url).into(imageView);
        rl.addView(imageView);
        return rl;
    }
}
