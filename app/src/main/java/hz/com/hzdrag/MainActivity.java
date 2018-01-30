package hz.com.hzdrag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nineoldandroids.view.ViewHelper;

import hz.com.hzdrag.base.BaseActivity;
import hz.com.hzdrag.widget.DragLayout;

public class MainActivity extends BaseActivity {

    private DragLayout drag_layout;
    private ImageView top_bar_icon;
    private long firstTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setStatusBar();
        initView();
        initListener();

    }

    public DragLayout getDrag_layout() {
        return drag_layout;
    }

    private void initView() {
        drag_layout = (DragLayout) findViewById(R.id.drag_layout);
        top_bar_icon = (ImageView) findViewById(R.id.top_bar_icon);
    }

    private void initListener() {
        drag_layout.setDragListener(new CustomDragListener());
        top_bar_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drag_layout.open();
            }
        });
    }

    class CustomDragListener implements DragLayout.DragListener{

        /**
         * 界面打开
         */
        @Override
        public void onOpen() {

        }

        /**
         * 界面关闭
         */
        @Override
        public void onClose() {

        }

        /**
         * 界面进行滑动
         * @param percent
         */
        @Override
        public void onDrag(float percent) {
            ViewHelper.setAlpha(top_bar_icon,1-percent);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(drag_layout.getStatus() == DragLayout.Status.Open){
                drag_layout.close();
                return false;
            }else{
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;
                    return true;
                } else {
                    System.exit(0);
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
